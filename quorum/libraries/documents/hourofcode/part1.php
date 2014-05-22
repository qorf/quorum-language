<?php include("../../static/templates/pageheader.template.php"); ?> 

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Hour of Code : Part 1</h1>
	</div>
</div>
    <?php include("../../static/templates/contentwrapperheader.template.php"); ?>

<div id ="sidebar-navigation">
    <ul>
        <a href="#"><li id = "sidebar-intro" class = "sidebar-link">Introduction</li></a>
        <a href="#"><li id = "sidebar-part1" class = "sidebar-link">Part 1: Output Statements</li></a>
        <a href="#"><li id = "sidebar-part2" class = "sidebar-link">Part 2: Something Cool</li></a>
        <a href="#"><li id = "sidebar-part3" class = "sidebar-link">Part 3: Something Rad</li></a>
        <a href="#"><li id = "sidebar-part4" class = "sidebar-link">Part 4: Something Awesome</li></a>
        <a href="#"><li id = "sidebar-part5" class = "sidebar-link">Part 5: Something Something</li></a>
    </ul>    
</div>

<!--<script type="text/javascript">
    document.title = 'Hour of Code Part 1 | Quorum Programming Language';
    
    var url = window.location.pathname.substring(window.location.pathname.indexOf("hourofcode/") + "hourofcode/".length, window.location.pathname.indexOf(".php"));


var arr = ["intro", "part1", "part2", "part3", "part4", "part5"];

for(var i = 0; i < arr.length; i++)
{
        if(url == arr[i])
                $("#sidebar-".concat(arr[i]) ).css([{"background-color":"rgb(235,235,235);", "color":"#049CDB"}]);
}
    
</script>-->