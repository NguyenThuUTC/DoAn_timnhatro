package com.example.admin.doan_timnhatro.DangTin;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.admin.doan_timnhatro.R;

/**
 * Created by admin on 4/4/2017.
 */

public class DangTinDialog{

    Button btnThemAnh,btnDangAnh,btnDangTin,btnHuy;
    EditText edtTenChuNha,edtDTLienHe,edtDiaChi,edtDienTich,edtTang,edtGioGiac;
    RadioButton rdoChungChu,rdoKhongChungChu,rdocoDieuHoa,rdoKhongCoDieuHoa,rdoCoNL,rdoKhongNL,rdoNVSKhepKin,rdoNVSKhongKhepKin;
    RadioButton rdoCoChoDeXe,rdoKhongCoChoDeXe,rdoCoWifi,rdoTuKeoMang;
    ImageView  imgAnhPhongTro1;
    Dialog dialog;
    public void showDialog(Activity activity, String msg){
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dangtin_chothuephong);


        anhXa();
        batSuKien();


        dialog.show();
    }

    private void batSuKien() {
        btnThemAnh.setOnClickListener(ThemAnhClick);
        btnDangAnh.setOnClickListener(DangAnhClick);
        btnDangTin.setOnClickListener(DangTinClick);
        btnHuy.setOnClickListener(HuyClick);
    }

    private void anhXa(){
        //Tìm controls
        edtTenChuNha           = (EditText) dialog.findViewById(R.id.edtTenChuNha);
        edtDTLienHe            = (EditText) dialog.findViewById(R.id.edtDTLienHe);
        edtDiaChi              = (EditText) dialog.findViewById(R.id.edtDiaChi);
        edtDienTich            = (EditText) dialog.findViewById(R.id.edtDienTich);
        edtTang                = (EditText) dialog.findViewById(R.id.edtTang);
        edtGioGiac             = (EditText) dialog.findViewById(R.id.edtGioGiac);

        rdoChungChu         = (RadioButton) dialog.findViewById(R.id.rdoChungChu);
        rdoKhongChungChu    = (RadioButton) dialog.findViewById(R.id.rdoKhongChungChu);
        rdocoDieuHoa        = (RadioButton) dialog.findViewById(R.id.rdoCoDieuHoa);
        rdoKhongCoDieuHoa   = (RadioButton) dialog.findViewById(R.id.rdoKhongCoDieuHoa);
        rdoCoNL             = (RadioButton) dialog.findViewById(R.id.rdoCoNL);
        rdoKhongNL          = (RadioButton) dialog.findViewById(R.id.rdoKhongNL);
        rdoNVSKhepKin       = (RadioButton) dialog.findViewById(R.id.rdoNVSKhepKin);
        rdoNVSKhongKhepKin  = (RadioButton) dialog.findViewById(R.id.rdoNVSKhongKhepKin);
        rdoCoChoDeXe        = (RadioButton) dialog.findViewById(R.id.rdoCoChoDeXe);
        rdoKhongCoChoDeXe   = (RadioButton) dialog.findViewById(R.id.rdoKhongCoChoDeXe);
        rdoCoWifi           = (RadioButton) dialog.findViewById(R.id.rdoCoWifi);
        rdoTuKeoMang        = (RadioButton) dialog.findViewById(R.id.rdoTuKeoMang);

        imgAnhPhongTro1       = (ImageView) dialog.findViewById(R.id.imgAnhPhongTro1);

        btnThemAnh               = (Button) dialog.findViewById(R.id.btnThemAnh);
        btnDangAnh               = (Button) dialog.findViewById(R.id.btnDangAnh);
        btnDangTin               = (Button) dialog.findViewById(R.id.btnDangTin);
        btnHuy                   = (Button) dialog.findViewById(R.id.btnHuy);
    }

    private View.OnClickListener ThemAnhClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private View.OnClickListener DangAnhClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private View.OnClickListener DangTinClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private View.OnClickListener HuyClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
        }
    };

}
