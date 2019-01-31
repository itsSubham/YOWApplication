package f.ratnatech.yowapplication.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RatnaDev008 on 12/28/2018.
 */

public class MobileUpdateResponse {
    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }

    public MobileDataResponse getData() {
        return Data;
    }

    public void setData(MobileDataResponse data) {
        Data = data;
    }

    @SerializedName("Success")
    private int Success;
    @SerializedName("Data")
    private MobileDataResponse Data;
}
