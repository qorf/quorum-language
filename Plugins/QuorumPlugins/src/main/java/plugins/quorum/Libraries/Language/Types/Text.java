/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Language.Types;

import java.util.regex.Pattern;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Language.Errors.InvalidLocationError;
import quorum.Libraries.Language.Errors.UndefinedObjectError;
import quorum.Libraries.Language.Object_;
import quorum.Libraries.Language.Types.Text_;

/**
 * A plugin for handling text system functions.
 * 
 * @author Andreas Stefik
 */
public class Text {
    public java.lang.Object me_ = null;
    private String text = "";
    
    public static String ConvertObjectToString(Object_ object) {
        if(object == null) {
            return null;
        }
        Text_ text = (Text_) object;
        return text.GetValue();
    }
    
    public static Object_ ConvertTextToObject(String value) {
        Text_ i = new quorum.Libraries.Language.Types.Text();
        i.SetValue(value);
        return i;
    }
    
    public static String ConvertTextObjectToText(Text_ object) {
        if(object == null) {
            throw new NullPointerException("Cannot convert an undefined type to an integer.");
        }
        return object.GetValue();
    }
    
    public static Text_ ConvertTextToTextObject(String value) {
        Text_ i = new quorum.Libraries.Language.Types.Text();
        i.SetValue(value);
        return i;
    }
    
    public static int CompareTo(String left, String right) {
        if(left != null && right != null) {
            return left.compareTo(right);
        } 
        return - 1;
    }
    
    public int ParseInteger() {//throws ParseError{
        try{
            int parseInt = java.lang.Integer.parseInt(text);
            return parseInt;
        }catch(NumberFormatException e){
            throw(e);
          //  ParseError error = new ParseError();
          //  error.SetErrorMessage("Parse Error: The text value could not be converted to an integer.");
          //  throw(error);
        }
    }
    
    public double ParseNumber() {//throws ParseError{
        try{
            double parseInt = java.lang.Double.parseDouble(text);
            return parseInt;
        }catch(NumberFormatException e){
            throw(e);
//            ParseError error = new ParseError();
//            error.SetErrorMessage("Parse Error: The text value could not be converted to an number.");
//            throw(error);
        }
    }
        
    public boolean ParseBoolean() {//throws ParseError{
        try{
            boolean parseInt = java.lang.Boolean.parseBoolean(text);
            return parseInt;
        }catch(NumberFormatException e){
            throw(e);
//            ParseError error = new ParseError();
//            error.SetErrorMessage("Parse Error: The text value could not be converted to an boolean.");
//            throw(error);
        }
    }
    
    public void SetValueNative(String value) {
        text = value;
    }
    
    public boolean ContainsNative(String lhv, String rhv){
        return lhv.contains(rhv);
    }
    
    public String GetCharacterNative(int index) {
        String ret = "" + text.charAt(index);
        return ret;
    }
    
    public Array_ Split(String delimiter, boolean keep) {
        return PrimitiveSplit(text, delimiter, keep);
    }
    
    public static Array_ PrimitiveSplit(String self, String delimiter, boolean keep) {
        Array_ array = new quorum.Libraries.Containers.Array();
        String quote = Pattern.quote(delimiter);
        String[] split = null;
        if(keep) {
            split = self.split(quote, -1); //indicates to Java to keep the trailing parts
        } else {
            split = self.split(quote);
        }
        
        for(int i = 0; i < split.length; i++) {
            String value = split[i];
            Text_ item = new quorum.Libraries.Language.Types.Text();
            item.SetValue(value);
            array.Add(item);
        }
        return array;
    }
    
    public int CompareInt(String left, String right, boolean isIgnoringCase) {
        if(isIgnoringCase){
            return left.compareToIgnoreCase(right);
        }else{
            return left.compareTo(right);
        }
    }
    
    public boolean EndsWithNative(String left, String suffix){
        return left.endsWith(suffix);
    }
    
    public boolean StartsWithNative(String left, String prefix){
        return left.startsWith(prefix);
    }
    
