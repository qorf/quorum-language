<hmtl>
    
    <header>
        <style type="text/css">
            td {
                white-space: pre;
                padding: 10px;
            }
            tr:nth-child(even) {
                background-color: #DDD;
            }
            th {
                background-color: #DDD;
            }
            .code {
                font-family: "Courier New", Courier, monospace;
            }
        </style>
    </header>
    
    <body>
        <h1>Internal Server Errors</h1>
        <table>
            <thead>
            <tr>
                <th>Time</th>
                <th>Version</th>
                <th>Code</th>
                <th>Output</th>
                <th>PageNr</th>
                <th>SlideNr</th>
            </tr>
            </thead>
            <tbody>
                
         
                <?php
                        require_once(__DIR__."/models/data.model.php");

                        $db  = new QuorumDataModel();

                        $sqlQuery = "SELECT quorum_version, code, output, slidenr, pagenr, insertionTime FROM run_code WHERE output LIKE '%Internal Server Error%'";
                        try {
                                $preparedStatement = $db->connection->prepare($sqlQuery);
                                $preparedStatement->execute();
                        }
                        catch (Exception $ex) {
                                return $ex;
                        }

                        if (isset($preparedStatement)) {
                            if ($preparedStatement->rowCount() > 0 ){
                                $resarray = $preparedStatement->fetchAll();
                                foreach ($resarray as $item) {
                                    echo '<tr>';
                                    echo '<td>'.$item['insertionTime'].'</td>';
                                    echo '<td>'.$item['quorum_version'].'</td>';
                                    echo '<td class="code">'.$item['code'].'</td>';
                                    echo '<td>'.$item['output'].'</td>';
                                    echo '<td>'.$item['pagenr'].'</td>';
                                    echo '<td>'.$item['slidenr'].'</td>';
                                    echo '</tr>';
                                }
                            }
                        }
                ?>
            </tbody>
        </table>
    </body>
</hmtl>