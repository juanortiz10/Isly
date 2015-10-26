package project.com.isly.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

import project.com.isly.R;
import project.com.isly.models.Lists;
import project.com.isly.models.Student;

/**
 * Helper used to give connection for the transactions that are going to be called
 * when the user set the application active
 * */
public class DBH extends SQLiteOpenHelper {
    public static final int database_version=1;
    public String sqlQuery="CREATE TABLE IF NOT EXISTS " +MyTable.TableInfo.lists_table+"( "+ MyTable.TableInfo.id_list+" INTEGER PRIMARY KEY, " +
            MyTable.TableInfo.name_list+" TEXT," +MyTable.TableInfo.key_list+" TEXT," +MyTable.TableInfo.is_active+" TEXT);";
    public String sqlQuery2="CREATE TABLE IF NOT EXISTS "+MyTable.TableInfo.students_table+"( "+MyTable.TableInfo.id_student+" INTEGER PRIMARY KEY, "+MyTable.TableInfo.id_list+
            " TEXT,"+MyTable.TableInfo.name_student+" TEXT,"+MyTable.TableInfo.mat+" TEXT,"+MyTable.TableInfo.id_list+" TEXT,"+MyTable.TableInfo.last_updated+" DATE,"+MyTable.TableInfo.counter+" TEXT);";

    //Constructor
    public DBH(Context context) {
        super(context, MyTable.TableInfo.database_name,null, database_version);
    }


    //Method which creates the tables
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlQuery);
        sqLiteDatabase.execSQL(sqlQuery2);
    }

    //If the DBA exists don't create it again
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        onCreate(sqLiteDatabase);
    }

    //Adds a new list into a DB
    public static void setNewList(Context ctx,Lists lists){
        DBH conection = new DBH(ctx);
        SQLiteDatabase db = conection.getReadableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(MyTable.TableInfo.name_list, lists.getName_lst());
            values.put(MyTable.TableInfo.key_list, lists.getKey_list());
            values.put(MyTable.TableInfo.is_active, lists.getIsActive());

            db.insert(MyTable.TableInfo.lists_table, null, values);
            Toast.makeText(ctx,"List Added Correctly",Toast.LENGTH_SHORT).show();
        }catch (Exception ex){
            Toast.makeText(ctx,"Error",Toast.LENGTH_SHORT).show();
        }finally {
            db.close();
        }
    }

    //Method which loads the available lists
    public static ArrayList<String> loadLists(Context ctx){
        try{
            ArrayList<String> arrayList=new ArrayList<String>();
            DBH conection = new DBH(ctx);
            String sql = "SELECT * FROM "+MyTable.TableInfo.lists_table+" ORDER BY "+MyTable.TableInfo.is_active+" DESC";
            SQLiteDatabase sq=conection.getWritableDatabase();
            Cursor cr = sq.rawQuery(sql, null);

            if(cr.moveToFirst()){
                do{
                    arrayList.add(cr.getString(cr.getColumnIndex(MyTable.TableInfo.name_list)));
                }while(cr.moveToNext());
            }
            conection.close();
            return arrayList;
        } catch (Exception e){
            return null;
        }
    }

    //Method which is able to put active the list selected by the user
    public static int setNewActiveList(Context ctx,String name_list){
        DBH conection = new DBH(ctx);
        SQLiteDatabase db= conection.getReadableDatabase();

        ContentValues values= new ContentValues();
        values.put(MyTable.TableInfo.is_active, "1");
        ContentValues value2= new ContentValues();
        value2.put(MyTable.TableInfo.is_active, "0");

        db.update(MyTable.TableInfo.lists_table, value2, null, null);
        db.update(MyTable.TableInfo.lists_table, values, MyTable.TableInfo.name_list + "=?",
                new String[]{name_list});
        db.close();
        return 1;
    }

    //Verifies if the user is active on list
    public static boolean checkIfExists(Context ctx,Student student){
        DBH conection = new DBH(ctx);
        SQLiteDatabase sq=conection.getWritableDatabase();
        Cursor cr = sq.rawQuery("SELECT * FROM "+ MyTable.TableInfo.students_table+" WHERE "+MyTable.TableInfo.mat+"=? ",  new String[]{student.getMac()});

        if(cr.moveToFirst()) return true;

        conection.close();
        return false;
    }

    //Adds a new student if doesn't exist
    public static void addNewStudent(Context ctx,Student student){
        DBH conection = new DBH(ctx);
        SQLiteDatabase db = conection.getReadableDatabase();
        try {

            int id_list = 0;
            Cursor cr = db.rawQuery("SELECT "+MyTable.TableInfo.id_list+" FROM "+MyTable.TableInfo.lists_table+" WHERE "+MyTable.TableInfo.is_active+"='1'",null);
            if(cr.moveToFirst())
                id_list=cr.getInt(cr.getColumnIndex(MyTable.TableInfo.id_list));

            ContentValues values = new ContentValues();
            values.put(MyTable.TableInfo.name_student,student.getName());
            values.put(MyTable.TableInfo.id_mobile, student.getMac());
            values.put(MyTable.TableInfo.id_list,id_list);
            db.insert(MyTable.TableInfo.students_table, null, values);
            System.out.println(id_list);
            Toast.makeText(ctx, R.string.ok,Toast.LENGTH_SHORT).show();
        }catch (Exception ex){
            Toast.makeText(ctx,R.string.unexpected,Toast.LENGTH_SHORT).show();
        }finally {
            db.close();
        }
    }
}