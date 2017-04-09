package com.example.admin.doan_timnhatro.Login;

import java.io.Serializable;
import java.io.SerializablePermission;

/**
 * Created by admin on 4/3/2017.
 */

public class UserInformation implements Serializable {
    String urlAvatar;
    String ten;
    String soDT;

    public UserInformation() {
    }

    public UserInformation(String urlAvatar, String ten, String soDT) {
        this.urlAvatar = urlAvatar;
        this.ten = ten;
        this.soDT = soDT;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

}
