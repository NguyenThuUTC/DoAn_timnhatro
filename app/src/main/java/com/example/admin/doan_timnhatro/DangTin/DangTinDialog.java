package com.example.admin.doan_timnhatro.DangTin;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.sax.RootElement;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.admin.doan_timnhatro.R;
import com.example.admin.doan_timnhatro.TrangChuActivity;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by admin on 4/4/2017.
 */

public class DangTinDialog extends DialogFragment implements View.OnClickListener {//DialogFragment là một dạng api mới cho phép người dùng nhập liệu trong dialog

    Button btnThemAnh, btnDangAnh, btnDangTin, btnHuy;
    EditText edtTenChuNha, edtDTLienHe, edtDiaChi, edtDienTich, edtTang, edtGioGiac;
    RadioButton rdoChungChu, rdoKhongChungChu, rdocoDieuHoa, rdoKhongCoDieuHoa, rdoCoNL, rdoKhongNL, rdoNVSKhepKin, rdoNVSKhongKhepKin;
    RadioButton rdoCoChoDeXe, rdoKhongCoChoDeXe, rdoCoWifi, rdoTuKeoMang;

    public final static int CHON_ANH_REQUESTCODE = 1;


    //----khi người dùng chọn ảnh xong-----
    private LinearLayout lnrImages;
    private Button btnAddPhots;
    private Button btnSaveImages;
    private ArrayList<String> imagesPathList;
    private Bitmap yourbitmap;
    private Bitmap resized;
    private final int PICK_IMAGE_MULTIPLE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dangtin_chothuephong, null);
        anhXa(view);

        btnDangAnh.setOnClickListener(this);
        btnDangTin.setOnClickListener(this);
        btnHuy.setOnClickListener(this);


        return view;
    }


    private void anhXa(View view) {

        //Tìm controls
        edtTenChuNha = (EditText) view.findViewById(R.id.edtTenChuNha);
        edtDTLienHe = (EditText) view.findViewById(R.id.edtDTLienHe);
        edtDiaChi = (EditText) view.findViewById(R.id.edtDiaChi);
        edtDienTich = (EditText) view.findViewById(R.id.edtDienTich);
        edtTang = (EditText) view.findViewById(R.id.edtTang);
        edtGioGiac = (EditText) view.findViewById(R.id.edtGioGiac);

        rdoChungChu = (RadioButton) view.findViewById(R.id.rdoChungChu);
        rdoKhongChungChu = (RadioButton) view.findViewById(R.id.rdoKhongChungChu);
        rdocoDieuHoa = (RadioButton) view.findViewById(R.id.rdoCoDieuHoa);
        rdoKhongCoDieuHoa = (RadioButton) view.findViewById(R.id.rdoKhongCoDieuHoa);
        rdoCoNL = (RadioButton) view.findViewById(R.id.rdoCoNL);
        rdoKhongNL = (RadioButton) view.findViewById(R.id.rdoKhongNL);
        rdoNVSKhepKin = (RadioButton) view.findViewById(R.id.rdoNVSKhepKin);
        rdoNVSKhongKhepKin = (RadioButton) view.findViewById(R.id.rdoNVSKhongKhepKin);
        rdoCoChoDeXe = (RadioButton) view.findViewById(R.id.rdoCoChoDeXe);
        rdoKhongCoChoDeXe = (RadioButton) view.findViewById(R.id.rdoKhongCoChoDeXe);
        rdoCoWifi = (RadioButton) view.findViewById(R.id.rdoCoWifi);
        rdoTuKeoMang = (RadioButton) view.findViewById(R.id.rdoTuKeoMang);


        btnDangAnh = (Button) view.findViewById(R.id.btnDangAnh);
        btnDangTin = (Button) view.findViewById(R.id.btnDangTin);
        btnHuy = (Button) view.findViewById(R.id.btnHuy);


        lnrImages = (LinearLayout) view.findViewById(R.id.lnrImages);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnDangAnh) {
            Intent intent = new Intent(getActivity(), ChonAnhTrongGalleryActivity.class);
            startActivityForResult(intent, CHON_ANH_REQUESTCODE);
        } else if (v.getId() == R.id.btnDangTin) {

        } else if (v.getId() == R.id.btnHuy) {

        } else {
            //thêm ảnh
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CHON_ANH_REQUESTCODE) {
                imagesPathList = new ArrayList<String>();
                String[] imagesPath = data.getStringExtra("data").split("\\|");
                try {
                    lnrImages.removeAllViews();
                } catch (Throwable e) {
                    e.printStackTrace();
                }

                //--lấy chiều dài và chiều rộng màn hình
                Display display = getActivity().getWindowManager().getDefaultDisplay();
                DisplayMetrics outMetrics = new DisplayMetrics();
                display.getMetrics(outMetrics);
                int screen_height = outMetrics.heightPixels;
                int screen_width = outMetrics.widthPixels;


                for (int i = 0; i < imagesPath.length; i++) {
                    imagesPathList.add(imagesPath[i]);
                    yourbitmap = BitmapFactory.decodeFile(imagesPath[i]);
                    ImageView imageView = new ImageView(getActivity());
                    lnrImages.addView(imageView);
                    ViewGroup.LayoutParams params = imageView.getLayoutParams();
                    params.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    //params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    params.height = 150;
//                    imageView.requestLayout();
                    imageView.setPadding(0, 10, 0, 0);
                    //imageView.getLayoutParams().height = 150;

                    imageView.setImageBitmap(yourbitmap);
                    imageView.setAdjustViewBounds(true);

                }
            }
        }
    }
}
