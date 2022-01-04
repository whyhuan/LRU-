public class Node<T> {
    public T date;
    public Node<T> next;//指向下一节点

    public Node(T d){
        this.date=d;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public Node<T> getNext() {
        return next;
    }
}
