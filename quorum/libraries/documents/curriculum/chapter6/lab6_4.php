<?php include("../../../static/templates/pageheader.template.php"); ?> <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Lab6_4';
</script>

          <h1>Lab 6.4: Making a Screen Reader that reads Keystrokes</h1>
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
<p>
  A screen reader is a computer program that runs in the background and attempts to read what is displayed on the screen out loud for the user. They can tell you when a new window popped up on the screen, when you press a key on the keyboard, or even when you select a new icon on the desktop. In this lab you will make a screen reader that will tell you what key you pushed on the keyboard.
</p>
<h2>Task 1: Getting Started</h2>
<p>
  Start Sodbeans. Create a new “Quorum Application” project, and name it <strong>Lab6_4</strong>.  In the <tt>Main.quorum</tt> file, it should contain a <tt>Main</tt> class and <tt>Main</tt> action.
</p>
<p>
  You will create an additional class in this project.  In the New File dialog, create a new file by selecting “Quorum” and “Quorum Class” in the Categories and File Types windows, respectively.  Then, name the new file <strong>Observer</strong> in the New Quorum Class dialog.
</p>
<p>
  For this lab you will be using a few new libraries. At the top of your <strong>Observer</strong> file, make sure you include the following code to include the Speech class from the Sound library and all of the Accessibility libraries. Your main file will also need to use the Accessibility libraries, so you will need to include that use statement in your main file as well.
</p>
<pre class="code">
  use Libraries.Accessibility.all 
  use Libraries.Sound.Speech
</pre>
<h2>Task 2: Inheriting from the KeyboardObserver Class</h2>
<p>
  Your <strong>Observer</strong> class will need to inherit the variables and actions of the <tt>KeyboardObserver</tt> class. To do this, tell Sodbeans that your <tt>Observer</tt> class "is" a <tt>KeyboardObserver</tt>. 
</p>
<pre class="code">
  class Observer is KeyboardObserver
  end
</pre>
<p>
  Since you are going to use your <tt>Observer</tt> class to say things very quickly, you will need to use an object of class <tt>Speech</tt>. Using the <tt>Speech</tt> class instead of the say command will allow you to say the event as soon as you get it instead of waiting until the previous event is done. To use the <tt>Speech</tt> class, you use the action Say and pass it the text you want it to say. Make sure you add a use statement for the Speech class, found in the Sound library, to your file.
</p>
<p>
  All of the observer classes (<tt>MouseObserver</tt>, <tt>KeyboardObserver</tt>, <tt>FocusObserver</tt>, <tt>WindowObserver</tt>, and <tt>EverythingObserver</tt>) have a blueprint action, <strong>RecieveEvent</strong>, that you will need to implement. The implementation of the <tt>RecieveEvent</tt> action should say the event your screen reader is getting. To do that we will use the <tt>RecieveEvent</tt>'s parameter of type <tt>AccessibilityEvent</tt> called event. An <tt>AccessibilityEvent</tt> holds all of the information about the event that happened on the computer. The <tt>AccessibilityEvent</tt> class has an action, called <tt>GetDescription</tt>, that returns a sentence describing the event. You can then give that sentence to the speech object and have it say that. 
</p>
<pre class="code">
  action RecieveEvent(AccessibilityEvent event)
	speech:Say(event:GetDescription())
  end
</pre>
<p>
  In the example above, I used the <tt>Speech</tt> variable in the class to say the description of the event.
</p>

<h2>Task 3: Using the Observer class</h2>
<p>
  Now that you have created a observer class, you can add that to an <tt>AccessibilityManager</tt> to begin listening for events. 
</p>
<p>
  Go to your <tt>Main</tt> and instantiate an object of <tt>AccessibilityManager</tt> and one of your <tt>Observer</tt> class. The <tt>AccessibilityManager</tt> is what will run your screen reader. You can add as many instances of the observer classes as you want to the <tt>AccessibilityManager</tt>. When you start the <tt>AccessibilityManager</tt> it will start getting events from the system. It sends the events to the observers and calls their <tt>RecieveEvent</tt> function, which you should have implemented to say the event.
</p>
<p>
  You should have an instance of <tt>Observer</tt> and an instance of <tt>AccessibilityManager</tt>. Use AccessibilityManager's <tt>Add</tt> action to add your observer to the instance of <tt>AccessibilityManager</tt>. Now all you have to do is call AccessibilityManager's <tt>Start</tt> action and run your program. Congratulations! You now have a talking screen reader. 
</p>
<h2>Sample Output</h2>
<p>
  When you run the program it should not do anything until you push a key on the keyboard. Once you do that it should say out loud which key it was. 
</p>
<p>
  When you are done, debug and fix any errors, then show your code to your instructor.
</p>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>