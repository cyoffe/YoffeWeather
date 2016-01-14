package yoffe.weather;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by CS on 1/10/2016.
 */
public interface WeatherService {

    @GET("/data/2.5/forecast/daily?")
    Call<WeatherList> listWeather(@QueryMap Map<String, String> params);
}
