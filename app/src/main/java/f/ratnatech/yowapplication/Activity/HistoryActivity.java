package f.ratnatech.yowapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import f.ratnatech.yowapplication.Adapter.HistoryAdapter;
import f.ratnatech.yowapplication.AdapterResponse.HistoryResponse;
import f.ratnatech.yowapplication.R;

public class HistoryActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView historyRecyclerview;
    private TextView noMesgTv;
    private HistoryAdapter adapter;
    private ArrayList<HistoryResponse> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HistoryActivity.this,HomeActivity.class));
                finish();
            }
        });

        intialize();

    }
    private void intialize(){
        historyRecyclerview=findViewById(R.id.historyRecyclerview);
        noMesgTv=findViewById(R.id.noMesgTv);
        data=new ArrayList<>();

        for(int i=0;i<3;i++){
            HistoryResponse historyResponse=new HistoryResponse();
            historyResponse.setTime("Sat, Dec 29,03.33 Am");

            data.add(historyResponse);
        }
        noMesgTv.setVisibility(View.GONE);
        historyRecyclerview.setVisibility(View.VISIBLE);
        historyRecyclerview.setHasFixedSize(true);
        historyRecyclerview.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
        //placeRecyclerview.setItemAnimator(new DefaultItemAnimator());
        adapter=new HistoryAdapter(HistoryActivity.this,data);
        historyRecyclerview.setAdapter(adapter);
    }
}
