def Integer_(value):
	val = quorum_Libraries_Language_Types_Integer_()
	val.SetValue__quorum_integer(value)
	return val

def _signed32(value):
	# The 32-bit pattern value as a signed integer, as Java's int.
	return value - 0x100000000 if value & 0x80000000 else value

class plugins_quorum_Libraries_Language_Types_Integer_:
	def __init__(self, value = 0):
		self.integer = value
    
	def SetValueNative__quorum_integer(self, value):
		self.integer = value

	def GetHashCode(self):
		return hash(self.integer)
    
	def BitCount(self):
		return bin(self.integer & 0xFFFFFFFF).count("1")

	def HighestOneBit(self):
		# As Java's Integer.highestOneBit, on the 32-bit two's complement value:
		# 0 for 0, and the minimum integer for any negative value.
		value = self.integer & 0xFFFFFFFF
		if value == 0:
			return 0
		result = 1 << (value.bit_length() - 1)
		if result & 0x80000000:
			return result - 0x100000000
		return result
		
	def LowestOneBit(self):
		# As Java's Integer.lowestOneBit (i & -i), without changing the value.
		value = self.integer & 0xFFFFFFFF
		return _signed32(value & -value)
		
	def LeadingZeros(self):
		return 32 - (self.integer & 0xFFFFFFFF).bit_length()

	def TrailingZeros(self):
		# As Java's Integer.numberOfTrailingZeros (32 for 0), without changing the value.
		value = self.integer & 0xFFFFFFFF
		if value == 0:
			return 32
		return (value & -value).bit_length() - 1

	def Reverse(self):
		binary = format(self.integer & 0xFFFFFFFF, '032b')
		binary = binary[::-1]
		if binary[0] == '1':
			reversed = ''.join(['1' if bit == '0' else '0' for bit in binary])
			result = -(int(reversed, 2) + 1)
		else:
			result = int(binary, 2)
		return result

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
		
	def GetBinary(self):
		# The unsigned 32-bit value with no prefix, as Java's toBinaryString.
		return format(self.integer & 0xFFFFFFFF, 'b')

	def GetHex(self):
		return format(self.integer & 0xFFFFFFFF, 'x')
		
	def GetOctal(self):
		return format(self.integer & 0xFFFFFFFF, 'o')
