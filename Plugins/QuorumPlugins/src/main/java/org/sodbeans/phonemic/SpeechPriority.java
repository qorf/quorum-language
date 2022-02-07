/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sodbeans.phonemic;

/**
 * Represents the available speech priorities that each TTS engine implements.
 * 
 * Note: Priorities are put in this order so that they work properly with the
 * priority queue.
 * @author Jeff Wilson
 */
public enum SpeechPriority {
    BLOCKING,
    HIGHEST,
    HIGH,
    MEDIUM_HIGH,
    MEDIUM,
    MEDIUM_LOW,
    LOW,
    LOWEST;
}