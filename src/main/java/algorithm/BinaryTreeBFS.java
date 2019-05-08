package algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
   当建立好二叉树类后可以创建二叉树实例，并实现二叉树的先根遍历，中根遍历，后根遍历，代码如下：
    具体图片是

 */


public class BinaryTreeBFS {

    /**
     * 深度优先遍历分为，先序、中序和后序遍历
     */

    public static void preOrder(BinaryTree root){  //先根遍历
        if(root!=null){
            System.out.print(root.data+"-");
            preOrder(root.left);
            preOrder(root.right);
        }
    }


    /**
     * 中间打印结果，所以先访问中间的。
     *
     */

    public static void inOrder(BinaryTree root){     //中根遍历

        if(root!=null){
            inOrder(root.left);
            System.out.print(root.data+"--");
            inOrder(root.right);
        }
    }

    public static void postOrder(BinaryTree root){    //后根遍历

        if(root!=null){
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data+"---");
        }
    }
    //=====================================上面都是深度优先遍历， 下面是BFS ===========================

    /**
     * 对列法实现二叉树广度优先遍历，队列遵循先进先出的规则，适合本方法
     * @param root
     */
    public static void levelOrer(BinaryTree root){

        Queue<BinaryTree> queue = new LinkedList<BinaryTree>();//新增队列

        if(root != null){
            queue.add(root);//将根节点加入队列 。
            System.out.println("queue的大小：" + queue.size());
        }

        while (!queue.isEmpty()){
            BinaryTree  cur = queue.peek(); //创建cur的目的是在while循环的时候逐层将树带入，如果直接用root,会导致只能输出一级树
            System.out.print(cur.data + " ");
            queue.remove();
            if (cur.left != null){
                queue.add(cur.left);//先将左分支加入队列，之后先输出
            }
            if(cur.right != null){
                queue.add(cur.right);
            }
        }

    }



    public static void main(String[] str) {

        int[] array = {12, 76, 35, 22, 16, 48, 90, 46, 9, 40};
        BinaryTree root = new BinaryTree(array[0]);   //创建二叉树
        for (int i = 1; i < array.length; i++) {
            root.insert(root, array[i]);       //向二叉树中插入数据
        }

        System.out.println("先根遍历：");
        preOrder(root);
        System.out.println();
        System.out.println("中根遍历：");
        inOrder(root);
        System.out.println();
        System.out.println("后根遍历：");
        postOrder(root);
        System.out.println();

        //=============== 广度优先遍历==============
        System.out.println("BFS 广度优先遍历：");
        levelOrer(root);

    }
}

class BinaryTree {

    int data;      //根节点数据
    BinaryTree left;    //左子树
    BinaryTree right;   //右子树

    public BinaryTree(int data)    //实例化二叉树类
    {
        this.data = data;
        left = null;
        right = null;
    }

    public void insert(BinaryTree root,int data){     //向二叉树中插入子节点
        if(data>root.data)                               //二叉树的左节点都比根节点小
        {
            if(root.right==null){  //全部插入到右边节点
                root.right = new BinaryTree(data);
            }else{
                this.insert(root.right, data);
            }
        }else{                                          //二叉树的右节点都比根节点大
            if(root.left==null){  //全部插入到左边
                root.left = new BinaryTree(data);
            }else{
                this.insert(root.left, data);
            }
        }
    }

}