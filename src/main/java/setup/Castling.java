package setup;

import java.util.List;

public class Castling {

    private static final int MAX_VALUE = 1000;

    private int whiteShortCastle;
    private int whiteLongCastle;
    private int blackShortCastle;
    private int blackLongCastle;

    public Castling() {
        whiteShortCastle = MAX_VALUE;
        whiteLongCastle = MAX_VALUE;
        blackShortCastle = MAX_VALUE;
        blackLongCastle = MAX_VALUE;
    }

    public void movePlayed(final List<Coordinates> move, final int moveCount) {
        if (isSquareUsed(move, "e1")) {
            whiteShortCastle = Math.min(whiteShortCastle, moveCount);
            whiteLongCastle = Math.min(whiteLongCastle, moveCount);

        } else if (isSquareUsed(move, "a1")) {
            whiteLongCastle = Math.min(whiteLongCastle, moveCount);

        } else if (isSquareUsed(move, "h1")) {
            whiteShortCastle = Math.min(whiteShortCastle, moveCount);
        }

        if (isSquareUsed(move, "e8")) {
            blackShortCastle = Math.min(blackShortCastle, moveCount);
            blackLongCastle = Math.min(blackLongCastle, moveCount);

        } else if (isSquareUsed(move, "a8")) {
            blackLongCastle = Math.min(blackLongCastle, moveCount);

        } else if (isSquareUsed(move, "h8")) {
            blackShortCastle = Math.min(blackShortCastle, moveCount);
        }
    }

    public void moveUndone(final int moveCount) {
        if (whiteShortCastle == moveCount) whiteShortCastle = MAX_VALUE;
        if (whiteLongCastle == moveCount) whiteLongCastle = MAX_VALUE;
        if (blackShortCastle == moveCount) blackShortCastle = MAX_VALUE;
        if (blackLongCastle == moveCount) blackLongCastle = MAX_VALUE;
    }

    private boolean isSquareUsed(final List<Coordinates> move, final String square) {
        return move.stream().anyMatch(m -> square.equals(m.toString()));
    }

    public Integer getWhiteShortCastle() {
        return whiteShortCastle;
    }

    public Integer getWhiteLongCastle() {
        return whiteLongCastle;
    }

    public Integer getBlackShortCastle() {
        return blackShortCastle;
    }

    public Integer getBlackLongCastle() {
        return blackLongCastle;
    }

}
