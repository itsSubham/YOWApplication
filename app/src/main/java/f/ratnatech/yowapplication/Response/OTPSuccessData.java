package f.ratnatech.yowapplication.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by NISHIKANT on 10/22/2018.
 */

public class OTPSuccessData {


    @SerializedName("Verified")
    @Expose
    private int Verified;

    public int getVerified() {
        return Verified;
    }

    public void setVerified(int verified) {
        Verified = verified;
    }
}
