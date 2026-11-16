package padroesestruturais.composite;

import java.util.ArrayList;
import java.util.List;

public class Formation implements BoardElement {

    private final String name;
    private final List<BoardElement> elements = new ArrayList<>();

    public Formation(String name) {
        this.name = name;
    }

    public void add(BoardElement element) {
        elements.add(element);
    }

    public void remove(BoardElement element) {
        elements.remove(element);
    }

    public String getName() {
        return name;
    }

    @Override
    public void move(Direction direction) {
        for (BoardElement element : elements) {
            element.move(direction);
        }
    }

    @Override
    public String getPosition() {
        List<String> positions = new ArrayList<>();
        for (BoardElement element : elements) {
            positions.add(element.getPosition());
        }
        return name + "[" + String.join(", ", positions) + "]";
    }

    @Override
    public int getValue() {
        int total = 0;
        for (BoardElement element : elements) {
            total += element.getValue();
        }
        return total;
    }

    @Override
    public String draw() {
        List<String> drawings = new ArrayList<>();
        for (BoardElement element : elements) {
            drawings.add(element.draw());
        }
        return name + ": " + String.join(" ", drawings);
    }
}
