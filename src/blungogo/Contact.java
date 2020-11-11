package blungogo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Contact {

    Servidor server;

    String nickname;
    Socket socket;

    ObjectInputStream ois;
    ObjectOutputStream oos;

    class InputThread extends Thread {

        @Override
        public void run() {
            try {
                nickname = ois.readUTF();

                while(true) {
                    String[] msg = (String[]) ois.readObject();
                    server.send(nickname, msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Contact(Servidor server, Socket socket) {
        this.server = server;
        this.socket = socket;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputThread input = new InputThread();
        input.start();

    }

    public void send(String[] msg) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public String getNickname() {
        return nickname;
    }

    public Socket getSocket() {
        return socket;
    }
}
