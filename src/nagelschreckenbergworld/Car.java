package nagelschreckenbergworld;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

class Car extends Actor {

    int maxSpeed = 3;
    double slowdownRate = 0.5;

    int speed = 0;

    public Car() {
        setDirection(Location.EAST);
    }

    public void act() {
        accelerateIfPossible();
        breakIfNecessary();
        idleRandomly();
        drive();
    }

    void accelerateIfPossible() {
        if (speed < maxSpeed) {
            speed++;
        }
    }

    void breakIfNecessary() {
        Grid<Actor> grid = getGrid();
        Location next = getLocation();
        int i = 0;
        while (i < speed) {
            next = next.getAdjacentLocation(getDirection());
            if (grid.isValid(next)) {
                Actor occupant = grid.get(next);
                if (occupant != null) break;
            }
            i++;
        }
        speed = i;
    }

    void idleRandomly() {
        if (speed > 0 && Math.random() < slowdownRate) {
            speed--;
        }
    }

    void drive() {
        Grid<Actor> grid = getGrid();
        Location next = getLocation();
        for (int j = 0; j < speed; j++) {
            next = next.getAdjacentLocation(getDirection());
        }
        if (grid.isValid(next)) {
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
    }

}
