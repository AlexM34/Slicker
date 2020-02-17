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
}
