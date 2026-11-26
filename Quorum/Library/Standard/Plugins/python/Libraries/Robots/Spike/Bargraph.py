from app import bargraph

class plugins_quorum_Libraries_Robots_Spike_Bargraph_:
	def __init__(self):
		pass

	def ChangeNative__quorum_integer__quorum_number(self, color, value):
		bargraph.change(color, value)

	def ClearAllNative(self):
		bargraph.clear_all()

	def GetValueNative__quorum_integer__quorum_Libraries_Multitasking_Task(self, color, waiting):
		_MT_WaitFor(bargraph.get_value(color), waiting)

	def HideNative(self):
		bargraph.hide()

	def SetValueNative__quorum_integer__quorum_number(self, color, value):
		bargraph.set_value(color, value)

	def ShowNative__quorum_boolean(self, fullscreen):
		bargraph.show(fullscreen)

	def GetResultNative__quorum_Libraries_Multitasking_Task(self, waiting):
		return _MT_ResultOf(waiting)
