package f.ratnatech.yowapplication.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by RatnaDev008 on 12/24/2018.
 */

public class SaveUserResponse {
@SerializedName("Success")
    private int Success;

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }

    public data getData() {
        return Data;
    }

    public void setData(data data) {
        Data = data;
    }

    @SerializedName("Data")
    private data Data;

public class data{
    @SerializedName("OTP_Verified")
    private int OTP_Verified;

    public int getOTP_Verified() {
        return OTP_Verified;
    }

    public void setOTP_Verified(int OTP_Verified) {
        this.OTP_Verified = OTP_Verified;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @SerializedName("Id")
    private int Id;

}
}
