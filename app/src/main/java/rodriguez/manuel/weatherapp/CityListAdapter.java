package rodriguez.manuel.weatherapp;

import android.content.Context;
import android.location.Location;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CityListAdapter extends ArrayAdapter<LocationInfo> {
    public CityListAdapter(Context context, List<LocationInfo> locations){
        super(context, 0, locations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LocationInfo location = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.weather_card,parent,false);
        }

        TextView locationView = convertView.findViewById(R.id.location);
        TextView conditionView = convertView.findViewById(R.id.condition);
        TextView temperatureView = convertView.findViewById(R.id.temperature);
        ImageView iconView = convertView.findViewById(R.id.icon);


        locationView.setText(location.getCity());
        conditionView.setText(location.getCondition());
        temperatureView.setText(location.getTemperature());
        iconView.setImageResource(location.getIconResId());

        return convertView;

    }
}
