package org.academiadecodigo.tropadelete.alpha.mc_ufc;

import org.academiadecodigo.tropadelete.alpha.mc_ufc.Grid.GridRing;
import org.academiadecodigo.tropadelete.alpha.mc_ufc.Utils.HealthBar;
import org.academiadecodigo.tropadelete.alpha.mc_ufc.directions.Directions;
import org.academiadecodigo.tropadelete.alpha.mc_ufc.directions.DirectionsRightFighter;
import org.academiadecodigo.tropadelete.alpha.mc_ufc.fighter.Fighter;
import org.academiadecodigo.tropadelete.alpha.mc_ufc.fighter.LeftFighter;
import org.academiadecodigo.tropadelete.alpha.mc_ufc.fighter.RightFighter;


public class Game {

    private GridRing ring;

    private Directions directions;
    private DirectionsRightFighter directionsRightFighter;

    private final int JUMP = 100;
    private final int MOVEMENT = 10;

    private Fighter leftFighter;
    private Fighter rightFighter;

    private CollisionDetector collisionDetector;
    private HealthBar leftHealthBar;
    private HealthBar rightHealthBar;

    private boolean gameEnd;

    public Game() {

        //GRID
        ring = new GridRing(120, 60);
        ring.init();

        leftFighter = new LeftFighter(100, 30);
        ((LeftFighter) leftFighter).draw();

        rightFighter = new RightFighter(100, 800);
        ((RightFighter) rightFighter).draw();


        this.directionsRightFighter = DirectionsRightFighter.NODIRECTION;
        this.directions = Directions.NODIRECTION;

        collisionDetector = new CollisionDetector(leftFighter, rightFighter);

        leftHealthBar = new HealthBar(leftFighter.getHealth(), 30, 30);
        leftHealthBar.show();

        rightHealthBar = new HealthBar(rightFighter.getHealth(), 1090, 30);
        rightHealthBar.show();

        gameEnd = false;
    }

