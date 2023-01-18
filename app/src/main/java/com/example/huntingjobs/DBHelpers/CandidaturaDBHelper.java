package com.example.huntingjobs.DBHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.huntingjobs.Modelo.Candidatura;

import java.util.ArrayList;

public class CandidaturaDBHelper extends SQLiteOpenHelper {


    //Constantes
    private static final String TABLE_NAME = "candidaturas";

    //Constantes dos campos das candidaturas
    private static final String ID = "id";
    private static final String ID_USER = "id_user";
    private static final String ID_ANUNCIO = "id_anuncio";
    private static final String MENSAGEM = "mensagem";
    private static final String DATA = "data_candidatura";


    //Instancia da SQLiteDatabase -- usado para CRUDS
    SQLiteDatabase database;

    public CandidaturaDBHelper(Context context) {
        super(context, "DBJobs", null, 3);

       this.database = this.getWritableDatabase();
       this.onCreate(database);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQLtable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( " +
                ID + " INTEGER PRIMARY KEY, " +
                ID_USER + " INTEGER NOT NULL, " +
                ID_ANUNCIO + " INTEGER NOT NULL, " +
                MENSAGEM + " TEXT NOT NULL, " +
                DATA + " TEXT );";


        sqLiteDatabase.execSQL(SQLtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
            database.execSQL(sql);


            this.onCreate(database);
    }


    public Candidatura adicionarCandidaturasDB(Candidatura candidatura) {

        ContentValues valoresCandidatura = new ContentValues();
        valoresCandidatura.put(ID, candidatura.getId());
        valoresCandidatura.put(ID_USER, candidatura.getId_user());
        valoresCandidatura.put(ID_ANUNCIO, candidatura.getId_anuncio());
        valoresCandidatura.put(MENSAGEM, candidatura.getMensagem());
        valoresCandidatura.put(DATA, candidatura.getData());


        int id = (int) this.database.insert(TABLE_NAME, null, valoresCandidatura);
        if (id > -1) {
            candidatura.setId(id);
            return candidatura;
        }
        return null;
    }

    public boolean removerCandidatura(long id) {
        int nDelete = database.delete(TABLE_NAME, ID + " = ?", new String[]{"" + id});

        return nDelete > 0;
    }


    public ArrayList<Candidatura> getAllCandidaturasBD() {
        ArrayList<Candidatura> candidaturas = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_NAME,
                new String[]{ID, ID_USER, ID_ANUNCIO, MENSAGEM, DATA},
                null, null, null, null, null);


        if (cursor.moveToFirst()) {
            do {
                Candidatura novaCandidatura;
                novaCandidatura = new Candidatura(
                        cursor.getInt(0),  //get ID
                        cursor.getInt(1),  //get id_user
                        cursor.getInt(2),  //get id_anuncio
                        cursor.getString(3),  //get mensagem
                        cursor.getString(4)  //get data
                );

                candidaturas.add(novaCandidatura); // adicionar candidaturas á bd
            }while (cursor.moveToNext()); //Enquando o cursor conseguir mover para a próxima
        }

        return candidaturas;
    }

    public void removeAllCandidaturasLBD() {
        database.delete(TABLE_NAME,null,null);
    }

}
