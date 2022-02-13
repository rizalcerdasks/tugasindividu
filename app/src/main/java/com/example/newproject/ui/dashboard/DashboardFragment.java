package com.example.newproject.ui.dashboard;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.newproject.R;

public class DashboardFragment extends Fragment implements View.OnClickListener{

    Button button_simpan;
    RadioGroup radioGroup;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        button_simpan = view.findViewById(R.id.button_simpan);

        EditText layout_nama = view.findViewById(R.id.dashboard_edit_name);
        EditText layout_email = view.findViewById(R.id.dashboard_edit_email);
        RadioButton yes = (RadioButton) view.findViewById(R.id.rb_ya);
        RadioButton no = (RadioButton) view.findViewById(R.id.rb_tidak);

        radioGroup = view.findViewById(R.id.id_radioGroup);
        button_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String res = null;
                Log.d("nama", String.valueOf(layout_nama.getText()));
                String nama = String.valueOf(layout_nama.getText());
                String email = String.valueOf(layout_email.getText());

                if(yes.isChecked()){
                    res = "Yes";
                }
                else if(no.isChecked()){
                    res = "No";
                }
                Log.d("selected_text",res);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Insert Data");
                builder.setMessage("Are you sure want to insert this data? " +
                        "\n Nama : " + nama +
                        "\n Email: " + email +
                        "\n Employee : " + res);
                builder.setIcon(getResources().getDrawable(android.R.drawable.ic_input_add));
                builder.setCancelable(false);
                builder.setNegativeButton("Cancel",null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }

        });

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == button_simpan){
//            Log.d("email", String.valueOf(layout_nama));
        }
    }
}