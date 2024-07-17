package rodriguez.manuel.weatherapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentCurrentWeather extends Fragment {

    private static final String ARG_LOCATION = "location";
    private  static final String ARG_CONDITION = "condition";
    private static final String ARG_TEMPERATURE = "temperature";
    private static final String ARG_ICON_RES_ID = "iconResIcon";

    private String location = "Vancouver";
    private String condition = "Rainy";
    private String temperature = "20°C";
    private int iconResId = R.drawable.rainy;

    private CityBroadcastReceiver cityBroadcastReceiver;
    private TimeTickReceiver timeTickReceiver;

    private TextView cityNameTextView;
    private TextView conditionTextView;
    private TextView temperatureTextView;
    private ImageView weatherIconImageView;


//    public static FragmentCurrentWeather newInstance(String location){
//        FragmentCurrentWeather fragment = new FragmentCurrentWeather();
//        Bundle args = new Bundle();
//        args.putString(ARG_LOCATION, location);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            location = savedInstanceState.getString(ARG_LOCATION, location);
            condition = savedInstanceState.getString(ARG_CONDITION, condition);
            temperature = savedInstanceState.getString(ARG_TEMPERATURE, temperature);
            iconResId = savedInstanceState.getInt(ARG_ICON_RES_ID, iconResId);
        }
        if (getArguments() != null){
            location = getArguments().getString(ARG_LOCATION);
            condition = getArguments().getString(ARG_CONDITION);
            temperature = getArguments().getString(ARG_TEMPERATURE);
            iconResId = getArguments().getInt(ARG_ICON_RES_ID);
        }

        cityBroadcastReceiver = new CityBroadcastReceiver(this);
        LocalBroadcastManager.getInstance(requireContext())
                .registerReceiver(cityBroadcastReceiver, new IntentFilter("rodriguez.manuel.weatherapp.LOCATION_SELECTED"));

        timeTickReceiver = new TimeTickReceiver(this);
        requireContext().registerReceiver(timeTickReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_current_weather, container, false);

        cityNameTextView = view.findViewById(R.id.city_name);
        conditionTextView = view.findViewById(R.id.condition);
        temperatureTextView = view.findViewById(R.id.temperature);
        weatherIconImageView = view.findViewById(R.id.weather_icon);

        // Set initial values
        updateWeatherInfo(location, condition, temperature, iconResId);

        return view;
    }

    public void updateWeatherInfo(String location, String condition, String temperature, int iconResId){
        this.location = location;
        this.condition = condition;
        this.temperature = temperature;
        this.iconResId = iconResId;

        cityNameTextView.setText(location);
        conditionTextView.setText(condition);
        temperatureTextView.setText(temperature);
        weatherIconImageView.setImageResource(iconResId);
    }

    public void updateTemperature(int change){
        int currentTemperature = Integer.parseInt(temperature.replace("°C", "").trim());
        currentTemperature += change;
        this.temperature = currentTemperature +"°C";
        temperatureTextView.setText(this.temperature);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the selected city temperature
        outState.putString(ARG_LOCATION, location);
        outState.putString(ARG_CONDITION, condition);
        outState.putString(ARG_TEMPERATURE, temperature);
        outState.putInt(ARG_ICON_RES_ID, iconResId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(cityBroadcastReceiver);
        requireContext().unregisterReceiver(timeTickReceiver);
    }
}