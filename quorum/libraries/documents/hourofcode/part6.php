<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Part 6 | Quorum Programming Language';
    
    //slide array
    var slideArray = [];
    slideArray[0] = "<h5>Actions</h5>\n\
        <ul>\n\
        <li>Often times inside a program, we want to run the same code sections many times in different places. We usually want to eliminate this duplicate code to save time, reduce errors and make things easier to change.</li>\n\
        <li>We do this by writing procedures that we call whenever we want code to run.  In Quorum, these are called actions.</li>\n\
        <li>You didn't realize it, but we have already been using an action called <code>Main</code>. The computer always starts running our program from our <code>Main</code> action.  We haven't had to use it so far, because Quorum automatically puts our code inside a <code>Main</code> action if we don't use any other actions.</li>\n\
        <li>Now that we are going to use other actions though, we always need to include it.</li>\n\
</ul>";
    
    slideArray[1] = "<h5>Creating Actions: Part 1</h5>\n\
        <ul>\n\
        <li>In Quorum to create an action, we use the keyword <code>action</code> followed by the name of the action.  We usually capitalize the first letter of an action name, but it is not required. Naming rules for actions are the same as variables, it must start with a letter.</li>\n\
        <li>Since we are going to have a code block inside the action, we also need to include an <code>end</code> to mark where the action ends.</li>\n\
        <span class=\"title\">Try it!</span><div class =\"task\"><p>Lets start a new program by creating a Main action:<br>\n\
        <code>action Main</code><br>\n\
        <code>end</code>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp(then move to the next slide)</p>\n\
        </div>\n\
        </ul>";
    
    slideArray[2] = "<h5>Creating Actions: Part 2</h5>\n\
        <ul>\n\
        <li>You can put any type of code that you've learned so far inside the <code>Main</code> action or call other actions from it.</li>\n\
        <li>We are going to create another action now that we will call from the <code>Main</code> action.</li>\n\
        <li>To do this, just create a new action after the <code>end</code> statement of the <code>Main</code> action.</li>\n\
            <span class=\"title\">Try it!</span><div class =\"task\"><p>Create an action called <code>PrintMsg</code> after the <code>Main</code> action:<br>\n\
        <code>action PrintMsg</code>.<br>\n\
        <code>end</code>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp(then move to the next slide).</p></div>\n\
        </ul>";
    
    slideArray[3] = "<h5>Creating Actions: Part 3</h5>\n\
        <ul>\n\
        <li>At the moment, we have two actions in our program, but no code to run inside the actions.  We'll learn how to call an action in a moment, but first, let's write some code inside the <code>PrintMsg</code> action. </li>\n\
        <li>To keep our example simple, we are going to write a simple output message to the screen.</li>\n\
        <span class=\"title\">Try it!</span>\n\
        <div class =\"task\"><p>Insert an <code>output</code> statement inside the <code>PrintMsg</code> action:<br>\n\
        <code>output \"Programming in Quorum is fun!!!\"</code><br>(then move to the next slide)</div></p>\n\
        </ul>";
    
    slideArray[4] = "<h5>Calling Actions</h5>\n\
        <ul>\n\
        <li>Now, in order to complete this program we need to call our <code>PrintMsg</code> action from our <code>Main</code> action so that it will execute.</li>\n\
        <li>In Quorum, this is very easy to do, we just insert a line of code in the <code>Main</code> action that redirects the computer to the other action.  We do this adding the line <code>PrintMsg()</code> to the <code>Main</code> action.</li>\n\
        <li>The parentheses are necessary after the action name to notify the computer that it should run an action.</li>\n\
        <span class=\"title\">Try it!</span>\n\
        <div class =\"task\"><p>Call <code>PrintMsg</code> from <code>Main</code>:<br>\n\
        <code>PrintMsg()</code>nbsp&nbsp&nbsp&nbsp&nbsp&nbsp(then Run your code).</p></div>\n\
        </ul>";
    
    slideArray[5] = "<h5>Actions: More Information</h5>\n\
        <ul>\n\
        <li>Here is our full example:<br>\n\
        <code>action Main</code><br>\n\
        <code>&nbsp&nbsp&nbsp&nbspPrintMsg()</code><br>\n\
        <code>end</code><br><br>\n\
        <code>action PrintMsg</code><br>\n\
        <code>&nbsp&nbsp&nbsp&nbspoutput \"Programming in Quorum is Fun!!!\"</code><br>\n\
        <code>end</code><br>\n\</li>\n\<li>Actions are an important and useful concept in programming, but we've only covered the basics so far. You can also do things like pass variables to an action and return values from an action.</li>\n\
        <li>For more information on actions, click <a href=\"http://quorumlanguage.com/documents/syntax/actions.php\">here</a></li>\n\
        </ul>";
    
    $(document).ready(function(){
//        $('#IDE-input').text('');
    });
</script>
<?php include("slideshow.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container" role="banner">
		<h2 id="pageTitle">Hour of Code: Part 6</h2>
                <h4 class="sub-heading">Mutate the Bugs</h4>
	</div>
</div>
  <?php include("../../static/templates/contentwrapperheader.template.php"); ?>

      <?php include("sidebar.php"); ?><div id="content-box"><div id="slide-navigation"></div>
          <div id="slide-box-wrapper" role="description" aria-live="polite"><button id="leftArrow" class="leftArrow-disabled" aria-hidden="true" aria-label="Previous Slide"></button><div id ="slide-box"></div></div><button id="rightArrow" aria-label="Next Slide"></button>
</div><?php include ("../../ide.php"); ?><div id="video-container">
    <iframe width="946" height="571" src="//www.youtube.com/embed/qIvXHEhIQI8?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

<?php require_once("../../static/templates/pagefooter.template.php"); ?>
