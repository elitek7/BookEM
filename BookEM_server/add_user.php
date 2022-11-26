<?php

require 'connection.php';

$username = $_POST["username"];
$password = $_POST["password"];
$email = $_POST["email"];

$query = $mysqli->prepare("INSERT INTO `users` (`user_id`, `username`, `password`) VALUES (NULL, ?, ?)");
$query->bind_param("ss", $username, $password, $email);
$query->execute();

echo "Succeeded";