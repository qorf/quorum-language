$(function() {
	createRatingControls();

	fiveStarRatings();
});

var createRatingControls = function() {
	var template = doT.template($('#template-ratings-controls').text());

	$.each($(".controllable"), function() {
		var componentType = $(this).data("componenttype");
		componentType = (componentType !== undefined) ? componentType.replace(/-/g," ") : "component";
		var templateData = { id: $(this).data("id"), componentType: componentType }; // TODO: get real value
		$(this).append(template(templateData));
	});

	$('.star-ratings a').tooltip({'selector': '', 'placement': 'bottom'});

	var getRatingForId = function(id) {
		// TODO: make an ajax call to the database to get the real rating
		
		return 3;
	}
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
		var username = $("input#hidden-username").val();
		var classStaticKey = $("input#classkey").val();
		var componentType = controllable.data("componenttype")
		var postData = { username: username, classstatickey: classStaticKey, rating: starNumber, componenttype: componentType };
		switch (componentType) {
			case "class-name": case "class-description": case "class-example": break;
			case "action-name": case "action-description": case "action-example": 
				postData['actionkey'] = controllable.data("actionkey");
				break;
			case "parameter-name": case "parameter-description":
				postData['actionkey'] = controllable.closest(".action").find(".controllable[data-componenttype=action-name]").data("actionkey");
				postData['parameterkey'] = controllable.data("parameterkey");
				break;
		}

		$.ajax({
			type: "POST",
			url: "/controllers/ratings.controller.php?action=submitRating", // TODO: fix this URL
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

			postRating($(this).closest(".controllable"), starNumber);
		}
	});
}