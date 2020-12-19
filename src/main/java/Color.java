public enum Color {
    WHITE, BLACK, EMPTY;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public Color reverseColor() {
        return this == WHITE ? BLACK : WHITE;
    }
}
