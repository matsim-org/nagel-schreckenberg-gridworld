package nagelschreckenbergworld;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

class CarSource extends Actor {

    ActorWorld world;

    CarSource(ActorWorld world) {
        this.world = world;
        setDirection(Location.EAST);
    }

    @Override
    public void act() {
        insertCarIfPossible();
        measure();
    }

    void insertCarIfPossible() {
        Location loc = getLocation().getAdjacentLocation(getDirection());
        if (getGrid().get(loc) == null) {
            Car car = new Car();
            car.putSelfInGrid(getGrid(), loc);
        }
    }

    void measure() {
        double rho = calcRho();
        world.setMessage(String.format("rho: %5.2f", rho));
    }

    double calcRho() {
        double nVehicles = 0.0;
        for (Location location : getGrid().getOccupiedLocations()) {
            if (getGrid().get(location) instanceof Car) {
                nVehicles++;
            }
        }
        return nVehicles / (getGrid().getNumCols() - 1);
    }

}
