package dsc.dtu.dogs;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

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
