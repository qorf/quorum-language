<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Part 4 | Quorum Programming Language';
    
    //slide array
    var slideArray = new Array();
    slideArray[0] = "<h5>Putting It All Together</h5><ul><li>We've learned quite a bit up to this point.  In this exercise, we're going to use all of our past knowledge to make a program that can detect if a number is even or odd.</li><li>Our first step is to set our starting number and tell the computer which number we want to stop at.</li><span class=\"title\">Try it!</span><div class =\"task\"><p>Create a number variable called <code>testNumber</code> that holds a starting number of your choosing.<br>Next, set up a repeat statement using <code>repeat until</code> and tell the computer what number it should stop at.  Also, don't forget to <code>end</code> the repeat.</p></div></ul>";
    
    slideArray[1] = "<h5>Implementing the Logic: Part 1</h5><ul><li>Now that we have our repeat statement set up, we need to tell the computer what it should do while it's repeating.</li><li>The way we will decide if a number is even or odd is with the modulus (%) operator</li><li>Since the modulus operator gives us the remainder from diving two numbers, and any even number is divisible by 2 (meaning it has a remainder of 0), then we can use the modulus operator to tell if a number is even or odd.</li><span class=\"title\">Try it!</span><div class =\"task\"><p>Create an if statement using the condition <code>if testNumber % 2 = 0</code>.<br>Also, don't forget to <code>end</code> the if statement</p></div></ul>";
    
    slideArray[2] = "<h5>Implementing the Logic: Part 2</h5><ul><li>We've created our condition for even numbers, meaning anything that makes it through is guaranteed to be even.</li><li>Of course, if a number is not even then we know it is always an odd number instead, so we can use the conditionless else statement to know when we have an odd number.</li><span class=\"title\">Try it!</span><div class =\"task\"><p>In between the <code>if</code> statement to catch even numbers and the <code>end</code> of it, place an <code>else</code> clause to catch the odd numbers.<br>Also be sure to add 1 to <code>currentNumber</code> between the if's <code>end</code> and the repeat's <code>end</code>, otherwise the computer will repeat forever!</p></div></ul>";
    
    slideArray[3] = "<h5>Being Creative</h5><ul><li>Since we now know how to check a number's parity (whether it's even or odd), we can proceed to do whatever we want with the even numbers and the odd numbers.</li><li>One thing that may be useful is to output whether the number is even or odd.  On the next slide there's an example of the code for doing just that.</li><li>Feel free to add or change code and experiment for yourself!</li></ul>";
    
    slideArray[4] = "<h5>Example Code</h5><p class =\"code\">number testNumber = 0<br>repeat until testNumber = 10<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspif testNumber % 2 = 0<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspoutput testNumber + \" is even\"<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspelse<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspoutput testNumber + \" is odd\"<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsptestNumber = testNumber + 1<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspend<br>end</p>";
    
    $(document).ready(function(){
        $('#IDE-input').text('number testNumber = 0\nrepeat until testNumber = 10\n        if testNumber % 2 = 0\n                output testNumber + \" is even\"\n        else\n                output testNumber + \" is odd\"\n        testNumber = testNumber + 1\n        end\nend');
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