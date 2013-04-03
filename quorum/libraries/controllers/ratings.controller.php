<?php
	require("../models/ratings.model.php");

	function submitClassRating($componentType) {
		$componentType = str_replace("class-", "", $componentType);
		$ratingName = ($componentType == "name") ? $_POST['rating'] : null;
		$ratingExample = ($componentType == "example") ? $_POST['rating'] : null;
		$ratingDescription = ($componentType == "description") ? $_POST['rating'] : null;

		$rating = new ClassRating($_POST['classstatickey'], $_POST['username'], $ratingName, $ratingExample, $ratingDescription);
		print $rating->addRatingToDatabase($componentType);
	}

	function submitActionRating($componentType) {
		$componentType = str_replace("action-", "", $componentType);
		$ratingName = ($componentType == "name") ? $_POST['rating'] : null;
		$ratingExample = ($componentType == "example") ? $_POST['rating'] : null;
		$ratingDescription = ($componentType == "description") ? $_POST['rating'] : null;

		$rating = new ActionRating($_POST['classstatickey'], $_POST['actionkey'], $_POST['username'], $ratingName, $ratingExample, $ratingDescription);
		print $rating->addRatingToDatabase($componentType);
	}

	function submitParameterRating($componentType) {
		$componentType = str_replace("parameter-", "", $componentType);
		$ratingName = ($componentType == "name") ? $_POST['rating'] : null;
		$ratingExample = ($componentType == "example") ? $_POST['rating'] : null;
		$ratingDescription = ($componentType == "description") ? $_POST['rating'] : null;

		$rating = new ActionRating($_POST['classstatickey'], $_POST['actionkey'], $_POST['username'], $ratingName, $ratingExample, $ratingDescription);
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

	function router($action) {
		switch ($action) {
			case "submitRating": submitRating(); break;
		}
	}

	router($_GET['action']);
?>