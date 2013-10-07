<?php 
require_once("static/templates/pageheader.template.php");
require_once("models/user.model.php");
require_once("models/librarySubmissions.model.php");
?>

<div class="container control-panel-container">
    <div class="row">
        <div class="span6">
            <h1>Welcome, <?php echo $_COOKIE['username']; ?> </h1>
            <?php
        		$user = new User("", $_COOKIE['username'], "", "");
                $user->getDataFromUsername();
                echo '<h3>Email: ' . $user->email . '</h3>';
            ?>
        </div>
        <div class="span6">
            <?php
            $library_submissions = new LibrarySubmissions(null, null, null, null);
            $submissions_for_reviewer = $library_submissions->getLibrarySubmissionsForReviewer($_COOKIE['username']);
            
            if (count($submissions_for_reviewer) > 0) {
            ?>
                <h4>Reviewer Controls</h4>
                <ul>
                    <li>
                        <select id="reviewer-feedback-select">
                            <option value="">-- Give Feedback on a Submitted Library --</option>
                            <?php
                            foreach ($submissions_for_reviewer as $submission) {
                              echo '<option value="' . $submission['library_id'] . '">' . $submission['library_name'] . '</option>'; 
                            }
                            ?>
                        </select>
                    </li>
                </ul>
            <?php
            }
            
            if ($user->administrator == 1) {
                $users = new User(null, null, null, null);
                $users = $users->getAllUsers();
                $all_libraries = $library_submissions->getAllSubmissions();
                $pending_reviews = $library_submissions->getLibrariesForAdmin("pending-admin");
                $accepted_reviews = $library_submissions->getLibrariesForAdmin("accepted");
                $rejected_reviews = $library_submissions->getLibrariesForAdmin("rejected");
                $accepted_with_revisions_reviews = $library_submissions->getLibrariesForAdmin("accept-with-revisions");
                $waiting_on_information_reviews = $library_submissions->getLibrariesForAdmin("request-more-information");
            ?>
                <h4>Administrator Controls</h4>
                <ul>
                    <li>
                        <select id="admin-pending-feedback-select">
                            <option value="">-- View Libraries Pending Admin Review --</option>
                            <?php
                            foreach ($pending_reviews as $submission) {
                                echo '<option value="' . $submission['library_id'] . '">' . $submission['library_name'] . '</option>'; 
                            }
                            ?>
                        </select>
                    </li>
                    <li>
                        <select id="admin-accepted-feedback-select">
                            <option value="">-- View Accepted Libraries --</option>
                            <?php
                            foreach ($accepted_reviews as $submission) {
                                echo '<option value="' . $submission['library_id'] . '">' . $submission['library_name'] . '</option>'; 
                            }
                            ?>
                        </select>
                    </li>
                    <li>
                        <select id="admin-accepted-revisions-feedback-select">
                            <option value="">-- View Libraries Accepted with Revisions --</option>
                            <?php
                            foreach ($accepted_with_revisions_reviews as $submission) {
                                echo '<option value="' . $submission['library_id'] . '">' . $submission['library_name'] . '</option>'; 
                            }
                            ?>
                        </select>
                    </li>
                    <li>
                        <select id="admin-rejected-feedback-select">
                            <option value="">-- View Rejected Libraries  --</option>
                            <?php
                            foreach ($rejected_reviews as $submission) {
                                echo '<option value="' . $submission['library_id'] . '">' . $submission['library_name'] . '</option>'; 
                            }
                            ?>
                        </select>
                    </li>
                    <li>
                        <select id="admin-request-more-info-feedback-select">
                            <option value="">-- View Libraries Waiting More Information --</option>
                            <?php
                            foreach ($waiting_on_information_reviews as $submission) {
                                echo '<option value="' . $submission['library_id'] . '">' . $submission['library_name'] . '</option>'; 
                            }
                            ?>
                        </select>
                    </li>
                    <li id="add-user-to-library">
                        <select id="add-user-to-library-library">
                            <option value="">-- Select Library to Set Reviewer --</option>
                            <?php
                            foreach ($all_libraries as $submission) {
                                echo '<option value="' . $submission['library_id'] . '">' . $submission['library_name'] . '</option>'; 
                            }
                            ?>
                        </select>
                        <select id="add-user-to-library-user">
                            <option value="">-- Select User to Set Reviewer --</option>
                            <?php
                            foreach ($users as $user) {
                                echo '<option value="' . $user['username'] . '">' . $user['username'] . '</option>'; 
                            }
                            ?>
                        </select>
                        <button class="btn btn-primary" id="add-user-submit" />Add User</button>
                    </li>
                </ul>
            <?php
            }
            ?>
        </div>
    </div>
</div>

<?php
require_once("static/templates/pagefooter.template.php");