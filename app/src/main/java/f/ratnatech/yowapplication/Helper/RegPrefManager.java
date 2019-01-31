package f.ratnatech.yowapplication.Helper;


import android.content.Context;
import android.content.SharedPreferences;


public class RegPrefManager {

    private SharedPreferences mSharedPreferences;
    private static RegPrefManager mPrefManager;
    public Context mContext;

    private RegPrefManager(Context context) {
        this.mContext=context;
        mSharedPreferences = context.
                getSharedPreferences("digichamps_mentor_app_pref", Context.MODE_PRIVATE);
    }

    public static RegPrefManager getInstance(Context context) {
        if (mPrefManager == null) {
            mPrefManager = new RegPrefManager(context);
        }
        return mPrefManager;
    }
    public void setPhone(String ph) {
        mSharedPreferences.
                edit().putString("ph", ph).apply();
    }


    public String getPhone() {
        return mSharedPreferences.
                getString("ph", null);
    }

    public void logout() {

        mSharedPreferences.edit().clear().apply();
       // mContext.getContentResolver().delete(DbContract.CoinTable.CONTENT_URI,null,null);
       // mContext.getContentResolver().delete(DbContract.NotificationTable.CONTENT_URI,null,null);

        //mContext.getContentResolver().delete(DbContract.ExamTable.CONTENT_URI,null,null);

        //mContext.getContentResolver().delete(DbContract.UserTable.CONTENT_URI,null,null);

    }

    public void setUserId(int userId) {
        mSharedPreferences.
                edit().putInt("userId", userId).apply();
    }


    public int getUserId() {
        return mSharedPreferences.
                getInt("userId", 0);
    }

    public void setFirstName(String userName) {
        mSharedPreferences.
                edit().putString("firstName", userName).apply();
    }


    public String getFirstName() {
        return mSharedPreferences.
                getString("firstName", null);
    }
    public void setSecondName(String userName) {
        mSharedPreferences.
                edit().putString("secondName", userName).apply();
    }


    public String getSecondName() {
        return mSharedPreferences.
                getString("secondName", null);
    }

    public String getMobileNumber() {
        return mSharedPreferences.
                getString("mobilenumber", null);
    }

    public void setMobileNumber(String mobilenumber) {
        mSharedPreferences.
                edit().putString("mobilenumber", mobilenumber).apply();
    }
    public String getEmailNumber() {
        return mSharedPreferences.
                getString("emailid", null);
    }

    public void setEmailNumber(String emailid) {
        mSharedPreferences.
                edit().putString("emailid", emailid).apply();
    }

    public String getOTP() {
        return mSharedPreferences.
                getString("OTP", null);
    }

    public void setOTP(String OTP) {
        mSharedPreferences.
                edit().putString("OTP", OTP).apply();
    }


    public String getSaveUser() {
        return mSharedPreferences.
                getString("SaveID", null);
    }

    public void setSaveUser(String id) {
        mSharedPreferences.
                edit().putString("SaveID", id).apply();
    }
    public String getPickLocationName() {
        return mSharedPreferences.
                getString("PickUpLocationName", null);
    }

    public void setPickLocationName(String name) {
        mSharedPreferences.
                edit().putString("PickUpLocationName", name).apply();
    }

    public String getPickLocationAddress() {
        return mSharedPreferences.
                getString("PickUpLocationAddress", null);
    }

    public void setPickLocationAddress(String name) {
        mSharedPreferences.
                edit().putString("PickUpLocationAddress", name).apply();
    }
    public String getPickLocationLatLng() {
        return mSharedPreferences.
                getString("PickLocationLatLng", null);
    }

    public void setPickLocationLatLng(String latLng) {
        mSharedPreferences.
                edit().putString("PickLocationLatLng", latLng).apply();
    }

    public String getChooseLocation() {
        return mSharedPreferences.
                getString("ChooseLocation", null);
    }

    public void setChooseLocation(String loc) {
        mSharedPreferences.
                edit().putString("ChooseLocation", loc).apply();
    }


    public String getDropLocationName() {
        return mSharedPreferences.
                getString("DropLocationName", null);
    }

    public void setDropLocationName(String name) {
        mSharedPreferences.
                edit().putString("DropLocationName", name).apply();
    }

    public String getDropLocationAddress() {
        return mSharedPreferences.
                getString("DropLocationAddress", null);
    }

    public void setDropLocationAddress(String name) {
        mSharedPreferences.
                edit().putString("DropLocationAddress", name).apply();
    }
    public String getDropLocationLatLng() {
        return mSharedPreferences.
                getString("DropLocationLatLng", null);
    }

    public void setDropLocationLatLng(String latLng) {
        mSharedPreferences.
                edit().putString("DropLocationLatLng", latLng).apply();
    }

    public String getRate() {
        return mSharedPreferences.
                getString("Rate", null);
    }

    public void setRate(String latLng) {
        mSharedPreferences.
                edit().putString("Rate", latLng).apply();
    }

    public String getZipcode() {
        return mSharedPreferences.
                getString("zipcode", null);
    }

    public void setZipcode(String zipcode) {
        mSharedPreferences.
                edit().putString("zipcode", zipcode).apply();
    }

    public void clearlocation(){
        mSharedPreferences.edit().remove("PickUpLocationName").commit();
        mSharedPreferences.edit().remove("PickUpLocationAddress").commit();
        mSharedPreferences.edit().remove("PickLocationLatLng").commit();
        mSharedPreferences.edit().remove("ChooseLocation").commit();
        mSharedPreferences.edit().remove("DropLocationName").commit();
        mSharedPreferences.edit().remove("DropLocationAddress").commit();
        mSharedPreferences.edit().remove("DropLocationLatLng").commit();
    }
}

