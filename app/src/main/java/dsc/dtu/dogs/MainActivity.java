package dsc.dtu.dogs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import dsc.dtu.dogs.adapter.DogsAdapter;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    DogsAdapter dogsAdapter;

    @Inject
    MainViewModelFactory viewModelFactory;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ((DogsApplication) getApplication())
                .appComponent
                .inject(this);

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
        RecyclerView dogsRecyclerView = findViewById(R.id.dogsRv);
        dogsRecyclerView.setAdapter(dogsAdapter);
        dogsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        new LinearSnapHelper().attachToRecyclerView(dogsRecyclerView);
    }

    private void setupViewModel() {
        ViewModelProvider provider = new ViewModelProvider(this, viewModelFactory);
        mainViewModel = provider.get(MainViewModel.class);
    }

    private void subscribeToImages() {
        Disposable disposable = mainViewModel
                .dogImages
                .subscribe(images -> dogsAdapter.submitList(images));

        compositeDisposable.add(disposable);
    }
}
