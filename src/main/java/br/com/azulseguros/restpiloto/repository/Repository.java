package br.com.azulseguros.restpiloto.repository;

import java.util.List;

public interface Repository<T> {
	
    void add(T item);

    void update(T item);

    void remove(T item);

    void remove(Integer  id);

    List<T> query(Integer  id);
}
