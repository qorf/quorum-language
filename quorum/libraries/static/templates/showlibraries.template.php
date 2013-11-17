<div class="container">
    <?php
    function sort_by_icon($column) {
        $url = "/submitted_library_index.php?page=" . $_GET['page'] . "&sort_by=" . $column . "&ascending_or_descending=";
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
        require_once("models/librarySubmissions.model.php"); 
        require_once("models/librarySubmission.model.php"); 

        $sort_by = $_GET['sort_by'] == "" ? "date_submitted" : $_GET['sort_by'];
        $ascending_or_descending = $_GET['ascending_or_descending'] == "" ? "DESC" : $_GET['ascending_or_descending'];
              
        $submissions = new LibrarySubmissions($sort_by, $ascending_or_descending, null, null);

        switch ($user_type) {
            case "guest": $submissionsResults = $submissions->getPublicSubmissions();
            case "user": $submissionsResults = $submissions->getPublicSubmissions();
            case "reviewer": $submissionsResults = $submissions->getPublicSubmissions();
            case "admin": $submissionsResults = $submissions->getPublicSubmissions();
        }

        $page = $_GET['page'] == "" ? 1 : $_GET['page'];

        $submissionsResultsPage = $submissions->getPageOfResults($page, $submissionsResults);
        
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
        foreach ($submissionsResultsPage as $row) {
            print displayLibrary($row);
        }
        print '</tbody></table>';
        
        $submission_count = count($submissionsResults);
        $submissions_per_page = $submissions->countPerPage;

        if ($submission_count > $submissions_per_page) { 
            $url = "/submitted_library_index.php?sort_by=" . $sort_by;
            $url .= "&ascending_or_descending=" . $ascending_or_descending;
            $url .= "&page=";

            $previous_url = $url . ($page - 1);
            $next_url = $url . ($page + 1);
        ?>
        <table class="table" style="width: 650px; margin: 0 auto;">
            <tr>
                <td style="width: 125px;">
                    <?php
                    if ($page > 1) {
                        echo '<a href="'. $previous_url .'" class="btn btn-primary pull-left">Previous Page</a>';   
                    }
                    ?>
                </td>
                <td style="width: 400px;">
                    <div class="btn-toolbar pull-left" style="margin: 0 auto; width: 100%; text-align: center; ">
                        <div class="btn-group">
                            <?php 
                                $page_count = 0;
                                for ($i = 0; $submission_count >= $i; ) { 
                                    $page_number = ($i / $submissions_per_page) + 1;
                                    $page_url = $url . $page_number;
                                    $i += $submissions_per_page;
                                    echo '<a class="btn" href="' . $page_url . '">' . $page_number . '</a>';
                                    $page_count++;
                                }
                            ?>
                        </div>    
                    </div>  
                </td>
                <td style="width: 125px;">
                    <?php
                    if ($page != $page_count) {
                        echo '<a href="'. $next_url .'" class="btn btn-primary pull-right" style="margin:0 0 0 -200px;">Next Page</a>';
                    }
                    ?> 
                </td>
            </tr>
        </table>
       <?php } ?>

</div>