package atas.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.ui.Tab;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, null, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, null, false)));

        // different switchTab value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, Tab.STUDENTS, true)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, null, true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, null, false).hashCode());

        // different switchTab value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, Tab.STUDENTS, true)));

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, null, true).hashCode());
    }

    @Test
    public void getters() {
        CommandResult commandResult = new CommandResult("feedback", true, Tab.CURRENT, true);

        assertTrue(commandResult.isShowHelp());
        assertTrue(commandResult.isSwitchTab());
        assertTrue(commandResult.isExit());
        assertEquals(Tab.CURRENT, commandResult.getTab());
    }
}