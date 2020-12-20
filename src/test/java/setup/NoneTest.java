package setup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static setup.Color.EMPTY;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class NoneTest {

    @Test
    void getLetter() {
        assertEquals('.', new None(EMPTY).getLetter());
    }

    @Test
    void getValidSquares() {
        assertEquals(new ArrayList<>(), new None(EMPTY).getValidSquares(new Board(), new Coordinates("e4")));
    }

}
