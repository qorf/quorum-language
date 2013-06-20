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
  In Chapter 6, you made two different screen readers. In <a href="../../../../documents/curriculum/chapter6/lab6_4.php">lab 6_4</a> you made a screen reader for the keyboard. In <a href="../../../../documents/curriculum/chapter6/lab6_5.php">lab 6_5</a> you made a screen reader that told you which component on the screen had gained focus. For this lab, you will use arrays to customize multiple types of observers, then combine the observers into one screen reader.
</p>
<h2>Task 1: Getting Started</h2>
<p>
  Start Sodbeans. Create a new “Quorum Application” project, and name it <strong>Lab7_4</strong>.  In the <tt>Main.quorum</tt> file, it should contain a <tt>Main</tt> class and <tt>Main</tt> action.
</p>
<p>
  You will create 3 additional class in this project.  In the New File dialog, create a new file by selecting “Quorum” and “Quorum Class” in the Categories and File Types windows, respectively.  Then, name  the new files <strong>fObserver</strong>, <strong>wObserver</strong>, and <strong>kObserver</strong> in the New Quorum Class dialog.
</p>
<p>
  Don't forget to include the Accessibility and Speech libraries.
</p>
<h2>Task 2: Inheriting from the Observer Classes</h2>
<p>
  To make multiple types of observers we will need multiple classes. Your <strong>fObserver</strong> class should inherit from the <tt>FocusObserver</tt> class, <strong>wObserver</strong> from the <tt>WindowObserver</tt> class, and <strong>kObserver</strong> from the <tt>KeyboardObserver</tt> class. To do this, use the "is" keyword just as you did in <tt>lab 6.4</tt> and <tt>lab 6.5</tt>. 
</p>
<p>
  You are still going to need an instance of the Speech class in all of your observer classes, just as you did in the first two screen reader labs, to say the events quickly. In this lab, you are also going to need an array of text objects that will be used to filter out the kinds of events we want to say.
</p>
<p>
  Each of the types of observers, <tt>FocusObserver</tt>, <tt>WindowObserver</tt>, <tt>KeyboardObserver</tt>, <tt>MouseObserver</tt>, and <tt>EverythingObserver</tt>, have a specific list of actions that can be used. Using that knowledge, you can choose which events you say by comparing the action of the event, using the <tt>GetAction action</tt>, to the phrase you want. For this lab choose a couple of actions that you want to filter out of your observers from the lists below.
</p>
<p>
  KeyboardType
</p>
<ul>
  <li><strong>KeyPress:</strong> Indicates that the user has pressed a key on the keyboard</li>
  <li><strong>KeyRelease:</strong> Indicates that the user has let go of a key (released it)</li>
</ul>
<p>
  FocusObserver
</p>
<ul>
  <li><strong>Component:</strong> Indicates that a component has been focused</li>
  <li><strong>Desktop:</strong> Indicates that the active desktop was switched.</li>
  <li><strong>SwitchWindow:</strong> Indicates that the user has pressed or released ALT + TAB and the switch window was activated or deactivated
</li>
  <li><strong>MouseCaptureStart:</strong> Indicates that a window has received mouse capture focus</li>
  <li><strong>MouseCaptureStop:</strong> Indicates that a window has lost the mouse capture focus</li>
</ul>
<p>
  WindowObserver
</p>
<ul>
  <li><strong>Minimize:</strong> Indicates that a window is about to be minimized</li>
  <li><strong>RestoreMinimized:</strong> Indicates that a minimized window will be restored</li>
  <li><strong>MoveOrResize:</strong> Indicates that a window has started or stopped being  moved or resized</li>
  <li><strong>HelpMode:</strong> Indicates that a window has entered or exited help mode</li>
  <li><strong>DragDropMode:</strong> Indicates that an application is entering or exiting drag-and-drop mode</li>
  <li><strong>Scroll:</strong> Indicates that the user has started or stopped scrolling a scroll bar</li>
  <li><strong>ElementTriggered:</strong> Indicates that an element has been triggered (e.g. clicking a button)</li>
  <li><strong>CreateComponent:</strong> Indicates that a new object has been created, this object could be a caret, header, list, tab, toolbar, tree view, or window</li>
  <li><strong>DestroyComponent:</strong> Indicates that an object has been destroyed, this object could be a caret, header, list, tab, toolbar, tree view, or window</li>
  <li><strong>ShowComponent:</strong> Indicates that an object that was hidden is now shown, this object could be a caret, cursor, or window</li>
  <li><strong>HideComponent:</strong> Indicates that an object was hidden, this object could be a caret or cursor</li>
  <li><strong>ReorderComponents:</strong> A container object has changed the order of its children, the object could be a header, list, toolbar, or window</li>
</ul>
<p>
  You can store all of the actions you want to say in the array you created so that it can be easily searched when deciding to say an event. To make sure that the array has the action phrases you want before you use them, you can add the text to the arrays in the <tt>on create</tt> action. Use the <tt>Add</tt> action to add the action phrases you want to say.
</p>
<p>
  Now that you have all of the phrases in an array you can check the array in the <tt>ReceiveEvent</tt> action. Using the <tt>Has</tt> action you can check the action of the event, using the <tt>GetAction</tt> action, to see if it is in the array. If it is in the array then you can say that event, otherwise you can filter it out by not doing anything with it.
</p>
<p>
  You can apply this type of filtering to other aspects of the event as well. For example you can only say events that have "Sodbeans" in the name or only say events for the number keys. Feel free to try filtering the events based on other details if you want to.
</p>
<h2>Task 3: Using the observer classes</h2>
<p>
  Now that you have created your three observer classes, you can add them all to an <tt>AccessibilityManager</tt> to begin listening for events. 
  Go to your <tt>Main</tt> and create an instance of all three of your observer classes. Then, use the AccessibilityManager's <tt>Add</tt> action to add all three of your observers. Now all you have to do is call AccessibilityManager's <tt>Start</tt> action to run all three of the observers at once.
</p>
<h2>Sample Output</h2>
<p>
  When you run the program it should not do anything until you create one of the events that you filtered your observers to say.
</p>
<p>
  When you are done, debug and fix any errors, then show your code to your instructor.
</p>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>