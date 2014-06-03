    <?php include("../../static/templates/pageheader.template.php"); ?>
<script type="text/javascript">
    document.title = 'Hour of Code: Intro | Quorum Programming Language';
    
    //slide array
    var slideArray = new Array();
    slideArray[0] = "<div id =\"slide-box\"><h5>What is programming?</h5><ul><li>A gigantic headache</li></ul></div>";
    slideArray[1] = "<div id =\"slide-box\"><h5>What is programming good for?</h5><ul><li>Impressing the ladies</li></ul></div>";
    slideArray[2] = "<div id =\"slide-box\"><h5>How does programming work?</h5><ul><li>Through witchcraft and wizardry</li></ul></div>";
    slideArray[3] = "<div id =\"slide-box\"><h5>Practical applications of programming</h5><ul><li>Saving the world from the bad guys</li></ul></div>";
    slideArray[4] = "<div id =\"slide-box\"><h5>But... we have to start somewhere</h5><ul><li>So let's tell the computer to say hello to our friend, science</li><li>Type <code>output \"Hello science!\"</code> into the text area and click the Run button</li><li>We can use the <code>output</code> command whenever we want the computer to talk to us</li></ul></div>";
    
    $( document ).ready(function() {
    //make the slide-navigation buttons
    for (var i = 0; i < slideArray.length; i++) {
        $('#slide-navigation').append('<div class ="slide-navigation-button">' + (i+1) + '</div>');
    }
    
    //button array
    var buttonArray = new Array();
    buttonArray = $('.slide-navigation-button').toArray();
    
    //initial load stuff
    $(buttonArray[0]).addClass('active-slide-navigation-button');
    $('#slide-box').replaceWith($.parseHTML(slideArray[0]));
    
    //changing slides
    $('.slide-navigation-button').click(function() {
        var i = $('.slide-navigation-button').index(this);
        $('.active-slide-navigation-button').removeClass('active-slide-navigation-button');
        $(buttonArray[i]).addClass('active-slide-navigation-button');
        
        //the slide animation
        $('#slide-box').hide('slide', { direction: 'left' }, 400, function(){
            var div = $($.parseHTML(slideArray[i])).hide();
            $(this).replaceWith($.parseHTML(slideArray[i]));
            $('#slide-box').show('slide', { direction: 'left' }, 400);
        });
        
//        $('#slide-box').hide('slide', { direction: 'left' }, 400);
//        $('#slide-box' + i + '').show('slide', { direction: 'left' }, 400);
        
//        $('#slide-box').replaceWith($.parseHTML(slideArray[i]));
    });
});
    
</script>

<div class="hero-unit">
	<div class="hero-unit-container">
		<h1>Hour of Code: Introduction</h1>
                <p>Hello Science!</p>
	</div>
</div>
  <?php include("../../static/templates/contentwrapperheader.template.php"); ?>

      <?php include("sidebar.php"); ?><div id="content-box"><div id="slide-navigation"></div>
          <div id ="slide-box"></div>
</div><div id="hour-of-code-IDE">
    <h2>Try It! <span class="hour-of-code-IDE-subtitle">Enter Quorum code below and press "Run" to execute it</span></h2>
    <div id="hour-of-code-IDE-controller">
    </div>
        <textarea id="hour-of-code-IDE-input" class="ide inputArea">var output = "Hello science!";
output;</textarea>
        <pre id="hour-of-code-IDE-output" class="outputArea"></pre><div id="run-button" class="btn-group">
                <a class="btn btn-success" href="#">Run</a>
        </div></div><div id="video-container">
    <iframe width="946" height="710" src="//www.youtube.com/embed/If3KlMxbtcc?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

<?php include("../../static/templates/pagefooter.template.php"); ?>