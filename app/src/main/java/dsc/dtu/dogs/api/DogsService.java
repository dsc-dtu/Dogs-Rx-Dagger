package dsc.dtu.dogs.api;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * A Retrofit Service interface for the Dogs API
 */
public interface DogsService {

    /**
     * Makes a request to get a single random image
     * @return A random image
     */
    @GET("breeds/image/random")
    Flowable<RandomImageResponse> getRandomImage();

}
