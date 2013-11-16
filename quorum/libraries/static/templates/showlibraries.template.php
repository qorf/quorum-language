<div class="container">
    <?php
    function sort_by_icon($column) {
        $url = "/library_index.php?sort_by=" . $column . "&ascending_or_descending=";
        echo '<div class="sort-by-icon">';
        echo '<a class="sort-desc" href="' . $url . 'DESC">Sort by Descending</a>';
        echo '<a class="sort-asc" href="' . $url . 'ASC">Sort by Ascending</a>';
        echo '</div>';
    }
    ?>

    <table class="table table-bordered library-submissions">
        <thead>
            <tr>
                <th><span>Library Name</span> <?php sort_by_icon("library_name"); ?></th>
                <th><span>Author Name</span> <?php sort_by_icon("uploader_username"); ?></th>
                <th class="library-row-description"><span>Library Description</span></th>
                <th><span>Date Submitted</span> <?php sort_by_icon("date_submitted"); ?></th>
                <th><span>Download Link</span></th>
                <th><span>View Library Information</span></th>
            </tr>
        </thead>
        
        <?php 
        require_once("models/workinprogress_librarySubmissions.model.php"); 
        require_once("models/librarySubmission.model.php"); 
              
        $submissions = new LibrarySubmissions($_GET['sort_by'], $_GET['ascending_or_descending'], $_GET['search_query'], $_GET['page']);

        switch ($user_type) {
            case "guest": $submissionsResults = $submissions->getPublicSubmissions();
            case "user": $submissionsResults = $submissions->getPublicSubmissions();
            case "reviewer": $submissionsResults = $submissions->getPublicSubmissions();
            case "admin": $submissionsResults = $submissions->getPublicSubmissions();
        }
        
        function displayLibrary($row) {
            $date = new DateTime($row["date_submitted"]);

            $html = '<tr id="' . $row["library_id"] . '">';
            $html .= '<td>' . $row["library_name"] . '</td>';
            $html .= '<td>' . $row["author_name"] . '</td>';
            $html .= '<td class="library-row-description">' . Truncate($row["library_description"], 100) . '</td>';
            $html .= '<td>' . $date->format('m-d-y') . '</td>';
            $html .= '<td><a href="' . $row["submission_url"] . '" target="_blank">Download Files</a></td>';
            $html .= '<td><a href="/submitted_library.php?id=' . $row["library_id"] . '">View More Information</a></td>';
            $html .= '</tr>';
            return $html;
        }
       
        print '<tbody>';
        foreach ($submissionsResults as $row)
        {
            print displayLibrary($row);
        }
        print '</tbody></table>';
        ?>
        
<!--    <div class="actions clearfix" style="margin: 40px auto 20px; width: 550px; ">
        <button class="btn btn-primary pull-left">Previous Page</button>
        <div class="btn-toolbar pull-left" style="margin: 0 90px;">
          <div class="btn-group">
            <a class="btn" href="#">1</a>
            <a class="btn" href="#">2</a>
            <a class="btn" href="#">3</a>
            <a class="btn" href="#">4</a>
            <a class="btn" href="#">5</a>
          </div>
        </div>
        <button class="btn btn-primary pull-right" style="margin-top:-30px;">Next Page</button>
    </div>-->
</div>