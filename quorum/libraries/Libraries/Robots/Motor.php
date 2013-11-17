<?php $classPageTitle = "Motor"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Robots.Motor</h1>
<input type="hidden" id="classkey" value="Libraries.Robots.Motor" /><h2> <span class="controllable" data-componentType="class-name">Class Motor</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Motor class represents a physical motor. Each motor has a designation, an integer, that says which motor it is (e.g., 0, 1, 2 or 3). In order to use a motor we need to set connection with the motor first and then use the motor for other operations. Generally most of the motors have 1 circumference = 1100 ticks. The maximum speed with which the motor can move is at 1000 ticks per second. In order for us to specify the speed at which the motor has to move, speed must be mentioned in terms of the percentage of the maximum speed.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Robots.Motor
class Main
   action Main
      // Declaraing the motor
      Motor motor
      // Set connection with the motor connected to port 1
      motor:SetPort(1)
      // Move the motor to position 2000, with 100 percent speed(i.e 1000 ticks per second)
      motor:MoveToPosition(100, 2000)
      // Wait until the motor has completed its movement
      motor:Wait()
      // Switch off the motor
      motor:Off()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Robots/Connectable.php">Libraries.Robots.Connectable</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AllMotorOff">public system action AllMotorOff()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AllMotorOff"><p>This action is used to switch off all the motors connected to the controller.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="AllMotorOff"><em>Example Code:</em></span>
<pre class="code">Motor motor
motor:SetPort(0)
// switches off all the other motors, if any connected
motor:AllMotorOff()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPort">public system action GetPort()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPort"><p>This action returns the port to which the motor is connected to.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the port to which the motor is connected to</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPort"><em>Example Code:</em></span>
<pre class="code">Motor motor
integer mc = motor:GetPort()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPosition">public system action GetPosition()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPosition"><p>This action returns the currnet position of the motor.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the current position of the motor</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPosition"><em>Example Code:</em></span>
<pre class="code">Motor motor
motor:SetPort(0)
integer mp = motor:GetPosition()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSpeed">public system action GetSpeed()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSpeed"><p>This action is used to set the speed of the motor. This speed is used as the default speed, if speed is not mentioned.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the speed in integer, which is the percentage of the 
                   maximum speed;</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSpeed"><em>Example Code:</em></span>
<pre class="code">Motor motor
motor:SetPort(0)
motor:SetSpeed(500)
motor:MoveFromHere(50, 900)
// a value 50 is returned
integer speed = GetSpeed()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsMotionComplete">public system action IsMotionComplete()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsMotionComplete"><p>This action tells if the motor is moving towards the goal position or not.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if specified motion is complete otherwise returns
        false</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsMotionComplete"><em>Example Code:</em></span>
<pre class="code">Motor motor
motor:SetPort(0)
motor:MoveFromHere(90, 500)
boolean success
success = motor:IsMotionComplete()
motor:Off()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="MoveBackward">public system action MoveBackward()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="MoveBackward"><p>This action moves the motor in the backward direction at the specified speed. The direction is determined by the plug orientation. The motor moves at the speed set using SetSpeed() action.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="MoveBackward"><em>Example Code:</em></span>
<pre class="code">Motor motor
motor:SetPort(0)
//motor moves Backward at a previously set speed
motor:MoveBackward()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="MoveBackward:integer">public system action MoveBackward(integer speed)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="MoveBackward:integer"><p>This action moves the motor in the backward direction at the specified speed. The direction is determined by the plug orientation.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="speed"><strong>integer</strong> <em>speed</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="speed">is in integer, which is the percentage of the 
                   maximum speed.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="MoveBackward:integer"><em>Example Code:</em></span>
<pre class="code">Motor motor
motor:SetPort(0)
//motor moves Backward at 50% of the maximum speed (i.e. 500 ticks per second)
motor:MoveBackward(50)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="MoveForward">public system action MoveForward()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="MoveForward"><p>This action moves the motor in the forward direction. The direction is determined by the plug orientation. The motor moves at the speed set using SetSpeed() action.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="MoveForward"><em>Example Code:</em></span>
<pre class="code">Motor motor
motor:SetPort(0)
motor:MoveFoward()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="MoveForward:integer">public system action MoveForward(integer speed)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="MoveForward:integer"><p>This action moves the motor in the forward direction at the specified speed. The direction is determined by the plug orientation.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="speed"><strong>integer</strong> <em>speed</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="speed">is in integer, which is the percentage of the 
                   maximum speed.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="MoveForward:integer"><em>Example Code:</em></span>
