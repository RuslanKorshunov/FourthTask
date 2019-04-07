package by.epam.fourthtask.builder;

import by.epam.fourthtask.entity.Gem;
import by.epam.fourthtask.exception.BuilderInitializationException;
import by.epam.fourthtask.exception.ParsingException;
import by.epam.fourthtask.handler.SAXHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SAXBuilder extends AbstractBuilder
{
    private static final Logger logger= LogManager.getLogger(SAXBuilder.class);

    private SAXHandler handler;
    private XMLReader reader;

    public SAXBuilder() throws BuilderInitializationException
    {
        handler=new SAXHandler();
        try
        {
            reader=XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        }
        catch (SAXException e)
        {
            throw new BuilderInitializationException("DOMBuilder couldn't be created.");
        }
    }

    @Override
    public List<Gem> buildGems(String filePath) throws ParsingException
    {
        try
        {
            reader.parse(filePath);
        }
        catch (SAXException| IOException e)
        {
            throw new ParsingException("SAXBuilder can't parse "+filePath+".");
        }
        return handler.getGems();
    }
}
