package by.epam.fourthtask.servlet;

import by.epam.fourthtask.builder.AbstractBuilder;
import by.epam.fourthtask.builder.BuilderFactory;
import by.epam.fourthtask.builder.FactoryEnum;
import by.epam.fourthtask.creator.FileCreator;
import by.epam.fourthtask.exception.BuilderInitializationException;
import by.epam.fourthtask.exception.IncorrectDataException;
import by.epam.fourthtask.exception.ParsingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@WebServlet(name = "ControlServlet", urlPatterns = {"/ControlServlet"})
@MultipartConfig
public class ControlServlet extends HttpServlet
{
    private static final String FILE="file";
    private static final String BUTTON="button";
    private static final String WEB_INF="WEB-INF";
    private static final String GEMS="gems";
    private static final String REFERER="referer";
    private static final Logger logger= LogManager.getLogger(ControlServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String hiddenParameter=request.getParameter(REFERER);
        request.getRequestDispatcher(hiddenParameter).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            processRequest(request, response);
        }
        catch (ParsingException|BuilderInitializationException|IncorrectDataException e)
        {
            logger.error(e);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                                                        IOException,
                                                                                        ParsingException,
                                                                                        BuilderInitializationException,
                                                                                        IncorrectDataException
    {
        System.out.println(request.getHeader(REFERER));
        Part filePart=request.getPart(FILE);
        String fileName=Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream stream=filePart.getInputStream();
        String data=new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));

        String directory=request.getServletContext().getRealPath("")+WEB_INF;
        FileCreator creator=new FileCreator();

        File file=creator.create(directory, fileName, data);
        String buttonParameter=request.getParameter(BUTTON);
        FactoryEnum factoryEnum= FactoryEnum.valueOf(buttonParameter);
        AbstractBuilder builder;
        BuilderFactory builderFactory=new BuilderFactory();
        switch (factoryEnum)
        {
            case DOM:
                builder=builderFactory.createBuilder(FactoryEnum.DOM);
                builder.buildGems(file.getAbsolutePath());
                break;
            case SAX:
                builder=builderFactory.createBuilder(FactoryEnum.SAX);
                builder.buildGems(file.getAbsolutePath());
                break;
            case StAX:
                builder=builderFactory.createBuilder(FactoryEnum.StAX);
                builder.buildGems(file.getAbsolutePath());
                break;
            default:
                throw new IncorrectDataException("buttonParameter="+buttonParameter+" has unknown value.");
        }
        file.delete();
        String page="jsp/main.jsp";
        request.setAttribute(REFERER, page);
        request.setAttribute(GEMS, builder.getGems());
        request.getRequestDispatcher("jsp/result.jsp").forward(request, response);
    }
}