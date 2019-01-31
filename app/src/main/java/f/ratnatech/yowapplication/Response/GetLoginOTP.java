package f.ratnatech.yowapplication.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RatnaDev008 on 12/24/2018.
 */

public class GetLoginOTP {
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

    @SerializedName("Success")
    private int Success;
    @SerializedName("Data")
    private data Data;

    public class data{
        public int getOTP_Id() {
            return OTP_Id;
        }

        public void setOTP_Id(int OTP_Id) {
            this.OTP_Id = OTP_Id;
        }

        public String getOTP() {
            return OTP;
        }

        public void setOTP(String OTP) {
            this.OTP = OTP;
        }

        @SerializedName("OTP_Id")
        private int OTP_Id;
        @SerializedName("OTP")
        private String OTP;

    }
}
