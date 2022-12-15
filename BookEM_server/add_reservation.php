<?php

require 'connection.php';

if (array_key_exists("user_id", $_POST) && array_key_exists("resource_id", $_POST) && array_key_exists("start_date", $_POST) && array_key_exists("end_date", $_POST)) {

	$user_id = $_POST["user_id"];
	$resource_id = $_POST["resource_id"];
	$start_date = $_POST["start_date"];
	$end_date = $_POST["end_date"];

	try {

		
		$query = $mysqli->prepare("SELECT * FROM reservations WHERE user_id = ? AND resource_id = ?");
		$query->bind_param("ii", $user_id, $resource_id);
		$query->execute();
		$result = $query->get_result();

		if (mysqli_num_rows($result) > 0) {

			$output["success"] = false;
			$output["error"]   = "Reservation already Exists";

		} else {

			$query = $mysqli->prepare("INSERT INTO reservations (user_id, resource_id, start_date, end_date) VALUES (?, ?, ?, ?)");
			$query->bind_param("iis", $user_id, $resource_id, $start_date, $end_date);
			$query->execute();

			$query = $mysqli->prepare("UPDATE resources SET reservations_TEMP = reservations_TEMP + 1 WHERE resource_id = ?");
			$query->bind_param("i", $resource_id);
			$query->execute();

			$output["success"] = true;
			$output["error"] = 0;

		}
	} catch (Exception $e) {

		$output["success"] = false;
		$output["error"] = $e->getMessage();

	}
} else {

	$output["success"] = false;
	$output["error"]   = "Missing Attributes";

}
echo json_encode($output);
