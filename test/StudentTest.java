import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StudentTest
{

    @Test
    public void emptyGraph()
    {
        Graph<Integer> graph = new UndirectedAdjacencyList(0);
        Map<Integer, Integer> representative = new HashMap<>();

        ConnectedComponents.connected_components(graph, representative);
        assertEquals(0, representative.size());
        assertEquals(0, graph.numVertices());
    }

    @Test
    public void singleGraph()
    {
        Graph<Integer> graph = new UndirectedAdjacencyList(1);
        Map<Integer, Integer> representative = new HashMap<>();

        graph.addEdge(0, 1);

        ConnectedComponents.connected_components(graph, representative);

        assertEquals(1, representative.size());
        assertEquals(1, graph.numVertices());
    }

    @Test
    public void disconnectedGraphs()
    {
        Graph<Integer> graph = new UndirectedAdjacencyList(5);
        graph.addEdge(0, 1);
        graph.addEdge(2, 3);

        Map<Integer, Integer> representative = new HashMap<>();
        ConnectedComponents.connected_components(graph, representative);
        assertEquals(5, representative.size());
        assertEquals(5, graph.numVertices());

        assertNotEquals(representative.get(0), representative.get(2));
    }

    @Test
    public void smallGraph()
    {
        Graph<Integer> graph = new UndirectedAdjacencyList(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);

        Map<Integer, Integer> representative = new HashMap<>();
        ConnectedComponents.connected_components(graph, representative);

        assertEquals(3, representative.size());
        assertEquals(representative.get(0), representative.get(1));
        assertEquals(representative.get(1), representative.get(2));


    }

    @Test
    public void largeGraph()
    {
        Graph<Integer> graph = new UndirectedAdjacencyList(7);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(5, 6);

        Map<Integer, Integer> representative = new HashMap<>();
        ConnectedComponents.connected_components(graph, representative);
        assertEquals(7, representative.size());
        assertEquals(7, graph.numVertices());

        assertEquals(representative.get(0), representative.get(1));
        assertEquals(representative.get(1), representative.get(2));
        assertEquals(representative.get(3), representative.get(4));
        assertEquals(representative.get(5), representative.get(6));
        assertNotEquals(representative.get(0), representative.get(3));
    }

    @Test
    public void randomTest()
    {

        for (int loop = 0; loop < 10; ++loop)
        {
            int num_vertices = 1000;

            Map<Integer, Integer> representative = new HashMap<>();

            Graph<Integer> graph = new UndirectedAdjacencyList(num_vertices);

            Random rand = new Random();
            for (int i = 0; i < num_vertices - 1; ++i)
            {
                for (int j = i + 1; j < num_vertices; ++j)
                {
                    if (rand.nextDouble() < 0.5)
                    {
                        graph.addEdge(i, j);
                    }
                }
            }

            ConnectedComponents.connected_components(graph, representative);
            assertEquals(num_vertices, representative.size());

            for (int i = 0; i < graph.numVertices(); ++i)
            {
                for (int j = i + 1; j < graph.numVertices(); ++j)
                {
                    if (((UndirectedAdjacencyList) graph).hasEdge(i, j))
                    {
                        assertTrue(representative.containsKey(i));
                        assertEquals(representative.get(i), representative.get(j));

                        int currentRep = representative.get(i);
                        assertEquals(currentRep, representative.get(currentRep));
                    }
                }
            }
        }
    }

    @Test
    public void test() {
        emptyGraph();
        singleGraph();
        disconnectedGraphs();
        smallGraph();
        largeGraph();
        randomTest();
    }

}
