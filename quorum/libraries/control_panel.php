<?php 
$classPageTitle = 'Control Panel';
if (!isset($_COOKIE['username'])) {
    header( 'Location: /' );
}
else {
    require_once("static/templates/pageheader.template.php");
    require_once("models/user.model.php");
    require_once("models/librarySubmissions.model.php");
    require_once("models/badge.model.php");

    $user = new User("", $_COOKIE['username'], "", "");
    $user->getDataFromUsername();

    $library_submissions = new LibrarySubmissions(null, null, null, null);
    $submissions_for_reviewer = $library_submissions->getLibrarySubmissionsForReviewer($_COOKIE['username']);
    $submissions_for_user = $library_submissions->getLibrarySubmissionsForUser($_COOKIE['username']);
?>
    <div class="container control-panel-container">
        <div class="row">
            <div class="span6">
                <?php render_left_user_controls(); ?>
            </div>
            <div class="span6">
                <?php
                if (count($submissions_for_user) > 0) {
                    render_right_user_controls();
                }

                if (count($submissions_for_reviewer) > 0) {
                    render_reviewer_controls();
                }

                if ($user->administrator == 1) {
                    render_admin_controls();
                }
                ?>
            </div>
        </div>
    </div>
<?php
}

function process_profile_save() {
    global $user;
    $user->first_name = $_POST['first_name'];
    $user->last_name = $_POST['last_name'];
    $user->updateUser();

    echo '<p class="text-info">Your changes have been saved!</p>';
}

function render_badges() {
    global $user;

    $badges_model = new Badge($user->username, null, null);
    $badges = $badges_model->getBadgesForUser();

    if (count($badges) > 0) {
        echo '<h3>Your Earned Badges</h3>';
        echo '<ul class="badges-list unstyled">';
        foreach ($badges as $badge) {
            $englishified_badge_name = ucwords(str_replace('-', ' ', $badge["badge"]));
            echo '<li class="quorum-badge">';
            echo ' <img src="/static/img/badges/' . $badge["badge"] . '.png" alt="' . $englishified_badge_name . '" />';
            echo ' <span class="title">' . $englishified_badge_name . '</span>';
            echo '</li>';
        }
        echo '</ul>';
    }
}

function render_left_user_controls() {
    global $user;
?>
    <h1>Welcome, <?php echo $_COOKIE['username']; ?> </h1>

    <?php render_badges(); ?>

    <a href="/submit_library.php" class="btn btn-primary">Earn badges by submitting libraries to Quorum</a>

    <form method="post" id="edit-profile">
        <fieldset>
            <legend>View/Edit Your Profile</legend>
            <?php if (isset($_POST['first_name']) || isset($_POST['last_name'])) { process_profile_save(); }  ?>
            <div class="control-group"><label class="control-label">Email</label> <?php echo $user->email; ?></div>
            <div class="control-group"><label class="control-label">Username</label> <?php echo $user->username; ?></div>
            <div class="control-group"><label class="control-label">First Name</label> <input type="text" value="<?php echo $user->first_name; ?>" name="first_name"></div>
            <div class="control-group"><label class="control-label">Last Name</label> <input type="text" value="<?php echo $user->last_name; ?>" name="last_name"></div>

            <button class="btn" />Save Profile</button>
        </fieldset>
    </form>
<?php
}

function render_right_user_controls() {
    global $library_submissions;
    global $submissions_for_user;
    global $user;
?>
    <h4>User Controls</h4>
    <ul>
        <li>
            <select id="user-feedback-select">
                <option value="">-- View your Submitted Libraries --</option>
                <?php
                foreach ($submissions_for_user as $submission) {
                  echo '<option value="' . $submission['library_id'] . '">' . $submission['library_name'] . '</option>'; 
                }
                ?>
            </select>
        </li>
    </ul>
<?php
}

function render_reviewer_controls() {
    global $library_submissions;
    global $submissions_for_reviewer;
    global $user;
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

function render_admin_controls() {
    global $library_submissions;
    global $submissions_for_reviewer;
    global $user;

    $users = new User(null, null, null, null);
    $users = $users->getAllUsers();
    $all_libraries = $library_submissions->getAllSubmissions();
    $pending_reviews = $library_submissions->getLibrariesForAdmin("pending-admin");
    $accepted_reviews = $library_submissions->getLibrariesForAdmin("accept");
    $rejected_reviews = $library_submissions->getLibrariesForAdmin("reject");
    $accepted_with_revisions_reviews = $library_submissions->getLibrariesForAdmin("accept-with-revisions");
    $waiting_on_information_reviews = $library_submissions->getLibrariesForAdmin("request-more-information");
?>    
    <h4>Administrator Controls</h4>
    <ul class="unstyled admin-controls">
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
        <li id="add-user-to-library" class="control-group well">
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
            <input class="typeahead tt-query users-typeahead" type="text" placeholder="Begin typing a username..." autocomplete="off" spellcheck="false" />

            <script type="text/javascript" src="/assets/js/typeahead.min.js"></script>
            <script type="text/javascript">
                <?php // form a javascript using PHP to avoid another server call. blowin ya mind. ...kinda yuck, sorry.
                echo "var users = [''";
                foreach ($users as $user) {
                    echo ", '" . $user['username'] . "'"; 
                }
                echo "];";
                ?>

                // Set up the typeahead and when an option is selected, update the dropdown.
                $(".users-typeahead").typeahead({ name: 'users', local: users, limit: 10 })
                                    .bind('typeahead:selected', function (obj, datum) {
                                        $("#add-user-to-library-user").val(datum.value);
                                    });
            </script>
            <button class="btn btn-primary" id="add-user-submit" />Add User</button>
        </li>
    </ul>

<?php
}


require_once("static/templates/pagefooter.template.php");