package fr.utt.if26.planningsportif.Persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Meklo on 10/12/2017.
 */

public class ProgrammePersistence extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Modules.db";
    public static final String TABLE_PROGRAMME = "programme";
    public static final int
    public static final
    public static final
    public static final
    public static final


    public ProgrammePersistence(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String table_programme_create =
                "CREATE TABLE "+ TABLE_PROGRAMME + "(" +
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}

