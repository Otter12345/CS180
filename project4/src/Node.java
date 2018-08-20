public class Node {

    private Object data;
    private Node next;
    private Node prev;

    public Node(Object obj) {
        this.data=obj;
        }

    public void setNext(Node next) {this.next = next;}
    public Node getNext() {return next;}
    public Object getData() {return data;}
    public Node getPrev(){return prev;}
    public void setPrev(Node prev){this.prev = prev;}
}