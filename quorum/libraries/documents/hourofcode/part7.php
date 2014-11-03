<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Part 7 | Quorum Programming Language';
    
    //slide array
    var slideArray = [];
    slideArray[0] = "<h5>Hour of Code Review - Variables</h5>\n\
        <ul>\n\
        <li>Variables are containers for information.</li>\n\
        <li>To create a variable, declare the type of the variable followed by the name.</li>\n\
        <li>To assign a value into a variable, use the <code>=</code> operator.</li>\n\
        <li>There are four main types of variables in Quorum, each for specific kinds of data:<br>\n\
        &nbsp&nbsp&nbsp&nbsp<code>text</code> for any number of characters<br>\n\
        &nbsp&nbsp&nbsp&nbsp<code>integer</code> for positive and negative whole numbers<br>\n\
        &nbsp&nbsp&nbsp&nbsp<code>number</code> for values with a demical point<br>\n\
        &nbsp&nbsp&nbsp&nbsp<code>boolean</code> for true or false values.</li>\n\
        <li>When creating a variable name, always start it with a letter (usually lower case, but this is not required).</li>\n\
        <li>More information on variables can be found <a href=\"http://quorumlanguage.com/documents/syntax/types.php\">here</a>.</li>\n\
        </ul>";
    
    slideArray[1] = "<h5>Hour of Code Review - Output/Say Statements</h5>\n\
        <ul>\n\
        <li>An <code>output</code> statement will print text on the screen.</li>\n\
        <li>A <code>say</code> statements will instruct the computer to speak the expression aloud.</li>\n\
        <li>Note that some browsers do not support the <code>say</code> feature in Quorum's online mode.</li>\n\
        </ul>";
    
    slideArray[2] = "<h5>Hour of Code Review - If Statements</h5>\n\
        <ul>\n\
        <li>Use an <code>if</code> statement to have the computer decide whether or not to run certain code.</li>\n\
        <li>An <code>if</code> statement requires a condition to be evaluated (variable > 5, variable = 5, etc.) and an <code>end</code> statement to mark the end of the code block.</li>\n\
        <li>An <code>if</code> statement can optionally contain an <code>elseif</code> statement to test another condition or a default <code>else</code> statement (or both) to handle any other possibility not listed.</li>\n\
        <li>More information on <code>if</code> statements can be found <a href=\"http://quorumlanguage.com/documents/syntax/if.php\">here</a>.</li>\n\
        </ul>";
    
    slideArray[3] = "<h5>Hour of Code Review - Repeat</h5>\n\
        <ul>\n\
        <li>When we want to run a section of code repeatedly, we use a <code>repeat</code> statement to create a loop.</li>\n\
        <li>Like an <code>if</code> statement, a <code>repeat</code> statement requires a condition for stopping and an <code>end</code> statement.</li>\n\
        <li>We specify the condition for stopping the loop by using one of these formats: <br>\n\
        <code>repeat {number} times</code>,<br>\n\
        <code>repeat while {condition}</code> or <br>\n\
        <code>repeat until {condition}</code>.</li>\n\
        <li>More information on repeat loops can be found <a href=\"http://quorumlanguage.com/documents/syntax/repeat.php\">here</a>.</li>\n\
        </ul>";
    
    slideArray[4] = "<h5>Hour of Code Review - Actions</h5>\n\
        <ul>\n\
        <li>Actions are used to reduce the amount of duplicate code in a program and to make it easier to read.</li>\n\
        <li>To create an action, use the keyword <code>action</code> followed by the action's name, which usually starts with a capital letter.</li>\n\
        <li>The computer always starts executing a program at the <code>Main</code> action, but if the program has only one action, Quorum will automatically insert the program code into a <code>Main</code> action.</li>\n\
        <li>If a program has actions besides <code>Main</code>, the action <code>Main</code> must be defined somewhere in the program.</li>\n\
        <li>To execute an action at a particular point in the program, type the name of the action followed by parentheses.</li>\n\
        <li>More information on actions can be found <a href=\"http://quorumlanguage.com/documents/syntax/actions.php\">here</a></li>\n\
        </ul>";
    
    $(document).ready(function(){
//        $('#IDE-input').text('');
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