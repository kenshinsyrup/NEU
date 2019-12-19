public interface Stack<E> {

  Stack push(E element); // Adds an element to the Stack

  Stack pop(); // Removes the most recently added element

  E top(); // Returns but does not remove the most recent element.
}
