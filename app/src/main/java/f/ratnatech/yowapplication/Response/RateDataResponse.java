package f.ratnatech.yowapplication.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RatnaDev008 on 12/28/2018.
 */

public class RateDataResponse {
    @SerializedName("Id")
    private int Id;
    @SerializedName("City")
    private String City;
    @SerializedName("Pincode")
    private String Pincode;
    @SerializedName("Vehicle_Type")
    private String Vehicle_Type;
    @SerializedName("Trip_Type")
    private String Trip_Type;
    @SerializedName("Min_KM")
    private int Min_KM;
    @SerializedName("Max_KM")
    private int Max_KM;
    @SerializedName("Base_Rate")
    private String Base_Rate;
    @SerializedName("Rate_Modifier")
    private String Rate_Modifier;
    @SerializedName("KM_Rate")
    private String KM_Rate;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public String getVehicle_Type() {
        return Vehicle_Type;
    }

    public void setVehicle_Type(String vehicle_Type) {
        Vehicle_Type = vehicle_Type;
    }

    public String getTrip_Type() {
        return Trip_Type;
    }

    public void setTrip_Type(String trip_Type) {
        Trip_Type = trip_Type;
    }

    public int getMin_KM() {
        return Min_KM;
    }

    public void setMin_KM(int min_KM) {
        Min_KM = min_KM;
    }

    public int getMax_KM() {
        return Max_KM;
    }

    public void setMax_KM(int max_KM) {
        Max_KM = max_KM;
    }

    public String getBase_Rate() {
        return Base_Rate;
    }

    public void setBase_Rate(String base_Rate) {
        Base_Rate = base_Rate;
    }

    public String getRate_Modifier() {
        return Rate_Modifier;
    }

    public void setRate_Modifier(String rate_Modifier) {
        Rate_Modifier = rate_Modifier;
    }

    public String getKM_Rate() {
        return KM_Rate;
    }

    public void setKM_Rate(String KM_Rate) {
        this.KM_Rate = KM_Rate;
    }

    public String getWaiting_Charge() {
        return Waiting_Charge;
    }

    public void setWaiting_Charge(String waiting_Charge) {
        Waiting_Charge = waiting_Charge;
    }

    public String getWEF() {
        return WEF;
    }

    public void setWEF(String WEF) {
        this.WEF = WEF;
    }

    public String getWET() {
        return WET;
    }

    public void setWET(String WET) {
        this.WET = WET;
    }

    @SerializedName("Waiting_Charge")
    private String Waiting_Charge;
    @SerializedName("WEF")
    private String WEF;
    @SerializedName("WET")
    private String WET;

}
