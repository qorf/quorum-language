import force_sensor

class plugins_quorum_Libraries_Robots_Spike_ForceSensor_:
	def __init__(self):
		self.port = 0
	
	def GetPortNative(self):
		return self.port
		
	def SetPortNative__quorum_integer(self, port):
		self.port = port
		
	def GetForceNative():
		return force_sensor.force(self.port)

	def GetRawForceNative():
		return force_sensor.raw(self.port)

	def IsPressedNative():
		return force_sensor.pressed(self.port)