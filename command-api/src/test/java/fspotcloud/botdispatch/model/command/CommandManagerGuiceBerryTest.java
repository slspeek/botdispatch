package fspotcloud.botdispatch.model.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import net.customware.gwt.dispatch.shared.Action;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.guiceberry.junit4.GuiceBerryRule;
import com.google.inject.Provider;

import fspotcloud.botdispatch.model.api.Command;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.api.NullCommand;
import fspotcloud.botdispatch.test.TestAction;
import fspotcloud.botdispatch.test.TestAsyncCallback;

public class CommandManagerGuiceBerryTest {

    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(EmptyGuiceBerryEnv.class);
    @Inject
    Commands commandManager;
    @Inject
    Provider<Commands> provider;
    Action<?> action;
    TestAsyncCallback callback = new TestAsyncCallback();

    @Before
    public void setUp() {
        action = new TestAction("Jim");
        System.out.println("ye");
        commandManager.deleteAll();
    }

    @Test
    public void testGetAndLockFirst() {
        Command cmdDO = commandManager.createAndSave(action, callback);
        Command retrieved = commandManager.getAndLockFirstCommand();
        assertTrue(retrieved.isLocked());
        assertEquals(cmdDO.getAction(), retrieved.getAction());
        assertNotNull(retrieved.getCallback());
        assertEquals(TestAsyncCallback.class, retrieved.getCallback()
        		.getClass());
        retrieved = commandManager.getAndLockFirstCommand();
        assertEquals(NullCommand.class, retrieved.getClass());
    }

    @Test
    public void testGetAndLockFirstProvied() {
        Command cmdDO = provider.get().createAndSave(action, callback);
        Command retrieved = provider.get().getAndLockFirstCommand();
        assertTrue(retrieved.isLocked());
        assertEquals(cmdDO.getAction(), retrieved.getAction());
		assertNotNull(retrieved.getCallback());
		assertEquals(TestAsyncCallback.class, retrieved.getCallback()
				.getClass());
        retrieved = provider.get().getAndLockFirstCommand();
        assertEquals(NullCommand.class, retrieved.getClass());
    }
    
     @Test
    public void GetAndLockFirst_like_integration() {
        Command retrieved = commandManager.getAndLockFirstCommand();
        assertEquals(NullCommand.class, retrieved.getClass());
        commandManager.createAndSave(action, callback);
        commandManager.createAndSave(action, callback);
        commandManager.createAndSave(action, callback);
        commandManager.getAndLockFirstCommand();
        
    }

    @Test
    public void testCreate() {
        Command cmdDO = commandManager.createAndSave(action, callback);
    }

    @Test
    public void testCountZero() {
        assertEquals(0, commandManager.getCountUnderAThousend());
    }

    @Test
    public void testCountTwo() {
        commandManager.createAndSave(action, callback);
        commandManager.createAndSave(action, callback);
        assertEquals(2, commandManager.getCountUnderAThousend());
    }

    @Test
    public void testCountTwoProvided() {
        provider.get().createAndSave(action, callback);
        provider.get().createAndSave(action, callback);
        assertEquals(2, provider.get().getCountUnderAThousend());
    }

    @Test
    public void testGetById() {
        Command cmd = commandManager.createAndSave(action, callback);
        System.out.println(cmd);

        long callbackId = cmd.getId();
        Command retrieved = commandManager.getById(callbackId);
        System.out.println(retrieved);
        assertNotNull(retrieved.getCtime());
		assertNotNull(retrieved.getCallback());
        assertEquals(TestAsyncCallback.class, retrieved.getCallback().getClass());
    }

    @Test
    public void testDelete() {
        Command cmd = commandManager.createAndSave(action, callback);
        commandManager.delete(cmd);
    }

    @Test
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
