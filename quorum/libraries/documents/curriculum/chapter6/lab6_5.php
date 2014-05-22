<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Lab 6.5: Making a Screen Reader that Identifies a Change in Focus | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Lab 6.5</h1>
		<p>Making a Screen Reader that Identifies a Change in Focus</p>
	</div>
</div>

    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>


          <!--<h1>Lab 6.5: Making a Screen Reader that Identifies a Change in Focus</h1>-->
<h2>Objectives</h2>
<p>
  The goal of this lab is to understand the following concepts:
</p>
<ul>
  <li>
    Using a class hierarchical system
  </li>
  <li>
    How to instantiate blueprint actions
  </li>
  <li>
    How to use the Libraries.Accessibility classes
  </li>
</ul>
<h2 id="Overview">Overview</h2>
  In <a href="lab6_4.php">lab 6_4</a> we built a screen reader that told us what key on the keyboard was pressed. In Quorum, we can write screen readers that do even more. In this lab we will make another screen reader that will tell us when a new window, button, or any other component has gained focus.
<p>
</p>
<h2>Task 1: Getting Started</h2>
<p>
  Start Sodbeans. Create a new “Quorum Application” project, and name it <strong>Lab6_5</strong>.  In the <code>Main.quorum</code> file, it should contain a <code>Main</code> class and <code>Main</code> action.
</p>
<p>
  We will need to create an additional class in this project.  In the New File dialog, create a new file by selecting “Quorum” and “Quorum Class” in the Categories and File Types windows, respectively.  Then, name  the new file <strong>Observer</strong> in the New Quorum Class dialog.
</p>
<p>
  Don't forget to include the appropriate libraries!
</p>
<h2>Task 2: Inheriting from the FocusObserver Class</h2>
<p>
  Just like in <a href="lab6_4.php">lab 6_4</a>, our <strong>Observer</strong> class will need to inherit the variables and actions of an observer class. The class we need to include in order to get the events that involve the focus changing is the <code>FocusObserver</code> class. Just as in the last lab, use the <code>is</code> keyword to inherit from the <code>FocusObserver</code> class.
</p>
<pre class="code">
  use Libraries.Accessibility.all 
  use Libraries.Sound.Speech
  
  class Observer is FocusObserver
  end
</pre>
<p>
  Just as in <a href="lab6_4.php">lab 6_4</a>, our <code>Observer</code> class is going to need an instance of the <code>Speech</code> class to say things quickly. 
</p>
<p>
  We will also need to implement the blueprint action <code>ReceiveEvent</code> in our <code>Observer</code> class. In <a href="lab6_4.php">lab 6_4</a> we used the <code>GetDescription</code> action to explain the event, but you can also get the individual aspects of the event and make your own sentence to describe the event. The FocusEvent class has quite a few actions that you can use to get individual information about the event.
</p>
<ul>
  <li>
    <strong>GetCategory():</strong> For FocusEvents this will always be "Focus".
  </li>
  <li>
    <strong>GetAction():</strong> This returns a short indication of what was focused (e.g. Desktop, SwitchWindow, MouseCaptureStart).
  </li>
  <li>
    <strong>GetComponent():</strong> This returns a short description of the type of element that was focused (e.g. Button, Window, MenuItem).
  </li>
  <li>
    <strong>GetComponentName():</strong> This returns the name of the component that was focused(e.g. Cancel, Sodbeans 3.5, File).
  </li>
  <li>
    <strong>GetKeyboardShortcut():</strong> This returns the keyboard shortcut of the component that was focused.
  </li>
  <li>
    <strong>GetChildCount():</strong> This returns the number of children the event has For example, when you focus on a list it will tell you the number of items that list has in it.
  </li>
  <li>
    <strong>GetChild(integer index):</strong> This returns an AccessibleChild representing the child at the index of the parameter, if it is a valid index.
  </li>
</ul>
<p>
  Use any or all of these actions to create a description of the event and use the <code>Speech</code> object to say it.
</p>
<pre class="code">
  action ReceiveEvent(FocusEvent event)
	speech:Say("The " + event:GetComponent() + ", " + event:GetComponentName() + ", was focused. Its keyboard shortcut is " + event:GetKeyboardShortcut() + ".")
  end
</pre>
<p>
  Above is an example of how you could combine the parts of the event to make a sentence to describe it.
</p>

<h2>Task 3: Using the Observer class</h2>
<p>
  Now that we have implemented the <code>Observer</code> class, we can add an instance of it to our of <code>AccessibilityManager</code>. You can then start the <code>AccessibilityManager</code> and begin listening for events just as you did in <a href="lab6_4.php">lab 6_4</a>.
</p>
<h2>Sample Output</h2>
<p>
  When you run the program it should not do anything until you focus on something (open a new window or click a button or menu). Once you do that it should say out loud the sentence you created.  
</p>
<p>
  When you are done, debug and fix any errors, then show your code to your instructor.
</p>