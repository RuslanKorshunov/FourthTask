package by.epam.fourthtask.builder;

import by.epam.fourthtask.exception.BuilderInitializationException;
import by.epam.fourthtask.exception.ParsingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SAXBuilderTest
{
    private static final Logger logger= LogManager.getLogger(DOMBuilderTest.class);

    @Test
    public void buildGemsNegative() throws BuilderInitializationException
    {
        String filePath="doesnt_exist.xml";
        try
        {
            SAXBuilder builder=new SAXBuilder();
            builder.buildGems(filePath);
            fail("buildGemsNegative was failed.");
        }
        catch (ParsingException e)
        {
            logger.error(e);
        }
    }
}