<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST[sjsuid]) && isset($_POST['password'])&& isset($_POST['first_name'])&& isset($_POST['last_name'])) {
    
    $sjsuid = $_POST[sjsuid];
    $password = $_POST['password'];
    $first_name = $_POST['first_name'];
    $last_name =$_POST['last_name'];
    
    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql inserting a new row
    $result = mysql_query("INSERT INTO User( SJSUID, Password, firstName, lastName) VALUES('$sjsuid', '$password', '$first_name' , '$last_name')");

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "user added";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        
        // echoing JSON response
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