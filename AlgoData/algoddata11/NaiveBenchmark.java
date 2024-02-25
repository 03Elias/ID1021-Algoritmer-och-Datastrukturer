  import java.util.Set;
import java.util.HashSet;
public class NaiveBenchmark {
    public static void main(String[] args) {
        CityMap map = new CityMap("trains.csv");

        benchmarkAndPrint("Malmö", "Göteborg", 300, map);
        benchmarkAndPrint("Göteborg", "Stockholm", 300, map);
        benchmarkAndPrint("Malmö", "Stockholm", 300, map);
        benchmarkAndPrint("Stockholm", "Sundsvall", 300, map);
        benchmarkAndPrint("Stockholm", "Umeå", 300, map);
        benchmarkAndPrint("Göteborg", "Sundsvall", 300, map);
        benchmarkAndPrint("Sundsvall", "Umeå", 300, map);
        benchmarkAndPrint("Umeå", "Göteborg", 300, map);
        benchmarkAndPrint("Göteborg", "Umeå", 300, map);
    }

    public static void benchmarkAndPrint(String fromCity, String toCity, int maxDepth, CityMap map) {
        long startTime = System.currentTimeMillis();
        Integer dist = shortest(map.lookup(fromCity), map.lookup(toCity), maxDepth);
        long endTime = System.currentTimeMillis();

        if (dist != null) {
            System.out.println("Shortest Path from " + fromCity + " to " + toCity + ": " + dist + " minutes");
        } else {
            System.out.println("No path found between " + fromCity + " and " + toCity);
        }

        long elapsedTime = endTime - startTime;
        System.out.println("Elapsed Time: " + elapsedTime + " milliseconds");
        System.out.println("-------------------------");
    }

    public static Integer shortest(CityMap.City city, CityMap.City city2, int maxDepth) {
        return shortestHelper(city, city2, maxDepth, 0, Integer.MAX_VALUE, null);
    }

  

    
    public static Integer shortestHelper(CityMap.City currentCity, CityMap.City toCity, int maxDepth, int depth, int shortestDistance, Set<CityMap.City> visited) {
        if (currentCity.equals(toCity)) {
            return 0; // We've reached the destination
        }
        if (depth >= maxDepth || (shortestDistance != Integer.MAX_VALUE && depth >= shortestDistance) || visited.contains(currentCity)) {
            return null; // Depth limit exceeded, already found a shorter path, or already visited this city
        }
    
        visited.add(currentCity);
    
        int minDistance = Integer.MAX_VALUE;
        boolean foundValidPath = false;
    
        for (CityMap.Connection connection : currentCity.getConnections()) {
            Integer nextDistance = shortestHelper(connection.getDestination(), toCity, maxDepth, depth + 1, shortestDistance, visited);
            if (nextDistance != null) {
                int totalDistance = connection.getMinutes() + nextDistance;
                if (totalDistance < minDistance) {
                    minDistance = totalDistance;
                    foundValidPath = true;
                }
            }
        }
    
        visited.remove(currentCity);
    
        return foundValidPath ? minDistance : null;
    }
    
    
}
