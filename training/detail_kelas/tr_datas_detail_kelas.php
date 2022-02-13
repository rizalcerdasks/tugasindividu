<?php 
	//Import File Koneksi Database
	require_once('../koneksi.php');
	
	//Membuat SQL Query
	$sql = "SELECT a.id_detail_kls, c.id_mat, c.nama_mat, d.id_pst, d.nama_pst, e.id_ins, e.nama_ins FROM detail_kelas a 
	JOIN kelas b ON a.id_kls = b.id_kls 
	JOIN materi c ON b.id_mat = c.id_mat 
	JOIN peserta d ON a.id_pst = d.id_pst
	JOIN instruktur e ON b.id_ins = e.id_ins
	ORDER BY a.id_detail_kls";
	
	//Mendapatkan Hasil
	$r = mysqli_query($con,$sql);
	
	//Membuat Array Kosong 
	$result = array();
	
	while($row = mysqli_fetch_array($r)){
		
		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat 
		array_push($result,array(
			"id_detail_kls"=>$row['id_detail_kls'],
			"id_mat"=>$row['id_mat'],
			"nama_mat"=>$row['nama_mat'],
		"id_pst"=>$row['id_pst'],
            "nama_pst"=>$row['nama_pst'],
            "id_ins"=>$row['id_ins'],
            "nama_ins"=>$row['nama_ins']
		));
	}
	
	//Menampilkan Array dalam Format JSON
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>