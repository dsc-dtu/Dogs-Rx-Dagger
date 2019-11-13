package dsc.dtu.dogs.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import javax.inject.Inject;

import dsc.dtu.dogs.R;

public class DogsAdapter extends ListAdapter<String, DogsAdapter.DogViewHolder> {

    @Inject
    public DogsAdapter(@NonNull DiffUtil.ItemCallback<String> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dog, parent, false);
        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        holder.bind(getItem(position));
   }

    class DogViewHolder extends RecyclerView.ViewHolder {

        final ImageView imageView;
        final ProgressBar progressBar;

        DogViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            progressBar = itemView.findViewById(R.id.progressBar);
        }

        void bind(String url) {
            if (!TextUtils.isEmpty(url)) {
                Glide.with(itemView)
                        .load(url)
                        .transition(new DrawableTransitionOptions().crossFade())
                        .into(imageView);
            }
        }
    }
}
