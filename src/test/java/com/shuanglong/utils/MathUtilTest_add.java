package com.shuanglong.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MathUtilTest_add
{
    private int nValue1;
    private int nValue2;
    private int nTarget;

    public MathUtilTest_add(int value1, int value2, int target)
    {
        nValue1 = value1;
        nValue2 = value2;
        nTarget = target;
    }

    @Parameters
    public static Collection<Object[]> prepareAddParams()
    {
        List<Object[]> params = null;
        params = Arrays.asList(new Object[][]
                {
                        {1, 1, 2},
                        {1, 2, 3},
                        {2, 2, 4},
                        {4, 5, 9}
                });

        return params;
    }

    @Test
    public void testAdd()
    {
        Assert.assertEquals(nTarget, MathUtil.add(nValue1, nValue2));
    }
}
