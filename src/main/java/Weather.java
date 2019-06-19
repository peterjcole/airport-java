import java.util.Random;

public class Weather {

    Random random;

    public Weather() {

    }

    public Weather(int seed) {
        random = new Random(seed);
    }

    public boolean isStormy() {
        return random.nextBoolean();
    }

}
