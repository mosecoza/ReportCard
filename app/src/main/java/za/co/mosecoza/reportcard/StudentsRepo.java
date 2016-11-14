package za.co.mosecoza.reportcard;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Bonginkosi Lukhele on 11-11-2016.
 */

public class StudentsRepo {
    private Students students;

    public StudentsRepo(){
        students = new Students();
    }

    public static String createTable(){
        return "CREATE TABLE " + Students.TABLE + "("
                + Students.KEY_ROW_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Students.KEY_Surname + " TEXT, "+ Students.KEY_Name+" TEXT, "
                +Students.KEY_Student_ID+" TEXT, "+Students.KEY_Address+" TEXT, "
                +Students.KEY_next_of_kin+" TEXT, "+Students.KEY_Contact+" TEXT, "
                +Students.KEY_Subject_1+" TEXT, "+Students.KEY_Subject_2+" TEXT, "
                +Students.KEY_Subject_3+ " TEXT )";
    }
    public int insert(Students students){
        int row_Id;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Students.KEY_ROW_ID,students.getRow_id());
        cv.put(Students.KEY_Surname,students.getSurname());
        cv.put(Students.KEY_Name,students.getName());
        cv.put(Students.KEY_Student_ID,students.getId());
        cv.put(Students.KEY_Address,students.getAddress());
        cv.put(Students.KEY_next_of_kin,students.getNext_of_kin());
        cv.put(Students.KEY_Contact,students.getContact());
        cv.put(Students.KEY_Subject_1,students.getSubject_1());
        cv.put(Students.KEY_Subject_2,students.getSubject_2());
        cv.put(Students.KEY_Subject_3,students.getSubject_3());

        row_Id = (int)db.insert(Students.TABLE,null,cv);
        DatabaseManager.getInstance().closeDatabase();

        return row_Id;
    }

    public ArrayList<String> getDataForListview() {
        String[] columns = new String[] {Students.KEY_ROW_ID, Students.KEY_Surname, Students.KEY_Name};
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.query(Students.TABLE,columns,null,null,null, null,null);
        ArrayList<String> Answer = new ArrayList<>();
        String ans = "";

        int iRow = c.getColumnIndex(Students.KEY_ROW_ID);
        int iSurname = c.getColumnIndex(Students.KEY_Surname);
        int iName = c.getColumnIndex(Students.KEY_Name);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())

            /*adding the required info for all students and initialise their indexes with existing students using the ROW_ID*/
            Answer.add(c.getString(iRow)+". "+c.getString(iSurname)+" "+c.getString(iName));
        c.close();
        DatabaseManager.getInstance().closeDatabase();

        return   Answer;
    }

//    public long addNewStudent(Students students) {
//
//        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
//        ContentValues cv = new ContentValues();
//
//        cv.put(Students.KEY_Surname,students.getSurname());
//        cv.put(Students.KEY_Name,students.getName());
//        cv.put(Students.KEY_Student_ID,students.getId());
//        cv.put(Students.KEY_Address,students.getAddress());
//        cv.put(Students.KEY_next_of_kin,students.getNext_of_kin());
//        cv.put(Students.KEY_Contact,students.getContact());
//        cv.put(Students.KEY_Subject_1, students.getSubject_1());
//        cv.put(Students.KEY_Subject_2, students.getSubject_2());
//        cv.put(Students.KEY_Subject_3, students.getSubject_3());
//        return db.insert(Students.TABLE,null,cv);
//    }

//    public String getStSurname(String ya) {
//        String[] columns = new String[] {Students.KEY_Surname};
//        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
//        Cursor c = db.query(Students.TABLE,columns,Students.KEY_ROW_ID+" = "+ ya,null,null, null,null);
//        if (c!=null){
//            c.moveToFirst();
//            String name = c.getString(1);
//            c.close();
//            return name;
//        }
//
//        return null;
//    }

    public boolean doesStudentExist(String long_id) {
        String[] columns = new String[] {Students.KEY_ROW_ID};
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor c = db.query(Students.TABLE,columns,Students.KEY_ROW_ID+" = "+long_id,null,null, null,null);
        if (c != null){
            c.moveToFirst();
            String name = c.getString(0);
            if (name!=null){
                return true;}
        }

        return false;
    }

    public ArrayList<String> getStudentToEditInfo(String theId) {
        String[] columns = new String[] {Students.KEY_ROW_ID, Students.KEY_Surname, Students.KEY_Name,
        Students.KEY_Student_ID, Students.KEY_Address, Students.KEY_next_of_kin, Students.KEY_Contact,
                Students.KEY_Subject_1, Students.KEY_Subject_2, Students.KEY_Subject_3};

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        students.setRow_id(theId);

        Cursor c = db.query(Students.TABLE,columns,Students.KEY_ROW_ID + " = " + students.getRow_id(),null,null, null,null);

        ArrayList<String> Answer = new ArrayList<>();

        c.moveToFirst();

            /*adding the required info for all students and initialise their indexes with existing students using the ROW_ID*/
        Answer.add(0,c.getString(0));
        Answer.add(1,c.getString(1));
        Answer.add(2,c.getString(2));
        Answer.add(3,c.getString(3));
        Answer.add(4,c.getString(4));
        Answer.add(5,c.getString(5));
        Answer.add(6,c.getString(6));
        Answer.add(7,c.getString(7));
        Answer.add(8,c.getString(8));
        Answer.add(9,c.getString(9));
        c.close();
        DatabaseManager.getInstance().closeDatabase();

        return   Answer;
    }

    public int updateStudentData(Students students) {
        boolean didItWork;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues cvUpdate = new ContentValues();

        cvUpdate.put(Students.KEY_Surname, students.getSurname());
        cvUpdate.put(Students.KEY_Name, students.getName());
        cvUpdate.put(Students.KEY_Student_ID, students.getId());
        cvUpdate.put(Students.KEY_Address, students.getAddress());
        cvUpdate.put(Students.KEY_next_of_kin, students.getNext_of_kin());
        cvUpdate.put(Students.KEY_Contact, students.getContact());
        cvUpdate.put(Students.KEY_Subject_1, students.getSubject_1());
        cvUpdate.put(Students.KEY_Subject_2, students.getSubject_2());
        cvUpdate.put(Students.KEY_Subject_3, students.getSubject_3());

       return db.update(Students.TABLE,cvUpdate,Students.KEY_ROW_ID +" = "+students.getRow_id(),null);
    }
}
