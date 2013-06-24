<?php include("../../../static/templates/pageheader.template.php"); ?> <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Lab7_4';
</script>

          <h1>Lab 7.4: Using Arrays to Customize a Screen Reader</h1>
<h2">Objectives</h2>
<p>
  The goal of this lab is to understand the following concepts:
</p>
<ul>
  <li>
    Create and use arrays
  </li>
  <li>
    How to search an array
  </li>
  <li>
    How to use the Libraries.Accessibility classes
  </li>
</ul>
<h2 id="Overview">Overview</h2>
<p>
  In the Chapter 6 labs, you made two different screen readers. In <a href="../../../../documents/curriculum/chapter6/lab6_4.php">lab 6_4</a> you made a screen reader that read keystrokes. In <a href="../../../../documents/curriculum/chapter6/lab6_5.php">lab 6_5</a> you made a screen reader that told you which component on the screen had gained focus. For this lab, we will customize the output of a few different types of observers, then combine the observers into one screen reader.
</p>
<h2>Task 1: Getting Started</h2>
<p>
  Start Sodbeans. Create a new “Quorum Application” project, and name it <strong>Lab7_4</strong>.  In the <tt>Main.quorum</tt> file, it should contain a <tt>Main</tt> class and <tt>Main</tt> action.
</p>
<p>
  You will create 3 additional class in this project.  In the New File dialog, create a new file by selecting “Quorum” and “Quorum Class” in the Categories and File Types windows, respectively.  Then, name  the new files <strong>CustomizedFocusObserver</strong>, <strong>CustomizedMenuObserver</strong>, and <strong>CustomizedKeyboardObserver</strong> in the New Quorum Class dialog.
</p>
<p>
  Don't forget to include the Accessibility, Speech, and Array libraries!
</p>
<h2>Task 2: Inheriting from the Observer Classes</h2>
<p>
  To make multiple types of observers we will need multiple classes to implement them. Our <strong>CustomizedFocusObserver</strong> class should inherit from the <tt>FocusObserver</tt> class, <strong>CustomizedMenuObserver</strong> from the <tt>MenuObserver</tt> class, and <strong>CustomizedKeyboardObserver</strong> from the <tt>KeyboardObserver</tt> class. To do this, use the <tt>is</tt> keyword just as we did in <tt>lab 6.4</tt> and <tt>lab 6.5</tt>. 
</p>
<p>
  We are still going to need an instance of the Speech class in all of your observer classes, just as you did in the first two screen reader labs, to say the events quickly. In this lab, we are also going to use array of text objects, called actions, to customize the FocusObserver and MenuObserver.
</p>
<pre class="code">
  use Libraries.Accessibility.all 
  use Libraries.Sound.Speech
  use Libraries.Containers.Array
  
  class CustomizedFocusObserver is FocusObserver
	Speech speech
	Array&lttext&gt actions
  end
</pre>
<p>
  <strong>CustomizedKeyboardObserver</strong>
</p>
<p>
  Now we can choose how we are going to customize our observers within the ReceiveEvent. In our <strong>CustomizedKeyboardObserver</strong>, let's only say the events for keys being pressed and take out the events for keys being released. The KeyboardEvent class has an action called <tt>GetAction()</tt> that will return one of two text values, "KeyPress" or "KeyRelease". Before we say the event, we can use an if statement and see if the <tt>GetAction()</tt> action returned "KeyPress". We will only say the event inside that if statement so that we are only going to speak when a key was pressed.
</p>
<pre class="code">
  action ReceiveEvent(AccessibilityEvent event)
	if event:GetAction() = "KeyPress"
		speech:Say(event:GetKey())
	end
  end
</pre>
<p>
  <strong>CustomizedMenuObserver</strong>
</p>
<p>
  Now, let's customize our <strong>CustomizedMenuObserver</strong> class. The MenuEvent class also has a <tt>GetAction()</tt> function, but there are 4 different text values that it can return. 
</p>
<ul>
  <li><strong>MenuOpen:</strong> Indicates that a menu from the menu bar was opened.</li>
  <li><strong>MenuClose:</strong> Indicates that a menu from the menu bar was closed.</li>
  <li><strong>PopUpMenuOpen:</strong> Indicates that a pop up menu was opened.</li>
  <li><strong>PopUpMenuClose:</strong> Indicates that a pop up menu was closed.</li>
