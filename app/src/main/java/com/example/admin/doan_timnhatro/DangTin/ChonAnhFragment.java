package com.example.admin.doan_timnhatro.DangTin;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.doan_timnhatro.R;
import com.example.admin.doan_timnhatro.TrangChuActivity;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChonAnhFragment extends Fragment implements View.OnClickListener {

    TextView txtQuayLai, txtAnh, txtHuy, txtDangTin;

    private ArrayList<String> imagesPathList;
    private Bitmap yourbitmap;
    private Bitmap resized;
    private final int PICK_IMAGE_MULTIPLE = 1;
    public final static int CHON_ANH_REQUESTCODE = 1;

    RecyclerView recyclerViewAnh;
    RecycerviewAdapter adapter;
    List<String> listAnh;

    public ChonAnhFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chon_anh, container, false);

        recyclerViewAnh= (RecyclerView) view.findViewById(R.id.recyclerviewAnh);
        listAnh=new ArrayList<>();

        txtQuayLai = (TextView) view.findViewById(R.id.txtQuayLai);
        txtAnh = (TextView) view.findViewById(R.id.txtAnh);
        txtHuy = (TextView) view.findViewById(R.id.txtHuy);
        txtDangTin = (TextView) view.findViewById(R.id.txtDangTin);

        txtQuayLai.setOnClickListener(this);
        txtAnh.setOnClickListener(this);
        txtHuy.setOnClickListener(this);
        txtDangTin.setOnClickListener(this);


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.txtQuayLai) {

        } else if (v.getId() == R.id.txtAnh) {
            txtAnh.setTextColor(getResources().getColor(R.color.text_red));
            Intent intent = new Intent(getActivity(), ChonAnhTrongGalleryActivity.class);
            startActivityForResult(intent, CHON_ANH_REQUESTCODE);
        } else if (v.getId() == R.id.txtHuy) {
            Intent intentHuy = new Intent(getActivity(), TrangChuActivity.class);
            startActivity(intentHuy);
        } else if (v.getId() == R.id.txtDangTin) {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CHON_ANH_REQUESTCODE) {
                String[] imagesPath = data.getStringExtra("data").split("\\|");


                for (int i = 0; i < imagesPath.length; i++) {
                    listAnh.add(imagesPath[i]);
                }

                //khởi tạo adapter cho recyclerview và gán vào recyclerview
                adapter=new RecycerviewAdapter(listAnh);
                LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewAnh.setLayoutManager(layoutManager);
                recyclerViewAnh.setAdapter(adapter);
            }
        }
    }
}
