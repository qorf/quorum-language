<?php $classPageTitle = "Area"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Web.Area</h1>
<input type="hidden" id="classkey" value="Libraries.Web.Area" /><h2> <span class="controllable" data-componentType="class-name">Class Area</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Area class represents HTML's (Hypertext Markup Language) area tag which is used to make clickable areas on an ImageMap class. You can find more information about this tag at: <a href="http://www.w3schools.com/tags/tag_area.asp">The area attribute</a>.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Web.All
class Main
   action main
      WebPage page
      
      Image image
      image:SetAddress("http://images.free-extras.com/pics/s/smile-1613.jpg")
      image:SetWidth("150")
      image:SetHeight("150")
      image:SetAlternate("Smile")
      image:SetUseMap("#smilemap")
      ImageMap imageMap
      imageMap:SetName("smilemap")
      Area area
      area:SetShape("rect")
      area:SetCoordinates("0,0,100,100")
      area:SetAddress("http://www.darpa.mil/uploadedImages/Content/NewsEvents/Releases/2012/Upward_Falling_Image.jpg")
      imageMap:Add(area)
      page:AddToBody(image)
      page:AddToBody(imageMap)
      output page:Generate()
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>, <a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a>, <a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a>, <a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a>
 <h2 class="action_or_variables_header"> Variables</h2>
<ul class="variables"><li class = "package_standard" ><strong>text</strong> <em>relationshipAuthor</em></li>
<li class = "package_alternate" ><strong>text</strong> <em>relationshipBookmark</em></li>
<li class = "package_standard" ><strong>text</strong> <em>relationshipHelp</em></li>
<li class = "package_alternate" ><strong>text</strong> <em>relationshipLicense</em></li>
<li class = "package_standard" ><strong>text</strong> <em>relationshipNext</em></li>
<li class = "package_alternate" ><strong>text</strong> <em>relationshipNoFollow</em></li>
<li class = "package_standard" ><strong>text</strong> <em>relationshipNoReferrer</em></li>
<li class = "package_alternate" ><strong>text</strong> <em>relationshipPrefetch</em></li>
<li class = "package_standard" ><strong>text</strong> <em>relationshipPrev</em></li>
<li class = "package_alternate" ><strong>text</strong> <em>relationshipRelationship</em></li>
<li class = "package_standard" ><strong>text</strong> <em>relationshipSearch</em></li>
<li class = "package_alternate" ><strong>text</strong> <em>relationshipTag</em></li>
<li class = "package_standard" ><strong>text</strong> <em>targetNewTab</em></li>
<li class = "package_alternate" ><strong>text</strong> <em>targetParentFrame</em></li>
<li class = "package_standard" ><strong>text</strong> <em>targetSameFrame</em></li>
<li class = "package_alternate" ><strong>text</strong> <em>targetSameWindow</em></li>
</ul>
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

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAddress">public action GetAddress()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAddress"><p>This action gets the address attribute of the area. The address is the location of the linked webpage or document.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The address of the link.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAddress"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Area
use Libraries.Web.Attribute
Area area
Attribute address = area:GetAddress()
if address not= undefined
   text href = address:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAlternativeText">public action GetAlternativeText()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAlternativeText"><p>This action gets the alterinative text attribute of the area.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The alternitive text for the area.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAlternativeText"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Area
use Libraries.Web.Attribute
Area area
Attribute altText = area:GetAlternativeText()
if altText not= undefined
   text alt = altText:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetCoordinates">public action GetCoordinates()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetCoordinates"><p>This action gets the coordinates attribute of the area. The coordinates is used together with the shape attribute to specify the size, shape, and placement of an area.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The coordinates of specified area</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetCoordinates"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Area
use Libraries.Web.Attribute
Area area
Attribute coor = area:GetCoordinates()
if coor not= undefined
   text coordinates = coor:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetLanguageCode">public action GetLanguageCode()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetLanguageCode"><p>This action gets the language code attribute of the area. The language code is the two letter code corresponding to a language. See the Libraries.Web.LanguageCode class.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The two letter language code.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetLanguageCode"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Area
use Libraries.Web.Attribute
Area area
Attribute code = area:GetLanguageCode()
if code not= undefined
   text languageCode = code:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMedia">public action GetMedia()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMedia"><p>This action gets the media attribute of the area. The media tag specifies the type of media the area will be displayed on (e.g. handheld).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The type of media the page will be displayed on (braille, handheld, aural, print, screen, etc.)</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMedia"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Area
use Libraries.Web.Attribute
Area area
Attribute media = area:GetMedia()
if media not= undefined
   text mediaType = media:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetRelationship">public action GetRelationship()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetRelationship"><p>This action gets the relationship attribute of the area. The relationship tag specifies the type of relationship between the area and the current page.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The type of relationship between the current page and area.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetRelationship"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Area
use Libraries.Web.Attribute
Area area
Attribute relationship = area:GetRelationship()
if relationship not= undefined
   text rel = relationship:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetShape">public action GetShape()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetShape"><p>This action gets the shape attribute of the area. The shape tag specifies the type of shape the area should be.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The type of shape the area should be.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetShape"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Area
