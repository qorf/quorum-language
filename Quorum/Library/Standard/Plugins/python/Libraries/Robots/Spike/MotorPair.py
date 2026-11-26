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

	def RunForDegreesNative__quorum_integer__quorum_integer__quorum_Libraries_Multitasking_Task(self, steering, degrees, waiting):
		_MT_WaitFor(motor_pair.move_for_degrees(self.pair, degrees, steering), waiting)

	def RunForDegreesNative__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_Libraries_Multitasking_Task(self, steering, degrees, velocity, braking_mode, acceleration, deceleration, waiting):
		_MT_WaitFor(motor_pair.move_for_degrees(self.pair, degrees, steering, velocity=velocity, stop=braking_mode, acceleration=acceleration, deceleration=deceleration), waiting)

	def RunForTimeNative__quorum_integer__quorum_integer__quorum_Libraries_Multitasking_Task(self, steering, duration, waiting):
		_MT_WaitFor(motor_pair.move_for_time(self.pair, duration, steering), waiting)

	def RunForTimeNative__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_Libraries_Multitasking_Task(self, steering, duration, velocity, braking_mode, acceleration, deceleration, waiting):
		_MT_WaitFor(motor_pair.move_for_time(self.pair, duration, steering, velocity=velocity, stop=braking_mode, acceleration=acceleration, deceleration=deceleration), waiting)

	def RunTankNative__quorum_integer__quorum_integer__quorum_integer(self, left_velocity, right_velocity, acceleration):
		motor_pair.move_tank(self.pair, left_velocity, right_velocity, acceleration=acceleration)

	def RunTankForDegreesNative__quorum_integer__quorum_integer__quorum_integer__quorum_Libraries_Multitasking_Task(self, left_velocity, right_velocity, degrees, waiting):
		_MT_WaitFor(motor_pair.move_tank_for_degrees(self.pair, degrees, left_velocity, right_velocity), waiting)

	def RunTankForDegreesNative__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_Libraries_Multitasking_Task(self, left_velocity, right_velocity, degrees, braking_mode, acceleration, deceleration, waiting):
		_MT_WaitFor(motor_pair.move_tank_for_degrees(self.pair, degrees, left_velocity, right_velocity, stop=braking_mode, acceleration=acceleration, deceleration=deceleration), waiting)

	def RunTankForTimeNative__quorum_integer__quorum_integer__quorum_integer__quorum_Libraries_Multitasking_Task(self, left_velocity, right_velocity, duration, waiting):
		_MT_WaitFor(motor_pair.move_tank_for_time(self.pair, left_velocity, right_velocity, duration), waiting)

	def RunTankForTimeNative__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_Libraries_Multitasking_Task(self, left_velocity, right_velocity, duration, braking_mode, acceleration, deceleration, waiting):
		_MT_WaitFor(motor_pair.move_tank_for_time(self.pair, left_velocity, right_velocity, duration, stop=braking_mode, acceleration=acceleration, deceleration=deceleration), waiting)

	def StopNative(self):
		motor_pair.stop(self.pair)

	def StopNative__quorum_integer(self, braking_mode):
		motor_pair.stop(self.pair, stop=braking_mode)
