import java.util.ArrayList;
import java.util.List;

public class Airport {
    private List<Plane> planes = new ArrayList<Plane>();

    public void land(Plane plane) {
        if(plane.isFlying()) {
            planes.add(plane);
            plane.stopFlying();
        }
    }

    public void takeOff(Plane plane) {
        planes.remove(plane);
    }

    public List planes() {
        return planes;
    }
}
