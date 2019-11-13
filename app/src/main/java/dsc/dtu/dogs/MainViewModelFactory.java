package dsc.dtu.dogs;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dsc.dtu.dogs.api.RandomImagesUseCase;

/**
 * A factory class for MainViewModel. This class is required because the Android Architecture
 * Components ViewModel library does not support ViewModels with non-empty constructors.
 *
 * When a ViewModel with a non empty constructor is needed, the library delegates its creation to
 * the factory class we supply to it.
 */
public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final RandomImagesUseCase usecase;

    @Inject
    public MainViewModelFactory(RandomImagesUseCase usecase) {
        this.usecase = usecase;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(usecase);
    }
}
