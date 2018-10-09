package com.shuanglong.attempt;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FopDemo
{
	private static Logger logger = LogManager.getLogger(FopDemo.class);
	static private FopDemo instance = new FopDemo();
	
	public FopDemo()
	{
	}
	
	public static void Enter()
	{
		instance.FopTest();
	}
	
	private void FopTest()
	{
		logger.debug("-----> FopDemo.FopTest() <-----");
		OutputStream out = null;
		OutputStream outXslt = null;
		
		try
		{
			// Step 1: Construct a FopFactory by specifying a reference to the configuration file
			// (reuse if you plan to render multiple documents!)
			FopFactory fopFactory = FopFactory.newInstance(new File("E:/Temp/FopTest/fop.xconf"));

			// Step 2: Set up output stream.
			// Note: Using BufferedOutputStream for performance reasons (helpful with FileOutputStreams).
			out = new BufferedOutputStream(new FileOutputStream(new File("E:/Temp/FopTest/simple.pdf")));
			
		    // Step 3: Construct fop with desired output format
		    Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

		    // Step 4: Setup JAXP using identity transformer
		    TransformerFactory factory = TransformerFactory.newInstance();
		    Transformer transformer = factory.newTransformer(); // identity transformer

		    // Step 5: Setup input and output for XSLT transformation
		    // Setup input stream
		    Source src = new StreamSource(new File("E:/Temp/FopTest/simple.fo"));

		    // Resulting SAX events (the generated FO) must be piped through to FOP
		    Result res = new SAXResult(fop.getDefaultHandler());
		    
		    // Step 6: Start XSLT transformation and FOP processing
		    transformer.transform(src, res);
		    
		    // Temp test added by Shuanglong (xml + xsl --> pdf)
		    
		    outXslt = new BufferedOutputStream(new FileOutputStream(new File("E:/Temp/FopTest/name.pdf")));
		    Fop fopXslt = fopFactory.newFop(MimeConstants.MIME_PDF, outXslt);
		    Source xmlSrc = new StreamSource(new File("E:/Temp/FopTest/name.xml"));
		    Source xslt = new StreamSource(new File("E:/Temp/FopTest/name2fo.xsl"));
		    Transformer xsltTrans = factory.newTransformer(xslt);
		    Result resXslt = new SAXResult(fopXslt.getDefaultHandler());
		    xsltTrans.transform(xmlSrc, resXslt);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (out != null)
				{
					out.close();
				}
				if (outXslt != null)
				{
					outXslt.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
