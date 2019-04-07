package by.epam.fourthtask.builder;

import by.epam.fourthtask.exception.BuilderInitializationException;
import by.epam.fourthtask.exception.IncorrectDataException;

public class BuilderFactory
{
    public AbstractBuilder createBuilder(FactoryEnum factoryEnum) throws BuilderInitializationException, IncorrectDataException
    {
        switch (factoryEnum)
        {
            case DOM:
                return new DOMBuilder();
            case SAX:
                return new SAXBuilder();
            case StAX:
                return new StAXBuilder();
        }
        throw new IncorrectDataException("This factoryEnum doesn't exist.");
    }
}
