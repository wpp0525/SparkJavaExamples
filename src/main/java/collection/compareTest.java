package collection;

import java.util.*;
//以下是学生类Student定义，有点类似C语言的结构体啊！^_^
class Student {
    public int s_no;
    public String s_name;
    public int s_class;
}

public class compareTest {
    public static void main(String[] args) {
        //存放学生类的动态数组的初始化
        ArrayList<Student> studentArr = new ArrayList<Student>();
        Student s1 = new Student();
        s1.s_no = 3;
        s1.s_name = "a";
        s1.s_class = 102;
        studentArr.add(s1);
        Student s2 = new Student();
        s2.s_no = 2;
        s2.s_name = "b";
        s2.s_class = 101;
        studentArr.add(s2);
        Student s3 = new Student();
        s3.s_no = 1;
        s3.s_name = "c";
        s3.s_class = 103;
        studentArr.add(s3);
        //初始化之后先打印以下这个动态数组
        System.out.println("排序前：");
        for (int i = 0; i < studentArr.size(); i++) {
            System.out
                    .println("我是" + studentArr.get(i).s_class + "班的"
                            + studentArr.get(i).s_name + "学号是"
                            + studentArr.get(i).s_no);
        }
        //对于Comparator接口的重写
        //这个接口就一个抽象函数，给出的参数与返回值都是定死的。
        Collections.sort(studentArr, new Comparator<Object>() {
            public int compare(Object o1, Object o2) {
                //你首先设置你要比较的东西
                //具体是把参数中的Object强制转换成你要比较的东西，这里是两个Student类
                //这里的s1,s2与上面的s1,s2一点关系都没有，只是抽象的前者与后者的关系
                Student s1 = (Student) o1;
                Student s2 = (Student) o2;
                //如果前者的学号大于后者的学号，就是前者大于后者，返回1系统就会识别是前者大于后者
                if (s1.s_no > s2.s_no) {
                    return 1;
                }
                //小于同理
                if (s1.s_no < s2.s_no) {
                    return -1;
                }
                //如果返回0则认为前者与后者相等
                return 0;
            }
        });
        //比较完毕再输出以学号排序之后的结果
        System.out.println("按学号排序后：");
        for (int i = 0; i < studentArr.size(); i++) {
            System.out
                    .println("我是" + studentArr.get(i).s_class + "班的"
                            + studentArr.get(i).s_name + "学号是"
                            + studentArr.get(i).s_no);
        }
        //以下是以班级排序的过程
        Collections.sort(studentArr, new Comparator<Object>() {
            public int compare(Object o1, Object o2) {
                Student s1 = (Student) o1;
                Student s2 = (Student) o2;
                if (s1.s_class > s2.s_class) {
                    return 1;
                }
                if (s1.s_class < s2.s_class) {
                    return -1;
                }
                return 0;
            }
        });
        System.out.println("按班级排序后：");
        for (int i = 0; i < studentArr.size(); i++) {
            System.out
                    .println("我是" + studentArr.get(i).s_class + "班的"
                            + studentArr.get(i).s_name + "学号是"
                            + studentArr.get(i).s_no);
        }
    }
}

