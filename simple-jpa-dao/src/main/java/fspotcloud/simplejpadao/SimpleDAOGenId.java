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
public interface SimpleDAOGenId<T extends HasId> extends AbstractDAO<T> {
    
    T newEntity();
    
    T newPeristentEntity();
}
