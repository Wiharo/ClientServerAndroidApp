package it.mirea.kursovaya;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;


public interface FestApi {
    @GET("/fests")
    Call<List<FestModel>> getFests();
}
