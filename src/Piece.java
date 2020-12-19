import java.util.List;

public abstract class Piece {

    abstract char getLetter();

    abstract List<Coordinates> getValidSquares(final Board board,
                                               final Coordinates coordinates, final Color color);

    boolean isValidDestination(final Square square, final Color color) {
        return square != null && square.getCoordinates().areValid() && square.getColor() != color;
    }
}
