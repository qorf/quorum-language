<?php

class Email {
	public $to = null;
	public $from = null;
	public $subject = null;
	public $message = null;

	function __construct($to, $from, $subject, $message) {
		$this->to = $to;
		$this->from = $from;
		$this->subject = $subject;
		$this->message = $message;
	}

	function send() {
	    $headers = 'From: ' . $this->from . "\r\n" .
	        'Reply-To: ' . $this->from . "\r\n" .
	        'MIME-Version: 1.0' . "\r\n" .
			'Content-type: text/html; charset=iso-8859-1' . "\r\n" .
	        'X-Mailer: PHP/' . phpversion();

	    mail($this->to, $this->subject, $this->message, $headers);
	}
}