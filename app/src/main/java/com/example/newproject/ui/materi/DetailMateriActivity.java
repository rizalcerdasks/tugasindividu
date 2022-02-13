package com.example.newproject.ui.materi;

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

public class DetailMateriActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edit_id_materi, edit_nama_materi;
    Button btn_update_materi, btn_delete_materi;
    String id_mat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_materi);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Data Materi");

        edit_id_materi = findViewById(R.id.edit_id_materi);
        edit_nama_materi = findViewById(R.id.edit_nama_materi);

        btn_update_materi = findViewById(R.id.btn_update_materi);
        btn_delete_materi = findViewById(R.id.btn_delete_materi);

        //menerima intent dari class Materi Fragment
        Intent receiveIntent = getIntent();
        id_mat = receiveIntent.getStringExtra(Konfigurasi.MAT_ID);
        edit_id_materi.setText(id_mat);

        //mengambil data JSON
        getJSON();

        btn_update_materi.setOnClickListener(this);
        btn_delete_materi.setOnClickListener(this);
    }

    private void getJSON() {
        //bantuan dari class AsyncTask
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailMateriActivity.this,
                        "Mengambil Data", "Harap Tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_DETAIL_MATERI, id_mat);
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

    private void displayDetailData(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject object = result.getJSONObject(0);

            String nama = object.getString(Konfigurasi.TAG_JSON_NAMA_MAT);


            edit_nama_materi.setText(nama);
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
        if (myButton == btn_update_materi)
        {
            updateDataMateri();
        }
        else if (myButton == btn_delete_materi)
        {
            confirmDeleteDataMateri();
        }
    }

    private void confirmDeleteDataMateri() {
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
                deleteDataMateri();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteDataMateri() {
        class Delete extends AsyncTask<Void, Void, String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailMateriActivity.this, "Deleting Data", "Please Wait...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_DELETE_MATERI, id_mat);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(DetailMateriActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                System.exit(1);
            }
        }
        Delete delete = new Delete();
        delete.execute();
    }

    private void updateDataMateri() {
        // data apa saja yang akan diubah
        final String nama = edit_nama_materi.getText().toString().trim();

        class UpdateDataMateri extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailMateriActivity.this, "Mengubah data", "Harap tunggu..", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_MAT_ID, id_mat);
                params.put(Konfigurasi.KEY_MAT_NAMA, nama);

                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_UPDATE_MATERI, params);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(DetailMateriActivity.this,
                        "Berhasil Update Data Materi", Toast.LENGTH_SHORT).show();
                System.exit(1);
            }
        }
        UpdateDataMateri updateDataMateri = new UpdateDataMateri();
        updateDataMateri.execute();
    }
}