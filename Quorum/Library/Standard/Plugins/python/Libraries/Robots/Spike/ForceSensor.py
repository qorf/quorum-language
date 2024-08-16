import force_sensor

class plugins_quorum_Libraries_Robots_Spike_ForceSensor_:
	def __init__(self):
		self.port = 0
	
	def GetPortNative(self):
		return self.port
		
	def SetPortNative__quorum_integer(self, port):
		self.port = port
		
	def GetForceNative(self):
		return force_sensor.force(self.port)

	def GetRawForceNative(self):
		return force_sensor.raw(self.port)

	def IsPressedNative(self):
		return force_sensor.pressed(self.port)