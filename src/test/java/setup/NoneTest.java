package setup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static setup.Color.EMPTY;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

class NoneTest {

    @Test
    void getLetter() {
        assertEquals('.', new None(EMPTY).getLetter());
    }

    @Test
    void getValidSquares() {
        assertEquals(new HashSet<>(), new None(EMPTY).getValidSquares(new Board(), new Coordinates("e4")));
    }

}
