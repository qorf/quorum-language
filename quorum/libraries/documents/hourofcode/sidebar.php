<div id="sidebar-navigation">
    <ul>
        <a href="introduction.php"><li id="sidebar-introduction" class="sidebar-link">Intro: What is programming?</li></a>
        <a href="part1.php"><li id="sidebar-part1" class="sidebar-link">Part 1: Building Blocks of Life</li></a>
        <a href="part2.php"><li id="sidebar-part2" class="sidebar-link">Part 2: Mutations!</li></a>
        <a href="part3.php"><li id="sidebar-part3" class="sidebar-link">Part 3: Count the Mutants</li></a>
        <a href="part4.php"><li id="sidebar-part4" class="sidebar-link">Part 4: Hi... Mom?</li></a>
        <a href="part5.php"><li id="sidebar-part5" class="sidebar-link">Part 5: Breed the Mutants</li></a>
        <a href="part6.php"><li id="sidebar-part6" class="sidebar-link">Part 6: Create Superbugs</li></a>
        <a href="part7.php"><li id="sidebar-part7" class="sidebar-link">Part 7: Take Over The World!</li></a>
    </ul>    
</div><script type="text/javascript">
    
    var url = window.location.pathname.substring(window.location.pathname.indexOf("hourofcode/") + "hourofcode/".length, window.location.pathname.indexOf(".php"));

var arr = ["introduction", "part1", "part2", "part3", "part4", "part5", "part6"];

for(var i = 0; i < arr.length; i++)
{
        if(url == arr[i])
                $("#sidebar-".concat(arr[i])).css({"background-color":"rgb(235,235,235)", "color":"#049CDB"});
}
</script>