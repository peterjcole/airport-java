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

    @Mock
    Plane secondPlaneMock;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        airport = new Airport();
        when(planeMock.isFlying()).thenReturn(true);
        when(secondPlaneMock.isFlying()).thenReturn(true);

    }

    @Test
    public void land_addsPlaneToPlanes() throws AirportFullException {
        airport.land(planeMock);
        assertEquals(0, airport.planes().indexOf(planeMock));
    }

    @Test
    public void takeoff_removesPlaneFromPlanes() throws AirportFullException {
        airport.land(planeMock);
        when(planeMock.isFlying()).thenReturn(false);
        airport.takeOff(planeMock);
        assertEquals(-1, airport.planes().indexOf(planeMock));
    }

    @Test
    public void takeoff_keepsOtherPlanesIfPlaneIsNotInAirport() throws AirportFullException {
        airport.land(secondPlaneMock);
        airport.takeOff(planeMock);
        assertEquals(1, airport.planes().size());
    }

    @Test
    public void takeoff_callsStartFlyingOnPlane() throws AirportFullException {
        airport.land(planeMock);
        airport.takeOff(planeMock);
        verify(planeMock).startFlying();

    }

    @Test
    public void takeoff_doesNotCallStartFlyingIfPlaneIsNotInAirport() throws AirportFullException {
        airport.land(planeMock);
        airport.takeOff(secondPlaneMock);
        verify(secondPlaneMock, never()).startFlying();
    }

    @Test
    public void land_planeDoesNotLandIfAlreadyLanded() throws AirportFullException {
        airport.land(planeMock);
        when(planeMock.isFlying()).thenReturn(false);
        airport.land(planeMock);
        assertEquals(1, airport.planes().size());
    }

    @Test
    public void land_callsIsFlyingOnPlane() throws AirportFullException {
        airport.land(planeMock);
        verify(planeMock).isFlying();
    }

    @Test
    public void land_callsStopFlyingOnPlane() throws AirportFullException {
        airport.land(planeMock);
        verify(planeMock).stopFlying();
    }

    @Test(expected = AirportFullException.class)
    public void land_exceptionWhenAirportIsFull() throws AirportFullException {
        for (int i = 0; i <= Airport.CAPACITY; i++) {
            airport.land(planeMock);
        }
    }
}
