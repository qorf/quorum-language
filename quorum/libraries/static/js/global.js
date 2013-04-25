$(function() {
	slideInSublibraries();

	autoComplete();

	openModalFromURL();

	registrationValidateAndSubmit();

	userSignIn();

	expandAndCollapseLeftSideMenu();
	
    submitCodeSample();

    fadeInLibraryTable();

    changeCodeExample();

	tab();

	embed();

	extendLeftSidebar(); // keep this at the end
});

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function refresh() {
	var url = document.URL;
	url = url.replace('?registerWith=google','');
	url = url.replace('?loginWith=google','');
	window.location.replace(url);
}

var bodyMessage = function(text, state) {
	var element = '<div class="alert alert-' + state + ' alert-body-bottom-right" style="display:none">' + text + '</div>'
	$("body").append(element);
	
	$(".alert-body-bottom-right").fadeIn('slow', function(){
		$(this).delay(5000).fadeOut('slow', function() {
			$(this).remove();
		});
	});
}

var successMessage = function(text) {
	bodyMessage(text, "success");
}

var errorMessage = function(text) {
	bodyMessage(text, "error");
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

var openModalFromURL = function() {
	if (getUrlVars()["loginWith"] == "google") {
		googleUserLoginAuthenticate();
	}
	if (getUrlVars()["registerWith"] == "google") {
		$('#modal-registration').modal();
	}
}

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
	if (password !== undefined && password.length <= 0) {
		return false;
	}

	return true;
}

var registerUser = function() {
	var buttons = $("#modal-registration .modal-footer button");
	var spinner = $("#modal-registration .modal-footer .loading-spinner");

	buttons.hide();
	spinner.show();

	$.ajax({
		type: "POST",
		url: "/controllers/user.controller.php?action=register",
		data: $("#registration-form").serialize(),
		success: function(result) {
			if ($.trim(result) == "1") {
				successMessage("<strong>You have successfully registered.</strong> Welcome to the Quorum website!");
				showUserHeaderControls();
				$('#modal-registration').modal('hide');
				spinner.hide();
			}
			else {
				$("#integrity-error").remove();
				$("#registration-form").before('<duv class="text-error" id="integrity-error">Sorry, but the email or username entered has been registered already.</div>');
				$("#integrity-error").show();
				buttons.show();
				spinner.hide();
			}
			console.log(result);
		},
		error: function (xhr, ajaxOptions, thrownError) {
			errorMessage("<strong>Sorry!</strong> There was a server error and your registration could not be completed.");
			$('#modal-registration').modal('hide');
			spinner.hide();
			console.error(xhr, ajaxOptions, thrownError);
		}
	});
}

var showUserHeaderControls = function() {
	$.ajax({
		url: "/static/templates/user-headercontrols.template.php",
		context: document.body
	}).done(function(data) {
		$(".user-controls-loggedout").remove();
		$(".user-controls").html(data);
	});
}


var loginDisplayValidateAndSubmit = function() {
	$('#modal-login').modal();
	$("#modal-registration button.btn-primary").on("click", function() {
		$('#modal-login').modal("show");
	});
}

var registrationValidateAndSubmit = function() {
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
			registerUser();
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

		if ($(this).val() == "" || validateEmail($(this).val())) {
			emailError.hide();
		}
		else {
			emailError.show();
		}
	});

	$("#registration-username").on("blur", function() {
		var usernameError = $("#modal-registration .username .text-error");

		if ($(this).val() == "" || validateUsername($(this).val())) {
			usernameError.hide();
		}
		else {
			usernameError.show();
		}
	});

	$("#registration-password").on("blur", function() {
		var passwordError = $("#modal-registration .password .text-error");

		if ($(this).val() == "" || validatePassword($(this).val())) {
			passwordError.hide();
		}
		else {
			passwordError.show();
		}
	});
}

var userSignIn = function() {
	var checkCredentials = function(username, password) {
		var buttons = $("#modal-login .modal-footer button");
		var spinner = $("#modal-login .modal-footer .loading-spinner");

		$.ajax({
			type: "POST",
			url: "/controllers/user.controller.php?action=login",
			data: $("#login-form").serialize(),
			success: function(result) {
				if ($.trim(result) == "1") {
					refresh();
				}
				else {
					$("#integrity-error").remove();
					$("#login-form").before('<div class="text-error" id="integrity-error">Sorry, but that login is not correct.</div>');
					$("#integrity-error").show();
					buttons.show();
					spinner.hide();
				}
				console.log(result);
			},
			error: function (xhr, ajaxOptions, thrownError) {
				errorMessage("<strong>Sorry!</strong> There was a server error and your login could not be completed.");
				$('#modal-login').modal('hide');
				spinner.hide();
				console.error(xhr, ajaxOptions, thrownError);
			}
		});
	}

	$("#modal-login .btn-primary").on("click", function() {
		checkCredentials($("#login-username"), $("#login-password"));
	});
}

