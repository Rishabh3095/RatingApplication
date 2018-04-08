<?php

// array for JSON response
$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();
    
    $result = mysql_query("SELECT *FROM Rating") or die(mysql_error());

    // check for empty result
    if (mysql_num_rows($result) > 0) {
        // looping through all results
        // products node
        $response["ratings"] = array();
        
        while ($row = mysql_fetch_array($result)) {
            // temp user array
            $rating = array();
            $rating["ratingID"]= $row["ratingID"];
            $rating["title"] = $row["title"];
            $rating["sjsuid"] = $row["user"];
            $rating["date"] = $row["date"];
            $rating["maxRate"] = $row["maxRate"];

            // push single product into final response array
            array_push($response["ratings"], $rating);
        }
        // success
        $response["success"] = 1;

        // echoing JSON response
        echo json_encode($response);
    } else {
        // no products found
        $response["success"] = 0;
        $response["message"] = "No ratings found";

        // echo no users JSON
        echo json_encode($response);
    }
?>
