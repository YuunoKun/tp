package atas.commons.core.index;

import static atas.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IndexTest {

    @Test
    public void createOneBasedIndex() {
        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromOneBased(0));
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromOneBased(-1));

        // check equality using the same base
        assertEquals(1, Index.fromOneBased(1).getOneBased());
        assertEquals(5, Index.fromOneBased(5).getOneBased());

        // convert from one-based index to zero-based index
        assertEquals(0, Index.fromOneBased(1).getZeroBased());
        assertEquals(4, Index.fromOneBased(5).getZeroBased());
    }

    @Test
    public void createZeroBasedIndex() {
        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromZeroBased(-1));

        // check equality using the same base
        assertEquals(0, Index.fromZeroBased(0).getZeroBased());
        assertEquals(5, Index.fromZeroBased(5).getZeroBased());

        // convert from zero-based index to one-based index
        assertEquals(1, Index.fromZeroBased(0).getOneBased());
        assertEquals(6, Index.fromZeroBased(5).getOneBased());
    }

    @Test
    public void equals() {
        final Index fifthStudentIndex = Index.fromOneBased(5);

        // same values -> returns true
        assertTrue(fifthStudentIndex.equals(Index.fromOneBased(5)));
        assertTrue(fifthStudentIndex.equals(Index.fromZeroBased(4)));

        // same object -> returns true
        assertTrue(fifthStudentIndex.equals(fifthStudentIndex));

        // null -> returns false
        assertFalse(fifthStudentIndex.equals(null));

        // different types -> returns false
        assertFalse(fifthStudentIndex.equals(5.0f));

        // different index -> returns false
        assertFalse(fifthStudentIndex.equals(Index.fromOneBased(1)));
    }

    @Test
    public void toString_Test() {
        Index firstStudentIndex = Index.fromOneBased(1);
        Index secondStudentIndex = Index.fromOneBased(2);
        Index thirdStudentIndex = Index.fromOneBased(3);

        // returns zeroBasedValue in String
        assertEquals(firstStudentIndex.toString(), "0");
        assertEquals(secondStudentIndex.toString(), "1");
        assertEquals(thirdStudentIndex.toString(), "2");
    }
}
