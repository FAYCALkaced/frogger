package environment;

import gameCommons.Game;
import util.Case;

import java.util.ArrayList;

public class Lane {
    private Game game;
    private ArrayList<Car> cars;

    private int ord;
    private int speed;
    private boolean leftToRight;
    private double density;
    private int tempo;

    public Lane(Game game, int laneOrd) {
        this.game = game;
        this.ord = laneOrd;
        // Initialisation avec une vitesse aléatoire entre 1 et game.minSpeedInTimerLoops
        this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops - 1) + 1;
        this.cars = new ArrayList();
        // Initialisation avec un sens aléatoire des cars
        this.leftToRight = game.randomGen.nextBoolean();
        // density = 0 pour la premiere et derniere ligne
        if (laneOrd == 0 || game.height - 1 == laneOrd) {
            this.density = 0;
        } else {
            this.density = game.defaultDensity;
        }
        this.addCars();
    }

    private void addCars() {
        // Faire bouger les cars pour pouvoir ajouter de nouvelles cars
        for (int i = 0; i < game.height; ++i) {
            this.mayAddCar();
            this.moveCars();
        }
    }

    private void moveCars() {
        for (Car car : this.cars) {
            car.move();
        }
    }

    private void addCarsToGraphics() {
        for (Car car : this.cars) {
            car.addToGraphics();
        }
    }

    public void update() {
        if (this.tempo > this.speed) {
            this.moveCars();
            this.mayAddCar();
            this.addCarsToGraphics();
            this.tempo = 0;
        } else {
            addCarsToGraphics();
        }
    }

    boolean isSafe(Case position) {
        for (Car car : this.cars) {
            if (!car.isSafe(position)) {
                return false;
            }
        }
        return true;
    }

    // TODO : ajout de methodes

    /*
     * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase()
     */

    /**
     * Ajoute une voiture au d?but de la voie avec probabilit? ?gale ? la
     * densit?, si la premi?re case de la voie est vide
     */
    private void mayAddCar() {
        if (this.isSafe(getFirstCase()) && this.isSafe(getBeforeFirstCase())) {
            if (game.randomGen.nextDouble() < density) {
                cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
            }
        }
    }

    private Case getFirstCase() {
        if (leftToRight) {
            return new Case(0, ord);
        } else
            return new Case(game.width - 1, ord);
    }

    private Case getBeforeFirstCase() {
        if (leftToRight) {
            return new Case(-1, ord);
        } else
            return new Case(game.width, ord);
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
}
