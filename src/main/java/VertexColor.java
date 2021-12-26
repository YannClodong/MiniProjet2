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

    public VertexColor swap() {
        if(b) return Blue;
        else return Red;
    }
}
