<?php $classPageTitle = "Robot"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Robots.Robot</h1>
<input type="hidden" id="classkey" value="Libraries.Robots.Robot" /><h2> <span class="controllable" data-componentType="class-name">Class Robot</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Robot class represents a physical iRobot Create. iRobot Create is a primitive robot or we can say it is a complete robot development kit that allows you to program some fundamental robot behaviors. When a Robot object is instantiated, it is automatically connected to the controller in the safe mode i.e with the safety features turned on. After which we can make the robot perform different actions like moving the robot front, back and in a circle. The robot has two bumpers, left bumper and right bumper namely. The robot has three wheels, left wheel, right wheel and center wheel. It also has four cliff sensors namely, left cliff sensor, left front cliff sensor, right cliff sensor, and right front cliff sensor. The cliff sensors are nothing but the IR sensors. The speed with which the robot moves has to be mentioned. Speed is always mentioned in terms of percentage. The maximum speed with which the robot can move is at 0.5 meters/second. Speed range for all commands is +/-4 percent to +/- 100 percent. Lag values less than 0.05 should be avoided and larger times should be used for the angle and distance functions.  You can attach and control other hardware and electronic devices to iRobot Create; Such as a robotic arm, light display, or a ranging sensor. It can also become a musical instrument! By using some of the song commands. We can write and save up to 16 songs in iRobot Create.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Robots.Robot
// Declaraing the Robot
Robot robot
// Move the robot straight, at 100% speed (0.5 meters per second)
robot:MoveStraight(100)
// The Robot wheels are stopped
robot:StopWheels()
// Connection between the robot and the controller is severed
robot:Disconnect()</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Connect">public system action Connect()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Connect"><p>The robot can be only controlled using the the controller. Inorder to do so we need to first set a connection between the robot and the controller. This action sets a connection between the robot and the controller. Once the connection is made, robot can now be controlled by the controller.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Connect"><em>Example Code:</em></span>
<pre class="code">Robot robot
// Set connection between the robot and the controller
robot:Connect()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Disconnect">public system action Disconnect()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Disconnect"><p>This action breaks the connection between the robot and the controller. Once the connection between the robot and the controller is severed, we can no longer control the robot. Inorder to control the robot again we have to make a connection between the robot and the controller again.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Disconnect"><em>Example Code:</em></span>
<pre class="code">Robot robot
robot:MoveStraight(90)
//break the connection between the robot and the controller
robot:Disconnect()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetLeftFrontSensorValue">public system action GetLeftFrontSensorValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetLeftFrontSensorValue"><p>This action returns 12 bit analog value from left front cliff sensor. This value represents the radiation intensity measured by the cliff sensor. Data has been gathered within 0.1 seconds.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: 12 bit analog value from left front cliff sensor,   
                   which is an integer.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetLeftFrontSensorValue"><em>Example Code:</em></span>
<pre class="code">Robot robot
integer amount
if robot:IsLeftFrontSensorTriggered() = true
   amount = robot:GetLeftFrontSensorValue()
   output "LeftFrontSensorTriggered" + amount
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetLeftSensorValue">public system action GetLeftSensorValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetLeftSensorValue"><p>This action returns 12 bit analog value from left cliff sensor. This value represents the radiation intensity measured by the cliff sensor. Data has been gathered within 0.1 seconds.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: 12 bit analog value from left cliff sensor,   
                   which is an integer.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetLeftSensorValue"><em>Example Code:</em></span>
<pre class="code">Robot robot
integer amount
if robot:IsLeftSensorTriggered() = true
   amount = robot:GetLeftSensorValue()
   output "LeftSensorTriggered" + amount
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetLeftWheelSpeed:number">public system action GetLeftWheelSpeed(number lag)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetLeftWheelSpeed:number"><p>This action returns the left wheel speed of the robot.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="lag"><strong>number</strong> <em>lag</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="lag">is in seconds, is the time lag with in which
        the left wheel speed of the robot is gathered.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: speed of the left wheel, which is in percentage.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetLeftWheelSpeed:number"><em>Example Code:</em></span>
