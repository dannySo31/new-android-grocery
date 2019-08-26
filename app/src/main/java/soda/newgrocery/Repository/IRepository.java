package soda.newgrocery.Repository;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public interface IRepository<T> {
    List<T> GetAll();
    long Update(T entity);
    long Insert(T entity, boolean closeContext);
    void Delete(T entity);
    long Insert(T entity, boolean closeContext, SQLiteDatabase db);
    T Get(long id);
    void Close();
}

