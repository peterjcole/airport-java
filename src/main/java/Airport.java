import java.util.ArrayList;
import java.util.List;

public class Airport {

    public static final int CAPACITY = 10;

    private List<Plane> planes = new ArrayList<Plane>();

    public void land(Plane plane) throws AirportFullException {
        if(planes.size() >= CAPACITY) throw new AirportFullException();
        if(plane.isFlying()) {
            planes.add(plane);
            plane.stopFlying();
        }
    }

    public void takeOff(Plane plane) {
        if(planes.indexOf(plane) > -1) {
            planes.remove(plane);
            plane.startFlying();
        }

    }

    public List planes() {
        return planes;
    }
}
