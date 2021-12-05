package environment;

import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Case;

import java.util.ArrayList;

public class Environment implements IEnvironment {

    private final Game game;
    private ArrayList<Lane> lanes;

    public Environment(Game game) {
        this.game = game;
        this.lanes = this.addLanes();
    }

    // Methode prive pour la construction des lignes
    private ArrayList<Lane> addLanes() {
        ArrayList<Lane> lanes = new ArrayList();
        for (int i = 0; i < game.height; i++) {
            Lane lane = new Lane(game, i);
            lanes.add(lane);
        }
        return lanes;
    }

    @Override
    public boolean isSafe(Case frogPosition) {
        Lane currentLane = this.lanes.get(frogPosition.ord);
        return currentLane.isSafe(frogPosition);
    }

    @Override
    public boolean isWinningPosition(Case frogPosition) {
        if (this.game.height == frogPosition.ord + 1) {
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        for (Lane lane : this.lanes) {
            lane.setTempo(lane.getTempo() + 1);
            lane.update();
        }
    }

    @Override
    public void addNewLane() {
        // TODO
    }
}
