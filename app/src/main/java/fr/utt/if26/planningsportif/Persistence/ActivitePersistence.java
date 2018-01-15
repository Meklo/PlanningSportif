package fr.utt.if26.planningsportif.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fr.utt.if26.planningsportif.Modeles.Activite;
import fr.utt.if26.planningsportif.Modeles.ActiviteRepetition;
import fr.utt.if26.planningsportif.Modeles.ActiviteTemps;
import fr.utt.if26.planningsportif.Modeles.Programme;
import fr.utt.if26.planningsportif.Modeles.TypeProgramme;


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
    MCrypt mcrypt;


    public ActivitePersistence(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mcrypt = new MCrypt();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = this.getWritableDatabase();
        final String table_programme_create =
                "CREATE TABLE IF NOT EXISTS "+ TABLE_ACTIVITE + "(" +
                        ATTRIBUT_ID + " INTEGER PRIMARY KEY, " +
                        ATTRIBUT_PROGRAMME_ID + " INTEGER, " +
                        ATTRIBUT_TITRE + " TEXT, " +
                        ATTRIBUT_TYPE_ACTIVITE + " TEXT, " +
                        ATTRIBUT_TEMPS + " TEXT, " +
                        ATTRIBUT_SERIE + " TEXT, " +
                        ATTRIBUT_REPETITION + " TEXT, " +
                        "FOREIGN KEY (" + ATTRIBUT_PROGRAMME_ID + ") REFERENCES "+ TABLE_PROGRAMME + "(" + ATTRIBUT_ID + ")"+
                        ")";
        db.execSQL(table_programme_create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addActiviteTemps(ActiviteTemps activite) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(ATTRIBUT_PROGRAMME_ID, activite.getProgramme_id());
            values.put(ATTRIBUT_TITRE, mcrypt.encrypt(activite.getTitre()));
            values.put(ATTRIBUT_TYPE_ACTIVITE, mcrypt.encrypt(activite.getType()));
            values.put(ATTRIBUT_TEMPS, activite.getTemps());
            values.put(ATTRIBUT_REPETITION, 0);
            values.put(ATTRIBUT_SERIE, 0);
            db.insert(TABLE_ACTIVITE, null, values);

            Log.d("BD", "activite temps insere");
        } catch (Exception e){
            Log.e("BD", e.getMessage());
        }
    }

    public void addActiviteRepetition(ActiviteRepetition activite) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(ATTRIBUT_PROGRAMME_ID, activite.getProgramme_id());
            values.put(ATTRIBUT_TITRE,  mcrypt.byteArrayToHexString(mcrypt.encrypt(activite.getTitre())));
            values.put(ATTRIBUT_TYPE_ACTIVITE,  mcrypt.byteArrayToHexString(mcrypt.encrypt(activite.getType())));
            values.put(ATTRIBUT_TEMPS, activite.getSerie());
            values.put(ATTRIBUT_REPETITION, activite.getRepetition());
            values.put(ATTRIBUT_SERIE, activite.getSerie());
            values.put(ATTRIBUT_TEMPS, 0);
            db.insert(TABLE_ACTIVITE, null, values);
            db.close();
            Log.d("BD", "activite repetition insere");

        } catch (Exception e){
            Log.e("BD", e.getMessage());
        }
    }

    Activite getActivite(int id) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_ACTIVITE, new String[]{ATTRIBUT_ID,
                             ATTRIBUT_PROGRAMME_ID,ATTRIBUT_TITRE, ATTRIBUT_TYPE_ACTIVITE, ATTRIBUT_TEMPS, ATTRIBUT_REPETITION, ATTRIBUT_SERIE}, ATTRIBUT_ID + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);

            if (cursor != null)
                cursor.moveToFirst();

            if(new String (mcrypt.decrypt(cursor.getString(3))).trim().equals("temps")){
                ActiviteTemps activite = new ActiviteTemps(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)),new String (mcrypt.decrypt(cursor.getString(2))).trim(),Integer.parseInt(cursor.getString(4)));
                return activite;
            } else if(new String (mcrypt.decrypt(cursor.getString(3))).trim().equals("repetition")){
                ActiviteRepetition activite = new ActiviteRepetition(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)),new String (mcrypt.decrypt(cursor.getString(2))).trim(),Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)));
                return activite;
            }

            return null;
        } catch (Exception e){
            Log.e("BD", e.getMessage());
            return null;
        }
    }

    public ArrayList<Activite> getAllActivite() {
        try {
            ArrayList<Activite> activiteList = new ArrayList<Activite>();

            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_ACTIVITE;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    if (new String(mcrypt.decrypt(cursor.getString(3))).trim().equals("temps")) {
                        ActiviteTemps activite = new ActiviteTemps(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), new String (mcrypt.decrypt(cursor.getString(2))).trim(), Integer.parseInt(cursor.getString(4)));
                        activiteList.add(activite);
                    } else if (new String(mcrypt.decrypt(cursor.getString(3))).trim().equals("repetition")) {
                        ActiviteRepetition activite = new ActiviteRepetition(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), new String(mcrypt.decrypt(cursor.getString(2))).trim(), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)));
                        activiteList.add(activite);
                    }

                } while (cursor.moveToNext());
            }

            // return contact list
            return activiteList;
        } catch (Exception e){
            Log.e("BD", e.getMessage());
            return null;
        }
    }

    public ArrayList<Activite> getActiviteFromProgramme(int id) {
        ArrayList<Activite> activiteList = new ArrayList<Activite>();

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_ACTIVITE, new String[]{ATTRIBUT_ID,
                            ATTRIBUT_PROGRAMME_ID,ATTRIBUT_TITRE, ATTRIBUT_TYPE_ACTIVITE, ATTRIBUT_TEMPS, ATTRIBUT_REPETITION, ATTRIBUT_SERIE}, ATTRIBUT_PROGRAMME_ID + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);

            if (cursor != null)
                cursor.moveToFirst();

            if (cursor.moveToFirst()) {
                do {
                    if(new String (mcrypt.decrypt(cursor.getString(3))).trim().equals("temps")){
                        ActiviteTemps activite = new ActiviteTemps(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)),new String (mcrypt.decrypt(cursor.getString(2))).trim(),Integer.parseInt(cursor.getString(4)));
                        activiteList.add(activite);
                    } else if(new String (mcrypt.decrypt(cursor.getString(3))).trim().equals("repetition")){
                        ActiviteRepetition activite = new ActiviteRepetition(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), new String (mcrypt.decrypt(cursor.getString(2))).trim(),Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)));
                        activiteList.add(activite);
                    }

                } while (cursor.moveToNext());
            }

            return activiteList;
        } catch (Exception e){
            Log.e("BD", e.getMessage());
            return null;
        }
    }

    public void deleteActivite(Activite activite) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROGRAMME, ATTRIBUT_ID + " = ?",
                new String[] { String.valueOf(activite.getId()) });
        db.close();
    }

    public void deleteTable() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("delete from "+ TABLE_ACTIVITE);
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_ACTIVITE);
            Log.d("BD", "table activite supprime");
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
