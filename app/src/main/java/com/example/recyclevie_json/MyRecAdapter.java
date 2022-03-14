package com.example.recyclevie_json;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;

public class MyRecAdapter extends RecyclerView.Adapter<MyRecAdapter.ViewHolder> {
    private  ArrayList<HashMap<String,String>> mInfoList;
    private Context context;
    CardView evencard,oddcard;



    public MyRecAdapter(MainActivity mainActivity, ArrayList<HashMap<String, String>> infoList) {
        this.mInfoList = infoList;
        this.context = mainActivity;
    }

    //inflate row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);


    }

    //bind the data to the textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("absde", String.valueOf(position));
        if (mInfoList.indexOf(position)%2 ==0){

            oddcard.setVisibility(View.INVISIBLE);
            holder.eid.setText(mInfoList.get(position).get("id"));
            holder.ename.setText(mInfoList.get(position).get("name"));
            holder.eemail.setText(mInfoList.get(position).get("email"));
            holder.eaddress.setText(mInfoList.get(position).get("address"));
            holder.emobile.setText(mInfoList.get(position).get("mobile"));
            holder.ehome.setText(mInfoList.get(position).get("home"));
            holder.eoffice.setText(mInfoList.get(position).get("office"));
            holder.egender.setText(mInfoList.get(position).get("gender"));
        }
        else {
            evencard.setVisibility(View.INVISIBLE);
            holder.id.setText(mInfoList.get(position).get("id"));
            holder.name.setText(mInfoList.get(position).get("name"));
            holder.email.setText(mInfoList.get(position).get("email"));
            holder.address.setText(mInfoList.get(position).get("address"));
            holder.mobile.setText(mInfoList.get(position).get("mobile"));
            holder.home.setText(mInfoList.get(position).get("home"));
            holder.office.setText(mInfoList.get(position).get("office"));
            holder.gender.setText(mInfoList.get(position).get("gender"));
        }
    }

    @Override
    public int getItemCount() {
        return mInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id,name,email,address,mobile,home,office,gender;
        TextView eid,ename,eemail,eaddress,emobile,ehome,eoffice,egender;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            evencard = itemView.findViewById(R.id.cardeven);
            oddcard = itemView.findViewById(R.id.cardodd);

                eid = (TextView) itemView.findViewById(R.id.evenid);
                ename = (TextView)itemView.findViewById(R.id.evenname);
                eemail =(TextView) itemView.findViewById(R.id.evenemail);
                eaddress =(TextView) itemView.findViewById(R.id.evenaddress);
                emobile= (TextView)itemView.findViewById(R.id.evenmobile);
                ehome = (TextView)itemView.findViewById(R.id.evenhome);
                eoffice = (TextView)itemView.findViewById(R.id.evenoffice);
                egender =(TextView) itemView.findViewById(R.id.evengender);

                id = (TextView)itemView.findViewById(R.id.id);
                name =(TextView) itemView.findViewById(R.id.name);
                email =(TextView) itemView.findViewById(R.id.email);
                address =(TextView) itemView.findViewById(R.id.address);
                mobile= (TextView)itemView.findViewById(R.id.mobile);
                home = (TextView)itemView.findViewById(R.id.home);
                office = (TextView)itemView.findViewById(R.id.office);
                gender = (TextView)itemView.findViewById(R.id.gender);
            }

        }
    }


