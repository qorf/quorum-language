<?php include("static/templates/pageheader.template.php"); ?>
<?php include("models/librarySubmissions.model.php"); ?>
<?php include("models/librarySubmission.model.php"); ?>
<?php include("models/librarySubmissionReview.model.php"); ?>
<?php include_once("models/user.model.php"); ?>

<?php
if (isset($_GET['id'])) {
    $library = new LibrarySubmission($_GET['id'], null, null, null, null, null, null, null, null, null, null);
    $library = $library->getSubmissionByID();
?>
<div class="container content" id="submitted-library">
    <div id="library-info">
   
    <div class="container well">
        <table class="table library-info table-striped table-bordered">
            <tbody>
                <tr><td colspan="2" class="label-row"><h3><?php print $library->libraryName; ?></h3></td></tr>
                <tr><td class="label-cell"><label>Uploader Username</label></td><td><?php print $library->uploaderUsername; ?></td></tr>
                <tr><td class="label-cell"><label>Author Name</label></td><td><?php print $library->authorName; ?></td></tr>
                <tr><td class="label-cell"><label>Library Description</label></td><td><?php print $library->libraryDescription; ?></td></tr>
                <tr><td class="label-cell"><label>Usage Instructions</label></td><td><?php print $library->usageInstructions; ?></td></tr>
                <tr><td class="label-cell"><label>Submission Files</label></td><td><?php print '<a href="'. $library->submissionURL . '">Download Library Files</a>'; ?></td></tr>
                <tr><td class="label-cell"><label>Supplementary Files</label></td><td><?php print '<a href="'. $library->supplementaryFilesURL .'">Download Supplementary Files</a>'; ?></td></tr>
            </tbody>
        </table>
    </div>
</div>
    
<?php
        $user = new User(null, $_COOKIE['username'], null, null);
        $user->getDataFromUsername();
        if ($user->administrator) {
            $submissionReviews= new LibrarySubmissionReview($library->libraryID, null, null, null, null, null, null);
            $submissionReviews = $submissionReviews->getFeedbackForLibrary();
            
            echo '<div class="submission-reviews">';
            if (count($submissionReviews)) {
                echo '<h3>Feedback for this review</h3><ul class="no-bullets">';
                foreach ($submissionReviews as $review)
                {
                    echo '<li><a href="/submitted_reviewer_feedback.php?username=' . $review['username']  . '&library_id=' . $review['library_id'] . '">View feedback from ' . $review['username'] . '</a></li>';
                }
                echo '</ul>';
            }
            else {
                echo '<p>There has been no feedback submitted for this library.</p>';
            }
            
        }    
}
?>
<?php include("static/templates/pagefooter.template.php"); ?>
