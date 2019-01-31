package f.ratnatech.yowapplication.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RatnaDev008 on 1/2/2019.
 */

public class VehicalListResponse {
    @SerializedName("Id")
    private int Id;
    @SerializedName("Max_Seats")
    private int Max_Seats;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getMax_Seats() {
        return Max_Seats;
    }

    public void setMax_Seats(int max_Seats) {
        Max_Seats = max_Seats;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getTrip_Type() {
        return Trip_Type;
    }

    public void setTrip_Type(String trip_Type) {
        Trip_Type = trip_Type;
    }

    public int getTrip_Type_Id() {
        return Trip_Type_Id;
    }

    public void setTrip_Type_Id(int trip_Type_Id) {
        Trip_Type_Id = trip_Type_Id;
    }

    public String getTrip_Type_Snap() {
        return Trip_Type_Snap;
    }

    public void setTrip_Type_Snap(String trip_Type_Snap) {
        Trip_Type_Snap = trip_Type_Snap;
    }

    public String getVehicle_Type() {
        return Vehicle_Type;
    }

    public void setVehicle_Type(String vehicle_Type) {
        Vehicle_Type = vehicle_Type;
    }

    public int getVehicle_Type_Id() {
        return Vehicle_Type_Id;
    }

    public void setVehicle_Type_Id(int vehicle_Type_Id) {
        Vehicle_Type_Id = vehicle_Type_Id;
    }

    public String getVehicle_Type_Snap() {
        return Vehicle_Type_Snap;
    }

    public void setVehicle_Type_Snap(String vehicle_Type_Snap) {
        Vehicle_Type_Snap = vehicle_Type_Snap;
    }

    @SerializedName("Remarks")
    private String Remarks;
    @SerializedName("Trip_Type")
    private String Trip_Type;
    @SerializedName("Trip_Type_Id")
    private int Trip_Type_Id;
    @SerializedName("Trip_Type_Snap")
    private String Trip_Type_Snap;
    @SerializedName("Vehicle_Type")
    private String Vehicle_Type;
    @SerializedName("Vehicle_Type_Id")
    private int Vehicle_Type_Id;
    @SerializedName("Vehicle_Type_Snap")
    private String Vehicle_Type_Snap;
}
