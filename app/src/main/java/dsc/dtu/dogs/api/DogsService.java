package dsc.dtu.dogs.api;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * A class which acts as a fake API service for retrieving pictures of dogs.
 */
public class DogsService {

    private final List<String> images = Arrays.asList(
            "https://images.dog.ceo/breeds/setter-irish/n02100877_2142.jpg",
            "https://images.dog.ceo/breeds/setter-gordon/n02101006_4807.jpg",
            "https://images.dog.ceo/breeds/terrier-scottish/n02097298_4162.jpg",
            "https://images.dog.ceo/breeds/weimaraner/n02092339_363.jpg",
            "https://images.dog.ceo/breeds/bouvier/n02106382_2553.jpg",
            "https://images.dog.ceo/breeds/spaniel-cocker/n02102318_10645.jpg",
            "https://images.dog.ceo/breeds/malamute/n02110063_10025.jpg",
            "https://images.dog.ceo/breeds/maltese/n02085936_3677.jpg",
            "https://images.dog.ceo/breeds/spaniel-brittany/n02101388_1941.jpg",
            "https://images.dog.ceo/breeds/hound-afghan/n02088094_10982.jpg",
            "https://images.dog.ceo/breeds/sheepdog-english/n02105641_13952.jpg",
            "https://images.dog.ceo/breeds/basenji/n02110806_1778.jpg",
            "https://images.dog.ceo/breeds/pinscher-miniature/n02107312_759.jpg",
            "https://images.dog.ceo/breeds/hound-english/n02089973_417.jpg",
            "https://images.dog.ceo/breeds/poodle-toy/n02113624_764.jpg",
            "https://images.dog.ceo/breeds/greyhound-italian/n02091032_11660.jpg",
            "https://images.dog.ceo/breeds/spaniel-cocker/n02102318_8275.jpg",
            "https://images.dog.ceo/breeds/airedale/n02096051_3823.jpg",
            "https://images.dog.ceo/breeds/terrier-norwich/n02094258_1840.jpg",
            "https://images.dog.ceo/breeds/bluetick/n02088632_3585.jpg",
            "https://images.dog.ceo/breeds/collie-border/n02106166_2072.jpg",
            "https://images.dog.ceo/breeds/elkhound-norwegian/n02091467_4110.jpg",
            "https://images.dog.ceo/breeds/maltese/n02085936_5435.jpg",
            "https://images.dog.ceo/breeds/terrier-wheaten/n02098105_398.jpg",
            "https://images.dog.ceo/breeds/coonhound/n02089078_4312.jpg",
            "https://images.dog.ceo/breeds/shiba/shiba-5.jpg",
            "https://images.dog.ceo/breeds/terrier-irish/n02093991_2126.jpg",
            "https://images.dog.ceo/breeds/germanshepherd/n02106662_24774.jpg",
            "https://images.dog.ceo/breeds/retriever-curly/n02099429_2119.jpg",
            "https://images.dog.ceo/breeds/appenzeller/n02107908_7392.jpg",
            "https://images.dog.ceo/breeds/elkhound-norwegian/n02091467_481.jpg",
            "https://images.dog.ceo/breeds/entlebucher/n02108000_2802.jpg",
            "https://images.dog.ceo/breeds/terrier-toy/n02087046_2419.jpg",
            "https://images.dog.ceo/breeds/groenendael/n02105056_4873.jpg",
            "https://images.dog.ceo/breeds/spaniel-welsh/n02102177_3524.jpg",
            "https://images.dog.ceo/breeds/terrier-irish/n02093991_4791.jpg",
            "https://images.dog.ceo/breeds/keeshond/n02112350_4368.jpg",
            "https://images.dog.ceo/breeds/retriever-curly/n02099429_907.jpg",
            "https://images.dog.ceo/breeds/whippet/n02091134_17108.jpg",
            "https://images.dog.ceo/breeds/entlebucher/n02108000_3601.jpg",
            "https://images.dog.ceo/breeds/terrier-irish/n02093991_278.jpg",
            "https://images.dog.ceo/breeds/dingo/n02115641_6250.jpg",
            "https://images.dog.ceo/breeds/terrier-russell/jack2.jpg",
            "https://images.dog.ceo/breeds/stbernard/n02109525_7579.jpg",
            "https://images.dog.ceo/breeds/terrier-patterdale/patterdale-terrier-287612805105275kBT.jpg",
            "https://images.dog.ceo/breeds/saluki/n02091831_346.jpg",
            "https://images.dog.ceo/breeds/dachshund/foxhound-53951_640.jpg",
            "https://images.dog.ceo/breeds/cattledog-australian/IMG_0206.jpg",
            "https://images.dog.ceo/breeds/bluetick/n02088632_101.jpg",
            "https://images.dog.ceo/breeds/mix/cheyenne2.jpg"
    );

    @Inject
    public DogsService() { }

    @NonNull
    public Flowable<String> getRandomImage() {
        int index = new Random().nextInt() % images.size();
        String url = images.get(index);
        return Flowable
                .just(url)
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io());
    }

    @NonNull
    public Flowable<String> getRandomImages(int count) {
        List<String> message = images.subList(0, count);
        return Flowable
                .fromIterable(message)
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io());
    }
}
