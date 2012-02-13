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
public interface SimpleDAONamedId<T extends HasSetId> extends AbstractDAO<T> {

    T findOrNew(Object key);
    
    T newEntity(Object key);
    
}
