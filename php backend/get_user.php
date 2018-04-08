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
if (isset($_GET[sjsuID])) {
    $id = $_GET[sjsuID];

    // get a activity from products table
    $result = mysql_query("SELECT *FROM User WHERE SJSUID = $id");

    if (!empty($result)) {
        // check for empty result
		
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $User = array();
            $User["sjsuid"] = $result["SJSUID"];
            $User["Password"] = $result["Password"];
            $User["firstName"] = $result["firstName"];
            $User["lastName"] = $result["lastName"];
            // success
            $response["success"] = 1;

            // user node
            $response["User"] = array();

            array_push($response["User"], $User);

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