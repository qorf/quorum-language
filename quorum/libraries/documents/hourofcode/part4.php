<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Part 4 | Quorum Programming Language';
    
    //slide array
    var slideArray = new Array();
    slideArray[0] = "<h5>Control Structures: Repeat</h5>\n\
        <ul>\n\
        <li>If we want the computer to repeat a task multiple times, we can use a loop in our program.  This is usually a lot less work than typing the instruction repeatedly and more flexible because we can have the instructions executed a variable number of times.</li>\n\
        <li>In Quorum, we create a loop using the keyword <code>repeat</code>.</li>\n\
        <li>We mark the end of a loop in the same way we marked the end of an <code>if</code> statement, by using the keyword <code>end</code>.</li>\n\
        <li>In this example, we will explore three different ways to control loops using the statements: <code>repeat {number} times</code>, <code>repeat while {condition}</code> and <code>repeat until {condition}</code>.</li>\n\
        </ul><br><p>Move on to the next slide when you are ready.<p>";
    
    slideArray[1] = "<h5>Control Structures: Repeat Times</h5>\n\
        <ul>\n\
        <li>The most basic type of loop involves just telling the computer how many times to repeat something.</li>\n\
        <li>In Quorum, if we want to do something 5 times, we can type: <code>repeat 5 times</code> or if we have a numeric variable, we can place that in the \"5\" position like: <code>repeat x times</code>.</li>\n\
        <span class=\"title\">Try it!</span>\n\
        <div class =\"task\"><p>Output five even numbers:<br><code>number evenNumber = 2</code><br>\n\
        <code>repeat 5 times</code><br>\n\
        &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<code>output evenNumber</code><br>\n\
        &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<code>evenNumber = evenNumber + 2</code><br>\n\
        <code>end</code>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp(Then Run your code)</p>\n\
        </div>\n\
        </ul>";
    
    slideArray[2] = "<h5>Control Structures: Repeat While</h5>\n\
        <ul>\n\
        <li>An alternative type of loop is to repeat a block of code while a certain condition is met.</li>\n\
        <li>In Quorum we do this by using a <code>repeat while</code> statement. After the keyword <code>while</code>, we include a condition, just like we did with an <code>if</code> statement.</li>\n\
        <li>If we want to mimic the last example, we could just repeat while our variable is less than or equal to 10.</li>\n\
        <li>Note that the value of the variable will be 12, but the output statement is skipped when the repeat condition is false.</li>\n\
        <span class=\"title\">Try it!</span>\n\
        <div class =\"task\"><p>Output the even numbers again by changing line 2 to: <code>repeat while evenNumber <= 10</code>, then hit Run.</p></div>\n\
        </ul>";
    
    slideArray[3] = "<h5>Control Structures: Repeat Until</h5>\n\
        <ul>\n\
        <li>The final way of representing a loop in Quorum is to use a <code>repeat until</code> statement, which is very similar to the <code>repeat while</code> loop.</li>\n\
        <li>The difference is that the <code>repeat until</code> loop stops when a specific condition is met instead of repeating while a condition is met.</li>\n\
        <li>You can represent the same logical control with either format, so it is your choice which one you use, but sometimes one is more natural than the other.</li>\n\
        <span class=\"title\">Try it!</span>\n\
        <div class =\"task\"><p>Output the even numbers by changing line 2 to: <code>repeat until evenNumber > 10</code>, then hit Run</p></div>\n\
        </ul>";
    
    slideArray[4] = "<h5>Control Structures: Additional Information</h5>\n\
        <ul>\n\
        <li>More documentation on <code>repeat</code> can be found <a href=\"http://quorumlanguage.com/documents/syntax/repeat.php\">here</a>.</li>\n\
        </ul>";
    
    $(document).ready(function(){
//        $('#IDE-input').text('number evenNumber = 2\nrepeat 5 times\n        output evenNumber\n        evenNumber = evenNumber + 2\nend');
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