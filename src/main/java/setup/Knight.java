package setup;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(final Color color) {
        this.color = color;
    }

    @Override
    public char getLetter() {
        return color.isWhite() ? 'N' : 'n';
    }

    @Override
    public List<Coordinates> getValidSquares(final Board board, final Coordinates coordinates) {
        final List<Coordinates> moves = new ArrayList<>();
        for (int x0 = -2; x0 <= 2; x0++) {
            if (x0 == 0) continue;

            final Square goUp = board.getShiftedSquare(coordinates, x0, 3 - Math.abs(x0));
            if (isValidDestination(goUp, color)) moves.add(goUp.getCoordinates());

            final Square goDown = board.getShiftedSquare(coordinates, x0, Math.abs(x0) - 3);
            if (isValidDestination(goDown, color)) moves.add(goDown.getCoordinates());
        }

        return moves;
    }
}
