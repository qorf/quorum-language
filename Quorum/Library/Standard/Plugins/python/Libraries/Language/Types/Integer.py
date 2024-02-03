def Integer_(value):
	val = quorum_Libraries_Language_Types_Integer_()
	val.SetValue__quorum_integer(value)
	return val

class plugins_quorum_Libraries_Language_Types_Integer_:
	def __init__(self, value = 0):
		self.integer = value
    
	def SetValueNative__quorum_integer(self, value):
		self.integer = value

	def GetHashCode(self):
		return hash(self.integer)
    
	def BitCount(self):
		return bin(self.integer).count("1")

	def HighestOneBit(self):
		if self.integer == 0:
			return -1 

		return 2 ** (self.integer.bit_length() - 1)
		
	def LowestOneBit(self):
		if self.integer == 0:
			return -1  # Special case for zero

		position = 0
		while self.integer & 1 == 0:
			self.integer >>= 1
			position += 1

		return 2 ** position
		
	def LeadingZeros(self):
		return 32 - self.integer.bit_length()

	def TrailingZeros(self):
		if self.integer == 0:
			return self.integer.bit_length()  # Special case for zero

		count = 0
		while self.integer & 1 == 0:
			self.integer >>= 1
			count += 1

		return count

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
		return  self.integer << value
		        
	def RotateRight__quorum_integer(self, value):
		bits = 32
		return ((self.integer >> value) | (self.integer << (bits - value))) & ((1 << bits) - 1)
		
	def GetBinary(self):
		return bin(self.integer)[2:]

	def GetHex(self):
		return hex(self.integer)
		
	def GetOctal(self):
		return oct(self.integer)[2:]
