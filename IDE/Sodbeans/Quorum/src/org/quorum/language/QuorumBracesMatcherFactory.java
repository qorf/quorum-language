/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.language;

import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.bracesmatching.BracesMatcher;
import org.netbeans.spi.editor.bracesmatching.BracesMatcherFactory;
import org.netbeans.spi.editor.bracesmatching.MatcherContext;
import org.netbeans.spi.editor.bracesmatching.support.BracesMatcherSupport;
import static org.quorum.language.QuorumLanguageConfig.QUORUM_MIME_TYPE;

/**
 *
 * @author stefika
 */
@MimeRegistration(mimeType = QUORUM_MIME_TYPE, service = BracesMatcherFactory.class)
public class QuorumBracesMatcherFactory implements BracesMatcherFactory{

    @Override
    public BracesMatcher createMatcher(MatcherContext mc) {
        return new QuorumBracesMatcher(mc);
    }
    
}
