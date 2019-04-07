package by.epam.fourthtask.builder;

import by.epam.fourthtask.entity.Gem;
import by.epam.fourthtask.entity.GemEnum;
import by.epam.fourthtask.exception.IncorrectDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class GemBuilder
{
    private static final Logger logger=LogManager.getLogger(GemBuilder.class);

    public Gem build(Element elementGem)
    {
        Gem gem=new Gem();
        GemEnum gemEnum=GemEnum.NAME;

        try
        {
            gem.setName(findContent(elementGem, gemEnum.getValue()));
            gemEnum=GemEnum.KIND;
            gem.setKind(findContent(elementGem, gemEnum.getValue()));
            gemEnum=GemEnum.PRECIOUSNESS;
            gem.setPreciousness(findContent(elementGem, gemEnum.getValue()));
            gemEnum=GemEnum.ORIGIN;
            gem.setOrigin(findContent(elementGem, gemEnum.getValue()));
            gemEnum=GemEnum.COLOR;
            gem.setColor(findContent(elementGem, gemEnum.getValue()));
            gemEnum=GemEnum.FACETING;
            gem.setFaceting(findContent(elementGem, gemEnum.getValue()));
            gemEnum=GemEnum.TRANSPARENCY;
            double value=Double.parseDouble(findContent(elementGem, gemEnum.getValue()));
            gem.setTransparency(value);
            gemEnum=GemEnum.WEIGHT;
            value=Double.parseDouble(findContent(elementGem, gemEnum.getValue()));
            gem.setWeight(value);
        }
        catch (IncorrectDataException|NumberFormatException e)//TODO ??NumberFormatException
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
        String result=node.getTextContent();
        return result;
    }
}
