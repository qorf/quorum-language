$(function() {
	slideInSublibraries();

	autoComplete();

	registerWithGoogle();

	registrationValidation();

	userSignIn();

	expandAndCollapseLeftSideMenu();

	createRatingControls();

	fiveStarRatings();

	extendLeftSidebar(); // keep this at the end
});

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

var slideInSublibraries = function() {
	var toggleList = function(e, gridItem) {
		var isTargetSublistItem = ($(e.target).hasClass("sublist-item")) ? true : ($(e.target).parent().hasClass("sublist-item"));
		if (isTargetSublistItem) { return; }

		var sublist = gridItem.find(".grid-sublist");
		if (sublist.length == 0) { delete sublist; return; }
		e.preventDefault();

		$(sublist).slideToggle();
	}

	$(".grid-item").on("click", function(e) { toggleList(e,$(this)); });

}

var extendLeftSidebar = function() {
	var contentHeight = $(".content-wrapper").height();
	$(".navigation-sidebar").css("min-height", contentHeight + "px");
}

var autoComplete = function() {
	var searchTerms = ["Math", "Random", "Array", "List", "Queue", "Stack", "Table", "Addable", "ArrayBlueprint", "Container", "Copyable", "Indexed", "Iterative", "Iterator", "KeyedAddable", "KeyedIterative", "ListBlueprint", "QueueBlueprint", "StackBlueprint", "TableBlueprint", "ArrayIterator", "KeyedNode", "ListIterator", "ListNode", "Object", "CastError", "DivideByZeroError", "EndOfFileError", "Error", "FileNotFoundError", "InputOutputError", "InvalidArgumentError", "InvalidLocationError", "InvalidPathError", "OutOfBoundsError", "UndefinedObjectError", "CompareResult", "Boolean", "Integer", "Number", "Text", "AnalogSensor", "Button", "Connectable", "Controller", "DigitalSensor", "Motor", "Robot", "Servo", "Audio", "Chord", "Instrument", "Music", "MusicEvent", "Note", "Playable", "Speech", "Track", "Console", "DateTime", "File", "FileRandomAccess", "FileReader", "FileWriter", "Path", "StackTraceItem", "FileRandomAccessBlueprint", "FileReaderBlueprint", "FileWriterBlueprint"];

	$(".search-query").typeahead({
      source: searchTerms,
      minLength: 3
    });	
}

var registerWithGoogle = function() {
	if (getUrlVars()["loginWith"] == "google") {
		$('#modal-registration').modal();
		console.log($('#modal-registration'));
	}
}

