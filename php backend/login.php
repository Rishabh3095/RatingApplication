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
if (isset($_POST["sjsuid"]) && isset($_POST["password"])) {
    $name = $_POST['sjsuid'];
	$password = $_POST['password'];
	
    // get a user from products table
    $result = mysql_query("SELECT * FROM User WHERE SJSUID = $name");
	
    if (!empty($result)) {
		
        // check for empty result
		$row = mysql_fetch_array($result);
		
        if ($row["Password"]==$password) {
			$r = 1;
			$message = "Password correct";
			while($row1 =mysql_fetch_array($result)) {
				if($row1["Password"]==$password && $r!=3 ) {
					$r = 4;
					$message = "duplicate, identical users";
				} else if ($row1["Password"]!=$password){
					$r = 3;
					$message = "duplicate users with different passwords";
				}
			} 

            // success
            $response["success"] = $r;

			$response["message"] = $message;

            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
			if ($row["SJSUID"]==$name) {
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
        $response["success"] = 3;
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