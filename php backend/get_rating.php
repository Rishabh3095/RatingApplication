<?php

/*
 * Following code will get single activity details
 * An activity is identified by activity id
 */

// array for JSON response
$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_GET['ratingID'])) {
    $id = $_GET['ratingID'];

    // get a activity from products table
    $result = mysql_query("SELECT *FROM Rating WHERE ratingID = '$ratingID'");

    if (!empty($result)) {
        // check for empty result
		
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $rating = array();
            $rating["title"] = $result["title"];
            $rating["sjsuid"] = $result["user"];
            $rating["date"] = $result["date"];
            $rating["maxRate"] = $result["maxRate"];
            
            $result = mysql_query("SELECT *FROM RatingRate WHERE ratingID = '$ratingID'") or die(mysql_error());

            // check for empty result
            if (mysql_num_rows($result) > 0) {
                // looping through all results
                // products node
                $response["votes"] = array();
                
                while ($row = mysql_fetch_array($result)) {
                    // temp user array
                    $product = array();
                    $product["userID"] = $row["userID"];
                    $product["score"] = $row["score"];
        
                    // push single product into final response array
                    array_push($response["votes"], $product);
                }
            }
            // success
            $response["success"] = 1;

            // user node
            $response["rating"] = array();

            array_push($response["rating"], $rating);

            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No rating found";

            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No poll found";

        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>