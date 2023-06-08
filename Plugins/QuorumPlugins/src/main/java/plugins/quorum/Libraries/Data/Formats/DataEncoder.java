/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Data.Formats;

import quorum.Libraries.System.File_;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

/**
 *
 * @author alleew
 */
public class DataEncoder
{
    public java.lang.Object me_ = null;

    public String EncodeImageAsDataURI(File_ quorumFile) throws FileNotFoundException, IOException
    {
        File file = new File(quorumFile.GetAbsolutePath());
        String fileExtension = GetFileExtension(file);
        String result = "data:image/" + fileExtension + ";base64,";

        try
        {
            FileInputStream stream = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            stream.read(bytes);
            result = result + Base64.getEncoder().encodeToString(bytes);
        }
        catch (FileNotFoundException error)
        {
            throw error;
        }
        catch (IOException error)
        {
            throw error;
        }

        return result;
    }

    private String GetFileExtension(File file)
    {
        String result = "";
        String name = file.getName();
        int index = name.lastIndexOf('.');
        if (index > 0)
            result = name.substring((index + 1));

        return result;
    }

}
