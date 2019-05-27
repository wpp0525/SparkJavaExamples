package aleetcode.problem.linked_list;

import java.util.HashMap;
import java.util.Hashtable;

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
public class ReverseSingleLinkedList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(new Integer(1));
        ListNode node2 = new ListNode(new Integer(2));
        ListNode node3 = new ListNode(new Integer(3));
        ListNode node4 = new ListNode(new Integer(4));
        ListNode node5 = new ListNode(new Integer(5));
        ListNode node6 = new ListNode(new Integer(6));

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        ListNode node11 = node1;
//        ListNode newListNode = new ReverseSingleLinkedList().reverseListNode(node1);
//        recursivePrintLink(newListNode);

        ListNode newListNode2 = new ReverseSingleLinkedList().recursiveLink(node1);
        recursivePrintLink(newListNode2);

//        ListNode newListNode2 = recursiveLink(node1);
//        recursivePrintLink(newListNode2);
//        System.exit(0);
//        System.out.println(newListNode.data);
//        printLink(newListNode);
        System.out.println("=============================");
//        recursivePrintLink(newListNode);

//        recursivePrintLink(newListNode2);



        System.out.println("=============================");


    }


    // 对链表进行遍历。
    public static void printLink(ListNode node){

        while ( node != null){
            System.out.println(node.data);
            node = node.next;
        }
    }

    // 递归对链表进行访问。
    public static void recursivePrintLink(ListNode node){

        if(node == null){
            return ;
        }

        System.out.println(node.data);
        recursivePrintLink(node.next);
    }


    //
    public  ListNode recursiveLink(ListNode head){
        if (head == null  || head.getNext() == null) {
            return  head;
        }
        ListNode prenode = null;
        ListNode curnode = head;
        ListNode nextnode =null;

        while (curnode != null){
            nextnode =  curnode.next;
            curnode.next = prenode;
            prenode = curnode;
            curnode =  nextnode ;
        }

        return prenode;
    }

    public   ListNode reverseListNode(ListNode head){ //  自己实现的。
        //单链表为空或只有一个节点，直接返回原单链表
        if (head == null || head.getNext() == null){
            return head;
        }
        ListNode preListNode = null;   //前一个节点指针
        ListNode curListNode = head;      //当前节点指针
        ListNode nextListNode = null;  //下一个节点指针

        while (curListNode != null){
            //规则的话，1，都有 curnode 2，前两个是curnode.next ，后两个是curnode。
            nextListNode = curListNode.next;//nextListNode 指向下一个节点
            curListNode.next = preListNode;//将当前节点next域指向前一个节点
            preListNode = curListNode;//preListNode 指针向后移动
            curListNode = nextListNode;//curListNode指针向后移动 //当前指针指向下个节点
        }

        return preListNode;
    }

}

class ListNode {

    public Object data;//数据域
    //        private int data;//数据域
    public ListNode next;//指针域

    public ListNode(Object data){
        this.data = data;
    }

    public ListNode(Object data,ListNode next){
        this.data = data;
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

}
