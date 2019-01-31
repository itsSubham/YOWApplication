package f.ratnatech.yowapplication.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RatnaDev008 on 12/28/2018.
 */

public class MobileDataResponse {
    public int getOTP_Verified() {
        return OTP_Verified;
    }

    public void setOTP_Verified(int OTP_Verified) {
        this.OTP_Verified = OTP_Verified;
    }

    public int getProfile_Updated() {
        return Profile_Updated;
    }

    public void setProfile_Updated(int profile_Updated) {
        Profile_Updated = profile_Updated;
    }

    @SerializedName("OTP_Verified")
    private int OTP_Verified;
    @SerializedName("Profile_Updated")
    private int Profile_Updated;
}
