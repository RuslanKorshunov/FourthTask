package by.epam.fourthtask.builder;

import by.epam.fourthtask.exception.BuilderInitializationException;
import by.epam.fourthtask.exception.ParsingException;
import by.epam.fourthtask.handler.SAXHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class SAXBuilder extends AbstractBuilder
{
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
    public void buildGems(String filePath) throws ParsingException
    {
        try
        {
            reader.parse(filePath);
        }
        catch (SAXException| IOException e)
        {
            throw new ParsingException("SAXBuilder can't parse "+filePath+".");
        }
        gems=handler.getGems();
    }
}