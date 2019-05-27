package aleetcode.problem.linked_list;

/**
 * Created by gouthamvidyapradhan on 24/02/2017.
 * Reverse a singly linked list.
 *
 * 先说下思路：所谓的单链表反转，就是把每个节点的指针域由原来的指向下一个节点变为指向其前一个节点。
 * 但由于单链表没有指向前一个节点的指针域，因此我们需要增加一个指向前一个节点的指针pre，用于存储每一个节点的前一个节点。
 * 此外，还需要定义一个保存当前节点的指针cur，以及下一个节点的next。定义好这三个指针后，遍历单链表，
 * 将当前节点的指针域指向前一个节点，之后将定义三个指针往后移动，直至遍历到最后一个节点停止
 *
 */
public class ReverseSingleLinkedList2 {
    public static ListNode2 head = new ListNode2();// 定义一个头节点

    public static void main(String[] args) {
        ListNode2 node1 = new ListNode2(new Integer(1));
        ListNode2 node2 = new ListNode2(new Integer(2));
        ListNode2 node3 = new ListNode2(new Integer(3));
        ListNode2 node4 = new ListNode2(new Integer(4));
        ListNode2 node5 = new ListNode2(new Integer(5));
        ListNode2 node6 = new ListNode2(new Integer(6));

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;


        ListNode2 node21 = new ListNode2(new Integer(41));
        ListNode2 node22 = new ListNode2(new Integer(52));
        ListNode2 node23 = new ListNode2(new Integer(62));

        node21.next = node22;
        node22.next = node23;

        ListNode2 node11 = node1;
//        ListNode2 newListNode2 = new ReverseSingleLinkedList().reverseListNode2(node1);
//        recursivePrintLink(newListNode2);

//        ListNode2 newListNode22 = new ReverseSingleLinkedList().recursiveLink(node1);
//        recursivePrintLink(newListNode22);

//        ListNode2 newListNode22 = recursiveLink(node1);
//        recursivePrintLink(newListNode22);
//        System.exit(0);
//        System.out.println(newListNode2.data);
//        printLink(newListNode2);
        System.out.println("=============================");
//        recursivePrintLink(newListNode2);

        ListNode2  aa =  mergeTwoLists(node1,node21);
        recursivePrintLink(aa);

        System.out.println("=============================!!!");

        System.out.println(findx(aa, 41));

//        ListNode2 aa2 = deleteFirst(aa);
//        recursivePrintLink(aa2);

        System.out.println("===============delete ==============!!!");
        ListNode2 aa4 = deleteX(aa,1);
        recursivePrintLink(aa4);

//        head.addNode(1);

    }

    public static void addNode(int data) {

        ListNode2 node = new ListNode2(data);
        ListNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    // 删除头节点
    public static ListNode2 deleteFirst(ListNode2 first ){
        first = first.next;
        return first;
    }

    //删除指定链表的值
    public static ListNode2 deleteX(ListNode2 first,int key) {
        if (first == null)
            return first;

        ListNode2 current = first;
        ListNode2 previous = first;

        while(current.data != key){
            if(current.next == null)
                return current;
            else{
                previous = current;
                current = current.next;
            }
        }
        if(current == first) {
            first = first.next;
        }else{
            previous.next = current.next;
        }
        return current;
    }

     // 递归对链表进行访问。
    public static void recursivePrintLink(ListNode2 node){

        if(node == null){
            return ;
        }

        System.out.println(node.data);
        recursivePrintLink(node.next);
    }


    public static int findx(ListNode2 l1,int x) {
        if (l1 == null)
            return 0;

        int cnt = 0;
        ListNode2 preNode= l1;
        while(preNode.next != null){
            cnt++;
            if(preNode.data == x){
                return  cnt;
            }
            preNode = preNode.next;
        }
        return 0;
    }


    public static ListNode2 mergeTwoLists(ListNode2 l1, ListNode2 l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode2 head = null;
        if (  l1.data <= l2.data ) {
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }

    //
    public  ListNode2 recursiveLink(ListNode2 head){
        if (head == null  || head.next == null) {
            return  head;
        }
        ListNode2 prenode = null;
        ListNode2 curnode = head;
        ListNode2 nextnode =null;

        while (curnode != null){
            nextnode =  curnode.next;
            curnode.next = prenode;
            prenode = curnode;
            curnode =  nextnode ;
        }

        return prenode;
    }

    public   ListNode2 reverseListNode2(ListNode2 head){ //  自己实现的。
        //单链表为空或只有一个节点，直接返回原单链表
        if (head == null || head.next == null){
            return head;
        }
        ListNode2 preListNode2 = null;   //前一个节点指针
        ListNode2 curListNode2 = head;      //当前节点指针
        ListNode2 nextListNode2 = null;  //下一个节点指针

        while (curListNode2 != null){
            //规则的话，1，都有 curnode 2，前两个是curnode.next ，后两个是curnode。
            nextListNode2 = curListNode2.next;//nextListNode2 指向下一个节点
            curListNode2.next = preListNode2;//将当前节点next域指向前一个节点
            preListNode2 = curListNode2;//preListNode2 指针向后移动
            curListNode2 = nextListNode2;//curListNode2指针向后移动 //当前指针指向下个节点
        }

        return preListNode2;
    }


}

class ListNode2 {

    public Integer data;//数据域
    //        private int data;//数据域
    public ListNode2 next;//指针域

    public ListNode2(){
    }

    public ListNode2(Integer data){
        this.data = data;
    }

    public ListNode2(Integer data,ListNode2 next){
        this.data = data;
        this.next = next;
    }

}
