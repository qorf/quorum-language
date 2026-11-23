import math
import struct

def _Number32BitArray_Location_Error(index):
	# The error Java's Number32BitArray gives for a position outside the array.
	error = quorum_Libraries_Language_Errors_InvalidLocationError_()
	error.SetErrorMessage__quorum_text("Index out of range, " + str(index))
	return error

def _Number32BitArray_Float(value):
	# The value kept to 32-bit precision, as Java's float.
	try:
		return struct.unpack("f", struct.pack("f", value))[0]
	except OverflowError:
		return math.copysign(float("inf"), value)

class plugins_quorum_Libraries_Containers_Number32BitArray_:
	# A fixed-size array of 32-bit numbers, as Java's Number32BitArray:
	# SetSize makes a new array of zeros.
	def __init__(self):
		self.array_ = []

	def Get__quorum_integer(self, index):
		if index < 0 or index >= len(self.array_):
			raise _Number32BitArray_Location_Error(index)
		return self.array_[index]

	def Set__quorum_integer__quorum_number(self, index, value):
		if index < 0 or index >= len(self.array_):
			raise _Number32BitArray_Location_Error(index)
		self.array_[index] = _Number32BitArray_Float(value)

	def GetSize(self):
		return len(self.array_)

	def SetSize__quorum_integer(self, size):
		self.array_ = [0.0] * size
