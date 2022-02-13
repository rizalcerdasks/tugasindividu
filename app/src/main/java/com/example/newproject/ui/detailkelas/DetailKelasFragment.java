package com.example.newproject.ui.detailkelas;

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
import com.example.newproject.ui.kelas.DetailKelasActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailKelasFragment extends Fragment implements AdapterView.OnItemClickListener {
    private String JSON_STRING;
    public FloatingActionButton fab_detail_kelas;

    ListView list_view_dtl_kls;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_kelas, container, false);
        list_view_dtl_kls = view.findViewById(R.id.listview_detailkelas);
        fab_detail_kelas = view.findViewById(R.id.fab_detail_kelas);
        list_view_dtl_kls.setOnItemClickListener(this);

        getJSON();

        fab_detail_kelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TambahDataDetailKelasActivity.class));
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
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_DTL_KELAS);
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
                String id_det_kls = object.getString(Konfigurasi.TAG_JSON_DK_ID_DET_KLS);
                String nama_mat = object.getString(Konfigurasi.TAG_JSON_DK_NAMA_MAT);
                String nama_pst = object.getString(Konfigurasi.TAG_JSON_DK_NAMA_PST);
                HashMap<String, String> detail_kelas  = new HashMap<>();
                detail_kelas.put(Konfigurasi.TAG_JSON_DK_ID_DET_KLS, id_det_kls);
                detail_kelas.put(Konfigurasi.TAG_JSON_DK_NAMA_MAT, nama_mat);
                detail_kelas.put(Konfigurasi.TAG_JSON_DK_NAMA_PST, nama_pst);

                //ubah format JSON menjadi Array List
                list.add(detail_kelas);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // adapter untuk meletakan array list kedalam list view
        ListAdapter adapter = new SimpleAdapter(
                getActivity(), list,
                R.layout.list_item_detail_kelas,
                new String[]{Konfigurasi.TAG_JSON_DK_ID_DET_KLS, Konfigurasi.TAG_JSON_DK_NAMA_MAT,Konfigurasi.TAG_JSON_DK_NAMA_PST},
                new int[]{R.id.txt_id_dk, R.id.txt_nama_materi_dk, R.id.txt_nama_peserta_dk}
        );
        list_view_dtl_kls.setAdapter(adapter);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //ketika salah satu list dipilih
        Intent myIntent = new Intent(getActivity(), DetailDetailKelasActivity.class);
        HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
        String dklsId = map.get(Konfigurasi.TAG_JSON_DK_ID_DET_KLS).toString();
        myIntent.putExtra(Konfigurasi.DK_ID_DK, dklsId);
        startActivity(myIntent);
    }
}