<pre class="code">Motor motor
motor:SetPort(0)
//motor moves forward at 50% of the maximum speed (i.e. 500 ticks per second)
motor:MoveFoward(50)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="MoveFromHere:integer">public system action MoveFromHere(integer position)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="MoveFromHere:integer"><p>This action moves the motor to the specified relative position at the specified speed.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="position"><strong>integer</strong> <em>position</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="position">to which the motor has to be moved reltively,
                   is in integer.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="MoveFromHere:integer"><em>Example Code:</em></span>
<pre class="code">Motor motor
motor:SetPort(0)
motor:MoveFromHere(500)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="MoveFromHere:integer:integer">public system action MoveFromHere(integer speed,integer position)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="MoveFromHere:integer:integer"><p>This action moves the motor to the specified relative position at the specified speed.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="speed"><strong>integer</strong> <em>speed</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="speed">is in integer, which is the percentage of the 
                   maximum speed;position to which the motor has to be moved 
                   reltively is in integer.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="position"><strong>integer</strong> <em>position</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="position"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="MoveFromHere:integer:integer"><em>Example Code:</em></span>
<pre class="code">Motor motor
motor:SetPort(0)
motor:MoveFromHere(50, 700)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="MoveToPosition:integer">public system action MoveToPosition(integer position)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="MoveToPosition:integer"><p>This action moves the motor to the specified absolute position at a previously set speed.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="position"><strong>integer</strong> <em>position</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="position">to which the motor has to be moved, is in
                   integer</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="MoveToPosition:integer"><em>Example Code:</em></span>
<pre class="code">Motor motor
motor:SetPort(0)
motor:MoveToPosition(2000)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="MoveToPosition:integer:integer">public system action MoveToPosition(integer speed,integer position)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="MoveToPosition:integer:integer"><p>This action moves the motor to the specified absolute position at a previously set speed.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="speed"><strong>integer</strong> <em>speed</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="speed">is in integer, which is the percentage of the 
                   maximum speed; position to which the motor has to be moved is 
                   in integer.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="position"><strong>integer</strong> <em>position</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="position"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="MoveToPosition:integer:integer"><em>Example Code:</em></span>
<pre class="code">Motor motor
motor:SetPort(0)
motor:MoveToPosition(50, 2000)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Off">public system action Off()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Off"><p>This action switches off the motor.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Off"><em>Example Code:</em></span>
<pre class="code">Motor motor
motor:SetPort(0)
motor:MoveFromHere(40, 500)
motor:Off()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="ResetPosition">public system action ResetPosition()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="ResetPosition"><p>This action sets the position of the motor to 0.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="ResetPosition"><em>Example Code:</em></span>
<pre class="code">Motor motor
motor:SetPort(0)
motor:ResetPosition()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPort:integer">public system action SetPort(integer port)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetPort:integer"><p>This action connects the motor to the specified port.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="port"><strong>integer</strong> <em>port</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="port">to which we intend to connect the motor to</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetPort:integer"><em>Example Code:</em></span>
<pre class="code">Motor motor
motor:SetPort(0)
//motor is connected to port 0</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetSpeed:integer">public system action SetSpeed(integer speed)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetSpeed:integer"><p>This action is used to set the speed of the motor. This speed is used as the default speed, if speed is not mentioned.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="speed"><strong>integer</strong> <em>speed</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="speed">is in ticks per second.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetSpeed:integer"><em>Example Code:</em></span>
<pre class="code">Motor motor
motor:SetPort(0)
motor:SetSpeed(1000)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Wait">public system action Wait()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Wait"><p>This action waits until the motor has completed the specified movement. specified speed.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Wait"><em>Example Code:</em></span>
<pre class="code">Motor motor
motor:SetPort(0)
motor:MoveFromHere(50, 600)
motor:Wait()</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>