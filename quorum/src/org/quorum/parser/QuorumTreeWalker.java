// $ANTLR 3.4 /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g 2012-06-12 13:36:39


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
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;
import java.util.HashMap;
@SuppressWarnings({"all", "warnings", "unchecked"})
public class QuorumTreeWalker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "FUNCTION_CALL", "FUNCTION_CALL_PARENT", "FUNCTION_CALL_THIS", "FUNCTION_EXPRESSION_LIST", "SOLO_FUNCTION_CALL", "SOLO_FUNCTION_CALL_PARENT", "SOLO_FUNCTION_CALL_THIS", "QUALIFIED_NAME", "EXPRESSION_STATEMENT", "STATEMENT_LIST", "CONSTRUCTOR", "FPARAM", "UNARY_NOT", "ELSE_IF_STATEMENT", "FINAL_ELSE", "PAREN_WRAPPED_EXPRESSION", "ROOT_EXPRESSION", "QUALIFIED_SOLO_EXPRESSION", "QUALIFIED_SOLO_EXPRESSION_SELECTOR", "QUALIFIED_SOLO_PARENT_EXPRESSON", "GENERIC", "PACKAGE_NAME", "USE", "CLASS", "ID", "END", "INHERITS", "COMMA", "PUBLIC", "PRIVATE", "ACTION", "LEFT_PAREN", "RIGHT_PAREN", "RETURNS", "BLUEPRINT", "NATIVE", "ON_CREATE", "PERIOD", "COLON", "PARENT", "ME", "ALERT", "CHECK", "DETECT", "ALWAYS", "OF_TYPE", "OR", "PRINT", "SAY", "RETURN", "NOW", "LESS", "GREATER", "INTEGER_KEYWORD", "NUMBER_KEYWORD", "TEXT", "BOOLEAN_KEYWORD", "EQUALITY", "IF", "ELSE_IF", "ELSE", "REPEAT", "TIMES", "WHILE", "UNTIL", "AND", "NOTEQUALS", "GREATER_EQUAL", "LESS_EQUAL", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", "MODULO", "NOT", "CAST", "INT", "BOOLEAN", "DECIMAL", "STRING", "NULL", "INPUT", "ON_DESTROY", "OVER", "LEFT_SQR_BRACE", "RIGHT_SQR_BRACE", "DOUBLE_QUOTE", "NEWLINE", "WS", "COMMENTS"
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
    public static final int ALERT=45;
    public static final int CHECK=46;
    public static final int DETECT=47;
    public static final int ALWAYS=48;
    public static final int OF_TYPE=49;
    public static final int OR=50;
    public static final int PRINT=51;
    public static final int SAY=52;
    public static final int RETURN=53;
    public static final int NOW=54;
    public static final int LESS=55;
    public static final int GREATER=56;
    public static final int INTEGER_KEYWORD=57;
    public static final int NUMBER_KEYWORD=58;
    public static final int TEXT=59;
    public static final int BOOLEAN_KEYWORD=60;
    public static final int EQUALITY=61;
    public static final int IF=62;
    public static final int ELSE_IF=63;
    public static final int ELSE=64;
    public static final int REPEAT=65;
    public static final int TIMES=66;
    public static final int WHILE=67;
    public static final int UNTIL=68;
    public static final int AND=69;
    public static final int NOTEQUALS=70;
    public static final int GREATER_EQUAL=71;
    public static final int LESS_EQUAL=72;
    public static final int PLUS=73;
    public static final int MINUS=74;
    public static final int MULTIPLY=75;
    public static final int DIVIDE=76;
    public static final int MODULO=77;
    public static final int NOT=78;
    public static final int CAST=79;
    public static final int INT=80;
    public static final int BOOLEAN=81;
    public static final int DECIMAL=82;
    public static final int STRING=83;
    public static final int NULL=84;
    public static final int INPUT=85;
    public static final int ON_DESTROY=86;
    public static final int OVER=87;
    public static final int LEFT_SQR_BRACE=88;
    public static final int RIGHT_SQR_BRACE=89;
    public static final int DOUBLE_QUOTE=90;
    public static final int NEWLINE=91;
    public static final int WS=92;
    public static final int COMMENTS=93;

    // delegates
    public TreeParser[] getDelegates() {
        return new TreeParser[] {};
    }

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
    	QualifiedNameDescriptor thisPackage = new QualifiedNameDescriptor();
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:63:1: start : ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ |) class_declaration EOF ;
    public final QuorumTreeWalker.start_return start() throws RecognitionException {
        QuorumTreeWalker.start_return retval = new QuorumTreeWalker.start_return();
        retval.start = input.LT(1);


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:63:7: ( ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ |) class_declaration EOF )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:63:9: ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ |) class_declaration EOF
            {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:63:9: ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ |)
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
        	// do for sure before leaving
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


        QuorumTreeWalker.qualified_name_return qn =null;


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
        	// do for sure before leaving
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
        	// do for sure before leaving
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
            else if ( ((LA8_0 >= SOLO_FUNCTION_CALL && LA8_0 <= QUALIFIED_NAME)||LA8_0==ID||(LA8_0 >= PUBLIC && LA8_0 <= ACTION)||(LA8_0 >= BLUEPRINT && LA8_0 <= ON_CREATE)||(LA8_0 >= PARENT && LA8_0 <= CHECK)||(LA8_0 >= PRINT && LA8_0 <= RETURN)||(LA8_0 >= INTEGER_KEYWORD && LA8_0 <= BOOLEAN_KEYWORD)||LA8_0==IF||LA8_0==REPEAT) ) {
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

                        if ( (LA7_0==QUALIFIED_NAME||LA7_0==ID||(LA7_0 >= PUBLIC && LA7_0 <= ACTION)||(LA7_0 >= BLUEPRINT && LA7_0 <= ON_CREATE)||(LA7_0 >= PARENT && LA7_0 <= ME)||(LA7_0 >= INTEGER_KEYWORD && LA7_0 <= BOOLEAN_KEYWORD)) ) {
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
        	// do for sure before leaving
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


        QuorumTreeWalker.access_modifier_return modEnum =null;


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

                if ( (LA12_2==QUALIFIED_NAME||(LA12_2 >= INTEGER_KEYWORD && LA12_2 <= BOOLEAN_KEYWORD)) ) {
                    alt12=1;
                }
                else if ( (LA12_2==ACTION||(LA12_2 >= BLUEPRINT && LA12_2 <= ON_CREATE)) ) {
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

                if ( (LA12_3==QUALIFIED_NAME||(LA12_3 >= INTEGER_KEYWORD && LA12_3 <= BOOLEAN_KEYWORD)) ) {
                    alt12=1;
                }
                else if ( (LA12_3==ACTION||(LA12_3 >= BLUEPRINT && LA12_3 <= ON_CREATE)) ) {
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

                        if ( ((LA9_0 >= SOLO_FUNCTION_CALL && LA9_0 <= QUALIFIED_NAME)||LA9_0==ID||(LA9_0 >= PUBLIC && LA9_0 <= PRIVATE)||(LA9_0 >= PARENT && LA9_0 <= CHECK)||(LA9_0 >= PRINT && LA9_0 <= RETURN)||(LA9_0 >= INTEGER_KEYWORD && LA9_0 <= BOOLEAN_KEYWORD)||LA9_0==IF||LA9_0==REPEAT) ) {
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

                        if ( ((LA11_0 >= PUBLIC && LA11_0 <= ACTION)||(LA11_0 >= BLUEPRINT && LA11_0 <= ON_CREATE)) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:148:5: (modEnum= access_modifier )? method_declaration
                    	    {
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:148:13: (modEnum= access_modifier )?
                    	    int alt10=2;
                    	    int LA10_0 = input.LA(1);

                    	    if ( ((LA10_0 >= PUBLIC && LA10_0 <= PRIVATE)) ) {
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
        	// do for sure before leaving
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


        QuorumTreeWalker.qualified_name_return qn =null;

        QuorumTreeWalker.generic_statement_return gd =null;


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
        	// do for sure before leaving
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
        	// do for sure before leaving
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


        QuorumTreeWalker.access_modifier_return modEnum =null;


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

                if ( (LA17_2==QUALIFIED_NAME||(LA17_2 >= INTEGER_KEYWORD && LA17_2 <= BOOLEAN_KEYWORD)) ) {
                    alt17=1;
                }
                else if ( (LA17_2==ACTION||(LA17_2 >= BLUEPRINT && LA17_2 <= ON_CREATE)) ) {
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

                if ( (LA17_3==QUALIFIED_NAME||(LA17_3 >= INTEGER_KEYWORD && LA17_3 <= BOOLEAN_KEYWORD)) ) {
                    alt17=1;
                }
                else if ( (LA17_3==ACTION||(LA17_3 >= BLUEPRINT && LA17_3 <= ON_CREATE)) ) {
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

                    if ( ((LA16_0 >= PUBLIC && LA16_0 <= PRIVATE)) ) {
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
        	// do for sure before leaving
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
        QuorumTreeWalker.formal_parameter_return fp =null;

        QuorumTreeWalker.assignment_declaration_return ad =null;


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
        	// do for sure before leaving
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
        	// do for sure before leaving
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

                    if ( ((LA26_0 >= SOLO_FUNCTION_CALL && LA26_0 <= QUALIFIED_NAME)||LA26_0==ID||(LA26_0 >= PUBLIC && LA26_0 <= PRIVATE)||(LA26_0 >= PARENT && LA26_0 <= CHECK)||(LA26_0 >= PRINT && LA26_0 <= RETURN)||(LA26_0 >= INTEGER_KEYWORD && LA26_0 <= BOOLEAN_KEYWORD)||LA26_0==IF||LA26_0==REPEAT) ) {
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
        	// do for sure before leaving
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
        	// do for sure before leaving
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
        QuorumTreeWalker.expression_return e =null;

        QuorumTreeWalker.qualified_name_return qualified_name7 =null;

        QuorumTreeWalker.qualified_name_return qualified_name9 =null;

        QuorumTreeWalker.qualified_name_return qualified_name11 =null;



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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:354:3: ^( SOLO_FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN )
                    {
                    match(input,SOLO_FUNCTION_CALL,FOLLOW_SOLO_FUNCTION_CALL_in_solo_method_call582); 


                    			inCallStep = true;
                    			builder.addStepLabel(OpcodeType.ROOT_EXPRESSION, -1);
                    		

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_qualified_name_in_solo_method_call591);
                    qualified_name7=qualified_name();

                    state._fsp--;


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:359:18: ( COLON ID )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==COLON) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:359:19: COLON ID
                            {
                            match(input,COLON,FOLLOW_COLON_in_solo_method_call594); 

                            ID8=(CommonTree)match(input,ID,FOLLOW_ID_in_solo_method_call596); 

                            }
                            break;

                    }


                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_solo_method_call600); 

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:359:41: (e= expression ( COMMA e= expression )* )?
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( ((LA30_0 >= FUNCTION_CALL && LA30_0 <= FUNCTION_CALL_THIS)||LA30_0==UNARY_NOT||(LA30_0 >= QUALIFIED_SOLO_EXPRESSION && LA30_0 <= QUALIFIED_SOLO_PARENT_EXPRESSON)||LA30_0==INHERITS||LA30_0==ME||LA30_0==OR||(LA30_0 >= LESS && LA30_0 <= GREATER)||LA30_0==EQUALITY||(LA30_0 >= AND && LA30_0 <= MODULO)||(LA30_0 >= CAST && LA30_0 <= INPUT)) ) {
                        alt30=1;
                    }
                    switch (alt30) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:360:3: e= expression ( COMMA e= expression )*
                            {
                            pushFollow(FOLLOW_expression_in_solo_method_call610);
                            e=expression();

                            state._fsp--;



                            			values.add((e!=null?e.eval:null));
                            			steps.add((e!=null?e.step:null));
                            			registers.add((e!=null?e.eval:null).getRegister());
                            			if((e!=null?e.eval:null).getType() != null)
                            				types.add((e!=null?e.eval:null).getType().getStaticKey());
                                            	argumentTypes.add((e!=null?e.eval:null).getType());
                                            	inCallStep = false;
                            		

                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:370:3: ( COMMA e= expression )*
                            loop29:
                            do {
                                int alt29=2;
                                int LA29_0 = input.LA(1);

                                if ( (LA29_0==COMMA) ) {
                                    alt29=1;
                                }


                                switch (alt29) {
                            	case 1 :
                            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:370:4: COMMA e= expression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_solo_method_call620); 

                            	    pushFollow(FOLLOW_expression_in_solo_method_call626);
                            	    e=expression();

                            	    state._fsp--;



                            	    			values.add((e!=null?e.eval:null));
                            	    			steps.add((e!=null?e.step:null));
                            	    			registers.add((e!=null?e.eval:null).getRegister());
                            	    			if((e!=null?e.eval:null).getType() != null)
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


                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_solo_method_call640); 

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
                    		builder.addStepLabel(OpcodeType.SOLO_METHOD_CALL, result.getStepCount());		
                    		
                    		temp = result.getNextRegister();
                    		

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:419:4: ^( SOLO_FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN )
                    {
                    match(input,SOLO_FUNCTION_CALL_PARENT,FOLLOW_SOLO_FUNCTION_CALL_PARENT_in_solo_method_call652); 


                    		inCallStep = true;
                    		builder.addStepLabel(OpcodeType.ROOT_EXPRESSION, -1);
                    	

                    match(input, Token.DOWN, null); 
                    match(input,PARENT,FOLLOW_PARENT_in_solo_method_call659); 

                    match(input,COLON,FOLLOW_COLON_in_solo_method_call661); 

                    pushFollow(FOLLOW_qualified_name_in_solo_method_call663);
                    qualified_name9=qualified_name();

                    state._fsp--;


                    match(input,COLON,FOLLOW_COLON_in_solo_method_call665); 

                    ID10=(CommonTree)match(input,ID,FOLLOW_ID_in_solo_method_call667); 

                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_solo_method_call669); 

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:425:3: (e= expression ( COMMA e= expression )* )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( ((LA32_0 >= FUNCTION_CALL && LA32_0 <= FUNCTION_CALL_THIS)||LA32_0==UNARY_NOT||(LA32_0 >= QUALIFIED_SOLO_EXPRESSION && LA32_0 <= QUALIFIED_SOLO_PARENT_EXPRESSON)||LA32_0==INHERITS||LA32_0==ME||LA32_0==OR||(LA32_0 >= LESS && LA32_0 <= GREATER)||LA32_0==EQUALITY||(LA32_0 >= AND && LA32_0 <= MODULO)||(LA32_0 >= CAST && LA32_0 <= INPUT)) ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:425:4: e= expression ( COMMA e= expression )*
                            {
                            pushFollow(FOLLOW_expression_in_solo_method_call679);
                            e=expression();

                            state._fsp--;



                            			values.add((e!=null?e.eval:null));
                            			steps.add((e!=null?e.step:null));
                            			registers.add((e!=null?e.eval:null).getRegister());
                            			types.add((e!=null?e.eval:null).getType().getStaticKey());
                                            	argumentTypes.add((e!=null?e.eval:null).getType());
                                            	inCallStep = false;
                            		

                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:433:5: ( COMMA e= expression )*
                            loop31:
                            do {
                                int alt31=2;
                                int LA31_0 = input.LA(1);

                                if ( (LA31_0==COMMA) ) {
                                    alt31=1;
                                }


                                switch (alt31) {
                            	case 1 :
                            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:433:6: COMMA e= expression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_solo_method_call686); 

                            	    pushFollow(FOLLOW_expression_in_solo_method_call692);
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


                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_solo_method_call702); 

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
                    		builder.addStepLabel(OpcodeType.SOLO_METHOD_CALL, result.getStepCount());
                    		
                    		temp = result.getNextRegister();
                    		inCallStep = false;
                    		

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:481:4: ^( SOLO_FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN )
                    {
                    match(input,SOLO_FUNCTION_CALL_THIS,FOLLOW_SOLO_FUNCTION_CALL_THIS_in_solo_method_call713); 


                    		inCallStep = true;
                    		builder.addStepLabel(OpcodeType.ROOT_EXPRESSION, -1);
                    	

                    match(input, Token.DOWN, null); 
                    match(input,ME,FOLLOW_ME_in_solo_method_call720); 

                    match(input,COLON,FOLLOW_COLON_in_solo_method_call722); 

                    pushFollow(FOLLOW_qualified_name_in_solo_method_call724);
                    qualified_name11=qualified_name();

                    state._fsp--;


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:486:26: ( COLON ID )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==COLON) ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:486:27: COLON ID
                            {
                            match(input,COLON,FOLLOW_COLON_in_solo_method_call727); 

                            ID12=(CommonTree)match(input,ID,FOLLOW_ID_in_solo_method_call729); 

                            }
                            break;

                    }


                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_solo_method_call733); 

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:487:3: (e= expression ( COMMA e= expression )* )?
                    int alt35=2;
                    int LA35_0 = input.LA(1);

                    if ( ((LA35_0 >= FUNCTION_CALL && LA35_0 <= FUNCTION_CALL_THIS)||LA35_0==UNARY_NOT||(LA35_0 >= QUALIFIED_SOLO_EXPRESSION && LA35_0 <= QUALIFIED_SOLO_PARENT_EXPRESSON)||LA35_0==INHERITS||LA35_0==ME||LA35_0==OR||(LA35_0 >= LESS && LA35_0 <= GREATER)||LA35_0==EQUALITY||(LA35_0 >= AND && LA35_0 <= MODULO)||(LA35_0 >= CAST && LA35_0 <= INPUT)) ) {
                        alt35=1;
                    }
                    switch (alt35) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:487:4: e= expression ( COMMA e= expression )*
                            {
                            pushFollow(FOLLOW_expression_in_solo_method_call743);
                            e=expression();

                            state._fsp--;



                            			values.add((e!=null?e.eval:null));
                            			steps.add((e!=null?e.step:null));
                            			registers.add((e!=null?e.eval:null).getRegister());
                            			types.add((e!=null?e.eval:null).getType().getStaticKey());
                                            	argumentTypes.add((e!=null?e.eval:null).getType());
                                            	inCallStep = false;
                            		

                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:495:4: ( COMMA e= expression )*
                            loop34:
                            do {
                                int alt34=2;
                                int LA34_0 = input.LA(1);

                                if ( (LA34_0==COMMA) ) {
                                    alt34=1;
                                }


                                switch (alt34) {
                            	case 1 :
                            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:495:5: COMMA e= expression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_solo_method_call750); 

                            	    pushFollow(FOLLOW_expression_in_solo_method_call756);
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


                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_solo_method_call766); 

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
                    		builder.addStepLabel(OpcodeType.SOLO_METHOD_CALL, result.getStepCount());
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
        	// do for sure before leaving
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:541:1: alert_statement : ^( ALERT LEFT_PAREN ex= expression RIGHT_PAREN ) ;
    public final QuorumTreeWalker.alert_statement_return alert_statement() throws RecognitionException {
        alert_statement_stack.push(new alert_statement_scope());
        QuorumTreeWalker.alert_statement_return retval = new QuorumTreeWalker.alert_statement_return();
        retval.start = input.LT(1);


        CommonTree LEFT_PAREN13=null;
        QuorumTreeWalker.expression_return ex =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:547:2: ( ^( ALERT LEFT_PAREN ex= expression RIGHT_PAREN ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:547:4: ^( ALERT LEFT_PAREN ex= expression RIGHT_PAREN )
            {
            match(input,ALERT,FOLLOW_ALERT_in_alert_statement786); 


            		((alert_statement_scope)alert_statement_stack.peek()).errorValue = new ExpressionValue();
            		((alert_statement_scope)alert_statement_stack.peek()).errorStep = null;
            	

            match(input, Token.DOWN, null); 
            LEFT_PAREN13=(CommonTree)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_alert_statement794); 

            pushFollow(FOLLOW_expression_in_alert_statement798);
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
            	

            match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_alert_statement806); 


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
        	// do for sure before leaving
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
        String startSymbol;
        JumpStep detectJump;
    }
    protected Stack check_statement_stack = new Stack();


    public static class check_statement_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "check_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:604:1: check_statement :check= CHECK block[true] ( (detect_start= DETECT det_param= detect_parameter block[true] )+ (always= ALWAYS block[true] )? |always= ALWAYS block[true] ) end= END ;
    public final QuorumTreeWalker.check_statement_return check_statement() throws RecognitionException {
        check_statement_stack.push(new check_statement_scope());
        QuorumTreeWalker.check_statement_return retval = new QuorumTreeWalker.check_statement_return();
        retval.start = input.LT(1);


        CommonTree check=null;
        CommonTree detect_start=null;
        CommonTree always=null;
        CommonTree end=null;
        QuorumTreeWalker.detect_parameter_return det_param =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:614:2: (check= CHECK block[true] ( (detect_start= DETECT det_param= detect_parameter block[true] )+ (always= ALWAYS block[true] )? |always= ALWAYS block[true] ) end= END )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:615:2: check= CHECK block[true] ( (detect_start= DETECT det_param= detect_parameter block[true] )+ (always= ALWAYS block[true] )? |always= ALWAYS block[true] ) end= END
            {
            	
            		((check_statement_scope)check_statement_stack.peek()).detectJump = new JumpStep();
            		((check_statement_scope)check_statement_stack.peek()).info = new ExceptionInfo();
            		((check_statement_scope)check_statement_stack.peek()).detect_counter = 0;
            		((check_statement_scope)check_statement_stack.peek()).has_always = false;
            		
            		sub_counter++;
            		if(sub_counter > 1){labelCounter++;}
            		((check_statement_scope)check_statement_stack.peek()).tempLabelCounter = labelCounter;
            		((check_statement_scope)check_statement_stack.peek()).info.alwaysStartLabel = builder.getCurrentClass().getStaticKey() + "_" + ((check_statement_scope)check_statement_stack.peek()).info.ALWAYS + ((check_statement_scope)check_statement_stack.peek()).tempLabelCounter + ((check_statement_scope)check_statement_stack.peek()).info.START;
            	

            check=(CommonTree)match(input,CHECK,FOLLOW_CHECK_in_check_statement838); 


            		((check_statement_scope)check_statement_stack.peek()).info.location = new LineInformation(
            			check.getLine(),
            			check.getLine(),
            			check.getCharPositionInLine(),
            			(check!=null?check.getText():null).length() + check.getCharPositionInLine());
            		((check_statement_scope)check_statement_stack.peek()).info.checkStartLabel = builder.getCurrentClass().getStaticKey() + "_" + (check!=null?check.getText():null) + ((check_statement_scope)check_statement_stack.peek()).tempLabelCounter + ((check_statement_scope)check_statement_stack.peek()).info.START;
            		stepFactory.startCheck(((check_statement_scope)check_statement_stack.peek()).info);
            		((check_statement_scope)check_statement_stack.peek()).startSymbol = "check";
            	

            pushFollow(FOLLOW_block_in_check_statement844);
            block(true);

            state._fsp--;



            		((check_statement_scope)check_statement_stack.peek()).info.checkJump.setBeginColumn(check.getCharPositionInLine());
            		((check_statement_scope)check_statement_stack.peek()).info.checkJump.setEndColumn(check.getCharPositionInLine() + ((check!=null?check.getText():null).length()));
                            ((check_statement_scope)check_statement_stack.peek()).info.checkJump.setEndLine(check.getLine());
            		stepFactory.addCheckEndJumpStep(((check_statement_scope)check_statement_stack.peek()).info);
            	

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:643:2: ( (detect_start= DETECT det_param= detect_parameter block[true] )+ (always= ALWAYS block[true] )? |always= ALWAYS block[true] )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:644:7: (detect_start= DETECT det_param= detect_parameter block[true] )+ (always= ALWAYS block[true] )?
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:644:7: (detect_start= DETECT det_param= detect_parameter block[true] )+
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
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:644:8: detect_start= DETECT det_param= detect_parameter block[true]
                    	    {
                    	    detect_start=(CommonTree)match(input,DETECT,FOLLOW_DETECT_in_check_statement866); 


                    	    	    		if(((check_statement_scope)check_statement_stack.peek()).startSymbol.equals("check")){
                    	    		    		((check_statement_scope)check_statement_stack.peek()).info.checkJump.setBeginLine(detect_start.getLine());
                    	    				stepFactory.endCheck(((check_statement_scope)check_statement_stack.peek()).info);
                    	    			}else{
                    	    				((check_statement_scope)check_statement_stack.peek()).detectJump.setBeginLine(detect_start.getLine());
                    	    		    		stepFactory.endDetect(((check_statement_scope)check_statement_stack.peek()).info, ((check_statement_scope)check_statement_stack.peek()).detect_counter);
                    	    		    		((check_statement_scope)check_statement_stack.peek()).detect_counter = ((check_statement_scope)check_statement_stack.peek()).detect_counter + 1;
                    	    			}
                    	    	    		
                    	    	    		((check_statement_scope)check_statement_stack.peek()).info.addDetectLabel((detect_start!=null?detect_start.getText():null) + ((check_statement_scope)check_statement_stack.peek()).detect_counter
                    	    	    			 + "_" + ((check_statement_scope)check_statement_stack.peek()).tempLabelCounter + ((check_statement_scope)check_statement_stack.peek()).info.START);
                    	    	    			 symbol.addStatementFlagToCurrentFile(detect_start.getLine());
                    	    	    		symbol.getControlFlow().detectStart();
                    	    	    		symbol.enterNextBlock();
                    	    	    		((check_statement_scope)check_statement_stack.peek()).startSymbol = "detect";
                    	    	    		
                    	    	    	

                    	    pushFollow(FOLLOW_detect_parameter_in_check_statement893);
                    	    det_param=detect_parameter();

                    	    state._fsp--;



                    	    	    		Iterator<ErrorTypeDescriptor> detectParamIt = det_param.exceptionTypeList.iterator();
                    	    	    		while(detectParamIt.hasNext()){
                    	    		    		VariableParameterCommonDescriptor d = new VariableParameterCommonDescriptor();
                    	    		    		DetectParameter er = new DetectParameter();
                    	    		    		TypeDescriptor t = new TypeDescriptor();
                    	    		    		
                    	    		    		d = symbol.getVariable((det_param!=null?det_param.name:null));
                    	    		    		er.setVariableNumber(d.getVariableNumber());
                    	    		    		er.errorType = detectParamIt.next();
                    	    		    		t.setName(er.errorType.getName());
                    	    		    		er.setType(t);
                    	    		    		er.setName((det_param!=null?det_param.name:null));
                    	    		    		
                    	    		    		((check_statement_scope)check_statement_stack.peek()).info.addDetectParameter(er);
                    	    	    		}
                    	    	    		stepFactory.startDetect(((check_statement_scope)check_statement_stack.peek()).info, ((check_statement_scope)check_statement_stack.peek()).detect_counter);
                    	    	    	

                    	    pushFollow(FOLLOW_block_in_check_statement917);
                    	    block(true);

                    	    state._fsp--;



                    	    	    		((check_statement_scope)check_statement_stack.peek()).detectJump = new JumpStep();
                    	    	    		((check_statement_scope)check_statement_stack.peek()).detectJump.setType(JumpType.CHECK);
                    	    		    	((check_statement_scope)check_statement_stack.peek()).detectJump.setBeginColumn(detect_start.getCharPositionInLine());
                    	    			((check_statement_scope)check_statement_stack.peek()).detectJump.setEndColumn(detect_start.getCharPositionInLine() + ((detect_start!=null?detect_start.getText():null).length()));
                    	    	                ((check_statement_scope)check_statement_stack.peek()).detectJump.setEndLine(detect_start.getLine());
                    	    	                ((check_statement_scope)check_statement_stack.peek()).info.detectJumps.add(((check_statement_scope)check_statement_stack.peek()).detectJump);
                    	    			stepFactory.addDetectEndJumpStep(((check_statement_scope)check_statement_stack.peek()).info, ((check_statement_scope)check_statement_stack.peek()).detectJump);
                    	    	    	

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


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:695:7: (always= ALWAYS block[true] )?
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0==ALWAYS) ) {
                        alt38=1;
                    }
                    switch (alt38) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:695:8: always= ALWAYS block[true]
                            {
                            always=(CommonTree)match(input,ALWAYS,FOLLOW_ALWAYS_in_check_statement950); 


                            	    		((check_statement_scope)check_statement_stack.peek()).detectJump.setBeginLine(always.getLine());
                            	    		stepFactory.endDetect(((check_statement_scope)check_statement_stack.peek()).info, ((check_statement_scope)check_statement_stack.peek()).detect_counter);
                            	    		((check_statement_scope)check_statement_stack.peek()).detect_counter = ((check_statement_scope)check_statement_stack.peek()).detect_counter + 1;
                            	    		
                            	    		((check_statement_scope)check_statement_stack.peek()).has_always = true;
                            	    		((check_statement_scope)check_statement_stack.peek()).info.hasAlways = true;
                            	    		stepFactory.startAlways(((check_statement_scope)check_statement_stack.peek()).info, true);
                            	    		((check_statement_scope)check_statement_stack.peek()).startSymbol = "always";
                            	    	

                            pushFollow(FOLLOW_block_in_check_statement966);
                            block(true);

                            state._fsp--;


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:710:7: always= ALWAYS block[true]
                    {
                    always=(CommonTree)match(input,ALWAYS,FOLLOW_ALWAYS_in_check_statement998); 


                    		    	((check_statement_scope)check_statement_stack.peek()).info.checkJump.setBeginLine(always.getLine());
                    			stepFactory.endCheck(((check_statement_scope)check_statement_stack.peek()).info);
                    			
                    	    		((check_statement_scope)check_statement_stack.peek()).has_always = true;
                    	    		((check_statement_scope)check_statement_stack.peek()).info.hasAlways = true;
                    	    		stepFactory.startAlways(((check_statement_scope)check_statement_stack.peek()).info, true);
                    	    		((check_statement_scope)check_statement_stack.peek()).startSymbol = "always";
                    	    	

                    pushFollow(FOLLOW_block_in_check_statement1021);
                    block(true);

                    state._fsp--;


                    }
                    break;

            }


            end=(CommonTree)match(input,END,FOLLOW_END_in_check_statement1030); 


                		if(((check_statement_scope)check_statement_stack.peek()).startSymbol.equals("always")){
            	    		stepFactory.endAlways(((check_statement_scope)check_statement_stack.peek()).info);
            		}else{
            			((check_statement_scope)check_statement_stack.peek()).detectJump.setBeginLine(end.getLine());
            	    		stepFactory.endDetect(((check_statement_scope)check_statement_stack.peek()).info, ((check_statement_scope)check_statement_stack.peek()).detect_counter);
            	    		((check_statement_scope)check_statement_stack.peek()).detect_counter = ((check_statement_scope)check_statement_stack.peek()).detect_counter + 1;
            	    		
            	    		if (((check_statement_scope)check_statement_stack.peek()).has_always == false) {
            	    			((check_statement_scope)check_statement_stack.peek()).info.hasAlways = false;
            	    			stepFactory.startAlways(((check_statement_scope)check_statement_stack.peek()).info, false);
            	    			stepFactory.endAlways(((check_statement_scope)check_statement_stack.peek()).info);
                			}
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
        	// do for sure before leaving
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:745:1: detect_parameter returns [String name, ArrayList<ErrorTypeDescriptor> exceptionTypeList] : ^(id= ID ( OF_TYPE qn= qualified_name ( OR qn= qualified_name )* )? ) ;
    public final QuorumTreeWalker.detect_parameter_return detect_parameter() throws RecognitionException {
        detect_parameter_stack.push(new detect_parameter_scope());
        QuorumTreeWalker.detect_parameter_return retval = new QuorumTreeWalker.detect_parameter_return();
        retval.start = input.LT(1);


        CommonTree id=null;
        QuorumTreeWalker.qualified_name_return qn =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:749:2: ( ^(id= ID ( OF_TYPE qn= qualified_name ( OR qn= qualified_name )* )? ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:749:4: ^(id= ID ( OF_TYPE qn= qualified_name ( OR qn= qualified_name )* )? )
            {
            id=(CommonTree)match(input,ID,FOLLOW_ID_in_detect_parameter1059); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:750:2: ( OF_TYPE qn= qualified_name ( OR qn= qualified_name )* )?
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==OF_TYPE) ) {
                    alt41=1;
                }
                switch (alt41) {
                    case 1 :
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:750:3: OF_TYPE qn= qualified_name ( OR qn= qualified_name )*
                        {
                        match(input,OF_TYPE,FOLLOW_OF_TYPE_in_detect_parameter1064); 


                        		((detect_parameter_scope)detect_parameter_stack.peek()).exceptionList = new ArrayList<ErrorTypeDescriptor>();
                        	

                        pushFollow(FOLLOW_qualified_name_in_detect_parameter1073);
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
                        	

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:792:2: ( OR qn= qualified_name )*
                        loop40:
                        do {
                            int alt40=2;
                            int LA40_0 = input.LA(1);

                            if ( (LA40_0==OR) ) {
                                alt40=1;
                            }


                            switch (alt40) {
                        	case 1 :
                        	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:792:3: OR qn= qualified_name
                        	    {
                        	    match(input,OR,FOLLOW_OR_in_detect_parameter1080); 

                        	    pushFollow(FOLLOW_qualified_name_in_detect_parameter1084);
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
        	// do for sure before leaving
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:843:1: print_statement : PRINT root_expression ;
    public final QuorumTreeWalker.print_statement_return print_statement() throws RecognitionException {
        QuorumTreeWalker.print_statement_return retval = new QuorumTreeWalker.print_statement_return();
        retval.start = input.LT(1);


        QuorumTreeWalker.root_expression_return root_expression14 =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:844:2: ( PRINT root_expression )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:844:4: PRINT root_expression
            {
            match(input,PRINT,FOLLOW_PRINT_in_print_statement1111); 

            pushFollow(FOLLOW_root_expression_in_print_statement1113);
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
            		builder.addStepLabel(OpcodeType.PRINT, -1);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:864:1: speak_statement : SAY root_expression ;
    public final QuorumTreeWalker.speak_statement_return speak_statement() throws RecognitionException {
        QuorumTreeWalker.speak_statement_return retval = new QuorumTreeWalker.speak_statement_return();
        retval.start = input.LT(1);


        QuorumTreeWalker.root_expression_return root_expression15 =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:865:2: ( SAY root_expression )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:865:4: SAY root_expression
            {
            match(input,SAY,FOLLOW_SAY_in_speak_statement1129); 

            pushFollow(FOLLOW_root_expression_in_speak_statement1131);
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
            		builder.addStepLabel(OpcodeType.SAY, -1);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:883:1: return_statement : RETURN ( root_expression | NOW ) ;
    public final QuorumTreeWalker.return_statement_return return_statement() throws RecognitionException {
        QuorumTreeWalker.return_statement_return retval = new QuorumTreeWalker.return_statement_return();
        retval.start = input.LT(1);


        CommonTree NOW17=null;
        CommonTree RETURN18=null;
        QuorumTreeWalker.root_expression_return root_expression16 =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:884:2: ( RETURN ( root_expression | NOW ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:884:4: RETURN ( root_expression | NOW )
            {
            RETURN18=(CommonTree)match(input,RETURN,FOLLOW_RETURN_in_return_statement1145); 

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:884:11: ( root_expression | NOW )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:884:12: root_expression
                    {
                    pushFollow(FOLLOW_root_expression_in_return_statement1148);
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
                    		builder.addStepLabel(OpcodeType.RETURN, -1);
                    	

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:901:4: NOW
                    {
                    NOW17=(CommonTree)match(input,NOW,FOLLOW_NOW_in_return_statement1156); 


                    		LineInformation location = new LineInformation();
                                    location.setEndColumn(NOW17.getCharPositionInLine());
                                    location.setEndLine(NOW17.getLine());
                                    location.setStartColumn(RETURN18.getCharPositionInLine());
                                    location.setStartLine(RETURN18.getLine());
                                    location.setFile(getGrammarFileNameNoExtension());

                                    symbol.addStatementFlagToCurrentFile(RETURN18.getLine());
                    		stepFactory.addReturnStep(location, null, null);
                    		builder.addStepLabel(OpcodeType.RETURN, -1);
                    	

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
        	// do for sure before leaving
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:916:1: generic_declaration : ^( GENERIC LESS ids+= ID ( COMMA ids+= ID )* GREATER ) ;
    public final QuorumTreeWalker.generic_declaration_return generic_declaration() throws RecognitionException {
        QuorumTreeWalker.generic_declaration_return retval = new QuorumTreeWalker.generic_declaration_return();
        retval.start = input.LT(1);


        CommonTree ids=null;
        List list_ids=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:917:2: ( ^( GENERIC LESS ids+= ID ( COMMA ids+= ID )* GREATER ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:917:4: ^( GENERIC LESS ids+= ID ( COMMA ids+= ID )* GREATER )
            {
            match(input,GENERIC,FOLLOW_GENERIC_in_generic_declaration1174); 

            match(input, Token.DOWN, null); 
            match(input,LESS,FOLLOW_LESS_in_generic_declaration1176); 

            ids=(CommonTree)match(input,ID,FOLLOW_ID_in_generic_declaration1180); 
            if (list_ids==null) list_ids=new ArrayList();
            list_ids.add(ids);


            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:917:27: ( COMMA ids+= ID )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==COMMA) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:917:28: COMMA ids+= ID
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_generic_declaration1183); 

            	    ids=(CommonTree)match(input,ID,FOLLOW_ID_in_generic_declaration1187); 
            	    if (list_ids==null) list_ids=new ArrayList();
            	    list_ids.add(ids);


            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);


            match(input,GREATER,FOLLOW_GREATER_in_generic_declaration1191); 

            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:920:1: generic_statement returns [ArrayList<GenericDescriptor> templateTypes] : ^( GENERIC LESS ad= assignment_declaration ( COMMA a= assignment_declaration )* GREATER ) ;
    public final QuorumTreeWalker.generic_statement_return generic_statement() throws RecognitionException {
        QuorumTreeWalker.generic_statement_return retval = new QuorumTreeWalker.generic_statement_return();
        retval.start = input.LT(1);


        QuorumTreeWalker.assignment_declaration_return ad =null;

        QuorumTreeWalker.assignment_declaration_return a =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:921:2: ( ^( GENERIC LESS ad= assignment_declaration ( COMMA a= assignment_declaration )* GREATER ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:921:4: ^( GENERIC LESS ad= assignment_declaration ( COMMA a= assignment_declaration )* GREATER )
            {
            match(input,GENERIC,FOLLOW_GENERIC_in_generic_statement1209); 

            match(input, Token.DOWN, null); 
            match(input,LESS,FOLLOW_LESS_in_generic_statement1211); 


            		ArrayList<GenericDescriptor> types = new ArrayList<GenericDescriptor>();
            	

            pushFollow(FOLLOW_assignment_declaration_in_generic_statement1220);
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
            	

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:939:3: ( COMMA a= assignment_declaration )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==COMMA) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:939:4: COMMA a= assignment_declaration
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_generic_statement1228); 

            	    pushFollow(FOLLOW_assignment_declaration_in_generic_statement1232);
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
            	

            match(input,GREATER,FOLLOW_GREATER_in_generic_statement1248); 

            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:966:1: class_type returns [TypeDescriptor myType] : qn= qualified_name ;
    public final QuorumTreeWalker.class_type_return class_type() throws RecognitionException {
        QuorumTreeWalker.class_type_return retval = new QuorumTreeWalker.class_type_return();
        retval.start = input.LT(1);


        QuorumTreeWalker.qualified_name_return qn =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:967:2: (qn= qualified_name )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:967:4: qn= qualified_name
            {
            pushFollow(FOLLOW_qualified_name_in_class_type1273);
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
        	// do for sure before leaving
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:996:1: assignment_declaration returns [TypeDescriptor myType] : (qn= qualified_name (gs= generic_statement )? | INTEGER_KEYWORD | NUMBER_KEYWORD | TEXT | BOOLEAN_KEYWORD );
    public final QuorumTreeWalker.assignment_declaration_return assignment_declaration() throws RecognitionException {
        QuorumTreeWalker.assignment_declaration_return retval = new QuorumTreeWalker.assignment_declaration_return();
        retval.start = input.LT(1);


        QuorumTreeWalker.qualified_name_return qn =null;

        QuorumTreeWalker.generic_statement_return gs =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:997:2: (qn= qualified_name (gs= generic_statement )? | INTEGER_KEYWORD | NUMBER_KEYWORD | TEXT | BOOLEAN_KEYWORD )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:997:4: qn= qualified_name (gs= generic_statement )?
                    {
                    pushFollow(FOLLOW_qualified_name_in_assignment_declaration1295);
                    qn=qualified_name();

                    state._fsp--;


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:997:26: (gs= generic_statement )?
                    int alt45=2;
                    int LA45_0 = input.LA(1);

                    if ( (LA45_0==GENERIC) ) {
                        alt45=1;
                    }
                    switch (alt45) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:997:26: gs= generic_statement
                            {
                            pushFollow(FOLLOW_generic_statement_in_assignment_declaration1299);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1027:4: INTEGER_KEYWORD
                    {
                    match(input,INTEGER_KEYWORD,FOLLOW_INTEGER_KEYWORD_in_assignment_declaration1308); 


                    		TypeDescriptor type = new TypeDescriptor();
                    		type.setName(TypeDescriptor.INTEGER);
                    		retval.myType = type;
                    	

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1033:4: NUMBER_KEYWORD
                    {
                    match(input,NUMBER_KEYWORD,FOLLOW_NUMBER_KEYWORD_in_assignment_declaration1316); 


                    		TypeDescriptor type = new TypeDescriptor();
                    		type.setName(TypeDescriptor.NUMBER);
                    		retval.myType = type;
                    	

                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1039:4: TEXT
                    {
                    match(input,TEXT,FOLLOW_TEXT_in_assignment_declaration1324); 


                    		TypeDescriptor type = new TypeDescriptor();
                    		type.setName(TypeDescriptor.TEXT);
                    		retval.myType = type;
                    	

                    }
                    break;
                case 5 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1045:4: BOOLEAN_KEYWORD
                    {
                    match(input,BOOLEAN_KEYWORD,FOLLOW_BOOLEAN_KEYWORD_in_assignment_declaration1332); 


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
        	// do for sure before leaving
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1051:1: assignment_statement : ( (sel= selector COLON )? ID rhs= assign_right_hand_side |obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side | (modifier= access_modifier )? type= assignment_declaration name= ID (rhs= assign_right_hand_side )? );
    public final QuorumTreeWalker.assignment_statement_return assignment_statement() throws RecognitionException {
        QuorumTreeWalker.assignment_statement_return retval = new QuorumTreeWalker.assignment_statement_return();
        retval.start = input.LT(1);


        CommonTree name=null;
        CommonTree ID19=null;
        CommonTree ID20=null;
        CommonTree PARENT21=null;
        QuorumTreeWalker.selector_return sel =null;

        QuorumTreeWalker.assign_right_hand_side_return rhs =null;

        QuorumTreeWalker.qualified_name_return obj =null;

        QuorumTreeWalker.qualified_name_return parent =null;

        QuorumTreeWalker.access_modifier_return modifier =null;

        QuorumTreeWalker.assignment_declaration_return type =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1052:2: ( (sel= selector COLON )? ID rhs= assign_right_hand_side |obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side | (modifier= access_modifier )? type= assignment_declaration name= ID (rhs= assign_right_hand_side )? )
            int alt51=3;
            alt51 = dfa51.predict(input);
            switch (alt51) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1053:3: (sel= selector COLON )? ID rhs= assign_right_hand_side
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1053:3: (sel= selector COLON )?
                    int alt47=2;
                    int LA47_0 = input.LA(1);

                    if ( ((LA47_0 >= PARENT && LA47_0 <= ME)) ) {
                        alt47=1;
                    }
                    switch (alt47) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1053:4: sel= selector COLON
                            {
                            pushFollow(FOLLOW_selector_in_assignment_statement1348);
                            sel=selector();

                            state._fsp--;


                            match(input,COLON,FOLLOW_COLON_in_assignment_statement1350); 

                            }
                            break;

                    }


                    ID19=(CommonTree)match(input,ID,FOLLOW_ID_in_assignment_statement1354); 

                    pushFollow(FOLLOW_assign_right_hand_side_in_assignment_statement1358);
                    rhs=assign_right_hand_side();

                    state._fsp--;



                    		boolean isMe = false;
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
                    				isMe = true;
                    			}
                    		}
                    		
                    		symbol.addStatementFlagToCurrentFile((ID19!=null?ID19.getLine():0));
                    		
                    		stepFactory.addAssignmentStep(location, (ID19!=null?ID19.getText():null), (rhs!=null?rhs.eval:null), (rhs!=null?rhs.step:null), false, "", cd, isMe);
                    		builder.addStepLabel(OpcodeType.ASSIGNMENT, -1);
                    	

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1084:4: obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side
                    {
                    pushFollow(FOLLOW_qualified_name_in_assignment_statement1368);
                    obj=qualified_name();

                    state._fsp--;


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1084:23: ( COLON PARENT COLON parent= qualified_name )?
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
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1084:24: COLON PARENT COLON parent= qualified_name
                            {
                            match(input,COLON,FOLLOW_COLON_in_assignment_statement1371); 

                            PARENT21=(CommonTree)match(input,PARENT,FOLLOW_PARENT_in_assignment_statement1373); 

                            match(input,COLON,FOLLOW_COLON_in_assignment_statement1375); 

                            pushFollow(FOLLOW_qualified_name_in_assignment_statement1379);
                            parent=qualified_name();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input,COLON,FOLLOW_COLON_in_assignment_statement1383); 

                    ID20=(CommonTree)match(input,ID,FOLLOW_ID_in_assignment_statement1385); 

                    pushFollow(FOLLOW_assign_right_hand_side_in_assignment_statement1389);
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
                    		
                    		stepFactory.addAssignmentStep(location, (obj!=null?obj.type:null).getStaticKey(), (rhs!=null?rhs.eval:null), (rhs!=null?rhs.step:null), isLocal, (ID20!=null?ID20.getText():null), cd, false);
                    		builder.addStepLabel(OpcodeType.ASSIGNMENT, -1);
                    	

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1115:4: (modifier= access_modifier )? type= assignment_declaration name= ID (rhs= assign_right_hand_side )?
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1115:13: (modifier= access_modifier )?
                    int alt49=2;
                    int LA49_0 = input.LA(1);

                    if ( ((LA49_0 >= PUBLIC && LA49_0 <= PRIVATE)) ) {
                        alt49=1;
                    }
                    switch (alt49) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1115:13: modifier= access_modifier
                            {
                            pushFollow(FOLLOW_access_modifier_in_assignment_statement1401);
                            modifier=access_modifier();

                            state._fsp--;


                            }
                            break;

                    }


                    pushFollow(FOLLOW_assignment_declaration_in_assignment_statement1408);
                    type=assignment_declaration();

                    state._fsp--;


                    name=(CommonTree)match(input,ID,FOLLOW_ID_in_assignment_statement1414); 

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1115:75: (rhs= assign_right_hand_side )?
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( (LA50_0==EQUALITY) ) {
                        alt50=1;
                    }
                    switch (alt50) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1115:75: rhs= assign_right_hand_side
                            {
                            pushFollow(FOLLOW_assign_right_hand_side_in_assignment_statement1418);
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
                                    	builder.addStepLabel(OpcodeType.ROOT_EXPRESSION, -1);
                                        	stepFactory.addAssignmentStep(location, (name!=null?name.getText():null), isLocal);
                                    }
                                    builder.addStepLabel(OpcodeType.ASSIGNMENT, -1);
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1144:1: assign_right_hand_side returns [ExpressionValue eval, ExecutionStep step] : ( EQUALITY root_expression ) ;
    public final QuorumTreeWalker.assign_right_hand_side_return assign_right_hand_side() throws RecognitionException {
        QuorumTreeWalker.assign_right_hand_side_return retval = new QuorumTreeWalker.assign_right_hand_side_return();
        retval.start = input.LT(1);


        QuorumTreeWalker.root_expression_return root_expression22 =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1145:2: ( ( EQUALITY root_expression ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1146:3: ( EQUALITY root_expression )
            {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1146:3: ( EQUALITY root_expression )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1146:4: EQUALITY root_expression
            {
            match(input,EQUALITY,FOLLOW_EQUALITY_in_assign_right_hand_side1439); 

            pushFollow(FOLLOW_root_expression_in_assign_right_hand_side1441);
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
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "assign_right_hand_side"


    protected static class if_statement_scope {
        IfStatementInfo info;
        int else_if_counter;
        String endMatch;
    }
    protected Stack if_statement_stack = new Stack();


    public static class if_statement_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "if_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1154:1: if_statement : begin_if= IF condition= root_expression b= block[true] (begin_else_if= ELSE_IF condition2= root_expression b= block[true] )* (begin_else= ELSE b= block[true] )? end= END ;
    public final QuorumTreeWalker.if_statement_return if_statement() throws RecognitionException {
        if_statement_stack.push(new if_statement_scope());
        QuorumTreeWalker.if_statement_return retval = new QuorumTreeWalker.if_statement_return();
        retval.start = input.LT(1);


        CommonTree begin_if=null;
        CommonTree begin_else_if=null;
        CommonTree begin_else=null;
        CommonTree end=null;
        QuorumTreeWalker.root_expression_return condition =null;

        QuorumTreeWalker.block_return b =null;

        QuorumTreeWalker.root_expression_return condition2 =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1160:3: (begin_if= IF condition= root_expression b= block[true] (begin_else_if= ELSE_IF condition2= root_expression b= block[true] )* (begin_else= ELSE b= block[true] )? end= END )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1160:5: begin_if= IF condition= root_expression b= block[true] (begin_else_if= ELSE_IF condition2= root_expression b= block[true] )* (begin_else= ELSE b= block[true] )? end= END
            {
            begin_if=(CommonTree)match(input,IF,FOLLOW_IF_in_if_statement1467); 


            			((if_statement_scope)if_statement_stack.peek()).endMatch = "if";
            			((if_statement_scope)if_statement_stack.peek()).info = new IfStatementInfo();
            			((if_statement_scope)if_statement_stack.peek()).else_if_counter = 0;
            			((if_statement_scope)if_statement_stack.peek()).info.endLabel = (begin_if!=null?begin_if.getText():null) + labelCounter + ((if_statement_scope)if_statement_stack.peek()).info.END;
            			((if_statement_scope)if_statement_stack.peek()).info.ifFalseLabel =(begin_if!=null?begin_if.getText():null) + labelCounter + ((if_statement_scope)if_statement_stack.peek()).info.FALSE;
            			((if_statement_scope)if_statement_stack.peek()).info.ifStartLabel =(begin_if!=null?begin_if.getText():null) + labelCounter + ((if_statement_scope)if_statement_stack.peek()).info.START;
            			symbol.addStatementFlagToCurrentFile(begin_if.getLine());
            			labelCounter++;		
            		

            pushFollow(FOLLOW_root_expression_in_if_statement1480);
            condition=root_expression();

            state._fsp--;



            			stepFactory.assertBooleanExpression((condition!=null?condition.eval:null).getType(),
            			    (condition!=null?condition.step:null),
            			    getGrammarFileNameNoExtension());
            			    
            			((if_statement_scope)if_statement_stack.peek()).info.ifLocation = new LineInformation(
            				begin_if.getLine(),
            				begin_if.getLine(),
            				begin_if.getCharPositionInLine(),
            				(begin_if!=null?begin_if.getText():null).length() + begin_if.getCharPositionInLine());
            				
            			ConditionalJumpIfStep ifConditionalStep = new ConditionalJumpIfStep();
            			ifConditionalStep.setEndColumn((begin_if!=null?begin_if.getText():null).length() + begin_if.getCharPositionInLine());
            			ifConditionalStep.setEndLine(begin_if.getLine());
            			ifConditionalStep.setBeginColumn(begin_if.getCharPositionInLine());
            			ifConditionalStep.setBeginLine(begin_if.getLine());

            			ifConditionalStep.setLeftRegister((condition!=null?condition.eval:null).getRegister());
            			((if_statement_scope)if_statement_stack.peek()).info.ifConditionalStep = ifConditionalStep;

            			stepFactory.startIf(((if_statement_scope)if_statement_stack.peek()).info);
            			
            		

            pushFollow(FOLLOW_block_in_if_statement1492);
            b=block(true);

            state._fsp--;



                                    
                                    ((if_statement_scope)if_statement_stack.peek()).info.ifJumpStep.setBeginColumn(begin_if.getCharPositionInLine());
            			((if_statement_scope)if_statement_stack.peek()).info.ifJumpStep.setEndColumn(begin_if.getCharPositionInLine() + ((begin_if!=null?begin_if.getText():null).length()));
            	                ((if_statement_scope)if_statement_stack.peek()).info.ifJumpStep.setEndLine(begin_if.getLine());
            			stepFactory.addIfEndJumpStep(((if_statement_scope)if_statement_stack.peek()).info);
            			
            		

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1204:3: (begin_else_if= ELSE_IF condition2= root_expression b= block[true] )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==ELSE_IF) ) {
                    alt52=1;
                }


                switch (alt52) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1204:4: begin_else_if= ELSE_IF condition2= root_expression b= block[true]
            	    {
            	    begin_else_if=(CommonTree)match(input,ELSE_IF,FOLLOW_ELSE_IF_in_if_statement1509); 


            	    			
            	    			if(((if_statement_scope)if_statement_stack.peek()).endMatch.equals("if"))
            	    			{
            	    				((if_statement_scope)if_statement_stack.peek()).info.ifJumpStep.setBeginLine(begin_else_if.getLine());
            	    				stepFactory.endIf(((if_statement_scope)if_statement_stack.peek()).info);
            	    			}
            	    			else
            	    			{
            	    				((if_statement_scope)if_statement_stack.peek()).info.elseIfJumpSteps.get(((if_statement_scope)if_statement_stack.peek()).else_if_counter).setBeginLine(begin_else_if.getLine());
            	    				stepFactory.endElseIf(((if_statement_scope)if_statement_stack.peek()).info, ((if_statement_scope)if_statement_stack.peek()).else_if_counter);
            	    				((if_statement_scope)if_statement_stack.peek()).else_if_counter = ((if_statement_scope)if_statement_stack.peek()).else_if_counter + 1;
            	    			}
            	    			((if_statement_scope)if_statement_stack.peek()).endMatch = "elseif";
            	    			
            	    			((if_statement_scope)if_statement_stack.peek()).info.elseIfEndLabels.add((begin_else_if!=null?begin_else_if.getText():null)  + labelCounter + ((if_statement_scope)if_statement_stack.peek()).info.END + ((if_statement_scope)if_statement_stack.peek()).else_if_counter);
            	    			((if_statement_scope)if_statement_stack.peek()).info.elseIfFalseLabels.add((begin_else_if!=null?begin_else_if.getText():null) + labelCounter + ((if_statement_scope)if_statement_stack.peek()).info.FALSE + ((if_statement_scope)if_statement_stack.peek()).else_if_counter);
            	    			((if_statement_scope)if_statement_stack.peek()).info.elseIfStartLabels.add((begin_else_if!=null?begin_else_if.getText():null) + + labelCounter + ((if_statement_scope)if_statement_stack.peek()).info.START + ((if_statement_scope)if_statement_stack.peek()).else_if_counter);	
            	    			
            	    			symbol.addStatementFlagToCurrentFile(begin_else_if.getLine());		
            	    			labelCounter++;					
            	    		

            	    pushFollow(FOLLOW_root_expression_in_if_statement1522);
            	    condition2=root_expression();

            	    state._fsp--;



            	    			stepFactory.assertBooleanExpression((condition2!=null?condition2.eval:null).getType(),
            	    			    (condition2!=null?condition2.step:null),
            	    			    getGrammarFileNameNoExtension());
            	    			    
            	    			((if_statement_scope)if_statement_stack.peek()).info.elseIfLocations.add(new LineInformation(
            	    				begin_else_if.getLine(),
            	    				begin_else_if.getLine(),
            	    				begin_else_if.getCharPositionInLine(),
            	    				(begin_else_if!=null?begin_else_if.getText():null).length() + begin_else_if.getCharPositionInLine()));
            	    			
            	    			ConditionalJumpIfStep conditionalStep = new ConditionalJumpIfStep();
            	    			conditionalStep.setIsElseIf(true);
            	    			conditionalStep.setEndColumn((begin_else_if!=null?begin_else_if.getText():null).length() + begin_else_if.getCharPositionInLine());
            	    			conditionalStep.setEndLine(begin_else_if.getLine());
            	    			conditionalStep.setBeginColumn(begin_else_if.getCharPositionInLine());
            	    			conditionalStep.setBeginLine(begin_else_if.getLine());

            	    			conditionalStep.setLeftRegister((condition2!=null?condition2.eval:null).getRegister());
            	    			
            	    			((if_statement_scope)if_statement_stack.peek()).info.elseIfConditionalSteps.add(conditionalStep);	
            	    			
            	    			stepFactory.startElseIf(((if_statement_scope)if_statement_stack.peek()).info,((if_statement_scope)if_statement_stack.peek()).else_if_counter);
            	    		

            	    pushFollow(FOLLOW_block_in_if_statement1532);
            	    b=block(true);

            	    state._fsp--;



            	    			JumpStep jump = new JumpStep();
            	    			jump.setType(JumpType.IF);
            	    			jump.setBeginColumn(begin_else_if.getCharPositionInLine());
            	    			jump.setEndColumn(begin_else_if.getCharPositionInLine() + ((begin_else_if!=null?begin_else_if.getText():null).length()));
            	    			jump.setEndLine(begin_else_if.getLine());
            	    			((if_statement_scope)if_statement_stack.peek()).info.elseIfJumpSteps.add(jump);
            	    			
            	    			stepFactory.addElseIfEndJumpStep(((if_statement_scope)if_statement_stack.peek()).info, ((if_statement_scope)if_statement_stack.peek()).else_if_counter);	
            	    		


            	    								
            	    		

            	    }
            	    break;

            	default :
            	    break loop52;
                }
            } while (true);


            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1267:3: (begin_else= ELSE b= block[true] )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==ELSE) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1267:4: begin_else= ELSE b= block[true]
                    {
                    begin_else=(CommonTree)match(input,ELSE,FOLLOW_ELSE_in_if_statement1566); 


                    			if(((if_statement_scope)if_statement_stack.peek()).endMatch.equals("if"))
                    			{
                    				((if_statement_scope)if_statement_stack.peek()).info.ifJumpStep.setBeginLine(begin_else.getLine());
                    				stepFactory.endIf(((if_statement_scope)if_statement_stack.peek()).info);
                    			}
                    			else
                    			{
                    				((if_statement_scope)if_statement_stack.peek()).info.elseIfJumpSteps.get(((if_statement_scope)if_statement_stack.peek()).else_if_counter).setBeginLine(begin_else.getLine());
                    				stepFactory.endElseIf(((if_statement_scope)if_statement_stack.peek()).info, ((if_statement_scope)if_statement_stack.peek()).else_if_counter);
                    				((if_statement_scope)if_statement_stack.peek()).else_if_counter = ((if_statement_scope)if_statement_stack.peek()).else_if_counter + 1;
                    			}
                    			((if_statement_scope)if_statement_stack.peek()).endMatch = "else";
                    			
                    			((if_statement_scope)if_statement_stack.peek()).info.elseStartLabel = (begin_else!=null?begin_else.getText():null) + labelCounter + ((if_statement_scope)if_statement_stack.peek()).info.START;	
                    			((if_statement_scope)if_statement_stack.peek()).info.hasElse = true;
                    			labelCounter++;	
                    			stepFactory.startElse(((if_statement_scope)if_statement_stack.peek()).info);	
                    		

                    pushFollow(FOLLOW_block_in_if_statement1576);
                    b=block(true);

                    state._fsp--;


                    }
                    break;

            }


            end=(CommonTree)match(input,END,FOLLOW_END_in_if_statement1585); 


            			if(((if_statement_scope)if_statement_stack.peek()).endMatch.equals("if"))
            			{
            				((if_statement_scope)if_statement_stack.peek()).info.ifJumpStep.setBeginLine(end.getLine());
            				stepFactory.endIf(((if_statement_scope)if_statement_stack.peek()).info);
            			}
            			else if(((if_statement_scope)if_statement_stack.peek()).endMatch.equals("elseif"))
            			{
            				((if_statement_scope)if_statement_stack.peek()).info.elseIfJumpSteps.get(((if_statement_scope)if_statement_stack.peek()).else_if_counter).setBeginLine(end.getLine());
            				stepFactory.endElseIf(((if_statement_scope)if_statement_stack.peek()).info, ((if_statement_scope)if_statement_stack.peek()).else_if_counter);
            				// ((if_statement_scope)if_statement_stack.peek()).else_if_counter = ((if_statement_scope)if_statement_stack.peek()).else_if_counter + 1;
            			}
            			else
            			{
            				stepFactory.endElse();
            			}
            			
            			stepFactory.endIfBlock(((if_statement_scope)if_statement_stack.peek()).info);
              																		
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1311:1: loop_statement : REPEAT ( (expr= root_expression TIMES ) | ( ( WHILE | UNTIL ) expr= root_expression ) ) block[true] END ;
    public final QuorumTreeWalker.loop_statement_return loop_statement() throws RecognitionException {
        loop_statement_stack.push(new loop_statement_scope());
        QuorumTreeWalker.loop_statement_return retval = new QuorumTreeWalker.loop_statement_return();
        retval.start = input.LT(1);


        CommonTree REPEAT23=null;
        CommonTree TIMES24=null;
        QuorumTreeWalker.root_expression_return expr =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1330:2: ( REPEAT ( (expr= root_expression TIMES ) | ( ( WHILE | UNTIL ) expr= root_expression ) ) block[true] END )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1331:2: REPEAT ( (expr= root_expression TIMES ) | ( ( WHILE | UNTIL ) expr= root_expression ) ) block[true] END
            {
            REPEAT23=(CommonTree)match(input,REPEAT,FOLLOW_REPEAT_in_loop_statement1608); 



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
            		((loop_statement_scope)loop_statement_stack.peek()).jumpToTop.setType(JumpType.LOOP);
            		((loop_statement_scope)loop_statement_stack.peek()).jumpToTop.setLineInformation(((loop_statement_scope)loop_statement_stack.peek()).location);
            		symbol.addStatementFlagToCurrentFile(REPEAT23.getLine());

            		((loop_statement_scope)loop_statement_stack.peek()).first_value = null;
            		((loop_statement_scope)loop_statement_stack.peek()).first_step = null;
            		((loop_statement_scope)loop_statement_stack.peek()).last_value = null;
            		((loop_statement_scope)loop_statement_stack.peek()).last_step = null;
            		((loop_statement_scope)loop_statement_stack.peek()).type = -1;
            		((loop_statement_scope)loop_statement_stack.peek()).cJumpStep = null;
            		
            				symbol.getControlFlow().repeatStart();
            	

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1359:2: ( (expr= root_expression TIMES ) | ( ( WHILE | UNTIL ) expr= root_expression ) )
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==ROOT_EXPRESSION) ) {
                alt55=1;
            }
            else if ( ((LA55_0 >= WHILE && LA55_0 <= UNTIL)) ) {
                alt55=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;

            }
            switch (alt55) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1389:3: (expr= root_expression TIMES )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1389:3: (expr= root_expression TIMES )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1389:4: expr= root_expression TIMES
                    {
                    pushFollow(FOLLOW_root_expression_in_loop_statement1681);
                    expr=root_expression();

                    state._fsp--;


                    TIMES24=(CommonTree)match(input,TIMES,FOLLOW_TIMES_in_loop_statement1683); 

                    }



                    		((loop_statement_scope)loop_statement_stack.peek()).first_value = (expr!=null?expr.eval:null);
                    		TypeDescriptor type = (expr!=null?expr.eval:null).getType();
                    		if(type == null || !type.isInteger()) {
                    			CompilerError error = new CompilerError(TIMES24.getLine(), "repeat times requires an integer.", ErrorType.REPEAT_TIMES_NON_INTEGER);
                    			vm.getCompilerErrors().addError(error);	
                    		}
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
                    		builder.addStepLabel(OpcodeType.TIMES, -1);
                    		builder.addMarker(((loop_statement_scope)loop_statement_stack.peek()).marker_bottom);
                    		stepFactory.addBeginScopeStep(((loop_statement_scope)loop_statement_stack.peek()).marker_loop, "loop");
                    		symbol.enterNextBlock();
                    		((loop_statement_scope)loop_statement_stack.peek()).type = 1;
                    		
                    	

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1417:4: ( ( WHILE | UNTIL ) expr= root_expression )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1417:4: ( ( WHILE | UNTIL ) expr= root_expression )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1417:5: ( WHILE | UNTIL ) expr= root_expression
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1417:5: ( WHILE | UNTIL )
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
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1417:6: WHILE
                            {
                            match(input,WHILE,FOLLOW_WHILE_in_loop_statement1694); 

                            ((loop_statement_scope)loop_statement_stack.peek()).isWhile = true;

                            }
                            break;
                        case 2 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1417:49: UNTIL
                            {
                            match(input,UNTIL,FOLLOW_UNTIL_in_loop_statement1700); 

                            ((loop_statement_scope)loop_statement_stack.peek()).isWhile = false;

                            }
                            break;

                    }



                                   		builder.addLabel(((loop_statement_scope)loop_statement_stack.peek()).jumpToTop);
                    	

                    pushFollow(FOLLOW_root_expression_in_loop_statement1715);
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
                    			builder.addStepLabel(OpcodeType.LOOP, -1);
                    			builder.addMarker(((loop_statement_scope)loop_statement_stack.peek()).marker_bottom);
                    			stepFactory.addBeginScopeStep(((loop_statement_scope)loop_statement_stack.peek()).marker_loop, "loop");
                    			symbol.enterNextBlock();

                    		

                    }


                    }
                    break;

            }


            pushFollow(FOLLOW_block_in_loop_statement1726);
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
            	

            match(input,END,FOLLOW_END_in_loop_statement1734); 


            		
            		symbol.getControlFlow().repeatEnd();
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            loop_statement_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "loop_statement"


    public static class selector_return extends TreeRuleReturnScope {
        public ScopeSelector scopeSel;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "selector"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1497:1: selector returns [ScopeSelector scopeSel] : ( ^( PARENT qualified_name ) | ME );
    public final QuorumTreeWalker.selector_return selector() throws RecognitionException {
        QuorumTreeWalker.selector_return retval = new QuorumTreeWalker.selector_return();
        retval.start = input.LT(1);


        CommonTree PARENT26=null;
        QuorumTreeWalker.qualified_name_return qualified_name25 =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1498:2: ( ^( PARENT qualified_name ) | ME )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1498:4: ^( PARENT qualified_name )
                    {
                    PARENT26=(CommonTree)match(input,PARENT,FOLLOW_PARENT_in_selector1773); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_qualified_name_in_selector1775);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1511:4: ME
                    {
                    match(input,ME,FOLLOW_ME_in_selector1784); 


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
        	// do for sure before leaving
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1519:1: root_expression returns [ExpressionValue eval, ExecutionStep step] : ^( ROOT_EXPRESSION expression ) ;
    public final QuorumTreeWalker.root_expression_return root_expression() throws RecognitionException {
        QuorumTreeWalker.root_expression_return retval = new QuorumTreeWalker.root_expression_return();
        retval.start = input.LT(1);


        QuorumTreeWalker.expression_return expression27 =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1520:2: ( ^( ROOT_EXPRESSION expression ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1521:2: ^( ROOT_EXPRESSION expression )
            {

            		builder.addStepLabel(OpcodeType.ROOT_EXPRESSION, -1);
            	

            match(input,ROOT_EXPRESSION,FOLLOW_ROOT_EXPRESSION_in_root_expression1810); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_root_expression1812);
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
        	// do for sure before leaving
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1532:1: expression returns [ExpressionValue eval, ExecutionStep step] : ( ^( UNARY_NOT NOT expr= expression ) | ^( EQUALITY left= expression right= expression ) | ^( NOTEQUALS left= expression right= expression ) | ^( GREATER left= expression right= expression ) | ^( GREATER_EQUAL left= expression right= expression ) | ^( INHERITS left= expression dec= class_type ) | ^( LESS left= expression right= expression ) | ^( LESS_EQUAL left= expression right= expression ) | ^( OR left= expression right= expression ) | ^( AND left= expression right= expression ) | ^( PLUS left= expression right= expression ) | ^( MINUS left= expression right= expression ) | ^( MULTIPLY left= expression right= expression ) | ^( DIVIDE left= expression right= expression ) | ^( MODULO left= expression right= expression ) | ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | ^( QUALIFIED_SOLO_EXPRESSION var= qualified_name ( COLON ID )? ) | ^( QUALIFIED_SOLO_PARENT_EXPRESSON var= qualified_name COLON PARENT COLON par= qualified_name COLON ID ) | ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name ) | ^( FUNCTION_CALL_PARENT PARENT COLON qn1= qualified_name COLON ID LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | BOOLEAN | ( MINUS )? DECIMAL | ( MINUS )? INT | STRING | NULL | ME | INPUT LEFT_PAREN input_expr= expression RIGHT_PAREN | CAST LEFT_PAREN castqn= assignment_declaration COMMA cast_expr= expression castrpn= RIGHT_PAREN );
    public final QuorumTreeWalker.expression_return expression() throws RecognitionException {
        QuorumTreeWalker.expression_return retval = new QuorumTreeWalker.expression_return();
        retval.start = input.LT(1);


        CommonTree castrpn=null;
        CommonTree ID29=null;
        CommonTree ID30=null;
        CommonTree PARENT31=null;
        CommonTree ID32=null;
        CommonTree ID35=null;
        CommonTree ID37=null;
        CommonTree BOOLEAN38=null;
        CommonTree DECIMAL39=null;
        CommonTree MINUS40=null;
        CommonTree INT41=null;
        CommonTree MINUS42=null;
        CommonTree STRING43=null;
        CommonTree NULL44=null;
        CommonTree ME45=null;
        CommonTree INPUT46=null;
        CommonTree RIGHT_PAREN47=null;
        CommonTree CAST48=null;
        QuorumTreeWalker.expression_return expr =null;

        QuorumTreeWalker.expression_return left =null;

        QuorumTreeWalker.expression_return right =null;

        QuorumTreeWalker.class_type_return dec =null;

        QuorumTreeWalker.function_expression_list_return fel =null;

        QuorumTreeWalker.qualified_name_return var =null;

        QuorumTreeWalker.qualified_name_return par =null;

        QuorumTreeWalker.qualified_name_return qn1 =null;

        QuorumTreeWalker.expression_return input_expr =null;

        QuorumTreeWalker.assignment_declaration_return castqn =null;

        QuorumTreeWalker.expression_return cast_expr =null;

        QuorumTreeWalker.qualified_name_return qualified_name28 =null;

        QuorumTreeWalker.qualified_name_return qualified_name33 =null;

        QuorumTreeWalker.selector_return selector34 =null;

        QuorumTreeWalker.qualified_name_return qualified_name36 =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1533:2: ( ^( UNARY_NOT NOT expr= expression ) | ^( EQUALITY left= expression right= expression ) | ^( NOTEQUALS left= expression right= expression ) | ^( GREATER left= expression right= expression ) | ^( GREATER_EQUAL left= expression right= expression ) | ^( INHERITS left= expression dec= class_type ) | ^( LESS left= expression right= expression ) | ^( LESS_EQUAL left= expression right= expression ) | ^( OR left= expression right= expression ) | ^( AND left= expression right= expression ) | ^( PLUS left= expression right= expression ) | ^( MINUS left= expression right= expression ) | ^( MULTIPLY left= expression right= expression ) | ^( DIVIDE left= expression right= expression ) | ^( MODULO left= expression right= expression ) | ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | ^( QUALIFIED_SOLO_EXPRESSION var= qualified_name ( COLON ID )? ) | ^( QUALIFIED_SOLO_PARENT_EXPRESSON var= qualified_name COLON PARENT COLON par= qualified_name COLON ID ) | ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name ) | ^( FUNCTION_CALL_PARENT PARENT COLON qn1= qualified_name COLON ID LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | BOOLEAN | ( MINUS )? DECIMAL | ( MINUS )? INT | STRING | NULL | ME | INPUT LEFT_PAREN input_expr= expression RIGHT_PAREN | CAST LEFT_PAREN castqn= assignment_declaration COMMA cast_expr= expression castrpn= RIGHT_PAREN )
            int alt62=29;
            switch ( input.LA(1) ) {
            case UNARY_NOT:
                {
                alt62=1;
                }
                break;
            case EQUALITY:
                {
                alt62=2;
                }
                break;
            case NOTEQUALS:
                {
                alt62=3;
                }
                break;
            case GREATER:
                {
                alt62=4;
                }
                break;
            case GREATER_EQUAL:
                {
                alt62=5;
                }
                break;
            case INHERITS:
                {
                alt62=6;
                }
                break;
            case LESS:
                {
                alt62=7;
                }
                break;
            case LESS_EQUAL:
                {
                alt62=8;
                }
                break;
            case OR:
                {
                alt62=9;
                }
                break;
            case AND:
                {
                alt62=10;
                }
                break;
            case PLUS:
                {
                alt62=11;
                }
                break;
            case MINUS:
                {
                switch ( input.LA(2) ) {
                case DOWN:
                    {
                    alt62=12;
                    }
                    break;
                case DECIMAL:
                    {
                    alt62=23;
                    }
                    break;
                case INT:
                    {
                    alt62=24;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 62, 12, input);

                    throw nvae;

                }

                }
                break;
            case MULTIPLY:
                {
                alt62=13;
                }
                break;
            case DIVIDE:
                {
                alt62=14;
                }
                break;
            case MODULO:
                {
                alt62=15;
                }
                break;
            case FUNCTION_CALL:
                {
                alt62=16;
                }
                break;
            case QUALIFIED_SOLO_EXPRESSION:
                {
                alt62=17;
                }
                break;
            case QUALIFIED_SOLO_PARENT_EXPRESSON:
                {
                alt62=18;
                }
                break;
            case QUALIFIED_SOLO_EXPRESSION_SELECTOR:
                {
                alt62=19;
                }
                break;
            case FUNCTION_CALL_PARENT:
                {
                alt62=20;
                }
                break;
            case FUNCTION_CALL_THIS:
                {
                alt62=21;
                }
                break;
            case BOOLEAN:
                {
                alt62=22;
                }
                break;
            case DECIMAL:
                {
                alt62=23;
                }
                break;
            case INT:
                {
                alt62=24;
                }
                break;
            case STRING:
                {
                alt62=25;
                }
                break;
            case NULL:
                {
                alt62=26;
                }
                break;
            case ME:
                {
                alt62=27;
                }
                break;
            case INPUT:
                {
                alt62=28;
                }
                break;
            case CAST:
                {
                alt62=29;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;

            }

            switch (alt62) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1534:3: ^( UNARY_NOT NOT expr= expression )
                    {
                    match(input,UNARY_NOT,FOLLOW_UNARY_NOT_in_expression1834); 

                    match(input, Token.DOWN, null); 
                    match(input,NOT,FOLLOW_NOT_in_expression1836); 

                    pushFollow(FOLLOW_expression_in_expression1842);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1541:4: ^( EQUALITY left= expression right= expression )
                    {
                    match(input,EQUALITY,FOLLOW_EQUALITY_in_expression1854); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1860);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression1866);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1548:4: ^( NOTEQUALS left= expression right= expression )
                    {
                    match(input,NOTEQUALS,FOLLOW_NOTEQUALS_in_expression1876); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1882);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression1888);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1555:4: ^( GREATER left= expression right= expression )
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_expression1898); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1904);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression1910);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1562:4: ^( GREATER_EQUAL left= expression right= expression )
                    {
                    match(input,GREATER_EQUAL,FOLLOW_GREATER_EQUAL_in_expression1920); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1926);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression1932);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1569:4: ^( INHERITS left= expression dec= class_type )
                    {
                    match(input,INHERITS,FOLLOW_INHERITS_in_expression1942); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1946);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_class_type_in_expression1950);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1576:4: ^( LESS left= expression right= expression )
                    {
                    match(input,LESS,FOLLOW_LESS_in_expression1960); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1966);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression1972);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1583:4: ^( LESS_EQUAL left= expression right= expression )
                    {
                    match(input,LESS_EQUAL,FOLLOW_LESS_EQUAL_in_expression1982); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1988);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression1994);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1590:4: ^( OR left= expression right= expression )
                    {
                    match(input,OR,FOLLOW_OR_in_expression2004); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2010);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression2016);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1597:4: ^( AND left= expression right= expression )
                    {
                    match(input,AND,FOLLOW_AND_in_expression2026); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2032);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression2038);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1604:4: ^( PLUS left= expression right= expression )
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_expression2048); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2054);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression2060);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1611:4: ^( MINUS left= expression right= expression )
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_expression2070); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2076);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression2082);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1618:4: ^( MULTIPLY left= expression right= expression )
                    {
                    match(input,MULTIPLY,FOLLOW_MULTIPLY_in_expression2092); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2098);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression2104);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1625:4: ^( DIVIDE left= expression right= expression )
                    {
                    match(input,DIVIDE,FOLLOW_DIVIDE_in_expression2114); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2120);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression2126);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1632:4: ^( MODULO left= expression right= expression )
                    {
                    match(input,MODULO,FOLLOW_MODULO_in_expression2136); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2142);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression2148);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1639:4: ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN fel= function_expression_list RIGHT_PAREN )
                    {
                    match(input,FUNCTION_CALL,FOLLOW_FUNCTION_CALL_in_expression2158); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_qualified_name_in_expression2160);
                    qualified_name28=qualified_name();

                    state._fsp--;


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1639:34: ( COLON ID )?
                    int alt57=2;
                    int LA57_0 = input.LA(1);

                    if ( (LA57_0==COLON) ) {
                        alt57=1;
                    }
                    switch (alt57) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1639:35: COLON ID
                            {
                            match(input,COLON,FOLLOW_COLON_in_expression2162); 

                            ID29=(CommonTree)match(input,ID,FOLLOW_ID_in_expression2164); 

                            }
                            break;

                    }


                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_expression2168); 


                    		boolean unsetFlag = false;
                    		boolean nested = inCallStep;
                    		if (!inCallStep) {
                    			// We are now inside a call step--set the flag appropriately.
                    			inCallStep = true;
                    			unsetFlag = true;
                    		}
                    	

                    pushFollow(FOLLOW_function_expression_list_in_expression2180);
                    fel=function_expression_list();

                    state._fsp--;


                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_expression2182); 

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
                    		
                    		if(fel!=null && !(fel!=null?fel.list:null).isEmpty()){
                    			builder.addCallLabel(parameterPosition);
                    			//j builder.addStepLabel(OpcodeType.METHOD_CALL);
                    		}
                    		
                    		ResultTuple result =  stepFactory.addCallStep(info);
                    		
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    		if(fel!=null){
                    			builder.addStepLabel(OpcodeType.METHOD_CALL, result.getStepCount());
                    		}
                    		
                    		if (unsetFlag)
                    			inCallStep = false;

                    	

                    }
                    break;
                case 17 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1722:4: ^( QUALIFIED_SOLO_EXPRESSION var= qualified_name ( COLON ID )? )
                    {
                    match(input,QUALIFIED_SOLO_EXPRESSION,FOLLOW_QUALIFIED_SOLO_EXPRESSION_in_expression2193); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_qualified_name_in_expression2197);
                    var=qualified_name();

                    state._fsp--;


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1722:51: ( COLON ID )?
                    int alt58=2;
                    int LA58_0 = input.LA(1);

                    if ( (LA58_0==COLON) ) {
                        alt58=1;
                    }
                    switch (alt58) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1722:52: COLON ID
                            {
                            match(input,COLON,FOLLOW_COLON_in_expression2200); 

                            ID30=(CommonTree)match(input,ID,FOLLOW_ID_in_expression2202); 

                            }
                            break;

                    }


                    match(input, Token.UP, null); 



                    		LineInformation location = new LineInformation (
                    			(var!=null?var.type:null).getLineBegin(),
                    			(var!=null?var.type:null).getLineEnd(),
                    			(var!=null?var.type:null).getColumnBegin(),
                    			(var!=null?var.type:null).getColumnEnd()
                    		);
                    		location.setFile(fileName);
                                    location.setClassName(symbol.getCurrentClass().getStaticKey());
                                    MethodDescriptor curMethod = symbol.getCurrentMethod();
                                    if(curMethod != null){
                                    	location.setMethodName(curMethod.getStaticKey());
                                    }

                    		ResultTuple result = new ResultTuple();
                    		ClassDescriptor cd = null;
                    		
                    		if(ID30 != null){
                    			result = stepFactory.addVariableInObjectMoveStep(location, null, null, temp, (var!=null?var.type:null), (ID30!=null?ID30.getText():null), null);
                    		}else{
                    			result = stepFactory.addVariableMoveStep(location, null, null, temp, (var!=null?var.type:null));
                    		}

                    		temp++;
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();


                    	

                    }
                    break;
                case 18 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1752:4: ^( QUALIFIED_SOLO_PARENT_EXPRESSON var= qualified_name COLON PARENT COLON par= qualified_name COLON ID )
                    {
                    match(input,QUALIFIED_SOLO_PARENT_EXPRESSON,FOLLOW_QUALIFIED_SOLO_PARENT_EXPRESSON_in_expression2214); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_qualified_name_in_expression2218);
                    var=qualified_name();

                    state._fsp--;


                    match(input,COLON,FOLLOW_COLON_in_expression2220); 

                    PARENT31=(CommonTree)match(input,PARENT,FOLLOW_PARENT_in_expression2222); 

                    match(input,COLON,FOLLOW_COLON_in_expression2224); 

                    pushFollow(FOLLOW_qualified_name_in_expression2228);
                    par=qualified_name();

                    state._fsp--;


                    match(input,COLON,FOLLOW_COLON_in_expression2230); 

                    ID32=(CommonTree)match(input,ID,FOLLOW_ID_in_expression2232); 

                    match(input, Token.UP, null); 



                    		LineInformation location = new LineInformation (
                    			(var!=null?var.type:null).getLineBegin(),
                    			(var!=null?var.type:null).getLineEnd(),
                    			(var!=null?var.type:null).getColumnBegin(),
                    			(var!=null?var.type:null).getColumnEnd()
                    		);
                    		location.setFile(fileName);
                                    location.setClassName(symbol.getCurrentClass().getStaticKey());
                                    MethodDescriptor curMethod = symbol.getCurrentMethod();
                                    if(curMethod != null){
                                    	location.setMethodName(curMethod.getStaticKey());
                                    }

                    		ResultTuple result = new ResultTuple();
                    		ClassDescriptor cd = null;
                    		
                    		if((par!=null?par.type:null) != null){
                    			cd = symbol.findClassDescriptorFromCurrentClass((par!=null?par.type:null).getStaticKey());
                    			
                    			if(cd == null){
                    				CompilerError error = new CompilerError(PARENT31.getLine(), "The class "+ symbol.getCurrentClass().getStaticKey() +" does not have access to " + (par!=null?par.type:null).getStaticKey(), ErrorType.MISSING_PARENT);
                    				vm.getCompilerErrors().addError(error);
                    			}
                    		}
                    		
                    		if(ID32 != null){
                    			result = stepFactory.addVariableInObjectMoveStep(location, null, null, temp, (var!=null?var.type:null), (ID32!=null?ID32.getText():null), cd);
                    		}else{
                    			result = stepFactory.addVariableMoveStep(location, null, null, temp, (var!=null?var.type:null));
                    		}

                    		temp++;
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 19 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1789:4: ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name )
                    {
                    match(input,QUALIFIED_SOLO_EXPRESSION_SELECTOR,FOLLOW_QUALIFIED_SOLO_EXPRESSION_SELECTOR_in_expression2242); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_selector_in_expression2244);
                    selector34=selector();

                    state._fsp--;


                    match(input,COLON,FOLLOW_COLON_in_expression2246); 

                    pushFollow(FOLLOW_qualified_name_in_expression2248);
                    qualified_name33=qualified_name();

                    state._fsp--;


                    match(input, Token.UP, null); 



                    		LineInformation location = new LineInformation (
                    			(qualified_name33!=null?qualified_name33.type:null).getLineBegin(),
                    			(qualified_name33!=null?qualified_name33.type:null).getLineEnd(),
                    			(qualified_name33!=null?qualified_name33.type:null).getColumnBegin(),
                    			(qualified_name33!=null?qualified_name33.type:null).getColumnEnd()
                    		);
                    		location.setFile(fileName);
                                    location.setClassName(symbol.getCurrentClass().getStaticKey());
                                    MethodDescriptor curMethod = symbol.getCurrentMethod();
                                    if(curMethod != null){
                                    	location.setMethodName(curMethod.getStaticKey());
                                    }
                    		
                    		ResultTuple result = new ResultTuple();
                    		ScopeSelector scope = (selector34!=null?selector34.scopeSel:null);
                    		
                    		if(!scope.isParent()){
                    			result = stepFactory.addMeVariableMoveStep(location, null, null, temp, (qualified_name33!=null?qualified_name33.type:null));
                    		} else{
                    			result = stepFactory.addParentVariableMoveStep(location, null, null, temp, (qualified_name33!=null?qualified_name33.type:null), scope.getCurrentClass());
                    		}
                    		temp++;
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    		
                    	

                    }
                    break;
                case 20 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1818:4: ^( FUNCTION_CALL_PARENT PARENT COLON qn1= qualified_name COLON ID LEFT_PAREN fel= function_expression_list RIGHT_PAREN )
                    {
                    match(input,FUNCTION_CALL_PARENT,FOLLOW_FUNCTION_CALL_PARENT_in_expression2260); 

                    match(input, Token.DOWN, null); 
                    match(input,PARENT,FOLLOW_PARENT_in_expression2262); 

                    match(input,COLON,FOLLOW_COLON_in_expression2264); 

                    pushFollow(FOLLOW_qualified_name_in_expression2268);
                    qn1=qualified_name();

                    state._fsp--;


                    match(input,COLON,FOLLOW_COLON_in_expression2270); 

                    ID35=(CommonTree)match(input,ID,FOLLOW_ID_in_expression2272); 

                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_expression2274); 


                    		boolean unsetFlag = false;
                    		boolean nested = inCallStep;
                    		if (!inCallStep) {
                    			// We are now inside a call step--set the flag appropriately.
                    			inCallStep = true;
                    			unsetFlag = true;
                    		}
                    	

                    pushFollow(FOLLOW_function_expression_list_in_expression2285);
                    fel=function_expression_list();

                    state._fsp--;


                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_expression2287); 

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
                    		
                    		int parameterPosition = -1;
                    		if(fel != null) {
                    			parameterPosition = (fel!=null?fel.firstParam:0);
                    			for(Object o : (fel!=null?fel.list:null)) {
                    				expression_return ex = (expression_return)o;
                                    		types.add(ex.eval.getType().getName());
                                    		argumentTypes.add(ex.eval.getType());
                                    		steps.add(ex.step);
                                    		values.add(ex.eval);
                                    		registers.add(ex.eval.getRegister());
                    			
                    			}
                    		}

                    		String key = MethodDescriptor.generateKey((ID35!=null?ID35.getText():null), types);
                    		String myMethodName = (ID35!=null?ID35.getText():null);
                    		
                    		CallInfo info = new CallInfo();
                    		info.register = temp;
                    		info.location = location;
                    		info.locatedIn = (qn1!=null?qn1.type:null).getStaticKey();
                    		info.argumentRegisters = registers;
                    		info.argumentSteps = steps;
                    		info.argumentTypes = argumentTypes;
                    		info.methodName = myMethodName;
                    		info.isObjectCall = false;
                    		info.isNested = nested;
                    		
                    		if(fel!=null && !(fel!=null?fel.list:null).isEmpty()){
                    			builder.addCallLabel(parameterPosition);
                    			//j builder.addStepLabel(OpcodeType.METHOD_CALL);
                    		}
                    		
                    		ResultTuple result =  stepFactory.addParentCallStep(info);
                    		
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    		
                    		if(fel!=null){
                    			builder.addStepLabel(OpcodeType.METHOD_CALL, result.getStepCount());
                    		}
                    		
                    		if (unsetFlag)
                    			inCallStep = false;
                    		
                    		
                    	

                    }
                    break;
                case 21 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1897:4: ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN fel= function_expression_list RIGHT_PAREN )
                    {
                    match(input,FUNCTION_CALL_THIS,FOLLOW_FUNCTION_CALL_THIS_in_expression2297); 

                    match(input, Token.DOWN, null); 
                    match(input,ME,FOLLOW_ME_in_expression2299); 

                    match(input,COLON,FOLLOW_COLON_in_expression2301); 

                    pushFollow(FOLLOW_qualified_name_in_expression2303);
                    qualified_name36=qualified_name();

                    state._fsp--;


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1897:49: ( COLON ID )?
                    int alt59=2;
                    int LA59_0 = input.LA(1);

                    if ( (LA59_0==COLON) ) {
                        alt59=1;
                    }
                    switch (alt59) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1897:50: COLON ID
                            {
                            match(input,COLON,FOLLOW_COLON_in_expression2306); 

                            ID37=(CommonTree)match(input,ID,FOLLOW_ID_in_expression2308); 

                            }
                            break;

                    }


                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_expression2312); 


                    		boolean unsetFlag = false;
                    		boolean nested = inCallStep;
                    		if (!inCallStep) {
                    			// We are now inside a call step--set the flag appropriately.
                    			inCallStep = true;
                    			unsetFlag = true;
                    		}
                    	

                    pushFollow(FOLLOW_function_expression_list_in_expression2323);
                    fel=function_expression_list();

                    state._fsp--;


                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_expression2325); 

                    match(input, Token.UP, null); 



                    		LineInformation location = new LineInformation();
                                    location.setEndColumn((qualified_name36!=null?qualified_name36.type:null).getColumnEnd());
                                    location.setEndLine((qualified_name36!=null?qualified_name36.type:null).getLineEnd());
                                    location.setStartColumn((qualified_name36!=null?qualified_name36.type:null).getColumnBegin());
                                    location.setStartLine((qualified_name36!=null?qualified_name36.type:null).getLineBegin());
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
                    		
                    		int parameterPosition = -1;
                    		if(fel != null) {
                    			parameterPosition = (fel!=null?fel.firstParam:0);
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
                                    if(ID37 == null) {
                                    	key = MethodDescriptor.generateKey((qualified_name36!=null?qualified_name36.type:null).getStaticKey(), types);
                                    	myMethodName = (qualified_name36!=null?qualified_name36.type:null).getStaticKey();
                    		}
                    		else {
                    			key = MethodDescriptor.generateKey((ID37!=null?ID37.getText():null), types);
                    			myMethodName = (ID37!=null?ID37.getText():null);
                    		}

                    		CallInfo info = new CallInfo();
                    		info.register = temp;
                    		info.location = location;
                    		info.argumentRegisters = registers;
                    		info.argumentSteps = steps;
                    		info.variable = (qualified_name36!=null?qualified_name36.type:null);
                    		info.argumentTypes = argumentTypes;
                    		info.methodName = myMethodName;
                    		info.isObjectCall = false;
                    		info.isThisCall = true;
                    		info.isNested = nested;
                    		
                    		if(fel!=null && !(fel!=null?fel.list:null).isEmpty()){
                    			builder.addCallLabel(parameterPosition);
                    		}
                    		
                    		ResultTuple result =  stepFactory.addCallStep(info);
                    		
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    		
                    		if(fel!=null){
                    			builder.addStepLabel(OpcodeType.METHOD_CALL, result.getStepCount());
                    		}
                    		
                    		if (unsetFlag)
                    			inCallStep = false;
                    	

                    }
                    break;
                case 22 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1982:4: BOOLEAN
                    {
                    BOOLEAN38=(CommonTree)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_expression2334); 


                    		LineInformation location = new LineInformation (
                    			BOOLEAN38.getLine(),
                    			BOOLEAN38.getLine(),
                    			BOOLEAN38.getCharPositionInLine(),
                    			BOOLEAN38.getCharPositionInLine() + (BOOLEAN38!=null?BOOLEAN38.getText():null).length()
                    		);
                    		location.setFile(fileName);
                    		
                    		ResultTuple result = stepFactory.addMoveStep(temp, location, Boolean.parseBoolean((BOOLEAN38!=null?BOOLEAN38.getText():null)));
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 23 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1997:4: ( MINUS )? DECIMAL
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1997:4: ( MINUS )?
                    int alt60=2;
                    int LA60_0 = input.LA(1);

                    if ( (LA60_0==MINUS) ) {
                        alt60=1;
                    }
                    switch (alt60) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1997:5: MINUS
                            {
                            MINUS40=(CommonTree)match(input,MINUS,FOLLOW_MINUS_in_expression2343); 

                            }
                            break;

                    }


                    DECIMAL39=(CommonTree)match(input,DECIMAL,FOLLOW_DECIMAL_in_expression2347); 


                    		LineInformation location = new LineInformation (
                    			DECIMAL39.getLine(),
                    			DECIMAL39.getLine(),
                    			DECIMAL39.getCharPositionInLine(),
                    			DECIMAL39.getCharPositionInLine() + (DECIMAL39!=null?DECIMAL39.getText():null).length()
                    		);
                    		location.setFile(fileName);
                    		
                    		double val = -1;
                    		try{
                    			val = Double.parseDouble((DECIMAL39!=null?DECIMAL39.getText():null));
                    		}catch(NumberFormatException e){
                    			CompilerError error = new CompilerError();
                    			error.setLineNumber(DECIMAL39.getLine());
                    			error.setError((DECIMAL39!=null?DECIMAL39.getText():null) + " is an invalid number.");
                    			error.setErrorType(ErrorType.INCOMPATIBLE_TYPES);
                    			error.setColumn(DECIMAL39.getCharPositionInLine());
                    			error.setFile(getGrammarFileNameNoExtension());
                    			vm.getCompilerErrors().addError(error);
                    		}
                    		
                    		if(MINUS40 != null) {
                    			val *= -1;
                    		}
                    		ResultTuple result = stepFactory.addMoveStep(temp, location, val);
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 24 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2028:4: ( MINUS )? INT
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2028:4: ( MINUS )?
                    int alt61=2;
                    int LA61_0 = input.LA(1);

                    if ( (LA61_0==MINUS) ) {
                        alt61=1;
                    }
                    switch (alt61) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2028:5: MINUS
                            {
                            MINUS42=(CommonTree)match(input,MINUS,FOLLOW_MINUS_in_expression2356); 

                            }
                            break;

                    }


                    INT41=(CommonTree)match(input,INT,FOLLOW_INT_in_expression2360); 


                    		LineInformation location = new LineInformation (
                    			INT41.getLine(),
                    			INT41.getLine(),
                    			INT41.getCharPositionInLine(),
                    			INT41.getCharPositionInLine() + (INT41!=null?INT41.getText():null).length()
                    		);
                    		location.setFile(fileName);
                    		int val = -1;
                    		try{
                    			val = Integer.parseInt((INT41!=null?INT41.getText():null));
                    		}catch(NumberFormatException e){
                    			CompilerError error = new CompilerError();
                    			error.setLineNumber(INT41.getLine());
                    			error.setError((INT41!=null?INT41.getText():null) + " is an invalid integer. The integer may be too large.");
                    			error.setErrorType(ErrorType.INCOMPATIBLE_TYPES);
                    			error.setColumn(INT41.getCharPositionInLine());
                    			error.setFile(getGrammarFileNameNoExtension());
                    			vm.getCompilerErrors().addError(error);
                    		}
                    		if(MINUS42 != null) {
                    			val *= -1;
                    		}
                    		ResultTuple result = stepFactory.addMoveStep(temp, location, val);
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 25 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2057:4: STRING
                    {
                    STRING43=(CommonTree)match(input,STRING,FOLLOW_STRING_in_expression2368); 


                    		LineInformation location = new LineInformation (
                    			STRING43.getLine(),
                    			STRING43.getLine(),
                    			STRING43.getCharPositionInLine(),
                    			STRING43.getCharPositionInLine() + (STRING43!=null?STRING43.getText():null).length()
                    		);
                    		location.setFile(fileName);
                    		ResultTuple result = stepFactory.addMoveStep(temp, location, (STRING43!=null?STRING43.getText():null));
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 26 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2071:4: NULL
                    {
                    NULL44=(CommonTree)match(input,NULL,FOLLOW_NULL_in_expression2376); 


                    		LineInformation location = new LineInformation (
                    			NULL44.getLine(),
                    			NULL44.getLine(),
                    			NULL44.getCharPositionInLine(),
                    			NULL44.getCharPositionInLine() + (NULL44!=null?NULL44.getText():null).length()
                    		);
                    		location.setFile(fileName);
                    		ResultTuple result = stepFactory.addMoveNullStep(temp, location);
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 27 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2085:4: ME
                    {
                    ME45=(CommonTree)match(input,ME,FOLLOW_ME_in_expression2384); 


                    		LineInformation location = new LineInformation (
                    			ME45.getLine(),
                    			ME45.getLine(),
                    			ME45.getCharPositionInLine(),
                    			ME45.getCharPositionInLine() + (ME45!=null?ME45.getText():null).length()
                    		);
                    		location.setFile(fileName);
                    		
                    		ResultTuple result = new ResultTuple();

                    		result = stepFactory.addMoveThisStep(location, temp);

                    		temp++;
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    	

                    }
                    break;
                case 28 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2103:4: INPUT LEFT_PAREN input_expr= expression RIGHT_PAREN
                    {
                    INPUT46=(CommonTree)match(input,INPUT,FOLLOW_INPUT_in_expression2392); 

                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_expression2394); 

                    pushFollow(FOLLOW_expression_in_expression2398);
                    input_expr=expression();

                    state._fsp--;


                    RIGHT_PAREN47=(CommonTree)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_expression2400); 


                    		LineInformation location = new LineInformation (
                    			INPUT46.getLine(),
                    			RIGHT_PAREN47.getLine(),
                    			INPUT46.getCharPositionInLine(),
                    			RIGHT_PAREN47.getCharPositionInLine()
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
                case 29 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2121:4: CAST LEFT_PAREN castqn= assignment_declaration COMMA cast_expr= expression castrpn= RIGHT_PAREN
                    {
                    CAST48=(CommonTree)match(input,CAST,FOLLOW_CAST_in_expression2408); 

                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_expression2410); 

                    pushFollow(FOLLOW_assignment_declaration_in_expression2414);
                    castqn=assignment_declaration();

                    state._fsp--;


                    match(input,COMMA,FOLLOW_COMMA_in_expression2416); 

                    pushFollow(FOLLOW_expression_in_expression2420);
                    cast_expr=expression();

                    state._fsp--;


                    castrpn=(CommonTree)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_expression2424); 


                    		LineInformation location = new LineInformation (
                    			CAST48.getLine(),
                    			castrpn.getLine(),
                    			CAST48.getCharPositionInLine(),
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
        	// do for sure before leaving
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2142:1: function_expression_list returns [List list, int firstParam] : ^( FUNCTION_EXPRESSION_LIST (e= expression )* ) ;
    public final QuorumTreeWalker.function_expression_list_return function_expression_list() throws RecognitionException {
        QuorumTreeWalker.function_expression_list_return retval = new QuorumTreeWalker.function_expression_list_return();
        retval.start = input.LT(1);


        QuorumTreeWalker.expression_return e =null;


        retval.list = new ArrayList(); retval.firstParam = -1;
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2144:2: ( ^( FUNCTION_EXPRESSION_LIST (e= expression )* ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2145:2: ^( FUNCTION_EXPRESSION_LIST (e= expression )* )
            {
            match(input,FUNCTION_EXPRESSION_LIST,FOLLOW_FUNCTION_EXPRESSION_LIST_in_function_expression_list2448); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2145:29: (e= expression )*
                loop63:
                do {
                    int alt63=2;
                    int LA63_0 = input.LA(1);

                    if ( ((LA63_0 >= FUNCTION_CALL && LA63_0 <= FUNCTION_CALL_THIS)||LA63_0==UNARY_NOT||(LA63_0 >= QUALIFIED_SOLO_EXPRESSION && LA63_0 <= QUALIFIED_SOLO_PARENT_EXPRESSON)||LA63_0==INHERITS||LA63_0==ME||LA63_0==OR||(LA63_0 >= LESS && LA63_0 <= GREATER)||LA63_0==EQUALITY||(LA63_0 >= AND && LA63_0 <= MODULO)||(LA63_0 >= CAST && LA63_0 <= INPUT)) ) {
                        alt63=1;
                    }


                    switch (alt63) {
                	case 1 :
                	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2146:2: e= expression
                	    {

                	    		if(retval.list.size() >= 1){
                	    			inCallStep = false;
                	    		}
                	    		if(retval.list.size() == 0){
                	    			retval.firstParam = builder.addParameterLabel();
                	    		}
                	    	

                	    pushFollow(FOLLOW_expression_in_function_expression_list2460);
                	    e=expression();

                	    state._fsp--;



                	    		retval.list.add(e);
                	    	

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
        	// do for sure before leaving
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2161:1: formal_parameter returns [TypeDescriptor type, String name] : ^( FPARAM ad= assignment_declaration ID ) ;
    public final QuorumTreeWalker.formal_parameter_return formal_parameter() throws RecognitionException {
        QuorumTreeWalker.formal_parameter_return retval = new QuorumTreeWalker.formal_parameter_return();
        retval.start = input.LT(1);


        CommonTree ID49=null;
        QuorumTreeWalker.assignment_declaration_return ad =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2162:2: ( ^( FPARAM ad= assignment_declaration ID ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2162:6: ^( FPARAM ad= assignment_declaration ID )
            {
            match(input,FPARAM,FOLLOW_FPARAM_in_formal_parameter2489); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_assignment_declaration_in_formal_parameter2493);
            ad=assignment_declaration();

            state._fsp--;


            ID49=(CommonTree)match(input,ID,FOLLOW_ID_in_formal_parameter2495); 

            match(input, Token.UP, null); 


            	
            		retval.type = (ad!=null?ad.myType:null);
            		retval.name = (ID49!=null?ID49.getText():null);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "formal_parameter"

    // Delegated rules


    protected DFA4 dfa4 = new DFA4(this);
    protected DFA51 dfa51 = new DFA51(this);
    static final String DFA4_eotS =
        "\24\uffff";
    static final String DFA4_eofS =
        "\24\uffff";
    static final String DFA4_minS =
        "\1\10\2\13\1\uffff\2\2\2\34\2\3\1\34\1\10\1\34\1\10\1\3\2\uffff"+
        "\1\3\2\uffff";
    static final String DFA4_maxS =
        "\1\101\2\13\1\uffff\2\2\2\34\2\51\1\34\1\101\1\34\1\101\1\51\2\uffff"+
        "\1\51\2\uffff";
    static final String DFA4_acceptS =
        "\3\uffff\1\5\13\uffff\1\1\1\3\1\uffff\1\2\1\4";
    static final String DFA4_specialS =
        "\24\uffff}>";
    static final String[] DFA4_transitionS = {
            "\4\3\15\uffff\1\1\1\2\2\3\3\uffff\3\3\3\uffff\3\3\2\uffff\4"+
            "\3\4\uffff\3\3\3\uffff\4\3\1\uffff\1\3\2\uffff\1\3",
            "\1\4",
            "\1\5",
            "",
            "\1\6",
            "\1\7",
            "\1\10",
            "\1\11",
            "\1\13\45\uffff\1\12",
            "\1\15\45\uffff\1\14",
            "\1\16",
            "\4\20\16\uffff\1\17\2\20\3\uffff\3\20\3\uffff\3\20\2\uffff"+
            "\4\20\4\uffff\3\20\3\uffff\4\20\1\uffff\1\20\2\uffff\1\20",
            "\1\21",
            "\4\23\15\uffff\1\22\1\2\2\23\3\uffff\3\23\3\uffff\3\23\2\uffff"+
            "\4\23\4\uffff\3\23\3\uffff\4\23\1\uffff\1\23\2\uffff\1\23",
            "\1\13\45\uffff\1\12",
            "",
            "",
            "\1\15\45\uffff\1\14",
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
            return "63:9: ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ |)";
        }
    }
    static final String DFA51_eotS =
        "\12\uffff";
    static final String DFA51_eofS =
        "\12\uffff";
    static final String DFA51_minS =
        "\1\13\1\uffff\1\2\1\uffff\1\34\1\3\1\34\1\30\1\3\1\uffff";
    static final String DFA51_maxS =
        "\1\74\1\uffff\1\2\1\uffff\1\34\1\51\1\34\1\52\1\51\1\uffff";
    static final String DFA51_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\5\uffff\1\2";
    static final String DFA51_specialS =
        "\12\uffff}>";
    static final String[] DFA51_transitionS = {
            "\1\2\20\uffff\1\1\3\uffff\2\3\11\uffff\2\1\14\uffff\4\3",
            "",
            "\1\4",
            "",
            "\1\5",
            "\1\7\45\uffff\1\6",
            "\1\10",
            "\1\3\3\uffff\1\3\15\uffff\1\11",
            "\1\7\45\uffff\1\6",
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
            return "1051:1: assignment_statement : ( (sel= selector COLON )? ID rhs= assign_right_hand_side |obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side | (modifier= access_modifier )? type= assignment_declaration name= ID (rhs= assign_right_hand_side )? );";
        }
    }
 

    public static final BitSet FOLLOW_package_rule_in_start51 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_reference_in_start53 = new BitSet(new long[]{0x5E3879C71C000F00L,0x0000000000000002L});
    public static final BitSet FOLLOW_reference_in_start59 = new BitSet(new long[]{0x0000000006000000L});
    public static final BitSet FOLLOW_package_rule_in_start62 = new BitSet(new long[]{0x5E3879C718000F00L,0x0000000000000002L});
    public static final BitSet FOLLOW_package_rule_in_start67 = new BitSet(new long[]{0x5E3879C718000F00L,0x0000000000000002L});
    public static final BitSet FOLLOW_reference_in_start72 = new BitSet(new long[]{0x5E3879C71C000F00L,0x0000000000000002L});
    public static final BitSet FOLLOW_class_declaration_in_start81 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_start84 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PACKAGE_NAME_in_package_rule96 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_package_rule100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_USE_in_reference113 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_reference115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_in_class_declaration131 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_class_declaration133 = new BitSet(new long[]{0x1E0019C771000800L});
    public static final BitSet FOLLOW_generic_declaration_in_class_declaration139 = new BitSet(new long[]{0x1E0019C770000800L});
    public static final BitSet FOLLOW_inherit_stmnts_in_class_declaration146 = new BitSet(new long[]{0x1E0019C730000800L});
    public static final BitSet FOLLOW_class_stmnts_in_class_declaration153 = new BitSet(new long[]{0x1E0019C730000800L});
    public static final BitSet FOLLOW_END_in_class_declaration156 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_no_class_stmnts_in_class_declaration169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_no_class_stmnts189 = new BitSet(new long[]{0x5E38780310000F02L,0x0000000000000002L});
    public static final BitSet FOLLOW_access_modifier_in_no_class_stmnts203 = new BitSet(new long[]{0x000001C400000000L});
    public static final BitSet FOLLOW_method_declaration_in_no_class_stmnts211 = new BitSet(new long[]{0x000001C700000002L});
    public static final BitSet FOLLOW_INHERITS_in_inherit_stmnts225 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_qualified_name_in_inherit_stmnts233 = new BitSet(new long[]{0x0000000001000808L});
    public static final BitSet FOLLOW_generic_statement_in_inherit_stmnts239 = new BitSet(new long[]{0x0000000000000808L});
    public static final BitSet FOLLOW_PUBLIC_in_access_modifier257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIVATE_in_access_modifier265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_statement_in_class_stmnts279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_access_modifier_in_class_stmnts288 = new BitSet(new long[]{0x000001C400000000L});
    public static final BitSet FOLLOW_method_declaration_in_class_stmnts296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_method_declaration314 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_method_declaration320 = new BitSet(new long[]{0x000000200000A000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration325 = new BitSet(new long[]{0x000000200000A000L});
    public static final BitSet FOLLOW_RETURNS_in_method_declaration338 = new BitSet(new long[]{0x1E00000000000800L});
    public static final BitSet FOLLOW_assignment_declaration_in_method_declaration342 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_block_in_method_declaration348 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_END_in_method_declaration351 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BLUEPRINT_in_method_declaration366 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ACTION_in_method_declaration368 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_method_declaration375 = new BitSet(new long[]{0x0000002000008008L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration380 = new BitSet(new long[]{0x0000002000008008L});
    public static final BitSet FOLLOW_RETURNS_in_method_declaration389 = new BitSet(new long[]{0x1E00000000000800L});
    public static final BitSet FOLLOW_assignment_declaration_in_method_declaration391 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NATIVE_in_method_declaration400 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ACTION_in_method_declaration402 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_method_declaration409 = new BitSet(new long[]{0x0000002000008008L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration414 = new BitSet(new long[]{0x0000002000008008L});
    public static final BitSet FOLLOW_RETURNS_in_method_declaration423 = new BitSet(new long[]{0x1E00000000000800L});
    public static final BitSet FOLLOW_assignment_declaration_in_method_declaration425 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ON_CREATE_in_method_declaration434 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_method_declaration441 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_END_in_method_declaration444 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_QUALIFIED_NAME_in_qualified_name467 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_qualified_name472 = new BitSet(new long[]{0x0000020000000008L});
    public static final BitSet FOLLOW_PERIOD_in_qualified_name475 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_qualified_name479 = new BitSet(new long[]{0x0000020000000008L});
    public static final BitSet FOLLOW_STATEMENT_LIST_in_block498 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_block505 = new BitSet(new long[]{0x5E38780310000F08L,0x0000000000000002L});
    public static final BitSet FOLLOW_solo_method_call_in_statement522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_statement_in_statement527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_statement_in_statement532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_loop_statement_in_statement537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_statement_in_statement542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_print_statement_in_statement547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_speak_statement_in_statement552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_check_statement_in_statement557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_alert_statement_in_statement562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SOLO_FUNCTION_CALL_in_solo_method_call582 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_qualified_name_in_solo_method_call591 = new BitSet(new long[]{0x0000040800000000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call594 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_solo_method_call596 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call600 = new BitSet(new long[]{0x2184101040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_solo_method_call610 = new BitSet(new long[]{0x0000001080000000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call620 = new BitSet(new long[]{0x2184100040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_solo_method_call626 = new BitSet(new long[]{0x0000001080000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call640 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SOLO_FUNCTION_CALL_PARENT_in_solo_method_call652 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_PARENT_in_solo_method_call659 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call661 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_solo_method_call663 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call665 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_solo_method_call667 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call669 = new BitSet(new long[]{0x2184101040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_solo_method_call679 = new BitSet(new long[]{0x0000001080000000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call686 = new BitSet(new long[]{0x2184100040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_solo_method_call692 = new BitSet(new long[]{0x0000001080000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call702 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SOLO_FUNCTION_CALL_THIS_in_solo_method_call713 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ME_in_solo_method_call720 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call722 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_solo_method_call724 = new BitSet(new long[]{0x0000040800000000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call727 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_solo_method_call729 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call733 = new BitSet(new long[]{0x2184101040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_solo_method_call743 = new BitSet(new long[]{0x0000001080000000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call750 = new BitSet(new long[]{0x2184100040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_solo_method_call756 = new BitSet(new long[]{0x0000001080000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call766 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ALERT_in_alert_statement786 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_alert_statement794 = new BitSet(new long[]{0x2184100040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_alert_statement798 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_alert_statement806 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CHECK_in_check_statement838 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_block_in_check_statement844 = new BitSet(new long[]{0x0001800000000000L});
    public static final BitSet FOLLOW_DETECT_in_check_statement866 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_detect_parameter_in_check_statement893 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_block_in_check_statement917 = new BitSet(new long[]{0x0001800020000000L});
    public static final BitSet FOLLOW_ALWAYS_in_check_statement950 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_block_in_check_statement966 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_ALWAYS_in_check_statement998 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_block_in_check_statement1021 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_END_in_check_statement1030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_detect_parameter1059 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_OF_TYPE_in_detect_parameter1064 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_detect_parameter1073 = new BitSet(new long[]{0x0004000000000008L});
    public static final BitSet FOLLOW_OR_in_detect_parameter1080 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_detect_parameter1084 = new BitSet(new long[]{0x0004000000000008L});
    public static final BitSet FOLLOW_PRINT_in_print_statement1111 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_root_expression_in_print_statement1113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAY_in_speak_statement1129 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_root_expression_in_speak_statement1131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_return_statement1145 = new BitSet(new long[]{0x0040000000100000L});
    public static final BitSet FOLLOW_root_expression_in_return_statement1148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOW_in_return_statement1156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GENERIC_in_generic_declaration1174 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_LESS_in_generic_declaration1176 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_generic_declaration1180 = new BitSet(new long[]{0x0100000080000000L});
    public static final BitSet FOLLOW_COMMA_in_generic_declaration1183 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_generic_declaration1187 = new BitSet(new long[]{0x0100000080000000L});
    public static final BitSet FOLLOW_GREATER_in_generic_declaration1191 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GENERIC_in_generic_statement1209 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_LESS_in_generic_statement1211 = new BitSet(new long[]{0x1E00000000000800L});
    public static final BitSet FOLLOW_assignment_declaration_in_generic_statement1220 = new BitSet(new long[]{0x0100000080000000L});
    public static final BitSet FOLLOW_COMMA_in_generic_statement1228 = new BitSet(new long[]{0x1E00000000000800L});
    public static final BitSet FOLLOW_assignment_declaration_in_generic_statement1232 = new BitSet(new long[]{0x0100000080000000L});
    public static final BitSet FOLLOW_GREATER_in_generic_statement1248 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_qualified_name_in_class_type1273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_assignment_declaration1295 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_generic_statement_in_assignment_declaration1299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_KEYWORD_in_assignment_declaration1308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_KEYWORD_in_assignment_declaration1316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TEXT_in_assignment_declaration1324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_KEYWORD_in_assignment_declaration1332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_assignment_statement1348 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1350 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_assignment_statement1354 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_assign_right_hand_side_in_assignment_statement1358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_assignment_statement1368 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1371 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_PARENT_in_assignment_statement1373 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1375 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_assignment_statement1379 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1383 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_assignment_statement1385 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_assign_right_hand_side_in_assignment_statement1389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_access_modifier_in_assignment_statement1401 = new BitSet(new long[]{0x1E00000000000800L});
    public static final BitSet FOLLOW_assignment_declaration_in_assignment_statement1408 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_assignment_statement1414 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_assign_right_hand_side_in_assignment_statement1418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALITY_in_assign_right_hand_side1439 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_root_expression_in_assign_right_hand_side1441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_if_statement1467 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_root_expression_in_if_statement1480 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_block_in_if_statement1492 = new BitSet(new long[]{0x8000000020000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ELSE_IF_in_if_statement1509 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_root_expression_in_if_statement1522 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_block_in_if_statement1532 = new BitSet(new long[]{0x8000000020000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_ELSE_in_if_statement1566 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_block_in_if_statement1576 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_END_in_if_statement1585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REPEAT_in_loop_statement1608 = new BitSet(new long[]{0x0000000000100000L,0x0000000000000018L});
    public static final BitSet FOLLOW_root_expression_in_loop_statement1681 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_TIMES_in_loop_statement1683 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_WHILE_in_loop_statement1694 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_UNTIL_in_loop_statement1700 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_root_expression_in_loop_statement1715 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_block_in_loop_statement1726 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_END_in_loop_statement1734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENT_in_selector1773 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_qualified_name_in_selector1775 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ME_in_selector1784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ROOT_EXPRESSION_in_root_expression1810 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_root_expression1812 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNARY_NOT_in_expression1834 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NOT_in_expression1836 = new BitSet(new long[]{0x2184100040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_expression1842 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EQUALITY_in_expression1854 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1860 = new BitSet(new long[]{0x2184100040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_expression1866 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NOTEQUALS_in_expression1876 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1882 = new BitSet(new long[]{0x2184100040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_expression1888 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GREATER_in_expression1898 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1904 = new BitSet(new long[]{0x2184100040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_expression1910 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GREATER_EQUAL_in_expression1920 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1926 = new BitSet(new long[]{0x2184100040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_expression1932 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INHERITS_in_expression1942 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1946 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_class_type_in_expression1950 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LESS_in_expression1960 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1966 = new BitSet(new long[]{0x2184100040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_expression1972 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LESS_EQUAL_in_expression1982 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1988 = new BitSet(new long[]{0x2184100040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_expression1994 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OR_in_expression2004 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2010 = new BitSet(new long[]{0x2184100040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_expression2016 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_AND_in_expression2026 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2032 = new BitSet(new long[]{0x2184100040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_expression2038 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PLUS_in_expression2048 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2054 = new BitSet(new long[]{0x2184100040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_expression2060 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MINUS_in_expression2070 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2076 = new BitSet(new long[]{0x2184100040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_expression2082 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MULTIPLY_in_expression2092 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2098 = new BitSet(new long[]{0x2184100040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_expression2104 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DIVIDE_in_expression2114 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2120 = new BitSet(new long[]{0x2184100040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_expression2126 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MODULO_in_expression2136 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2142 = new BitSet(new long[]{0x2184100040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_expression2148 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNCTION_CALL_in_expression2158 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_qualified_name_in_expression2160 = new BitSet(new long[]{0x0000040800000000L});
    public static final BitSet FOLLOW_COLON_in_expression2162 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_expression2164 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_expression2168 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_function_expression_list_in_expression2180 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_expression2182 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_QUALIFIED_SOLO_EXPRESSION_in_expression2193 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_qualified_name_in_expression2197 = new BitSet(new long[]{0x0000040000000008L});
    public static final BitSet FOLLOW_COLON_in_expression2200 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_expression2202 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_QUALIFIED_SOLO_PARENT_EXPRESSON_in_expression2214 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_qualified_name_in_expression2218 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_COLON_in_expression2220 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_PARENT_in_expression2222 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_COLON_in_expression2224 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_expression2228 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_COLON_in_expression2230 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_expression2232 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_QUALIFIED_SOLO_EXPRESSION_SELECTOR_in_expression2242 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_selector_in_expression2244 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_COLON_in_expression2246 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_expression2248 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNCTION_CALL_PARENT_in_expression2260 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_PARENT_in_expression2262 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_COLON_in_expression2264 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_expression2268 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_COLON_in_expression2270 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_expression2272 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_expression2274 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_function_expression_list_in_expression2285 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_expression2287 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNCTION_CALL_THIS_in_expression2297 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ME_in_expression2299 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_COLON_in_expression2301 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_qualified_name_in_expression2303 = new BitSet(new long[]{0x0000040800000000L});
    public static final BitSet FOLLOW_COLON_in_expression2306 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_expression2308 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_expression2312 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_function_expression_list_in_expression2323 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_expression2325 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BOOLEAN_in_expression2334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_expression2343 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_DECIMAL_in_expression2347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_expression2356 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_INT_in_expression2360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_expression2368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_expression2376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ME_in_expression2384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INPUT_in_expression2392 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_expression2394 = new BitSet(new long[]{0x2184100040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_expression2398 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_expression2400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CAST_in_expression2408 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_expression2410 = new BitSet(new long[]{0x1E00000000000800L});
    public static final BitSet FOLLOW_assignment_declaration_in_expression2414 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_COMMA_in_expression2416 = new BitSet(new long[]{0x2184100040E10070L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_expression_in_expression2420 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_expression2424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_EXPRESSION_LIST_in_function_expression_list2448 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_function_expression_list2460 = new BitSet(new long[]{0x2184100040E10078L,0x00000000003FBFE0L});
    public static final BitSet FOLLOW_FPARAM_in_formal_parameter2489 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_assignment_declaration_in_formal_parameter2493 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ID_in_formal_parameter2495 = new BitSet(new long[]{0x0000000000000008L});

}