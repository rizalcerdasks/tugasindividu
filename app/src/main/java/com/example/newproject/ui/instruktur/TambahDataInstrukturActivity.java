package com.example.newproject.ui.instruktur;

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

public class TambahDataInstrukturActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_tambah_nama_instruktur, edit_tambah_email_instruktur, edit_tambah_hp_instruktur;
    private Button btn_tambah_instruktur;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_instruktur);
        getSupportActionBar().setTitle("Tambah Instruktur");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_500)));

        edit_tambah_nama_instruktur = findViewById(R.id.edit_tambah_nama_instruktur);
        edit_tambah_email_instruktur = findViewById(R.id.edit_tambah_email_instruktur);
        edit_tambah_hp_instruktur = findViewById(R.id.edit_tambah_hp_instruktur);

        btn_tambah_instruktur = findViewById(R.id.btn_tambah_instruktur);
        btn_tambah_instruktur.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == btn_tambah_instruktur) {
            confirmDataInstruktur();
//            simpanDataInstruktur();
        }
    }

    private void confirmDataInstruktur() {
        //get value text field
        final String nama = edit_tambah_nama_instruktur.getText().toString().trim();
        final String email = edit_tambah_email_instruktur.getText().toString().trim();
        final String hp = edit_tambah_hp_instruktur.getText().toString().trim();

        //Confirmation altert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Insert Data");
        builder.setMessage("Are you sure want to insert this data? " +
                "\n Nama  : " + nama +
                "\n Email : " + email +
                "\n No HP : " + hp);

        builder.setIcon(getResources().getDrawable(android.R.drawable.ic_input_add));
        builder.setCancelable(false);
        builder.setNegativeButton("Cancel",null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                simpanDataInstruktur();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void simpanDataInstruktur() {
        // fields apa saja yang akan disimpan
        final String nama = edit_tambah_nama_instruktur.getText().toString().trim();
        final String email = edit_tambah_email_instruktur.getText().toString().trim();
        final String hp = edit_tambah_hp_instruktur.getText().toString().trim();

        class SimpanDataInstruktur extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDataInstrukturActivity.this,
                        "Menyimpan Data", "Harap tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_INS_NAMA, nama);
                params.put(Konfigurasi.KEY_INS_EMAIL, email);
                params.put(Konfigurasi.KEY_INS_HP, hp);
                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_ADD_INSTRUKTUR, params);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(TambahDataInstrukturActivity.this, "Berhasil Menambahkan Instruktur",
                        Toast.LENGTH_SHORT).show();
                System.exit(1);
            }
        }
        SimpanDataInstruktur simpanDataInstruktur = new SimpanDataInstruktur();
        simpanDataInstruktur.execute();
    }

}