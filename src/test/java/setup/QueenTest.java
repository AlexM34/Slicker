package setup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static setup.Color.BLACK;
import static setup.Color.WHITE;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class QueenTest {

    private final Board board = new Board();
    private final Piece whiteQueen = new Queen(WHITE);
    private final Piece blackQueen = new Queen(BLACK);

    @Test
    void getLetterWhite() {
        assertEquals('Q', whiteQueen.getLetter());
    }

    @Test
    void getLetterBlack() {
        assertEquals('q', blackQueen.getLetter());
    }

    @Test
    void canMove() {
        board.play("d8g5");

        final List<Coordinates> validSquares = blackQueen.getValidSquares(board, new Coordinates("g5"));
        final List<Coordinates> expectedSquares = transform("f5", "e5", "d5", "c5", "b5",
                "a5", "g4", "g3", "g2", "h5", "g6", "f4", "e3", "d2", "f6", "h6", "h4");
        assertEquals(expectedSquares, validSquares);
    }

    @Test
    void cannotMove() {
        final List<Coordinates> validSquares = whiteQueen.getValidSquares(board, new Coordinates("d1"));
        assertEquals(new ArrayList<>(), validSquares);
    }

    private List<Coordinates> transform(final String... squares) {
        return Arrays.stream(squares).map(Coordinates::new).collect(Collectors.toList());
    }

}