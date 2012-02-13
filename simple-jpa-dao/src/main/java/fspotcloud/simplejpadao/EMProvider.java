/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.simplejpadao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author steven
 */
public class EMProvider implements Provider<EntityManager> {

    private final String name;
    private static EntityManagerFactory emfInstance;

    @Inject
    public EMProvider(@Named("persistence-unit") String name) {
        this.name = name;
        if (emfInstance == null) {
            this.emfInstance = Persistence.createEntityManagerFactory(name);
        }
    }

    @Override
    public EntityManager get() {
        return emfInstance.createEntityManager();
    }
}
