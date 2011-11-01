// $ANTLR 3.3 Nov 30, 2010 12:45:30 /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g 2011-11-01 16:52:56


package org.quorum.parser;
import org.quorum.vm.interfaces.*;
import org.quorum.symbols.*;
import org.quorum.vm.implementation.*;
import org.quorum.steps.*;
import org.quorum.execution.ExpressionValue;
import org.quorum.execution.ExecutionStep;
import org.quorum.execution.ScopeSelector;
import java.util.Iterator;
import java.util.Vector;
import java.util.Enumeration;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;
import java.util.HashMap;
public class QuorumTreeWalker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "FUNCTION_CALL", "FUNCTION_CALL_PARENT", "FUNCTION_CALL_THIS", "FUNCTION_EXPRESSION_LIST", "SOLO_FUNCTION_CALL", "SOLO_FUNCTION_CALL_PARENT", "SOLO_FUNCTION_CALL_THIS", "QUALIFIED_NAME", "EXPRESSION_STATEMENT", "STATEMENT_LIST", "CONSTRUCTOR", "FPARAM", "UNARY_NOT", "ELSE_IF_STATEMENT", "FINAL_ELSE", "PAREN_WRAPPED_EXPRESSION", "ROOT_EXPRESSION", "QUALIFIED_SOLO_EXPRESSION", "QUALIFIED_SOLO_EXPRESSION_SELECTOR", "GENERIC", "PACKAGE_NAME", "USE", "CLASS", "ID", "END", "INHERITS", "COMMA", "PUBLIC", "PRIVATE", "ACTION", "LEFT_PAREN", "RIGHT_PAREN", "RETURNS", "BLUEPRINT", "NATIVE", "ON_CREATE", "PERIOD", "COLON", "PARENT", "ME", "LIBRARY_CALL", "CONNECT_TO", "SEND_TO", "ALERT", "CHECK", "DETECT", "ALWAYS", "OF_TYPE", "OR", "PRINT", "SAY", "RETURN", "NOW", "LESS", "GREATER", "INTEGER_KEYWORD", "NUMBER_KEYWORD", "TEXT", "BOOLEAN_KEYWORD", "EQUALITY", "IF", "THEN", "ELSE", "REPEAT", "OVER", "FROM", "TIMES", "WHILE", "UNTIL", "TO", "AND", "NOTEQUALS", "GREATER_EQUAL", "LESS_EQUAL", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", "MODULO", "NOT", "CAST", "INT", "BOOLEAN", "DECIMAL", "STRING", "NULL", "INPUT", "ON_DESTROY", "ON", "LEFT_ARROW", "LEFT_SQR_BRACE", "RIGHT_SQR_BRACE", "DOUBLE_QUOTE", "CALL_FUNCTION_TOKEN", "NEWLINE", "WS", "COMMENTS"
    };
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
    public static final int GENERIC=23;
    public static final int PACKAGE_NAME=24;
    public static final int USE=25;
    public static final int CLASS=26;
    public static final int ID=27;
    public static final int END=28;
    public static final int INHERITS=29;
    public static final int COMMA=30;
    public static final int PUBLIC=31;
    public static final int PRIVATE=32;
    public static final int ACTION=33;
    public static final int LEFT_PAREN=34;
    public static final int RIGHT_PAREN=35;
    public static final int RETURNS=36;
    public static final int BLUEPRINT=37;
    public static final int NATIVE=38;
    public static final int ON_CREATE=39;
    public static final int PERIOD=40;
    public static final int COLON=41;
    public static final int PARENT=42;
    public static final int ME=43;
    public static final int LIBRARY_CALL=44;
    public static final int CONNECT_TO=45;
    public static final int SEND_TO=46;
    public static final int ALERT=47;
    public static final int CHECK=48;
    public static final int DETECT=49;
    public static final int ALWAYS=50;
    public static final int OF_TYPE=51;
    public static final int OR=52;
    public static final int PRINT=53;
    public static final int SAY=54;
    public static final int RETURN=55;
    public static final int NOW=56;
    public static final int LESS=57;
    public static final int GREATER=58;
    public static final int INTEGER_KEYWORD=59;
    public static final int NUMBER_KEYWORD=60;
    public static final int TEXT=61;
    public static final int BOOLEAN_KEYWORD=62;
    public static final int EQUALITY=63;
    public static final int IF=64;
    public static final int THEN=65;
    public static final int ELSE=66;
    public static final int REPEAT=67;
    public static final int OVER=68;
    public static final int FROM=69;
    public static final int TIMES=70;
    public static final int WHILE=71;
    public static final int UNTIL=72;
    public static final int TO=73;
    public static final int AND=74;
    public static final int NOTEQUALS=75;
    public static final int GREATER_EQUAL=76;
    public static final int LESS_EQUAL=77;
    public static final int PLUS=78;
    public static final int MINUS=79;
    public static final int MULTIPLY=80;
    public static final int DIVIDE=81;
    public static final int MODULO=82;
    public static final int NOT=83;
    public static final int CAST=84;
    public static final int INT=85;
    public static final int BOOLEAN=86;
    public static final int DECIMAL=87;
    public static final int STRING=88;
    public static final int NULL=89;
    public static final int INPUT=90;
    public static final int ON_DESTROY=91;
    public static final int ON=92;
    public static final int LEFT_ARROW=93;
    public static final int LEFT_SQR_BRACE=94;
    public static final int RIGHT_SQR_BRACE=95;
    public static final int DOUBLE_QUOTE=96;
    public static final int CALL_FUNCTION_TOKEN=97;
    public static final int NEWLINE=98;
    public static final int WS=99;
    public static final int COMMENTS=100;

    // delegates
    // delegators


        public QuorumTreeWalker(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public QuorumTreeWalker(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected StringTemplateGroup templateLib =
      new StringTemplateGroup("QuorumTreeWalkerTemplates", AngleBracketTemplateLexer.class);

    public void setTemplateLib(StringTemplateGroup templateLib) {
      this.templateLib = templateLib;
    }
    public StringTemplateGroup getTemplateLib() {
      return templateLib;
    }
    /** allows convenient multi-value initialization:
     *  "new STAttrMap().put(...).put(...)"
     */
    public static class STAttrMap extends HashMap {
      public STAttrMap put(String attrName, Object value) {
        super.put(attrName, value);
        return this;
      }
      public STAttrMap put(String attrName, int value) {
        super.put(attrName, new Integer(value));
        return this;
      }
    }

    public String[] getTokenNames() { return QuorumTreeWalker.tokenNames; }
    public String getGrammarFileName() { return "/Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g"; }


    	QuorumVirtualMachine vm;
    	AccessModifierEnum accessModifier;
    	SymbolTable symbol;
    	QualifiedNameDescriptor thisPackage = new QualifiedNameDescriptor();;
    	String fileName;
    	boolean classWithNoName = false;
    	ClassDescriptor currentClass;
    	MethodDescriptor currentMethod;
    	IntermediateExecutionBuilder builder;
    	TypeChecker typeChecker;
    	StepFactory stepFactory = new StepFactory();
    	//used to create unique label hashes
    	static int labelCounter = 0;
    	static int sub_counter = 0;
    	boolean inCallStep = false;
    	
    	//the register number, used to place values into fake registers in the computer
    	static int temp = 0;
    	public void setQuorumVirtualMachine(QuorumVirtualMachine m) {
    		vm = m;
    		symbol = vm.getSymbolTable();
    		builder = vm.getBuilder();
    		stepFactory.setMachine(vm);
    		stepFactory.setTypeChecker(m.getTypeChecker());
    		typeChecker = m.getTypeChecker();
    	}

    	public String getGrammarFileNameNoExtension() {
    		return fileName;
    	}

    	public void setGrammarFileNameNoExtension(String name) {
    		fileName = name;
    	}


    public static class start_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "start"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:63:1: start : ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ | ) class_declaration EOF ;
    public final QuorumTreeWalker.start_return start() throws RecognitionException {
        QuorumTreeWalker.start_return retval = new QuorumTreeWalker.start_return();
        retval.start = input.LT(1);

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:63:7: ( ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ | ) class_declaration EOF )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:63:9: ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ | ) class_declaration EOF
            {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:63:9: ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ | )
            int alt4=5;
            alt4 = dfa4.predict(input);
            switch (alt4) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:63:10: package_rule ( reference )+
                    {
                    pushFollow(FOLLOW_package_rule_in_start51);
                    package_rule();

                    state._fsp--;

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:63:23: ( reference )+
                    int cnt1=0;
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==USE) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:63:23: reference
                    	    {
                    	    pushFollow(FOLLOW_reference_in_start53);
                    	    reference();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt1 >= 1 ) break loop1;
                                EarlyExitException eee =
                                    new EarlyExitException(1, input);
                                throw eee;
                        }
                        cnt1++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:64:4: ( reference )+ package_rule
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:64:4: ( reference )+
                    int cnt2=0;
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==USE) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:64:4: reference
                    	    {
                    	    pushFollow(FOLLOW_reference_in_start59);
                    	    reference();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt2 >= 1 ) break loop2;
                                EarlyExitException eee =
                                    new EarlyExitException(2, input);
                                throw eee;
                        }
                        cnt2++;
                    } while (true);

                    pushFollow(FOLLOW_package_rule_in_start62);
                    package_rule();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:65:4: package_rule
                    {
                    pushFollow(FOLLOW_package_rule_in_start67);
                    package_rule();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:66:4: ( reference )+
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:66:4: ( reference )+
                    int cnt3=0;
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==USE) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:66:4: reference
                    	    {
                    	    pushFollow(FOLLOW_reference_in_start72);
                    	    reference();

                    	    state._fsp--;


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
                    break;
                case 5 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:67:4: 
                    {
                    }
                    break;

            }

            pushFollow(FOLLOW_class_declaration_in_start81);
            class_declaration();

            state._fsp--;

            match(input,EOF,FOLLOW_EOF_in_start84); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "start"

    public static class package_rule_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "package_rule"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:71:1: package_rule : PACKAGE_NAME qn= qualified_name ;
    public final QuorumTreeWalker.package_rule_return package_rule() throws RecognitionException {
        QuorumTreeWalker.package_rule_return retval = new QuorumTreeWalker.package_rule_return();
        retval.start = input.LT(1);

        QuorumTreeWalker.qualified_name_return qn = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:71:15: ( PACKAGE_NAME qn= qualified_name )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:71:17: PACKAGE_NAME qn= qualified_name
            {
            match(input,PACKAGE_NAME,FOLLOW_PACKAGE_NAME_in_package_rule96); 
            pushFollow(FOLLOW_qualified_name_in_package_rule100);
            qn=qualified_name();

            state._fsp--;


            		thisPackage = (qn!=null?qn.type:null);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "package_rule"

    public static class reference_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "reference"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:77:1: reference : USE qualified_name ;
    public final QuorumTreeWalker.reference_return reference() throws RecognitionException {
        QuorumTreeWalker.reference_return retval = new QuorumTreeWalker.reference_return();
        retval.start = input.LT(1);

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:77:11: ( USE qualified_name )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:77:13: USE qualified_name
            {
            match(input,USE,FOLLOW_USE_in_reference113); 
            pushFollow(FOLLOW_qualified_name_in_reference115);
            qualified_name();

            state._fsp--;



            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "reference"

    public static class class_declaration_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "class_declaration"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:83:1: class_declaration : ( ^( CLASS ID ( generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END ) | no_class_stmnts );
    public final QuorumTreeWalker.class_declaration_return class_declaration() throws RecognitionException {
        QuorumTreeWalker.class_declaration_return retval = new QuorumTreeWalker.class_declaration_return();
        retval.start = input.LT(1);

        CommonTree ID1=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:83:20: ( ^( CLASS ID ( generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END ) | no_class_stmnts )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==CLASS) ) {
                alt8=1;
            }
            else if ( ((LA8_0>=SOLO_FUNCTION_CALL && LA8_0<=QUALIFIED_NAME)||LA8_0==ID||(LA8_0>=PUBLIC && LA8_0<=ACTION)||(LA8_0>=BLUEPRINT && LA8_0<=ON_CREATE)||(LA8_0>=PARENT && LA8_0<=ME)||(LA8_0>=ALERT && LA8_0<=CHECK)||(LA8_0>=PRINT && LA8_0<=RETURN)||(LA8_0>=INTEGER_KEYWORD && LA8_0<=BOOLEAN_KEYWORD)||LA8_0==IF||LA8_0==REPEAT) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:84:2: ^( CLASS ID ( generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END )
                    {
                    match(input,CLASS,FOLLOW_CLASS_in_class_declaration131); 

                    match(input, Token.DOWN, null); 
                    ID1=(CommonTree)match(input,ID,FOLLOW_ID_in_class_declaration133); 

                    		AccessModifierEnum e;
                    		String name;
                    		if((ID1!=null?ID1.getText():null) == null) {
                    			name = getGrammarFileNameNoExtension();
                    		}
                    		else {
                    			name = (ID1!=null?ID1.getText():null);
                    		}
                    		//get the class from the symbol table
                    		String container = thisPackage.toString();
                    		ClassDescriptor cl = symbol.enterClass(name, container);
                    		builder.begin(cl);
                    	
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:99:2: ( generic_declaration )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==GENERIC) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:99:2: generic_declaration
                            {
                            pushFollow(FOLLOW_generic_declaration_in_class_declaration139);
                            generic_declaration();

                            state._fsp--;


                            }
                            break;

                    }



                    	
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:103:2: ( inherit_stmnts )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==INHERITS) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:103:2: inherit_stmnts
                            {
                            pushFollow(FOLLOW_inherit_stmnts_in_class_declaration146);
                            inherit_stmnts();

                            state._fsp--;


                            }
                            break;

                    }


                    	
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:106:2: ( class_stmnts )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==QUALIFIED_NAME||LA7_0==ID||(LA7_0>=PUBLIC && LA7_0<=ACTION)||(LA7_0>=BLUEPRINT && LA7_0<=ON_CREATE)||(LA7_0>=PARENT && LA7_0<=ME)||(LA7_0>=INTEGER_KEYWORD && LA7_0<=BOOLEAN_KEYWORD)) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:106:2: class_stmnts
                    	    {
                    	    pushFollow(FOLLOW_class_stmnts_in_class_declaration153);
                    	    class_stmnts();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    match(input,END,FOLLOW_END_in_class_declaration156); 

                    		builder.endClass();
                    		symbol.popScope();
                    	

                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:112:2: no_class_stmnts
                    {

                    		String name = getGrammarFileNameNoExtension();
                    		String container = thisPackage.toString();
                    		ClassDescriptor cl = symbol.enterClass(name, container);
                    		builder.begin(cl);
                    	
                    pushFollow(FOLLOW_no_class_stmnts_in_class_declaration169);
                    no_class_stmnts();

                    state._fsp--;


                    		builder.endClass();
                    		symbol.popScope();
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "class_declaration"

    public static class no_class_stmnts_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "no_class_stmnts"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:125:1: no_class_stmnts : ( ( statement )+ | ( (modEnum= access_modifier )? method_declaration )+ );
    public final QuorumTreeWalker.no_class_stmnts_return no_class_stmnts() throws RecognitionException {
        QuorumTreeWalker.no_class_stmnts_return retval = new QuorumTreeWalker.no_class_stmnts_return();
        retval.start = input.LT(1);

        QuorumTreeWalker.access_modifier_return modEnum = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:126:2: ( ( statement )+ | ( (modEnum= access_modifier )? method_declaration )+ )
            int alt12=2;
            switch ( input.LA(1) ) {
            case SOLO_FUNCTION_CALL:
            case SOLO_FUNCTION_CALL_PARENT:
            case SOLO_FUNCTION_CALL_THIS:
            case QUALIFIED_NAME:
            case ID:
            case PARENT:
            case ME:
            case ALERT:
            case CHECK:
            case PRINT:
            case SAY:
            case RETURN:
            case INTEGER_KEYWORD:
            case NUMBER_KEYWORD:
            case TEXT:
            case BOOLEAN_KEYWORD:
            case IF:
            case REPEAT:
                {
                alt12=1;
                }
                break;
            case PUBLIC:
                {
                int LA12_2 = input.LA(2);

                if ( (LA12_2==QUALIFIED_NAME||(LA12_2>=INTEGER_KEYWORD && LA12_2<=BOOLEAN_KEYWORD)) ) {
                    alt12=1;
                }
                else if ( (LA12_2==ACTION||(LA12_2>=BLUEPRINT && LA12_2<=ON_CREATE)) ) {
                    alt12=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 2, input);

                    throw nvae;
                }
                }
                break;
            case PRIVATE:
                {
                int LA12_3 = input.LA(2);

                if ( (LA12_3==QUALIFIED_NAME||(LA12_3>=INTEGER_KEYWORD && LA12_3<=BOOLEAN_KEYWORD)) ) {
                    alt12=1;
                }
                else if ( (LA12_3==ACTION||(LA12_3>=BLUEPRINT && LA12_3<=ON_CREATE)) ) {
                    alt12=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 3, input);

                    throw nvae;
                }
                }
                break;
            case ACTION:
            case BLUEPRINT:
            case NATIVE:
            case ON_CREATE:
                {
                alt12=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:127:2: ( statement )+
                    {
                    //enter the fake method main
                    		MethodDescriptor md = symbol.enterMethod("main");
                    		builder.begin(md);
                    	
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:131:3: ( statement )+
                    int cnt9=0;
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0>=SOLO_FUNCTION_CALL && LA9_0<=QUALIFIED_NAME)||LA9_0==ID||(LA9_0>=PUBLIC && LA9_0<=PRIVATE)||(LA9_0>=PARENT && LA9_0<=ME)||(LA9_0>=ALERT && LA9_0<=CHECK)||(LA9_0>=PRINT && LA9_0<=RETURN)||(LA9_0>=INTEGER_KEYWORD && LA9_0<=BOOLEAN_KEYWORD)||LA9_0==IF||LA9_0==REPEAT) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:131:3: statement
                    	    {
                    	    pushFollow(FOLLOW_statement_in_no_class_stmnts189);
                    	    statement();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt9 >= 1 ) break loop9;
                                EarlyExitException eee =
                                    new EarlyExitException(9, input);
                                throw eee;
                        }
                        cnt9++;
                    } while (true);

                    //exit the fake method main
                    	
                    		CompilerError error = symbol.getControlFlow().endMethod();
                    		if(error != null) {
                    			vm.getCompilerErrors().addError(error);
                    		}
                    		if(symbol.getControlFlow().needsReturnStep() && error == null) { //do this only for void types
                                    	LineInformation location = new LineInformation();
                    	                location.setStartColumn(0);
                    	                location.setStartLine(0);
                    	                location.setFile(getGrammarFileNameNoExtension());
                    			stepFactory.addReturnStep(location, null, null);
                    		}
                    		builder.endMethod();
                    		symbol.popScope();
                    	

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:148:4: ( (modEnum= access_modifier )? method_declaration )+
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:148:4: ( (modEnum= access_modifier )? method_declaration )+
                    int cnt11=0;
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( ((LA11_0>=PUBLIC && LA11_0<=ACTION)||(LA11_0>=BLUEPRINT && LA11_0<=ON_CREATE)) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:148:5: (modEnum= access_modifier )? method_declaration
                    	    {
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:148:13: (modEnum= access_modifier )?
                    	    int alt10=2;
                    	    int LA10_0 = input.LA(1);

                    	    if ( ((LA10_0>=PUBLIC && LA10_0<=PRIVATE)) ) {
                    	        alt10=1;
                    	    }
                    	    switch (alt10) {
                    	        case 1 :
                    	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:148:13: modEnum= access_modifier
                    	            {
                    	            pushFollow(FOLLOW_access_modifier_in_no_class_stmnts203);
                    	            modEnum=access_modifier();

                    	            state._fsp--;


                    	            }
                    	            break;

                    	    }


                    	    		accessModifier = (modEnum!=null?modEnum.amEnum:null);
                    	    		if(accessModifier == null){
                    	    			accessModifier = accessModifier.PUBLIC;
                    	    		}
                    	    	
                    	    pushFollow(FOLLOW_method_declaration_in_no_class_stmnts211);
                    	    method_declaration();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt11 >= 1 ) break loop11;
                                EarlyExitException eee =
                                    new EarlyExitException(11, input);
                                throw eee;
                        }
                        cnt11++;
                    } while (true);


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "no_class_stmnts"

    public static class inherit_stmnts_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "inherit_stmnts"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:157:1: inherit_stmnts : ^( INHERITS (qn= qualified_name (gd= generic_statement )? )+ ) ;
    public final QuorumTreeWalker.inherit_stmnts_return inherit_stmnts() throws RecognitionException {
        QuorumTreeWalker.inherit_stmnts_return retval = new QuorumTreeWalker.inherit_stmnts_return();
        retval.start = input.LT(1);

        QuorumTreeWalker.qualified_name_return qn = null;

        QuorumTreeWalker.generic_statement_return gd = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:158:2: ( ^( INHERITS (qn= qualified_name (gd= generic_statement )? )+ ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:158:4: ^( INHERITS (qn= qualified_name (gd= generic_statement )? )+ )
            {
            match(input,INHERITS,FOLLOW_INHERITS_in_inherit_stmnts225); 

            match(input, Token.DOWN, null); 
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:158:15: (qn= qualified_name (gd= generic_statement )? )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==QUALIFIED_NAME) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:158:17: qn= qualified_name (gd= generic_statement )?
            	    {
            	    pushFollow(FOLLOW_qualified_name_in_inherit_stmnts233);
            	    qn=qualified_name();

            	    state._fsp--;

            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:158:40: (gd= generic_statement )?
            	    int alt13=2;
            	    int LA13_0 = input.LA(1);

            	    if ( (LA13_0==GENERIC) ) {
            	        alt13=1;
            	    }
            	    switch (alt13) {
            	        case 1 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:158:40: gd= generic_statement
            	            {
            	            pushFollow(FOLLOW_generic_statement_in_inherit_stmnts239);
            	            gd=generic_statement();

            	            state._fsp--;


            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "inherit_stmnts"

    public static class access_modifier_return extends TreeRuleReturnScope {
        public AccessModifierEnum amEnum;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "access_modifier"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:160:1: access_modifier returns [AccessModifierEnum amEnum] : ( PUBLIC | PRIVATE );
    public final QuorumTreeWalker.access_modifier_return access_modifier() throws RecognitionException {
        QuorumTreeWalker.access_modifier_return retval = new QuorumTreeWalker.access_modifier_return();
        retval.start = input.LT(1);

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:161:2: ( PUBLIC | PRIVATE )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==PUBLIC) ) {
                alt15=1;
            }
            else if ( (LA15_0==PRIVATE) ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:161:4: PUBLIC
                    {
                    match(input,PUBLIC,FOLLOW_PUBLIC_in_access_modifier257); 

                    		retval.amEnum = retval.amEnum.PUBLIC;
                    	

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:165:4: PRIVATE
                    {
                    match(input,PRIVATE,FOLLOW_PRIVATE_in_access_modifier265); 

                    		retval.amEnum = retval.amEnum.PRIVATE;
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "access_modifier"

    public static class class_stmnts_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "class_stmnts"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:170:1: class_stmnts : ( assignment_statement | (modEnum= access_modifier )? method_declaration );
    public final QuorumTreeWalker.class_stmnts_return class_stmnts() throws RecognitionException {
        QuorumTreeWalker.class_stmnts_return retval = new QuorumTreeWalker.class_stmnts_return();
        retval.start = input.LT(1);

        QuorumTreeWalker.access_modifier_return modEnum = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:171:2: ( assignment_statement | (modEnum= access_modifier )? method_declaration )
            int alt17=2;
            switch ( input.LA(1) ) {
            case QUALIFIED_NAME:
            case ID:
            case PARENT:
            case ME:
            case INTEGER_KEYWORD:
            case NUMBER_KEYWORD:
            case TEXT:
            case BOOLEAN_KEYWORD:
                {
                alt17=1;
                }
                break;
            case PUBLIC:
                {
                int LA17_2 = input.LA(2);

                if ( (LA17_2==QUALIFIED_NAME||(LA17_2>=INTEGER_KEYWORD && LA17_2<=BOOLEAN_KEYWORD)) ) {
                    alt17=1;
                }
                else if ( (LA17_2==ACTION||(LA17_2>=BLUEPRINT && LA17_2<=ON_CREATE)) ) {
                    alt17=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 17, 2, input);

                    throw nvae;
                }
                }
                break;
            case PRIVATE:
                {
                int LA17_3 = input.LA(2);

                if ( (LA17_3==QUALIFIED_NAME||(LA17_3>=INTEGER_KEYWORD && LA17_3<=BOOLEAN_KEYWORD)) ) {
                    alt17=1;
                }
                else if ( (LA17_3==ACTION||(LA17_3>=BLUEPRINT && LA17_3<=ON_CREATE)) ) {
                    alt17=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 17, 3, input);

                    throw nvae;
                }
                }
                break;
            case ACTION:
            case BLUEPRINT:
            case NATIVE:
            case ON_CREATE:
                {
                alt17=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:171:4: assignment_statement
                    {
                    pushFollow(FOLLOW_assignment_statement_in_class_stmnts279);
                    assignment_statement();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:172:4: (modEnum= access_modifier )? method_declaration
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:172:12: (modEnum= access_modifier )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( ((LA16_0>=PUBLIC && LA16_0<=PRIVATE)) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:172:12: modEnum= access_modifier
                            {
                            pushFollow(FOLLOW_access_modifier_in_class_stmnts288);
                            modEnum=access_modifier();

                            state._fsp--;


                            }
                            break;

                    }


                    		if(modEnum == null){
                    			accessModifier = AccessModifierEnum.PUBLIC;
                    		}
                    		else {
                    			accessModifier = (modEnum!=null?modEnum.amEnum:null);
                    		}
                    	
                    pushFollow(FOLLOW_method_declaration_in_class_stmnts296);
                    method_declaration();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "class_stmnts"

    protected static class method_declaration_scope {
        Vector<TypeDescriptor> types;
    }
    protected Stack method_declaration_stack = new Stack();

    public static class method_declaration_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "method_declaration"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:183:1: method_declaration : ( ^( ACTION ID (fp= formal_parameter )* ( RETURNS ad= assignment_declaration )? block[false] END ) | ^( BLUEPRINT ACTION ID (fp= formal_parameter )* ( RETURNS assignment_declaration )? ) | ^( NATIVE ACTION ID (fp= formal_parameter )* ( RETURNS assignment_declaration )? ) | ^( ON_CREATE block[true] END ) );
    public final QuorumTreeWalker.method_declaration_return method_declaration() throws RecognitionException {
        method_declaration_stack.push(new method_declaration_scope());
        QuorumTreeWalker.method_declaration_return retval = new QuorumTreeWalker.method_declaration_return();
        retval.start = input.LT(1);

        CommonTree ID2=null;
        CommonTree END3=null;
        CommonTree END4=null;
        QuorumTreeWalker.formal_parameter_return fp = null;

        QuorumTreeWalker.assignment_declaration_return ad = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:187:2: ( ^( ACTION ID (fp= formal_parameter )* ( RETURNS ad= assignment_declaration )? block[false] END ) | ^( BLUEPRINT ACTION ID (fp= formal_parameter )* ( RETURNS assignment_declaration )? ) | ^( NATIVE ACTION ID (fp= formal_parameter )* ( RETURNS assignment_declaration )? ) | ^( ON_CREATE block[true] END ) )
            int alt24=4;
            switch ( input.LA(1) ) {
            case ACTION:
                {
                alt24=1;
                }
                break;
            case BLUEPRINT:
                {
                alt24=2;
                }
                break;
            case NATIVE:
                {
                alt24=3;
                }
                break;
            case ON_CREATE:
                {
                alt24=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }

            switch (alt24) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:187:4: ^( ACTION ID (fp= formal_parameter )* ( RETURNS ad= assignment_declaration )? block[false] END )
                    {
                    match(input,ACTION,FOLLOW_ACTION_in_method_declaration314); 


                    		((method_declaration_scope)method_declaration_stack.peek()).types = new Vector<TypeDescriptor>();
                    	

                    match(input, Token.DOWN, null); 
                    ID2=(CommonTree)match(input,ID,FOLLOW_ID_in_method_declaration320); 
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:193:5: (fp= formal_parameter )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==FPARAM) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:193:6: fp= formal_parameter
                    	    {
                    	    pushFollow(FOLLOW_formal_parameter_in_method_declaration325);
                    	    fp=formal_parameter();

                    	    state._fsp--;

                    	     ((method_declaration_scope)method_declaration_stack.peek()).types.add((fp).type); 

                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);


                                    String key = MethodDescriptor.autoGenerateKey((ID2!=null?ID2.getText():null), 
                                    	((method_declaration_scope)method_declaration_stack.peek()).types);
                                    MethodDescriptor md = symbol.enterMethod(key);
                                    md.setAccessModifier(accessModifier);
                    		builder.begin(md);

                    	
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:203:3: ( RETURNS ad= assignment_declaration )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==RETURNS) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:203:4: RETURNS ad= assignment_declaration
                            {
                            match(input,RETURNS,FOLLOW_RETURNS_in_method_declaration338); 
                            pushFollow(FOLLOW_assignment_declaration_in_method_declaration342);
                            ad=assignment_declaration();

                            state._fsp--;


                            			if((ad!=null?ad.myType:null) != null) {
                            				MethodDescriptor mdResolve = symbol.getCurrentMethod();	
                            				mdResolve.setReturnType((ad!=null?ad.myType:null));
                            			}
                            		

                            }
                            break;

                    }

                    pushFollow(FOLLOW_block_in_method_declaration348);
                    block(false);

                    state._fsp--;

                    END3=(CommonTree)match(input,END,FOLLOW_END_in_method_declaration351); 

                    		
                    		symbol.addStatementFlagToCurrentFile((END3!=null?END3.getLine():0));
                    		
                    		CompilerError error = symbol.getControlFlow().endMethod();
                    		if(error != null) {
                    			vm.getCompilerErrors().addError(error);
                    		}
                    		
                                    if(symbol.getControlFlow().needsReturnStep() && error == null) { //do this only for void types
                                    	LineInformation location = new LineInformation();
                    	                location.setStartColumn(END3.getCharPositionInLine());
                    	                location.setStartLine((END3!=null?END3.getLine():0));
                    	                location.setFile(getGrammarFileNameNoExtension());
                    			stepFactory.addReturnStep(location, null, null);
                    		}
                    		
                    		builder.endMethod();
                    		//return scope to the class
                    		symbol.popScope();
                    		

                    	

                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:233:6: ^( BLUEPRINT ACTION ID (fp= formal_parameter )* ( RETURNS assignment_declaration )? )
                    {
                    match(input,BLUEPRINT,FOLLOW_BLUEPRINT_in_method_declaration366); 

                    match(input, Token.DOWN, null); 
                    match(input,ACTION,FOLLOW_ACTION_in_method_declaration368); 

                    		((method_declaration_scope)method_declaration_stack.peek()).types = new Vector<TypeDescriptor>();
                    	
                    match(input,ID,FOLLOW_ID_in_method_declaration375); 
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:237:6: (fp= formal_parameter )*
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==FPARAM) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:237:7: fp= formal_parameter
                    	    {
                    	    pushFollow(FOLLOW_formal_parameter_in_method_declaration380);
                    	    fp=formal_parameter();

                    	    state._fsp--;

                    	     ((method_declaration_scope)method_declaration_stack.peek()).types.add((fp).type); 

                    	    }
                    	    break;

                    	default :
                    	    break loop20;
                        }
                    } while (true);

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:238:4: ( RETURNS assignment_declaration )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==RETURNS) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:238:5: RETURNS assignment_declaration
                            {
                            match(input,RETURNS,FOLLOW_RETURNS_in_method_declaration389); 
                            pushFollow(FOLLOW_assignment_declaration_in_method_declaration391);
                            assignment_declaration();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:239:4: ^( NATIVE ACTION ID (fp= formal_parameter )* ( RETURNS assignment_declaration )? )
                    {
                    match(input,NATIVE,FOLLOW_NATIVE_in_method_declaration400); 

                    match(input, Token.DOWN, null); 
                    match(input,ACTION,FOLLOW_ACTION_in_method_declaration402); 

                    		((method_declaration_scope)method_declaration_stack.peek()).types = new Vector<TypeDescriptor>();
                    	
                    match(input,ID,FOLLOW_ID_in_method_declaration409); 
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:243:5: (fp= formal_parameter )*
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==FPARAM) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:243:6: fp= formal_parameter
                    	    {
                    	    pushFollow(FOLLOW_formal_parameter_in_method_declaration414);
                    	    fp=formal_parameter();

                    	    state._fsp--;

                    	     ((method_declaration_scope)method_declaration_stack.peek()).types.add((fp).type); 

                    	    }
                    	    break;

                    	default :
                    	    break loop22;
                        }
                    } while (true);

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:244:4: ( RETURNS assignment_declaration )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==RETURNS) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:244:5: RETURNS assignment_declaration
                            {
                            match(input,RETURNS,FOLLOW_RETURNS_in_method_declaration423); 
                            pushFollow(FOLLOW_assignment_declaration_in_method_declaration425);
                            assignment_declaration();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 

                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:245:4: ^( ON_CREATE block[true] END )
                    {
                    match(input,ON_CREATE,FOLLOW_ON_CREATE_in_method_declaration434); 


                    		MethodDescriptor construct = symbol.enterConstructor();
                                    construct.setAccessModifier(AccessModifierEnum.PRIVATE);
                                    construct.flagMethodAsConstructor();
                    		builder.begin(construct);
                    	

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_block_in_method_declaration441);
                    block(true);

                    state._fsp--;

                    END4=(CommonTree)match(input,END,FOLLOW_END_in_method_declaration444); 

                    		symbol.addStatementFlagToCurrentFile((END4!=null?END4.getLine():0));
                    		
                    		CompilerError error = symbol.getControlFlow().endMethod();
                    		if(error != null) {
                    			vm.getCompilerErrors().addError(error);
                    		}
                    		
                    		if(symbol.getControlFlow().needsReturnStep() && error == null) { //do this only for void types
                                    	LineInformation location = new LineInformation();
                    	                location.setStartColumn(END4.getCharPositionInLine());
                    	                location.setStartLine((END4!=null?END4.getLine():0));
                    	                location.setFile(getGrammarFileNameNoExtension());
                    	                location.setClassName(symbol.getCurrentClass().getStaticKey());
                    	                location.setMethodName(construct.getStaticKey());
                    	                
                    			stepFactory.addReturnStep(location, null, null);
                    		}
                    		
                    		builder.endMethod();
                    		//return scope to the class
                    		symbol.popScope();
                    		

                    	

                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            method_declaration_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "method_declaration"

    public static class qualified_name_return extends TreeRuleReturnScope {
        public QualifiedNameDescriptor type;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "qualified_name"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:281:1: qualified_name returns [QualifiedNameDescriptor type] : ^( QUALIFIED_NAME ids+= ID ( PERIOD ids+= ID )* ) ;
    public final QuorumTreeWalker.qualified_name_return qualified_name() throws RecognitionException {
        QuorumTreeWalker.qualified_name_return retval = new QuorumTreeWalker.qualified_name_return();
        retval.start = input.LT(1);

        CommonTree PERIOD5=null;
        CommonTree ids=null;
        List list_ids=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:282:2: ( ^( QUALIFIED_NAME ids+= ID ( PERIOD ids+= ID )* ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:282:4: ^( QUALIFIED_NAME ids+= ID ( PERIOD ids+= ID )* )
            {
            match(input,QUALIFIED_NAME,FOLLOW_QUALIFIED_NAME_in_qualified_name467); 

            match(input, Token.DOWN, null); 
            ids=(CommonTree)match(input,ID,FOLLOW_ID_in_qualified_name472); 
            if (list_ids==null) list_ids=new ArrayList();
            list_ids.add(ids);

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:282:30: ( PERIOD ids+= ID )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==PERIOD) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:282:31: PERIOD ids+= ID
            	    {
            	    PERIOD5=(CommonTree)match(input,PERIOD,FOLLOW_PERIOD_in_qualified_name475); 
            	    ids=(CommonTree)match(input,ID,FOLLOW_ID_in_qualified_name479); 
            	    if (list_ids==null) list_ids=new ArrayList();
            	    list_ids.add(ids);


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);


            match(input, Token.UP, null); 

            		QualifiedNameDescriptor t = new QualifiedNameDescriptor();
            		t.setLineBegin(((CommonTree)list_ids.get(0)).token.getLine());
            		t.setColumnBegin(((CommonTree)list_ids.get(0)).token.getCharPositionInLine());
            		/*t.setNameFromList(list_ids);
            		*/
            		String name = "";
            		Iterator it = list_ids.iterator();

                    	while(it.hasNext()) {
                        		name += ((CommonTree) it.next()).getText();

                        		if(it.hasNext()) {
                            		name += (PERIOD5!=null?PERIOD5.getText():null);
                       		}

                        	}
                        	t.setName(name);



            		t.setLineEnd(((CommonTree)list_ids.get(list_ids.size() - 1)).token.getLine());
            		t.setColumnEnd(((CommonTree)list_ids.get(list_ids.size() - 1)).token.getCharPositionInLine());
            		retval.type = t;
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "qualified_name"

    public static class block_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "block"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:310:1: block[boolean bool] : ^( STATEMENT_LIST ( statement )* ) ;
    public final QuorumTreeWalker.block_return block(boolean bool) throws RecognitionException {
        QuorumTreeWalker.block_return retval = new QuorumTreeWalker.block_return();
        retval.start = input.LT(1);

        CommonTree STATEMENT_LIST6=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:310:22: ( ^( STATEMENT_LIST ( statement )* ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:310:24: ^( STATEMENT_LIST ( statement )* )
            {
            STATEMENT_LIST6=(CommonTree)match(input,STATEMENT_LIST,FOLLOW_STATEMENT_LIST_in_block498); 


            		if(bool) {
            			//add scope change step for runtime scoping
            			LineInformation location = new LineInformation();
            	                location.setStartColumn(STATEMENT_LIST6.getCharPositionInLine());
            	                location.setStartLine((STATEMENT_LIST6!=null?STATEMENT_LIST6.getLine():0));
            	                location.setFile(getGrammarFileNameNoExtension());
            		}

            	

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:321:3: ( statement )*
                loop26:
                do {
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( ((LA26_0>=SOLO_FUNCTION_CALL && LA26_0<=QUALIFIED_NAME)||LA26_0==ID||(LA26_0>=PUBLIC && LA26_0<=PRIVATE)||(LA26_0>=PARENT && LA26_0<=ME)||(LA26_0>=ALERT && LA26_0<=CHECK)||(LA26_0>=PRINT && LA26_0<=RETURN)||(LA26_0>=INTEGER_KEYWORD && LA26_0<=BOOLEAN_KEYWORD)||LA26_0==IF||LA26_0==REPEAT) ) {
                        alt26=1;
                    }


                    switch (alt26) {
                	case 1 :
                	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:321:3: statement
                	    {
                	    pushFollow(FOLLOW_statement_in_block505);
                	    statement();

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop26;
                    }
                } while (true);


                		if(bool) {
                			//add scope change step for runtime scoping
                			LineInformation location2 = new LineInformation();
                	                location2.setStartColumn(STATEMENT_LIST6.getCharPositionInLine());
                	                location2.setStartLine((STATEMENT_LIST6!=null?STATEMENT_LIST6.getLine():0));
                	                location2.setFile(getGrammarFileNameNoExtension());
                		}
                	

                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "block"

    public static class statement_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:332:1: statement : ( solo_method_call | if_statement | assignment_statement | loop_statement | return_statement | print_statement | speak_statement | check_statement | alert_statement );
    public final QuorumTreeWalker.statement_return statement() throws RecognitionException {
        QuorumTreeWalker.statement_return retval = new QuorumTreeWalker.statement_return();
        retval.start = input.LT(1);

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:332:10: ( solo_method_call | if_statement | assignment_statement | loop_statement | return_statement | print_statement | speak_statement | check_statement | alert_statement )
            int alt27=9;
            switch ( input.LA(1) ) {
            case SOLO_FUNCTION_CALL:
            case SOLO_FUNCTION_CALL_PARENT:
            case SOLO_FUNCTION_CALL_THIS:
                {
                alt27=1;
                }
                break;
            case IF:
                {
                alt27=2;
                }
                break;
            case QUALIFIED_NAME:
            case ID:
            case PUBLIC:
            case PRIVATE:
            case PARENT:
            case ME:
            case INTEGER_KEYWORD:
            case NUMBER_KEYWORD:
            case TEXT:
            case BOOLEAN_KEYWORD:
                {
                alt27=3;
                }
                break;
            case REPEAT:
                {
                alt27=4;
                }
                break;
            case RETURN:
                {
                alt27=5;
                }
                break;
            case PRINT:
                {
                alt27=6;
                }
                break;
            case SAY:
                {
                alt27=7;
                }
                break;
            case CHECK:
                {
                alt27=8;
                }
                break;
            case ALERT:
                {
                alt27=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }

            switch (alt27) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:334:3: solo_method_call
                    {
                    pushFollow(FOLLOW_solo_method_call_in_statement522);
                    solo_method_call();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:335:4: if_statement
                    {
                    pushFollow(FOLLOW_if_statement_in_statement527);
                    if_statement();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:336:4: assignment_statement
                    {
                    pushFollow(FOLLOW_assignment_statement_in_statement532);
                    assignment_statement();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:337:4: loop_statement
                    {
                    pushFollow(FOLLOW_loop_statement_in_statement537);
                    loop_statement();

                    state._fsp--;


                    }
                    break;
                case 5 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:338:4: return_statement
                    {
                    pushFollow(FOLLOW_return_statement_in_statement542);
                    return_statement();

                    state._fsp--;


                    }
                    break;
                case 6 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:339:4: print_statement
                    {
                    pushFollow(FOLLOW_print_statement_in_statement547);
                    print_statement();

                    state._fsp--;


                    }
                    break;
                case 7 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:340:4: speak_statement
                    {
                    pushFollow(FOLLOW_speak_statement_in_statement552);
                    speak_statement();

                    state._fsp--;


                    }
                    break;
                case 8 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:341:4: check_statement
                    {
                    pushFollow(FOLLOW_check_statement_in_statement557);
                    check_statement();

                    state._fsp--;


                    }
                    break;
                case 9 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:342:4: alert_statement
                    {
                    pushFollow(FOLLOW_alert_statement_in_statement562);
                    alert_statement();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "statement"

    public static class solo_method_call_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "solo_method_call"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:345:1: solo_method_call : ( ^( SOLO_FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN ) | ^( SOLO_FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN ) | ^( SOLO_FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN ) );
    public final QuorumTreeWalker.solo_method_call_return solo_method_call() throws RecognitionException {
        QuorumTreeWalker.solo_method_call_return retval = new QuorumTreeWalker.solo_method_call_return();
        retval.start = input.LT(1);

        CommonTree ID8=null;
        CommonTree ID10=null;
        CommonTree ID12=null;
        QuorumTreeWalker.expression_return e = null;

        QuorumTreeWalker.qualified_name_return qualified_name7 = null;

        QuorumTreeWalker.qualified_name_return qualified_name9 = null;

        QuorumTreeWalker.qualified_name_return qualified_name11 = null;



        	Vector<ExpressionValue> values = new Vector<ExpressionValue>();
        	Vector<ExecutionStep> steps = new Vector<ExecutionStep>();
        	Vector<Integer> registers = new Vector<Integer>();
        	Vector<String> types = new Vector<String>();
        	Vector<TypeDescriptor> argumentTypes = new Vector<TypeDescriptor>();

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:353:2: ( ^( SOLO_FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN ) | ^( SOLO_FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN ) | ^( SOLO_FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN ) )
            int alt36=3;
            switch ( input.LA(1) ) {
            case SOLO_FUNCTION_CALL:
                {
                alt36=1;
                }
                break;
            case SOLO_FUNCTION_CALL_PARENT:
                {
                alt36=2;
                }
                break;
            case SOLO_FUNCTION_CALL_THIS:
                {
                alt36=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }

            switch (alt36) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:353:4: ^( SOLO_FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN )
                    {

                    			inCallStep = true;
                    			builder.addStepLabel(OpcodeType.ROOT_EXPRESSION);
                    		
                    match(input,SOLO_FUNCTION_CALL,FOLLOW_SOLO_FUNCTION_CALL_in_solo_method_call583); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_qualified_name_in_solo_method_call585);
                    qualified_name7=qualified_name();

                    state._fsp--;

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:357:39: ( COLON ID )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==COLON) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:357:40: COLON ID
                            {
                            match(input,COLON,FOLLOW_COLON_in_solo_method_call588); 
                            ID8=(CommonTree)match(input,ID,FOLLOW_ID_in_solo_method_call590); 

                            }
                            break;

                    }

                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_solo_method_call594); 
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:357:62: (e= expression ( COMMA e= expression )* )?
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( ((LA30_0>=FUNCTION_CALL && LA30_0<=FUNCTION_CALL_THIS)||LA30_0==UNARY_NOT||(LA30_0>=QUALIFIED_SOLO_EXPRESSION && LA30_0<=QUALIFIED_SOLO_EXPRESSION_SELECTOR)||LA30_0==INHERITS||LA30_0==ME||LA30_0==OR||(LA30_0>=LESS && LA30_0<=GREATER)||LA30_0==EQUALITY||(LA30_0>=AND && LA30_0<=MODULO)||(LA30_0>=CAST && LA30_0<=INPUT)) ) {
                        alt30=1;
                    }
                    switch (alt30) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:358:3: e= expression ( COMMA e= expression )*
                            {
                            pushFollow(FOLLOW_expression_in_solo_method_call604);
                            e=expression();

                            state._fsp--;


                            			values.add((e!=null?e.eval:null));
                            			steps.add((e!=null?e.step:null));
                            			registers.add((e!=null?e.eval:null).getRegister());
                            			types.add((e!=null?e.eval:null).getType().getStaticKey());
                                            	argumentTypes.add((e!=null?e.eval:null).getType());
                                            	inCallStep = false;
                            		
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:367:3: ( COMMA e= expression )*
                            loop29:
                            do {
                                int alt29=2;
                                int LA29_0 = input.LA(1);

                                if ( (LA29_0==COMMA) ) {
                                    alt29=1;
                                }


                                switch (alt29) {
                            	case 1 :
                            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:367:4: COMMA e= expression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_solo_method_call614); 
                            	    pushFollow(FOLLOW_expression_in_solo_method_call620);
                            	    e=expression();

                            	    state._fsp--;


                            	    			values.add((e!=null?e.eval:null));
                            	    			steps.add((e!=null?e.step:null));
                            	    			registers.add((e!=null?e.eval:null).getRegister());
                            	    			types.add((e!=null?e.eval:null).getType().getStaticKey());
                            	                    	argumentTypes.add((e!=null?e.eval:null).getType());
                            	    		

                            	    }
                            	    break;

                            	default :
                            	    break loop29;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_solo_method_call634); 

                    match(input, Token.UP, null); 

                    		LineInformation location = new LineInformation();
                                    location.setEndColumn((qualified_name7!=null?qualified_name7.type:null).getColumnEnd());
                                    location.setEndLine((qualified_name7!=null?qualified_name7.type:null).getLineEnd());
                                    location.setStartColumn((qualified_name7!=null?qualified_name7.type:null).getColumnBegin());
                                    location.setStartLine((qualified_name7!=null?qualified_name7.type:null).getLineBegin());
                                    location.setFile(symbol.getCurrentClass().getFile().getStaticKey());
                                    location.setClassName(symbol.getCurrentClass().getStaticKey());
                                    location.setMethodName(symbol.getCurrentMethod().getStaticKey());
                                    
                                    
                                    symbol.addStatementFlagToCurrentFile((qualified_name7!=null?qualified_name7.type:null).getLineBegin());
                                    
                                    String key = "";
                                    String myMethodName = "";
                                    if(ID8 == null) {
                                    	key = MethodDescriptor.generateKey((qualified_name7!=null?qualified_name7.type:null).getStaticKey(), types);
                                    	myMethodName = (qualified_name7!=null?qualified_name7.type:null).getStaticKey();
                    		}
                    		else {
                    			key = MethodDescriptor.generateKey((ID8!=null?ID8.getText():null), types);
                    			myMethodName = (ID8!=null?ID8.getText():null);
                    		}
                    		CallInfo info = new CallInfo();
                    		info.register = temp;
                    		info.location = location;
                    		info.argumentRegisters = registers;
                    		info.argumentSteps = steps;
                    		info.variable = (qualified_name7!=null?qualified_name7.type:null);
                    		info.argumentTypes = argumentTypes;
                    		info.methodName = myMethodName;
                    		info.isObjectCall = (ID8 != null);
                    		info.isSoloMethod = true;
                    		
                    		ResultTuple result =  stepFactory.addCallStep(info);
                    		builder.addStepLabel(OpcodeType.SOLO_METHOD_CALL);		
                    		
                    		temp = result.getNextRegister();
                    		

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:415:4: ^( SOLO_FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN )
                    {
                    match(input,SOLO_FUNCTION_CALL_PARENT,FOLLOW_SOLO_FUNCTION_CALL_PARENT_in_solo_method_call646); 

                    match(input, Token.DOWN, null); 
                    match(input,PARENT,FOLLOW_PARENT_in_solo_method_call648); 
                    match(input,COLON,FOLLOW_COLON_in_solo_method_call650); 
                    pushFollow(FOLLOW_qualified_name_in_solo_method_call652);
                    qualified_name9=qualified_name();

                    state._fsp--;

                    match(input,COLON,FOLLOW_COLON_in_solo_method_call654); 
                    ID10=(CommonTree)match(input,ID,FOLLOW_ID_in_solo_method_call656); 
                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_solo_method_call658); 
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:416:3: (e= expression ( COMMA e= expression )* )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( ((LA32_0>=FUNCTION_CALL && LA32_0<=FUNCTION_CALL_THIS)||LA32_0==UNARY_NOT||(LA32_0>=QUALIFIED_SOLO_EXPRESSION && LA32_0<=QUALIFIED_SOLO_EXPRESSION_SELECTOR)||LA32_0==INHERITS||LA32_0==ME||LA32_0==OR||(LA32_0>=LESS && LA32_0<=GREATER)||LA32_0==EQUALITY||(LA32_0>=AND && LA32_0<=MODULO)||(LA32_0>=CAST && LA32_0<=INPUT)) ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:416:4: e= expression ( COMMA e= expression )*
                            {
                            pushFollow(FOLLOW_expression_in_solo_method_call668);
                            e=expression();

                            state._fsp--;


                            			values.add((e!=null?e.eval:null));
                            			steps.add((e!=null?e.step:null));
                            			registers.add((e!=null?e.eval:null).getRegister());
                            			types.add((e!=null?e.eval:null).getType().getStaticKey());
                                            	argumentTypes.add((e!=null?e.eval:null).getType());
                                            	inCallStep = false;
                            		
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:424:5: ( COMMA e= expression )*
                            loop31:
                            do {
                                int alt31=2;
                                int LA31_0 = input.LA(1);

                                if ( (LA31_0==COMMA) ) {
                                    alt31=1;
                                }


                                switch (alt31) {
                            	case 1 :
                            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:424:6: COMMA e= expression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_solo_method_call675); 
                            	    pushFollow(FOLLOW_expression_in_solo_method_call681);
                            	    e=expression();

                            	    state._fsp--;


                            	    			values.add((e!=null?e.eval:null));
                            	    			steps.add((e!=null?e.step:null));
                            	    			registers.add((e!=null?e.eval:null).getRegister());
                            	    			types.add((e!=null?e.eval:null).getType().getStaticKey());
                            	                    	argumentTypes.add((e!=null?e.eval:null).getType());
                            	    		

                            	    }
                            	    break;

                            	default :
                            	    break loop31;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_solo_method_call691); 

                    match(input, Token.UP, null); 

                    		LineInformation location = new LineInformation();
                                    location.setEndColumn((qualified_name9!=null?qualified_name9.type:null).getColumnEnd());
                                    location.setEndLine((qualified_name9!=null?qualified_name9.type:null).getLineEnd());
                                    location.setStartColumn((qualified_name9!=null?qualified_name9.type:null).getColumnBegin());
                                    location.setStartLine((qualified_name9!=null?qualified_name9.type:null).getLineBegin());
                                    location.setFile(symbol.getCurrentClass().getFile().getStaticKey());
                                    location.setClassName(symbol.getCurrentClass().getStaticKey());
                                    location.setMethodName(symbol.getCurrentMethod().getStaticKey());
                                    
                                    symbol.addStatementFlagToCurrentFile((qualified_name9!=null?qualified_name9.type:null).getLineBegin());
                                    
                                    String key = "";
                                    String myMethodName = "";
                                    if(ID10 == null) {
                                    	key = MethodDescriptor.generateKey((qualified_name9!=null?qualified_name9.type:null).getStaticKey(), types);
                                    	myMethodName = (qualified_name9!=null?qualified_name9.type:null).getStaticKey();
                    		}
                    		else {
                    			key = MethodDescriptor.generateKey((ID10!=null?ID10.getText():null), types);
                    			myMethodName = (ID10!=null?ID10.getText():null);
                    		}
                    		CallInfo info = new CallInfo();
                    		info.register = temp;
                    		info.location = location;
                    		info.argumentRegisters = registers;
                    		info.argumentSteps = steps;
                    		info.variable = (qualified_name9!=null?qualified_name9.type:null);
                    		info.locatedIn = (qualified_name9!=null?qualified_name9.type:null).getStaticKey();
                    		info.argumentTypes = argumentTypes;
                    		info.methodName = myMethodName;
                    		info.isObjectCall = (ID10 != null);
                    		info.isSoloMethod = true;
                    		
                    		ResultTuple result =  stepFactory.addParentCallStep(info);
                    		builder.addStepLabel(OpcodeType.SOLO_METHOD_CALL);
                    		
                    		temp = result.getNextRegister();
                    		inCallStep = false;
                    		

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:472:4: ^( SOLO_FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN )
                    {
                    match(input,SOLO_FUNCTION_CALL_THIS,FOLLOW_SOLO_FUNCTION_CALL_THIS_in_solo_method_call702); 

                    match(input, Token.DOWN, null); 
                    match(input,ME,FOLLOW_ME_in_solo_method_call704); 
                    match(input,COLON,FOLLOW_COLON_in_solo_method_call706); 
                    pushFollow(FOLLOW_qualified_name_in_solo_method_call708);
                    qualified_name11=qualified_name();

                    state._fsp--;

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:472:54: ( COLON ID )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==COLON) ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:472:55: COLON ID
                            {
                            match(input,COLON,FOLLOW_COLON_in_solo_method_call711); 
                            ID12=(CommonTree)match(input,ID,FOLLOW_ID_in_solo_method_call713); 

                            }
                            break;

                    }

                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_solo_method_call717); 
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:473:3: (e= expression ( COMMA e= expression )* )?
                    int alt35=2;
                    int LA35_0 = input.LA(1);

                    if ( ((LA35_0>=FUNCTION_CALL && LA35_0<=FUNCTION_CALL_THIS)||LA35_0==UNARY_NOT||(LA35_0>=QUALIFIED_SOLO_EXPRESSION && LA35_0<=QUALIFIED_SOLO_EXPRESSION_SELECTOR)||LA35_0==INHERITS||LA35_0==ME||LA35_0==OR||(LA35_0>=LESS && LA35_0<=GREATER)||LA35_0==EQUALITY||(LA35_0>=AND && LA35_0<=MODULO)||(LA35_0>=CAST && LA35_0<=INPUT)) ) {
                        alt35=1;
                    }
                    switch (alt35) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:473:4: e= expression ( COMMA e= expression )*
                            {
                            pushFollow(FOLLOW_expression_in_solo_method_call727);
                            e=expression();

                            state._fsp--;


                            			values.add((e!=null?e.eval:null));
                            			steps.add((e!=null?e.step:null));
                            			registers.add((e!=null?e.eval:null).getRegister());
                            			types.add((e!=null?e.eval:null).getType().getStaticKey());
                                            	argumentTypes.add((e!=null?e.eval:null).getType());
                                            	inCallStep = false;
                            		
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:481:4: ( COMMA e= expression )*
                            loop34:
                            do {
                                int alt34=2;
                                int LA34_0 = input.LA(1);

                                if ( (LA34_0==COMMA) ) {
                                    alt34=1;
                                }


                                switch (alt34) {
                            	case 1 :
                            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:481:5: COMMA e= expression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_solo_method_call734); 
                            	    pushFollow(FOLLOW_expression_in_solo_method_call740);
                            	    e=expression();

                            	    state._fsp--;


                            	    			values.add((e!=null?e.eval:null));
                            	    			steps.add((e!=null?e.step:null));
                            	    			registers.add((e!=null?e.eval:null).getRegister());
                            	    			types.add((e!=null?e.eval:null).getType().getStaticKey());
                            	                    	argumentTypes.add((e!=null?e.eval:null).getType());
                            	    		

                            	    }
                            	    break;

                            	default :
                            	    break loop34;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_solo_method_call750); 

                    match(input, Token.UP, null); 

                    		LineInformation location = new LineInformation();
                                    location.setEndColumn((qualified_name11!=null?qualified_name11.type:null).getColumnEnd());
                                    location.setEndLine((qualified_name11!=null?qualified_name11.type:null).getLineEnd());
                                    location.setStartColumn((qualified_name11!=null?qualified_name11.type:null).getColumnBegin());
                                    location.setStartLine((qualified_name11!=null?qualified_name11.type:null).getLineBegin());
                                    location.setFile(symbol.getCurrentClass().getFile().getStaticKey());
                                    location.setClassName(symbol.getCurrentClass().getStaticKey());
                                    location.setMethodName(symbol.getCurrentMethod().getStaticKey());
                                    
                                    symbol.addStatementFlagToCurrentFile((qualified_name11!=null?qualified_name11.type:null).getLineBegin());
                                    
                                    String key = "";
                                    String myMethodName = "";
                                    if(ID12 == null) {
                                    	key = MethodDescriptor.generateKey((qualified_name11!=null?qualified_name11.type:null).getStaticKey(), types);
                                    	myMethodName = (qualified_name11!=null?qualified_name11.type:null).getStaticKey();
                    		}
                    		else {
                    			key = MethodDescriptor.generateKey((ID12!=null?ID12.getText():null), types);
                    			myMethodName = (ID12!=null?ID12.getText():null);
                    		}
                    		CallInfo info = new CallInfo();
                    		info.register = temp;
                    		info.location = location;
                    		info.argumentRegisters = registers;
                    		info.argumentSteps = steps;
                    		info.variable = (qualified_name11!=null?qualified_name11.type:null);
                    		info.argumentTypes = argumentTypes;
                    		info.methodName = myMethodName;
                    		info.isObjectCall = (ID12 != null);
                    		info.isSoloMethod = true;
                    		
                    		ResultTuple result =  stepFactory.addCallStep(info);
                    		builder.addStepLabel(OpcodeType.SOLO_METHOD_CALL);
                    		temp = result.getNextRegister();
                    		

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "solo_method_call"

    protected static class alert_statement_scope {
        ErrorTypeDescriptor errorType;
        ExpressionValue errorValue;
        ExecutionStep errorStep;
    }
    protected Stack alert_statement_stack = new Stack();

    public static class alert_statement_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "alert_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:527:1: alert_statement : ^( ALERT LEFT_PAREN ex= expression RIGHT_PAREN ) ;
    public final QuorumTreeWalker.alert_statement_return alert_statement() throws RecognitionException {
        alert_statement_stack.push(new alert_statement_scope());
        QuorumTreeWalker.alert_statement_return retval = new QuorumTreeWalker.alert_statement_return();
        retval.start = input.LT(1);

        CommonTree LEFT_PAREN13=null;
        QuorumTreeWalker.expression_return ex = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:533:2: ( ^( ALERT LEFT_PAREN ex= expression RIGHT_PAREN ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:533:4: ^( ALERT LEFT_PAREN ex= expression RIGHT_PAREN )
            {
            match(input,ALERT,FOLLOW_ALERT_in_alert_statement770); 


            		((alert_statement_scope)alert_statement_stack.peek()).errorValue = new ExpressionValue();
            		((alert_statement_scope)alert_statement_stack.peek()).errorStep = null;
            	

            match(input, Token.DOWN, null); 
            LEFT_PAREN13=(CommonTree)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_alert_statement778); 
            pushFollow(FOLLOW_expression_in_alert_statement782);
            ex=expression();

            state._fsp--;


            		ErrorTypeDescriptor t = new ErrorTypeDescriptor();

            		if(!(ex!=null?ex.eval:null).getType().getName().equals(TypeDescriptor.TEXT)){
            			
            			ClassDescriptor cd = symbol.findFullyQualifiedClass((ex!=null?ex.eval:null).getType().getName());
            			
            		
            			if(cd.getStaticKey().equals(ErrorTypeDescriptor.ERROR) || cd.getParent(ErrorTypeDescriptor.ERROR) != null){
            		        	t.setName(cd.getStaticKey());
            		        	t.setType(cd.getType());
            		        }else{
            		                CompilerError error = new CompilerError();
            		          	error.setLineNumber(cd.getLineBegin());
            		          	error.setError("Class " + cd.getStaticKey() + " is not an error type." +
            		          				cd.getStaticKey() + " must inherit from class Error to be an error type");
            		          	error.setColumn(LEFT_PAREN13.getCharPositionInLine());
            		          	error.setErrorType(ErrorType.INVALID_ERROR);
            		          	error.setFile(getGrammarFileNameNoExtension());
            		          	vm.getCompilerErrors().addError(error);
            		        }
            	        }else{
            	        	t = null;
            	        }

            		((alert_statement_scope)alert_statement_stack.peek()).errorValue = (ex!=null?ex.eval:null);
            		((alert_statement_scope)alert_statement_stack.peek()).errorStep = (ex!=null?ex.step:null);
            		((alert_statement_scope)alert_statement_stack.peek()).errorType = t;
            	
            match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_alert_statement790); 

            		LineInformation location = new LineInformation();
                            
            		location.setEndColumn(((alert_statement_scope)alert_statement_stack.peek()).errorStep.getEndColumn());
                            location.setEndLine(((alert_statement_scope)alert_statement_stack.peek()).errorStep.getEndLine());
                            location.setStartColumn(((alert_statement_scope)alert_statement_stack.peek()).errorStep.getBeginColumn());
                            location.setStartLine(((alert_statement_scope)alert_statement_stack.peek()).errorStep.getBeginLine());
                            location.setFile(symbol.getCurrentClass().getFile().getStaticKey());
                            location.setClassName(symbol.getCurrentClass().getStaticKey());
                            location.setMethodName(symbol.getCurrentMethod().getStaticKey());
                            
                            
            		stepFactory.addAlertStep(location, ((alert_statement_scope)alert_statement_stack.peek()).errorType, ((alert_statement_scope)alert_statement_stack.peek()).errorValue, ((alert_statement_scope)alert_statement_stack.peek()).errorStep);
            		
            		symbol.addStatementFlagToCurrentFile(location.getStartLine());
            		
            	

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            alert_statement_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "alert_statement"

    protected static class check_statement_scope {
        ExceptionInfo info;
        int detect_counter;
        int tempLabelCounter;
        boolean has_always;
        LineInformation location;
    }
    protected Stack check_statement_stack = new Stack();

    public static class check_statement_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "check_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:590:1: check_statement : check= CHECK block[true] check_end= END ( (detect_start= DETECT det_param= detect_parameter block[true] detect_end= END )+ (always= ALWAYS block[true] END )? | ALWAYS block[true] END ) ;
    public final QuorumTreeWalker.check_statement_return check_statement() throws RecognitionException {
        check_statement_stack.push(new check_statement_scope());
        QuorumTreeWalker.check_statement_return retval = new QuorumTreeWalker.check_statement_return();
        retval.start = input.LT(1);

        CommonTree check=null;
        CommonTree check_end=null;
        CommonTree detect_start=null;
        CommonTree detect_end=null;
        CommonTree always=null;
        QuorumTreeWalker.detect_parameter_return det_param = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:598:2: (check= CHECK block[true] check_end= END ( (detect_start= DETECT det_param= detect_parameter block[true] detect_end= END )+ (always= ALWAYS block[true] END )? | ALWAYS block[true] END ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:599:2: check= CHECK block[true] check_end= END ( (detect_start= DETECT det_param= detect_parameter block[true] detect_end= END )+ (always= ALWAYS block[true] END )? | ALWAYS block[true] END )
            {
            	
            		((check_statement_scope)check_statement_stack.peek()).info = new ExceptionInfo();
            		((check_statement_scope)check_statement_stack.peek()).detect_counter = 0;
            		((check_statement_scope)check_statement_stack.peek()).has_always = false;
            		
            		sub_counter++;
            		if(sub_counter > 1){labelCounter++;}
            		((check_statement_scope)check_statement_stack.peek()).tempLabelCounter = labelCounter;
            		((check_statement_scope)check_statement_stack.peek()).info.alwaysStartLabel = builder.getCurrentClass().getStaticKey() + "_" + ((check_statement_scope)check_statement_stack.peek()).info.ALWAYS + ((check_statement_scope)check_statement_stack.peek()).tempLabelCounter + ((check_statement_scope)check_statement_stack.peek()).info.START;
            	
            check=(CommonTree)match(input,CHECK,FOLLOW_CHECK_in_check_statement822); 

            		((check_statement_scope)check_statement_stack.peek()).info.location = new LineInformation(
            			check.getLine(),
            			check.getLine(),
            			check.getCharPositionInLine(),
            			(check!=null?check.getText():null).length() + check.getCharPositionInLine());
            		((check_statement_scope)check_statement_stack.peek()).info.checkStartLabel = builder.getCurrentClass().getStaticKey() + "_" + (check!=null?check.getText():null) + ((check_statement_scope)check_statement_stack.peek()).tempLabelCounter + ((check_statement_scope)check_statement_stack.peek()).info.START;
            		stepFactory.startCheck(((check_statement_scope)check_statement_stack.peek()).info);
            	
            pushFollow(FOLLOW_block_in_check_statement828);
            block(true);

            state._fsp--;


            		((check_statement_scope)check_statement_stack.peek()).info.checkJump.setBeginColumn(check.getCharPositionInLine());
            		((check_statement_scope)check_statement_stack.peek()).info.checkJump.setEndColumn(check.getCharPositionInLine() + ((check!=null?check.getText():null).length()));
                            ((check_statement_scope)check_statement_stack.peek()).info.checkJump.setEndLine(check.getLine());
            		stepFactory.addCheckEndJumpStep(((check_statement_scope)check_statement_stack.peek()).info);
            	
            check_end=(CommonTree)match(input,END,FOLLOW_END_in_check_statement840); 

            		((check_statement_scope)check_statement_stack.peek()).info.checkJump.setBeginLine(check_end.getLine());
            		stepFactory.endCheck(((check_statement_scope)check_statement_stack.peek()).info);
            	
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:630:2: ( (detect_start= DETECT det_param= detect_parameter block[true] detect_end= END )+ (always= ALWAYS block[true] END )? | ALWAYS block[true] END )
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==DETECT) ) {
                alt39=1;
            }
            else if ( (LA39_0==ALWAYS) ) {
                alt39=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }
            switch (alt39) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:631:7: (detect_start= DETECT det_param= detect_parameter block[true] detect_end= END )+ (always= ALWAYS block[true] END )?
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:631:7: (detect_start= DETECT det_param= detect_parameter block[true] detect_end= END )+
                    int cnt37=0;
                    loop37:
                    do {
                        int alt37=2;
                        int LA37_0 = input.LA(1);

                        if ( (LA37_0==DETECT) ) {
                            alt37=1;
                        }


                        switch (alt37) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:631:8: detect_start= DETECT det_param= detect_parameter block[true] detect_end= END
                    	    {
                    	    detect_start=(CommonTree)match(input,DETECT,FOLLOW_DETECT_in_check_statement860); 

                    	    	    		((check_statement_scope)check_statement_stack.peek()).info.addDetectLabel((detect_start!=null?detect_start.getText():null) + ((check_statement_scope)check_statement_stack.peek()).detect_counter
                    	    	    			 + "_" + ((check_statement_scope)check_statement_stack.peek()).tempLabelCounter + ((check_statement_scope)check_statement_stack.peek()).info.START);
                    	    	    			 symbol.addStatementFlagToCurrentFile(detect_start.getLine());
                    	    	    		
                    	    	    	
                    	    pushFollow(FOLLOW_detect_parameter_in_check_statement887);
                    	    det_param=detect_parameter();

                    	    state._fsp--;


                    	    	    		Iterator<ErrorTypeDescriptor> detectParamIt = det_param.exceptionTypeList.iterator();
                    	    	    		while(detectParamIt.hasNext()){
                    	    		    		DetectParameter d = new DetectParameter();
                    	    		    		TypeDescriptor t = new TypeDescriptor();
                    	    		    		
                    	    		    		d.errorType = detectParamIt.next();
                    	    		    		t.setName(d.errorType.getName());
                    	    		    		d.setType(t);
                    	    		    		d.setName((det_param!=null?det_param.name:null));
                    	    		    		((check_statement_scope)check_statement_stack.peek()).info.addDetectParameter(d);
                    	    	    		}
                    	    	    		stepFactory.startDetect(((check_statement_scope)check_statement_stack.peek()).info, ((check_statement_scope)check_statement_stack.peek()).detect_counter);
                    	    	    	
                    	    pushFollow(FOLLOW_block_in_check_statement911);
                    	    block(true);

                    	    state._fsp--;


                    	    	    		JumpStep detectJump = new JumpStep();
                    	    		    	detectJump.setBeginColumn(detect_start.getCharPositionInLine());
                    	    			detectJump.setEndColumn(detect_start.getCharPositionInLine() + ((detect_start!=null?detect_start.getText():null).length()));
                    	    	                detectJump.setEndLine(detect_start.getLine());
                    	    	                ((check_statement_scope)check_statement_stack.peek()).info.detectJumps.add(detectJump);
                    	    			stepFactory.addDetectEndJumpStep(((check_statement_scope)check_statement_stack.peek()).info, detectJump);
                    	    	    	
                    	    detect_end=(CommonTree)match(input,END,FOLLOW_END_in_check_statement933); 

                    	    	    		detectJump.setBeginLine(detect_end.getLine());
                    	    	    		stepFactory.endDetect(((check_statement_scope)check_statement_stack.peek()).info, ((check_statement_scope)check_statement_stack.peek()).detect_counter);
                    	    	    		((check_statement_scope)check_statement_stack.peek()).detect_counter = ((check_statement_scope)check_statement_stack.peek()).detect_counter + 1;
                    	    	    	

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt37 >= 1 ) break loop37;
                                EarlyExitException eee =
                                    new EarlyExitException(37, input);
                                throw eee;
                        }
                        cnt37++;
                    } while (true);

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:671:7: (always= ALWAYS block[true] END )?
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0==ALWAYS) ) {
                        alt38=1;
                    }
                    switch (alt38) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:671:8: always= ALWAYS block[true] END
                            {
                            always=(CommonTree)match(input,ALWAYS,FOLLOW_ALWAYS_in_check_statement964); 

                            	    		((check_statement_scope)check_statement_stack.peek()).has_always = true;
                            	    		((check_statement_scope)check_statement_stack.peek()).info.hasAlways = true;
                            	    		stepFactory.startAlways(((check_statement_scope)check_statement_stack.peek()).info);
                            	    	
                            pushFollow(FOLLOW_block_in_check_statement980);
                            block(true);

                            state._fsp--;

                            match(input,END,FOLLOW_END_in_check_statement997); 

                            	    		stepFactory.endAlways(((check_statement_scope)check_statement_stack.peek()).info);
                            	    	

                            }
                            break;

                    }


                    	    		if (((check_statement_scope)check_statement_stack.peek()).has_always == false) {
                    	    			((check_statement_scope)check_statement_stack.peek()).info.hasAlways = false;
                    	    			stepFactory.startAlways(((check_statement_scope)check_statement_stack.peek()).info);
                    	    			stepFactory.endAlways(((check_statement_scope)check_statement_stack.peek()).info);
                    	    		}
                    	    	

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:692:7: ALWAYS block[true] END
                    {
                    match(input,ALWAYS,FOLLOW_ALWAYS_in_check_statement1041); 

                    	    		((check_statement_scope)check_statement_stack.peek()).has_always = true;
                    	    		((check_statement_scope)check_statement_stack.peek()).info.hasAlways = true;
                    	    		stepFactory.startAlways(((check_statement_scope)check_statement_stack.peek()).info);
                    	    	
                    pushFollow(FOLLOW_block_in_check_statement1064);
                    block(true);

                    state._fsp--;

                    match(input,END,FOLLOW_END_in_check_statement1067); 

                    	    		stepFactory.endAlways(((check_statement_scope)check_statement_stack.peek()).info);
                    	    	

                    }
                    break;

            }


            		sub_counter--;
            		((check_statement_scope)check_statement_stack.peek()).tempLabelCounter--;
            		if(sub_counter == 0){labelCounter++;}
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            check_statement_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "check_statement"

    protected static class detect_parameter_scope {
        ArrayList<ErrorTypeDescriptor> exceptionList;
    }
    protected Stack detect_parameter_stack = new Stack();

    public static class detect_parameter_return extends TreeRuleReturnScope {
        public String name;
        public ArrayList<ErrorTypeDescriptor> exceptionTypeList;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "detect_parameter"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:712:1: detect_parameter returns [String name, ArrayList<ErrorTypeDescriptor> exceptionTypeList] : ^(id= ID ( OF_TYPE qn= qualified_name ( OR qn= qualified_name )* )? ) ;
    public final QuorumTreeWalker.detect_parameter_return detect_parameter() throws RecognitionException {
        detect_parameter_stack.push(new detect_parameter_scope());
        QuorumTreeWalker.detect_parameter_return retval = new QuorumTreeWalker.detect_parameter_return();
        retval.start = input.LT(1);

        CommonTree id=null;
        QuorumTreeWalker.qualified_name_return qn = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:716:2: ( ^(id= ID ( OF_TYPE qn= qualified_name ( OR qn= qualified_name )* )? ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:716:4: ^(id= ID ( OF_TYPE qn= qualified_name ( OR qn= qualified_name )* )? )
            {
            id=(CommonTree)match(input,ID,FOLLOW_ID_in_detect_parameter1110); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:717:2: ( OF_TYPE qn= qualified_name ( OR qn= qualified_name )* )?
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==OF_TYPE) ) {
                    alt41=1;
                }
                switch (alt41) {
                    case 1 :
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:717:3: OF_TYPE qn= qualified_name ( OR qn= qualified_name )*
                        {
                        match(input,OF_TYPE,FOLLOW_OF_TYPE_in_detect_parameter1115); 

                        		((detect_parameter_scope)detect_parameter_stack.peek()).exceptionList = new ArrayList<ErrorTypeDescriptor>();
                        	
                        pushFollow(FOLLOW_qualified_name_in_detect_parameter1124);
                        qn=qualified_name();

                        state._fsp--;


                        		ErrorTypeDescriptor t = new ErrorTypeDescriptor();

                        		ClassDescriptor cd = symbol.findClassDescriptorFromCurrentClass((qn!=null?qn.type:null).getStaticKey());

                        		if(cd == null) { //this is a compiler error
                        			cd = symbol.getCurrentClass();
                        			GenericDescriptor gd = cd.getTemplateVariable((qn!=null?qn.type:null).getStaticKey());
                        			if(gd != null){
                        				t.setName(gd.getType().getStaticKey());
                        			}else{
                        				CompilerError error = new CompilerError();
                        				error.setLineNumber((qn!=null?qn.type:null).getLineBegin());
                        				error.setError("Class " + (qn!=null?qn.type:null).getStaticKey() + " could not be found." +
                        					" Did you forget a \"use\" statement?");
                        				error.setErrorType(ErrorType.MISSING_CLASS);
                        				error.setColumn((qn!=null?qn.type:null).getColumnBegin());
                        				error.setFile(getGrammarFileNameNoExtension());
                        				vm.getCompilerErrors().addError(error);
                        			}
                        		}
                        		else {
                        			if(cd.getStaticKey().equals(ErrorTypeDescriptor.ERROR) || cd.getParent(ErrorTypeDescriptor.ERROR) != null){
                                    			t.setName(cd.getStaticKey());
                                    		}else{
                                                        CompilerError error = new CompilerError();
                                    			error.setLineNumber(cd.getLineBegin());
                                    			error.setError("Class " + cd.getStaticKey() + " is not an error type." +
                                    					cd.getStaticKey() + " must inherit from class Error to be an error type");
                                    			error.setErrorType(ErrorType.INVALID_ERROR);
                                    			error.setColumn((qn!=null?qn.type:null).getColumnBegin());
                                    			error.setFile(getGrammarFileNameNoExtension());
                                    			vm.getCompilerErrors().addError(error);
                                                 }
                        		}
                        		((detect_parameter_scope)detect_parameter_stack.peek()).exceptionList.add(t);
                        	
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:759:2: ( OR qn= qualified_name )*
                        loop40:
                        do {
                            int alt40=2;
                            int LA40_0 = input.LA(1);

                            if ( (LA40_0==OR) ) {
                                alt40=1;
                            }


                            switch (alt40) {
                        	case 1 :
                        	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:759:3: OR qn= qualified_name
                        	    {
                        	    match(input,OR,FOLLOW_OR_in_detect_parameter1131); 
                        	    pushFollow(FOLLOW_qualified_name_in_detect_parameter1135);
                        	    qn=qualified_name();

                        	    state._fsp--;


                        	    		t = new ErrorTypeDescriptor();

                        	    		cd = symbol.findClassDescriptorFromCurrentClass((qn!=null?qn.type:null).getStaticKey());

                        	    		if(cd == null) { //this is a compiler error
                        	    			cd = symbol.getCurrentClass();
                        	    			GenericDescriptor gd = cd.getTemplateVariable((qn!=null?qn.type:null).getStaticKey());
                        	    			if(gd != null){
                        	    				t.setName(gd.getType().getStaticKey());
                        	    			}else{
                        	    				CompilerError error = new CompilerError();
                        	    				error.setLineNumber((qn!=null?qn.type:null).getLineBegin());
                        	    				error.setError("Class " + (qn!=null?qn.type:null).getStaticKey() + " could not be found." +
                        	    					" Did you forget a \"use\" statement?");
                        	    				error.setErrorType(ErrorType.MISSING_CLASS);
                        	    				error.setColumn((qn!=null?qn.type:null).getColumnBegin());
                        	    				error.setFile(getGrammarFileNameNoExtension());
                        	    				vm.getCompilerErrors().addError(error);
                        	    			}
                        	    		}
                        	    		else {
                        	    			if(cd.getStaticKey().equals(ErrorTypeDescriptor.ERROR) || cd.getParent(ErrorTypeDescriptor.ERROR) != null){
                        	    				t.setName(cd.getStaticKey());
                        	    			}else{
                        	    				CompilerError error = new CompilerError();
                        	                			error.setLineNumber(cd.getLineBegin());
                        	                			error.setError("Class " + cd.getStaticKey() + " is not an error type." +
                        	                					cd.getStaticKey() + " must inherit from class Error to be an error type");
                        	                			error.setErrorType(ErrorType.INVALID_ERROR);
                        	                			error.setColumn((qn!=null?qn.type:null).getColumnBegin());
                        	                			error.setFile(getGrammarFileNameNoExtension());
                        	                			vm.getCompilerErrors().addError(error);
                        	    			}
                        	    		}
                        	    		((detect_parameter_scope)detect_parameter_stack.peek()).exceptionList.add(t);
                        	    	

                        	    }
                        	    break;

                        	default :
                        	    break loop40;
                            }
                        } while (true);


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }

            		if(((detect_parameter_scope)detect_parameter_stack.peek()).exceptionList == null || ((detect_parameter_scope)detect_parameter_stack.peek()).exceptionList.isEmpty()){
            			((detect_parameter_scope)detect_parameter_stack.peek()).exceptionList = new ArrayList<ErrorTypeDescriptor>();
            			ErrorTypeDescriptor t = new ErrorTypeDescriptor();
            			t.setName(ErrorTypeDescriptor.ERROR);
            			((detect_parameter_scope)detect_parameter_stack.peek()).exceptionList.add(t);
            		}
            		retval.exceptionTypeList = ((detect_parameter_scope)detect_parameter_stack.peek()).exceptionList;
            		retval.name = (id!=null?id.getText():null);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            detect_parameter_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "detect_parameter"

    public static class print_statement_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "print_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:810:1: print_statement : PRINT root_expression ;
    public final QuorumTreeWalker.print_statement_return print_statement() throws RecognitionException {
        QuorumTreeWalker.print_statement_return retval = new QuorumTreeWalker.print_statement_return();
        retval.start = input.LT(1);

        QuorumTreeWalker.root_expression_return root_expression14 = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:811:2: ( PRINT root_expression )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:811:4: PRINT root_expression
            {
            match(input,PRINT,FOLLOW_PRINT_in_print_statement1162); 
            pushFollow(FOLLOW_root_expression_in_print_statement1164);
            root_expression14=root_expression();

            state._fsp--;


            		ExecutionStep step = (root_expression14!=null?root_expression14.step:null);
            		ExpressionValue value = (root_expression14!=null?root_expression14.eval:null);

            		LineInformation location = new LineInformation();
                            location.setEndColumn(step.getEndColumn());
                            location.setEndLine(step.getEndLine());
                            location.setStartColumn(step.getBeginColumn());
                            location.setStartLine(step.getBeginLine());
                            location.setFile(getGrammarFileNameNoExtension());

                            symbol.addStatementFlagToCurrentFile(step.getBeginLine());
                            
            		stepFactory.addPrintStep(location, (root_expression14!=null?root_expression14.eval:null), (root_expression14!=null?root_expression14.step:null));
            		builder.addStepLabel(OpcodeType.PRINT);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "print_statement"

    public static class speak_statement_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "speak_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:831:1: speak_statement : SAY root_expression ;
    public final QuorumTreeWalker.speak_statement_return speak_statement() throws RecognitionException {
        QuorumTreeWalker.speak_statement_return retval = new QuorumTreeWalker.speak_statement_return();
        retval.start = input.LT(1);

        QuorumTreeWalker.root_expression_return root_expression15 = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:832:2: ( SAY root_expression )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:832:4: SAY root_expression
            {
            match(input,SAY,FOLLOW_SAY_in_speak_statement1180); 
            pushFollow(FOLLOW_root_expression_in_speak_statement1182);
            root_expression15=root_expression();

            state._fsp--;


            		ExecutionStep step = (root_expression15!=null?root_expression15.step:null);
            		ExpressionValue value = (root_expression15!=null?root_expression15.eval:null);

            		LineInformation location = new LineInformation();
                            location.setEndColumn(step.getEndColumn());
                            location.setEndLine(step.getEndLine());
                            location.setStartColumn(step.getBeginColumn());
                            location.setStartLine(step.getBeginLine());
                            location.setFile(getGrammarFileNameNoExtension());

                            symbol.addStatementFlagToCurrentFile(step.getBeginLine());
            		stepFactory.addSpeakStep(location, (root_expression15!=null?root_expression15.eval:null), (root_expression15!=null?root_expression15.step:null));
            	
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "speak_statement"

    public static class return_statement_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "return_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:850:1: return_statement : RETURN ( root_expression | NOW ) ;
    public final QuorumTreeWalker.return_statement_return return_statement() throws RecognitionException {
        QuorumTreeWalker.return_statement_return retval = new QuorumTreeWalker.return_statement_return();
        retval.start = input.LT(1);

        CommonTree NOW17=null;
        CommonTree RETURN18=null;
        QuorumTreeWalker.root_expression_return root_expression16 = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:851:2: ( RETURN ( root_expression | NOW ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:851:4: RETURN ( root_expression | NOW )
            {
            RETURN18=(CommonTree)match(input,RETURN,FOLLOW_RETURN_in_return_statement1196); 
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:851:11: ( root_expression | NOW )
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==ROOT_EXPRESSION) ) {
                alt42=1;
            }
            else if ( (LA42_0==NOW) ) {
                alt42=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;
            }
            switch (alt42) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:851:12: root_expression
                    {
                    pushFollow(FOLLOW_root_expression_in_return_statement1199);
                    root_expression16=root_expression();

                    state._fsp--;



                    		ExecutionStep step = (root_expression16!=null?root_expression16.step:null);
                    		ExpressionValue value = (root_expression16!=null?root_expression16.eval:null);

                    		LineInformation location = new LineInformation();
                                    location.setEndColumn(step.getEndColumn());
                                    location.setEndLine(step.getEndLine());
                                    location.setStartColumn(step.getBeginColumn());
                                    location.setStartLine(step.getBeginLine());
                                    location.setFile(getGrammarFileNameNoExtension());

                                    symbol.addStatementFlagToCurrentFile(step.getBeginLine());
                    		stepFactory.addReturnStep(location, (root_expression16!=null?root_expression16.eval:null), (root_expression16!=null?root_expression16.step:null));
                    		builder.addStepLabel(OpcodeType.RETURN);
                    	

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:868:4: NOW
                    {
                    NOW17=(CommonTree)match(input,NOW,FOLLOW_NOW_in_return_statement1207); 

                    		LineInformation location = new LineInformation();
                                    location.setEndColumn(NOW17.getCharPositionInLine());
                                    location.setEndLine(NOW17.getLine());
                                    location.setStartColumn(RETURN18.getCharPositionInLine());
                                    location.setStartLine(RETURN18.getLine());
                                    location.setFile(getGrammarFileNameNoExtension());

                                    symbol.addStatementFlagToCurrentFile(RETURN18.getLine());
                    		stepFactory.addReturnStep(location, null, null);
                    		builder.addStepLabel(OpcodeType.RETURN);
                    	

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "return_statement"

    public static class generic_declaration_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "generic_declaration"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:883:1: generic_declaration : ^( GENERIC LESS ids+= ID ( COMMA ids+= ID )* GREATER ) ;
    public final QuorumTreeWalker.generic_declaration_return generic_declaration() throws RecognitionException {
        QuorumTreeWalker.generic_declaration_return retval = new QuorumTreeWalker.generic_declaration_return();
        retval.start = input.LT(1);

        CommonTree ids=null;
        List list_ids=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:884:2: ( ^( GENERIC LESS ids+= ID ( COMMA ids+= ID )* GREATER ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:884:4: ^( GENERIC LESS ids+= ID ( COMMA ids+= ID )* GREATER )
            {
            match(input,GENERIC,FOLLOW_GENERIC_in_generic_declaration1225); 

            match(input, Token.DOWN, null); 
            match(input,LESS,FOLLOW_LESS_in_generic_declaration1227); 
            ids=(CommonTree)match(input,ID,FOLLOW_ID_in_generic_declaration1231); 
            if (list_ids==null) list_ids=new ArrayList();
            list_ids.add(ids);

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:884:27: ( COMMA ids+= ID )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==COMMA) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:884:28: COMMA ids+= ID
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_generic_declaration1234); 
            	    ids=(CommonTree)match(input,ID,FOLLOW_ID_in_generic_declaration1238); 
            	    if (list_ids==null) list_ids=new ArrayList();
            	    list_ids.add(ids);


            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);

            match(input,GREATER,FOLLOW_GREATER_in_generic_declaration1242); 

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "generic_declaration"

    public static class generic_statement_return extends TreeRuleReturnScope {
        public ArrayList<GenericDescriptor> templateTypes;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "generic_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:887:1: generic_statement returns [ArrayList<GenericDescriptor> templateTypes] : ^( GENERIC LESS ad= assignment_declaration ( COMMA a= assignment_declaration )* GREATER ) ;
    public final QuorumTreeWalker.generic_statement_return generic_statement() throws RecognitionException {
        QuorumTreeWalker.generic_statement_return retval = new QuorumTreeWalker.generic_statement_return();
        retval.start = input.LT(1);

        QuorumTreeWalker.assignment_declaration_return ad = null;

        QuorumTreeWalker.assignment_declaration_return a = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:888:2: ( ^( GENERIC LESS ad= assignment_declaration ( COMMA a= assignment_declaration )* GREATER ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:888:4: ^( GENERIC LESS ad= assignment_declaration ( COMMA a= assignment_declaration )* GREATER )
            {
            match(input,GENERIC,FOLLOW_GENERIC_in_generic_statement1260); 

            match(input, Token.DOWN, null); 
            match(input,LESS,FOLLOW_LESS_in_generic_statement1262); 

            		ArrayList<GenericDescriptor> types = new ArrayList<GenericDescriptor>();
            	
            pushFollow(FOLLOW_assignment_declaration_in_generic_statement1271);
            ad=assignment_declaration();

            state._fsp--;


            		TypeDescriptor type = (ad!=null?ad.myType:null);
            				
            		GenericDescriptor genericType = new GenericDescriptor();
            		genericType.setLineBegin(type.getLineBegin());
            		genericType.setColumnBegin(type.getColumnBegin());
            		genericType.setLineEnd(type.getLineEnd());
            		genericType.setColumnEnd(type.getColumnEnd());
            		genericType.setName(type.getName());
            		genericType.addBoundType(type);
            			
            		types.add(genericType);
            	
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:906:3: ( COMMA a= assignment_declaration )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==COMMA) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:906:4: COMMA a= assignment_declaration
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_generic_statement1279); 
            	    pushFollow(FOLLOW_assignment_declaration_in_generic_statement1283);
            	    a=assignment_declaration();

            	    state._fsp--;


            	    		type = (a!=null?a.myType:null);
            	    		
            	    		genericType = new GenericDescriptor();
            	    		genericType.setLineBegin(type.getLineBegin());
            	    		genericType.setColumnBegin(type.getColumnBegin());
            	    		genericType.setLineEnd(type.getLineEnd());
            	    		genericType.setColumnEnd(type.getColumnEnd());
            	    		genericType.setName(type.getName());
            	    		genericType.addBoundType(type);
            	    		
            	    		types.add(genericType);
            	    	

            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);


            	
            	 	retval.templateTypes = types;
            	
            match(input,GREATER,FOLLOW_GREATER_in_generic_statement1299); 

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "generic_statement"

    public static class class_type_return extends TreeRuleReturnScope {
        public TypeDescriptor myType;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "class_type"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:933:1: class_type returns [TypeDescriptor myType] : qn= qualified_name ;
    public final QuorumTreeWalker.class_type_return class_type() throws RecognitionException {
        QuorumTreeWalker.class_type_return retval = new QuorumTreeWalker.class_type_return();
        retval.start = input.LT(1);

        QuorumTreeWalker.qualified_name_return qn = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:934:2: (qn= qualified_name )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:934:4: qn= qualified_name
            {
            pushFollow(FOLLOW_qualified_name_in_class_type1324);
            qn=qualified_name();

            state._fsp--;


            		TypeDescriptor t = new TypeDescriptor();

            		ClassDescriptor cd = symbol.findClassDescriptorFromCurrentClass((qn!=null?qn.type:null).getStaticKey());

            		if(cd == null) { //this is a compiler error
            			cd = symbol.getCurrentClass();
            			GenericDescriptor gd = cd.getTemplateVariable((qn!=null?qn.type:null).getStaticKey());
            			if(gd != null){
            				t = gd.getType();
            			}else{
            				CompilerError error = new CompilerError();
            				error.setLineNumber((qn!=null?qn.type:null).getLineBegin());
            				error.setError("Class " + (qn!=null?qn.type:null).getStaticKey() + " could not be found." +
            					" Did you forget a \"use\" statement?");
            				error.setErrorType(ErrorType.MISSING_CLASS);
            				error.setColumn((qn!=null?qn.type:null).getColumnBegin());
            				error.setFile(getGrammarFileNameNoExtension());
            				vm.getCompilerErrors().addError(error);
            			}
            		}
            		else {
            			t.setName(cd.getStaticKey());
            		}
            		retval.myType =t;
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "class_type"

    public static class assignment_declaration_return extends TreeRuleReturnScope {
        public TypeDescriptor myType;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "assignment_declaration"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:963:1: assignment_declaration returns [TypeDescriptor myType] : (qn= qualified_name (gs= generic_statement )? | INTEGER_KEYWORD | NUMBER_KEYWORD | TEXT | BOOLEAN_KEYWORD );
    public final QuorumTreeWalker.assignment_declaration_return assignment_declaration() throws RecognitionException {
        QuorumTreeWalker.assignment_declaration_return retval = new QuorumTreeWalker.assignment_declaration_return();
        retval.start = input.LT(1);

        QuorumTreeWalker.qualified_name_return qn = null;

        QuorumTreeWalker.generic_statement_return gs = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:964:2: (qn= qualified_name (gs= generic_statement )? | INTEGER_KEYWORD | NUMBER_KEYWORD | TEXT | BOOLEAN_KEYWORD )
            int alt46=5;
            switch ( input.LA(1) ) {
            case QUALIFIED_NAME:
                {
                alt46=1;
                }
                break;
            case INTEGER_KEYWORD:
                {
                alt46=2;
                }
                break;
            case NUMBER_KEYWORD:
                {
                alt46=3;
                }
                break;
            case TEXT:
                {
                alt46=4;
                }
                break;
            case BOOLEAN_KEYWORD:
                {
                alt46=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;
            }

            switch (alt46) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:964:4: qn= qualified_name (gs= generic_statement )?
                    {
                    pushFollow(FOLLOW_qualified_name_in_assignment_declaration1346);
                    qn=qualified_name();

                    state._fsp--;

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:964:26: (gs= generic_statement )?
                    int alt45=2;
                    int LA45_0 = input.LA(1);

                    if ( (LA45_0==GENERIC) ) {
                        alt45=1;
                    }
                    switch (alt45) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:964:26: gs= generic_statement
                            {
                            pushFollow(FOLLOW_generic_statement_in_assignment_declaration1350);
                            gs=generic_statement();

                            state._fsp--;


                            }
                            break;

                    }


                    		TypeDescriptor t = new TypeDescriptor();

                    		ClassDescriptor cd = symbol.findClassDescriptorFromCurrentClass((qn!=null?qn.type:null).getStaticKey());

                    		if(cd == null) { //this is a compiler error
                    			cd = symbol.getCurrentClass();
                    			GenericDescriptor gd = cd.getTemplateVariable((qn!=null?qn.type:null).getStaticKey());
                    			if(gd != null){
                    				t = gd.getType();
                    			}else{
                    				CompilerError error = new CompilerError();
                    				error.setLineNumber((qn!=null?qn.type:null).getLineBegin());
                    				error.setError("Class " + (qn!=null?qn.type:null).getStaticKey() + " could not be found." +
                    					" Did you forget a \"use\" statement?");
                    				error.setErrorType(ErrorType.MISSING_CLASS);
                    				error.setColumn((qn!=null?qn.type:null).getColumnBegin());
                    				error.setFile(getGrammarFileNameNoExtension());
                    				vm.getCompilerErrors().addError(error);
                    			}
                    		}
                    		else {
                    			if((gs!=null?gs.templateTypes:null) != null){
                    				t.setSubTypes((gs!=null?gs.templateTypes:null));
                    			}
                    			t.setName(cd.getStaticKey());
                    		}
                    		retval.myType =t;
                    	

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:994:4: INTEGER_KEYWORD
                    {
                    match(input,INTEGER_KEYWORD,FOLLOW_INTEGER_KEYWORD_in_assignment_declaration1359); 

                    		TypeDescriptor type = new TypeDescriptor();
                    		type.setName(TypeDescriptor.INTEGER);
                    		retval.myType = type;
                    	

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1000:4: NUMBER_KEYWORD
                    {
                    match(input,NUMBER_KEYWORD,FOLLOW_NUMBER_KEYWORD_in_assignment_declaration1367); 

                    		TypeDescriptor type = new TypeDescriptor();
                    		type.setName(TypeDescriptor.NUMBER);
                    		retval.myType = type;
                    	

                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1006:4: TEXT
                    {
                    match(input,TEXT,FOLLOW_TEXT_in_assignment_declaration1375); 

                    		TypeDescriptor type = new TypeDescriptor();
                    		type.setName(TypeDescriptor.TEXT);
                    		retval.myType = type;
                    	

                    }
                    break;
                case 5 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1012:4: BOOLEAN_KEYWORD
                    {
                    match(input,BOOLEAN_KEYWORD,FOLLOW_BOOLEAN_KEYWORD_in_assignment_declaration1383); 

                    		TypeDescriptor type = new TypeDescriptor();
                    		type.setName(TypeDescriptor.BOOLEAN);
                    		retval.myType = type;
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "assignment_declaration"

    public static class assignment_statement_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "assignment_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1018:1: assignment_statement : ( (sel= selector COLON )? ID rhs= assign_right_hand_side | obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side | (modifier= access_modifier )? type= assignment_declaration name= ID (rhs= assign_right_hand_side )? );
    public final QuorumTreeWalker.assignment_statement_return assignment_statement() throws RecognitionException {
        QuorumTreeWalker.assignment_statement_return retval = new QuorumTreeWalker.assignment_statement_return();
        retval.start = input.LT(1);

        CommonTree name=null;
        CommonTree ID19=null;
        CommonTree ID20=null;
        CommonTree PARENT21=null;
        QuorumTreeWalker.selector_return sel = null;

        QuorumTreeWalker.assign_right_hand_side_return rhs = null;

        QuorumTreeWalker.qualified_name_return obj = null;

        QuorumTreeWalker.qualified_name_return parent = null;

        QuorumTreeWalker.access_modifier_return modifier = null;

        QuorumTreeWalker.assignment_declaration_return type = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1019:2: ( (sel= selector COLON )? ID rhs= assign_right_hand_side | obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side | (modifier= access_modifier )? type= assignment_declaration name= ID (rhs= assign_right_hand_side )? )
            int alt51=3;
            alt51 = dfa51.predict(input);
            switch (alt51) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1020:3: (sel= selector COLON )? ID rhs= assign_right_hand_side
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1020:3: (sel= selector COLON )?
                    int alt47=2;
                    int LA47_0 = input.LA(1);

                    if ( ((LA47_0>=PARENT && LA47_0<=ME)) ) {
                        alt47=1;
                    }
                    switch (alt47) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1020:4: sel= selector COLON
                            {
                            pushFollow(FOLLOW_selector_in_assignment_statement1399);
                            sel=selector();

                            state._fsp--;

                            match(input,COLON,FOLLOW_COLON_in_assignment_statement1401); 

                            }
                            break;

                    }

                    ID19=(CommonTree)match(input,ID,FOLLOW_ID_in_assignment_statement1405); 
                    pushFollow(FOLLOW_assign_right_hand_side_in_assignment_statement1409);
                    rhs=assign_right_hand_side();

                    state._fsp--;


                    		LineInformation location = new LineInformation (
                    			 (ID19!=null?ID19.getLine():0),
                    			 0,
                    			 ID19.getCharPositionInLine(),
                    			 0
                    		);
                    		location.setFile(symbol.getCurrentClass().getFile().getStaticKey());
                                    location.setClassName(symbol.getCurrentClass().getStaticKey());
                                    if(symbol.getCurrentMethod() != null){
                                    	location.setMethodName(symbol.getCurrentMethod().getStaticKey());
                                    }
                                    
                    		ScopeSelector scope = (sel!=null?sel.scopeSel:null);
                    		ClassDescriptor cd = null;
                    		if(scope!= null){
                    			if(scope.isParent()){
                    				cd = scope.getCurrentClass();
                    			}else{
                    				cd = symbol.getCurrentClass();
                    			}
                    		}
                    		
                    		symbol.addStatementFlagToCurrentFile((ID19!=null?ID19.getLine():0));
                    		
                    		stepFactory.addAssignmentStep(location, (ID19!=null?ID19.getText():null), (rhs!=null?rhs.eval:null), (rhs!=null?rhs.step:null), false, "", cd);
                    		builder.addStepLabel(OpcodeType.ASSIGNMENT);
                    	

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1049:4: obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side
                    {
                    pushFollow(FOLLOW_qualified_name_in_assignment_statement1419);
                    obj=qualified_name();

                    state._fsp--;

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1049:23: ( COLON PARENT COLON parent= qualified_name )?
                    int alt48=2;
                    int LA48_0 = input.LA(1);

                    if ( (LA48_0==COLON) ) {
                        int LA48_1 = input.LA(2);

                        if ( (LA48_1==PARENT) ) {
                            alt48=1;
                        }
                    }
                    switch (alt48) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1049:24: COLON PARENT COLON parent= qualified_name
                            {
                            match(input,COLON,FOLLOW_COLON_in_assignment_statement1422); 
                            PARENT21=(CommonTree)match(input,PARENT,FOLLOW_PARENT_in_assignment_statement1424); 
                            match(input,COLON,FOLLOW_COLON_in_assignment_statement1426); 
                            pushFollow(FOLLOW_qualified_name_in_assignment_statement1430);
                            parent=qualified_name();

                            state._fsp--;


                            }
                            break;

                    }

                    match(input,COLON,FOLLOW_COLON_in_assignment_statement1434); 
                    ID20=(CommonTree)match(input,ID,FOLLOW_ID_in_assignment_statement1436); 
                    pushFollow(FOLLOW_assign_right_hand_side_in_assignment_statement1440);
                    rhs=assign_right_hand_side();

                    state._fsp--;


                    		LineInformation location = new LineInformation (
                    			 (ID20!=null?ID20.getLine():0),
                    			 0,
                    			 ID20.getCharPositionInLine(),
                    			 0
                    		);
                    		location.setFile(symbol.getCurrentClass().getFile().getStaticKey());
                                    location.setClassName(symbol.getCurrentClass().getStaticKey());
                                    if(symbol.getCurrentMethod() != null){
                                    	location.setMethodName(symbol.getCurrentMethod().getStaticKey());
                                    }
                                    
                    		boolean isLocal = (type!=null?type.myType:null) != null;
                    		
                    		symbol.addStatementFlagToCurrentFile((ID20!=null?ID20.getLine():0));
                    		ClassDescriptor cd = null;
                    		
                    		if((parent!=null?parent.type:null) != null){
                    			cd = symbol.findClassDescriptorFromCurrentClass((parent!=null?parent.type:null).getStaticKey());
                    			
                    			if(cd == null){
                    				CompilerError error = new CompilerError(PARENT21.getLine(), "The class "+ symbol.getCurrentClass().getStaticKey() +" does not have access to " + (parent!=null?parent.type:null).getStaticKey(), ErrorType.MISSING_PARENT);
                    				vm.getCompilerErrors().addError(error);
                    			}
                    		}
                    		
                    		stepFactory.addAssignmentStep(location, (obj!=null?obj.type:null).getStaticKey(), (rhs!=null?rhs.eval:null), (rhs!=null?rhs.step:null), isLocal, (ID20!=null?ID20.getText():null), cd);
                    		builder.addStepLabel(OpcodeType.ASSIGNMENT);
                    	

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1080:4: (modifier= access_modifier )? type= assignment_declaration name= ID (rhs= assign_right_hand_side )?
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1080:13: (modifier= access_modifier )?
                    int alt49=2;
                    int LA49_0 = input.LA(1);

                    if ( ((LA49_0>=PUBLIC && LA49_0<=PRIVATE)) ) {
                        alt49=1;
                    }
                    switch (alt49) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1080:13: modifier= access_modifier
                            {
                            pushFollow(FOLLOW_access_modifier_in_assignment_statement1452);
                            modifier=access_modifier();

                            state._fsp--;


                            }
                            break;

                    }

                    pushFollow(FOLLOW_assignment_declaration_in_assignment_statement1459);
                    type=assignment_declaration();

                    state._fsp--;

                    name=(CommonTree)match(input,ID,FOLLOW_ID_in_assignment_statement1465); 
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1080:75: (rhs= assign_right_hand_side )?
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( (LA50_0==EQUALITY) ) {
                        alt50=1;
                    }
                    switch (alt50) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1080:75: rhs= assign_right_hand_side
                            {
                            pushFollow(FOLLOW_assign_right_hand_side_in_assignment_statement1469);
                            rhs=assign_right_hand_side();

                            state._fsp--;


                            }
                            break;

                    }


                                    LineInformation location = new LineInformation (
                                        (name!=null?name.getLine():0),
                                        0,
                                        name.getCharPositionInLine(),
                                        0
                                    );
                                    location.setFile(symbol.getCurrentClass().getFile().getStaticKey());
                                    location.setClassName(symbol.getCurrentClass().getStaticKey());
                                    if(symbol.getCurrentMethod() != null){
                                    	location.setMethodName(symbol.getCurrentMethod().getStaticKey());
                                    }
                                    
                                    boolean isLocal = (type!=null?type.myType:null) != null;
                                    
                                    symbol.addStatementFlagToCurrentFile((name!=null?name.getLine():0));
                                    
                    		if((rhs!=null?rhs.eval:null) != null)
                    		{
                    			stepFactory.addAssignmentStep(location, (name!=null?name.getText():null), (rhs!=null?rhs.eval:null), (rhs!=null?rhs.step:null), isLocal);
                    		}
                                    else { // are we are trying to instantiate an object?
                                    	builder.addStepLabel(OpcodeType.ROOT_EXPRESSION);
                                        	stepFactory.addAssignmentStep(location, (name!=null?name.getText():null), isLocal);
                                    }
                                    builder.addStepLabel(OpcodeType.ASSIGNMENT);
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "assignment_statement"

    public static class assign_right_hand_side_return extends TreeRuleReturnScope {
        public ExpressionValue eval;
        public ExecutionStep step;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "assign_right_hand_side"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1109:1: assign_right_hand_side returns [ExpressionValue eval, ExecutionStep step] : ( EQUALITY root_expression ) ;
    public final QuorumTreeWalker.assign_right_hand_side_return assign_right_hand_side() throws RecognitionException {
        QuorumTreeWalker.assign_right_hand_side_return retval = new QuorumTreeWalker.assign_right_hand_side_return();
        retval.start = input.LT(1);

        QuorumTreeWalker.root_expression_return root_expression22 = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1110:2: ( ( EQUALITY root_expression ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1111:3: ( EQUALITY root_expression )
            {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1111:3: ( EQUALITY root_expression )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1111:4: EQUALITY root_expression
            {
            match(input,EQUALITY,FOLLOW_EQUALITY_in_assign_right_hand_side1490); 
            pushFollow(FOLLOW_root_expression_in_assign_right_hand_side1492);
            root_expression22=root_expression();

            state._fsp--;


            }


            		retval.eval = (root_expression22!=null?root_expression22.eval:null);
            		retval.step = (root_expression22!=null?root_expression22.step:null);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "assign_right_hand_side"

    protected static class if_statement_scope {
        IfStatementInfo info;
        int else_if_counter;
    }
    protected Stack if_statement_stack = new Stack();

    public static class if_statement_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "if_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1119:1: if_statement : begin_if= IF condition= root_expression if_then= THEN b= block[true] if_end= END ( (begin_else_if= ELSE second_if= IF condition2= root_expression else_if_then= THEN b= block[true] else_if_end= END ) )* (begin_else= ELSE THEN b= block[true] END )? ;
    public final QuorumTreeWalker.if_statement_return if_statement() throws RecognitionException {
        if_statement_stack.push(new if_statement_scope());
        QuorumTreeWalker.if_statement_return retval = new QuorumTreeWalker.if_statement_return();
        retval.start = input.LT(1);

        CommonTree begin_if=null;
        CommonTree if_then=null;
        CommonTree if_end=null;
        CommonTree begin_else_if=null;
        CommonTree second_if=null;
        CommonTree else_if_then=null;
        CommonTree else_if_end=null;
        CommonTree begin_else=null;
        QuorumTreeWalker.root_expression_return condition = null;

        QuorumTreeWalker.block_return b = null;

        QuorumTreeWalker.root_expression_return condition2 = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1124:3: (begin_if= IF condition= root_expression if_then= THEN b= block[true] if_end= END ( (begin_else_if= ELSE second_if= IF condition2= root_expression else_if_then= THEN b= block[true] else_if_end= END ) )* (begin_else= ELSE THEN b= block[true] END )? )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1124:5: begin_if= IF condition= root_expression if_then= THEN b= block[true] if_end= END ( (begin_else_if= ELSE second_if= IF condition2= root_expression else_if_then= THEN b= block[true] else_if_end= END ) )* (begin_else= ELSE THEN b= block[true] END )?
            {
            begin_if=(CommonTree)match(input,IF,FOLLOW_IF_in_if_statement1518); 

            			((if_statement_scope)if_statement_stack.peek()).info = new IfStatementInfo();
            			((if_statement_scope)if_statement_stack.peek()).else_if_counter = 0;
            			((if_statement_scope)if_statement_stack.peek()).info.endLabel = (begin_if!=null?begin_if.getText():null) + labelCounter + ((if_statement_scope)if_statement_stack.peek()).info.END;
            			((if_statement_scope)if_statement_stack.peek()).info.ifFalseLabel =(begin_if!=null?begin_if.getText():null) + labelCounter + ((if_statement_scope)if_statement_stack.peek()).info.FALSE;
            			((if_statement_scope)if_statement_stack.peek()).info.ifStartLabel =(begin_if!=null?begin_if.getText():null) + labelCounter + ((if_statement_scope)if_statement_stack.peek()).info.START;
            			symbol.addStatementFlagToCurrentFile(begin_if.getLine());
            			labelCounter++;		
            		
            pushFollow(FOLLOW_root_expression_in_if_statement1531);
            condition=root_expression();

            state._fsp--;

            if_then=(CommonTree)match(input,THEN,FOLLOW_THEN_in_if_statement1537); 

            			stepFactory.assertBooleanExpression((condition!=null?condition.eval:null).getType(),
            			    (condition!=null?condition.step:null),
            			    getGrammarFileNameNoExtension());
            			    
            			((if_statement_scope)if_statement_stack.peek()).info.ifLocation = new LineInformation(
            				if_then.getLine(),
            				if_then.getLine(),
            				if_then.getCharPositionInLine(),
            				(if_then!=null?if_then.getText():null).length() + if_then.getCharPositionInLine());
            				
            			ConditionalJumpIfStep ifConditionalStep = new ConditionalJumpIfStep();
            			ifConditionalStep.setEndColumn((if_then!=null?if_then.getText():null).length() + if_then.getCharPositionInLine());
            			ifConditionalStep.setEndLine(if_then.getLine());
            			ifConditionalStep.setBeginColumn(if_then.getCharPositionInLine());
            			ifConditionalStep.setBeginLine(if_then.getLine());

            			ifConditionalStep.setLeftRegister((condition!=null?condition.eval:null).getRegister());
            			((if_statement_scope)if_statement_stack.peek()).info.ifConditionalStep = ifConditionalStep;

            			stepFactory.startIf(((if_statement_scope)if_statement_stack.peek()).info);
            			
            		
            pushFollow(FOLLOW_block_in_if_statement1548);
            b=block(true);

            state._fsp--;


                                    
                                    ((if_statement_scope)if_statement_stack.peek()).info.ifJumpStep.setBeginColumn(begin_if.getCharPositionInLine());
            			((if_statement_scope)if_statement_stack.peek()).info.ifJumpStep.setEndColumn(begin_if.getCharPositionInLine() + ((begin_if!=null?begin_if.getText():null).length()));
            	                ((if_statement_scope)if_statement_stack.peek()).info.ifJumpStep.setEndLine(begin_if.getLine());
            			stepFactory.addIfEndJumpStep(((if_statement_scope)if_statement_stack.peek()).info);
            			
            		
            if_end=(CommonTree)match(input,END,FOLLOW_END_in_if_statement1562); 

            			((if_statement_scope)if_statement_stack.peek()).info.ifJumpStep.setBeginLine(if_end.getLine());
            			stepFactory.endIf(((if_statement_scope)if_statement_stack.peek()).info);									
            		
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1172:3: ( (begin_else_if= ELSE second_if= IF condition2= root_expression else_if_then= THEN b= block[true] else_if_end= END ) )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==ELSE) ) {
                    int LA52_1 = input.LA(2);

                    if ( (LA52_1==IF) ) {
                        alt52=1;
                    }


                }


                switch (alt52) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1172:4: (begin_else_if= ELSE second_if= IF condition2= root_expression else_if_then= THEN b= block[true] else_if_end= END )
            	    {
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1172:4: (begin_else_if= ELSE second_if= IF condition2= root_expression else_if_then= THEN b= block[true] else_if_end= END )
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1172:5: begin_else_if= ELSE second_if= IF condition2= root_expression else_if_then= THEN b= block[true] else_if_end= END
            	    {
            	    begin_else_if=(CommonTree)match(input,ELSE,FOLLOW_ELSE_in_if_statement1579); 
            	    second_if=(CommonTree)match(input,IF,FOLLOW_IF_in_if_statement1585); 

            	    			((if_statement_scope)if_statement_stack.peek()).info.elseIfEndLabels.add((begin_else_if!=null?begin_else_if.getText():null) + (second_if!=null?second_if.getText():null) + labelCounter + ((if_statement_scope)if_statement_stack.peek()).info.END + ((if_statement_scope)if_statement_stack.peek()).else_if_counter);
            	    			((if_statement_scope)if_statement_stack.peek()).info.elseIfFalseLabels.add((begin_else_if!=null?begin_else_if.getText():null) + (second_if!=null?second_if.getText():null) + labelCounter + ((if_statement_scope)if_statement_stack.peek()).info.FALSE + ((if_statement_scope)if_statement_stack.peek()).else_if_counter);
            	    			((if_statement_scope)if_statement_stack.peek()).info.elseIfStartLabels.add((begin_else_if!=null?begin_else_if.getText():null) + (second_if!=null?second_if.getText():null) + labelCounter + ((if_statement_scope)if_statement_stack.peek()).info.START + ((if_statement_scope)if_statement_stack.peek()).else_if_counter);	
            	    			
            	    			symbol.addStatementFlagToCurrentFile(begin_else_if.getLine());		
            	    			labelCounter++;					
            	    		
            	    pushFollow(FOLLOW_root_expression_in_if_statement1598);
            	    condition2=root_expression();

            	    state._fsp--;

            	    else_if_then=(CommonTree)match(input,THEN,FOLLOW_THEN_in_if_statement1604); 

            	    			stepFactory.assertBooleanExpression((condition2!=null?condition2.eval:null).getType(),
            	    			    (condition2!=null?condition2.step:null),
            	    			    getGrammarFileNameNoExtension());
            	    			    
            	    			((if_statement_scope)if_statement_stack.peek()).info.elseIfLocations.add(new LineInformation(
            	    				else_if_then.getLine(),
            	    				else_if_then.getLine(),
            	    				else_if_then.getCharPositionInLine(),
            	    				(else_if_then!=null?else_if_then.getText():null).length() + else_if_then.getCharPositionInLine()));
            	    			
            	    			ConditionalJumpIfStep conditionalStep = new ConditionalJumpIfStep();
            	    			conditionalStep.setEndColumn((else_if_then!=null?else_if_then.getText():null).length() + else_if_then.getCharPositionInLine());
            	    			conditionalStep.setEndLine(else_if_then.getLine());
            	    			conditionalStep.setBeginColumn(else_if_then.getCharPositionInLine());
            	    			conditionalStep.setBeginLine(else_if_then.getLine());

            	    			conditionalStep.setLeftRegister((condition2!=null?condition2.eval:null).getRegister());
            	    			
            	    			((if_statement_scope)if_statement_stack.peek()).info.elseIfConditionalSteps.add(conditionalStep);	
            	    			
            	    			stepFactory.startElseIf(((if_statement_scope)if_statement_stack.peek()).info,((if_statement_scope)if_statement_stack.peek()).else_if_counter);
            	    		
            	    pushFollow(FOLLOW_block_in_if_statement1614);
            	    b=block(true);

            	    state._fsp--;


            	    			JumpStep jump = new JumpStep();
            	    			jump.setBeginColumn(begin_else_if.getCharPositionInLine());
            	    			jump.setEndColumn(begin_else_if.getCharPositionInLine() + ((begin_else_if!=null?begin_else_if.getText():null).length()));
            	    			jump.setEndLine(begin_else_if.getLine());
            	    			((if_statement_scope)if_statement_stack.peek()).info.elseIfJumpSteps.add(jump);
            	    			
            	    			stepFactory.addElseIfEndJumpStep(((if_statement_scope)if_statement_stack.peek()).info, ((if_statement_scope)if_statement_stack.peek()).else_if_counter);	
            	    		
            	    else_if_end=(CommonTree)match(input,END,FOLLOW_END_in_if_statement1632); 

            	    			((if_statement_scope)if_statement_stack.peek()).info.elseIfJumpSteps.get(((if_statement_scope)if_statement_stack.peek()).else_if_counter).setBeginLine(else_if_end.getLine());
            	    			stepFactory.endElseIf(((if_statement_scope)if_statement_stack.peek()).info, ((if_statement_scope)if_statement_stack.peek()).else_if_counter);
            	    			((if_statement_scope)if_statement_stack.peek()).else_if_counter = ((if_statement_scope)if_statement_stack.peek()).else_if_counter + 1;					
            	    		

            	    }


            	    }
            	    break;

            	default :
            	    break loop52;
                }
            } while (true);

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1222:3: (begin_else= ELSE THEN b= block[true] END )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==ELSE) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1222:4: begin_else= ELSE THEN b= block[true] END
                    {
                    begin_else=(CommonTree)match(input,ELSE,FOLLOW_ELSE_in_if_statement1661); 
                    match(input,THEN,FOLLOW_THEN_in_if_statement1663); 

                    			((if_statement_scope)if_statement_stack.peek()).info.elseStartLabel = (begin_else!=null?begin_else.getText():null) + labelCounter + ((if_statement_scope)if_statement_stack.peek()).info.START;	
                    			((if_statement_scope)if_statement_stack.peek()).info.hasElse = true;
                    			labelCounter++;	
                    			stepFactory.startElse(((if_statement_scope)if_statement_stack.peek()).info);	
                    		
                    pushFollow(FOLLOW_block_in_if_statement1673);
                    b=block(true);

                    state._fsp--;

                    match(input,END,FOLLOW_END_in_if_statement1676); 

                    			stepFactory.endElse();																					
                    		

                    }
                    break;

            }


            			stepFactory.endIfBlock(((if_statement_scope)if_statement_stack.peek()).info);
              																		
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if_statement_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "if_statement"

    protected static class loop_statement_scope {
        String marker_top;
        String marker_loop;
        String marker_bottom;
        LineInformation location;
        ResultTuple loop_counter;
        JumpStep jumpToTop;
        ExpressionValue first_value;
        ExecutionStep first_step;
        ExpressionValue last_value;
        ExecutionStep last_step;
        int type;
        ConditionalJumpLoopStep cJumpStep;
        ConditionalJumpUntilStep uJumpStep;
        boolean hasCounter;
        boolean isWhile;
    }
    protected Stack loop_statement_stack = new Stack();

    public static class loop_statement_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "loop_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1239:1: loop_statement : REPEAT ( ( OVER ID ) | ( ( FROM ran= range ) ) | (expr= root_expression TIMES ) | ( ( WHILE | UNTIL ) expr= root_expression ) ) block[true] END ;
    public final QuorumTreeWalker.loop_statement_return loop_statement() throws RecognitionException {
        loop_statement_stack.push(new loop_statement_scope());
        QuorumTreeWalker.loop_statement_return retval = new QuorumTreeWalker.loop_statement_return();
        retval.start = input.LT(1);

        CommonTree REPEAT23=null;
        QuorumTreeWalker.range_return ran = null;

        QuorumTreeWalker.root_expression_return expr = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1258:2: ( REPEAT ( ( OVER ID ) | ( ( FROM ran= range ) ) | (expr= root_expression TIMES ) | ( ( WHILE | UNTIL ) expr= root_expression ) ) block[true] END )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1259:2: REPEAT ( ( OVER ID ) | ( ( FROM ran= range ) ) | (expr= root_expression TIMES ) | ( ( WHILE | UNTIL ) expr= root_expression ) ) block[true] END
            {
            REPEAT23=(CommonTree)match(input,REPEAT,FOLLOW_REPEAT_in_loop_statement1705); 


            		((loop_statement_scope)loop_statement_stack.peek()).marker_top = (REPEAT23!=null?REPEAT23.getText():null) + labelCounter + "_top";
            		((loop_statement_scope)loop_statement_stack.peek()).marker_loop = (REPEAT23!=null?REPEAT23.getText():null) + labelCounter + "_loop";
            		((loop_statement_scope)loop_statement_stack.peek()).marker_bottom = (REPEAT23!=null?REPEAT23.getText():null) + labelCounter + "_bottom";
            		labelCounter++;
            		((loop_statement_scope)loop_statement_stack.peek()).location = new LineInformation(
            			REPEAT23.getLine(),
            			REPEAT23.getLine(),
            			REPEAT23.getCharPositionInLine(),
            			(REPEAT23!=null?REPEAT23.getText():null).length() + REPEAT23.getCharPositionInLine());
            		((loop_statement_scope)loop_statement_stack.peek()).loop_counter = null;
            		((loop_statement_scope)loop_statement_stack.peek()).jumpToTop = new JumpStep();
            		((loop_statement_scope)loop_statement_stack.peek()).jumpToTop.setLineInformation(((loop_statement_scope)loop_statement_stack.peek()).location);
            		symbol.addStatementFlagToCurrentFile(REPEAT23.getLine());

            		((loop_statement_scope)loop_statement_stack.peek()).first_value = null;
            		((loop_statement_scope)loop_statement_stack.peek()).first_step = null;
            		((loop_statement_scope)loop_statement_stack.peek()).last_value = null;
            		((loop_statement_scope)loop_statement_stack.peek()).last_step = null;
            		((loop_statement_scope)loop_statement_stack.peek()).type = -1;
            		((loop_statement_scope)loop_statement_stack.peek()).cJumpStep = null;
            		
            				symbol.getControlFlow().repeatStart();
            	
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1286:2: ( ( OVER ID ) | ( ( FROM ran= range ) ) | (expr= root_expression TIMES ) | ( ( WHILE | UNTIL ) expr= root_expression ) )
            int alt55=4;
            switch ( input.LA(1) ) {
            case OVER:
                {
                alt55=1;
                }
                break;
            case FROM:
                {
                alt55=2;
                }
                break;
            case ROOT_EXPRESSION:
                {
                alt55=3;
                }
                break;
            case WHILE:
            case UNTIL:
                {
                alt55=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }

            switch (alt55) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1288:2: ( OVER ID )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1288:2: ( OVER ID )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1288:4: OVER ID
                    {
                    match(input,OVER,FOLLOW_OVER_in_loop_statement1718); 
                    match(input,ID,FOLLOW_ID_in_loop_statement1720); 

                    }


                    		((loop_statement_scope)loop_statement_stack.peek()).type = 0;
                    	

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1292:4: ( ( FROM ran= range ) )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1292:4: ( ( FROM ran= range ) )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1292:5: ( FROM ran= range )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1292:5: ( FROM ran= range )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1292:6: FROM ran= range
                    {
                    match(input,FROM,FOLLOW_FROM_in_loop_statement1732); 
                    pushFollow(FOLLOW_range_in_loop_statement1738);
                    ran=range();

                    state._fsp--;


                    }


                    			((loop_statement_scope)loop_statement_stack.peek()).first_value = (ran!=null?ran.first_value:null);
                    		((loop_statement_scope)loop_statement_stack.peek()).first_step = (ran!=null?ran.first_step:null);
                    		((loop_statement_scope)loop_statement_stack.peek()).last_value = (ran!=null?ran.last_value:null);
                    		((loop_statement_scope)loop_statement_stack.peek()).last_step = (ran!=null?ran.last_step:null);
                    		
                    		((loop_statement_scope)loop_statement_stack.peek()).loop_counter = stepFactory.addLoopCounter(temp,((loop_statement_scope)loop_statement_stack.peek()).first_value,((loop_statement_scope)loop_statement_stack.peek()).location);
                    		builder.addLabel(((loop_statement_scope)loop_statement_stack.peek()).jumpToTop);
                    		temp = ((loop_statement_scope)loop_statement_stack.peek()).loop_counter.getNextRegister();
                    		ResultTuple jump_compare = stepFactory.addBinaryLessEqualsStep(temp, ((loop_statement_scope)loop_statement_stack.peek()).loop_counter.getValue(), ((loop_statement_scope)loop_statement_stack.peek()).loop_counter.getStep(),  ((loop_statement_scope)loop_statement_stack.peek()).last_value, ((loop_statement_scope)loop_statement_stack.peek()).last_step);
                    		temp = jump_compare.getNextRegister();
                    		((loop_statement_scope)loop_statement_stack.peek()).cJumpStep = new ConditionalJumpLoopStep();
                    		((loop_statement_scope)loop_statement_stack.peek()).cJumpStep.setLeftRegister(jump_compare.getValue().getRegister());
                    		((loop_statement_scope)loop_statement_stack.peek()).cJumpStep.setLineInformation(((loop_statement_scope)loop_statement_stack.peek()).location);
                    		((loop_statement_scope)loop_statement_stack.peek()).cJumpStep.setLoopType(LoopType.FROM);
                    		builder.add(((loop_statement_scope)loop_statement_stack.peek()).cJumpStep);
                    		builder.addStepLabel(OpcodeType.FROM);
                    		builder.addMarker(((loop_statement_scope)loop_statement_stack.peek()).marker_bottom);
                    		
                    		symbol.enterNextBlock();
                    		((loop_statement_scope)loop_statement_stack.peek()).type = 1;
                    		stepFactory.addBeginScopeStep(((loop_statement_scope)loop_statement_stack.peek()).marker_loop, "loop");
                    	

                    }


                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1316:4: (expr= root_expression TIMES )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1316:4: (expr= root_expression TIMES )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1316:5: expr= root_expression TIMES
                    {
                    pushFollow(FOLLOW_root_expression_in_loop_statement1753);
                    expr=root_expression();

                    state._fsp--;

                    match(input,TIMES,FOLLOW_TIMES_in_loop_statement1755); 

                    }


                    		((loop_statement_scope)loop_statement_stack.peek()).first_value = (expr!=null?expr.eval:null);
                    		((loop_statement_scope)loop_statement_stack.peek()).first_step = (expr!=null?expr.step:null);
                    		((loop_statement_scope)loop_statement_stack.peek()).loop_counter = stepFactory.addLoopCounter(temp,((loop_statement_scope)loop_statement_stack.peek()).location);
                    		builder.addLabel(((loop_statement_scope)loop_statement_stack.peek()).jumpToTop);
                    		temp = ((loop_statement_scope)loop_statement_stack.peek()).loop_counter.getNextRegister();
                    		ResultTuple jump_compare = stepFactory.addBinaryLessThanStep(temp, ((loop_statement_scope)loop_statement_stack.peek()).loop_counter.getValue(), ((loop_statement_scope)loop_statement_stack.peek()).loop_counter.getStep(),  ((loop_statement_scope)loop_statement_stack.peek()).first_value, ((loop_statement_scope)loop_statement_stack.peek()).first_step);
                    		temp = jump_compare.getNextRegister();
                    		((loop_statement_scope)loop_statement_stack.peek()).cJumpStep = new ConditionalJumpLoopStep();
                    		((loop_statement_scope)loop_statement_stack.peek()).cJumpStep.setIsEndValueKnown(true);
                    		((loop_statement_scope)loop_statement_stack.peek()).cJumpStep.setHowManyTimesRegister((expr!=null?expr.eval:null).getRegister());
                    		((loop_statement_scope)loop_statement_stack.peek()).cJumpStep.setLeftRegister(jump_compare.getValue().getRegister());
                    		((loop_statement_scope)loop_statement_stack.peek()).cJumpStep.setLineInformation(((loop_statement_scope)loop_statement_stack.peek()).location);
                    		((loop_statement_scope)loop_statement_stack.peek()).cJumpStep.setLoopType(LoopType.TIMES);
                    		builder.add(((loop_statement_scope)loop_statement_stack.peek()).cJumpStep);
                    		builder.addStepLabel(OpcodeType.TIMES);
                    		builder.addMarker(((loop_statement_scope)loop_statement_stack.peek()).marker_bottom);
                    		stepFactory.addBeginScopeStep(((loop_statement_scope)loop_statement_stack.peek()).marker_loop, "loop");
                    		symbol.enterNextBlock();
                    		((loop_statement_scope)loop_statement_stack.peek()).type = 1;
                    		
                    	

                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1339:4: ( ( WHILE | UNTIL ) expr= root_expression )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1339:4: ( ( WHILE | UNTIL ) expr= root_expression )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1339:5: ( WHILE | UNTIL ) expr= root_expression
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1339:5: ( WHILE | UNTIL )
                    int alt54=2;
                    int LA54_0 = input.LA(1);

                    if ( (LA54_0==WHILE) ) {
                        alt54=1;
                    }
                    else if ( (LA54_0==UNTIL) ) {
                        alt54=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 54, 0, input);

                        throw nvae;
                    }
                    switch (alt54) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1339:6: WHILE
                            {
                            match(input,WHILE,FOLLOW_WHILE_in_loop_statement1766); 
                            ((loop_statement_scope)loop_statement_stack.peek()).isWhile = true;

                            }
                            break;
                        case 2 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1339:49: UNTIL
                            {
                            match(input,UNTIL,FOLLOW_UNTIL_in_loop_statement1772); 
                            ((loop_statement_scope)loop_statement_stack.peek()).isWhile = false;

                            }
                            break;

                    }


                                   		builder.addLabel(((loop_statement_scope)loop_statement_stack.peek()).jumpToTop);
                    	
                    pushFollow(FOLLOW_root_expression_in_loop_statement1787);
                    expr=root_expression();

                    state._fsp--;


                    			((loop_statement_scope)loop_statement_stack.peek()).first_value = (expr!=null?expr.eval:null);
                    			((loop_statement_scope)loop_statement_stack.peek()).first_step = (expr!=null?expr.step:null);
                    			((loop_statement_scope)loop_statement_stack.peek()).type = 2;
                    			
                    			if(((loop_statement_scope)loop_statement_stack.peek()).isWhile){
                    				((loop_statement_scope)loop_statement_stack.peek()).cJumpStep = new ConditionalJumpLoopStep();
                    				((loop_statement_scope)loop_statement_stack.peek()).cJumpStep.setLoopType(LoopType.WHILE);
                    	
                    				((loop_statement_scope)loop_statement_stack.peek()).cJumpStep.setLeftRegister(((loop_statement_scope)loop_statement_stack.peek()).first_value.getRegister());
                    				((loop_statement_scope)loop_statement_stack.peek()).cJumpStep.setLineInformation(((loop_statement_scope)loop_statement_stack.peek()).location);
                    				builder.add(((loop_statement_scope)loop_statement_stack.peek()).cJumpStep);
                    			}else{
                    				((loop_statement_scope)loop_statement_stack.peek()).uJumpStep = new ConditionalJumpUntilStep();
                    				((loop_statement_scope)loop_statement_stack.peek()).uJumpStep.setLoopType(LoopType.UNTIL);
                    	
                    				((loop_statement_scope)loop_statement_stack.peek()).uJumpStep.setLeftRegister(((loop_statement_scope)loop_statement_stack.peek()).first_value.getRegister());
                    				((loop_statement_scope)loop_statement_stack.peek()).uJumpStep.setLineInformation(((loop_statement_scope)loop_statement_stack.peek()).location);
                    				builder.add(((loop_statement_scope)loop_statement_stack.peek()).uJumpStep);
                    			}
                    			builder.addStepLabel(OpcodeType.LOOP);
                    			builder.addMarker(((loop_statement_scope)loop_statement_stack.peek()).marker_bottom);
                    			stepFactory.addBeginScopeStep(((loop_statement_scope)loop_statement_stack.peek()).marker_loop, "loop");
                    			symbol.enterNextBlock();

                    		

                    }


                    }
                    break;

            }

            pushFollow(FOLLOW_block_in_loop_statement1798);
            block(true);

            state._fsp--;


            		stepFactory.addEndScopeStep("loop");
            		symbol.popScope();
            		if(((loop_statement_scope)loop_statement_stack.peek()).type == 1)
            		{
            			// move the ammount to increment to a register
            			ResultTuple inc_ammount = stepFactory.addMoveStep(temp, ((loop_statement_scope)loop_statement_stack.peek()).location, 1);
            			temp = inc_ammount.getNextRegister();
            			//add the increment ammount to the loop counter
            			ResultTuple inc_result = stepFactory.addBinaryAddStep(temp, inc_ammount.getValue(), inc_ammount.getStep(), ((loop_statement_scope)loop_statement_stack.peek()).loop_counter.getValue(), ((loop_statement_scope)loop_statement_stack.peek()).loop_counter.getStep());
            			int loopResult = temp;
            			temp = inc_result.getNextRegister();
            			//move the result to loop counters register
            			stepFactory.addMoveRegistersStep(((loop_statement_scope)loop_statement_stack.peek()).loop_counter.getValue().getRegister(), loopResult, ((loop_statement_scope)loop_statement_stack.peek()).location);
            		}
            		
            		builder.convertBackJump(((loop_statement_scope)loop_statement_stack.peek()).jumpToTop);
            		builder.add(((loop_statement_scope)loop_statement_stack.peek()).jumpToTop);
            		builder.addLabel(((loop_statement_scope)loop_statement_stack.peek()).marker_bottom);
            	
            match(input,END,FOLLOW_END_in_loop_statement1806); 

            		
            		symbol.getControlFlow().repeatEnd();
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            loop_statement_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "loop_statement"

    public static class range_return extends TreeRuleReturnScope {
        public ExpressionValue first_value;
        public ExecutionStep first_step;
        public ExpressionValue last_value;
        public ExecutionStep last_step;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "range"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1399:1: range returns [ExpressionValue first_value, ExecutionStep first_step, ExpressionValue last_value, ExecutionStep last_step] : ^( TO first= root_expression last= root_expression ) ;
    public final QuorumTreeWalker.range_return range() throws RecognitionException {
        QuorumTreeWalker.range_return retval = new QuorumTreeWalker.range_return();
        retval.start = input.LT(1);

        CommonTree TO24=null;
        QuorumTreeWalker.root_expression_return first = null;

        QuorumTreeWalker.root_expression_return last = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1400:2: ( ^( TO first= root_expression last= root_expression ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1400:4: ^( TO first= root_expression last= root_expression )
            {
            TO24=(CommonTree)match(input,TO,FOLLOW_TO_in_range1829); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_root_expression_in_range1835);
            first=root_expression();

            state._fsp--;


            pushFollow(FOLLOW_root_expression_in_range1842);
            last=root_expression();

            state._fsp--;


            		TypeCheckerResult result = typeChecker.check((first!=null?first.eval:null).getType(), (last!=null?last.eval:null).getType(), OperationEnum.RANGE, false);

            		if(result.getResult() == null)
            		{
            			CompilerError error = new CompilerError(TO24.getLine(), result.getErrorMessage(), result.getErrorType());
            			vm.getCompilerErrors().addError(error);
            		}

            		retval.first_value = (first!=null?first.eval:null);
            		retval.first_step = (first!=null?first.step:null);
            		retval.last_value = (last!=null?last.eval:null);
            		retval.last_step = (last!=null?last.step:null);
            	

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "range"

    public static class selector_return extends TreeRuleReturnScope {
        public ScopeSelector scopeSel;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "selector"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1418:1: selector returns [ScopeSelector scopeSel] : ( ^( PARENT qualified_name ) | ME );
    public final QuorumTreeWalker.selector_return selector() throws RecognitionException {
        QuorumTreeWalker.selector_return retval = new QuorumTreeWalker.selector_return();
        retval.start = input.LT(1);

        CommonTree PARENT26=null;
        QuorumTreeWalker.qualified_name_return qualified_name25 = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1419:2: ( ^( PARENT qualified_name ) | ME )
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==PARENT) ) {
                alt56=1;
            }
            else if ( (LA56_0==ME) ) {
                alt56=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;
            }
            switch (alt56) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1419:4: ^( PARENT qualified_name )
                    {
                    PARENT26=(CommonTree)match(input,PARENT,FOLLOW_PARENT_in_selector1863); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_qualified_name_in_selector1865);
                    qualified_name25=qualified_name();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		ScopeSelector scopeItem = new ScopeSelector();
                    		ClassDescriptor cd = symbol.findClassDescriptorFromCurrentClass((qualified_name25!=null?qualified_name25.type:null).getStaticKey());
                    		
                    		if(cd == null){
                    			CompilerError error = new CompilerError(PARENT26.getLine(), "The class "+ symbol.getCurrentClass().getStaticKey() +" does not have access to " + (qualified_name25!=null?qualified_name25.type:null).getStaticKey(), ErrorType.MISSING_PARENT);
                    			vm.getCompilerErrors().addError(error);
                    		}
                    		scopeItem.setIsParent(true);
                    		scopeItem.setCurrentClass(cd);
                    		retval.scopeSel = scopeItem;
                    	

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1432:4: ME
                    {
                    match(input,ME,FOLLOW_ME_in_selector1874); 

                    		ScopeSelector scopeItem = new ScopeSelector();
                    		scopeItem.setIsParent(false);
                    		retval.scopeSel = scopeItem;
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "selector"

    public static class root_expression_return extends TreeRuleReturnScope {
        public ExpressionValue eval;
        public ExecutionStep step;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "root_expression"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1440:1: root_expression returns [ExpressionValue eval, ExecutionStep step] : ^( ROOT_EXPRESSION expression ) ;
    public final QuorumTreeWalker.root_expression_return root_expression() throws RecognitionException {
        QuorumTreeWalker.root_expression_return retval = new QuorumTreeWalker.root_expression_return();
        retval.start = input.LT(1);

        QuorumTreeWalker.expression_return expression27 = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1441:2: ( ^( ROOT_EXPRESSION expression ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1442:2: ^( ROOT_EXPRESSION expression )
            {

            		builder.addStepLabel(OpcodeType.ROOT_EXPRESSION);
            	
            match(input,ROOT_EXPRESSION,FOLLOW_ROOT_EXPRESSION_in_root_expression1900); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_root_expression1902);
            expression27=expression();

            state._fsp--;


            match(input, Token.UP, null); 

            		
            		retval.eval = (expression27!=null?expression27.eval:null);
            		retval.step = (expression27!=null?expression27.step:null);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "root_expression"

    public static class expression_return extends TreeRuleReturnScope {
        public ExpressionValue eval;
        public ExecutionStep step;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "expression"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1453:1: expression returns [ExpressionValue eval, ExecutionStep step] : ( ^( UNARY_NOT NOT expr= expression ) | ^( EQUALITY left= expression right= expression ) | ^( NOTEQUALS left= expression right= expression ) | ^( GREATER left= expression right= expression ) | ^( GREATER_EQUAL left= expression right= expression ) | ^( INHERITS left= expression dec= class_type ) | ^( LESS left= expression right= expression ) | ^( LESS_EQUAL left= expression right= expression ) | ^( OR left= expression right= expression ) | ^( AND left= expression right= expression ) | ^( PLUS left= expression right= expression ) | ^( MINUS left= expression right= expression ) | ^( MULTIPLY left= expression right= expression ) | ^( DIVIDE left= expression right= expression ) | ^( MODULO left= expression right= expression ) | ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | ^( QUALIFIED_SOLO_EXPRESSION qualified_name ( COLON ID )? ) | ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name ) | ^( FUNCTION_CALL_PARENT PARENT COLON qn1= qualified_name COLON ID LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | BOOLEAN | ( MINUS )? DECIMAL | ( MINUS )? INT | STRING | NULL | ME | INPUT LEFT_PAREN input_expr= expression RIGHT_PAREN | CAST LEFT_PAREN castqn= assignment_declaration COMMA cast_expr= expression castrpn= RIGHT_PAREN );
    public final QuorumTreeWalker.expression_return expression() throws RecognitionException {
        QuorumTreeWalker.expression_return retval = new QuorumTreeWalker.expression_return();
        retval.start = input.LT(1);

        CommonTree castrpn=null;
        CommonTree ID29=null;
        CommonTree ID31=null;
        CommonTree ID34=null;
        CommonTree ID36=null;
        CommonTree BOOLEAN37=null;
        CommonTree DECIMAL38=null;
        CommonTree MINUS39=null;
        CommonTree INT40=null;
        CommonTree MINUS41=null;
        CommonTree STRING42=null;
        CommonTree NULL43=null;
        CommonTree ME44=null;
        CommonTree INPUT45=null;
        CommonTree RIGHT_PAREN46=null;
        CommonTree CAST47=null;
        QuorumTreeWalker.expression_return expr = null;

        QuorumTreeWalker.expression_return left = null;

        QuorumTreeWalker.expression_return right = null;

        QuorumTreeWalker.class_type_return dec = null;

        QuorumTreeWalker.function_expression_list_return fel = null;

        QuorumTreeWalker.qualified_name_return qn1 = null;

        QuorumTreeWalker.expression_return input_expr = null;

        QuorumTreeWalker.assignment_declaration_return castqn = null;

        QuorumTreeWalker.expression_return cast_expr = null;

        QuorumTreeWalker.qualified_name_return qualified_name28 = null;

        QuorumTreeWalker.qualified_name_return qualified_name30 = null;

        QuorumTreeWalker.qualified_name_return qualified_name32 = null;

        QuorumTreeWalker.selector_return selector33 = null;

        QuorumTreeWalker.qualified_name_return qualified_name35 = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1454:2: ( ^( UNARY_NOT NOT expr= expression ) | ^( EQUALITY left= expression right= expression ) | ^( NOTEQUALS left= expression right= expression ) | ^( GREATER left= expression right= expression ) | ^( GREATER_EQUAL left= expression right= expression ) | ^( INHERITS left= expression dec= class_type ) | ^( LESS left= expression right= expression ) | ^( LESS_EQUAL left= expression right= expression ) | ^( OR left= expression right= expression ) | ^( AND left= expression right= expression ) | ^( PLUS left= expression right= expression ) | ^( MINUS left= expression right= expression ) | ^( MULTIPLY left= expression right= expression ) | ^( DIVIDE left= expression right= expression ) | ^( MODULO left= expression right= expression ) | ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | ^( QUALIFIED_SOLO_EXPRESSION qualified_name ( COLON ID )? ) | ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name ) | ^( FUNCTION_CALL_PARENT PARENT COLON qn1= qualified_name COLON ID LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | BOOLEAN | ( MINUS )? DECIMAL | ( MINUS )? INT | STRING | NULL | ME | INPUT LEFT_PAREN input_expr= expression RIGHT_PAREN | CAST LEFT_PAREN castqn= assignment_declaration COMMA cast_expr= expression castrpn= RIGHT_PAREN )
            int alt62=28;
            alt62 = dfa62.predict(input);
            switch (alt62) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1455:3: ^( UNARY_NOT NOT expr= expression )
                    {
                    match(input,UNARY_NOT,FOLLOW_UNARY_NOT_in_expression1924); 

                    match(input, Token.DOWN, null); 
                    match(input,NOT,FOLLOW_NOT_in_expression1926); 
                    pushFollow(FOLLOW_expression_in_expression1932);
                    expr=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		ResultTuple result = stepFactory.addUnaryNotStep(temp, (expr!=null?expr.eval:null), (expr!=null?expr.step:null));
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1462:4: ^( EQUALITY left= expression right= expression )
                    {
                    match(input,EQUALITY,FOLLOW_EQUALITY_in_expression1944); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1950);
                    left=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1956);
                    right=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		ResultTuple result = stepFactory.addBinaryEqualsStep(temp, (left!=null?left.eval:null), (left!=null?left.step:null), (right!=null?right.eval:null), (right!=null?right.step:null));
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1469:4: ^( NOTEQUALS left= expression right= expression )
                    {
                    match(input,NOTEQUALS,FOLLOW_NOTEQUALS_in_expression1966); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1972);
                    left=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression1978);
                    right=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		ResultTuple result = stepFactory.addBinaryNotEqualsStep(temp, (left!=null?left.eval:null), (left!=null?left.step:null), (right!=null?right.eval:null), (right!=null?right.step:null));
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1476:4: ^( GREATER left= expression right= expression )
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_expression1988); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1994);
                    left=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression2000);
                    right=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		ResultTuple result = stepFactory.addBinaryGreaterThanStep(temp, (left!=null?left.eval:null), (left!=null?left.step:null), (right!=null?right.eval:null), (right!=null?right.step:null));
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 5 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1483:4: ^( GREATER_EQUAL left= expression right= expression )
                    {
                    match(input,GREATER_EQUAL,FOLLOW_GREATER_EQUAL_in_expression2010); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2016);
                    left=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression2022);
                    right=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		ResultTuple result = stepFactory.addBinaryGreaterEqualsStep(temp, (left!=null?left.eval:null), (left!=null?left.step:null), (right!=null?right.eval:null), (right!=null?right.step:null));
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 6 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1490:4: ^( INHERITS left= expression dec= class_type )
                    {
                    match(input,INHERITS,FOLLOW_INHERITS_in_expression2032); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2036);
                    left=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_class_type_in_expression2040);
                    dec=class_type();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		ResultTuple result = stepFactory.addBinaryIsAStep(temp, (left!=null?left.eval:null), (left!=null?left.step:null), (dec!=null?dec.myType:null));
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 7 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1497:4: ^( LESS left= expression right= expression )
                    {
                    match(input,LESS,FOLLOW_LESS_in_expression2050); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2056);
                    left=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression2062);
                    right=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		ResultTuple result = stepFactory.addBinaryLessThanStep(temp, (left!=null?left.eval:null), (left!=null?left.step:null), (right!=null?right.eval:null), (right!=null?right.step:null));
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 8 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1504:4: ^( LESS_EQUAL left= expression right= expression )
                    {
                    match(input,LESS_EQUAL,FOLLOW_LESS_EQUAL_in_expression2072); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2078);
                    left=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression2084);
                    right=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		ResultTuple result = stepFactory.addBinaryLessEqualsStep(temp, (left!=null?left.eval:null), (left!=null?left.step:null), (right!=null?right.eval:null), (right!=null?right.step:null));
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 9 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1511:4: ^( OR left= expression right= expression )
                    {
                    match(input,OR,FOLLOW_OR_in_expression2094); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2100);
                    left=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression2106);
                    right=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		ResultTuple result = stepFactory.addBinaryOrStep(temp, (left!=null?left.eval:null), (left!=null?left.step:null), (right!=null?right.eval:null), (right!=null?right.step:null));
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 10 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1518:4: ^( AND left= expression right= expression )
                    {
                    match(input,AND,FOLLOW_AND_in_expression2116); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2122);
                    left=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression2128);
                    right=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		ResultTuple result = stepFactory.addBinaryAndStep(temp, (left!=null?left.eval:null), (left!=null?left.step:null), (right!=null?right.eval:null), (right!=null?right.step:null));
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 11 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1525:4: ^( PLUS left= expression right= expression )
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_expression2138); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2144);
                    left=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression2150);
                    right=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		ResultTuple result = stepFactory.addBinaryAddStep(temp, (left!=null?left.eval:null), (left!=null?left.step:null), (right!=null?right.eval:null), (right!=null?right.step:null));
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 12 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1532:4: ^( MINUS left= expression right= expression )
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_expression2160); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2166);
                    left=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression2172);
                    right=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		ResultTuple result = stepFactory.addBinarySubtractStep(temp, (left!=null?left.eval:null), (left!=null?left.step:null), (right!=null?right.eval:null), (right!=null?right.step:null));
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 13 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1539:4: ^( MULTIPLY left= expression right= expression )
                    {
                    match(input,MULTIPLY,FOLLOW_MULTIPLY_in_expression2182); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2188);
                    left=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression2194);
                    right=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		ResultTuple result = stepFactory.addBinaryMultiplyStep(temp, (left!=null?left.eval:null), (left!=null?left.step:null), (right!=null?right.eval:null), (right!=null?right.step:null));
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 14 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1546:4: ^( DIVIDE left= expression right= expression )
                    {
                    match(input,DIVIDE,FOLLOW_DIVIDE_in_expression2204); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2210);
                    left=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression2216);
                    right=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		ResultTuple result = stepFactory.addBinaryDivideStep(temp, (left!=null?left.eval:null), (left!=null?left.step:null), (right!=null?right.eval:null), (right!=null?right.step:null));
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 15 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1553:4: ^( MODULO left= expression right= expression )
                    {
                    match(input,MODULO,FOLLOW_MODULO_in_expression2226); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2232);
                    left=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_expression2238);
                    right=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		ResultTuple result = stepFactory.addBinaryModStep(temp, (left!=null?left.eval:null), (left!=null?left.step:null), (right!=null?right.eval:null), (right!=null?right.step:null));
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 16 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1560:4: ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN fel= function_expression_list RIGHT_PAREN )
                    {
                    match(input,FUNCTION_CALL,FOLLOW_FUNCTION_CALL_in_expression2248); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_qualified_name_in_expression2250);
                    qualified_name28=qualified_name();

                    state._fsp--;

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1560:34: ( COLON ID )?
                    int alt57=2;
                    int LA57_0 = input.LA(1);

                    if ( (LA57_0==COLON) ) {
                        alt57=1;
                    }
                    switch (alt57) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1560:35: COLON ID
                            {
                            match(input,COLON,FOLLOW_COLON_in_expression2252); 
                            ID29=(CommonTree)match(input,ID,FOLLOW_ID_in_expression2254); 

                            }
                            break;

                    }

                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_expression2258); 

                    		boolean unsetFlag = false;
                    		boolean nested = inCallStep;
                    		if (!inCallStep) {
                    			// We are now inside a call step--set the flag appropriately.
                    			inCallStep = true;
                    			unsetFlag = true;
                    		}
                    	
                    pushFollow(FOLLOW_function_expression_list_in_expression2270);
                    fel=function_expression_list();

                    state._fsp--;

                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_expression2272); 

                    match(input, Token.UP, null); 
                    							//Dog.walk(50) should be dissallowed during semantic analysis
                    		LineInformation location = new LineInformation();
                                    location.setEndColumn((qualified_name28!=null?qualified_name28.type:null).getColumnEnd());
                                    location.setEndLine((qualified_name28!=null?qualified_name28.type:null).getLineEnd());
                                    location.setStartColumn((qualified_name28!=null?qualified_name28.type:null).getColumnBegin());
                                    location.setStartLine((qualified_name28!=null?qualified_name28.type:null).getLineBegin());
                                    location.setFile(fileName);
                                    location.setClassName(symbol.getCurrentClass().getStaticKey());
                                    MethodDescriptor curMethod = symbol.getCurrentMethod();
                                    if(curMethod != null){
                                    	location.setMethodName(curMethod.getStaticKey());
                                    }
                                    
                    		Vector<ExecutionStep> steps = new Vector<ExecutionStep>();
                    		Vector<ExpressionValue> values = new Vector<ExpressionValue>();
                    		Vector<Integer> registers = new Vector<Integer>();

                    		Vector<TypeDescriptor> types = new Vector<TypeDescriptor>();
                    		Vector<TypeDescriptor> argumentTypes = new Vector<TypeDescriptor>();
                    		
                    		int parameterPosition = -1;
                    		if(fel != null) {
                    			parameterPosition = (fel!=null?fel.firstParam:0);
                    			for(Object o : (fel!=null?fel.list:null)) {
                    				expression_return ex = (expression_return)o;
                                    		types.add(ex.eval.getType());
                                    		argumentTypes.add(ex.eval.getType());
                                    		steps.add(ex.step);
                                    		values.add(ex.eval);
                                    		registers.add(ex.eval.getRegister());
                    			}
                    		}
                    		
                                    String key = "";
                                    String myMethodName = "";
                                    if(ID29 == null) {
                                    	key = MethodDescriptor.autoGenerateKey((qualified_name28!=null?qualified_name28.type:null).getStaticKey(), types);
                                    	myMethodName = (qualified_name28!=null?qualified_name28.type:null).getStaticKey();
                    		}
                    		else {
                    			key = MethodDescriptor.autoGenerateKey((ID29!=null?ID29.getText():null), types);
                    			myMethodName = (ID29!=null?ID29.getText():null);
                    		}
                    		CallInfo info = new CallInfo();
                    		info.register = temp;
                    		info.location = location;
                    		info.argumentRegisters = registers;
                    		info.argumentSteps = steps;
                    		info.variable = (qualified_name28!=null?qualified_name28.type:null);
                    		info.argumentTypes = argumentTypes;
                    		info.methodName = myMethodName;
                    		info.isObjectCall = (ID29 != null);
                    		info.isNested = nested;
                    		
                    		if(fel!=null){
                    			builder.addCallLabel(parameterPosition);
                    			//j builder.addStepLabel(OpcodeType.METHOD_CALL);
                    		}
                    		
                    		ResultTuple result =  stepFactory.addCallStep(info);
                    		
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    		if(fel!=null){
                    			builder.addStepLabel(OpcodeType.METHOD_CALL);
                    		}
                    		
                    		if (unsetFlag)
                    			inCallStep = false;

                    	

                    }
                    break;
                case 17 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1643:4: ^( QUALIFIED_SOLO_EXPRESSION qualified_name ( COLON ID )? )
                    {
                    match(input,QUALIFIED_SOLO_EXPRESSION,FOLLOW_QUALIFIED_SOLO_EXPRESSION_in_expression2283); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_qualified_name_in_expression2285);
                    qualified_name30=qualified_name();

                    state._fsp--;

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1643:47: ( COLON ID )?
                    int alt58=2;
                    int LA58_0 = input.LA(1);

                    if ( (LA58_0==COLON) ) {
                        alt58=1;
                    }
                    switch (alt58) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1643:48: COLON ID
                            {
                            match(input,COLON,FOLLOW_COLON_in_expression2288); 
                            ID31=(CommonTree)match(input,ID,FOLLOW_ID_in_expression2290); 

                            }
                            break;

                    }


                    match(input, Token.UP, null); 

                    		LineInformation location = new LineInformation (
                    			(qualified_name30!=null?qualified_name30.type:null).getLineBegin(),
                    			(qualified_name30!=null?qualified_name30.type:null).getLineEnd(),
                    			(qualified_name30!=null?qualified_name30.type:null).getColumnBegin(),
                    			(qualified_name30!=null?qualified_name30.type:null).getColumnEnd()
                    		);
                    		location.setFile(fileName);
                                    location.setClassName(symbol.getCurrentClass().getStaticKey());
                                    MethodDescriptor curMethod = symbol.getCurrentMethod();
                                    if(curMethod != null){
                                    	location.setMethodName(curMethod.getStaticKey());
                                    }

                    		ResultTuple result = new ResultTuple();
                    		
                    		if(ID31 != null){
                    			result = stepFactory.addVariableInObjectMoveStep(location, null, null, temp, (qualified_name30!=null?qualified_name30.type:null), (ID31!=null?ID31.getText():null));
                    		}else{
                    			result = stepFactory.addVariableMoveStep(location, null, null, temp, (qualified_name30!=null?qualified_name30.type:null));
                    		}

                    		temp++;
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();


                    	

                    }
                    break;
                case 18 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1672:4: ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name )
                    {
                    match(input,QUALIFIED_SOLO_EXPRESSION_SELECTOR,FOLLOW_QUALIFIED_SOLO_EXPRESSION_SELECTOR_in_expression2302); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_selector_in_expression2304);
                    selector33=selector();

                    state._fsp--;

                    match(input,COLON,FOLLOW_COLON_in_expression2306); 
                    pushFollow(FOLLOW_qualified_name_in_expression2308);
                    qualified_name32=qualified_name();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		LineInformation location = new LineInformation (
                    			(qualified_name32!=null?qualified_name32.type:null).getLineBegin(),
                    			(qualified_name32!=null?qualified_name32.type:null).getLineEnd(),
                    			(qualified_name32!=null?qualified_name32.type:null).getColumnBegin(),
                    			(qualified_name32!=null?qualified_name32.type:null).getColumnEnd()
                    		);
                    		location.setFile(fileName);
                                    location.setClassName(symbol.getCurrentClass().getStaticKey());
                                    MethodDescriptor curMethod = symbol.getCurrentMethod();
                                    if(curMethod != null){
                                    	location.setMethodName(curMethod.getStaticKey());
                                    }
                    		
                    		ResultTuple result = new ResultTuple();
                    		ScopeSelector scope = (selector33!=null?selector33.scopeSel:null);
                    		
                    		if(!scope.isParent()){
                    			result = stepFactory.addMeVariableMoveStep(location, null, null, temp, (qualified_name32!=null?qualified_name32.type:null));
                    		} else{
                    			result = stepFactory.addParentVariableMoveStep(location, null, null, temp, (qualified_name32!=null?qualified_name32.type:null), scope.getCurrentClass());
                    		}
                    		temp++;
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    		
                    	

                    }
                    break;
                case 19 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1700:4: ^( FUNCTION_CALL_PARENT PARENT COLON qn1= qualified_name COLON ID LEFT_PAREN fel= function_expression_list RIGHT_PAREN )
                    {
                    match(input,FUNCTION_CALL_PARENT,FOLLOW_FUNCTION_CALL_PARENT_in_expression2318); 

                    match(input, Token.DOWN, null); 
                    match(input,PARENT,FOLLOW_PARENT_in_expression2320); 
                    match(input,COLON,FOLLOW_COLON_in_expression2322); 
                    pushFollow(FOLLOW_qualified_name_in_expression2326);
                    qn1=qualified_name();

                    state._fsp--;

                    match(input,COLON,FOLLOW_COLON_in_expression2328); 
                    ID34=(CommonTree)match(input,ID,FOLLOW_ID_in_expression2330); 
                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_expression2332); 
                    pushFollow(FOLLOW_function_expression_list_in_expression2338);
                    fel=function_expression_list();

                    state._fsp--;

                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_expression2340); 

                    match(input, Token.UP, null); 

                    		LineInformation location = new LineInformation();
                                    location.setEndColumn((qn1!=null?qn1.type:null).getColumnEnd());
                                    location.setEndLine((qn1!=null?qn1.type:null).getLineEnd());
                                    location.setStartColumn((qn1!=null?qn1.type:null).getColumnBegin());
                                    location.setStartLine((qn1!=null?qn1.type:null).getLineBegin());
                                    location.setFile(fileName);
                                    location.setClassName(symbol.getCurrentClass().getStaticKey());
                                    MethodDescriptor curMethod = symbol.getCurrentMethod();
                                    if(curMethod != null){
                                    	location.setMethodName(curMethod.getStaticKey());
                                    }

                    		Vector<ExecutionStep> steps = new Vector<ExecutionStep>();
                    		Vector<ExpressionValue> values = new Vector<ExpressionValue>();
                    		Vector<Integer> registers = new Vector<Integer>();

                    		Vector<String> types = new Vector<String>();
                    		Vector<TypeDescriptor> argumentTypes = new Vector<TypeDescriptor>();
                    		
                    		if(fel != null) {
                    			for(Object o : (fel!=null?fel.list:null)) {
                    				expression_return ex = (expression_return)o;
                                    		types.add(ex.eval.getType().getName());
                                    		argumentTypes.add(ex.eval.getType());
                                    		steps.add(ex.step);
                                    		values.add(ex.eval);
                                    		registers.add(ex.eval.getRegister());
                    			
                    			}
                    		}

                    		String key = MethodDescriptor.generateKey((ID34!=null?ID34.getText():null), types);
                    		String myMethodName = (ID34!=null?ID34.getText():null);
                    		
                    		CallInfo info = new CallInfo();
                    		info.register = temp;
                    		info.location = location;
                    		info.locatedIn = (qn1!=null?qn1.type:null).getStaticKey();
                    		info.argumentRegisters = registers;
                    		info.argumentSteps = steps;
                    		info.argumentTypes = argumentTypes;
                    		info.methodName = myMethodName;
                    		info.isObjectCall = false;
                    		
                    		ResultTuple result =  stepFactory.addParentCallStep(info);
                    		
                    		if(fel!=null){
                    			builder.addStepLabel(OpcodeType.METHOD_CALL);
                    		}
                    		
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 20 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1756:4: ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN fel= function_expression_list RIGHT_PAREN )
                    {
                    match(input,FUNCTION_CALL_THIS,FOLLOW_FUNCTION_CALL_THIS_in_expression2350); 

                    match(input, Token.DOWN, null); 
                    match(input,ME,FOLLOW_ME_in_expression2352); 
                    match(input,COLON,FOLLOW_COLON_in_expression2354); 
                    pushFollow(FOLLOW_qualified_name_in_expression2356);
                    qualified_name35=qualified_name();

                    state._fsp--;

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1756:49: ( COLON ID )?
                    int alt59=2;
                    int LA59_0 = input.LA(1);

                    if ( (LA59_0==COLON) ) {
                        alt59=1;
                    }
                    switch (alt59) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1756:50: COLON ID
                            {
                            match(input,COLON,FOLLOW_COLON_in_expression2359); 
                            ID36=(CommonTree)match(input,ID,FOLLOW_ID_in_expression2361); 

                            }
                            break;

                    }

                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_expression2365); 
                    pushFollow(FOLLOW_function_expression_list_in_expression2371);
                    fel=function_expression_list();

                    state._fsp--;

                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_expression2373); 

                    match(input, Token.UP, null); 

                    		LineInformation location = new LineInformation();
                                    location.setEndColumn((qualified_name35!=null?qualified_name35.type:null).getColumnEnd());
                                    location.setEndLine((qualified_name35!=null?qualified_name35.type:null).getLineEnd());
                                    location.setStartColumn((qualified_name35!=null?qualified_name35.type:null).getColumnBegin());
                                    location.setStartLine((qualified_name35!=null?qualified_name35.type:null).getLineBegin());
                                    location.setFile(fileName);
                                    location.setClassName(symbol.getCurrentClass().getStaticKey());
                                    MethodDescriptor curMethod = symbol.getCurrentMethod();
                                    if(curMethod != null){
                                    	location.setMethodName(curMethod.getStaticKey());
                                    }

                    		Vector<ExecutionStep> steps = new Vector<ExecutionStep>();
                    		Vector<ExpressionValue> values = new Vector<ExpressionValue>();
                    		Vector<Integer> registers = new Vector<Integer>();

                    		Vector<String> types = new Vector<String>();
                    		Vector<TypeDescriptor> argumentTypes = new Vector<TypeDescriptor>();
                    		
                    		if(fel != null) {
                    			for(Object o : (fel!=null?fel.list:null)) {
                    				expression_return ex = (expression_return)o;
                                    		types.add(ex.eval.getType().getName());
                                    		argumentTypes.add(ex.eval.getType());
                                    		steps.add(ex.step);
                                    		values.add(ex.eval);
                                    		registers.add(ex.eval.getRegister());
                    			
                    			}
                    		}

                    		String key = "";
                                    String myMethodName = "";
                                    if(ID36 == null) {
                                    	key = MethodDescriptor.generateKey((qualified_name35!=null?qualified_name35.type:null).getStaticKey(), types);
                                    	myMethodName = (qualified_name35!=null?qualified_name35.type:null).getStaticKey();
                    		}
                    		else {
                    			key = MethodDescriptor.generateKey((ID36!=null?ID36.getText():null), types);
                    			myMethodName = (ID36!=null?ID36.getText():null);
                    		}

                    		CallInfo info = new CallInfo();
                    		info.register = temp;
                    		info.location = location;
                    		info.argumentRegisters = registers;
                    		info.argumentSteps = steps;
                    		info.variable = (qualified_name35!=null?qualified_name35.type:null);
                    		info.argumentTypes = argumentTypes;
                    		info.methodName = myMethodName;
                    		info.isObjectCall = false;
                    		info.isThisCall = true;
                    		
                    		ResultTuple result =  stepFactory.addCallStep(info);
                    		
                    		if(fel!=null){
                    			builder.addStepLabel(OpcodeType.METHOD_CALL);
                    		}
                    		
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 21 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1821:4: BOOLEAN
                    {
                    BOOLEAN37=(CommonTree)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_expression2382); 

                    		LineInformation location = new LineInformation (
                    			BOOLEAN37.getLine(),
                    			BOOLEAN37.getLine(),
                    			BOOLEAN37.getCharPositionInLine(),
                    			BOOLEAN37.getCharPositionInLine() + (BOOLEAN37!=null?BOOLEAN37.getText():null).length()
                    		);
                    		location.setFile(fileName);
                    		
                    		ResultTuple result = stepFactory.addMoveStep(temp, location, Boolean.parseBoolean((BOOLEAN37!=null?BOOLEAN37.getText():null)));
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 22 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1836:4: ( MINUS )? DECIMAL
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1836:4: ( MINUS )?
                    int alt60=2;
                    int LA60_0 = input.LA(1);

                    if ( (LA60_0==MINUS) ) {
                        alt60=1;
                    }
                    switch (alt60) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1836:5: MINUS
                            {
                            MINUS39=(CommonTree)match(input,MINUS,FOLLOW_MINUS_in_expression2391); 

                            }
                            break;

                    }

                    DECIMAL38=(CommonTree)match(input,DECIMAL,FOLLOW_DECIMAL_in_expression2395); 

                    		LineInformation location = new LineInformation (
                    			DECIMAL38.getLine(),
                    			DECIMAL38.getLine(),
                    			DECIMAL38.getCharPositionInLine(),
                    			DECIMAL38.getCharPositionInLine() + (DECIMAL38!=null?DECIMAL38.getText():null).length()
                    		);
                    		location.setFile(fileName);
                    		
                    		double val = -1;
                    		try{
                    			val = Double.parseDouble((DECIMAL38!=null?DECIMAL38.getText():null));
                    		}catch(NumberFormatException e){
                    			CompilerError error = new CompilerError();
                    			error.setLineNumber(DECIMAL38.getLine());
                    			error.setError((DECIMAL38!=null?DECIMAL38.getText():null) + " is an invalid number.");
                    			error.setErrorType(ErrorType.INCOMPATIBLE_TYPES);
                    			error.setColumn(DECIMAL38.getCharPositionInLine());
                    			error.setFile(getGrammarFileNameNoExtension());
                    			vm.getCompilerErrors().addError(error);
                    		}
                    		
                    		if(MINUS39 != null) {
                    			val *= -1;
                    		}
                    		ResultTuple result = stepFactory.addMoveStep(temp, location, val);
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 23 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1867:4: ( MINUS )? INT
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1867:4: ( MINUS )?
                    int alt61=2;
                    int LA61_0 = input.LA(1);

                    if ( (LA61_0==MINUS) ) {
                        alt61=1;
                    }
                    switch (alt61) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1867:5: MINUS
                            {
                            MINUS41=(CommonTree)match(input,MINUS,FOLLOW_MINUS_in_expression2404); 

                            }
                            break;

                    }

                    INT40=(CommonTree)match(input,INT,FOLLOW_INT_in_expression2408); 

                    		LineInformation location = new LineInformation (
                    			INT40.getLine(),
                    			INT40.getLine(),
                    			INT40.getCharPositionInLine(),
                    			INT40.getCharPositionInLine() + (INT40!=null?INT40.getText():null).length()
                    		);
                    		location.setFile(fileName);
                    		int val = -1;
                    		try{
                    			val = Integer.parseInt((INT40!=null?INT40.getText():null));
                    		}catch(NumberFormatException e){
                    			CompilerError error = new CompilerError();
                    			error.setLineNumber(INT40.getLine());
                    			error.setError((INT40!=null?INT40.getText():null) + " is an invalid integer. The integer may be too large.");
                    			error.setErrorType(ErrorType.INCOMPATIBLE_TYPES);
                    			error.setColumn(INT40.getCharPositionInLine());
                    			error.setFile(getGrammarFileNameNoExtension());
                    			vm.getCompilerErrors().addError(error);
                    		}
                    		if(MINUS41 != null) {
                    			val *= -1;
                    		}
                    		ResultTuple result = stepFactory.addMoveStep(temp, location, val);
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 24 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1896:4: STRING
                    {
                    STRING42=(CommonTree)match(input,STRING,FOLLOW_STRING_in_expression2416); 

                    		LineInformation location = new LineInformation (
                    			STRING42.getLine(),
                    			STRING42.getLine(),
                    			STRING42.getCharPositionInLine(),
                    			STRING42.getCharPositionInLine() + (STRING42!=null?STRING42.getText():null).length()
                    		);
                    		location.setFile(fileName);
                    		ResultTuple result = stepFactory.addMoveStep(temp, location, (STRING42!=null?STRING42.getText():null));
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 25 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1910:4: NULL
                    {
                    NULL43=(CommonTree)match(input,NULL,FOLLOW_NULL_in_expression2424); 

                    		LineInformation location = new LineInformation (
                    			NULL43.getLine(),
                    			NULL43.getLine(),
                    			NULL43.getCharPositionInLine(),
                    			NULL43.getCharPositionInLine() + (NULL43!=null?NULL43.getText():null).length()
                    		);
                    		location.setFile(fileName);
                    		ResultTuple result = stepFactory.addMoveNullStep(temp, location);
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 26 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1924:4: ME
                    {
                    ME44=(CommonTree)match(input,ME,FOLLOW_ME_in_expression2432); 

                    		LineInformation location = new LineInformation (
                    			ME44.getLine(),
                    			ME44.getLine(),
                    			ME44.getCharPositionInLine(),
                    			ME44.getCharPositionInLine() + (ME44!=null?ME44.getText():null).length()
                    		);
                    		location.setFile(fileName);
                    		
                    		ResultTuple result = new ResultTuple();

                    		result = stepFactory.addMoveThisStep(location, temp);

                    		temp++;
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 27 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1942:4: INPUT LEFT_PAREN input_expr= expression RIGHT_PAREN
                    {
                    INPUT45=(CommonTree)match(input,INPUT,FOLLOW_INPUT_in_expression2440); 
                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_expression2442); 
                    pushFollow(FOLLOW_expression_in_expression2446);
                    input_expr=expression();

                    state._fsp--;

                    RIGHT_PAREN46=(CommonTree)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_expression2448); 

                    		LineInformation location = new LineInformation (
                    			INPUT45.getLine(),
                    			RIGHT_PAREN46.getLine(),
                    			INPUT45.getCharPositionInLine(),
                    			RIGHT_PAREN46.getCharPositionInLine()
                    		);
                    		location.setFile(fileName);
                    		ExecutionStep step = (input_expr!=null?input_expr.step:null);
                    		ExpressionValue value = (input_expr!=null?input_expr.eval:null);

                    		ResultTuple result = stepFactory.addInputStep(location, value, step, temp);
                    		
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 28 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1960:4: CAST LEFT_PAREN castqn= assignment_declaration COMMA cast_expr= expression castrpn= RIGHT_PAREN
                    {
                    CAST47=(CommonTree)match(input,CAST,FOLLOW_CAST_in_expression2456); 
                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_expression2458); 
                    pushFollow(FOLLOW_assignment_declaration_in_expression2462);
                    castqn=assignment_declaration();

                    state._fsp--;

                    match(input,COMMA,FOLLOW_COMMA_in_expression2464); 
                    pushFollow(FOLLOW_expression_in_expression2468);
                    cast_expr=expression();

                    state._fsp--;

                    castrpn=(CommonTree)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_expression2472); 

                    		LineInformation location = new LineInformation (
                    			CAST47.getLine(),
                    			castrpn.getLine(),
                    			CAST47.getCharPositionInLine(),
                    			castrpn.getCharPositionInLine()
                    		);
                    		location.setFile(fileName);
                    		TypeDescriptor type = (castqn!=null?castqn.myType:null);
                    		ExecutionStep step = (cast_expr!=null?cast_expr.step:null);
                    		ExpressionValue value = (cast_expr!=null?cast_expr.eval:null);

                    		ResultTuple result = stepFactory.addCastStep(location, type, value, step, temp);
                    		
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "expression"

    public static class function_expression_list_return extends TreeRuleReturnScope {
        public List list;
        public int firstParam;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "function_expression_list"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1981:1: function_expression_list returns [List list, int firstParam] : ^( FUNCTION_EXPRESSION_LIST (e= expression )* ) ;
    public final QuorumTreeWalker.function_expression_list_return function_expression_list() throws RecognitionException {
        QuorumTreeWalker.function_expression_list_return retval = new QuorumTreeWalker.function_expression_list_return();
        retval.start = input.LT(1);

        QuorumTreeWalker.expression_return e = null;


        retval.list = new ArrayList(); retval.firstParam = -1;
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1983:2: ( ^( FUNCTION_EXPRESSION_LIST (e= expression )* ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1984:2: ^( FUNCTION_EXPRESSION_LIST (e= expression )* )
            {
            match(input,FUNCTION_EXPRESSION_LIST,FOLLOW_FUNCTION_EXPRESSION_LIST_in_function_expression_list2496); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1984:29: (e= expression )*
                loop63:
                do {
                    int alt63=2;
                    int LA63_0 = input.LA(1);

                    if ( ((LA63_0>=FUNCTION_CALL && LA63_0<=FUNCTION_CALL_THIS)||LA63_0==UNARY_NOT||(LA63_0>=QUALIFIED_SOLO_EXPRESSION && LA63_0<=QUALIFIED_SOLO_EXPRESSION_SELECTOR)||LA63_0==INHERITS||LA63_0==ME||LA63_0==OR||(LA63_0>=LESS && LA63_0<=GREATER)||LA63_0==EQUALITY||(LA63_0>=AND && LA63_0<=MODULO)||(LA63_0>=CAST && LA63_0<=INPUT)) ) {
                        alt63=1;
                    }


                    switch (alt63) {
                	case 1 :
                	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1985:2: e= expression
                	    {

                	    		if(retval.list.size() >= 1){
                	    			inCallStep = false;
                	    		}
                	    	
                	    pushFollow(FOLLOW_expression_in_function_expression_list2508);
                	    e=expression();

                	    state._fsp--;


                	    		retval.list.add(e);
                	    		if(retval.list.size() == 1){
                	    			retval.firstParam = builder.addParameterLabel() - 1;
                	    		}
                	    	

                	    }
                	    break;

                	default :
                	    break loop63;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "function_expression_list"

    public static class formal_parameter_return extends TreeRuleReturnScope {
        public TypeDescriptor type;
        public String name;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "formal_parameter"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2000:1: formal_parameter returns [TypeDescriptor type, String name] : ^( FPARAM ad= assignment_declaration ID ) ;
    public final QuorumTreeWalker.formal_parameter_return formal_parameter() throws RecognitionException {
        QuorumTreeWalker.formal_parameter_return retval = new QuorumTreeWalker.formal_parameter_return();
        retval.start = input.LT(1);

        CommonTree ID48=null;
        QuorumTreeWalker.assignment_declaration_return ad = null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2001:2: ( ^( FPARAM ad= assignment_declaration ID ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2001:6: ^( FPARAM ad= assignment_declaration ID )
            {
            match(input,FPARAM,FOLLOW_FPARAM_in_formal_parameter2537); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_assignment_declaration_in_formal_parameter2541);
            ad=assignment_declaration();

            state._fsp--;

            ID48=(CommonTree)match(input,ID,FOLLOW_ID_in_formal_parameter2543); 

            match(input, Token.UP, null); 
            	
            		retval.type = (ad!=null?ad.myType:null);
            		retval.name = (ID48!=null?ID48.getText():null);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "formal_parameter"

    // Delegated rules


    protected DFA4 dfa4 = new DFA4(this);
    protected DFA51 dfa51 = new DFA51(this);
    protected DFA62 dfa62 = new DFA62(this);
    static final String DFA4_eotS =
        "\24\uffff";
    static final String DFA4_eofS =
        "\24\uffff";
    static final String DFA4_minS =
        "\1\10\2\13\1\uffff\2\2\2\33\2\3\1\33\1\10\1\33\1\10\1\3\2\uffff"+
        "\1\3\2\uffff";
    static final String DFA4_maxS =
        "\1\103\2\13\1\uffff\2\2\2\33\2\50\1\33\1\103\1\33\1\103\1\50\2\uffff"+
        "\1\50\2\uffff";
    static final String DFA4_acceptS =
        "\3\uffff\1\5\13\uffff\1\1\1\3\1\uffff\1\2\1\4";
    static final String DFA4_specialS =
        "\24\uffff}>";
    static final String[] DFA4_transitionS = {
            "\4\3\14\uffff\1\1\1\2\2\3\3\uffff\3\3\3\uffff\3\3\2\uffff\2"+
            "\3\3\uffff\2\3\4\uffff\3\3\3\uffff\4\3\1\uffff\1\3\2\uffff\1"+
            "\3",
            "\1\4",
            "\1\5",
            "",
            "\1\6",
            "\1\7",
            "\1\10",
            "\1\11",
            "\1\13\44\uffff\1\12",
            "\1\15\44\uffff\1\14",
            "\1\16",
            "\4\20\15\uffff\1\17\2\20\3\uffff\3\20\3\uffff\3\20\2\uffff"+
            "\2\20\3\uffff\2\20\4\uffff\3\20\3\uffff\4\20\1\uffff\1\20\2"+
            "\uffff\1\20",
            "\1\21",
            "\4\23\14\uffff\1\22\1\2\2\23\3\uffff\3\23\3\uffff\3\23\2\uffff"+
            "\2\23\3\uffff\2\23\4\uffff\3\23\3\uffff\4\23\1\uffff\1\23\2"+
            "\uffff\1\23",
            "\1\13\44\uffff\1\12",
            "",
            "",
            "\1\15\44\uffff\1\14",
            "",
            ""
    };

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "63:9: ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ | )";
        }
    }
    static final String DFA51_eotS =
        "\12\uffff";
    static final String DFA51_eofS =
        "\12\uffff";
    static final String DFA51_minS =
        "\1\13\1\uffff\1\2\1\uffff\1\33\1\3\1\33\1\27\1\3\1\uffff";
    static final String DFA51_maxS =
        "\1\76\1\uffff\1\2\1\uffff\1\33\1\50\1\33\1\51\1\50\1\uffff";
    static final String DFA51_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\5\uffff\1\2";
    static final String DFA51_specialS =
        "\12\uffff}>";
    static final String[] DFA51_transitionS = {
            "\1\2\17\uffff\1\1\3\uffff\2\3\11\uffff\2\1\17\uffff\4\3",
            "",
            "\1\4",
            "",
            "\1\5",
            "\1\7\44\uffff\1\6",
            "\1\10",
            "\1\3\3\uffff\1\3\15\uffff\1\11",
            "\1\7\44\uffff\1\6",
            ""
    };

    static final short[] DFA51_eot = DFA.unpackEncodedString(DFA51_eotS);
    static final short[] DFA51_eof = DFA.unpackEncodedString(DFA51_eofS);
    static final char[] DFA51_min = DFA.unpackEncodedStringToUnsignedChars(DFA51_minS);
    static final char[] DFA51_max = DFA.unpackEncodedStringToUnsignedChars(DFA51_maxS);
    static final short[] DFA51_accept = DFA.unpackEncodedString(DFA51_acceptS);
    static final short[] DFA51_special = DFA.unpackEncodedString(DFA51_specialS);
    static final short[][] DFA51_transition;

    static {
        int numStates = DFA51_transitionS.length;
        DFA51_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA51_transition[i] = DFA.unpackEncodedString(DFA51_transitionS[i]);
        }
    }

    class DFA51 extends DFA {

        public DFA51(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 51;
            this.eot = DFA51_eot;
            this.eof = DFA51_eof;
            this.min = DFA51_min;
            this.max = DFA51_max;
            this.accept = DFA51_accept;
            this.special = DFA51_special;
            this.transition = DFA51_transition;
        }
        public String getDescription() {
            return "1018:1: assignment_statement : ( (sel= selector COLON )? ID rhs= assign_right_hand_side | obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side | (modifier= access_modifier )? type= assignment_declaration name= ID (rhs= assign_right_hand_side )? );";
        }
    }
    static final String DFA62_eotS =
        "\36\uffff";
    static final String DFA62_eofS =
        "\36\uffff";
    static final String DFA62_minS =
        "\1\4\13\uffff\1\2\21\uffff";
    static final String DFA62_maxS =
        "\1\132\13\uffff\1\127\21\uffff";
    static final String DFA62_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\uffff"+
        "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
        "\1\32\1\33\1\34\1\14";
    static final String DFA62_specialS =
        "\36\uffff}>";
    static final String[] DFA62_transitionS = {
            "\1\20\1\23\1\24\11\uffff\1\1\4\uffff\1\21\1\22\6\uffff\1\6\15"+
            "\uffff\1\32\10\uffff\1\11\4\uffff\1\7\1\4\4\uffff\1\2\12\uffff"+
            "\1\12\1\3\1\5\1\10\1\13\1\14\1\15\1\16\1\17\1\uffff\1\34\1\27"+
            "\1\25\1\26\1\30\1\31\1\33",
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
            "\1\35\122\uffff\1\27\1\uffff\1\26",
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
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA62_eot = DFA.unpackEncodedString(DFA62_eotS);
    static final short[] DFA62_eof = DFA.unpackEncodedString(DFA62_eofS);
    static final char[] DFA62_min = DFA.unpackEncodedStringToUnsignedChars(DFA62_minS);
    static final char[] DFA62_max = DFA.unpackEncodedStringToUnsignedChars(DFA62_maxS);
    static final short[] DFA62_accept = DFA.unpackEncodedString(DFA62_acceptS);
    static final short[] DFA62_special = DFA.unpackEncodedString(DFA62_specialS);
    static final short[][] DFA62_transition;

    static {
        int numStates = DFA62_transitionS.length;
        DFA62_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA62_transition[i] = DFA.unpackEncodedString(DFA62_transitionS[i]);
        }
    }

    class DFA62 extends DFA {

        public DFA62(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 62;
            this.eot = DFA62_eot;
            this.eof = DFA62_eof;
            this.min = DFA62_min;
            this.max = DFA62_max;
            this.accept = DFA62_accept;
            this.special = DFA62_special;
            this.transition = DFA62_transition;
        }
        public String getDescription() {
            return "1453:1: expression returns [ExpressionValue eval, ExecutionStep step] : ( ^( UNARY_NOT NOT expr= expression ) | ^( EQUALITY left= expression right= expression ) | ^( NOTEQUALS left= expression right= expression ) | ^( GREATER left= expression right= expression ) | ^( GREATER_EQUAL left= expression right= expression ) | ^( INHERITS left= expression dec= class_type ) | ^( LESS left= expression right= expression ) | ^( LESS_EQUAL left= expression right= expression ) | ^( OR left= expression right= expression ) | ^( AND left= expression right= expression ) | ^( PLUS left= expression right= expression ) | ^( MINUS left= expression right= expression ) | ^( MULTIPLY left= expression right= expression ) | ^( DIVIDE left= expression right= expression ) | ^( MODULO left= expression right= expression ) | ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | ^( QUALIFIED_SOLO_EXPRESSION qualified_name ( COLON ID )? ) | ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name ) | ^( FUNCTION_CALL_PARENT PARENT COLON qn1= qualified_name COLON ID LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | BOOLEAN | ( MINUS )? DECIMAL | ( MINUS )? INT | STRING | NULL | ME | INPUT LEFT_PAREN input_expr= expression RIGHT_PAREN | CAST LEFT_PAREN castqn= assignment_declaration COMMA cast_expr= expression castrpn= RIGHT_PAREN );";
        }
    }
 

    public static final BitSet FOLLOW_package_rule_in_start51 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_reference_in_start53 = new BitSet(new long[]{0x78E18CE38E000F00L,0x0000000000000009L});
    public static final BitSet FOLLOW_reference_in_start59 = new BitSet(new long[]{0x0000000003000000L});
    public static final BitSet FOLLOW_package_rule_in_start62 = new BitSet(new long[]{0x78E18CE38C000F00L,0x0000000000000009L});
    public static final BitSet FOLLOW_package_rule_in_start67 = new BitSet(new long[]{0x78E18CE38C000F00L,0x0000000000000009L});
    public static final BitSet FOLLOW_reference_in_start72 = new BitSet(new long[]{0x78E18CE38E000F00L,0x0000000000000009L});
    public static final BitSet FOLLOW_class_declaration_in_start81 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_start84 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PACKAGE_NAME_in_package_rule96 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_package_rule100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_USE_in_reference113 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_reference115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_in_class_declaration131 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_class_declaration133 = new BitSet(new long[]{0x78E18CE3BC800F00L,0x0000000000000009L});
    public static final BitSet FOLLOW_generic_declaration_in_class_declaration139 = new BitSet(new long[]{0x78E18CE3BC000F00L,0x0000000000000009L});
    public static final BitSet FOLLOW_inherit_stmnts_in_class_declaration146 = new BitSet(new long[]{0x78E18CE39C000F00L,0x0000000000000009L});
    public static final BitSet FOLLOW_class_stmnts_in_class_declaration153 = new BitSet(new long[]{0x78E18CE39C000F00L,0x0000000000000009L});
    public static final BitSet FOLLOW_END_in_class_declaration156 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_no_class_stmnts_in_class_declaration169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_no_class_stmnts189 = new BitSet(new long[]{0x78E18C0188000F02L,0x0000000000000009L});
    public static final BitSet FOLLOW_access_modifier_in_no_class_stmnts203 = new BitSet(new long[]{0x78E18CE38C000F00L,0x0000000000000009L});
    public static final BitSet FOLLOW_method_declaration_in_no_class_stmnts211 = new BitSet(new long[]{0x78E18CE38C000F02L,0x0000000000000009L});
    public static final BitSet FOLLOW_INHERITS_in_inherit_stmnts225 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_qualified_name_in_inherit_stmnts233 = new BitSet(new long[]{0x0000000000800808L});
    public static final BitSet FOLLOW_generic_statement_in_inherit_stmnts239 = new BitSet(new long[]{0x0000000000000808L});
    public static final BitSet FOLLOW_PUBLIC_in_access_modifier257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIVATE_in_access_modifier265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_statement_in_class_stmnts279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_access_modifier_in_class_stmnts288 = new BitSet(new long[]{0x78E18CE38C000F00L,0x0000000000000009L});
    public static final BitSet FOLLOW_method_declaration_in_class_stmnts296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_method_declaration314 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_method_declaration320 = new BitSet(new long[]{0x000000100000A000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration325 = new BitSet(new long[]{0x000000100000A000L});
    public static final BitSet FOLLOW_RETURNS_in_method_declaration338 = new BitSet(new long[]{0x78000C0188000800L});
    public static final BitSet FOLLOW_assignment_declaration_in_method_declaration342 = new BitSet(new long[]{0x000000100000A000L});
    public static final BitSet FOLLOW_block_in_method_declaration348 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_method_declaration351 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BLUEPRINT_in_method_declaration366 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ACTION_in_method_declaration368 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_method_declaration375 = new BitSet(new long[]{0x0000001000008008L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration380 = new BitSet(new long[]{0x0000001000008008L});
    public static final BitSet FOLLOW_RETURNS_in_method_declaration389 = new BitSet(new long[]{0x78000C0188000800L});
    public static final BitSet FOLLOW_assignment_declaration_in_method_declaration391 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NATIVE_in_method_declaration400 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ACTION_in_method_declaration402 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_method_declaration409 = new BitSet(new long[]{0x0000001000008008L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration414 = new BitSet(new long[]{0x0000001000008008L});
    public static final BitSet FOLLOW_RETURNS_in_method_declaration423 = new BitSet(new long[]{0x78000C0188000800L});
    public static final BitSet FOLLOW_assignment_declaration_in_method_declaration425 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ON_CREATE_in_method_declaration434 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_method_declaration441 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_method_declaration444 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_QUALIFIED_NAME_in_qualified_name467 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_qualified_name472 = new BitSet(new long[]{0x0000010000000008L});
    public static final BitSet FOLLOW_PERIOD_in_qualified_name475 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_qualified_name479 = new BitSet(new long[]{0x0000010000000008L});
    public static final BitSet FOLLOW_STATEMENT_LIST_in_block498 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_block505 = new BitSet(new long[]{0x78E18C0188000F08L,0x0000000000000009L});
    public static final BitSet FOLLOW_solo_method_call_in_statement522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_statement_in_statement527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_statement_in_statement532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_loop_statement_in_statement537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_statement_in_statement542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_print_statement_in_statement547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_speak_statement_in_statement552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_check_statement_in_statement557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_alert_statement_in_statement562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SOLO_FUNCTION_CALL_in_solo_method_call583 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_qualified_name_in_solo_method_call585 = new BitSet(new long[]{0x0000020400000000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call588 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_solo_method_call590 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call594 = new BitSet(new long[]{0x8610080820610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_solo_method_call604 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call614 = new BitSet(new long[]{0x8610080020610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_solo_method_call620 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call634 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SOLO_FUNCTION_CALL_PARENT_in_solo_method_call646 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_PARENT_in_solo_method_call648 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call650 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_solo_method_call652 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call654 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_solo_method_call656 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call658 = new BitSet(new long[]{0x8610080820610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_solo_method_call668 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call675 = new BitSet(new long[]{0x8610080020610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_solo_method_call681 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call691 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SOLO_FUNCTION_CALL_THIS_in_solo_method_call702 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ME_in_solo_method_call704 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call706 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_solo_method_call708 = new BitSet(new long[]{0x0000020400000000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call711 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_solo_method_call713 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call717 = new BitSet(new long[]{0x8610080820610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_solo_method_call727 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call734 = new BitSet(new long[]{0x8610080020610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_solo_method_call740 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call750 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ALERT_in_alert_statement770 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_alert_statement778 = new BitSet(new long[]{0x8610080020610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_alert_statement782 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_alert_statement790 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CHECK_in_check_statement822 = new BitSet(new long[]{0x000000100000A000L});
    public static final BitSet FOLLOW_block_in_check_statement828 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_check_statement840 = new BitSet(new long[]{0x0006000000000000L});
    public static final BitSet FOLLOW_DETECT_in_check_statement860 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_detect_parameter_in_check_statement887 = new BitSet(new long[]{0x000000100000A000L});
    public static final BitSet FOLLOW_block_in_check_statement911 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_check_statement933 = new BitSet(new long[]{0x0006000000000002L});
    public static final BitSet FOLLOW_ALWAYS_in_check_statement964 = new BitSet(new long[]{0x000000100000A000L});
    public static final BitSet FOLLOW_block_in_check_statement980 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_check_statement997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALWAYS_in_check_statement1041 = new BitSet(new long[]{0x000000100000A000L});
    public static final BitSet FOLLOW_block_in_check_statement1064 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_check_statement1067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_detect_parameter1110 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_OF_TYPE_in_detect_parameter1115 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_detect_parameter1124 = new BitSet(new long[]{0x0010000000000008L});
    public static final BitSet FOLLOW_OR_in_detect_parameter1131 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_detect_parameter1135 = new BitSet(new long[]{0x0010000000000008L});
    public static final BitSet FOLLOW_PRINT_in_print_statement1162 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_root_expression_in_print_statement1164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAY_in_speak_statement1180 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_root_expression_in_speak_statement1182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_return_statement1196 = new BitSet(new long[]{0x0100000000100000L});
    public static final BitSet FOLLOW_root_expression_in_return_statement1199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOW_in_return_statement1207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GENERIC_in_generic_declaration1225 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_LESS_in_generic_declaration1227 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_generic_declaration1231 = new BitSet(new long[]{0x0400000040000000L});
    public static final BitSet FOLLOW_COMMA_in_generic_declaration1234 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_generic_declaration1238 = new BitSet(new long[]{0x0400000040000000L});
    public static final BitSet FOLLOW_GREATER_in_generic_declaration1242 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GENERIC_in_generic_statement1260 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_LESS_in_generic_statement1262 = new BitSet(new long[]{0x78000C0188000800L});
    public static final BitSet FOLLOW_assignment_declaration_in_generic_statement1271 = new BitSet(new long[]{0x0400000040000000L});
    public static final BitSet FOLLOW_COMMA_in_generic_statement1279 = new BitSet(new long[]{0x78000C0188000800L});
    public static final BitSet FOLLOW_assignment_declaration_in_generic_statement1283 = new BitSet(new long[]{0x0400000040000000L});
    public static final BitSet FOLLOW_GREATER_in_generic_statement1299 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_qualified_name_in_class_type1324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_assignment_declaration1346 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_generic_statement_in_assignment_declaration1350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_KEYWORD_in_assignment_declaration1359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_KEYWORD_in_assignment_declaration1367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TEXT_in_assignment_declaration1375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_KEYWORD_in_assignment_declaration1383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_assignment_statement1399 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1401 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_assignment_statement1405 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_assign_right_hand_side_in_assignment_statement1409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_assignment_statement1419 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1422 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_PARENT_in_assignment_statement1424 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1426 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_assignment_statement1430 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1434 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_assignment_statement1436 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_assign_right_hand_side_in_assignment_statement1440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_access_modifier_in_assignment_statement1452 = new BitSet(new long[]{0x78000C0188000800L});
    public static final BitSet FOLLOW_assignment_declaration_in_assignment_statement1459 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_assignment_statement1465 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_assign_right_hand_side_in_assignment_statement1469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALITY_in_assign_right_hand_side1490 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_root_expression_in_assign_right_hand_side1492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_if_statement1518 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_root_expression_in_if_statement1531 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_THEN_in_if_statement1537 = new BitSet(new long[]{0x000000100000A000L});
    public static final BitSet FOLLOW_block_in_if_statement1548 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_if_statement1562 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_ELSE_in_if_statement1579 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_IF_in_if_statement1585 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_root_expression_in_if_statement1598 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_THEN_in_if_statement1604 = new BitSet(new long[]{0x000000100000A000L});
    public static final BitSet FOLLOW_block_in_if_statement1614 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_if_statement1632 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_ELSE_in_if_statement1661 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_THEN_in_if_statement1663 = new BitSet(new long[]{0x000000100000A000L});
    public static final BitSet FOLLOW_block_in_if_statement1673 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_if_statement1676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REPEAT_in_loop_statement1705 = new BitSet(new long[]{0x0000000000100000L,0x00000000000001B0L});
    public static final BitSet FOLLOW_OVER_in_loop_statement1718 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_loop_statement1720 = new BitSet(new long[]{0x000000100000A000L});
    public static final BitSet FOLLOW_FROM_in_loop_statement1732 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_range_in_loop_statement1738 = new BitSet(new long[]{0x000000100000A000L});
    public static final BitSet FOLLOW_root_expression_in_loop_statement1753 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_TIMES_in_loop_statement1755 = new BitSet(new long[]{0x000000100000A000L});
    public static final BitSet FOLLOW_WHILE_in_loop_statement1766 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_UNTIL_in_loop_statement1772 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_root_expression_in_loop_statement1787 = new BitSet(new long[]{0x000000100000A000L});
    public static final BitSet FOLLOW_block_in_loop_statement1798 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_loop_statement1806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TO_in_range1829 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_root_expression_in_range1835 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_root_expression_in_range1842 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PARENT_in_selector1863 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_qualified_name_in_selector1865 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ME_in_selector1874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ROOT_EXPRESSION_in_root_expression1900 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_root_expression1902 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNARY_NOT_in_expression1924 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NOT_in_expression1926 = new BitSet(new long[]{0x8610080020610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_expression1932 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EQUALITY_in_expression1944 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1950 = new BitSet(new long[]{0x8610080020610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_expression1956 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NOTEQUALS_in_expression1966 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1972 = new BitSet(new long[]{0x8610080020610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_expression1978 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GREATER_in_expression1988 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1994 = new BitSet(new long[]{0x8610080020610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_expression2000 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GREATER_EQUAL_in_expression2010 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2016 = new BitSet(new long[]{0x8610080020610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_expression2022 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INHERITS_in_expression2032 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2036 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_class_type_in_expression2040 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LESS_in_expression2050 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2056 = new BitSet(new long[]{0x8610080020610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_expression2062 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LESS_EQUAL_in_expression2072 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2078 = new BitSet(new long[]{0x8610080020610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_expression2084 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OR_in_expression2094 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2100 = new BitSet(new long[]{0x8610080020610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_expression2106 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_AND_in_expression2116 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2122 = new BitSet(new long[]{0x8610080020610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_expression2128 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PLUS_in_expression2138 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2144 = new BitSet(new long[]{0x8610080020610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_expression2150 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MINUS_in_expression2160 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2166 = new BitSet(new long[]{0x8610080020610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_expression2172 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MULTIPLY_in_expression2182 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2188 = new BitSet(new long[]{0x8610080020610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_expression2194 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DIVIDE_in_expression2204 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2210 = new BitSet(new long[]{0x8610080020610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_expression2216 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MODULO_in_expression2226 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2232 = new BitSet(new long[]{0x8610080020610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_expression2238 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNCTION_CALL_in_expression2248 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_qualified_name_in_expression2250 = new BitSet(new long[]{0x0000020400000000L});
    public static final BitSet FOLLOW_COLON_in_expression2252 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_expression2254 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_expression2258 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_function_expression_list_in_expression2270 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_expression2272 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_QUALIFIED_SOLO_EXPRESSION_in_expression2283 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_qualified_name_in_expression2285 = new BitSet(new long[]{0x0000020000000008L});
    public static final BitSet FOLLOW_COLON_in_expression2288 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_expression2290 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_QUALIFIED_SOLO_EXPRESSION_SELECTOR_in_expression2302 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_selector_in_expression2304 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_expression2306 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_expression2308 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNCTION_CALL_PARENT_in_expression2318 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_PARENT_in_expression2320 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_expression2322 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_expression2326 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_expression2328 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_expression2330 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_expression2332 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_function_expression_list_in_expression2338 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_expression2340 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNCTION_CALL_THIS_in_expression2350 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ME_in_expression2352 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_expression2354 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_expression2356 = new BitSet(new long[]{0x0000020400000000L});
    public static final BitSet FOLLOW_COLON_in_expression2359 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_expression2361 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_expression2365 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_function_expression_list_in_expression2371 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_expression2373 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BOOLEAN_in_expression2382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_expression2391 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_DECIMAL_in_expression2395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_expression2404 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_INT_in_expression2408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_expression2416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_expression2424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ME_in_expression2432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INPUT_in_expression2440 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_expression2442 = new BitSet(new long[]{0x8610080020610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_expression2446 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_expression2448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CAST_in_expression2456 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_expression2458 = new BitSet(new long[]{0x78000C0188000800L});
    public static final BitSet FOLLOW_assignment_declaration_in_expression2462 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_COMMA_in_expression2464 = new BitSet(new long[]{0x8610080020610070L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_expression_in_expression2468 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_expression2472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_EXPRESSION_LIST_in_function_expression_list2496 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_function_expression_list2508 = new BitSet(new long[]{0x8610080020610078L,0x0000000007F7FC00L});
    public static final BitSet FOLLOW_FPARAM_in_formal_parameter2537 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_assignment_declaration_in_formal_parameter2541 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_formal_parameter2543 = new BitSet(new long[]{0x0000000000000008L});

}