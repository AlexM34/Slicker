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

    private Set<Coordinates> transform(final String... squares) {
        return Arrays.stream(squares).map(Coordinates::new).collect(Collectors.toSet());
    }

}