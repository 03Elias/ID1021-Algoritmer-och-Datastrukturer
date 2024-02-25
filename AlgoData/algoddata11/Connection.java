public class Connection {
    private City destination;
    private int distance; // Distance in minutes

    public Connection(City destination, int distance) {
        this.destination = destination;
        this.distance = distance;
    }

    public City getDestination() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }
}
