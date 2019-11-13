package dsc.dtu.dogs;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dsc.dtu.dogs.api.RandomImagesUseCase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * A ViewModel class to fetch images from the Dogs API service.
 *
 * This class needs a separate ViewModelFactory because it has a non-empty constructor.
 */
public class MainViewModel extends ViewModel {

    private static final String TAG = "MainViewModel";

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

    public void fetchImages(int count) {
        Disposable disposable = usecase.getRandomImages(count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        image -> {
                            List<String> images = new ArrayList<>(dogImages.getValue());
                            images.add(image);
                            dogImages.onNext(images);
                        },
                        error -> {
                            Log.e(TAG, "An Error occurred while fetching images.");
                            error.printStackTrace();
                        });

        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
