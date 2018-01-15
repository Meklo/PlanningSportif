package fr.utt.if26.planningsportif.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;
import android.util.Log;

import fr.utt.if26.planningsportif.Modeles.Programme;
import fr.utt.if26.planningsportif.Modeles.TypeProgramme;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    public static final String ATTRIBUT_TYPE = "typeprogramme";
    public static final String ATTRIBUT_DATE_CREATION = "creation";

    ActivitePersistence activitePersistence;

    public ProgrammePersistence(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        activitePersistence = new ActivitePersistence(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = this.getWritableDatabase();
        try {
            String table_programme_create =
                    "CREATE TABLE IF NOT EXISTS " + TABLE_PROGRAMME + "(" +
                            ATTRIBUT_ID + " INTEGER PRIMARY KEY, " +
                            ATTRIBUT_TITRE + " TEXT, " +
                            ATTRIBUT_TYPE + " TEXT, " +
                            ATTRIBUT_DATE_CREATION + " TEXT " +
                            ");";
            //String table_programme_create = "CREATE TABLE IF NOT EXISTS programme(_id INTEGER PRIMARY KEY AUTOINCREMENT, TITRE TEXT, TYPEPROG TEXT, CREATION TEXT);";
            db.execSQL(table_programme_create);
            Log.d("table cree", "ok");
        } catch (Exception exception){
            Log.e("BD",exception.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addProgramme(Programme programme) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            this.onOpen(db);

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c.getTime());

            ContentValues values = new ContentValues();
            values.put(ATTRIBUT_TITRE, programme.getTitre());
            values.put(ATTRIBUT_TYPE, programme.getType().toString());
            values.put(ATTRIBUT_DATE_CREATION, formattedDate);

            db.insert(TABLE_PROGRAMME, null, values);
            Log.d("BD", "programme insere");
            db.close();
        } catch(Exception e){
            Log.e("BD", e.getMessage());
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
                    cursor.getString(1), TypeProgramme.valueOf(cursor.getString(2)), cursor.getString(3), null); // a changer pour afficher les activités
            db.close();
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
                Programme programme = new Programme(cursor.getInt(0), cursor.getString(1), TypeProgramme.valueOf(cursor.getString(2)), cursor.getString(3), activitePersistence.getActiviteFromProgramme(cursor.getInt(0)));
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
        Log.d("BD", "programme supprime");
        db.close();
    }

    public void deleteTable() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PROGRAMME);
            Log.d("BD", "table programme supprime");
        }catch (Exception e){
                Log.i("BD", e.getMessage());
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

