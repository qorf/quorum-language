import device

class plugins_quorum_Libraries_Robots_Spike_Device_:
    def __init__(self):
        pass

    def GetDataFromPortNative__quorum_integer(self, port):
        raw_data = quorum_Libraries_Containers_Array_()
        data = device.data(port)
        for item in data:
            raw_data.Add__quorum_Libraries_Language_Object(quorum_Libraries_Language_Types_Integer_(0, int(item)))
        return raw_data
    
    def GetIDOfPortNative__quorum_integer(self, port):
        return device.id(port)
    
    def GetDutyCycleNative__quorum_integer(self, port):
        return device.get_duty_cycle(port)
    
    def IsPortReadyNative__quorum_integer(self, port):
        return device.ready(port)
    
    def SetDutyCycleNative__quorum_integer__quorum_integer(self, port, duty_cycle):
        device.set_duty_cyle(port, duty_cycle)