package blungogo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Servidor implements Runnable {

    private final int port = 8000;

    LinkedList<Contact> contacts = new LinkedList<>();
    LinkedList<Group> groups = new LinkedList<>();

    public void send(String sender, String[] msg) {
        if (msg[1] == null) {
            for (Contact c : contacts) {
                if (msg[0].equals(c.getNickname())) {
                    msg[0] = sender;
                    c.send(msg);
                }
            }
        } else {
            for (Group g : groups) {
                if (msg[1].equals(g.idGroup)) {
                    msg[0] = sender;
                    g.send(msg);
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                ServerSocket serverSocket = new ServerSocket(port);
                Socket socket = serverSocket.accept();
                Contact contact = new Contact(this, socket);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
