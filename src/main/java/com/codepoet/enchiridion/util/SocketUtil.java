package com.codepoet.enchiridion.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;

public class SocketUtil {

    public static BufferedReader buildReader(final Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public static PrintWriter buildWriter(final Socket socket) throws IOException {
        return new PrintWriter(socket.getOutputStream(), true);
    }
}
