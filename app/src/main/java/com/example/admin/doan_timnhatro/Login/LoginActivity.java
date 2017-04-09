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
    ImageView imgAvatar_login;
    EditText edtTen__login, edtSoDT_login, edtPass_login;

    private FirebaseAuth mAuth;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://doantimnhatro.appspot.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        anhXa();
        batSuKien();
    }

    public void taoTaiKhoan(){
        String email=edtSoDT_login.getText().toString().trim()+"@gmail.com";
        String password=edtPass_login.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if(task.isSuccessful()){
                            // đặt tên và ảnh đại diện
                            loadAnh();
                        }

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                }
                });
    }

    public void loadAnh(){
        // Get the data from an ImageView as bytes
        imgAvatar_login.setDrawingCacheEnabled(true);
        imgAvatar_login.buildDrawingCache();
        Bitmap bitmap = imgAvatar_login.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        // Create a reference to "avatar.png"
        StorageReference mountainsRef = storageRef.child("Avatar/abc123.jpg");
        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();

                //-------Đặt tên và ảnh đại diện-----
                //lấy người dùng hiện tại
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                String ten=edtTen__login.getText().toString();

                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(ten)
                        .setPhotoUri(downloadUrl)
                        .build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "User profile updated.");
                                    Toast.makeText(LoginActivity.this, "Thành công ", Toast.LENGTH_SHORT).show();

                                    Intent intent=new Intent(LoginActivity.this,TrangChuActivity.class);
                                    startActivity(intent);
                                }
                                else
                                    Toast.makeText(LoginActivity.this, "Không thể tạo tên và ảnh đại diện", Toast.LENGTH_SHORT).show();
                            }
                        });

                //Toast.makeText(LoginActivity.this, String.valueOf(downloadUrl), Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void batSuKien() {
        txtDangKy.setOnClickListener(onDangKyClick);
        txtDangKy1.setOnClickListener(onDangKy1Click);
        txtDangNhap.setOnClickListener(onDangNhapClick);
        txtDangNhap1.setOnClickListener(onDangNhap1Click);
    }


    private void anhXa() {
        txtDangKy = (TextView) findViewById(R.id.txtDangKy);
        txtDangKy1 = (TextView) findViewById(R.id.txtDangKy1);
        txtDangNhap = (TextView) findViewById(R.id.txtDangNhap);
        txtDangNhap1 = (TextView) findViewById(R.id.txtDangNhap1);
        imgAvatar_login = (ImageView) findViewById(R.id.imgAvatar_login);
        edtTen__login = (EditText) findViewById(R.id.edtTen_login);
        edtSoDT_login = (EditText) findViewById(R.id.edtSDT_login);
        edtPass_login = (EditText) findViewById(R.id.edtPass_login);
    }

    private View.OnClickListener onDangKyClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            txtDangNhap.setTextColor(getResources().getColor(R.color.text_black));
            txtDangKy.setTextColor(getResources().getColor(R.color.text_blue));
            txtDangNhap1.setVisibility(View.GONE);
            edtTen__login.setVisibility(View.VISIBLE);
            txtDangKy1.setVisibility(View.VISIBLE);

        }
    };

    private View.OnClickListener onDangNhapClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            txtDangNhap.setTextColor(getResources().getColor(R.color.text_blue));
            txtDangKy.setTextColor(getResources().getColor(R.color.text_black));
            edtTen__login.setVisibility(View.GONE);
            txtDangKy1.setVisibility(View.GONE);
            txtDangNhap1.setVisibility(View.VISIBLE);
        }
    };

    private View.OnClickListener onDangKy1Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            txtDangKy1.setTextColor(getResources().getColor(R.color.text_red));
            if (edtTen__login.getText().toString().equals("") || edtSoDT_login.getText().toString().equals("")
                    || edtPass_login.getText().toString().equals("")) {
                Toast.makeText(LoginActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                    taoTaiKhoan();
            }

        }
    };
    private View.OnClickListener onDangNhap1Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            txtDangNhap1.setTextColor(getResources().getColor(R.color.text_red));
            String sdt=edtSoDT_login.getText().toString()+"@gmail.com";
            String pass=edtPass_login.getText().toString();

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
