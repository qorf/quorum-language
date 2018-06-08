/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound;

/**
 *
 * @author alleew
 */
public interface AudioManager 
{
    public void SetListenerPosition(double x, double y, double z);
    
    public double GetListenerX();
    
    public double GetListenerY();
    
    public double GetListenerZ();
    
    public void SetListenerVelocity(double x, double y, double z);
    
    public double GetListenerVelocityX();
    
    public double GetListenerVelocityY();
    
    public double GetListenerVelocityZ();
    
    public void EnableListenerDoppler();
    
    public void DisableListenerDoppler();
    
    public boolean IsListenerDopplerEnabled();
    
    public void SetListenerDirection(double x, double y, double z);
    
    public void SetListenerUp(double x, double y, double z);
    
    public double GetListenerDirectionX();
    
    public double GetListenerDirectionY();
    
    public double GetListenerDirectionZ();
    
    public double GetListenerUpX();
    
    public double GetListenerUpY();
    
    public double GetListenerUpZ();
}
