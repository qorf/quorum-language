<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Part 2 | Quorum Programming Language';
    
    //slide array
    var slideArray = new Array();
    slideArray[0] = "<h5 role=\"heading\">Number Variables</h5><ul><li>Another type of variable used often in programming is a <code>number</code>.</li><li>A number variable holds numeric values such as 42 or 8.3439</li><li>Unlike text variables, we never need double quotes when storing a number.</li><span class=\"title\">Try it!</span><div class =\"task\">Create a number variable with the following code: <code>number pi = 3.14159</code>.</div></ul>";
    
    slideArray[1] = "<h5 role=\"heading\">Using Number Variables: Arithmetic</h5><ul><li>With number variables, we can do calculations on whatever we have stored in them.</li><li>For example, if we make another variable that holds the radius of a circle, then we can calculate its area.</li><li>In this case, we will need to multiply using the * operator.</li><span class=\"title\">Try it!</span><div class =\"task\">Create a variable to hold your circle's radius: <code>number radius = 3</code> and then calculate the area of a circle and store it in a new variable: <code>number area = pi * radius * radius</code></div></ul>";
    
    slideArray[2] = "<h5 role=\"heading\">Say Statements</h5><ul><li>With Quorum, we can tell the computer to say things out loud for us.  The way we do this is with the word <code>say</code> followed by whatever we want said.</li><li>Like with output statements, we can have the computer tell us what is stored in a variable and we can also have it say something we have typed in specifically.</li><li><span class = \"bold\">Note:</span> Say statements may not work with all web browsers.</li><span class=\"title\">Try it!</span><div class =\"task\">Tell the computer to say the result of the area calculation: <code>say \"The area is\" + area</code></div></ul>"; //operators, concat
    
    slideArray[3] = "<h5 role=\"heading\">Additional Information</h5><ul><li>There are 5 useful operators when using numbers: + (addition), - (subtraction), * (multiplication), / division and % (modulus).</li><li>The modulus operator is used for finding the remainder of a division operation, for example: 15 % 10 will give us a result of 5.  A good example for the use of modulus would be to determine if number is even or odd, since any even number % 2 = 0.</li><li>When using say statements, or output statements, you can concatenate (add) things to be said together like in the previous slide with the + operator.</li></ul>";
    
    $(document).ready(function(){
        $('#IDE-input').text('number pi = 3.14159\nnumber radius = 3\nnumber area = pi * radius * radius\nsay "The area is" + area');
    });
</script>
<?php include("slideshow.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container" role="banner">
		<h1>Hour of Code: Part 2</h1>
                <p>Sayin' Stuff</p>
	</div>
</div>
  <?php include("../../static/templates/contentwrapperheader.template.php"); ?>

      <?php include("sidebar.php"); ?><div id="content-box"><div id="slide-navigation"></div>
          <div id="slide-box-wrapper" role="description" aria-live="polite"><button id="leftArrow" class="leftArrow-disabled" aria-hidden="true" aria-label="Previous Slide"></button><div id ="slide-box"></div></div><button id="rightArrow" aria-label="Next Slide"></button>
</div><?php include ("../../ide.php"); ?><div id="video-container">
    <iframe width="946" height="710" src="//www.youtube.com/embed/jiBoy-YndQw?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

<?php require_once("../../static/templates/pagefooter.template.php"); ?>