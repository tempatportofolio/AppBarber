<?php

include("config.php");

$nama = $_POST['nama'];
$tgl = $_POST['tgl'];
$jam = $_POST['jam'];
$layanan = $_POST['layanan'];


$sql = "INSERT INTO booking VALUES ( NULL,'$nama' , '$tgl', '$jam', '$layanan' )";
$query = mysqli_query($db , $sql);

// apakah query update berhasil ?
if ($query) {
  echo json_encode(array('message'=>'Selamat Mencukur :)'));
} else {
  // kalau gagal tampilkan pesan
  die("Gagal menyimpan perubahan");
}