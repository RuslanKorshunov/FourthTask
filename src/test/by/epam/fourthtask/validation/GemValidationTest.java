package by.epam.fourthtask.validation;

import by.epam.fourthtask.entity.Gem;
import by.epam.fourthtask.exception.IncorrectDataException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GemValidationTest
{
    @Test
    public void validateTestTrue() throws IncorrectDataException
    {
        Gem gem=new Gem();
        double transparency=48;
        gem.setTransparency(transparency);
        GemValidation validation=new GemValidation();
        boolean actual=validation.validate(gem);
        assertTrue(actual);
    }

    @Test(dependsOnMethods = "validateTestTrue")
    public void validateTestFalse() throws IncorrectDataException
    {
        Gem gem=new Gem();
        double transparency=48;
        gem.setTransparency(transparency);
        GemValidation validation=new GemValidation();
        boolean actual=validation.validate(gem);
        assertTrue(actual);
    }
}