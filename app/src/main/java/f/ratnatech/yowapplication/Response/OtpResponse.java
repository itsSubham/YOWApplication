package f.ratnatech.yowapplication.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by NISHIKANT on 10/22/2018.
 */

public class OtpResponse {

    @SerializedName("Success")
    @Expose
    private int Success;

    @SerializedName("Data")
    @Expose
    private OTPSuccessData Data;


    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }

    public OTPSuccessData getData() {
        return Data;
    }

    public void setData(OTPSuccessData data) {
        Data = data;
    }
}
