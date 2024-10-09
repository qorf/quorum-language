from app import display

class plugins_quorum_Libraries_Robots_Spike_Display_:
	def __init__(self):
		pass

	def HideNative(self):
		display.hide()

	def ImageNative(self, image):
		display.image(image)

	def ShowNative(self, fullscreen):
		display.show(fullscreen)
	
	def TextNative(self, text_value):
		display.text(text_value)
