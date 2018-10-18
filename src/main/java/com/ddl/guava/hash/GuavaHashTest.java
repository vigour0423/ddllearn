package com.ddl.guava.hash;

import com.google.common.base.Charsets;
import com.google.common.hash.*;

import java.nio.charset.Charset;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class GuavaHashTest {

    public static void main(String[] args) {


        HashFunction function0 = Hashing.md5();
        HashFunction function1 = Hashing.murmur3_128();
        Hasher hasher0 = function0.newHasher();
        Hasher hasher1 = function1.newHasher();

        Person person = new Person();
        person.setAge(27);
        person.setName("hahahah");
        person.setAddress("北京三里屯");
        person.setPhoneNumber(16666666666L);
        person.setMale(Male.man);

        HashCode code0 = hasher0.putInt(person.getAge())
                .putString(person.getName(), Charsets.UTF_8)
                .putString(person.getAddress(), Charsets.UTF_8)
                .putLong(person.getPhoneNumber())
                .putObject(person.getMale(), new Funnel<Male>() {
                    @Override
                    public void funnel(Male from, PrimitiveSink into) {
                        PrimitiveSink primitiveSink = into.putString(from.name(), Charsets.UTF_8);
                    }
                }).hash();

        HashCode code1 = hasher1.putInt(person.getAge())
                .putString(person.getName(), Charsets.UTF_8)
                .putString(person.getAddress(), Charsets.UTF_8)
                .putLong(person.getPhoneNumber())
                .putObject(person.getMale(), new Funnel<Male>() {
                    @Override
                    public void funnel(Male from, PrimitiveSink into) {
                        into.putString(from.name(), Charsets.UTF_8);
                    }
                }).hash();

        System.out.println(code0.asLong());
        System.out.println(code1.asLong());
    }


    public enum Male {
        man, woman;
    }

    public static class Person {
        int age;

        String name;

        String address;

        long phoneNumber;

        Male male;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(long phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Male getMale() {
            return male;
        }

        public void setMale(Male male) {
            this.male = male;
        }
    }
}