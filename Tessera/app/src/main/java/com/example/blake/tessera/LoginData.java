//package com.example.blake.tessera;
//
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//import java.util.ArrayList;
//
//public class LoginData {
//
//    @SerializedName("name")
//    @Expose
//    private static String mName;
//    @SerializedName("id")
//    @Expose
//    private static Integer mId;
//
//    public LoginData(String name, Integer id) {
//        mName = name;
//        mId = id;
//    }
//
//    public static String getName() {
//        return mName;
//    }
//
//    public void setName(String name) {
//        mName = name;
//    }
//
//    public static Integer getId() {
//        return mId;
//    }
//
//    public void setId(Integer id) {
//       mId = id;
//    }
//
//    public static ArrayList<LoginData> idList (int numId) {
//        ArrayList<LoginData> loginUser = new ArrayList<LoginData>();
//
//        for (int i = 1; i <= numId; i++) {
//            loginUser.add(new LoginData(mName, mId));
//        }
//
//        return loginUser;
//    }
//}