package by.epam.fourthtask.validation;

import by.epam.fourthtask.entity.Gem;

public class GemValidation //TODO нужно так делать?
{
    private static final int MAX_TRANSPARENCY=50;

    public boolean validate(Gem gem)
    {
        return (gem.getTransparency()<=MAX_TRANSPARENCY);
    }
}
