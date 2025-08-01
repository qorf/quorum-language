import hub

class plugins_quorum_Libraries_Robots_Spike_Hub_:
	def __init__(self):
		pass
		
	async def Sleep__quorum_integer(self, value):
		await runloop.sleep_ms(value)

	def GetDeviceUUIDNative(self):
		return hub.device_uuid()
	
	def GetHardwareIDNative(self):
		return hub.hardware_id()
		
	def PowerOffNative(self):
		return hub.power_off()
	
	def GetTemperatureNative(self):
		return hub.temperature()