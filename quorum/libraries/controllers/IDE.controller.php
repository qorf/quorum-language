<?php
	$url = "http://localhost:8000"; // destination, like localhost:8000 or whatever
	$data = $_POST['code']; // make this your data object
	$ch = @curl_init();
	curl_setopt($ch, CURLOPT_POST, true);
	curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
	curl_setopt($ch, CURLOPT_URL, $url);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	$result = curl_exec($ch);
	curl_close($ch);
	var_dump($result);
?>