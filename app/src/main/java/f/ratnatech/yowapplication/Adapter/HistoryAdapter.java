package f.ratnatech.yowapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;



import java.util.ArrayList;

import f.ratnatech.yowapplication.Activity.HistoryDetailsActivity;
import f.ratnatech.yowapplication.AdapterResponse.HistoryResponse;
import f.ratnatech.yowapplication.R;

/**
 * Created by RatnaDev008 on 10/29/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private Context context;
    private ArrayList<HistoryResponse> operatorList;

    public HistoryAdapter(Context context, ArrayList<HistoryResponse> operatorList) {
       this.operatorList = operatorList;
        this.context=context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.historylayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {

        final HistoryResponse data=operatorList.get(position);
        holder.timeTv.setText(data.getTime());
        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, HistoryDetailsActivity.class));

            }
        });
    }

    @Override
    public int getItemCount() {
        return operatorList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView timeTv;
        LinearLayout linear;

        ViewHolder(View itemView) {
            super(itemView);

            timeTv = (TextView) itemView.findViewById(R.id.timeTv);
            linear=(LinearLayout)itemView.findViewById(R.id.linear);
        }
    }



}
