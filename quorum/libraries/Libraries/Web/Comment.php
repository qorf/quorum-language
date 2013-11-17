<?php $classPageTitle = "Comment"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Comment</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Comment" /><h2> <span class="controllable" data-componentType="class-name">Class Comment</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Comment represents HTML's (Hypertext Markup Language) !-- tag which allows a comment to be added to the webpage. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_comment.asp">The comment attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      //make a web page
      WebPage page
      
      //create a comment
      Comment c
      c:SetDescription("This is a comment")
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/WebGenerator.php">Libraries.Web.WebGenerator</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Generate">public action Generate()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Generate"><p>Generate method converts web page information into raw text that can be sent to a web server or otherwise printed out.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: A text representation of the item being generated.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Generate"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPage
//by default, this would output a blank web page
WebPage page
output page:Generate()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetCommentClose">public action GetCommentClose()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetCommentClose"><p>This action gets the comment close value. If true, the comment is closed with a //--> to prevent javascript from executing the -->. If false, the comment is closed with a -->.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if // is needed and false if not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetCommentClose"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Comment
Comment comment
boolean close = comment:GetCommentClose()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDescription">public action GetDescription()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDescription"><p>This action gets the description or the comment text.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The comment text.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDescription"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Comment
Comment comment
comment:SetDescription("This is a comment.")
output comment:GetDescription()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetCommentClose:boolean">public action SetCommentClose(boolean commentClose)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetCommentClose:boolean"><p>This action sets the comment close value. If true, the comment is closed with a //--> to prevent javascript from executing the -->. If false, the comment is closed with a -->.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="commentClose"><strong>boolean</strong> <em>commentClose</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="commentClose">True if // is needed and false if not.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetCommentClose:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Comment
Comment comment
comment:SetCommentClose(false)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetDescription:text">public action SetDescription(text description)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetDescription:text"><p>This action sets the description(text) in the comment.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="description"><strong>text</strong> <em>description</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="description">The comments text.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetDescription:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Comment
Comment comment
comment:SetDescription("This is my comment")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>