<?php include("../../../static/templates/pageheader.template.php"); ?> <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Lab6_5';
</script>

          <h1>Lab 6.5: Making a Screen Reader that Identifies a Change in Focus</h1>
<h2">Objectives</h2>
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
  In <a href="lab6_4.php">lab 6_4</a> you built a screen reader that told you what key on the keyboard you pressed. In Quorum, you can write screen readers that do even more. In this lab you will make another screen reader that will tell you when a new window, button, or any other component has gained focus.
<p>
</p>
<h2>Task 1: Getting Started</h2>
<p>
  Start Sodbeans. Create a new “Quorum Application” project, and name it <strong>Lab6_5</strong>.  In the <tt>Main.quorum</tt> file, it should contain a <tt>Main</tt> class and <tt>Main</tt> action.
</p>
<p>
  You will create an additional class in this project.  In the New File dialog, create a new file by selecting “Quorum” and “Quorum Class” in the Categories and File Types windows, respectively.  Then, name  the new file <strong>Observer</strong> in the New Quorum Class dialog.
</p>
<p>
  Don't forget to include the appropriate libraries.
</p>
<h2>Task 2: Inheriting from the FocusObserver Class</h2>
<p>
  Just like in <a href="lab6_4.php">lab 6_4</a>, your <strong>Observer</strong> class will need to inherit the variables and actions of an observer class. To get the events that involve the focus changing we need to inherit from the <tt>FocusObserver</tt> class.
</p>
<pre class="code">
  use Libraries.Accessibility.all 
  use Libraries.Sound.Speech
  class Observer is FocusObserver
  end
</pre>
<p>
  Just as in <a href="lab6_4.php">lab 6_4</a>, your <tt>Observer</tt> class is going to need an instance of the <tt>Speech</tt> class to say things quickly. 
</p>
<p>
  You also need to implement the blueprint action <tt>ReceiveEvent</tt>. In <a href="lab6_4.php">lab 6_4</a> we used the <tt>GetDescription</tt> action to explain the event, but you can also get the individual aspects of the event and make your own sentence to describe the event. There are 4 other actions that you can call on an event to get a description of the event. <tt>GetCategory</tt> will give you the "category" of the event. This will be Focus, Keyboard, Mouse, Window, Notification, Menu, or PropertyChange. <tt>GetName</tt> returns the name of the object that created the action, this will be things like "K" for the k key on the keyboard or "Sodbeans 3.5" for the Sodbeans window. <tt>GetAction</tt> action will tell you what happened in the event, and if there is more description for the event you can use the <tt>GetMoreActionInformation</tt> action. Use any or all of these actions to create a sentence to describe the event and use the <tt>Speech</tt> object to say your sentence.
</p>
<pre class="code">
  action ReceiveEvent(AccessibilityEvent event)
	speech:Say("The " + event:GetMoreActionInformation() + ", " + event:GetName() + ", was focused.")
  end
</pre>
<p>
  Above is an example of how you could combine the parts of the event to make a sentence to describe it.
</p>

<h2>Task 3: Using the Observer class</h2>
<p>
  Now that you have created the <tt>Observer</tt> class, you can add an instance of it to an instance of <tt>AccessibilityManager</tt>. You can then start the <tt>AccessibilityManager</tt> and begin listening for events just as you did in <a href="lab6_4.php">lab 6_4</a>.
</p>
<h2>Sample Output</h2>
<p>
  When you run the program it should not do anything until you focus something (open a new window or select a button or menu). Once you do that it should say out loud the sentence you created.  
</p>
<p>
  When you are done, debug and fix any errors, then show your code to your instructor.
</p>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>