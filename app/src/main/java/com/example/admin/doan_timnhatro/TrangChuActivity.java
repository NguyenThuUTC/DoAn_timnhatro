package com.example.admin.doan_timnhatro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.doan_timnhatro.DangTin.DangTinPhongTroActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class TrangChuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    TextView txtDangTin;
    ImageView imgAvatar_nav;
    TextView txtTen_nav,txtSoDT_nav;

    public static final int DANG_TIN_REQUESTCODE=1;

    private FirebaseAuth mAuth;
    FirebaseDatabase fb;

    public final static int PICK_PICTURE_AVATAR = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mAuth = FirebaseAuth.getInstance();


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fb=FirebaseDatabase.getInstance();
        anhXa();


        laythongtinnguoidung();
        batSuKien();
    }

    private void laythongtinnguoidung() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();

            txtTen_nav.setText(name);
            txtSoDT_nav.setText(email);
            Picasso.with(TrangChuActivity.this).load(String.valueOf(photoUrl)).into(imgAvatar_nav);
        }
    }


    private void batSuKien() {
        txtDangTin.setOnClickListener(DangTinClick);
        imgAvatar_nav.setOnClickListener(ClickThayDoiAvatar);
    }

    private View.OnClickListener ClickThayDoiAvatar=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PICTURE_AVATAR);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_PICTURE_AVATAR && resultCode == RESULT_OK){
            //thay ảnh đã chọn vào vị trí ảnh đại diện
            Uri imageUri = data.getData();
            imgAvatar_nav.setImageURI(imageUri);

        }
    }

    private View.OnClickListener DangTinClick =new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent dangTin=new Intent(TrangChuActivity.this, DangTinPhongTroActivity.class);
            startActivityForResult(dangTin,DANG_TIN_REQUESTCODE);

        }
    };


    private void anhXa() {
        txtDangTin= (TextView) findViewById(R.id.txtDangTin);

        View hView =  navigationView.getHeaderView(0);
        txtTen_nav = (TextView)hView.findViewById(R.id.txtTen_nav);
        txtSoDT_nav= (TextView) hView.findViewById(R.id.txtSoDT_nav);
        imgAvatar_nav= (ImageView) hView.findViewById(R.id.imgAvatar_nav);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.trang_chu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.mnTimNguoiOGhep) {
            // Handle the camera action
        } else if (id == R.id.mnTinDaDang) {

        } else if (id == R.id.mnHuyNhanTinNhan) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
