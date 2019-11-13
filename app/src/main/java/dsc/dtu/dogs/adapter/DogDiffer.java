package dsc.dtu.dogs.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import javax.inject.Inject;

/**
 * An implementation of DiffUtil.ItemCallback to diff between urls of dog images.
 */
public class DogDiffer extends DiffUtil.ItemCallback<String> {

    @Inject
    public DogDiffer() { }

    @Override
    public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
        return oldItem.equals(newItem);
    }
}
