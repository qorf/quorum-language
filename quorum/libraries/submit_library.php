<?php require_once("static/templates/pageheader.template.php"); ?>

<?php
function send_email() {
    $to      = 'nobody@example.com';
    $subject = 'A Library Submission has been made';
    $message = '';
    $headers = 'From: webmaster@example.com' . "\r\n" .
        'Reply-To: webmaster@example.com' . "\r\n" .
        'X-Mailer: PHP/' . phpversion();

    mail($to, $subject, $message, $headers);
}

function create_input($name, $placeholder, $textarea = false) {
    $value = empty($_POST[$name]) ? "" : $_POST[$name];
    if ($textarea) {
        print '<textarea id="' . $name .'" name="' . $name .'" placeholder="' . $placeholder .'">'. $value .'</textarea>';
    }
    else {
        print '<input type="text" id="' . $name .'" name="' . $name .'" placeholder="' . $placeholder .'" value="'. $value .'">';
    }    
}

function input_invalid($name) {
    return !(isset($_POST[$name]) && !empty($_POST[$name])); 
}

function error_message($msg) {
    return '<li class="text-error">' . $msg . '</span>';
}

function process_post() {
     if ($_GET['post'] == "true") {
        $errors = "";
        
        if (input_invalid("library-name")) {
            $errors .= error_message("Please enter a library name.");
        }
        if (input_invalid("author-name")) {
            $errors .= error_message("Please enter an author name.");
        }
        if (input_invalid("library-description")) {
            $errors .= error_message("Please enter a description for your library.");
        }
        if (input_invalid("library-usage")) {
            $errors .= error_message("Please enter usage instructions for the library.");
        }
        if (isset($_FILES["library-files"])) {
            if ($_FILES["library-files"]["size"] >= 524288) {
                $errors .= error_message("Your submission exceeds the maximum file size.");
            }
            if ($_FILES["library-files"]["type"] != "application/zip") {
                $errors .= error_message("Your submission must be a ZIP file.");
            }
        }
        else {
            $errors .= error_message("Please enter a submission file.");
        }
        if (isset($_FILES["library-supplements"])) {
            if ($_FILES["library-supplements"]["type"] != "application/zip") {
                $errors .= error_message("Your supplement file must be a ZIP file.");
            }
        }
        
        if ($errors != "") {
            return '<ul class=" container errors">' . $errors . '</ul>';
        }
        
        move_uploaded_file(
            $_FILES["library-files"]["tmp_name"],
            "/library-submissions/" . $_FILES["library-files"]["name"]
        );
        
        if (isset($_FILES["library-supplements"])) {
            move_uploaded_file(
                $_FILES["library-supplements"]["tmp_name"],
                "/library-submissions/supplements" . $_FILES["library-supplements"]["name"]
            );
        }
        
        return false;
    }
     return "";
}

$errors = process_post();

if ($errors != false) {
?>

<div id="library-submission-wizard-container container">
	<h1 class="container">Submit a Library to Quorum</h1>
    <h2>Your submission has been received!</h2>
    <p>Some text here about what happens with submission.</p>
</div>
    
<?php    
}
else {
?>

<div id="library-submission-wizard-container">
	<h1 class="container">Submit a Library to Quorum</h1>
    <?php print $errors; ?>
	<form id="library-submission" class="form-horizontal" method="post" action="?post=true" enctype="multipart/form-data">
		<div id="submission-wizard" class="carousel">
			<div class="carousel-inner">
				<div class="active item" id="wizard-1">
					<div class="container">
						<h2>Software License Agreement (BSD License)</h2>
						<h3>Copyright &copy; 2013 Quorum Language All rights reserved.</h3>
						<p>Redistribution and use of this software in source and binary forms, with or without modification, are permitted provided that the following conditions are met:</p>
						<ul>
							<li>Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.</li>
							<li>Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.</li>
						</ul>
						<p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.</p>
						<div class="actions clearfix">
							<a class="btn btn-primary btn-next pull-right" href="#submission-wizard" disabled>Next</a>
							<label class="checkbox pull-right">
							<input type="checkbox" id="agree-to-bsd" value="">
							I agree to submit my work under the BSD license
							</label>
						</div>
					</div>
				</div>
				<div class="item" id="wizard-2">
					<div class="container">
						<div class="control-group">
							<label class="control-label" for="library-name">Name of the Library Submission</label>
							<div class="controls">
                                <?php create_input("library-name", "Email"); ?>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="author-name">Name of the Submission's Author(s)</label>
							<div class="controls">
                                <?php create_input("author-name", "Author Name(s)"); ?>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="library-description">Description of the Library Submission</label>
							<div class="controls">
                                <?php create_input("library-description", "", true); ?>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="library-usage">Instructions on the Library's Usage</label>
							<div class="controls">
                                <?php create_input("library-usage", "", true); ?>
							</div>
						</div>
						<div class="actions clearfix">
							<a class="btn btn-previous pull-left" href="#submission-wizard" data-slide="previous">Back</a>
							<a class="btn btn-primary btn-next pull-right" href="#submission-wizard" data-slide="next">Next</a>
						</div>
					</div>
				</div>
				<div class="item" id="wizard-3">
					<div class="container">
						<div class="control-group">
							<label class="control-label" for="library-files">Library Submission Files</label>
							<div class="controls">
								<input type="file" id="library-files" name="library-files" placeholder="">
								<span class="help-block"><em>Note: ZIP files only. Please upload only text files in the archive and do not upload any binaries. <br />Maximum file size: 512KB</em></span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="library-supplements">Supplementary Files</label>
							<div class="controls">
								<input type="file" id="library-supplements" name="library-supplements" placeholder="">
								<span class="help-block">Upload any files relevant to the submission that can help users understand your submission, such as usability studies or testing results.</span>
							</div>
						</div>
						<div class="actions clearfix">
							<a class="btn btn-previous pull-left" href="#submission-wizard" data-slide="previous">Back</a>
							<input type="submit" class="btn btn-primary btn-submit pull-right" value="Submit">
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<?php
}
?>
<?php require_once("static/templates/pagefooter.template.php"); ?>