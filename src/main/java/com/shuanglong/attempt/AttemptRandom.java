package com.shuanglong.attempt;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AttemptRandom
{
    static private final Logger logger = LogManager.getLogger(AttemptRandom.class);
    static private final AttemptRandom instance = new AttemptRandom();

    public AttemptRandom()
    {
    }

    public static void Enter()
    {
        instance.RandomTest();
    }

    private void RandomTest()
    {
        logger.debug("-----> AttemptRandom.RandomTest() <-----");

        int row = 5;
        int col = 5;
        int total = row * col;

        for (int i = 0; i < total; i++)
        {
//            System.out.printf("%10s", i);
            double randomNumber = Math.random();
            System.out.printf("%25.18f", randomNumber);

            if ((i + 1) % col == 0)
            {
                System.out.println();
            }
        }

        System.out.println("\n\n");

        Random randomDouble = new Random(1);
        PrintRandomDouble(col, total, randomDouble);

        randomDouble.setSeed(1);
        PrintRandomDouble(col, total, randomDouble);

        Random randomInt = new Random(1);
        for (int i = 0; i < total; i++)
        {
            int randomIntN = randomInt.nextInt();
            System.out.printf("%15d", randomIntN);
            if ((i + 1) % col == 0)
            {
                System.out.println();
            }
        }

        System.out.println("\n\n");

//        long oldseed = 1L;
//        long oldseed = 1L ^ 25214903917L;
        long oldseed = 25214903916L; // 相当于构造 Random 时初始种子为 1
        long nextseed;
        for (int i = 0; i < total; i++)
        {
            nextseed = (oldseed * 25214903917L + 11L) & 281474976710655L;
            int randomValue = (int) (nextseed >>> (48 - 32));
            oldseed = nextseed;
            System.out.printf("%15d", randomValue);

            if ((i + 1) % col == 0)
            {
                System.out.println();
            }
        }
    }

    private void PrintRandomDouble(int col, int total, Random randomDouble)
    {
        for (int i = 0; i < total; i++)
        {
            double randomDoubleN = randomDouble.nextDouble();
            System.out.printf("%25.18f", randomDoubleN);
            if ((i + 1) % col == 0)
            {
                System.out.println();
            }
        }

        System.out.println("\n\n");
    }
}
