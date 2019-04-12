package by.epam.fourthtask.builder;

import by.epam.fourthtask.entity.Gem;
import by.epam.fourthtask.exception.ParsingException;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBuilder
{
    protected List<Gem> gems;

    public AbstractBuilder()
    {
        gems=new ArrayList<>();
    }

    public List<Gem> getGems()
    {
        return gems;
    }

    public abstract void buildGems(String filePath) throws ParsingException;
}