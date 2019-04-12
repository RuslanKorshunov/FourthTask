package by.epam.fourthtask.validation;

import by.epam.fourthtask.entity.Gem;

public class GemValidation
{
    private static final int MAX_TRANSPARENCY=50;

    public boolean validate(Gem gem)
    {
        return (gem.getTransparency()<=MAX_TRANSPARENCY);
    }
}
