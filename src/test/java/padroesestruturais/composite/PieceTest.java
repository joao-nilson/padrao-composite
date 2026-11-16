package padroesestruturais.composite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    @Test
    void deveRetornarPosicaoInicial() {
        Piece pawn = new Piece(PieceType.PAWN, Color.WHITE, "e2");
        assertEquals("e2", pawn.getPosition());
    }

    @Test
    void deveMoverPeaoBrancoParaFrente() {
        Piece pawn = new Piece(PieceType.PAWN, Color.WHITE, "e2");
        pawn.move(Direction.FORWARD);
        assertEquals("e3", pawn.getPosition());
    }

    @Test
    void deveMoverPeaoPretoParaFrente() {
        Piece pawn = new Piece(PieceType.PAWN, Color.BLACK, "e7");
        pawn.move(Direction.FORWARD);
        assertEquals("e6", pawn.getPosition());
    }

    @Test
    void deveMoverParaTrasEParaOsLados() {
        Piece rook = new Piece(PieceType.ROOK, Color.WHITE, "d4");
        rook.move(Direction.BACKWARD);
        assertEquals("d3", rook.getPosition());
        rook.move(Direction.LEFT);
        assertEquals("c3", rook.getPosition());
        rook.move(Direction.RIGHT);
        rook.move(Direction.RIGHT);
        assertEquals("e3", rook.getPosition());
    }

    @Test
    void deveLancarExcecaoAoSairDoTabuleiro() {
        Piece pawn = new Piece(PieceType.PAWN, Color.WHITE, "a8");
        assertThrows(IllegalStateException.class, () -> pawn.move(Direction.FORWARD));
        assertThrows(IllegalStateException.class, () -> pawn.move(Direction.LEFT));
        assertEquals("a8", pawn.getPosition());
    }

    @Test
    void deveLancarExcecaoParaCasaInvalida() {
        assertThrows(IllegalArgumentException.class, () -> new Piece(PieceType.KING, Color.WHITE, "i9"));
        assertThrows(IllegalArgumentException.class, () -> new Piece(PieceType.KING, Color.WHITE, null));
    }

    @Test
    void deveRetornarValorDasPecas() {
        assertEquals(1, new Piece(PieceType.PAWN, Color.WHITE, "a2").getValue());
        assertEquals(3, new Piece(PieceType.KNIGHT, Color.WHITE, "b1").getValue());
        assertEquals(3, new Piece(PieceType.BISHOP, Color.WHITE, "c1").getValue());
        assertEquals(5, new Piece(PieceType.ROOK, Color.WHITE, "a1").getValue());
        assertEquals(9, new Piece(PieceType.QUEEN, Color.WHITE, "d1").getValue());
        assertEquals(0, new Piece(PieceType.KING, Color.WHITE, "e1").getValue());
    }

    @Test
    void deveDesenharMaiusculaParaBrancasEMinusculaParaPretas() {
        assertEquals("N", new Piece(PieceType.KNIGHT, Color.WHITE, "g1").draw());
        assertEquals("n", new Piece(PieceType.KNIGHT, Color.BLACK, "g8").draw());
    }
}
