package com.ddl.guava.objects;

import com.ddl.guava.entity.Student;
import com.google.common.base.MoreObjects;

import org.junit.Test;


/**
 * @author dongdongliu
 * @version 1.0
 */
public class ObjectsTest {
    public static void main(String args[]) {

        Student s1 = new Student("Mahesh", "Parashar", 1, "VI");
        Student s2 = new Student("Suresh", null, 3, null);
        System.out.println(s1.equals(s2));
        System.out.println(s1.hashCode());
        System.out.println(
                //编写toString方法
                MoreObjects.toStringHelper(s1)
                        .add("Name", s1.getFirstName() + " " + s1.getLastName())
                        .add("Class", s1.getClassName())
                        .add("Roll No", s1.getRollNo())
                        .toString());

        //获取不为空的实列，要是第一个为空，排查第二实列，第二实列为空抛出空指针
        Student student = MoreObjects.firstNonNull(null, s2);
        System.out.println(student);

    }

    @Test
    public void compareTo() {

    }
}
