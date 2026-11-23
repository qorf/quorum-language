def Number_(value):
	val = quorum_Libraries_Language_Types_Number_()
	val.SetValue__quorum_number(value)
	return val


class plugins_quorum_Libraries_Language_Types_Number_:
	def __init__(self, value = 0.0):
		self.number = value;
    
	def SetValueNative__quorum_number(self, value):
		self.number = value

	def GetMaximumValue(self):
		return float.fromhex('0x1.fffffffffffffp+1023')

	def GetMinimumValue(self):
		return float.fromhex('-0x1.fffffffffffffp+1023')

	def GetMinimumPositiveValue(self):
		# The smallest positive number, 4.9e-324 (Java's Double.MIN_VALUE).
		return float.fromhex('0x0.0000000000001p-1022')

	def GetPositiveInfinityValue(self):
		return float('inf')

	def GetNumberOfBits(self):
		# A number is a 64-bit floating point value, as in Java (Double.SIZE).
		return 64

	def GetNegativeInfinityValue(self):
		return -float('inf')

	def GetNotANumberValue(self):
		return float('nan')

	def IsInfinite(self):
		return self.number == float('inf') or self.number == -float('inf')

	def IsNotANumber(self):
		# NaN is the only value not equal to itself.
		return self.number != self.number
		
	def GetHex(self):
		# The format of Java's Double.toHexString: Python's float.hex gives
		# 0x1.4000000000000p+1, Java 0x1.4p1 (no trailing zeros, no plus sign).
		value = self.number
		if value != value:
			return "NaN"
		if value == float('inf'):
			return "Infinity"
		if value == -float('inf'):
			return "-Infinity"
		# From the bits, in two 32-bit halves: MicroPython has no float.hex,
		# and may not have integers wider than 64 bits.
		import struct
		high, low = struct.unpack('>II', struct.pack('>d', value))
		sign = "-" if high & 0x80000000 else ""
		exponent = (high >> 20) & 0x7FF
		fraction = "%05x" % (high & 0xFFFFF) + "%08x" % low
		fraction = fraction.rstrip('0')
		if fraction == "":
			fraction = "0"
		if exponent == 0:
			if fraction == "0":
				return sign + "0x0.0p0"
			return sign + "0x0." + fraction + "p-1022"
		return sign + "0x1." + fraction + "p" + str(exponent - 1023)

	def GetHashCode(self):
		# Java's Double.hashCode: the high 32 bits XORed with the low 32 bits,
		# with every NaN as the canonical NaN, as a signed 32-bit integer.
		# Two 32-bit halves, not one 64-bit value, for MicroPython.
		import struct
		value = self.number
		if value != value:
			high = 0x7ff80000
			low = 0
		else:
			high, low = struct.unpack('>II', struct.pack('>d', value))
		result = (high ^ low) & 0xFFFFFFFF
		return result - 0x100000000 if result & 0x80000000 else result
