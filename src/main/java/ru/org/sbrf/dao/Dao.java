package ru.org.sbrf.dao;

import ru.org.sbrf.exception.AddObjectException;

import java.util.List;

public interface Dao<T> {

    void add(T object) throws AddObjectException;

    T get(long id);

    List<T> getAll();

    void delete(long id);

    void update(T object);
}
