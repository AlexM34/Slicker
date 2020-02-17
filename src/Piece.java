import java.util.List;

public abstract class Piece {
    abstract char getLetter();

    abstract List<Coordinates> getValidSquares(final Board board,
                                               final Coordinates coordinates, final boolean isWhite);

    boolean isValidDestination(final Square square, final boolean isWhite) {
        return square != null &&square.getCoordinates().areValid() && square.getColor() != Boolean.valueOf(isWhite);
    }
}
