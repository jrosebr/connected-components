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

        assertNull(representative.get(0));

        assertEquals(0, representative.size());
        assertEquals(0, graph.numVertices());
    }

    @Test
    public void singleGraph()
    {
        Graph<Integer> graph = new UndirectedAdjacencyList(1);
        Map<Integer, Integer> representative = new HashMap<>();

        graph.addEdge(0, 0);

        ConnectedComponents.connected_components(graph, representative);
        assertEquals(0, representative.get(0));

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

        assertEquals(0, representative.get(0));
        assertEquals(0, representative.get(1));
        assertEquals(2, representative.get(2));
        assertEquals(2, representative.get(3));

        assertEquals(graph.numVertices(), representative.size());
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
        assertEquals(3, graph.numVertices());

        assertEquals(0, representative.get(0));
        assertEquals(0, representative.get(1));
        assertEquals(0, representative.get(2));
    }

    @Test
    public void largeGraph()
    {
        Graph<Integer> graph = new UndirectedAdjacencyList(7);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);

        Map<Integer, Integer> representative = new HashMap<>();
        ConnectedComponents.connected_components(graph, representative);
        assertEquals(7, representative.size());
        assertEquals(7, graph.numVertices());

        assertEquals(0, representative.get(0));
        assertEquals(0, representative.get(1));
        assertEquals(0, representative.get(2));
        assertEquals(0, representative.get(3));
        assertEquals(0, representative.get(4));
        assertEquals(0, representative.get(5));
        assertEquals(0, representative.get(6));
    }

    @Test
    public void randomTest()
    {

        for (int loop = 0; loop < 100; ++loop)
        {
            Random rand = new Random();

            int num_vertices = rand.nextInt(500) + 1;
            int randomVertexAmount = rand.nextInt(1000) + 1;

            Map<Integer, Integer> representative = new HashMap<>();

            Graph<Integer> graph = new UndirectedAdjacencyList(num_vertices);

            for (int i = 0; i < num_vertices - 1; ++i)
            {
                int randomNum1 = rand.nextInt(randomVertexAmount);
                int randomNum2 = rand.nextInt(randomVertexAmount);

                graph.addEdge(randomNum1, randomNum2);
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
    public void test()
    {
        emptyGraph();
        singleGraph();
        disconnectedGraphs();
        smallGraph();
        largeGraph();
        randomTest();
    }

}
