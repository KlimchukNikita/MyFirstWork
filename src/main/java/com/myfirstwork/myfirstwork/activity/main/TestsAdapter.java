package com.myfirstwork.myfirstwork.activity.main;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myfirstwork.myfirstwork.R;

public class TestsAdapter extends RecyclerView.Adapter<TestsAdapter.NumberViewHolder>{

    private static int viewHolderCount;
    private int numberItems;
    private String[] TestName;


    public TestsAdapter(int numberOfItems){

        viewHolderCount = 0;
        numberItems = numberOfItems;
        TestName = new String[]{"Тест 1", "Тест 2", "Тест 3", "Тест 4", "Тест 5", "Тест 6"};
    }


    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.include_newtest;

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_newtest, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        NumberViewHolder viewHolder = new NumberViewHolder(view);
        viewHolder.viewHolderIndex.setText(TestName[viewHolderCount]);

        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        holder.bind(position+1);
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    class NumberViewHolder extends RecyclerView.ViewHolder {

        TextView listItemNumberView;
        TextView viewHolderIndex;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);

            listItemNumberView = itemView.findViewById(R.id.tv_number_item);
            viewHolderIndex = itemView.findViewById(R.id.tv_view_holder_number);
        }

        void bind(int listIndex){
            listItemNumberView.setText(String.valueOf(listIndex));
        }
    }
}