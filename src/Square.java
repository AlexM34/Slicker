public class Square {
    private final int x;
    private final int y;
    private Piece piece;
    private Boolean color;

    public Square(final int x, final int y, final Piece piece, final Boolean color) {
        this.x = x;
        this.y = y;
        this.piece = piece;
        this.color = color;
    }

    Piece getPiece() {
        return piece;
    }

    Boolean getColor() {
        return color;
    }

    public char printValue() {
        if (piece == null) return '.';
        final char letter = piece.getLetter();
        return color ? (char) (letter - 'a' + 'A') : letter;
    }
}
