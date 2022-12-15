<?php

for($i = 0; $i < 5; $i++) {


$img = file_get_contents('http://localhost/BookEM/BookEM_server/assets/' . $i . '.jpg');
  
// Encode the image string data into base64
$data[] = base64_encode($img);
}
// Display the output
$a["Images"] = $data; 
$output["query_result"] = $a;
echo json_encode($output);
echo "Succeeded";