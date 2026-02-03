package myne.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class EventTest {
    @Test
    public void testEquality() {
        Event e1 = new Event("test", LocalDate.of(2026, 6, 7), LocalDate.of(2026, 6, 8));
        Event e2 = new Event("test", LocalDate.of(2026, 6, 7), LocalDate.of(2026, 6, 8));
        assertEquals(e1, e2);

        e2.mark();
        assertNotEquals(e1, e2);

        Event e3 = new Event("test", LocalDate.of(2026, 6, 7), LocalDate.of(2026, 6, 9));
        assertNotEquals(e1, e3);
    }
}
