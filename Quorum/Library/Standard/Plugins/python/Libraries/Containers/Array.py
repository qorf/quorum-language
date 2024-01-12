class plugins_quorum_Libraries_Containers_Array_:
	def __init__(self):
		self.array_ = []
		self.size = 0
		self.resizable = True
		self.DEFAULT_CAPACITY = 10
		self.capacity = self.DEFAULT_CAPACITY
		self.DEFAULT_RESIZE = 2
		return
	

	def SetNative__quorum_integer__quorum_Libraries_Language_Object(self, index, value):
		self.array_[index] = value

	def GetNative__quorum_integer(self, index):
		return self.array_[index]

	def SetSizeNative__quorum_integer(self, size):
		return

	def SetSizeNoFillNative__quorum_integer(self, size):
		self.SetSizeNative__quorum_integer(size)

	def AddNative__quorum_integer__quorum_Libraries_Language_Object(self, index, value):
		return

	def AddNative__quorum_Libraries_Language_Object(self, value):
		return

	def RemoveAtNative__quorum_integer(self, index):
		return

	def SetMaxSize__quorum_integer(self, size):
		return

	def GetMaxSize(self):
		return 0

	def GetSize(self):
		return self.size

	def GetAutoResize(self):
		return self.resizable

	def SetAutoResize__quorum_boolean(self, resize):
		self.resizable = resize;

	def IsEmpty(self):
		return self.size == 0

	def ClearContents__quorum_integer__quorum_integer(self, start, stop):
		return

	def Empty(self):
		self.array_.length = 0
		self.size = 0

	def Empty__quorum_boolean(self, value):
		if value:
			self.Empty()
		else:
			self.size = 0
