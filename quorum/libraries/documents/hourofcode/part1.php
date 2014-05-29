<?php include("../../static/templates/pageheader.template.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Hour of Code: Part 1</h1>
                <p>Are you my mommy?</p>
	</div>
</div>
  <?php include("../../static/templates/contentwrapperheader.template.php"); ?>

<?php include("sidebar.php"); ?><div id="video-container">
    <iframe width="468" height="351" src="//www.youtube.com/embed/dqaDSlYdRcs?rel=0" frameborder="0" allowfullscreen></iframe>
</div><div id="hour-of-code-IDE">
    <h2>Try It! <span class="hour-of-code-IDE-subtitle">Enter Quorum code below and press "Run" to execute it</span></h2>
    <div id="hour-of-code-IDE-controller">
    </div>
        <textarea id="hour-of-code-IDE-input" class="ide inputArea">var mom = "ACGTTGCA";
var child = "ACTGTGCA";
AreYouMyMommy(mom, child);</textarea>
        
        <pre id="hour-of-code-IDE-output" class="outputArea"></pre><div id="run-button" class="btn-group">
                <a class="btn btn-success" href="#">Run</a>
        </div></div><div id="hour-of-code-canvas">
</div>

<?php include("../../static/templates/pagefooter.template.php"); ?>

<script type="text/javascript">
    document.title = 'Hour of Code Part 1 | Quorum Programming Language';
    
    var url = window.location.pathname.substring(window.location.pathname.indexOf("hourofcode/") + "hourofcode/".length, window.location.pathname.indexOf(".php"));

var arr = ["introduction", "part1", "part2", "part3", "part4", "part5"];

for(var i = 0; i < arr.length; i++)
{
        if(url == arr[i])
                $("#sidebar-".concat(arr[i])).css({"background-color":"rgb(235,235,235)", "color":"#049CDB"});
}
</script>