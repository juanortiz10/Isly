package project.com.isly.helpers;

import android.provider.BaseColumns;

/**
 * Created by juan on 15/10/15.
 */
public class MyTable {
    public MyTable(){

    }

    public static abstract class TableInfo implements BaseColumns {
        public static final String database_name="isly";
        public static final String lists_table="lists";
        public static final String students_table="students";
        public static final String id_list="id_list";
        public static final String id_mobile="id_mobile";
        public static final String name_list="name_list";
        public static final String key_list="key_list";
        public static final String is_active="is_active";
        public static final String id_student="id_student";
        public static final String name_student="name_student";
        public static final String mat="mat";
        public static final String last_updated="last_updated";
        public static final String counter="counter";
    }
}

