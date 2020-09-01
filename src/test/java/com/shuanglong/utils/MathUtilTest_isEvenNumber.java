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
public class MathUtilTest_isEvenNumber
{
    private int nNumber;
    private boolean nTarget;

    public MathUtilTest_isEvenNumber(int number, boolean target)
    {
        nNumber = number;
        nTarget = target;
    }

    @Parameters
    public static Collection<Object[]> prepareAddParams()
    {
        List<Object[]> params = null;
        params = Arrays.asList(new Object[][]
                {
                        {1, false},
                        {2, true},
                        {4, true}
                });

        return params;
    }

    @Test
    public void testIsEvenNumber()
    {
        Assert.assertEquals(nTarget, MathUtil.isEvenNumber(nNumber));
    }
}