    public boolean EqualsIgnoringCaseNative(String left, String right){
        return left.equalsIgnoreCase(right);
    }
    
    public int IndexOfNative(String left, String right){
        return left.indexOf(right);
    }
    
    public int IndexOfNative(String left, String right, int index){
        return left.indexOf(right, index);
    }
    
    public boolean IsEmptyNative(String left){
        return left.isEmpty();
    }
    
    public String ReplaceNative(String left, String old, String replacement){
        return left.replace(old, replacement);
    }
    
    public String SubtextNative(String left, int start, int end){
        return left.substring(start, end);
    }
    
    public String GetSubtextNative(String left, int start){
        return left.substring(start);
    }
    
    public String ToLowerCase(){
        return text.toLowerCase();
    }
    
    public String ToUpperCase(){
        return text.toUpperCase();
    }
    
    public String TrimNative(String left){
        return left.trim();
    }
    
    public int GetSize() {
        return text.length();
    }
    
    public String GetCarriageReturn(){
        return "\r";
    }
    
    public String GetLineFeed(){
        return "\n";
    }
    
    public String GetTab(){
        return "\t";
    }
    
    public String GetDoubleQuote(){
        return "\"";
    }
    
    public String GetUnicodeValue(int unicode){
        return Character.toString((char)unicode);
    }
    
    public int GetUnicodeInteger(int index){
        char value = text.charAt(index);
        return (int) value;
    }
    
    public static int PrimitiveGetUnicodeInteger(String self, int index){
        char value = self.charAt(index);
        return (int) value;
    }

    /**
     * If the value is valid, return its hashcode. Otherwise, return the 
     * generic hash for the object.
     * 
     * @return 
     */
    public int GetHashCode() {
        if(text != null) {
            return text.hashCode();
        }
        else {
            return me_.hashCode();
        }
    }
    
    public static int PrimitiveParseInteger(String self) {// throws ParseError{
        try{
            int parseInt = java.lang.Integer.parseInt(self);
            return parseInt;
        }catch(NumberFormatException e){
            throw(e);
//            ParseError error = new ParseError();
//            error.SetErrorMessage("Parse Error: The text value could not be converted to an integer.");
//            throw(error);
        }
    }
    
    public static double PrimitiveParseNumber(String self) { //throws ParseError{
        try{
            double parseInt = java.lang.Double.parseDouble(self);
            return parseInt;
        }catch(NumberFormatException e){
            throw(e);
//            ParseError error = new ParseError();
//            error.SetErrorMessage("Parse Error: The text value could not be converted to an number.");
//            throw(error);
        }
    }
        
    public static boolean PrimitiveParseBoolean(String self) {//throws ParseError{
        try{
            boolean parseInt = java.lang.Boolean.parseBoolean(self);
            return parseInt;
        }catch(NumberFormatException e){
            throw(e);
//            ParseError error = new ParseError();
//            error.SetErrorMessage("Parse Error: The text value could not be converted to an boolean.");
//            throw(error);
        }
    }
    
    public static int PrimitiveGetSize(String self) {//throws Error {
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        return self.length();
    }
    
    public static int PrimitiveGetHashCode(String self) { //throws Error {
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        return self.hashCode();
    }
    
    public static String PrimitiveGetCharacter(String self, int index) { //throws Error{
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        
        int length = self.length();
        if(index < 0 || index >= length){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new InvalidLocationError();
//            error.SetErrorMessage("Text index out of range, " + index);
//            throw(error);
        }
        return "" + self.charAt(index);
    }
    
    public static Array_ PrimitiveSplit(String self, String delimiter) { //throws Error{
        
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        
        String[] split = self.split(delimiter);
        quorum.Libraries.Containers.Array a = new quorum.Libraries.Containers.Array();
        
        for(int i = 0; i < split.length; i++){
            Text_ t = new quorum.Libraries.Language.Types.Text();
            t.SetValue(split[i]);
            a.AddNative((Object_)t);
        }
        return a;
    }
    
