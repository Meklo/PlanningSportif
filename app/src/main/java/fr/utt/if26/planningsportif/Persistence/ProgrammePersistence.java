package fr.utt.if26.planningsportif.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import fr.utt.if26.planningsportif.Modeles.Programme;
import fr.utt.if26.planningsportif.Modeles.TypeProgramme;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Meklo on 10/12/2017.
 */

public class ProgrammePersistence extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "programme_sportif.db";
    public static final String TABLE_PROGRAMME = "programme";
    public static final String ATTRIBUT_TITRE = "titre";
    public static final String ATTRIBUT_ID = "id";
    public static final String ATTRIBUT_TYPE = "type";
    public static final String ATTRIBUT_DATE_CREATION = "datecreation";


    public ProgrammePersistence(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
        final String table_programme_create =
                "CREATE TABLE "+ TABLE_PROGRAMME + "(" +
                ATTRIBUT_ID + " INTEGER PRIMARY KEY," +
                ATTRIBUT_TITRE + " TEXT," +
                ATTRIBUT_TYPE + " TEXT," +
                ATTRIBUT_DATE_CREATION + " TEXT" +
                ")";

        db.execSQL(table_programme_create);
        } catch(Exception e){
            Log.e("Base de donnees", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addProgramme(Programme programme) {
        try {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ATTRIBUT_TITRE, programme.getTitre());
        values.put(ATTRIBUT_TYPE, programme.getType().toString()); // Contact Name
        values.put(ATTRIBUT_DATE_CREATION, "test"); // Contact Phone

        db.insert(TABLE_PROGRAMME, null, values);
        db.close(); // Closing database connection
        } catch(Exception e){
            Log.e("Base de donnees", e.getMessage());
        }
    }

    public Programme getProgramme(int id) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_PROGRAMME, new String[]{ATTRIBUT_ID,
                            ATTRIBUT_TITRE, ATTRIBUT_TYPE, ATTRIBUT_DATE_CREATION}, ATTRIBUT_ID + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);

            if (cursor != null)
                cursor.moveToFirst();

            Programme programme = new Programme(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), TypeProgramme.valueOf(cursor.getString(2)), new SimpleDateFormat("dd/MM/yyyy").parse(cursor.getString(3)), null);
            // return contact
            return programme;


        } catch (Exception e){
            Log.i("Base de données", e.getMessage());
            return null;
        }
    }

    public ArrayList<Programme> getAllProgrammes() {
        ArrayList<Programme> programmeList = new ArrayList<Programme>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PROGRAMME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Programme programme = new Programme(cursor.getInt(0), cursor.getString(1));
                // Adding contact to list
                programmeList.add(programme);
            } while (cursor.moveToNext());
        }

        // return contact list
        return programmeList;
    }

    public void deleteProgramme(Programme programme) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROGRAMME, ATTRIBUT_ID + " = ?",
                new String[] { String.valueOf(programme.getId()) });
        db.close();
    }

    public void deleteTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROGRAMME, null, null);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

}

