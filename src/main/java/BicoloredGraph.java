import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BicoloredGraph {
    private final EdgeColor[][] edges;
    private final Vertex[] vertices;
    private final List<Vertex> vertexList;
    private final int n;

    public BicoloredGraph(int n) {
        this.edges = new EdgeColor[n][n];
        this.vertices = new Vertex[n];
        this.vertexList = new ArrayList<>(n + 1);
        this.n = n;

        // Initialize all edges
        for(int s = 0; s < n; s++) {
            for(int e = 0; e < n; e++) {
                edges[s][e] = EdgeColor.None;
            }
        }
    }

    public void addVertex(Vertex vertex) {
        if(vertices[vertex.getId()] != null)
            throw new RuntimeException("Vertex already have the same id");
        if(vertex.getId() < 0 || vertex.getId() >= 100)
            throw new RuntimeException("Id is out of range [0, 100[");

        vertexList.add(vertex);

        vertices[vertex.getId()] = vertex;
    }

    public void addEdge(Vertex start, Vertex end, EdgeColor color) {
        addEdge(start.getId(), end.getId(), color);
    }
    public void addEdge(int idStart, int idEnd, EdgeColor color) {
        if(edges[idStart][idEnd] != EdgeColor.None)
            throw new RuntimeException("Can't add this edge because it already exist");
        edges[idStart][idEnd] = color;
        vertices[idStart].addNeighbour(idEnd);
        vertices[idEnd].addIsNeighbourOf(idStart);
    }

    public void removeVertex(int id) {
        if(vertices[id] == null)
            throw new RuntimeException("No vertex with this id");

        vertexList.remove(vertices[id]);

        // Remove sorting edges
        for(var end : vertices[id].getNeighbours()) {
            edges[id][end] = EdgeColor.None;
            vertices[end].removeIsNeighbourOf(id);
        }

        // Remove entering edges
        for(var start : vertices[id].getNeighbourOf()) {
            edges[start][id] = EdgeColor.None;
            vertices[start].removeNeighbour(id);
        }

        // Remove vertex
        vertices[id] = null;
    }

    public void removeEdge(int idStart, int idEnd) {
        if(edges[idStart][idEnd] == EdgeColor.None)
            throw new RuntimeException("No edges between these vertices");
        edges[idStart][idEnd] = EdgeColor.None;

        vertices[idStart].removeNeighbour(idEnd);
        vertices[idEnd].removeIsNeighbourOf(idStart);
    }

    public Vertex getVertex(int id) {
        if(vertices[id] == null)
            throw new RuntimeException("Vertex not found");
        return vertices[id];
    }

    public EdgeColor getEdgeColor(int start, int end) {
        if(edges[start][end] == EdgeColor.None)
            throw new RuntimeException("This edge don't exist");
        return edges[start][end];
    }

    public BicoloredGraph copy() {
        var result = new BicoloredGraph(n);

        vertexList.forEach(v -> result.addVertex(new Vertex(v.getId(), v.getColor())));
        vertexList.forEach(start -> start.getNeighbours()
                .forEach(end -> result.addEdge(start.getId(), end, getEdgeColor(start.getId(), end))));

        return result;
    }

    public RedBlueProblem ConvertToProblem(int k) {
        return new RedBlueProblem(this, k);
    }

    public List<Vertex> getVertices() {
        return vertexList;
    }

    public static BicoloredGraph generate(float p, float q, float r) {
        Random rnd = new Random();
        var graph = new BicoloredGraph(100);
        Vertex prev = new Vertex(0, VertexColor.random(rnd, p));
        graph.addVertex(prev);
        for (int i = 1; i < 100; i++) {
            Vertex vertex = new Vertex(i, VertexColor.random(rnd, p));
            graph.addVertex(vertex);
            if(rnd.nextFloat() <= r)
                graph.addEdge(i, i-1, EdgeColor.random(rnd, q));
            else
                graph.addEdge(i-1, i, EdgeColor.random(rnd, q));
        }
        return graph;
    }
}
