package nagelschreckenbergworld;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

class Car extends Actor {

    // These are values which are "too bad" for reality, but reliably produce
    // serious congestion, so that there is something to see.
    int maxSpeed = 3;
    double slowdownRate = 0.5;

    // Initial speed is 0 - We put in new cars from the left-hand side of the screen,
    // and they are initially standing. This could be the end of a bottleneck.
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
            // Peek to the right until I see a car in front of me, up to my maximum speed.
            next = next.getAdjacentLocation(getDirection());
            if (grid.isValid(next)) {
                Actor occupant = grid.get(next);
                if (occupant != null) break;
                // This place is occupied, so this is my new maximum speed.
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
            // Walk speed places to the right.
            next = next.getAdjacentLocation(getDirection());
        }
        if (grid.isValid(next)) {
            moveTo(next);
        } else {
            // Drive out of the right-hand side of the screen.
            removeSelfFromGrid();
        }
    }

}
