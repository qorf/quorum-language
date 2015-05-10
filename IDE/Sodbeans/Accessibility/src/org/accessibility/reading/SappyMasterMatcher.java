/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2007 Sun Microsystems, Inc.
 */

package org.accessibility.reading;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyleConstants;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.settings.AttributesUtilities;
import org.netbeans.api.editor.settings.EditorStyleConstants;
import org.netbeans.api.lexer.TokenHierarchy;
import org.netbeans.api.lexer.TokenSequence;
import org.netbeans.lib.editor.util.swing.DocumentUtilities;
import org.netbeans.spi.editor.bracesmatching.BracesMatcher;
import org.netbeans.spi.editor.bracesmatching.BracesMatcherFactory;
import org.openide.util.Exceptions;
import org.openide.util.RequestProcessor;

/**
 *
 * @author Vita Stejskal, Modified by Andreas Stefik and Susanna Siebert
 */
public class SappyMasterMatcher {

    private static final Logger LOG = Logger.getLogger(SappyMasterMatcher.class.getName());

    public static final String PROP_SEARCH_DIRECTION = "nbeditor-bracesMatching-searchDirection"; //NOI18N
    public static final String D_BACKWARD = "backward-preferred"; //NOI18N
    public static final String D_FORWARD = "forward-preferred"; //NOI18N

    public static final String PROP_CARET_BIAS = "nbeditor-bracesMatching-caretBias"; //NOI18N
    public static final String B_BACKWARD = "backward"; //NOI18N
    public static final String B_FORWARD = "forward"; //NOI18N

    public static final String PROP_MAX_BACKWARD_LOOKAHEAD = "nbeditor-bracesMatching-maxBackwardLookahead"; //NOI18N
    public static final String PROP_MAX_FORWARD_LOOKAHEAD = "nbeditor-bracesMatching-maxForwardLookahead"; //NOI18N
    private static final int DEFAULT_MAX_LOOKAHEAD = 1;
    private static final int MAX_MAX_LOOKAHEAD = 256;

    private int [] origin;
    private int [] matches;

    // Just for debugging
    public static final String PROP_SHOW_SEARCH_PARAMETERS = "debug-showSearchParameters-dont-ever-use-it-or-you-will-die"; //NOI18N
    private static final AttributeSet CARET_BIAS_HIGHLIGHT = AttributesUtilities.createImmutable(StyleConstants.Underline, Color.BLACK);
    private static final AttributeSet MAX_LOOKAHEAD_HIGHLIGHT = AttributesUtilities.createImmutable(EditorStyleConstants.WaveUnderlineColor, Color.BLUE);

    public static synchronized SappyMasterMatcher get(JTextComponent component) {
        SappyMasterMatcher mm = (SappyMasterMatcher) component.getClientProperty(SappyMasterMatcher.class);
        if (mm == null) {
            mm = new SappyMasterMatcher(component);
            component.putClientProperty(SappyMasterMatcher.class, mm);
        }
        return mm;
    }

    public static boolean isTaskCanceled() {
        Result threadTask = THREAD_RESULTS.get(Thread.currentThread());
        //assert threadTask != null : "MatcherContext.isTaskCanceled() should only be called from the matcher task's thread"; //NOI18N
        return threadTask.isCanceled();
    }


    private static final RequestProcessor PR = new RequestProcessor("EditorBracesMatching", 5, true); //NOI18N
    // package private just for tests
    /* package */ static final Map<Thread, Result> THREAD_RESULTS = Collections.synchronizedMap(new HashMap<Thread, Result>());

    private final String LOCK = new String("MasterMatcher.LOCK"); //NOI18N

    private final JTextComponent component;

    private RequestProcessor.Task task = null;
    private Result lastResult = null;
    private static Document currentDocument;

    public SappyMasterMatcher(JTextComponent component) {
        this.component = component;
    }

    private Object getAllowedDirection() {
        Object allowedDirection = component.getClientProperty(PROP_SEARCH_DIRECTION);
        return allowedDirection != null ? allowedDirection : D_BACKWARD;
    }

    private Object getCaretBias() {
        Object caretBias = component.getClientProperty(PROP_CARET_BIAS);
        return caretBias != null ? caretBias : B_BACKWARD;
    }

    private int getMaxLookahead(boolean backward) {
        String propName = backward ? PROP_MAX_BACKWARD_LOOKAHEAD : PROP_MAX_FORWARD_LOOKAHEAD;
        int maxLookahead = DEFAULT_MAX_LOOKAHEAD;
        Object value = component.getClientProperty(propName);
        if (value instanceof Integer) {
            maxLookahead = ((Integer) value).intValue();
        } else if (value != null) {
            try {
                maxLookahead = Integer.valueOf(value.toString());
            } catch (NumberFormatException nfe) {
                LOG.log(Level.WARNING, "Can't parse the value of " + propName + ": '" + value + "'", nfe); //NOI18N
            }
        }

        if (maxLookahead >= 0 && maxLookahead <= MAX_MAX_LOOKAHEAD) {
            return maxLookahead;
        } else {
            LOG.warning("Invalid value of " + propName + ": " + maxLookahead); //NOI18N
            return MAX_MAX_LOOKAHEAD;
        }
    }

