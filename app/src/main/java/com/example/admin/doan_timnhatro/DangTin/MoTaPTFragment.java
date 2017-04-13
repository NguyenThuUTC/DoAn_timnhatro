package com.example.admin.doan_timnhatro.DangTin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.doan_timnhatro.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoTaPTFragment extends Fragment implements View.OnClickListener{

    TextView txtTiepTuc,txtQuayLai;
    public MoTaPTFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mo_ta_pt,container,false);

        txtTiepTuc= (TextView) view.findViewById(R.id.txtTiepTucDangTin);
        txtQuayLai= (TextView) view.findViewById(R.id.txtQuayLai);

        //bắt sự kiện
        txtTiepTuc.setOnClickListener(this);
        txtQuayLai.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.txtTiepTucDangTin){
            txtTiepTuc.setTextColor(getResources().getColor(R.color.text_red));
            ChonAnhFragment chonAnhFragment = new ChonAnhFragment();
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fmContent, chonAnhFragment)
                    .commit();
        }
        else if(v.getId()==R.id.txtQuayLai){

        }
    }
}
