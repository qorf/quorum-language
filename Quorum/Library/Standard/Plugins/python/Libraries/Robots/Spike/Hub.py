class plugins_quorum_Libraries_Robots_Spike_Hub_:
	def __init__(self):
		pass
		
	async def Sleep__quorum_integer(self, value):
		await runloop.sleep_ms(value)
		