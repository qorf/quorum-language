<?php $classPageTitle = "AccessibilityManager"; ?><?php include('../../static/templates/pageheader.template.php'); ?><?php include('../../static/templates/classheader.template.php'); ?><h1 class="page_title"> Libraries.Accessibility.AccessibilityManager</h1>
<input type="hidden" id="classkey" value="Libraries.Accessibility.AccessibilityManager" /><h2> <span class="controllable" data-componentType="class-name">Class AccessibilityManager</span></h2> 
<p><span class="controllable" data-componentType="class-description"><em>Description</em>:
The AccessibilityManager class provides basic functionality for creating a screen reader for the operating system. This class can be used to listen to any subset of Observers from the system.</span></p>

<span class="controllable" data-componentType="class-example"><em>Example Code</em>:</span>
<pre class="code">AccessibilityManager manager
KeyboardObserver kObserver
MouseObserver mObserver
FocusObserver fObserver
manager:Add(kObserver)
manager:Add(mObserver)
manager:Add(fObserver)
manager:Start()
manager:Stop()
Iterator&lt;MouseObserver&gt; mouseObservers = manager:GetMouseObservers()
MouseObserver first = mouseObservers:GetCurrent()
manager:Remove(first)</pre>

<em>Inherits from</em>:
<a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a>
 <h2> Actions</h2> 
<div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Accessibility.EverythingObserver">public action Add(Libraries.Accessibility.EverythingObserver observer)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Accessibility.EverythingObserver"><p>This action adds a EverythingObserver to the AccessibilityManager.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="observer"><strong>Libraries.Accessibility.EverythingObserver</strong> <em>observer</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="observer">The observer to be added.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:Libraries.Accessibility.EverythingObserver"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
EverythingObserver observer
manager:Add(observer)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Accessibility.FocusObserver">public action Add(Libraries.Accessibility.FocusObserver observer)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Accessibility.FocusObserver"><p>This action adds a FocusObserver to the AccessibilityManager.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="observer"><strong>Libraries.Accessibility.FocusObserver</strong> <em>observer</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="observer">The observer to be added.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:Libraries.Accessibility.FocusObserver"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
FocusObserver observer
manager:Add(observer)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Accessibility.KeyboardObserver">public action Add(Libraries.Accessibility.KeyboardObserver observer)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Accessibility.KeyboardObserver"><p>This action adds a KeyboardObserver to the AccessibilityManager.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="observer"><strong>Libraries.Accessibility.KeyboardObserver</strong> <em>observer</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="observer">The observer to be added.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:Libraries.Accessibility.KeyboardObserver"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
KeyboardObserver observer
manager:Add(observer)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Accessibility.MenuObserver">public action Add(Libraries.Accessibility.MenuObserver observer)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Accessibility.MenuObserver"><p>This action adds a MenuObserver to the AccessibilityManager.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="observer"><strong>Libraries.Accessibility.MenuObserver</strong> <em>observer</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="observer">The observer to be added.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:Libraries.Accessibility.MenuObserver"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
MenuObserver observer
manager:Add(observer)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Accessibility.MouseObserver">public action Add(Libraries.Accessibility.MouseObserver observer)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Accessibility.MouseObserver"><p>This action adds a MouseObserver to the AccessibilityManager.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="observer"><strong>Libraries.Accessibility.MouseObserver</strong> <em>observer</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="observer">The observer to be added.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:Libraries.Accessibility.MouseObserver"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
MouseObserver observer
manager:Add(observer)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Accessibility.PropertyObserver">public action Add(Libraries.Accessibility.PropertyObserver observer)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Accessibility.PropertyObserver"><p>This action adds a PropertyObserver to the AccessibilityManager.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="observer"><strong>Libraries.Accessibility.PropertyObserver</strong> <em>observer</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="observer">The observer to be added.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:Libraries.Accessibility.PropertyObserver"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
PropertyObserver observer
manager:Add(observer)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Add:Libraries.Accessibility.WindowObserver">public action Add(Libraries.Accessibility.WindowObserver observer)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Add:Libraries.Accessibility.WindowObserver"><p>This action adds a WindowObserver to the AccessibilityManager.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="observer"><strong>Libraries.Accessibility.WindowObserver</strong> <em>observer</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="observer">The observer to be added.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="Add:Libraries.Accessibility.WindowObserver"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
WindowObserver observer
manager:Add(observer)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Empty">public action Empty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Empty"><p>This action removes all observers in the AccessibilityManager.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Empty"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
EverythingObserver eObserver
WindowObserver wObserver
FocusObserver fObserver
manager:Add(eObserver)
manager:Add(wObserver)
manager:Add(fObserver)
manager:Empty()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="EmptyEverythingObservers">public action EmptyEverythingObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="EmptyEverythingObservers"><p>This action removes all EverythingObservers in the AccessibilityManager.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="EmptyEverythingObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
EverythingObserver observer
manager:Add(observer)
manager:EmptyEverythingObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="EmptyFocusObservers">public action EmptyFocusObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="EmptyFocusObservers"><p>This action removes all FoucsObservers in the AccessibilityManager.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="EmptyFocusObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
FocusObserver observer
manager:Add(observer)
manager:EmptyFocusObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="EmptyKeyboardObservers">public action EmptyKeyboardObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="EmptyKeyboardObservers"><p>This action removes all KeyboardObservers in the AccessibilityManager.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="EmptyKeyboardObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
KeyboardObserver observer
manager:Add(observer)
manager:EmptyKeyboardObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="EmptyMenuObservers">public action EmptyMenuObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="EmptyMenuObservers"><p>This action removes all MenuObservers in the AccessibilityManager.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="EmptyMenuObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
MenuObserver observer
manager:Add(observer)
manager:EmptyMenuObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="EmptyMouseObservers">public action EmptyMouseObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="EmptyMouseObservers"><p>This action removes all MouseObservers in the AccessibilityManager.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="EmptyMouseObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
MouseObserver observer
manager:Add(observer)
manager:EmptyMouseObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="EmptyPropertyObservers">public action EmptyPropertyObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="EmptyPropertyObservers"><p>This action removes all PropertyObservers in the AccessibilityManager.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="EmptyPropertyObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
PropertyObserver observer
manager:Add(observer)
manager:EmptyPropertyObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="EmptyWindowObservers">public action EmptyWindowObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="EmptyWindowObservers"><p>This action removes all WindowObservers in the AccessibilityManager.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="EmptyWindowObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
WindowObserver observer
manager:Add(observer)
manager:EmptyWindowObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetEvent:text">public action GetEvent(text event)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetEvent:text"><p>This action gets the XML for an event from the system and parses it into a keyboard, mouse, or system event. It then sends that event to the appropriate observers (KeyboardObserver, MouseObserver, FocusObserver, WindowObserver, EverythingObserver) so that they will be processed/displayed.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="event"><strong>text</strong> <em>event</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="event">Text in XML format to represent an event.</span></li>