    public void move() {

        switch (directions) {

            case RIGHT:
                if (collisionDetector.isUnsafe()) {
                    if (rightFighter.getX() >= ring.getWidth() - 310) { // BLOCKS THE PLAYER FROM GETTING OFF
                        return;
                    }
                    ((RightFighter) rightFighter).getBodyShape().translate(MOVEMENT, 0);
                    ((RightFighter) rightFighter).getRightFighterArm().translate(MOVEMENT, 0);
                }
                ((LeftFighter) leftFighter).getBodyShape().translate(MOVEMENT, 0);
                ((LeftFighter) leftFighter).getLeftFighterArm().translate(MOVEMENT, 0);
                break;

            case LEFT:
                if (leftFighter.getX() <= ring.PADDING) { // BLOCKS THE PLAYER FROM GETTING OF
                    return;
                }
                ((LeftFighter) leftFighter).getBodyShape().translate(-MOVEMENT, 0);
                ((LeftFighter) leftFighter).getLeftFighterArm().translate(-MOVEMENT, 0);
                break;

            case UP:
                ((LeftFighter) leftFighter).getBodyShape().translate(0, -JUMP);
                ((LeftFighter) leftFighter).getLeftFighterArm().translate(0, -JUMP);
                try {
                    Thread.sleep(JUMP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ((LeftFighter) leftFighter).getBodyShape().translate(0, JUMP);
                ((LeftFighter) leftFighter).getLeftFighterArm().translate(0, JUMP);
        }

        directions = Directions.NODIRECTION;
    }

    public void punch() {
        ((LeftFighter) leftFighter).punch();


        if (rightFighter.getX() + 80 >= ring.getWidth() - 310) { // BLOCKS THE PLAYER FROM GETTING OFF

            if (collisionDetector.rightPunch()) {
                leftFighter.hit(rightFighter);
                System.out.println("Right Fighter health is: " + rightFighter.getHealth());
            }

            ((RightFighter) rightFighter).getBodyShape().translate(0, 0);
            ((RightFighter) rightFighter).getRightFighterArm().translate(0, 0);
            return;
        }
        if (collisionDetector.leftPunch()) {  // MUDEI AQUI
            leftFighter.hit(rightFighter);
            System.out.println("Right Fighter health is: " + rightFighter.getHealth());
            ((RightFighter) rightFighter).getBodyShape().translate(80, 0);
            ((RightFighter) rightFighter).getRightFighterArm().translate(80, 0);
        }
        rightHealthBar.delete();
        rightHealthBar = new HealthBar(rightFighter.getHealth(), 1090, 30);
        rightHealthBar.show();
    }

    public void resetPunch() {
        ((LeftFighter) leftFighter).resetPunch();
    }


    public void setDirections(Directions direction) {

        this.directions = direction;
    }

    public void move2() {

        switch (directionsRightFighter) {

            case RIGHT:
                if (rightFighter.getX() >= ring.getWidth() - 310) { // BLOCKS THE PLAYER FROM GETTING OFF
                    return;
                }
                ((RightFighter) rightFighter).getBodyShape().translate(MOVEMENT, 0);
                ((RightFighter) rightFighter).getRightFighterArm().translate(MOVEMENT, 0);
                break;

            case LEFT:
                if (collisionDetector.isUnsafe()) {
                    if (leftFighter.getX() <= ring.PADDING) { // BLOCKS THE PLAYER FROM GETTING OFF
                        return;
                    }
                    ((LeftFighter) leftFighter).getBodyShape().translate(-MOVEMENT, 0);
                    ((LeftFighter) leftFighter).getLeftFighterArm().translate(-MOVEMENT, 0);
                }
                ((RightFighter) rightFighter).getBodyShape().translate(-MOVEMENT, 0);
                ((RightFighter) rightFighter).getRightFighterArm().translate(-MOVEMENT, 0);
                break;

            case UP:
                ((RightFighter) rightFighter).getBodyShape().translate(0, -JUMP);
                ((RightFighter) rightFighter).getRightFighterArm().translate(0, -JUMP);
                try {
                    Thread.sleep(JUMP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ((RightFighter) rightFighter).getBodyShape().translate(0, JUMP);
                ((RightFighter) rightFighter).getRightFighterArm().translate(0, JUMP);
        }

        directionsRightFighter = DirectionsRightFighter.NODIRECTION;

    }

    public void punch2() {

        ((RightFighter) rightFighter).punch();


        if (leftFighter.getX() - 20 <= ring.PADDING + 30) { // BLOCKS THE PLAYER FROM GETTING OFF

            if (collisionDetector.rightPunch()) {
                rightFighter.hit(leftFighter);
                System.out.println("Left Fighter health is: " + leftFighter.getHealth());
            }

            ((LeftFighter) leftFighter).getBodyShape().translate(0, 0);
            ((LeftFighter) leftFighter).getLeftFighterArm().translate(0, 0);
            return;
        }
        if (collisionDetector.rightPunch()) {  // MUDEI AQUI
            rightFighter.hit(leftFighter);


            System.out.println("Left Fighter health is: " + leftFighter.getHealth());
            ((LeftFighter) leftFighter).getBodyShape().translate(-80, 0);
            ((LeftFighter) leftFighter).getLeftFighterArm().translate(-80, 0);
        }
        leftHealthBar.delete();
        leftHealthBar = new HealthBar(leftFighter.getHealth(), 30, 30);
        leftHealthBar.show();
    }

    public void resetPunch2() {

        ((RightFighter) rightFighter).resetPunch();
    }

    public void setDirectionsRightFighter(DirectionsRightFighter directionRightFighter) {
        this.directionsRightFighter = directionRightFighter;
    }

    public boolean isRightFighterDead() {
        return rightFighter.getHealth() <= 0;
    }

    public boolean isLeftFighterDead() {
        return leftFighter.getHealth() <= 0;
    }

    public void endGame() {
        ((RightFighter) rightFighter).getBodyShape().delete();
        ((LeftFighter) leftFighter).getBodyShape().delete();
        ((RightFighter) rightFighter).getRightFighterArm().delete();
        ((LeftFighter) leftFighter).getLeftFighterArm().delete();
        leftHealthBar.delete();
        rightHealthBar.delete();

        gameEnd = true;
    }

    public boolean isGameEnd() {
        return gameEnd;
    }

}