var googleUserLoginAuthenticate = function() {
	successMessage("<strong>We're checking your login...</strong>");

	$.ajax({
		type: "GET",
		url: "/controllers/user.controller.php",
		data: { action: "googleUserLoginAuthenticate" },
		success: function(result) {
			if ($.trim(result) == "1") {
				refresh();
			}
			else {
				$('#modal-login').modal();
				$("#integrity-error").remove();
				$("#login-form").before('<div class="text-error" id="integrity-error">Sorry, but there has been an error authenticating your Google account.</div>');
				$("#integrity-error").show();
				buttons.show();
				spinner.hide();
			}
			console.info(result);

		},
		error: function(result) {
			errorMessage("<strong>Sorry!</strong> There was a server error and your login could not be completed.");
			$('#modal-login').modal('hide');
			console.error(xhr, ajaxOptions, thrownError);
		},
		complete: function() { }
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

var submitCodeSample = function(){
	$("#run a").on("click", function(e) {
		e.preventDefault();
		$(".outputArea").text("");
		
		var codeData = {code: $(".inputArea").val()};
		console.log(codeData);
		$.ajax({
			type: "POST",
			url: "/controllers/IDE.controller.php",
			data: codeData,
			success: function(result){
				var start = 0;
				result.indexOf("|") > 0  ? start = result.indexOf("|")+1 : start = 1;
				var output = result.substring(start, result.length-1);
				console.log(output);
				$(".outputArea").text(output);
			},
			error: function (xhr, ajaxOptions, thrownError) {
				console.log(xhr, ajaxOptions, thrownError);
			}
		});
	})
}


var fadeInLibraryTable = function() {
	if ($(".index-grid").length > 0) {
		$(".index-package").hide();

		$(".index_package_title a").on("click", function(e) {
			e.preventDefault();
			var anchorHash = $(this).attr("href");
			var tableId = $.trim("#table-" + (anchorHash.substring(1, anchorHash.length)));

			$(".index-package").hide();	
			$(tableId).show();
		});

	
		$(".sublist-item").on("click", function(e) {
			e.preventDefault();
			var sublibrary = $(this).html().split(".").pop();
			sublibrary = sublibrary.toLowerCase();

			var anchorHash = $(this).parent().parent().find("h2 a").attr("href");
			var tableId = $.trim("#table-" + (anchorHash.substring(1, anchorHash.length)) + "-" + sublibrary);

			$(".index-package").hide();	
			$(tableId).show();
		});
	}
}

var changeCodeExample = function(){
	$("li.code-example").on("click", function(e) {
			$(".inputArea").text("");
			var selection = $(this).text();
			var carret = '\u25BE';
			var codeExample = "";
			$("#dropdown-button").text(selection + carret);	
			if (selection == " Hello, World! "){
				codeExample = "output \"Hello, World!\"";
			}
			else if(selection == " Conditionals "){
				codeExample = "integer a = 1\ninteger c = 0\nif a = 1\n\tc = 1\nelseif a > 1\n\tc = 2\nelse\n\tc = 0\nend\noutput c";
			}
			else if (selection == " Loops "){
				codeExample = "repeat 3 times\n\toutput \"Quorum\"\nend"
			}
			else if (selection == " Actions "){
				codeExample = "action main\n\tinteger addedNumbers = add(5,10)\n\toutput addedNumbers\nend\naction add(integer a, integer b) returns integer\n\treturn a + b\nend";
			}
                        else if (selection == " Classes "){
				codeExample = 
                                    "class Main\n" +
                                    "   action Main\n" +
                                    "       i = 1\n" +
                                    "       repeat 10 times\n" +
                                    "           output i\n" +
                                    "           i = i + 1\n" +
                                    "       end\n" +
                                    "   end\n" +
                                    "end";
			}
			$(".inputArea").val(codeExample);
	})
}
var tab = function(){
	$("textarea").keydown(function(e) {
    	if(e.keyCode === 9) { 
        	var start = this.selectionStart;
	        var end = this.selectionEnd;
    	    var $this = $(this);
       		var value = $this.val();
    	    $this.val(value.substring(0, start) + "\t" + value.substring(end));
    	    this.selectionStart = this.selectionEnd = start + 1;
    	    e.preventDefault();
    	}
	});
}


var embed = function() {
	$(".ide-embed-info label, .ide-embed-info input").on("click", function() {
		$(".ide-embed-info input").select();
	});
}








































 