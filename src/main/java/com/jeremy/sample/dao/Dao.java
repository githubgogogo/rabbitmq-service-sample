package com.jeremy.sample.dao;

/**
 * Created by Jeremy Yang on 27/07/2016.
 */
public interface Dao<T>
{

    /**
     * Gets Object by Id
     *
     * @param id the id
     * @return the by id
     */
    T getById(Long id);

    /**
     * Save an entity.
     *
     * @param entity the entity
     * @return the t
     */
    T save(T entity);

    /**
     * Delete an entity
     *
     * @param entity the entity
     */
    void delete(T entity);
}
