/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.actions;

import quorum.Libraries.Language.Compile.CompilerRequest_;
import quorum.Libraries.Language.Compile.CompilerResult_;

/**
 *
 * @author stefika
 */
public class BuildInformation {
    public boolean success = false;
    public CompilerRequest_ request;
    public CompilerResult_ result;
}
