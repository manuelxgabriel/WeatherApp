package rodriguez.manuel.weatherapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class CityBroadcastReceiver extends BroadcastReceiver {

    private FragmentCurrentWeather fragment;

    public CityBroadcastReceiver(FragmentCurrentWeather fragment){

        this.fragment = fragment;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String location = intent.getStringExtra("location");
        String condition = intent.getStringExtra("condition");
        String temperature = intent.getStringExtra("temperature");
        int iconResId = intent.getIntExtra("iconResId", -1);

        // Log the received data for debugging
        Log.d("CityBroadcastReceiver", "Receiver: " + location);

        // Update the weather fragment
        fragment.updateWeatherInfo(location, condition, temperature, iconResId);
    }
}
