<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Part 6 | Quorum Programming Language';
    
    //slide array
    var slideArray = new Array();
    slideArray[0] = "<h5>Actions</h5><ul><li>Sometimes when we're programming, we may find ourselves using the same code over and over again in different parts of our program.</li><li>With actions, we can eleminate the duplicate code.</li><li>In a nutshell, actions hold code that can be executed from anywhere in your program, whenever you need it.</li><li>In fact, we have already been using an action all along called <code>Main</code>.  Quorum automatically generates this for us if we don't need to use any other actions (like in the previous excercises).</li><li>The computer always starts executing our programs from the <code>Main</code> action.</li></ul>";
    slideArray[1] = "<h5>Creating Actions: Part 1</h5><ul><li>We create actions by starting a line with the word <code>action</code> followed by the name of the action.  Unlike variable names, action names should start with a capital letter.</li><li>Since actions can contain however many lines of code we want in them, they will also need an <code>end</code> so the computer knows where the action stops.</li><span class=\"title\">Try it!</span><div class =\"task\"><p>Create the Main action by typing <code>action Main</code> with an appropriate <code>end</code> below it.</p></div></ul>";
    slideArray[2] = "<h5>Creating Actions: Part 2</h5><ul><li>Now that the Main action has been created, we can type all of the code we're used to from before inside of it.</li><li>This also means we can start creating more actions to perform a set of instructions for us at any point.</li><span class=\"title\">Try it!</span><div class =\"task\"><p>Create an action called <code>OutputHello</code> underneath the Main action (below the <code>end</code>).</p></div></ul>";
    slideArray[3] = "<h5></h5><ul><li></li><span class=\"title\">Try it!</span><div class =\"task\"><p></p></div></ul>";
    slideArray[4] = "<h5></h5><ul><li></li><span class=\"title\">Try it!</span><div class =\"task\"><p></p></div></ul>";
    
    $(document).ready(function(){
        $('#IDE-input').text('');
    });
</script>
<?php include("slideshow.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container" role="banner">
		<h1>Hour of Code: Part 6</h1>
                <p>Mutate the Bugs</p>
	</div>
</div>
  <?php include("../../static/templates/contentwrapperheader.template.php"); ?>

      <?php include("sidebar.php"); ?><div id="content-box"><div id="slide-navigation"></div>
          <div id="slide-box-wrapper" role="description" aria-live="polite"><button id="leftArrow" class="leftArrow-disabled" aria-hidden="true" aria-label="Previous Slide"></button><div id ="slide-box"></div></div><button id="rightArrow" aria-label="Next Slide"></button>
</div><?php include ("../../ide.php"); ?><div id="video-container">
    <iframe width="946" height="710" src="//www.youtube.com/embed/qIvXHEhIQI8?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

<?php require_once("../../static/templates/pagefooter.template.php"); ?>