<pre class="code">Robot robot
// left wheel speed of the robot is returned
number LeftWheelSpeed = robot:GetLeftWheelSpeed(0.1)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMaximumSpeed">public system action GetMaximumSpeed()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMaximumSpeed"><p>This action returns the maximum speed with which the robot can move, which is 0.5 meters/second.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: maximum speed which is in meters/second.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMaximumSpeed"><em>Example Code:</em></span>
<pre class="code">Robot robot
// maximum speed of the robot is returned, which is 0.5 meters/second
number MaximumSpeed = robot:GetMaximumSpeed()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMode">public system action GetMode()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMode"><p></p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: </li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetRightFrontSensorValue">public system action GetRightFrontSensorValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetRightFrontSensorValue"><p>This action returns 12 bit analog value from right front cliff sensor. This value represents the radiation intensity measured by the cliff sensor. Data has been gathered within 0.1 seconds.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: 12 bit analog value from right front cliff sensor,   
                   which is an integer.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetRightFrontSensorValue"><em>Example Code:</em></span>
<pre class="code">Robot robot
integer amount
if robot:IsRightFrontSensorTriggered() = true
   amount = robot:GetRightFrontSensorValue()
   output "RightFrontSensorTriggered" + amount
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetRightSensorValue">public system action GetRightSensorValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetRightSensorValue"><p>This action returns 12 bit analog value from right cliff sensor. This value represents the radiation intensity measured by the cliff sensor. Data has been gathered within 0.1 seconds.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: 12 bit analog value from right cliff sensor, which is 
                   an integer.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetRightSensorValue"><em>Example Code:</em></span>
<pre class="code">Robot robot
integer amount
if robot:IsRightSensorTriggered() = true
   amount = robot:GetRightSensorValue()
   output "RightSensorTriggered" + amount
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetRightWheelSpeed:number">public system action GetRightWheelSpeed(number lag)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetRightWheelSpeed:number"><p>This action returns the right wheel speed of the robot.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="lag"><strong>number</strong> <em>lag</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="lag">is in seconds, is the time lag with in which
        the right wheel speed of the robot is gathered.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: speed of the right wheel, which is in percentage.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetRightWheelSpeed:number"><em>Example Code:</em></span>
<pre class="code">Robot robot
// left wheel speed of the robot is returned
number RightWheelSpeed = robot:GetRightWheelSpeed(0.1)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSpeed:number">public system action GetSpeed(number lag)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSpeed:number"><p>This action returns the speed with which the robot was asked to move.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="lag"><strong>number</strong> <em>lag</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="lag">is in seconds, is the time lag with in which
        the speed of the robot is gathered.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: speed which is in percentage.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSpeed:number"><em>Example Code:</em></span>
<pre class="code">Robot robot
// speed of the robot is returned
number Speed = robot:GetSpeed(0.1)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetTotalAngle">public system action GetTotalAngle()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetTotalAngle"><p>This action returns the total accumulated angle the robot has turned since it was turned on or the robot angle was set. Turning left(or Counter Clockwise) direction increases this value, while turning right(or Clockwise) direction decreases this value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the total accumulated angle in degrees.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetTotalAngle"><em>Example Code:</em></span>
<pre class="code">Robot robot
robot:TurnRight(90.0/360.0, 100)
robot:TurnLeft(180.0/360.0, 100)
// the value of angle is -90+180 = +90 degrees
// -90 degrees for turning right, +180 degrees for turning left
integer angle = robot:GetTotalAngle()
robot:StopWheels()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetTotalDistance">public system action GetTotalDistance()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetTotalDistance"><p>This action returns the total accumulated distance the robot has moved since it was turned on or the robot distance was set. Moving forward increases this value, while moving backwards decreases this value. The distance returned by this action is not very precise, it is approximately +/- few millimeters of the actual distance.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: the total accumulated distance in meters.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetTotalDistance"><em>Example Code:</em></span>
<pre class="code">Robot robot
// robot moves forward for 0.5 meters at the 100% speed (0.5 meter/second)
repeate while robot:GetTotalDistance() &lt; 0.5
robot:MoveStraight(100)
robot:StopWheels()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsCenterWheelTriggered:number">public system action IsCenterWheelTriggered(number lag)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsCenterWheelTriggered:number"><p>This action checks if the center wheel of the robot is dropped or is standing safe on land.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="lag"><strong>number</strong> <em>lag</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="lag">is in seconds, is the time lag with in which
        the center wheel is checked to see if it is dropped or not.

        Attribute : Returns true if center wheel is drooped, otherwise returns
        false</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsCenterWheelTriggered:number"><em>Example Code:</em></span>
