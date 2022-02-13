package com.example.newproject.ui.kelas;

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

public class TambahDataKelasActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edit_tambah_tglmulai_kelas, edit_tambah_tglakhir_kelas;
    private Button btn_tambah_kelas;
    Spinner spinner_id_instruktur, spinner_id_materi;
    private String JSON_STRING;
    private int spinner_value_ins, spinner_value_mat;

    DatePickerDialog.OnDateSetListener setListener_mulai;
    DatePickerDialog.OnDateSetListener setListener_akhir;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_kelas);
        getSupportActionBar().setTitle("Tambah Kelas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_500)));

        edit_tambah_tglmulai_kelas = findViewById(R.id.edit_tambah_tglmulai_kelas);
        edit_tambah_tglakhir_kelas= findViewById(R.id.edit_tambah_tglakhir_kelas);

        spinner_id_instruktur = findViewById(R.id.spinner_id_instruktur);
        spinner_id_materi = findViewById(R.id.spinner_id_materi);
        btn_tambah_kelas = findViewById(R.id.btn_tambah_kelas);

        btn_tambah_kelas.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        edit_tambah_tglmulai_kelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        TambahDataKelasActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener_mulai,year,month,day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        edit_tambah_tglakhir_kelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        TambahDataKelasActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener_akhir,year,month,day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener_mulai = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = year+"-"+month+"-"+dayOfMonth;
                edit_tambah_tglmulai_kelas.setText(date);
            }
        };

        setListener_akhir = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = year+"-"+month+"-"+dayOfMonth;
                edit_tambah_tglakhir_kelas.setText(date);
            }
        };



        getJSON();
        getJSON2();

    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(TambahDataKelasActivity.this, "Getting Data", "Please wait...", false, false);
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
                        String nama = object.getString(Konfigurasi.TAG_JSON_NAMA_INS);

                        listId.add(id);
                        listNama.add(nama);
                        Log.d("DataArr: ", String.valueOf(id));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahDataKelasActivity.this, android.R.layout.simple_spinner_dropdown_item, listNama);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner_id_instruktur.setAdapter(adapter);
                spinner_id_instruktur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void getJSON2() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(TambahDataKelasActivity.this, "Getting Data", "Please wait...", false, false);
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
                        String nama = object.getString(Konfigurasi.TAG_JSON_NAMA_MAT);

                        listId.add(id);
                        listNama.add(nama);
                        Log.d("DataArr: ", String.valueOf(id));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahDataKelasActivity.this, android.R.layout.simple_spinner_dropdown_item, listNama);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner_id_materi.setAdapter(adapter);
                spinner_id_materi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    @Override
    public void onClick(View view) {
        if (view == btn_tambah_kelas) {
            simpanDataKelas();
        }
    }

    private void simpanDataKelas() {
        // fields apa saja yang akan disimpan
        final String tgl_mulai_kls = edit_tambah_tglmulai_kelas.getText().toString().trim();
        final String tgl_akhir_kls = edit_tambah_tglakhir_kelas.getText().toString().trim();

        final String id_ins = String.valueOf(spinner_value_ins);
        final String id_mat= String.valueOf(spinner_value_mat);


        class SimpanDataKelas extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDataKelasActivity.this, "Menyimpan Data", "Harap menunggu...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_KLS_TGLMULAI, tgl_mulai_kls);
                params.put(Konfigurasi.KEY_KLS_TGLAKHIR, tgl_akhir_kls);
                params.put(Konfigurasi.KEY_KLS_ID_INS, id_ins);
                params.put(Konfigurasi.KEY_KLS_ID_MAT, id_mat);
                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_ADD_KELAS, params);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(TambahDataKelasActivity.this, "Berhasil Menambahkan Kelas",
                        Toast.LENGTH_LONG).show();
                System.exit(1);
            }
        }
        SimpanDataKelas simpanDataKelas = new SimpanDataKelas();
        simpanDataKelas.execute();
    }

}