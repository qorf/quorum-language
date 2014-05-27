<?php include("../../static/templates/pageheader.template.php"); ?>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Hour of Code: Part 1</h1>
                <p>Output Statements</p>
	</div>
</div>
  <?php include("../../static/templates/contentwrapperheader.template.php"); ?>

<div id="sidebar-navigation">
    <ul>
        <a href="#"><li id="sidebar-introduction" class="sidebar-link">Introduction</li></a>
        <a href="part1.php"><li id="sidebar-part1" class="sidebar-link">Part 1: Output Statements</li></a>
        <a href="#"><li id="sidebar-part2" class="sidebar-link">Part 2: Something Cool</li></a>
        <a href="#"><li id="sidebar-part3" class="sidebar-link">Part 3: Something Rad</li></a>
        <a href="#"><li id="sidebar-part4" class="sidebar-link">Part 4: Something Awesome</li></a>
        <a href="#"><li id="sidebar-part5" class="sidebar-link">Part 5: Something Something</li></a>
    </ul>    
</div><div id="video-container">
    <iframe width="468" height="351" src="//www.youtube.com/embed/dqaDSlYdRcs?rel=0" frameborder="0" allowfullscreen></iframe>
</div><div id="hour-of-code-IDE">
    <h2>Try It! <span class="hour-of-code-IDE-subtitle">Enter Quorum code below and press "Run" to execute it</span></h2>
    <div id="hour-of-code-IDE-controller">
    </div>
        <textarea id="hour-of-code-IDE-input" class="ide inputArea">output "Hello, Science!"</textarea>
        
        <pre id="hour-of-code-IDE-output" class="outputArea"></pre><div id="run-button" class="btn-group">
                <a class="btn btn-success" href="#">Run</a>
        </div></div><div id="related-content">
    <h1>Related Content</h1>
    <ul>
        <a href="#"><li class="related-content-list-item">Placeholder Links</li></a>
        <a href="#"><li class="related-content-list-item">Lorem Ipsum</li></a>
        <a href="#"><li class="related-content-list-item">Related Content or something</li></a>
        <a href="#"><li class="related-content-list-item">Long striiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiing</li></a>
        <a href="#"><li class="related-content-list-item">This may lead to somewhere useful if you wish to learn more on the current subject</li></a>
        <a href="#"><li class="related-content-list-item">This link is very special</li></a>
        <a href="#"><li class="related-content-list-item">Suck it</li></a>
    </ul>
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