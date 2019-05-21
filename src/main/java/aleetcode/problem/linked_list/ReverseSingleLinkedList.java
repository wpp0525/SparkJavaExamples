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
        Node node1 = new Node(new Integer(1));
        Node node2 = new Node(new Integer(200));
        Node node3 = new Node(new Integer(300));
        Node node4 = new Node(new Integer(400));
        Node node5 = new Node(new Integer(500));
        Node node6 = new Node(new Integer(500));

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        Node newNode = new ReverseSingleLinkedList().reverseNode(node1);

//        System.out.println(newNode.data);
        printLink(newNode);

        System.out.println("=============================");
        Node list2 = distinctLink(newNode);
        printLink(list2);

    }

    // 对链表进行遍历。
    public static void printLink(Node node){

        while ( node != null){
            System.out.println(node.data);
            node = node.next;
        }
    }

    /**
     * 去掉重复元素:
     * 需要额外的存储空间hashtable，调用hashtable.containsKey()来判断重复结点
     */
    public static Node distinctLink(Node node){
//        Node temp = node;
//        Node pre = null;
////        HashMap<Object, Integer> hb = new HashMap<Object, Integer>();
//        Hashtable<Object, Integer> hb = new Hashtable<Object, Integer>();
//        while(node != null){
//            if(hb.containsKey(node.data)){//如果hashtable中已存在该结点，则跳过该结点
//                pre.next = node.next;
//            }else{//如果hashtable中不存在该结点，将结点存到hashtable中
//                hb.put(node.data, 1);
//            }
//
//            node = node.next;
//        }
//        return  pre;
    }


    public static Node reverseNode(Node head){ //  自己实现的。
        //单链表为空或只有一个节点，直接返回原单链表
        if (head == null || head.getNext() == null){
            return head;
        }
        Node preNode = null;   //前一个节点指针
        Node curNode = head;      //当前节点指针
        Node nextNode = null;  //下一个节点指针

        while (curNode != null){
            //规则的话，1，都有 curnode 2，前两个是curnode.next ，后两个是curnode。
            nextNode = curNode.next;//nextNode 指向下一个节点
            curNode.next = preNode;//将当前节点next域指向前一个节点
            preNode = curNode;//preNode 指针向后移动
            curNode = nextNode;//curNode指针向后移动 //当前指针指向下个节点
        }

        return preNode;
    }


    public static class Node {

        private Object data;//数据域
//        private int data;//数据域
        private Node next;//指针域

        public Node(Object data){
            this.data = data;
        }

        public Node(Object data,Node next){
            this.data = data;
            this.next = next;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }

}
