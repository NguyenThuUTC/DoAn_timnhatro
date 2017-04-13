package com.example.admin.doan_timnhatro.DangTin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.admin.doan_timnhatro.R;

public class DangTinPhongTroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_tin_phong_tro);

        // Gọi hàm này để khi chạy lần đầu tiên màn hình sẽ hiển thị MoTaPTFragment
        callFragment(new ThongTinLienHeFragment());

    }



    // Hàm gọi Fragment để khi click vào thì sẽ gọi Fragment tương ứng.
    // Hàm này có tham số truyền vào 1 Fragment
    public void callFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //Khi được goi, fragment truyền vào sẽ thay thế vào vị trí FrameLayout trong Activity chính
        transaction.replace(R.id.fmContent, fragment);
        transaction.commit();
    }

}
