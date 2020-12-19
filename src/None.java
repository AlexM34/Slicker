import java.util.ArrayList;
import java.util.List;

public class None extends Piece {

    @Override
    char getLetter() {
        return '.';
    }

    @Override
    List<Coordinates> getValidSquares(final Board board, final Coordinates coordinates, final Color color) {
        return new ArrayList<>();
    }

}
