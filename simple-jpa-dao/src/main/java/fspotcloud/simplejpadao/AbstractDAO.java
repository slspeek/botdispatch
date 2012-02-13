/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.simplejpadao;

import java.util.List;

/**
 *
 * @author steven
 */
public interface AbstractDAO<T> {

    List<T> findAll(int max);

    List<Object> findAllKeys(int max);

    void save(T entity);

    void saveAll(List<T> entityList);

    T find(Object key);

    void delete(T entity);

    void deleteAll(List<T> entityList);

    boolean deleteBulk(int max);

    boolean isEmpty();
    
    int count(int max);
}
