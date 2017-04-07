package com.example.admin.doan_timnhatro.DangTin;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.sax.RootElement;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.admin.doan_timnhatro.R;
import com.example.admin.doan_timnhatro.TrangChuActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by admin on 4/4/2017.
 */

public class DangTinDialog extends DialogFragment implements View.OnClickListener{//DialogFragment là một dạng api mới cho phép người dùng nhập liệu trong dialog

    Button btnThemAnh,btnDangAnh,btnDangTin,btnHuy;
    EditText edtTenChuNha,edtDTLienHe,edtDiaChi,edtDienTich,edtTang,edtGioGiac;
    RadioButton rdoChungChu,rdoKhongChungChu,rdocoDieuHoa,rdoKhongCoDieuHoa,rdoCoNL,rdoKhongNL,rdoNVSKhepKin,rdoNVSKhongKhepKin;
    RadioButton rdoCoChoDeXe,rdoKhongCoChoDeXe,rdoCoWifi,rdoTuKeoMang;
    ImageView  imgAnhPhongTro1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dangtin_chothuephong,null);
        anhXa(view);

        btnThemAnh.setOnClickListener(this);
        btnDangAnh.setOnClickListener(this);
        btnDangTin.setOnClickListener(this);
        btnHuy.setOnClickListener(this);


        return view;
    }


    private void anhXa(View view){

        //Tìm controls
        edtTenChuNha           = (EditText) view.findViewById(R.id.edtTenChuNha);
        edtDTLienHe            = (EditText) view.findViewById(R.id.edtDTLienHe);
        edtDiaChi              = (EditText) view.findViewById(R.id.edtDiaChi);
        edtDienTich            = (EditText) view.findViewById(R.id.edtDienTich);
        edtTang                = (EditText) view.findViewById(R.id.edtTang);
        edtGioGiac             = (EditText) view.findViewById(R.id.edtGioGiac);

        rdoChungChu         = (RadioButton) view.findViewById(R.id.rdoChungChu);
        rdoKhongChungChu    = (RadioButton) view.findViewById(R.id.rdoKhongChungChu);
        rdocoDieuHoa        = (RadioButton) view.findViewById(R.id.rdoCoDieuHoa);
        rdoKhongCoDieuHoa   = (RadioButton) view.findViewById(R.id.rdoKhongCoDieuHoa);
        rdoCoNL             = (RadioButton) view.findViewById(R.id.rdoCoNL);
        rdoKhongNL          = (RadioButton) view.findViewById(R.id.rdoKhongNL);
        rdoNVSKhepKin       = (RadioButton) view.findViewById(R.id.rdoNVSKhepKin);
        rdoNVSKhongKhepKin  = (RadioButton) view.findViewById(R.id.rdoNVSKhongKhepKin);
        rdoCoChoDeXe        = (RadioButton) view.findViewById(R.id.rdoCoChoDeXe);
        rdoKhongCoChoDeXe   = (RadioButton) view.findViewById(R.id.rdoKhongCoChoDeXe);
        rdoCoWifi           = (RadioButton) view.findViewById(R.id.rdoCoWifi);
        rdoTuKeoMang        = (RadioButton) view.findViewById(R.id.rdoTuKeoMang);

        imgAnhPhongTro1       = (ImageView) view.findViewById(R.id.imgAnhPhongTro1);

        btnThemAnh               = (Button) view.findViewById(R.id.btnThemAnh);
        btnDangAnh               = (Button) view.findViewById(R.id.btnDangAnh);
        btnDangTin               = (Button) view.findViewById(R.id.btnDangTin);
        btnHuy                   = (Button) view.findViewById(R.id.btnHuy);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnDangAnh){
            Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
            gallery.setType("image/*");
            // Thêm dòng này để có thể select nhiều ảnh trong 1 lần nhé các bạn
            gallery.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            startActivityForResult(Intent.createChooser(gallery, "Select Files to Upload"),
                    1);
        }
        else if(v.getId()==R.id.btnDangTin){

        }
        else if(v.getId()==R.id.btnHuy){

        }
        else {
            //thêm ảnh
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            imgAnhPhongTro1.setImageURI(imageUri);

        }
    }
}
