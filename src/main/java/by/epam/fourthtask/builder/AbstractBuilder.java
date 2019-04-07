package by.epam.fourthtask.builder;

import by.epam.fourthtask.entity.Gem;

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

    public void buildGems(String filePath)
    {}
}
