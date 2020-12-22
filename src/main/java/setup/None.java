package setup;

import java.util.HashSet;
import java.util.Set;

public class None extends Piece {

    public None() {
        this.color = Color.EMPTY;
    }

    @Override
    public char getLetter() {
        return '.';
    }

    @Override
    public Set<Coordinates> getValidSquares(final Board board, final Coordinates coordinates) {
        return new HashSet<>();
    }

}
