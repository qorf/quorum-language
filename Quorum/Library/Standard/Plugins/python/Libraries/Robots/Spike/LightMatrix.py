from hub import light_matrix

class plugins_quorum_Libraries_Robots_Spike_LightMatrix_:
	def __init__(self):
		pass
		
	async def Write__quorum_text(self, value):
		await light_matrix.write(value)

	def GetPixelNative__quorum_integer__quorum_integer(self, x, y):
		return light_matrix.get_pixel(x, y)
	
	def SetPixelNative__quorum_integer__quorum_integer__quorum_integer(self, x, y, intensity):
		return light_matrix.set_pixel(x, y, intensity)
	
	def ShowImageNative__quorum_integer(self, image):
		light_matrix.show_image(image)

	def ClearNative(self):
		light_matrix.clear()

	def SetOrientationNative__quorum_integer(self, orientation):
		return light_matrix.set_orientation(orientation)

	def GetOrientationNative(self):
		return light_matrix.get_orientation()
	
	def ShowNative__quorum_Libraries_Containers_Array(self, pixels):
		pyPixels = []
		for i in range(pixels.GetSize()):
			pyPixels.append(Global_GetValue_(pixels.Get__quorum_integer(i), "integer"))
		light_matrix.show(pyPixels)
