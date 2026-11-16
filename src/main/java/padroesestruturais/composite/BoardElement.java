package padroesestruturais.composite;

public interface BoardElement {

    void move(Direction direction);

    String getPosition();

    int getValue();

    String draw();
}
