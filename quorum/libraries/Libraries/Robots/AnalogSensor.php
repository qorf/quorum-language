<?php $classPageTitle = "AnalogSensor"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Robots.AnalogSensor</h1>
<input type="hidden" id="classkey" value="Libraries.Robots.AnalogSensor" /><h2> <span class="controllable" data-componentType="class-name">Class AnalogSensor</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Analog Sensor class represents a physical analog sensor. Each senor has a designation, an integer, that says which port it is connected to(e.g., 0 to 7). The analog sensor can be used only after it is connected to a port. Once the sensor is connected to a port, we can read the analog value from the sensor and set the sensor to floating value.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Robots.AnalogSensor
// Declaraing an analog sensor
AnalogSensor analog
// Set connection with the analog sensor connected to port 4
analog:SetPort(4)
integer analog = analog:GetValue()
if analog &gt; 512
   output "Sensor detects a light color"
   else
   output "Sensor detects a dark color"
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Robots/Connectable.php">Libraries.Robots.Connectable</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Get10BitValue">public system action Get10BitValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Get10BitValue"><p>This action is similar to the previous action, except it is more precise. It returns the 10 bit analog value of the analog sensor, which is an integer between 0 to 1023.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: an integer between 0 to 1023.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Get10BitValue"><em>Example Code:</em></span>
<pre class="code">AnalogSensor analog
analog:SetPort(4)
integer analogValue = analog:GetValue()
//returns the current 10 bit analog value of the sensor connected to port 4</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDistance">public system action GetDistance()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDistance"><p>This action returns the approximate distance of the object using SONAR. SONAR stands for SOund Navigation And Ranging. The SONAR sensor works by sending out a burst of ultrasonic sound (a ping) and measuring the amount of time it takes to hear the echo. Then the sensor divides that time by the speed of sound and returns a distance.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the object distance in millimeter.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDistance"><em>Example Code:</em></span>
<pre class="code">AnalogSensor analog
analog:SetPort()
//SONAR distance of the object is returned
integer distance = analog:GetDistance()
output "Sonar Distance is " + distance</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPort">public system action GetPort()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPort"><p>This action returns the port to which the sensor is connected to.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the port to which the sensor is connected to</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPort"><em>Example Code:</em></span>
<pre class="code">AnalogSensor analog
analog:SetPort(6)
integer port = analog:GetPort()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetValue">public system action GetValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetValue"><p>This action returns the analog value of the analog sensor, which is an integer between 0 to 255.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: an integer between 0 to 255.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetValue"><em>Example Code:</em></span>
<pre class="code">AnalogSensor analog
analog:SetPort(3)
integer analogValue = analog:GetValue()
//returns the current value of the analog sensor connected to port 3</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Reset">public system action Reset()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Reset"><p>This action sets the analog sensor to a floating point, i.e. it resets the analog sensor. Please note that all sensor ports are set to non-floating when the controller is rebooted or when a program exits. So in order to set the sensor to floating we need to use this action.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Reset"><em>Example Code:</em></span>
<pre class="code">AnalogSensor analog
analog:SetPort(0)
analog:Reset()
//Analog sensor connected to port 0 is set to floating</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPort:integer">public system action SetPort(integer port)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetPort:integer"><p>This action connects the sensor to the specified analog port. After the sensor is connected to the port, we can read the values from the sensor.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="port"><strong>integer</strong> <em>port</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="port">to which we intend to connect the analog sensor 
        to. Port values vary from 0 to 7.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetPort:integer"><em>Example Code:</em></span>
<pre class="code">AnalogSensor analog
analog:SetPort(6)
//analog sensor is connected to port 6</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>