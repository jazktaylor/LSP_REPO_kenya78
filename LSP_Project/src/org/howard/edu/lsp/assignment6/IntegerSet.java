package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntegerSet {
	private List<Integer> set = new ArrayList<Integer>();

	    /** Clears all elements from the set. */
		public void clear() {
	        set.clear();
	    }

	    /** Returns the number of elements in the set. */
	    public int length() {
	        return set.size();
	    }

	    /**
	     * Returns true if two sets contain the same elements (order-independent).
	     * @param o another object to compare
	     * @return true if sets are equal, false otherwise
	     */
	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof IntegerSet)) return false;
	        IntegerSet other = (IntegerSet) o;
	        return this.set.containsAll(other.set) && other.set.containsAll(this.set);
	    }

	    /** Returns true if the set contains the specified value. */
	    public boolean contains(int value) {
	        return set.contains(value);
	    }

	    /** Returns the largest element in the set; throws if the set is empty. */
	    public int largest() {
	        if (set.isEmpty()) {
	            throw new IllegalStateException("Set is empty");
	        }
	        return Collections.max(set);
	    }

	    /** Returns the smallest element in the set; throws if the set is empty. */
	    public int smallest() {
	        if (set.isEmpty()) {
	            throw new IllegalStateException("Set is empty");
	        }
	        return Collections.min(set);
	    }

	    /** Adds an item to the set if not already present. */
	    public void add(int item) {
	        if (!set.contains(item)) {
	            set.add(item);
	        }
	    }

	    /** Removes an item from the set if present. */
	    public void remove(int item) {
	        set.remove(Integer.valueOf(item));
	    }

	    /** Modifies this set to contain all unique elements from both sets. */
	    public void union(IntegerSet other) {
	        for (int value : other.set) {
	            if (!set.contains(value)) {
	                set.add(value);
	            }
	        }
	    }

	    /** Modifies this set to contain only elements found in both sets. */
	    public void intersect(IntegerSet other) {
	        set.retainAll(other.set);
	    }

	    /** Modifies this set to remove elements found in the other set. */
	    public void diff(IntegerSet other) {
	        set.removeAll(other.set);
	    }

	    /** Modifies this set to become the complement (other \ this). */
	    public void complement(IntegerSet other) {
	        List<Integer> temp = new ArrayList<Integer>(other.set);
	        temp.removeAll(this.set);
	        set.clear();
	        set.addAll(temp);
	    }

	    /** Returns true if the set has no elements. */
	    public boolean isEmpty() {
	        return set.isEmpty();
	    }

	    /** Returns a string in the format [1, 2, 3]. */
	    @Override
	    public String toString() {
	        return set.toString();
	    }
}
