package setup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

class NoneTest {

    @Test
    void getLetter() {
        assertEquals('.', new None().getLetter());
    }

    @Test
    void getValidSquares() {
        assertEquals(new HashSet<>(), new None().getValidSquares(new Board(), new Coordinates("e4")));
    }

}
