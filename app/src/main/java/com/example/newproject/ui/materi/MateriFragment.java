package com.example.newproject.ui.materi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.newproject.HttpHandler;
import com.example.newproject.Konfigurasi;
import com.example.newproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MateriFragment extends Fragment implements AdapterView.OnItemClickListener {
    private String JSON_STRING;
    public FloatingActionButton fab_materi;

    ListView list_view_mat;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_materi, container, false);
        list_view_mat = view.findViewById(R.id.listview_materi);
        fab_materi = view.findViewById(R.id.fab_materi);
        list_view_mat.setOnItemClickListener(this);

        getJSON();

        fab_materi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TambahDataMateriActivity.class));
            }
        });

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
    private void getJSON() {

        //bantuan dari class AsyncTask
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),
                        "Mengambil Data", "Harap Tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_MATERI);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                JSON_STRING = message;
                Log.d("DATA JSON: ", JSON_STRING);

                //menampilkan data dalam bentuk list view
                displayAllData();
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void displayAllData() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            Log.d("DATA JSON: ", JSON_STRING);

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                String id_mat = object.getString(Konfigurasi.TAG_JSON_ID_MAT);
                String nama_mat = object.getString(Konfigurasi.TAG_JSON_NAMA_MAT);
                HashMap<String, String> materi = new HashMap<>();
                materi.put(Konfigurasi.TAG_JSON_ID_MAT, id_mat);
                materi.put(Konfigurasi.TAG_JSON_NAMA_MAT, nama_mat);

                //ubah format JSON menjadi Array List
                list.add(materi);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // adapter untuk meletakan array list kedalam list view
        ListAdapter adapter = new SimpleAdapter(
                getActivity(), list,
                R.layout.list_item_materi,
                new String[]{Konfigurasi.TAG_JSON_ID_MAT, Konfigurasi.TAG_JSON_NAMA_MAT},
                new int[]{R.id.txt_id_materi, R.id.txt_nama_materi}
        );
        list_view_mat.setAdapter(adapter);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //ketika salah satu list dipilih
        Intent myIntent = new Intent(getActivity(), DetailMateriActivity.class);
        HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
        String matId = map.get(Konfigurasi.TAG_JSON_ID_MAT).toString();
        myIntent.putExtra(Konfigurasi.MAT_ID, matId);
        startActivity(myIntent);
    }
}
