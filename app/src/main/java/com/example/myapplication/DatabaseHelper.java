package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Configurações do Banco de Dados
    private static final String DATABASE_NAME = "Login.db";
    private static final int DATABASE_VERSION = 1;

    // Configurações da Tabela de Usuários
    private static final String TABLE_USERS = "usuarios";
    private static final String COL_1_USERNAME = "username";
    private static final String COL_2_PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL para criar a tabela de usuários
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " ("
                + COL_1_USERNAME + " TEXT PRIMARY KEY,"
                + COL_2_PASSWORD + " TEXT)";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Remove a tabela antiga e cria uma nova (em um cenário real, você faria um 'ALTER TABLE')
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // --- Métodos de CRUD (Create, Read, Update, Delete) ---

    // Insere um novo usuário
    public boolean insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1_USERNAME, username);
        contentValues.put(COL_2_PASSWORD, password);

        long result = db.insert(TABLE_USERS, null, contentValues);
        return result != -1; // Retorna true se a inserção foi bem-sucedida
    }

    // Verifica se as credenciais existem no banco
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE "
                + COL_1_USERNAME + " = ? AND " + COL_2_PASSWORD + " = ?", new String[]{username, password});

        boolean userExists = cursor.getCount() > 0;
        cursor.close();
        return userExists;
    }
}