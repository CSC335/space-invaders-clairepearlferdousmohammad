package model;
import java.util.ArrayList;


public class AlienCollection {
    private ArrayList<Alien> aliens;

    public AlienCollection() {
        this.aliens = new ArrayList<Alien>();
    }

    public void addAlien(Alien alien) {
        this.aliens.add(alien);
    }

    public void removeAlien(Alien alien) {
        this.aliens.remove(alien);
    }

    public ArrayList<Alien> getAliens() {
        return this.aliens;
    }

    public Boolean isEmpty() {
        return this.aliens.isEmpty();
    }

    public Boolean doesContain(Alien alien) {
        return this.aliens.contains(alien);
    }
}
