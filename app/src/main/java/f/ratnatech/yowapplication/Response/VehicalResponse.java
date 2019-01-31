package f.ratnatech.yowapplication.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RatnaDev008 on 1/2/2019.
 */

public class VehicalResponse {
    @SerializedName("Success")
    private int Success;

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }

    public VehicalDataResponse getData() {
        return Data;
    }

    public void setData(VehicalDataResponse data) {
        Data = data;
    }

    @SerializedName("Data")
    private VehicalDataResponse Data;


}