</ul>

	<span class="controllable" data-componentType="action-example" data-actionkey="GetEvent:text"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
EverythingObserver observer
manager:Add(observer)
text eventXML
boolean result = manager:GetEvent(eventXML)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetEverythingObservers">public action GetEverythingObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetEverythingObservers"><p>This action gets the list of EverythingObservers in the AccessibilityManager and returns them in an iterator.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: an Iterator of all of the EverythingObservers.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetEverythingObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
EverythingObserver observer
manager:Add(observer)
Iterator&lt;EverythingObserver&gt; everythingObservers = manager:GetEverythingObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetFocusObservers">public action GetFocusObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetFocusObservers"><p>This action gets the list of FocusObservers in the AccessibilityManager and returns them in an iterator.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: an Iterator of all of the FocusObservers.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetFocusObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
FocusObserver observer
manager:Add(observer)
Iterator&lt;FocusObserver&gt; focusObservers = manager:GetFocusObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetKeyboardObservers">public action GetKeyboardObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetKeyboardObservers"><p>This action gets the list of KeyboardObservers in the AccessibilityManager and returns them in an iterator.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: an Iterator of all of the KeyboardObservers.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetKeyboardObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
KeyboardObserver observer
manager:Add(observer)
Iterator&lt;KeyboardObserver&gt; keyObservers = manager:GetKeyboardObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMenuObservers">public action GetMenuObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMenuObservers"><p>This action gets the list of MenuObservers in the AccessibilityManager and returns them in an iterator.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: an Iterator of all of the MenuObservers.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMenuObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
MenuObserver observer
manager:Add(observer)
Iterator&lt;MenuObserver&gt; menuObservers = manager:GetMenuObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetMouseObservers">public action GetMouseObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetMouseObservers"><p>This action gets the list of MouseObservers in the AccessibilityManager and returns them in an iterator.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: an Iterator of all of the MouseObservers.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetMouseObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
MouseObserver observer
manager:Add(observer)
Iterator&lt;MouseObserver&gt; mouseObservers = manager:GetMouseObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetPropertyObservers">public action GetPropertyObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetPropertyObservers"><p>This action gets the list of PropertyObservers in the AccessibilityManager and returns them in an iterator.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: an Iterator of all of the PropertyObservers.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetPropertyObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
PropertyObserver observer
manager:Add(observer)
Iterator&lt;PropertyObserver&gt; keyObservers = manager:GetPropertyObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSizeOfEverythingObservers">public action GetSizeOfEverythingObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSizeOfEverythingObservers"><p>This action gets the number of EverythingObservers in the AccessibilityManager and returns that value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: an integer of how many EverythingObservers.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSizeOfEverythingObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
EverythingObserver observer
manager:Add(observer)
integer numEverything = manager:GetSizeOfEverythingObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSizeOfFocusObservers">public action GetSizeOfFocusObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSizeOfFocusObservers"><p>This action gets the number of FocusObservers in the AccessibilityManager and returns that value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: an integer of how many FocusObservers.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSizeOfFocusObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
FocusObserver observer
manager:Add(observer)
integer numFocus = manager:GetSizeOfFocusObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSizeOfKeyboardObservers">public action GetSizeOfKeyboardObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSizeOfKeyboardObservers"><p>This action gets the number of KeyboardObservers in the AccessibilityManager and returns that value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: an integer of how many KeyboardObservers.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSizeOfKeyboardObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
KeyboadObserver observer
manager:Add(observer)
integer numKeyboard = manager:GetSizeOfKeyboardObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSizeOfMenuObservers">public action GetSizeOfMenuObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSizeOfMenuObservers"><p>This action gets the number of MenuObservers in the AccessibilityManager and returns that value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: an integer of how many MenuObservers.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSizeOfMenuObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
MenuObserver observer
manager:Add(observer)
integer numKeyboard = manager:GetSizeOfMenuObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSizeOfMouseObservers">public action GetSizeOfMouseObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSizeOfMouseObservers"><p>This action gets the number of MouseObservers in the AccessibilityManager and returns that value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: an integer of how many MouseObservers.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSizeOfMouseObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
MouseObserver observer
manager:Add(observer)
integer numMouse = manager:GetSizeOfMouseObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSizeOfPropertyObservers">public action GetSizeOfPropertyObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSizeOfPropertyObservers"><p>This action gets the number of PropertyObservers in the AccessibilityManager and returns that value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: an integer of how many PropertyObservers.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSizeOfPropertyObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
PropertyObserver observer
manager:Add(observer)
integer num = manager:GetSizeOfPropertyObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetSizeOfWindowObservers">public action GetSizeOfWindowObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetSizeOfWindowObservers"><p>This action gets the number of WindowObservers in the AccessibilityManager and returns that value.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>integer</strong>: an integer of how many WindowObservers.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetSizeOfWindowObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
WindowObserver observer
manager:Add(observer)
integer numWindow = manager:GetSizeOfWindowObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="GetWindowObservers">public action GetWindowObservers()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="GetWindowObservers"><p>This action gets the list of WindowObservers in the AccessibilityManager and returns them in an iterator.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>Libraries.Containers.Blueprints.Iterator</strong>: an Iterator of all of the WindowObservers.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="GetWindowObservers"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
WindowObserver observer
manager:Add(observer)
Iterator&lt;WindowObserver&gt; windowObservers = manager:GetWindowObservers()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsEmpty">public action IsEmpty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsEmpty"><p>This action checks if the AccessibilityManager has any observers in it, and returns true if it is empty, otherwise returns false.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: a boolean value, true if the AccessibilityManager is 
        empty, otherwise false.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsEmpty"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
