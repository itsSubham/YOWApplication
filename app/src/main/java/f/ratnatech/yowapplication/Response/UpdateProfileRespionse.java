package f.ratnatech.yowapplication.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RatnaDev008 on 1/2/2019.
 */

public class UpdateProfileRespionse {
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
    public data Data;

    public class data{
        public int getProfile_Updated() {
            return Profile_Updated;
        }

        public void setProfile_Updated(int profile_Updated) {
            Profile_Updated = profile_Updated;
        }

        @SerializedName("Profile_Updated")
        public int Profile_Updated;

    }
}
