package com.example.newproject;

public class Konfigurasi
{
    //PESERTA
    // url dimana web API berada
    public static final String URL_GET_ALL_PESERTA = "http://192.168.43.157/training/peserta/tr_datas_peserta.php";
    public static final String URL_GET_DETAIL_PESERTA = "http://192.168.43.157/training/peserta/tr_detail_peserta.php?id_pst=";
    public static final String URL_ADD_PESERTA = "http://192.168.43.157/training/peserta/tr_add_peserta.php";
    public static final String URL_UPDATE_PESERTA = "http://192.168.43.157/training/peserta/tr_update_peserta.php";
    public static final String URL_DELETE_PESERTA = "http://192.168.43.157/training/peserta/tr_delete_peserta.php?id_pst=";

    // key and value JSON yang muncul di browser
    public static final String KEY_PST_ID = "id_pst";
    public static final String KEY_PST_NAMA = "nama_pst";
    public static final String KEY_PST_EMAIL = "email_pst";
    public static final String KEY_PST_HP = "hp_pst";
    public static final String KEY_PST_INSTANSI = "instansi_pst";

    // flag JSON
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_JSON_ID_PST = "id_pst";
    public static final String TAG_JSON_NAMA_PST = "nama_pst";
    public static final String TAG_JSON_EMAIL_PST = "email_pst";
    public static final String TAG_JSON_HP_PST = "hp_pst";
    public static final String TAG_JSON_INSTANSI_PST = "instansi_pst";

    // variabel ID peserta
    public static final String PST_ID = "id_pst";

    //MATERI
    // url dimana web API berada
    public static final String URL_GET_ALL_MATERI = "http://192.168.43.157/training/materi/tr_datas_materi.php";
    public static final String URL_GET_DETAIL_MATERI = "http://192.168.43.157/training/materi/tr_detail_materi.php?id_mat=";
    public static final String URL_ADD_MATERI = "http://192.168.43.157/training/materi/tr_add_materi.php";
    public static final String URL_UPDATE_MATERI = "http://192.168.43.157/training/materi/tr_update_materi.php";
    public static final String URL_DELETE_MATERI = "http://192.168.43.157/training/materi/tr_delete_materi.php?id_mat=";

    // key and value JSON yang muncul di browser
    public static final String KEY_MAT_ID = "id_mat";
    public static final String KEY_MAT_NAMA = "nama_mat";


    // flag JSON
    public static final String TAG_JSON_ID_MAT = "id_mat";
    public static final String TAG_JSON_NAMA_MAT = "nama_mat";

    // variabel ID materi
    public static final String MAT_ID = "id_mat";

    //INSTRUKTUR
    // url dimana web API berada
    public static final String URL_GET_ALL_INSTRUKTUR = "http://192.168.43.157/training/instruktur/tr_datas_instruktur.php";
    public static final String URL_GET_DETAIL_INSTRUKTUR = "http://192.168.43.157/training/instruktur/tr_detail_instruktur.php?id_ins=";
    public static final String URL_ADD_INSTRUKTUR = "http://192.168.43.157/training/instruktur/tr_add_instruktur.php";
    public static final String URL_UPDATE_INSTRUKTUR = "http://192.168.43.157/training/instruktur/tr_update_instruktur.php";
    public static final String URL_DELETE_INSTRUKTUR = "http://192.168.43.157/training/instruktur/tr_delete_instruktur.php?id_ins=";

    // key and value JSON yang muncul di browser
    public static final String KEY_INS_ID = "id_ins";
    public static final String KEY_INS_NAMA = "nama_ins";
    public static final String KEY_INS_EMAIL = "email_ins";
    public static final String KEY_INS_HP = "hp_ins";


    // flag JSON
    public static final String TAG_JSON_ID_INS = "id_ins";
    public static final String TAG_JSON_NAMA_INS = "nama_ins";
    public static final String TAG_JSON_EMAIL_INS = "email_ins";
    public static final String TAG_JSON_HP_INS = "hp_ins";

    // variabel ID materi
    public static final String INS_ID = "id_ins";

