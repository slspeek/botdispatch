/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.simplejpadao;

import com.google.inject.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author steven
 */
public class SimpleDAONamedIdImpl<T extends HasSetId, U extends T> extends DAOBase<T, U> implements SimpleDAONamedId<T> {

    public SimpleDAONamedIdImpl(Class<U> entityType, Provider<EntityManager> emProvider) {
        super(entityType, emProvider);
    }

    @Override
    public T findOrNew(Object key) {
        T entity = find(key);
        if (entity == null) {
            entity = newEntity(key);
        }
        return entity;
    }

    @Override
    public T newEntity(Object key) {
        T entity;
        try {
            entity = entityType.newInstance();
            entity.setId(key);
            return entity;
        } catch (InstantiationException ex) {
            Logger.getLogger(SimpleDAONamedIdImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SimpleDAONamedIdImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
