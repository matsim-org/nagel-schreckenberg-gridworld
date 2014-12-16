package nagelschreckenbergworld;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

public class CarRunner {

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        int nLanes = 1;
        int nPlaces = 200;
        world.setGrid(new BoundedGrid<Actor>(nLanes, nPlaces));
        for (int j = 0; j < nLanes; j++) {
            world.add(new Location(j,0), new CarSource(world));
        }
        world.show();
    }

}
