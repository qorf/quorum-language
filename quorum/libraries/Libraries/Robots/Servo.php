<?php $classPageTitle = "Servo"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Robots.Servo</h1>
<input type="hidden" id="classkey" value="Libraries.Robots.Servo" /><h2> <span class="controllable" data-componentType="class-name">Class Servo</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Servo class represents a physical servo. Each servo has a designation, an integer, that says which port this servo is connected to (e.g., 0, 1, 2 or 3). In order to use a servo we need to set connection with the servo first and then use the servo for other operations. A standard servo has a range of about 180 degrees and 2048 positions in that range it can be set to. When you first set connection to the servo using SetPort(), if we have not specified a position, all servos will go to the middle position (1024). After setting a servo position, the program needs to wait for the servo to get to the desired position. Servos are typically used in arms, or claws of the robot that require high precision.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Robots.Servo
// Declaraing the servo
Servo servo
// Set connection with the servo connected to port 1
servo:SetPort(1)
// Move the servo to position 2000
servo:MoveToPosition(2000)
// Switch off the servo
servo:Off()</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Robots/Connectable.php">Libraries.Robots.Connectable</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPort">public system action GetPort()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPort"><p>This action returns the port to which the servo is connected to.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the port to which the servo is connected to</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPort"><em>Example Code:</em></span>
<pre class="code">Servo servo
servo:SetPort(0)
integer port = servo:GetPort()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPosition">public system action GetPosition()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPosition"><p>This action connects the servo to the specified port.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the current position of the servo.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPosition"><em>Example Code:</em></span>
<pre class="code">Servo servo
servo:SetPort(0)
//servo is connected to port 0
intger position
// current position of the servo is returned to integer position
position = s:GetPosition</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="MoveToPosition:integer">public system action MoveToPosition(integer position)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="MoveToPosition:integer"><p>This action moves the servo to the specified position. The value of pos must be in the range 0 to 2047 Servo has mechanical stops at 0 and 180 degrees, sometimes it is possible to “overdrive” a servo. This means the mechanical stops are preventing the servo from reaching the desired position. Overdriving causes the servo to buzz or hum and can cause permanent damage to the servo.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="position"><strong>integer</strong> <em>position</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="position">to which we intend to move the servo to</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="MoveToPosition:integer"><em>Example Code:</em></span>
<pre class="code">Servo servo
servo:SetPort(0)
//servo connected to port 0 is moved to position 1310
servo:MoveToPosition(1310)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Off">public system action Off()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Off"><p>This action switches off the servo. Once the servo is swtiched</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Off"><em>Example Code:</em></span>
<pre class="code">Servo servo
servo:SetPort(1)
servo:MoveToPosition(210)
//servo is swtiched off
servo:Off()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPort:integer">public system action SetPort(integer port)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetPort:integer"><p>This action connects the servo to the specified port.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="port"><strong>integer</strong> <em>port</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="port">to which we intend to connect the servo to</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetPort:integer"><em>Example Code:</em></span>
<pre class="code">Servo servo
//servo is connected to port 0
servo:SetPort(0)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>