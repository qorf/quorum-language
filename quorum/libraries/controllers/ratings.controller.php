<?php
	require("../models/ratings.model.php");
	require("../models/user.model.php");

	function checkIfValidRating($rating) {
		try {
			if (property_exists($rating, 'rating_example')) {
				if ($rating->rating_example != NULL) {
					if (($rating->rating_example > 5) || ($rating->rating_example < 1)) {
						return false;
					}
				}
			}

			if ($rating->rating_name != NULL) {
				if (($rating->rating_name > 5) || ($rating->rating_name < 1)) {
					return false;
				}
			}

			if ($rating->rating_description != NULL) {
				if (($rating->rating_description > 5) || ($rating->rating_description < 1)) {
					return false;
				}
			}
		}
		catch (Exception $ex) {
			return "error";
		}

		$user = new User(null, $_COOKIE['username'], $_COOKIE['password'], null);
		if ($user->checkLogin() == false) {
			return false;
		}

		return true;
	}

	function submitClassRating($componentType) {
		$componentType = str_replace("class-", "", $componentType);
		$ratingName = ($componentType == "name") ? $_POST['rating'] : null;
		$ratingExample = ($componentType == "example") ? $_POST['rating'] : null;
		$ratingDescription = ($componentType == "description") ? $_POST['rating'] : null;

		$rating = new ClassRating($_POST['classstatickey'], $_COOKIE['username'], $ratingName, $ratingExample, $ratingDescription);
		
		if (checkIfValidRating($rating) == false) { print "0"; }

		print $rating->addRatingToDatabase($componentType);
	}

	function submitActionRating($componentType) {
		$componentType = str_replace("action-", "", $componentType);
		$ratingName = ($componentType == "name") ? $_POST['rating'] : null;
		$ratingExample = ($componentType == "example") ? $_POST['rating'] : null;
		$ratingDescription = ($componentType == "description") ? $_POST['rating'] : null;

		$rating = new ActionRating($_POST['classstatickey'], $_POST['actionkey'], $_COOKIE['username'], $ratingName, $ratingExample, $ratingDescription);
		
		if (checkIfValidRating($rating) == false) { print "0"; }

		print $rating->addRatingToDatabase($componentType);
	}

	function submitParameterRating($componentType) {
		$componentType = str_replace("parameter-", "", $componentType);
		$ratingName = ($componentType == "name") ? $_POST['rating'] : null;
		$ratingDescription = ($componentType == "description") ? $_POST['rating'] : null;

		$rating = new ParameterRating($_POST['classstatickey'], $_POST['actionkey'], $_POST['parameterkey'], $_COOKIE['username'], $ratingName, $ratingDescription);
		
		if (checkIfValidRating($rating) == false) { print "0"; }

		print $rating->addRatingToDatabase($componentType);
	}

	function submitRating() {
		$componentType = $_POST['componenttype'];
		switch ($componentType) {
			case "class-name": case "class-example": case "class-description": 
				submitClassRating($componentType); break;
			case "action-name": case "action-example": case "action-description":
				submitActionRating($componentType); break;
			case "parameter-name": case "parameter-description": 
				submitParameterRating($componentType); break;
		}
	}

	function getRatingsForLibrary() {
		$ratings = new RatingsForLibrary($_COOKIE['username'], $_POST['classstatickey']);

		print json_encode($ratings->getRatings());
	}

	function router($action) {
		switch ($action) {
			case "submitRating": submitRating(); break;
			case "getRatingsForLibrary": getRatingsForLibrary(); break;
		}
	}

	router($_GET['action']);
?>