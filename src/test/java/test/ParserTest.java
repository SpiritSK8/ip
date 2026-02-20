package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import emu.EmuException;
import emu.Parser;

public class ParserTest {

    @Test
    void testHandleDeadline() throws EmuException {
        // valid case
        String[] result = Parser.parseDeadline("finish homework /by tomorrow");
        assertEquals("finish homework", result[0]);
        assertEquals("tomorrow", result[1]);

        // missing /by
        try {
            Parser.parseDeadline("finish homework tomorrow");
            fail("Expected EmuException");
        } catch (EmuException e) {
            assertEquals("You forgot to include /by in your deadline task!", e.realMessage());
        }

        // empty description
        try {
            Parser.parseDeadline("/by tomorrow");
            fail("Expected EmuException");
        } catch (EmuException e) {
            assertEquals("You can't make a deadline without a description and a by date silly!!", e.realMessage());
        }

        // empty /by
        try {
            Parser.parseDeadline("finish /by ");
            fail("Expected EmuException");
        } catch (EmuException e) {
            assertEquals("You can't make a deadline without a description and a by date silly!!", e.realMessage());
        }

        // empty
        try {
            Parser.parseDeadline("  ");
            fail("Expected EmuException");
        } catch (EmuException e) {
            assertEquals("You forgot to include /by in your deadline task!", e.realMessage());
        }
    }

    @Test
    void testHandleEvent() throws EmuException {
        // valid case
        String[] result = Parser.parseEvent("meeting /from Mon /to Tue");
        assertEquals("meeting", result[0]);
        assertEquals("Mon", result[1]);
        assertEquals("Tue", result[2]);

        // /to and /from swapped
        try {
            Parser.parseEvent("meeting /to Tue /from Mon");
            fail("Expected EmuException");
        } catch (EmuException e) {
            assertEquals("Incorrect format! Use: event (desc) /from (start) /to (end)", e.realMessage());
        }

        // missing /to
        try {
            Parser.parseEvent("meeting /from Mon");
            fail("Expected EmuException");
        } catch (EmuException e) {
            assertEquals("Incorrect format! Use: event (desc) /from (start) /to (end)", e.realMessage());
        }

        // missing /from
        try {
            Parser.parseEvent("meeting Mon /to Tue");
            fail("Expected EmuException");
        } catch (EmuException e) {
            assertEquals("Incorrect format! Use: event (desc) /from (start) /to (end)", e.realMessage());
        }

        // empty fields
        try {
            Parser.parseEvent(" /from  /to ");
            fail("Expected EmuException");
        } catch (EmuException e) {
            assertEquals("You can't make an event without a description, a from date, and a to date silly!!", e.realMessage());
        }

        // empty
        try {
            Parser.parseEvent("  ");
            fail("Expected EmuException");
        } catch (EmuException e) {
            assertEquals("Incorrect format! Use: event (desc) /from (start) /to (end)", e.realMessage());
        }
    }
}