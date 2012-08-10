// $ANTLR 3.4 /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g 2012-08-09 11:23:42
package org.quorum.parser;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import org.quorum.vm.interfaces.CompilerErrorManager;
import org.quorum.vm.interfaces.CompilerError;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class QuorumLexer extends Lexer {
    public static final int EOF=-1;
    public static final int ACTION=4;
    public static final int ALERT=5;
    public static final int ALWAYS=6;
    public static final int AND=7;
    public static final int BLUEPRINT=8;
    public static final int BOOLEAN=9;
    public static final int BOOLEAN_KEYWORD=10;
    public static final int CAST=11;
    public static final int CHECK=12;
    public static final int CLASS=13;
    public static final int COLON=14;
    public static final int COMMA=15;
    public static final int COMMENTS=16;
    public static final int CONSTRUCTOR=17;
    public static final int DECIMAL=18;
    public static final int DETECT=19;
    public static final int DIVIDE=20;
    public static final int DOUBLE_QUOTE=21;
    public static final int ELSE=22;
    public static final int ELSE_IF=23;
    public static final int ELSE_IF_STATEMENT=24;
    public static final int END=25;
    public static final int EQUALITY=26;
    public static final int EXPRESSION_STATEMENT=27;
    public static final int FINAL_ELSE=28;
    public static final int FPARAM=29;
    public static final int FUNCTION_CALL=30;
    public static final int FUNCTION_CALL_PARENT=31;
    public static final int FUNCTION_CALL_THIS=32;
    public static final int FUNCTION_EXPRESSION_LIST=33;
    public static final int GENERIC=34;
    public static final int GREATER=35;
    public static final int GREATER_EQUAL=36;
    public static final int ID=37;
    public static final int IF=38;
    public static final int INHERITS=39;
    public static final int INPUT=40;
    public static final int INT=41;
    public static final int INTEGER_KEYWORD=42;
    public static final int LEFT_PAREN=43;
    public static final int LEFT_SQR_BRACE=44;
    public static final int LESS=45;
    public static final int LESS_EQUAL=46;
    public static final int ME=47;
    public static final int MINUS=48;
    public static final int MODULO=49;
    public static final int MULTIPLY=50;
    public static final int NATIVE=51;
    public static final int NEWLINE=52;
    public static final int NOT=53;
    public static final int NOTEQUALS=54;
    public static final int NOW=55;
    public static final int NULL=56;
    public static final int NUMBER_KEYWORD=57;
    public static final int OF_TYPE=58;
    public static final int ON_CREATE=59;
    public static final int ON_DESTROY=60;
    public static final int OR=61;
    public static final int OVER=62;
    public static final int PACKAGE_NAME=63;
    public static final int PARENT=64;
    public static final int PAREN_WRAPPED_EXPRESSION=65;
    public static final int PERIOD=66;
    public static final int PLUS=67;
    public static final int PRINT=68;
    public static final int PRIVATE=69;
    public static final int PUBLIC=70;
    public static final int QUALIFIED_NAME=71;
    public static final int QUALIFIED_SOLO_EXPRESSION=72;
    public static final int QUALIFIED_SOLO_EXPRESSION_SELECTOR=73;
    public static final int QUALIFIED_SOLO_PARENT_EXPRESSON=74;
    public static final int REPEAT=75;
    public static final int RETURN=76;
    public static final int RETURNS=77;
    public static final int RIGHT_PAREN=78;
    public static final int RIGHT_SQR_BRACE=79;
    public static final int ROOT_EXPRESSION=80;
    public static final int SAY=81;
    public static final int SOLO_FUNCTION_CALL=82;
    public static final int SOLO_FUNCTION_CALL_PARENT=83;
    public static final int SOLO_FUNCTION_CALL_THIS=84;
    public static final int STATEMENT_LIST=85;
    public static final int STRING=86;
    public static final int TEXT=87;
    public static final int TIMES=88;
    public static final int UNARY_NOT=89;
    public static final int UNTIL=90;
    public static final int USE=91;
    public static final int WHILE=92;
    public static final int WS=93;

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
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public QuorumLexer() {} 
    public QuorumLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public QuorumLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "/Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g"; }

    // $ANTLR start "ELSE_IF"
    public final void mELSE_IF() throws RecognitionException {
        try {
            int _type = ELSE_IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1222:9: ( 'elseif' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1222:11: 'elseif'
            {
            match("elseif"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ELSE_IF"

    // $ANTLR start "ME"
    public final void mME() throws RecognitionException {
        try {
            int _type = ME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1223:4: ( 'me' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1223:6: 'me'
            {
            match("me"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ME"

    // $ANTLR start "UNTIL"
    public final void mUNTIL() throws RecognitionException {
        try {
            int _type = UNTIL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1224:7: ( 'until' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1224:9: 'until'
            {
            match("until"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UNTIL"

    // $ANTLR start "ON_DESTROY"
    public final void mON_DESTROY() throws RecognitionException {
        try {
            int _type = ON_DESTROY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1226:2: ( 'on destroy' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1226:4: 'on destroy'
            {
            match("on destroy"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ON_DESTROY"

    // $ANTLR start "ON_CREATE"
    public final void mON_CREATE() throws RecognitionException {
        try {
            int _type = ON_CREATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1228:2: ( 'on create' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1228:4: 'on create'
            {
            match("on create"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ON_CREATE"

    // $ANTLR start "OF_TYPE"
    public final void mOF_TYPE() throws RecognitionException {
        try {
            int _type = OF_TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1229:9: ( 'of type' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1229:11: 'of type'
            {
            match("of type"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OF_TYPE"

    // $ANTLR start "PUBLIC"
    public final void mPUBLIC() throws RecognitionException {
        try {
            int _type = PUBLIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1230:8: ( 'public' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1230:10: 'public'
            {
            match("public"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PUBLIC"

    // $ANTLR start "PRIVATE"
    public final void mPRIVATE() throws RecognitionException {
        try {
            int _type = PRIVATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1231:9: ( 'private' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1231:11: 'private'
            {
            match("private"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PRIVATE"

    // $ANTLR start "ALERT"
    public final void mALERT() throws RecognitionException {
        try {
            int _type = ALERT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1232:7: ( 'alert' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1232:9: 'alert'
            {
            match("alert"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ALERT"

    // $ANTLR start "DETECT"
    public final void mDETECT() throws RecognitionException {
        try {
            int _type = DETECT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1233:8: ( 'detect' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1233:10: 'detect'
            {
            match("detect"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DETECT"

    // $ANTLR start "ALWAYS"
    public final void mALWAYS() throws RecognitionException {
        try {
            int _type = ALWAYS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1234:8: ( 'always' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1234:10: 'always'
            {
            match("always"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ALWAYS"

    // $ANTLR start "CHECK"
    public final void mCHECK() throws RecognitionException {
        try {
            int _type = CHECK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1235:7: ( 'check' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1235:9: 'check'
            {
            match("check"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CHECK"

    // $ANTLR start "PARENT"
    public final void mPARENT() throws RecognitionException {
        try {
            int _type = PARENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1236:8: ( 'parent' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1236:10: 'parent'
            {
            match("parent"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PARENT"

    // $ANTLR start "BLUEPRINT"
    public final void mBLUEPRINT() throws RecognitionException {
        try {
            int _type = BLUEPRINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1237:11: ( 'blueprint' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1237:13: 'blueprint'
            {
            match("blueprint"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BLUEPRINT"

    // $ANTLR start "NATIVE"
    public final void mNATIVE() throws RecognitionException {
        try {
            int _type = NATIVE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1238:8: ( 'system' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1238:10: 'system'
            {
            match("system"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NATIVE"

    // $ANTLR start "INHERITS"
    public final void mINHERITS() throws RecognitionException {
        try {
            int _type = INHERITS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1239:10: ( 'is a' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1239:12: 'is a'
            {
            match("is a"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INHERITS"

    // $ANTLR start "CAST"
    public final void mCAST() throws RecognitionException {
        try {
            int _type = CAST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1240:6: ( 'cast' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1240:8: 'cast'
            {
            match("cast"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CAST"

    // $ANTLR start "PRINT"
    public final void mPRINT() throws RecognitionException {
        try {
            int _type = PRINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1241:7: ( 'print' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1241:9: 'print'
            {
            match("print"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PRINT"

    // $ANTLR start "INPUT"
    public final void mINPUT() throws RecognitionException {
        try {
            int _type = INPUT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1242:7: ( 'input' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1242:9: 'input'
            {
            match("input"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INPUT"

    // $ANTLR start "SAY"
    public final void mSAY() throws RecognitionException {
        try {
            int _type = SAY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1243:5: ( 'say' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1243:7: 'say'
            {
            match("say"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SAY"

    // $ANTLR start "NOW"
    public final void mNOW() throws RecognitionException {
        try {
            int _type = NOW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1244:5: ( 'now' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1244:7: 'now'
            {
            match("now"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NOW"

    // $ANTLR start "WHILE"
    public final void mWHILE() throws RecognitionException {
        try {
            int _type = WHILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1245:7: ( 'while' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1245:9: 'while'
            {
            match("while"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WHILE"

    // $ANTLR start "PACKAGE_NAME"
    public final void mPACKAGE_NAME() throws RecognitionException {
        try {
            int _type = PACKAGE_NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1246:14: ( 'package' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1246:16: 'package'
            {
            match("package"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PACKAGE_NAME"

    // $ANTLR start "TIMES"
    public final void mTIMES() throws RecognitionException {
        try {
            int _type = TIMES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1248:7: ( 'times' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1248:9: 'times'
            {
            match("times"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TIMES"

    // $ANTLR start "REPEAT"
    public final void mREPEAT() throws RecognitionException {
        try {
            int _type = REPEAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1249:8: ( 'repeat' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1249:10: 'repeat'
            {
            match("repeat"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "REPEAT"

    // $ANTLR start "OVER"
    public final void mOVER() throws RecognitionException {
        try {
            int _type = OVER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1250:6: ( 'over' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1250:8: 'over'
            {
            match("over"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OVER"

    // $ANTLR start "ELSE"
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1251:7: ( 'else' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1251:9: 'else'
            {
            match("else"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ELSE"

    // $ANTLR start "RETURNS"
    public final void mRETURNS() throws RecognitionException {
        try {
            int _type = RETURNS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1252:9: ( 'returns' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1252:11: 'returns'
            {
            match("returns"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RETURNS"

    // $ANTLR start "RETURN"
    public final void mRETURN() throws RecognitionException {
        try {
            int _type = RETURN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1253:9: ( 'return' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1253:11: 'return'
            {
            match("return"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RETURN"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1254:5: ( 'and' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1254:7: 'and'
            {
            match("and"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1255:5: ( 'or' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1255:7: 'or'
            {
            match("or"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "NULL"
    public final void mNULL() throws RecognitionException {
        try {
            int _type = NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1257:6: ( 'undefined' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1257:8: 'undefined'
            {
            match("undefined"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NULL"

    // $ANTLR start "ACTION"
    public final void mACTION() throws RecognitionException {
        try {
            int _type = ACTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1259:2: ( 'action' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1259:4: 'action'
            {
            match("action"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ACTION"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1261:7: ( ':' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1261:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "INTEGER_KEYWORD"
    public final void mINTEGER_KEYWORD() throws RecognitionException {
        try {
            int _type = INTEGER_KEYWORD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1264:2: ( 'integer' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1264:4: 'integer'
            {
            match("integer"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INTEGER_KEYWORD"

    // $ANTLR start "NUMBER_KEYWORD"
    public final void mNUMBER_KEYWORD() throws RecognitionException {
        try {
            int _type = NUMBER_KEYWORD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1266:2: ( 'number' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1266:4: 'number'
            {
            match("number"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NUMBER_KEYWORD"

    // $ANTLR start "TEXT"
    public final void mTEXT() throws RecognitionException {
        try {
            int _type = TEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1268:2: ( 'text' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1268:4: 'text'
            {
            match("text"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TEXT"

    // $ANTLR start "BOOLEAN_KEYWORD"
    public final void mBOOLEAN_KEYWORD() throws RecognitionException {
        try {
            int _type = BOOLEAN_KEYWORD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1270:2: ( 'boolean' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1270:4: 'boolean'
            {
            match("boolean"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BOOLEAN_KEYWORD"

    // $ANTLR start "USE"
    public final void mUSE() throws RecognitionException {
        try {
            int _type = USE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1271:6: ( 'use' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1271:8: 'use'
            {
            match("use"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "USE"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1273:5: ( 'not' | 'Not' )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1273:7: 'not'
                    {
                    match("not"); 



                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1273:15: 'Not'
                    {
                    match("Not"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "NOTEQUALS"
    public final void mNOTEQUALS() throws RecognitionException {
        try {
            int _type = NOTEQUALS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1275:2: ( ( 'n' | 'N' ) 'ot=' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1275:4: ( 'n' | 'N' ) 'ot='
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            match("ot="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NOTEQUALS"

    // $ANTLR start "PERIOD"
    public final void mPERIOD() throws RecognitionException {
        try {
            int _type = PERIOD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1276:8: ( '.' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1276:10: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PERIOD"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1277:7: ( ',' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1277:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "EQUALITY"
    public final void mEQUALITY() throws RecognitionException {
        try {
            int _type = EQUALITY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1278:9: ( '=' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1278:11: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EQUALITY"

    // $ANTLR start "GREATER"
    public final void mGREATER() throws RecognitionException {
        try {
            int _type = GREATER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1279:9: ( '>' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1279:11: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GREATER"

    // $ANTLR start "GREATER_EQUAL"
    public final void mGREATER_EQUAL() throws RecognitionException {
        try {
            int _type = GREATER_EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1281:2: ( '>=' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1281:4: '>='
            {
            match(">="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GREATER_EQUAL"

    // $ANTLR start "LESS"
    public final void mLESS() throws RecognitionException {
        try {
            int _type = LESS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1282:6: ( '<' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1282:8: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LESS"

    // $ANTLR start "LESS_EQUAL"
    public final void mLESS_EQUAL() throws RecognitionException {
        try {
            int _type = LESS_EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1284:2: ( '<=' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1284:4: '<='
            {
            match("<="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LESS_EQUAL"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1285:6: ( '+' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1285:8: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1286:7: ( '-' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1286:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "MULTIPLY"
    public final void mMULTIPLY() throws RecognitionException {
        try {
            int _type = MULTIPLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1287:9: ( '*' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1287:11: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MULTIPLY"

    // $ANTLR start "DIVIDE"
    public final void mDIVIDE() throws RecognitionException {
        try {
            int _type = DIVIDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1288:8: ( '/' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1288:10: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIVIDE"

    // $ANTLR start "MODULO"
    public final void mMODULO() throws RecognitionException {
        try {
            int _type = MODULO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1289:8: ( 'mod' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1289:10: 'mod'
            {
            match("mod"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MODULO"

    // $ANTLR start "LEFT_SQR_BRACE"
    public final void mLEFT_SQR_BRACE() throws RecognitionException {
        try {
            int _type = LEFT_SQR_BRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1291:2: ( '[' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1291:4: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LEFT_SQR_BRACE"

    // $ANTLR start "RIGHT_SQR_BRACE"
    public final void mRIGHT_SQR_BRACE() throws RecognitionException {
        try {
            int _type = RIGHT_SQR_BRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1293:2: ( ']' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1293:4: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RIGHT_SQR_BRACE"

    // $ANTLR start "LEFT_PAREN"
    public final void mLEFT_PAREN() throws RecognitionException {
        try {
            int _type = LEFT_PAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1295:2: ( '(' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1295:4: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LEFT_PAREN"

    // $ANTLR start "RIGHT_PAREN"
    public final void mRIGHT_PAREN() throws RecognitionException {
        try {
            int _type = RIGHT_PAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1297:2: ( ')' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1297:4: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RIGHT_PAREN"

    // $ANTLR start "DOUBLE_QUOTE"
    public final void mDOUBLE_QUOTE() throws RecognitionException {
        try {
            int _type = DOUBLE_QUOTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1299:2: ( '\"' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1299:4: '\"'
            {
            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOUBLE_QUOTE"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1300:4: ( 'if' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1300:6: 'if'
            {
            match("if"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "END"
    public final void mEND() throws RecognitionException {
        try {
            int _type = END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1301:5: ( 'end' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1301:7: 'end'
            {
            match("end"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "END"

    // $ANTLR start "CLASS"
    public final void mCLASS() throws RecognitionException {
        try {
            int _type = CLASS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1302:7: ( 'class' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1302:9: 'class'
            {
            match("class"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CLASS"

    // $ANTLR start "BOOLEAN"
    public final void mBOOLEAN() throws RecognitionException {
        try {
            int _type = BOOLEAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1303:9: ( 'true' | 'false' )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1303:11: 'true'
                    {
                    match("true"); 



                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1303:20: 'false'
                    {
                    match("false"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BOOLEAN"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1304:6: ( ( '0' .. '9' )+ )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1304:8: ( '0' .. '9' )+
            {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1304:8: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


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
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "DECIMAL"
    public final void mDECIMAL() throws RecognitionException {
        try {
            int _type = DECIMAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1305:9: ( ( '0' .. '9' )+ ( PERIOD ( '0' .. '9' )* )? )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1305:11: ( '0' .. '9' )+ ( PERIOD ( '0' .. '9' )* )?
            {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1305:11: ( '0' .. '9' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0 >= '0' && LA4_0 <= '9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


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


            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1305:21: ( PERIOD ( '0' .. '9' )* )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='.') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1305:22: PERIOD ( '0' .. '9' )*
                    {
                    mPERIOD(); 


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1305:29: ( '0' .. '9' )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( ((LA5_0 >= '0' && LA5_0 <= '9')) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


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
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DECIMAL"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1306:5: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1306:8: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1306:27: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0 >= '0' && LA7_0 <= '9')||(LA7_0 >= 'A' && LA7_0 <= 'Z')||LA7_0=='_'||(LA7_0 >= 'a' && LA7_0 <= 'z')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


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
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1307:8: ( DOUBLE_QUOTE (~ ( DOUBLE_QUOTE ) )* DOUBLE_QUOTE )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1307:10: DOUBLE_QUOTE (~ ( DOUBLE_QUOTE ) )* DOUBLE_QUOTE
            {
            mDOUBLE_QUOTE(); 


            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1307:23: (~ ( DOUBLE_QUOTE ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0 >= '\u0000' && LA8_0 <= '!')||(LA8_0 >= '#' && LA8_0 <= '\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


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
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1310:9: ( ( '\\r' )? '\\n' )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1310:12: ( '\\r' )? '\\n'
            {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1310:12: ( '\\r' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0=='\r') ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1310:12: '\\r'
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
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1311:4: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1311:6: ( ' ' | '\\t' | '\\n' | '\\r' )+
            {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1311:6: ( ' ' | '\\t' | '\\n' | '\\r' )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0 >= '\t' && LA10_0 <= '\n')||LA10_0=='\r'||LA10_0==' ') ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:
            	    {
            	    if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


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
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "COMMENTS"
    public final void mCOMMENTS() throws RecognitionException {
        try {
            int _type = COMMENTS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1314:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( ( ( '\\r' )? '\\n' ) | EOF ) | '/*' ( options {greedy=false; } : . )* '*/' )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1314:9: '//' (~ ( '\\n' | '\\r' ) )* ( ( ( '\\r' )? '\\n' ) | EOF )
                    {
                    match("//"); 



                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1314:14: (~ ( '\\n' | '\\r' ) )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( ((LA11_0 >= '\u0000' && LA11_0 <= '\t')||(LA11_0 >= '\u000B' && LA11_0 <= '\f')||(LA11_0 >= '\u000E' && LA11_0 <= '\uFFFF')) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:
                    	    {
                    	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1314:28: ( ( ( '\\r' )? '\\n' ) | EOF )
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0=='\n'||LA13_0=='\r') ) {
                        alt13=1;
                    }
                    else {
                        alt13=2;
                    }
                    switch (alt13) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1314:29: ( ( '\\r' )? '\\n' )
                            {
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1314:29: ( ( '\\r' )? '\\n' )
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1314:30: ( '\\r' )? '\\n'
                            {
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1314:30: ( '\\r' )?
                            int alt12=2;
                            int LA12_0 = input.LA(1);

                            if ( (LA12_0=='\r') ) {
                                alt12=1;
                            }
                            switch (alt12) {
                                case 1 :
                                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1314:30: '\\r'
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
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1314:44: EOF
                            {
                            match(EOF); 


                            }
                            break;

                    }


                    _channel=HIDDEN;

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1315:9: '/*' ( options {greedy=false; } : . )* '*/'
                    {
                    match("/*"); 



                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1315:14: ( options {greedy=false; } : . )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0=='*') ) {
                            int LA14_1 = input.LA(2);

                            if ( (LA14_1=='/') ) {
                                alt14=2;
                            }
                            else if ( ((LA14_1 >= '\u0000' && LA14_1 <= '.')||(LA14_1 >= '0' && LA14_1 <= '\uFFFF')) ) {
                                alt14=1;
                            }


                        }
                        else if ( ((LA14_0 >= '\u0000' && LA14_0 <= ')')||(LA14_0 >= '+' && LA14_0 <= '\uFFFF')) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1315:42: .
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
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMENTS"

    public void mTokens() throws RecognitionException {
        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:8: ( ELSE_IF | ME | UNTIL | ON_DESTROY | ON_CREATE | OF_TYPE | PUBLIC | PRIVATE | ALERT | DETECT | ALWAYS | CHECK | PARENT | BLUEPRINT | NATIVE | INHERITS | CAST | PRINT | INPUT | SAY | NOW | WHILE | PACKAGE_NAME | TIMES | REPEAT | OVER | ELSE | RETURNS | RETURN | AND | OR | NULL | ACTION | COLON | INTEGER_KEYWORD | NUMBER_KEYWORD | TEXT | BOOLEAN_KEYWORD | USE | NOT | NOTEQUALS | PERIOD | COMMA | EQUALITY | GREATER | GREATER_EQUAL | LESS | LESS_EQUAL | PLUS | MINUS | MULTIPLY | DIVIDE | MODULO | LEFT_SQR_BRACE | RIGHT_SQR_BRACE | LEFT_PAREN | RIGHT_PAREN | DOUBLE_QUOTE | IF | END | CLASS | BOOLEAN | INT | DECIMAL | ID | STRING | NEWLINE | WS | COMMENTS )
        int alt16=69;
        alt16 = dfa16.predict(input);
        switch (alt16) {
            case 1 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:10: ELSE_IF
                {
                mELSE_IF(); 


                }
                break;
            case 2 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:18: ME
                {
                mME(); 


                }
                break;
            case 3 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:21: UNTIL
                {
                mUNTIL(); 


                }
                break;
            case 4 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:27: ON_DESTROY
                {
                mON_DESTROY(); 


                }
                break;
            case 5 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:38: ON_CREATE
                {
                mON_CREATE(); 


                }
                break;
            case 6 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:48: OF_TYPE
                {
                mOF_TYPE(); 


                }
                break;
            case 7 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:56: PUBLIC
                {
                mPUBLIC(); 


                }
                break;
            case 8 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:63: PRIVATE
                {
                mPRIVATE(); 


                }
                break;
            case 9 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:71: ALERT
                {
                mALERT(); 


                }
                break;
            case 10 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:77: DETECT
                {
                mDETECT(); 


                }
                break;
            case 11 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:84: ALWAYS
                {
                mALWAYS(); 


                }
                break;
            case 12 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:91: CHECK
                {
                mCHECK(); 


                }
                break;
            case 13 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:97: PARENT
                {
                mPARENT(); 


                }
                break;
            case 14 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:104: BLUEPRINT
                {
                mBLUEPRINT(); 


                }
                break;
            case 15 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:114: NATIVE
                {
                mNATIVE(); 


                }
                break;
            case 16 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:121: INHERITS
                {
                mINHERITS(); 


                }
                break;
            case 17 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:130: CAST
                {
                mCAST(); 


                }
                break;
            case 18 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:135: PRINT
                {
                mPRINT(); 


                }
                break;
            case 19 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:141: INPUT
                {
                mINPUT(); 


                }
                break;
            case 20 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:147: SAY
                {
                mSAY(); 


                }
                break;
            case 21 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:151: NOW
                {
                mNOW(); 


                }
                break;
            case 22 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:155: WHILE
                {
                mWHILE(); 


                }
                break;
            case 23 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:161: PACKAGE_NAME
                {
                mPACKAGE_NAME(); 


                }
                break;
            case 24 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:174: TIMES
                {
                mTIMES(); 


                }
                break;
            case 25 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:180: REPEAT
                {
                mREPEAT(); 


                }
                break;
            case 26 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:187: OVER
                {
                mOVER(); 


                }
                break;
            case 27 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:192: ELSE
                {
                mELSE(); 


                }
                break;
            case 28 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:197: RETURNS
                {
                mRETURNS(); 


                }
                break;
            case 29 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:205: RETURN
                {
                mRETURN(); 


                }
                break;
            case 30 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:212: AND
                {
                mAND(); 


                }
                break;
            case 31 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:216: OR
                {
                mOR(); 


                }
                break;
            case 32 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:219: NULL
                {
                mNULL(); 


                }
                break;
            case 33 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:224: ACTION
                {
                mACTION(); 


                }
                break;
            case 34 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:231: COLON
                {
                mCOLON(); 


                }
                break;
            case 35 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:237: INTEGER_KEYWORD
                {
                mINTEGER_KEYWORD(); 


                }
                break;
            case 36 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:253: NUMBER_KEYWORD
                {
                mNUMBER_KEYWORD(); 


                }
                break;
            case 37 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:268: TEXT
                {
                mTEXT(); 


                }
                break;
            case 38 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:273: BOOLEAN_KEYWORD
                {
                mBOOLEAN_KEYWORD(); 


                }
                break;
            case 39 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:289: USE
                {
                mUSE(); 


                }
                break;
            case 40 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:293: NOT
                {
                mNOT(); 


                }
                break;
            case 41 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:297: NOTEQUALS
                {
                mNOTEQUALS(); 


                }
                break;
            case 42 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:307: PERIOD
                {
                mPERIOD(); 


                }
                break;
            case 43 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:314: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 44 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:320: EQUALITY
                {
                mEQUALITY(); 


                }
                break;
            case 45 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:329: GREATER
                {
                mGREATER(); 


                }
                break;
            case 46 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:337: GREATER_EQUAL
                {
                mGREATER_EQUAL(); 


                }
                break;
            case 47 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:351: LESS
                {
                mLESS(); 


                }
                break;
            case 48 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:356: LESS_EQUAL
                {
                mLESS_EQUAL(); 


                }
                break;
            case 49 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:367: PLUS
                {
                mPLUS(); 


                }
                break;
            case 50 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:372: MINUS
                {
                mMINUS(); 


                }
                break;
            case 51 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:378: MULTIPLY
                {
                mMULTIPLY(); 


                }
                break;
            case 52 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:387: DIVIDE
                {
                mDIVIDE(); 


                }
                break;
            case 53 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:394: MODULO
                {
                mMODULO(); 


                }
                break;
            case 54 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:401: LEFT_SQR_BRACE
                {
                mLEFT_SQR_BRACE(); 


                }
                break;
            case 55 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:416: RIGHT_SQR_BRACE
                {
                mRIGHT_SQR_BRACE(); 


                }
                break;
            case 56 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:432: LEFT_PAREN
                {
                mLEFT_PAREN(); 


                }
                break;
            case 57 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:443: RIGHT_PAREN
                {
                mRIGHT_PAREN(); 


                }
                break;
            case 58 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:455: DOUBLE_QUOTE
                {
                mDOUBLE_QUOTE(); 


                }
                break;
            case 59 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:468: IF
                {
                mIF(); 


                }
                break;
            case 60 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:471: END
                {
                mEND(); 


                }
                break;
            case 61 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:475: CLASS
                {
                mCLASS(); 


                }
                break;
            case 62 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:481: BOOLEAN
                {
                mBOOLEAN(); 


                }
                break;
            case 63 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:489: INT
                {
                mINT(); 


                }
                break;
            case 64 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:493: DECIMAL
                {
                mDECIMAL(); 


                }
                break;
            case 65 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:501: ID
                {
                mID(); 


                }
                break;
            case 66 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:504: STRING
                {
                mSTRING(); 


                }
                break;
            case 67 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:511: NEWLINE
                {
                mNEWLINE(); 


                }
                break;
            case 68 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:519: WS
                {
                mWS(); 


                }
                break;
            case 69 :
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1:522: COMMENTS
                {
                mCOMMENTS(); 


                }
                break;

        }

    }


    protected DFA16 dfa16 = new DFA16(this);
    static final String DFA16_eotS =
        "\1\uffff\17\42\1\uffff\1\42\3\uffff\1\112\1\114\3\uffff\1\116\4"+
        "\uffff\1\117\1\42\1\122\1\uffff\1\45\1\124\1\uffff\2\42\1\127\6"+
        "\42\1\137\20\42\1\163\10\42\10\uffff\1\42\3\uffff\1\42\1\u0080\1"+
        "\uffff\1\u0081\2\42\1\u0084\2\uffff\1\42\1\uffff\6\42\1\u008f\10"+
        "\42\1\u0098\1\uffff\2\42\1\uffff\1\u009b\1\u009d\7\42\1\u009d\1"+
        "\42\1\u00a7\2\uffff\2\42\3\uffff\1\u00aa\7\42\1\uffff\3\42\1\u00b5"+
        "\4\42\1\uffff\2\42\3\uffff\3\42\1\u00bf\1\u00c0\4\42\1\uffff\1\u00c5"+
        "\1\42\1\uffff\2\42\1\u00c9\2\42\1\u00cc\3\42\1\u00d0\1\uffff\1\u00d1"+
        "\3\42\1\u00d5\2\42\1\u00d8\1\u00d9\2\uffff\2\42\1\u00c0\1\u00dc"+
        "\1\uffff\1\42\1\u00de\1\42\1\uffff\1\u00e0\1\42\1\uffff\1\u00e2"+
        "\1\u00e3\1\u00e4\2\uffff\2\42\1\u00e7\1\uffff\1\42\1\u00e9\2\uffff"+
        "\1\u00ea\1\u00ec\1\uffff\1\42\1\uffff\1\u00ee\1\uffff\1\u00ef\3"+
        "\uffff\1\42\1\u00f1\1\uffff\1\u00f2\2\uffff\1\u00f3\1\uffff\1\42"+
        "\2\uffff\1\42\3\uffff\1\u00f6\1\u00f7\2\uffff";
    static final String DFA16_eofS =
        "\u00f8\uffff";
    static final String DFA16_minS =
        "\1\11\1\154\1\145\1\156\1\146\1\141\1\143\1\145\1\141\1\154\1\141"+
        "\1\146\1\157\1\150\2\145\1\uffff\1\157\3\uffff\2\75\3\uffff\1\52"+
        "\4\uffff\1\0\1\141\1\56\1\uffff\1\12\1\11\1\uffff\1\163\1\144\1"+
        "\60\2\144\1\145\2\40\1\145\1\60\1\142\1\151\1\143\1\145\1\144\2"+
        "\164\1\145\1\163\1\141\1\165\1\157\1\163\1\171\1\40\1\160\1\60\1"+
        "\164\1\155\1\151\1\155\1\170\1\165\1\160\1\164\10\uffff\1\154\3"+
        "\uffff\1\145\1\60\1\uffff\1\60\1\151\1\145\1\60\1\143\1\uffff\1"+
        "\162\1\uffff\1\154\1\156\1\145\1\153\1\162\1\141\1\60\1\151\1\145"+
        "\1\143\1\164\1\163\1\145\1\154\1\164\1\60\1\uffff\1\165\1\145\1"+
        "\uffff\2\60\1\142\1\154\1\145\1\164\2\145\1\165\1\60\1\163\1\60"+
        "\2\uffff\1\154\1\146\3\uffff\1\60\1\151\1\141\1\164\1\156\1\141"+
        "\1\164\1\171\1\uffff\1\157\1\143\1\153\1\60\1\163\1\160\2\145\1"+
        "\uffff\1\164\1\147\3\uffff\2\145\1\163\2\60\1\141\1\162\1\145\1"+
        "\146\1\uffff\1\60\1\151\1\uffff\1\143\1\164\1\60\1\164\1\147\1\60"+
        "\1\163\1\156\1\164\1\60\1\uffff\1\60\1\162\1\141\1\155\1\60\1\145"+
        "\1\162\2\60\2\uffff\1\164\1\156\2\60\1\uffff\1\156\1\60\1\145\1"+
        "\uffff\1\60\1\145\1\uffff\3\60\2\uffff\1\151\1\156\1\60\1\uffff"+
        "\1\162\1\60\2\uffff\2\60\1\uffff\1\145\1\uffff\1\60\1\uffff\1\60"+
        "\3\uffff\1\156\1\60\1\uffff\1\60\2\uffff\1\60\1\uffff\1\144\2\uffff"+
        "\1\164\3\uffff\2\60\2\uffff";
    static final String DFA16_maxS =
        "\1\172\1\156\1\157\1\163\1\166\1\165\1\156\1\145\1\154\1\157\1\171"+
        "\1\163\1\165\1\150\1\162\1\145\1\uffff\1\157\3\uffff\2\75\3\uffff"+
        "\1\57\4\uffff\1\uffff\1\141\1\71\1\uffff\1\12\1\40\1\uffff\1\163"+
        "\1\144\1\172\1\144\1\164\1\145\2\40\1\145\1\172\1\142\1\151\1\162"+
        "\1\167\1\144\2\164\1\145\1\163\1\141\1\165\1\157\1\163\1\171\1\40"+
        "\1\164\1\172\1\167\1\155\1\151\1\155\1\170\1\165\2\164\10\uffff"+
        "\1\154\3\uffff\1\145\1\172\1\uffff\1\172\1\151\1\145\1\172\1\144"+
        "\1\uffff\1\162\1\uffff\1\154\1\166\1\145\1\153\1\162\1\141\1\172"+
        "\1\151\1\145\1\143\1\164\1\163\1\145\1\154\1\164\1\172\1\uffff\1"+
        "\165\1\145\1\uffff\2\172\1\142\1\154\1\145\1\164\2\145\1\165\1\172"+
        "\1\163\1\172\2\uffff\1\154\1\146\3\uffff\1\172\1\151\1\141\1\164"+
        "\1\156\1\141\1\164\1\171\1\uffff\1\157\1\143\1\153\1\172\1\163\1"+
        "\160\2\145\1\uffff\1\164\1\147\3\uffff\2\145\1\163\2\172\1\141\1"+
        "\162\1\145\1\146\1\uffff\1\172\1\151\1\uffff\1\143\1\164\1\172\1"+
        "\164\1\147\1\172\1\163\1\156\1\164\1\172\1\uffff\1\172\1\162\1\141"+
        "\1\155\1\172\1\145\1\162\2\172\2\uffff\1\164\1\156\2\172\1\uffff"+
        "\1\156\1\172\1\145\1\uffff\1\172\1\145\1\uffff\3\172\2\uffff\1\151"+
        "\1\156\1\172\1\uffff\1\162\1\172\2\uffff\2\172\1\uffff\1\145\1\uffff"+
        "\1\172\1\uffff\1\172\3\uffff\1\156\1\172\1\uffff\1\172\2\uffff\1"+
        "\172\1\uffff\1\144\2\uffff\1\164\3\uffff\2\172\2\uffff";
    static final String DFA16_acceptS =
        "\20\uffff\1\42\1\uffff\1\52\1\53\1\54\2\uffff\1\61\1\62\1\63\1\uffff"+
        "\1\66\1\67\1\70\1\71\3\uffff\1\101\2\uffff\1\104\43\uffff\1\56\1"+
        "\55\1\60\1\57\1\105\1\64\1\72\1\102\1\uffff\1\77\1\100\1\103\2\uffff"+
        "\1\2\5\uffff\1\6\1\uffff\1\37\20\uffff\1\20\2\uffff\1\73\14\uffff"+
        "\1\74\1\65\2\uffff\1\47\1\4\1\5\10\uffff\1\36\10\uffff\1\24\2\uffff"+
        "\1\25\1\51\1\50\11\uffff\1\33\2\uffff\1\32\12\uffff\1\21\11\uffff"+
        "\1\45\1\76\4\uffff\1\3\3\uffff\1\22\2\uffff\1\11\3\uffff\1\14\1"+
        "\75\3\uffff\1\23\2\uffff\1\26\1\30\2\uffff\1\1\1\uffff\1\7\1\uffff"+
        "\1\15\1\uffff\1\13\1\41\1\12\2\uffff\1\17\1\uffff\1\44\1\31\1\uffff"+
        "\1\35\1\uffff\1\10\1\27\1\uffff\1\46\1\43\1\34\2\uffff\1\40\1\16";
    static final String DFA16_specialS =
        "\37\uffff\1\0\u00d8\uffff}>";
    static final String[] DFA16_transitionS = {
            "\1\45\1\44\2\uffff\1\43\22\uffff\1\45\1\uffff\1\37\5\uffff\1"+
            "\35\1\36\1\31\1\27\1\23\1\30\1\22\1\32\12\41\1\20\1\uffff\1"+
            "\26\1\24\1\25\2\uffff\15\42\1\21\14\42\1\33\1\uffff\1\34\3\uffff"+
            "\1\6\1\11\1\10\1\7\1\1\1\40\2\42\1\13\3\42\1\2\1\14\1\4\1\5"+
            "\1\42\1\17\1\12\1\16\1\3\1\42\1\15\3\42",
            "\1\46\1\uffff\1\47",
            "\1\50\11\uffff\1\51",
            "\1\52\4\uffff\1\53",
            "\1\55\7\uffff\1\54\3\uffff\1\57\3\uffff\1\56",
            "\1\62\20\uffff\1\61\2\uffff\1\60",
            "\1\65\10\uffff\1\63\1\uffff\1\64",
            "\1\66",
            "\1\70\6\uffff\1\67\3\uffff\1\71",
            "\1\72\2\uffff\1\73",
            "\1\75\27\uffff\1\74",
            "\1\100\7\uffff\1\77\4\uffff\1\76",
            "\1\101\5\uffff\1\102",
            "\1\103",
            "\1\105\3\uffff\1\104\10\uffff\1\106",
            "\1\107",
            "",
            "\1\110",
            "",
            "",
            "",
            "\1\111",
            "\1\113",
            "",
            "",
            "",
            "\1\115\4\uffff\1\115",
            "",
            "",
            "",
            "",
            "\0\120",
            "\1\121",
            "\1\123\1\uffff\12\41",
            "",
            "\1\44",
            "\2\45\2\uffff\1\45\22\uffff\1\45",
            "",
            "\1\125",
            "\1\126",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\130",
            "\1\132\17\uffff\1\131",
            "\1\133",
            "\1\134",
            "\1\135",
            "\1\136",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\140",
            "\1\141",
            "\1\143\16\uffff\1\142",
            "\1\144\21\uffff\1\145",
            "\1\146",
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
            "\1\161\3\uffff\1\162",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\165\2\uffff\1\164",
            "\1\166",
            "\1\167",
            "\1\170",
            "\1\171",
            "\1\172",
            "\1\173\3\uffff\1\174",
            "\1\175",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\176",
            "",
            "",
            "",
            "\1\177",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0082",
            "\1\u0083",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0086\1\u0085",
            "",
            "\1\u0087",
            "",
            "\1\u0088",
            "\1\u008a\7\uffff\1\u0089",
            "\1\u008b",
            "\1\u008c",
            "\1\u008d",
            "\1\u008e",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u0090",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\1\u0099",
            "\1\u009a",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\12\42\3\uffff\1\u009c\3\uffff\32\42\4\uffff\1\42\1\uffff\32"+
            "\42",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0",
            "\1\u00a1",
            "\1\u00a2",
            "\1\u00a3",
            "\1\u00a4",
            "\12\42\3\uffff\1\u009c\3\uffff\32\42\4\uffff\1\42\1\uffff\32"+
            "\42",
            "\1\u00a5",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\10\42\1\u00a6\21"+
            "\42",
            "",
            "",
            "\1\u00a8",
            "\1\u00a9",
            "",
            "",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00ab",
            "\1\u00ac",
            "\1\u00ad",
            "\1\u00ae",
            "\1\u00af",
            "\1\u00b0",
            "\1\u00b1",
            "",
            "\1\u00b2",
            "\1\u00b3",
            "\1\u00b4",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00b8",
            "\1\u00b9",
            "",
            "\1\u00ba",
            "\1\u00bb",
            "",
            "",
            "",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00c1",
            "\1\u00c2",
            "\1\u00c3",
            "\1\u00c4",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00c6",
            "",
            "\1\u00c7",
            "\1\u00c8",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00ca",
            "\1\u00cb",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00cd",
            "\1\u00ce",
            "\1\u00cf",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00d2",
            "\1\u00d3",
            "\1\u00d4",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00d6",
            "\1\u00d7",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "",
            "\1\u00da",
            "\1\u00db",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\1\u00dd",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00df",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\1\u00e1",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "",
            "\1\u00e5",
            "\1\u00e6",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\1\u00e8",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\22\42\1\u00eb\7\42",
            "",
            "\1\u00ed",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "",
            "",
            "\1\u00f0",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "",
            "\12\42\7\uffff\32\42\4\uffff\1\42\1\uffff\32\42",
            "",
            "\1\u00f4",
            "",
            "",
            "\1\u00f5",
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
            return "1:1: Tokens : ( ELSE_IF | ME | UNTIL | ON_DESTROY | ON_CREATE | OF_TYPE | PUBLIC | PRIVATE | ALERT | DETECT | ALWAYS | CHECK | PARENT | BLUEPRINT | NATIVE | INHERITS | CAST | PRINT | INPUT | SAY | NOW | WHILE | PACKAGE_NAME | TIMES | REPEAT | OVER | ELSE | RETURNS | RETURN | AND | OR | NULL | ACTION | COLON | INTEGER_KEYWORD | NUMBER_KEYWORD | TEXT | BOOLEAN_KEYWORD | USE | NOT | NOTEQUALS | PERIOD | COMMA | EQUALITY | GREATER | GREATER_EQUAL | LESS | LESS_EQUAL | PLUS | MINUS | MULTIPLY | DIVIDE | MODULO | LEFT_SQR_BRACE | RIGHT_SQR_BRACE | LEFT_PAREN | RIGHT_PAREN | DOUBLE_QUOTE | IF | END | CLASS | BOOLEAN | INT | DECIMAL | ID | STRING | NEWLINE | WS | COMMENTS );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA16_31 = input.LA(1);

                        s = -1;
                        if ( ((LA16_31 >= '\u0000' && LA16_31 <= '\uFFFF')) ) {s = 80;}

                        else s = 79;

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