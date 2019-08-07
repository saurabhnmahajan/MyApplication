package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayoutManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        MyAdapter adapter = new MyAdapter(this, new String[] {"1", "2", "3", "4", "5", "6", "7", "8","9", "10", "11", "12", "2", "3", "4", "5", "6", "7", "8","9", "10", "11", "12"});
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private String[] mDataset;
        private Context context;

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView textView;
            public MyViewHolder(View v) {
                super(v);
                textView = v.findViewById(R.id.item_text);
            }
        }

        public MyAdapter(Context context, String[] myDataset) {
            mDataset = myDataset;
            this.context = context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
            View view =  LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.textView.setText(mDataset[position]);
            Log.d("recycler", position + "");
        }

        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}
