package dsc.dtu.dogs.api;

import androidx.annotation.NonNull;

import com.squareup.moshi.Json;

public class RandomImageResponse {

    @Json(name = "message")
    @NonNull
    public final String message;

    @Json(name = "status")
    @NonNull
    public final String status;

    public RandomImageResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }
}
