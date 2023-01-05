package com.example.huntingjobs.DBHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.huntingjobs.Modelo.Empresa;

import java.util.ArrayList;

public class EmpresaDBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "empresas";
    public static final String ID = "id";
    public static final String ID_ADMIN = "idAdmin";
    public static final String NOME = "Nome";
    public static final String DESCRICAO = "descricao";
    public static final String CONTACTO_TELEFONE = "contacto_telefone";
    public static final String CONTACTO_TELEMOVEL = "contacto_telemovel";
    public static final String MORADA = "morada";
    public static final String EMAIL = "email";
    public SQLiteDatabase database;

    public EmpresaDBHelper(Context context) {
        super(context, AnuncioDBHelper.DB_NAME, null, AnuncioDBHelper.DB_VERSION);

        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQLtable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( " +
                ID + " INTEGER PRIMARY KEY, " +
                ID_ADMIN + " INTEGER NOT NULL, " +
                NOME + " TEXT NOT NULL, " +
                DESCRICAO + " TEXT NOT NULL, " +
                CONTACTO_TELEFONE + " INTEGER NOT NULL, " +
                CONTACTO_TELEMOVEL + " INTEGER NOT NULL, " +
                MORADA + " INTEGER NOT NULL, " +
                EMAIL + " TEXT );";

        sqLiteDatabase.execSQL(SQLtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        database.execSQL("DROP TABLE IF EXISTS " + AnuncioDBHelper.DB_NAME);

        this.onCreate(database);
    }


    public Empresa adicionarEmpresasBD(Empresa empresa){

        ContentValues valoresEmpresa = new ContentValues();
        valoresEmpresa.put(ID, empresa.getId());
        valoresEmpresa.put(ID_ADMIN, empresa.getIdAdmin());
        valoresEmpresa.put(NOME, empresa.getNomeEmpresa());
        valoresEmpresa.put(DESCRICAO, empresa.getDescricao());
        valoresEmpresa.put(CONTACTO_TELEFONE, empresa.getContacto_telefone());
        valoresEmpresa.put(CONTACTO_TELEMOVEL, empresa.getContacto_telemovel());
        valoresEmpresa.put(MORADA, empresa.getMorada());
        valoresEmpresa.put(EMAIL, empresa.getEmail());

        int id = (int)this.database.insert(TABLE_NAME, null, valoresEmpresa);
        if (id > -1) {
            empresa.setId(id);
            return empresa;
        }
        return null;

    }


    public ArrayList<Empresa> getAllEmpresasBD(){
        ArrayList<Empresa> empresas = new ArrayList<>();

        Cursor cursor =  this.database.query(TABLE_NAME,
                new String[]{ID,ID_ADMIN,NOME,DESCRICAO,CONTACTO_TELEFONE,CONTACTO_TELEMOVEL,MORADA,EMAIL},
                null,null,null,null,null);


        if (cursor.moveToFirst()){
            do {
                Empresa empresa = new Empresa(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        cursor.getString(6),
                        cursor.getString(7)
                );

                empresas.add(empresa);
            }while (cursor.moveToNext());
        }
        return empresas;
    }

    public void removeAllEmpresasLBD(){
        //database.delete(TABLE_NAME, null,null);
    }
}
