import distance_sensor

class plugins_quorum_Libraries_Robots_Spike_DistanceSensor_:
    def __init__(self):
        self.port = 0

    def GetPortNative(self):
        return self.port

    def SetPortNative__quorum_integer(self, port):
        self.port = port

    def GetDistanceNative(self):
        return distance_sensor.distance(self.port)
    
    def GetPixelNative__quorum_integer__quorum_integer(self, x, y):
        return distance_sensor.get_pixel(self.port, x, y)
    
    def SetPixelNative__quorum_integer__quorum_integer__quorum_integer(self, x, y, intensity):
        distance_sensor.set_pixel(self.port, x, y, intensity)

    def ClearLightsNative(self):
        distance_sensor.clear(self.port)

    def SetAllLightsNative__quorum_integer(self, intensity):
        pixels = [intensity] * 4
        distance_sensor.show(self.port, pixels)

    
    def ShowNative__quorum_Libraries_Containers_Array(self, pixels):
        pyPixels = []
        for i in range(pixels.GetSize()):
            pyPixels.append(Global_GetValue_(pixels.Get__quorum_integer(i), "integer"))
        distance_sensor.show(self.port, pyPixels)
        
    