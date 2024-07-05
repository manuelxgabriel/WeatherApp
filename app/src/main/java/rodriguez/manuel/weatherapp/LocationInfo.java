package rodriguez.manuel.weatherapp;

public class LocationInfo {
    public String location;
    public String weather;
    public String condition;
    public String iconName;
    public int iconResId;

    LocationInfo (String location, String weather, String condition, String iconName ){
        this.location = location;
        this.weather = weather;
        this.condition = condition;
        this.iconName = iconName;
    }

    // Getters
    public String getCity(){return location;}

    public String getCondition(){return condition;}

    public String getTemperature(){return weather;}

    public String getIconName(){return iconName;}
    public int getIconResId(){return iconResId;}

    // Setters
    public void setIconResId(int iconResId){
        this.iconResId = iconResId;
    }





}
