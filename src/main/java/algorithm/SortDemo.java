package algorithm;

import java.util.Scanner;

/*
 * 二分查找
 */
public class SortDemo {
    public static void main(String[] args) {

        int[] arr={5,3,6,1,9,8,2,4,7};
        //先打印输出原始数组数据
        System.out.println("原始数组数据如下：");
        for (int n : arr) {
            System.out.print(n+" ");
        }
        System.out.println();

        int[] aa = bubbleSort1(arr);
        System.out.println("返回结果");
        for (int row: aa) {
            System.out.print(row);
        }




    }

    public static  int[] bubbleSort1(int[] arr){

        //首先对数组进行排序，这里用冒泡排序
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){ //从小到大
//                if(arr[j]<arr[j+1]){ //从大到小。
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        //遍历输出排序好的数组
        System.out.println("经过冒泡排序后的数组：");
        for(int n:arr){
            System.out.print(n+" ");
        }
        return  arr;
    }



}