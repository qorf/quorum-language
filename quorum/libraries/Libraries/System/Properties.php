<?php $classPageTitle = "Properties"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.System.Properties</h1>
<input type="hidden" id="classkey" value="Libraries.System.Properties" /><h2> <span class="controllable" data-componentType="class-name">Class Properties</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The properties class is designed to gather information about the system that the user is running. Currently, it supports a general property action, GetProperty, which can gather any known system property. It also currently contains helper methods for determining which operating system the user is on and for determining which web page was requested if this is a web project. System properties are equivalent to what are often termed "environment variables."</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.System.Properties
Properties properties
text address = properties:GetRequestedWebPage()
output address</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2 class="action_or_variables_header"> Variables</h2>
<ul class="variables"><li class = "package_standard" ><strong>text</strong> <em>operatingSystem</em>: This is a public constant for the operating system property name.</li>
<li class = "package_alternate" ><strong>text</strong> <em>requestedWebPage</em>: This is a public constant for the web address environment variable, if 
    one is set.</li>
</ul>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetEnvironmentVariable:text">public action GetEnvironmentVariable(text key)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetEnvironmentVariable:text"><p>This action returns an environment variable from the system.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="key"><strong>text</strong> <em>key</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="key">the property being requested</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetEnvironmentVariable:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.Properties
Properties properties
text value = properties:GetEnvironmentVariable("quorum.url")
output value</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetOperatingSystemName">public action GetOperatingSystemName()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetOperatingSystemName"><p>This property returns the name of the operating system currently running the program.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetOperatingSystemName"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.Properties
Properties properties
text value = properties:GetOperatingSystemName()
output value</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetProperty:text">public action GetProperty(text key)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetProperty:text"><p>This action returns a property from the system.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="key"><strong>text</strong> <em>key</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="key">the property being requested</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetProperty:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.Properties
Properties properties
text value = properties:GetProperty("os.name")
output value</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetRequestedWebPage">public action GetRequestedWebPage()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetRequestedWebPage"><p>This helper method returns the requested web address if the project was a web project.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetRequestedWebPage"><em>Example Code:</em></span>
<pre class="code">use Libraries.System.Properties
Properties properties
text address = properties:GetRequestedWebPage()
output address</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>