import java.util.Objects;

public class Coordinates {
    private final int x;
    private final int y;

    Coordinates(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    boolean areValid() {
        return 0 <= x && x < 8 && 0 <= y && y < 8;
    }

    boolean canShift(final int xShift, final int yShift) {
        final int x0 = x + xShift;
        final int y0 = y + yShift;

        return 0 <= x0 && x0 < 8 && 0 <= y0 && y0 < 8;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Coordinates that = (Coordinates) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
