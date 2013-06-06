<?php include("../../../static/templates/pageheader.template.php"); ?> <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Lab6_4';
</script>

          <h1>Lab 6.4: Using Inheritance to Make a Screen Reader</h1>
<h2">Objectives</h2>
<p>
  The goal of this lab is to understand the following concepts:
</p>
<ul>
  <li>
    How to create and use a class hierarchical system
  </li>
  <li>
    How to use blueprint actions
  </li>
  <li>
    How to use the Libraries.Accessibility classes
  </li>
</ul>
<h2 id="Overview">Overview</h2>
<p>
  A screen reader is a computer program that runs in the background and attempts to read what is displayed on the screen out loud for the user. They can tell you when a new window popped up on the screen, when you press a button on the keyboard, or even when you select a new icon on the desktop. In this lab you will make a screen reader that will tell you what button you pushed on the keyboard.
</p>
<h2>Task 1: Getting Started</h2>
<p>
  Start Sodbeans. Create a new “Quorum Application” project, and name it <strong>Lab6_3</strong>.  In the <tt>Main.quorum</tt> file, it should contain a <tt>Main</tt> class and <tt>Main</tt> action.
</p>
<p>
  You will create an additional classes in this project.  In the New File dialog, create a new file by selecting “Quorum” and “Quorum Class” in the Categories and File Types windows, respectively.  Then, name  the new file <strong>Observer</strong> in the New Quorum Class dialog.
</p>
<h2>Task 2: Inheriting from the KeyboardObserver Class</h2>
<p>
  Your <strong>Observer</strong> class will need to inherit the variables and actions of the <tt>KeyboardObserver</tt> class. To do this, tell Sodbeans that your <tt>Observer</tt> class "is" a <tt>KeyboardObserver</tt>. 
</p>
<pre class="code">
  class Observer is KeyboardObserver
  end
</pre>
<p>
  Since you are going to use your <tt>Observer</tt> class to say things very quickly you will need to use an object of class <tt>Speech</tt>. Using the <tt>Speech</tt> class instead of the say command will allow you to say the event as soon as you get it instead of waiting until the previous event is done.
</p>
<p>
  All of the observer classes (<tt>MouseObserver</tt>, <tt>KeyboardObserver</tt>, <tt>FocusObserver</tt>, <tt>WindowObserver</tt>, and <tt>EverythingObserver</tt>) have a blueprint action, <strong>RecieveEvent</strong>, that you will need to implement. The <tt>RecieveEvent</tt> action also has a parameter of type <tt>AccessibilityEvent</tt> called event. An <tt>AccessibilityEvent</tt> holds all of the information about the event that happened on the computer. The <tt>RecieveEvent</tt> action is where you will say the events your screen reader is getting. 
</p>
<pre class="code">
  action RecieveEvent(AccessibilityEvent event)
	speech:Say(event:GetDescription())
  end
</pre>
<p>
  In the example above, I used the <tt>Speech</tt> variable in the class to say the description of the event. All events have a <tt>GetDescription</tt> action that will return a phrase to describe the event. You can get the individual descriptions of the event and create your own phrase from that as well. 
</p>

<h2>Task 3: Using the Observer class</h2>
<p>
  Now that you have created a observer class, you can add that to an AccessibilityManager to begin listening for events. 
</p>
<p>
  Go to your <tt>Main</tt> and instantiate an object of <tt>AccessibilityManager</tt> and one of your <tt>Observer</tt> class. Then, using the <tt>Add(KeyboardObserver observer)</tt> action you can add your observer to the <tt>AccessibilityManager</tt>. Now all you have to do is use the <tt>Start</tt> action to start your <tt>AccessibilityManager</tt>, and you now have a talking screen reader. 
</p>
<h2>Sample Output</h2>
<p>
  When you run the program it should not do anything until you push a button on the keyboard. Once you do that it should say out loud which button it was. 
</p>
<p>
  When you are done, debug and fix any errors, then show your code to your instructor.
</p>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>