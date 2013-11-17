<?php $classPageTitle = "Body"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Body</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Body" /><h2> <span class="controllable" data-componentType="class-name">Class Body</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Body class represents HTML's (Hypertext Markup Language) body tag which contains the majority of the elements that a user sees on screen. Since the Body class is a WebTag, other elements can be added. By default, WebPages automatically have a body, so one rarely needs to create this object directly. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_body.asp">The body attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.all
class Main
   action main
      WebPage page
      Body body
      Link link
      link:SetAddress("www.google.com")
      link:SetDescription("Google")
      body:Add(link)
      page:SetBody(body)
      output page:Generate()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>, <a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a>, <a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a>, <a href="../../Libraries/Web/WindowAttributeAccepter.php">Libraries.Web.WindowAttributeAccepter</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Generate">public action Generate()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Generate"><p>This action generates text for a particular web page. Subclasses with a Generate method should be sure to honor nested tags or attributes if it is appropriate for a particular tag.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: This returns text in hypertext markup language (HTML)
            representing the tag.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Generate"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.WebPage
WebPage page
text result = page:Generate()
//output out the web page
output result</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/WindowAttributeAccepter.php">Libraries.Web.WindowAttributeAccepter</a> public action GetOnAHasChange(), public action GetOnAfterPrint(), public action GetOnBeforePrint(), public action GetOnError(), public action GetOnLoad(), public action GetOnMessage(), public action GetOnOffline(), public action GetOnOnline(), public action GetOnPageHide(), public action GetOnPageShow(), public action GetOnPopState(), public action GetOnRedo(), public action GetOnResize(), public action GetOnStorage(), public action GetOnUndo(), public action GetOnUnload(), public action SetOnAfterPrint(text value), public action SetOnBeforePrint(text value), public action SetOnBeforeUnload(text value), public action SetOnError(text value), public action SetOnHasChange(text value), public action SetOnLoad(text value), public action SetOnMessage(text value), public action SetOnOffline(text value), public action SetOnOnline(text value), public action SetOnPageHide(text value), public action SetOnPageShow(text value), public action SetOnPopState(text value), public action SetOnRedo(text value), public action SetOnResize(text value), public action SetOnStorage(text value), public action SetOnUndo(text value), public action SetOnUnload(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>