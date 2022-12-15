<?php

require 'connection.php';

$username = $_POST["username"];
$password = $_POST["password"];
$email = $_POST["email"];

$query = $mysqli->prepare("INSERT INTO `users` (`user_id`, `username`, `password`,`email`) VALUES (NULL, ?, ?, ?)");
$query->bind_param("sss", $username, $password, $email);
$query->execute();

echo "Succeeded";