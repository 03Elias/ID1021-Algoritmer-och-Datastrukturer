import java.util.List;
import java.util.ArrayList;
public class City {
    private String name;
    private List<Connection> connections; // List of direct connections
    public Connection[] neighbors;

    public City(String name) {
        this.name = name;
        this.connections = new ArrayList<>();
        
    }
    public void setNeighbors(Connection[] neighbors) {
        this.neighbors = neighbors;
    }

    public String getName() {
        return name;
    }

    public void connect(City destination, int distance) {
        Connection connection = new Connection(destination, distance);
        connections.add(connection);
    }

    public List<Connection> getConnections() {
        return connections;
    }
}

