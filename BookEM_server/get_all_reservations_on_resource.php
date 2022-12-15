<?php

require 'connection.php';

if (array_key_exists("resource_id", $_GET)) {

	try {

		$query = $mysqli->prepare("SELECT DISTINCT users.user_id, users.username, reservations.start_date, reservations.end_date  FROM reservations, resources, users WHERE reservations.resource_id = ? AND reservations.user_id = users.user_id AND reservations.user_id");
		$query->bind_param("i", $resource_id);
		$query->execute();
		$result = $query->get_result();

		$resources = [];


		while ($row = mysqli_fetch_assoc($result)) {

			
			$resources[] = $row;

		}
		
		
		$result = [];
		$result["reservation"] = $resources;	
		$output["query_result"] = $result;
		$output["success"] = true;
		$output["error"] = 0;
		

	} catch (Exception $e) {

		$output["success"] = false;
		$output["query_result"] = 0;
		$output["error"] = $e->getMessage();
	}

} else {

	$output["success"] = false;
	$output["error"]   = "Missing Attributes";

}

echo json_encode($output);
