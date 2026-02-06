package myne.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testEquality() {
        Deadline d1 = new Deadline("test", LocalDate.of(2026, 6, 7));
        Deadline d2 = new Deadline("test", LocalDate.of(2026, 6, 7));
        assertEquals(d1, d2);

        d2.mark();
        assertNotEquals(d1, d2);

        Deadline d3 = new Deadline("test", LocalDate.of(2026, 6, 9));
        assertNotEquals(d1, d3);
    }
}
