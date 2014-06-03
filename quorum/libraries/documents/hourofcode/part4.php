<?php include("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Part 4 | Quorum Programming Language';
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Hour of Code: Part 4</h1>
                <p>Parental DNA Testing</p>
	</div>
</div>
  <?php include("../../static/templates/contentwrapperheader.template.php"); ?>

<?php include("sidebar.php"); ?><div id="hour-of-code-IDE">
    <h2>Try It! <span class="hour-of-code-IDE-subtitle">Enter Quorum code below and press "Run" to execute it</span></h2>
    <div id="hour-of-code-IDE-controller">
    </div>
        <textarea id="hour-of-code-IDE-input" class="ide inputArea">var mom = "ACGTTGCA"; //provided code
var child = "ACTGTGCA"; //provided code
var matches = 0; //provided code

var MomTest = function(mom, child) {
  for (var i = 0; i < mom.length; i++) {
    if (mom.charAt(i) == child.charAt(i)) {
      matches++;
    }
  }
  if (matches > 2) {
    return true;
  }
  else {
    return false;
  }
}

var output = MomTest(mom, child);
output;</textarea>
        
        <pre id="hour-of-code-IDE-output" class="outputArea"></pre><div id="run-button" class="btn-group">
                <a class="btn btn-success" href="#">Run</a>
        </div></div><div id="content-box">
            <h3>Output Statements</h3>
            <p>To write something onto the screen in Quorum, simply type <code>output</code> followed by whatever you want in quotation marks like so: <code>output "Hello, science!"</code></p>
</div><div id="video-container">
    <iframe width="946" height="710" src="//www.youtube.com/embed/If3KlMxbtcc?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

<?php include("../../static/templates/pagefooter.template.php"); ?>