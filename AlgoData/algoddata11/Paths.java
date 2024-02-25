public class Paths {
    City[] path;
    int sp;

    public Paths() {
        path = new City[54]; // Assuming there are 54 cities
        sp = 0;
    }

    private Integer shortest(City from, City to) {
        // Check for loops
        for (int i = 0; i < sp; i++) {
            if (path[i] == from) {
                return null; // Abort the search if the city is already in the path
            }
        }

        path[sp++] = from;

        if (from == to) {
            // Destination city reached, return 0
            path[sp--] = null;
            return 0;
        }

        Integer shrt = null;

        for (int i = 0; i < from.neighbors.length; i++) {
            if (from.neighbors[i] != null) {
                Connection conn = from.neighbors[i];
                int nextDistance = conn.getDistance();
                Integer dist = shortest(conn.getDestination(), to);
                if (dist != null) {
                    dist += nextDistance;
                    if (shrt == null || dist < shrt) {
                        shrt = dist;
                    }
                }
            }
        }

        path[sp--] = null;

        return shrt;
    }

    // Other methods and variables...
}
