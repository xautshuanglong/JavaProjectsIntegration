package com.shuanglong.JavaProjectsIntegration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.shuanglong.utils.MathUtilTest;
import com.shuanglong.utils.NumberUtilTest;

@RunWith(Suite.class)
@SuiteClasses
        ({
                MathUtilTest.class,
                NumberUtilTest.class
        })
public class AppTest
{
    public AppTest()
    {
    }

    @BeforeClass
    public static void beforeClass()
    {
        System.out.println("AppTest.beforeClass");
    }

    @AfterClass
    public static void afterClass()
    {
        System.out.println("AppTest.afterClass");
    }
}
