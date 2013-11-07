<?php require_once("static/templates/pageheader.template.php"); ?>

<?php
require("models/librarySubmission.model.php");
require("models/user.model.php");
require("model/badge.model.php");

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


function insert_to_database() {
    $library_slug = slugify($_POST['library-name']);
    
    $check_for_id_existence = new LibrarySubmission($library_slug, null, null, null, null, null, null, null, null, null, null);
    $library_exists = !($check_for_id_existence->getSubmissionByID());
    
    if ($library_exists) {    
        $submissionURL = "/submissions/" . $_FILES["library-supplements"]["name"];
        $supplementaryFilesURL = isset($_FILES["library-supplements"]) ? "/submissions/supplements/" . $_FILES["library-supplements"]["name"] : "";
    
        $submission = new LibrarySubmission($library_slug, $_POST['library-name'], $_COOKIE['username'], $_POST['author-name'], $_POST['library-description'], $_POST['library-usage'], $submissionURL, $supplementaryFilesURL, 1, "pending-reviewer", date("Y-m-d H:i:s"));
        try {
            $submission->insertSubmission();            
            return true;
        }
        catch (Exception $ex) {
            return false; 
        }   
    }
    return false;
}

function email_administrators() {
    // Check that the library ID exists, and if so, append a number to it. 
}

function upload_files() {
    try {
        $submissions_save_location = "/home/stefika/public_html/test/submissions/" . $_FILES["library-files"]["name"];
        $tmp_name =  $_FILES["library-files"]["tmp_name"];
        $result = move_uploaded_file( $tmp_name, $submissions_save_location );
    
        if (isset($_FILES["library-supplements"])) {
            $tmp_name =  $_FILES["library-supplements"]["tmp_name"];
            $supplements_save_location = "/home/stefika/public_html/test/submissions/supplements/" . $_FILES["library-supplements"]["name"];
            move_uploaded_file( $tmp_name, $supplements_save_location );
        }
        return true;
    }
    catch (Exception $ex) {
        return false;
    }
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
            $file_type = $_FILES["library-files"]["type"];
            if ($file_type != "application/zip" && $file_type != "application/x-zip-compressed") {
                $errors .= error_message("Your submission must be a ZIP file.");
            }
        }
        else {
            $errors .= error_message("Please enter a submission file.");
        }
        if (isset($_FILES["library-supplements"])) {
            $file_type = $_FILES["library-supplements"]["type"];
            if ($file_type != "application/zip" && $file_type != "application/x-zip-compressed") {
                $errors .= error_message("Your supplement file must be a ZIP file.");
            }
        }
        
        if ($errors != "") {
            return '<ul class=" container errors">' . $errors . '</ul>';
        }
        
        // At this point, we know there are no errors so post to the DB upload the files.
        if (insert_to_database()) {
            if (upload_files()) {
                email_administrators();
                checkBadgeStatusForUser($_COOKIE("username"));
            }
            else {
                // could not upload files
                return '<ul class=" container errors"><li class="text-error">There was an error uploading your files.</span></ul>';
            }
        }
        else {
            // could not insert to database
            return '<ul class=" container errors"><li class="text-error">This library name already exists on in our database.</span></ul>';
        }
        
        return false;
    }
     return true;
}

function checkBadgeStatusForUser($username) {
    $badgeType = "type-submission";
    $badge = new Badge($username, null, $badgeType);
    $badge->checkUserBadgeStatusForType();
}

$errors = process_post();

if ($errors == false) { // Successful submission
?>

<div class="library-submission-wizard-container container">
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
    <?php
    if (isset($_COOKIE['username'])) {
        $user = new User(null, $_COOKIE['username'], null, null);
        $user->getDataFromUsername();

        if (trim($user->first_name) == "" || trim($user->last_name) == "") {
    ?>

    <div class="container">
        <h4 class="text-info">
            To submit a library, we need to have your first and last name. To continue with
            submitting a library, please go to the control panel and update your profile with 
            your legal first and last name. Thank you for your interest in submitting to
            the Quorum standard library!
        </h4>
    </div>

    <?php
        }
        else {
    ?>
    	<form id="library-submission" class="form-horizontal" method="post" action="?post=true" enctype="multipart/form-data">
    		<div id="submission-wizard" class="carousel">
    			<div class="carousel-inner">
    				<div class="active item" id="wizard-1">
    					<div class="container">
    						<h2>Quorum Project Contributors Certification of Origin and Rights</h2>
                            <p> contributors to the Quorum Programming Language must formally agree to abide by this certificate of origin by signing on the bottom with their quorumlanguage.com userid, full name, email address (you can obscure your e-mail, but it must be computable by human), and date.</p>
                            <p>By signing this agreement, you are warranting and representing that you have the right to release code contributions or other content free of any obligations to third parties and are granting Andreas Stefik and Quorum project contributors, henceforth referred to as The Quorum Project, a license to incorporate it into The Quorum Project tools (such as Sodbeans and the Quorum standard library) or related works under the BSD license. You understand that The Quorum Project may or may not incorporate your contribution and you warrant and represent the following:</p>
                            <ol>
                                <li> I am the creator of all my contributions. I am the author of all contributed work submitted and further warrant and represent that such work is my original creation and I have the right to license it to The Quorum Project for release under the 3-clause BSD license. I hereby grant The Quorum Project a nonexclusive, irrevocable, royalty-free, worldwide license to reproduce, distribute, prepare derivative works, and otherwise use this contribution as part of the Quorum project, associated documentation, books, and tools at no cost to The Quorum Project. </li>
                                <li>I have the right to submit. This submission does not violate the rights of any person or entity and that I have legal authority over this submission and to make this certification.</li>
                                <li>If I violate another's rights, liability lies with me. I agree to defend, indemnify, and hold The Quorum Project and Quorum users harmless from any claim or demand, including reasonable attorney fees, made by any third party due to or arising out of my violation of these terms and conditions or my violation of the rights of another person or entity.</li>
                                <li>I understand and agree that this project and the contribution are public and that a record of the contribution (including all personal information I submit with it, including my sign-off) is maintained indefinitely and may be redistributed consistent with this project or the open source license indicated in the file.</li>
                            </ol>

    						<div class="actions clearfix">
    							<a class="btn btn-primary btn-next pull-right" href="#submission-wizard" disabled>Next</a>
    							<label class="checkbox pull-right">
    							<input type="checkbox" id="agree-to-bsd" value="">
    							I have read this agreement and do so certify by adding my signoff to the end of the following contributors list.
    							</label>
    						</div>
    					</div>
    				</div>
    				<div class="item" id="wizard-2">
    					<div class="container">
    						<div class="control-group">
    							<label class="control-label" for="library-name">Name of the Library Submission</label>
    							<div class="controls">
                                    <?php create_input("library-name", "Library Name"); ?>
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
    							<a class="btn btn-previous pull-left" href="#submission-wizard" data-slide="prev">Back</a>
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
    							<a class="btn btn-previous pull-left" href="#submission-wizard" data-slide="prev">Back</a>
    							<input type="submit" class="btn btn-primary btn-submit pull-right" value="Submit">
    						</div>
    					</div>
    				</div>
    			</div>
    		</div>
    	</form>   
    <?php            
        }
    }
    else { // User is not logged in
        echo '<div class="container"><h4 class="text-info">You must be registered and logged in to submit a library to Quorum.</h4></div>';
    }
    ?>
</div>
<?php
}
?>
<?php require_once("static/templates/pagefooter.template.php"); ?>