/**
 * Created by Chris on 3/31/2017.
 */


public class MyQueue extends Object {
    private Node head;
    private int count = 0;

    public MyQueue() {

    }

    public void add(Object o) {
        Node obj = new Node(o);
        if (head == null) {
            head = obj;
            count++;


        }
        else if (count == 1) {
            head.setNext(obj);
            count++;
        }

        else
        {
            Node n = head.getNext();
            boolean containsNull = false;
            while(n!=null)
            {
                if(n.getNext() == null)
                    break;
                n= n.getNext();
            }

            n.setNext(obj);
            count++;
        }
    }
    public Node remove() {

        Node result = head;
        if(count == 0)
        {
            return null;
        }
        else if(count == 1)
        {
            head = null;
            count--;
        }
        else
        {
            head = head.getNext();
            count--;
        }
        return result;
    }

    public boolean isEmpty() {
        return (count == 0);
    }

    public Node peek() {
        return head;
    }

    public int size() {
        return count;
    }
}

