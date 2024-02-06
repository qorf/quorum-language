class plugins_quorum_Libraries_Containers_Number64BitArray_:
	def __init__(self):
		self.array_ = []
		return
		
	def Get__quorum_integer(self, index):
		return self.array_[index]

	def Set__quorum_integer__quorum_number(self, index, value):
		self.array_[index] = value

	def GetSize(self):
		return len(self.array_)
    
	def SetSize__quorum_integer(self, size):
		self.array_ = [None] * size