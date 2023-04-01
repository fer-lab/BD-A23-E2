package movieRiew.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import static java.lang.Math.floor;

public class Tools {

    public static boolean hasDecimal(float number) {
        float roundedNumber = (float) floor(number);
        return number - roundedNumber > 0;
    }

    public static String dateFormat(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
        return dateFormat.format(new Date(timestamp * 1000));
    }

    public static long currentDate() {
        return new Date().getTime() / 1000;
    }

    public static long randomDate() {
        Random random = new Random(System.currentTimeMillis());
        long sixtyDaysAgoMs = 60 * 24 * 60 * 60 * 1000L;

        long randomMs = random.nextLong(sixtyDaysAgoMs);
        return (System.currentTimeMillis() - sixtyDaysAgoMs + randomMs) / 1000;
    }

    public static void newPage() {
        System.out.println("\n".repeat(60));

    }

    public static String uuid() {
        return UUID.randomUUID().toString();
    }





}
