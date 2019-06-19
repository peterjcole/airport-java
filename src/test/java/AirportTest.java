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
    Airport stormyAirport;

    @Mock
    Plane planeMock;

    @Mock
    Plane secondPlaneMock;

    @Mock
    Weather stormyWeatherMock;

    @Mock
    Weather goodWeatherMock;


    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        airport = new Airport(goodWeatherMock);
        when(planeMock.isFlying()).thenReturn(true);
        when(secondPlaneMock.isFlying()).thenReturn(true);
        when(stormyWeatherMock.isStormy()).thenReturn(true);
        stormyAirport = new Airport(stormyWeatherMock);

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

    @Test
    public void takeoff_doesNotTakeOffWhenWeatherIsStormy() throws AirportFullException {
        when(stormyWeatherMock.isStormy()).thenReturn(false);
        stormyAirport.land(planeMock);
        when(stormyWeatherMock.isStormy()).thenReturn(true);
        stormyAirport.takeOff(planeMock);
        assertEquals(0, stormyAirport.planes().indexOf(planeMock));
    }

    @Test
    public void takeoff_doesNotCallStartFlyingOnPlaneWhenWeatherIsStormy() throws AirportFullException {
        when(stormyWeatherMock.isStormy()).thenReturn(false);
        stormyAirport.land(planeMock);
        when(stormyWeatherMock.isStormy()).thenReturn(true);
        stormyAirport.takeOff(planeMock);
        verify(secondPlaneMock, never()).startFlying();
    }

    @Test
    public void takeoff_callsIsStormyOnWeather() throws AirportFullException {
        airport.takeOff(planeMock);
        verify(goodWeatherMock).isStormy();
    }

    @Test
    public void land_doesNotLandWhenWeatherIsStormy() throws AirportFullException {
        stormyAirport.land(planeMock);
        assertEquals(-1, stormyAirport.planes().indexOf(planeMock));
    }

    @Test
    public void land_doesNotCallStopFlyingOnPlaneWhenWeatherIsStormy() throws AirportFullException {
        stormyAirport.land(planeMock);
        verify(planeMock, never()).stopFlying();
    }

    @Test
    public void land_callsIsStormyOnWeather() throws AirportFullException {
        airport.land(planeMock);
        verify(goodWeatherMock).isStormy();
    }

}
