<?php $classPageTitle = "Table"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Containers.Table</h1>
<input type="hidden" id="classkey" value="Libraries.Containers.Table" /><h2> <span class="controllable" data-componentType="class-name">Class Table&lt;Type&gt;</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Table class is a data structure that stores items in contiguous memory. An item is typically stored and accessed through an index or location(row and column). This location always starts at 0, this means the first item in the table is at location 0, 0. The default maximum size is set to 10, but can be changed by using the SetSize(row, column) method or the array will automatically make itself large when the space is needed (note: it is possible to turn the resizing off with the SetAutoResize(false) method).</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">use Libraries.Containers.Table
class Main
   action Main
      //make the table
      Table&lt;integer&gt; myTable
      //add a value
      myTable:AddToRow(0, 12)
      //get it back
      integer value = myTable:Get(0,0)
   end
end</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Containers/Blueprints/Copyable.php">Libraries.Containers.Blueprints.Copyable</a>, <a href="../../Libraries/Containers/Blueprints/TableBlueprint.php">Libraries.Containers.Blueprints.TableBlueprint</a>, <a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:integer:integer:Libraries.Language.Object">public action Add(integer row,integer column,Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:integer:integer:Libraries.Language.Object"><p>This action adds a value at a location in the table.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="row"><strong>integer</strong> <em>row</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="row">The row to store the value at.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="column"><strong>integer</strong> <em>column</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="column">The column to store the value at.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to be added to the indexed object.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:integer:integer:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
myTable:Add(0, 0, 22)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AddRow:Libraries.Containers.Array">public action AddRow(Libraries.Containers.Array row)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AddRow:Libraries.Containers.Array"><p>This action adds a row to the table.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="row"><strong>Libraries.Containers.Array</strong> <em>row</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="row">A row (array) of values to add to the table.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="AddRow:Libraries.Containers.Array"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
use Libraries.Containers.Array
Table&lt;integer&gt; myTable
Array&lt;integer&gt; row
myTable:AddRow(row)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AddToEndOfRow:integer:Libraries.Language.Object">public action AddToEndOfRow(integer row,Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AddToEndOfRow:integer:Libraries.Language.Object"><p>This action adds an item to the end of a row in the table. If the max size has been reached an the table is not re-sizable an InvalidLocationError will be alerted.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="row"><strong>integer</strong> <em>row</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="row">The row to add the value to the end of.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to be added to the table.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="AddToEndOfRow:integer:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
use Libraries.Containers.Array
Table&lt;integer&gt; myTable
myTable:AddToEndOfRow(0,12)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="AddToFrontOfRow:integer:Libraries.Language.Object">public action AddToFrontOfRow(integer row,Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="AddToFrontOfRow:integer:Libraries.Language.Object"><p>This action adds an item to the front of the table's row at index 0. Then moves all other items down one index. If the max size is already reached and the array is not re-sizable an InvalidLocationError will be alerted.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="row"><strong>integer</strong> <em>row</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="row">The row to add the value to the end of.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to be added to the array.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="AddToFrontOfRow:integer:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
myTable:AddToFrontOfRow(0, 12)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Copy">public action Copy()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Copy"><p>This blueprint action copies an object and returns the copy.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Language.Object</strong>: Returns a copy of this object. This copy is not guaranteed
        to be a deep copy.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Copy"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
//the array class is Copyable
Table&lt;integer&gt; table
Object o = table:Copy()
Table&lt;integer&gt; copy = cast(Table&lt;integer&gt;, o)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Empty">public action Empty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Empty"><p>This action empty's the table, clearing out all of the items contained within it.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Empty"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
myTable:SetSize(10,10)
myTable:Set(0, 0, 22)
myTable:Set(1, 0, 33)
myTable:Set(2, 0, 45)
myTable:Set(3, 0, 22)
myTable:Empty()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Get:integer:integer">public action Get(integer row,integer column)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Get:integer:integer"><p>This action gets the item at a given location in an table.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="row"><strong>integer</strong> <em>row</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="row">The row the value is located at.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="column"><strong>integer</strong> <em>column</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="column">The column the value is located at.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the given location.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Get:integer:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
myTable:SetSize(10,10)
myTable:Set(0,0,22)
integer result = myTable:Get(0,0)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetAutoResize">public action GetAutoResize()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetAutoResize"><p>This action returns true if the table is dynamic(resizable) or false if the table does not automatically resize.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the table is resizable and false if it is not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetAutoResize"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
boolean result = myTable:GetAutoResize()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFromEndOfRow:integer">public action GetFromEndOfRow(integer row)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFromEndOfRow:integer"><p>This action gets the item at the end of the row in the table(the item will remain in the table).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="row"><strong>integer</strong> <em>row</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="row">The row in the table.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the end of the row in the table.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFromEndOfRow:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
myTable:AddToEndOfRow(0, 4)
myTable:AddToEndOfRow(0, 13)
myTable:AddToEndOfRow(0, 12)
integer value = myTable:GetFromEndOfRow(0)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFromFrontOfRow:integer">public action GetFromFrontOfRow(integer row)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFromFrontOfRow:integer"><p>This action gets the item at the front of the specified row in the table(the item will remain in the table).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="row"><strong>integer</strong> <em>row</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="row">The row in the table.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the front of the array.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFromFrontOfRow:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
myTable:AddToEndOfRow(0,4)
myTable:AddToEndOfRow(0,13)
myTable:AddToEndOfRow(0,12)
integer value = myTable:GetFromFrontOfRow(0)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMaxNumberOfColumns">public action GetMaxNumberOfColumns()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMaxNumberOfColumns"><p>This action gets the capacity of columns in the table(max size of columns).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMaxNumberOfColumns"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
integer maxSize = myTable:GetMaxNumberOfColumns()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMaxNumberOfRows">public action GetMaxNumberOfRows()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMaxNumberOfRows"><p>This action gets the capacity of rows in the table(max size of rows).</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMaxNumberOfRows"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
integer maxSize = myTable:GetMaxNumberOfRows()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetNumberOfRows">public action GetNumberOfRows()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetNumberOfRows"><p>This action gets the size of the rows in the table.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetNumberOfRows"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
integer size = myTable:GetNumberOfRows()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetRow:integer">public action GetRow(integer row)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetRow:integer"><p>This action gets a row from the table at a given location.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="row"><strong>integer</strong> <em>row</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="row">The location to find the row</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Array</strong>: The array or row at the given location</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetRow:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
use Libraries.Containers.Array
Table&lt;integer&gt; myTable
myTable:SetSize(1,4)
myTable:Set(0, 0, 22)
myTable:Set(0, 1, 33)
myTable:Set(0, 2, 45)
myTable:Set(0, 3, 22)
Array&lt;integer&gt; row = myTable:GetRow(0)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetRowIterator">public action GetRowIterator()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetRowIterator"><p>This action gets an iterator for the object and returns that iterator.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: Returns the iterator for an object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetRowIterator"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
use Libraries.Containers.Array
use Libraries.Containers.Blueprints.Iterator
Table&lt;integer&gt; myTable
myTable:SetSize(4,4)
myTable:Set(0, 0, 22)
myTable:Set(0, 1, 33)
myTable:Set(0, 2, 45)
myTable:Set(0, 3, 22)
Iterator&lt;Array&lt;integer&gt;&gt; it = myTable:GetRowIterator()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSizeOfRow:integer">public action GetSizeOfRow(integer row)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSizeOfRow:integer"><p>This action gets the size of the row in the table.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="row"><strong>integer</strong> <em>row</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="row"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: </li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSizeOfRow:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
integer size = myTable:GetSizeOfRow(0)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Has:Libraries.Language.Object">public action Has(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Has:Libraries.Language.Object"><p>This action determines if an addable object contains a certain item.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to find in the Addable object.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true if the item was in the Addable object and false if it was not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Has:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
myTable:SetSize(10, 10)
myTable:Set(0, 0, 22)
myTable:Set(0, 1, 33)
myTable:Set(0, 2, 45)
myTable:Set(0, 3, 22)
boolean result = myTable:Has(33)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsEmpty">public action IsEmpty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsEmpty"><p>This action returns a boolean value, true if the container is empty and false if it contains any items.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true when the container is empty and false when it is not.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsEmpty"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
myTable:SetSize(10, 10)
myTable:Set(0, 0, 22)
myTable:Set(0, 1, 33)
myTable:Set(0, 2, 45)
myTable:Set(0, 3, 22)
boolean result = myTable:IsEmpty()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Remove:Libraries.Language.Object">public action Remove(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Remove:Libraries.Language.Object"><p>This action removes the first occurrence of an item that is found in the Addable object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to find in the Addable object.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true if the item was removed and false if it was not removed.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Remove:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
myTable:SetSize(10,10)
myTable:Set(0, 0, 22)
myTable:Set(0, 1, 33)
myTable:Set(0, 2, 45)
myTable:Set(0, 3, 22)
boolean result = myTable:Remove(22)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveAll:Libraries.Language.Object">public action RemoveAll(Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveAll:Libraries.Language.Object"><p>This action removes all occurrences of an item from the Addable object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to find in the Addable object.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: Returns true if the item was removed and false if it was not removed.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveAll:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
myTable:SetSize(10,10)
myTable:Set(0, 0, 22)
myTable:Set(0, 1, 33)
myTable:Set(0, 2, 45)
myTable:Set(0, 3, 22)
boolean result = myTable:RemoveAll(22)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveAt:integer:integer">public action RemoveAt(integer row,integer column)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveAt:integer:integer"><p>This action removes an item from an indexed object and returns that item.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="row"><strong>integer</strong> <em>row</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="row">The row in the table.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="column"><strong>integer</strong> <em>column</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="column">The column in the table.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item that was removed from the indexed object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveAt:integer:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
use Libraries.Containers.Array
Table&lt;integer&gt; myTable
myTable:SetSize(10,10)
myTable:Set(0, 0, 22)
myTable:Set(0, 1, 33)
myTable:Set(0, 2, 45)
myTable:Set(0, 3, 22)
integer item = myTable:RemoveAt(0, 2)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveFromEndOfRow:integer">public action RemoveFromEndOfRow(integer row)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveFromEndOfRow:integer"><p>This action removes the item at the end of the table.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="row"><strong>integer</strong> <em>row</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="row"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the end of the table.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveFromEndOfRow:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
myTable:AddToEndOfRow(0, 33)
myTable:AddToEndOfRow(0, 13)
myTable:AddToEndOfRow(0, 43)
integer removed = myTable:RemoveFromEndOfRow(0)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="RemoveFromFrontOfRow:integer">public action RemoveFromFrontOfRow(integer row)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="RemoveFromFrontOfRow:integer"><p>This action removes the item at the front of the table.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="row"><strong>integer</strong> <em>row</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="row"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Type</strong>: The item at the front of the table.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="RemoveFromFrontOfRow:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
myTable:Add(0,0,33)
myTable:Add(0,1,13)
myTable:Add(0,2,43)
integer removed = myTable:RemoveFromFrontOfRow(0)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Set:integer:integer:Libraries.Language.Object">public action Set(integer row,integer column,Type value)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Set:integer:integer:Libraries.Language.Object"><p>This action sets the item at a given location in the indexed object to a new item.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="row"><strong>integer</strong> <em>row</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="row">The row the value will be stored at.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="column"><strong>integer</strong> <em>column</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="column">The column the value will be stored at.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="value"><strong>Type</strong> <em>value</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="value">The item to be added to the indexed object.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Set:integer:integer:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
myTable:SetSize(10, 10)
myTable:Set(0, 0, 22)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetAutoResize:boolean">public action SetAutoResize(boolean resizable)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetAutoResize:boolean"><p>This action changes the flag that tells the structure if it is a dynamic table or not. If it is dynamic then resizable is true and if it is not dynamic then resizable is false.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="resizable"><strong>boolean</strong> <em>resizable</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="resizable">The value to set the resizable flag to.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetAutoResize:boolean"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
myTable:SetAutoResize(false)
myTable:SetSize(10, 10)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetMaxSize:integer:integer">public action SetMaxSize(integer row,integer column)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetMaxSize:integer:integer"><p>This action sets the number of items that can be stored in the table(max rows and columns). The max size can only be increased, any value that is lower will leave the array with the same max size it had.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="row"><strong>integer</strong> <em>row</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="row">The max number of rows in the table.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="column"><strong>integer</strong> <em>column</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="column">The max number of columns in the table.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetMaxSize:integer:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
myTable:SetMaxSize(10, 10)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetSize:integer:integer">public action SetSize(integer row,integer column)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetSize:integer:integer"><p>This action sets the size of the table and fills it with undefined items. Changing the size of the table means any items already in the table must be copied over.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="row"><strong>integer</strong> <em>row</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="row">The number of rows.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="column"><strong>integer</strong> <em>column</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="column">The number of columns.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetSize:integer:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Containers.Table
Table&lt;integer&gt; myTable
myTable:SetSize(10,10)</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>