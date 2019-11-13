package dsc.dtu.dogs.di;

import dagger.Component;
import dsc.dtu.dogs.MainActivity;
import dsc.dtu.dogs.MainViewModelFactory;
import dsc.dtu.dogs.api.RandomImagesUseCase;

/**
 * A Dagger component to initialize the object graph.
 *
 * The AppModule class is installed here as a dependency of this component. Without including the
 * module here, we will not be able to access the dependencies provided in that module.
 *
 * It also contains injection methods for classes which require field injection.
 */
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    RandomImagesUseCase randomImagesUseCase();

    MainViewModelFactory mainViewModelFactory();
}
