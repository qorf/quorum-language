/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
@TemplateRegistrations({
        @TemplateRegistration(
                folder = "Quorum", 
                iconBase="org/quorum/resources/file.png",
                displayName = "Quorum Class", 
                description = "QuorumClass.html",
                content = "Main.quorum",
                scriptEngine = "freemarker"),
        
        @TemplateRegistration(
                folder = "Quorum", 
                iconBase="org/quorum/resources/file.png",
                displayName = "Empty Quorum File", 
                description = "EmptyQuorumFile.html",
                content = "Empty.quorum",
                scriptEngine = "freemarker")

    })
package org.quorum.files;

import org.netbeans.api.templates.TemplateRegistration;
import org.netbeans.api.templates.TemplateRegistrations;
