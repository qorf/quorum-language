<?php
	class database {
		/* // Production 
        public $host = "198.38.82.101";
		public $name = "stefika_sodbeans_users";
		public $username = "stefika_sodbeans";
		public $password = "sodbeansSucks";*/

        // Dev
		public $host = "198.38.82.101";
		public $name = "stefika_quorlang_test";
		public $username = "stefika_qltest";
		public $password = "S1m0nP3GG!";
	}

	function saltPassword($password) {
		$password_salt = '$0db34n$'; 
		return $password_salt . $password . $password_salt;
	}

	function secretGooglePassword() {
		return "Q8I5AP4L7A9I0I4C3AS";
	}

	function domainRoot() {
		//return "http://quorumlanguage.com/";
		return "http://test.quorumlanguage.com/";
	}
?>