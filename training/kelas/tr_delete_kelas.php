<?php 
 //Mendapatkan Nilai ID
 $id_detail_kls = $_GET['id_kls'];
 
 //Import File Koneksi Database
 require_once('../koneksi.php');
 
 //Membuat SQL Query
 $sql = "DELETE FROM kelas WHERE id_kls=$id_detail_kls;";

 
 //Menghapus Nilai pada Database 
 if(mysqli_query($con,$sql)){
 echo 'Berhasil Menghapus Kelas';
 }else{
 echo 'Gagal Menghapus Kelas';
 }
 
 mysqli_close($con);
 ?>