use Libraries.Web.Attribute
Area area
Attribute shapeType = area:GetShape()
if shapeType not= undefined
   text shape = shapeType:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetTarget">public action GetTarget()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetTarget"><p>This action gets the target attribute of the area. The target tag specifies where the area is to be opened.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The place the area will be opened.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetTarget"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Area
use Libraries.Web.Attribute
Area area
Attribute target = area:GetTarget()
if target not= undefined
   text tar = target:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetType">public action GetType()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetType"><p>This action gets the MIME type attribute of the area. The type attribute maps to a MIME type of the area.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Web.Attribute</strong>: The MIME type attribute of the area.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetType"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Area
use Libraries.Web.Attribute
Area area
Attribute type = area:GetType()
if type not= undefined
   text mimeType = type:GetValue()
end</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAddress:text">public action SetAddress(text address)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAddress:text"><p>This action sets the address attribute of the area. The address is the location of the linked webpage or document.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="address"><strong>text</strong> <em>address</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="address"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAddress:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Area
Area area
area:SetAddress("http://www.google.com")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAlternativeText:text">public action SetAlternativeText(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAlternativeText:text"><p>This action sets the alterinative text attribute of the area.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The alterinative text attribute for the area.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAlternativeText:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Area
Area area
area:SetAlternativeText("next")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetCoordinates:text">public action SetCoordinates(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetCoordinates:text"><p>This action sets the coordinates attribute of the area. The coordinates is used together with the shape attribute to specify the size, shape, and placement of an area.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetCoordinates:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Area
use Libraries.Web.Attribute
Area area
area:SetCoordinates("0,0,16,24")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetLanguageCode:text">public action SetLanguageCode(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetLanguageCode:text"><p>This action sets the language code attribute of the area. The language code is the two letter code corresponding to a language. See the Libraries.Web.LanguageCode class.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The two letter language code.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetLanguageCode:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Area
use Libraries.Web.LanguageCode
Area area
LanguageCode language
area:SetLanguageCode(language:english)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetMedia:text">public action SetMedia(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetMedia:text"><p>This action sets the media attribute of the area. The media tag specifies the type of media the area will be displayed on (e.g. handheld).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The type of media the page will be displayed on (braille, handheld, aural, print, screen, etc.)</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetMedia:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Area
Area area
area:SetMedia("handheld")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetRelationship:text">public action SetRelationship(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetRelationship:text"><p>This action sets the relationship attribute of the area. The relationship tag specifies the type of relationship between the area and the current page. These relationships include: alternate, author, help, bookmark, help, license, next, nofollow, noreferrer, prefetch, prev, search, tag</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The type of relationship between the current page and the area.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetRelationship:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Area
Area area
area:SetRelationship(area:relationshipNext)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetShape:text">public action SetShape(text shapeType)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetShape:text"><p>This action sets the shape attribute of the area. The shape tag specifies the type shape the area should be. These types include: default,rect,circle,poly</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="shapeType"><strong>text</strong> <em>shapeType</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="shapeType"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetShape:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Area
Area area
area:SetShape("circle")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetTarget:text">public action SetTarget(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetTarget:text"><p>This action sets the target attribute of the area. The target tag specifies where the area is to be opened. These include: _blank, _self, _parent, _top, framename</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The target of the area.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetTarget:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Area
Area area
area:SetTarget("_top")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetType:text">public action SetType(text value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetType:text"><p>This action sets the Type attribute of the area. The type attribute maps to a MIME type of the area</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>text</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The MIME type for the area.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetType:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Web.Area
Area area
area:SetType("text/html")</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/AttributeAccepter.php">Libraries.Web.AttributeAccepter</a> public action Add(Libraries.Web.Attribute attribute), public action AddAttribute(text name,text value), public action GenerateAttributes(), public action GetAttribute(text name), public action GetAttributeValue(text name), public action GetAttributes(), public action GetIterator(), public action GetNumberOfAttributes(), public action HasAttribute(text name), public action RemoveAttribute(text name)</li>
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Web/GlobalAttributeAccepter.php">Libraries.Web.GlobalAttributeAccepter</a> public action GetAccessKey(), public action GetClassAttribute(), public action GetContentEditable(), public action GetContextMenu(), public action GetDraggable(), public action GetDropZone(), public action GetHidden(), public action GetIdentifier(), public action GetLanguage(), public action GetSpellcheck(), public action GetStyle(), public action GetTabIndex(), public action GetTextDirection(), public action GetTitle(), public action SetAccessKey(text value), public action SetClassAttribute(text value), public action SetContentEditable(text value), public action SetContextMenu(text value), public action SetDraggable(boolean value), public action SetDropZone(text value), public action SetHidden(boolean value), public action SetIdentifier(text value), public action SetLanguage(text value), public action SetSpellcheck(boolean value), public action SetStyle(text value), public action SetTabIndex(text value), public action SetTextDirection(text value), public action SetTitle(text value)</li>
<li class="package_alternate"><strong>From: </strong><a href="../../Libraries/Web/WebTag.php">Libraries.Web.WebTag</a> public action Add(Libraries.Web.WebTag tag), public action GenerateNestedTags(), public action GetDescription(), public action GetNumberOfNestedTags(), public action Remove(Libraries.Web.WebTag tag), public action SetDescription(text value)</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>