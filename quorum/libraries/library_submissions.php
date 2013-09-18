<?php require_once("static/templates/pageheader.template.php"); ?> 
<div class="container">
    <div class="actions">
        <button {{action 'prevPage'}}>Previous Page</button>
        <button {{action 'nextPage'}}>Next Page</button>
    </div>
        
    <table class='table'>
        <thead>
            <tr>
                <th>Library Name <b>^</b></th>
                <th>Author Name <b>^</b></th>
                <th>Library Description <b>^</b></th>
                <th>Date Submitted <b>^</b></th>
                <th>Download Link</th>
                <th>Upvote <b>^</b></th>
                <th>Downvote <b>^</b></th>
            </tr>
        </thead>
        
        <?php 
        require_once("models/submissions.model.php"); 
              
        $submissions = new LibrarySubmissions($_GET['sort_by'], $_GET['ascending_or_descending'], $_GET['search_query'], $_GET['page']);
        $submissionsResults = $submissions->getPublicSubmissions();
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
       
        print '<tbody>';
        foreach ($submissionsResults as $row)
        {
            print displayLibrary($row);
        }
        print '</tbody></table>';
        ?>
</div>

<?php require_once("static/templates/pagefooter.template.php"); ?>
