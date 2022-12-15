<?php

require 'connection.php';

if (array_key_exists("owner_id", $_GET) && array_key_exists("user_id", $_GET)) {

	$owner_id = $_GET["owner_id"];
	$user_id = $_GET["user_id"];

	
			$query = $mysqli->prepare("SELECT *, FROM reservations WHERE owner_id = ?");
			$query->bind_param("ii", $user_id, $owner_id);
		}
		$query->execute();
		$result = $query->get_result();

		$reservations = [];
		$users = [];

		while ($row = mysqli_fetch_assoc($result)) {
			$reservations[] = $row;
		}


		$output["success"] = true;
		$output["error"] = 0;

		$result = [];
		$result["reservation"] = $reservations;
		$output["query_result"] = $result;



	
 else {

	$output["success"] = false;
	$output["query_result"] = null;
	$output["error"] = "Missing User ID";

}

echo json_encode($output);
