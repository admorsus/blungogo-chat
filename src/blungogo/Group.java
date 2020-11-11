package blungogo;

import java.util.LinkedList;

public class Group {

    static int cid = 0;

    String idGroup;
    String nickname;
    LinkedList<Contact> contacts = new LinkedList<>();

    public Group(String nickname) {
        this.nickname = nickname;
        this.idGroup = getNewID();
    }

    private static String getNewID() {
        return String.valueOf(cid++);
    }

    public void send(String[] msg) {
        for (Contact c : contacts) {
            c.send(msg);
        }
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void deleteContact(Contact contact) {
        contacts.remove(contact);
    }

    public String getIdGroup() {
        return idGroup;
    }

    public String getNickname() {
        return nickname;
    }

    public LinkedList<Contact> getContacts() {
        return contacts;
    }
}
