package setup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static setup.Color.BLACK;
import static setup.Color.WHITE;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class BishopTest {

    private final Board board = new Board();
    private final Piece whiteBishop = new Bishop(WHITE);
    private final Piece blackBishop = new Bishop(BLACK);

    @Test
    void getLetterWhite() {
        assertEquals('B', whiteBishop.getLetter());
    }

    @Test
    void getLetterBlack() {
        assertEquals('b', blackBishop.getLetter());
    }

    @Test
    void canMove() {
        board.play("c8e4");

        final List<Coordinates> validSquares = blackBishop.getValidSquares(board, new Coordinates("e4"));
        final List<Coordinates> expectedSquares = transform("d3", "c2", "d5", "c6", "f5", "g6", "f3", "g2");
        assertEquals(expectedSquares, validSquares);
    }

    @Test
    void cannotMove() {
        final List<Coordinates> validSquares = whiteBishop.getValidSquares(board, new Coordinates("b1"));
        assertEquals(new ArrayList<>(), validSquares);
    }

    private List<Coordinates> transform(final String... squares) {
        return Arrays.stream(squares).map(Coordinates::new).collect(Collectors.toList());
    }

}
