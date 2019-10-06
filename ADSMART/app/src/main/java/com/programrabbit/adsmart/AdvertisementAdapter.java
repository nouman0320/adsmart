package com.programrabbit.adsmart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdvertisementAdapter extends RecyclerView.Adapter<AdvertisementAdapter.MyViewHolder> {

    private Context mContext;
    private List<Advertisement> advertisementList;

    public AdvertisementAdapter(Context mContext, List<Advertisement> aList){
        this.mContext = mContext;
        this.advertisementList = aList;
    }

    @NonNull
    @Override
    public AdvertisementAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_advertisement, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvertisementAdapter.MyViewHolder holder, int position) {
        // here assign to xml

        holder.tv_title.setText(advertisementList.get(position).getTitle());
        holder.tv_price.setText(advertisementList.get(position).getPrice());
        holder.tv_description.setText(advertisementList.get(position).getDescription());

        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return advertisementList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_title, tv_price, tv_description;
        public ImageView thumbnail;


        public MyViewHolder(View view){
            super(view);
            tv_title = view.findViewById(R.id.tv_title);
            tv_price = view.findViewById(R.id.tv_price);
            tv_description = view.findViewById(R.id.tv_description);
            thumbnail = view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                   /* if (pos != RecyclerView.NO_POSITION){
                        Movie clickedDataItem = movieList.get(pos);
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra("movies", clickedDataItem );
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getOriginalTitle(), Toast.LENGTH_SHORT).show();
                    }*/
                }
            });
        }
    }
}
