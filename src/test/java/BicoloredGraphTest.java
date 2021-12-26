import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BicoloredGraphTest {
    private Vertex Ab, Bb;
    private Vertex A, B, C, D, E, F, G, H;
    private BicoloredGraph graph, connectedGraph, largeGraph;

    @BeforeEach
    public void Init() {
        graph = new BicoloredGraph(100);
        this.Ab = new Vertex(0, VertexColor.Blue);
        this.Bb = new Vertex(1, VertexColor.Red);
        connectedGraph = new BicoloredGraph(10);
        connectedGraph.addVertex(this.Ab);
        connectedGraph.addVertex(this.Bb);
        connectedGraph.addEdge(this.Ab, this.Bb, EdgeColor.Blue);

        largeGraph = new BicoloredGraph(8);
        A = new Vertex(0, VertexColor.Blue);
        B = new Vertex(1, VertexColor.Red);
        C = new Vertex(2, VertexColor.Red);
        D = new Vertex(3, VertexColor.Blue);
        E = new Vertex(4, VertexColor.Red);
        F = new Vertex(5, VertexColor.Blue);
        G = new Vertex(6, VertexColor.Blue);
        H = new Vertex(7, VertexColor.Red);

        largeGraph.addVertex(A);
        largeGraph.addVertex(B);
        largeGraph.addVertex(C);
        largeGraph.addVertex(D);
        largeGraph.addVertex(E);
        largeGraph.addVertex(F);
        largeGraph.addVertex(G);
        largeGraph.addVertex(H);

        largeGraph.addEdge(A, B, EdgeColor.Blue);
        largeGraph.addEdge(B, D, EdgeColor.Red);
        largeGraph.addEdge(A, C, EdgeColor.Blue);
        largeGraph.addEdge(C, A, EdgeColor.Red);
        largeGraph.addEdge(C, E, EdgeColor.Blue);
        largeGraph.addEdge(C, D, EdgeColor.Blue);
        largeGraph.addEdge(E, D, EdgeColor.Red);
        largeGraph.addEdge(E, F, EdgeColor.Blue);
        largeGraph.addEdge(F, E, EdgeColor.Blue);
        largeGraph.addEdge(G, E, EdgeColor.Red);
        largeGraph.addEdge(G, F, EdgeColor.Blue);
        largeGraph.addEdge(G, H, EdgeColor.Red);
        largeGraph.addEdge(H, F, EdgeColor.Red);
        largeGraph.addEdge(H, D, EdgeColor.Blue);
    }

    @Test
    public void CourseProblem() {

    }

    @Test
    public void TestAddVertex() {
        graph.addVertex(new Vertex(0, VertexColor.Red));

        var vertex = graph.getVertex(0);

        Assertions.assertEquals(0, vertex.getId());
        Assertions.assertEquals(VertexColor.Red, vertex.getColor());

        Assertions.assertThrows(RuntimeException.class, () -> graph.getVertex(1));
    }

    @Test
    public void TestAddEdge() {
        var vertexA = new Vertex(0, VertexColor.Blue);
        var vertexB = new Vertex(1, VertexColor.Red);

        graph.addVertex(vertexA);
        graph.addVertex(vertexB);

        graph.addEdge(vertexA.getId(), vertexB.getId(), EdgeColor.Blue);

        Assertions.assertTrue(vertexA.getNeighbours().contains(vertexB.getId()));
        Assertions.assertTrue(vertexB.getNeighbourOf().contains(vertexA.getId()));

        Assertions.assertFalse(vertexA.getNeighbourOf().contains(vertexB.getId()));
        Assertions.assertFalse(vertexB.getNeighbours().contains(vertexA.getId()));
    }

    @Test
    public void TestRemoveVertex() {
        Assertions.assertTrue(Bb.getNeighbourOf().contains(Ab.getId()));
        connectedGraph.removeVertex(Ab.getId());
        Assertions.assertFalse(Bb.getNeighbourOf().contains(Ab.getId()));

        Assertions.assertTrue(A.getNeighbours().contains(B.getId()));
        largeGraph.removeVertex(B.getId());
        Assertions.assertFalse(A.getNeighbours().contains(B.getId()));

    }

}
