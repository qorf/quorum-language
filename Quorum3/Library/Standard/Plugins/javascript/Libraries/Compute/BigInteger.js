// Copyright 2018 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the “License”);
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// <https://apache.org/licenses/LICENSE-2.0>.

class JSBI extends Array {
  constructor(length, sign) {
    if (length > JSBI.__kMaxLength) {
      throw new RangeError('Maximum BigInt size exceeded');
    }
    super(length);
    this.sign = sign;
  }

  static BigInt(arg) {
    if (typeof arg === 'number') {
      if (arg === 0) return JSBI.__zero();
      if ((arg | 0) === arg) {
        if (arg < 0) {
          return JSBI.__oneDigit(-arg, true);
        }
        return JSBI.__oneDigit(arg, false);
      }
      if (!Number.isFinite(arg) || Math.floor(arg) !== arg) {
        throw new RangeError('The number ' + arg + ' cannot be converted to ' +
                             'BigInt because it is not an integer');
      }
      return JSBI.__fromDouble(arg);
    } else if (typeof arg === 'string') {
      const result = JSBI.__fromString(arg);
      if (result === null) {
        throw new SyntaxError('Cannot convert ' + arg + ' to a BigInt');
      }
      return result;
    } else if (typeof arg === 'boolean') {
      if (arg === true) {
        return JSBI.__oneDigit(1, false);
      }
      return JSBI.__zero();
    } else if (typeof arg === 'object') {
      if (arg.constructor === JSBI) return arg;
      const primitive = JSBI.__toPrimitive(arg);
      return JSBI.BigInt(primitive);
    }
    throw new TypeError('Cannot convert ' + arg + ' to a BigInt');
  }

  toDebugString() {
    const result = ['BigInt['];
    for (const digit of this) {
      result.push((digit ? (digit >>> 0).toString(16) : digit) + ', ');
    }
    result.push(']');
    return result.join('');
  }

  toString(radix = 10) {
    if (radix < 2 || radix > 36) {
      throw new RangeError(
          'toString() radix argument must be between 2 and 36');
    }
    if (this.length === 0) return '0';
    if ((radix & (radix - 1)) === 0) {
      return JSBI.__toStringBasePowerOfTwo(this, radix);
    }
    return JSBI.__toStringGeneric(this, radix, false);
  }

  // Equivalent of "Number(my_bigint)" in the native implementation.
  static toNumber(x) {
    const xLength = x.length;
    if (xLength === 0) return 0;
    if (xLength === 1) {
      const value = x.__unsignedDigit(0);
      return x.sign ? -value : value;
    }
    const xMsd = x.__digit(xLength - 1);
    const msdLeadingZeros = Math.clz32(xMsd);
    const xBitLength = xLength * 32 - msdLeadingZeros;
    if (xBitLength > 1024) return x.sign ? -Infinity : Infinity;
    let exponent = xBitLength - 1;
    let currentDigit = xMsd;
    let digitIndex = xLength - 1;
    const shift = msdLeadingZeros + 1;
    let mantissaHigh = (shift === 32) ? 0 : currentDigit << shift;
    mantissaHigh >>>= 12;
    const mantissaHighBitsUnset = shift - 12;
    let mantissaLow = (shift >= 12) ? 0 : (currentDigit << (20 + shift));
    let mantissaLowBitsUnset = 20 + shift;
    if (mantissaHighBitsUnset > 0 && digitIndex > 0) {
      digitIndex--;
      currentDigit = x.__digit(digitIndex);
      mantissaHigh |= (currentDigit >>> (32 - mantissaHighBitsUnset));
      mantissaLow = currentDigit << mantissaHighBitsUnset;
      mantissaLowBitsUnset = mantissaHighBitsUnset;
    }
    if (mantissaLowBitsUnset > 0 && digitIndex > 0) {
      digitIndex--;
      currentDigit = x.__digit(digitIndex);
      mantissaLow |= (currentDigit >>> (32 - mantissaLowBitsUnset));
      mantissaLowBitsUnset -= 32;
    }
    const rounding = JSBI.__decideRounding(x, mantissaLowBitsUnset,
        digitIndex, currentDigit);
    if (rounding === 1 || (rounding === 0 && (mantissaLow & 1) === 1)) {
      mantissaLow = (mantissaLow + 1) >>> 0;
      if (mantissaLow === 0) {
        // Incrementing mantissaLow overflowed.
        mantissaHigh++;
        if ((mantissaHigh >>> 20) !== 0) {
          // Incrementing mantissaHigh overflowed.
          mantissaHigh = 0;
          exponent++;
          if (exponent > 1023) {
            // Incrementing the exponent overflowed.
            return x.sign ? -Infinity : Infinity;
          }
        }
      }
    }
    const signBit = x.sign ? (1 << 31) : 0;
    exponent = (exponent + 0x3FF) << 20;
    JSBI.__kBitConversionInts[1] = signBit | exponent | mantissaHigh;
    JSBI.__kBitConversionInts[0] = mantissaLow;
    return JSBI.__kBitConversionDouble[0];
  }

  // Operations.

  static unaryMinus(x) {
    if (x.length === 0) return x;
    const result = x.__copy();
    result.sign = !x.sign;
    return result;
  }

  static bitwiseNot(x) {
    if (x.sign) {
      // ~(-x) == ~(~(x-1)) == x-1
      return JSBI.__absoluteSubOne(x).__trim();
    }
    // ~x == -x-1 == -(x+1)
    return JSBI.__absoluteAddOne(x, true);
  }

  static exponentiate(x, y) {
    if (y.sign) {
      throw new RangeError('Exponent must be positive');
    }
    if (y.length === 0) {
      return JSBI.__oneDigit(1, false);
    }
    if (x.length === 0) return x;
    if (x.length === 1 && x.__digit(0) === 1) {
      // (-1) ** even_number == 1.
      if (x.sign && (y.__digit(0) & 1) === 0) {
        return JSBI.unaryMinus(x);
      }
      // (-1) ** odd_number == -1, 1 ** anything == 1.
      return x;
    }
    // For all bases >= 2, very large exponents would lead to unrepresentable
    // results.
    if (y.length > 1) throw new RangeError('BigInt too big');
    let expValue = y.__unsignedDigit(0);
    if (expValue === 1) return x;
    if (expValue >= JSBI.__kMaxLengthBits) {
      throw new RangeError('BigInt too big');
    }
    if (x.length === 1 && x.__digit(0) === 2) {
      // Fast path for 2^n.
      const neededDigits = 1 + (expValue >>> 5);
      const sign = x.sign && ((expValue & 1) !== 0);
      const result = new JSBI(neededDigits, sign);
      result.__initializeDigits();
      // All bits are zero. Now set the n-th bit.
      const msd = 1 << (expValue & 31);
      result.__setDigit(neededDigits - 1, msd);
      return result;
    }
    let result = null;
    let runningSquare = x;
    // This implicitly sets the result's sign correctly.
    if ((expValue & 1) !== 0) result = x;
    expValue >>= 1;
    for (; expValue !== 0; expValue >>= 1) {
      runningSquare = JSBI.multiply(runningSquare, runningSquare);
      if ((expValue & 1) !== 0) {
        if (result === null) {
          result = runningSquare;
        } else {
          result = JSBI.multiply(result, runningSquare);
        }
      }
    }
    return result;
  }

  static multiply(x, y) {
    if (x.length === 0) return x;
    if (y.length === 0) return y;
    let resultLength = x.length + y.length;
    if (x.__clzmsd() + y.__clzmsd() >= 32) {
      resultLength--;
    }
    const result = new JSBI(resultLength, x.sign !== y.sign);
    result.__initializeDigits();
    for (let i = 0; i < x.length; i++) {
      JSBI.__multiplyAccumulate(y, x.__digit(i), result, i);
    }
    return result.__trim();
  }

  static divide(x, y) {
    if (y.length === 0) throw new RangeError('Division by zero');
    if (JSBI.__absoluteCompare(x, y) < 0) return JSBI.__zero();
    const resultSign = x.sign !== y.sign;
    const divisor = y.__unsignedDigit(0);
    let quotient;
    if (y.length === 1 && divisor <= 0xFFFF) {
      if (divisor === 1) {
        return resultSign === x.sign ? x : JSBI.unaryMinus(x);
      }
      quotient = JSBI.__absoluteDivSmall(x, divisor, null);
    } else {
      quotient = JSBI.__absoluteDivLarge(x, y, true, false);
    }
    quotient.sign = resultSign;
    return quotient.__trim();
  }

  static remainder(x, y) {
    if (y.length === 0) throw new RangeError('Division by zero');
    if (JSBI.__absoluteCompare(x, y) < 0) return x;
    const divisor = y.__unsignedDigit(0);
    if (y.length === 1 && divisor <= 0xFFFF) {
      if (divisor === 1) return JSBI.__zero();
      const remainderDigit = JSBI.__absoluteModSmall(x, divisor);
      if (remainderDigit === 0) return JSBI.__zero();
      return JSBI.__oneDigit(remainderDigit, x.sign);
    }
    const remainder = JSBI.__absoluteDivLarge(x, y, false, true);
    remainder.sign = x.sign;
    return remainder.__trim();
  }

  static add(x, y) {
    const sign = x.sign;
    if (sign === y.sign) {
      // x + y == x + y
      // -x + -y == -(x + y)
      return JSBI.__absoluteAdd(x, y, sign);
    }
    // x + -y == x - y == -(y - x)
    // -x + y == y - x == -(x - y)
    if (JSBI.__absoluteCompare(x, y) >= 0) {
      return JSBI.__absoluteSub(x, y, sign);
    }
    return JSBI.__absoluteSub(y, x, !sign);
  }

  static subtract(x, y) {
    const sign = x.sign;
    if (sign !== y.sign) {
      // x - (-y) == x + y
      // (-x) - y == -(x + y)
      return JSBI.__absoluteAdd(x, y, sign);
    }
    // x - y == -(y - x)
    // (-x) - (-y) == y - x == -(x - y)
    if (JSBI.__absoluteCompare(x, y) >= 0) {
      return JSBI.__absoluteSub(x, y, sign);
    }
    return JSBI.__absoluteSub(y, x, !sign);
  }

  static leftShift(x, y) {
    if (y.length === 0 || x.length === 0) return x;
    if (y.sign) return JSBI.__rightShiftByAbsolute(x, y);
    return JSBI.__leftShiftByAbsolute(x, y);
  }

  static signedRightShift(x, y) {
    if (y.length === 0 || x.length === 0) return x;
    if (y.sign) return JSBI.__leftShiftByAbsolute(x, y);
    return JSBI.__rightShiftByAbsolute(x, y);
  }

  static unsignedRightShift() {
    throw new TypeError(
        'BigInts have no unsigned right shift; use >> instead');
  }

  static lessThan(x, y) {
    return JSBI.__compareToBigInt(x, y) < 0;
  }

  static lessThanOrEqual(x, y) {
    return JSBI.__compareToBigInt(x, y) <= 0;
  }

  static greaterThan(x, y) {
    return JSBI.__compareToBigInt(x, y) > 0;
  }

  static greaterThanOrEqual(x, y) {
    return JSBI.__compareToBigInt(x, y) >= 0;
  }

  static equal(x, y) {
    if (x.sign !== y.sign) return false;
    if (x.length !== y.length) return false;
    for (let i = 0; i < x.length; i++) {
      if (x.__digit(i) !== y.__digit(i)) return false;
    }
    return true;
  }

  static bitwiseAnd(x, y) {
    if (!x.sign && !y.sign) {
      return JSBI.__absoluteAnd(x, y).__trim();
    } else if (x.sign && y.sign) {
      const resultLength = Math.max(x.length, y.length) + 1;
      // (-x) & (-y) == ~(x-1) & ~(y-1) == ~((x-1) | (y-1))
      // == -(((x-1) | (y-1)) + 1)
      let result = JSBI.__absoluteSubOne(x, resultLength);
      const y1 = JSBI.__absoluteSubOne(y);
      result = JSBI.__absoluteOr(result, y1, result);
      return JSBI.__absoluteAddOne(result, true, result).__trim();
    }
    // Assume that x is the positive BigInt.
    if (x.sign) {
      [x, y] = [y, x];
    }
    // x & (-y) == x & ~(y-1) == x &~ (y-1)
    return JSBI.__absoluteAndNot(x, JSBI.__absoluteSubOne(y)).__trim();
  }

  static bitwiseXor(x, y) {
    if (!x.sign && !y.sign) {
      return JSBI.__absoluteXor(x, y).__trim();
    } else if (x.sign && y.sign) {
      // (-x) ^ (-y) == ~(x-1) ^ ~(y-1) == (x-1) ^ (y-1)
      const resultLength = Math.max(x.length, y.length);
      const result = JSBI.__absoluteSubOne(x, resultLength);
      const y1 = JSBI.__absoluteSubOne(y);
      return JSBI.__absoluteXor(result, y1, result).__trim();
    }
    const resultLength = Math.max(x.length, y.length) + 1;
    // Assume that x is the positive BigInt.
    if (x.sign) {
      [x, y] = [y, x];
    }
    // x ^ (-y) == x ^ ~(y-1) == ~(x ^ (y-1)) == -((x ^ (y-1)) + 1)
    let result = JSBI.__absoluteSubOne(y, resultLength);
    result = JSBI.__absoluteXor(result, x, result);
    return JSBI.__absoluteAddOne(result, true, result).__trim();
  }

  static bitwiseOr(x, y) {
    const resultLength = Math.max(x.length, y.length);
    if (!x.sign && !y.sign) {
      return JSBI.__absoluteOr(x, y).__trim();
    } else if (x.sign && y.sign) {
      // (-x) | (-y) == ~(x-1) | ~(y-1) == ~((x-1) & (y-1))
      // == -(((x-1) & (y-1)) + 1)
      let result = JSBI.__absoluteSubOne(x, resultLength);
      const y1 = JSBI.__absoluteSubOne(y);
      result = JSBI.__absoluteAnd(result, y1, result);
      return JSBI.__absoluteAddOne(result, true, result).__trim();
    }
    // Assume that x is the positive BigInt.
    if (x.sign) {
      [x, y] = [y, x];
    }
    // x | (-y) == x | ~(y-1) == ~((y-1) &~ x) == -(((y-1) ~& x) + 1)
    let result = JSBI.__absoluteSubOne(y, resultLength);
    result = JSBI.__absoluteAndNot(result, x, result);
    return JSBI.__absoluteAddOne(result, true, result).__trim();
  }

