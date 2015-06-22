/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

/**
 * An error class specific to the Games library, used to mark game errors.
 * 
 * @author alleew
 */
public class GameRuntimeError extends RuntimeException {
	
    //private static final long serialVersionUID = 6735854402467673117L;

    public GameRuntimeError (String message) 
    {
        super(message);
    }

    public GameRuntimeError (Throwable t) {
        super(t);
    }

    public GameRuntimeError (String message, Throwable t) {
        super(message, t);
    }
}