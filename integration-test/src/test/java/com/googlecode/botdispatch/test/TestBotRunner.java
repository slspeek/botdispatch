/*
 * Copyright 2010-2012 Steven L. Speek.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */
package com.googlecode.botdispatch.test;

import com.google.inject.Inject;

import java.io.IOException;
import java.util.Arrays;


/**
 *
 * @author steven
 */
public class TestBotRunner {
    private final String endpoint;
    private final String secret;
    private final String peerJar;

    @Inject
    public TestBotRunner() {
        this.endpoint = System.getProperty("endpoint");
        this.secret = System.getProperty("bot.secret");
        this.peerJar = System.getProperty("peer.jar");
    }

    public void startPeer() throws IOException, InterruptedException {
        String[] command = getCommand();
        Process peer = Runtime.getRuntime().exec(command);
        System.out.println("peer start with cmd: " + Arrays.asList(command));
        int error = peer.waitFor();;
        if (error != 0) {
            throw new IllegalStateException("start peer failed");
        }
    }

    public void stopPeer() throws IOException {
        Process peer = Runtime.getRuntime()
                              .exec(new String[] { "telnet", "localhost", "4444" });

        System.out.println("peer stopped");
    }

    private String[] getCommand() {
        String[] cmd = new String[] {
                "screen", "-d", "-m", "java", "-cp", peerJar,
                "-Dendpoint=" + endpoint,
                "-Dbot.secret=" + secret,
                "-Dpause=1",
                "com.googlecode.botdispatch.testbot.Main"
            };

        return cmd;
    }
}
