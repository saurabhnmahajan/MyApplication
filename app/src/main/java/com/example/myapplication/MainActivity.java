package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
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
        MyAdapter adapter = new MyAdapter(this, new String[] {"1", "2", "3", "4", "5", "6", "7", "8","9", "10", "11", "12", "2", "3", "4", "5", "6", "7", "8","9", "10", "11", "12"}, getSupportFragmentManager());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private String[] mDataset;
        private Context context;
        private FragmentManager mFragmentManager;

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView textView;

            public MyViewHolder(View v) {
                super(v);
                textView = v.findViewById(R.id.item_text);
            }
        }

        class ViewHolder2 extends RecyclerView.ViewHolder {

            public ViewHolder2(@NonNull View itemView, FragmentManager fragmentManager) {
                super(itemView);
                fragmentManager.beginTransaction()
                        .replace(itemView.getId(), new BlankFragment())
                        .commit();

                setIsRecyclable(false);
            }
        }

        public MyAdapter(Context context, String[] myDataset, FragmentManager fragmentManager) {
            mDataset = myDataset;
            this.context = context;
            mFragmentManager = fragmentManager;
        }

        @Override
        public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
            if(holder instanceof ViewHolder2) {
                holder.setIsRecyclable(false);
            }
            super.onViewAttachedToWindow(holder);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
            if(viewType == 1) {
                View view =  LayoutInflater.from(context).inflate(R.layout.item, parent, false);
                return new MyViewHolder(view);
            } else {
                View view =  LayoutInflater.from(context).inflate(R.layout.fragment_blank, parent, false);
                return new ViewHolder2(view, mFragmentManager);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof MyViewHolder) {
                ((MyViewHolder)holder).textView.setText(mDataset[position - 1]);

            } else {
                Log.d("recycler", "fragment");
            }
            Log.d("recycler", position + "");
        }

        @Override
        public int getItemViewType(int position) {
            if(position == 0) return 0;
            else return 1;
        }

        @Override
        public int getItemCount() {
            return mDataset.length + 1;
        }
    }
}
