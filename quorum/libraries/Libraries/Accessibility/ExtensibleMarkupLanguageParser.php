<?php $classPageTitle = "ExtensibleMarkupLanguageParser"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Accessibility.ExtensibleMarkupLanguageParser</h1>
<input type="hidden" id="classkey" value="Libraries.Accessibility.ExtensibleMarkupLanguageParser" /><h2> <span class="controllable" data-componentType="class-name">Class ExtensibleMarkupLanguageParser</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The ExtensibleMarkupLanguageParser class is used to parse XML text that that it is given into AccessibilityEvent object. The XML is formatted to follow the XML guidlines in the Legam Screen Reader XML documentation.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">ExtensibleMarkupLanguageParser parser
AccessibilityEvent e = parser:ParseEvent(event)
text type = e:GetCategory()</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAction">public system action GetAction()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAction"><p>This action returns the text value to represent what happened (or the action) of the event.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text to represent the action of the event.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAction"><em>Example Code:</em></span>
<pre class="code">ExtensibleMarkupLanguageParser parser
text toParse = "&lt;?xml version=1.0 encoding=UTF-8?&gt;"
parser:Parse(toParse)
text action = parser:GetAction()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetButton">public system action GetButton()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetButton"><p>This action returns the text value to represent the button clicked/released on the mouse for a mouse event.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text to represent the button clicked or released.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetButton"><em>Example Code:</em></span>
<pre class="code">ExtensibleMarkupLanguageParser parser
text toParse = "&lt;?xml version=1.0 encoding=UTF-8?&gt;"
parser:Parse(toParse)
text button = parser:GetButton()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetCategory">public system action GetCategory()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetCategory"><p>This action returns the text value of the category tag from the xml it parsed.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text to represent the category of the event.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetCategory"><em>Example Code:</em></span>
<pre class="code">ExtensibleMarkupLanguageParser parser
text toParse = "&lt;?xml version=1.0 encoding=UTF-8?&gt;"
parser:Parse(toParse)
text category = parser:GetCategory()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetChildComponent:integer">public system action GetChildComponent(integer childNum)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetChildComponent:integer"><p>This action returns the text value of the component type of the child at the parameter's index for the system component that triggered the event.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="childNum"><strong>integer</strong> <em>childNum</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="childNum"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text for the component type of the child at the index 
        for the system component that triggered the event.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetChildComponent:integer"><em>Example Code:</em></span>
<pre class="code">ExtensibleMarkupLanguageParser parser
text toParse = "&lt;?xml version=1.0 encoding=UTF-8?&gt;"
parser:Parse(toParse)
text childComponent = parser:GetChildComponent(0)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetChildCount">public system action GetChildCount()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetChildCount"><p>This action returns the integer value of the number of children for the system component that triggered the event.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: integer for the number of children for the system 
        component that triggered the event.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetChildCount"><em>Example Code:</em></span>
<pre class="code">ExtensibleMarkupLanguageParser parser
text toParse = "&lt;?xml version=1.0 encoding=UTF-8?&gt;"
parser:Parse(toParse)
integer childName = parser:GetChildCount(0)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetChildName:integer">public system action GetChildName(integer childNum)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetChildName:integer"><p>This action returns the text value of the name of the child at the parameter's index for the system component that triggered the event.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="childNum"><strong>integer</strong> <em>childNum</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="childNum"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text for the name of the child at the index for the system 
        component that triggered the event.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetChildName:integer"><em>Example Code:</em></span>
<pre class="code">ExtensibleMarkupLanguageParser parser
text toParse = "&lt;?xml version=1.0 encoding=UTF-8?&gt;"
parser:Parse(toParse)
text childName = parser:GetChildName(0)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetChildShortcut:integer">public system action GetChildShortcut(integer childNum)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetChildShortcut:integer"><p>This action returns the text value of the keyboard shortcut of the child at the parameter's index for the system component that triggered the event.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="childNum"><strong>integer</strong> <em>childNum</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="childNum"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text for the keyboard shortcut of the child at the index 
        for the system component that triggered the event.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetChildShortcut:integer"><em>Example Code:</em></span>
<pre class="code">ExtensibleMarkupLanguageParser parser
text toParse = "&lt;?xml version=1.0 encoding=UTF-8?&gt;"
parser:Parse(toParse)
text childShortcut = parser:GetChildShortcut(0)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetComponent">public system action GetComponent()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetComponent"><p>This action returns the text value to represent what type of object created the action the event</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text to represent the type of component.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetComponent"><em>Example Code:</em></span>
<pre class="code">ExtensibleMarkupLanguageParser parser
text toParse = "&lt;?xml version=1.0 encoding=UTF-8?&gt;"
parser:Parse(toParse)
text component = parser:GetComponent()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetComponentName">public system action GetComponentName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetComponentName"><p>This action returns the text value for the name of the object that created the action the event.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text to represent the name of the component.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetComponentName"><em>Example Code:</em></span>
<pre class="code">ExtensibleMarkupLanguageParser parser
text toParse = "&lt;?xml version=1.0 encoding=UTF-8?&gt;"
parser:Parse(toParse)
text componentName = parser:GetComponentName()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetKey">public system action GetKey()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetKey"><p>This action returns the text value to represent the key pressed/released on the keyboard for a keyboard event.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text to represent the key pressed or released.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetKey"><em>Example Code:</em></span>
<pre class="code">ExtensibleMarkupLanguageParser parser
text toParse = "&lt;?xml version=1.0 encoding=UTF-8?&gt;"
parser:Parse(toParse)
text key = parser:GetKey()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPosition">public system action GetPosition()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPosition"><p>This action returns the text value to represent the position on the screen a mouse event happened.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text to represent the mouse position on the screen.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPosition"><em>Example Code:</em></span>
<pre class="code">ExtensibleMarkupLanguageParser parser
text toParse = "&lt;?xml version=1.0 encoding=UTF-8?&gt;"
parser:Parse(toParse)
text position = parser:GetPosition()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetShortcut">public system action GetShortcut()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetShortcut"><p>This action returns the text value to represent the keyboard shortcut for the system component that triggered the event.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text to represent the keyboard shortcut for the system 
        component that triggered the event.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetShortcut"><em>Example Code:</em></span>
<pre class="code">ExtensibleMarkupLanguageParser parser
text toParse = "&lt;?xml version=1.0 encoding=UTF-8?&gt;"
parser:Parse(toParse)
text shortcut = parser:GetShortcut()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Parse:text">public system action Parse(text toParse)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Parse:text"><p>This action returns text to represent the category of the parsed XML text it was given.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="toParse"><strong>text</strong> <em>toParse</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="toParse"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: text to represent the category from the XML.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Parse:text"><em>Example Code:</em></span>
<pre class="code">ExtensibleMarkupLanguageParser parser
text parsedXML = parser:Parse(toParse)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="ParseEvent:text">public action ParseEvent(text toParse)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="ParseEvent:text"><p>This action returns an AccessibilityEvent after parsing the XML text it was passed.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="toParse"><strong>text</strong> <em>toParse</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="toParse"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Accessibility.AccessibilityEvent</strong>: an AccessibilityEvent to represent the XML text it 
        was passed.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="ParseEvent:text"><em>Example Code:</em></span>
<pre class="code">ExtensibleMarkupLanguageParser parser
AccessibilityEvent e = parser:ParseEvent(event)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>