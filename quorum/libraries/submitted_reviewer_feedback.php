<?php include("static/templates/pageheader.template.php"); ?>
<?php require("models/librarySubmissionReview.model.php"); ?>
<?php require("models/librarySubmission.model.php"); ?>
<?php require("models/user.model.php"); ?>

<?php
$submissionReview = new LibrarySubmissionReview($_GET['library_id'], $_GET['username'], null, null, null, null, null);
$review = $submissionReview->getFeedbackForLibraryAndUser();

$library = new LibrarySubmission($_GET['library_id'], null, null, null, null, null, null, null, null, null, null);
$library = $library->getSubmissionByID();

$user = new User(null, $_COOKIE['username'], null, null);
$user->getDataFromUsername();

if ($user->administrator == 1 || $user->username == $review->username || $user->username == $library->uploaderUsername) {
?>

<div class="container content" id="submitted-reviewer-feedback">
    <h1>Submitted Feedback</h1>
    <div id="library-info">
    <div class="container well">
        <table class="table library-info table-striped table-bordered">
            <tbody>
                <tr><td colspan="2" class="label-row"><h3><?php print $library->libraryName; ?></h3></td></tr>
                <tr><td class="label-cell"><label>Uploader Username</label></td><td><?php print $library->uploaderUsername; ?></td></tr>
                <tr><td class="label-cell"><label>Reviewer Username</label></td><td><?php print $review["username"]; ?></td></tr>
                <tr><td class="label-cell"><label>Author Name</label></td><td><?php print $library->authorName; ?></td></tr>
                <tr><td class="label-cell"><label>Library Description</label></td><td><?php print $library->libraryDescription; ?></td></tr>
                <tr><td class="label-cell"><label>Usage Instructions</label></td><td><?php print $library->usageInstructions; ?></td></tr>
                <tr><td class="label-cell"><label>Submission Files</label></td><td><?php print '<a href="'. $library->submissionURL . '">Download Library Files</a>'; ?></td></tr>
                <tr><td class="label-cell"><label>Supplementary Files</label></td><td><?php print '<a href="'. $library->supplementaryFilesURL .'">Download Supplementary Files</a>'; ?></td></tr>
            </tbody>
        </table>
        
    </div>
    
    <form id="reviewer_submission_form" action="" method="post">
        <input type="hidden" name="library_id" value="<?php echo $library->libraryID; ?>" />
        <div class="control-group">
            <label class="control-label" for="decision">Decision:</label>
            <div class="controls">
                <?php
                $status_display = "";
                switch ($review["status"]) {
                    case "reject": $status_display = "Reject";
                    case "request-more-information": $status_display = "Request More Information";
                    case "accept-with-revisions": $status_display = "Accept with Revisions";
                    case "accept": $status_display = "Accept";
                }
                echo $status_display;
                ?>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="usability">Usability Rating:</label>
            <div class="controls">
                <?php
                if ($review["usability_rating"] == 1) { echo "1 star out of 5"; }
                else { echo $review["usability_rating"] . " stars out of 5"; }
                ?>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="technical">Technical Rating:</label>
            <div class="controls">
                <?php
                if ($review["technical_rating"] == 1) { echo "1 star out of 5"; }
                else { echo $review["technical_rating"] . " stars out of 5"; }
                ?>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="feedback">Feedback for Library's Submitter:</label>
            <div class="controls">
                <?php
                echo "<textarea rows=\"6\" name=\"feedback\" disabled=\"disabled\">" . $review["feedback"] . "</textarea>";
                ?>
            </div>
        </div>
        <?php
        if ($user->administrator == 1 || $user->username == $review->username) {
        ?>
        <div class="control-group">
            <label class="control-label" for="confidential_feedback">Confidential Feedback for Editor:</label>
            <div class="controls">
                <?php
                echo "<textarea rows=\"6\" name=\"confidential_feedback\" disabled=\"disabled\">" . $review["confidential_feedback"] . "</textarea>";
                ?>
            </div>
        </div>
        <?php
        }
        ?>
    </form>    
</div>

<?php } ?>
    
<?php include("static/templates/pagefooter.template.php"); ?>
