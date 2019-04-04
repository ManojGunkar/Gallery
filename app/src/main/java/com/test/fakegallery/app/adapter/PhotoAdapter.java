package com.test.fakegallery.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test.fakegallery.R;
import com.test.fakegallery.api.pojo.Photo;
import com.test.fakegallery.app.DetailActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Manoj Kumar on 04-04-2019.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private Context mContext;
    private List<Photo> photos;

    public PhotoAdapter(Context context, List<Photo> photos) {
        this.mContext = context;
        this.photos = photos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder viewHolder = holder;

        Photo photo = photos.get(position);

        Glide.with(mContext).load(photo.getUrl())
                .centerCrop()
                .into(viewHolder.imgLogo);
        viewHolder.txtTitle.setText(photo.getTitle());

        viewHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("id", String.valueOf(photo.getId()));
            intent.putExtra("title", photo.getTitle());
            mContext.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle;
        private ImageView imgLogo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txt_title);
            imgLogo = itemView.findViewById(R.id.img_logo);
        }
    }
}
