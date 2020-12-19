public class Square {

    private final Coordinates coordinates;
    private Piece piece;
    private Color color;

    public Square() {
        this.coordinates = new Coordinates(-1, -1);
        this.piece = new None();
        this.color = Color.EMPTY;
    }

    public Square(final Coordinates coordinates, final Piece piece, final Color color) {
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

    Color getColor() {
        return color;
    }

    boolean isEmpty() {
        return color.isEmpty();
    }

    void makeEmpty() {
        piece = new None();
        color = Color.EMPTY;
    }

    void movePiece(final Square square) {
        this.piece = square.getPiece();
        this.color = square.getColor();
    }

    public char printValue() {
        final char letter = piece.getLetter();
        return color.isWhite() ? (char) (letter - 'a' + 'A') : letter;
    }
}
