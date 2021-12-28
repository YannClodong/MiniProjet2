import java.util.Random;

public enum VertexColor {
    Blue(false),
    Red(true);


    private final boolean b;

    VertexColor(boolean b) {
        this.b = b;
    }

    boolean getValue() {
        return b;
    }

    public static VertexColor getByValue(boolean v) {
        if(v) return Red;
        else return Blue;
    }

    public static VertexColor random(Random random, float p) {
        return random.nextFloat() <= p ? VertexColor.Red : VertexColor.Blue;
    }

    public VertexColor swap() {
        if(b) return Blue;
        else return Red;
    }
}
