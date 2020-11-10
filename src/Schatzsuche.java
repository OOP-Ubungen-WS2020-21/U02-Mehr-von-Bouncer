import de.ur.mi.bouncer.apps.BouncerApp;
import de.ur.mi.bouncer.apps.BouncerLauncher;
import de.ur.mi.bouncer.world.fields.FieldColor;


public class Schatzsuche extends BouncerApp {

    @Override
    public void bounce() {
        loadMap("Pool");
        enterPool();
        diveIn();
        clearPool();
        diveOut();
    }

    /**
     * Bouncer moves to the see.
     * Pre-condition: Bouncer is on the west side of the map, water edge, Bouncer faces east.
     * Post-condition: Bouncer is on the first water-field, Bouncer faces east.
     */
    private void enterPool() {
        while (bouncer.canNotMoveRight()) {
            bouncer.move();
        }
    }

    /**
     * Bouncer dives to the bottom of the see.
     * Pre-condition: Bouncer is on the first water-field, Bouncer faces east.
     * Post-condition: Bouncer is on the bottom of the see, Bouncer faces east.
     */
    private void diveIn() {
        turnRight();
        while (bouncer.canMoveForward()) {
            bouncer.move();
        }
        bouncer.turnLeft();
    }

    /**
     * Bouncer leaves the see on the east edge of the map.
     * Pre-condition: Bouncer is in the southeast of the see, under water, Bouncer faces east.
     * Post-condition: Bouncer is at the east edge of the map at the water edge, Bouncer faces east.
     */
    private void diveOut() {
        bouncer.turnLeft();
        while (bouncer.canNotMoveRight()) {
            bouncer.move();
        }
        turnRight();
        while (bouncer.canMoveForward()) {
            bouncer.move();
        }
    }

    /**
     * Bouncer collects the treasure in the pool.
     * Pre-condition: Bouncer is at the bottom of the see in the west, Bouncer faces east.
     * Post-condition: Bouncer is southeast in the see, under water, Bouncer faces east.
     */
    private void clearPool() {
        salvageLoot();
        while (bouncer.canMoveForward()) {
            bouncer.move();
            salvageLoot();
        }
    }

    /**
     * Bouncer looks for treasures and carry them to the surface.
     * Pre-condition: Bouncer is on a treasure-field, Bouncer faces east.
     * Post-condition: Bouncer is on the same field (without the treasure), Bouncer faces east.
     */
    private void salvageLoot() {
        if (bouncer.isOnFieldWithColor(FieldColor.RED)) {
            bouncer.paintField(FieldColor.BLUE);
            returnToSurface();
            bouncer.paintField(FieldColor.RED);
            returnToGround();
        }
    }

    /**
     * Bouncer dives to the surface of the see.
     * Pre-condition: Bouncer is at the bottom of the see, Bouncer faces east.
     * Post-condition: Bouncer is one field above the water, Bouncer faces north.
     */
    private void returnToSurface() {
        bouncer.turnLeft();
        while (bouncer.isOnFieldWithColor(FieldColor.BLUE)) {
            bouncer.move();
        }
    }

    /**
     * Bouncer goes back to the bottom of the see.
     * Pre-condition: Bouncer is one field above the water, Bouncer faces north.
     * Post-condition: Bouncer is at the bottom of the see, Bouncer faces east.
     */
    private void returnToGround() {
        bouncer.turnLeft();
        bouncer.turnLeft();
        while (bouncer.canMoveForward()) {
            bouncer.move();
        }
        bouncer.turnLeft();
    }

    /**
     * Bouncer turns right
     * Pre-condition: Bouncer faces a ceratin direction (e.g. north)
     * Post-condition: Bouncer faces the direction to the right of the previous direction (e.g. east)
     */
    private void turnRight() {
        for (int i = 0; i < 3; i++) {
            bouncer.turnLeft();
        }
    }

    public static void main(String[] args) {
        BouncerLauncher.launch("Schatzsuche");
    }
}