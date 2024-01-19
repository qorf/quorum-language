from hub import light_matrix

class plugins_quorum_Libraries_Robots_Spike_LightMatrix_:
	def __init__(self):
		pass
		
	async def Write__quorum_text(self, value):
		await light_matrix.write(value)