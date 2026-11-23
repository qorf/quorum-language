def _Number64BitArray_Location_Error(index):
	# The error Java's Number64BitArray gives for a position outside the array.
	error = quorum_Libraries_Language_Errors_InvalidLocationError_()
	error.SetErrorMessage__quorum_text("Index out of range, " + str(index))
	return error

class plugins_quorum_Libraries_Containers_Number64BitArray_:
	# A fixed-size array of numbers, as Java's Number64BitArray: SetSize
	# makes a new array of zeros.
	def __init__(self):
		self.array_ = []
		return

	def Get__quorum_integer(self, index):
		if index < 0 or index >= len(self.array_):
			raise _Number64BitArray_Location_Error(index)
		return self.array_[index]

	def Set__quorum_integer__quorum_number(self, index, value):
		if index < 0 or index >= len(self.array_):
			raise _Number64BitArray_Location_Error(index)
		self.array_[index] = float(value)

	def GetSize(self):
		return len(self.array_)

	def SetSize__quorum_integer(self, size):
		self.array_ = [0.0] * size
