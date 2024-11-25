from hub import motion_sensor

class plugins_quorum_Libraries_Robots_Spike_MotionSensor_:
    def __init__(self):
        pass

    def GetAccelerationNative__quorum_boolean(self, raw_data):
        quorum_acc = quorum_Libraries_Containers_Array_()
        acc = motion_sensor.acceleration(raw_data)
        for item in acc:
            quorum_acc.Add__quorum_Libraries_Language_Object(quorum_Libraries_Language_Types_Integer_(0, int(item)))
        return quorum_acc
    
    def GetAngularVelocityNative__quorum_boolean(self, raw_data):
        quorum_vel = quorum_Libraries_Containers_Array_()
        vel = motion_sensor.angular_velocity(raw_data)
        for item in vel:
            quorum_vel.Add__quorum_Libraries_Language_Object(quorum_Libraries_Language_Types_Integer_(0, int(item)))
        return quorum_vel
    
    def GetGestureNative(self):
        return motion_sensor.gesture()
    
    def GetYawFaceNative(self):
        return motion_sensor.get_yaw_face()
    
    def GetQuaternionNative(self):
        quorum_quaternion = quorum_Libraries_Containers_Array_()
        quaternion = motion_sensor.quaternion()
        for item in quaternion:
            quorum_quaternion.Add_quorum_Libraries_Language_Object(quorum_Libraries_Language_Types_Number_(0, float(item)))
        return quorum_quaternion
    
    def ResetTapCountNative(self):
        motion_sensor.reset_tap_count()

    def ChangeYawOffsetNative__quorum_integer(self, angle):
        motion_sensor.reset_yaw(angle)

    def SetYawFaceNative__quorum_integer(self, upface):
        return motion_sensor.set_yaw_face(upface)
    
    def IsHubStableNative(self):
        return motion_sensor.stable()
    
    def GetTapCountNative(self):
        return motion_sensor.tap_count()
    
    def GetTiltAnglesNative(self):
        quorum_angles = quorum_Libraries_Containers_Array_()
        angles = motion_sensor.tilt_angles()
        for item in angles:
            quorum_angles.Add__quorum_Libraries_Language_Object(quorum_Libraries_Language_Types_Integer_(0, int(item)))
        return quorum_angles
    
    def GetCurrentUpFaceNative(self):
        return motion_sensor.up_face()