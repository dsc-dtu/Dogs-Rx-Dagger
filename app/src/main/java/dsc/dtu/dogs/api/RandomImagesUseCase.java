package dsc.dtu.dogs.api;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import dsc.dtu.dogs.api.DogsService;
import io.reactivex.Flowable;

/**
 * A use-case class to retrieve dog pictures from the API service.
 */
public class RandomImagesUseCase {

    @NonNull
    private final DogsService dogsService;

    @Inject
    public RandomImagesUseCase(@NonNull DogsService dogsService) {
        this.dogsService = dogsService;
    }

    public Flowable<String> getRandomImages(int count) {
        return dogsService.getRandomImages(count);
    }
}
