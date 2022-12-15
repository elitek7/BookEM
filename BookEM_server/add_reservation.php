<?php

require 'connection.php';

if (array_key_exists("owner_id", $_POST) && array_key_exists("reservation_id", $_POST) && array_key_exists("start_date", $_POST) && array_key_exists("end_date", $_POST)) {

	$owner_id = $_POST["owner_id"];
	$reservation_id = $_POST["reservation_id"];
	$start_date = $_POST["start_date"];
	$end_date = $_POST["end_date"];

	try {

		
		$query = $mysqli->prepare("SELECT * FROM reservations WHERE owner_id = ? AND reservation_id = ?");
		$query->bind_param("ii", $owner_id, $reservation_id);
		$query->execute();
		$result = $query->get_result();

		if (mysqli_num_rows($result) > 0) {

			$output["success"] = false;
			$output["error"]   = "Reservation already Exists";

		} else {

			$query = $mysqli->prepare("INSERT INTO reservations (owner_id, reservation_id, start_date, end_date) VALUES (?, ?, ?, ?)");
			$query->bind_param("iiss", $owner_id, $reservation_id, $start_date, $end_date);
			$query->execute();
//
			// = $mysqli->prepare("UPDATE resources SET reservations_TEMP = reservations_TEMP + 1 WHERE reservation_id = ?");
			//$query->bind_param("i", $reservation_id);
		//	$query->execute();

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
