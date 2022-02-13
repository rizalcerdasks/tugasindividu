package com.example.newproject.ui.materi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.newproject.HttpHandler;
import com.example.newproject.Konfigurasi;
import com.example.newproject.R;

import java.util.HashMap;

public class TambahDataMateriActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_tambah_nama_materi;
    private Button btn_tambah_materi;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_materi);
        getSupportActionBar().setTitle("Tambah Materi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_500)));

        edit_tambah_nama_materi = findViewById(R.id.edit_tambah_nama_materi);
        btn_tambah_materi = findViewById(R.id.btn_tambah_materi);
        btn_tambah_materi.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == btn_tambah_materi) {
            confirmDataMateri();
//            simpanDataMateri();
        }
    }

    private void confirmDataMateri() {
        //get value text field
        final String nama = edit_tambah_nama_materi.getText().toString().trim();

        //Confirmation altert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Insert Data");
        builder.setMessage("Are you sure want to insert this data? " +
                "\n Nama : " + nama);
        builder.setIcon(getResources().getDrawable(android.R.drawable.ic_delete));
        builder.setCancelable(false);
        builder.setNegativeButton("Cancel",null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                simpanDataMateri();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void simpanDataMateri() {
        // fields apa saja yang akan disimpan
        final String nama_mat = edit_tambah_nama_materi.getText().toString().trim();

        class SimpanDataMateri extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDataMateriActivity.this,
                        "Menyimpan Data", "Harap tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_MAT_NAMA, nama_mat);
                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_ADD_MATERI, params);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(TambahDataMateriActivity.this, "Berhasil Menambahkan Materi",
                        Toast.LENGTH_SHORT).show();
                System.exit(1);
            }
        }
        SimpanDataMateri simpanDataMateri = new SimpanDataMateri();
        simpanDataMateri.execute();
    }

}