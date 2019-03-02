package org.academiadecodigo.tropadelete.alpha.mc_ufc.fighter;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tropadelete.alpha.mc_ufc.Grid.GridRing;
import org.academiadecodigo.tropadelete.alpha.mc_ufc.directions.Directions;

public class RightFighter extends Fighter {

    private Picture rightFighterArm;


    private final int DAMAGE = 30;
    private int randomDamage;
    private boolean dead;
    //private Rectangle shape;
    private GridRing ring;
    private Directions directions;
    private final int FIGHTER_SIZE = 300;
    private final int ARM = 120;
    private final int JUMP = 100;
    private final int MOVEMENT = 10;

    public Picture bodyShape;

    public RightFighter(int health, int posX) {

        super(health, posX);


        bodyShape = new Picture(posX, 200 + 40, "Resources/fighterRight.png");

        rightFighterArm = new Picture(getX() - 50, getY() + 60, "Resources/rightfighterglove.png");

    }


    public void punch() {

        try {
            Thread.sleep(30);
            rightFighterArm.draw();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resetPunch() {

        try {
            Thread.sleep(250);
            rightFighterArm.delete();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void draw() {
        bodyShape.draw();
    }

    public Picture getBodyShape() {
        return bodyShape;
    }

    public Picture getRightFighterArm(){
        return rightFighterArm;
    }

    public int getX(){
        return bodyShape.getX();
    }

    public int getY(){
        return bodyShape.getY();
    }
}
