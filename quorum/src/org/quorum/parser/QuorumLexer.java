// $ANTLR 3.3 Nov 30, 2010 12:45:30 /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g 2012-01-24 20:14:22
package org.quorum.parser;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import org.quorum.vm.interfaces.CompilerErrorManager;
import org.quorum.vm.interfaces.CompilerError;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class QuorumLexer extends Lexer {
    public static final int EOF=-1;
    public static final int FUNCTION_CALL=4;
    public static final int FUNCTION_CALL_PARENT=5;
    public static final int FUNCTION_CALL_THIS=6;
    public static final int FUNCTION_EXPRESSION_LIST=7;
    public static final int SOLO_FUNCTION_CALL=8;
    public static final int SOLO_FUNCTION_CALL_PARENT=9;
    public static final int SOLO_FUNCTION_CALL_THIS=10;
    public static final int QUALIFIED_NAME=11;
    public static final int EXPRESSION_STATEMENT=12;
    public static final int STATEMENT_LIST=13;
    public static final int CONSTRUCTOR=14;
    public static final int FPARAM=15;
    public static final int UNARY_NOT=16;
    public static final int ELSE_IF_STATEMENT=17;
    public static final int FINAL_ELSE=18;
    public static final int PAREN_WRAPPED_EXPRESSION=19;
    public static final int ROOT_EXPRESSION=20;
    public static final int QUALIFIED_SOLO_EXPRESSION=21;
    public static final int QUALIFIED_SOLO_EXPRESSION_SELECTOR=22;
    public static final int QUALIFIED_SOLO_PARENT_EXPRESSON=23;
    public static final int GENERIC=24;
    public static final int PACKAGE_NAME=25;
    public static final int USE=26;
    public static final int CLASS=27;
    public static final int ID=28;
    public static final int END=29;
    public static final int INHERITS=30;
    public static final int COMMA=31;
    public static final int PUBLIC=32;
    public static final int PRIVATE=33;
    public static final int ACTION=34;
    public static final int LEFT_PAREN=35;
    public static final int RIGHT_PAREN=36;
    public static final int RETURNS=37;
    public static final int BLUEPRINT=38;
    public static final int NATIVE=39;
    public static final int ON_CREATE=40;
    public static final int PERIOD=41;
    public static final int COLON=42;
    public static final int PARENT=43;
    public static final int ME=44;
    public static final int LIBRARY_CALL=45;
    public static final int CONNECT_TO=46;
    public static final int SEND_TO=47;
    public static final int ALERT=48;
    public static final int CHECK=49;
    public static final int DETECT=50;
    public static final int ALWAYS=51;
    public static final int OF_TYPE=52;
    public static final int OR=53;
    public static final int PRINT=54;
    public static final int SAY=55;
    public static final int RETURN=56;
    public static final int NOW=57;
    public static final int LESS=58;
    public static final int GREATER=59;
    public static final int INTEGER_KEYWORD=60;
    public static final int NUMBER_KEYWORD=61;
    public static final int TEXT=62;
    public static final int BOOLEAN_KEYWORD=63;
    public static final int EQUALITY=64;
    public static final int IF=65;
    public static final int THEN=66;
    public static final int ELSE=67;
    public static final int REPEAT=68;
    public static final int OVER=69;
    public static final int FROM=70;
    public static final int TIMES=71;
    public static final int WHILE=72;
    public static final int UNTIL=73;
    public static final int TO=74;
    public static final int AND=75;
    public static final int NOTEQUALS=76;
    public static final int GREATER_EQUAL=77;
    public static final int LESS_EQUAL=78;
    public static final int PLUS=79;
    public static final int MINUS=80;
    public static final int MULTIPLY=81;
    public static final int DIVIDE=82;
    public static final int MODULO=83;
    public static final int NOT=84;
    public static final int CAST=85;
    public static final int INT=86;
    public static final int BOOLEAN=87;
    public static final int DECIMAL=88;
    public static final int STRING=89;
    public static final int NULL=90;
    public static final int INPUT=91;
    public static final int ON_DESTROY=92;
    public static final int LEFT_SQR_BRACE=93;
    public static final int RIGHT_SQR_BRACE=94;
    public static final int DOUBLE_QUOTE=95;
    public static final int NEWLINE=96;
    public static final int WS=97;
    public static final int COMMENTS=98;

    	public static final int HIDDEN_DOCUMENTATION = 100;
    	QuorumVirtualMachine vm;
    	String fileName;
    	public void setQuorumVirtualMachine(QuorumVirtualMachine m) {
    		vm = m;
    	}
    	
    	public String getGrammarFileNameNoExtension() {
    		return fileName;
    	}
    	
    	public void setGrammarFileNameNoExtension(String name) {
    		fileName = name;
    	}
    	
    	public void emitErrorMessage(String msg) {
    		//super.emitErrorMessage(msg);
     	}
    	public void recover(RecognitionException re) {
    		if(vm != null) {
    			String message = super.getErrorMessage(re, null);
    			CompilerError error = new CompilerError();
    			error.setLineNumber(re.line);
    			error.setColumn(re.charPositionInLine);
    			error.setError(message);
    			error.setFile(fileName);
    			CompilerErrorManager ces = vm.getCompilerErrors();
    			ces.addError(error);
    		}
    		super.recover(re);
    	}



    // delegates
    // delegators

    public QuorumLexer() {;} 
    public QuorumLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public QuorumLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g"; }

    // $ANTLR start "ME"
    public final void mME() throws RecognitionException {
        try {
            int _type = ME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1167:4: ( 'me' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1167:6: 'me'
            {
            match("me"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ME"

    // $ANTLR start "UNTIL"
    public final void mUNTIL() throws RecognitionException {
        try {
            int _type = UNTIL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1168:7: ( 'until' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1168:9: 'until'
            {
            match("until"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UNTIL"

    // $ANTLR start "ON_DESTROY"
    public final void mON_DESTROY() throws RecognitionException {
        try {
            int _type = ON_DESTROY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1170:2: ( 'on destroy' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1170:4: 'on destroy'
            {
            match("on destroy"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ON_DESTROY"

    // $ANTLR start "ON_CREATE"
    public final void mON_CREATE() throws RecognitionException {
        try {
            int _type = ON_CREATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1172:2: ( 'on create' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1172:4: 'on create'
            {
            match("on create"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ON_CREATE"

    // $ANTLR start "OF_TYPE"
    public final void mOF_TYPE() throws RecognitionException {
        try {
            int _type = OF_TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1173:9: ( 'of type' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1173:11: 'of type'
            {
            match("of type"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OF_TYPE"

    // $ANTLR start "PUBLIC"
    public final void mPUBLIC() throws RecognitionException {
        try {
            int _type = PUBLIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1174:8: ( 'public' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1174:10: 'public'
            {
            match("public"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PUBLIC"

    // $ANTLR start "PRIVATE"
    public final void mPRIVATE() throws RecognitionException {
        try {
            int _type = PRIVATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1175:9: ( 'private' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1175:11: 'private'
            {
            match("private"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PRIVATE"

    // $ANTLR start "ALERT"
    public final void mALERT() throws RecognitionException {
        try {
            int _type = ALERT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1176:7: ( 'alert' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1176:9: 'alert'
            {
            match("alert"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ALERT"

    // $ANTLR start "DETECT"
    public final void mDETECT() throws RecognitionException {
        try {
            int _type = DETECT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1177:8: ( 'detect' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1177:10: 'detect'
            {
            match("detect"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DETECT"

    // $ANTLR start "ALWAYS"
    public final void mALWAYS() throws RecognitionException {
        try {
            int _type = ALWAYS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1178:8: ( 'always' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1178:10: 'always'
            {
            match("always"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ALWAYS"

    // $ANTLR start "CHECK"
    public final void mCHECK() throws RecognitionException {
        try {
            int _type = CHECK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1179:7: ( 'check' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1179:9: 'check'
            {
            match("check"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHECK"

    // $ANTLR start "PARENT"
    public final void mPARENT() throws RecognitionException {
        try {
            int _type = PARENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1180:8: ( 'parent' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1180:10: 'parent'
            {
            match("parent"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PARENT"

    // $ANTLR start "BLUEPRINT"
    public final void mBLUEPRINT() throws RecognitionException {
        try {
            int _type = BLUEPRINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1181:11: ( 'blueprint' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1181:13: 'blueprint'
            {
            match("blueprint"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BLUEPRINT"

    // $ANTLR start "NATIVE"
    public final void mNATIVE() throws RecognitionException {
        try {
            int _type = NATIVE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1182:8: ( 'system' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1182:10: 'system'
            {
            match("system"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NATIVE"

    // $ANTLR start "INHERITS"
    public final void mINHERITS() throws RecognitionException {
        try {
            int _type = INHERITS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1183:10: ( 'is a' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1183:12: 'is a'
            {
            match("is a"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INHERITS"

    // $ANTLR start "CAST"
    public final void mCAST() throws RecognitionException {
        try {
            int _type = CAST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1184:6: ( 'cast' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1184:8: 'cast'
            {
            match("cast"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CAST"

    // $ANTLR start "PRINT"
    public final void mPRINT() throws RecognitionException {
        try {
            int _type = PRINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1185:7: ( 'print' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1185:9: 'print'
            {
            match("print"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PRINT"

    // $ANTLR start "INPUT"
    public final void mINPUT() throws RecognitionException {
        try {
            int _type = INPUT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1186:7: ( 'input' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1186:9: 'input'
            {
            match("input"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INPUT"

    // $ANTLR start "SAY"
    public final void mSAY() throws RecognitionException {
        try {
            int _type = SAY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1187:5: ( 'say' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1187:7: 'say'
            {
            match("say"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SAY"

    // $ANTLR start "NOW"
    public final void mNOW() throws RecognitionException {
        try {
            int _type = NOW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1188:5: ( 'now' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1188:7: 'now'
            {
            match("now"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOW"

    // $ANTLR start "WHILE"
    public final void mWHILE() throws RecognitionException {
        try {
            int _type = WHILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1189:7: ( 'while' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1189:9: 'while'
            {
            match("while"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHILE"

    // $ANTLR start "PACKAGE_NAME"
    public final void mPACKAGE_NAME() throws RecognitionException {
        try {
            int _type = PACKAGE_NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1190:14: ( 'package' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1190:16: 'package'
            {
            match("package"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PACKAGE_NAME"

    // $ANTLR start "FROM"
    public final void mFROM() throws RecognitionException {
        try {
            int _type = FROM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1191:6: ( 'from' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1191:8: 'from'
            {
            match("from"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FROM"

    // $ANTLR start "TIMES"
    public final void mTIMES() throws RecognitionException {
        try {
            int _type = TIMES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1192:7: ( 'times' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1192:9: 'times'
            {
            match("times"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TIMES"

    // $ANTLR start "REPEAT"
    public final void mREPEAT() throws RecognitionException {
        try {
            int _type = REPEAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1193:8: ( 'repeat' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1193:10: 'repeat'
            {
            match("repeat"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "REPEAT"

    // $ANTLR start "OVER"
    public final void mOVER() throws RecognitionException {
        try {
            int _type = OVER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1194:6: ( 'over' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1194:8: 'over'
            {
            match("over"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OVER"

    // $ANTLR start "THEN"
    public final void mTHEN() throws RecognitionException {
        try {
            int _type = THEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1195:7: ( 'then' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1195:9: 'then'
            {
            match("then"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "THEN"

    // $ANTLR start "ELSE"
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1196:7: ( 'else' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1196:9: 'else'
            {
            match("else"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ELSE"

    // $ANTLR start "RETURNS"
    public final void mRETURNS() throws RecognitionException {
        try {
            int _type = RETURNS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1197:9: ( 'returns' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1197:11: 'returns'
            {
            match("returns"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RETURNS"

    // $ANTLR start "RETURN"
    public final void mRETURN() throws RecognitionException {
        try {
            int _type = RETURN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1198:9: ( 'return' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1198:11: 'return'
            {
            match("return"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RETURN"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1199:5: ( 'and' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1199:7: 'and'
            {
            match("and"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1200:5: ( 'or' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1200:7: 'or'
            {
            match("or"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "TO"
    public final void mTO() throws RecognitionException {
        try {
            int _type = TO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1201:4: ( 'to' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1201:6: 'to'
            {
            match("to"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TO"

    // $ANTLR start "NULL"
    public final void mNULL() throws RecognitionException {
        try {
            int _type = NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1202:6: ( 'undefined' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1202:8: 'undefined'
            {
            match("undefined"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NULL"

    // $ANTLR start "ACTION"
    public final void mACTION() throws RecognitionException {
        try {
            int _type = ACTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1204:2: ( 'action' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1204:4: 'action'
            {
            match("action"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ACTION"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1206:7: ( ':' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1206:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "INTEGER_KEYWORD"
    public final void mINTEGER_KEYWORD() throws RecognitionException {
        try {
            int _type = INTEGER_KEYWORD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1209:2: ( 'integer' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1209:4: 'integer'
            {
            match("integer"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTEGER_KEYWORD"

    // $ANTLR start "NUMBER_KEYWORD"
    public final void mNUMBER_KEYWORD() throws RecognitionException {
        try {
            int _type = NUMBER_KEYWORD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1211:2: ( 'number' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1211:4: 'number'
            {
            match("number"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NUMBER_KEYWORD"

    // $ANTLR start "TEXT"
    public final void mTEXT() throws RecognitionException {
        try {
            int _type = TEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1213:2: ( 'text' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1213:4: 'text'
            {
            match("text"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TEXT"

    // $ANTLR start "BOOLEAN_KEYWORD"
    public final void mBOOLEAN_KEYWORD() throws RecognitionException {
        try {
            int _type = BOOLEAN_KEYWORD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1215:2: ( 'boolean' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1215:4: 'boolean'
            {
            match("boolean"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BOOLEAN_KEYWORD"

    // $ANTLR start "USE"
    public final void mUSE() throws RecognitionException {
        try {
            int _type = USE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1216:6: ( 'use' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1216:8: 'use'
            {
            match("use"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "USE"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1218:5: ( 'not' | 'Not' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='n') ) {
                alt1=1;
            }
            else if ( (LA1_0=='N') ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1218:7: 'not'
                    {
                    match("not"); 


                    }
                    break;
                case 2 :
                    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1218:15: 'Not'
                    {
                    match("Not"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "NOTEQUALS"
    public final void mNOTEQUALS() throws RecognitionException {
        try {
            int _type = NOTEQUALS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1220:2: ( ( 'n' | 'N' ) 'ot=' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1220:4: ( 'n' | 'N' ) 'ot='
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match("ot="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOTEQUALS"

    // $ANTLR start "PERIOD"
    public final void mPERIOD() throws RecognitionException {
        try {
            int _type = PERIOD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1221:8: ( '.' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1221:10: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PERIOD"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1222:7: ( ',' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1222:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "EQUALITY"
    public final void mEQUALITY() throws RecognitionException {
        try {
            int _type = EQUALITY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1223:9: ( '=' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1223:11: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQUALITY"

    // $ANTLR start "GREATER"
    public final void mGREATER() throws RecognitionException {
        try {
            int _type = GREATER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1224:9: ( '>' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1224:11: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GREATER"

    // $ANTLR start "GREATER_EQUAL"
    public final void mGREATER_EQUAL() throws RecognitionException {
        try {
            int _type = GREATER_EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1226:2: ( '>=' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1226:4: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GREATER_EQUAL"

    // $ANTLR start "LESS"
    public final void mLESS() throws RecognitionException {
        try {
            int _type = LESS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1227:6: ( '<' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1227:8: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LESS"

    // $ANTLR start "LESS_EQUAL"
    public final void mLESS_EQUAL() throws RecognitionException {
        try {
            int _type = LESS_EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1229:2: ( '<=' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1229:4: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LESS_EQUAL"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1230:6: ( '+' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1230:8: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1231:7: ( '-' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1231:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "MULTIPLY"
    public final void mMULTIPLY() throws RecognitionException {
        try {
            int _type = MULTIPLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1232:9: ( '*' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1232:11: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MULTIPLY"

    // $ANTLR start "DIVIDE"
    public final void mDIVIDE() throws RecognitionException {
        try {
            int _type = DIVIDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1233:8: ( '/' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1233:10: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIVIDE"

    // $ANTLR start "MODULO"
    public final void mMODULO() throws RecognitionException {
        try {
            int _type = MODULO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1234:8: ( 'mod' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1234:10: 'mod'
            {
            match("mod"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MODULO"

    // $ANTLR start "LEFT_SQR_BRACE"
    public final void mLEFT_SQR_BRACE() throws RecognitionException {
        try {
            int _type = LEFT_SQR_BRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1236:2: ( '[' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1236:4: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LEFT_SQR_BRACE"

    // $ANTLR start "RIGHT_SQR_BRACE"
    public final void mRIGHT_SQR_BRACE() throws RecognitionException {
        try {
            int _type = RIGHT_SQR_BRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1238:2: ( ']' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1238:4: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RIGHT_SQR_BRACE"

    // $ANTLR start "LEFT_PAREN"
    public final void mLEFT_PAREN() throws RecognitionException {
        try {
            int _type = LEFT_PAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1240:2: ( '(' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1240:4: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LEFT_PAREN"

    // $ANTLR start "RIGHT_PAREN"
    public final void mRIGHT_PAREN() throws RecognitionException {
        try {
            int _type = RIGHT_PAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1242:2: ( ')' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1242:4: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RIGHT_PAREN"

    // $ANTLR start "DOUBLE_QUOTE"
    public final void mDOUBLE_QUOTE() throws RecognitionException {
        try {
            int _type = DOUBLE_QUOTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1244:2: ( '\"' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1244:4: '\"'
            {
            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOUBLE_QUOTE"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1245:4: ( 'if' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1245:6: 'if'
            {
            match("if"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "END"
    public final void mEND() throws RecognitionException {
        try {
            int _type = END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1246:5: ( 'end' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1246:7: 'end'
            {
            match("end"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "END"

    // $ANTLR start "CLASS"
    public final void mCLASS() throws RecognitionException {
        try {
            int _type = CLASS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1247:7: ( 'class' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1247:9: 'class'
            {
            match("class"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CLASS"

    // $ANTLR start "BOOLEAN"
    public final void mBOOLEAN() throws RecognitionException {
        try {
            int _type = BOOLEAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1248:9: ( 'true' | 'false' )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='t') ) {
                alt2=1;
            }
            else if ( (LA2_0=='f') ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1248:11: 'true'
                    {
                    match("true"); 


                    }
                    break;
                case 2 :
                    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1248:20: 'false'
                    {
                    match("false"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BOOLEAN"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1249:6: ( ( '0' .. '9' )+ )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1249:8: ( '0' .. '9' )+
            {
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1249:8: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1249:8: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "DECIMAL"
    public final void mDECIMAL() throws RecognitionException {
        try {
            int _type = DECIMAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1250:9: ( ( '0' .. '9' )+ ( PERIOD ( '0' .. '9' )* )? )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1250:11: ( '0' .. '9' )+ ( PERIOD ( '0' .. '9' )* )?
            {
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1250:11: ( '0' .. '9' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1250:11: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1250:21: ( PERIOD ( '0' .. '9' )* )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='.') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1250:22: PERIOD ( '0' .. '9' )*
                    {
                    mPERIOD(); 
                    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1250:29: ( '0' .. '9' )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1250:30: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DECIMAL"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1251:5: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1251:8: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1251:27: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='0' && LA7_0<='9')||(LA7_0>='A' && LA7_0<='Z')||LA7_0=='_'||(LA7_0>='a' && LA7_0<='z')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1252:8: ( DOUBLE_QUOTE (~ ( DOUBLE_QUOTE ) )* DOUBLE_QUOTE )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1252:10: DOUBLE_QUOTE (~ ( DOUBLE_QUOTE ) )* DOUBLE_QUOTE
            {
            mDOUBLE_QUOTE(); 
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1252:23: (~ ( DOUBLE_QUOTE ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\u0000' && LA8_0<='!')||(LA8_0>='#' && LA8_0<='\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1252:23: ~ ( DOUBLE_QUOTE )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            mDOUBLE_QUOTE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1255:9: ( ( '\\r' )? '\\n' )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1255:12: ( '\\r' )? '\\n'
            {
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1255:12: ( '\\r' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0=='\r') ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1255:12: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 
            _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1256:4: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1256:6: ( ' ' | '\\t' | '\\n' | '\\r' )+
            {
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1256:6: ( ' ' | '\\t' | '\\n' | '\\r' )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='\t' && LA10_0<='\n')||LA10_0=='\r'||LA10_0==' ') ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
            } while (true);

            _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "COMMENTS"
    public final void mCOMMENTS() throws RecognitionException {
        try {
            int _type = COMMENTS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1259:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( ( ( '\\r' )? '\\n' ) | EOF ) | '/*' ( options {greedy=false; } : . )* '*/' )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0=='/') ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1=='/') ) {
                    alt15=1;
                }
                else if ( (LA15_1=='*') ) {
                    alt15=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1259:9: '//' (~ ( '\\n' | '\\r' ) )* ( ( ( '\\r' )? '\\n' ) | EOF )
                    {
                    match("//"); 

                    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1259:14: (~ ( '\\n' | '\\r' ) )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( ((LA11_0>='\u0000' && LA11_0<='\t')||(LA11_0>='\u000B' && LA11_0<='\f')||(LA11_0>='\u000E' && LA11_0<='\uFFFF')) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1259:14: ~ ( '\\n' | '\\r' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);

                    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1259:28: ( ( ( '\\r' )? '\\n' ) | EOF )
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0=='\n'||LA13_0=='\r') ) {
                        alt13=1;
                    }
                    else {
                        alt13=2;}
                    switch (alt13) {
                        case 1 :
                            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1259:29: ( ( '\\r' )? '\\n' )
                            {
                            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1259:29: ( ( '\\r' )? '\\n' )
                            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1259:30: ( '\\r' )? '\\n'
                            {
                            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1259:30: ( '\\r' )?
                            int alt12=2;
                            int LA12_0 = input.LA(1);

                            if ( (LA12_0=='\r') ) {
                                alt12=1;
                            }
                            switch (alt12) {
                                case 1 :
                                    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1259:30: '\\r'
                                    {
                                    match('\r'); 

                                    }
                                    break;

                            }

                            match('\n'); 

                            }


                            }
                            break;
                        case 2 :
                            // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1259:44: EOF
                            {
                            match(EOF); 

                            }
                            break;

                    }

                    _channel=HIDDEN;

                    }
                    break;
                case 2 :
                    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1260:9: '/*' ( options {greedy=false; } : . )* '*/'
                    {
                    match("/*"); 

                    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1260:14: ( options {greedy=false; } : . )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0=='*') ) {
                            int LA14_1 = input.LA(2);

                            if ( (LA14_1=='/') ) {
                                alt14=2;
                            }
                            else if ( ((LA14_1>='\u0000' && LA14_1<='.')||(LA14_1>='0' && LA14_1<='\uFFFF')) ) {
                                alt14=1;
                            }


                        }
                        else if ( ((LA14_0>='\u0000' && LA14_0<=')')||(LA14_0>='+' && LA14_0<='\uFFFF')) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1260:42: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);

                    match("*/"); 

                    _channel=HIDDEN_DOCUMENTATION;

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENTS"

    public void mTokens() throws RecognitionException {
        // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:8: ( ME | UNTIL | ON_DESTROY | ON_CREATE | OF_TYPE | PUBLIC | PRIVATE | ALERT | DETECT | ALWAYS | CHECK | PARENT | BLUEPRINT | NATIVE | INHERITS | CAST | PRINT | INPUT | SAY | NOW | WHILE | PACKAGE_NAME | FROM | TIMES | REPEAT | OVER | THEN | ELSE | RETURNS | RETURN | AND | OR | TO | NULL | ACTION | COLON | INTEGER_KEYWORD | NUMBER_KEYWORD | TEXT | BOOLEAN_KEYWORD | USE | NOT | NOTEQUALS | PERIOD | COMMA | EQUALITY | GREATER | GREATER_EQUAL | LESS | LESS_EQUAL | PLUS | MINUS | MULTIPLY | DIVIDE | MODULO | LEFT_SQR_BRACE | RIGHT_SQR_BRACE | LEFT_PAREN | RIGHT_PAREN | DOUBLE_QUOTE | IF | END | CLASS | BOOLEAN | INT | DECIMAL | ID | STRING | NEWLINE | WS | COMMENTS )
        int alt16=71;
        alt16 = dfa16.predict(input);
        switch (alt16) {
            case 1 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:10: ME
                {
                mME(); 

                }
                break;
            case 2 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:13: UNTIL
                {
                mUNTIL(); 

                }
                break;
            case 3 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:19: ON_DESTROY
                {
                mON_DESTROY(); 

                }
                break;
            case 4 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:30: ON_CREATE
                {
                mON_CREATE(); 

                }
                break;
            case 5 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:40: OF_TYPE
                {
                mOF_TYPE(); 

                }
                break;
            case 6 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:48: PUBLIC
                {
                mPUBLIC(); 

                }
                break;
            case 7 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:55: PRIVATE
                {
                mPRIVATE(); 

                }
                break;
            case 8 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:63: ALERT
                {
                mALERT(); 

                }
                break;
            case 9 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:69: DETECT
                {
                mDETECT(); 

                }
                break;
            case 10 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:76: ALWAYS
                {
                mALWAYS(); 

                }
                break;
            case 11 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:83: CHECK
                {
                mCHECK(); 

                }
                break;
            case 12 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:89: PARENT
                {
                mPARENT(); 

                }
                break;
            case 13 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:96: BLUEPRINT
                {
                mBLUEPRINT(); 

                }
                break;
            case 14 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:106: NATIVE
                {
                mNATIVE(); 

                }
                break;
            case 15 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:113: INHERITS
                {
                mINHERITS(); 

                }
                break;
            case 16 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:122: CAST
                {
                mCAST(); 

                }
                break;
            case 17 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:127: PRINT
                {
                mPRINT(); 

                }
                break;
            case 18 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:133: INPUT
                {
                mINPUT(); 

                }
                break;
            case 19 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:139: SAY
                {
                mSAY(); 

                }
                break;
            case 20 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:143: NOW
                {
                mNOW(); 

                }
                break;
            case 21 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:147: WHILE
                {
                mWHILE(); 

                }
                break;
            case 22 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:153: PACKAGE_NAME
                {
                mPACKAGE_NAME(); 

                }
                break;
            case 23 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:166: FROM
                {
                mFROM(); 

                }
                break;
            case 24 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:171: TIMES
                {
                mTIMES(); 

                }
                break;
            case 25 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:177: REPEAT
                {
                mREPEAT(); 

                }
                break;
            case 26 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:184: OVER
                {
                mOVER(); 

                }
                break;
            case 27 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:189: THEN
                {
                mTHEN(); 

                }
                break;
            case 28 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:194: ELSE
                {
                mELSE(); 

                }
                break;
            case 29 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:199: RETURNS
                {
                mRETURNS(); 

                }
                break;
            case 30 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:207: RETURN
                {
                mRETURN(); 

                }
                break;
            case 31 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:214: AND
                {
                mAND(); 

                }
                break;
            case 32 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:218: OR
                {
                mOR(); 

                }
                break;
            case 33 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:221: TO
                {
                mTO(); 

                }
                break;
            case 34 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:224: NULL
                {
                mNULL(); 

                }
                break;
            case 35 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:229: ACTION
                {
                mACTION(); 

                }
                break;
            case 36 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:236: COLON
                {
                mCOLON(); 

                }
                break;
            case 37 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:242: INTEGER_KEYWORD
                {
                mINTEGER_KEYWORD(); 

                }
                break;
            case 38 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:258: NUMBER_KEYWORD
                {
                mNUMBER_KEYWORD(); 

                }
                break;
            case 39 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:273: TEXT
                {
                mTEXT(); 

                }
                break;
            case 40 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:278: BOOLEAN_KEYWORD
                {
                mBOOLEAN_KEYWORD(); 

                }
                break;
            case 41 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:294: USE
                {
                mUSE(); 

                }
                break;
            case 42 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:298: NOT
                {
                mNOT(); 

                }
                break;
            case 43 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:302: NOTEQUALS
                {
                mNOTEQUALS(); 

                }
                break;
            case 44 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:312: PERIOD
                {
                mPERIOD(); 

                }
                break;
            case 45 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:319: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 46 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:325: EQUALITY
                {
                mEQUALITY(); 

                }
                break;
            case 47 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:334: GREATER
                {
                mGREATER(); 

                }
                break;
            case 48 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:342: GREATER_EQUAL
                {
                mGREATER_EQUAL(); 

                }
                break;
            case 49 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:356: LESS
                {
                mLESS(); 

                }
                break;
            case 50 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:361: LESS_EQUAL
                {
                mLESS_EQUAL(); 

                }
                break;
            case 51 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:372: PLUS
                {
                mPLUS(); 

                }
                break;
            case 52 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:377: MINUS
                {
                mMINUS(); 

                }
                break;
            case 53 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:383: MULTIPLY
                {
                mMULTIPLY(); 

                }
                break;
            case 54 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:392: DIVIDE
                {
                mDIVIDE(); 

                }
                break;
            case 55 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:399: MODULO
                {
                mMODULO(); 

                }
                break;
            case 56 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:406: LEFT_SQR_BRACE
                {
                mLEFT_SQR_BRACE(); 

                }
                break;
            case 57 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:421: RIGHT_SQR_BRACE
                {
                mRIGHT_SQR_BRACE(); 

                }
                break;
            case 58 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:437: LEFT_PAREN
                {
                mLEFT_PAREN(); 

                }
                break;
            case 59 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:448: RIGHT_PAREN
                {
                mRIGHT_PAREN(); 

                }
                break;
            case 60 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:460: DOUBLE_QUOTE
                {
                mDOUBLE_QUOTE(); 

                }
                break;
            case 61 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:473: IF
                {
                mIF(); 

                }
                break;
            case 62 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:476: END
                {
                mEND(); 

                }
                break;
            case 63 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:480: CLASS
                {
                mCLASS(); 

                }
                break;
            case 64 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:486: BOOLEAN
                {
                mBOOLEAN(); 

                }
                break;
            case 65 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:494: INT
                {
                mINT(); 

                }
                break;
            case 66 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:498: DECIMAL
                {
                mDECIMAL(); 

                }
                break;
            case 67 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:506: ID
                {
                mID(); 

                }
                break;
            case 68 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:509: STRING
                {
                mSTRING(); 

                }
                break;
            case 69 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:516: NEWLINE
                {
                mNEWLINE(); 

                }
                break;
            case 70 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:524: WS
                {
                mWS(); 

                }
                break;
            case 71 :
                // /Users/stefika/Quorum/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:527: COMMENTS
                {
                mCOMMENTS(); 

                }
                break;

        }

    }


    protected DFA16 dfa16 = new DFA16(this);
    static final String DFA16_eotS =
        "\1\uffff\20\42\1\uffff\1\42\3\uffff\1\116\1\120\3\uffff\1\122\4"+
        "\uffff\1\123\1\125\1\uffff\1\45\1\127\1\uffff\1\130\6\42\1\140\20"+
        "\42\1\164\7\42\1\175\6\42\14\uffff\1\u0085\2\42\1\u0088\2\uffff"+
        "\1\42\1\uffff\6\42\1\u0093\10\42\1\u009c\1\uffff\2\42\1\uffff\1"+
        "\u009f\1\u00a1\6\42\1\uffff\5\42\1\u00ad\1\u00a1\1\uffff\2\42\3"+
        "\uffff\1\u00b0\7\42\1\uffff\3\42\1\u00bb\4\42\1\uffff\2\42\3\uffff"+
        "\2\42\1\u00c4\2\42\1\u00c7\1\u00c8\1\u00c9\2\42\1\u00cc\1\uffff"+
        "\1\u00cd\1\42\1\uffff\2\42\1\u00d1\2\42\1\u00d4\3\42\1\u00d8\1\uffff"+
        "\1\u00d9\3\42\1\u00dd\2\42\1\u00e0\1\uffff\1\u00c9\1\u00e1\3\uffff"+
        "\2\42\2\uffff\1\42\1\u00e5\1\42\1\uffff\1\u00e7\1\42\1\uffff\1\u00e9"+
        "\1\u00ea\1\u00eb\2\uffff\2\42\1\u00ee\1\uffff\1\42\1\u00f0\2\uffff"+
        "\1\u00f1\1\u00f3\1\42\1\uffff\1\u00f5\1\uffff\1\u00f6\3\uffff\1"+
        "\42\1\u00f8\1\uffff\1\u00f9\2\uffff\1\u00fa\1\uffff\1\42\2\uffff"+
        "\1\42\3\uffff\1\u00fd\1\u00fe\2\uffff";
    static final String DFA16_eofS =
        "\u00ff\uffff";
    static final String DFA16_minS =
        "\1\11\1\145\1\156\1\146\1\141\1\143\1\145\1\141\1\154\1\141\1\146"+
        "\1\157\1\150\1\141\2\145\1\154\1\uffff\1\157\3\uffff\2\75\3\uffff"+
        "\1\52\4\uffff\1\0\1\56\1\uffff\1\12\1\11\1\uffff\1\60\2\144\1\145"+
        "\2\40\1\145\1\60\1\142\1\151\1\143\1\145\1\144\2\164\1\145\1\163"+
        "\1\141\1\165\1\157\1\163\1\171\1\40\1\160\1\60\1\164\1\155\1\151"+
        "\1\157\1\154\1\155\1\145\1\60\1\170\1\165\1\160\1\163\1\144\1\164"+
        "\14\uffff\1\60\1\151\1\145\1\60\1\143\1\uffff\1\162\1\uffff\1\154"+
        "\1\156\1\145\1\153\1\162\1\141\1\60\1\151\1\145\1\143\1\164\1\163"+
        "\1\145\1\154\1\164\1\60\1\uffff\1\165\1\145\1\uffff\2\60\1\142\1"+
        "\154\1\155\1\163\1\145\1\156\1\uffff\1\164\2\145\1\165\1\145\2\60"+
        "\1\uffff\1\154\1\146\3\uffff\1\60\1\151\1\141\1\164\1\156\1\141"+
        "\1\164\1\171\1\uffff\1\157\1\143\1\153\1\60\1\163\1\160\2\145\1"+
        "\uffff\1\164\1\147\3\uffff\2\145\1\60\1\145\1\163\3\60\1\141\1\162"+
        "\1\60\1\uffff\1\60\1\151\1\uffff\1\143\1\164\1\60\1\164\1\147\1"+
        "\60\1\163\1\156\1\164\1\60\1\uffff\1\60\1\162\1\141\1\155\1\60\1"+
        "\145\1\162\1\60\1\uffff\2\60\3\uffff\1\164\1\156\2\uffff\1\156\1"+
        "\60\1\145\1\uffff\1\60\1\145\1\uffff\3\60\2\uffff\1\151\1\156\1"+
        "\60\1\uffff\1\162\1\60\2\uffff\2\60\1\145\1\uffff\1\60\1\uffff\1"+
        "\60\3\uffff\1\156\1\60\1\uffff\1\60\2\uffff\1\60\1\uffff\1\144\2"+
        "\uffff\1\164\3\uffff\2\60\2\uffff";
    static final String DFA16_maxS =
        "\1\172\1\157\1\163\1\166\1\165\1\156\1\145\1\154\1\157\1\171\1\163"+
        "\1\165\1\150\2\162\1\145\1\156\1\uffff\1\157\3\uffff\2\75\3\uffff"+
        "\1\57\4\uffff\1\uffff\1\71\1\uffff\1\12\1\40\1\uffff\1\172\1\144"+
        "\1\164\1\145\2\40\1\145\1\172\1\142\1\151\1\162\1\167\1\144\2\164"+
        "\1\145\1\163\1\141\1\165\1\157\1\163\1\171\1\40\1\164\1\172\1\167"+
        "\1\155\1\151\1\157\1\154\1\155\1\145\1\172\1\170\1\165\1\164\1\163"+
        "\1\144\1\164\14\uffff\1\172\1\151\1\145\1\172\1\144\1\uffff\1\162"+
        "\1\uffff\1\154\1\166\1\145\1\153\1\162\1\141\1\172\1\151\1\145\1"+
        "\143\1\164\1\163\1\145\1\154\1\164\1\172\1\uffff\1\165\1\145\1\uffff"+
        "\2\172\1\142\1\154\1\155\1\163\1\145\1\156\1\uffff\1\164\2\145\1"+
        "\165\1\145\2\172\1\uffff\1\154\1\146\3\uffff\1\172\1\151\1\141\1"+
        "\164\1\156\1\141\1\164\1\171\1\uffff\1\157\1\143\1\153\1\172\1\163"+
        "\1\160\2\145\1\uffff\1\164\1\147\3\uffff\2\145\1\172\1\145\1\163"+
        "\3\172\1\141\1\162\1\172\1\uffff\1\172\1\151\1\uffff\1\143\1\164"+
        "\1\172\1\164\1\147\1\172\1\163\1\156\1\164\1\172\1\uffff\1\172\1"+
        "\162\1\141\1\155\1\172\1\145\1\162\1\172\1\uffff\2\172\3\uffff\1"+
        "\164\1\156\2\uffff\1\156\1\172\1\145\1\uffff\1\172\1\145\1\uffff"+
        "\3\172\2\uffff\1\151\1\156\1\172\1\uffff\1\162\1\172\2\uffff\2\172"+
        "\1\145\1\uffff\1\172\1\uffff\1\172\3\uffff\1\156\1\172\1\uffff\1"+
        "\172\2\uffff\1\172\1\uffff\1\144\2\uffff\1\164\3\uffff\2\172\2\uffff";
    static final String DFA16_acceptS =
        "\21\uffff\1\44\1\uffff\1\54\1\55\1\56\2\uffff\1\63\1\64\1\65\1\uffff"+
        "\1\70\1\71\1\72\1\73\2\uffff\1\103\2\uffff\1\106\47\uffff\1\60\1"+
        "\57\1\62\1\61\1\107\1\66\1\74\1\104\1\101\1\102\1\105\1\1\5\uffff"+
        "\1\5\1\uffff\1\40\20\uffff\1\17\2\uffff\1\75\10\uffff\1\41\7\uffff"+
        "\1\67\2\uffff\1\51\1\3\1\4\10\uffff\1\37\10\uffff\1\23\2\uffff\1"+
        "\24\1\53\1\52\13\uffff\1\76\2\uffff\1\32\12\uffff\1\20\10\uffff"+
        "\1\27\2\uffff\1\33\1\47\1\100\2\uffff\1\34\1\2\3\uffff\1\21\2\uffff"+
        "\1\10\3\uffff\1\13\1\77\3\uffff\1\22\2\uffff\1\25\1\30\3\uffff\1"+
        "\6\1\uffff\1\14\1\uffff\1\12\1\43\1\11\2\uffff\1\16\1\uffff\1\46"+
        "\1\31\1\uffff\1\36\1\uffff\1\7\1\26\1\uffff\1\50\1\45\1\35\2\uffff"+
        "\1\42\1\15";
    static final String DFA16_specialS =
        "\40\uffff\1\0\u00de\uffff}>";
    static final String[] DFA16_transitionS = {
            "\1\45\1\44\2\uffff\1\43\22\uffff\1\45\1\uffff\1\40\5\uffff\1"+
            "\36\1\37\1\32\1\30\1\24\1\31\1\23\1\33\12\41\1\21\1\uffff\1"+
            "\27\1\25\1\26\2\uffff\15\42\1\22\14\42\1\34\1\uffff\1\35\3\uffff"+
            "\1\5\1\10\1\7\1\6\1\20\1\15\2\42\1\12\3\42\1\1\1\13\1\3\1\4"+
            "\1\42\1\17\1\11\1\16\1\2\1\42\1\14\3\42",
            "\1\46\11\uffff\1\47",
            "\1\50\4\uffff\1\51",
            "\1\53\7\uffff\1\52\3\uffff\1\55\3\uffff\1\54",
            "\1\60\20\uffff\1\57\2\uffff\1\56",
            "\1\63\10\uffff\1\61\1\uffff\1\62",
            "\1\64",
            "\1\66\6\uffff\1\65\3\uffff\1\67",
            "\1\70\2\uffff\1\71",
            "\1\73\27\uffff\1\72",
            "\1\76\7\uffff\1\75\4\uffff\1\74",
            "\1\77\5\uffff\1\100",
            "\1\101",
            "\1\103\20\uffff\1\102",
            "\1\107\2\uffff\1\105\1\104\5\uffff\1\106\2\uffff\1\110",
            "\1\111",
            "\1\112\1\uffff\1\113",
            "",
            "\1\114",
            "",
            "",
            "",
            "\1\115",
            "\1\117",
            "",
            "",
            "",
            "\1\121\4\uffff\1\121",
            "",
            "",
            "",
            "",
            "\0\124",
            "\1\126\1\uffff\12\41",
            "",
            "\1\44",
            "\2\45\2\uffff\1\45\22\uffff\1\45",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\131",
            "\1\133\17\uffff\1\132",
            "\1\134",
            "\1\135",
            "\1\136",
            "\1\137",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\141",
            "\1\142",
            "\1\144\16\uffff\1\143",
            "\1\145\21\uffff\1\146",
            "\1\147",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\154",
            "\1\155",
            "\1\156",
            "\1\157",
            "\1\160",
            "\1\161",
            "\1\162\3\uffff\1\163",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\166\2\uffff\1\165",
            "\1\167",
            "\1\170",
            "\1\171",
            "\1\172",
            "\1\173",
            "\1\174",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\176",
            "\1\177",
            "\1\u0080\3\uffff\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0086",
            "\1\u0087",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u008a\1\u0089",
            "",
            "\1\u008b",
            "",
            "\1\u008c",
            "\1\u008e\7\uffff\1\u008d",
            "\1\u008f",
            "\1\u0090",
            "\1\u0091",
            "\1\u0092",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "\1\u0099",
            "\1\u009a",
            "\1\u009b",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\1\u009d",
            "\1\u009e",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\12\42\3\uffff\1\u00a0\3\uffff\32\42\4\uffff\1\42\1\uffff\32"+
            "\42",
            "\1\u00a2",
            "\1\u00a3",
            "\1\u00a4",
            "\1\u00a5",
            "\1\u00a6",
            "\1\u00a7",
            "",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\12\42\3\uffff\1\u00a0\3\uffff\32\42\4\uffff\1\42\1\uffff\32"+
            "\42",
            "",
            "\1\u00ae",
            "\1\u00af",
            "",
            "",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00b1",
            "\1\u00b2",
            "\1\u00b3",
            "\1\u00b4",
            "\1\u00b5",
            "\1\u00b6",
            "\1\u00b7",
            "",
            "\1\u00b8",
            "\1\u00b9",
            "\1\u00ba",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "",
            "\1\u00c0",
            "\1\u00c1",
            "",
            "",
            "",
            "\1\u00c2",
            "\1\u00c3",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00c5",
            "\1\u00c6",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00ca",
            "\1\u00cb",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00ce",
            "",
            "\1\u00cf",
            "\1\u00d0",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00d2",
            "\1\u00d3",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00d5",
            "\1\u00d6",
            "\1\u00d7",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00da",
            "\1\u00db",
            "\1\u00dc",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00de",
            "\1\u00df",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "",
            "",
            "\1\u00e2",
            "\1\u00e3",
            "",
            "",
            "\1\u00e4",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00e6",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00e8",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "",
            "\1\u00ec",
            "\1\u00ed",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\1\u00ef",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\22\42\1\u00f2\7\42",
            "\1\u00f4",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "",
            "",
            "\1\u00f7",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\1\u00fb",
            "",
            "",
            "\1\u00fc",
            "",
            "",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            ""
    };

    static final short[] DFA16_eot = DFA.unpackEncodedString(DFA16_eotS);
    static final short[] DFA16_eof = DFA.unpackEncodedString(DFA16_eofS);
    static final char[] DFA16_min = DFA.unpackEncodedStringToUnsignedChars(DFA16_minS);
    static final char[] DFA16_max = DFA.unpackEncodedStringToUnsignedChars(DFA16_maxS);
    static final short[] DFA16_accept = DFA.unpackEncodedString(DFA16_acceptS);
    static final short[] DFA16_special = DFA.unpackEncodedString(DFA16_specialS);
    static final short[][] DFA16_transition;

    static {
        int numStates = DFA16_transitionS.length;
        DFA16_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA16_transition[i] = DFA.unpackEncodedString(DFA16_transitionS[i]);
        }
    }

    class DFA16 extends DFA {

        public DFA16(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 16;
            this.eot = DFA16_eot;
            this.eof = DFA16_eof;
            this.min = DFA16_min;
            this.max = DFA16_max;
            this.accept = DFA16_accept;
            this.special = DFA16_special;
            this.transition = DFA16_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( ME | UNTIL | ON_DESTROY | ON_CREATE | OF_TYPE | PUBLIC | PRIVATE | ALERT | DETECT | ALWAYS | CHECK | PARENT | BLUEPRINT | NATIVE | INHERITS | CAST | PRINT | INPUT | SAY | NOW | WHILE | PACKAGE_NAME | FROM | TIMES | REPEAT | OVER | THEN | ELSE | RETURNS | RETURN | AND | OR | TO | NULL | ACTION | COLON | INTEGER_KEYWORD | NUMBER_KEYWORD | TEXT | BOOLEAN_KEYWORD | USE | NOT | NOTEQUALS | PERIOD | COMMA | EQUALITY | GREATER | GREATER_EQUAL | LESS | LESS_EQUAL | PLUS | MINUS | MULTIPLY | DIVIDE | MODULO | LEFT_SQR_BRACE | RIGHT_SQR_BRACE | LEFT_PAREN | RIGHT_PAREN | DOUBLE_QUOTE | IF | END | CLASS | BOOLEAN | INT | DECIMAL | ID | STRING | NEWLINE | WS | COMMENTS );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA16_32 = input.LA(1);

                        s = -1;
                        if ( ((LA16_32>='\u0000' && LA16_32<='\uFFFF')) ) {s = 84;}

                        else s = 83;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 16, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}