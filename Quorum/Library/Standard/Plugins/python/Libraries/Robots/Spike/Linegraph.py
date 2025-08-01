from app import linegraph

class plugins_quorum_Libraries_Robots_Spike_Linegraph_:
	def __init__(self):
		pass
	
	def ClearNative__quorum_integer(self, color):
		linegraph.clear(color)
		
	def ClearAllNative(self):
		linegraph.clear_all()

	async def GetAverage__quorum_integer(self, color):
		return (await linegraph.get_average(color))
	
	async def GetLast__quorum_integer(self, color):
		return (await linegraph.get_last(color))
	
	async def GetMax__quorum_integer(self, color):
		return (await linegraph.get_max(color))
	
	async def GetMin__quorum_integer(self, color):
		return (await linegraph.get_min(color))

	def HideNative(self):
		linegraph.hide()

	def PlotNative__quorum_integer__quorum_number__quorum_number(self, color, x, y):
		linegraph.plot(color, x, y)

	def ShowNative__quorum_boolean(self, fullscreen):
		linegraph.show(fullscreen)