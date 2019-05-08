package algorithm;

public class MyDoubleLinkList {

    private int length =0;//当前长度
    private Node head;//头结点
    private Node tail;//当前结点结点


    public MyDoubleLinkList(){
        initLink();
    }
    public void initLink(){
        head  = new Node(null);
        tail = new Node(null);
        this.head = tail;
        length++;
    }

    //获取链表长度
    public int getSize(){
        return length;
    }
    //判断链表是否为空
    public boolean getEmpty(){
        return getSize()==0;
    }
    //根据索引查找元素  从第一个有效值开始
    public Node getNode(int index){
        Node p;
        if(index < 0 || index > length ){
            System.out.println("参数错误");
        }
        if(index < this.length/2){
            p = this.head;
            for(int i=0; i<index; i++){
                p = p.next;
            }
        }else{
            p = this.tail;
            for(int i= length; i>index;i--){
                p = p.prev;
            }
        }
        return p;
    }

    public Object getData(int index){
        return getNode(index).data;
    }

    //在头结点处插入
    public boolean addHead(Object e){
        //前驱引用为null，后继为node
        Node node = new Node(e, null, this.head);
        //改变头结点的前驱后继
        this.head.prev = node;
        this.head = node;
        if(tail == null){
            tail =  this.head;
        }
        length++;
        return true;
    }
    //在尾结点插入
    public boolean addTail(Object e){
        if(this.head == null){
            this.head = new Node(e,null,null);
            this.tail = this.head;
        }else{
            Node node = new Node(e,this.tail,null);
            this.tail.next = node;
            this.tail = node;

        }
        length++;
        return true;

    }
    //在指定位置插入元素
    public boolean addData(int index,Object ele){
        if(index <0 || index > this.length){
            System.out.println("参数错误");
        }
        if(this.head == null){
            this.addTail(ele);//用尾插法
        }else{
            if(index == 0){
                addHead(ele);//用头插法
            }else{
                Node p = this.getNode(index);//要插入处的结点
                Node n = p.next;
                Node node = new Node(ele,p,n);//要插入的结点
                n.prev = node;
                p.next = node;
                length ++;

            }
        }

        return true;
    }

    public void removeData(int index){
        if(index < 0 || index > length){
            System.out.println("参数错误");
        }else{
            Node del = null;
            if(index == 0){
                del = this.head;
                this.head = this.head.next;
                this.head.prev = null;
                length--;
            }else if(index == (length-1)){
                Node p = this.getNode(index-1);//得到要删除结点的前驱结点
                del = p.next;//要删除的结点
                p.next = del.next;
                if(del.next != null){
                    del.next.prev = p;
                }
                del.next = null;
                del.prev = null;
                length --;

                this.tail.next = null;
                this.tail.prev = p;
                this.tail = p;
            }
            else{
                Node p = this.getNode(index-1);//要删除结点的前驱结点
                del = p.next;//要删除的结点
                p.next = del.next;
                if(del.next != null){
                    del.next.prev = p;
                }
                del.prev = null;
                del.next = null;
                length--;
            }

        }
    }
    //打印所有链表中的元素
    public void print(){
        Node current = this.head;
        while(current != null){
            System.out.println(current.data);
            current = current.next;
        }
    }
    //反向打印链表
    public void reversePrint(){
        Node current = this.tail;
        while(current != null){
            System.out.println(current.data);
            current = current.prev;
        }
    }
    public static void main(String args[]){
        MyDoubleLinkList linkList = new MyDoubleLinkList();
        linkList.addHead("aaaa");
//        System.out.println(linkList.getData(1));
        linkList.addTail("bbbb");
//        System.out.println(linkList.getData(3));
        linkList.addData(2, "eeee");
//        linkList.print();
        linkList.removeData(2);
        linkList.print();
        System.out.println(".....");
        linkList.reversePrint();
    }

}
class Node{
    Node prev;//指针域中前驱
    Node next;//指针域中后继
    Object data;//数据域
    public Node(Node current){
        prev = current;
        next = current;
    }
    //双链表前驱后继及数字域
    public Node(Object d, Node p,Node n){
        this.data = d;
        this.prev = p;
        this.next = n;
    }
    public Node getPrev() {
        return prev;
    }
    public void setPrev(Node prev) {
        this.prev = prev;
    }
    public Node getNext() {
        return next;
    }
    public void setNext(Node next) {
        this.next = next;
    }

}