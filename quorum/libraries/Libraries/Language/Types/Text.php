<?php $classPageTitle = "Text"; ?><?php include('../../../static/templates/pageheader.template.php'); ?><?php include('../../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Language.Types.Text</h1>
<input type="hidden" id="classkey" value="Libraries.Language.Types.Text" /><h2> <span class="controllable" data-componentType="class-name">Class Text</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The Text class is the object representation of the primitive type text.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">class Main
   action Main
      text name = "melissa"
      Text result = test(name)
   end
   action test(Text value) returns Text
      return value
   end
end</pre>

<em>Inherits from</em>:
<a href="../../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Append:text">public action Append(text val)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Append:text"><p>This action appends the given text value to the value in this object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="val"><strong>text</strong> <em>val</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="val"></span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Append:text"><em>Example Code:</em></span>
<pre class="code">Text a
a:SetValue("hello ")
a:Append("world.")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Compare:Libraries.Language.Object">public action Compare(Libraries.Language.Object object)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Compare:Libraries.Language.Object"><p>This action compares two object values and returns a CompareResult. The compare result is either larger if this hash code is larger than the object passed as a parameter, smaller, or equal.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="object"><strong>Libraries.Language.Object</strong> <em>object</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="object">The object to compare to.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Language.Support.CompareResult</strong>: The Comprare result, Smaller, Equal, or Larger.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Compare:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Language.Support.CompareResult
Text o
Text t
CompareResult result = o:Compare(t)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="CompareIgnoringCase:Libraries.Language.Object">public action CompareIgnoringCase(Libraries.Language.Object object)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="CompareIgnoringCase:Libraries.Language.Object"><p>This action compares two object values (ignoring case) and returns a CompareResult. The compare result is either larger if this hash code is larger than the object passed as a parameter, smaller, or equal.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="object"><strong>Libraries.Language.Object</strong> <em>object</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="object">The object to compare to.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Language.Support.CompareResult</strong>: The Comprare result, Smaller, Equal, or Larger.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="CompareIgnoringCase:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">use Libraries.Language.Support.CompareResult
Text o
Text t
CompareResult result = o:CompareIgnoringCase(t)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Contains:text">public action Contains(text val)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Contains:text"><p>This action returns true if the Text value contains the text from the parameter or if the parameter is a substring in the text variable.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="val"><strong>text</strong> <em>val</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="val"></span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the text contains a substring passed to the action.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Contains:text"><em>Example Code:</em></span>
<pre class="code">Text a
a:SetValue("hello ")
boolean result = a:Contains("lo")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="EndsWith:text">public action EndsWith(text suffix)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="EndsWith:text"><p>This action returns true if the Text value ends with the given suffix.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="suffix"><strong>text</strong> <em>suffix</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="suffix">The value to check for a substring.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the text ends with the given suffix.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="EndsWith:text"><em>Example Code:</em></span>
<pre class="code">Text a
a:SetValue("hello ")
boolean result = a:EndsWith("lo")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Equals:Libraries.Language.Object">public action Equals(Libraries.Language.Object object)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Equals:Libraries.Language.Object"><p>This action determines if two objects are equal based on their values.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="object"><strong>Libraries.Language.Object</strong> <em>object</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="object">The to be compared.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the values are equal and false if they
        are not equal.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Equals:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">Text o
Text t
boolean result = o:Equals(t)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="EqualsIgnoringCase:Libraries.Language.Object">public action EqualsIgnoringCase(Libraries.Language.Object object)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="EqualsIgnoringCase:Libraries.Language.Object"><p>This action determines if two objects are equal based on their values(the case is ignored).</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="object"><strong>Libraries.Language.Object</strong> <em>object</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="object">The to be compared.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the values are equal and false if they
        are not equal.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="EqualsIgnoringCase:Libraries.Language.Object"><em>Example Code:</em></span>
<pre class="code">Text o
Text t
boolean result = o:EqualsIgnoringCase(t)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetCarriageReturn">public system action GetCarriageReturn()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetCarriageReturn"><p>This action gets the carriage return text value. This allows a carriage return to be added to a text variable without using the literal value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The carriage return text value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetCarriageReturn"><em>Example Code:</em></span>
<pre class="code">text a = "hello world"
a = a + a:GetCarriageReturn() + "My name is"
output a</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetCharacter:integer">public action GetCharacter(integer index)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetCharacter:integer"><p>This action gets the character specified by the given index and returns it.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="index"><strong>integer</strong> <em>index</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="index">The index to get. Must be greater than or equal to zero.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: the character at the specified index</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetCharacter:integer"><em>Example Code:</em></span>
<pre class="code">Text a
a:SetValue("abcd")
output a:GetCharacter(0)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetDoubleQuote">public system action GetDoubleQuote()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetDoubleQuote"><p>This action gets the double quote text value. This allows a double quote to be added to a text variable without using the literal value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The double quote text value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetDoubleQuote"><em>Example Code:</em></span>
<pre class="code">text a = "hello world"
a = a + a:GetDoubleQuote() + "My name is" + a:GetDoubleQuote()
output a</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetHashCode">public system action GetHashCode()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetHashCode"><p>This action gets the hash code for an object. In this case, GetHashCode is overriden to be equivalent to the hash code of its containing object, value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The integer hash code of the object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetHashCode"><em>Example Code:</em></span>
<pre class="code">Object o
integer hash = o:GetHashCode()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetLineFeed">public system action GetLineFeed()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetLineFeed"><p>This action gets the line feed text value. This allows a line feed to be added to a text variable without using the literal value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The line feed text value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetLineFeed"><em>Example Code:</em></span>
<pre class="code">text a = "hello world"
a = a + a:GetLineFeed() + "My name is"
output a</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSize">public system action GetSize()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSize"><p>This action returns the length of the text object.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: the length of the text object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSize"><em>Example Code:</em></span>
<pre class="code">Text a
a:SetValue("X")
output a:GetSize()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSubtext:integer">public action GetSubtext(integer startIndex)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSubtext:integer"><p>This action gets a subtext value between the given start index and the end. This includes the value at the start index.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="startIndex"><strong>integer</strong> <em>startIndex</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="startIndex">The start of the subtext.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The subtext from the text.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSubtext:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Language.Types.Text
Text a
a:SetValue("hello ")
text result = a:GetSubtext(1)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSubtext:integer:integer">public action GetSubtext(integer startIndex,integer endIndex)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSubtext:integer:integer"><p>This action returns a substring of the text object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="startIndex"><strong>integer</strong> <em>startIndex</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="startIndex">The starting index</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="endIndex"><strong>integer</strong> <em>endIndex</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="endIndex">The ending index</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The substring</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSubtext:integer:integer"><em>Example Code:</em></span>
<pre class="code">use Libraries.Language.Types.Text
Text a
a:SetValue("hello world")
output a:GetSubtext(0, 5)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetTab">public system action GetTab()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetTab"><p>This action gets the tab text value. This allows a tab to be added to a text variable without using the literal value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The tab text value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetTab"><em>Example Code:</em></span>
<pre class="code">text a = "hello world"
a = a + a:GetTab() + "My name is"
output a</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetUnicodeValue:integer">public system action GetUnicodeValue(integer twosCompliment)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetUnicodeValue:integer"><p>This action gets a unicode symbol text value. This allows any unicode symbol to be added to a text variable without using the literal value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="twosCompliment"><strong>integer</strong> <em>twosCompliment</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="twosCompliment">the twos compliments value associated with each unicode symbol.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The unicode symbols text value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetUnicodeValue:integer"><em>Example Code:</em></span>
<pre class="code">text a = "hello world"
a = a + a:GetCarriageReturn() + "My name is" + a:GetUnicodeValue(2318)
output a</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetValue">public action GetValue()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetValue"><p>This action gets the value from the text object.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The value of the object.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetValue"><em>Example Code:</em></span>
<pre class="code">Text name
text result = name:GetValue()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IndexOf:text">public action IndexOf(text subText)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IndexOf:text"><p>This action returns the index location of the first occurrence of the sub-text.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="subText"><strong>text</strong> <em>subText</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="subText">The value to check for an index.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The index location of the sub-text.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IndexOf:text"><em>Example Code:</em></span>
<pre class="code">Text a
a:SetValue("hello ")
integer result = a:IndexOf("o")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IndexOf:text:integer">public action IndexOf(text subText,integer index)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IndexOf:text:integer"><p>This action returns the index location of the first occurrence of the sub-text, starting from the given index location.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="subText"><strong>text</strong> <em>subText</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="subText">The value to check for an index.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="index"><strong>integer</strong> <em>index</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="index">The index to start searching for the sub-text</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The index location of the first occurrence of the sub-text.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IndexOf:text:integer"><em>Example Code:</em></span>
<pre class="code">Text a
a:SetValue("hello ")
integer result = a:IndexOf("l", 3)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsEmpty">public action IsEmpty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsEmpty"><p>This action returns true if the text is empty and false if it contains any value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the text is empty.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsEmpty"><em>Example Code:</em></span>
<pre class="code">Text a
a:SetValue("hello ")
boolean result = a:IsEmpty()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="ParseBoolean">public system action ParseBoolean()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="ParseBoolean"><p>This action parses a text value and translates it into a boolean if the text value is a valid boolean value. If the text value is not a boolean then the user will be alerted by an Error.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: The boolean value contained in the text value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="ParseBoolean"><em>Example Code:</em></span>
<pre class="code">text a = "true"
boolean result = a:ParseBoolean()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="ParseInteger">public system action ParseInteger()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="ParseInteger"><p>This action parses a text value and translates it into an integer if the text value is a valid integer value. If the text value is not an integer then the user will be alerted by an Error.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: The integer value contained in the text value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="ParseInteger"><em>Example Code:</em></span>
<pre class="code">text a = "12"
integer result = a:ParseInteger()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="ParseNumber">public system action ParseNumber()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="ParseNumber"><p>This action parses a text value and translates it into a number if the text value is a valid number value. If the text value is not a number then the user will be alerted by an Error.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>number</strong>: The number value contained in the text value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="ParseNumber"><em>Example Code:</em></span>
<pre class="code">text a = "12.5"
number result = a:ParseNumber()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Replace:text:text">public action Replace(text old,text new)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Replace:text:text"><p>This action replaces a specific subtext with a new value.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="old"><strong>text</strong> <em>old</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="old">The subtext to be replaced.</span></li>

<li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="new"><strong>text</strong> <em>new</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="new">The replacing subtext.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The modified text after replacing all occurrence of the old subtext.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Replace:text:text"><em>Example Code:</em></span>
<pre class="code">Text a
a:SetValue("hello ")
text result = a:Replace("l", "z")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="SetValue:text">public action SetValue(text i)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="SetValue:text"><p>This action sets the value of the text object.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="i"><strong>text</strong> <em>i</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="i">The text value.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="SetValue:text"><em>Example Code:</em></span>
<pre class="code">Text name
name:SetValue("Melissa")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Split:text">public action Split(text delimiter)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Split:text"><p>This action splits the string based on the given non-empty delimiter. The delimiter can be of any length. The string will be split by the given delimiter and an array of text objects will be returned, without the delimiter.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="delimiter"><strong>text</strong> <em>delimiter</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="delimiter">The non-empty delimiter to use.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Array</strong>: An array of text objects corresponding to the splits by the delimiter.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Split:text"><em>Example Code:</em></span>
<pre class="code">use Libraries.Language.Types.Text
use Libraries.Containers.Array
Text a
a:SetValue("hello world")
Array&lt;Text&gt; values = a:Split("l")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="StartsWith:text">public action StartsWith(text prefix)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="StartsWith:text"><p>This action returns true if the Text value starts with the given suffix.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="prefix"><strong>text</strong> <em>prefix</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="prefix">The value to check for a substring.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: True if the text starts with a given prefix.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="StartsWith:text"><em>Example Code:</em></span>
<pre class="code">Text a
a:SetValue("hello ")
boolean result = a:StartsWith("h")</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="ToLowerCase">public system action ToLowerCase()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="ToLowerCase"><p>This action converts a text value to all lower case.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The text with all lower case characters.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="ToLowerCase"><em>Example Code:</em></span>
<pre class="code">Text a
a:SetValue("HeLlo ")
text result = a:ToLowerCase()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="ToUpperCase">public system action ToUpperCase()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="ToUpperCase"><p>This action converts a text value to all upper case.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The text with all upper case characters.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="ToUpperCase"><em>Example Code:</em></span>
<pre class="code">Text a
a:SetValue("HeLlo ")
text result = a:ToUpperCase()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Trim">public action Trim()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Trim"><p>This action trims the white space from the beginning and end of the text value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>text</strong>: The text that has been trimmed of white space from the beginning and end of the text value.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Trim"><em>Example Code:</em></span>
<pre class="code">Text a
a:SetValue(" hello  ")
text result = a:Trim()</pre>

</div><?php include('../../../static/templates/classfooter.template.php'); ?><?php include('../../../static/templates/pagefooter.template.php'); ?>