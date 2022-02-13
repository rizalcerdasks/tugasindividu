package com.example.newproject.ui.detailkelas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.newproject.HttpHandler;
import com.example.newproject.Konfigurasi;
import com.example.newproject.MainActivity;
import com.example.newproject.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class TambahDataDetailKelasActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_dk_tambah_detail_kelas;
    Spinner spinner_dk_id_kelas, spinner_dk_id_peserta;
    private String JSON_STRING;
    private int spinner_value_kls, spinner_value_pst;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_detail_kelas);
        getSupportActionBar().setTitle("Tambah Detail Kelas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_500)));

        spinner_dk_id_kelas = findViewById(R.id.spinner_dk_id_kelas);
        spinner_dk_id_peserta = findViewById(R.id.spinner_dk_id_peserta);

        btn_dk_tambah_detail_kelas = findViewById(R.id.btn_dk_tambah_detail_kelas);
        btn_dk_tambah_detail_kelas.setOnClickListener(this);

        getJSON();
        getJSON2();

    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(TambahDataDetailKelasActivity.this, "Getting Data", "Please wait...", false, false);
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
                ArrayList<String> listNamaIns = new ArrayList<>();
                ArrayList<String> listNamaMat = new ArrayList<>();

                try {
                    jsonObject = new JSONObject(JSON_STRING);
                    JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
                    Log.d("Data_JSON_LIST: ", String.valueOf(jsonArray));


                    for (int i=0;i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id_kls = object.getString(Konfigurasi.TAG_JSON_ID_KLS);
                        String nama_ins = object.getString(Konfigurasi.TAG_JSON_ID_KLS) + " - " + object.getString(Konfigurasi.TAG_JSON_NAMA_INS_KLS) + " - " + object.getString(Konfigurasi.TAG_JSON_NAMA_MAT_KLS);
                        String nama_mat = object.getString(Konfigurasi.TAG_JSON_NAMA_INS_KLS);

                        listId.add(id_kls);
                        listNamaIns.add(nama_ins);
                        listNamaMat.add(nama_mat);
                        Log.d("DataArr: ", String.valueOf(id_kls));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahDataDetailKelasActivity.this, android.R.layout.simple_spinner_dropdown_item, listNamaIns);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner_dk_id_kelas.setAdapter(adapter);
                spinner_dk_id_kelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void getJSON2() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(TambahDataDetailKelasActivity.this, "Getting Data", "Please wait...", false, false);
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
                        String nama = object.getString(Konfigurasi.TAG_JSON_ID_PST) + " - " +object.getString(Konfigurasi.TAG_JSON_NAMA_PST) ;

                        listId.add(id);
                        listNama.add(nama);
                        Log.d("DataArr: ", String.valueOf(id));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahDataDetailKelasActivity.this, android.R.layout.simple_spinner_dropdown_item, listNama);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                spinner_dk_id_peserta.setAdapter(adapter);
                spinner_dk_id_peserta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    @Override
    public void onClick(View view) {
        if (view == btn_dk_tambah_detail_kelas) {
            simpanDataDetailKelas();
        }
    }

    private void simpanDataDetailKelas() {
        // fields apa saja yang akan disimpan

        final String id_kls = String.valueOf(spinner_value_kls);
        final String id_pst= String.valueOf(spinner_value_pst);


        class SimpanDataDetailKelas extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDataDetailKelasActivity.this, "Menyimpan Data", "Harap menunggu...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_DK_ID_KLS, id_kls);
                params.put(Konfigurasi.KEY_DK_ID_PST, id_pst);
                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_ADD_DTL_KELAS, params);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(TambahDataDetailKelasActivity.this, "Berhasil Menambahkan Kelas",
                        Toast.LENGTH_LONG).show();
                System.exit(1);
            }
        }
        SimpanDataDetailKelas simpanDataDetailKelas = new SimpanDataDetailKelas();
        simpanDataDetailKelas.execute();
    }

}