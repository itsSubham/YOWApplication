package f.ratnatech.yowapplication.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import f.ratnatech.yowapplication.Adapter.DailyRideAdapter;
import f.ratnatech.yowapplication.AdapterResponse.DailyRideResponse;
import f.ratnatech.yowapplication.R;
import f.ratnatech.yowapplication.Response.VehicalListResponse;
import f.ratnatech.yowapplication.Response.VehicalResponse;
import f.ratnatech.yowapplication.client.ApiClient;
import f.ratnatech.yowapplication.client.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BottomFragment extends Fragment {
    private RecyclerView recyclerview;
    private DailyRideAdapter adapter;
    private ArrayList<VehicalListResponse> arrayList,typeList;
    ApiInterface apiService;
    private String pinCode;
    public BottomFragment() {
        // Required empty public constructor
    }


    public static BottomFragment newInstance(String pincode) {

       /* Bundle bundle = new Bundle();
        bundle.putString("pincode", pincode);*/

        BottomFragment bt = new BottomFragment();
       // bt.setArguments(bundle);



        return bt;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiService =
                ApiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_bottom, container, false);

        recyclerview=v.findViewById(R.id.recyclerview);
        arrayList=new ArrayList<>();
        typeList=new ArrayList<>();
        arrayList.clear();
       // if(getArguments()!=null) {

        //    String pincode = getArguments().getString("pincode");
          //  Log.d("Tag", "pincode" + pincode);
        typeList.clear();
            Call<VehicalResponse> call = apiService.getVehicalTypeResponse("751001");
            call.enqueue(new Callback<VehicalResponse>() {
                @Override
                public void onResponse(Call<VehicalResponse> call, Response<VehicalResponse> response) {
                    int success = response.body().getSuccess();
                    if (success == 0) {
                        Toast.makeText(getContext(), "Service unavailable for this area.", Toast.LENGTH_LONG).show();
                    } else {
                        arrayList = response.body().getData().getLists();
                        if (!arrayList.isEmpty()) {
                            for (int i=0;i<arrayList.size();i++){
                                String type=arrayList.get(i).getTrip_Type();
                               if(type.equals("Daily Rides"))
                               {
                                    VehicalListResponse listResponse=new VehicalListResponse();
                                    listResponse.setTrip_Type(arrayList.get(i).getTrip_Type());
                                    listResponse.setVehicle_Type(arrayList.get(i).getVehicle_Type());

                                   typeList.add(listResponse);

                               }
                            }

                            adapter = new DailyRideAdapter(getContext(), typeList);
                            LinearLayoutManager horizontalLayoutManagaer
                                    = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                            recyclerview.setLayoutManager(horizontalLayoutManagaer);
                            recyclerview.setAdapter(adapter);
                        }
                    }
                }

                @Override
                public void onFailure(Call<VehicalResponse> call, Throwable t) {
                    Toast.makeText(getContext(), "Some thing went wrong",
                            Toast.LENGTH_SHORT).show();
                }
            });
      //  }
        return v;
    }


}
