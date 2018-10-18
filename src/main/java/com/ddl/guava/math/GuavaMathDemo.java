package com.ddl.guava.math;

import com.google.common.math.BigIntegerMath;
import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;
import com.google.common.primitives.Booleans;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class GuavaMathDemo {

    /**
     * @param args 运行参数
     */
    public static void main(String[] args) {
        // 检查两个整数相加后，是否溢出，等于5
        IntMath.checkedAdd(2, 3);
        // 整数最大值加1，溢出，抛出ArithmeticException
        try {
            IntMath.checkedAdd(Integer.MAX_VALUE, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 检查两个整数相减后，是否溢出，等于4
        IntMath.checkedSubtract(5, 1);

        // 整数最小值减1，溢出，抛出ArithmeticException
        try {
            IntMath.checkedSubtract(Integer.MIN_VALUE, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 检查两个整数相乘后，是否溢出,等于160
        IntMath.checkedMultiply(20, 8);
        // 整数最大值乘以2，溢出，抛出ArithmeticException
        IntMath.checkedMultiply(Integer.MAX_VALUE, 2);

        // 整数最小值乘以2，溢出，抛出ArithmeticException
        try {
            IntMath.checkedMultiply(Integer.MIN_VALUE, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 检查一个整数的N次幂（即把一个整数自乘N次），是否溢出,等于25，即 5 * 5 = 25
        IntMath.checkedPow(5, 2);

        // 整数最大值自乘2次，溢出，抛出ArithmeticException
        try {
            IntMath.checkedPow(Integer.MAX_VALUE, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 整数最小值自乘2次，溢出，抛出ArithmeticException
        try {
            IntMath.checkedPow(Integer.MIN_VALUE, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 检查两个长整数相加后，是否溢出,整数最大值加100，等于2147483747
        LongMath.checkedAdd(Integer.MAX_VALUE, 100);

        // 长整数最大值加1，溢出，抛出ArithmeticException
        try {
            LongMath.checkedAdd(Long.MAX_VALUE, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 检查两个长整数相减后，是否溢出,整数最小值减1，等于-2147483649
        LongMath.checkedSubtract(Integer.MIN_VALUE, 1);

        // 长整数最小值减1，溢出，抛出ArithmeticException
        try {
            LongMath.checkedSubtract(Long.MIN_VALUE, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 检查两个长整数相乘后，是否溢出,两个整数最大值相乘，等于4611686014132420609
        LongMath.checkedMultiply(Integer.MAX_VALUE, Integer.MAX_VALUE);

        // 长整数最大值乘以2，溢出，抛出ArithmeticException
        try {
            LongMath.checkedMultiply(Long.MAX_VALUE, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 长整数最小值乘以2，溢出，抛出ArithmeticException
        try {
            LongMath.checkedMultiply(Long.MIN_VALUE, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 检查一个长整数的N次幂（即把一个长整数自乘N次），是否溢出,整数的最大值自乘2次，等于4611686014132420609
        LongMath.checkedPow(Integer.MAX_VALUE, 2);

        // 长整数最大值自乘2次，溢出，抛出ArithmeticException
        try {
            LongMath.checkedPow(Long.MAX_VALUE, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 长整数最小值自乘2次，溢出，抛出ArithmeticException
        LongMath.checkedPow(Long.MIN_VALUE, 2);


    }

}