  // Operators.

  static ADD(x, y) {
    x = JSBI.__toPrimitive(x);
    y = JSBI.__toPrimitive(y);
    if (typeof x === 'string') {
      if (typeof y !== 'string') y = y.toString();
      return x + y;
    }
    if (typeof y === 'string') {
      return x.toString() + y;
    }
    x = JSBI.__toNumeric(x);
    y = JSBI.__toNumeric(y);
    if (JSBI.__isBigInt(x) && JSBI.__isBigInt(y)) {
      return JSBI.add(x, y);
    }
    if (typeof x === 'number' && typeof y === 'number') {
      return x + y;
    }
    throw new TypeError(
        'Cannot mix BigInt and other types, use explicit conversions');
  }

  static LT(x, y) {
    return JSBI.__compare(x, y, 0);
  }
  static LE(x, y) {
    return JSBI.__compare(x, y, 1);
  }
  static GT(x, y) {
    return JSBI.__compare(x, y, 2);
  }
  static GE(x, y) {
    return JSBI.__compare(x, y, 3);
  }

  static EQ(x, y) {
    while (true) {
      if (JSBI.__isBigInt(x)) {
        if (JSBI.__isBigInt(y)) return JSBI.equal(x, y);
        return JSBI.EQ(y, x);
      } else if (typeof x === 'number') {
        if (JSBI.__isBigInt(y)) return JSBI.__equalToNumber(y, x);
        if (typeof y !== 'object') return x == y;
        y = JSBI.__toPrimitive(y);
      } else if (typeof x === 'string') {
        if (JSBI.__isBigInt(y)) {
          x = JSBI.__fromString(x);
          if (x === null) return false;
          return JSBI.equal(x, y);
        }
        if (typeof y !== 'object') return x == y;
        y = JSBI.__toPrimitive(y);
      } else if (typeof x === 'boolean') {
        if (JSBI.__isBigInt(y)) return JSBI.__equalToNumber(y, +x);
        if (typeof y !== 'object') return x == y;
        y = JSBI.__toPrimitive(y);
      } else if (typeof x === 'symbol') {
        if (JSBI.__isBigInt(y)) return false;
        if (typeof y !== 'object') return x == y;
        y = JSBI.__toPrimitive(y);
      } else if (typeof x === 'object') {
        if (typeof y === 'object' && y.constructor !== JSBI) return x == y;
        x = JSBI.__toPrimitive(x);
      } else {
        return x == y;
      }
    }
  }

  // Helpers.

  static __zero() {
    return new JSBI(0, false);
  }

  static __oneDigit(value, sign) {
    const result = new JSBI(1, sign);
    result.__setDigit(0, value);
    return result;
  }

  __copy() {
    const result = new JSBI(this.length, this.sign);
    for (let i = 0; i < this.length; i++) {
      result[i] = this[i];
    }
    return result;
  }

  __trim() {
    let newLength = this.length;
    let last = this[newLength - 1];
    while (last === 0) {
      newLength--;
      last = this[newLength - 1];
      this.pop();
    }
    if (newLength === 0) this.sign = false;
    return this;
  }

  __initializeDigits() {
    for (let i = 0; i < this.length; i++) {
      this[i] = 0;
    }
  }

  static __decideRounding(x, mantissaBitsUnset, digitIndex, currentDigit) {
    if (mantissaBitsUnset > 0) return -1;
    let topUnconsumedBit;
    if (mantissaBitsUnset < 0) {
      topUnconsumedBit = -mantissaBitsUnset - 1;
    } else {
      // {currentDigit} fit the mantissa exactly; look at the next digit.
      if (digitIndex === 0) return -1;
      digitIndex--;
      currentDigit = x.__digit(digitIndex);
      topUnconsumedBit = 31;
    }
    // If the most significant remaining bit is 0, round down.
    let mask = 1 << topUnconsumedBit;
    if ((currentDigit & mask) === 0) return -1;
    // If any other remaining bit is set, round up.
    mask -= 1;
    if ((currentDigit & mask) !== 0) return 1;
    while (digitIndex > 0) {
      digitIndex--;
      if (x.__digit(digitIndex) !== 0) return 1;
    }
    return 0;
  }

  static __fromDouble(value) {
    const sign = value < 0;
    JSBI.__kBitConversionDouble[0] = value;
    const rawExponent = (JSBI.__kBitConversionInts[1] >>> 20) & 0x7FF;
    const exponent = rawExponent - 0x3FF;
    const digits = (exponent >>> 5) + 1;
    const result = new JSBI(digits, sign);
    const kHiddenBit = 0x00100000;
    let mantissaHigh = (JSBI.__kBitConversionInts[1] & 0xFFFFF) | kHiddenBit;
    let mantissaLow = JSBI.__kBitConversionInts[0];
    const kMantissaHighTopBit = 20;
    // 0-indexed position of most significant bit in most significant digit.
    const msdTopBit = exponent & 31;
    // Number of unused bits in the mantissa. We'll keep them shifted to the
    // left (i.e. most significant part).
    let remainingMantissaBits = 0;
    // Next digit under construction.
    let digit;
    // First, build the MSD by shifting the mantissa appropriately.
    if (msdTopBit < kMantissaHighTopBit) {
      const shift = kMantissaHighTopBit - msdTopBit;
      remainingMantissaBits = shift + 32;
      digit = mantissaHigh >>> shift;
      mantissaHigh = (mantissaHigh << (32 - shift)) |
                      (mantissaLow >>> shift);
      mantissaLow = mantissaLow << (32 - shift);
    } else if (msdTopBit === kMantissaHighTopBit) {
      remainingMantissaBits = 32;
      digit = mantissaHigh;
      mantissaHigh = mantissaLow;
    } else {
      const shift = msdTopBit - kMantissaHighTopBit;
      remainingMantissaBits = 32 - shift;
      digit = (mantissaHigh << shift) | (mantissaLow >>> (32 - shift));
      mantissaHigh = mantissaLow << shift;
    }
    result.__setDigit(digits - 1, digit);
    // Then fill in the rest of the digits.
    for (let digitIndex = digits - 2; digitIndex >= 0; digitIndex--) {
      if (remainingMantissaBits > 0) {
        remainingMantissaBits -= 32;
        digit = mantissaHigh;
        mantissaHigh = mantissaLow;
      } else {
        digit = 0;
      }
      result.__setDigit(digitIndex, digit);
    }
    return result.__trim();
  }

  static __isWhitespace(c) {
    if (c <= 0x0D && c >= 0x09) return true;
    if (c <= 0x9F) return c === 0x20;
    if (c <= 0x01FFFF) {
      return c === 0xA0 || c === 0x1680;
    }
    if (c <= 0x02FFFF) {
      c &= 0x01FFFF;
      return c <= 0x0A || c === 0x28 || c === 0x29 || c === 0x2F ||
             c === 0x5F || c === 0x1000;
    }
    return c === 0xFEFF;
  }

  static __fromString(string, radix = 0) {
    let sign = 0;
    let leadingZero = false;
    const length = string.length;
    let cursor = 0;
    if (cursor === length) return JSBI.__zero();
    let current = string.charCodeAt(cursor);
    // Skip whitespace.
    while (JSBI.__isWhitespace(current)) {
      if (++cursor === length) return JSBI.__zero();
      current = string.charCodeAt(cursor);
    }

    // Detect radix.
    if (current === 0x2B) { // '+'
      if (++cursor === length) return null;
      current = string.charCodeAt(cursor);
      sign = 1;
    } else if (current === 0x2D) { // '-'
      if (++cursor === length) return null;
      current = string.charCodeAt(cursor);
      sign = -1;
    }

    if (radix === 0) {
      radix = 10;
      if (current === 0x30) { // '0'
        if (++cursor === length) return JSBI.__zero();
        current = string.charCodeAt(cursor);
        if (current === 0x58 || current === 0x78) { // 'X' or 'x'
          radix = 16;
          if (++cursor === length) return null;
          current = string.charCodeAt(cursor);
        } else if (current === 0x4F || current === 0x6F) { // 'O' or 'o'
          radix = 8;
          if (++cursor === length) return null;
          current = string.charCodeAt(cursor);
        } else if (current === 0x42 || current === 0x62) { // 'B' or 'b'
          radix = 2;
          if (++cursor === length) return null;
          current = string.charCodeAt(cursor);
        } else {
          leadingZero = true;
        }
      }
    } else if (radix === 16) {
      if (current === 0x30) { // '0'
        // Allow "0x" prefix.
        if (++cursor === length) return JSBI.__zero();
        current = string.charCodeAt(cursor);
        if (current === 0x58 || current === 0x78) { // 'X' or 'x'
          if (++cursor === length) return null;
          current = string.charCodeAt(cursor);
        } else {
          leadingZero = true;
        }
      }
    }
    // Skip leading zeros.
    while (current === 0x30) {
      leadingZero = true;
      if (++cursor === length) return JSBI.__zero();
      current = string.charCodeAt(cursor);
    }

    // Allocate result.
    const chars = length - cursor;
    let bitsPerChar = JSBI.__kMaxBitsPerChar[radix];
    let roundup = JSBI.__kBitsPerCharTableMultiplier - 1;
    if (chars > (1 << 30) / bitsPerChar) return null;
    const bitsMin =
        (bitsPerChar * chars + roundup) >>> JSBI.__kBitsPerCharTableShift;
    const resultLength = (bitsMin + 31) >>> 5;
    const result = new JSBI(resultLength, false);

    // Parse.
    const limDigit = radix < 10 ? radix : 10;
    const limAlpha = radix > 10 ? radix - 10 : 0;

    if ((radix & (radix - 1)) === 0) {
      // Power-of-two radix.
      bitsPerChar >>= JSBI.__kBitsPerCharTableShift;
      const parts = [];
      const partsBits = [];
      let done = false;
      do {
        let part = 0;
        let bits = 0;
        while (true) {
          let d;
          if (((current - 48) >>> 0) < limDigit) {
            d = current - 48;
          } else if ((((current | 32) - 97) >>> 0) < limAlpha) {
            d = (current | 32) - 87;
          } else {
            done = true;
            break;
          }
          bits += bitsPerChar;
          part = (part << bitsPerChar) | d;
          if (++cursor === length) {
            done = true;
            break;
          }
          current = string.charCodeAt(cursor);
          if (bits + bitsPerChar > 32) break;
        }
        parts.push(part);
        partsBits.push(bits);
      } while (!done);
      JSBI.__fillFromParts(result, parts, partsBits);
    } else {
      result.__initializeDigits();
      let done = false;
      let charsSoFar = 0;
      do {
        let part = 0;
        let multiplier = 1;
        while (true) {
          let d;
          if (((current - 48) >>> 0) < limDigit) {
            d = current - 48;
          } else if ((((current | 32) - 97) >>> 0) < limAlpha) {
            d = (current | 32) - 87;
          } else {
            done = true;
            break;
          }

          const m = multiplier * radix;
          if (m > 0xFFFFFFFF) break;
          multiplier = m;
          part = part * radix + d;
          charsSoFar++;
          if (++cursor === length) {
            done = true;
            break;
          }
          current = string.charCodeAt(cursor);
        }
        roundup = JSBI.__kBitsPerCharTableMultiplier * 32 - 1;
        const digitsSoFar = (bitsPerChar * charsSoFar + roundup) >>>
                            (JSBI.__kBitsPerCharTableShift + 5);
        result.__inplaceMultiplyAdd(multiplier, part, digitsSoFar);
      } while (!done);
    }

    while (cursor !== length) {
      if (!JSBI.__isWhitespace(current)) return null;
      current = string.charCodeAt(cursor++);
    }

    // Get result.
    if (sign !== 0 && radix !== 10) return null;
    result.sign = (sign === -1);
    return result.__trim();
  }

  static __fillFromParts(result, parts, partsBits) {
    let digitIndex = 0;
    let digit = 0;
    let bitsInDigit = 0;
    for (let i = parts.length - 1; i >= 0; i--) {
      const part = parts[i];
      const partBits = partsBits[i];
      digit |= (part << bitsInDigit);
      bitsInDigit += partBits;
      if (bitsInDigit === 32) {
        result.__setDigit(digitIndex++, digit);
        bitsInDigit = 0;
        digit = 0;
      } else if (bitsInDigit > 32) {
        result.__setDigit(digitIndex++, digit);
        bitsInDigit -= 32;
        digit = part >>> (partBits - bitsInDigit);
      }
    }
    if (digit !== 0) {
      if (digitIndex >= result.length) throw new Error('implementation bug');
      result.__setDigit(digitIndex++, digit);
    }
    for (; digitIndex < result.length; digitIndex++) {
      result.__setDigit(digitIndex, 0);
    }
  }

  static __toStringBasePowerOfTwo(x, radix) {
    const length = x.length;
    let bits = radix - 1;
    bits = ((bits >>> 1) & 0x55) + (bits & 0x55);
    bits = ((bits >>> 2) & 0x33) + (bits & 0x33);
    bits = ((bits >>> 4) & 0x0F) + (bits & 0x0F);
    const bitsPerChar = bits;
    const charMask = radix - 1;
    const msd = x.__digit(length - 1);
    const msdLeadingZeros = Math.clz32(msd);
    const bitLength = length * 32 - msdLeadingZeros;
    let charsRequired =
        ((bitLength + bitsPerChar - 1) / bitsPerChar) | 0;
    if (x.sign) charsRequired++;
    if (charsRequired > (1 << 28)) throw new Error('string too long');
    const result = new Array(charsRequired);
    let pos = charsRequired - 1;
    let digit = 0;
    let availableBits = 0;
    for (let i = 0; i < length - 1; i++) {
      const newDigit = x.__digit(i);
      const current = (digit | (newDigit << availableBits)) & charMask;
      result[pos--] = JSBI.__kConversionChars[current];
      const consumedBits = bitsPerChar - availableBits;
      digit = newDigit >>> consumedBits;
      availableBits = 32 - consumedBits;
      while (availableBits >= bitsPerChar) {
        result[pos--] = JSBI.__kConversionChars[digit & charMask];
        digit >>>= bitsPerChar;
        availableBits -= bitsPerChar;
      }
    }
    const current = (digit | (msd << availableBits)) & charMask;
    result[pos--] = JSBI.__kConversionChars[current];
    digit = msd >>> (bitsPerChar - availableBits);
    while (digit !== 0) {
      result[pos--] = JSBI.__kConversionChars[digit & charMask];
      digit >>>= bitsPerChar;
    }
    if (x.sign) result[pos--] = '-';
    if (pos !== -1) throw new Error('implementation bug');
    return result.join('');
  }

