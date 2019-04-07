package by.epam.fourthtask.builder;

import by.epam.fourthtask.entity.Gem;
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

    public SAXBuilder()
    {
        super();
        handler=new SAXHandler();
        try
        {
            reader=XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        }
        catch (SAXException e)//TODO создать свое иключение
        {
            logger.error(e);
        }
    }

    @Override
    public List<Gem> getGems()
    {
        return super.getGems();
    }

    @Override
    public void buildGems(String filePath)
    {
        try
        {
            reader.parse(filePath);
        }
        catch (SAXException| IOException e)
        {
            logger.error(e);
        }
        gems=handler.getGems();
    }
}
