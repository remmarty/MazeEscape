package units;

import java.awt.*;

public abstract class Unit {   // abstrakcyjna klasa, po ktorej dziedziczyc będzie m.in. Player
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    protected float x, y;       // dzięki float uzyskamy płynny ruch

    public Unit(float x, float y) {   // każda jednostka ma swoje położenie
        this.x = x;
        this.y = y;
    }
    public abstract void update();    // i metody aktualizacji położenia oraz rendorowania
    public abstract void render(Graphics graphics);
}
