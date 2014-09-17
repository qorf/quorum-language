<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Part 1 | Quorum Programming Language';
    
    //slide array
    var slideArray = new Array();
    slideArray[0] = "<h5 role=\"heading\">Variables</h5><ul><li>In programming, a <code>variable</code> is a container to store information that can be used at a later time.</li><li>There are many types of variables that will hold different kinds of information.</li><li>For this example, we will create a <code>text</code> variable, which can hold anything you want to type in.</li></ul>";
    
    slideArray[1] = "<h5 role=\"heading\">Creating Variables</h5><ul><li>To create a variable in Quorum, we start by telling the computer what type of variable we want to make, in this case it's a text variable, followed by a space and a name for the variable.</li><li>When naming a variable, we start with a lowercase letter followed by more letters or numbers.</li><span class=\"title\">Try it!</span><div class =\"task\">Create a text variable named dna by typing the code: <code>text dna</code>.</div></ul>";
    
    slideArray[2] = "<h5 role=\"heading\">Storing Text in Variables: Literals</h5><ul><li>We have made a variable, but we haven't stored any information in it yet.</li><li>To put something into a variable we use the <code>=</code> operator followed by the information we want stored.</li><li>For this example, let's store the text GATTACA</li><li>In this example, we want double quotes around the information we're storing to tell the computer we want to store exactly what we typed.</li><span class=\"title\">Try it!</span><div class =\"task\">Store the text GATTACA to the dna variable: <code>text dna = \"GATTACA\"</code>.</div></ul>";
    
    //rewrite the example to include a literal string concatenate with a variable holding a string
    slideArray[3] = "<h5 role=\"heading\">Using Variables for Output</h5><ul><li>Now that we've stored something in our variable, we can refer to its contents whenever we need.</li><li>To demonstrate this, let's grab what we have stored in our variable and display it to the screen.</li><li>In Quorum, to output information to the screen we type the word <code>output</code> followed by a space and then whatever we want to show up on screen.</li><span class=\"title\">Try it!</span><div class =\"task\">Output the contents of the dna variable by typing <code>output dna</code> on a new line and then run it.</div></ul>";
    
    slideArray[4] = "<h5 role=\"heading\">Storing Text in Variables: From Variables</h5><ul><li>We've already stored directly typed text into a variable, which required double quotes, but what if we want to store text that already exists in another variable?</li><li>To store existing text into a text variable, we tell the computer to store whatever is inside another variable.</li><li>The key difference here is that we don't use double quotes, which tells the computer we want to store existing text.</li><span class=\"title\">Try it!</span><div class =\"task\">Assign the contents of the dna variable to a new variable: <code>text dna2 = dna</code> and then output it on a new line: <code>output dna2</code>.</div></ul>";
    
    slideArray[5] = "<h5 role=\"heading\">Additional Information Regarding Variables</h5><ul><li>Names are case sensitive: dna is not the same as DNA.</li><li>When storing something in a text variable, be sure to use double quotes when necessary.</li><li>Because variables are just containers, if we were to go back and change what was stored inside of dna we could still use the same output statement and get a different result on screen.  If you've been following along, you can test this by changing what is initally stored in the dna variable.</li></ul>";

$(document).ready(function(){
    $('#IDE-input').text('text dna = "GATTACA"\noutput dna\ntext dna2 = dna\noutput dna2');
});
</script>
<?php include("slideshow.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container" role="banner">
		<h1>Hour of Code: Part 1</h1>
                <p>Got a New Gig</p>
	</div>
</div>
  <?php include("../../static/templates/contentwrapperheader.template.php"); ?>

      <?php include("sidebar.php"); ?><div id="content-box"><div id="slide-navigation"></div>
          <div id="slide-box-wrapper" role="description" aria-live="polite"><button id="leftArrow" class="leftArrow-disabled" aria-hidden="true" aria-label="Previous Slide"></button><div id ="slide-box"></div></div><button id="rightArrow" aria-label="Next Slide"></button>
</div><?php include("../../ide.php"); ?><div id="video-container">
    <iframe width="946" height="710" src="//www.youtube.com/embed/pwRc8bjoACw?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

<?php require_once("../../static/templates/pagefooter.template.php"); ?>