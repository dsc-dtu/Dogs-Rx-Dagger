package dsc.dtu.dogs;

import org.junit.Before;
import org.junit.Test;

import dsc.dtu.dogs.api.RandomImagesUseCase;
import dsc.dtu.dogs.di.AppComponent;
import dsc.dtu.dogs.di.DaggerAppComponent;

public class RandomImagesUseCaseTest {

    private AppComponent appComponent;
    private RandomImagesUseCase usecase;

    @Before
    public void setup() {
        appComponent = DaggerAppComponent.create();
        usecase = appComponent.randomImagesUseCase();
    }

    @Test
    public void givenUseCase_whenFetchingTenRandomImages_shouldReturnTenRandomImages() throws InterruptedException {
        usecase.getRandomImages(10)
                .test()
                .await()
                .assertValueCount(10);
    }

}
