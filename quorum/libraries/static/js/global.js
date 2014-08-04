$(function() {
	slideInSublibraries();

	//autoComplete();

	openModalFromURL();

	registrationValidateAndSubmit();

	userSignIn();

	expandAndCollapseLeftSideMenu();
	
    submitCodeSample();

    fadeInLibraryTable();

    changeCodeExample();

	tab();

	embed();

	detectEnterKey();

	forgotPasswordShow();

	submitLibraryWizard();

	controlPanel();

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
	location.reload();
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
	if (window.location.hash == "#googleLogin") {
		googleUserLoginAuthenticate();
		window.location.hash = "";
	}
	if (window.location.hash == "#googleRegister") {
		$('#modal-registration').modal();
		window.location.hash = "";
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
	var re = /[-!$%^&*()_+|~=`{}\[\]:";'<>?,\/]/;

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

var validateConfirmPassword = function() {
	var confirm = $("#registration-confirm-password").val();
	var password = $("#registration-password").val();

	if ( (password == "") || (confirm != password) ) {
		return false;
	}
	else { 
		return true;
	}
}

var validateFirstName = function(name) {
	if (name == undefined || $.trim(name) == "") {
		return false;
	}

	return true;
}

var validateLastName = function(name) {
	if (name == undefined || $.trim(name) == "") {
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
				//showUserHeaderControls();
				$('#modal-registration').modal('hide');
				spinner.hide();
				refresh();
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
		var firstNameField = $("#registration-first-name");
		var lastNameField = $("#registration-last-name");

		var email = emailField.val();
		var username = usernameField.val();
		var password = passwordField.val();
		var firstName = firstNameField.val();
		var lastName = lastNameField.val();

		var textError = $("#modal-registration .text-error");
		var emailError = $(textError[0]);
		var usernameError = $(textError[1]);
		var firstNameError = $(textError[2]);
		var lastNameError = $(textError[3]);
		var passwordError = $(textError[4]);
		var confirmError = $(textError[5]);

		console.log(lastName, firstName);

		var emailIsValid = validateEmail(email);
		var usernameIsValid = validateUsername(username);
		var passwordIsValid = validatePassword(password);
		var confirmIsValid = validateConfirmPassword();
		var firstNameIsValid = validateFirstName(firstName);
		var lastNameIsValid = validateLastName(lastName);

		emailError.hide();
		usernameError.hide();
		passwordError.hide();
		confirmError.hide();
		firstNameError.hide();
		lastNameError.hide();

		if (emailIsValid && usernameIsValid && passwordIsValid && confirmIsValid && firstNameIsValid && lastNameIsValid) {
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
			if (!confirmIsValid) {
				confirmError.show();
			}
			
			if (!firstNameIsValid) {
				firstNameError.show();
			}
			else { firstNameError.hide(); }

			if (!lastNameIsValid) {
				lastNameError.show();
			}
			else { lastNameError.hide(); }

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

	$("#registration-first-name").on("blur", function() {
		var firstNameError = $("#modal-registration .first-name .text-error");

		if (validateFirstName($(this).val())) {
			firstNameError.hide();
		}
		else {
			firstNameError.show();
		}
	});

	$("#registration-last-name").on("blur", function() {
		var lastNameError = $("#modal-registration .last-name .text-error");

		if (validateLastName($(this).val())) {
			lastNameError.hide();
		}
		else {
			lastNameError.show();
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

	$("#login-username").on("blur", function() {
		var usernameError = $("#modal-login .username .text-error");

		if ($(this).val() == "" || validateUsername($(this).val())) {
			usernameError.hide();
		}
		else {
			usernameError.show();
		}
	});

	$("#login-password").on("blur", function() {
		var passwordError = $("#modal-login .password .text-error");

		if ($(this).val() == "" || validatePassword($(this).val())) {
			passwordError.hide();
		}
		else {
			passwordError.show();
		}
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
				console.info("refresh");
			}
			else {
				$('#modal-login').modal();
				$("#integrity-error").remove();
				$("#login-form").before('<div class="text-error" id="integrity-error">Sorry, but there has been an error authenticating your Google account.</div>');
				$("#integrity-error").show();
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
    //front page IDE
    
//	$("#run a").on("click", function(e) {
//		e.preventDefault();
//		$(".outputArea").text("");
//		
//		var codeData = {code: $(".inputArea").val()};
//		console.log(codeData);
//		$.ajax({
//			type: "POST",
//			url: "controllers/IDE.controller.php",
//			data: codeData,
//			success: function(result){
//				var start = 0;
//				result.indexOf("|") > 0  ? start = result.indexOf("|")+1 : start = 0;
//				var output = result.substring(start, result.length-1);
//				console.log(output);
//				//$(".outputArea").text(output);
//                                //document.getElementById("myDiv").innerHTML='<script type="text/javascript" id="runscript">alert("This alert was produced from the AJAX call");<\/script>';
//                                var value = eval(output);
//                                $(".outputArea").text(value);
//			},
//			error: function (xhr, ajaxOptions, thrownError) {
//				console.log(xhr, ajaxOptions, thrownError);
//                                $(".outputArea").text("Error: Could not connect to server");
//			}
//		});
//	})

        
        $("#run-button a").on("click", function(e) {
		e.preventDefault();
		$(".outputArea").text("");
		var codeData = {code: $(".inputArea").val()};
                //var msg = new SpeechSynthesisUtterance('28.27431');
		//console.log(codeData);
		$.ajax({
			type: "POST",
			url: "../../controllers/IDE.controller.php",
			data: codeData,
			success: function(result){
//				var start = 0;
//				result.indexOf("|") > 0  ? start = result.indexOf("|")+1 : start = 0;
//				var output = result.substring(start, result.length-1);
				//$(".outputArea").text(output);
                                //document.getElementById("myDiv").innerHTML='<script type="text/javascript" id="runscript">alert("This alert was produced from the AJAX call");<\/script>';
                                //alert(output);
                                //var value = eval(result);
                                console.log(result);
                                $("#IDE-output").text("Before\nTest Message\nAfter");
                                //$("#IDE-output").text(eval(result));
                                //window.speechSynthesis.speak(msg);
			},
			error: function (xhr, ajaxOptions, thrownError) {
				console.log(xhr, ajaxOptions, thrownError);
                                $(".outputArea").text("Error: Could not connect to server");
			}
		});
	})
}

var IsDNA = function(DNA) {
    for (var i = 0; i < DNA.length; i++) {
        if ((DNA.charAt(i) == 'A') || (DNA.charAt(i) == 'C') || (DNA.charAt(i) == 'G') || (DNA.charAt(i) == 'T')) {
        }
        else {
            return false;
        }
    }
    return true;
}

var CloneDNA = function(DNA) {
    //loop through the DNA one character at a time, 1% chance to mutate a letter
    var randomNumber = Math.floor((Math.random() * 100) + 1); //random 1 - 100
    var cloneDNA = "";
    for (var i = 0; i < DNA.length; i++) {
        console.log(randomNumber);
        if (randomNumber == 42) {
            //mutate
            var randomNumber2 = Math.floor((Math.random() * 4) + 1); //random 1 - 4
            switch (randomNumber2) {
                case 1:
                    cloneDNA = cloneDNA + 'A';
                    break;
                case 2:
                    cloneDNA = cloneDNA + 'C';
                    break;
                case 3:
                    cloneDNA = cloneDNA + 'G';
                    break;
                case 4:
                    cloneDNA = cloneDNA + 'T';
                    break;
            }
        }
        else {
            cloneDNA = cloneDNA + DNA.charAt(i);
        }
        
        if (cloneDNA.length == DNA.length) {
            if (cloneDNA != DNA) {
                document.write(cloneDNA);
            }
            cloneDNA = "";
        }
    }
}

//var AreYouMyMommy = function(mom, child) {
//    if (mom.length == 0 || child.length == 0) {
//        if (mom.length == 0) {
//        return "Error: the variable containing the parent's DNA is empty";
//        }
//        else {
//        return "Error: the variable containing the child's DNA is empty";
//        }
//    }
//    
//    else {
//        //TODO: check to make sure string consists of ony ACGT chars
//        
//        if (mom.length != child.length) {
//            return "Error: DNA sequences are of different sizes";
//        }
//        
//        else {
//            var matches = 0;
//            for (var i = 0; i < mom.length; i++) {
//                if (mom.charAt(i) == child.charAt(i)) {
//                    matches++;
//                }
//            }
//            if ((matches / mom.length) < .26) {
//                return "I am not your mommy.";
//            }
//            else {
//                return "I am your mommy!";
//            }
//        }
//    }
//};

//var Person = function(eyeColor, hairColor) {
//  this.eyeColor = eyeColor;
//  this.hairColor = hairColor;
//}
//
//var CreateChild = function(parent1, parent2) {
//    var parents = [parent1, parent2];
//    var child = new Person(parents[Math.floor(Math.random() + 0.5)].eyeColor, parents[Math.floor(Math.random() + 0.5)].hairColor);
//    return child;
//}
        


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
	$("#buttonGroup").change(function() {
			$(".inputArea").text("");
			var selection = $(this).children(":selected").html();
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
				codeExample = "action Main\n\tinteger addedNumbers = add(5,10)\n\toutput addedNumbers\nend\n\naction add(integer a, integer b) returns integer\n\treturn a + b\nend";
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
	$("textarea.ide").keydown(function(e) {
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

var forgotPasswordShow = function() {
	$(".forgot-password-btn").on("click", function() {
		$(this).hide();
		$(this).parent().find(".forgot-password-form").show();
	});
}


var detectEnterKey = function() {
	var triggerModalPrimary = function(container) {
		if ($(container).hasClass("in")) {
			$(container + " .btn-primary").trigger("click");
		}
	}

	$('body').keydown(function (e){
	    if(e.keyCode == 13) { // Enter has been pressed
	        triggerModalPrimary("#modal-registration");
	        triggerModalPrimary("#modal-login");
	    }
	});
}


var submitLibraryWizard = function () {
    var checkboxEnableNext = function () {
        var checkbox = wizard.find(".checkbox input");
        checkbox.on("click", function () {
            var button = wizard.find("#wizard-1 .btn-next");
            var slideVal = button.attr("disabled");
            console.log(slideVal);
            if (slideVal == "disabled") {
                button
                    .removeAttr("disabled");

                $(button).on("click", function (e) {
                    wizard.carousel("next");
                    console.log(wizard)
                    e.preventDefault();
                });
            }
            else {
                button
                    .attr("disabled", "disabled")
                    .unbind("click");
            }
        });


    }

    var wizard = $('#submission-wizard');
    wizard.carousel({ 'interval': false });
    checkboxEnableNext(wizard);
}

var controlPanel = function () {
	// Change URL on dropdown for users
    $("#user-feedback-select").on("change", function () {
        var libraryId = $(this).val();
        if (libraryId != "") {
            window.location = "/submitted_library.php?id=" + libraryId;
        }
    });


	// Change URL on dropdown for reviewers
    $("#reviewer-feedback-select").on("change", function () {
        var libraryId = $(this).val();
        if (libraryId != "") {
            window.location = "/reviewer_feedback.php?id=" + libraryId;
        }
    });


	// Change URL on dropdown for admins
    $("#admin-pending-feedback-select").on("change", function () {
        var libraryId = $(this).val();
        if (libraryId != "") {
            window.location = "/submitted_library.php?id=" + libraryId;
        }
    });


    // Add a reviewer to a library
    $("#add-user-submit").on("click", function () {
    	$("#add-user-to-library .text-info, #add-user-to-library .text-success").remove();

        var data = {
            library_id: $("#add-user-to-library-library").val(),
            username: $("#add-user-to-library-user").val()
        }

        var endpoint = "/controllers/control_panel.controller.php?action=addUserToLibrary";
        $.ajax({
            type: "POST",
            url: endpoint,
            data: data,
            success: function (result) {
                result = result.replace(" ", "");

            	message = data.username + ' has been assigned to library "' + data.library_id;
				clasz = "text-success";

                $("#add-user-to-library").append('<span class="' + clasz + '" style="display:block;">' + message + '</span>');
                setTimeout(function () {
                    $("#add-user-to-library .text-success").fadeOut('slow', function () { $(this).remove(); });
                }, 2000);
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr, ajaxOptions, thrownError);
            }
        });
    });
}