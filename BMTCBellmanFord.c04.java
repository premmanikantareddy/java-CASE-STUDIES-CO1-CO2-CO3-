import java.util.*;

class Edge {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

public class BMTCBellmanFord {

    static final int INF = Integer.MAX_VALUE;

    static void bellmanFord(List<Edge> edges, int V, int source) {
        int[] dist = new int[V];

        Arrays.fill(dist, INF);
        dist[source] = 0;

        // Relax all edges V-1 times
        for (int i = 1; i < V; i++) {
            boolean updated = false;

            for (Edge edge : edges) {
                if (dist[edge.src] != INF &&
                    dist[edge.src] + edge.weight < dist[edge.dest]) {

                    dist[edge.dest] = dist[edge.src] + edge.weight;
                    updated = true;
                }
            }

            // Early convergence
            if (!updated)
                break;
        }

        // Check for negative-weight cycles
        for (Edge edge : edges) {
            if (dist[edge.src] != INF &&
                dist[edge.src] + edge.weight < dist[edge.dest]) {

                System.out.println("Negative Weight Cycle Detected!");
                return;
            }
        }

        System.out.println("Shortest distances from source MJC:");
        String[] hubs = {"MJC", "KEM", "JAY", "KOR", "WHF", "HBR", "MRT"};

        for (int i = 0; i < V; i++) {
            System.out.println(hubs[i] + " : " + dist[i]);
        }
    }

    public static void main(String[] args) {

        int V = 7; // MJC, KEM, JAY, KOR, WHF, HBR, MRT

        List<Edge> edges = new ArrayList<>();

        // Sample BMTC route graph
        edges.add(new Edge(0, 1, 4));   // MJC -> KEM
        edges.add(new Edge(0, 2, 5));   // MJC -> JAY
        edges.add(new Edge(1, 3, 3));   // KEM -> KOR
        edges.add(new Edge(2, 3, 2));   // JAY -> KOR
        edges.add(new Edge(3, 4, 4));   // KOR -> WHF
        edges.add(new Edge(4, 6, -3));  // WHF -> MRT (bus-lane bonus)
        edges.add(new Edge(3, 5, 6));   // KOR -> HBR
        edges.add(new Edge(5, 6, 5));   // HBR -> MRT

        int source = 0; // MJC

        bellmanFord(edges, V, source);
    }
}