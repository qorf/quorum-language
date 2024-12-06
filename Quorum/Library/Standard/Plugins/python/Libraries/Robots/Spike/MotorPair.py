import motor_pair

class plugins_quorum_Libraries_Robots_Spike_MotorPair_:
	def __init__(self):
		# automatically creating a pair may overwrite a users existing pair, so the user must do that themself
		self.pair = 0

	def GetPairNative(self):
		return self.pair

	def SetPairNative__quorum_integer(self, pair):
		self.pair = pair

	def SetPortsNative__quorum_integer__quorum_integer(self, left_motor_port, right_motor_port):
		motor_pair.pair(self.pair, left_motor_port, right_motor_port)

	def RemovePortsNative(self):
		motor_pair.unpair(self.pair)

	def RunNative__quorum_integer(self, steering):
		motor_pair.move(self.pair, steering)

	def RunNative__quorum_integer__quorum_integer__quorum_integer(self, steering, velocity, acceleration):
		motor_pair.move(self.pair, steering, velocity=velocity, acceleration=acceleration)

	async def RunForDegrees__quorum_integer__quorum_integer(self, steering, degrees):
		await motor_pair.move_for_degrees(self.pair, degrees, steering)

	async def RunForDegrees__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer(self, steering, degrees, velocity, braking_mode, acceleration, deceleration):
		await motor_pair.move_for_degrees(self.pair, degrees, steering, velocity=velocity, stop=braking_mode, acceleration=acceleration, deceleration=deceleration)

	async def RunForTime__quorum_integer__quorum_integer(self, steering, duration):
		await motor_pair.move_for_time(self.pair, duration, steering)

	async def RunForTime__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer(self, steering, duration, velocity, braking_mode, acceleration, deceleration):
		await motor_pair.move_for_time(self.pair, duration, steering, velocity=velocity, stop=braking_mode, acceleration=acceleration, deceleration=deceleration)

	def RunTankNative__quorum_integer__quorum_integer__quorum_integer(self, left_velocity, right_velocity, acceleration):
		motor_pair.move_tank(self.pair, left_velocity, right_velocity, acceleration=acceleration)

	async def RunTankForDegrees__quorum_integer__quorum_integer__quorum_integer(self, left_velocity, right_velocity, degrees):
		await motor_pair.move_tank_for_degrees(self.pair, degrees, left_velocity, right_velocity)

	async def RunTankForDegrees__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer(self, left_velocity, right_velocity, degrees, braking_mode, acceleration, deceleration):
		await motor_pair.move_tank_for_degrees(self.pair, degrees, left_velocity, right_velocity, stop=braking_mode, acceleration=acceleration, deceleration=deceleration)

	async def RunTankForTime__quorum_integer__quorum_integer__quorum_integer(self, left_velocity, right_velocity, duration):
		await motor_pair.move_tank_for_time(self.pair, left_velocity, right_velocity, duration)

	async def RunTankForTime__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer(self, left_velocity, right_velocity, duration, braking_mode, acceleration, deceleration):
		await motor_pair.move_tank_for_time(self.pair, left_velocity, right_velocity, duration, stop=braking_mode, acceleration=acceleration, deceleration=deceleration)

	def StopNative(self):
		motor_pair.stop(self.pair)

	def StopNative__quorum_integer(self, braking_mode):
		motor_pair.stop(self.pair, stop=braking_mode)
