;<?php
	require("../models/codeSample.model.php");
	$code = $_POST['code'];
	$codeSample = new CodeSample($code);
	$returned = $codeSample->fetchOutput();
	if ($returned == 1)
	{
		var_dump($codeSample->getOutput());
		$result = 0;
		do {
			$result = $codeSample->updateCount();
		}while ($result < 1);
	}
	else
	{
		$url = "http://localhost:8000"; // destination, like localhost:8000 or whatever
		$data = $code; // make this your data object
		$ch = @curl_init();
		curl_setopt($ch, CURLOPT_POST, true);
		curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
		curl_setopt($ch, CURLOPT_URL, $url);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
		$result = curl_exec($ch);
		curl_close($ch);
		$codeSample->setOutput($result);
		var_dump($codeSample->getOutput());
		$result = 0;
		do {
			$result = $codeSample->insertCodeSample();
		}while ($result < 1);
		
	}
	
?>