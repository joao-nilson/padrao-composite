package padroesestruturais.composite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormationTest {

    private Formation createKingsideAttack() {
        Formation attack = new Formation("Kingside attack");
        attack.add(new Piece(PieceType.KNIGHT, Color.WHITE, "f3"));
        attack.add(new Piece(PieceType.PAWN, Color.WHITE, "g2"));
        attack.add(new Piece(PieceType.PAWN, Color.WHITE, "h2"));
        return attack;
    }

    @Test
    void deveRetornarPosicaoDaFormacao() {
        assertEquals("Kingside attack[f3, g2, h2]", createKingsideAttack().getPosition());
    }

    @Test
    void deveSomarValorDasPecas() {
        assertEquals(5, createKingsideAttack().getValue()); // 3 + 1 + 1
    }

    @Test
    void deveDesenharFormacao() {
        assertEquals("Kingside attack: N P P", createKingsideAttack().draw());
    }

    @Test
    void deveMoverTodasAsPecasDaFormacao() {
        Formation attack = createKingsideAttack();
        attack.move(Direction.FORWARD);
        assertEquals("Kingside attack[f4, g3, h3]", attack.getPosition());
    }

    @Test
    void deveRetornarFormacaoVazia() {
        Formation empty = new Formation("Empty");
        assertEquals("Empty[]", empty.getPosition());
        assertEquals(0, empty.getValue());
        assertEquals("Empty: ", empty.draw());
    }

    @Test
    void deveRemoverPecaDaFormacao() {
        Formation chain = new Formation("Pawn chain");
        Piece d4 = new Piece(PieceType.PAWN, Color.WHITE, "d4");
        Piece e5 = new Piece(PieceType.PAWN, Color.WHITE, "e5");
        chain.add(d4);
        chain.add(e5);

        chain.remove(d4);

        assertEquals("Pawn chain[e5]", chain.getPosition());
        assertEquals(1, chain.getValue());
    }

    @Test
    void deveTratarPecaEFormacaoDeFormaUniforme() {
        BoardElement pawn = new Piece(PieceType.PAWN, Color.WHITE, "e2");
        BoardElement group = createKingsideAttack();

        for (BoardElement element : new BoardElement[]{pawn, group}) {
            element.move(Direction.FORWARD);
        }

        assertEquals("e3", pawn.getPosition());
        assertEquals("Kingside attack[f4, g3, h3]", group.getPosition());
    }

    @Test
    void deveSuportarFormacoesAninhadas() {
        Formation pawnChain = new Formation("Pawn chain");
        pawnChain.add(new Piece(PieceType.PAWN, Color.WHITE, "d4"));
        pawnChain.add(new Piece(PieceType.PAWN, Color.WHITE, "e5"));

        Formation rookBattery = new Formation("Rook battery");
        rookBattery.add(new Piece(PieceType.ROOK, Color.WHITE, "a1"));
        rookBattery.add(new Piece(PieceType.ROOK, Color.WHITE, "a2"));

        Formation army = new Formation("Army");
        army.add(pawnChain);
        army.add(rookBattery);
        army.add(new Piece(PieceType.KING, Color.WHITE, "e1"));

        assertEquals("Army[Pawn chain[d4, e5], Rook battery[a1, a2], e1]", army.getPosition());
        assertEquals(12, army.getValue()); // 1 + 1 + 5 + 5 + 0
        assertEquals("Army: Pawn chain: P P Rook battery: R R K", army.draw());

        army.move(Direction.RIGHT);

        assertEquals("Army[Pawn chain[e4, f5], Rook battery[b1, b2], f1]", army.getPosition());
    }

    @Test
    void deveMoverPecasPretasParaFrenteNaDirecaoCorreta() {
        Formation blackChain = new Formation("Black pawns");
        blackChain.add(new Piece(PieceType.PAWN, Color.BLACK, "d7"));
        blackChain.add(new Piece(PieceType.PAWN, Color.BLACK, "e7"));

        blackChain.move(Direction.FORWARD);

        assertEquals("Black pawns[d6, e6]", blackChain.getPosition());
        assertEquals("Black pawns: p p", blackChain.draw());
    }
}
