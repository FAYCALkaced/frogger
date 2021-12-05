package environmentInfini;

import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Case;

import java.util.ArrayList;

public class EnvInf implements IEnvironment {
    private final Game game;
    private ArrayList<LaneInf> lanes;

    public EnvInf(Game game) {
        this.game = game;
        this.lanes = this.addLanes();
    }

    // Methode prive pour la construction des lignes
    private ArrayList<LaneInf> addLanes() {
        ArrayList<LaneInf> lanes = new ArrayList();
        for (int i = 0; i < game.height; i++) {
            LaneInf lane = new LaneInf(game, i);
            lanes.add(lane);
        }
        return lanes;
    }

    @Override
    public boolean isSafe(Case frogPosition) {
        LaneInf currentLane = this.lanes.get(frogPosition.ord);
        return currentLane.isSafe(frogPosition);
    }

    @Override
    public boolean isWinningPosition(Case c) {
        return false;
    }

    @Override
    public void update() {
        for (LaneInf lane: this.lanes) {
            lane.setTempo(lane.getTempo()+1);
            lane.update(this.game.getFrog().getScore());
        }
    }

    @Override
    public void addNewLane() {
        int tailleLanes = this.lanes.size();
        LaneInf lane = new LaneInf(this.game, tailleLanes);
        this.lanes.add(lane);
    }
}
