/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}

		Node current = first;
		int i = 0;
		
		while (current != null) {
			if (i == index) {
				return current;
			}
			
			current = current.next;
			i ++;
			
		}

		return null;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		
		Node newElement = new Node(block);

		if (index == 0) {
			addFirst(block);
		}

		else if (index == size) {
			addLast(block);
		}

		else {
			Node prev = getNode(index - 1);
			newElement.next = prev.next;
			prev.next = newElement; 
			size ++;
		}
		
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		Node newElement = new Node(block);
		
		if (size == 0) {
			first = newElement;
			last = newElement;
		} 
		
		else {
			last.next = newElement;
			last = newElement;
		}
		
		size ++;
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		Node newElement = new Node(block);
		
		if (size == 0) {
			first = newElement;
			last = newElement;
		} 	
		
		else {
			newElement.next = first;
			first = newElement;
		}
		
		size ++;

	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}

		Node current = first;
		int i = 0;
		
		while (current != null) {
			if (i == index) {
				return current.block;
			}
			
			current = current.next;
			i ++;
			
		}

		return null;
	}		

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		Node current = first;
		int index = 0;
		while (current != null) {
			if (current.block.equals(block)) {
				return index;
			}
			
			current = current.next;
			index ++;
		}

		return -1; // The block wasn't found
	}
	public void removeFirst () {
		if (size > 0) {
			if (size == 1) {
				first = null;
				last = null;
			}
			else {
				first = first.next;
			}
			size --;
		}	
	}

	public void removeLast () {
		if (size > 0) {
			if (size == 1) {
				first = null;
				last = null;
			}
			else {
				Node beforeLast = getNode(size - 2);
				beforeLast.next = null;
				last = beforeLast;
			}
			size --;
		}	
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		if (node == null) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
		if (node == first) {
			removeFirst();
		} 
		
		else if (node == last) {
			removeLast();
		} 
		
		else {
			Node current = first;
			while (current != null) {
				if (current.next == node) {
					current.next = node.next;
					size --;
					break;
				}
				current = current.next;
			}

			if (current == null || node == null) {
				throw new IllegalArgumentException("index must be between 0 and size");
			}
		}
	}
	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException(
					" NullPointerException!");
		}

		if (index == 0) { // remove the first element 
			removeFirst();
		} 
		
		else if (index == size - 1) { // remove the last element 
			removeLast();
		}

		else {
			Node prev = null;
			Node current = first;

			for (int i = 0; i < index; i++) {
				prev = current;
				current = current.next;
			}

			prev.next = current.next; // removing the element at the given index 
			size --;
			
		}
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {

		if (block.equals(first.block)) {
			removeFirst();
		} 
		
		else if (block.equals(last.block)) {
			removeLast();
		} 
		
		else {
			Node prev = null;
			Node current = first;
			while (current != null) {
				if (current.block.equals(block)) {
					prev.next = current.next; // skip current 					
					size --;
					break;
				}
				prev = current;
				current = current.next;
			}

			if (current == null || block == null) {
				throw new IllegalArgumentException("index must be between 0 and size");
			}
		}
	}		

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		String str = "";
		Node current = first;
		while (current != null) {
			str += "(" + current.block.baseAddress + " , " + current.block.length + ")";
			if (current.next != null) {
				str += " ";
			}
			current = current.next;
		}
		return str;
	}
}