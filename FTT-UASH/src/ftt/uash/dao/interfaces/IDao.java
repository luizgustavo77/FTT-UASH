package ftt.uash.dao.interfaces;

import java.util.ArrayList;

public interface IDao<T> {
	public int insert(T obj);
	
	public int update(T obj);

	public void delete(int id);

	public T find(T obj);

	public ArrayList<T> findAll();
}
