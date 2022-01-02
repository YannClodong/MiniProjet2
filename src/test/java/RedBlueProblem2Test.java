import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

public class RedBlueProblem2Test {

    @Test
    public void checkResolve() {
        BicoloredGraph graph = new BicoloredGraph(8);
        Vertex A = new Vertex(0, VertexColor.Red);
        Vertex B = new Vertex(1, VertexColor.Blue);
        Vertex C = new Vertex(2, VertexColor.Red);
        Vertex D = new Vertex(3, VertexColor.Blue);
        Vertex E = new Vertex(4, VertexColor.Blue);
        Vertex F = new Vertex(5, VertexColor.Red);
        Vertex G = new Vertex(6, VertexColor.Red);
        Vertex H = new Vertex(7, VertexColor.Blue);

        graph.addVertex(A);
        graph.addVertex(B);
        graph.addVertex(C);
        graph.addVertex(D);
        graph.addVertex(E);
        graph.addVertex(F);
        graph.addVertex(G);
        graph.addVertex(H);

        graph.addEdge(A, B, EdgeColor.Red);
        graph.addEdge(C, B, EdgeColor.Blue);
        graph.addEdge(C, D, EdgeColor.Red);
        graph.addEdge(D, E, EdgeColor.Blue);
        graph.addEdge(F, E, EdgeColor.Blue);
        graph.addEdge(F, G, EdgeColor.Blue);
        graph.addEdge(H, G, EdgeColor.Red);

        var pb = new RedBlueProblem2(graph);
        var res = pb.resolve();

        int[] ids = new int[res.size()];
        for (int i = 0; i < res.size(); i++)
            ids[i] = res.get(i).getId();
        Assertions.assertEquals(6, res.size());
        Assertions.assertArrayEquals(new int[]{6, 0, 1, 2, 3, 5}, ids);
    }
}