<pre class="code">Robot robot
// if center wheel of the robot is dropped, the robot is stopped
if robot:IsCenterWheelDropped(0.1)
   robot:StopWheels()
   output "Center wheel is dropped."
end
robot:StopWheels()
robot:Disconnect()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsLeftBumperTriggered:number">public system action IsLeftBumperTriggered(number lag)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsLeftBumperTriggered:number"><p>This action tells if the left bumper of the Robot is currently being pressed or not.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="lag"><strong>number</strong> <em>lag</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="lag">is in seconds, is the time lag with in which
        the bumper is checked to see if it is pressed or not.

        Attribute : Returns true if left bumper is pressed, otherwise returns
        false</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsLeftBumperTriggered:number"><em>Example Code:</em></span>
<pre class="code">Robot robot
// turns the robot right by 90 degrees at 100% speed, if
// left bumper is pressed.
if robot:IsLeftBumperpressed(0.1)
   robot:TurnRight(90.0/360.0, 100)
end
robot:StopWheels()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsLeftFrontSensorTriggered">public system action IsLeftFrontSensorTriggered()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsLeftFrontSensorTriggered"><p>This action is similar to the previous function except, it ckecks if the left front cliff sensor is over a cliff or over a black line.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if left front cliff sensor is over a cliff or a 
        black line, otherwise returns false.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsLeftSensorTriggered">public system action IsLeftSensorTriggered()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsLeftSensorTriggered"><p>This action checks if the left cliff sensor is over a cliff or over a black line. The cliff sensor is an IR sensor. For example if the robot is falling from a table this sensor is triggered.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if left cliff sensor is over a cliff or a black 
        line, otherwise returns false.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsLeftSensorTriggered"><em>Example Code:</em></span>
<pre class="code">Robot robot
Button button
// robot turns in counter clockwise direction continuously until
// left cliff sensor detects a cliff
repeat while rob:IsLeftSensorTriggered(0.1) = false
   robot:TurnCounterClockwise(100)
end
robot:StopWheels()
robot:Disconnect()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsLeftWheelTriggered:number">public system action IsLeftWheelTriggered(number lag)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsLeftWheelTriggered:number"><p>This action checks if the left wheel of the robot is dropped or is standing safe on land.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="lag"><strong>number</strong> <em>lag</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="lag">is in seconds, is the time lag with in which
        the left wheel is checked to see if it is dropped or not.

        Attribute : Returns true if left wheel is drooped, otherwise returns
        false</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsLeftWheelTriggered:number"><em>Example Code:</em></span>
<pre class="code">Robot robot
if robot:IsLeftWheelDropped(0.1)
   robot:StopWheels()
   output "Left wheel is dropped."
end
robot:StopWheels()
robot:Disconnect()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsRightBumperTriggered:number">public system action IsRightBumperTriggered(number lag)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsRightBumperTriggered:number"><p>This action tells if the right bumper of the Robot is currently being pressed or not.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="lag"><strong>number</strong> <em>lag</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="lag">is in seconds, is the time lag with in which
        the bumper is checked to see if it is pressed or not.

        Attribute : Returns true if right bumper is pressed, otherwise returns
        false</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsRightBumperTriggered:number"><em>Example Code:</em></span>
<pre class="code">Robot robot
// turns the robot left by 90 degrees at 100% speed, if
// right bumper is pressed.
if robot:IsRightBumperpressed(0.1)
   robot:TurnLeft(0.25, 100)
end
robot:StopWheels()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsRightFrontSensorTriggered">public system action IsRightFrontSensorTriggered()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsRightFrontSensorTriggered"><p>This action is similar to the previous function except, it ckecks if the right front cliff sensor is over a cliff or over a black line.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if right front cliff sensor is over a cliff or a 
        black line, otherwise returns false.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsRightSensorTriggered">public system action IsRightSensorTriggered()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsRightSensorTriggered"><p>This action is similar to the previous function except, it ckecks if the right cliff sensor is over a cliff or over a black line.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if right cliff sensor is over a cliff or a black 
        line, otherwise returns false.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsRightWheelTriggered:number">public system action IsRightWheelTriggered(number lag)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsRightWheelTriggered:number"><p>This action checks if the right wheel of the robot is dropped or is standing safe on land.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="lag"><strong>number</strong> <em>lag</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="lag">is in seconds, is the time lag with in which
        the right wheel is checked to see if it is dropped or not.

        Attribute : Returns true if right wheel is drooped, otherwise returns
        false</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsRightWheelTriggered:number"><em>Example Code:</em></span>
