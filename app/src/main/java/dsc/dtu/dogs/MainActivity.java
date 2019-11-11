package dsc.dtu.dogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private MainViewModel mainViewModel;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private RecyclerView dogsRecyclerView;
    private DogsAdapter dogsAdapter;

    @Inject MainViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ((DogsApplication) getApplication()).appComponent.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecyclerView();
        setupViewModel();
        subscribeToImages();

        mainViewModel.fetchImages();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    private void setupRecyclerView() {
        dogsRecyclerView = findViewById(R.id.dogsRv);
        dogsAdapter = new DogsAdapter(new DogsAdapter.DogDiffer());
        dogsRecyclerView.setAdapter(dogsAdapter);
        dogsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupViewModel() {
        ViewModelProvider provider = new ViewModelProvider(this, viewModelFactory);
        mainViewModel = provider.get(MainViewModel.class);
    }

    private void subscribeToImages() {
        Disposable disposable = mainViewModel
                .dogImages
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        images -> dogsAdapter.submitList(images),
                        error -> {
                            Log.d(TAG, "Error occurred");
                            error.printStackTrace();
                            Toast.makeText(this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                );

        compositeDisposable.add(disposable);
    }
}
