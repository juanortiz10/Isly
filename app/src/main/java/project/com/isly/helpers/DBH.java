package project.com.isly.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

import project.com.isly.models.Lists;


public class DBH extends SQLiteOpenHelper {
    public static final int database_version=1;
    public String sqlQuery="CREATE TABLE IF NOT EXISTS " +MyTable.TableInfo.lists_table+"( "+ MyTable.TableInfo.id_list+" INTEGER PRIMARY KEY, " +
            MyTable.TableInfo.name_list+" TEXT," +MyTable.TableInfo.key_list+" TEXT," +MyTable.TableInfo.is_active+" TEXT);";
    public String sqlQuery2="CREATE TABLE IF NOT EXISTS "+MyTable.TableInfo.students_table+"( "+MyTable.TableInfo.id_student+" INTEGER PRIMARY KEY, "+MyTable.TableInfo.id_list+
            " TEXT,"+MyTable.TableInfo.name_student+" TEXT,"+MyTable.TableInfo.mat+" TEXT,"+MyTable.TableInfo.last_updated+" DATE,"+MyTable.TableInfo.counter+" TEXT);";

    public DBH(Context context) {
        super(context, MyTable.TableInfo.database_name,null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlQuery);
        sqLiteDatabase.execSQL(sqlQuery2);
    }

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
            String sql = "SELECT * FROM "+MyTable.TableInfo.lists_table;
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

    public static boolean setNewActiveList(){
        return false;
    }
}