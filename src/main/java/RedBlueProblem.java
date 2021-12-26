import java.util.*;

public class RedBlueProblem {

    private final BicoloredGraph graph;
    private final int k;

    public RedBlueProblem(BicoloredGraph graph, int k) {
        this.graph = graph;
        this.k = k;
    }

    public Optional<List<Vertex>> ResolvePb() {
        if(k == 0) return Optional.of(new LinkedList<>());

        var vs = graph.getVertices();
        PriorityQueue<Vertex> vertices = new PriorityQueue<>(
                vs.size(),
                Comparator.comparingLong(n -> -n.countRedEdgesSorting(graph) + n.countBlueEdgesSorting(graph))
        );

        vs.stream()
                .filter(v -> v.getColor() == VertexColor.Red)
                .forEach(vertices::offer);

        while(!vertices.isEmpty()) {
            var current = vertices.poll();

            System.out.println("Test with: " + current.getId());
            var ng = graph.copy().ConvertToProblem(k - 1);
            ng.removeVertex(current.getId());
            var res = ng.ResolvePb();

            if(res.isPresent()) {
                var result = res.get();
                result.add(0, current);
                System.out.println("Success with: " + current.getId());
                return Optional.of(result);
            }
            System.out.println("Failed with: " + current.getId());

        }

        return Optional.empty();
    }

    public void removeVertex(int id) {
        if(this.graph.getVertex(id).getColor() == VertexColor.Blue)
            throw new RuntimeException("Removal of Blue vertex impossible.");

        graph.getVertex(id).getNeighbours().stream().map(graph::getVertex).forEach(n -> n.setColor(graph.getEdgeColor(id, n.getId()).toVertexColor()));

        graph.removeVertex(id);
    }
}
