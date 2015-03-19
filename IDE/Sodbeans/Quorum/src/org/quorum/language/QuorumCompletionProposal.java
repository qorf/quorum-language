/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import org.netbeans.modules.csl.api.ElementHandle;
import org.netbeans.modules.csl.api.ElementKind;
import org.netbeans.modules.csl.spi.DefaultCompletionProposal;

/**
 *
 * @author stefika
 */
public class QuorumCompletionProposal extends DefaultCompletionProposal{
    public QuorumCompletionProposal() {
        elementKind = ElementKind.METHOD;
    }
    
    @Override
    public ElementHandle getElement() {
        return new ElementHandle.UrlHandle("Bob");
    }

    
}