<pre class="code">Robot robot
if robot:IsRightWheelDropped(0.1)
   robot:StopWheels()
   output "Right wheel is dropped."
end
robot:StopWheels()
robot:Disconnect()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsWallDetected:number">public system action IsWallDetected(number lag)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsWallDetected:number"><p></p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="lag"><strong>number</strong> <em>lag</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="lag"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: </li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="MoveInCirle:number:number">public system action MoveInCirle(number speed,number radius)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="MoveInCirle:number:number"><p>This action moves the Robot in a circle whose radius is specified.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="speed"><strong>number</strong> <em>speed</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="speed">is in percentage, radius is in 
        meters per second. Radius is the radius of the circle, along which 
        the robot moves.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="radius"><strong>number</strong> <em>radius</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="radius"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="MoveInCirle:number:number"><em>Example Code:</em></span>
<pre class="code">Robot robot
Button button
repeat while button:IsBlackButtonPressed = false
   // drives the robot in an arc at 20% speed,
   // circle radius is 0.25 meters
   // until black button is pressed.
   robot:MoveInCirle(20, 0.25)
end
robot:StopWheels()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="MoveStraight:number">public system action MoveStraight(number speed)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="MoveStraight:number"><p>This action moves the Robot front in a straight line at the specified speed.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="speed"><strong>number</strong> <em>speed</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="speed">is in percentage, speed with 
        which the robot will move</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="MoveStraight:number"><em>Example Code:</em></span>
<pre class="code">Robot robot
// drives the robot straight, at 100% speed which is 0.5 meters/second
robot:MoveStraight(100)
robot:StopWheels()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="MoveStraight:number:number">public system action MoveStraight(number speed,number distance)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="MoveStraight:number:number"><p>This action moves the Robot in a straight line at the specified speed, for a specified distance.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="speed"><strong>number</strong> <em>speed</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="speed">is in percentage, distance in 
        meters</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="distance"><strong>number</strong> <em>distance</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="distance"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="MoveStraight:number:number"><em>Example Code:</em></span>
<pre class="code">Robot robot
// drives the robot straight, at 50% speed which is 0.25 meters/second
// for 0.5 meters
robot:MoveStraight(50, 0.5)
robot:StopWheels()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="MoveWheels:number:number">public system action MoveWheels(number rightWheelspeed,number leftWheelspeed)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="MoveWheels:number:number"><p>This action moves the Robot with different speeds for each wheel. The left wheel and right wheel speeds of the robot are specified. Giving different values for left and right wheel makes the robot move in a zig zag manner. Giving same values for left and right wheel makes the robot move in a striaght line. Giving negative values for the left wheel and positive values for the right wheel makes the robot turn left. Giving negative values for the right wheel and positive values for the left wheel makes the robot turn right. Giving negative values for both the left and right wheel, makes the robot move backwards.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="rightWheelspeed"><strong>number</strong> <em>rightWheelspeed</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="rightWheelspeed">is the speed of the right wheel in 
        percentage, leftWheelspeed is the speed of the left wheel in 
        percentage.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="leftWheelspeed"><strong>number</strong> <em>leftWheelspeed</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="leftWheelspeed"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="MoveWheels:number:number"><em>Example Code:</em></span>
<pre class="code">Robot robot
// drives the robot front with left wheel at 20% speed which is
// 0.1 meters/second and right wheel at 60% speed which is
// 0.3 meters/second
robot:MoveWithRightLeftSpeed(20, 60)
robot:StopWheels()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RunDemo:integer">public system action RunDemo(integer demo)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RunDemo:integer"><p>This action starts running a built-in demo. There are 9 built-in demos. Please refer the documentation for more details about the demo.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="demo"><strong>integer</strong> <em>demo</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="demo">is an integer; demo can have a value: -1, 0 to 9.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="RunDemo:integer"><em>Example Code:</em></span>
<pre class="code">Robot robot
// demo 4 is a Drive Figure Eight, robot continuously moves in a figure
// 8 pattern
robot:RunDemo(4)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="StopWheels">public system action StopWheels()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="StopWheels"><p>This action halts/stops the wheels of the Robot without making any further movement.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="StopWheels"><em>Example Code:</em></span>
<pre class="code">Robot robot
robot:MoveStraight(100)
//Robot wheels are halted.
robot:StopWheels()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="TurnClockwise:number">public system action TurnClockwise(number speed)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="TurnClockwise:number"><p>This action makes the robot to spin/rotate in the Clockwise direction at the specified speed continuously. Angle is irrelevant here.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="speed"><strong>number</strong> <em>speed</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="speed">in percentage.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="TurnClockwise:number"><em>Example Code:</em></span>
<pre class="code">Robot robot
Button button
// robot turns in clockwise direction continuously until button A is pressed
// at the speed of 0.1 meters/second
repeat while() button:IsAButtonPressed()
   robot:TurnClockwise(20)