    //KELAS
    // url dimana web API berada
    public static final String URL_GET_ALL_KELAS = "http://192.168.43.157/training/kelas/tr_datas_kelas.php";
    public static final String URL_GET_DETAIL_KELAS = "http://192.168.43.157/training/kelas/tr_detail_kelas.php?id_kls=";
    public static final String URL_ADD_KELAS = "http://192.168.43.157/training/kelas/tr_add_kelas.php";
    public static final String URL_UPDATE_KELAS = "http://192.168.43.157/training/kelas/tr_update_kelas.php";
    public static final String URL_DELETE_KELAS = "http://192.168.43.157/training/kelas/tr_delete_kelas.php?id_kls=";

    // key and value JSON yang muncul di browser
    public static final String KEY_KLS_ID = "id_kls";
    public static final String KEY_KLS_TGLMULAI = "tgl_mulai_kls";
    public static final String KEY_KLS_TGLAKHIR = "tgl_akhir_kls";
    public static final String KEY_KLS_ID_INS = "id_ins";
    public static final String KEY_KLS_ID_MAT = "id_mat";
    public static final String KEY_KLS_NAMA_INS = "nama_ins";
    public static final String KEY_KLS_NAMA_MAT = "nama_mat";


    // flag JSON
    public static final String TAG_JSON_ID_KLS = "id_kls";
    public static final String TAG_JSON_TGLMULAI_KLS = "tgl_mulai_kls";
    public static final String TAG_JSON_TGLAKHIR_KLS = "tgl_akhir_kls";
    public static final String TAG_JSON_ID_INS_KLS = "id_ins";
    public static final String TAG_JSON_ID_MAT_KLS = "id_mat";
    public static final String TAG_JSON_NAMA_INS_KLS = "nama_ins";
    public static final String TAG_JSON_NAMA_MAT_KLS = "nama_mat";

    // variabel ID materi
    public static final String KLS_ID = "id_kls";

    //dropdown
    public static final String URL_GET_ALL_ID_INSTRUKTUR = "http://192.168.43.157/training/kelas/dropdown_id_instruktur.php";


    //DETAIL KELAS
    // url dimana web API berada
    public static final String URL_GET_ALL_DTL_KELAS = "http://192.168.43.157/training/detail_kelas/tr_datas_detail_kelas.php";
    public static final String URL_GET_DETAIL_DTL_KELAS = "http://192.168.43.157/training/detail_kelas/tr_detail_detail_kelas.php?id_detail_kls=";
    public static final String URL_ADD_DTL_KELAS = "http://192.168.43.157/training/detail_kelas/tr_add_detail_kelas.php";
    public static final String URL_UPDATE_DTL_KELAS = "http://192.168.43.157/training/detail_kelas/tr_update_detail_kelas.php";
    public static final String URL_DELETE_DTL_KELAS = "http://192.168.43.157/training/detail_kelas/tr_delete_detail_kelas.php?id_detail_kls=";

    // key and value JSON yang muncul di browser
    public static final String KEY_DK_ID_DET_KLS = "id_detail_kls";
    public static final String KEY_DK_ID_KLS = "id_kls";
    public static final String KEY_DK_NAMA_MAT = "nama_mat";
    public static final String KEY_DK_NAMA_PST = "nama_pst";
    public static final String KEY_DK_ID_PST = "id_pst";


    // flag JSON
    public static final String TAG_JSON_DK_ID_DET_KLS = "id_detail_kls";
    public static final String TAG_JSON_DK_ID_KLS = "id_kls";
    public static final String TAG_JSON_DK_NAMA_MAT = "nama_mat";
    public static final String TAG_JSON_DK_NAMA_PST = "nama_pst";
    public static final String TAG_JSON_DK_NAMA_INS = "nama_ins";
    public static final String TAG_JSON_DK_ID_PST = "id_pst";

    // variabel ID materi
    public static final String DK_ID_DK = "id_detail_kls";

    //dropdown
    public static final String URL_GET_ALL_DK_DROPDOWN ="http://192.168.43.157/training/detail_kelas/dropdown.php";


    //SEARCH
    public static final String URL_SEARCH_PST = "http://192.168.43.157/training/search/search_info_pst.php?id_pst=";
    public static final String URL_SEARCH_KLS = "http://192.168.43.157/training/search/search_info_kls.php?id_kls=";
    public static final String URL_SEARCH_INS = "http://192.168.43.157/training/search/search_info_ins.php?id_ins=";




}