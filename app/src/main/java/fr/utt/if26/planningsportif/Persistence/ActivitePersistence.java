package fr.utt.if26.planningsportif.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;

import fr.utt.if26.planningsportif.Modeles.Activite;
import fr.utt.if26.planningsportif.Modeles.ActiviteRepetition;
import fr.utt.if26.planningsportif.Modeles.ActiviteTemps;


/**
 * Created by Meklo on 07/01/2018.
 */

public class ActivitePersistence extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "programme_sportif.db";
    public static final String TABLE_ACTIVITE = "activite";
    public static final String ATTRIBUT_TITRE = "titre";
    public static final String ATTRIBUT_ID = "id";
    public static final String ATTRIBUT_PROGRAMME_ID = "programme_id";
    public static final String ATTRIBUT_TYPE_ACTIVITE = "typeActivite";
    public static final String ATTRIBUT_TEMPS = "temps";
    public static final String ATTRIBUT_SERIE = "serie";
    public static final String ATTRIBUT_REPETITION = "repetition";

    public static final String TABLE_PROGRAMME = "programme";

    public ActivitePersistence(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String table_programme_create =
                "CREATE TABLE "+ TABLE_ACTIVITE + "(" +
                        ATTRIBUT_ID + "INTEGER primary key, " +
                        ATTRIBUT_PROGRAMME_ID + "INTEGER, " +
                        ATTRIBUT_TITRE + "TEXT, " +
                        ATTRIBUT_TYPE_ACTIVITE + "TEXT, " +
                        ATTRIBUT_TEMPS + "TEXT, " +
                        ATTRIBUT_SERIE + "TEXT, " +
                        ATTRIBUT_REPETITION + "TEXT, " +
                        "FOREIGN KEY (" + ATTRIBUT_PROGRAMME_ID + ") REFERENCES "+ TABLE_PROGRAMME + "(" + ATTRIBUT_ID + ")"+
                        ")";
        db.execSQL(table_programme_create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    void addActiviteTemps(ActiviteTemps activite) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ATTRIBUT_PROGRAMME_ID, activite.getProgramme_id());
        values.put(ATTRIBUT_TITRE, activite.getTitre());
        values.put(ATTRIBUT_TYPE_ACTIVITE, activite.getType());
        values.put(ATTRIBUT_TEMPS, activite.getTemps());
        values.put(ATTRIBUT_REPETITION,  0);
        values.put(ATTRIBUT_SERIE, 0);
        db.insert(TABLE_ACTIVITE, null, values);
        db.close();
    }

    void addActiviteRepetition(ActiviteRepetition activite) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ATTRIBUT_PROGRAMME_ID, activite.getProgramme_id());
        values.put(ATTRIBUT_TITRE, activite.getTitre());
        values.put(ATTRIBUT_TYPE_ACTIVITE, activite.getType());
        values.put(ATTRIBUT_TEMPS,  activite.getSerie());
        values.put(ATTRIBUT_REPETITION,  activite.getRepetition());
        values.put(ATTRIBUT_SERIE, activite.getSerie());
        values.put(ATTRIBUT_TEMPS, 0);
        db.insert(TABLE_ACTIVITE, null, values);
        db.close();
    }

    Activite getActivite(int id) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_ACTIVITE, new String[]{ATTRIBUT_ID,
                             ATTRIBUT_PROGRAMME_ID,ATTRIBUT_TITRE, ATTRIBUT_TYPE_ACTIVITE, ATTRIBUT_TEMPS, ATTRIBUT_REPETITION, ATTRIBUT_SERIE}, ATTRIBUT_ID + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);

            if (cursor != null)
                cursor.moveToFirst();

            if(cursor.getString(3).equals("temps")){
                ActiviteTemps activite = new ActiviteTemps(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)),cursor.getString(2),Integer.parseInt(cursor.getString(4)));
                return activite;
            } else if(cursor.getString(3).equals("repetition")){
                ActiviteRepetition activite = new ActiviteRepetition(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)),cursor.getString(2),Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)));
                return activite;
            }

            return null;
        } catch (Exception e){
            Log.i("Base de données", e.getMessage());
            return null;
        }
    }

    public void deleteActivite(Activite activite) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROGRAMME, ATTRIBUT_ID + " = ?",
                new String[] { String.valueOf(activite.getId()) });
        db.close();
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
