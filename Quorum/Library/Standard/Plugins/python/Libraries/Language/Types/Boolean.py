class plugins_quorum_Libraries_Language_Types_Boolean_:
	def __init__(self, value = False):
		self.myBoolean = value

	def SetValueNative__quorum_boolean(self, bool):
		myBoolean = bool;
    
	def GetHashCode():
		if self.myBoolean:
			return 1231
		else:
			return 1237