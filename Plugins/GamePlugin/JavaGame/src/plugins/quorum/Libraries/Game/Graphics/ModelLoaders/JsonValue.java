/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics.ModelLoaders;

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
        Set(value, null);
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
    
    public int GetSize()
    {
        return size;
    }
    
    public String AsString() 
    {
        switch (type) 
        {
            case stringValue:
                    return stringValue;
            case doubleValue:
                    return stringValue != null ? stringValue : Double.toString(doubleValue);
            case longValue:
                    return stringValue != null ? stringValue : Long.toString(longValue);
            case booleanValue:
                    return longValue != 0 ? "true" : "false";
            case nullValue:
                    return null;
        }
        throw new IllegalStateException("Value cannot be converted to string: " + type);
    }
    
    public float AsFloat() 
    {
        switch (type) 
        {
            case stringValue:
                    return Float.parseFloat(stringValue);
            case doubleValue:
                    return (float)doubleValue;
            case longValue:
                    return (float)longValue;
            case booleanValue:
                    return longValue != 0 ? 1 : 0;
        }
        throw new IllegalStateException("Value cannot be converted to float: " + type);
    }
    
    public double AsDouble() 
    {
        switch (type) 
        {
            case stringValue:
                    return Double.parseDouble(stringValue);
            case doubleValue:
                    return doubleValue;
            case longValue:
                    return (double)longValue;
            case booleanValue:
                    return longValue != 0 ? 1 : 0;
        }
        throw new IllegalStateException("Value cannot be converted to double: " + type);
    }
    
    public long AsLong() 
    {
        switch (type) 
        {
            case stringValue:
                    return Long.parseLong(stringValue);
            case doubleValue:
                    return (long)doubleValue;
            case longValue:
                    return longValue;
            case booleanValue:
                    return longValue != 0 ? 1 : 0;
        }
        throw new IllegalStateException("Value cannot be converted to long: " + type);
    }
    
    public int AsInteger() 
    {
        switch (type) 
        {
            case stringValue:
                    return Integer.parseInt(stringValue);
            case doubleValue:
                    return (int)doubleValue;
            case longValue:
                    return (int)longValue;
            case booleanValue:
                    return longValue != 0 ? 1 : 0;
        }
        throw new IllegalStateException("Value cannot be converted to int: " + type);
    }

    public boolean AsBoolean() 
    {
        switch (type) 
        {
            case stringValue:
                    return stringValue.equalsIgnoreCase("true");
            case doubleValue:
                    return doubleValue != 0;
            case longValue:
                    return longValue != 0;
            case booleanValue:
                    return longValue != 0;
        }
        throw new IllegalStateException("Value cannot be converted to boolean: " + type);
    }
    
    public byte AsByte() 
    {
        switch (type) 
        {
            case stringValue:
                    return Byte.parseByte(stringValue);
            case doubleValue:
                    return (byte)doubleValue;
            case longValue:
                    return (byte)longValue;
            case booleanValue:
                    return longValue != 0 ? (byte)1 : 0;
        }
        throw new IllegalStateException("Value cannot be converted to byte: " + type);
    }
    
    public short AsShort() 
    {
        switch (type) 
        {
            case stringValue:
                    return Short.parseShort(stringValue);
            case doubleValue:
                    return (short)doubleValue;
            case longValue:
                    return (short)longValue;
            case booleanValue:
                    return longValue != 0 ? (short)1 : 0;
        }
        throw new IllegalStateException("Value cannot be converted to short: " + type);
    }

    public char AsChar() 
    {
        switch (type) 
        {
            case stringValue:
                return stringValue.length() == 0 ? 0 : stringValue.charAt(0);
            case doubleValue:
                return (char)doubleValue;
            case longValue:
                return (char)longValue;
            case booleanValue:
                return longValue != 0 ? (char)1 : 0;
        }
        throw new IllegalStateException("Value cannot be converted to char: " + type);
    }
    
    public String[] AsStringArray () 
    {
        if (type != ValueType.array)
            throw new IllegalStateException("Value is not an array: " + type);
        
        String[] array = new String[size];
        int i = 0;
        for (JsonValue value = child; value != null; value = value.next, i++)
        {
            String v;
            switch (value.type) 
            {
                case stringValue:
                    v = value.stringValue;
                    break;
                case doubleValue:
                    v = stringValue != null ? stringValue : Double.toString(value.doubleValue);
                    break;
                case longValue:
                    v = stringValue != null ? stringValue : Long.toString(value.longValue);
                    break;
                case booleanValue:
                    v = value.longValue != 0 ? "true" : "false";
                    break;
                case nullValue:
                    v = null;
                    break;
                default:
                    throw new IllegalStateException("Value cannot be converted to string: " + value.type);
            }
            array[i] = v;
        }
        return array;
    }

    public float[] AsFloatArray () 
    {
        if (type != ValueType.array)
            throw new IllegalStateException("Value is not an array: " + type);
        float[] array = new float[size];
        int i = 0;
        for (JsonValue value = child; value != null; value = value.next, i++) 
        {
            float v;
            switch (value.type) 
            {
                case stringValue:
                    v = Float.parseFloat(value.stringValue);
                    break;
                case doubleValue:
                    v = (float)value.doubleValue;
                    break;
                case longValue:
                    v = (float)value.longValue;
                    break;
                case booleanValue:
                    v = value.longValue != 0 ? 1 : 0;
                    break;
                default:
                    throw new IllegalStateException("Value cannot be converted to float: " + value.type);
            }
            array[i] = v;
        }
        return array;
    }

    public double[] AsDoubleArray ()
    {
        if (type != ValueType.array)
            throw new IllegalStateException("Value is not an array: " + type);
        double[] array = new double[size];
        int i = 0;
        for (JsonValue value = child; value != null; value = value.next, i++) 
        {
            double v;
            switch (value.type) 
            {
                case stringValue:
                    v = Double.parseDouble(value.stringValue);
                    break;
                case doubleValue:
                    v = value.doubleValue;
                    break;
                case longValue:
                    v = (double)value.longValue;
                    break;
                case booleanValue:
                    v = value.longValue != 0 ? 1 : 0;
                    break;
                default:
                    throw new IllegalStateException("Value cannot be converted to double: " + value.type);
            }
            array[i] = v;
        }
        return array;
    }

    public long[] AsLongArray () 
    {
        if (type != ValueType.array)
            throw new IllegalStateException("Value is not an array: " + type);
        long[] array = new long[size];
        int i = 0;
        for (JsonValue value = child; value != null; value = value.next, i++) 
        {
            long v;
            switch (value.type) 
            {
            case stringValue:
                v = Long.parseLong(value.stringValue);
                break;
            case doubleValue:
                v = (long)value.doubleValue;
                break;
            case longValue:
                v = value.longValue;
                break;
            case booleanValue:
                v = value.longValue != 0 ? 1 : 0;
                break;
            default:
                throw new IllegalStateException("Value cannot be converted to long: " + value.type);
            }
            array[i] = v;
        }
        return array;
    }

    public int[] AsIntArray () 
    {
        if (type != ValueType.array)
            throw new IllegalStateException("Value is not an array: " + type);
        int[] array = new int[size];
        int i = 0;
        for (JsonValue value = child; value != null; value = value.next, i++) 
        {
            int v;
            switch (value.type) 
            {
                case stringValue:
                    v = Integer.parseInt(value.stringValue);
                    break;
                case doubleValue:
                    v = (int)value.doubleValue;
                    break;
                case longValue:
                    v = (int)value.longValue;
                    break;
                case booleanValue:
                    v = value.longValue != 0 ? 1 : 0;
                    break;
                default:
                    throw new IllegalStateException("Value cannot be converted to int: " + value.type);
            }
            array[i] = v;
        }
        return array;
    }

    public boolean[] AsBooleanArray () 
    {
        if (type != ValueType.array)
            throw new IllegalStateException("Value is not an array: " + type);
        boolean[] array = new boolean[size];
        int i = 0;
        for (JsonValue value = child; value != null; value = value.next, i++) 
        {
            boolean v;
            switch (value.type) 
            {
                case stringValue:
                    v = Boolean.parseBoolean(value.stringValue);
                    break;
                case doubleValue:
                    v = value.doubleValue == 0;
                    break;
                case longValue:
                    v = value.longValue == 0;
                    break;
                case booleanValue:
                    v = value.longValue != 0;
                    break;
                default:
                    throw new IllegalStateException("Value cannot be converted to boolean: " + value.type);
            }
            array[i] = v;
        }
        return array;
    }

    public byte[] AsByteArray () 
    {
        if (type != ValueType.array)
            throw new IllegalStateException("Value is not an array: " + type);
        byte[] array = new byte[size];
        int i = 0;
        for (JsonValue value = child; value != null; value = value.next, i++) 
        {
            byte v;
            switch (value.type) 
            {
                case stringValue:
                    v = Byte.parseByte(value.stringValue);
                    break;
                case doubleValue:
                    v = (byte)value.doubleValue;
                    break;
                case longValue:
                    v = (byte)value.longValue;
                    break;
                case booleanValue:
                    v = value.longValue != 0 ? (byte)1 : 0;
                    break;
                default:
                    throw new IllegalStateException("Value cannot be converted to byte: " + value.type);
            }
            array[i] = v;
        }
        return array;
    }

    public short[] AsShortArray () 
    {
        if (type != ValueType.array)
            throw new IllegalStateException("Value is not an array: " + type);
        short[] array = new short[size];
        int i = 0;
        for (JsonValue value = child; value != null; value = value.next, i++) 
        {
            short v;
            switch (value.type) 
            {
                case stringValue:
                    v = Short.parseShort(value.stringValue);
                    break;
                case doubleValue:
                    v = (short)value.doubleValue;
                    break;
                case longValue:
                    v = (short)value.longValue;
                    break;
                case booleanValue:
                    v = value.longValue != 0 ? (short)1 : 0;
                    break;
                default:
                    throw new IllegalStateException("Value cannot be converted to short: " + value.type);
            }
            array[i] = v;
        }
        return array;
    }

    public char[] AsCharArray () 
    {
        if (type != ValueType.array)
            throw new IllegalStateException("Value is not an array: " + type);
        char[] array = new char[size];
        int i = 0;
        for (JsonValue value = child; value != null; value = value.next, i++) 
        {
            char v;
            switch (value.type) 
            {
                case stringValue:
                    v = value.stringValue.length() == 0 ? 0 : value.stringValue.charAt(0);
                    break;
                case doubleValue:
                    v = (char)value.doubleValue;
                    break;
                case longValue:
                    v = (char)value.longValue;
                    break;
                case booleanValue:
                    v = value.longValue != 0 ? (char)1 : 0;
                    break;
                default:
                    throw new IllegalStateException("Value cannot be converted to char: " + value.type);
            }
            array[i] = v;
        }
        return array;
    }
    
    public boolean HasChild(String name) 
    {
        return GetChild(name) != null;
    }

    public JsonValue GetChild(String name) 
    {
        JsonValue child = Get(name);
        return child == null ? null : child.child;
    }
    
    public String GetString(String name, String defaultValue) 
    {
        JsonValue child = Get(name);
        return (child == null || !child.IsValue() || child.IsNull()) ? defaultValue : child.AsString();
    }

    public float GetFloat(String name, float defaultValue) 
    {
        JsonValue child = Get(name);
        return (child == null || !child.IsValue()) ? defaultValue : child.AsFloat();
    }

    public double GetDouble(String name, double defaultValue)
    {
        JsonValue child = Get(name);
        return (child == null || !child.IsValue()) ? defaultValue : child.AsDouble();
    }

    public long GetLong(String name, long defaultValue) 
    {
        JsonValue child = Get(name);
        return (child == null || !child.IsValue()) ? defaultValue : child.AsLong();
    }

    public int GetInteger(String name, int defaultValue) 
    {
            JsonValue child = Get(name);
            return (child == null || !child.IsValue()) ? defaultValue : child.AsInteger();
    }

    public boolean GetBoolean(String name, boolean defaultValue) 
    {
            JsonValue child = Get(name);
            return (child == null || !child.IsValue()) ? defaultValue : child.AsBoolean();
    }

    public byte GetByte(String name, byte defaultValue) 
    {
        JsonValue child = Get(name);
        return (child == null || !child.IsValue()) ? defaultValue : child.AsByte();
    }

    public short GetShort (String name, short defaultValue) 
    {
        JsonValue child = Get(name);
        return (child == null || !child.IsValue()) ? defaultValue : child.AsShort();
    }

    public char GetChar(String name, char defaultValue) 
    {
        JsonValue child = Get(name);
        return (child == null || !child.IsValue()) ? defaultValue : child.AsChar();
    }

    public String GetString(String name) 
    {
            JsonValue child = Get(name);
            if (child == null)
                throw new IllegalArgumentException("Named value not found: " + name);
            return child.AsString();
    }

    public float GetFloat(String name) 
    {
        JsonValue child = Get(name);
        if (child == null)
            throw new IllegalArgumentException("Named value not found: " + name);
        return child.AsFloat();
    }

    public double GetDouble(String name) 
    {
        JsonValue child = Get(name);
        if (child == null)
            throw new IllegalArgumentException("Named value not found: " + name);
        return child.AsDouble();
    }

    public long GetLong(String name) 
    {
            JsonValue child = Get(name);
            if (child == null)
                throw new IllegalArgumentException("Named value not found: " + name);
            return child.AsLong();
    }

    public int GetInteger(String name) 
    {
            JsonValue child = Get(name);
            if (child == null)
                throw new IllegalArgumentException("Named value not found: " + name);
            return child.AsInteger();
    }

    public boolean GetBoolean(String name)
    {
        JsonValue child = Get(name);
        if (child == null)
            throw new IllegalArgumentException("Named value not found: " + name);
        return child.AsBoolean();
    }

    public byte GetByte(String name) 
    {
        JsonValue child = Get(name);
        if (child == null)
            throw new IllegalArgumentException("Named value not found: " + name);
        return child.AsByte();
    }

    public short GetShort (String name) 
    {
        JsonValue child = Get(name);
        if (child == null)
            throw new IllegalArgumentException("Named value not found: " + name);
        return child.AsShort();
    }

    public char GetChar(String name)
    {
        JsonValue child = Get(name);
        if (child == null)
            throw new IllegalArgumentException("Named value not found: " + name);
        return child.AsChar();
    }

    public String GetString(int index) 
    {
        JsonValue child = Get(index);
        if (child == null)
            throw new IllegalArgumentException("Indexed value not found: " + name);
        return child.AsString();
    }

    public float GetFloat(int index) 
    {
            JsonValue child = Get(index);
            if (child == null)
                throw new IllegalArgumentException("Indexed value not found: " + name);
            return child.AsFloat();
    }

    public double GetDouble(int index) 
    {
        JsonValue child = Get(index);
        if (child == null)
            throw new IllegalArgumentException("Indexed value not found: " + name);
        return child.AsDouble();
    }

    public long GetLong(int index) 
    {
        JsonValue child = Get(index);
        if (child == null)
            throw new IllegalArgumentException("Indexed value not found: " + name);
        return child.AsLong();
    }

    public int GetInteger(int index) 
    {
        JsonValue child = Get(index);
        if (child == null) 
            throw new IllegalArgumentException("Indexed value not found: " + name);
        return child.AsInteger();
    }

    public boolean GetBoolean(int index)
    {
        JsonValue child = Get(index);
        if (child == null)
            throw new IllegalArgumentException("Indexed value not found: " + name);
        return child.AsBoolean();
    }

    public byte GetByte(int index) 
    {
        JsonValue child = Get(index);
        if (child == null)
            throw new IllegalArgumentException("Indexed value not found: " + name);
        return child.AsByte();
    }

    public short GetShort (int index) 
    {
        JsonValue child = Get(index);
        if (child == null)
            throw new IllegalArgumentException("Indexed value not found: " + name);
        return child.AsShort();
    }

    public char GetChar(int index) 
    {
        JsonValue child = Get(index);
        if (child == null) 
            throw new IllegalArgumentException("Indexed value not found: " + name);
        return child.AsChar();
    }
    
    public ValueType GetType()
    {
        return type;
    }

    public void SetType(ValueType type) 
    {
        if (type == null) 
            throw new IllegalArgumentException("type cannot be null.");
        this.type = type;
    }

    public boolean IsArray()
    {
        return type == ValueType.array;
    }

    public boolean IsObject()
    {
        return type == ValueType.object;
    }

    public boolean IsString()
    {
        return type == ValueType.stringValue;
    }

    /** Returns true if this is a double or long value. */
    public boolean IsNumber()
    {
        return type == ValueType.doubleValue || type == ValueType.longValue;
    }

    public boolean IsDouble()
    {
        return type == ValueType.doubleValue;
    }

    public boolean IsLong()
    {
        return type == ValueType.longValue;
    }

    public boolean IsBoolean()
    {
        return type == ValueType.booleanValue;
    }

    public boolean IsNull()
    {
        return type == ValueType.nullValue;
    }

    /** Returns true if this is not an array or object. */
    public boolean IsValue()
    {
        switch (type) 
        {
            case stringValue:
            case doubleValue:
            case longValue:
            case booleanValue:
            case nullValue:
                    return true;
        }
            return false;
    }

    public String GetName()
    {
        return name;
    }

    public void SetName(String name)
    {
        this.name = name;
    }
    
    public JsonValue GetChild() 
    {
        return child;
    }

    public JsonValue GetNext() 
    {
        return next;
    }

    public void SetNext(JsonValue next)
    {
        this.next = next;
    }

    public JsonValue GetPrevious()
    {
        return prev;
    }

    public void SetPrevious(JsonValue prev)
    {
        this.prev = prev;
    }

}
