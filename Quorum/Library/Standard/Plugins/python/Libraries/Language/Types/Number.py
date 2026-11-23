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
		mantissa, exponent = float.hex(value).split('p')
		whole, fraction = mantissa.split('.')
		fraction = fraction.rstrip('0') or '0'
		return whole + '.' + fraction + 'p' + str(int(exponent))

	def GetHashCode(self):
		# Java's Double.hashCode: the high 32 bits XORed with the low 32 bits,
		# with every NaN as the canonical NaN, as a signed 32-bit integer.
		import struct
		value = self.number
		if value != value:
			bits = 0x7ff8000000000000
		else:
			bits = struct.unpack('>Q', struct.pack('>d', value))[0]
		result = (bits ^ (bits >> 32)) & 0xFFFFFFFF
		return result - 0x100000000 if result & 0x80000000 else result
