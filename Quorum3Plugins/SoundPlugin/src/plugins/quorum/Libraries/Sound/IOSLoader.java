/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound;

import quorum.Libraries.System.File_;

/**
 *
 * @author alleew
 */
public class IOSLoader implements DataLoader
{

    @Override
    public Data Load(File_ quorumFile) 
    {
        return new IOSSoundData(quorumFile);
    }

    @Override
    public Data LoadToStream(File_ quorumFile) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
