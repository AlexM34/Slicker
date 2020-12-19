public class Square {

    private final Coordinates coordinates;
    private Piece piece;

    public Square() {
        this.coordinates = new Coordinates(-1, -1);
        this.piece = new None(Color.EMPTY);
    }

    public Square(final Coordinates coordinates, final Piece piece) {
        this.coordinates = coordinates;
        this.piece = piece;
    }

    Coordinates getCoordinates() {
        return coordinates;
    }

    Piece getPiece() {
        return piece;
    }

    Color getColor() {
        return piece.getColor();
    }

    boolean isEmpty() {
        return getColor().isEmpty();
    }

    void makeEmpty() {
        piece = new None(Color.EMPTY);
    }

    void movePiece(final Square square) {
        this.piece = square.getPiece();
    }

}
