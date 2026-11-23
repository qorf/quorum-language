def _Integer32BitArray_Location_Error(index):
	# The error Java's Integer32BitArray gives for a position outside the array.
	error = quorum_Libraries_Language_Errors_InvalidLocationError_()
	error.SetErrorMessage__quorum_text("Index out of range, " + str(index))
	return error

class plugins_quorum_Libraries_Containers_Integer32BitArray_:
	# A fixed-size array of 32-bit integers, as Java's Integer32BitArray:
	# SetSize keeps the items that fit.
	def __init__(self):
		self.array_ = []

	def Get__quorum_integer(self, index):
		if index < 0 or index >= len(self.array_):
			raise _Integer32BitArray_Location_Error(index)
		return self.array_[index]

	def Set__quorum_integer__quorum_integer(self, index, value):
		if index < 0 or index >= len(self.array_):
			raise _Integer32BitArray_Location_Error(index)
		self.array_[index] = value

	def GetSize(self):
		return len(self.array_)

	def SetSize__quorum_integer(self, size):
		# As documented: the items that fit are kept, and new places are 0.
		if size <= len(self.array_):
			self.array_ = self.array_[0:size]
		else:
			self.array_ = self.array_ + [0] * (size - len(self.array_))
