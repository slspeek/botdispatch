package fspotcloud.botdispatch.model.command;

import javax.persistence.EntityManager;

import junit.framework.TestSuite;
import net.customware.gwt.dispatch.shared.Action;

import com.google.inject.Provider;

import fspotcloud.botdispatch.model.api.Command;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.api.NullCommand;
import fspotcloud.botdispatch.test.TestAction;
import fspotcloud.botdispatch.test.TestAsyncCallback;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.TestCase;

public class CommandManagerTest extends TestCase {

    private static final EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("derby");
    Provider<EntityManager> pmProvider = new Provider<EntityManager>() {

        @Override
        public EntityManager get() {
            System.out.println("ENTITY MANAGER CREATED");
            return emfInstance.createEntityManager();
        }
    };

    {
        System.setProperty("appengine.orm.duplicate.emf.exception", "true");
    }

    public static TestSuite suite() {
        return new TestSuite(CommandManagerTest.class);
    }
    Commands commandManager;
    Action<?> action;
    TestAsyncCallback callback = new TestAsyncCallback();

    public void setUp() throws Exception {
        super.setUp();
        action = new TestAction("Jim");
        commandManager = new CommandManager(pmProvider, 3);
        commandManager.deleteAll();
    }

    public void testGetAndLockFirst() {
        Command cmdDO = commandManager.createAndSave(action, callback);
        Command retrieved = commandManager.getAndLockFirstCommand();
        assertTrue(retrieved.isLocked());
        assertEquals(cmdDO.getAction(), retrieved.getAction());
        //assertNotNull(retrieved.getCallback());
        //assertEquals(TestAsyncCallback.class, retrieved.getCallback().getClass());
        retrieved = commandManager.getAndLockFirstCommand();
        assertEquals(NullCommand.class, retrieved.getClass());

    }

    public void testCreate() {
        Command cmdDO = commandManager.createAndSave(action, callback);
        assertEquals(1, commandManager.getCountUnderAThousend());
    }

    public void testCountZero() {
        assertEquals(0, commandManager.getCountUnderAThousend());
    }

    public void testCountTwo() {
        commandManager.createAndSave(action, callback);
        commandManager.createAndSave(action, callback);
        assertEquals(2, commandManager.getCountUnderAThousend());
    }

    public void testGetById() {
        Command cmd = commandManager.createAndSave(action, callback);
        System.out.println(cmd);

        long callbackId = cmd.getId();
        Command retrieved = commandManager.getById(callbackId);
        System.out.println(retrieved);
        assertNotNull(retrieved.getCtime());
        //assertNotNull(retrieved.getCallback());

        //assertEquals(TestAsyncCallback.class, retrieved.getCallback().getClass());

    }

    public void testDelete() {
        Command cmd = commandManager.createAndSave(action, callback);
        commandManager.delete(cmd);
    }

    public void testDeleteAll() {
        commandManager.createAndSave(action, callback);
        commandManager.createAndSave(action, callback);
        commandManager.createAndSave(action, callback);
        commandManager.createAndSave(action, callback);
        assertEquals(4, commandManager.getCountUnderAThousend());
        commandManager.deleteAll();
        assertEquals(1, commandManager.getCountUnderAThousend());
    }
}
