$(function() {
	slideInSublibraries();

	//autoComplete();

	openModalFromURL();

	registrationValidateAndSubmit();

	userSignIn();

	expandAndCollapseLeftSideMenu();
	
    submitCodeSample();
    
    overlayFunctions();

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

var slideNr = 1;

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

function getCookie(name) {
  var value = "; " + document.cookie;
  var parts = value.split("; " + name + "=");
  if (parts.length === 2) return parts.pop().split(";").shift();
} 

function getGAClient(){
    var client = ga.getAll()[0].get('clientId');
    return client;
};

var submitCodeSample = function(){
    //IDE
    $("#run-button").on("click", function(e) {
            e.preventDefault();
            $(".outputArea").text("");
            var pageNumber = window.location.pathname;
            var pageURL = "";
            pageURL =  window.location.protocol + "//" + window.location.host + pageNumber;
            pageNumber = pageNumber.charAt(pageNumber.length - 5);
            var id = getCookie("PHPSESSID");
            var ga_cookie = getGAClient();
            var tempCode = $('.inputArea').val();
            var codeData = {code: tempCode};
            $.ajax({
                    type: "GET",
                    url: "http://quorumlanguage.com/proxy.php",
                    data: codeData,
                    success: function(result){
                        var hadCompilerError = false;
                        //console.log(result);
                        try {
                            $("#IDE-output").html(eval(result));
//                            $("#IDE-output").html('GATTACA<br>Welcome to GATTACA<br>');
                        } catch (e) {
                            if (e instanceof SyntaxError) {
                                $("#IDE-output").text(result);
                                hadCompilerError = true;
                            }
                        }
                        var completedcheck = 0;
                        //check hour of code output based on the page
                        if(!hadCompilerError) {

                            if (pageNumber >= 1 && pageNumber <= 6) { //7th page has no exercises
                                completedcheck = checkOutput(pageNumber, $('#IDE-output').html());
                            }
                        }

                        var reportData = { code: tempCode, uuid: id, pagenumber: pageNumber, slidenumber: slideNr, resultCode: result, completed: completedcheck, pageurl: pageURL, gacookie: ga_cookie};
                        $.ajax({
                            type: "POST",
                            url: "http://quorumlanguage.com/quorum_logger.php",
                            data: reportData,
                            success: function(result) {
                                console.log("Logged Result " + result);
                            },
                            error: function (xhr, ajaxOptions, thrownError) {
                                console.log(xhr, ajaxOptions, thrownError);
                            }

                        });
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                            //console.log(xhr, ajaxOptions, thrownError);
                            $(".outputArea").html("Error: Could not connect to server: " + thrownError.toString());
                            var reportData = { code: tempCode, uuid: id, pagenumber: pageNumber, slidenumber: slideNr, resultCode: thrownError.toString(), completed: 0, pageurl: pageURL, gacookie: ga_cookie};
                            $.ajax({
                                type: "POST",
                                url: "http://quorumlanguage.com/quorum_logger.php",
                                data: reportData,
                                success: function(result) {
                                    console.log("Logged Result " + result);
                                },
                                error: function (xhr, ajaxOptions, thrownError) {
                                    console.log(xhr, ajaxOptions, thrownError);
                                }
                            });
                    }
            });
    })
}

var checkOutput = function(pageNumber, output) {
    var outputArray = [];
    outputArray[0] = 'GATTACA<br>Welcome to GATTACA<br>';
    outputArray[1] = 'The area is 28.';
    outputArray[2] = 'Hello!<br>';
    outputArray[3] = '2<br>4<br>6<br>8<br>10<br>';
    outputArray[4] = '0 is even<br>1 is odd<br>2 is even<br>3 is odd<br>4 is even<br>5 is odd<br>6 is even<br>7 is odd<br>8 is even<br>9 is odd<br>';
    outputArray[5] = 'Programming in Quorum is fun!<br>';
    
    if (pageNumber == 2) {
        output = output.substring(0,15); //to get around any rounding errors
    }
    
    //Should make this case insensitive
    if (outputArray[pageNumber - 1].toLowerCase() == output.toLowerCase()) {
        //success
        showModal($('#modal'));
        return 1;
    }
    else {
        //failure
        //alert("The result was not correct, try again.");
        return 0;
    }
    
}

var overlayFunctions = function() {
    
    $("#modal").css('height', $(document).height());
    
    $("#modalCloseButton").click(function() {
        $("#modal").hide();
    });
    
    //code below hides overlay if user clicks in the grey area
    //not reccommended by the accessible modal overlay
    //basically: more user-friendly, less accessible
//    $("#overlay").on('click', function(e) {
//    if (e.target !== this)
//        return;
//    $("#overlay").hide();
//    });
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











/* Accessible Modal Window
 
 ============================================
 License for Application
 ============================================
 
 This license is governed by United States copyright law, and with respect to matters
 of tort, contract, and other causes of action it is governed by North Carolina law,
 without regard to North Carolina choice of law provisions.  The forum for any dispute
 resolution shall be in Wake County, North Carolina.
 
 Redistribution and use in source and binary forms, with or without modification, are
 permitted provided that the following conditions are met:
 
 1. Redistributions of source code must retain the above copyright notice, this list
 of conditions and the following disclaimer.
 
 2. Redistributions in binary form must reproduce the above copyright notice, this
 list of conditions and the following disclaimer in the documentation and/or other
 materials provided with the distribution.
 
 3. The name of the author may not be used to endorse or promote products derived from
 this software without specific prior written permission.
 
 THIS SOFTWARE IS PROVIDED BY THE AUTHOR "AS IS" AND ANY EXPRESS OR IMPLIED
 WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE
 LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 
 */

// jQuery formatted selector to search for focusable items
var focusableElementsString = "a[href], area[href], input:not([disabled]), select:not([disabled]), textarea:not([disabled]), button:not([disabled]), iframe, object, embed, *[tabindex], *[contenteditable]";

// store the item that has focus before opening the modal window
var focusedElementBeforeModal;

$(document).ready(function() {
//    jQuery('#startModal').click(function(e) {
//        showModal($('#modal'));
//    });
//    jQuery('#cancel').click(function(e) {
//        hideModal();
//    });
//    jQuery('#cancelButton').click(function(e) {
//        hideModal();
//    });
//    jQuery('#enter').click(function(e) {
//        enterButtonModal();
//    });
    jQuery('#modalCloseButton').click(function(e) {
        hideModal();
    });
    jQuery('#modalCloseButton').keydown(function(event) {
        trapSpaceKey($(this), event, hideModal);
    })
    jQuery('#modal').keydown(function(event) {
        trapTabKey($(this), event);
    })
    jQuery('#modal').keydown(function(event) {
        trapEscapeKey($(this), event);
    })

});


function trapSpaceKey(obj, evt, f) {
    // if space key pressed
    if (evt.which == 32) {
        // fire the user passed event
        f();
        evt.preventDefault();
    }
}

function trapEscapeKey(obj, evt) {

    // if escape pressed
    if (evt.which == 27) {
        hideModal();
//        // get list of all children elements in given object
//        var o = obj.find('*');
//
//        // get list of focusable items
//        var cancelElement;
//        cancelElement = o.filter("#cancel")
//
//        // close the modal window
//        cancelElement.click();
        evt.preventDefault();
    }

}

function trapTabKey(obj, evt) {

    // if tab or shift-tab pressed
    if (evt.which == 9) {

        // get list of all children elements in given object
        var o = obj.find('*');

        // get list of focusable items
        var focusableItems;
        focusableItems = o.filter(focusableElementsString).filter(':visible')

        // get currently focused item
        var focusedItem;
        focusedItem = jQuery(':focus');

        // get the number of focusable items
        var numberOfFocusableItems;
        numberOfFocusableItems = focusableItems.length

        // get the index of the currently focused item
        var focusedItemIndex;
        focusedItemIndex = focusableItems.index(focusedItem);

        if (evt.shiftKey) {
            //back tab
            // if focused on first item and user preses back-tab, go to the last focusable item
            if (focusedItemIndex == 0) {
                focusableItems.get(numberOfFocusableItems - 1).focus();
                evt.preventDefault();
            }

        } else {
            //forward tab
            // if focused on the last item and user preses tab, go to the first focusable item
            if (focusedItemIndex == numberOfFocusableItems - 1) {
                focusableItems.get(0).focus();
                evt.preventDefault();
            }
        }
    }

}

//function setInitialFocusModal(obj) {
//    // get list of all children elements in given object
//    var o = obj.find('*');
//
//    // set focus to first focusable item
//    var focusableItems;
//    focusableItems = o.filter(focusableElementsString).filter(':visible').first().focus();
//
//}

//function enterButtonModal() {
//    // BEGIN logic for executing the Enter button action for the modal window
//    alert('form submitted');
//    // END logic for executing the Enter button action for the modal window
//    hideModal();
//}

function showModal(obj) {
    jQuery('#mainPage').attr('aria-hidden', 'true'); // mark the main page as hidden
    jQuery('#modalOverlay').css('display', 'block'); // insert an overlay to prevent clicking and make a visual change to indicate the main apge is not available
    jQuery('#modal').css('display', 'block'); // make the modal window visible
    jQuery('#modal').attr('aria-hidden', 'false'); // mark the modal window as visible

    // save current focus
    focusedElementBeforeModal = jQuery(':focus');

    // get list of all children elements in given object
    var o = obj.find('*');

    // Safari and VoiceOver shim
    // if VoiceOver in Safari is used, set the initial focus to the modal window itself instead of the first keyboard focusable item. This causes VoiceOver to announce the aria-labelled attributes. Otherwise, Safari and VoiceOver will not announce the labels attached to the modal window.

    // set the focus to the first keyboard focusable item
    o.filter(focusableElementsString).filter(':visible').first().focus();


}

function hideModal() {
    jQuery('#modalOverlay').css('display', 'none'); // remove the overlay in order to make the main screen available again
    jQuery('#modal').css('display', 'none'); // hide the modal window
    jQuery('#modal').attr('aria-hidden', 'true'); // mark the modal window as hidden
    jQuery('#mainPage').attr('aria-hidden', 'false'); // mark the main page as visible

    // set focus back to element that had it before the modal was opened
    focusedElementBeforeModal.focus();
}