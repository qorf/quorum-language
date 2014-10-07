<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Part 7 | Quorum Programming Language';
    
    //slide array
    var slideArray = new Array();
    slideArray[0] = "<h5>Hour of Code Review - Variables</h5><ul><li>Variables are just containers for information.</li><li>To create a variable, we must declare the type of variable and give it a name.</li><li>There are many type of variables, each for the specific kinds of data we want to store in them.</li><li>We can use <code>text</code>, <code>integer</code>, <code>number</code> and <code>boolean</code> variables when needed.</li><li>When creating a variable name, start with a lower-case letter.</li><li>More information on variables can be found <a href=\"http://quorumlanguage.com/documents/syntax/types.php\">here</a>.</li></ul>";
    
    slideArray[1] = "<h5>Hour of Code Review - Output/Say Statements</h5><ul><li>An <code>output</code> statement will produce text on the screen for us to read.</li><li>On the other hand, <code>say</code> statements will speak the words aloud.</li><li>Some browsers do not support the <code>say</code> feature in Quorum's online mode.</li></ul>";
    
    slideArray[2] = "<h5>Hour of Code Review - If Statements</h5><ul><li>With <code>if</code> statements, we can let the computer decide whether or not to run certain code.</li><li>Every <code>if</code> statement requires a condition (variable > 5, variable = 5, etc.) and an <code>end</code>.</li><li>Within an <code>if</code> statement, we can type <code>elseif</code> to list another condition under which the computer should run code.  After that, we can then type the word <code>else</code> which tells the computer, \"None of the elseif conditions were met, so just run this code instead.\"</li><li>More information on if statements can be found <a href=\"http://quorumlanguage.com/documents/syntax/if.php\">here</a>.</li></ul>";
    
    slideArray[3] = "<h5>Hour of Code Review - Repeat</h5><ul><li>When we want to run some code multiple times, we can use a <code>repeat</code> statement.</li><li>Each <code>repeat</code> statement requires a condition for stopping and an <code>end</code>.</li><li>We state the condtion for stopping by using <code>repeat times</code>, <code>repeat while</code> or <code>repeat until</code>.</li><li>More information on repeats can be found <a href=\"http://quorumlanguage.com/documents/syntax/repeat.php\">here</a>.</li></ul>";
    
    slideArray[4] = "<h5>Hour of Code Review - Actions</h5><ul><li>We use actions to reduce the amount of duplicate code in our programs.</li><li>To create one, we type <code>action</code> followed by the action's name, which should start with a capital letter.</li><li>Remember that if we want to create our own actions, we must also declare the action <code>Main</code> somewhere in our program, because the computer always starts there.</li><li>To execute an action, we type the name of the action followed by parentheses.</li><li>More information on actions can be found <a href=\"http://quorumlanguage.com/documents/syntax/actions.php\">here</a></li></ul>";
    
    $(document).ready(function(){
        $('#IDE-input').text('');
    });
</script>
<?php include("slideshow.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container" role="banner">
		<h1>Hour of Code: Part 7</h1>
                <p>The End of the World</p>
	</div>
</div>
  <?php include("../../static/templates/contentwrapperheader.template.php"); ?>

      <?php include("sidebar.php"); ?><div id="content-box"><div id="slide-navigation"></div>
          <div id="slide-box-wrapper" role="description" aria-live="polite"><button id="leftArrow" class="leftArrow-disabled" aria-hidden="true" aria-label="Previous Slide"></button><div id ="slide-box"></div></div><button id="rightArrow" aria-label="Next Slide"></button>
</div><?php include ("../../ide.php"); ?><div id="video-container">
    <iframe width="946" height="710" src="//www.youtube.com/embed/6G5Vou8oojQ?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

<?php require_once("../../static/templates/pagefooter.template.php"); ?>