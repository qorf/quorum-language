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
    Using a class hierarchy
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
  A screen reader is a computer program that runs in the background, reading
  what is displayed on the screen out loud for a blind or visually impaired user. 
  For example, screen readers might tell us when a new window popped up on the 
  screen, what keys are being pressed on a keyboard, or when an icon is selected 
  on the desktop. In this lab, we will make a simple screen reader that reads
  the keyboard and tells us which keys are being pressed. In future labs, we will
  expand on these ideas by adding more screen reader features.
</p>
<h2>Task 1: Getting Started</h2>
<p>
  We will begin the lab by starting Sodbeans 4.0 or later. Note that because 
  the accessibility libraries were created for Quorum 2.0, they are not available
  in Sodbeans 3.5 or earlier. Once we have started Sodbeans, we will create a 
  new "Quorum Application" project, and name it 
  <strong>Lab6_4</strong>.  In the <tt>Main.quorum</tt> file, it should
contain a <tt>Main</tt> class and <tt>Main</tt> action.
</p>
<p>
  Next, we will create an additional class for the project. This class is going to
  handle speaking our keyboard events. To start, in the New File dialog, 
  create a new file by selecting "Quorum" and "Quorum Class" in the Categories and 
  File Types windows, respectively.  Then, name the new file <strong>Observer</strong> 
  in the New Quorum Class dialog. In computer science, an "Observer" is a nickname
  for a class that "listens" for events that happen on a computer. While we are
  making an observer that listens for keyboard events, there are other observers
  available that listen for changes in focus, the mouse, menus, changes in the 
  computer's clock, or other events.
</p>
<p>
  For this lab we will be using a few new libraries to create our Observer. 
  At the top of our <strong>Observer</strong> file, we need to include the 1) 
  Speech class from the Sound library and 2) all of the Accessibility 
  libraries. Our main file will also need to use the Accessibility libraries, 
  so we will need to include that <tt>use</tt> statement in our main file as well.
</p>
<pre class="code">
  use Libraries.Accessibility.all 
  use Libraries.Sound.Speech
</pre>
<h2>Task 2: Inheriting from the KeyboardObserver Class</h2>
<p>
  Our <strong>Observer</strong> class will need to inherit the variables and 
  actions of the <tt>KeyboardObserver</tt> class. To do this, tell Sodbeans that 
  our <tt>Observer</tt> class "is" a <tt>KeyboardObserver</tt>. 
</p>
<pre class="code">
  //This is called "inheritance"
  class Observer is KeyboardObserver
  end
</pre>
<p>
  Since you we will be using our <tt>Observer</tt> class to say things very 
  quickly, we will need to use an object of class <tt>Speech</tt>, which 
  has more functionality than the <tt>say</tt> command for controlling speech. Using the
  <tt>Speech</tt> class instead of the say command will allow us to say events
  as soon as we get them instead of waiting until the previous event is done. 
  To use the <tt>Speech</tt> class, we use the action <tt>Say</tt> and pass it the text 
  we want it to say. As usual, when we use the <tt>Speech</tt> class, we need to
  add <tt>use Libraries.Sound.Speech</tt> to our file.
</p>
<p>
 As mentioned previously, there are many kinds of Observer. Specifically, Quorum 
 defines a <tt>MouseObserver</tt>, <tt>KeyboardObserver</tt>,
<tt>FocusObserver</tt>, <tt>WindowObserver</tt>, <tt>MenuObserver</tt>, 
<tt>PropertyObserver</tt>, and <tt>EverythingObserver</tt>. Each observer has
a blueprint action, <strong>RecieveEvent</strong>, that we need to 
implement in order to use them. The implementation of the <tt>RecieveEvent</tt> 
is where we code our implementation. To do that, we will use the 
<tt>RecieveEvent</tt>'s parameter of type <tt>AccessibilityEvent</tt>, <tt>FocusEvent</tt>,
or other types of events, depending on the kind of Observer. An 
<tt>AccessibilityEvent</tt> contains information about the 
event (e.g., what key was pressed, the coordinates of the mouse on the screen). \
The <tt>AccessibilityEvent</tt> class also has a custom action called 
<tt>GetDescription</tt>, which returns a sentence describing 
the event in English. We can use this sentence if we wish, or other values in the 
event, to write our screen reader.
</p>
<pre class="code">
  action RecieveEvent(AccessibilityEvent event)
	speech:Say(event:GetDescription())
  end
</pre>
<p>
  In the example above, we used the <tt>Speech</tt> variable in the class to say the description of the event.

</p>

<h2>Task 3: Using the Observer class</h2>
<p>
  Now that we have created a observer class, we can add that to an 
<tt>AccessibilityManager</tt> to begin listening for events. 
</p>
<p>
  Go to our <tt>Main</tt> and instantiate an object of <tt>AccessibilityManager</tt> 
  and one of your <tt>Observer</tt> class. The <tt>AccessibilityManager</tt> is 
  what will run your screen reader. You can add as many instances of the 
  observer classes as we want to the <tt>AccessibilityManager</tt>. When we 
  start the <tt>AccessibilityManager</tt> it will begin receiving events from the 
  system. More specifically, the <tt>RecieveEvent</tt> action will begin obtaining
  events from the system. 
</p>
<p>
  We should have an instance of <tt>Observer</tt> and an instance of 
<tt>AccessibilityManager</tt>. We can use AccessibilityManager's <tt>Add</tt> action to 
add our observer to the instance of <tt>AccessibilityManager</tt>. Now, we need 
to call AccessibilityManager's <tt>Start</tt> action and run our program. 
Congratulations, we now the beginnings of a screen reader that talks to us! 
</p>
<h2>Sample Output</h2>
<p>
  When you run the program it should not do anything until we press a key on the 
  keyboard. When we press a key, our screen reader should output which key was 
  pressed.
</p>
<p>
  When we are done, first debug, fix any errors, and then show the code to the instructor.
</p>

 <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>  <?php include("../../../static/templates/pageheader.template.php"); ?>