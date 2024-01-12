class plugins_quorum_Libraries_Language_Types_Number_:
	def __init__(self, value = 0.0):
		self.number = value;
    
	def SetValueNative__quorum_number(self, value):
		self.number = value

	def GetMaximumValue(self):
		return 0

	def GetMinimumValue(self):
		return 0

	def GetMinimumPositiveValue(self):
		return 0

	def GetPositiveInfinityValue(self):
		return 0

	def GetNumberOfBits(self):
		return 0

	def GetNegativeInfinityValue(self):
		return 0

	def GetNotANumberValue(self):
		return 0

	def IsInfinite(self):
		return 0

	def IsNotANumber(self):
		return 0
		
	def GetHex(self):
		return 0
		
	def GetHashCode(self):
		return self.number
