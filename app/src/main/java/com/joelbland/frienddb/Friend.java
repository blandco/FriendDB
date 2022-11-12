package com.joelbland.frienddb;
public class Friend {

    private int id;
    private String firstname;
    private String lastname;
    private String email;

    public Friend(int newId, String newFirstname, String newLastname, String newEmail) {
        setId(newId);
        setFirstName(newFirstname);
        setLastName(newLastname);
        setEmail(newEmail);
    }

    private void setId(int newId) { id = newId; }

    private void setFirstName(String newFirstname) { firstname = newFirstname; }

    private void setLastName(String newLastname) { lastname = newLastname; }

    private void setEmail(String newEmail) { email = newEmail; }

    public int getId() { return id; }

    public String getFirstName() { return firstname; }

    public String getLastName() { return lastname; }

    public String getEmail() { return email; }
}
