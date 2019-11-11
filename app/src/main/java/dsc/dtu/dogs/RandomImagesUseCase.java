package dsc.dtu.dogs;

import android.util.Log;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import dsc.dtu.dogs.api.DogsService;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class RandomImagesUseCase {

    private static final String TAG = "RandomImagesUseCase";

    @NonNull
    private final DogsService dogsService;

    @Inject
    public RandomImagesUseCase(@NonNull DogsService dogsService) {
        this.dogsService = dogsService;
    }

    public Flowable<String> getRandomImages(int count) {
        return Flowable
                .range(0, count) // Emit 0, 1, 2, 3, ... till count
                .flatMap(index -> dogsService.getRandomImage()) // Get a random image each time
                .filter(response -> response.status.equals("success")) // Filter successful responses
                .map(response -> response.message) // Convert responses to only their URLs
                .onErrorReturn(error -> {
                    Log.d(TAG, "Error occurred while fetching image.");
                    error.printStackTrace();
                    return "";
                })
                .subscribeOn(Schedulers.io());
    }
}
