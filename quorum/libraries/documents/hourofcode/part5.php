<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Part 5 | Quorum Programming Language';
    
    //slide array
    var slideArray = new Array();
    slideArray[0] = "<h5>Putting It All Together</h5>\n\
        <ul>\n\
        <li>We've learned quite a bit up to this point and now it's time use all of our new skills to write a program that will detect if a number is even or odd.</li>\n\
        <li>We are going to test a range of numbers, so we need to loop from a starting number to a stopping number.</li>\n\
        <span class=\"title\">Try it!</span>\n\
        <div class =\"task\"><p>First create a number variable called <code>testNumber</code> and set it to your starting number.<br>Next, set up a repeat loop using <code>repeat until</code> and for the condition specify the number to stop at.<br>Finally, don't forget to include <code>end</code> to mark the end of the loop. Move on to the next slide when you're done.</p></div>\n\
        </ul>";
    
    slideArray[1] = "<h5>Implementing the Logic: Part 1</h5>\n\
        <ul>\n\
        <li>Now that we have the <code>repeat</code> loop set up, we need to complete the code block to repeat inside the loop.</li>\n\
        <li>We will use the modulus operator <code>mod</code> to determine if a number is even or odd. Remember that the modulus operator returns the remainder when dividing two numbers, so any even number divided by two will have a remainder of 0 and any odd number will have a remainder of 1.</li>\n\
        <span class=\"title\">Try it!</span>\n\
        <div class =\"task\"><p>Create an <code>if</code> statement inside the <code>repeat</code> loop checking the modulus of the variable:<br><code>if testNumber mod 2 = 0</code><br><code>end</code> <code>end</code>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp(Then move to the next slide).</div>\n\
        </ul>";
    
    slideArray[2] = "<h5>Implementing the Logic: Part 2</h5>\n\
        <ul>\n\
        <li>Our <code>if</code> statement is now set up for even numbers.  If we want alternative instructions to run if the number is odd, we need some kind of <code>else</code> condition as well.</li>\n\
        <li>Since there are only two possibilities (even or odd), we can use the simple default <code>else</code> statement here.</li>\n\
        <span class=\"title\">Try it!</span>\n\
        <div class =\"task\"><p>Before the <code>end</code> in the <code>if</code> statement, insert a line with <code>else</code> to set up the block for odd numbers.<br>\n\
Now, to increment our testing variable by one on each pass of the loop, insert a line <code>testNumber = testNumber + 1</code> just after the end of the <code>if</code> block.&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp(Then move to the next slide)</p></div>\n\
        </ul>";
    
    slideArray[3] = "<h5>Being Creative</h5>\n\
        <ul>\n\
        <li>So now we have a logical code structure set up to execute different instructions if a number is even or if it is odd inside a repeat loop that will test a range of numbers.</li>\n\
        <li>All we need to do now is write some instructions inside the code blocks!</li>\n\
        <li>For this example, let's just use an <code>output</code> statement to print a message depending on whether the number is even or odd. You can also try a <code>say</code> statement if you want to hear it. Try something like <code>output testNumber + \" is even.\"</code> in the first block and then Run it.</li>\n\
        <li>The next slide has the full code for this section...experiment with other ideas yourself.</li>\n\
        </ul>";
    
    slideArray[4] = "<h5>Example Code</h5><p class =\"code\">number testNumber = 0<br>\n\
        repeat until testNumber = 10<br>\n\
        &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspif testNumber mod 2 = 0<br>\n\
        &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspoutput testNumber + \" is even\"<br>\n\
        &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspelse<br>\n\
        &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspoutput testNumber + \" is odd\"<br>\n\
        &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspend<br>\n\
        &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsptestNumber = testNumber + 1<br>\n\
        end</p>";
    
    $(document).ready(function(){
//        $('#IDE-input').text('number testNumber = 0\nrepeat until testNumber = 10\n    if testNumber mod 2 = 0\n        output testNumber + \" is even\"\n    else\n        output testNumber + \" is odd\"\n    end\n    testNumber = testNumber + 1\nend');
    });
</script>
<?php include("slideshow.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container" role="banner">
		<h1>Hour of Code: Part 5</h1>
                <p>Choose My Clothing!</p>
	</div>
</div>
  <?php include("../../static/templates/contentwrapperheader.template.php"); ?>

      <?php include("sidebar.php"); ?><div id="content-box"><div id="slide-navigation"></div>
          <div id="slide-box-wrapper" role="description" aria-live="polite"><button id="leftArrow" class="leftArrow-disabled" aria-hidden="true" aria-label="Previous Slide"></button><div id ="slide-box"></div></div><button id="rightArrow" aria-label="Next Slide"></button>
</div><?php include ("../../ide.php"); ?><div id="video-container">
    <iframe width="946" height="710" src="//www.youtube.com/embed/xZJCkKSXmq4?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

<?php require_once("../../static/templates/pagefooter.template.php"); ?>