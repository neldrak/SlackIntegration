package com.sap.iot.ch.slack.dao;

public interface BaseDAO<T> {
	public Iterable<T> findAll();

	public T save(T entity);

	public Iterable<T> save(Iterable<? extends T> entities);

	public T findOne(String id);

	public boolean exists(String id);

	public long count();

	public void delete(String id);

	public void delete(T entity);

	public void delete(Iterable<? extends T> entities);

	public void deleteAll();
}
