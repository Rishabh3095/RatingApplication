<?php
// array for JSON response
$response = array();
// include db connect class
require_once __DIR__ . '/db_connect.php';
// connecting to db
$db = new DB_CONNECT();
    
    $result = mysql_query("SELECT *FROM Poll") or die(mysql_error());
    // check for empty result
    if (mysql_num_rows($result) > 0) {
        // looping through all results
        // products node
        $response["polls"] = array();
        
        while ($row = mysql_fetch_array($result)) {
            // temp user array
            $poll = array();
            $poll["pollID"]= $row["pollID"];
            $poll["title"] = $row["title"];
            $poll["sjsuid"] = $row["user"];
            $poll["date"] = $row["date"];
            // push single product into final response array
            array_push($response["polls"], $poll);
        }
        // success
        $response["success"] = 1;
        // echoing JSON response
        echo json_encode($response);
    } else {
        // no products found
        $response["success"] = 0;
        $response["message"] = "No polls found";
        // echo no users JSON
        echo json_encode($response);
    }
?>