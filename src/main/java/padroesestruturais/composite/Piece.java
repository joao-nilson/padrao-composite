package padroesestruturais.composite;

public class Piece implements BoardElement {

    private final PieceType type;
    private final Color color;
    private char file; // 'a'..'h'
    private int rank;  // 1..8

    public Piece(PieceType type, Color color, String square) {
        if (square == null || square.length() != 2 || !isInsideBoard(square.charAt(0), square.charAt(1) - '0')) {
            throw new IllegalArgumentException("Invalid square: " + square);
        }
        this.type = type;
        this.color = color;
        this.file = square.charAt(0);
        this.rank = square.charAt(1) - '0';
    }

    @Override
    public void move(Direction direction) {
        int forward = color == Color.WHITE ? 1 : -1;
        char newFile = file;
        int newRank = rank;

        switch (direction) {
            case FORWARD:  newRank += forward; break;
            case BACKWARD: newRank -= forward; break;
            case LEFT:     newFile--; break;
            case RIGHT:    newFile++; break;
        }

        if (!isInsideBoard(newFile, newRank)) {
            throw new IllegalStateException("Move leaves the board: " + getPosition() + " " + direction);
        }
        file = newFile;
        rank = newRank;
    }

    @Override
    public String getPosition() {
        return "" + file + rank;
    }

    @Override
    public int getValue() {
        return type.getValue();
    }

    @Override
    public String draw() {
        char symbol = type.getSymbol();
        return String.valueOf(color == Color.WHITE ? symbol : Character.toLowerCase(symbol));
    }

    public PieceType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    private static boolean isInsideBoard(char file, int rank) {
        return file >= 'a' && file <= 'h' && rank >= 1 && rank <= 8;
    }
}
