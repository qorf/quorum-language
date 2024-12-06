import color_matrix

class plugins_quorum_Libraries_Robots_Spike_ColorMatrix_:
    def __init__(self):
        self.port = 0

    def GetPortNative(self):
        return self.port
    
    def SetPortNative__quorum_integer(self, port):
        self.port = port

    def ClearNative(self):
        color_matrix.clear(self.port)

    def GetPixelColorNative__quorum_integer__quorum_integer(self, x, y):
        quorum_pixelcolor = quorum_Libraries_Containers_Array_()
        pixelcolor = color_matrix.get_pixel(self.port, x, y)
        for item in pixelcolor:
            quorum_pixelcolor.Add__quorum_Libraries_Language_Object(quorum_Libraries_Language_Types_Integer_(0, int(item)))
        return quorum_pixelcolor
    
    def SetPixelColorNative__quorum_integer__quorum_integer__quorum_integer__quorum_integer(self, x, y, color, intensity):
        pixel = (color, intensity)
        color_matrix.set_pixel(self.port, x, y, pixel)

    def ShowColorsNative__quorum_Libraries_Containers_Array__quorum_Libraries_Containers_Array(self, color_values, intensity_values)
        py_color_values = []
        py_intensity_values = []
        for i in range(color_values.GetSize()):
            py_color_values.append(Global_GetValue_(color_values.Get__quorum_integer(i), "integer"))
            
        for i in range(intensity_values.GetSize()):
            py_intensity_values.append(Global_GetValue_(intensity_values.Get__quorum_integer(i), "integer"))

        pixels_list = []
        for i in range(len(py_color_values)):
            pixels_list.append((py_color_values[i], py_intensity_values[i]))

        color_matrix.show(self.port, pixels_list)