package yoffe.weather;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by CS on 1/13/2016.
 */
public class MyLocationListener implements LocationListener {

    private final Context context;
    private final ArrayList<String> locations;
    private final ViewPager viewPager;
    private String gpsZipCode;

    public MyLocationListener(Context baseContext, ArrayList<String> locations, ViewPager viewPager) {
        this.context = baseContext;
        this.locations = locations;
        this.viewPager = viewPager;
    }

    @Override
    public void onLocationChanged(Location location) {
        double longitude = location.getLongitude();
        double lat = location.getLongitude();

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());

        Address address;
        try {
            addresses = geocoder.getFromLocation(lat, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                address = addresses.get(0);
                gpsZipCode = address.getPostalCode();
            }

            if (!locations.contains(gpsZipCode)) {
                locations.add(gpsZipCode);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public String toString() {
        return gpsZipCode;
    }
}
