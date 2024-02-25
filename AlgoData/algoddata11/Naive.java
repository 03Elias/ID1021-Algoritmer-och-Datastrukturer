import java.util.*;

public class Naive {
    public static void main(String[] args) {
        CityMap map = new CityMap("trains.csv");

        // Specify the trips and maximum allowed time
        String[][] trips = {
            {"Malmö", "Göteborg", "Malmö to Göteborg"},
            {"Göteborg", "Stockholm", "Göteborg to Stockholm"},
            {"Malmö", "Stockholm", "Malmö to Stockholm"},
            {"Stockholm", "Sundsvall", "Stockholm to Sundsvall"},
            {"Stockholm", "Umeå", "Stockholm to Umeå"},
            {"Göteborg", "Sundsvall", "Göteborg to Sundsvall"},
            {"Sundsvall", "Umeå", "Sundsvall to Umeå"},
            {"Umeå", "Göteborg", "Umeå to Göteborg"},
            {"Göteborg", "Umeå", "Göteborg to Umeå"}
        };
        Integer maxMinutes = 550; // Maximum allowed time

        for (String[] trip : trips) {
            String fromCityName = trip[0];
            String toCityName = trip[1];
            String tripDescription = trip[2];

            long t0 = System.nanoTime();
            Integer dist = shortest(map.lookup(fromCityName), map.lookup(toCityName), maxMinutes);
            long time = (System.nanoTime() - t0) / 1_000_000;

            if (dist != null) {
                System.out.println("Shortest path for " + tripDescription + ": " + dist + " min (" + time + " ms)");
            } else {
                System.out.println("No path found within " + maxMinutes + " minutes for " + tripDescription + " (" + time + " ms)");
            }
        }
    }

    private static Integer shortest(CityMap.City from, CityMap.City to, Integer maxMinutes) {
        if (maxMinutes < 0)
            return null;
        if (from == to)
            return 0;

        Integer shortestDistance = null;

        for (CityMap.Connection connection : from.getConnections()) {
            int connectionMinutes = connection.getMinutes();
            CityMap.City nextCity = connection.getDestination();
            Integer remainingMinutes = maxMinutes - connectionMinutes;

            Integer pathDistance = shortest(nextCity, to, remainingMinutes);

            if (pathDistance != null) {
                int totalDistance = connectionMinutes + pathDistance;
                if (shortestDistance == null || totalDistance < shortestDistance) {
                    shortestDistance = totalDistance;
                }
            }
        }

        return shortestDistance;
    }
}
