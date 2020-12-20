package setup;

import java.util.ArrayList;
import java.util.List;

public class None extends Piece {

    public None(final Color color) {
        this.color = color;
    }

    @Override
    public char getLetter() {
        return '.';
    }

    @Override
    public List<Coordinates> getValidSquares(final Board board, final Coordinates coordinates) {
        return new ArrayList<>();
    }

}
