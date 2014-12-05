<div id='sidebar'>
    <nav id="sidebar-navigation">
        <ul>
            <a href="part1.php"><li id="sidebar-part1" class="sidebar-link">Part 1: Got a New Gig</li></a>
            <a href="part2.php"><li id="sidebar-part2" class="sidebar-link">Part 2: Sayin' Stuff</li></a>
            <a href="part3.php"><li id="sidebar-part3" class="sidebar-link">Part 3: This or That</li></a>
            <a href="part4.php"><li id="sidebar-part4" class="sidebar-link">Part 4: Clothing Picker</li></a>
            <a href="part5.php"><li id="sidebar-part5" class="sidebar-link">Part 5: Choose My Clothing!</li></a>
            <a href="part6.php"><li id="sidebar-part6" class="sidebar-link">Part 6: Mutate the Bugs</li></a>
            <a href="part7.php"><li id="sidebar-part7" class="sidebar-link">Part 7: The End of the World</li></a>
        </ul>

    </nav>
    <a id='finish' href='http://code.org/api/hour/finish'>I'm done with my hour of code <img alt="" src="/static/img/gridicons/hourofcode.png"></a>
</div><script type="text/javascript">
    
    var url = window.location.pathname.substring(window.location.pathname.indexOf("hourofcode/") + "hourofcode/".length, window.location.pathname.indexOf(".php"));

var arr = ["introduction", "part1", "part2", "part3", "part4", "part5", "part6"];

for(var i = 0; i < arr.length; i++)
{
        if(url == arr[i])
                $("#sidebar-".concat(arr[i])).css({"background-color":"rgb(235,235,235)", "color":"#049CDB"});
}
</script>