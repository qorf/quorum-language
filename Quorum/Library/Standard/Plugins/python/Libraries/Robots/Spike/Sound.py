from hub import sound

class plugins_quorum_Libraries_Robots_Spike_Sound_:
    def __init__(self):
        pass

    def BeepNative__quorum_integer__quorum_integer__quorum_integer__quorum_Libraries_Multitasking_Task(self, frequency, duration, volume, waiting):
        _MT_WaitFor(sound.beep(frequency, duration, volume), waiting)

    def BeepNative__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_Libraries_Multitasking_Task(self, frequency, duration, volume, \
                                                                                                                                                                                   attack, decay, sustain, release, \
                                                                                                                                                                                    transition, waveform, channel, waiting):
        _MT_WaitFor(sound.beep(frequency, duration, volume, attack=attack, decay=decay, sustain=sustain, release=release, transition=transition, waveform=waveform, channel=channel), waiting)

    def StopSoundNative(self):
        sound.stop()

    def SetVolumeNative__quorum_integer(self, vol):
        sound.volume(vol)
