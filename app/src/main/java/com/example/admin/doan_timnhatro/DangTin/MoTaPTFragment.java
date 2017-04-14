package com.example.admin.doan_timnhatro.DangTin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.doan_timnhatro.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoTaPTFragment extends Fragment implements View.OnClickListener{

    TextView txtTiepTuc,txtQuayLai;

    Spinner spDoiTuongThue,spGioGiac;

    RadioButton rdoChungChu,rdoKhongchungChu,rdoCoDieuHoa,rdoKhongCoDieuHoa,rdoCoNL,rdoKhongNL,rdoNVSKhepKin;
    RadioButton rdoNVSKhongKhepKin,rdoCoChoDeXe,rdoKhongCoChoDeXe,rdoCoWifi,rdoKhongWifi;

    EditText edtSoLuongPhong,edtDienTich, edtGiaPhong, edtTang, edtTgianMoCua,edtTgianDongCua;

    TextView txtMoCua,txtDongCua;

    public MoTaPTFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mo_ta_pt,container,false);

        txtTiepTuc= (TextView) view.findViewById(R.id.txtTiepTucDangTin);
        txtQuayLai= (TextView) view.findViewById(R.id.txtQuayLai);
        txtMoCua= (TextView) view.findViewById(R.id.txtMoCua);
        txtDongCua= (TextView) view.findViewById(R.id.txtDongCua);

        //-----------------
        spDoiTuongThue= (Spinner) view.findViewById(R.id.spDoiTuongThue);
        spGioGiac= (Spinner) view.findViewById(R.id.spGioGiac);

        //------------------
        rdoChungChu= (RadioButton) view.findViewById(R.id.rdoChungChu);
        rdoKhongchungChu= (RadioButton) view.findViewById(R.id.rdoKhongChungChu);
        rdoCoDieuHoa= (RadioButton) view.findViewById(R.id.rdoCoDieuHoa);
        rdoKhongCoDieuHoa= (RadioButton) view.findViewById(R.id.rdoKhongCoDieuHoa);
        rdoCoNL= (RadioButton) view.findViewById(R.id.rdoCoNL);
        rdoKhongNL= (RadioButton) view.findViewById(R.id.rdoKhongNL);
        rdoNVSKhepKin= (RadioButton) view.findViewById(R.id.rdoNVSKhepKin);
        rdoNVSKhongKhepKin= (RadioButton) view.findViewById(R.id.rdoNVSKhongKhepKin);
        rdoCoChoDeXe= (RadioButton) view.findViewById(R.id.rdoCoChoDeXe);
        rdoKhongCoChoDeXe= (RadioButton) view.findViewById(R.id.rdoKhongCoChoDeXe);
        rdoCoWifi= (RadioButton) view.findViewById(R.id.rdoCoWifi);
        rdoKhongWifi= (RadioButton) view.findViewById(R.id.rdoKhongCoWifi);

        //-------------------
        edtSoLuongPhong= (EditText) view.findViewById(R.id.edtSoLuongPhong);
        edtDienTich= (EditText) view.findViewById(R.id.edtDienTich);
        edtGiaPhong= (EditText) view.findViewById(R.id.edtGiaPhong);
        edtTang= (EditText) view.findViewById(R.id.edtTang);
        edtTgianMoCua= (EditText) view.findViewById(R.id.edtTgianMoCua);
        edtTgianDongCua= (EditText) view.findViewById(R.id.edtTgianDongCua);

        //bắt sự kiện
        txtTiepTuc.setOnClickListener(this);
        txtQuayLai.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.txtTiepTucDangTin){
            layThongTinTruyenDi();
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

    private void layThongTinTruyenDi() {
        String doiTuongThue=spDoiTuongThue.getSelectedItem().toString();
        String chungchu="";
        if(rdoChungChu.isChecked()){
            chungchu="Có";
        }else chungchu="Không";
        int soLuongPhong=Integer.parseInt(edtSoLuongPhong.getText().toString());
        int dienTich=Integer.parseInt(edtDienTich.getText().toString());
        int giaPhong=Integer.parseInt(edtGiaPhong.getText().toString());
        String tang=edtTang.getText().toString();
        String gioGiac="";
        if(spGioGiac.getSelectedItem().toString().equals("Tự do")){
            gioGiac="Tự do";
        }else if(spGioGiac.getSelectedItem().toString().equals("Quy định")){
            gioGiac="Mở cửa lúc: "+edtTgianMoCua.getText().toString()+" / Khóa cửa lúc "+edtTgianDongCua.getText().toString();
        }
        String dieuHoa="";
        if(rdoCoDieuHoa.isChecked()){
            dieuHoa="Có";
        }else dieuHoa="Không";
        String nongLanh="";
        if(rdoCoNL.isChecked()){
            nongLanh="Có";
        }else nongLanh="Không";



        Bundle bundle = new Bundle();

        bundle.putString("doiTuongThue",doiTuongThue);

    }
}
