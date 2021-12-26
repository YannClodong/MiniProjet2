import java.util.Random;

public class BicoloredGraph {
    private final EdgeColor[][] edges;
    public final VerticeColor[] vertices;

    public BicoloredGraph(int n) {
        this.edges = new EdgeColor[n][n];
        this.vertices = new VerticeColor[n];
    }

    public void colorVertice(int i, VerticeColor color) {
        vertices[i] = color;
    }

    public void swapVerticeColor(int i) {
        vertices[i] = vertices[i].swap();
    }

    public void makeEdge(int from, int to, EdgeColor color) {
        if(this.edges[from][to] != EdgeColor.None) throw new RuntimeException("There is already an edge here");
        this.edges[from][to] = color;
    }

    public static BicoloredGraph generate(float p, float q, float r) {
        Random rnd = new Random();

        var result = new BicoloredGraph(100);

        for(int i = 0; i < 100; i++) {
            if(rnd.nextInt(Integer.MAX_VALUE) < r * (double)Integer.MAX_VALUE)
                result.colorVertice(i, VerticeColor.Red);
            else
                result.colorVertice(i, VerticeColor.Blue);

            int from = i;
            int to = i + 1;

            // color edge ...
        }

        return result;
    }
}