</ul>
<p>
  For this lab, let's choose the "MenuOpen" and "PopUpMenuOpen" events to say. To filter out everything else, the first thing we need to do is add those values to the <tt>actions</tt> array.  We are going to want this array initialized as soon as we create the <strong>CustomizedMenuObserver</strong> class. This can be done by implementing the <tt>on create</tt> action that is called as soon as we create an instance of the class. In this action, use the <tt>Add</tt> action to add the action phrases to the <strong>actions</strong> array. 
</p>
<pre class="code">
  on create
	actions:Add("MenuOpen")
	actions:Add("PopUpMenuOpen")
  end
</pre>
<p>
  In the <tt>ReceiveEvent</tt> action we can use the <tt>Has(Type value)</tt> action and check to see if <tt>GetAction()</tt> returned a value in the array. Just as in the <strong>CustomizedKeyboardObserver</strong>, we will only say the event inside an if statement so that we will speak when one of those events happened.
</p>
<pre class="code">
  text toSay 
  if actions:Has(event:GetAction())
	toSay = event:GetName() + "opened."
  end
</pre>
<p>
  <tt>MenuEvents</tt> often have children in them as well. These children represent the individual items of the menu. We can access them using the <tt>GetChild(integer index)</tt> action of the <tt>MenuEvent</tt> class. 
</p>
<p>
  The first step to getting all of the children for a menu is to figure out how many children there are using the <tt>GetChildCount()</tt> action of the <tt>MenuEvent</tt> class. We can then use a <tt>repeat</tt> block to loop through each one of the children to get its information.
</p>
<p>
  Inside of our <tt>repeat</tt> block we need to create an AccessibleChild. We can then set the child using the <tt>GetChild(integer index)</tt> action, using a counting variable as the index. Using the <tt>AccessibleChild</tt>'s<tt>GetName()</tt>, <tt>GetComponentType()</tt>, and <tt>GetKeyboardShortcut()</tt> actions we can build a description of the child to say. An example of how to use this AccessibleChild is below.
</p>
<pre class="code">
  integer count = 0
  repeat while count < event:GetChildCount()
	AccessibleChild child = event:GetChild(count)
	if child not= undefined
		toSay = toSay + " " + child:GetName() + " " + child:GetKeyboardShortcut()
	end
	count = count + 1
  end
</pre>
<p>
  We can then add the repeat block into the <tt>ReceiveEvent</tt> action to include that in the information we say about the menu event.
</p>
<p>
  <strong>CustomizedFocusObserver</strong>
</p>
<p>
  Just as the two previous observers the FocusEvent class also has a <tt>GetAction()</tt> function, this time with 5 different text values that it can return. 
</p>
<ul>
  <li><strong>Component:</strong> Indicates that a component has been focused</li>
  <li><strong>Desktop:</strong> Indicates that the active desktop was switched.</li>
  <li><strong>SwitchWindow:</strong> Indicates that the user has pressed or released ALT + TAB and the switch window was activated or deactivated</li>
  <li><strong>MouseCaptureStart:</strong> Indicates that a window has received mouse capture focus</li>
  <li><strong>MouseCaptureStop:</strong> Indicates that a window has lost the mouse capture focus</li>
</ul>
<p>
  For this lab add the "Component" and "Desktop" values to the actions array in the same way we did for the <strong>CustomizedMenuObserver</strong> class. Then use the <tt>Has()</tt> action to check the array in the <tt>ReceiveEvent</tt> action.
</p>
<p>

  We can apply this type of filtering to other aspects of the event as well. For example, we can only say events that have "Sodbeans" in the name or only say events for the number keys. Feel free to try filtering the events based on other details if you want to.
</p>
<h2>Task 3: Using the observer classes</h2>
<p>
  Now that we have created our three observer classes, we can add them all to an <tt>AccessibilityManager</tt> to begin listening for events. Go to our <tt>Main</tt> and create an instance of all three of our observer classes. Then, use the AccessibilityManager's <tt>Add</tt> action to add all three of our observers. Now all we have to do is call <tt>AccessibilityManager</tt>'s <tt>Start</tt> action to run all three of the observers at once.
</p>
<h2>Sample Output</h2>
<p>
  When you run the program it should not do anything until you trigger one of the events that we filtered our observers to say.
</p>
<p>
  When you are done, debug and fix any errors, then show your code to your instructor.
</p>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>