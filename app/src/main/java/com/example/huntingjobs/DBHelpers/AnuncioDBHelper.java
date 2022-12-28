package com.example.huntingjobs.DBHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.huntingjobs.Modelo.Anuncio;

import java.util.ArrayList;

public class AnuncioDBHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "DBAnuncios";
    private final static String TABLE_NAME = "anuncios";
    private final static int DB_VERSION = 1;


    //CONSTANTES DAS TABELAS DA BASE DE DADOS
    private final static String ID = "id";
    private final static String ID_EMPRESA = "id_Empresa";
    private final static String TITULO = "titulo";
    private final static String DESCRICAO = "descricao";
    private final static String PERFIL_PROCURADO = "perfil_procurado";
    private final static String CATEGORIA = "categoria";
    private SQLiteDatabase database; //Instancia da SQLiteDatabase -- usado para CRUDS


    public AnuncioDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQLtable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( " +
                ID + " INTEGER PRIMARY KEY, " +
                ID_EMPRESA + " INTEGER NOT NULL, " +
                TITULO + " TEXT NOT NULL, " +
                DESCRICAO + " TEXT NOT NULL, " +
                PERFIL_PROCURADO + " TEXT NOT NULL, " +
                CATEGORIA + " INTEGER );";

        sqLiteDatabase.execSQL(SQLtable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        database.execSQL("DROP TABLE IF EXISTS " + DB_NAME);

        this.onCreate(database);
    }

    public Anuncio adicionarAnunciosDB(Anuncio anuncio) {

        ContentValues valoresAnuncio = new ContentValues();
        valoresAnuncio.put(ID, anuncio.getId());
        valoresAnuncio.put(ID_EMPRESA, anuncio.getId_empresa());
        valoresAnuncio.put(TITULO, anuncio.getTitulo());
        valoresAnuncio.put(DESCRICAO, anuncio.getDescricao());
        valoresAnuncio.put(PERFIL_PROCURADO, anuncio.getPerfil_procurado());
        valoresAnuncio.put(CATEGORIA, anuncio.getCategoria());

        int id = (int) this.database.insert(TABLE_NAME, null, valoresAnuncio);
        if (id > -1) {
            anuncio.setId(id);
            return anuncio;
        }
        return null;
    }

    public ArrayList<Anuncio> getAllAnunciosBD() {
        ArrayList<Anuncio> anuncios = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_NAME,
                new String[]{ID, ID_EMPRESA, TITULO, DESCRICAO, PERFIL_PROCURADO, CATEGORIA},
                null, null, null, null, null);


        if (cursor.moveToFirst()) {
            do {
                Anuncio novoAnuncio = new Anuncio(cursor.getInt(0),  //get ID
                        cursor.getInt(1),  //get id_empresa
                        cursor.getString(2),  //get titulo
                        cursor.getString(3),  //get descricao
                        cursor.getString(4),  //get perfil_procurado
                        cursor.getInt(5)
                );  //get categoria

                anuncios.add(novoAnuncio); // adicionar anuncios á bd
            }while (cursor.moveToNext()); //Enquando o cursor conseguir mover para o próximo
        }

        return anuncios;
    }


    public void removeAllAnunciosLBD() {
        database.delete(TABLE_NAME,null,null);
    }


}
