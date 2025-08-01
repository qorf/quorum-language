from hub import sound

class plugins_quorum_Libraries_Robots_Spike_Sound_:
    def __init__(self):
        pass

    async def Beep__quorum_integer__quorum_integer__quorum_integer(self, frequency, duration, volume):
        await sound.beep(frequency, duration, volume)

    async def Beep__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer(self, frequency, duration, volume, \
                                                                                                                                                                                   attack, decay, sustain, release, \
                                                                                                                                                                                    transition, waveform, channel):
        await sound.beep(frequency, duration, volume, attack=attack, decay=decay, sustain=sustain, release=release, transition=transition, waveform=waveform, channel=channel)

    def StopSoundNative(self):
        sound.stop()

    def SetVolumeNative__quorum_integer(self, vol):
        sound.volume(vol)
