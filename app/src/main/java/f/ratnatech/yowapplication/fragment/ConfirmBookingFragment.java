package f.ratnatech.yowapplication.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import f.ratnatech.yowapplication.Activity.RequestBookingActivity;
import f.ratnatech.yowapplication.Activity.SuccessfulActivity;
import f.ratnatech.yowapplication.Helper.RegPrefManager;
import f.ratnatech.yowapplication.R;


public class ConfirmBookingFragment extends BottomSheetDialogFragment {
     private TextView confirmbtn,rateLatest;
    AlertDialog.Builder builder;
    public ConfirmBookingFragment() {
        // Required empty public constructor
    }


    public static ConfirmBookingFragment newInstance(String param1, String param2) {
        ConfirmBookingFragment fragment = new ConfirmBookingFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_confirm_booking, container, false);
        confirmbtn=v.findViewById(R.id.confirmbtn);
        rateLatest=v.findViewById(R.id.rateLatest);

        rateLatest.setText("\u20B9 "+RegPrefManager.getInstance(getContext()).getRate());
        builder = new AlertDialog.Builder(getContext());
        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pickup=RegPrefManager.getInstance(getContext()).getPickLocationAddress();
                if (pickup!=null) {
                    startActivity(new Intent(getContext(), RequestBookingActivity.class));
                    getActivity().finish();
                }
                else {
                    Toast.makeText(getContext(),"Please Choose Pickup and drop Location",Toast.LENGTH_LONG).show();
                }
            }
        });
        return v;
    }

}
