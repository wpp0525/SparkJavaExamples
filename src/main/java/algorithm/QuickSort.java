package algorithm;

/**
 * ：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 *  然后再按此方法对这两部分数据分别进行快速排序。
 *
 *  首先任意选取一个数据（通常选用数组的第一个数）作为关键数据，然后将所有比它小的数都放到它左边，
 *  所有比它大的数都放到它右边，这个过程称为一趟快速排序
 *
 */

public class QuickSort {

    public static void main(String[] args) {
        Integer[] list={34,3,53,2,23,7,14,10};

        quickSort(list,0,list.length-1);

        //排序后的结果
        for(int i=0;i<list.length;i++){
            System.out.print(list[i]+" ");
        }
    }

    public static void quickSort(Integer[] list, int low, int high) {
        if (low < high) {
            int middle = getMiddle2(list, low, high);  //将list数组进行一分为二
            quickSort(list, low, middle - 1);        //对低字表进行递归排序
            quickSort(list, middle + 1, high);       //对高字表进行递归排序
        }
    }

    public static int getMiddle2(Integer[] list, int low, int high){

       int temp = list[low];
        if(low < high){
            while (low < high && temp < list[high]){
                    high--;
            }
            list[low] = list[high];

            while(low < high && temp > list[low]){
                low++;
            }
            list[high] = list[low];
        }
        list[low]   = temp;
        return  low;

    }


    //核心是拿到中位数,同时对相关数据进行替换.
    public  static int getMiddle(Integer[] list, int low, int high) {

        int tmp = list[low];    //数组的第一个作为中轴
        while (low < high) {
            while (low < high && tmp < list[high]  ) {// 从右朝左找,
                high--;
            }
            list[low] = list[high];   //这个情况是大于的时候做替换. 比中轴小的记录移到低端
            while (low < high && tmp > list[low]  ) {  // 从左朝右找,
                low++;
            }
            list[high] = list[low];   //比中轴大的记录移到高端
        }
        list[low] = tmp;              //中轴记录到尾
        return low;                   //返回中轴的位置
    }
}
