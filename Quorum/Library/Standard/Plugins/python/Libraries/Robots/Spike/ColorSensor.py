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
		
	def SetColorValuesIntoPassedColorNative__quorum_Libraries_Game_Graphics_Color(self, color):
		tuple = color_sensor.rgbi(self.port)
		red = float(tuple[0])
		green = float(tuple[1])
		blue = float(tuple[2])
		intensity = float(tuple[3])
		color.SetColor__quorum_number__quorum_number__quorum_number__quorum_number(red, green, blue, intensity)
	
		