package setup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static setup.Color.BLACK;
import static setup.Color.WHITE;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class RookTest {

    private final Board board = new Board();
    private final Piece whiteRook = new Rook(WHITE);
    private final Piece blackRook = new Rook(BLACK);

    @Test
    void getLetterWhite() {
        assertEquals('R', whiteRook.getLetter());
    }

    @Test
    void getLetterBlack() {
        assertEquals('r', blackRook.getLetter());
    }

    @Test
    void canMove() {
        board.play("a8c5");

        final Set<Coordinates> validSquares = blackRook.getValidSquares(board, new Coordinates("c5"));
        final Set<Coordinates> expectedSquares = transform("b5", "a5", "c4", "c3", "c2",
                "d5", "e5", "f5", "g5", "h5", "c6");
        assertEquals(expectedSquares, validSquares);
    }

    @Test
    void cannotMove() {
        final Set<Coordinates> validSquares = whiteRook.getValidSquares(board, new Coordinates("h1"));
        assertEquals(new HashSet<>(), validSquares);
    }

    private Set<Coordinates> transform(final String... squares) {
        return Arrays.stream(squares).map(Coordinates::new).collect(Collectors.toSet());
    }

}