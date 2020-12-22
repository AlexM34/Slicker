package setup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static setup.Color.BLACK;
import static setup.Color.WHITE;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class KingTest {

    private final Board board = new Board();
    private final Piece whiteKing = new King(WHITE);
    private final Piece blackKing = new King(BLACK);

    @Test
    void getLetterWhite() {
        assertEquals('K', whiteKing.getLetter());
    }

    @Test
    void getLetterBlack() {
        assertEquals('k', blackKing.getLetter());
    }

    @Test
    void canMove() {
        board.play("e1d3");
        board.play("b8d4");

        final Set<Coordinates> validSquares = whiteKing.getValidSquares(board, new Coordinates("d3"));
        final Set<Coordinates> expectedSquares = transform("c3", "c4", "d4", "e3", "e4");
        assertEquals(expectedSquares, validSquares);
    }

    @Test
    void cannotMove() {
        final Set<Coordinates> validSquares = blackKing.getValidSquares(board, new Coordinates("d8"));
        assertEquals(new HashSet<>(), validSquares);
    }

    @Test
    void canCastleShort() {
        board.play("f1c4");
        board.play("g1f3");

        final Set<Coordinates> validSquares = whiteKing.getValidSquares(board, new Coordinates("e1"));
        final Set<Coordinates> expectedSquares = transform("f1", "g1");
        assertEquals(expectedSquares, validSquares);

        board.play("e1g1");
        assertEquals(new Rook(WHITE), board.getSquare("f1").getPiece());
        assertEquals(new None(), board.getSquare("h1").getPiece());
    }

    @Test
    void canCastleLong() {
        board.play("b8c6");
        board.play("c8g4");
        board.play("d8d5");
        board.play("d1a7");

        final Set<Coordinates> validSquares = blackKing.getValidSquares(board, new Coordinates("e8"));
        final Set<Coordinates> expectedSquares = transform("d8", "c8");
        assertEquals(expectedSquares, validSquares);

        board.play("e8c8");
        assertEquals(new Rook(BLACK), board.getSquare("d8").getPiece());
        assertEquals(new None(), board.getSquare("a8").getPiece());
    }

    @Test
    void canCastleBoth() {
        board.play("f1c4");
        board.play("g1f3");
        board.play("c1e3");
        board.play("b1c3");
        board.play("d1d4");

        final Set<Coordinates> validSquares = whiteKing.getValidSquares(board, new Coordinates("e1"));
        final Set<Coordinates> expectedSquares = transform("c1", "d1", "f1", "g1");
        assertEquals(expectedSquares, validSquares);

        board.play("e1c1");
        assertEquals(new Rook(WHITE), board.getSquare("d1").getPiece());
        assertEquals(new None(), board.getSquare("a1").getPiece());

        board.undo("e1c1");
        board.play("e1g1");
        assertEquals(new Rook(WHITE), board.getSquare("f1").getPiece());
        assertEquals(new None(), board.getSquare("h1").getPiece());
    }

    @Test
    void cannotCastleBlocked() {
        board.play("g8f6");

        final Set<Coordinates> validSquares = blackKing.getValidSquares(board, new Coordinates("e8"));
        assertEquals(new HashSet<>(), validSquares);
    }

    @Test
    void cannotCastleInCheck() {
        board.play("c1e3");
        board.play("b1c3");
        board.play("d1d4");
        board.play("d8f2");

        final Set<Coordinates> validSquares = whiteKing.getValidSquares(board, new Coordinates("e1"));
        final Set<Coordinates> expectedSquares = transform("d1", "f2");
        assertEquals(expectedSquares, validSquares);
    }

    @Test
    void cannotCastleMiddleSquareInCheck() {
        board.play("g8f6");
        board.play("c8b4");
        board.play("g1g6");

        final Set<Coordinates> validSquares = blackKing.getValidSquares(board, new Coordinates("e8"));
        assertEquals(new HashSet<>(), validSquares);
    }

    @Test
    void cannotCastleLastSquareInCheck() {
        board.play("c1e3");
        board.play("b1c3");
        board.play("d1d4");
        board.play("d8b2");

        final Set<Coordinates> validSquares = whiteKing.getValidSquares(board, new Coordinates("e1"));
        final Set<Coordinates> expectedSquares = transform("d1");
        assertEquals(expectedSquares, validSquares);
    }

    @Test
    void cannotCastleAfterMovingKing() {
        board.play("f1c4");
        board.play("g1f3");
        board.play("e1f1");
        board.play("f1e1");

        final Set<Coordinates> validSquares = whiteKing.getValidSquares(board, new Coordinates("e1"));
        final Set<Coordinates> expectedSquares = transform("f1");
        assertEquals(expectedSquares, validSquares);
    }

    @Test
    void cannotCastleLongAfterMovingRook() {
        board.play("a8b8");
        board.play("b8a8");
        board.play("b8c6");
        board.play("c8g4");
        board.play("d8d5");
        board.play("d1a7");

        final Set<Coordinates> validSquares = blackKing.getValidSquares(board, new Coordinates("e8"));
        final Set<Coordinates> expectedSquares = transform("d8");
        assertEquals(expectedSquares, validSquares);
    }

    private Set<Coordinates> transform(final String... squares) {
        return Arrays.stream(squares).map(Coordinates::new).collect(Collectors.toSet());
    }

}