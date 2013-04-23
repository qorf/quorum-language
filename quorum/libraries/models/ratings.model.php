<?php
	require_once("data.model.php");

	class Rating extends QuorumDataModel {
		function __construct() {
			parent::__construct();
		}

		public function addRatingToDatabase($ratingToUpdate) {
			$updateResult = $this->update($ratingToUpdate);

			if (is_int($updateResult)) {
				// if no rows are updated, try to insert and return the result of the insertion.
				// otherwise the update was successful so return true.
				return ($updateResult == 0) ? $this->insert() : 1;
			}

			return $updateResult; // if we got here, we know the update threw an exception
		}
	}

	class ClassRating extends Rating {
		private $table_name = "class_ratings";
		public $static_key = "";
		public $user = "";
		public $rating_name = "";
		public $rating_example = "";
		public $rating_description = "";

		function __construct($static_key, $user, $rating_name, $rating_example, $rating_description) {
			parent::__construct();
			$this->static_key = $static_key;
			$this->user = $user;
			$this->rating_name = $rating_name;
			$this->rating_example = $rating_example;
			$this->rating_description = $rating_description;
		}

		public function insert() {
			try {
				$sqlQuery = "INSERT INTO " . $this->table_name	 										
						  . " (static_key, user, rating_name, rating_example, rating_description)"
						  . " VALUES (?, ?, ?, ?, ?)";

				$valuesToPrepare = array($this->static_key, $this->user, $this->rating_name, $this->rating_example, $this->rating_description);
				$preparedStatement = $this->connection->prepare($sqlQuery);
				$preparedStatement->execute($valuesToPrepare);

				return $preparedStatement->rowCount();
			}
			catch (Exception $e) {
				return $e;
			}
		}

		public function update($ratingToUpdate) {
			try {
				$sqlQuery = null;
				$valuesToPrepare = null;

				switch ($ratingToUpdate) {
					case "name": 
						$sqlQuery = "UPDATE " . $this->table_name . " SET rating_name=? WHERE static_key=? AND user=?";
						$valuesToPrepare = array($this->rating_name, $this->static_key, $this->user);
						break;
					case "example":
						$sqlQuery = "UPDATE " . $this->table_name . " SET rating_example=? WHERE static_key=? AND user=?";
						$valuesToPrepare = array($this->rating_example, $this->static_key, $this->user);
						break;
					case "description":
						$sqlQuery = "UPDATE " . $this->table_name . " SET rating_description=? WHERE static_key=? AND user=?";
						$valuesToPrepare = array($this->rating_description, $this->static_key, $this->user);
						break;
				}

				$preparedStatement = $this->connection->prepare($sqlQuery);
				$preparedStatement->execute($valuesToPrepare);

				return $preparedStatement->rowCount();
			}
			catch (Exception $e) {
				return $e;
			}
		}
	}

	class ActionRating extends Rating {
		private $table_name = "action_ratings";
		public $class_static_key = "";
		public $static_key = "";
		public $user = "";
		public $rating_name = "";
		public $rating_example = "";
		public $rating_description = "";

		function __construct($class_static_key, $static_key, $user, $rating_name, $rating_example, $rating_description) {
			parent::__construct();
			$this->class_static_key = $class_static_key;
			$this->static_key = $static_key;
			$this->user = $user;
			$this->rating_name = $rating_name;
			$this->rating_example = $rating_example;
			$this->rating_description = $rating_description;
		}

		public function insert() {
			try {
				$sqlQuery = "INSERT INTO " . $this->table_name		 										
						  . " (class_static_key, static_key, user, rating_name, rating_example, rating_description)"
						  . " VALUES (?, ?, ?, ?, ?, ?)";

				$valuesToPrepare = array($this->class_static_key, $this->static_key, $this->user, $this->rating_name, $this->rating_example, $this->rating_description);
				$preparedStatement = $this->connection->prepare($sqlQuery);

				$preparedStatement->execute($valuesToPrepare);

				return $preparedStatement->rowCount();
			}
			catch (Exception $e) {
				return $e;
			}
		}

		public function update($ratingToUpdate) {
			try {
				$sqlQuery = null;
				$valuesToPrepare = null;

				switch ($ratingToUpdate) {
					case "name": 
						$sqlQuery = "UPDATE " . $this->table_name . " SET rating_name=? WHERE static_key = ? AND user = ? AND class_static_key = ?";
						$valuesToPrepare = array($this->rating_name, $this->static_key, $this->user, $this->class_static_key);
						break;
					case "example":
						$sqlQuery = "UPDATE " . $this->table_name . " SET rating_example=? WHERE static_key = ? AND user = ? AND class_static_key = ?";
						$valuesToPrepare = array($this->rating_example, $this->static_key, $this->user, $this->class_static_key);
						break;
					case "description":
						$sqlQuery = "UPDATE " . $this->table_name . " SET rating_description=? WHERE static_key = ? AND user = ? AND class_static_key = ?";
						$valuesToPrepare = array($this->rating_description, $this->static_key, $this->user, $this->class_static_key);
						break;
				}
				
				$preparedStatement = $this->connection->prepare($sqlQuery);
				$preparedStatement->execute($valuesToPrepare);

				return $preparedStatement->rowCount();
			}
			catch (Exception $e) {
				return $e;
			}
		}
	}

	class ParameterRating extends Rating {
		private $table_name = "parameter_ratings";
		public $class_static_key = "";
		public $action_static_key = "";
		public $static_key = "";
		public $user = "";
		public $rating_name = "";
		public $rating_description = "";

		function __construct($class_static_key, $action_static_key, $static_key, $user, $rating_name, $rating_description) {
			parent::__construct();
			$this->class_static_key = $class_static_key;
			$this->action_static_key = $action_static_key;
			$this->static_key = $static_key;
			$this->user = $user;
			$this->rating_name = $rating_name;
			$this->rating_description = $rating_description;
		}

		public function insert() {
			try {
				$sqlQuery = "INSERT INTO " . $this->table_name			 										
						  . " (class_static_key, action_static_key, static_key, user, rating_name, rating_description)"
						  . " VALUES (?, ?, ?, ?, ?, ?)";

				$valuesToPrepare = array($this->class_static_key, $this->action_static_key, $this->static_key, $this->user, $this->rating_name, $this->rating_description);
				
				$preparedStatement = $this->connection->prepare($sqlQuery);
				$preparedStatement->execute($valuesToPrepare);


				return $preparedStatement->rowCount();
			}
			catch (Exception $e) {
				return $e;
			}
		}

		public function update($ratingToUpdate) {
			try {
				$sqlQuery = null;
				$valuesToPrepare = null;

				switch ($ratingToUpdate) {
					case "name": 
						$sqlQuery = "UPDATE " . $this->table_name . " SET rating_name=? WHERE static_key = ? AND user = ? AND class_static_key = ? AND action_static_key = ?";
						$valuesToPrepare = array($this->rating_name, $this->static_key, $this->user, $this->class_static_key, $this->action_static_key);
						break;
					case "description":
						$sqlQuery = "UPDATE " . $this->table_name . " SET rating_description=? WHERE static_key = ? AND user = ? AND class_static_key = ? AND action_static_key = ?";
						$valuesToPrepare = array($this->rating_description, $this->static_key, $this->user, $this->class_static_key, $this->action_static_key);
					 	break;
				}
				
				$preparedStatement = $this->connection->prepare($sqlQuery);
				$preparedStatement->execute($valuesToPrepare);

				return $preparedStatement->rowCount();
			}
			catch (Exception $e) {
				return $e;
			}
		}
	}

	class RatingsForLibrary extends QuorumDataModel {
		public $user = "";
		public $class_static_key = "";
		public $class = null;
		public $actions = null;
		public $parameters = null;

		function __construct($user, $class_static_key) {
			parent::__construct();
			$this->user = $user;
			$this->class_static_key = $class_static_key;

			$this->class = array();
			$this->actions = array();
			$this->parameters = array();
		}

		public function getRatings() {
			$this->getClassRatings();
			$this->getActionRatings();
			$this->getParameterRatings();

			$data = array(
					"class" => $this->class,
					"actions" => $this->actions,
					"parameters" => $this->parameters
				);

			return $data;
		}

		public function getClassRatings() {
			$sqlQuery = "SELECT * FROM class_ratings WHERE static_key=? AND user=?";
			$valuesToPrepare = array($this->class_static_key, $this->user);

			$preparedStatement = $this->connection->prepare($sqlQuery);
			$preparedStatement->execute($valuesToPrepare);

			if ($preparedStatement->rowCount() == 1) {
				$this->class = $preparedStatement->fetchAll();

				return $result[0];
			}			
		}

		public function getActionRatings() {
			$sqlQuery = "SELECT * FROM action_ratings WHERE class_static_key=? AND user=?";
			$valuesToPrepare = array($this->class_static_key, $this->user);

			$preparedStatement = $this->connection->prepare($sqlQuery);
			$preparedStatement->execute($valuesToPrepare);

			$this->actions = $preparedStatement->fetchAll();			
		}

		public function getParameterRatings() {
			$sqlQuery = "SELECT * FROM parameter_ratings WHERE class_static_key=? AND user=?";
			$valuesToPrepare = array($this->class_static_key, $this->user);

			$preparedStatement = $this->connection->prepare($sqlQuery);
			$preparedStatement->execute($valuesToPrepare);

			$this->parameters = $preparedStatement->fetchAll();
		}

	}

?>
