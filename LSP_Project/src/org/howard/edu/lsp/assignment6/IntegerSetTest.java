package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class IntegerSetTest {

    // ---------- Basic functionality ----------

	@Test
    public void testAddAndToString() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        set.add(1);  // duplicate ignored
        assertEquals("[1, 2]", set.toString());
    }

    @Test
    public void testClearAndIsEmpty() {
        IntegerSet set = new IntegerSet();
        set.add(10);
        set.clear();
        assertTrue(set.isEmpty());
        assertEquals(0, set.length());
    }

    @Test
    public void testLength() {
        IntegerSet set = new IntegerSet();
        set.add(5);
        set.add(6);
        assertEquals(2, set.length());
    }

    @Test
    public void testContains() {
        IntegerSet set = new IntegerSet();
        set.add(3);
        assertTrue(set.contains(3));
        assertFalse(set.contains(9));
    }

    @Test
    public void testEquals() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();
        set1.add(1); set1.add(2);
        set2.add(2); set2.add(1);
        assertTrue(set1.equals(set2));

        set2.add(3);
        assertFalse(set1.equals(set2));
    }

    @Test
    public void testLargestAndSmallest() {
        IntegerSet set = new IntegerSet();
        set.add(10);
        set.add(3);
        set.add(7);
        assertEquals(10, set.largest());
        assertEquals(3, set.smallest());
    }

    @Test
    public void testLargestThrowsException() {
        IntegerSet set = new IntegerSet();
        assertThrows(IllegalStateException.class, () -> set.largest());
    }

    @Test
    public void testSmallestThrowsException() {
        IntegerSet set = new IntegerSet();
        assertThrows(IllegalStateException.class, () -> set.smallest());
    }

    @Test
    public void testRemove() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        set.remove(1);
        assertFalse(set.contains(1));
        assertEquals("[2]", set.toString());
    }

    @Test
    public void testUnion() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();
        set1.add(1); set1.add(2);
        set2.add(2); set2.add(3);
        set1.union(set2);
        assertTrue(set1.contains(3));
        assertEquals(3, set1.length());
    }

    @Test
    public void testIntersect() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(4);
        set1.intersect(set2);
        assertEquals("[2]", set1.toString());
    }

    @Test
    public void testDiff() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2);
        set1.diff(set2);
        assertEquals("[1, 3]", set1.toString());
    }

    @Test
    public void testComplement() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();
        set1.add(1); set1.add(2);
        set2.add(2); set2.add(3); set2.add(4);
        set1.complement(set2);
        assertEquals("[3, 4]", set1.toString());
    }

    // ---------- Hidden edge cases ----------

    /** Empty set behavior for all key methods. */
    @Test
    public void testEmptySetOperations() {
        IntegerSet empty = new IntegerSet();
        IntegerSet other = new IntegerSet();
        other.add(1); other.add(2);

        // union with empty
        empty.union(other);
        assertEquals("[1, 2]", empty.toString());

        // diff from empty (should stay empty)
        IntegerSet empty2 = new IntegerSet();
        empty2.diff(other);
        assertTrue(empty2.isEmpty());

        // intersection with empty (should become empty)
        other.intersect(new IntegerSet());
        assertTrue(other.isEmpty());
    }

    /** Duplicates should not be added; length should reflect unique count. */
    @Test
    public void testDuplicatesNotAdded() {
        IntegerSet set = new IntegerSet();
        set.add(5);
        set.add(5);
        set.add(5);
        assertEquals(1, set.length());
        assertEquals("[5]", set.toString());
    }

    /** Self-operations (union, diff, intersect, complement) should behave correctly. */
    @Test
    public void testSelfOperations() {
        IntegerSet set = new IntegerSet();
        set.add(1); set.add(2); set.add(3);

        set.union(set);         // should stay the same
        assertEquals("[1, 2, 3]", set.toString());

        set.intersect(set);     // should stay the same
        assertEquals("[1, 2, 3]", set.toString());

        set.diff(set);          // should become empty
        assertTrue(set.isEmpty());

        set.add(1); set.add(2);
        set.complement(set);    // complement of self = empty
        assertTrue(set.isEmpty());
    }

    /** Mutation check: operations should modify this, not create a new object. */
    @Test
    public void testMutationBehavior() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1); set1.add(2);
        IntegerSet set2 = new IntegerSet();
        set2.add(3);

        // Save reference to compare later
        IntegerSet sameRef = set1;

        set1.union(set2);  // modifies same object
        assertSame(sameRef, set1);  // same reference
        assertEquals("[1, 2, 3]", set1.toString());

        // Ensure diff modifies in place too
        set1.diff(set2);
        assertEquals("[1, 2]", set1.toString());
        assertSame(sameRef, set1);
    }
}

