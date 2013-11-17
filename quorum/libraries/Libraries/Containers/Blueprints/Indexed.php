<?php $classPageTitle = "Indexed"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Blueprints.Indexed</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Blueprints.Indexed" /><h2> <span class="controllable" data-componentType="class-name">Class Indexed&lt;Type&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
Inheriting from the Indexed class provides a basic blueprint for indexed objects.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Blueprints.Indexed
class MyIndexedClass&lt;Type&gt; is Indexed&lt;Type&gt;
   action Add(integer location, Type value)
      //implement here
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:integer:Libraries.Language.Object">public blueprint action Add(integer location,Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:integer:Libraries.Language.Object"><p>This action adds a value at a location in the indexed object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="location"><strong>integer</strong> <em>location</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="location">The index or location the value will be stored at.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to be added to the indexed object.</span></li>

</ul>
</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Get:integer">public blueprint action Get(integer location)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Get:integer"><p>This action gets the item at a given location in an List.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="location"><strong>integer</strong> <em>location</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="location">The index or location the value is located at.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the given location.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFirstLocation:Libraries.Language.Object">public blueprint action GetFirstLocation(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFirstLocation:Libraries.Language.Object"><p>This action gets the first occurrence of the item and returns its location.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item being searched for.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The location of the first occurrence of the item.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetLastLocation:Libraries.Language.Object">public blueprint action GetLastLocation(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetLastLocation:Libraries.Language.Object"><p>This action gets the last occurrence of the item and returns its location.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item being searched for.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The location of the last occurrence of the item.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveAt:integer">public blueprint action RemoveAt(integer location)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveAt:integer"><p>This action removes an item from an indexed object and returns that item.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="location"><strong>integer</strong> <em>location</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="location">The index or location of the item to remove.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item that was removed from the indexed object.</li>
</ul></div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Set:integer:Libraries.Language.Object">public blueprint action Set(integer location,Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Set:integer:Libraries.Language.Object"><p>This action sets the item at a given location in the indexed object to a new item.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="location"><strong>integer</strong> <em>location</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="location">The index or location the value will be stored at.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to be added to the indexed object.</span></li>

</ul>
</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>