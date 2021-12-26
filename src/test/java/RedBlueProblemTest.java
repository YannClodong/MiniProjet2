import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RedBlueProblemTest {



    @Test
    public void CourseProblem() {
        BicoloredGraph graph = new BicoloredGraph(8);
        Vertex A = new Vertex(0, VertexColor.Blue);
        Vertex B = new Vertex(1, VertexColor.Red);
        Vertex C = new Vertex(2, VertexColor.Red);
        Vertex D = new Vertex(3, VertexColor.Blue);
        Vertex E = new Vertex(4, VertexColor.Red);
        Vertex F = new Vertex(5, VertexColor.Blue);
        Vertex G = new Vertex(6, VertexColor.Blue);
        Vertex H = new Vertex(7, VertexColor.Red);

        graph.addVertex(A);
        graph.addVertex(B);
        graph.addVertex(C);
        graph.addVertex(D);
        graph.addVertex(E);
        graph.addVertex(F);
        graph.addVertex(G);
        graph.addVertex(H);

        graph.addEdge(A, B, EdgeColor.Blue);
        graph.addEdge(B, D, EdgeColor.Red);
        graph.addEdge(A, C, EdgeColor.Blue);
        graph.addEdge(C, A, EdgeColor.Red);
        graph.addEdge(C, E, EdgeColor.Blue);
        graph.addEdge(C, D, EdgeColor.Blue);
        graph.addEdge(E, D, EdgeColor.Red);
        graph.addEdge(E, F, EdgeColor.Blue);
        graph.addEdge(F, E, EdgeColor.Blue);
        graph.addEdge(G, E, EdgeColor.Red);
        graph.addEdge(G, F, EdgeColor.Blue);
        graph.addEdge(G, H, EdgeColor.Red);
        graph.addEdge(H, F, EdgeColor.Red);
        graph.addEdge(H, D, EdgeColor.Blue);

        int k = 6;
        var pb = graph.ConvertToProblem(k);
        var res = pb.ResolvePb();

        Assertions.assertTrue(res.isPresent());
        Assertions.assertEquals(k, res.get().size());
    }
}
