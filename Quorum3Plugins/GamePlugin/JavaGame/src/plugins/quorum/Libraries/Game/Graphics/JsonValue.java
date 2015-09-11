/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JsonValue// implements Iterable<JsonValue> 
{
    public enum ValueType 
    {
        object, array, stringValue, doubleValue, longValue, booleanValue, nullValue
    }
    
    private ValueType type;
    
    private String stringValue;
    private double doubleValue;
    private long longValue;
    
    public String name;
    
    public JsonValue child, next, prev;
    public int size;
    public JsonValue(ValueType type)
    {
        this.type = type;
    }
    
    public JsonValue(String value)
    {
        Set(value);
    }
    
    public JsonValue(double value)
    {
        Set(value, null);
    }
    
    public JsonValue(long value)
    {
        //Set(value), null;
    }
    
    public JsonValue(double value, String stringValue)
    {
        Set(value, stringValue);
    }
    
    public JsonValue(long value, String stringValue)
    {
        Set(value, stringValue);
    }
    
    public JsonValue(boolean value)
    {
        Set(value);
    }
    
    public void Set(String value)
    {
        stringValue = value;
        type = value == null ? ValueType.nullValue : ValueType.stringValue;
    }
    
    public void Set(double value, String stringValue)
    {
        doubleValue = value;
        longValue = (long)value;
        this.stringValue = stringValue;
        type = ValueType.doubleValue;
    }
    
    public void Set(long value, String stringValue)
    {
        longValue = value;
        doubleValue = (double)value;
        this.stringValue = stringValue;
        type = ValueType.longValue;
    }
    
    public void Set(boolean value)
    {
        longValue = value ? 1 : 0;
        type = ValueType.booleanValue;
    }
    
    public JsonValue Get(int index)
    {
        JsonValue current = child;
        while (current != null && index > 0)
        {
            index--;
            current = current.next;
        }
        return current;
    }
    
    public JsonValue Get(String name)
    {
        JsonValue current = child;
        while (current != null && !current.name.equalsIgnoreCase(name))
            current = current.next;
        
        return current;
    }
    
    public JsonValue Require(int index)
    {
        JsonValue current = child;
        while (current != null && index > 0)
        {
            index--;
            current = current.next;
        }
        if (current == null)
            throw new IllegalArgumentException("Child not found with index: " + index);
        return current;
    }
    
    public JsonValue Require(String name)
    {
        JsonValue current = child;
        while (current != null && !current.name.equalsIgnoreCase(name))
            current = current.next;
        
        if (current == null)
            throw new IllegalArgumentException("Child not found with name: " + name);
        return current;
    }
    
    public JsonValue Remove(int index)
    {
        JsonValue child = Get(index);
        if (child == null)
            return null;
        if (child.prev == null)
        {
            this.child = child.next;
            if (this.child != null)
                child.next.prev = child.prev;
        }
        else
        {
            child.prev.next = child.next;
            if (child.next != null)
                child.next.prev = child.prev;
        }
        size--;
        return child;
    }
    
    public JsonValue Remove(String name)
    {
        JsonValue child = Get(name);
        if (child == null)
            return null;
        if (child.prev == null)
        {
            this.child = child.next;
            if (this.child != null)
                this.child.prev = null;
        }
        else
        {
            child.prev.next = child.next;
            if (child.next != null)
                child.next.prev = child.prev;
        }
        size--;
        return child;
    }
}