    public static BracesMatcher[] findMatcher(BracesMatcher [] matcher, Collection<? extends BracesMatcherFactory> factories, Document document, int caret, boolean backward, int lookahead){
        if (!factories.isEmpty()) {
            try {
                currentDocument = document;
                Class m = Class.forName("org.netbeans.spi.editor.bracesmatching.MatcherContext");
                Constructor[] constructors = m.getDeclaredConstructors();
                Constructor fourParam = null;
                for(int i = 0; i < constructors.length; i++) {
                    if(constructors[i].getParameterTypes().length == 4) {
                        fourParam = constructors[i];
                    }
                }

                if(fourParam != null) {
                    fourParam.setAccessible(true);
                    Object match = fourParam.newInstance(document, caret, backward, lookahead);
                    

                    for(BracesMatcherFactory factory : factories) {
                        Class<? extends BracesMatcherFactory> factoryClass = factory.getClass();
                        Method[] declaredMethods = factoryClass.getDeclaredMethods();

                        Method method = null;
                        for(int j = 0; j < declaredMethods.length; j++) {
                            Method method2 = declaredMethods[j];

                            //This assumes it is the only method of this name, which may not always hold
                            if(method2.getName().compareTo("createMatcher") == 0) {
                                method = declaredMethods[j];
                            }
                        }

                        if(method != null) {
                            Object invoke = method.invoke(factory, match);
                            matcher[0] = (BracesMatcher) invoke;

                            if (matcher[0] != null) {
                              break;
                            }
                        }
                        
                    }
                }
            }  catch (ClassNotFoundException ex) {
                Exceptions.printStackTrace(ex);
            }
                catch (InstantiationException ex) {
                Exceptions.printStackTrace(ex);
            } catch (IllegalAccessException ex) {
                Exceptions.printStackTrace(ex);
            } catch (IllegalArgumentException ex) {
                Exceptions.printStackTrace(ex);
            } catch (InvocationTargetException ex) {
                Exceptions.printStackTrace(ex);
            }
            }
        return matcher;
    }

    public static Collection<? extends BracesMatcherFactory> findFactories(final Document document,
            final int offset, final boolean backward
    ) {
        final MimePath[] mimePath = { null };
        document.render(new Runnable() {
            public void run() {
                TokenHierarchy<? extends Document> th = TokenHierarchy.get(document);
                if (th.isActive()) {
                    List<TokenSequence<?>> sequences = th.embeddedTokenSequences(offset, backward);
                    if (!sequences.isEmpty()) {
                        String path = sequences.get(sequences.size() - 1).languagePath().mimePath();
                        mimePath[0] = MimePath.parse(path);
                    }
                } else {
                    String mimeType = (String) document.getProperty("mimeType"); //NOI18N
                    mimePath[0] = mimeType != null ? MimePath.parse(mimeType) : MimePath.EMPTY;
                }
            }
        });
        Collection<? extends BracesMatcherFactory> factories = mimePath[0] == null ?
            Collections.<BracesMatcherFactory>emptyList() :
            MimeLookup.getLookup(mimePath[0]).lookupAll(BracesMatcherFactory.class);

//        System.out.println("@@@ '" + (mimePath == null ? "null" : mimePath.getPath()) + "', offset = " + offset + ", backward = " + backward + " -> {");
//        for(BracesMatcherFactory f : factories) {
//            System.out.println("@@@    " + f);
//        }
//        System.out.println("@@@ } --------------");

        return factories;
    }

    public class Result /*implements Runnable*/ {

        private final Document document;
        private final int caretOffset;
        private final Object allowedDirection;
        private final Object caretBias;
        private final int maxBwdLookahead;
        private final int maxFwdLookahead;

//        private boolean inDocumentRender = false;
        private volatile boolean canceled = false;

        private final List<Object []> highlightingJobs = new ArrayList<Object []>();
        private final List<Object []> navigationJobs = new ArrayList<Object []>();

        public Result(
            Document document,
            int caretOffset,
            Object allowedDirection,
            Object caretBias,
            int maxBwdLookahead,
            int maxFwdLookahead
        ) {
            this.document = document;
            this.caretOffset = caretOffset;
            this.allowedDirection = allowedDirection;
            this.caretBias = caretBias;
            this.maxBwdLookahead = maxBwdLookahead;
            this.maxFwdLookahead = maxFwdLookahead;
        }

        public Object getAllowedDirection() {
            return allowedDirection;
        }

        public Object getCaretBias() {
            return caretBias;
        }

        public int getMaxBwdLookahead() {
            return maxBwdLookahead;
        }

        public int getMaxFwdLookahead() {
            return maxFwdLookahead;
        }

