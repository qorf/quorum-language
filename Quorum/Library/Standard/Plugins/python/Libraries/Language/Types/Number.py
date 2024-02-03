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
		return float.fromhex('0x1.0p-52')

	def GetPositiveInfinityValue(self):
		return float('inf')

	def GetNumberOfBits(self):
		return self.number.bit_length()

	def GetNegativeInfinityValue(self):
		return -float('inf')

	def GetNotANumberValue(self):
		return float('nan')

	def IsInfinite(self):
		return self.number == float('inf')

	def IsNotANumber(self):
		return self.number == float('nan')
		
	def GetHex(self):
		return hex(self.number)
		
	def GetHashCode(self):
		return self.number
