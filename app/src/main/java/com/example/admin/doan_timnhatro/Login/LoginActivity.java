package com.example.admin.doan_timnhatro.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.doan_timnhatro.R;
import com.example.admin.doan_timnhatro.TrangChuActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    TextView txtDangKy, txtDangNhap, txtDangKy1, txtDangNhap1;
    ImageView imgAvatar;
    EditText edtTen, edtSoDT, edtPass;

    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;
    FirebaseDatabase fb;
    String uriImg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        fb = FirebaseDatabase.getInstance();

        anhXa();
        batSuKien();
    }

    private void batSuKien() {
        txtDangKy.setOnClickListener(onDangKyClick);
        txtDangKy1.setOnClickListener(onDangKy1Click1);
        txtDangNhap.setOnClickListener(onDangNhapClick);
        txtDangNhap1.setOnClickListener(onDangNhap1Click);
    }


    private void anhXa() {
        txtDangKy = (TextView) findViewById(R.id.txtDangKy);
        txtDangKy1 = (TextView) findViewById(R.id.txtDangKy1);
        txtDangNhap = (TextView) findViewById(R.id.txtDangNhap);
        txtDangNhap1 = (TextView) findViewById(R.id.txtDangNhap1);
        imgAvatar = (ImageView) findViewById(R.id.imgAvatar);
        edtTen = (EditText) findViewById(R.id.edtTen);
        edtSoDT = (EditText) findViewById(R.id.edtSDT);
        edtPass = (EditText) findViewById(R.id.edtPass);
    }

    private View.OnClickListener onDangKyClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            txtDangNhap.setTextColor(getResources().getColor(R.color.text_black));
            txtDangKy.setTextColor(getResources().getColor(R.color.text_blue));
            txtDangNhap1.setVisibility(View.GONE);
            edtTen.setVisibility(View.VISIBLE);
            txtDangKy1.setVisibility(View.VISIBLE);

        }
    };

    private View.OnClickListener onDangNhapClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            txtDangNhap.setTextColor(getResources().getColor(R.color.text_blue));
            txtDangKy.setTextColor(getResources().getColor(R.color.text_black));
            edtTen.setVisibility(View.GONE);
            txtDangKy1.setVisibility(View.GONE);
            txtDangNhap1.setVisibility(View.VISIBLE);
        }
    };

    private void dangKy() {
        final String soDT = edtSoDT.getText().toString() + "@gmail.com";
        String password = edtPass.getText().toString();

        mAuth.createUserWithEmailAndPassword(soDT, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                task.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(LoginActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                task.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void duaAnhVaoStorage() {

        //đẩy ảnh vào storge trên fibasse
        StorageReference mountainsRef = mStorageRef.child("avatar_" + edtSoDT.getText().toString() + ".png");
        // Get the data from an ImageView as bytes
        imgAvatar.setDrawingCacheEnabled(true);
        imgAvatar.buildDrawingCache();
        Bitmap bitmap = imgAvatar.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);

        Log.d(TAG, "duaAnhVaoStorage: ");
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(LoginActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Toast.makeText(LoginActivity.this, "Lấy được đường dẫn ảnh", Toast.LENGTH_SHORT).show();
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                uriImg = String.valueOf(downloadUrl);
            }
        });

    }

    public void luuThongTinUser() {
        duaAnhVaoStorage();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            String sdtUser = edtSoDT.getText().toString();
            String tenUser = edtTen.getText().toString().trim();

            Date date = new Date();
            String strDateFormat = "dd/MM/yyyy";
            //tạo đối tượng SimpleDateFormat;
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            //gọi hàm format để lấy chuỗi ngày tháng năm đúng theo yêu cầu
            String ngayTaoTK = sdf.format(date);
            //Muốn xuất Giờ:Phút:Giây AM (PM)
            String strDateFormat12 = "hh:mm:ss a";
            sdf = new SimpleDateFormat(strDateFormat12);

            ngayTaoTK += " " + sdf.format(date);

            fb.getReference("ListUser").child(uid).setValue(new UserInformation(uriImg, tenUser, sdtUser, ngayTaoTK));

        }
    }

    private View.OnClickListener onDangKy1Click1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (edtTen.getText().toString().equals("") || edtSoDT.getText().toString().equals("")
                    || edtPass.getText().toString().equals("")) {
                Toast.makeText(LoginActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                dangKy();
                luuThongTinUser();
                Intent intent = new Intent(LoginActivity.this, TrangChuActivity.class);
                startActivity(intent);
            }

        }
    };
    private View.OnClickListener onDangNhap1Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String sdt=edtSoDT.getText().toString()+"@gmail.com";
            String pass=edtPass.getText().toString();

            mAuth.signInWithEmailAndPassword(sdt,pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {
                        Log.w(TAG, "signInWithEmail", task.getException());
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent=new Intent(LoginActivity.this,TrangChuActivity.class);
                        startActivity(intent);
                    }

                }
            });
        }
    };
}
