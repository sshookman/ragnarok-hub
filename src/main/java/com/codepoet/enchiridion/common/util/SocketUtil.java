package com.codepoet.enchiridion.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;

public class SocketUtil {

    public static BufferedReader getReader(final Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public static PrintWriter getWriter(final Socket socket) throws IOException {
        return new PrintWriter(socket.getOutputStream(), true);
    }
}
