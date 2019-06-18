import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class PlaneTest {

    Plane plane;

    @Before
    public void initialize() {
        plane = new Plane();
    }

    @Test
    public void isFlying_returnsFalseByDefault() {
        assertEquals(false, plane.isFlying());
    }

    @Test
    public void startFlying_isFlying_becomesTrue() {
        plane.startFlying();
        assertTrue(plane.isFlying());
    }

    @Test
    public void stopFlying_isFlying_becomesFalse() {
        plane.startFlying();
        plane.stopFlying();
        assertEquals(false, plane.isFlying());
    }
}
