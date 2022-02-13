package com.example.newproject.ui.instruktur;

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

public class DetailInstrukturActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edit_id_instruktur, edit_nama_instruktur, edit_email_instruktur, edit_hp_instruktur;
    Button btn_update_instruktur, btn_delete_instruktur;
    String id_ins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_instruktur);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Data Instruktur");

        edit_id_instruktur = findViewById(R.id.edit_id_instruktur);
        edit_nama_instruktur = findViewById(R.id.edit_nama_instruktur);
        edit_email_instruktur = findViewById(R.id.edit_email_instruktur);
        edit_hp_instruktur = findViewById(R.id.edit_hp_instruktur);

        btn_update_instruktur = findViewById(R.id.btn_update_instruktur);
        btn_delete_instruktur = findViewById(R.id.btn_delete_instruktur);

        //menerima intent dari class Instruktur Fragment
        Intent receiveIntent = getIntent();
        id_ins = receiveIntent.getStringExtra(Konfigurasi.INS_ID);
        edit_id_instruktur.setText(id_ins);

        //mengambil data JSON
        getJSON();

        btn_update_instruktur.setOnClickListener(this);
        btn_delete_instruktur.setOnClickListener(this);
    }

    private void getJSON() {
        //bantuan dari class AsyncTask
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailInstrukturActivity.this,
                        "Mengambil Data", "Harap Tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_DETAIL_INSTRUKTUR, id_ins);
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

            String nama = object.getString(Konfigurasi.TAG_JSON_NAMA_INS);
            String email = object.getString(Konfigurasi.TAG_JSON_EMAIL_INS);
            String hp = object.getString(Konfigurasi.TAG_JSON_HP_INS);


            edit_nama_instruktur.setText(nama);
            edit_email_instruktur.setText(email);
            edit_hp_instruktur.setText(hp);

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
        if (myButton == btn_update_instruktur)
        {
            updateDataInstruktur();
        }
        else if (myButton == btn_delete_instruktur)
        {
            confirmDeleteDataInstruktur();
        }
    }

    private void confirmDeleteDataInstruktur() {
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
                deleteDataInstruktur();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteDataInstruktur() {
        class Delete extends AsyncTask<Void, Void, String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailInstrukturActivity.this, "Deleting Data", "Please Wait...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_DELETE_INSTRUKTUR, id_ins);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(DetailInstrukturActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                System.exit(1);
            }
        }
        Delete delete = new Delete();
        delete.execute();
    }

    private void updateDataInstruktur() {
        // data apa saja yang akan diubah
        final String nama = edit_nama_instruktur.getText().toString().trim();
        final String email = edit_email_instruktur.getText().toString().trim();
        final String hp = edit_hp_instruktur.getText().toString().trim();

        class UpdateDataInstruktur extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailInstrukturActivity.this, "Mengubah data", "Harap tunggu..", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_INS_ID, id_ins);
                params.put(Konfigurasi.KEY_INS_NAMA, nama);
                params.put(Konfigurasi.KEY_INS_EMAIL, email);
                params.put(Konfigurasi.KEY_INS_HP, hp);

                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_UPDATE_INSTRUKTUR, params);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(DetailInstrukturActivity.this,
                        "Berhasil Update Data Instruktur", Toast.LENGTH_SHORT).show();
                System.exit(1);
            }
        }
        UpdateDataInstruktur updateDataInstruktur = new UpdateDataInstruktur();
        updateDataInstruktur.execute();
    }
}