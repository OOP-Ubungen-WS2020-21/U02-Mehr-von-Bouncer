import de.ur.mi.bouncer.apps.BouncerApp;
import de.ur.mi.bouncer.apps.BouncerLauncher;
import de.ur.mi.bouncer.world.fields.FieldColor;

public class KirschenEssen extends BouncerApp {

    @Override
    public void bounce() {
        loadMap("Cherries");

        moveToBottomLeftCorner();

        bouncer.turnLeft();

        eatCherries();
    }

    public static void main(String[] args) {
        BouncerLauncher.launch("KirschenEssen");
    }

    private void moveToBottomLeftCorner() {
        while(bouncer.canMoveForward())
            bouncer.move();
    }

    private void eatCherries() {
        while (bouncer.canMoveLeft()) {
            lookForCherry();
            turnAround();
        }

        lookForCherry();
    }

    private void lookForCherry() {
        while (bouncer.canMoveForward()) {
            tryEatingCherry();
            bouncer.move();
        }
    }

    private void tryEatingCherry() {
        if(bouncer.isOnFieldWithColor(FieldColor.RED)) {
            bouncer.paintField(FieldColor.WHITE);
        }
    }

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

    private void turnRight() {
        bouncer.turnLeft();
        bouncer.turnLeft();
        bouncer.turnLeft();
    }
}