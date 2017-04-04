package com.example.admin.doan_timnhatro.DangTin;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.doan_timnhatro.R;

/**
 * Created by admin on 4/4/2017.
 */

public class DangTinDialog{

    public void showDialog(Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dangtin_chothuephong);

        dialog.show();
    }
}
