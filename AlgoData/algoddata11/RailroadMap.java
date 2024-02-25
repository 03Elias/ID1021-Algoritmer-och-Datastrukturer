import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RailroadMap {
    private Map<String, List<City>> cityMap;
    private int mod = 541; // Chosen prime number

    public RailroadMap() {
        cityMap = new HashMap<>();
    }

    public void addCity(String cityName) {
        String key = Integer.toString(hash(cityName)); // Convert the hash to a string for the key

        if (!cityMap.containsKey(key)) {
            cityMap.put(key, new ArrayList<>());
        }
        cityMap.get(key).add(new City(cityName));
    }

    public City getCity(String cityName) {
        String key = Integer.toString(hash(cityName)); // Convert the hash to a string for the key

        if (cityMap.containsKey(key)) {
            List<City> cities = cityMap.get(key);
            for (City city : cities) {
                if (city.getName().equals(cityName)) {
                    return city;
                }
            }
        }
        return null; // City not found
    }

    // Hash function
    private int hash(String name) {
        int hash = 0;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash * 31 % mod) + name.charAt(i);
        }
        return hash % mod;
    }
}
