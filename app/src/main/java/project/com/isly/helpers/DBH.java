package project.com.isly.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by juan on 15/10/15.

public class DBH extends SQLiteOpenHelper {
    public static final int database_version=1;
    public String sqlQuery="CREATE TABLE IF NOT EXISTS " +MyTable.TableInfo.table_name+"( "+ MyTable.TableInfo.id_word+" INTEGER PRIMARY KEY, " +
            MyTable.TableInfo.name_word+" TEXT," +MyTable.TableInfo.word_level+" INTEGER," +MyTable.TableInfo.date+" LONG);";


    public DBH(Context context) {
        super(context, MyTable.TableInfo.database_name,null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        onCreate(sqLiteDatabase);
    }

}
*/