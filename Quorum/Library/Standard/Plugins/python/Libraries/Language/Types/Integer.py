def Integer_(value):
	val = quorum_Libraries_Language_Types_Integer_()
	val.SetValue__quorum_integer(value)
	return val

def _signed32(value):
	# The 32-bit pattern value as a signed integer, as Java's int.
	return value - 0x100000000 if value & 0x80000000 else value

def _Integer_Bit_Length(value):
	# The number of bits in a value from 0 up to 2^32: int.bit_length isn't
	# in MicroPython, which this back end must run on.
	length = 0
	while value > 0:
		value = value >> 1
		length = length + 1
	return length

class plugins_quorum_Libraries_Language_Types_Integer_:
	def __init__(self, value = 0):
		self.integer = value
    
	def SetValueNative__quorum_integer(self, value):
		self.integer = value

	def GetHashCode(self):
		# Java's Integer.hashCode is the value itself (CPython's hash(-1) is -2).
		return self.integer
    
	def BitCount(self):
		value = self.integer & 0xFFFFFFFF
		count = 0
		while value > 0:
			count = count + (value & 1)
			value = value >> 1
		return count

	def HighestOneBit(self):
		# As Java's Integer.highestOneBit, on the 32-bit two's complement value:
		# 0 for 0, and the minimum integer for any negative value.
		value = self.integer & 0xFFFFFFFF
		if value == 0:
			return 0
		return _signed32(1 << (_Integer_Bit_Length(value) - 1))
		
	def LowestOneBit(self):
		# As Java's Integer.lowestOneBit (i & -i), without changing the value.
		value = self.integer & 0xFFFFFFFF
		return _signed32(value & -value)
		
	def LeadingZeros(self):
		return 32 - _Integer_Bit_Length(self.integer & 0xFFFFFFFF)

	def TrailingZeros(self):
		# As Java's Integer.numberOfTrailingZeros (32 for 0), without changing the value.
		value = self.integer & 0xFFFFFFFF
		if value == 0:
			return 32
		count = 0
		while value & 1 == 0:
			value = value >> 1
			count = count + 1
		return count

	def Reverse(self):
		# The 32 bits in reverse order, as Java's Integer.reverse.
		value = self.integer & 0xFFFFFFFF
		result = 0
		i = 0
		while i < 32:
			result = (result << 1) | (value & 1)
			value = value >> 1
			i = i + 1
		return _signed32(result)

	def RotateLeft__quorum_integer(self, value):
		# As Java's Integer.rotateLeft, on the 32-bit value.
		distance = value & 31
		bits = self.integer & 0xFFFFFFFF
		return _signed32(((bits << distance) | (bits >> (32 - distance))) & 0xFFFFFFFF)
		        
	def RotateRight__quorum_integer(self, value):
		# As Java's Integer.rotateRight, on the 32-bit value.
		distance = value & 31
		bits = self.integer & 0xFFFFFFFF
		return _signed32(((bits >> distance) | (bits << (32 - distance))) & 0xFFFFFFFF)
		
	# The unsigned 32-bit value with no prefix, as Java's toBinaryString,
	# toHexString and toOctalString. bin, hex and oct, since MicroPython has
	# no format(); their prefix is two characters (0b, 0x, 0o).
	def GetBinary(self):
		return bin(self.integer & 0xFFFFFFFF)[2:]

	def GetHex(self):
		return hex(self.integer & 0xFFFFFFFF)[2:]
		
	def GetOctal(self):
		return oct(self.integer & 0xFFFFFFFF)[2:]
