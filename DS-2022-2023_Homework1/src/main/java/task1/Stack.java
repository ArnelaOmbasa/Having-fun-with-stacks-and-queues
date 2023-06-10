package task1;

public class Stack<Item> {
    private Queue<Item> q1;
    private Queue<Item> q2;

    public Stack() {
        q1 = new Queue<>();
        q2 = new Queue<>();
    }

    public void push(Item data) {
        // Move all elements from q1 to q2
        while (!q1.isEmpty()) {
            q2.enqueue(q1.dequeue());
        }

        // Enqueue the new element in q1
        q1.enqueue(data);

        // Move back all elements from q2 to q1
        while (!q2.isEmpty()) {
            q1.enqueue(q2.dequeue());
        }
    }

    public Item pop() {
        if (q1.isEmpty()) {
            throw new IndexOutOfBoundsException("Stack is empty.");
        }

        // Dequeue the top element from q1, which is the item to be popped
        return q1.dequeue();
    }


    public Item peek() {
        if (q1.isEmpty()) {
            throw new IndexOutOfBoundsException("Stack is empty.");
        }
        // Move all elements from q1 to q2
        while (!q1.isEmpty()) {
            q2.enqueue(q1.dequeue());
        }
        // Get the top element from q1, which is the item to be peeked
        Item topElement= q2.dequeue();

        // Enqueue the item back to q1
        q1.enqueue(topElement);

        // Move back all elements from q2 to q1
        while (!q2.isEmpty()) {
            q1.enqueue(q2.dequeue());
        }
        return topElement;

    }

    public int size() {
         return q1.size();
    }

    public boolean isEmpty() {
        return q1.isEmpty();

    }
}
