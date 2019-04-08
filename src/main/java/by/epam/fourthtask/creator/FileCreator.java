package by.epam.fourthtask.creator;

import by.epam.fourthtask.exception.IncorrectDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class FileCreator
{
    private static final Logger logger=LogManager.getLogger(FileCreator.class);

    public File create(String directory, String name, String data) throws IncorrectDataException
    {
        File file=new File(directory, "temp_"+name);
        OutputStreamWriter writer=null;
        try
        {
            boolean result;
            if(file.exists())
            {
                result=file.delete();
            }
            result=file.createNewFile();
            if(!result)
            {
                throw new IncorrectDataException("FileCreator can't create file with name "+name+" in "+directory+".");
            }
            writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            writer.write(data.toCharArray());
        }
        catch (IOException e)
        {
            throw new IncorrectDataException("FileCreator can't write in file with name "+name+" in "+directory+" data.", e);
        }
        finally
        {
            if(writer!=null)
            {
                try
                {
                    writer.close();
                }
                catch (IOException e)
                {
                    logger.warn("Impossible close writer.", e);
                }
            }
        }
        return file;
    }
}