  static __toStringGeneric(x, radix, isRecursiveCall) {
    const length = x.length;
    if (length === 0) return '';
    if (length === 1) {
      let result = x.__unsignedDigit(0).toString(radix);
      if (isRecursiveCall === false && x.sign) {
        result = '-' + result;
      }
      return result;
    }
    const bitLength = length * 32 - Math.clz32(x.__digit(length - 1));
    const maxBitsPerChar = JSBI.__kMaxBitsPerChar[radix];
    const minBitsPerChar = maxBitsPerChar - 1;
    let charsRequired = bitLength * JSBI.__kBitsPerCharTableMultiplier;
    charsRequired += minBitsPerChar - 1;
    charsRequired = (charsRequired / minBitsPerChar) | 0;
    const secondHalfChars = (charsRequired + 1) >> 1;
    // Divide-and-conquer: split by a power of {radix} that's approximately
    // the square root of {x}, then recurse.
    const conqueror = JSBI.exponentiate(JSBI.__oneDigit(radix, false),
        JSBI.__oneDigit(secondHalfChars, false));
    let quotient;
    let secondHalf;
    const divisor = conqueror.__unsignedDigit(0);
    if (conqueror.length === 1 && divisor <= 0xFFFF) {
      quotient = new JSBI(x.length, false);
      quotient.__initializeDigits();
      let remainder = 0;
      for (let i = x.length * 2 - 1; i >= 0; i--) {
        const input = (remainder << 16) | x.__halfDigit(i);
        quotient.__setHalfDigit(i, (input / divisor) | 0);
        remainder = (input % divisor) | 0;
      }
      secondHalf = remainder.toString(radix);
    } else {
      const divisionResult = JSBI.__absoluteDivLarge(x, conqueror, true, true);
      quotient = divisionResult.quotient;
      const remainder = divisionResult.remainder.__trim();
      secondHalf = JSBI.__toStringGeneric(remainder, radix, true);
    }
    quotient.__trim();
    let firstHalf = JSBI.__toStringGeneric(quotient, radix, true);
    while (secondHalf.length < secondHalfChars) {
      secondHalf = '0' + secondHalf;
    }
    if (isRecursiveCall === false && x.sign) {
      firstHalf = '-' + firstHalf;
    }
    return firstHalf + secondHalf;
  }

  static __unequalSign(leftNegative) {
    return leftNegative ? -1 : 1;
  }
  static __absoluteGreater(bothNegative) {
    return bothNegative ? -1 : 1;
  }
  static __absoluteLess(bothNegative) {
    return bothNegative ? 1 : -1;
  }

  static __compareToBigInt(x, y) {
    const xSign = x.sign;
    if (xSign !== y.sign) return JSBI.__unequalSign(xSign);
    const result = JSBI.__absoluteCompare(x, y);
    if (result > 0) return JSBI.__absoluteGreater(xSign);
    if (result < 0) return JSBI.__absoluteLess(xSign);
    return 0;
  }

  static __compareToNumber(x, y) {
    if (y | 0 === 0) {
      const xSign = x.sign;
      const ySign = (y < 0);
      if (xSign !== ySign) return JSBI.__unequalSign(xSign);
      if (x.length === 0) {
        if (ySign) throw new Error('implementation bug');
        return y === 0 ? 0 : -1;
      }
      // Any multi-digit BigInt is bigger than an int32.
      if (x.length > 1) return JSBI.__absoluteGreater(xSign);
      const yAbs = Math.abs(y);
      const xDigit = x.__unsignedDigit(0);
      if (xDigit > yAbs) return JSBI.__absoluteGreater(xSign);
      if (xDigit < yAbs) return JSBI.__absoluteLess(xSign);
      return 0;
    }
    return JSBI.__compareToDouble(x, y);
  }

  static __compareToDouble(x, y) {
    if (y !== y) return y; // NaN.
    if (y === Infinity) return -1;
    if (y === -Infinity) return 1;
    const xSign = x.sign;
    const ySign = (y < 0);
    if (xSign !== ySign) return JSBI.__unequalSign(xSign);
    if (y === 0) {
      throw new Error('implementation bug: should be handled elsewhere');
    }
    if (x.length === 0) return -1;
    JSBI.__kBitConversionDouble[0] = y;
    const rawExponent = (JSBI.__kBitConversionInts[1] >>> 20) & 0x7FF;
    if (rawExponent === 0x7FF) {
      throw new Error('implementation bug: handled elsewhere');
    }
    const exponent = rawExponent - 0x3FF;
    if (exponent < 0) {
      // The absolute value of y is less than 1. Only 0n has an absolute
      // value smaller than that, but we've already covered that case.
      return JSBI.__absoluteGreater(xSign);
    }
    const xLength = x.length;
    let xMsd = x.__digit(xLength - 1);
    const msdLeadingZeros = Math.clz32(xMsd);
    const xBitLength = xLength * 32 - msdLeadingZeros;
    const yBitLength = exponent + 1;
    if (xBitLength < yBitLength) return JSBI.__absoluteLess(xSign);
    if (xBitLength > yBitLength) return JSBI.__absoluteGreater(xSign);
    // Same sign, same bit length. Shift mantissa to align with x and compare
    // bit for bit.
    const kHiddenBit = 0x00100000;
    let mantissaHigh = (JSBI.__kBitConversionInts[1] & 0xFFFFF) | kHiddenBit;
    let mantissaLow = JSBI.__kBitConversionInts[0];
    const kMantissaHighTopBit = 20;
    const msdTopBit = 31 - msdLeadingZeros;
    if (msdTopBit !== ((xBitLength - 1) % 31)) {
      throw new Error('implementation bug');
    }
    let compareMantissa; // Shifted chunk of mantissa.
    let remainingMantissaBits = 0;
    // First, compare most significant digit against beginning of mantissa.
    if (msdTopBit < kMantissaHighTopBit) {
      const shift = kMantissaHighTopBit - msdTopBit;
      remainingMantissaBits = shift + 32;
      compareMantissa = mantissaHigh >>> shift;
      mantissaHigh = (mantissaHigh << (32 - shift)) | (mantissaLow >>> shift);
      mantissaLow = mantissaLow << (32 - shift);
    } else if (msdTopBit === kMantissaHighTopBit) {
      remainingMantissaBits = 32;
      compareMantissa = mantissaHigh;
      mantissaHigh = mantissaLow;
    } else {
      const shift = msdTopBit - kMantissaHighTopBit;
      remainingMantissaBits = 32 - shift;
      compareMantissa =
          (mantissaHigh << shift) | (mantissaLow >>> (32 - shift));
      mantissaHigh = mantissaLow << shift;
    }
    xMsd = xMsd >>> 0;
    compareMantissa = compareMantissa >>> 0;
    if (xMsd > compareMantissa) return JSBI.__absoluteGreater(xSign);
    if (xMsd < compareMantissa) return JSBI.__absoluteLess(xSign);
    // Then, compare additional digits against remaining mantissa bits.
    for (let digitIndex = xLength - 2; digitIndex >= 0; digitIndex--) {
      if (remainingMantissaBits > 0) {
        remainingMantissaBits -= 32;
        compareMantissa = mantissaHigh >>> 0;
        mantissaHigh = mantissaLow;
        mantissaLow = 0;
      } else {
        compareMantissa = 0;
      }
      const digit = x.__unsignedDigit(digitIndex);
      if (digit > compareMantissa) return JSBI.__absoluteGreater(xSign);
      if (digit < compareMantissa) return JSBI.__absoluteLess(xSign);
    }
    // Integer parts are equal; check whether {y} has a fractional part.
    if (mantissaHigh !== 0 || mantissaLow !== 0) {
      if (remainingMantissaBits === 0) throw new Error('implementation bug');
      return JSBI.__absoluteLess(xSign);
    }
    return 0;
  }

  static __equalToNumber(x, y) {
    if (y | 0 === y) {
      if (y === 0) return x.length === 0;
      // Any multi-digit BigInt is bigger than an int32.
      return (x.length === 1) && (x.sign === (y < 0)) &&
             (x.__unsignedDigit(0) === Math.abs(y));
    }
    return JSBI.__compareToDouble(x, y) === 0;
  }

  // Comparison operations, chosen such that "op ^ 2" reverses direction:
  // 0 - lessThan
  // 1 - lessThanOrEqual
  // 2 - greaterThan
  // 3 - greaterThanOrEqual
  static __comparisonResultToBool(result, op) {
    switch (op) {
      case 0: return result < 0;
      case 1: return result <= 0;
      case 2: return result > 0;
      case 3: return result >= 0;
    }
    throw new Error('unreachable');
  }

  static __compare(x, y, op) {
    x = JSBI.__toPrimitive(x);
    y = JSBI.__toPrimitive(y);
    if (typeof x === 'string' && typeof y === 'string') {
      switch (op) {
        case 0: return x < y;
        case 1: return x <= y;
        case 2: return x > y;
        case 3: return x >= y;
      }
    }
    if (JSBI.__isBigInt(x) && typeof y === 'string') {
      y = JSBI.__fromString(y);
      if (y === null) return false;
      return JSBI.__comparisonResultToBool(JSBI.__compareToBigInt(x, y), op);
    }
    if (typeof x === 'string' && JSBI.__isBigInt(y)) {
      x = JSBI.__fromString(x);
      if (x === null) return false;
      return JSBI.__comparisonResultToBool(JSBI.__compareToBigInt(x, y), op);
    }
    x = JSBI.__toNumeric(x);
    y = JSBI.__toNumeric(y);
    if (JSBI.__isBigInt(x)) {
      if (JSBI.__isBigInt(y)) {
        return JSBI.__comparisonResultToBool(JSBI.__compareToBigInt(x, y), op);
      }
      if (typeof y !== 'number') throw new Error('implementation bug');
      return JSBI.__comparisonResultToBool(JSBI.__compareToNumber(x, y), op);
    }
    if (typeof x !== 'number') throw new Error('implementation bug');
    if (JSBI.__isBigInt(y)) {
      // Note that "op ^ 2" reverses the op's direction.
      return JSBI.__comparisonResultToBool(JSBI.__compareToNumber(y, x),
          op ^ 2);
    }
    if (typeof y !== 'number') throw new Error('implementation bug');
    switch (op) {
      case 0: return x < y;
      case 1: return x <= y;
      case 2: return x > y;
      case 3: return x >= y;
    }
  }

  __clzmsd() {
    return Math.clz32(this[this.length - 1]);
  }

  static __absoluteAdd(x, y, resultSign) {
    if (x.length < y.length) return JSBI.__absoluteAdd(y, x, resultSign);
    if (x.length === 0) return x;
    if (y.length === 0) return x.sign === resultSign ? x : JSBI.unaryMinus(x);
    let resultLength = x.length;
    if (x.__clzmsd() === 0 || (y.length === x.length && y.__clzmsd() === 0)) {
      resultLength++;
    }
    const result = new JSBI(resultLength, resultSign);
    let carry = 0;
    let i = 0;
    for (; i < y.length; i++) {
      const yDigit = y.__digit(i);
      const xDigit = x.__digit(i);
      const rLow = (xDigit & 0xFFFF) + (yDigit & 0xFFFF) + carry;
      const rHigh = (xDigit >>> 16) + (yDigit >>> 16) + (rLow >>> 16);
      carry = rHigh >>> 16;
      result.__setDigit(i, (rLow & 0xFFFF) | (rHigh << 16));
    }
    for (; i < x.length; i++) {
      const xDigit = x.__digit(i);
      const rLow = (xDigit & 0xFFFF) + carry;
      const rHigh = (xDigit >>> 16) + (rLow >>> 16);
      carry = rHigh >>> 16;
      result.__setDigit(i, (rLow & 0xFFFF) | (rHigh << 16));
    }
    if (i < result.length) {
      result.__setDigit(i, carry);
    }
    return result.__trim();
  }

  static __absoluteSub(x, y, resultSign) {
    if (x.length === 0) return x;
    if (y.length === 0) return x.sign === resultSign ? x : JSBI.unaryMinus(x);
    const result = new JSBI(x.length, resultSign);
    let borrow = 0;
    let i = 0;
    for (; i < y.length; i++) {
      const xDigit = x.__digit(i);
      const yDigit = y.__digit(i);
      const rLow = (xDigit & 0xFFFF) - (yDigit & 0xFFFF) - borrow;
      borrow = (rLow >>> 16) & 1;
      const rHigh = (xDigit >>> 16) - (yDigit >>> 16) - borrow;
      borrow = (rHigh >>> 16) & 1;
      result.__setDigit(i, (rLow & 0xFFFF) | (rHigh << 16));
    }
    for (; i < x.length; i++) {
      const xDigit = x.__digit(i);
      const rLow = (xDigit & 0xFFFF) - borrow;
      borrow = (rLow >>> 16) & 1;
      const rHigh = (xDigit >>> 16) - borrow;
      borrow = (rHigh >>> 16) & 1;
      result.__setDigit(i, (rLow & 0xFFFF) | (rHigh << 16));
    }
    return result.__trim();
  }

  static __absoluteAddOne(x, sign, result = null) {
    const inputLength = x.length;
    if (result === null) {
      result = new JSBI(inputLength, sign);
    } else {
      result.sign = sign;
    }
    let carry = true;
    for (let i = 0; i < inputLength; i++) {
      let digit = x.__digit(i);
      const newCarry = digit === (0xFFFFFFFF | 0);
      if (carry) digit = (digit + 1) | 0;
      carry = newCarry;
      result.__setDigit(i, digit);
    }
    if (carry) {
      result.__setDigitGrow(inputLength, 1);
    }
    return result;
  }

