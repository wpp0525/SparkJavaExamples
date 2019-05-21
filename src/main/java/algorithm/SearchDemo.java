package algorithm;

import java.util.Scanner;

/*
 * 二分查找
 */
public class SearchDemo {
    public static void main(String[] args) {

        int[] arr={5,3,6,1,9,8,2,4,7};
        //先打印输出原始数组数据
        System.out.println("原始数组数据如下：");
        for (int n : arr) {
            System.out.print(n+" ");
        }
        System.out.println();


        System.out.println();//换行
        Scanner input=new Scanner(System.in);
        System.out.println("请输入你要查找的数：");
        int num=input.nextInt();
        int result=binarySearch(arr, num);
        if(result == -1){
            System.out.println("你要查找的数不存在……");
        }else{
            System.out.println("你要查找的数存在，在数组中的位置是："+result);
        }



    }


    //二分查找算法 ,需要数据是有序的。
    public static int binarySearch(int[] arr,int num){

        int low=0;
        int high =arr.length-1;
        while(low<=high ){
            int mid=(high +low)/2;
            if(arr[mid]<num){
                low=mid+1;
            }else if(arr[mid]>num){
                high =mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }


}