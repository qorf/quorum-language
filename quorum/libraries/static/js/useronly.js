$(function() {
	createRatingControls();

	fiveStarRatings();

	getRatingsForLibrary();
});

var createRatingControls = function() {
	var template = doT.template($('#template-ratings-controls').text());

	$.each($(".controllable"), function() {
		var controllable = $(this);

		var componentType = controllable.data("componenttype");
		componentType = (componentType !== undefined) ? componentType : "component";

		var templateData = { componentType: componentType };
		switch (componentType) {
			case "class-name": case "class-description": case "class-example": 
				templateData['classkey'] = $("input#classkey").val();
				break;
			case "action-name": case "action-description": case "action-example": 
				templateData['classkey'] = $("input#classkey").val();
				templateData['actionkey'] = controllable.data("actionkey");
				break;
			case "parameter-name": case "parameter-description":
				templateData['classkey'] = $("input#classkey").val();
				templateData['actionkey'] = controllable.closest(".action").find(".controllable[data-componenttype=action-name]").data("actionkey");
				templateData['parameterkey'] = controllable.data("parameterkey");
				break;
		}

		templateData['choice'] = componentType.replace(/-/g, ' ');

		$(this).append(template(templateData));

		console.log(componentType);
	});

	$('.star-ratings a').tooltip({'selector': '', 'placement': 'bottom'});
}

var fiveStarRatings = function(starRatingsList) {
	var numberOfStars = function (clickTarget) {
		return ($(clickTarget).attr('class').split(/-/))[1]; // gets the class, splits at the hyphen, and grabs the number
	}

	var setStars = function(element, starNumber) {
		$(element)
			.removeClass("stars-0").removeClass("stars-1").removeClass("stars-2").removeClass("stars-3").removeClass("stars-4").removeClass("stars-5")
			.addClass("stars-" + starNumber);
	}

	var postRating = function(controllable, starNumber) {
		var username = $("input[name=hidden-username]").val();
		var classStaticKey = $("input#classkey").val();
		var componentType = controllable.data("componenttype")
		var postData = { username: username, classstatickey: classStaticKey, rating: starNumber, componenttype: componentType };

		switch (componentType) {
			case "class-name": case "class-description": case "class-example": break;
			case "action-name": case "action-description": case "action-example": 
				postData['actionkey'] = controllable.data("actionkey");
				console.log("action");
				break;
			case "parameter-name": case "parameter-description":
				postData['actionkey'] = controllable.closest(".action").find(".controllable[data-componenttype=action-name]").data("actionkey");
				postData['parameterkey'] = controllable.data("parameterkey");
				console.log("parameter");
				break;
		}
		
		$.ajax({
			type: "POST",
			url: "/controllers/ratings.controller.php?action=submitRating",
			dataType: "json",
			data: postData,
			success: function(result) {
				// show a success message
				console.info(result);
			},
			error: function (xhr, ajaxOptions, thrownError) {
				// show an error message
				console.error(xhr.status);
				console.error(thrownError);
			}
		});
	}

	$(".star-ratings li").on({
		mouseenter: function() {
			setStars($(this).parent(), numberOfStars(this));
		},
		mouseleave: function() { 
			setStars($(this).parent(), 0);
		},
		click: function(e) {
			e.preventDefault();
			var starNumber = numberOfStars(this);
			setStars($(this).parent(), starNumber);

			$(this).parent().find('li').unbind('mouseenter').unbind('mouseleave');

			var template = doT.template($('#template-ratings-success').text());
			var templateHtml = template();
			var container = $(this).closest(".ratings-controls");
			container.find(".ratings-success").remove();
			container.append(templateHtml);

			if ($(this).hasClass("existing") == false) {
				postRating($(this).closest(".controllable"), starNumber);
			}
			else {
				$(this).removeClass("existing");
			}
		}
	});
}

var getRatingsForLibrary = function() {
	var triggerRating = function (element, rating) {
		console.log(element,rating);
		if( Math.floor(rating) == rating && $.isNumeric(rating)) {
			element.find(".star-ratings .star-" + rating).addClass("existing").trigger("click");
		}
	}

	var showExistingRatings = function(data) {
		var clazz = data["class"];
		var actions = data["actions"]; 
		var parameters = data["parameters"];

		if (clazz.length > 0) {
			var ratingData = clazz[0];
			triggerRating($(".controllable[data-componenttype='class-name']"), ratingData["rating_name"]);
			triggerRating($(".controllable[data-componenttype='class-description']"), ratingData["rating_description"]);
			triggerRating($(".controllable[data-componenttype='class-example']"), ratingData["rating_example"]);
		}

		$.each( clazz, function( key, value ) {
			var components = $(".controllable[data-classkey='" + ratingData["static_key"] + "']");

			$.each( components, function(key, value) {
				var type = $(this).data("componenttype");
				var rating = 0;

				switch (type) {
					case "action-name": rating = ratingData["rating_name"]; break;
					case "action-description": rating = ratingData["rating_description"]; break;
					case "action-example": rating = ratingData["rating_example"]; break;
				}

				triggerRating($(this), rating);
			});
		});

		$.each( actions, function( key, ratingData ) {
			var components = $(".controllable[data-actionkey='" + ratingData["static_key"] + "']");

			$.each( components, function(key, value) {
				var type = $(this).data("componenttype");
				var rating = 0;

				switch (type) {
					case "action-name": rating = ratingData["rating_name"]; break;
					case "action-description": rating = ratingData["rating_description"]; break;
					case "action-example": rating = ratingData["rating_example"]; break;
				}

				triggerRating($(this), rating);
			});
		});

		$.each( parameters, function( key, ratingData ) {
			var container = $(".controllable[data-actionkey='" + ratingData["action_static_key"] + "']").closest(".action");
			var components = container.find(".controllable[data-parameterkey='" + ratingData["static_key"] + "']");

			$.each( components, function(key, value) {
				var type = $(this).data("componenttype");
				var rating = 0;

				switch (type) {
					case "parameter-name": rating = ratingData["rating_name"]; break;
					case "parameter-description": rating = ratingData["rating_description"]; break;
				}

				if( Math.floor(rating) == rating && $.isNumeric(rating)) {
					$(this).find(".star-ratings .star-" + rating).addClass("existing").trigger("click");
				}
			});
		});
	}

	var postData = { classstatickey: $("input#classkey").val() };
	
	$.ajax({
		type: "POST",
		url: "/controllers/ratings.controller.php?action=getRatingsForLibrary", 
		dataType: "json",
		data: postData,
		success: function(result) {
			// show a success message
			showExistingRatings(result);
			//console.info(result);
		},
		error: function (xhr, ajaxOptions, thrownError) {
			// show an error message
			console.error(xhr.status);
			console.error(thrownError);
		}
	});

}