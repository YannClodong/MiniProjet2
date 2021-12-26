public enum VerticeColor {
    Blue(false),
    Red(true);


    private final boolean b;

    VerticeColor(boolean b) {
        this.b = b;
    }

    boolean getValue() {
        return b;
    }

    public static VerticeColor getByValue(boolean v) {
        if(v) return Red;
        else return Blue;
    }

    public VerticeColor swap() {
        if(b) return Blue;
        else return Red;
    }
}
