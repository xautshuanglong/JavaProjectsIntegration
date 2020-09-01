package com.shuanglong.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses
        ({
                MathUtilTest_add.class,
                MathUtilTest_isEvenNumber.class
        })
public class MathUtilTest
{
    @Test
    public void testAdd()
    {
        System.out.println("test");
        Assert.assertTrue(true);
    }
}
