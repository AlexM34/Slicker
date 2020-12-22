package setup;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Piece {

    protected Color color;

    protected Color getColor() {
        return color;
    }

    public abstract char getLetter();

    abstract Set<Coordinates> getValidSquares(final Board board, final Coordinates coordinates);

    Set<Coordinates> getSquares(final Board board, final Coordinates coordinates,
                                final int x, final int y) {
        final Set<Coordinates> squares = new HashSet<>();
        for (int x1 = x, y1 = y; x1 != 8 * x || y1 != 8 * y; x1 += x, y1 += y) {
            final Square destination = board.getShiftedSquare(coordinates, x1, y1);
            if (isValidDestination(destination)) squares.add(destination.getCoordinates());

            if (isUnoccupied(destination)) break;
        }

        return squares;
    }

    boolean isValidDestination(final Square square) {
        return square.getCoordinates().areValid() && !color.equals(square.getColor());
    }

    private boolean isUnoccupied(final Square destination) {
        return !destination.getCoordinates().areValid() || !destination.getColor().isEmpty();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

}
