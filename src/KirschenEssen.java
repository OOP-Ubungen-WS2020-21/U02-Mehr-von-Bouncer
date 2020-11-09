import de.ur.mi.bouncer.apps.BouncerApp;
import de.ur.mi.bouncer.apps.BouncerLauncher;
import de.ur.mi.bouncer.world.fields.FieldColor;

public class KirschenEssen extends BouncerApp {

    /**
     * Bouncer ist hungry and intends to feast on the cherries in the map.
     */
    @Override
    public void bounce() {
        loadMap("Cherries");

        moveToBottomRightCorner();
        eatCherries();
    }

    /**
     * Bouncer moves to the bottom right corner of the map.
     * Pre-condition: Bouncer's spawning location is at the bottom edge of the map and Bouncer faces east.
     * Post-condition: Bouncer moved to the bottom right corner of the map and faces north.
     */
    private void moveToBottomRightCorner() {
        while(bouncer.canMoveForward())
            bouncer.move();

        bouncer.turnLeft();
    }

    /**
     * Bouncer actively tries to eat cherries.
     * Pre-condition: Bouncer is in the bottom right corner of the map and faces north.
     * Post-condition: Bouncer is in the top left corner of the map facing north.
     */
    private void eatCherries() {
        while (bouncer.canMoveLeft()) {
            lookForCherry();
            turnAround();
        }

        lookForCherry();
    }

    /**
     * Bouncer moves along the path Bouncers faces until Bouncer reaches the end
     * Pre-condition: Bouncer is at a edge of the map (north or south) and faces the direction Bouncers wants to move in.
     * Post-condition: Bouncer is at the opposite edge of the map, may have eaten cherries and still faces in the movement direction
     */
    private void lookForCherry() {
        while (bouncer.canMoveForward()) {
            tryEatingCherry();
            bouncer.move();
        }
    }

    /**
     * Bouncer takes a bite out of a cherry if Bouncer happens to be at a cherry's position
     * Pre-condition: Bouncer is at a location which may contain a piece of the cherry
     * Post-condition: Bouncer nibbles at the cherry if there indeed is one (colors the tile white) or does nothing.
     */
    private void tryEatingCherry() {
        if(bouncer.isOnFieldWithColor(FieldColor.RED)) {
            bouncer.paintField(FieldColor.WHITE);
        }
    }

    /**
     * Bouncer does a 180 by rotating 90 degrees, moving, and rotating 90 degrees again
     * Pre-condition: Bouncer cannot vertically move further and faces the direction Bouncer moved in previously.
     * Post-condition: Bouncer stands a tile to the left of the Bouncer's previous location and faces the opposite direction
     */
    private void turnAround() {
        if(bouncer.isFacingSouth()) {
            turnRight();
            bouncer.move();
            turnRight();
        } else {
            bouncer.turnLeft();
            bouncer.move();
            bouncer.turnLeft();
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

    public static void main(String[] args) {
        BouncerLauncher.launch("KirschenEssen");
    }
}