import color_sensor

class plugins_quorum_Libraries_Robots_Spike_ColorSensor_:
	def __init__(self):
		self.port = 0
	
	def GetPortNative(self):
		return self.port
		
	def SetPortNative__quorum_integer(self, port):
		self.port = port
		
	def GetColorIntegerNative(self):
		return color_sensor.color(self.port)

	def GetReflectionNative(self):
		return color_sensor.reflection(self.port)
		
	def GetRGBINative(self):
		quorum_rgbi = quorum_Libraries_Containers_Array_()
		rgbi = color_sensor.rgbi(self.port)
		for item in rgbi:
			quorum_rgbi.Add__quorum_Libraries_Language_Object(quorum_Libraries_Language_Types_Integer_(0, int(item)))
		return  quorum_rgbi
	
		