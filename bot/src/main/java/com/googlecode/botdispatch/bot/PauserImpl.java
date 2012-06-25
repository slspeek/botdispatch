package com.googlecode.botdispatch.bot;

import com.google.inject.name.Named;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.*;

public class PauserImpl implements Pauser {

    public static final int MILLIS_IN_SECOND = 1000;
    private int idleCount = 0;
    final private int maxSeconds;
    private static final int PERIOD = 20;

    @Inject
    public PauserImpl(@Named("pause") int maxSeconds) {
        super();
        this.maxSeconds = maxSeconds;
    }

    public int pause() {
        int seconds = getPauseSeconds();
        long millis = seconds * MILLIS_IN_SECOND;
        String line = null;
        long endTime = currentTimeMillis() + millis;
        try {
            while (currentTimeMillis() < endTime ) {
                 if ( in.available() != 0) {
                         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                         line = br.readLine();
                                 resetIdleCount();
                                 break;
                 }
                try {
                    Thread.sleep(PERIOD);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        return seconds;
    }

    @Override
    public void resetIdleCount() {
        this.idleCount = 0;
    }

    @Override
    public void increaseIdleCount() {
        idleCount++;
    }

    @Override
    public int getPauseSeconds() {
        return (int) Math.min(Math.pow(2, idleCount), maxSeconds);
    }

}
