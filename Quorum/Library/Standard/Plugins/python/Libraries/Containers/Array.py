class plugins_quorum_Libraries_Containers_Array_:
	def __init__(self):
		self.array_ = []
		self.resizable = True
		return
	

	def SetNative__quorum_integer__quorum_Libraries_Language_Object(self, index, value):
		self.array_[index] = value

	def GetNative__quorum_integer(self, index):
		return self.array_[index]

	def SetSizeNative__quorum_integer(self, size):
		self.array_ = [None] * size

	def SetSizeNoFillNative__quorum_integer(self, size):
		self.SetSizeNative__quorum_integer(size)

	def AddNative__quorum_integer__quorum_Libraries_Language_Object(self, index, value):
		self.array_.insert(index, value)
		return

	def AddNative__quorum_Libraries_Language_Object(self, value):
		self.array_.append(value)
		return

	def RemoveAtNative__quorum_integer(self, index):
		return self.array_.pop(index)

	def SetMaxSize__quorum_integer(self, size):
		pass 

	def GetMaxSize(self):
		return (1 << 63) - 1 if (1 << 63) > 0 else (1 << 63)

	def GetSize(self):
		return len(self.array_)

	def GetAutoResize(self):
		return self.resizable

	def SetAutoResize__quorum_boolean(self, resize):
		pass

	def IsEmpty(self):
		return self.GetSize() == 0

	def ClearContents__quorum_integer__quorum_integer(self, start, stop):
		self.array_ = []
		return

	def Empty(self):
		self.array_ = []
		self.size = 0

	def Empty__quorum_boolean(self, value):
		if value:
			self.Empty()
		else:
			self.size = 0
