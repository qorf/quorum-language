<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Part 4 | Quorum Programming Language';
    
    //slide array
    var slideArray = new Array();
    slideArray[0] = "<h5>Control Structures: Repeat</h5><ul><li>Let's say we want to list some even numbers.  We could use output statements for each one, but what if we had 100 numbers to output or even 1000?  Sometimes it makes more sense to tell the computer to do the repetitive work for us.</li><li>We can accomplish this with the use of the word <code>repeat</code>.</li><li>Just like with the <code>if</code> statements when we told the computer where the end of the code block was, we must always use the word <code>end</code> to indicate where the section of code being repeated ends.</li><li>In this example, we will explore three different ways to control the repitition of code: <code>repeat times</code>, <code>repeat while</code> and <code>repeat until</code>.</li>";
    
    slideArray[1] = "<h5>Control Structures: Repeat Times</h5><ul><li>When it comes to repeating, sometimes we know how many times we want the code to run.</li><li>If we want to do something 5 times, we can just tell the computer <code>repeat 5 times</code> and then the computer knows to execute any lines of code in the repeat block 5 times.</li><span class=\"title\">Try it!</span><div class =\"task\"><p>Output five even numbers:<br><code>number evenNumber = 0</code><br><code>repeat 5 times</code><br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<code>output evenNumber</code><br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<code>evenNumber = evenNumber + 2</code><br><code>end</code></p></div></ul>";
    
    slideArray[2] = "<h5>Control Structures: Repeat While</h5><ul><li>Sometimes we only want to repeat while a certain condition is true.</li><li>We can get the same result as the other repeat statement since we know it should keep going while evenNumber is less than 10.</li><li>The way we denote \"less than\" in Qurorum is with the operator <code><</code>.</li><li>Notice that evenNumber will hold the value 10 at the end, but never gets output because our repeat condition is false.</li><span class=\"title\">Try it!</span><div class =\"task\"><p>Output five even numbers by changing the repeat statement to: <code>repeat while evenNumber < 10</code></p></div></ul>";
    
    slideArray[3] = "<h5>Control Structures: Repeat Until</h5><ul><li>When we know exactly the condition(s) under which the repeat should stop, we can use <code>repeat until</code>.</li><li>Whereas <code>repeat while</code> stops once the given condition is false, <code>repeat until</code>, stops once the given condition is true.</li><li>Following the other repeat examples, we can get the equivalent output by telling our program to repeat until evenNumber hits 10.</li><span class=\"title\">Try it!</span><div class =\"task\"><p>Output five even numbers by changing the repeat statement to: <code>repeat until evenNumber = 10</code></p></div></ul>";
    
    slideArray[4] = "<h5>Control Structures: Additional Information</h5><ul><li>More documentation on <code>repeat</code> can be found <a href=\"http://quorumlanguage.com/documents/syntax/repeat.php\">here</a>.</li></ul>";
    
    $(document).ready(function(){
        $('#IDE-input').text('number evenNumber = 0\nrepeat 5 times\n        output evenNumber\n        evenNumber = evenNumber + 2\nend');
    });
</script>
<?php include("slideshow.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container" role="banner">
		<h1>Hour of Code: Part 4</h1>
                <p>Clothing Picker</p>
	</div>
</div>
  <?php include("../../static/templates/contentwrapperheader.template.php"); ?>

      <?php include("sidebar.php"); ?><div id="content-box"><div id="slide-navigation"></div>
          <div id="slide-box-wrapper" role="description" aria-live="polite"><button id="leftArrow" class="leftArrow-disabled" aria-hidden="true" aria-label="Previous Slide"></button><div id ="slide-box"></div></div><button id="rightArrow" aria-label="Next Slide"></button>
</div><?php include ("../../ide.php"); ?><div id="video-container">
    <iframe width="946" height="710" src="//www.youtube.com/embed/j4Bjfu1TaEE?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

<?php require_once("../../static/templates/pagefooter.template.php"); ?>