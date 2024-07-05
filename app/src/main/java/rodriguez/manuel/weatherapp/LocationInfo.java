package rodriguez.manuel.weatherapp;

public class LocationInfo {
    public String location;
    public String weather;
    public String condition;
    int icon;

    LocationInfo (String location, String weather, String condition, int icon ){
        this.location = location;
        this.weather = weather;
        this.condition = condition;
        this.icon = icon;
    }
}
