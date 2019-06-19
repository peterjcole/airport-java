import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WeatherTest {

    Weather seededWeather;

    @Before
    public void initialize() {
        seededWeather = new Weather(12345678);
    }

    @Test
    public void isStormy_returnsTrueWithFixedSeed() {
        assertEquals(true, seededWeather.isStormy());
    }

}
