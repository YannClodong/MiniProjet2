public enum EdgeColor {
    Blue,
    Red,
    None;

    public VertexColor toVertexColor() {
        switch (this) {
            case Blue: return VertexColor.Blue;
            case Red: return VertexColor.Red;
            case None: throw new RuntimeException("Can't convert None to a valid VertexColor");
        }
        throw new RuntimeException("Unexpected exeception");
    }
}
