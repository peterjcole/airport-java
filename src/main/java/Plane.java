public class Plane {

    private boolean flying = false;

    public boolean isFlying() {
        return flying;
    }

    public void stopFlying() {
        flying = false;
    }

    public void startFlying() {
        flying = true;
    }
}