var registrationValidation = function() {
	$("#modal-registration button.btn-primary").on("click", function() {
		var emailField = $("#registration-email");
		var usernameField = $("#registration-username");
		var passwordField = $("#registration-password");

		var email = emailField.val();
		var username = usernameField.val();
		var password = passwordField.val();

		var textError = $("#modal-registration .text-error");
		var emailError = $(textError[0]);
		var usernameError = $(textError[1]);
		var passwordError = $(textError[2]);

		var emailIsValid = validateEmail(email);
		var usernameIsValid = validateUsername(username);
		var passwordIsValid = validatePassword(password);

		emailError.hide();
		usernameError.hide();
		passwordError.hide();

		if (emailIsValid && usernameIsValid && passwordIsValid) {
			// do some shit
			$("#modal-registration .modal-footer button").hide();
			$("#modal-registration .modal-footer .spinner").show();

			// TODO: ajax call to determine if the user can be added. if so, add it. Remove this timeout crap.
			setTimeout(function(){
				$("#modal-registration .modal-footer .spinner").delay(3000).hide();
				$('#modal-registration').modal('hide');

				$('#modal-registration-success').modal({backdrop: false}).modal('show');

				showUserHeaderControls();
			},3000);
		}
		else {
			if (!emailIsValid) {
				emailError.show();
			}
			if (!usernameIsValid) {
				usernameError.show();
			}
			if (!passwordIsValid) {
				passwordError.show();
			}

			return false;
		}
	});

	$("#registration-email").on("blur", function() {
		var emailError = $("#modal-registration .email .text-error");

		if (validateEmail($(this).val())) {
			emailError.hide();
		}
		else {
			emailError.show();
		}
	});

	$("#registration-username").on("blur", function() {
		var usernameError = $("#modal-registration .username .text-error");

		if (validateUsername($(this).val())) {
			usernameError.hide();
		}
		else {
			usernameError.show();
		}
	});

	$("#registration-password").on("blur", function() {
		var passwordError = $("#modal-registration .password .text-error");

		if (validatePassword($(this).val())) {
			passwordError.hide();
		}
		else {
			passwordError.show();
		}
	});

	var validateEmail = function(email) {
		var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		
		if ((re.test(email) == false) || (email.length <= 0)) {
			return false;
		}

		return true;
	}

	var validateUsername = function(username) {
		var re = /[-!$%^&*()_+|~=`{}\[\]:";'<>?,.\/]/;

		if (re.test(username) || (username.indexOf(' ') >= 0) || (username.length <= 0)) {
			return false;
		}

		return true;
	}

	var validatePassword = function(password) {
		if (password.length <= 0) {
			return false;
		}

		return true;
	}
}

var userSignIn = function() {
	$(".header-signin-list button.btn-primary").on("click", function(e) {
		console.log("t");
		e.preventDefault();

		// TODO: ajax call to see if values are correct
		$(this).hide();
		$(".header-signin-list .spinner").show();
		$(".header-signin-list input").attr("disabled", "disabled");

		// TODO: ajax call to determine if the user can be added. if so, add it. Remove this timeout crap.
		setTimeout(function(){
			showUserHeaderControls();
		}, 3000);
	});
}

var showUserHeaderControls = function() {
	// TODO: make sure the system sets a cookie and use PHP to re-generate the user controls
	$.ajax({
		url: "/static/templates/user-headercontrols.php",
		context: document.body
	}).done(function(data) {
		$(".user-controls-loggedout").remove();
		$(".user-controls").html(data);
	});
}

var expandAndCollapseLeftSideMenu = function() {
	$(".child").hide();

	$(".collapsable").on("click", function() {
		$(this).parent().children().toggle();
		$(this).toggle();
	});

	$.each($(".collapsable"), function() {
		if ($(this).parent().find('.child').length > 0) {
			$(this).append(" [+]");
		}
	});

}

var createRatingControls = function() {
	var template = doT.template($('#template-ratings-controls').text());

	$.each($(".controllable"), function() {
		var componentType = $(this).data("componenttype");
		componentType = (componentType !== undefined) ? componentType.replace(/-/g," ") : "component";
		var templateData = { id: $(this).data("id"), componentType: componentType }; // TODO: get real value
		$(this).append(template(templateData));
	});

	var getRatingForId = function(id) {
		// TODO: make an ajax call to the database to get the real rating
		
		return 3;
	}
}

var fiveStarRatings = function(starRatingsList) {
	var setStars = function(element, starNumber) {
		$(element)
			.removeClass("stars-0").removeClass("stars-1").removeClass("stars-2").removeClass("stars-3").removeClass("stars-4").removeClass("stars-5")
			.addClass("stars-" + starNumber);
	}

	var setClickedStars = function(clickTarget) {
		var starNumber = ($(clickTarget).attr('class').split(/-/))[1]; // gets the class, splits at the hyphen, and grabs the number
		setStars($(clickTarget).parent(), starNumber);
	}

	$(".star-ratings li").on({
		mouseenter: function() {
			setClickedStars(this);
		},
		mouseleave: function() { 
			setStars($(this).parent(), 0);
		},
		click: function() {
			setClickedStars(this);
			$(this).parent().find('li').unbind('mouseenter').unbind('mouseleave');

			var template = doT.template($('#template-ratings-success').text());
			var templateHtml = template();
			var container = $(this).closest(".ratings-controls");
			container.find(".ratings-success").remove();
			container.append(templateHtml);

			// TODO: hook up ajax call to PHP
		}
	});
}










































 