<?php $classPageTitle = "Controller"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Robots.Controller</h1>
<input type="hidden" id="classkey" value="Libraries.Robots.Controller" /><h2> <span class="controllable" data-componentType="class-name">Class Controller</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Controller class represents the physical Botball Controller. Several functions have been created to assist programmers in creating programs that meet the basic Botball requirements of having robots wait until the starting lights come on and have their programs shut down after a specified amount of time. It is with the help of this controller, motors, sensors and the robot connected to the controller are controlled.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Robots.Controller
// Declaraing the Controller
Controller cruise
// Controller waits for 5 seconds without performing any operation
cruise:Wait(5)</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Beep">public system action Beep()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Beep"><p>This action makes the controller to beep.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Beep"><em>Example Code:</em></span>
<pre class="code">Controller cruise
cruise:Beep()
//the controller produces a beep sound</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="ShutDownIn:number">public system action ShutDownIn(number seconds)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="ShutDownIn:number"><p>This action will kill all the processes and switches off all the motors, after the specified amount of time has elapsed. The Controller is shut down after the speicified amount of time has elapsed.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="seconds"><strong>number</strong> <em>seconds</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="seconds"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="ShutDownIn:number"><em>Example Code:</em></span>
<pre class="code">Controller cruise
cruise:ShutDownIn(3)
//the controller shuts down after 3 seconds.</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Wait:number">public system action Wait(number seconds)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Wait:number"><p>This action makes the controller wait/sleep for the specified amount of time. The controller does not perform any operation during this time.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="seconds"><strong>number</strong> <em>seconds</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="seconds"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Wait:number"><em>Example Code:</em></span>
<pre class="code">Controller cruise
cruise:Wait(2)
//the controller waits/sleeps for 2 seconds.</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="WaitUntilLightDetectedInPort:integer">public system action WaitUntilLightDetectedInPort(integer port)</span></h3>

	<p>This action will make the controller wait until the sensor connected to the specified port has detected light. In order for the sensor to detect light, you need to first calibrate the light. The following message is displayed on the controller:</p><p>"Press B Button to Calibrate Light OFF Press Left Button to Calibrate Light ON Press A Button to Wait..."</p><p>Once you are ready to calibrate the Light ON, press left arrow button on the controller. The following message is displayed on the controller:</p><p>"Set ON value to xx Press B Button to Calibrate Light OFF Press Left Button to Calibrate Light ON Press A Button to Wait..."</p><p>Once you are ready to calibrate the Light OFF, press B button on the controller. The following message is displayed on the controller:</p><p>"Set OFF value to xx Press B Button to Calibrate Light OFF Press Left Button to Calibrate Light ON Press A Button to Wait..."</p><span class="controllable" data-componentType="action-description" data-actionkey="WaitUntilLightDetectedInPort:integer"><p>Now light is Calibrated. Press A Button for the controller to wait/detect light by the sensor in the specified port.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="port"><strong>integer</strong> <em>port</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="port">is an integer, the port to which the sensor is 
        connected and waiting to see/detect light.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="WaitUntilLightDetectedInPort:integer"><em>Example Code:</em></span>
<pre class="code">Controller cruise
cruise:IsLightInPort(5)
//the controller waits until the sensor connected to the port 5 has detected
light.</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>