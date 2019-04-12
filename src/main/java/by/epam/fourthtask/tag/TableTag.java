package by.epam.fourthtask.tag;

import by.epam.fourthtask.entity.Gem;
import by.epam.fourthtask.entity.GemEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("serial")
public class TableTag extends TagSupport
{
    private static final Logger logger= LogManager.getLogger(TableTag.class);

    private List<Gem> gems;

    public void setGems(List<Gem> gems)
    {
        this.gems=gems;
    }

    @Override
    public int doStartTag() throws JspException
    {
        try
        {
            JspWriter out=pageContext.getOut();
            createHeader(out);
            out.write("<tbody>");
            for(Gem gem:gems)
            {
                writeData(out, gem);
            }
        }
        catch(IOException e)
        {
            logger.error(e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException
    {
        try
        {
            JspWriter out=pageContext.getOut();
            out.write("</tbody></table>");
        }
        catch (IOException e)
        {
            logger.error(e);
        }
        return EVAL_PAGE;
    }

    private void createHeader(JspWriter out) throws IOException
    {
        out.write("<table border='1'><colgroup span='2' title='title' />");
        out.write("<caption>"+ "RESULT" +"</caption>");
        for(GemEnum gemEnum: GemEnum.values())
        {
            switch (gemEnum)
            {
                case FACETING:
                case TRANSPARENCY:
                case NAME:
                case PRECIOUSNESS:
                case WEIGHT:
                case ORIGIN:
                case COLOR:
                case KIND:
                    out.write("<th scope='col'>"+gemEnum.getValue()+"</th>");
                    break;
            }
        }
        out.write("</tr></thead>");
    }

    private void writeData(JspWriter out, Gem gem) throws IOException
    {
        out.write("<tr>");
        for(GemEnum gemEnum: GemEnum.values())
        {
            switch (gemEnum)
            {
                case KIND:
                    out.write("<td>"+gem.getKind()+"</td>");
                    break;
                case COLOR:
                    out.write("<td>"+gem.getColor()+"</td>");
                    break;
                case ORIGIN:
                    out.write("<td>"+gem.getOrigin()+"</td>");
                    break;
                case WEIGHT:
                    out.write("<td>"+gem.getWeight()+"</td>");
                    break;
                case PRECIOUSNESS:
                    out.write("<td>"+gem.getPreciousness()+"</td>");
                    break;
                case NAME:
                    out.write("<td>"+gem.getName()+"</td>");
                    break;
                case TRANSPARENCY:
                    out.write("<td>"+gem.getTransparency()+"</td>");
                    break;
                case FACETING:
                    out.write("<td>"+gem.getFaceting()+"</td>");
                    break;
            }
        }
        out.write("<tr>");
    }
}