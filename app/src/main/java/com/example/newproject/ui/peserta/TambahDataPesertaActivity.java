package com.example.newproject.ui.peserta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
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

import java.util.HashMap;

public class TambahDataPesertaActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_tambah_nama, edit_tambah_email, edit_tambah_hp, edit_tambah_instansi;
    private Button btn_tambah_peserta;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_peserta);
        getSupportActionBar().setTitle("Tambah Peserta");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_500)));

        edit_tambah_nama = findViewById(R.id.edit_tambah_nama);
        edit_tambah_email = findViewById(R.id.edit_tambah_email);
        edit_tambah_hp = findViewById(R.id.edit_tambah_hp);
        edit_tambah_instansi = findViewById(R.id.edit_tambah_instansi);
        btn_tambah_peserta = findViewById(R.id.btn_tambah_peserta);

        btn_tambah_peserta.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
         if (v == btn_tambah_peserta) {
             confirmDataPeserta();
//            simpanDataPeserta();
        }
    }

    private void confirmDataPeserta() {
        //get value text field
        final String nama = edit_tambah_nama.getText().toString().trim();
        final String email = edit_tambah_email.getText().toString().trim();
        final String hp = edit_tambah_hp.getText().toString().trim();
        final String instansi = edit_tambah_instansi.getText().toString().trim();

        //Confirmation altert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Insert Data");
        builder.setMessage("Are you sure want to insert this data? " +
                "\n Nama      : " + nama +
                "\n Email     : " + email +
                "\n Telephone : " + hp +
                "\n Instansi  : " + instansi);
        builder.setIcon(getResources().getDrawable(android.R.drawable.ic_delete));
        builder.setCancelable(false);
        builder.setNegativeButton("Cancel",null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                simpanDataPeserta();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void simpanDataPeserta() {
        // fields apa saja yang akan disimpan
        final String nama = edit_tambah_nama.getText().toString().trim();
        final String email = edit_tambah_email.getText().toString().trim();
        final String hp = edit_tambah_hp.getText().toString().trim();
        final String instansi = edit_tambah_instansi.getText().toString().trim();

        class SimpanDataPeserta extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDataPesertaActivity.this,
                        "Menyimpan Data", "Harap tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_PST_NAMA, nama);
                params.put(Konfigurasi.KEY_PST_EMAIL, email);
                params.put(Konfigurasi.KEY_PST_HP, hp);
                params.put(Konfigurasi.KEY_PST_INSTANSI, instansi);
                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_ADD_PESERTA, params);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(TambahDataPesertaActivity.this, "Berhasil Menambahkan Peserta",
                        Toast.LENGTH_SHORT).show();
                System.exit(1);
            }
        }
        SimpanDataPeserta simpanDataPeserta = new SimpanDataPeserta();
        simpanDataPeserta.execute();
    }


}