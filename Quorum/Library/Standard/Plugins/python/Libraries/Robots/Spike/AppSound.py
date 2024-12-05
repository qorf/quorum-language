from app import sound

class plugins_quorum_Libraries_Robots_Spike_AppSound_:
	def __init__(self):
		pass
	
	async def Play__quorum_text(self, soundName):
		await sound.play(soundName)

	async def Play__quorum_text__quorum_integer__quorum_integer__quorum_integer(self, soundName, volume, pitch, pan):
		await sound.play(soundName, volume=volume, pitch=pitch, pan=pan)
	
	def SetAttributesNative__quorum_integer__quorum_integer__quorum_integer(self, volume, pitch, pan):
		sound.set_attributes(volume, pitch, pan)

	def StopNative(self):
		sound.hide()