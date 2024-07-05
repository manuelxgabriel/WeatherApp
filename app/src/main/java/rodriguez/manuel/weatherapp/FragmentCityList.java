package rodriguez.manuel.weatherapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentCityList extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[]  locations = {
                "Victoria",
                "Vancouver",
                "Kelowna",
                "Surrey",
                "Burnaby",
                "Richmond",
                "Nanaimo",
                "Kamloops",
                "Prince George",
                "New Westminster",
                "Chilliwack",
                "Maple Ridge",
                "Duncan",
                "Penticton",
                "Courtenay",
                "Langford",
                "Fort St. John",
                "Parksville",
                "White Rock",
                "Port Moody"
        };


        View view  = inflater.inflate(R.layout.fragment_city_list, container, false);

        ListView listView = view.findViewById(R.id.city_list_view);

        ArrayAdapter<String> adapter = new ArrayAdapter<> (
                requireContext(),
                android.R.layout.simple_list_item_1,
                locations
        );

        listView.setAdapter(adapter);


        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_city_list, container, false);
        return view;
    }



}