package rodriguez.manuel.weatherapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Random;

public class TimeTickReceiver extends BroadcastReceiver {

    private FragmentCurrentWeather fragmentCurrentWeather;


    public TimeTickReceiver(FragmentCurrentWeather fragment){
        this.fragmentCurrentWeather = fragment;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_TIME_TICK.equals(intent.getAction())){
            Log.d("TimeTickReceiver", "Time tick received");

            Random random = new Random();
            int change = random.nextBoolean() ? 5 : -5;
            fragmentCurrentWeather.updateTemperature(change);
        }
    }
}
