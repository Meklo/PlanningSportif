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
    public static final String ATTRIBUT_ID = "_id";
    public static final String ATTRIBUT_TYPE = "typeprg";
    public static final String ATTRIBUT_DATE_CREATION = "creation";



    public ProgrammePersistence(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String table_programme_create =
                "CREATE TABLE "+ TABLE_PROGRAMME + "(" +
                ATTRIBUT_ID + " INTEGER PRIMARY KEY, " +
                ATTRIBUT_TITRE + " TEXT, " +
                ATTRIBUT_TYPE + " TEXT, " +
                ATTRIBUT_DATE_CREATION + " TEXT " +
                ");"; */
            String table_programme_create = "CREATE TABLE IF NOT EXISTS programme(_id INTEGER PRIMARY KEY AUTOINCREMENT, TITRE TEXT, TYPEPROG TEXT, CREATION TEXT);";
            db.execSQL(table_programme_create);
            Log.d("table cree","ok");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addProgramme(Programme programme) {
        try {
            Log.d("rentre ds Add", "ok");
        SQLiteDatabase db = this.getWritableDatabase();

        this.onOpen(db);
            ContentValues values = new ContentValues();
        values.put("TITRE", "tests");
        values.put("TYPEPROG", programme.getType().toString()); // Contact Name
        values.put("CREATION", "test"); // Contact Phone

                Log.d(values.toString(),"ok");

         db.insert(TABLE_PROGRAMME, null, values);
            Log.d("insertion ok", "ok");
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
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_PROGRAMME, null, null);
            Log.d("table sup", "ok");
        }catch (Exception e){
                Log.i("table non suppr", e.getMessage());
             }
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

