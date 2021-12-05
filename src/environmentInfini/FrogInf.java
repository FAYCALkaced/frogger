package environmentInfini;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class FrogInf implements IFrog {

    private Case position;
    private Game game;
    private int score;

    // Initialisation des attributs
    public FrogInf(Case position, Game game) {
        this.position = position;
        this.game = game;
        this.score = 0;
    }

    @Override
    public Case getPosition() {
        return position;
    }

    @Override
    public Direction getDirection() {
        return null;
    }

    // Implementation de la methode move pour definir les strategis de deplacement du frog
    public void move(Direction newDirection) {
        // Strategie de positionnement en cas de deplacement
        if (newDirection == Direction.right && this.position.absc + 1 < this.game.width) {
            this.position = new Case(this.position.absc + 1, this.position.ord);
        } else if (newDirection == Direction.left && this.position.absc > 0) {
            this.position = new Case(this.position.absc - 1, this.position.ord);
        } else if (newDirection == Direction.up) {
            this.position = new Case(this.position.absc, this.position.ord + 1);
            score++;
            this.game.addNewLane();
        } else if (newDirection == Direction.down && this.position.ord > 1) {
            this.position = new Case(this.position.absc, this.position.ord - 1);
            score--;
        }
    }

    @Override
    public int getScore() {
        return score;
    }
}
