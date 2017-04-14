package com.example.admin.doan_timnhatro.DangTin;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.doan_timnhatro.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThongTinLienHeFragment extends Fragment implements View.OnClickListener{


    EditText edtTenChuNha,edtDiaChi,edtSoDT;
    Spinner spTinh_ThanhPho,spQuan_Huyen,spPhuong_xa;
    TextView txtTiepTucDangTin;
    String ten,sdt,diaChi,tinh_tp,quan_huyen,phuong_xa;
    public ThongTinLienHeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_thong_tin_lien_he, container, false);

        edtTenChuNha = (EditText) view.findViewById(R.id.edtTenChuNha);
        edtDiaChi = (EditText) view.findViewById(R.id.edtDiaChi);
        edtSoDT= (EditText) view.findViewById(R.id.edtSoDT);
        spTinh_ThanhPho= (Spinner) view.findViewById(R.id.spTinh_ThanhPho);
        spQuan_Huyen= (Spinner) view.findViewById(R.id.spQuan_Huyen);
        spPhuong_xa= (Spinner) view.findViewById(R.id.spPhuong_Xa);
        txtTiepTucDangTin= (TextView) view.findViewById(R.id.txtTiepTucDangTin);

        txtTiepTucDangTin.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.txtTiepTucDangTin){
            txtTiepTucDangTin.setTextColor(getResources().getColor(R.color.text_red));
            ten=edtTenChuNha.getText().toString();
            diaChi=edtDiaChi.getText().toString();
            sdt=edtSoDT.getText().toString();
            tinh_tp=spTinh_ThanhPho.getSelectedItem().toString();
            quan_huyen=spQuan_Huyen.getSelectedItem().toString();
            phuong_xa=spPhuong_xa.getSelectedItem().toString();
            Bundle bundle = new Bundle();
            bundle.putString("ten", ten);
            bundle.putString("diaChi", diaChi);
            bundle.putString("soDT",sdt);
            bundle.putString("tinh_tp",tinh_tp);
            bundle.putString("quan_huyen",quan_huyen);
            bundle.putString("phuong_xa",phuong_xa);

            MoTaPTFragment moTaPTFragment = new MoTaPTFragment();
            //moTaPTFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fmContent, moTaPTFragment)
                    .commit();
        }
    }
}
