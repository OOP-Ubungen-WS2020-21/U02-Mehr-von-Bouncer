import de.ur.mi.bouncer.apps.BouncerApp;
import de.ur.mi.bouncer.apps.BouncerLauncher;
import de.ur.mi.bouncer.world.fields.FieldColor;


public class DieMauer extends BouncerApp {

    /**
     * Bouncer repairs the broken tiles in the wall with the included pile of bricks
     * in the left side of the world.
     */
    @Override
    public void bounce() {
        loadMap("Wall");
        moveToWall();
        checkWall();
    }

    /**
     * Bouncer moves to the wall.
     * Pre-condition: Bouncer is at the western bottom edge of the map, Bouncer faces east.
     * Post-condition: Bouncer is at the first tile on the bottom of the wall, Bouncer faces east.
     */
    private void moveToWall() {
        while (!bouncer.isOnFieldWithColor(FieldColor.GREEN)) {
            bouncer.move();
        }
    }

    /**
     * Bouncer starts with his search for broken tiles.
     */
    private void checkWall() {
        while (bouncer.isOnFieldWithColor(FieldColor.GREEN)) {
            repairWall();
        }
    }

    /**
     * Bouncer checks each block in every row and switch than to the next row.
     */
    private void repairWall() {
        while (bouncer.canMoveForward()) {
            checkBlock();
        }
        moveToNextLane();
    }

    /**
     * Bouncer moves from one tile row to the next.
     */
    private void moveToNextLane() {
        turnAround();
        while (bouncer.isOnFieldWithColor(FieldColor.GREEN)) {
            bouncer.move();
        }
        turnRight();
        bouncer.move();
        turnRight();
        bouncer.move();
    }

    /**
     * Bouncer checks if the current block is broken.
     * If that is the case he repairs it, if not he continues moving.
     */
    private void checkBlock() {
        if (bouncer.isOnFieldWithColor(FieldColor.RED)) {
            repairBlock();
        } else {
            bouncer.move();
        }
    }

    /**
     * Bouncer repairs a block, by fetching a new one and replacing the old .
     */
    private void repairBlock() {
        getNewBlock();
        returnToWall();
        bouncer.paintField(FieldColor.GREEN);
    }

    /**
     * Bouncer fetch a new block from the pile.
     */
    private void getNewBlock() {
        returnToPile();
        getNextBrick();
    }

    /**
     * Bouncer moves to the pile of blocks and marks his way blue.
     * Pre-condition: Bouncer is on a broken block.
     * Post-condition: Bouncer is at the bottom edge of the pile of replacement blocks, Bouncer faces north.
     */
    private void returnToPile() {
        turnAround();
        bouncer.move();
        while (bouncer.canMoveForward()) {
            if (bouncer.isOnFieldWithColor(FieldColor.GREEN)) {
                bouncer.move();
            } else {
                bouncer.paintField(FieldColor.BLUE);
                bouncer.move();
            }
        }
        bouncer.turnLeft();
        while (bouncer.canMoveForward()) {
            bouncer.move();
        }
        turnAround();
    }

    /**
     * Bouncer fetch the tile of the top of the pile.
     * Pre-condition: Bouncer is at the bottom edge of the pile of replacement blocks, Bouncer faces north.
     * Post-condition: Bouncer is on the pile, Bouncer faces south.
     */
    private void getNextBrick() {
        while (bouncer.isOnFieldWithColor(FieldColor.GREEN)) {
            bouncer.move();
        }
        turnAround();
        bouncer.move();
        bouncer.clearFieldColor();
    }

    /**
     * Bouncer goes back to the broken part of the wall
     * Pre-condition: Bouncer is on the pile of replacement blocks, Bouncer faces south.
     * Post-condition: Bouncer is on the broken tile in the wall, von which he started.
     */
    private void returnToWall() {
        bouncer.turnLeft();
        bouncer.move();
        turnRight();
        while (bouncer.canMoveForward()) {
            bouncer.move();
        }
        turnAround();
        while (!bouncer.isOnFieldWithColor(FieldColor.BLUE)) {
            bouncer.move();
        }
        turnRight();
        while (bouncer.isOnFieldWithColor(FieldColor.BLUE)) {
            bouncer.clearFieldColor();
            bouncer.move();
        }
        while (bouncer.isOnFieldWithColor(FieldColor.GREEN)) {
            bouncer.move();
        }
    }

    /**
     * Bouncer turns right
     * Pre-condition: Bouncer faces a ceratin direction (e.g. north)
     * Post-condition: Bouncer faces the direction to the right of the previous direction (e.g. east)
     */
    private void turnRight() {
        bouncer.turnLeft();
        bouncer.turnLeft();
        bouncer.turnLeft();
    }

    /**
     * Bouncer does a 180 by rotating 90 degrees, moving, and rotating 90 degrees again
     * Pre-condition: Bouncer cannot vertically move further and faces the direction Bouncer moved in previously.
     * Post-condition: Bouncer stands a tile to the left of the Bouncer's previous location and faces the opposite direction
     */
    private void turnAround() {
        bouncer.turnLeft();
        bouncer.turnLeft();
    }

    public static void main(String[] args) {
        BouncerLauncher.launch("DieMauer");
    }
}