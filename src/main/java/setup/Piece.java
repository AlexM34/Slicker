package setup;

import java.util.List;

public abstract class Piece {

    protected Color color;

    protected Color getColor() {
        return color;
    }

    public abstract char getLetter();

    abstract List<Coordinates> getValidSquares(final Board board, final Coordinates coordinates);

    boolean isValidDestination(final Square square, final Color color) {
        return square.getCoordinates().areValid() && !color.equals(square.getColor());
    }
}
