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

	def RunNative__quorum_integer__quorum_integer(self, velocity, acceleration):
		motor.run(self.port, velocity, acceleration=acceleration)
		
	def RunForTimeNative__quorum_integer__quorum_integer__quorum_Libraries_Multitasking_Task(self, time, velocity, waiting):
		_MT_WaitFor(motor.run_for_time(self.port, time, velocity), waiting)

	def RunForTimeNative__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_Libraries_Multitasking_Task(self, time, velocity, braking_mode, acceleration, deceleration, waiting):
		_MT_WaitFor(motor.run_for_time(self.port, time, velocity, stop=braking_mode, acceleration=acceleration, deceleration=deceleration), waiting)

	def RunForDegreesNative__quorum_integer__quorum_integer__quorum_Libraries_Multitasking_Task(self, degrees, velocity, waiting):
		_MT_WaitFor(motor.run_for_degrees(self.port, degrees, velocity), waiting)
	
	def RunForDegreesNative__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_Libraries_Multitasking_Task(self, degrees, velocity, braking_mode, acceleration, deceleration, waiting):
		_MT_WaitFor(motor.run_for_degrees(self.port, degrees, velocity, stop=braking_mode, acceleration=acceleration, deceleration=deceleration), waiting)

	def RunToAbsolutePositionNative__quorum_integer__quorum_integer__quorum_Libraries_Multitasking_Task(self, position, velocity, waiting):
		_MT_WaitFor(motor.run_to_absolute_position(self.port, position, velocity), waiting)
		
	def RunToAbsolutePositionNative__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_Libraries_Multitasking_Task(self, position, velocity, direction, braking_mode, acceleration, deceleration, waiting):
		_MT_WaitFor(motor.run_to_absolute_position(self.port, position, velocity, direction=direction, stop=braking_mode, acceleration=acceleration, deceleration=deceleration), waiting)

	def RunToRelativePositionNative__quorum_integer__quorum_integer__quorum_Libraries_Multitasking_Task(self, position, velocity, waiting):
		_MT_WaitFor(motor.run_to_relative_position(self.port, position, velocity), waiting)

	def RunToRelativePositionNative__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_integer__quorum_Libraries_Multitasking_Task(self, position, velocity, braking_mode, acceleration, deceleration, waiting):
		_MT_WaitFor(motor.run_to_relative_position(self.port, position, velocity, stop=braking_mode, acceleration=acceleration, deceleration=deceleration), waiting)
		
	def SetDutyCycleNative__quorum_integer(self, position):
		return motor.set_duty_cycle(self.port, position)
		
	def StopNative(self):
		return motor.stop(self.port)

	def StopNative__quorum_integer(self, braking_mode):
		return motor.stop(self.port, stop=braking_mode)
		
	def GetVelocityNative(self):
		return motor.velocity(self.port)
	