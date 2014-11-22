<!--The slideshow script relies on two things from the user:

    1: A div with the id of "slide-box" where you want the slides to appear

    2: An array named slideArray that contains strings of HTML
    that will be rendered in the #slide-box div. (Be sure to escape any quotations)

Example:

var slideArray = new Array();
    slideArray[0] = "<p>The inital slide</p>";
    slideArray[1] = "<h1>The second slide</h1>";
    slideArray[2] = "Whatever HTML you want";

etc...

Includes ARIA accessibility for reading the slide upon changing slides,
but accessibility tags within the slide HTML should still be implemented by whoever creates it
-->

<script type="text/javascript">
$( document ).ready(function() {
        //make the slide-navigation buttons
        for (var i = 0; i < slideArray.length; i++) {
            $('#slide-navigation').append('<button class="slide-navigation-button" aria-hidden="true" tabindex="0" aria-label="Show section ' + (i+1) + '">' + (i+1) + '</button>');
            slideArray[i] = "<article id =\"slide-box\" role=\"description\" aria-label=\"Section " + (i+1) + "\">" + slideArray[i] + "</article>";
        }

        //button array
        var buttonArray = new Array();
        buttonArray = $('.slide-navigation-button').toArray();

        //initial load stuff
        $(buttonArray[0]).addClass('active-slide-navigation-button');
        $('#slide-box').replaceWith($.parseHTML(slideArray[0]));
        $('#slide-navigation').append('<p aria-hidden="true">Programming Notes</p>');
        
        //changing slides
        var slide = function(index, direction) {
            //the slide animation
            //keep track of slide number. for some reason left and right are reversed
            if(direction === "right") {
                slideNr--;
            }
            if (direction === "left") {
                slideNr++;
            }
            $('#content-box-wrapper').attr('aria-live', 'none');
            $('#slide-box').hide('slide', { direction: direction }, 400, function(){
                //var div = $($.parseHTML(slideArray[index])).hide();
                $('#slide-box').replaceWith($.parseHTML(slideArray[index]));
                $('#content-box-wrapper').attr('aria-live', 'polite');
            });

    //        $('#slide-box').replaceWith($.parseHTML(slideArray[i]));
        };

        $('.slide-navigation-button').click(function() {
            var current = $('.slide-navigation-button').index($('.active-slide-navigation-button'));
            var next = $('.slide-navigation-button').index(this);
            $('.active-slide-navigation-button').removeClass('active-slide-navigation-button');
            $(buttonArray[next]).addClass('active-slide-navigation-button');
            if (current < next) { //next slide
                if (next == (slideArray.length - 1)) { //disable arrow
                    $('#rightArrow').click(false);
                    $('#rightArrow').attr("aria-hidden", "true");
                    $('#rightArrow').toggleClass("rightArrow-disabled");
                }
                
                slide(next, "left");
//                $('#slide-box').focus();
                
                if ($('#leftArrow').hasClass("leftArrow-disabled")) { //enable arrow
                    $('#leftArrow').click(true);
                    $('#leftArrow').attr("aria-hidden", "false");
                    $('#leftArrow').toggleClass("leftArrow-disabled");
                }
            }

            else if (current > next) { //previous slide
                if (next == 0) { //disable arrow
                    $('#leftArrow').click(false);
                    $('#leftArrow').attr("aria-hidden", "true");
                    $('#leftArrow').toggleClass("leftArrow-disabled");
                }
                
                slide(next, "right");
                
                if ($('#rightArrow').hasClass("rightArrow-disabled")) { //enable arrow
                    $('#rightArrow').click(true);
                    $('#rightArrow').attr("aria-hidden", "false");
                    $('#rightArrow').toggleClass("rightArrow-disabled");
                }
            }
        });
        
        //old visual style, but this was for if the nav buttons had a tabindex and could be navigated to by keyboard
//        $('.slide-navigation-button').keypress(function(e) {
//            if(e.which == 13 || e.which == 32)  // the enter key code || the space key code
//             {
//                 var current = $('.slide-navigation-button').index($('.active-slide-navigation-button'));
//                 var next = $('.slide-navigation-button').index(this);
//                 $('.active-slide-navigation-button').removeClass('active-slide-navigation-button');
//                 $(buttonArray[next]).addClass('active-slide-navigation-button');
//                 if (current < next) { //next slide
//                     if (next == (slideArray.length - 1)) { //disable arrow
//                         $('#rightArrow').click(false);
//                         $('#rightArrow').hide();
//                     }
//                     if ($('#leftArrow').is(":hidden")) { //enable arrow
//                         $('#leftArrow').click(true);
//                         $('#leftArrow').show();
//                     }
//                     slide(next, "left");
//                 }
//
//                 else if (current > next) { //previous slide
//                     if (next == 0) { //disable arrow
//                         $('#leftArrow').click(false);
//                         $('#leftArrow').hide();
//                     }
//                     if ($('#rightArrow').is(":hidden")) { //enable arrow
//                         $('#rightArrow').click(true);
//                         $('#rightArrow').show();
//                     }
//                     slide(next, "right");
//                 } 
//             }
//            
//        });
        
        $('#leftArrow').click(function() {
            var current = $('.slide-navigation-button').index($('.active-slide-navigation-button'));
            if (current != 0) { //check for bounds
                var next = current - 1;
                $('.active-slide-navigation-button').removeClass('active-slide-navigation-button');
                $(buttonArray[next]).addClass('active-slide-navigation-button');
                if ($('#rightArrow').hasClass("rightArrow-disabled")) { //enable arrow
                    $('#rightArrow').click(true);
                    $('#rightArrow').attr("aria-hidden", "false");
                    $('#rightArrow').toggleClass("rightArrow-disabled");
                }
                if (next == 0) { //disable arrow
                    $('#leftArrow').click(false);
                    $('#leftArrow').attr("aria-hidden", "true");
                    $('#leftArrow').attr("aria-label", "You are on the first slide.");
                    $('#leftArrow').toggleClass("leftArrow-disabled");
                }
                slide(next, "right");
            }
        });
        
        $('#rightArrow').click(function() {
            var current = $('.slide-navigation-button').index($('.active-slide-navigation-button'));
//            alert(current + " " + slideArray.length)
            if (current != (slideArray.length - 1)) {
                var next = current + 1;
                $('.active-slide-navigation-button').removeClass('active-slide-navigation-button');
                $(buttonArray[next]).addClass('active-slide-navigation-button');
                if ($('#leftArrow').hasClass("leftArrow-disabled")) { //enable arrow
                    $('#leftArrow').click(true);
                    $('#leftArrow').attr("aria-hidden", "false");
                    $('#leftArrow').toggleClass("leftArrow-disabled");
                }
                if (next == (slideArray.length - 1)) { //disable arrow
                    $('#rightArrow').click(false);
                    $('#rightArrow').attr("aria-hidden", "true");
                    $('#leftArrow').attr("aria-label", "You are on the first slide.");
                    $('#rightArrow').toggleClass("rightArrow-disabled");
                }
                slide(next, "left");
            }
        });
        
    });
</script>