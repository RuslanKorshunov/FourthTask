package by.epam.fourthtask.builder;

import by.epam.fourthtask.entity.Gem;
import by.epam.fourthtask.entity.GemEnum;
import by.epam.fourthtask.exception.BuilderInitializationException;
import by.epam.fourthtask.exception.ParsingException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMBuilder extends AbstractBuilder
{
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
    public List<Gem> buildGems(String filePath) throws ParsingException
    {
        List<Gem> gems=new ArrayList<>();
        Document document;
        try
        {
            document=documentBuilder.parse(filePath);
            Element elementMain=document.getDocumentElement();
            NodeList gemList=elementMain.getElementsByTagName(GemEnum.GEM.getValue());
            GemBuilder gemBuilder=new GemBuilder();
            for(int index=0; index<gemList.getLength(); index++)
            {
                Element elementGem=(Element) gemList.item(index);
                Gem gem=gemBuilder.build(elementGem);
                gems.add(gem);
            }
        }
        catch (SAXException| IOException e)
        {
            throw new ParsingException("DOMBuilder can't parse "+filePath+".");
        }
        return gems;
    }
}