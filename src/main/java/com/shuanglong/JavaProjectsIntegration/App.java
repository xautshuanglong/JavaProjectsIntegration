package com.shuanglong.JavaProjectsIntegration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App 
{
	private static Logger logger = LogManager.getLogger(App.class);
	
    public static void main( String[] args )
    {
        System.out.println( "================= Maven Test =================" );
        logger.debug("Shuanglong maven test successfully...");
        logger.error("Shuanglong logger error testing");
        
        new Thread()
        {
        	public void run()
        	{
        		logger.info("sub thread logger info testing");
        	};
        }.start();
    }
}