EverythingObserver eObserver
WindowObserver wObserver
FocusObserver fObserver
manager:Add(eObserver)
manager:Add(wObserver)
manager:Add(fObserver)
boolean result = manager:IsEmpty()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsEverythingObserversEmpty">public action IsEverythingObserversEmpty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsEverythingObserversEmpty"><p>This action checks if the AccessibilityManager has any EverythingObservers in it, and returns true if it is empty, otherwise returns false.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: a boolean value, true if the AccessibilityManager 
        has no EverythingObservers, otherwise false.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsEverythingObserversEmpty"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
EverythingObserver observer
manager:Add(observer)
boolean result = manager:IsEverythingObserversEmpty()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsFocusObserversEmpty">public action IsFocusObserversEmpty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsFocusObserversEmpty"><p>This action checks if the AccessibilityManager has any FocusObservers in it, and returns true if it is empty, otherwise returns false.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: a boolean value, true if the AccessibilityManager 
        has no FocusObservers, otherwise false.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsFocusObserversEmpty"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
FocusObserver observer
manager:Add(observer)
boolean result = manager:IsFocusObserversEmpty()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsKeyboardObserversEmpty">public action IsKeyboardObserversEmpty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsKeyboardObserversEmpty"><p>This action checks if the AccessibilityManager has any KeyboardObservers in it, and returns true if it is empty, otherwise returns false.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: a boolean value, true if the AccessibilityManager 
        has no KeyboardObservers, otherwise false.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsKeyboardObserversEmpty"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
