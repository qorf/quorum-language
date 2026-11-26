from app import sound

class plugins_quorum_Libraries_Robots_Spike_AppSound_:
	def __init__(self):
		pass
	
	def PlayNative__quorum_text__quorum_Libraries_Multitasking_Task(self, soundName, waiting):
		_MT_WaitFor(sound.play(soundName), waiting)

	def PlayNative__quorum_text__quorum_integer__quorum_integer__quorum_integer__quorum_Libraries_Multitasking_Task(self, soundName, volume, pitch, pan, waiting):
		_MT_WaitFor(sound.play(soundName, volume=volume, pitch=pitch, pan=pan), waiting)
	
	def SetAttributesNative__quorum_integer__quorum_integer__quorum_integer(self, volume, pitch, pan):
		sound.set_attributes(volume, pitch, pan)

	def StopNative(self):
		sound.hide()