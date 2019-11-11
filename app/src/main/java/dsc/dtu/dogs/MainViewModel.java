package dsc.dtu.dogs;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public class MainViewModel extends ViewModel {

    @NonNull
    private final RandomImagesUseCase usecase;
    @NonNull
    private final CompositeDisposable compositeDisposable;

    @NonNull
    public final BehaviorSubject<List<String>> dogImages;

    @Inject
    public MainViewModel(@NonNull RandomImagesUseCase usecase) {
        this.usecase = usecase;
        this.compositeDisposable = new CompositeDisposable();
        this.dogImages = BehaviorSubject.create();
        this.dogImages.onNext(Collections.emptyList());
    }

    public void fetchImages() {

        Disposable disposable = usecase.getRandomImages(20)
                .subscribeOn(Schedulers.io())
                .subscribe(image -> {
                    List<String> images = new ArrayList<>(dogImages.getValue());
                    images.add(image);
                    dogImages.onNext(images);
                });

        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