        public boolean isCanceled() {
            return canceled;
        }

        public void cancel() {
            this.canceled = true;
        }

        // ------------------------------------------------
        // Runnable implementation
        // ------------------------------------------------

//        public void run() {
//        }



        public int [] findOrigin(
            boolean backward,
            BracesMatcher [] matcher
        ) throws InterruptedException {
            Element paragraph = DocumentUtilities.getParagraphElement(document, caretOffset);

            int adjustedCaretOffset = caretOffset;
            int lookahead = 0;
            if (backward) {
                int maxLookahead = maxBwdLookahead;
                if (B_FORWARD.equalsIgnoreCase(caretBias.toString())) {
                    if (adjustedCaretOffset < paragraph.getEndOffset() - 1) {
                        adjustedCaretOffset++;
                        maxLookahead++;
                    }
                } else {
                    if (maxLookahead == 0) {
                        maxLookahead = 1;
                    }
                }

                lookahead = adjustedCaretOffset - paragraph.getStartOffset();
                if (lookahead > maxLookahead) {
                    lookahead = maxLookahead;
                }
            } else {
                int maxLookahead = maxFwdLookahead;
                if (B_BACKWARD.equalsIgnoreCase(caretBias.toString())) {
                    if (adjustedCaretOffset > paragraph.getStartOffset()) {
                        adjustedCaretOffset--;
                        maxLookahead++;
                    }
                } else {
                    if (maxLookahead == 0) {
                        maxLookahead = 1;
                    }
                }

                lookahead = paragraph.getEndOffset() - 1 - adjustedCaretOffset;
                if (lookahead > maxLookahead) {
                    lookahead = maxLookahead;
                }
            }

            if (matcher[0] != null) {
                // Find the original area



                
                int [] origin = null;
                try {
                    if(matcher[0].getClass().getName().compareTo("org.sodbeans.matching.QuorumEndMatcher")==0) {
                        origin = matcher[0].findOrigin();
                    }
                } catch (BadLocationException ex) {
                    Exceptions.printStackTrace(ex);
                }

                // Check the original area for consistency
                if (origin != null) {
                    if (origin.length == 0) {
                        origin = null;
                    } else if (origin.length % 2 != 0) {
                        if (LOG.isLoggable(Level.WARNING)) {
                            LOG.warning("Invalid BracesMatcher implementation, " + //NOI18N
                                "findOrigin() should return nothing or offset pairs. " + //NOI18N
                                "Offending BracesMatcher: " + matcher[0]); //NOI18N
                        }
                        origin = null;
                    } else {
                        for(int i = 0; i < origin.length / 2; i++) {
                            if (origin[2 * i] < 0 || origin[2 * i + 1] > document.getLength() || origin[2 * i] > origin[2 * i + 1]) {
                                if (LOG.isLoggable(Level.WARNING)) {
                                    LOG.warning("Invalid origin offsets [" + origin[2 * i] + ", " + origin[2 * i + 1] + "]. " + //NOI18N
                                        "Offending BracesMatcher: " + matcher[0]); //NOI18N
                                }
                                origin = null;
                                break;
                            }
                        }
                    }

                    if (origin != null) {
                        if (backward) {
                            if (origin[1] < caretOffset - lookahead || origin[0] > caretOffset) {
                                if (LOG.isLoggable(Level.WARNING)) {
                                    LOG.warning("Origin offsets out of range, " + //NOI18N
                                        "origin = [" + origin[0] + ", " + origin[1] + "], " + //NOI18N
                                        "caretOffset = " + caretOffset + //NOI18N
                                        ", lookahead = " + lookahead + //NOI18N
                                        ", searching backwards. " + //NOI18N
                                        "Offending BracesMatcher: " + matcher[0]); //NOI18N
                                }
                                origin = null;
                            }
                        } else {
                            if ((origin[1] < caretOffset || origin[0] > caretOffset + lookahead)) {
                                if (LOG.isLoggable(Level.WARNING)) {
                                    LOG.warning("Origin offsets out of range, " + //NOI18N
                                        "origin = [" + origin[0] + ", " + origin[1] + "], " + //NOI18N
                                        "caretOffset = " + caretOffset + //NOI18N
                                        ", lookahead = " + lookahead + //NOI18N
                                        ", searching forward. " + //NOI18N
                                        "Offending BracesMatcher: " + matcher[0]); //NOI18N
                                }
                                origin = null;
                            }
                        }
                    }
                }

                if (LOG.isLoggable(Level.FINE)) {
                    if (origin != null) {
                        LOG.fine("[" + origin[0] + ", " + origin[1] + "] for caret = " + caretOffset + ", lookahead = " + (backward ? "-" : "") + lookahead); //NOI18N
                    } else {
                        LOG.fine("[null] for caret = " + caretOffset + ", lookahead = " + (backward ? "-" : "") + lookahead); //NOI18N
                    }
                }

                return origin;
            } else {
                return null;
            }
        }

    }
}