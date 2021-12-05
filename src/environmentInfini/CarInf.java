package environmentInfini;

import gameCommons.Game;
import graphicalElements.Element;
import util.Case;

import java.awt.*;

public class CarInf {
    private Game game;
    private Case leftPosition;
    private boolean leftToRight;
    // Taille de car
    private int length;
    private final Color colorLtR = Color.BLACK;
    private final Color colorRtL = Color.GREEN;

    public CarInf(Game game, Case beforeFirstCase, boolean leftToRight) {
        this(game, leftToRight);
        // Taille de car ne dépasse est entre 1 et 3
        this.length = game.randomGen.nextInt(3) + 1;

        if(leftToRight) {
            this.leftPosition = new Case(beforeFirstCase.absc - this.length, beforeFirstCase.ord);
        } else {
            this.leftPosition = new Case(beforeFirstCase.absc, beforeFirstCase.ord);
        }
    }

    public CarInf(Game game, boolean leftToRight) {
        this.game = game;
        this.leftToRight = leftToRight;
    }

    public boolean isSafe(Case position) {
        if((this.leftPosition.absc > position.absc) || ( this.leftPosition.absc + this.length <= position.absc)) {
            return true;
        }
        return false;
    }

    public void move() {
        if (this.leftToRight && this.leftPosition.absc <= game.width) {
            this.leftPosition = new Case(this.leftPosition.absc + 1, this.leftPosition.ord);
        } else {
            this.leftPosition = new Case(this.leftPosition.absc - 1, this.leftPosition.ord);
        }
    }

    /* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
    public void addToGraphics(int linePosition) {
        for (int i = 0; i < this.length; i++) {
            Color color = this.leftToRight ? this.colorLtR : this.colorRtL;
            game.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord - linePosition, color));
        }
    }
}
