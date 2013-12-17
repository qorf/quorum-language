<?php
	require_once("../models/codeSample.model.php");
	$code = $_POST['code'];
	$codeSample = new CodeSample($code);
	$returned = $codeSample->fetchOutput();
	if ($returned == 1)
	{
		print($codeSample->getOutput());
		$result = 0;
		do {
			$result = $codeSample->updateCount();
		} while ($result < 1);
	}
	else
	{
		$url = "http://quorum.cs.siue.edu"; // destination, like localhost:8000 or whatever
		$ch = @curl_init();
		curl_setopt($ch, CURLOPT_POST, true);
		curl_setopt($ch, CURLOPT_POSTFIELDS, $code);
		curl_setopt($ch, CURLOPT_URL, $url);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
		$result = curl_exec($ch);
		curl_close($ch);
		$insert = false;
		if ((strrpos($result,"success") < strrpos($result,"|"))||(strrpos($result, "fail") < strrpos($result,"|")))
		{
			$codeSample->setOutput($result);
			print($codeSample->getOutput());
			$result = 0;
			do {
				$result = $codeSample->insertCodeSample();
			}while ($result < 1);	
		}
		else {
			print $result;
			print("Could not receive a response from the Quorum server. ");		
		}
	}
	
?>