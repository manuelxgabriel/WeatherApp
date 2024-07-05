package rodriguez.manuel.weatherapp;

import android.location.Location;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;


public class FragmentCityList extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_city_list, container, false);


        ListView listView = view.findViewById(R.id.city_list_view);
        String data = loadJSONFromAsset();
        List<LocationInfo>locations = parseLocation(data);


        CityListAdapter adapter = new CityListAdapter(
                requireContext(),
                locations
        );

        listView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }


    private String loadJSONFromAsset(){
        String json = null;
        try{
            InputStream is = requireContext().getAssets().open("weather_locations.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private List<LocationInfo> parseLocation(String json){
        Gson gson = new Gson();
        Type locationInfoType = new TypeToken<List<LocationInfo>>() {}.getType();
        List<LocationInfo> locations = gson.fromJson(json, locationInfoType);

        for(LocationInfo location : locations){
            int iconResId = getIconResId(location.getIconName());
            location.setIconResId(iconResId);
        }

        return locations;
    }

    // Convert the String into a int for the icons
    private int getIconResId(String iconName){
        return getContext()
                .getResources()
                .getIdentifier(iconName, "drawable", getContext().getPackageName());
    }




}