  static __absoluteSubOne(x, resultLength) {
    const length = x.length;
    resultLength = resultLength || length;
    const result = new JSBI(resultLength, false);
    let borrow = true;
    for (let i = 0; i < length; i++) {
      let digit = x.__digit(i);
      const newBorrow = digit === 0;
      if (borrow) digit = (digit - 1) | 0;
      borrow = newBorrow;
      result.__setDigit(i, digit);
    }
    for (let i = length; i < resultLength; i++) {
      result.__setDigit(i, 0);
    }
    return result;
  }

  static __absoluteAnd(x, y, result = null) {
    let xLength = x.length;
    let yLength = y.length;
    let numPairs = yLength;
    if (xLength < yLength) {
      numPairs = xLength;
      const tmp = x;
      const tmpLength = xLength;
      x = y;
      xLength = yLength;
      y = tmp;
      yLength = tmpLength;
    }
    let resultLength = numPairs;
    if (result === null) {
      result = new JSBI(resultLength, false);
    } else {
      resultLength = result.length;
    }
    let i = 0;
    for (; i < numPairs; i++) {
      result.__setDigit(i, x.__digit(i) & y.__digit(i));
    }
    for (; i < resultLength; i++) {
      result.__setDigit(i, 0);
    }
    return result;
  }

  static __absoluteAndNot(x, y, result = null) {
    const xLength = x.length;
    const yLength = y.length;
    let numPairs = yLength;
    if (xLength < yLength) {
      numPairs = xLength;
    }
    let resultLength = xLength;
    if (result === null) {
      result = new JSBI(resultLength, false);
    } else {
      resultLength = result.length;
    }
    let i = 0;
    for (; i < numPairs; i++) {
      result.__setDigit(i, x.__digit(i) & ~y.__digit(i));
    }
    for (; i < xLength; i++) {
      result.__setDigit(i, x.__digit(i));
    }
    for (; i < resultLength; i++) {
      result.__setDigit(i, 0);
    }
    return result;
  }

  static __absoluteOr(x, y, result = null) {
    let xLength = x.length;
    let yLength = y.length;
    let numPairs = yLength;
    if (xLength < yLength) {
      numPairs = xLength;
      const tmp = x;
      const tmpLength = xLength;
      x = y;
      xLength = yLength;
      y = tmp;
      yLength = tmpLength;
    }
    let resultLength = xLength;
    if (result === null) {
      result = new JSBI(resultLength, false);
    } else {
      resultLength = result.length;
    }
    let i = 0;
    for (; i < numPairs; i++) {
      result.__setDigit(i, x.__digit(i) | y.__digit(i));
    }
    for (; i < xLength; i++) {
      result.__setDigit(i, x.__digit(i));
    }
    for (; i < resultLength; i++) {
      result.__setDigit(i, 0);
    }
    return result;
  }

  static __absoluteXor(x, y, result = null) {
    let xLength = x.length;
    let yLength = y.length;
    let numPairs = yLength;
    if (xLength < yLength) {
      numPairs = xLength;
      const tmp = x;
      const tmpLength = xLength;
      x = y;
      xLength = yLength;
      y = tmp;
      yLength = tmpLength;
    }
    let resultLength = xLength;
    if (result === null) {
      result = new JSBI(resultLength, false);
    } else {
      resultLength = result.length;
    }
    let i = 0;
    for (; i < numPairs; i++) {
      result.__setDigit(i, x.__digit(i) ^ y.__digit(i));
    }
    for (; i < xLength; i++) {
      result.__setDigit(i, x.__digit(i));
    }
    for (; i < resultLength; i++) {
      result.__setDigit(i, 0);
    }
    return result;
  }

  static __absoluteCompare(x, y) {
    const diff = x.length - y.length;
    if (diff !== 0) return diff;
    let i = x.length - 1;
    while (i >= 0 && x.__digit(i) === y.__digit(i)) i--;
    if (i < 0) return 0;
    return x.__unsignedDigit(i) > y.__unsignedDigit(i) ? 1 : -1;
  }

  static __multiplyAccumulate(multiplicand, multiplier, accumulator,
      accumulatorIndex) {
    if (multiplier === 0) return;
    const m2Low = multiplier & 0xFFFF;
    const m2High = multiplier >>> 16;
    let carry = 0;
    let highLower = 0;
    let highHigher = 0;
    for (let i = 0; i < multiplicand.length; i++, accumulatorIndex++) {
      let acc = accumulator.__digit(accumulatorIndex);
      let accLow = acc & 0xFFFF;
      let accHigh = acc >>> 16;
      const m1 = multiplicand.__digit(i);
      const m1Low = m1 & 0xFFFF;
      const m1High = m1 >>> 16;
      const rLow = Math.imul(m1Low, m2Low);
      const rMid1 = Math.imul(m1Low, m2High);
      const rMid2 = Math.imul(m1High, m2Low);
      const rHigh = Math.imul(m1High, m2High);
      accLow += highLower + (rLow & 0xFFFF);
      accHigh += highHigher + carry + (accLow >>> 16) + (rLow >>> 16) +
                 (rMid1 & 0xFFFF) + (rMid2 & 0xFFFF);
      carry = accHigh >>> 16;
      highLower = (rMid1 >>> 16) + (rMid2 >>> 16) + (rHigh & 0xFFFF) + carry;
      carry = highLower >>> 16;
      highLower &= 0xFFFF;
      highHigher = rHigh >>> 16;
      acc = (accLow & 0xFFFF) | (accHigh << 16);
      accumulator.__setDigit(accumulatorIndex, acc);
    }
    for (; carry !== 0 || highLower !== 0 || highHigher !== 0;
      accumulatorIndex++) {
      let acc = accumulator.__digit(accumulatorIndex);
      const accLow = (acc & 0xFFFF) + highLower;
      const accHigh = (acc >>> 16) + (accLow >>> 16) + highHigher + carry;
      highLower = 0;
      highHigher = 0;
      carry = accHigh >>> 16;
      acc = (accLow & 0xFFFF) | (accHigh << 16);
      accumulator.__setDigit(accumulatorIndex, acc);
    }
  }

  static __internalMultiplyAdd(source, factor, summand, n, result) {
    let carry = summand;
    let high = 0;
    for (let i = 0; i < n; i++) {
      const digit = source.__digit(i);
      const rx = Math.imul(digit & 0xFFFF, factor);
      const r0 = (rx & 0xFFFF) + high + carry;
      carry = r0 >>> 16;
      const ry = Math.imul(digit >>> 16, factor);
      const r16 = (ry & 0xFFFF) + (rx >>> 16) + carry;
      carry = r16 >>> 16;
      high = ry >>> 16;
      result.__setDigit(i, (r16 << 16) | (r0 & 0xFFFF));
    }
    if (result.length > n) {
      result.__setDigit(n++, carry + high);
      while (n < result.length) {
        result.__setDigit(n++, 0);
      }
    } else {
      if (carry + high !== 0) throw new Error('implementation bug');
    }
  }

  __inplaceMultiplyAdd(multiplier, summand, length) {
    if (length > this.length) length = this.length;
    const mLow = multiplier & 0xFFFF;
    const mHigh = multiplier >>> 16;
    let carry = 0;
    let highLower = summand & 0xFFFF;
    let highHigher = summand >>> 16;
    for (let i = 0; i < length; i++) {
      const d = this.__digit(i);
      const dLow = d & 0xFFFF;
      const dHigh = d >>> 16;
      const pLow = Math.imul(dLow, mLow);
      const pMid1 = Math.imul(dLow, mHigh);
      const pMid2 = Math.imul(dHigh, mLow);
      const pHigh = Math.imul(dHigh, mHigh);
      const rLow = highLower + (pLow & 0xFFFF);
      const rHigh = highHigher + carry + (rLow >>> 16) + (pLow >>> 16) +
                    (pMid1 & 0xFFFF) + (pMid2 & 0xFFFF);
      highLower = (pMid1 >>> 16) + (pMid2 >>> 16) + (pHigh & 0xFFFF) +
                  (rHigh >>> 16);
      carry = highLower >>> 16;
      highLower &= 0xFFFF;
      highHigher = pHigh >>> 16;
      const result = (rLow & 0xFFFF) | (rHigh << 16);
      this.__setDigit(i, result);
    }
    if (carry !== 0 || highLower !== 0 || highHigher !== 0) {
      throw new Error('implementation bug');
    }
  }

  static __absoluteDivSmall(x, divisor, quotient) {
    if (quotient === null) quotient = new JSBI(x.length, false);
    let remainder = 0;
    for (let i = x.length * 2 - 1; i >= 0; i -= 2) {
      let input = ((remainder << 16) | x.__halfDigit(i)) >>> 0;
      const upperHalf = (input / divisor) | 0;
      remainder = (input % divisor) | 0;
      input = ((remainder << 16) | x.__halfDigit(i - 1)) >>> 0;
      const lowerHalf = (input / divisor) | 0;
      remainder = (input % divisor) | 0;
      quotient.__setDigit(i >>> 1, (upperHalf << 16) | lowerHalf);
    }
    return quotient;
  }

  static __absoluteModSmall(x, divisor) {
    let remainder = 0;
    for (let i = x.length * 2 - 1; i >= 0; i--) {
      const input = ((remainder << 16) | x.__halfDigit(i)) >>> 0;
      remainder = (input % divisor) | 0;
    }
    return remainder;
  }

  static __absoluteDivLarge(dividend, divisor, wantQuotient, wantRemainder) {
    const n = divisor.__halfDigitLength();
    const n2 = divisor.length;
    const m = dividend.__halfDigitLength() - n;
    let q = null;
    if (wantQuotient) {
      q = new JSBI((m + 2) >>> 1, false);
      q.__initializeDigits();
    }
    const qhatv = new JSBI((n + 2) >>> 1, false);
    qhatv.__initializeDigits();
    // D1.
    const shift = JSBI.__clz16(divisor.__halfDigit(n - 1));
    if (shift > 0) {
      divisor = JSBI.__specialLeftShift(divisor, shift, 0 /* add no digits*/);
    }
    const u = JSBI.__specialLeftShift(dividend, shift, 1 /* add one digit */);
    // D2.
    const vn1 = divisor.__halfDigit(n - 1);
    let halfDigitBuffer = 0;
    for (let j = m; j >= 0; j--) {
      // D3.
      let qhat = 0xFFFF;
      const ujn = u.__halfDigit(j + n);
      if (ujn !== vn1) {
        const input = ((ujn << 16) | u.__halfDigit(j + n - 1)) >>> 0;
        qhat = (input / vn1) | 0;
        let rhat = (input % vn1) | 0;
        const vn2 = divisor.__halfDigit(n - 2);
        const ujn2 = u.__halfDigit(j + n - 2);
        while ((Math.imul(qhat, vn2) >>> 0) > (((rhat << 16) | ujn2) >>> 0)) {
          qhat--;
          rhat += vn1;
          if (rhat > 0xFFFF) break;
        }
      }
      // D4.
      JSBI.__internalMultiplyAdd(divisor, qhat, 0, n2, qhatv);
      let c = u.__inplaceSub(qhatv, j, n + 1);
      if (c !== 0) {
        c = u.__inplaceAdd(divisor, j, n);
        u.__setHalfDigit(j + n, u.__halfDigit(j + n) + c);
        qhat--;
      }
      if (wantQuotient) {
        if (j & 1) {
          halfDigitBuffer = qhat << 16;
        } else {
          q.__setDigit(j >>> 1, halfDigitBuffer | qhat);
        }
      }
    }
    if (wantRemainder) {
      u.__inplaceRightShift(shift);
      if (wantQuotient) {
        return {quotient: q, remainder: u};
      }
      return u;
    }
    if (wantQuotient) return q;
  }

  static __clz16(value) {
    return Math.clz32(value) - 16;
  }

  // TODO: work on full digits, like __inplaceSub?
  __inplaceAdd(summand, startIndex, halfDigits) {
    let carry = 0;
    for (let i = 0; i < halfDigits; i++) {
      const sum = this.__halfDigit(startIndex + i) +
                summand.__halfDigit(i) +
                carry;
      carry = sum >>> 16;
      this.__setHalfDigit(startIndex + i, sum);
    }
    return carry;
  }

  __inplaceSub(subtrahend, startIndex, halfDigits) {
    const fullSteps = (halfDigits - 1) >>> 1;
    let borrow = 0;
    if (startIndex & 1) {
      // this:   [..][..][..]
      // subtr.:   [..][..]
      startIndex >>= 1;
      let current = this.__digit(startIndex);
      let r0 = current & 0xFFFF;
      let i = 0;
      for (; i < fullSteps; i++) {
        const sub = subtrahend.__digit(i);
        const r16 = (current >>> 16) - (sub & 0xFFFF) - borrow;
        borrow = (r16 >>> 16) & 1;
        this.__setDigit(startIndex + i, (r16 << 16) | (r0 & 0xFFFF));
        current = this.__digit(startIndex + i + 1);
        r0 = (current & 0xFFFF) - (sub >>> 16) - borrow;
        borrow = (r0 >>> 16) & 1;
      }
      // Unrolling the last iteration gives a 5% performance benefit!
      const sub = subtrahend.__digit(i);
      const r16 = (current >>> 16) - (sub & 0xFFFF) - borrow;
      borrow = (r16 >>> 16) & 1;
      this.__setDigit(startIndex + i, (r16 << 16) | (r0 & 0xFFFF));
      const subTop = sub >>> 16;
      if (startIndex + i + 1 >= this.length) {
        throw new RangeError('out of bounds');
      }
      if ((halfDigits & 1) === 0) {
        current = this.__digit(startIndex + i + 1);
        r0 = (current & 0xFFFF) - subTop - borrow;
        borrow = (r0 >>> 16) & 1;
        this.__setDigit(startIndex + subtrahend.length,
                        (current & 0xFFFF0000) | (r0 & 0xFFFF));
      }
    } else {
      startIndex >>= 1;
      let i = 0;
      for (; i < subtrahend.length - 1; i++) {
        const current = this.__digit(startIndex + i);
        const sub = subtrahend.__digit(i);
        const r0 = (current & 0xFFFF) - (sub & 0xFFFF) - borrow;
        borrow = (r0 >>> 16) & 1;
        const r16 = (current >>> 16) - (sub >>> 16) - borrow;
        borrow = (r16 >>> 16) & 1;
        this.__setDigit(startIndex + i, (r16 << 16) | (r0 & 0xFFFF));
      }
      const current = this.__digit(startIndex + i);
      const sub = subtrahend.__digit(i);
      const r0 = (current & 0xFFFF) - (sub & 0xFFFF) - borrow;
      borrow = (r0 >>> 16) & 1;
      let r16 = 0;
      if ((halfDigits & 1) === 0) {
        r16 = (current >>> 16) - (sub >>> 16) - borrow;
        borrow = (r16 >>> 16) & 1;
      }
      this.__setDigit(startIndex + i, (r16 << 16) | (r0 & 0xFFFF));
    }
    return borrow;
  }