KeyboardObserver observer
manager:Add(observer)
boolean result = manager:IsKeyboardObserversEmpty()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsMenuObserversEmpty">public action IsMenuObserversEmpty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsMenuObserversEmpty"><p>This action checks if the AccessibilityManager has any MenuObservers in it, and returns true if it is empty, otherwise returns false.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: a boolean value, true if the AccessibilityManager 
        has no MenuObservers, otherwise false.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsMenuObserversEmpty"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
MenuObserver observer
manager:Add(observer)
boolean result = manager:IsMenuObserversEmpty()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsMouseObserversEmpty">public action IsMouseObserversEmpty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsMouseObserversEmpty"><p>This action checks if the AccessibilityManager has any MouseObservers in it, and returns true if it is empty, otherwise returns false.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: a boolean value, true if the AccessibilityManager 
        has no MouseObservers, otherwise false.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsMouseObserversEmpty"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
MouseObserver observer
manager:Add(observer)
boolean result = manager:IsMouseObserversEmpty()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsPropertyObserversEmpty">public action IsPropertyObserversEmpty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsPropertyObserversEmpty"><p>This action checks if the AccessibilityManager has any PropertyObservers in it, and returns true if it is empty, otherwise returns false.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: a boolean value, true if the AccessibilityManager 
        has no PropertyObservers, otherwise false.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsPropertyObserversEmpty"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
PropertyObserver observer
manager:Add(observer)
boolean result = manager:IsPropertyObserversEmpty()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="IsWindowObserversEmpty">public action IsWindowObserversEmpty()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="IsWindowObserversEmpty"><p>This action checks if the AccessibilityManager has any WindowObservers in it, and returns true if it is empty, otherwise returns false.</p></span>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: a boolean value, true if the AccessibilityManager 
        has no WindowObservers, otherwise false.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="IsWindowObserversEmpty"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
