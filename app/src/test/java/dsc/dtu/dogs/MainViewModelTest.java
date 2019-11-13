package dsc.dtu.dogs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dsc.dtu.dogs.di.AppComponent;
import dsc.dtu.dogs.di.DaggerAppComponent;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

public class MainViewModelTest {

    private AppComponent appComponent;
    private MainViewModel mainViewModel;

    @Before
    public void setup() {
        appComponent = DaggerAppComponent.create();
        mainViewModel = appComponent.mainViewModelFactory().create(MainViewModel.class);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
    }

    @Test
    public void givenMainViewModel_whenFetchingImages_thenShouldReturnAsManyImagesAsRequested() throws InterruptedException {
        mainViewModel.fetchImages(10);
        mainViewModel.dogImages.test()
                .assertNoErrors()
                .assertNotComplete();
    }

    @After
    public void cleanup() {
        RxAndroidPlugins.reset();
    }
}
