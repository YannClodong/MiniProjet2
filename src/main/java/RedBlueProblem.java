import java.util.*;

public class RedBlueProblem {
    private final BicoloredGraph graph;

    public RedBlueProblem(BicoloredGraph graph) {
        this.graph = graph;
    }

    public LinkedList<Vertex> ResolvePb() {
        return getMaxOnBranch(graph);
    }


    private LinkedList<Vertex> getMaxOnBranch(BicoloredGraph graph) {
        var vs = graph.getVertices();
        if(vs.stream().noneMatch(v -> v.getColor() == VertexColor.Red))
            return new LinkedList<>();

        var vertices = new PriorityQueue<Vertex>(
                vs.size(),
                Comparator.comparingLong(v -> v.countPotentiel(graph) - v.countAntiPotentiel(graph))
        );

        vs.stream()
                .filter(v -> v.getColor() == VertexColor.Red)
                .forEach(vertices::offer);

        return vertices.stream()
                .map(v -> {
                    var g = graph.copy();
                    removeVertex(g, v.getId());
                    var soluce = getMaxOnBranch(g);
                    soluce.add(0, v);
                    return soluce;
                })
                .max(Comparator.comparingInt(List::size))
                .orElseThrow();
    }

    private void removeVertex(BicoloredGraph graph, int id) {
        if(graph.getVertex(id).getColor() == VertexColor.Blue)
            throw new RuntimeException("Removal of Blue vertex impossible.");

        graph.getVertex(id).getNeighbours().stream().map(graph::getVertex).forEach(n -> n.setColor(graph.getEdgeColor(id, n.getId()).toVertexColor()));

        graph.removeVertex(id);
    }
}
