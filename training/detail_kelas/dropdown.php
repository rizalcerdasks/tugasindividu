<?php 
	//Import File Koneksi Database
	require_once('../koneksi.php');
	
	//Membuat SQL Query
	$sql = "SELECT a.id_kls,c.nama_mat,i.nama_ins FROM kelas a
	JOIN materi c ON a.id_mat = c.id_mat
	JOIN instruktur i ON i.id_ins = a.id_ins";
	
	//Mendapatkan Hasil
	$r = mysqli_query($con,$sql);
	
	//Membuat Array Kosong 
	$result = array();
	
	while($row = mysqli_fetch_array($r)){
		
		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat 
		array_push($result,array(
			"id_kls"=>$row['id_kls'],
			"nama_mat"=>$row['nama_mat'],
            "nama_ins"=>$row['nama_ins']
		));
	}
	
	//Menampilkan Array dalam Format JSON
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>