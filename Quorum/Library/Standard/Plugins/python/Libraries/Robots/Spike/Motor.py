import motor

class plugins_quorum_Libraries_Robots_Spike_Motor_:
	def __init__(self):
		self.port = 0
	
	def GetPortNative(self):
		return self.port
		
	def SetPortNative__quorum_integer(self, port):
		self.port = port
		
	def GetDutyCycleNative(self):
		return motor.get_duty_cycle(self.port)
		
	def GetRelativePositionNative(self):
		return motor.relative_position(self.port)
		
	def GetAbsolutePositionNative(self):
		return motor.absolute_position(self.port)
		
	def SetRelativePositionNative__quorum_integer(self, position):
		return motor.reset_relative_position(self.port, position)
		
	def RunNative__quorum_integer(self, velocity):
		motor.run(self.port, velocity)
		
	async def RunForTime__quorum_integer__quorum_integer(self, time, velocity):
		await motor.run_for_time(self.port, time, velocity)
		
	async def RunForDegrees__quorum_integer__quorum_integer(self, degrees, velocity):
		await motor.run_for_degrees(self.port, degrees, velocity)
	
	async def RunToAbsolutePosition__quorum_integer__quorum_integer(self, position, velocity):
		await motor.run_to_absolute_position(self.port, position, velocity)
		
	async def RunToRelativePosition__quorum_integer__quorum_integer(self, position, velocity):
		await motor.run_to_relative_position(self.port, position, velocity)
		
	def SetDutyCycleNative__quorum_integer(self, position):
		return motor.set_duty_cycle(self.port, position)
		
	def StopNative(self):
		return motor.stop(self.port)

	def StopNative__quorum_integer(self, braking_mode):
		return motor.stop(self.port, stop=braking_mode)
		
	def GetVelocityNative(self):
		return motor.velocity(self.port) * 10
	