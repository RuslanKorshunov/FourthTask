package by.epam.fourthtask.builder;

import by.epam.fourthtask.entity.Gem;
import by.epam.fourthtask.entity.GemEnum;
import by.epam.fourthtask.exception.BuilderInitializationException;
import by.epam.fourthtask.exception.IncorrectDataException;
import by.epam.fourthtask.exception.ParsingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DOMBuilder extends AbstractBuilder
{
    private static final Logger logger= LogManager.getLogger(DOMBuilder.class);

    private DocumentBuilder documentBuilder;

    public DOMBuilder() throws BuilderInitializationException
    {
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        try
        {
            documentBuilder=factory.newDocumentBuilder();
        }
        catch(ParserConfigurationException e)
        {
            throw new BuilderInitializationException("DOMBuilder couldn't be created.");
        }
    }

    @Override
    public void buildGems(String filePath) throws ParsingException
    {
        Document document;
        try
        {
            document=documentBuilder.parse(filePath);
            Element elementMain=document.getDocumentElement();
            NodeList gemList=elementMain.getElementsByTagName(GemEnum.GEM.getValue());
            for(int index=0; index<gemList.getLength(); index++)
            {
                Element elementGem=(Element) gemList.item(index);
                Gem gem= buildGem(elementGem);
                gems.add(gem);
            }
        }
        catch (SAXException| IOException e)
        {
            throw new ParsingException("DOMBuilder can't parse "+filePath+".");
        }
    }

    private Gem buildGem(Element elementGem)
    {
        Gem gem=new Gem();
        GemEnum gemEnum=GemEnum.NAME;
        try
        {
            gem.setName(findContent(elementGem, gemEnum.getValue()));
            gemEnum= GemEnum.KIND;
            gem.setKind(findContent(elementGem, gemEnum.getValue()));
            gemEnum= GemEnum.PRECIOUSNESS;
            gem.setPreciousness(findContent(elementGem, gemEnum.getValue()));
            gemEnum= GemEnum.ORIGIN;
            gem.setOrigin(findContent(elementGem, gemEnum.getValue()));
            gemEnum= GemEnum.COLOR;
            gem.setColor(findContent(elementGem, gemEnum.getValue()));
            gemEnum= GemEnum.FACETING;
            gem.setFaceting(findContent(elementGem, gemEnum.getValue()));
            gemEnum= GemEnum.TRANSPARENCY;
            double value=Double.parseDouble(findContent(elementGem, gemEnum.getValue()));
            gem.setTransparency(value);
            gemEnum= GemEnum.WEIGHT;
            value=Double.parseDouble(findContent(elementGem, gemEnum.getValue()));
            gem.setWeight(value);
        }
        catch (IncorrectDataException|NumberFormatException e)
        {
            logger.error(e);
        }
        return gem;
    }

    private static String findContent(Element elementGem, String elementName) throws IncorrectDataException
    {
        final int INDEX=0;
        NodeList list=elementGem.getElementsByTagName(elementName);
        if(list.getLength()==0)
        {
            throw new IncorrectDataException("Node with name "+elementName+" doesn't exist.");
        }
        Node node=list.item(INDEX);
        return node.getTextContent();
    }
}