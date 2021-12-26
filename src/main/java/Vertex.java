import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private final int id;
    private VertexColor color;
    private final List<Integer> neighbours = new ArrayList<>();
    private final List<Integer> isNeighbourOf = new ArrayList<>();

    public Vertex(int id, VertexColor color) {
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public VertexColor getColor() {
        return color;
    }

    public List<Integer> getNeighbours() {
        return neighbours;
    }

    public long countRedEdgesSorting(BicoloredGraph graph) {
        return neighbours.stream().map(n -> graph.getEdgeColor(id, n)).filter(c -> c == EdgeColor.Red).count();
    }

    public long countBlueEdgesSorting(BicoloredGraph graph) {
        return neighbours.stream().map(n -> graph.getEdgeColor(id, n)).filter(c -> c == EdgeColor.Blue).count();
    }

    public void addNeighbour(int vertex) {
        this.neighbours.add(vertex);
    }

    public void removeNeighbour(int vertex) {
        this.neighbours.remove((Object)vertex);
    }

    public void addIsNeighbourOf(int vertex) {
        this.isNeighbourOf.add(vertex);
    }

    public void removeIsNeighbourOf(int vertex) {
        this.isNeighbourOf.remove((Object)vertex);
    }

    public List<Integer> getNeighbourOf() {
        return isNeighbourOf;
    }

    public void setColor(VertexColor color) {
        this.color = color;
    }
}
