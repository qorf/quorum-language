/*
 Copyright (c) 2013, Andreas Stefik
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met: 

 1. Redistributions of source code must retain the above copyright notice, this
 list of conditions and the following disclaimer. 
 2. Redistributions in binary form must reproduce the above copyright notice,
 this list of conditions and the following disclaimer in the documentation
 and/or other materials provided with the distribution. 

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 The views and conclusions contained in the software and documentation are those
 of the authors and should not be interpreted as representing official policies, 
 either expressed or implied, of the FreeBSD Project.
 */
package org.quorum.debugger;

import org.netbeans.api.debugger.DebuggerInfo;
import org.netbeans.api.debugger.DebuggerManager;
import org.netbeans.spi.debugger.SessionProvider;

/**
 * This class provides a method for telling NetBeans to begin debugging. As such
 * this class handles issues related to prepping the IDE, but the actual
 * starting of debugging itself is dong by the SodbeansDebugAction in the
 * Sodbeans Actions module. This class just handles the visual material,
 * like the flying out of the debugging controls, and setting up appropriate
 * cookies.
 * 
 * @author Andreas Stefik
 */
public class QuorumDebugStarter {

    public static final String QUORUM_DEBUGGER_INFO = "QuorumDebuggerInfo";
    public static final String QUORUM_SESSION = "QuorumSession";

    public void startDebugging() {
        QuorumDebuggerCookie cookie = new QuorumDebuggerCookie();
        DebuggerManager manager = DebuggerManager.getDebuggerManager();
        DebuggerInfo info = DebuggerInfo.create(QUORUM_DEBUGGER_INFO,
                new Object[]{
                    new SessionProvider() {

                        @Override
                        public String getSessionName() {
                            return "Quorum Program";
                        }

                        @Override
                        public String getLocationName() {
                            return "localhost";
                        }

                        public String getTypeID() {
                            return QUORUM_SESSION;
                        }

                        public Object[] getServices() {
                            return new Object[]{};
                        }
                    }, cookie
                });

        manager.startDebugging(info);
    }
}
