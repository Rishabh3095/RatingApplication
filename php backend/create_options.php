<?php
/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */
// array for JSON response
$response = array();
// check for required fields
if (isset($_POST['title'])&& isset($_POST['sjsuid'])) {
    
    $sjsuid = $_POST['sjsuid'];
    $title = $_POST['title'];
    
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
    // connecting to db
    $db = new DB_CONNECT();
    // mysql inserting a new row
    $result = mysql_query("INSERT INTO Option(title,  user) VALUES('$title', '$sjsuid')");
    // check if row inserted or not
    if (mysqli_multi_query($db, $result)) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "options added";
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