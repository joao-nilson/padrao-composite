package padroesestruturais.composite;

public enum PieceType {
    PAWN('P', 1),
    KNIGHT('N', 3),
    BISHOP('B', 3),
    ROOK('R', 5),
    QUEEN('Q', 9),
    KING('K', 0);

    private final char symbol;
    private final int value;

    PieceType(char symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getValue() {
        return value;
    }
}
