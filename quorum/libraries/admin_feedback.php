<?php include("static/templates/pageheader.template.php"); ?>
<?php include("models/librarySubmissions.model.php"); ?>
<?php include("models/librarySubmission.model.php"); ?>

<div class="container content" id="reviewer-feedback">
    <h1>Submit Admin Feedback</h1>
    <div id="library-info">
    <?php
        function displayLibrary($row) {
            $date = new DateTime($row["date_submitted"]);

            $html = '<tr id="' . $row["library_id"] . '">';
            $html .= '<td>' . $row["library_name"] . '</td>';
            $html .= '<td>' . $row["author_name"] . '</td>';
            $html .= '<td>' . $row["library_description"] . '</td>';
            $html .= '<td>' . $date->format('m-d-y') . '</td>';
            $html .= '<td><a href="' . $row["submission_url"] . '" target="_blank">Download Files</a></td>';
            $html .= '<td><a href="#" class="upvote">Upvote Library</a></td>';
            $html .= '<td><a href="#" class="downvote">Downvote Library</a></td>';
            $html .= '</tr>';
            return $html;
        }

        $library = new LibrarySubmission($_GET['id'], null, null, null, null, null, null, null, null, null, null);
        $library = $library->getSubmissionByID();

        if ($_GET['id'] && (!isset($_POST['decision']))) {
    ?>
   
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
    
    <form id="reviewer_submission_form" action="" method="post">
        <input type="hidden" name="library_id" value="<?php echo $library->libraryID; ?>" />
        <div class="control-group">
            <label class="control-label" for="decision"><h4>Decision:</h4></label>
            <div class="controls">
                <ul class="unstyled">
                    <li class="radio"><input type="radio" name="decision" value="reject"><strong>Reject:</strong> We never want to see this library again and will not accept it.</li>
                    <li class="radio"><input type="radio" name="decision" value="request-more-information"><strong>Request More Information:</strong> We don't know what to make of this library and need more information to evaluate it.</li>
                    <li class="radio"><input type="radio" name="decision" value="accept-with-revisions"><strong>Accept with Revisions:</strong> We like this library and are willing to accept it, but we expect changes to be made first.</li>
                    <li class="radio"><input type="radio" name="decision" value="accept"><strong>Accept:</strong> We have accepted the library without revisions.</li>
                </ul>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="usability"><h4>Usability Rating:</h4></label>
            <div class="controls">
                <ul class="unstyled">
                    <li class="radio"><input type="radio" name="usability" value="a"><strong>1:</strong> Not usable. The designer made design decisions that are inconsistent or hard to understand.</li>
                    <li class="radio"><input type="radio" name="usability" value="b"><strong>2:</strong> Difficult to use. There is some consistency, but it is still hard to understand.</li>
                    <li class="radio"><input type="radio" name="usability" value="c"><strong>3:</strong> Usable after some experience. This library is usable after a hard learning curve.</li>
                    <li class="radio"><input type="radio" name="usability" value="d"><strong>4:</strong> Pretty usable. This library is very usable after a small learning curve.</li>
                    <li class="radio"><input type="radio" name="usability" value="e"><strong>5:</strong> Very usable. The library is easy to understand, has naming conventions consistent with the design of Quorum, or otherwise appears easy to use.</li>
                </ul>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="technical"><h4>Technical Rating:</h4></label>
            <div class="controls">
                <ul class="unstyled">
                    <li class="radio"><input type="radio" name="technical" value="a"><strong>1:</strong> Does not work. This library does not provide its intended functionality.</li>
                    <li class="radio"><input type="radio" name="technical" value="b"><strong>2:</strong> Some of it works. While most of the intended functionality is there, the library has a lot of bugs.</li>
                    <li class="radio"><input type="radio" name="technical" value="c"><strong>3:</strong> Functional, but inefficient. The functionality works, but the library's functions use more computation than needed.</li>
                    <li class="radio"><input type="radio" name="technical" value="d"><strong>4:</strong> Pretty efficient. The library works as intended but still has some flaws.</li>
                    <li class="radio"><input type="radio" name="technical" value="e"><strong>5:</strong> Very efficient. The library works as intended and is computationally well-written.</li>
                </ul>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="feedback"><h4>Feedback for Library's Submitter:</h4></label>
            <div class="controls">
                <textarea rows="6" name="feedback"></textarea>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="confidential_feedback"><h4>Confidential Feedback for Editor:</h4></label>
            <div class="controls">
                <textarea rows="6" name="confidential_feedback"></textarea>
            </div>
        </div>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Submit Review</button>
            <button type="button" class="btn">Cancel</button>
        </div>
    </form>    
    
    <?php
        }

        if (isset($_POST['decision'])) {
            require('models/librarySubmissionReview.model.php');
            $technical = ord($_POST['technical']) - 96;
            $usability = ord($_POST['usability']) - 96;
            
            $review = new LibrarySubmissionReview($_POST['library_id'], $_COOKIE['username'], $_POST['feedback'], $_POST['confidential_feedback'], $_POST['decision'], $technical, $usability);
            $review->insertReview();

            $library->status = $_POST['decision'];
            $library->public_display = 0;

            $library->updateSubmissionStatus();
    ?>
   
    <div class="container">
        <h3 class="text-info">Your feedback has been submitted. The library has been set to "<?php echo $_POST['decision']; ?>"</h3>
    </div>

    <?php
        }
    ?>
    
</div>
<?php include("static/templates/pagefooter.template.php"); ?>
