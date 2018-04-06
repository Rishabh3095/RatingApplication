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
if (isset($_POST["sjsuid"])&&isset($_POST["password"])) {
    $name = $_POST['sjsuid'];
	$password = $_POST['password'];
	
    // get a user from products table
    $result = mysql_query("SELECT *FROM user WHERE SJSUID = '$name'");
	
    if (!empty($result)) {
		
        // check for empty result
		$row = mysql_fetch_array($result);
		
        if ($row["password"]==$password) {

            // success
            $response["success"] = 1;

			$response["message"] = "Password correct!";

            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
			if ($row["name"]==$name) {
				$response["success"] = 2;
				$response["message"] = "Wrong password";

				// echo no users JSON
				echo json_encode($response);
			} else {
			    // no product found
				$response["success"] = 0;
				$response["message"] = "No User found";

				// echo no users JSON
				echo json_encode($response);
			}
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No User found";

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