end
robot:StopWheels()
robot:Disconnect()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="TurnCounterClockwise:number">public system action TurnCounterClockwise(number speed)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="TurnCounterClockwise:number"><p>This action makes the robot to spin/rotate in the counter Clockwise direction at the specified speed continuously. Angle is irrelevant here.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="speed"><strong>number</strong> <em>speed</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="speed">in percentage.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="TurnCounterClockwise:number"><em>Example Code:</em></span>
<pre class="code">Robot robot
Button button
robot:SetConnection()
// robot turns in counter clockwise direction continuously until
// button B is pressed at 100% speed.
repeat while button:IsBButtonPressed()
   robot:TurnCounterClockwise(100)
end
robot:StopWheels()
robot:Disconnect()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="TurnLeft:number:number">public system action TurnLeft(number turn,number speed)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="TurnLeft:number:number"><p>This action turns the Robot left with the specified amount of turn and speed. One turn is considered as 360 degrees. So if we need to turn the robot left by 90 degrees(quater turn), we need to say robot:TurnLeft(0.25 ,20) or robot:TurnLeft(90.0/360.0 ,20). However robot:TurnLeft(90/360 ,20) does not work. The degrees turned left might not be very precise here, it is approximately +/-5 degrees.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="turn"><strong>number</strong> <em>turn</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="turn">is number of turns, where one turn is 360 degrees, 
                   speed is in percentage.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="speed"><strong>number</strong> <em>speed</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="speed"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="TurnLeft:number:number"><em>Example Code:</em></span>
<pre class="code">Robot robot
// robot does not make any turn, as 90/360 = 0
// use 90.0/360.0 to make the robot turn left by 90 degrees
robot:TurnLeft(90/360,50)
robot:StopWheels()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="TurnOff">public system action TurnOff()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="TurnOff"><p>This action turns off the robot, which means that the robot does not perfom any activity. However the connection between the robot and the controller still exsits.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="TurnOff"><em>Example Code:</em></span>
<pre class="code">Robot robot
// robot is turned off
robot:TurnOff()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="TurnRight:number:number">public system action TurnRight(number turn,number speed)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="TurnRight:number:number"><p>This action turns the Robot right with the specified amount of turn and speed. One turn is considered as 360 degrees. So if we need to turn the robot right by 90 degrees(quater turn), we need to say robot:TurnRight(0.25 ,50) or robot:TurnRight(90.0/360.0 ,50). However robot:TurnRight(90/360 ,50) does not work. The degrees turned right might not be very precise here, it is approximately +/-5 degrees.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="turn"><strong>number</strong> <em>turn</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="turn">is number of turns, where one turn is 360 degrees, 
                   speed is in percentage.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="speed"><strong>number</strong> <em>speed</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="speed"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="TurnRight:number:number"><em>Example Code:</em></span>
<pre class="code">Robot robot
// robot does not make any turn, as 90/360 = 0
// use 90.0/360.0 to make the robot turn right by 90 degrees
robot:TurnRight(90/360,50)
robot:StopWheels()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="TurnSafety:boolean">public system action TurnSafety(boolean enabled)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="TurnSafety:boolean"><p>This action either turns on or off the safety features for the robot. By default the robot is connected with the safe features turned on. With the safety features turned on we can control robot using all commands. However if the robot detects a cliff, or if any of the wheel is dropped, or if the robot charger is plugged in, the robot stops all motors. With the safety features turned off, we still have complete control over the robot. We can make the robot move in any way we wish to, even if its a bad move. For example, the robot will not stop and disconnect, even if it is picked up(wheel dropped) or if the cliff sensors are triggered.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="enabled"><strong>boolean</strong> <em>enabled</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="enabled"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="TurnSafety:boolean"><em>Example Code:</em></span>
<pre class="code">Robot robot
// safety features of the robot is turned on.
robot:TurnSafety(true)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>