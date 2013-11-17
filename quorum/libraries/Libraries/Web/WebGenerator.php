<?php $classPageTitle = "WebGenerator"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.WebGenerator</h1>
<input type="hidden" id="classkey" value="Libraries.Web.WebGenerator" /><h2> <span class="controllable" data-componentType="class-name">Class WebGenerator</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The WebGenerator class contains a single blueprint action designed for generating web content. Essentially, it is a helper parent class that any related class will need to implement. The examples used in this document show an example of using this blueprint in the WebPage class.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.WebPage
//by default, this would output a blank web page
WebPage page
output page:Generate()</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Generate">public blueprint action Generate()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Generate"><p>Generate method converts web page information into raw text that can be sent to a web server or otherwise printed out.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: A text representation of the item being generated.</li>
</ul></div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>