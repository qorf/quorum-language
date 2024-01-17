def Bool_(value):
	val = quorum_Libraries_Language_Types_Boolean_()
	val.SetValue__quorum_boolean(value)
	return val

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