    public static Array_ PrimitiveSplitIntoLines(String self) {
        quorum.Libraries.Language.Types.Text value = new quorum.Libraries.Language.Types.Text();
        value.SetValue(self);
        return value.SplitIntoLines();
    }
    
    public static boolean PrimitiveEquals(String self, Object_ obj) {//throws Error{
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        
        Text_ t = (Text_)obj;
        return self.equals(t.GetValue());
    }
    
    public static int PrimitiveCompare(String self, quorum.Libraries.Language.Object_ obj) {//throws Error{
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        
        Text_ t = (Text_)obj;
        int result = self.compareTo(t.GetValue());
        if(result == 0){
            return 0;
        } else if(result > 0){
            return 1;
        } else {
            return -1;
        }       
    }
    
    public static int PrimitiveCompareIgnoringCase(String self, quorum.Libraries.Language.Object_ obj) {//throws Error{
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        
        Text_ t = (Text_)obj;
        int result = self.compareToIgnoreCase(t.GetValue());
        if(result == 0){
            return 0;
        } else if(result > 0){
            return 1;
        } else {
            return -1;
        }
    }
    
    public static boolean PrimitiveContains(String self, String right) {//throws Error{
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        
        return self.contains(right); 
    }
    
    public static boolean PrimitiveEndsWith(String self, String suffix) {//throws Error{
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        
        return self.endsWith(suffix);
    }
    
    public static boolean PrimitiveStartsWith(String self, String prefix) {//throws Error{
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        
        return self.startsWith(prefix);
    }
    
    public static boolean PrimitiveEqualsIgnoringCase(String self, Object_ right) {//throws Error{
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        
        Text_ t = (Text_)right;
        return self.equalsIgnoreCase(t.GetValue());
    }
    
    public static int PrimitiveIndexOf(String self, String right) {//throws Error{
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        
        return self.indexOf(right);
    }
    
    public static int PrimitiveIndexOf(String self, String right, int index) {//throws Error{
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        
        int length = self.length();
        if(index < 0 || index >= length){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new InvalidLocationError();
//            error.SetErrorMessage("Text index out of range, " + index);
//            throw(error);
        }
        return self.indexOf(right, index);
    }
    
    public static boolean PrimitiveIsEmpty(String self) {//throws Error{
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        
        return self.isEmpty();
    }
    
    public static String PrimitiveReplace(String self, String old, String replacement) {//throws Error{
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        
        return self.replace(old, replacement);
    }
    
    public static String PrimitiveGetSubtext(String self, int startIndex, int endIndex) {
        return self.substring(startIndex, endIndex);
    }
    
    public String GetSubtext(int startIndex, int endIndex) {
        return text.substring(startIndex);
    }
    
    public static String PrimitiveGetSubtext(String self, int startIndex) {
        return self.substring(startIndex);
    }
    
    public static String PrimitiveToLowerCase(String self) {//throws Error{
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        
        return self.toLowerCase();
    }
    
    public static String PrimitiveToUpperCase(String self) {//throws Error{
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        
        return self.toUpperCase();
    }
    
    public static String PrimitiveTrim(String self) {//throws Error{
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        
        return self.trim();
    }
    
        
    public static String PrimitiveGetCarriageReturn(String self) {//throws Error{
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        return "\r";
    }
    
    public static String PrimitiveGetLineFeed(String self) {//throws Error{
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        return "\n";
    }
    
    public static String PrimitiveGetTab(String self) {//throws Error{
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        return "\t";
    }
    
    public static String PrimitiveGetDoubleQuote(String self) {//throws Error{
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        return "\"";
    }
    
    public static String PrimitiveGetUnicodeValue(String self, int unicode) {//throws Error{
        if(self == null){
            throw(new RuntimeException());
//            quorum.Libraries.Language.Errors.Error error = new UndefinedObjectError();
//            error.SetErrorMessage("Text variable is undefined.");
//            throw(error);
        }
        return Character.toString((char)unicode);
    }
}
