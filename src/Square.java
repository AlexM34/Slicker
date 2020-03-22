public class Square {
    private final Coordinates coordinates;
    private Piece piece;
    private Boolean color;

    public Square(final Coordinates coordinates, final Piece piece, final Boolean color) {
        this.coordinates = coordinates;
        this.piece = piece;
        this.color = color;
    }

    Coordinates getCoordinates() {
        return coordinates;
    }

    Piece getPiece() {
        return piece;
    }

    Boolean getColor() {
        return color;
    }

    void makeEmpty() {
        piece = null;
        color = null;
    }

    void movePiece(final Square square) {
        this.piece = square.getPiece();
        this.color = square.getColor();
    }

    public char printValue() {
        if (piece == null) return '.';
        final char letter = piece.getLetter();
        return color ? (char) (letter - 'a' + 'A') : letter;
    }
}
