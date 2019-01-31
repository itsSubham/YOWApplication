package f.ratnatech.yowapplication.Response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by RatnaDev008 on 1/2/2019.
 */

public class VehicalDataResponse {
    public ArrayList<VehicalListResponse> getLists() {
        return Lists;
    }

    public void setLists(ArrayList<VehicalListResponse> lists) {
        Lists = lists;
    }

    @SerializedName("Lists")
    private ArrayList<VehicalListResponse> Lists;
}
