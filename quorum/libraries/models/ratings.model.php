<?php
	class QuorumDataModel {
		public function connectToDatabase() {
			$databaseHost = "home.cs.siue.edu";
			$databaseName = "quorum";
			$databaseUsername = "quorum";
			$databasePassword ="quorum-123";

			$conn = new PDO('mysql:dbname=' . $databaseName . ';host=' . $databaseHost . ';charset=utf8', $databaseUsername, $databasePassword);
			$conn->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
			$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

			return $conn;
		}
	}


	class Rating extends QuorumDataModel {
		public $id = "";
		public $rating = "";
		public $componentType = "";
		public $userId = "";
		public $type = "";

		public function getTableNameForType() {
			return $this->type . "Ratings";
		}

		public function getIdColumnNameForType() {
			return $this->type . "Id";
		}

		public function insert() {
			$connection = $this->connectToDatabase();

			$sqlQuery = "INSERT INTO " . getTableNameForType() . " (" . getIdColumnNameForType() . ", rating, componentType, userId) "
					  . "VALUES (:id, :rating, :componentType, :userId)";

			$preparedStatement = $connection->prepare($sqlQuery);
			$preparedStatement->bindValue(":id", $this->id);
			$preparedStatement->bindValue(":rating", $this->rating);
			$preparedStatement->bindValue(":componentType", $this->componentType);
			$preparedStatement->bindValue(":userId", $this->userId);
			$preparedStatement->execute();

			return true;
		}
	}


	class ClassRating extends Rating {
		public $type = "class";
	}


	class ActionRating extends Rating {
		public $type = "action";
	}


	class ParameterRating extends Rating {
		public $type = "parameter";
	}

	$class = new ActionRating();
?>