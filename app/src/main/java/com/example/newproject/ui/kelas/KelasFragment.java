package com.example.newproject.ui.kelas;

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

public class KelasFragment extends Fragment implements AdapterView.OnItemClickListener {
    private String JSON_STRING;
    public FloatingActionButton fab_kelas;

    ListView list_view_kls;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kelas, container, false);
        list_view_kls = view.findViewById(R.id.listview_kelas);
        fab_kelas = view.findViewById(R.id.fab_kelas);
        list_view_kls.setOnItemClickListener(this);

        getJSON();

        fab_kelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TambahDataKelasActivity.class));
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
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_KELAS);
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
                String id_kls = object.getString(Konfigurasi.TAG_JSON_ID_KLS);
                String tgl_mulai_kls = object.getString(Konfigurasi.TAG_JSON_TGLMULAI_KLS);
                String tgl_akhir_kls = object.getString(Konfigurasi.TAG_JSON_TGLAKHIR_KLS);
                HashMap<String, String> kelas = new HashMap<>();
                kelas.put(Konfigurasi.TAG_JSON_ID_KLS, id_kls);
                kelas.put(Konfigurasi.TAG_JSON_TGLMULAI_KLS, tgl_mulai_kls);
                kelas.put(Konfigurasi.TAG_JSON_TGLAKHIR_KLS, tgl_akhir_kls);

                //ubah format JSON menjadi Array List
                list.add(kelas);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // adapter untuk meletakan array list kedalam list view
        ListAdapter adapter = new SimpleAdapter(
                getActivity(), list,
                R.layout.list_item_kelas,
                new String[]{Konfigurasi.TAG_JSON_ID_KLS, Konfigurasi.TAG_JSON_TGLMULAI_KLS, Konfigurasi.TAG_JSON_TGLAKHIR_KLS},
                new int[]{R.id.txt_id_kelas, R.id.txt_tglmulai_kelas, R.id.txt_tglakhir_kelas}
        );
        list_view_kls.setAdapter(adapter);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //ketika salah satu list dipilih
        Intent myIntent = new Intent(getActivity(), DetailKelasActivity.class);
        HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
        String klsId = map.get(Konfigurasi.TAG_JSON_ID_KLS).toString();
        myIntent.putExtra(Konfigurasi.KLS_ID, klsId);
        startActivity(myIntent);
    }
}
