public class LinkNode<T> {
    public Node<T> head;//头节点
    public Node<T> rear;//尾节点
    int size=0;

    public LinkNode(){
        head=rear=null;
    }

    public void add(T date){//节点加入链表尾
        Node<T> node=new Node<>(date);
        if(isEmpty()){//链表为空
            head=node;
            rear=head;
        }
        else{
            rear.setNext(node);
            rear=node;
        }
        size++;
    }

    public T delete(int i){//删除第i个节点
        if(i<0||i>size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(i));
        if(i==0){
            head.next=null;
            size--;
            return head.date;
        }
        int pos=1;
        Node<T> p =head;
        while(pos<i) {
            p=p.next;
            pos++;
        }
        p.next=p.next.next;
        size--;
        return p.date;
    }

    public void insert(int i,T date){//插入到第i个节点
        if(i<0||i>size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(i));
        if(i==0){
            Node<T> node=new Node<>(date);
            node.next=head;
            this.head=node;
        }
        int pos=1;
        Node<T> node=new Node<>(date);
        Node<T> p=head;
        while(pos<i){
            p=p.next;
            pos++;
        }
        p.next=node;
        p.next=p.next.next;
        size++;
    }

    public void set(int i,T date){//修改第i个节点的值
        if(i<0||i>size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(i));
        int pos=0;
        Node<T> p=head;
        while(pos<i) {
            p=p.next;
            pos++;
        }
        p.date=date;

    }

    //查看第i点的信息
    public T getDate(int i) {
        T date;
        if(i<0||i>size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(i));
        int pos=0;
        Node<T> p=head;
        while(pos<i) {
            p=p.next;
            pos++;
        }
        date=p.date;
        return date;
    }

    public boolean isEmpty()
    {
        if(head==null)
            return true;
        else
            return false;
    }


    private String outOfBoundsMsg(int i) {//访问链表越界
        return "Index: " + i + ", Size: " + size;
    }

}
