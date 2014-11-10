<?php include("../../../static/templates/pageheader.template.php"); ?> 
<script type="text/javascript">
    document.title = 'Assignment 4.2: Playing Major Scales | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Assignment 4.2</h1>
                <p>Playing Major Scales</p>
	</div>
</div>


    <?php include("../../../static/templates/contentwrapperheader.template.php"); ?>



<!--<h1>Short Assignment: Playing Major Scales</h1>-->
<h2>Objectives</h2>
<p>
The goal of this assignment is to learn the following:
</p>
<ul>
<li>How to write actions to perform specific tasks</li>
<li>How to use action overloading</li>
</ul>
<h2>Overview</h2>
<p>
In this assignment, you will write a program to play major scales in a specific key, for a certain number of octaves. To accomplish this task, you will take advantage of a technique known as <strong>action overloading</strong>, which allows you to define multiple actions with the same name that differ in their parameters. Create a new project and name it <b>Assignment4_2</b>.
</p>
<h2>Task 1: Action Overloading</h2>
<p>
In Quorum, it is possible to have multiple actions with the same name, assuming the parameter list for each action is different. For example, in Quorum, it would be valid to write the following two actions:
</p>
<p><pre class="code"><code>
action SayHello
    SayHello(&quot;stranger&quot;)
end
action SayHello(text name)
    output &quot;Hello, &quot; + name
end
</code></pre></p>
<p>
There are two possible ways to call the <code>SayHello</code> action. The first way, writing <code>SayHello()</code>, will say "Hello, stranger," as Quorum recognizes that you wish to call the first action, as you did not specify any parameters. The second possible way to call the <code>SayHello</code> action is to provide a text parameter, such as <code>SayHello("Jeff")</code>. In this case, Quorum will call the second <code>SayHello</code> action, saying "Hello, Jeff."
</p>
<p>
Notice that in the body of the first <code>SayHelllo</code> action, you call the second <code>SayHello</code> action with a text parameter. This gives you a way to tell Quorum what the default values of parameters should be if you don't wish to specify them for every action call.
</p>
<h2>Major Scales</h2>
<p>
In music, a major scale consists of seven notes. The first note, called the <b>tonic</b>, is the starting note for the scale. After you play the seven notes of the scale, you typically play an eighth note, which is one octave higher than the tonic. An example of how to play a C major scale in Quorum is shown below. For this assignment, you will need to generalize this algorithm for use with an action that you define (see the Description section).
</p>
<p><pre class="code"><code>
use Libraries.Sound.Music
Music m
integer tonic = 60 // This is the note number for &quot;Middle C.&quot;
m:Play(tonic, 1) // This is &quot;Middle C,&quot; played for one second.
m:Play(tonic + 2, 1) // D
m:Play(tonic + 4, 1) // E
m:Play(tonic + 5, 1) // F
m:Play(tonic + 7, 1) // G
m:Play(tonic + 9, 1) // A
m:Play(tonic + 11, 1) // B
m:Play(tonic + 12, 1) // C (the eighth note -- one octave higher)
</code></pre></p>
<p>
The application should start by prompting the user for a starting note number. However, it is not required that the user enter a value. If the user does not enter a value, the result of the <code>input</code> action will be an empty string (<code>""</code>). If the user does enter a note value, prompt the user to enter the number of octaves to play. It is not required that the user enter a value for this prompt, either.
</p>
<p>
After the user has been prompted, the application should then call an action to play a major scale. When calling this action, the program must take advantage of action overloading. To do this, your program should define the following actions:
</p>
<ul><li><code>PlayMajorScale</code>, with no parameters.
</li><li><code>PlayMajorScale</code>, which takes an <code>integer</code> parameter specifying the starting note.
</li><li><code>PlayMajorScale</code>, which takes an <code>integer</code> parameter specifying the starting note, and an <code>integer</code> parameter specifying the number of octaves to play.
</li></ul><p>
In the <code>Main</code> action, you must call one of these three methods depending on the data the user has given. Note that <strong>all</strong> of these methods should be called somewhere in <code>Main</code>.
</p>

</div>
<?php include("../../../static/templates/pagefooter.template.php"); ?> 