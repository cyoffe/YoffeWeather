package yoffe.weather;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by CS on 1/10/2016.
 */
public interface CurrentWeatherService {

    @GET("/data/2.5/weather?")
    Call<CurrentWeather> listCurrentWeather(@QueryMap Map<String, String> params);
                                            //zip=11210,us&appid=2de143494c0b295cca9337e1e96b00e0&units=imperial
}
