class plugins_quorum_Libraries_Containers_Array_:
	def __init__(self):
		self.array_ = []
		self.DEFAULT_CAPACITY = 10
		self.DEFAULT_RESIZE = 2
		self.capacity = self.DEFAULT_CAPACITY
		self.resizable = True
		return
	

	def SetNative__quorum_integer__quorum_Libraries_Language_Object(self, index, value):
		self.array_[index] = value

	def GetNative__quorum_integer(self, index):
		return self.array_[index]

	def SetSizeNative__quorum_integer(self, size):
		self.array_ = [None] * size
		self.capacity = size

	def SetSizeNoFillNative__quorum_integer(self, size):
		self.SetSizeNative__quorum_integer(size)

	def CheckCapacity_(self):
		if self.GetSize() + 1 > self.capacity and self.resizable:
			self.capacity = self.capacity * self.DEFAULT_RESIZE
		elif self.GetSize() + 1 > self.capacity and not self.resizable:
			raise Exception("Cannot add to a non-resizable array with a max size of " + str(self.capacity))
			
	def AddNative__quorum_integer__quorum_Libraries_Language_Object(self, index, value):
		self.CheckCapacity_()
		self.array_.insert(index, value)
		return

	def AddNative__quorum_Libraries_Language_Object(self, value):
		self.CheckCapacity_()
		self.array_.append(value)
		return

	def RemoveAtNative__quorum_integer(self, index):
		return self.array_.pop(index)

	def SetMaxSize__quorum_integer(self, size):
		if self.capacity > size:
			self.array_ = self.array_[:size]
		else:
			self.capacity = size
			

	def GetMaxSize(self):
		return self.capacity

	def GetSize(self):
		return len(self.array_)

	def GetAutoResize(self):
		return self.resizable

	def SetAutoResize__quorum_boolean(self, resize):
		self.resizable = resize

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
