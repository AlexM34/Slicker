package setup;

public class Square {

    private final Coordinates coordinates;
    private Piece piece;

    public Square() {
        this.coordinates = new Coordinates(-1, -1);
        this.piece = new None();
    }

    public Square(final Coordinates coordinates, final Piece piece) {
        this.coordinates = coordinates;
        this.piece = piece;
    }

    Coordinates getCoordinates() {
        return coordinates;
    }

    public Piece getPiece() {
        return piece;
    }

    void setPiece(final Piece piece) {
        this.piece = piece;
    }

    public Color getColor() {
        return piece.getColor();
    }

    boolean isEmpty() {
        return getColor().isEmpty();
    }

    void makeEmpty() {
        piece = new None();
    }

}
