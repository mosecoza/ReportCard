package za.co.mosecoza.reportcard;

/**
 * Created by Bonginkosi Lukhele on 11-11-2016.
 */

public class Students {
    public static final String TAG = Students.class.getSimpleName();
    public static final String TABLE = "Students";
    
    public static final String KEY_Surname = "Surname";
    public static final String KEY_ROW_ID = "Row_ID";
    public static final String KEY_Name = "Name";
    public static final String KEY_Student_ID = "ID";
    public static final String KEY_Address = "Address";
    public static final String KEY_next_of_kin = "Next_of_kin";
    public static final String KEY_Contact = "Contact";
    public static final String KEY_Subject_1 = "Subject_1";
    public static final String KEY_Subject_2 = "Subject_2";
    public static final String KEY_Subject_3 = "Subject_3";
    
    private String surname, name, id, address, next_of_kin, contact,
    subject_1, subject_2, subject_3,row_id;

    /*getters*/

    public String getRow_id() {
        return row_id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getNext_of_kin() {
        return next_of_kin;
    }

    public String getContact() {
        return contact;
    }

    public String getSubject_1() {
        return subject_1;
    }
    public String getSubject_2() {
        return subject_2;
    }

    public String getSubject_3() {
        return subject_3;
    }

    /*setters*/

    public void setRow_id(String row_id) {
        this.row_id = row_id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNext_of_kin(String next_of_kin) {
        this.next_of_kin = next_of_kin;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setSubject_1(String subject_1) {
        this.subject_1 = subject_1;
    }

    public void setSubject_2(String subject_2) {
        this.subject_2 = subject_2;
    }

    public void setSubject_3(String subject_3) {
        this.subject_3 = subject_3;
    }
}
