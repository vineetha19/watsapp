package com.example.admin.whatsapp;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class JobListAdapter1 extends RecyclerView.Adapter<JobListAdapter1.MyViewHolder> {
    Context context;
    List<JobListItem1> jobList1 = new ArrayList<>();
    public JobListAdapter1(Context context, List<JobListItem1> jobList1) {
        this.context = context;
        this.jobList1 = jobList1;
    }
    @NonNull
    @Override
    public JobListAdapter1.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.job_list_item1, viewGroup, false);


//       view.setOnClickListener(MainActivity.myOnClickListener);

        return new JobListAdapter1.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final JobListAdapter1.MyViewHolder myViewHolder, final int i) {
        myViewHolder.id.setText(jobList1.get(i).getId());
        myViewHolder.name.setText(jobList1.get(i).getName());
        myViewHolder.email.setText(jobList1.get(i).getEmail());
        myViewHolder.address.setText(jobList1.get(i).getAddress());
        myViewHolder.gender.setText(jobList1.get(i).getGender());
        //myViewHolder.phone.setText(jobList1.get(i).getPhone());

        myViewHolder.mobile.setText(jobList1.get(i).getMobile());
        myViewHolder.office.setText(jobList1.get(i).getOffice());
        myViewHolder.home.setText(jobList1.get(i).getHome());


        myViewHolder.card_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    @Override
    public int getItemCount() {
        return jobList1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id,name,email,address,gender,phone,mobile,home,office;
        CardView card_layout;
        CheckBox checkBox;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            /*productName = itemView.findViewById(R.id.product_name);
            prod_image = itemView.findViewById(R.id.product_image);*/
            id = itemView.findViewById(R.id.id);
            card_layout = itemView.findViewById(R.id.card_layout);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            address = itemView.findViewById(R.id.address);
            gender = itemView.findViewById(R.id.gender);
            //phone = itemView.findViewById(R.id.phone);
            mobile = itemView.findViewById(R.id.mobile);
            home = itemView.findViewById(R.id.home);
            office = itemView.findViewById(R.id.office);
            checkBox = itemView.findViewById(R.id.check_apply);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(checkBox.isChecked()) {

                    } else{

                    }

                }
            });
        }
    }
}

