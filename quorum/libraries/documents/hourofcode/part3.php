<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Part 3 | Quorum Programming Language';
    
    //slide array
    var slideArray = new Array();
    
    slideArray[0] = "<h5 role=\"heading\">Boolean Variables</h5><ul><li>A <code>boolean</code> variable is a special type of variable that contains one of two possible words: <code>true</code> or <code>false</code>.</li><li>Alone, booleans don't do much, but they are a powerful tool when working with control structures as we will soon see.</li><span class=\"title\">Try it!</span><div class =\"task\">Make a boolean variable: <code>boolean sayStatement = true</code> and a text variable with a whatever you want: <code>text greeting = \"I live in a giant bucket!\"</code> (we will use this later).</div></ul>";
    
    slideArray[1] = "<h5 role=\"heading\">Control Structures: If (Structure)</h5><ul><li>Suppose we want the computer to output a statement sometimes, but say the same statement at other times.  We would then need to have the computer make a decision.  In Quorum, we can do this by using an <code>if</code> statement.</li><li>The way we do this is by starting the statement with the word if, followed by some sort of condition.</li><li>With every if statement, we also need to tell the computer what it should do and where it should <code>end</code>.</li><span class=\"title\">Try it!</span><div class =\"task\"><p>Create an if statement using the boolean from the last task: <code>if sayStatement = true</code> and then type <code>end</code> on a line below.</p></div></ul>";
    
    slideArray[2] = "<h5 role=\"heading\">Control Structures: If (Logic)</h5><ul><li>When determining whether to execute code or not within the if block (from the <code>if</code> to the <code>end</code>) the computer checks the condition to the right of the word <code>if</code> and evaluates whether it is true or false.</li><li>For example, if you typed <code>if 1 + 1 = 4</code> then the computer would say this condition is false and ignore the code inside of your if block.</li><li>This is where booleans come in, because they are already defined as either <code>true</code> or <code>false</code>.</li><span class=\"title\">Try it!</span><div class =\"task\"><p>Tell the computer to do something inside of the <code>if</code> block, such as: <code>say greeting</code></p></div></ul>";
    
    slideArray[3] = "<h5 role=\"heading\">Control Structures: If (elseif)</h5><ul><li>Let's say the initial if statement isn't true, but we want to do something else under different conditions.  The way we can do this is with the combined word <code>elseif</code>, and it works just like another if statement.</li><li>With the elseif, we can specify as many more conditions and what code should run under those conditions.</li><span class=\"title\">Try it!</span><div class =\"task\"><p>Continuing from the last example, under the line <code>say greeting</code> specify another condition and what code should run in that circumstance: <code>elseif sayStatement = false</code><br><code>output greeting</code></p></div></ul>";
    
    slideArray[4] = "<h5 role=\"heading\">Control Structures: If (else)</h5><ul><li>Finally, there is one last piece to an if statement.  When no condition has been met, you can still tell the computer to do something with the word <code>else</code>.</li><li>Like with <code>elseif</code>, it's not always necessary to have this part in your if block.</li><li>If the computer gets to the <code>else</code> part, it knows no other condition was met and automatically executes code until it hits the word <code>end</code>.</li><li>If you've been following along with the examples, we've already accounted for sayStatement = true and sayStatement = false.  As such, the computer would never get to the <code>else</code> statement within our if block.</li></ul>";
    
    slideArray[5] = "<h5>If Statements: Additional Information</h5><ul><li>More documentation on <code>if</code> statements can be found <a href=\"http://quorumlanguage.com/documents/syntax/if.php\">here</a>.</li></ul>";
    
    $(document).ready(function(){
        $('#IDE-input').text('boolean sayStatement = true\ntext greeting = "I live in a giant bucket"\nif sayStatement\n        say greeting\nelseif sayStatement = false\n        output greeting\nend');
    });
</script>
<?php include("slideshow.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container" role="banner">
		<h1>Hour of Code: Part 3</h1>
                <p>This or That</p>
	</div>
</div>
  <?php include("../../static/templates/contentwrapperheader.template.php"); ?>

      <?php include("sidebar.php"); ?><div id="content-box"><div id="slide-navigation"></div>
          <div id="slide-box-wrapper" role="description" aria-live="polite"><button id="leftArrow" class="leftArrow-disabled" aria-hidden="true" aria-label="Previous Slide"></button><div id ="slide-box"></div></div><button id="rightArrow" aria-label="Next Slide"></button>
</div><?php include ("../../ide.php"); ?><div id="video-container">
    <iframe width="946" height="710" src="//www.youtube.com/embed/EKOXMt2FeTc?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

<?php require_once("../../static/templates/pagefooter.template.php"); ?>