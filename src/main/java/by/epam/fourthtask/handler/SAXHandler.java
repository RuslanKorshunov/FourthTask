package by.epam.fourthtask.handler;

import by.epam.fourthtask.entity.Gem;
import by.epam.fourthtask.entity.GemEnum;
import by.epam.fourthtask.exception.IncorrectDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXHandler extends DefaultHandler
{
    private static final Logger logger=LogManager.getLogger(SAXHandler.class);

    private List<Gem> gems;
    private Gem currentGem;
    private GemEnum currentEnum;

    public SAXHandler()
    {
        gems=new ArrayList<>();
    }

    public List<Gem> getGems()
    {
        return gems;
    }

    @Override
    public void startDocument() throws SAXException
    {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException
    {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
    {
        GemEnum gemEnum=GemEnum.GEM;
        if(localName.equals(gemEnum.getValue()))
        {
            currentGem=new Gem();
        }
        else
        {
            try
            {
                currentEnum=GemEnum.valueOf(localName.toUpperCase());
            }
            catch(IllegalArgumentException| NullPointerException e)
            {
                logger.error(e);
                currentEnum=null;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
    {
        GemEnum gemEnum=GemEnum.GEM;
        if(localName.equals(gemEnum.getValue()))
        {
            gems.add(currentGem);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
    {
        String characteristic=new String(ch, start, length).trim();
        if(currentEnum!=null)
        {
            switch(currentEnum)
            {
                case KIND:
                    currentGem.setKind(characteristic);
                    break;
                case NAME:
                    currentGem.setName(characteristic);
                    break;
                case COLOR:
                    currentGem.setColor(characteristic);
                    break;
                case ORIGIN:
                    currentGem.setOrigin(characteristic);
                    break;
                case WEIGHT:
                    try
                    {
                        currentGem.setWeight(Double.parseDouble(characteristic));
                    }
                    catch (IncorrectDataException | NumberFormatException e)//TODO???
                    {
                        logger.error(e);
                    }
                    break;
                case FACETING:
                    currentGem.setFaceting(characteristic);
                    break;
                case PARAMETERS:
                    break;
                case PRECIOUSNESS:
                    currentGem.setPreciousness(characteristic);
                    break;
                case TRANSPARENCY:
                    try
                    {
                        currentGem.setTransparency(Double.parseDouble(characteristic));
                    }
                    catch (IncorrectDataException | NumberFormatException e)//TODO???
                    {
                        logger.error(e);
                    }
                    break;
                default://TODO что с этим сделать
                    break;
            }
        }
        currentEnum=null;
    }
}