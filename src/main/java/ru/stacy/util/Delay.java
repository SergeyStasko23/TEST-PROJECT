package ru.stacy.util;

import java.util.concurrent.TimeUnit;

public class Delay {
    public static void startDelay(long id) {
        try {
            TimeUnit.SECONDS.sleep(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