  __inplaceRightShift(shift) {
    if (shift === 0) return;
    let carry = this.__digit(0) >>> shift;
    const last = this.length - 1;
    for (let i = 0; i < last; i++) {
      const d = this.__digit(i + 1);
      this.__setDigit(i, (d << (32 - shift)) | carry);
      carry = d >>> shift;
    }
    this.__setDigit(last, carry);
  }

  static __specialLeftShift(x, shift, addDigit) {
    const n = x.length;
    const resultLength = n + addDigit;
    const result = new JSBI(resultLength, false);
    if (shift === 0) {
      for (let i = 0; i < n; i++) result.__setDigit(i, x.__digit(i));
      if (addDigit > 0) result.__setDigit(n, 0);
      return result;
    }
    let carry = 0;
    for (let i = 0; i < n; i++) {
      const d = x.__digit(i);
      result.__setDigit(i, (d << shift) | carry);
      carry = d >>> (32 - shift);
    }
    if (addDigit > 0) {
      result.__setDigit(n, carry);
    }
    return result;
  }

  static __leftShiftByAbsolute(x, y) {
    const shift = JSBI.__toShiftAmount(y);
    if (shift < 0) throw new RangeError('BigInt too big');
    const digitShift = shift >>> 5;
    const bitsShift = shift & 31;
    const length = x.length;
    const grow = bitsShift !== 0 &&
                 (x.__digit(length - 1) >>> (32 - bitsShift)) !== 0;
    const resultLength = length + digitShift + (grow ? 1 : 0);
    const result = new JSBI(resultLength, x.sign);
    if (bitsShift === 0) {
      let i = 0;
      for (; i < digitShift; i++) result.__setDigit(i, 0);
      for (; i < resultLength; i++) {
        result.__setDigit(i, x.__digit(i - digitShift));
      }
    } else {
      let carry = 0;
      for (let i = 0; i < digitShift; i++) result.__setDigit(i, 0);
      for (let i = 0; i < length; i++) {
        const d = x.__digit(i);
        result.__setDigit(i + digitShift, (d << bitsShift) | carry);
        carry = d >>> (32 - bitsShift);
      }
      if (grow) {
        result.__setDigit(length + digitShift, carry);
      } else {
        if (carry !== 0) throw new Error('implementation bug');
      }
    }
    return result.__trim();
  }

  static __rightShiftByAbsolute(x, y) {
    const length = x.length;
    const sign = x.sign;
    const shift = JSBI.__toShiftAmount(y);
    if (shift < 0) return JSBI.__rightShiftByMaximum(sign);
    const digitShift = shift >>> 5;
    const bitsShift = shift & 31;
    let resultLength = length - digitShift;
    if (resultLength <= 0) return JSBI.__rightShiftByMaximum(sign);
    // For negative numbers, round down if any bit was shifted out (so that
    // e.g. -5n >> 1n == -3n and not -2n). Check now whether this will happen
    // and whether itc an cause overflow into a new digit. If we allocate the
    // result large enough up front, it avoids having to do grow it later.
    let mustRoundDown = false;
    if (sign) {
      const mask = (1 << bitsShift) - 1;
      if ((x.__digit(digitShift) & mask) !== 0) {
        mustRoundDown = true;
      } else {
        for (let i = 0; i < digitShift; i++) {
          if (x.__digit(i) !== 0) {
            mustRoundDown = true;
            break;
          }
        }
      }
    }
    // If bitsShift is non-zero, it frees up bits, preventing overflow.
    if (mustRoundDown && bitsShift === 0) {
      // Overflow cannot happen if the most significant digit has unset bits.
      const msd = x.__digit(length - 1);
      const roundingCanOverflow = ~msd === 0;
      if (roundingCanOverflow) resultLength++;
    }
    let result = new JSBI(resultLength, sign);
    if (bitsShift === 0) {
      for (let i = digitShift; i < length; i++) {
        result.__setDigit(i - digitShift, x.__digit(i));
      }
    } else {
      let carry = x.__digit(digitShift) >>> bitsShift;
      const last = length - digitShift - 1;
      for (let i = 0; i < last; i++) {
        const d = x.__digit(i + digitShift + 1);
        result.__setDigit(i, (d << (32 - bitsShift)) | carry);
        carry = d >>> bitsShift;
      }
      result.__setDigit(last, carry);
    }
    if (mustRoundDown) {
      // Since the result is negative, rounding down means adding one to its
      // absolute value. This cannot overflow.
      result = JSBI.__absoluteAddOne(result, true, result);
    }
    return result.__trim();
  }

  static __rightShiftByMaximum(sign) {
    if (sign) {
      return JSBI.__oneDigit(1, true);
    }
    return JSBI.__zero();
  }

  static __toShiftAmount(x) {
    if (x.length > 1) return -1;
    const value = x.__unsignedDigit(0);
    if (value > JSBI.__kMaxLengthBits) return -1;
    return value;
  }

  static __toPrimitive(obj, hint='default') {
    if (typeof obj !== 'object') return obj;
    if (obj.constructor === JSBI) return obj;
    const exoticToPrim = obj[Symbol.toPrimitive];
    if (exoticToPrim) {
      const primitive = exoticToPrim(hint);
      if (typeof primitive !== 'object') return primitive;
      throw new TypeError('Cannot convert object to primitive value');
    }
    const valueOf = obj.valueOf;
    if (valueOf) {
      const primitive = valueOf.call(obj);
      if (typeof primitive !== 'object') return primitive;
    }
    const toString = obj.toString;
    if (toString) {
      const primitive = toString.call(obj);
      if (typeof primitive !== 'object') return primitive;
    }
    throw new TypeError('Cannot convert object to primitive value');
  }

  static __toNumeric(value) {
    if (JSBI.__isBigInt(value)) return value;
    return +value;
  }

  static __isBigInt(value) {
    return typeof value === 'object' && value.constructor === JSBI;
  }

  // Digit helpers.
  __digit(i) {
    return this[i];
  }
  __unsignedDigit(i) {
    return this[i] >>> 0;
  }
  __setDigit(i, digit) {
    this[i] = digit | 0;
  }
  __setDigitGrow(i, digit) {
    this[i] = digit | 0;
  }
  __halfDigitLength() {
    const len = this.length;
    if (this.__unsignedDigit(len - 1) <= 0xFFFF) return len * 2 - 1;
    return len*2;
  }
  __halfDigit(i) {
    return (this[i >>> 1] >>> ((i & 1) << 4)) & 0xFFFF;
  }
  __setHalfDigit(i, value) {
    const digitIndex = i >>> 1;
    const previous = this.__digit(digitIndex);
    const updated = (i & 1) ? (previous & 0xFFFF) | (value << 16)
                            : (previous & 0xFFFF0000) | (value & 0xFFFF);
    this.__setDigit(digitIndex, updated);
  }

  static __digitPow(base, exponent) {
    let result = 1;
    while (exponent > 0) {
      if (exponent & 1) result *= base;
      exponent >>>= 1;
      base *= base;
    }
    return result;
  }
}

JSBI.__kMaxLength = 1 << 25;
JSBI.__kMaxLengthBits = JSBI.__kMaxLength << 5;
// Lookup table for the maximum number of bits required per character of a
// base-N string representation of a number. To increase accuracy, the array
// value is the actual value multiplied by 32. To generate this table:
//
// for (let i = 0; i <= 36; i++) {
//   console.log(Math.ceil(Math.log2(i) * 32) + ',');
// }
JSBI.__kMaxBitsPerChar = [
  0, 0, 32, 51, 64, 75, 83, 90, 96, // 0..8
  102, 107, 111, 115, 119, 122, 126, 128, // 9..16
  131, 134, 136, 139, 141, 143, 145, 147, // 17..24
  149, 151, 153, 154, 156, 158, 159, 160, // 25..32
  162, 163, 165, 166, // 33..36
];
JSBI.__kBitsPerCharTableShift = 5;
JSBI.__kBitsPerCharTableMultiplier = 1 << JSBI.__kBitsPerCharTableShift;
JSBI.__kConversionChars = '0123456789abcdefghijklmnopqrstuvwxyz'.split('');
JSBI.__kBitConversionBuffer = new ArrayBuffer(8);
JSBI.__kBitConversionDouble = new Float64Array(JSBI.__kBitConversionBuffer);
JSBI.__kBitConversionInts = new Int32Array(JSBI.__kBitConversionBuffer);

//export default JSBI;


/*
The BigInteger class is used to represent integer values that are too large to be
stored in an integer variable. Additionally, it contains actions to perform
expected operations on these BigIntegers, such as addition, division, comparison,
and so on.

Attribute: Example

    use Libraries.Compute.BigInteger

    class Main
        action main
            text value = "2147483648"
            BigInteger largeNumber
            largeNumber:SetValue(value)
            largeNumber = largeNumber:RaiseToPower(2)
            output largeNumber:GetText()
        end
    end
*/

