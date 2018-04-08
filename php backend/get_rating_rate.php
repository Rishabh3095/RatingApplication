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
if (isset($_GET[ratingID])) {
    $id = $_GET[ratingID];

    // get a activity from products table
    $result = mysql_query("select AVG(score) as avg from RatingRate where ratingId = $id");

    if (!empty($result)) {
        // check for empty result
		
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $User = array();
            $User["score"] = $result["avg"];
            // success
            $response["success"] = 1;

            // user node
            $response["score"] = array();

            array_push($response["score"], $User);

            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No user found";

            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No user found";

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