<?php $classPageTitle = "Button"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Robots.Button</h1>
<input type="hidden" id="classkey" value="Libraries.Robots.Button" /><h2> <span class="controllable" data-componentType="class-name">Class Button</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Button class represents the different touch screen buttons and the Black button present on the controller. There are seven different buttons present. The A button, B button, Black button, LeftArrow button, RightArrow button, UpArrow button, and DownArrow button.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Robots.Button
// Declaraing the Button
Button button
if button:IsAButtonPressed() then
   output "Button A is Pressed"
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsAButtonPressed">public system action IsAButtonPressed()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsAButtonPressed"><p>This action tells if A button on the controller is pressed or not.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if A button is pressed otherwise returns
        false</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsAButtonPressed"><em>Example Code:</em></span>
<pre class="code">Button button
if button:IsAButtonPressed() not= true then
   output "A Button is not Pressed"
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsBButtonPressed">public system action IsBButtonPressed()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsBButtonPressed"><p>This action tells if B button on the controller is pressed or not.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if B button is pressed otherwise returns
        false</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsBButtonPressed"><em>Example Code:</em></span>
<pre class="code">Button button
if button:IsBButtonPressed() not= true then
   output "B Button is not Pressed"
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsBlackButtonPressed">public system action IsBlackButtonPressed()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsBlackButtonPressed"><p>This action tells if Black button on the controller is pressed or not.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if Black button is pressed otherwise returns
        false</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsBlackButtonPressed"><em>Example Code:</em></span>
<pre class="code">Button button
if button:IsBlackButtonPressed() not= true then
   output "Black Button is not Pressed"
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsDownArrowPressed">public system action IsDownArrowPressed()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsDownArrowPressed"><p>This action tells if DownArrow button on the controller is pressed or not.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if DownArrow button is pressed otherwise returns
        false</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsDownArrowPressed"><em>Example Code:</em></span>
<pre class="code">Button button
if button:IsDownArrowPressed() not= true then
   output "Down Arrow is not Pressed"
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsLeftArrowPressed">public system action IsLeftArrowPressed()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsLeftArrowPressed"><p>This action tells if LeftArrow button on the controller is pressed or not.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if LeftArrow button is pressed otherwise returns
        false</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsLeftArrowPressed"><em>Example Code:</em></span>
<pre class="code">Button button
if button:IsLeftArrowPressed() not= true then
   output "Left Arrow is not Pressed"
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsRightArrowPressed">public system action IsRightArrowPressed()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsRightArrowPressed"><p>This action tells if RightArrow button on the controller is pressed or not.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if RightArrow button is pressed otherwise returns
        false</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsRightArrowPressed"><em>Example Code:</em></span>
<pre class="code">Button button
if button:IsRightArrowPressed() not= true then
   output "Right Arrow is not Pressed"
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsUpArrowPressed">public system action IsUpArrowPressed()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsUpArrowPressed"><p>This action tells if UpArrow button on the controller is pressed or not.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if UpArrow button is pressed otherwise returns
        false</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsUpArrowPressed"><em>Example Code:</em></span>
<pre class="code">Button button
if button:IsUpArrowPressed() not= true then
   output "Up Arrow is not Pressed"
end</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>