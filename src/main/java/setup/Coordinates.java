package setup;

import java.util.Objects;

public class Coordinates {

    private final int x;
    private final int y;

    public Coordinates(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(final String square) {
        this.x = square.charAt(0) - 'a';
        this.y = square.charAt(1) - '1';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    boolean isValid() {
        return isValid(x, y);
    }

    boolean canShift(final int xShift, final int yShift) {
        final int x0 = x + xShift;
        final int y0 = y + yShift;

        return isValid(x0, y0);
    }

    private boolean isValid(final int x, final int y) {
        return 0 <= x && x < 8 && 0 <= y && y < 8;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        final Coordinates that = (Coordinates) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return ((char) (x + 'a')) + "" + ((char) (y + '1'));
    }

}
