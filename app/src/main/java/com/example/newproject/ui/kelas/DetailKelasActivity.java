package com.example.newproject.ui.kelas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.newproject.HttpHandler;
import com.example.newproject.Konfigurasi;
import com.example.newproject.R;
import com.example.newproject.ui.detailkelas.TambahDataDetailKelasActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailKelasActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edit_id_kelas, edit_tglmulai_kelas, edit_tglakhir_kelas, edit_id_instruktur_kelas, edit_id_materi_kelas;
    Button btn_update_kelas, btn_delete_kelas;
    String id_kls;
    Spinner spinner_ins, spinner_mat;
    private String JSON_STRING;
    private int spinner_value_ins, spinner_value_mat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kelas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Data Kelas");

        edit_id_kelas = findViewById(R.id.edit_id_kelas);
        edit_tglmulai_kelas = findViewById(R.id.edit_tglmulai_kelas);
        edit_tglakhir_kelas = findViewById(R.id.edit_tglakhir_kelas);
        edit_id_instruktur_kelas = findViewById(R.id.edit_id_instruktur_kelas);
        edit_id_materi_kelas = findViewById(R.id.edit_id_materi_kelas);

        spinner_ins = findViewById(R.id.spinner_ins);
        spinner_mat = findViewById(R.id.spinner_mat);

        btn_update_kelas = findViewById(R.id.btn_update_kelas);
        btn_delete_kelas = findViewById(R.id.btn_delete_kelas);

        //menerima intent dari class Kelas Fragment
        Intent receiveIntent = getIntent();
        id_kls = receiveIntent.getStringExtra(Konfigurasi.KLS_ID);
        edit_id_kelas.setText(id_kls);

        //mengambil data JSON
        getJSON();
        getJSON2();
        getJSON3();

        btn_update_kelas.setOnClickListener(this);
        btn_delete_kelas.setOnClickListener(this);
    }

    private void getJSON() {
        //bantuan dari class AsyncTask
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailKelasActivity.this,
                        "Mengambil Data", "Harap Tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_DETAIL_KELAS, id_kls);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                displayDetailData(message);
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void getJSON2() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(DetailKelasActivity.this, "Getting Data", "Please wait...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_INSTRUKTUR);
                Log.d("GetData", result);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();

                JSON_STRING = s;
                Log.d("Data_JSON", JSON_STRING);

                JSONObject jsonObject = null;
                ArrayList<String> listId = new ArrayList<>();
                ArrayList<String> listNama = new ArrayList<>();


                try {
                    jsonObject = new JSONObject(JSON_STRING);
                    JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
                    Log.d("Data_JSON_LIST: ", String.valueOf(jsonArray));


                    for (int i=0;i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString(Konfigurasi.TAG_JSON_ID_INS);
                        String nama = object.getString(Konfigurasi.TAG_JSON_ID_INS) + " - " + object.getString(Konfigurasi.TAG_JSON_NAMA_INS);


                        listId.add(id);
                        listNama.add(nama);
                        Log.d("DataArr: ", String.valueOf(id));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailKelasActivity.this, android.R.layout.simple_spinner_dropdown_item, listNama);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner_ins.setAdapter(adapter);
                spinner_ins.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                        spinner_value_ins = Integer.parseInt(listId.get(i));

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }
    private void getJSON3() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(DetailKelasActivity.this, "Getting Data", "Please wait...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_MATERI);
                Log.d("GetData", result);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();

                JSON_STRING = s;
                Log.d("Data_JSON", JSON_STRING);

                JSONObject jsonObject = null;
                ArrayList<String> listId = new ArrayList<>();
                ArrayList<String> listNama = new ArrayList<>();


                try {
                    jsonObject = new JSONObject(JSON_STRING);
                    JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
                    Log.d("Data_JSON_LIST: ", String.valueOf(jsonArray));


                    for (int i=0;i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString(Konfigurasi.TAG_JSON_ID_MAT);
                        String nama = object.getString(Konfigurasi.TAG_JSON_ID_MAT) + " - " + object.getString(Konfigurasi.TAG_JSON_NAMA_MAT);

                        listId.add(id);
                        listNama.add(nama);
                        Log.d("DataArr: ", String.valueOf(id));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailKelasActivity.this, android.R.layout.simple_spinner_dropdown_item, listNama);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner_mat.setAdapter(adapter);
                spinner_mat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                        spinner_value_mat = Integer.parseInt(listId.get(i));

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void displayDetailData(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject object = result.getJSONObject(0);

            String tglmulai = object.getString(Konfigurasi.TAG_JSON_TGLMULAI_KLS);
            String tglakhir = object.getString(Konfigurasi.TAG_JSON_TGLAKHIR_KLS);
            String namains = object.getString(Konfigurasi.TAG_JSON_NAMA_INS_KLS);
            String namamat = object.getString(Konfigurasi.TAG_JSON_NAMA_MAT_KLS);


            edit_tglmulai_kelas.setText(tglmulai);
            edit_tglakhir_kelas.setText(tglakhir);
            edit_id_instruktur_kelas.setText(namains);
            edit_id_materi_kelas.setText(namamat);
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View myButton) {
        if (myButton == btn_update_kelas)
        {
            updateDataKelas();
        }
        else if (myButton == btn_delete_kelas)
        {
            confirmDeleteDataKelas();
        }
    }

    private void confirmDeleteDataKelas() {
        //Confirmation alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Menghapus Data");
        builder.setMessage("Yakin mau hapus ?");
        builder.setIcon(getResources().getDrawable(android.R.drawable.ic_delete));
        builder.setCancelable(false);
        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteDataKelas();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteDataKelas() {
        class Delete extends AsyncTask<Void, Void, String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailKelasActivity.this, "Deleting Data", "Please Wait...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_DELETE_KELAS, id_kls);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(DetailKelasActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                System.exit(1);
            }
        }
        Delete delete = new Delete();
        delete.execute();
    }

    private void updateDataKelas() {
        // data apa saja yang akan diubah
        final String tglmulai = edit_tglmulai_kelas.getText().toString().trim();
        final String tglakhir = edit_tglakhir_kelas.getText().toString().trim();


        final String id_ins = String.valueOf(spinner_value_ins);
        final String id_mat = String.valueOf(spinner_value_mat);

        class UpdateDataKelas extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailKelasActivity.this, "Mengubah data", "Harap tunggu..", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_KLS_ID, id_kls);
                params.put(Konfigurasi.KEY_KLS_TGLMULAI, tglmulai);
                params.put(Konfigurasi.KEY_KLS_TGLAKHIR, tglakhir);
                params.put(Konfigurasi.KEY_KLS_ID_INS, id_ins);
                params.put(Konfigurasi.KEY_KLS_ID_MAT, id_mat);

                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_UPDATE_KELAS, params);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(DetailKelasActivity.this,
                        "Berhasil Update Data Kelas" , Toast.LENGTH_SHORT).show();
                System.exit(1);
            }
        }
        UpdateDataKelas updateDataKelas = new UpdateDataKelas();
        updateDataKelas.execute();
    }
}