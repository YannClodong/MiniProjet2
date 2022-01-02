import java.util.ArrayList;
import java.util.List;

public class RedBlueProblem2 {

    BicoloredGraph graph;

    public RedBlueProblem2(BicoloredGraph graph) {
        this.graph = graph;
    }

    public List<Vertex> resolve() {
        List<Vertex> solution = new ArrayList<>();

        //Priorité n°1: les sommets rouges avec 2 arcs bleus entrants
        ArrayList<Vertex> vertexList = new ArrayList<>(this.graph.getVertices());
        for (Vertex vertex : vertexList) {
            List<Integer> neighbours = vertex.getNeighbourOf();
            if(vertex.getColor() == VertexColor.Red
                    && neighbours.size() == 2
                    && this.graph.getEdgeColor(neighbours.get(0), vertex.getId()) == EdgeColor.Blue
                    && this.graph.getEdgeColor(neighbours.get(1), vertex.getId()) == EdgeColor.Blue) {
                solution.add(vertex);
                this.removeVertex(vertex.getId());
            }
        }

        //Priorité n°2: les sommets rouges avec 1 arc bleu entrant et 1 arc rouge sortant
        vertexList = new ArrayList<>(this.graph.getVertices());
        for (Vertex vertex : vertexList) {
            List<Integer> neighbours = vertex.getNeighbours();
            List<Integer> neighboursOf = vertex.getNeighbourOf();
            if(vertex.getColor() == VertexColor.Red
                    && neighbours.size() == 1 && neighboursOf.size() == 1
                    && this.graph.getEdgeColor(vertex.getId(), neighbours.get(0)) == EdgeColor.Red
                    && this.graph.getEdgeColor(neighboursOf.get(0), vertex.getId()) == EdgeColor.Blue) {
                solution.add(vertex);
                this.removeVertex(vertex.getId());
            }
        }

        //Priorité n°3: les sommets rouges avec 1 arc bleu entrant ou 1 arc bleu et rouge entrants
        vertexList = new ArrayList<>(this.graph.getVertices());
        for (Vertex vertex : vertexList) {
            List<Integer> neighbours = vertex.getNeighbourOf();
            if (vertex.getColor() == VertexColor.Red
                    && (
                        (neighbours.size() == 1 && this.graph.getEdgeColor(neighbours.get(0), vertex.getId()) == EdgeColor.Blue)
                                ||
                        (neighbours.size() == 2 && (this.graph.getEdgeColor(neighbours.get(0), vertex.getId()) == EdgeColor.Blue
                            || this.graph.getEdgeColor(neighbours.get(1), vertex.getId()) == EdgeColor.Blue)))) {
                solution.add(vertex);
                this.removeVertex(vertex.getId());
            }
        }

        //Priorité n°4: les autres sommets rouges
        vertexList = new ArrayList<>(this.graph.getVertices());
        for (Vertex vertex : vertexList) {
            if (vertex.getColor() == VertexColor.Red) {
                solution.add(vertex);
                this.removeVertex(vertex.getId());
            }
        }

        return solution;
    }

    public void removeVertex(int id) {
        if(this.graph.getVertex(id).getColor() == VertexColor.Blue)
            throw new RuntimeException("Removal of Blue vertex impossible.");

        graph.getVertex(id).getNeighbours().stream().map(graph::getVertex).forEach(n -> n.setColor(graph.getEdgeColor(id, n.getId()).toVertexColor()));

        graph.removeVertex(id);
    }

}
