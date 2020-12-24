package setup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class CoordinatesTest {

    @Test
    void transformation() {
        final Coordinates coordinates = new Coordinates("e4");
        assertEquals(4, coordinates.getX());
        assertEquals(3, coordinates.getY());
    }

    @Test
    void isValid() {
        assertTrue(new Coordinates("h8").isValid());
    }

    @Test
    void isInvalid() {
        assertFalse(new Coordinates("A6").isValid());
        assertFalse(new Coordinates("i4").isValid());
        assertFalse(new Coordinates("c9").isValid());
        assertFalse(new Coordinates("g0").isValid());
    }

    @Test
    void canShift() {
        final Coordinates coordinates = new Coordinates("f5");
        assertTrue(coordinates.canShift(-5, 0));
        assertTrue(coordinates.canShift(1, -4));
        assertTrue(coordinates.canShift(0, 0));
        assertTrue(coordinates.canShift(2, -1));
        assertTrue(coordinates.canShift(0, 3));
    }

    @Test
    void cannotShift() {
        final Coordinates coordinates = new Coordinates("b3");
        assertFalse(coordinates.canShift(-2, 0));
        assertFalse(coordinates.canShift(2, -3));
        assertFalse(coordinates.canShift(7, -1));
        assertFalse(coordinates.canShift(0, 6));
    }


    @Test
    void equalsVerifier() {
        EqualsVerifier.forClass(Coordinates.class).verify();
    }

}