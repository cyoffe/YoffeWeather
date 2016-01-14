package yoffe.weather;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

import static java.util.Locale.*;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> locations;
    private WeatherPagerAdapter adapter;
    private SharedPreferences preferences;
    private LocationManager locationManager;
    private ViewPager viewPager;
    private LocationListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("DEFAULT", MODE_PRIVATE);


        viewPager = (ViewPager) findViewById(R.id.viewPager);

        locations = new ArrayList<String>();

        /*locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        listener = new MyLocationListener(getBaseContext(), locations, viewPager);

        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, listener);*/

        // locations.add("11230");
        // locations.add("08701");
        adapter = new WeatherPagerAdapter(locations, this);
        adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);


    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = preferences.edit();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < locations.size(); i++) {
            builder.append(locations.get(i) + " ");
        }
        editor.putString("ZipCodes", builder.toString().trim());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

      /*  if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
          return;
        }
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, listener);*/



            String zipCodes = preferences.getString("ZipCodes", "11210");
            String[] zipLocations = zipCodes.split(" ");
            for (int i = 0; i < zipLocations.length; i++) {
                if (!locations.contains(zipLocations[i])) {
                    locations.add(zipLocations[i]);
                    adapter.notifyDataSetChanged();
                }
            }

        }


    }

