package by.epam.fourthtask.entity;

import by.epam.fourthtask.exception.IncorrectDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GemTest
{
    private static final Logger logger= LogManager.getLogger(GemTest.class);

    @Test
    public void setTransparencyPositive() throws IncorrectDataException
    {
        Gem gem=new Gem();
        double transparency=15;
        gem.setTransparency(transparency);
    }

    @Test(dependsOnMethods = "setTransparencyPositive")
    public void setTransparencyNegativeFirst()
    {
        try
        {
            Gem gem=new Gem();
            double transparency=-2;
            gem.setTransparency(transparency);
            fail("setTransparencyNegativeFirst was failed.");
        }
        catch(IncorrectDataException e)
        {
            logger.error(e);
        }
    }

    @Test(dependsOnMethods = "setTransparencyNegativeFirst")
    public void setTransparencyNegativeSecond()
    {
        try
        {
            Gem gem=new Gem();
            double transparency=125;
            gem.setTransparency(transparency);
            fail("setTransparencyNegativeSecond was failed.");
        }
        catch(IncorrectDataException e)
        {
            logger.error(e);
        }
    }

    @Test(dependsOnMethods = "setTransparencyNegativeSecond")
    public void setWeightPositive() throws IncorrectDataException
    {
        Gem gem=new Gem();
        double weight=120;
        gem.setWeight(weight);
    }

    @Test(dependsOnMethods = "setWeightPositive")
    public void setWeightNegative()
    {
        try
        {
            Gem gem=new Gem();
            double weight=-120;
            gem.setWeight(weight);
            fail("setWeightNegative was failed.");
        }
        catch(IncorrectDataException e)
        {
            logger.error(e);
        }
    }
}