package by.epam.fourthtask.builder;

import by.epam.fourthtask.entity.Gem;
import by.epam.fourthtask.entity.GemEnum;
import by.epam.fourthtask.exception.IncorrectDataException;
import by.epam.fourthtask.exception.ParsingException;
import by.epam.fourthtask.validation.GemValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StAXBuilder extends AbstractBuilder
{
    private static final Logger logger= LogManager.getLogger(StAXBuilder.class);

    @Override
    public void buildGems(String filePath) throws ParsingException
    {
        FileInputStream input=null;
        XMLStreamReader reader=null;
        try
        {
            input=new FileInputStream(filePath);
            XMLInputFactory inputFactory=XMLInputFactory.newInstance();
            reader=inputFactory.createXMLStreamReader(input);
            while (reader.hasNext())
            {
                int type=reader.next();
                if(type== XMLStreamConstants.START_ELEMENT)
                {
                    String name=reader.getLocalName();
                    GemEnum gemEnum= GemEnum.GEM;
                    if(name.equals(gemEnum.getValue()))
                    {
                        Gem gem=buildGem(reader);
                        GemValidation validation=new GemValidation();
                        if(validation.validate(gem))
                        {
                            gems.add(gem);
                        }
                    }
                }
            }
        }
        catch (XMLStreamException | FileNotFoundException | IncorrectDataException e)
        {
            throw new ParsingException("StAXBuilder can't parse "+filePath+".");
        }
        finally
        {
            if(input!=null)
            {
                try
                {
                    input.close();
                }
                catch (IOException e)
                {
                    logger.warn("Impossible close file "+filePath+".", e);
                }
            }
            if(reader!=null)
            {
                try
                {
                    reader.close();
                }
                catch (XMLStreamException e)
                {
                    logger.warn("Impossible close file "+filePath+".", e);
                }
            }
        }
    }

    private Gem buildGem(XMLStreamReader reader) throws XMLStreamException, IncorrectDataException
    {
        Gem gem=new Gem();
        while (reader.hasNext())
        {
            int type=reader.next();
            String name;
            switch (type)
            {
                case XMLStreamConstants.START_ELEMENT:
                    name=reader.getLocalName();
                    name=name.toUpperCase();
                    switch(GemEnum.valueOf(name))
                    {
                        case NAME:
                            gem.setName(findXMLText(reader));
                            break;
                        case KIND:
                            gem.setKind(findXMLText(reader));
                            break;
                        case PRECIOUSNESS:
                            gem.setPreciousness(findXMLText(reader));
                            break;
                        case ORIGIN:
                            gem.setOrigin(findXMLText(reader));
                            break;
                        case COLOR:
                            gem.setColor(findXMLText(reader));
                            break;
                        case TRANSPARENCY:
                            try
                            {
                                double number=Double.parseDouble(findXMLText(reader));
                                gem.setTransparency(number);
                            }
                            catch (IncorrectDataException | NumberFormatException e)
                            {
                                logger.error(e);
                            }
                            break;
                        case FACETING:
                            gem.setFaceting(findXMLText(reader));
                            break;
                        case WEIGHT:
                            try
                            {
                                double number=Double.parseDouble(findXMLText(reader));
                                gem.setWeight(number);
                            }
                            catch (IncorrectDataException | NumberFormatException e)
                            {
                                logger.error(e);
                            }
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name=reader.getLocalName();
                    GemEnum gemEnum= GemEnum.GEM;
                    if(name.equals(gemEnum.getValue()))
                    {
                        return gem;
                    }
                    break;
            }
        }
        throw new IncorrectDataException("There was unknown tag.");
    }

    private String findXMLText(XMLStreamReader reader) throws XMLStreamException
    {
        String text = null;
        if (reader.hasNext())
        {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}