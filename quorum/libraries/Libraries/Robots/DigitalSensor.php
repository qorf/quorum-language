<?php $classPageTitle = "DigitalSensor"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Robots.DigitalSensor</h1>
<input type="hidden" id="classkey" value="Libraries.Robots.DigitalSensor" /><h2> <span class="controllable" data-componentType="class-name">Class DigitalSensor</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Digital Sensor class represents a physical digital sensor. Each senor has a designation, an integer, that says which port it is connected to(e.g., 8 to 15). Once the sensor is connected to the port, we can get the value read by the digital sensor and we can also output a value to the sensor. Digital Sensors are yypically used for bumpers or limit switches.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Robots.DigitalSensor
// Declaraing a digital sensor
DigitalSensor digital
// Set connection with the analog sensor connected to port 4
digital:SetPort(9)
integer open = digital:GetValue()
if open = 0
   output "Digital Sensor swtich is closed"
   else
   output "Digital Sensor swtich is open"
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Robots/Connectable.php">Libraries.Robots.Connectable</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPort">public system action GetPort()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPort"><p>This action returns the port to which the sensor is connected to.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the port to which the sensor is connected to</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPort"><em>Example Code:</em></span>
<pre class="code">AnalogSensor digital
digital:SetPort(6)
integer port = digital:GetPort()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetValue">public system action GetValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetValue"><p>This action returns the digital value of the sensor, which is either 0 or 1. This value represents if the sensor switch is open or closed. If the switch is open, it is represented by 1. If the switch is closed, it is represented by 0.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: an integer 0 or 1. If the digital sensor switch is 
        closed 0 is returned, otherwise 1 is returned.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetValue"><em>Example Code:</em></span>
<pre class="code">DigitalSensor digital
digital:SetPort(13)
integer digitalValue = digital:GetValue()
//returns the current value of the sensor connected to port 13</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetOutput:integer">public system action SetOutput(integer value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetOutput:integer"><p>This action sets the output value for the digital sensor. The output value can be either 0 or 1.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>integer</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">can either be 1 or 0.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetOutput:integer"><em>Example Code:</em></span>
<pre class="code">DigitalSensor digital
digital:SetPort(11)
digital:SetOutputValue(0)
//Sets the output of the digital sensor connected to port 11 as 0</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetPort:integer">public system action SetPort(integer port)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetPort:integer"><p>This action connects the sensor to the specified digital port. The sensor can read values only after it is connected to the port.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="port"><strong>integer</strong> <em>port</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="port">to which we intend to connect the sensor to
        Port values vary from 8 to 15.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetPort:integer"><em>Example Code:</em></span>
<pre class="code">DigitalSensor digital
digital:SetPort(10)
//sensor is connected to digital port 10</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>