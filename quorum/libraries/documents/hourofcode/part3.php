<?php require_once("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Part 3 | Quorum Programming Language';
    
    //slide array
    var slideArray = [];
    
    slideArray[0] = "<h5 role=\"heading\">Boolean Variables</h5>\n\
        <ul>\n\
        <li>A <code>boolean</code> variable is a special type of variable that contains one of two possible values: <code>true</code> or <code>false</code>.</li>\n\
        <li>By themselves, <code>boolean</code> variables don't seem to do too much, but they are a powerful tool when working with control structures, as we will soon see.</li>\n\
        <span class=\"title\">Try it!</span>\n\
        <div class =\"task\">\n\
            <ul>\n\
            <li>Make a <code>boolean</code> variable and assign it a value of true: <code>boolean sayStatement = true</code>.</li>\n\
            <li>Create a <code>text</code> variable named \"greeting\" and assign it a string you like in double quotes: <code>text greeting = \"I live in a giant bucket!\"</code></li>\n\
            <li>We will use these variables in a couple slides.</li>\n\
            </ul></div>\n\
        </ul>";
    
    slideArray[1] = "<h5 role=\"heading\">Control Structures: If (Structure)</h5>\n\
        <ul>\n\
        <li>One powerful ability of a computer is to evaluate a condition and make a decision about which instructions to execute. These decisions can be made with a simple conditional statement, which in Quorum uses the keyword <code>if</code>.</li>\n\
        <li>An <code>if</code> statement has three parts: the keyword <code>if</code> followed by a condition, followed by the block of code to execute if the condition is <code>true</code>, followed by <code>end</code>.</li>\n\
        <li><code>boolean</code> variables are often used to specify the condition.</li>\n\
        <span class=\"title\">Try it!</span>\n\
        <div class =\"task\"><p>Create an <code>if</code> statement using the <code>boolean</code> from the last slide: <code>if sayStatement = true</code> and then type <code>end</code> on a new line and then move to the next slide.</p>\n\</div>\n\
        </ul>";
    
    slideArray[2] = "<h5 role=\"heading\">Control Structures: If (Conditional Logic)</h5>\n\
        <ul>\n\
        <li>When determining whether to execute the block of code within the <code>if</code> statement the computer checks the condition part of the statement to see whether it is <code>true</code> or <code>false</code>.</li>\n\
        <li>You can specify the condition using a <code>boolean</code> variable or any other expression that evaluates to <code>true</code> or <code>false</code></li>\n\
        <li>For example, if you typed <code>if 1 + 1 = 4</code> the condition <code>1 + 1 = 4</code> evaluates to <code>false</code>, so the code is skipped.</li>\n\
        <li>If code is skipped, the computer jumps to the <code>end</code> keyword for the next instruction.</li>\n\
        <span class=\"title\">Try it!</span>\n\
        <div class =\"task\"><p>Inside the previous <code>if</code> block type: <code>say greeting</code> and then Run your code.</p></div>\n\
        </ul>";
    
    slideArray[3] = "<h5 role=\"heading\">Control Structures: If (elseif)</h5>\n\
        <ul>\n\
        <li>You can also specify an alternatve condition to evaluate if the condition in the first part of the <code>if</code> statement is <code>false</code> by using the keyword <code>elseif</code> before the <code>end</code>.</li>\n\
        <li>We can include as many <code>elseif</code> conditions as we want and include code blocks to execute in each case.</li>\n\
        <li>There is a single <code>end</code> statement at the conclusion.</li>\n\
        <span class=\"title\">Try it!</span>\n\
        <div class =\"task\"><p>Continuing the example from the last slide, insert a new condition before the line <code>end</code> and then a line containing the code to execute: <code>elseif sayStatement = false</code><br><code>output greeting</code> and then Run your code.</p></div>\n\
        </ul>";
    
    slideArray[4] = "<h5 role=\"heading\">Control Structures: If (else)</h5>\n\
        <ul>\n\
        <li>There is one other part of an <code>if</code> statement called the default condition, which will execute if no other condition in the statement is <code>true</code>.  In Quorum, this block is designated with the keyword <code>else</code></li>\n\
        <li>This statement is optional, just like <code>elseif</code>.</li>\n\
        <li>In our example, there are only two states: <code>true</code> and <code>false</code>, so there are no other possible conditions, although the <code>elseif</code> line could be converted to an <code>else</code> without a second condition.</li>\n\
        <li>In other cases, you might have a list of conditions like <code>if x = 1</code> {code block}<br>\n\
        <code>elseif x = 2</code> {code block}<br>\n\
        <code>else</code> {code block}<br>\n\
        <code>end</code>\n\
        &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp(You can experiment or move on when ready)</li>\n\
        </ul>";
    
    slideArray[5] = "<h5>If Statements: Additional Information</h5>\n\
        <ul>\n\
        <li>More documentation on <code>if</code> statements can be found <a href=\"http://quorumlanguage.com/documents/syntax/if.php\">here</a>.</li>\n\
        </ul>";
    
    $(document).ready(function(){
//        $('#IDE-input').text('boolean sayStatement = true\ntext greeting = "I live in a giant bucket"\nif sayStatement\n        say greeting\nelseif sayStatement = false\n        output greeting\nend');
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
