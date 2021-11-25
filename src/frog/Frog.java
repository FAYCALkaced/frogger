package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class Frog implements IFrog {

    private Case position;
    private Game game;

    // Initialisation des attributs
    public Frog(Case position, Game game) {
        this.position = position;
        this.game = game;
    }

    @Override
    public Case getPosition() {
        return position;
    }

    @Override
    public Direction getDirection() {
        return null;
    }

    public void setPosition(Case position) {
        this.position = position;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    // Implementation de la methode move pour definir les strategis de deplacement du frog
    public void move(Direction newDirection) {
        // Strategie de positionnement en cas de deplacement
        if (newDirection == Direction.right && this.position.absc + 1 < this.game.width) {
            this.position = new Case(this.position.absc + 1, this.position.ord);
        } else if (newDirection == Direction.left && this.position.absc > 0) {
            this.position = new Case(this.position.absc - 1, this.position.ord);
        } else if (newDirection == Direction.up && this.position.ord + 1 < this.game.height) {
            this.position = new Case(this.position.absc, this.position.ord + 1);
        } else if (newDirection == Direction.down && this.position.ord > 0) {
            this.position = new Case(this.position.absc, this.position.ord - 1);
        }
    }
}
