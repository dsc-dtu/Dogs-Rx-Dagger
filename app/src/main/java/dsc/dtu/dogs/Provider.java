package dsc.dtu.dogs;

import dsc.dtu.dogs.api.DogsService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class Provider {

    private static HttpLoggingInterceptor logger = new HttpLoggingInterceptor();

    static {
        logger.level(HttpLoggingInterceptor.Level.BODY);
    }

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor())
            .build();

    private static Retrofit retrofit = new Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl("https://dog.ceo/api/")
            .build();

    private static DogsService service = retrofit.create(DogsService.class);

    public static RandomImagesUseCase usecase = new RandomImagesUseCase(service);

}
