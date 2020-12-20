package setup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static setup.Color.BLACK;
import static setup.Color.WHITE;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class KnightTest {

    private final Board board = new Board();
    private final Piece whiteKnight = new Knight(WHITE);
    private final Piece blackKnight = new Knight(BLACK);

    @Test
    void getLetterWhite() {
        assertEquals('N', whiteKnight.getLetter());
    }

    @Test
    void getLetterBlack() {
        assertEquals('n', blackKnight.getLetter());
    }

    @Test
    void canMoveStartingPosition() {
        final List<Coordinates> validSquares = whiteKnight.getValidSquares(board, new Coordinates("b1"));
        final List<Coordinates> expectedSquares = transform("a3", "c3");
        assertEquals(expectedSquares, validSquares);
    }

    @Test
    void canMoveCenter() {
        board.play("g8e4");

        final List<Coordinates> validSquares = blackKnight.getValidSquares(board, new Coordinates("e4"));
        final List<Coordinates> expectedSquares = transform("c5", "c3", "d6", "d2", "f6", "f2", "g5", "g3");
        assertEquals(expectedSquares, validSquares);
    }

    @Test
    void cannotMove() {
        board.play("a1b3");
        board.play("b1a1");

        final List<Coordinates> validSquares = whiteKnight.getValidSquares(board, new Coordinates("a1"));
        assertEquals(new ArrayList<>(), validSquares);
    }

    private List<Coordinates> transform(final String... squares) {
        return Arrays.stream(squares).map(Coordinates::new).collect(Collectors.toList());
    }

}
