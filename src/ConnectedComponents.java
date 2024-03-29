import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConnectedComponents {

    /*
     * TODO
     */
    public static <V> void
    connected_components(Graph<V> G, Map<V, V> representative)
    {
        Set<V> visited = new HashSet<>();

        for (V vertex : G.vertices())
        {
            if (!visited.contains(vertex))
            {
                //Sets this vertex as the representative
                V rep = vertex;

                dfs(G, vertex, visited, rep, representative);
            }
        }
    }

    private static <V> void dfs(Graph<V> G, V vertex, Set<V> visited, V rep, Map<V, V> representative)
    {
        //Assign curr representative to the curr vertex
        visited.add(vertex);
        representative.put(vertex, rep);

        for (V neighbor : G.adjacent(vertex))
        {
            if (!visited.contains(neighbor))
            {
                //rep = neighbor;

                dfs(G, vertex, visited, rep, representative);
            }
        }
    }


}
