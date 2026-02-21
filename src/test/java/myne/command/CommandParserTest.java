package myne.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class CommandParserTest {
    @Test
    public void testExtractParameters() {
        HashMap<String, String> p = CommandParser.extractParameters(
                "summer festival     /  //from 03-05-2026 /to     6 May 2026     /extra");

        assertEquals("summer festival", p.get("first"));
        assertEquals("03-05-2026", p.get("/from"));
        assertEquals("6 May 2026", p.get("/to"));
        assertEquals("", p.get("/extra"));

        assertNull(p.get("from"));

        assertEquals(4, p.size());
    }
}
