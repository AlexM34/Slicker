package setup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static setup.Color.BLACK;
import static setup.Color.WHITE;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class PawnTest {

    private final Board board = new Board();
    private final Piece whitePawn = new Pawn(WHITE);
    private final Piece blackPawn = new Pawn(BLACK);

    @Test
    void getLetterWhite() {
        assertEquals('P', whitePawn.getLetter());
    }

    @Test
    void getLetterBlack() {
        assertEquals('p', blackPawn.getLetter());
    }

    @Test
    void canMoveStartingPosition() {
        final List<Coordinates> validSquares = whitePawn.getValidSquares(board, new Coordinates("d2"));
        final List<Coordinates> expectedSquares = transform("d3", "d4");
        assertEquals(expectedSquares, validSquares);
    }

    @Test
    void cannotMoveEnemyBlocking() {
        board.play("g2g6");

        final List<Coordinates> validSquares = blackPawn.getValidSquares(board, new Coordinates("g7"));
        assertEquals(new ArrayList<>(), validSquares);
    }

    @Test
    void cannotMoveOwnBlocking() {
        board.play("b1c3");

        final List<Coordinates> validSquares = whitePawn.getValidSquares(board, new Coordinates("c2"));
        assertEquals(new ArrayList<>(), validSquares);
    }

    @Test
    void cannotMoveDouble() {
        board.play("c7c4");

        final List<Coordinates> validSquares = whitePawn.getValidSquares(board, new Coordinates("c2"));
        final List<Coordinates> expectedSquares = transform("c3");
        assertEquals(expectedSquares, validSquares);
    }

    @Test
    void canMoveNotStartingPosition() {
        board.play("c7c6");

        final List<Coordinates> validSquares = blackPawn.getValidSquares(board, new Coordinates("c6"));
        final List<Coordinates> expectedSquares = transform("c5");
        assertEquals(expectedSquares, validSquares);
    }

    @Test
    void canCapture() {
        board.play("d7d3");
        board.play("f7f3");

        final List<Coordinates> validSquares = whitePawn.getValidSquares(board, new Coordinates("e2"));
        final List<Coordinates> expectedSquares = transform("e3", "e4", "d3", "f3");
        assertEquals(expectedSquares, validSquares);
    }

    @Test
    void cannotCaptureOwn() {
        board.play("g8f6");

        final List<Coordinates> validSquares = blackPawn.getValidSquares(board, new Coordinates("e7"));
        final List<Coordinates> expectedSquares = transform("e6", "e5");
        assertEquals(expectedSquares, validSquares);
    }

    @Disabled
    @Test
    void canEnPassant() {
        board.play("e2e5");
        board.play("d7d5");

        final List<Coordinates> validSquares = whitePawn.getValidSquares(board, new Coordinates("e5"));
        final List<Coordinates> expectedSquares = transform("d6", "e6");
        assertEquals(expectedSquares, validSquares);
    }

    @Test
    void cannotEnPassant() {
        board.play("e2e4");
        board.play("d7d4");

        final List<Coordinates> validSquares = blackPawn.getValidSquares(board, new Coordinates("d4"));
        final List<Coordinates> expectedSquares = transform("d3");
        assertEquals(expectedSquares, validSquares);
    }

    private List<Coordinates> transform(final String... squares) {
        return Arrays.stream(squares).map(Coordinates::new).collect(Collectors.toList());
    }

}