//var bigInt=function(undefined){"use strict";var BASE=1e7,LOG_BASE=7,MAX_INT=9007199254740992,MAX_INT_ARR=smallToArray(MAX_INT),LOG_MAX_INT=Math.log(MAX_INT);function Integer(v,radix){if(typeof v==="undefined")return Integer[0];if(typeof radix!=="undefined")return+radix===10?parseValue(v):parseBase(v,radix);return parseValue(v)}function BigInteger(value,sign){this.value=value;this.sign=sign;this.isSmall=false}BigInteger.prototype=Object.create(Integer.prototype);function SmallInteger(value){this.value=value;this.sign=value<0;this.isSmall=true}SmallInteger.prototype=Object.create(Integer.prototype);function isPrecise(n){return-MAX_INT<n&&n<MAX_INT}function smallToArray(n){if(n<1e7)return[n];if(n<1e14)return[n%1e7,Math.floor(n/1e7)];return[n%1e7,Math.floor(n/1e7)%1e7,Math.floor(n/1e14)]}function arrayToSmall(arr){trim(arr);var length=arr.length;if(length<4&&compareAbs(arr,MAX_INT_ARR)<0){switch(length){case 0:return 0;case 1:return arr[0];case 2:return arr[0]+arr[1]*BASE;default:return arr[0]+(arr[1]+arr[2]*BASE)*BASE}}return arr}function trim(v){var i=v.length;while(v[--i]===0);v.length=i+1}function createArray(length){var x=new Array(length);var i=-1;while(++i<length){x[i]=0}return x}function truncate(n){if(n>0)return Math.floor(n);return Math.ceil(n)}function add(a,b){var l_a=a.length,l_b=b.length,r=new Array(l_a),carry=0,base=BASE,sum,i;for(i=0;i<l_b;i++){sum=a[i]+b[i]+carry;carry=sum>=base?1:0;r[i]=sum-carry*base}while(i<l_a){sum=a[i]+carry;carry=sum===base?1:0;r[i++]=sum-carry*base}if(carry>0)r.push(carry);return r}function addAny(a,b){if(a.length>=b.length)return add(a,b);return add(b,a)}function addSmall(a,carry){var l=a.length,r=new Array(l),base=BASE,sum,i;for(i=0;i<l;i++){sum=a[i]-base+carry;carry=Math.floor(sum/base);r[i]=sum-carry*base;carry+=1}while(carry>0){r[i++]=carry%base;carry=Math.floor(carry/base)}return r}BigInteger.prototype.add=function(v){var n=parseValue(v);if(this.sign!==n.sign){return this.subtract(n.negate())}var a=this.value,b=n.value;if(n.isSmall){return new BigInteger(addSmall(a,Math.abs(b)),this.sign)}return new BigInteger(addAny(a,b),this.sign)};BigInteger.prototype.plus=BigInteger.prototype.add;SmallInteger.prototype.add=function(v){var n=parseValue(v);var a=this.value;if(a<0!==n.sign){return this.subtract(n.negate())}var b=n.value;if(n.isSmall){if(isPrecise(a+b))return new SmallInteger(a+b);b=smallToArray(Math.abs(b))}return new BigInteger(addSmall(b,Math.abs(a)),a<0)};SmallInteger.prototype.plus=SmallInteger.prototype.add;function subtract(a,b){var a_l=a.length,b_l=b.length,r=new Array(a_l),borrow=0,base=BASE,i,difference;for(i=0;i<b_l;i++){difference=a[i]-borrow-b[i];if(difference<0){difference+=base;borrow=1}else borrow=0;r[i]=difference}for(i=b_l;i<a_l;i++){difference=a[i]-borrow;if(difference<0)difference+=base;else{r[i++]=difference;break}r[i]=difference}for(;i<a_l;i++){r[i]=a[i]}trim(r);return r}function subtractAny(a,b,sign){var value;if(compareAbs(a,b)>=0){value=subtract(a,b)}else{value=subtract(b,a);sign=!sign}value=arrayToSmall(value);if(typeof value==="number"){if(sign)value=-value;return new SmallInteger(value)}return new BigInteger(value,sign)}function subtractSmall(a,b,sign){var l=a.length,r=new Array(l),carry=-b,base=BASE,i,difference;for(i=0;i<l;i++){difference=a[i]+carry;carry=Math.floor(difference/base);difference%=base;r[i]=difference<0?difference+base:difference}r=arrayToSmall(r);if(typeof r==="number"){if(sign)r=-r;return new SmallInteger(r)}return new BigInteger(r,sign)}BigInteger.prototype.subtract=function(v){var n=parseValue(v);if(this.sign!==n.sign){return this.add(n.negate())}var a=this.value,b=n.value;if(n.isSmall)return subtractSmall(a,Math.abs(b),this.sign);return subtractAny(a,b,this.sign)};BigInteger.prototype.minus=BigInteger.prototype.subtract;SmallInteger.prototype.subtract=function(v){var n=parseValue(v);var a=this.value;if(a<0!==n.sign){return this.add(n.negate())}var b=n.value;if(n.isSmall){return new SmallInteger(a-b)}return subtractSmall(b,Math.abs(a),a>=0)};SmallInteger.prototype.minus=SmallInteger.prototype.subtract;BigInteger.prototype.negate=function(){return new BigInteger(this.value,!this.sign)};SmallInteger.prototype.negate=function(){var sign=this.sign;var small=new SmallInteger(-this.value);small.sign=!sign;return small};BigInteger.prototype.abs=function(){return new BigInteger(this.value,false)};SmallInteger.prototype.abs=function(){return new SmallInteger(Math.abs(this.value))};function multiplyLong(a,b){var a_l=a.length,b_l=b.length,l=a_l+b_l,r=createArray(l),base=BASE,product,carry,i,a_i,b_j;for(i=0;i<a_l;++i){a_i=a[i];for(var j=0;j<b_l;++j){b_j=b[j];product=a_i*b_j+r[i+j];carry=Math.floor(product/base);r[i+j]=product-carry*base;r[i+j+1]+=carry}}trim(r);return r}function multiplySmall(a,b){var l=a.length,r=new Array(l),base=BASE,carry=0,product,i;for(i=0;i<l;i++){product=a[i]*b+carry;carry=Math.floor(product/base);r[i]=product-carry*base}while(carry>0){r[i++]=carry%base;carry=Math.floor(carry/base)}return r}function shiftLeft(x,n){var r=[];while(n-- >0)r.push(0);return r.concat(x)}function multiplyKaratsuba(x,y){var n=Math.max(x.length,y.length);if(n<=30)return multiplyLong(x,y);n=Math.ceil(n/2);var b=x.slice(n),a=x.slice(0,n),d=y.slice(n),c=y.slice(0,n);var ac=multiplyKaratsuba(a,c),bd=multiplyKaratsuba(b,d),abcd=multiplyKaratsuba(addAny(a,b),addAny(c,d));var product=addAny(addAny(ac,shiftLeft(subtract(subtract(abcd,ac),bd),n)),shiftLeft(bd,2*n));trim(product);return product}function useKaratsuba(l1,l2){return-.012*l1-.012*l2+15e-6*l1*l2>0}BigInteger.prototype.multiply=function(v){var n=parseValue(v),a=this.value,b=n.value,sign=this.sign!==n.sign,abs;if(n.isSmall){if(b===0)return Integer[0];if(b===1)return this;if(b===-1)return this.negate();abs=Math.abs(b);if(abs<BASE){return new BigInteger(multiplySmall(a,abs),sign)}b=smallToArray(abs)}if(useKaratsuba(a.length,b.length))return new BigInteger(multiplyKaratsuba(a,b),sign);return new BigInteger(multiplyLong(a,b),sign)};BigInteger.prototype.times=BigInteger.prototype.multiply;function multiplySmallAndArray(a,b,sign){if(a<BASE){return new BigInteger(multiplySmall(b,a),sign)}return new BigInteger(multiplyLong(b,smallToArray(a)),sign)}SmallInteger.prototype._multiplyBySmall=function(a){if(isPrecise(a.value*this.value)){return new SmallInteger(a.value*this.value)}return multiplySmallAndArray(Math.abs(a.value),smallToArray(Math.abs(this.value)),this.sign!==a.sign)};BigInteger.prototype._multiplyBySmall=function(a){if(a.value===0)return Integer[0];if(a.value===1)return this;if(a.value===-1)return this.negate();return multiplySmallAndArray(Math.abs(a.value),this.value,this.sign!==a.sign)};SmallInteger.prototype.multiply=function(v){return parseValue(v)._multiplyBySmall(this)};SmallInteger.prototype.times=SmallInteger.prototype.multiply;function square(a){var l=a.length,r=createArray(l+l),base=BASE,product,carry,i,a_i,a_j;for(i=0;i<l;i++){a_i=a[i];carry=0-a_i*a_i;for(var j=i;j<l;j++){a_j=a[j];product=2*(a_i*a_j)+r[i+j]+carry;carry=Math.floor(product/base);r[i+j]=product-carry*base}r[i+l]=carry}trim(r);return r}BigInteger.prototype.square=function(){return new BigInteger(square(this.value),false)};SmallInteger.prototype.square=function(){var value=this.value*this.value;if(isPrecise(value))return new SmallInteger(value);return new BigInteger(square(smallToArray(Math.abs(this.value))),false)};function divMod1(a,b){var a_l=a.length,b_l=b.length,base=BASE,result=createArray(b.length),divisorMostSignificantDigit=b[b_l-1],lambda=Math.ceil(base/(2*divisorMostSignificantDigit)),remainder=multiplySmall(a,lambda),divisor=multiplySmall(b,lambda),quotientDigit,shift,carry,borrow,i,l,q;if(remainder.length<=a_l)remainder.push(0);divisor.push(0);divisorMostSignificantDigit=divisor[b_l-1];for(shift=a_l-b_l;shift>=0;shift--){quotientDigit=base-1;if(remainder[shift+b_l]!==divisorMostSignificantDigit){quotientDigit=Math.floor((remainder[shift+b_l]*base+remainder[shift+b_l-1])/divisorMostSignificantDigit)}carry=0;borrow=0;l=divisor.length;for(i=0;i<l;i++){carry+=quotientDigit*divisor[i];q=Math.floor(carry/base);borrow+=remainder[shift+i]-(carry-q*base);carry=q;if(borrow<0){remainder[shift+i]=borrow+base;borrow=-1}else{remainder[shift+i]=borrow;borrow=0}}while(borrow!==0){quotientDigit-=1;carry=0;for(i=0;i<l;i++){carry+=remainder[shift+i]-base+divisor[i];if(carry<0){remainder[shift+i]=carry+base;carry=0}else{remainder[shift+i]=carry;carry=1}}borrow+=carry}result[shift]=quotientDigit}remainder=divModSmall(remainder,lambda)[0];return[arrayToSmall(result),arrayToSmall(remainder)]}function divMod2(a,b){var a_l=a.length,b_l=b.length,result=[],part=[],base=BASE,guess,xlen,highx,highy,check;while(a_l){part.unshift(a[--a_l]);trim(part);if(compareAbs(part,b)<0){result.push(0);continue}xlen=part.length;highx=part[xlen-1]*base+part[xlen-2];highy=b[b_l-1]*base+b[b_l-2];if(xlen>b_l){highx=(highx+1)*base}guess=Math.ceil(highx/highy);do{check=multiplySmall(b,guess);if(compareAbs(check,part)<=0)break;guess--}while(guess);result.push(guess);part=subtract(part,check)}result.reverse();return[arrayToSmall(result),arrayToSmall(part)]}function divModSmall(value,lambda){var length=value.length,quotient=createArray(length),base=BASE,i,q,remainder,divisor;remainder=0;for(i=length-1;i>=0;--i){divisor=remainder*base+value[i];q=truncate(divisor/lambda);remainder=divisor-q*lambda;quotient[i]=q|0}return[quotient,remainder|0]}function divModAny(self,v){var value,n=parseValue(v);var a=self.value,b=n.value;var quotient;if(b===0)throw new Error("Cannot divide by zero");if(self.isSmall){if(n.isSmall){return[new SmallInteger(truncate(a/b)),new SmallInteger(a%b)]}return[Integer[0],self]}if(n.isSmall){if(b===1)return[self,Integer[0]];if(b==-1)return[self.negate(),Integer[0]];var abs=Math.abs(b);if(abs<BASE){value=divModSmall(a,abs);quotient=arrayToSmall(value[0]);var remainder=value[1];if(self.sign)remainder=-remainder;if(typeof quotient==="number"){if(self.sign!==n.sign)quotient=-quotient;return[new SmallInteger(quotient),new SmallInteger(remainder)]}return[new BigInteger(quotient,self.sign!==n.sign),new SmallInteger(remainder)]}b=smallToArray(abs)}var comparison=compareAbs(a,b);if(comparison===-1)return[Integer[0],self];if(comparison===0)return[Integer[self.sign===n.sign?1:-1],Integer[0]];if(a.length+b.length<=200)value=divMod1(a,b);else value=divMod2(a,b);quotient=value[0];var qSign=self.sign!==n.sign,mod=value[1],mSign=self.sign;if(typeof quotient==="number"){if(qSign)quotient=-quotient;quotient=new SmallInteger(quotient)}else quotient=new BigInteger(quotient,qSign);if(typeof mod==="number"){if(mSign)mod=-mod;mod=new SmallInteger(mod)}else mod=new BigInteger(mod,mSign);return[quotient,mod]}BigInteger.prototype.divmod=function(v){var result=divModAny(this,v);return{quotient:result[0],remainder:result[1]}};SmallInteger.prototype.divmod=BigInteger.prototype.divmod;BigInteger.prototype.divide=function(v){return divModAny(this,v)[0]};SmallInteger.prototype.over=SmallInteger.prototype.divide=BigInteger.prototype.over=BigInteger.prototype.divide;BigInteger.prototype.mod=function(v){return divModAny(this,v)[1]};SmallInteger.prototype.remainder=SmallInteger.prototype.mod=BigInteger.prototype.remainder=BigInteger.prototype.mod;BigInteger.prototype.pow=function(v){var n=parseValue(v),a=this.value,b=n.value,value,x,y;if(b===0)return Integer[1];if(a===0)return Integer[0];if(a===1)return Integer[1];if(a===-1)return n.isEven()?Integer[1]:Integer[-1];if(n.sign){return Integer[0]}if(!n.isSmall)throw new Error("The exponent "+n.toString()+" is too large.");if(this.isSmall){if(isPrecise(value=Math.pow(a,b)))return new SmallInteger(truncate(value))}x=this;y=Integer[1];while(true){if(b&1===1){y=y.times(x);--b}if(b===0)break;b/=2;x=x.square()}return y};SmallInteger.prototype.pow=BigInteger.prototype.pow;BigInteger.prototype.modPow=function(exp,mod){exp=parseValue(exp);mod=parseValue(mod);if(mod.isZero())throw new Error("Cannot take modPow with modulus 0");var r=Integer[1],base=this.mod(mod);while(exp.isPositive()){if(base.isZero())return Integer[0];if(exp.isOdd())r=r.multiply(base).mod(mod);exp=exp.divide(2);base=base.square().mod(mod)}return r};SmallInteger.prototype.modPow=BigInteger.prototype.modPow;function compareAbs(a,b){if(a.length!==b.length){return a.length>b.length?1:-1}for(var i=a.length-1;i>=0;i--){if(a[i]!==b[i])return a[i]>b[i]?1:-1}return 0}BigInteger.prototype.compareAbs=function(v){var n=parseValue(v),a=this.value,b=n.value;if(n.isSmall)return 1;return compareAbs(a,b)};SmallInteger.prototype.compareAbs=function(v){var n=parseValue(v),a=Math.abs(this.value),b=n.value;if(n.isSmall){b=Math.abs(b);return a===b?0:a>b?1:-1}return-1};BigInteger.prototype.compare=function(v){if(v===Infinity){return-1}if(v===-Infinity){return 1}var n=parseValue(v),a=this.value,b=n.value;if(this.sign!==n.sign){return n.sign?1:-1}if(n.isSmall){return this.sign?-1:1}return compareAbs(a,b)*(this.sign?-1:1)};BigInteger.prototype.compareTo=BigInteger.prototype.compare;SmallInteger.prototype.compare=function(v){if(v===Infinity){return-1}if(v===-Infinity){return 1}var n=parseValue(v),a=this.value,b=n.value;if(n.isSmall){return a==b?0:a>b?1:-1}if(a<0!==n.sign){return a<0?-1:1}return a<0?1:-1};SmallInteger.prototype.compareTo=SmallInteger.prototype.compare;BigInteger.prototype.equals=function(v){return this.compare(v)===0};SmallInteger.prototype.eq=SmallInteger.prototype.equals=BigInteger.prototype.eq=BigInteger.prototype.equals;BigInteger.prototype.notEquals=function(v){return this.compare(v)!==0};SmallInteger.prototype.neq=SmallInteger.prototype.notEquals=BigInteger.prototype.neq=BigInteger.prototype.notEquals;BigInteger.prototype.greater=function(v){return this.compare(v)>0};SmallInteger.prototype.gt=SmallInteger.prototype.greater=BigInteger.prototype.gt=BigInteger.prototype.greater;BigInteger.prototype.lesser=function(v){return this.compare(v)<0};SmallInteger.prototype.lt=SmallInteger.prototype.lesser=BigInteger.prototype.lt=BigInteger.prototype.lesser;BigInteger.prototype.greaterOrEquals=function(v){return this.compare(v)>=0};SmallInteger.prototype.geq=SmallInteger.prototype.greaterOrEquals=BigInteger.prototype.geq=BigInteger.prototype.greaterOrEquals;BigInteger.prototype.lesserOrEquals=function(v){return this.compare(v)<=0};SmallInteger.prototype.leq=SmallInteger.prototype.lesserOrEquals=BigInteger.prototype.leq=BigInteger.prototype.lesserOrEquals;BigInteger.prototype.isEven=function(){return(this.value[0]&1)===0};SmallInteger.prototype.isEven=function(){return(this.value&1)===0};BigInteger.prototype.isOdd=function(){return(this.value[0]&1)===1};SmallInteger.prototype.isOdd=function(){return(this.value&1)===1};BigInteger.prototype.isPositive=function(){return!this.sign};SmallInteger.prototype.isPositive=function(){return this.value>0};BigInteger.prototype.isNegative=function(){return this.sign};SmallInteger.prototype.isNegative=function(){return this.value<0};BigInteger.prototype.isUnit=function(){return false};SmallInteger.prototype.isUnit=function(){return Math.abs(this.value)===1};BigInteger.prototype.isZero=function(){return false};SmallInteger.prototype.isZero=function(){return this.value===0};BigInteger.prototype.isDivisibleBy=function(v){var n=parseValue(v);var value=n.value;if(value===0)return false;if(value===1)return true;if(value===2)return this.isEven();return this.mod(n).equals(Integer[0])};SmallInteger.prototype.isDivisibleBy=BigInteger.prototype.isDivisibleBy;function isBasicPrime(v){var n=v.abs();if(n.isUnit())return false;if(n.equals(2)||n.equals(3)||n.equals(5))return true;if(n.isEven()||n.isDivisibleBy(3)||n.isDivisibleBy(5))return false;if(n.lesser(49))return true}function millerRabinTest(n,a){var nPrev=n.prev(),b=nPrev,r=0,d,t,i,x;while(b.isEven())b=b.divide(2),r++;next:for(i=0;i<a.length;i++){if(n.lesser(a[i]))continue;x=bigInt(a[i]).modPow(b,n);if(x.equals(Integer[1])||x.equals(nPrev))continue;for(d=r-1;d!=0;d--){x=x.square().mod(n);if(x.equals(nPrev))continue next}return false}return true}BigInteger.prototype.isPrime=function(strict){var isPrime=isBasicPrime(this);if(isPrime!==undefined)return isPrime;var n=this.abs();var bits=n.bitLength();if(bits<=64)return millerRabinTest(n,[2,325,9375,28178,450775,9780504,1795265022]);var logN=Math.log(2)*bits;var t=Math.ceil(strict===true?2*Math.pow(logN,2):logN);for(var a=[],i=0;i<t;i++){a.push(bigInt(i+2))}return millerRabinTest(n,a)};SmallInteger.prototype.isPrime=BigInteger.prototype.isPrime;BigInteger.prototype.isProbablePrime=function(iterations){var isPrime=isBasicPrime(this);if(isPrime!==undefined)return isPrime;var n=this.abs();var t=iterations===undefined?5:iterations;for(var a=[],i=0;i<t;i++){a.push(bigInt.randBetween(2,n.minus(2)))}return millerRabinTest(n,a)};SmallInteger.prototype.isProbablePrime=BigInteger.prototype.isProbablePrime;BigInteger.prototype.modInv=function(n){var t=bigInt.zero,newT=bigInt.one,r=parseValue(n),newR=this.abs(),q,lastT,lastR;while(!newR.equals(bigInt.zero)){q=r.divide(newR);lastT=t;lastR=r;t=newT;r=newR;newT=lastT.subtract(q.multiply(newT));newR=lastR.subtract(q.multiply(newR))}if(!r.equals(1))throw new Error(this.toString()+" and "+n.toString()+" are not co-prime");if(t.compare(0)===-1){t=t.add(n)}if(this.isNegative()){return t.negate()}return t};SmallInteger.prototype.modInv=BigInteger.prototype.modInv;BigInteger.prototype.next=function(){var value=this.value;if(this.sign){return subtractSmall(value,1,this.sign)}return new BigInteger(addSmall(value,1),this.sign)};SmallInteger.prototype.next=function(){var value=this.value;if(value+1<MAX_INT)return new SmallInteger(value+1);return new BigInteger(MAX_INT_ARR,false)};BigInteger.prototype.prev=function(){var value=this.value;if(this.sign){return new BigInteger(addSmall(value,1),true)}return subtractSmall(value,1,this.sign)};SmallInteger.prototype.prev=function(){var value=this.value;if(value-1>-MAX_INT)return new SmallInteger(value-1);return new BigInteger(MAX_INT_ARR,true)};var powersOfTwo=[1];while(2*powersOfTwo[powersOfTwo.length-1]<=BASE)powersOfTwo.push(2*powersOfTwo[powersOfTwo.length-1]);var powers2Length=powersOfTwo.length,highestPower2=powersOfTwo[powers2Length-1];function shift_isSmall(n){return(typeof n==="number"||typeof n==="string")&&+Math.abs(n)<=BASE||n instanceof BigInteger&&n.value.length<=1}BigInteger.prototype.shiftLeft=function(n){if(!shift_isSmall(n)){throw new Error(String(n)+" is too large for shifting.")}n=+n;if(n<0)return this.shiftRight(-n);var result=this;if(result.isZero())return result;while(n>=powers2Length){result=result.multiply(highestPower2);n-=powers2Length-1}return result.multiply(powersOfTwo[n])};SmallInteger.prototype.shiftLeft=BigInteger.prototype.shiftLeft;BigInteger.prototype.shiftRight=function(n){var remQuo;if(!shift_isSmall(n)){throw new Error(String(n)+" is too large for shifting.")}n=+n;if(n<0)return this.shiftLeft(-n);var result=this;while(n>=powers2Length){if(result.isZero()||result.isNegative()&&result.isUnit())return result;remQuo=divModAny(result,highestPower2);result=remQuo[1].isNegative()?remQuo[0].prev():remQuo[0];n-=powers2Length-1}remQuo=divModAny(result,powersOfTwo[n]);return remQuo[1].isNegative()?remQuo[0].prev():remQuo[0]};SmallInteger.prototype.shiftRight=BigInteger.prototype.shiftRight;function bitwise(x,y,fn){y=parseValue(y);var xSign=x.isNegative(),ySign=y.isNegative();var xRem=xSign?x.not():x,yRem=ySign?y.not():y;var xDigit=0,yDigit=0;var xDivMod=null,yDivMod=null;var result=[];while(!xRem.isZero()||!yRem.isZero()){xDivMod=divModAny(xRem,highestPower2);xDigit=xDivMod[1].toJSNumber();if(xSign){xDigit=highestPower2-1-xDigit}yDivMod=divModAny(yRem,highestPower2);yDigit=yDivMod[1].toJSNumber();if(ySign){yDigit=highestPower2-1-yDigit}xRem=xDivMod[0];yRem=yDivMod[0];result.push(fn(xDigit,yDigit))}var sum=fn(xSign?1:0,ySign?1:0)!==0?bigInt(-1):bigInt(0);for(var i=result.length-1;i>=0;i-=1){sum=sum.multiply(highestPower2).add(bigInt(result[i]))}return sum}BigInteger.prototype.not=function(){return this.negate().prev()};SmallInteger.prototype.not=BigInteger.prototype.not;BigInteger.prototype.and=function(n){return bitwise(this,n,function(a,b){return a&b})};SmallInteger.prototype.and=BigInteger.prototype.and;BigInteger.prototype.or=function(n){return bitwise(this,n,function(a,b){return a|b})};SmallInteger.prototype.or=BigInteger.prototype.or;BigInteger.prototype.xor=function(n){return bitwise(this,n,function(a,b){return a^b})};SmallInteger.prototype.xor=BigInteger.prototype.xor;var LOBMASK_I=1<<30,LOBMASK_BI=(BASE&-BASE)*(BASE&-BASE)|LOBMASK_I;function roughLOB(n){var v=n.value,x=typeof v==="number"?v|LOBMASK_I:v[0]+v[1]*BASE|LOBMASK_BI;return x&-x}function integerLogarithm(value,base){if(base.compareTo(value)<=0){var tmp=integerLogarithm(value,base.square(base));var p=tmp.p;var e=tmp.e;var t=p.multiply(base);return t.compareTo(value)<=0?{p:t,e:e*2+1}:{p:p,e:e*2}}return{p:bigInt(1),e:0}}BigInteger.prototype.bitLength=function(){var n=this;if(n.compareTo(bigInt(0))<0){n=n.negate().subtract(bigInt(1))}if(n.compareTo(bigInt(0))===0){return bigInt(0)}return bigInt(integerLogarithm(n,bigInt(2)).e).add(bigInt(1))};SmallInteger.prototype.bitLength=BigInteger.prototype.bitLength;function max(a,b){a=parseValue(a);b=parseValue(b);return a.greater(b)?a:b}function min(a,b){a=parseValue(a);b=parseValue(b);return a.lesser(b)?a:b}function gcd(a,b){a=parseValue(a).abs();b=parseValue(b).abs();if(a.equals(b))return a;if(a.isZero())return b;if(b.isZero())return a;var c=Integer[1],d,t;while(a.isEven()&&b.isEven()){d=Math.min(roughLOB(a),roughLOB(b));a=a.divide(d);b=b.divide(d);c=c.multiply(d)}while(a.isEven()){a=a.divide(roughLOB(a))}do{while(b.isEven()){b=b.divide(roughLOB(b))}if(a.greater(b)){t=b;b=a;a=t}b=b.subtract(a)}while(!b.isZero());return c.isUnit()?a:a.multiply(c)}function lcm(a,b){a=parseValue(a).abs();b=parseValue(b).abs();return a.divide(gcd(a,b)).multiply(b)}function randBetween(a,b){a=parseValue(a);b=parseValue(b);var low=min(a,b),high=max(a,b);var range=high.subtract(low).add(1);if(range.isSmall)return low.add(Math.floor(Math.random()*range));var length=range.value.length-1;var result=[],restricted=true;for(var i=length;i>=0;i--){var top=restricted?range.value[i]:BASE;var digit=truncate(Math.random()*top);result.unshift(digit);if(digit<top)restricted=false}result=arrayToSmall(result);return low.add(typeof result==="number"?new SmallInteger(result):new BigInteger(result,false))}var parseBase=function(text,base){var length=text.length;var i;var absBase=Math.abs(base);for(var i=0;i<length;i++){var c=text[i].toLowerCase();if(c==="-")continue;if(/[a-z0-9]/.test(c)){if(/[0-9]/.test(c)&&+c>=absBase){if(c==="1"&&absBase===1)continue;throw new Error(c+" is not a valid digit in base "+base+".")}else if(c.charCodeAt(0)-87>=absBase){throw new Error(c+" is not a valid digit in base "+base+".")}}}if(2<=base&&base<=36){if(length<=LOG_MAX_INT/Math.log(base)){var result=parseInt(text,base);if(isNaN(result)){throw new Error(c+" is not a valid digit in base "+base+".")}return new SmallInteger(parseInt(text,base))}}base=parseValue(base);var digits=[];var isNegative=text[0]==="-";for(i=isNegative?1:0;i<text.length;i++){var c=text[i].toLowerCase(),charCode=c.charCodeAt(0);if(48<=charCode&&charCode<=57)digits.push(parseValue(c));else if(97<=charCode&&charCode<=122)digits.push(parseValue(c.charCodeAt(0)-87));else if(c==="<"){var start=i;do{i++}while(text[i]!==">");digits.push(parseValue(text.slice(start+1,i)))}else throw new Error(c+" is not a valid character")}return parseBaseFromArray(digits,base,isNegative)};function parseBaseFromArray(digits,base,isNegative){var val=Integer[0],pow=Integer[1],i;for(i=digits.length-1;i>=0;i--){val=val.add(digits[i].times(pow));pow=pow.times(base)}return isNegative?val.negate():val}function stringify(digit){if(digit<=35){return"0123456789abcdefghijklmnopqrstuvwxyz".charAt(digit)}return"<"+digit+">"}function toBase(n,base){base=bigInt(base);if(base.isZero()){if(n.isZero())return{value:[0],isNegative:false};throw new Error("Cannot convert nonzero numbers to base 0.")}if(base.equals(-1)){if(n.isZero())return{value:[0],isNegative:false};if(n.isNegative())return{value:[].concat.apply([],Array.apply(null,Array(-n)).map(Array.prototype.valueOf,[1,0])),isNegative:false};var arr=Array.apply(null,Array(+n-1)).map(Array.prototype.valueOf,[0,1]);arr.unshift([1]);return{value:[].concat.apply([],arr),isNegative:false}}var neg=false;if(n.isNegative()&&base.isPositive()){neg=true;n=n.abs()}if(base.equals(1)){if(n.isZero())return{value:[0],isNegative:false};return{value:Array.apply(null,Array(+n)).map(Number.prototype.valueOf,1),isNegative:neg}}var out=[];var left=n,divmod;while(left.isNegative()||left.compareAbs(base)>=0){divmod=left.divmod(base);left=divmod.quotient;var digit=divmod.remainder;if(digit.isNegative()){digit=base.minus(digit).abs();left=left.next()}out.push(digit.toJSNumber())}out.push(left.toJSNumber());return{value:out.reverse(),isNegative:neg}}function toBaseString(n,base){var arr=toBase(n,base);return(arr.isNegative?"-":"")+arr.value.map(stringify).join("")}BigInteger.prototype.toArray=function(radix){return toBase(this,radix)};SmallInteger.prototype.toArray=function(radix){return toBase(this,radix)};BigInteger.prototype.toString=function(radix){if(radix===undefined)radix=10;if(radix!==10)return toBaseString(this,radix);var v=this.value,l=v.length,str=String(v[--l]),zeros="0000000",digit;while(--l>=0){digit=String(v[l]);str+=zeros.slice(digit.length)+digit}var sign=this.sign?"-":"";return sign+str};SmallInteger.prototype.toString=function(radix){if(radix===undefined)radix=10;if(radix!=10)return toBaseString(this,radix);return String(this.value)};BigInteger.prototype.toJSON=SmallInteger.prototype.toJSON=function(){return this.toString()};BigInteger.prototype.valueOf=function(){return parseInt(this.toString(),10)};BigInteger.prototype.toJSNumber=BigInteger.prototype.valueOf;SmallInteger.prototype.valueOf=function(){return this.value};SmallInteger.prototype.toJSNumber=SmallInteger.prototype.valueOf;function parseStringValue(v){if(isPrecise(+v)){var x=+v;if(x===truncate(x))return new SmallInteger(x);throw new Error("Invalid integer: "+v)}var sign=v[0]==="-";if(sign)v=v.slice(1);var split=v.split(/e/i);if(split.length>2)throw new Error("Invalid integer: "+split.join("e"));if(split.length===2){var exp=split[1];if(exp[0]==="+")exp=exp.slice(1);exp=+exp;if(exp!==truncate(exp)||!isPrecise(exp))throw new Error("Invalid integer: "+exp+" is not a valid exponent.");var text=split[0];var decimalPlace=text.indexOf(".");if(decimalPlace>=0){exp-=text.length-decimalPlace-1;text=text.slice(0,decimalPlace)+text.slice(decimalPlace+1)}if(exp<0)throw new Error("Cannot include negative exponent part for integers");text+=new Array(exp+1).join("0");v=text}var isValid=/^([0-9][0-9]*)$/.test(v);if(!isValid)throw new Error("Invalid integer: "+v);var r=[],max=v.length,l=LOG_BASE,min=max-l;while(max>0){r.push(+v.slice(min,max));min-=l;if(min<0)min=0;max-=l}trim(r);return new BigInteger(r,sign)}function parseNumberValue(v){if(isPrecise(v)){if(v!==truncate(v))throw new Error(v+" is not an integer.");return new SmallInteger(v)}return parseStringValue(v.toString())}function parseValue(v){if(typeof v==="number"){return parseNumberValue(v)}if(typeof v==="string"){return parseStringValue(v)}return v}for(var i=0;i<1e3;i++){Integer[i]=new SmallInteger(i);if(i>0)Integer[-i]=new SmallInteger(-i)}Integer.one=Integer[1];Integer.zero=Integer[0];Integer.minusOne=Integer[-1];Integer.max=max;Integer.min=min;Integer.gcd=gcd;Integer.lcm=lcm;Integer.isInstance=function(x){return x instanceof BigInteger||x instanceof SmallInteger};Integer.randBetween=randBetween;Integer.fromArray=function(digits,base,isNegative){return parseBaseFromArray(digits.map(parseValue),parseValue(base||10),isNegative)};return Integer}();if(typeof module!=="undefined"&&module.hasOwnProperty("exports")){module.exports=bigInt}if(typeof define==="function"&&define.amd){define("big-integer",[],function(){return bigInt})}
function plugins_quorum_Libraries_Compute_BigInteger_() {
    this.value = JSBI.BigInt('0'); 
    
    /*
    This action sets the value of the BigInteger object to the numeric value of
    the text parameter, in the base of the base parameter (base 2 for binary, 10
    for decimal, and so on). 

    Attribute: Parameter value The text to convert into a numeric value.

    Attribute: Parameter base The base (2, 8, 10, 16, etc.) to convert the value to.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    text value = "3000000000"
    largeNumber:SetValue(value, 10)
    */
    this.SetValue$quorum_text$quorum_integer = function(value, base){
        this.value = JSBI.BigInt(value);
        this.value.toString(base);
    };


    /*
    This action returns the value stored in the BigInteger in text format. Returns
    the value in whatever base you use as the parameter, such as 2, 10, 16, and so on.

    Attribute: Parameter base The base (2, 8, 10, 16, etc.) to convert the value to.

    Attribute: Returns Returns the value of the BigInteger in the given base in text format.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    largeNumber:SetValue("3000000000")
    output largeNumber:GetText(2)
    */
    this.GetText$quorum_integer = function(base){
        return this.value.toString(base);
    };

    /*
    This action adds two BigIntegers together and returns the result.

    Attribute: Parameter value The value to add to the BigInteger.

    Attribute: Returns Returns the result of adding the two BigIntegers together.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    largeNumber:SetValue("3000000000")
    largeNumber = largeNumber:Add(largeNumber)
    */
    this.Add$quorum_Libraries_Compute_BigInteger = function(value){
        
    };

    /*
    This action takes two BigIntegers and performs a bit-wise AND (&) operation
    on them. 

    Attribute: Parameter value The BigInteger to AND with the BigInteger.

    Attribute: Returns Returns the result of AND'ing together the two BigIntegers.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    largeNumber:SetValue("3000000000")
    largeNumber = largeNumber:And(largeNumber)
    */
    this.And$quorum_Libraries_Compute_BigInteger = function(value){
    
    };

    /*
    This action takes a BigInteger, gets the complement of it, and then ANDs it
    with the BigInteger this action was called on.

    Attribute: Parameter value The BigInteger that will be complemented and then AND'ed
    together with the BigInteger.

    Attribute: Returns Returns the result of AND'ing together the complement of
    the value with the BigInteger.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    largeNumber:SetValue("3000000000")
    largeNumber = largeNumber:AndNot(largeNumber)
    */
    this.AndNot$quorum_Libraries_Compute_BigInteger = function(value) {
        
    };

    /*
    This action compares two BigIntegers and returns whether or not the BigInteger
    that this action was called on is >, =, or < the value passed in, represented
    by 1, 0, and -1 respectively. 

    Attribute: Parameter value The BigInteger to compare against.

    Attribute: Returns Returns 1 if value < BigInteger, 0 if value = BigInteger,
    and -1 if value > BigInteger.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    BigInteger largerNumber
    largeNumber:SetValue("3000000000")
    largerNumber:SetValue("3000000001")
    integer result = largeNumber:CompareResult(largerNumber)
    output result
    */
    this.CompareResult$quorum_Libraries_Compute_BigInteger = function(value){
        
    };
   

    /*
    This action divides a BigInteger by the given BigInteger value and returns
    the result.

    Attribute: Parameter value The value to divide the BigInteger by.

    Attribute: Returns Returns the result of BigInteger/value as a BigInteger.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    BigInteger smallNumber
    largeNumber:SetValue("3000000000")
    smallNumber:SetValue("2")
    largeNumber = largeNumber:Divide(smallNumber)
    output largeNumber:GetText()
    */
    this.Divide$quorum_Libraries_Compute_BigInteger = function(value) {
        
    };

    /*
    This action checks if two BigIntegers are equal. To be equal, the BigIntegers
    must have the same numeric values.

    Attribute: Parameter value The BigInteger to compare against.

    Attribute: Returns Returns the result of the comparison, either true if the
    BigIntegers are the same or false otherwise.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    BigInteger sameNumber
    BigInteger differentNumber
    largeNumber:SetValue("3000000000")
    sameNumber:SetValue("3000000000")
    differentNumber:SetValue("1")
    output largeNumber:Equals(sameNumber)
    output largeNumber:Equals(differentNumber)
    */
    this.Equals$quorum_Libraries_Compute_BigInteger = function(value) {
        
    };

    /*
    This action takes a BigInteger and converts it into a number. If the BigInteger
    is too large to be stored in a number, it takes the low-order bits (i.e. 102
    would return 02 if 102 was too large to fit in a number) and gets rid of all
    other values. This loss of precision can result in incorrect values, as well
    as incorrect signs.

    Attribute: Returns Returns the BigInteger value as a number.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    largeNumber:SetValue("24")
    number value = largeNumber:ToNumber()
    */
    this.ToNumber = function() {
        
    };

    /*
    This action will find the greatest common divisor between two BigIntegers.

    Attribute: Parameter value The BigInteger to check against for the greatest common divisor.

    Attribute: Returns Returns a BigInteger value that is the greatest common
    divisor of the two BigIntegers.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    BigInteger smallNumber
    largeNumber:SetValue("24")
    smallNumber:SetValue("12")
    largeNumber = largeNumber:GreatestCommonDivisor(smallNumber)
    // Output will be 12
    output largeNumber:GetText()
    */
    this.GreatestCommonDivisor$quorum_Libraries_Compute_BigInteger = function(value) {
        
    };

    /*
    This action takes a BigInteger and converts it into an integer. If the BigInteger
    is too large to be stored in an integer, it takes the low-order bits (i.e. 102
    would return 02 if 102 was too large to fit in a number) and gets rid of all
    other values. This loss of precision can result in incorrect values, as well
    as incorrect signs.

    Attribute: Returns Returns the BigInteger value as an integer.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    largeNumber:SetValue("30")
    integer value = largeNumber:ToInteger()
    */
    this.ToInteger = function() {
        
    };

    /*
    This action compares two BigIntegers and returns the greater of the two.

    Attribute: Parameter value The BigInteger to compare against.

    Attribute: Returns Returns the BigInteger that is the greater of the two
    BigIntegers.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    BigInteger smallNumber
    largeNumber:SetValue("300")
    smallNumber:SetValue("1")
    largeNumber = largeNumber:GetMaximumValue(smallNumber)
    */
    this.GetMaximumValue$quorum_Libraries_Compute_BigInteger = function(value){
        
    };

    /*
    This action compares two BigInteger and returns the lesser of the two.

    Attribute: Parameter value The BigInteger to compare against.

    Attribute: Returns Returns the BigInteger that is the lesser of the two
    BigIntegers.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    BigInteger smallNumber
    largeNumber:SetValue("300")
    smallNumber:SetValue("1")
    largeNumber = largeNumber:GetMinimumValue(smallNumber)
    */
    this.GetMinimumValue$quorum_Libraries_Compute_BigInteger = function(value) {
        
    };

    /*
    This action takes a BigInteger, divides it by the BigInteger value, and returns
    the remainder as the result. The remainder is always positive. Use the Remainder(BigInteger)
    action if you want negative remainder results.

    Attribute: Parameter value The value to divide the BigInteger by.

    Attribute: Returns Returns the remainder of the division of the BigInteger
    on which this action was called by value.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    BigInteger smallNumber
    largeNumber:SetValue("10")
    smallNumber:SetValue("6")
    largeNumber = largeNumber:Mod(smallNumber)
    // Output will be 4
    output largeNumber:GetText()
    */
    this.Mod$quorum_Libraries_Compute_BigInteger = function(value) {
        
    };

    /*
    This action multiples two BigIntegers together and returns the result.

    Attribute: Parameter value The value to multiply the BigInteger by.

    Attribute: Returns Returns the result of the multiplication of these two
    BigIntegers as a BigInteger.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    BigInteger smallNumber
    largeNumber:SetValue("398")
    smallNumber:SetValue("10")
    largeNumber = largeNumber:Multiply(smallNumber)
    output largeNumber:GetText()
    */
    this.Multiply$quorum_Libraries_Compute_BigInteger = function(value) {
        
    };

    /*
    This action negates the value of a BigInteger, turning a positive number to
    a negative number and a negative number to a positive number.

    Attribute: Returns Returns a BigInteger with the opposite sign than what it
    originally had.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    largeNumber:SetValue("-10")
    largeNumber = largeNumber:Negate()
    // Output will be 10
    output largeNumber:GetText()
    */
    this.Negate = function(){
        
    };

    /*
    This action takes a BigInteger and finds the 2's complement of it. The calculation
    is: x = -x - 1. 

    Attribute: Returns Returns the 2's complement of a BigInteger.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    largeNumber:SetValue("10")
    largeNumber = largeNumber:BitwiseNot()
    // Output will be -11
    output largeNumber:GetText()
    */
    this.BitwiseNot = function() {
        
    };

    /*
    This action takes two BigIntegers and performs a bitwise OR (|) operation on
    them.
    
    Attribute: Parameter value The BigInteger value that will be logically
    OR'ed with the BigInteger on which this action was called.

    Attribute: Returns Returns a BigInteger that is the result of logically
    OR'ing the two BigIntegers together.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    BigInteger smallNumber
    largeNumber:SetValue("2")
    smallNumber:SetValue("1")
    largeNumber = largeNumber:Or(smallNumber)
    // Output will be 3
    output largeNumber:GetText()
    */
    this.Or$quorum_Libraries_Compute_BigInteger = function(value) {
        
    };

    /*
    This action raises a BigInteger to the given power and returns the result.

    Attribute: Parameter power The power to raise the BigInteger to.

    Attribute: Returns Returns a BigInteger which is the result of raising the
    BigInteger to the given power.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    largeNumber:SetValue("9")
    largeNumber = largeNumber:RaiseToPower(2)
    output largeNumber:GetText()
    */
    this.RaiseToPower$quorum_integer = function(power) {
        
    };

    /*
    This action divides a BigInteger by the BigInteger value and returns the
    remainder. This remainder can be negative.

    Attribute: Parameter value The value to divide the BigInteger by.

    Attribute: Returns Returns the remainder of the division operation as a BigInteger.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    BigInteger smallNumber
    largeNumber:SetValue("-10")
    smallNumber:SetValue("6")
    largeNumber = largeNumber:Remainder(smallNumber)
    // Output will be -4
    output largeNumber:GetText()
    */
    this.Remainder$quorum_Libraries_Compute_BigInteger = function(value) {
        
    };

    /*
    This action takes a BigInteger and shifts its bits to the left by the given
    amount of positions. This is equivalent to multiplying by 2 for each position
    shifted. So if you shift by 2 positions, you multiply the original number by 4.

    Attribute: Parameter positions The number of positions to shift the BigInteger
    to the left.

    Attribute: Returns Returns a BigInteger that has been shifted to the left
    positions number of times.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    largeNumber:SetValue("10")
    largeNumber = largeNumber:ShiftLeft(2)
    // Output will be 40
    output largeNumber:GetText()
    */
    this.ShiftLeft$quorum_integer = function(positions) {
        
    };

    /*
    This action takes a BigInteger and shifts its bits to the right by the given
    amount of positions. This is equivalent to dividing by 2 for each position
    shifted. So if you shift by 2 positions, you divide the original number by 4.

    Attribute: Parameter positions The number of positions to shift the BigInteger
    to the right.

    Attribute: Returns Returns a BigInteger that has been shifted to the right
    positions number of times.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    largeNumber:SetValue("10")
    largeNumber = largeNumber:ShiftRight(2)
    // Output will be 2
    output largeNumber:GetText()
    */
    this.ShiftRight$quorum_integer = function(positions){
        
    };

    /*
    This action returns the sign of a BigInteger, returning 1 for positive,
    -1 for negative, and 0 for 0.

    Attribute: Returns Returns an integer value, 1, 0, or -1, indicating the sign
    of the BigInteger.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    largeNumber:SetValue("-10")
    integer result = largeNumber:GetSignValue()
    // Output will be -1
    output result
    */
    this.GetSignValue = function(){
        
    };

    /*
    This action subtracts the BigInteger value from the BigInteger on which this
    action was called and returns the result.

    Attribute: Parameter value The value to subtract from the BigInteger.

    Attribute: Returns Returns the result of the BigInteger minus value as a
    BigInteger.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    BigInteger smallNumber
    largeNumber:SetValue("10")
    smallNumber:SetValue("5")
    largeNumber = largeNumber:Subtract(smallNumber)
    output largeNumber:GetText()
    */
    this.Subtract$quorum_Libraries_Compute_BigInteger = function(value) {
        
    };

    /*
    This action will find the bitwise exclusive-or value of two BigIntegers.
    
    Attribute: Parameter value The BigInteger to exclusive-or with the BigInteger on
    which this action was called.

    Attribute: Returns Returns the result of exclusive-or'ing the two BigIntegers
    together.

    Attribute: Example
    use Libraries.Compute.BigInteger
    BigInteger largeNumber
    BigInteger smallNumber
    largeNumber:SetValue("6")
    smallNumber:SetValue("4")
    largeNumber = largeNumber:ExclusiveOr(smallNumber)
    // Output will be 2
    output largeNumber:GetText()
    */
    this.ExclusiveOr$quorum_Libraries_Compute_BigInteger = function(value){
        
    };
    
}

