import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class AirportTest {
    Airport airport;

    @Mock
    Plane planeMock;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        airport = new Airport();
        when(planeMock.isFlying()).thenReturn(true);
    }

    @Test
    public void land_addsPlaneToPlanes() {
        airport.land(planeMock);
        assertEquals(0, airport.planes().indexOf(planeMock));
    }

    @Test
    public void takeoff_removesPlaneFromPlanes() {
        airport.land(planeMock);
        when(planeMock.isFlying()).thenReturn(false);
        airport.takeOff(planeMock);
        assertEquals(-1, airport.planes().indexOf(planeMock));
    }

    @Test
    public void land_planeDoesNotLandIfAlreadyLanded() {
        airport.land(planeMock);
        when(planeMock.isFlying()).thenReturn(false);
        airport.land(planeMock);
        assertEquals(1, airport.planes().size());
    }

    @Test
    public void land_callsIsFlyingOnPlane() {
        airport.land(planeMock);
        verify(planeMock).isFlying();
    }

    @Test
    public void land_callsStopFlyingOnPlane() {
        airport.land(planeMock);
        verify(planeMock).stopFlying();
    }
}
