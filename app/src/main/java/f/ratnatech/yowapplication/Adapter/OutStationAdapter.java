package f.ratnatech.yowapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import f.ratnatech.yowapplication.R;
import f.ratnatech.yowapplication.Response.VehicalListResponse;

/**
 * Created by RatnaDev008 on 10/29/2018.
 */

public class OutStationAdapter extends RecyclerView.Adapter<OutStationAdapter.ViewHolder> {
    private Context context;
    private ArrayList<VehicalListResponse> operatorList;

    public OutStationAdapter(Context context, ArrayList<VehicalListResponse> operatorList) {
       this.operatorList = operatorList;
        this.context=context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dailyrideslayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {

        final VehicalListResponse data=operatorList.get(position);

            holder.nameTv.setText(data.getVehicle_Type());
            //holder.timeTv.setText(data.getMax_Seats());

    }

    @Override
    public int getItemCount() {
        return operatorList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTv,timeTv;

        ViewHolder(View itemView) {
            super(itemView);

            nameTv = (TextView) itemView.findViewById(R.id.nameTv);
          //  timeTv=(TextView)itemView.findViewById(R.id.timeTv);
        }
    }



}