WindowObserver observer
manager:Add(observer)
boolean result = manager:IsWindowObserversEmpty()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Remove:Libraries.Accessibility.EverythingObserver">public action Remove(Libraries.Accessibility.EverythingObserver observer)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Remove:Libraries.Accessibility.EverythingObserver"><p>This action removes the EverythingObserver if it exists in the AccessibilityManager and returns a boolean for whether or not it was successful.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="observer"><strong>Libraries.Accessibility.EverythingObserver</strong> <em>observer</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="observer">The observer to be removed.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if remove was successful, otherwise returns false.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Remove:Libraries.Accessibility.EverythingObserver"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
EverythingObserver observer
manager:Add(observer)
boolean success = manager:Remove(observer)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Remove:Libraries.Accessibility.FocusObserver">public action Remove(Libraries.Accessibility.FocusObserver observer)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Remove:Libraries.Accessibility.FocusObserver"><p>This action removes the FocusObserver if it exists in the AccessibilityManager and returns a boolean for whether or not it was successful.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="observer"><strong>Libraries.Accessibility.FocusObserver</strong> <em>observer</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="observer">The observer to be removed.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if remove was successful, otherwise returns false.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Remove:Libraries.Accessibility.FocusObserver"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
FocusObserver observer
manager:Add(observer)
boolean success = manager:Remove(observer)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Remove:Libraries.Accessibility.KeyboardObserver">public action Remove(Libraries.Accessibility.KeyboardObserver observer)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Remove:Libraries.Accessibility.KeyboardObserver"><p>This action removes the KeyboardObserver if exists in the AccessibilityManager, and returns a boolean for whether or not it was successful.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="observer"><strong>Libraries.Accessibility.KeyboardObserver</strong> <em>observer</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="observer">The observer to be removed.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if remove was successful, otherwise returns false.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Remove:Libraries.Accessibility.KeyboardObserver"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
KeyboardObserver observer
manager:Add(observer)
boolean success = manager:Remove(observer)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Remove:Libraries.Accessibility.MenuObserver">public action Remove(Libraries.Accessibility.MenuObserver observer)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Remove:Libraries.Accessibility.MenuObserver"><p>This action removes the MenuObserver if it exists in the AccessibilityManager and returns a boolean for whether or not the removal was successful.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="observer"><strong>Libraries.Accessibility.MenuObserver</strong> <em>observer</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="observer">The observer to be removed.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if remove was successful, otherwise returns false.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Remove:Libraries.Accessibility.MenuObserver"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
MenuObserver observer
manager:Add(observer)
boolean success = manager:Remove(observer)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Remove:Libraries.Accessibility.MouseObserver">public action Remove(Libraries.Accessibility.MouseObserver observer)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Remove:Libraries.Accessibility.MouseObserver"><p>This action removes the MouseObserver if it exists in the AccessibilityManager and returns a boolean for whether or not it was successful.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="observer"><strong>Libraries.Accessibility.MouseObserver</strong> <em>observer</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="observer">The observer to be removed.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if remove was successful, otherwise returns false.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Remove:Libraries.Accessibility.MouseObserver"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
MouseObserver observer
manager:Add(observer)
boolean success = manager:Remove(observer)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Remove:Libraries.Accessibility.PropertyObserver">public action Remove(Libraries.Accessibility.PropertyObserver observer)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Remove:Libraries.Accessibility.PropertyObserver"><p>This action removes the PropertyObserver if it exists in the AccessibilityManager and returns a boolean for whether or not it was successful.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="observer"><strong>Libraries.Accessibility.PropertyObserver</strong> <em>observer</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="observer">The observer to be removed.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if remove was successful, otherwise returns false.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Remove:Libraries.Accessibility.PropertyObserver"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
PropertyObserver observer
manager:Add(observer)
boolean success = manager:Remove(observer)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Remove:Libraries.Accessibility.WindowObserver">public action Remove(Libraries.Accessibility.WindowObserver observer)</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Remove:Libraries.Accessibility.WindowObserver"><p>This action removes the WindowObserver if it exists in the AccessibilityManager and returns a boolean for whether or not it was successful.</p></span><em>Parameters</em>:<ul class="parameters"><li><h5><span class="controllable" data-componentType="parameter-name" data-parameterkey="observer"><strong>Libraries.Accessibility.WindowObserver</strong> <em>observer</em>:</span></h5><span class="controllable" data-componentType="parameter-description" data-parameterkey="observer">The observer to be removed.</span></li>

</ul>
<em>Returns</em>:<ul class="parameters">
	<li><strong>boolean</strong>: true if remove was successful, otherwise returns false.</li>
</ul>
	<span class="controllable" data-componentType="action-example" data-actionkey="Remove:Libraries.Accessibility.WindowObserver"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
WindowObserver observer
manager:Add(observer)
boolean success = manager:Remove(observer)</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Start">public system action Start()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Start"><p>This action allows the AccessibilityManager to start getting events from the system.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Start"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
EverythingObserver observer
manager:Add(observer)
manager:Start()</pre>

</div><div class="action">
	<h3 class="action_title"> <span class="controllable" data-componentType="action-name" data-actionkey="Stop">public system action Stop()</span></h3>

	<span class="controllable" data-componentType="action-description" data-actionkey="Stop"><p>This action stops events being passed to the AccessibilityManager from the system. Used after you have used Start() to start recieving events.</p></span>

	<span class="controllable" data-componentType="action-example" data-actionkey="Stop"><em>Example Code:</em></span>
<pre class="code">AccessibilityManager manager
EverythingObserver observer
manager:Add(observer)
manager:Start()
manager:Stop()</pre>

</div><h2> Inherited Actions</h2> 

<ul class="parent_variables">
<li class="package_standard"><strong>From: </strong><a href="../../Libraries/Language/Object.php">Libraries.Language.Object</a> public action Compare(Libraries.Language.Object object), public action Equals(Libraries.Language.Object object), public system action GetHashCode()</li>
</ul><?php include('../../static/templates/classfooter.template.php'); ?><?php include('../../static/templates/pagefooter.template.php'); ?>