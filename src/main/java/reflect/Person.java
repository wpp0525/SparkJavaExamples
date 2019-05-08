package reflect;


import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Person implements Serializable  {

    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;

    }

    public int getAge() {
        return age;
    }

    // get/set方法
    public Person( String name, int age){
        this.name =name;
        this.age = age;
    }

    public static void main(String[] args) {

        Person person = new Person("luoxn28", 23);
        Class clazz = person.getClass();
        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                String key = field.getName();
                PropertyDescriptor descriptor = new PropertyDescriptor(key, clazz);
                Method method = descriptor.getReadMethod();
                Object value = method.invoke(person);
                System.out.println(key + ":" + value);
            }
        }catch (Exception e){
             e.printStackTrace();
        }
    }
}