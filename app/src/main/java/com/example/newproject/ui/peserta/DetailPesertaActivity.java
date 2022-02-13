package com.example.newproject.ui.peserta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newproject.HttpHandler;
import com.example.newproject.Konfigurasi;
import com.example.newproject.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class DetailPesertaActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edit_id, edit_nama, edit_email, edit_hp, edit_instansi;
    Button btn_update_peserta, btn_delete_peserta;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_peserta);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Data Peserta");

        edit_id = findViewById(R.id.edit_id);
        edit_nama = findViewById(R.id.edit_nama);
        edit_email = findViewById(R.id.edit_email);
        edit_hp = findViewById(R.id.edit_hp);
        edit_instansi = findViewById(R.id.edit_instansi);
        btn_update_peserta = findViewById(R.id.btn_update_peserta);
        btn_delete_peserta = findViewById(R.id.btn_delete_peserta);


        //menerima intent dari class Peserta Fragment
        Intent receiveIntent = getIntent();
        id = receiveIntent.getStringExtra(Konfigurasi.PST_ID);
        edit_id.setText(id);

        //mengambil data JSON
        getJSON();

        btn_update_peserta.setOnClickListener(this);
        btn_delete_peserta.setOnClickListener(this);
    }

    private void getJSON() {
        //bantuan dari class AsyncTask
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailPesertaActivity.this,
                        "Mengambil Data", "Harap Tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_DETAIL_PESERTA, id);
                return result;
            }

            @Override
            protected void onPostExecute(String messagep) {
                super.onPostExecute(messagep);
                loading.dismiss();
                displayDetailData(messagep);
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

            String nama = object.getString(Konfigurasi.TAG_JSON_NAMA_PST);
            String email = object.getString(Konfigurasi.TAG_JSON_EMAIL_PST);
            String hp = object.getString(Konfigurasi.TAG_JSON_HP_PST);
            String instansi = object.getString(Konfigurasi.TAG_JSON_INSTANSI_PST);


            edit_nama.setText(nama);
            edit_email.setText(email);
            edit_hp.setText(hp);
            edit_instansi.setText(instansi);
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
        if (myButton == btn_update_peserta)
        {
            updateDataPeserta();
        }
        else if (myButton == btn_delete_peserta)
        {
            confirmDeleteDataPeserta();
        }
    }

    private void confirmDeleteDataPeserta() {
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
                deleteDataPeserta();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteDataPeserta() {
        class Delete extends AsyncTask<Void, Void, String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailPesertaActivity.this, "Deleting Data", "Please Wait...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_DELETE_PESERTA, id);
                return result;
            }

            @Override
            protected void onPostExecute(String messagep) {
                super.onPostExecute(messagep);
                loading.dismiss();
                Toast.makeText(DetailPesertaActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                System.exit(1);
            }
        }
        Delete delete = new Delete();
        delete.execute();
    }

    private void updateDataPeserta() {
        // data apa saja yang akan diubah
        final String nama = edit_nama.getText().toString().trim();
        final String email = edit_email.getText().toString().trim();
        final String hp = edit_hp.getText().toString().trim();
        final String instansi = edit_instansi.getText().toString().trim();

        class UpdateDataPeserta extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailPesertaActivity.this, "Mengubah data", "Harap tunggu..", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_PST_ID, id);
                params.put(Konfigurasi.KEY_PST_NAMA, nama);
                params.put(Konfigurasi.KEY_PST_EMAIL, email);
                params.put(Konfigurasi.KEY_PST_HP, hp);
                params.put(Konfigurasi.KEY_PST_INSTANSI, instansi);
                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_UPDATE_PESERTA, params);
                return result;
            }

            @Override
            protected void onPostExecute(String messagep) {
                super.onPostExecute(messagep);
                loading.dismiss();
                Toast.makeText(DetailPesertaActivity.this,
                        "Berhasil Update Data Peserta" , Toast.LENGTH_SHORT).show();
                System.exit(1);
            }
        }
        UpdateDataPeserta updateDataPeserta = new UpdateDataPeserta();
        updateDataPeserta.execute();
    }
}