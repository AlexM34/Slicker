package setup;

import java.util.HashSet;
import java.util.Set;

public class None extends Piece {

    public None(final Color color) {
        this.color = color;
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
