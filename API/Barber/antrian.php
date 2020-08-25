<?php

include("config.php");



$sql = "SELECT * FROM booking  ORDER BY `tgl` ASC";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    array_push($result, array('nama' => $row['nama'],
    'tgl' => $row['tgl'],
    'jam' => $row['jam']
));
}
echo json_encode(array("result" => $result));
?>



