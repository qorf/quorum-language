from app import linegraph

class plugins_quorum_Libraries_Robots_Spike_Linegraph_:
	def __init__(self):
		pass
	
	def ClearNative__quorum_integer(self, color):
		linegraph.clear(color)
		
	def ClearAllNative(self):
		linegraph.clear_all()

	def GetAverageNative__quorum_integer__quorum_Libraries_Multitasking_Task(self, color, waiting):
		_MT_WaitFor(linegraph.get_average(color), waiting)
	
	def GetLastNative__quorum_integer__quorum_Libraries_Multitasking_Task(self, color, waiting):
		_MT_WaitFor(linegraph.get_last(color), waiting)
	
	def GetMaxNative__quorum_integer__quorum_Libraries_Multitasking_Task(self, color, waiting):
		_MT_WaitFor(linegraph.get_max(color), waiting)
	
	def GetMinNative__quorum_integer__quorum_Libraries_Multitasking_Task(self, color, waiting):
		_MT_WaitFor(linegraph.get_min(color), waiting)

	def HideNative(self):
		linegraph.hide()

	def PlotNative__quorum_integer__quorum_number__quorum_number(self, color, x, y):
		linegraph.plot(color, x, y)

	def ShowNative__quorum_boolean(self, fullscreen):
		linegraph.show(fullscreen)

	def GetResultNative__quorum_Libraries_Multitasking_Task(self, waiting):
		return _MT_ResultOf(waiting)
