package com.example.newproject.ui.detailkelas;

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

public class DetailDetailKelasActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edit_dk_id_detail_kelas, edit_dk_id_kls, edit_dk_id_pst;
    Button btn_update_dk_detail_kelas, btn_delete_dk_detail_kelas;
    String id_detail_kls;
    Spinner spinner_kls, spinner_pst;
    private String JSON_STRING;
    private int spinner_value_kls, spinner_value_pst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_detail_kelas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Kelas");

        edit_dk_id_detail_kelas = findViewById(R.id.edit_dk_id_detail_kelas);
        edit_dk_id_kls = findViewById(R.id.edit_dk_id_kls);
        edit_dk_id_pst = findViewById(R.id.edit_dk_id_pst);


        spinner_kls = findViewById(R.id.spinner_kls);
        spinner_pst = findViewById(R.id.spinner_pst);

        btn_update_dk_detail_kelas = findViewById(R.id.btn_update_dk_detail_kelas);
        btn_delete_dk_detail_kelas = findViewById(R.id.btn_delete_dk_detail_kelas);

        //menerima intent dari class Kelas Fragment
        Intent receiveIntent = getIntent();
        id_detail_kls = receiveIntent.getStringExtra(Konfigurasi.DK_ID_DK);
        edit_dk_id_detail_kelas.setText(id_detail_kls);

        //mengambil data JSON
        getJSON();
        getJSON2();
        getJSON3();

        btn_update_dk_detail_kelas.setOnClickListener(this);
        btn_delete_dk_detail_kelas.setOnClickListener(this);
    }

    private void getJSON() {
        //bantuan dari class AsyncTask
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailDetailKelasActivity.this,
                        "Mengambil Data", "Harap Tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_DETAIL_DTL_KELAS, id_detail_kls);
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
                progressDialog = ProgressDialog.show(DetailDetailKelasActivity.this, "Getting Data", "Please wait...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_KELAS);
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
                        String id = object.getString(Konfigurasi.TAG_JSON_ID_KLS);
                        String nama = object.getString(Konfigurasi.TAG_JSON_ID_KLS) + " - " + object.getString(Konfigurasi.TAG_JSON_NAMA_INS_KLS) + " - " + object.getString(Konfigurasi.TAG_JSON_NAMA_MAT_KLS);


                        listId.add(id);
                        listNama.add(nama);
                        Log.d("DataArr: ", String.valueOf(id));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailDetailKelasActivity.this, android.R.layout.simple_spinner_dropdown_item, listNama);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner_kls.setAdapter(adapter);
                spinner_kls.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                        spinner_value_kls = Integer.parseInt(listId.get(i));
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
                progressDialog = ProgressDialog.show(DetailDetailKelasActivity.this, "Getting Data", "Please wait...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_PESERTA);
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
                        String id = object.getString(Konfigurasi.TAG_JSON_ID_PST);
                        String nama = object.getString(Konfigurasi.TAG_JSON_ID_PST) + " - " + object.getString(Konfigurasi.TAG_JSON_NAMA_PST);

                        listId.add(id);
                        listNama.add(nama);
                        Log.d("DataArr: ", String.valueOf(id));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailDetailKelasActivity.this, android.R.layout.simple_spinner_dropdown_item, listNama);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner_pst.setAdapter(adapter);
                spinner_pst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                        spinner_value_pst = Integer.parseInt(listId.get(i));

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

            String nama_mat = object.getString(Konfigurasi.TAG_JSON_DK_NAMA_INS) +" - "+object.getString(Konfigurasi.TAG_JSON_DK_NAMA_MAT);
            String nama_pst = object.getString(Konfigurasi.TAG_JSON_DK_NAMA_PST);


            edit_dk_id_kls.setText(nama_mat);
            edit_dk_id_pst.setText(nama_pst);
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
        if (myButton == btn_update_dk_detail_kelas)
        {
            updateDataDetailKelas();
        }
        else if (myButton == btn_delete_dk_detail_kelas)
        {
            confirmDeleteDataDetailKelas();
        }
    }

    private void confirmDeleteDataDetailKelas() {
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
                deleteDataDetailKelas();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteDataDetailKelas() {
        class Delete extends AsyncTask<Void, Void, String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailDetailKelasActivity.this, "Deleting Data", "Please Wait...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_DELETE_DTL_KELAS, id_detail_kls);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(DetailDetailKelasActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                System.exit(1);
            }
        }
        Delete delete = new Delete();
        delete.execute();
    }

    private void updateDataDetailKelas() {
        // data apa saja yang akan diubah
        final String kls = String.valueOf(spinner_value_kls);
        final String pst = String.valueOf(spinner_value_pst);

        class UpdateDataDetailKelas extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailDetailKelasActivity.this, "Mengubah data", "Harap tunggu..", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_DK_ID_DET_KLS, id_detail_kls);
                params.put(Konfigurasi.KEY_DK_ID_KLS, kls);
                params.put(Konfigurasi.KEY_DK_ID_PST, pst);

                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_UPDATE_DTL_KELAS, params);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(DetailDetailKelasActivity.this,
                        "Berhasil Update Data Detail Kelas" , Toast.LENGTH_SHORT).show();
                System.exit(1);
            }
        }
        UpdateDataDetailKelas updateDataDetailKelas = new UpdateDataDetailKelas();
        updateDataDetailKelas.execute();
    }
}