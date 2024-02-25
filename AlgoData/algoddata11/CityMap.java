import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CityMap {
    private City[] cities;
    private final int mod = 541;
    private int collisionCount;

    public CityMap(String file) {
        cities = new City[mod];
        collisionCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String fromCityName = parts[0];
                    String toCityName = parts[1];
                    int minutes = Integer.parseInt(parts[2]);

                    City fromCity = lookup(fromCityName);
                    City toCity = lookup(toCityName);
                    fromCity.addConnection(toCity, minutes);
                    toCity.addConnection(fromCity, minutes);
                }
            }
        } catch (IOException e) {
            System.out.println("File " + file + " not found or corrupt");
        }

        System.out.println("Total Collisions: " + collisionCount);
    }

    public City lookup(String name) {
        int index = hash(name);
        if (cities[index] == null || !cities[index].getName().equals(name)) {
            cities[index] = new City(name);
        } else {
            collisionCount++;
        }
        return cities[index];
    }

    private int hash(String name) {
        int hash = 0;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash * 31 % mod) + name.charAt(i);
        }
        return hash % mod;
    }

    public class City {
        private String name;
        private List<Connection> connections;

        public City(String name) {
            this.name = name;
            this.connections = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public void addConnection(City city, int minutes) {
            connections.add(new Connection(city, minutes));
        }

        public List<Connection> getConnections() {
            return connections;
        }
    }

    public class Connection {
        private City destination;
        private int minutes;

        public Connection(City destination, int minutes) {
            this.destination = destination;
            this.minutes = minutes;
        }

        public City getDestination() {
            return destination;
        }

        public int getMinutes() {
            return minutes;
        }
    }

    public List<City> findShortestPath(String fromCityName, String toCityName) {
        City fromCity = lookup(fromCityName);
        City toCity = lookup(toCityName);

        // Initialize data structures for Dijkstra's algorithm
        Map<City, Integer> distance = new HashMap<>();
        Map<City, City> previous = new HashMap<>();
        PriorityQueue<City> unvisited = new PriorityQueue<>(Comparator.comparingInt(distance::get));

        for (City city : cities) {
            distance.put(city, Integer.MAX_VALUE);
            previous.put(city, null);
        }
        distance.put(fromCity, 0);
        unvisited.add(fromCity);

        while (!unvisited.isEmpty()) {
            City current = unvisited.poll();
            if (current.equals(toCity)) {
                List<City> path = new ArrayList<>();
                while (current != null) {
                    path.add(current);
                    current = previous.get(current);
                }
                Collections.reverse(path);
                return path;
            }

            for (Connection connection : current.getConnections()) {
                City neighbor = connection.getDestination();
                int alt = distance.get(current) + connection.getMinutes();
                if (alt < distance.get(neighbor)) {
                    distance.put(neighbor, alt);
                    previous.put(neighbor, current);
                    unvisited.add(neighbor);
                }
            }
        }

        return null; // No path found
    }

    public static void main(String[] args) {
        CityMap swedenMap = new CityMap("trains.csv");
    
    
    // Specify source and destination cities for benchmarking
    String fromCityName = "Södertälje";
    String toCityName = "Norrköping";
    
    long startTime = System.currentTimeMillis();
    List<CityMap.City> shortestPath = swedenMap.findShortestPath(fromCityName, toCityName);
    long endTime = System.currentTimeMillis();
    
    if (shortestPath != null) {
        int totalMinutes = 0;
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            CityMap.City currentCity = shortestPath.get(i);
            CityMap.City nextCity = shortestPath.get(i + 1);
            for (CityMap.Connection connection : currentCity.getConnections()) {
                if (connection.getDestination().equals(nextCity)) {
                    totalMinutes += connection.getMinutes();
                    break;
                }
            }
        }
        
        System.out.println("Shortest Path from " + fromCityName + " to " + toCityName + ": " + shortestPath);
        System.out.println("Total Time: " + totalMinutes + " minutes");
    } else {
        System.out.println("No path found between " + fromCityName + " and " + toCityName);
    }

    long elapsedTime = endTime - startTime;
    System.out.println("Elapsed Time: " + elapsedTime + " milliseconds");
        // Add additional code for testing or using the map here.
    }
    
}
