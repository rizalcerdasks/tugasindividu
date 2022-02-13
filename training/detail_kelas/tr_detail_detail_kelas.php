<?php 
	//Mendapatkan Nilai Dari Variable ID Pegawai yang ingin ditampilkan
	$id_detail_kls = $_GET['id_detail_kls'];
	
	//Importing database
	require_once('../koneksi.php');
	
	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
	// $sql = "SELECT * FROM detail_kelas WHERE id_detail_kls=$id_detail_kls";

	$sql = "SELECT a.id_detail_kls, b.id_kls, c.id_mat, c.nama_mat, d.id_pst, d.nama_pst, e.id_ins, e.nama_ins FROM detail_kelas a 
	JOIN kelas b ON a.id_kls = b.id_kls 
	JOIN materi c ON b.id_mat = c.id_mat 
	JOIN peserta d ON a.id_pst = d.id_pst
	JOIN instruktur e ON b.id_ins = e.id_ins
	WHERE id_detail_kls=$id_detail_kls";
	
	//Mendapatkan Hasil 
	$r = mysqli_query($con,$sql);
	
	//Memasukkan Hasil Kedalam Array
	$result = array();
	$row = mysqli_fetch_array($r);
	array_push($result,array(
			"id_detail_kls"=>$row['id_detail_kls'],
			"id_kls"=>$row['id_kls'],
			"id_mat"=>$row['id_mat'],
			"nama_mat"=>$row['nama_mat'],
			"id_pst"=>$row['id_pst'],
			"nama_pst"=>$row['nama_pst'],
			"id_ins"=>$row['id_ins'],
			"nama_ins"=>$row['nama_ins']
		));

	//Menampilkan dalam format JSON
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>