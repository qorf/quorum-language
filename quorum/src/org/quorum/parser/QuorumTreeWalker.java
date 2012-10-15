// $ANTLR 3.4 /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g 2012-10-15 15:26:04


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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ACTION", "ALERT", "ALWAYS", "AND", "BLUEPRINT", "BOOLEAN", "BOOLEAN_KEYWORD", "CAST", "CHECK", "CLASS", "COLON", "COMMA", "COMMENTS", "CONSTANT", "CONSTRUCTOR", "DECIMAL", "DETECT", "DIVIDE", "DOUBLE_QUOTE", "ELSE", "ELSE_IF", "ELSE_IF_STATEMENT", "END", "EQUALITY", "EXPRESSION_STATEMENT", "FINAL_ELSE", "FPARAM", "FUNCTION_CALL", "FUNCTION_CALL_PARENT", "FUNCTION_CALL_THIS", "FUNCTION_EXPRESSION_LIST", "GENERIC", "GREATER", "GREATER_EQUAL", "ID", "IF", "INHERITS", "INPUT", "INT", "INTEGER_KEYWORD", "LEFT_PAREN", "LEFT_SQR_BRACE", "LESS", "LESS_EQUAL", "ME", "MINUS", "MODULO", "MULTIPLY", "NATIVE", "NEWLINE", "NOT", "NOTEQUALS", "NOW", "NULL", "NUMBER_KEYWORD", "OF_TYPE", "ON_CREATE", "ON_DESTROY", "OR", "OVER", "PACKAGE_NAME", "PARENT", "PAREN_WRAPPED_EXPRESSION", "PERIOD", "PLUS", "PRINT", "PRIVATE", "PUBLIC", "QUALIFIED_NAME", "QUALIFIED_SOLO_EXPRESSION", "QUALIFIED_SOLO_EXPRESSION_SELECTOR", "QUALIFIED_SOLO_PARENT_EXPRESSON", "QUOTE", "REPEAT", "RETURN", "RETURNS", "RIGHT_PAREN", "RIGHT_SQR_BRACE", "ROOT_EXPRESSION", "SAY", "SOLO_FUNCTION_CALL", "SOLO_FUNCTION_CALL_PARENT", "SOLO_FUNCTION_CALL_THIS", "STATEMENT_LIST", "STRING", "TEXT", "TIMES", "UNARY_NOT", "UNTIL", "USE", "WHILE", "WS"
    };

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
    public static final int CONSTANT=17;
    public static final int CONSTRUCTOR=18;
    public static final int DECIMAL=19;
    public static final int DETECT=20;
    public static final int DIVIDE=21;
    public static final int DOUBLE_QUOTE=22;
    public static final int ELSE=23;
    public static final int ELSE_IF=24;
    public static final int ELSE_IF_STATEMENT=25;
    public static final int END=26;
    public static final int EQUALITY=27;
    public static final int EXPRESSION_STATEMENT=28;
    public static final int FINAL_ELSE=29;
    public static final int FPARAM=30;
    public static final int FUNCTION_CALL=31;
    public static final int FUNCTION_CALL_PARENT=32;
    public static final int FUNCTION_CALL_THIS=33;
    public static final int FUNCTION_EXPRESSION_LIST=34;
    public static final int GENERIC=35;
    public static final int GREATER=36;
    public static final int GREATER_EQUAL=37;
    public static final int ID=38;
    public static final int IF=39;
    public static final int INHERITS=40;
    public static final int INPUT=41;
    public static final int INT=42;
    public static final int INTEGER_KEYWORD=43;
    public static final int LEFT_PAREN=44;
    public static final int LEFT_SQR_BRACE=45;
    public static final int LESS=46;
    public static final int LESS_EQUAL=47;
    public static final int ME=48;
    public static final int MINUS=49;
    public static final int MODULO=50;
    public static final int MULTIPLY=51;
    public static final int NATIVE=52;
    public static final int NEWLINE=53;
    public static final int NOT=54;
    public static final int NOTEQUALS=55;
    public static final int NOW=56;
    public static final int NULL=57;
    public static final int NUMBER_KEYWORD=58;
    public static final int OF_TYPE=59;
    public static final int ON_CREATE=60;
    public static final int ON_DESTROY=61;
    public static final int OR=62;
    public static final int OVER=63;
    public static final int PACKAGE_NAME=64;
    public static final int PARENT=65;
    public static final int PAREN_WRAPPED_EXPRESSION=66;
    public static final int PERIOD=67;
    public static final int PLUS=68;
    public static final int PRINT=69;
    public static final int PRIVATE=70;
    public static final int PUBLIC=71;
    public static final int QUALIFIED_NAME=72;
    public static final int QUALIFIED_SOLO_EXPRESSION=73;
    public static final int QUALIFIED_SOLO_EXPRESSION_SELECTOR=74;
    public static final int QUALIFIED_SOLO_PARENT_EXPRESSON=75;
    public static final int QUOTE=76;
    public static final int REPEAT=77;
    public static final int RETURN=78;
    public static final int RETURNS=79;
    public static final int RIGHT_PAREN=80;
    public static final int RIGHT_SQR_BRACE=81;
    public static final int ROOT_EXPRESSION=82;
    public static final int SAY=83;
    public static final int SOLO_FUNCTION_CALL=84;
    public static final int SOLO_FUNCTION_CALL_PARENT=85;
    public static final int SOLO_FUNCTION_CALL_THIS=86;
    public static final int STATEMENT_LIST=87;
    public static final int STRING=88;
    public static final int TEXT=89;
    public static final int TIMES=90;
    public static final int UNARY_NOT=91;
    public static final int UNTIL=92;
    public static final int USE=93;
    public static final int WHILE=94;
    public static final int WS=95;

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
    	static int expressionDepth = 0;
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:64:1: start : ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ |) class_declaration EOF ;
    public final QuorumTreeWalker.start_return start() throws RecognitionException {
        QuorumTreeWalker.start_return retval = new QuorumTreeWalker.start_return();
        retval.start = input.LT(1);


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:64:7: ( ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ |) class_declaration EOF )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:64:9: ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ |) class_declaration EOF
            {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:64:9: ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ |)
            int alt4=5;
            alt4 = dfa4.predict(input);
            switch (alt4) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:64:10: package_rule ( reference )+
                    {
                    pushFollow(FOLLOW_package_rule_in_start51);
                    package_rule();

                    state._fsp--;


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:64:23: ( reference )+
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
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:64:23: reference
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:65:4: ( reference )+ package_rule
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:65:4: ( reference )+
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
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:65:4: reference
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:66:4: package_rule
                    {
                    pushFollow(FOLLOW_package_rule_in_start67);
                    package_rule();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:67:4: ( reference )+
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:67:4: ( reference )+
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
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:67:4: reference
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:68:4: 
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:72:1: package_rule : PACKAGE_NAME qn= qualified_name ;
    public final QuorumTreeWalker.package_rule_return package_rule() throws RecognitionException {
        QuorumTreeWalker.package_rule_return retval = new QuorumTreeWalker.package_rule_return();
        retval.start = input.LT(1);


        QuorumTreeWalker.qualified_name_return qn =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:72:15: ( PACKAGE_NAME qn= qualified_name )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:72:17: PACKAGE_NAME qn= qualified_name
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:78:1: reference : USE qualified_name ;
    public final QuorumTreeWalker.reference_return reference() throws RecognitionException {
        QuorumTreeWalker.reference_return retval = new QuorumTreeWalker.reference_return();
        retval.start = input.LT(1);


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:78:11: ( USE qualified_name )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:78:13: USE qualified_name
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:84:1: class_declaration : ( ^( CLASS ID ( generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END ) | no_class_stmnts );
    public final QuorumTreeWalker.class_declaration_return class_declaration() throws RecognitionException {
        QuorumTreeWalker.class_declaration_return retval = new QuorumTreeWalker.class_declaration_return();
        retval.start = input.LT(1);


        CommonTree ID1=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:84:20: ( ^( CLASS ID ( generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END ) | no_class_stmnts )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==CLASS) ) {
                alt8=1;
            }
            else if ( ((LA8_0 >= ACTION && LA8_0 <= ALERT)||LA8_0==BLUEPRINT||LA8_0==BOOLEAN_KEYWORD||LA8_0==CHECK||LA8_0==CONSTANT||(LA8_0 >= ID && LA8_0 <= IF)||LA8_0==INTEGER_KEYWORD||LA8_0==ME||LA8_0==NATIVE||LA8_0==NUMBER_KEYWORD||LA8_0==ON_CREATE||LA8_0==PARENT||(LA8_0 >= PRINT && LA8_0 <= QUALIFIED_NAME)||(LA8_0 >= REPEAT && LA8_0 <= RETURN)||(LA8_0 >= SAY && LA8_0 <= SOLO_FUNCTION_CALL_THIS)||LA8_0==TEXT) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }
            switch (alt8) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:85:2: ^( CLASS ID ( generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END )
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
                    	

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:100:2: ( generic_declaration )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==GENERIC) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:100:2: generic_declaration
                            {
                            pushFollow(FOLLOW_generic_declaration_in_class_declaration139);
                            generic_declaration();

                            state._fsp--;


                            }
                            break;

                    }




                    	

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:104:2: ( inherit_stmnts )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==INHERITS) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:104:2: inherit_stmnts
                            {
                            pushFollow(FOLLOW_inherit_stmnts_in_class_declaration146);
                            inherit_stmnts();

                            state._fsp--;


                            }
                            break;

                    }



                    	

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:107:2: ( class_stmnts )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==ACTION||LA7_0==BLUEPRINT||LA7_0==BOOLEAN_KEYWORD||LA7_0==CONSTANT||LA7_0==ID||LA7_0==INTEGER_KEYWORD||LA7_0==ME||LA7_0==NATIVE||LA7_0==NUMBER_KEYWORD||LA7_0==ON_CREATE||LA7_0==PARENT||(LA7_0 >= PRIVATE && LA7_0 <= QUALIFIED_NAME)||LA7_0==TEXT) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:107:2: class_stmnts
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:113:2: no_class_stmnts
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:126:1: no_class_stmnts : ( ( statement )+ | ( (modEnum= access_modifier )? method_declaration )+ );
    public final QuorumTreeWalker.no_class_stmnts_return no_class_stmnts() throws RecognitionException {
        QuorumTreeWalker.no_class_stmnts_return retval = new QuorumTreeWalker.no_class_stmnts_return();
        retval.start = input.LT(1);


        QuorumTreeWalker.access_modifier_return modEnum =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:127:2: ( ( statement )+ | ( (modEnum= access_modifier )? method_declaration )+ )
            int alt12=2;
            switch ( input.LA(1) ) {
            case ALERT:
            case BOOLEAN_KEYWORD:
            case CHECK:
            case CONSTANT:
            case ID:
            case IF:
            case INTEGER_KEYWORD:
            case ME:
            case NUMBER_KEYWORD:
            case PARENT:
            case PRINT:
            case QUALIFIED_NAME:
            case REPEAT:
            case RETURN:
            case SAY:
            case SOLO_FUNCTION_CALL:
            case SOLO_FUNCTION_CALL_PARENT:
            case SOLO_FUNCTION_CALL_THIS:
            case TEXT:
                {
                alt12=1;
                }
                break;
            case PUBLIC:
                {
                int LA12_2 = input.LA(2);

                if ( (LA12_2==BOOLEAN_KEYWORD||LA12_2==CONSTANT||LA12_2==INTEGER_KEYWORD||LA12_2==NUMBER_KEYWORD||LA12_2==QUALIFIED_NAME||LA12_2==TEXT) ) {
                    alt12=1;
                }
                else if ( (LA12_2==ACTION||LA12_2==BLUEPRINT||LA12_2==NATIVE||LA12_2==ON_CREATE) ) {
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

                if ( (LA12_3==BOOLEAN_KEYWORD||LA12_3==CONSTANT||LA12_3==INTEGER_KEYWORD||LA12_3==NUMBER_KEYWORD||LA12_3==QUALIFIED_NAME||LA12_3==TEXT) ) {
                    alt12=1;
                }
                else if ( (LA12_3==ACTION||LA12_3==BLUEPRINT||LA12_3==NATIVE||LA12_3==ON_CREATE) ) {
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:128:2: ( statement )+
                    {
                    //enter the fake method main
                    		MethodDescriptor md = symbol.enterMethod("main");
                    		builder.begin(md);
                    	

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:132:3: ( statement )+
                    int cnt9=0;
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==ALERT||LA9_0==BOOLEAN_KEYWORD||LA9_0==CHECK||LA9_0==CONSTANT||(LA9_0 >= ID && LA9_0 <= IF)||LA9_0==INTEGER_KEYWORD||LA9_0==ME||LA9_0==NUMBER_KEYWORD||LA9_0==PARENT||(LA9_0 >= PRINT && LA9_0 <= QUALIFIED_NAME)||(LA9_0 >= REPEAT && LA9_0 <= RETURN)||(LA9_0 >= SAY && LA9_0 <= SOLO_FUNCTION_CALL_THIS)||LA9_0==TEXT) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:132:3: statement
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:149:4: ( (modEnum= access_modifier )? method_declaration )+
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:149:4: ( (modEnum= access_modifier )? method_declaration )+
                    int cnt11=0;
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==ACTION||LA11_0==BLUEPRINT||LA11_0==NATIVE||LA11_0==ON_CREATE||(LA11_0 >= PRIVATE && LA11_0 <= PUBLIC)) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:149:5: (modEnum= access_modifier )? method_declaration
                    	    {
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:149:13: (modEnum= access_modifier )?
                    	    int alt10=2;
                    	    int LA10_0 = input.LA(1);

                    	    if ( ((LA10_0 >= PRIVATE && LA10_0 <= PUBLIC)) ) {
                    	        alt10=1;
                    	    }
                    	    switch (alt10) {
                    	        case 1 :
                    	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:149:13: modEnum= access_modifier
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:158:1: inherit_stmnts : ^( INHERITS (qn= qualified_name (gd= generic_statement )? )+ ) ;
    public final QuorumTreeWalker.inherit_stmnts_return inherit_stmnts() throws RecognitionException {
        QuorumTreeWalker.inherit_stmnts_return retval = new QuorumTreeWalker.inherit_stmnts_return();
        retval.start = input.LT(1);


        QuorumTreeWalker.qualified_name_return qn =null;

        QuorumTreeWalker.generic_statement_return gd =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:159:2: ( ^( INHERITS (qn= qualified_name (gd= generic_statement )? )+ ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:159:4: ^( INHERITS (qn= qualified_name (gd= generic_statement )? )+ )
            {
            match(input,INHERITS,FOLLOW_INHERITS_in_inherit_stmnts225); 

            match(input, Token.DOWN, null); 
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:159:15: (qn= qualified_name (gd= generic_statement )? )+
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
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:159:17: qn= qualified_name (gd= generic_statement )?
            	    {
            	    pushFollow(FOLLOW_qualified_name_in_inherit_stmnts233);
            	    qn=qualified_name();

            	    state._fsp--;


            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:159:40: (gd= generic_statement )?
            	    int alt13=2;
            	    int LA13_0 = input.LA(1);

            	    if ( (LA13_0==GENERIC) ) {
            	        alt13=1;
            	    }
            	    switch (alt13) {
            	        case 1 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:159:40: gd= generic_statement
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:161:1: access_modifier returns [AccessModifierEnum amEnum] : ( PUBLIC | PRIVATE );
    public final QuorumTreeWalker.access_modifier_return access_modifier() throws RecognitionException {
        QuorumTreeWalker.access_modifier_return retval = new QuorumTreeWalker.access_modifier_return();
        retval.start = input.LT(1);


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:162:2: ( PUBLIC | PRIVATE )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:162:4: PUBLIC
                    {
                    match(input,PUBLIC,FOLLOW_PUBLIC_in_access_modifier257); 


                    		retval.amEnum = retval.amEnum.PUBLIC;
                    	

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:166:4: PRIVATE
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:171:1: class_stmnts : ( assignment_statement | (modEnum= access_modifier )? method_declaration );
    public final QuorumTreeWalker.class_stmnts_return class_stmnts() throws RecognitionException {
        QuorumTreeWalker.class_stmnts_return retval = new QuorumTreeWalker.class_stmnts_return();
        retval.start = input.LT(1);


        QuorumTreeWalker.access_modifier_return modEnum =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:172:2: ( assignment_statement | (modEnum= access_modifier )? method_declaration )
            int alt17=2;
            switch ( input.LA(1) ) {
            case BOOLEAN_KEYWORD:
            case CONSTANT:
            case ID:
            case INTEGER_KEYWORD:
            case ME:
            case NUMBER_KEYWORD:
            case PARENT:
            case QUALIFIED_NAME:
            case TEXT:
                {
                alt17=1;
                }
                break;
            case PUBLIC:
                {
                int LA17_2 = input.LA(2);

                if ( (LA17_2==BOOLEAN_KEYWORD||LA17_2==CONSTANT||LA17_2==INTEGER_KEYWORD||LA17_2==NUMBER_KEYWORD||LA17_2==QUALIFIED_NAME||LA17_2==TEXT) ) {
                    alt17=1;
                }
                else if ( (LA17_2==ACTION||LA17_2==BLUEPRINT||LA17_2==NATIVE||LA17_2==ON_CREATE) ) {
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

                if ( (LA17_3==BOOLEAN_KEYWORD||LA17_3==CONSTANT||LA17_3==INTEGER_KEYWORD||LA17_3==NUMBER_KEYWORD||LA17_3==QUALIFIED_NAME||LA17_3==TEXT) ) {
                    alt17=1;
                }
                else if ( (LA17_3==ACTION||LA17_3==BLUEPRINT||LA17_3==NATIVE||LA17_3==ON_CREATE) ) {
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:172:4: assignment_statement
                    {
                    pushFollow(FOLLOW_assignment_statement_in_class_stmnts279);
                    assignment_statement();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:173:4: (modEnum= access_modifier )? method_declaration
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:173:12: (modEnum= access_modifier )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( ((LA16_0 >= PRIVATE && LA16_0 <= PUBLIC)) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:173:12: modEnum= access_modifier
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:184:1: method_declaration : ( ^( ACTION ID (fp= formal_parameter )* ( RETURNS ad= assignment_declaration )? block[false] END ) | ^( BLUEPRINT ACTION ID (fp= formal_parameter )* ( RETURNS assignment_declaration )? ) | ^( NATIVE ACTION ID (fp= formal_parameter )* ( RETURNS assignment_declaration )? ) | ^( ON_CREATE block[true] END ) );
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
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:188:2: ( ^( ACTION ID (fp= formal_parameter )* ( RETURNS ad= assignment_declaration )? block[false] END ) | ^( BLUEPRINT ACTION ID (fp= formal_parameter )* ( RETURNS assignment_declaration )? ) | ^( NATIVE ACTION ID (fp= formal_parameter )* ( RETURNS assignment_declaration )? ) | ^( ON_CREATE block[true] END ) )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:188:4: ^( ACTION ID (fp= formal_parameter )* ( RETURNS ad= assignment_declaration )? block[false] END )
                    {
                    match(input,ACTION,FOLLOW_ACTION_in_method_declaration314); 


                    		((method_declaration_scope)method_declaration_stack.peek()).types = new Vector<TypeDescriptor>();
                    	

                    match(input, Token.DOWN, null); 
                    ID2=(CommonTree)match(input,ID,FOLLOW_ID_in_method_declaration320); 

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:194:5: (fp= formal_parameter )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==FPARAM) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:194:6: fp= formal_parameter
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

                    	

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:204:3: ( RETURNS ad= assignment_declaration )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==RETURNS) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:204:4: RETURNS ad= assignment_declaration
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:234:6: ^( BLUEPRINT ACTION ID (fp= formal_parameter )* ( RETURNS assignment_declaration )? )
                    {
                    match(input,BLUEPRINT,FOLLOW_BLUEPRINT_in_method_declaration366); 

                    match(input, Token.DOWN, null); 
                    match(input,ACTION,FOLLOW_ACTION_in_method_declaration368); 


                    		((method_declaration_scope)method_declaration_stack.peek()).types = new Vector<TypeDescriptor>();
                    	

                    match(input,ID,FOLLOW_ID_in_method_declaration375); 

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:238:6: (fp= formal_parameter )*
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==FPARAM) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:238:7: fp= formal_parameter
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


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:239:4: ( RETURNS assignment_declaration )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==RETURNS) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:239:5: RETURNS assignment_declaration
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:240:4: ^( NATIVE ACTION ID (fp= formal_parameter )* ( RETURNS assignment_declaration )? )
                    {
                    match(input,NATIVE,FOLLOW_NATIVE_in_method_declaration400); 

                    match(input, Token.DOWN, null); 
                    match(input,ACTION,FOLLOW_ACTION_in_method_declaration402); 


                    		((method_declaration_scope)method_declaration_stack.peek()).types = new Vector<TypeDescriptor>();
                    	

                    match(input,ID,FOLLOW_ID_in_method_declaration409); 

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:244:5: (fp= formal_parameter )*
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==FPARAM) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:244:6: fp= formal_parameter
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


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:245:4: ( RETURNS assignment_declaration )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==RETURNS) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:245:5: RETURNS assignment_declaration
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:246:4: ^( ON_CREATE block[true] END )
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:282:1: qualified_name returns [QualifiedNameDescriptor type] : ^( QUALIFIED_NAME ids+= ID ( PERIOD ids+= ID )* ) ;
    public final QuorumTreeWalker.qualified_name_return qualified_name() throws RecognitionException {
        QuorumTreeWalker.qualified_name_return retval = new QuorumTreeWalker.qualified_name_return();
        retval.start = input.LT(1);


        CommonTree PERIOD5=null;
        CommonTree ids=null;
        List list_ids=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:283:2: ( ^( QUALIFIED_NAME ids+= ID ( PERIOD ids+= ID )* ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:283:4: ^( QUALIFIED_NAME ids+= ID ( PERIOD ids+= ID )* )
            {
            match(input,QUALIFIED_NAME,FOLLOW_QUALIFIED_NAME_in_qualified_name467); 

            match(input, Token.DOWN, null); 
            ids=(CommonTree)match(input,ID,FOLLOW_ID_in_qualified_name472); 
            if (list_ids==null) list_ids=new ArrayList();
            list_ids.add(ids);


            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:283:30: ( PERIOD ids+= ID )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==PERIOD) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:283:31: PERIOD ids+= ID
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:311:1: block[boolean bool] : ^( STATEMENT_LIST ( statement )* ) ;
    public final QuorumTreeWalker.block_return block(boolean bool) throws RecognitionException {
        QuorumTreeWalker.block_return retval = new QuorumTreeWalker.block_return();
        retval.start = input.LT(1);


        CommonTree STATEMENT_LIST6=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:311:22: ( ^( STATEMENT_LIST ( statement )* ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:311:24: ^( STATEMENT_LIST ( statement )* )
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
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:322:3: ( statement )*
                loop26:
                do {
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==ALERT||LA26_0==BOOLEAN_KEYWORD||LA26_0==CHECK||LA26_0==CONSTANT||(LA26_0 >= ID && LA26_0 <= IF)||LA26_0==INTEGER_KEYWORD||LA26_0==ME||LA26_0==NUMBER_KEYWORD||LA26_0==PARENT||(LA26_0 >= PRINT && LA26_0 <= QUALIFIED_NAME)||(LA26_0 >= REPEAT && LA26_0 <= RETURN)||(LA26_0 >= SAY && LA26_0 <= SOLO_FUNCTION_CALL_THIS)||LA26_0==TEXT) ) {
                        alt26=1;
                    }


                    switch (alt26) {
                	case 1 :
                	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:322:3: statement
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:333:1: statement : ( solo_method_call | if_statement | assignment_statement | loop_statement | return_statement | print_statement | speak_statement | check_statement | alert_statement );
    public final QuorumTreeWalker.statement_return statement() throws RecognitionException {
        QuorumTreeWalker.statement_return retval = new QuorumTreeWalker.statement_return();
        retval.start = input.LT(1);


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:333:10: ( solo_method_call | if_statement | assignment_statement | loop_statement | return_statement | print_statement | speak_statement | check_statement | alert_statement )
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
            case BOOLEAN_KEYWORD:
            case CONSTANT:
            case ID:
            case INTEGER_KEYWORD:
            case ME:
            case NUMBER_KEYWORD:
            case PARENT:
            case PRIVATE:
            case PUBLIC:
            case QUALIFIED_NAME:
            case TEXT:
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:335:3: solo_method_call
                    {
                    pushFollow(FOLLOW_solo_method_call_in_statement522);
                    solo_method_call();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:336:4: if_statement
                    {
                    pushFollow(FOLLOW_if_statement_in_statement527);
                    if_statement();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:337:4: assignment_statement
                    {
                    pushFollow(FOLLOW_assignment_statement_in_statement532);
                    assignment_statement();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:338:4: loop_statement
                    {
                    pushFollow(FOLLOW_loop_statement_in_statement537);
                    loop_statement();

                    state._fsp--;


                    }
                    break;
                case 5 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:339:4: return_statement
                    {
                    pushFollow(FOLLOW_return_statement_in_statement542);
                    return_statement();

                    state._fsp--;


                    }
                    break;
                case 6 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:340:4: print_statement
                    {
                    pushFollow(FOLLOW_print_statement_in_statement547);
                    print_statement();

                    state._fsp--;


                    }
                    break;
                case 7 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:341:4: speak_statement
                    {
                    pushFollow(FOLLOW_speak_statement_in_statement552);
                    speak_statement();

                    state._fsp--;


                    }
                    break;
                case 8 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:342:4: check_statement
                    {
                    pushFollow(FOLLOW_check_statement_in_statement557);
                    check_statement();

                    state._fsp--;


                    }
                    break;
                case 9 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:343:4: alert_statement
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:346:1: solo_method_call : ( ^( SOLO_FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN ) | ^( SOLO_FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN ) | ^( SOLO_FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN ) );
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
        	int startLocation = 0;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:355:2: ( ^( SOLO_FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN ) | ^( SOLO_FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN ) | ^( SOLO_FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN ) )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:356:3: ^( SOLO_FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN )
                    {
                    match(input,SOLO_FUNCTION_CALL,FOLLOW_SOLO_FUNCTION_CALL_in_solo_method_call582); 


                    			inCallStep = true;
                    			builder.addStepLabel(OpcodeType.ROOT_EXPRESSION, -1);
                    		

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_qualified_name_in_solo_method_call591);
                    qualified_name7=qualified_name();

                    state._fsp--;


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:361:18: ( COLON ID )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==COLON) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:361:19: COLON ID
                            {
                            match(input,COLON,FOLLOW_COLON_in_solo_method_call594); 

                            ID8=(CommonTree)match(input,ID,FOLLOW_ID_in_solo_method_call596); 

                            }
                            break;

                    }


                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_solo_method_call600); 

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:361:41: (e= expression ( COMMA e= expression )* )?
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( (LA30_0==AND||LA30_0==BOOLEAN||LA30_0==CAST||LA30_0==DECIMAL||LA30_0==DIVIDE||LA30_0==EQUALITY||(LA30_0 >= FUNCTION_CALL && LA30_0 <= FUNCTION_CALL_THIS)||(LA30_0 >= GREATER && LA30_0 <= GREATER_EQUAL)||(LA30_0 >= INHERITS && LA30_0 <= INT)||(LA30_0 >= LESS && LA30_0 <= MULTIPLY)||LA30_0==NOTEQUALS||LA30_0==NULL||LA30_0==OR||LA30_0==PLUS||(LA30_0 >= QUALIFIED_SOLO_EXPRESSION && LA30_0 <= QUOTE)||LA30_0==STRING||LA30_0==UNARY_NOT) ) {
                        alt30=1;
                    }
                    switch (alt30) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:361:42: e= expression ( COMMA e= expression )*
                            {
                            if(builder.getCurrentMethod() != null){startLocation = builder.getCurrentMethod().getSteps().size();}

                            pushFollow(FOLLOW_expression_in_solo_method_call611);
                            e=expression();

                            state._fsp--;



                            			if(builder.getCurrentMethod() != null && startLocation != builder.getCurrentMethod().getSteps().size() -1){
                            				builder.getCurrentMethod().getSteps().get(startLocation).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
                            				(e!=null?e.step:null).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
                            			}
                            			values.add((e!=null?e.eval:null));
                            			steps.add((e!=null?e.step:null));
                            			registers.add((e!=null?e.eval:null).getRegister());
                            			if((e!=null?e.eval:null).getType() != null)
                            				types.add((e!=null?e.eval:null).getType().getStaticKey());
                                            	argumentTypes.add((e!=null?e.eval:null).getType());
                                            	inCallStep = false;
                            		

                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:376:3: ( COMMA e= expression )*
                            loop29:
                            do {
                                int alt29=2;
                                int LA29_0 = input.LA(1);

                                if ( (LA29_0==COMMA) ) {
                                    alt29=1;
                                }


                                switch (alt29) {
                            	case 1 :
                            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:376:4: COMMA e= expression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_solo_method_call621); 

                            	    if(builder.getCurrentMethod() != null){startLocation = builder.getCurrentMethod().getSteps().size();}

                            	    pushFollow(FOLLOW_expression_in_solo_method_call629);
                            	    e=expression();

                            	    state._fsp--;



                            	    			if(builder.getCurrentMethod() != null && startLocation != builder.getCurrentMethod().getSteps().size() -1){
                            	    				builder.getCurrentMethod().getSteps().get(startLocation).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
                            	    				(e!=null?e.step:null).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
                            	    			}
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


                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_solo_method_call643); 

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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:429:4: ^( SOLO_FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN )
                    {
                    match(input,SOLO_FUNCTION_CALL_PARENT,FOLLOW_SOLO_FUNCTION_CALL_PARENT_in_solo_method_call655); 


                    		inCallStep = true;
                    		builder.addStepLabel(OpcodeType.ROOT_EXPRESSION, -1);
                    	

                    match(input, Token.DOWN, null); 
                    match(input,PARENT,FOLLOW_PARENT_in_solo_method_call662); 

                    match(input,COLON,FOLLOW_COLON_in_solo_method_call664); 

                    pushFollow(FOLLOW_qualified_name_in_solo_method_call666);
                    qualified_name9=qualified_name();

                    state._fsp--;


                    match(input,COLON,FOLLOW_COLON_in_solo_method_call668); 

                    ID10=(CommonTree)match(input,ID,FOLLOW_ID_in_solo_method_call670); 

                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_solo_method_call672); 

                    if(builder.getCurrentMethod() != null){startLocation = builder.getCurrentMethod().getSteps().size();}

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:435:3: (e= expression ( COMMA e= expression )* )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==AND||LA32_0==BOOLEAN||LA32_0==CAST||LA32_0==DECIMAL||LA32_0==DIVIDE||LA32_0==EQUALITY||(LA32_0 >= FUNCTION_CALL && LA32_0 <= FUNCTION_CALL_THIS)||(LA32_0 >= GREATER && LA32_0 <= GREATER_EQUAL)||(LA32_0 >= INHERITS && LA32_0 <= INT)||(LA32_0 >= LESS && LA32_0 <= MULTIPLY)||LA32_0==NOTEQUALS||LA32_0==NULL||LA32_0==OR||LA32_0==PLUS||(LA32_0 >= QUALIFIED_SOLO_EXPRESSION && LA32_0 <= QUOTE)||LA32_0==STRING||LA32_0==UNARY_NOT) ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:435:4: e= expression ( COMMA e= expression )*
                            {
                            pushFollow(FOLLOW_expression_in_solo_method_call683);
                            e=expression();

                            state._fsp--;



                            			if(builder.getCurrentMethod() != null && startLocation != builder.getCurrentMethod().getSteps().size() -1){
                            				builder.getCurrentMethod().getSteps().get(startLocation).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
                            				(e!=null?e.step:null).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
                            			}
                            			values.add((e!=null?e.eval:null));
                            			steps.add((e!=null?e.step:null));
                            			registers.add((e!=null?e.eval:null).getRegister());
                            			types.add((e!=null?e.eval:null).getType().getStaticKey());
                                            	argumentTypes.add((e!=null?e.eval:null).getType());
                                            	inCallStep = false;
                            		

                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:447:5: ( COMMA e= expression )*
                            loop31:
                            do {
                                int alt31=2;
                                int LA31_0 = input.LA(1);

                                if ( (LA31_0==COMMA) ) {
                                    alt31=1;
                                }


                                switch (alt31) {
                            	case 1 :
                            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:447:6: COMMA e= expression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_solo_method_call690); 

                            	    if(builder.getCurrentMethod() != null){startLocation = builder.getCurrentMethod().getSteps().size();}

                            	    pushFollow(FOLLOW_expression_in_solo_method_call700);
                            	    e=expression();

                            	    state._fsp--;



                            	    			if(builder.getCurrentMethod() != null && startLocation != builder.getCurrentMethod().getSteps().size() -1){
                            	    				builder.getCurrentMethod().getSteps().get(startLocation).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
                            	    				(e!=null?e.step:null).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
                            	    			}
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


                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_solo_method_call710); 

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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:500:4: ^( SOLO_FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN (e= expression ( COMMA e= expression )* )? RIGHT_PAREN )
                    {
                    match(input,SOLO_FUNCTION_CALL_THIS,FOLLOW_SOLO_FUNCTION_CALL_THIS_in_solo_method_call721); 


                    		inCallStep = true;
                    		builder.addStepLabel(OpcodeType.ROOT_EXPRESSION, -1);
                    	

                    match(input, Token.DOWN, null); 
                    match(input,ME,FOLLOW_ME_in_solo_method_call728); 

                    match(input,COLON,FOLLOW_COLON_in_solo_method_call730); 

                    pushFollow(FOLLOW_qualified_name_in_solo_method_call732);
                    qualified_name11=qualified_name();

                    state._fsp--;


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:505:26: ( COLON ID )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==COLON) ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:505:27: COLON ID
                            {
                            match(input,COLON,FOLLOW_COLON_in_solo_method_call735); 

                            ID12=(CommonTree)match(input,ID,FOLLOW_ID_in_solo_method_call737); 

                            }
                            break;

                    }


                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_solo_method_call741); 

                    if(builder.getCurrentMethod() != null){startLocation = builder.getCurrentMethod().getSteps().size();}

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:506:3: (e= expression ( COMMA e= expression )* )?
                    int alt35=2;
                    int LA35_0 = input.LA(1);

                    if ( (LA35_0==AND||LA35_0==BOOLEAN||LA35_0==CAST||LA35_0==DECIMAL||LA35_0==DIVIDE||LA35_0==EQUALITY||(LA35_0 >= FUNCTION_CALL && LA35_0 <= FUNCTION_CALL_THIS)||(LA35_0 >= GREATER && LA35_0 <= GREATER_EQUAL)||(LA35_0 >= INHERITS && LA35_0 <= INT)||(LA35_0 >= LESS && LA35_0 <= MULTIPLY)||LA35_0==NOTEQUALS||LA35_0==NULL||LA35_0==OR||LA35_0==PLUS||(LA35_0 >= QUALIFIED_SOLO_EXPRESSION && LA35_0 <= QUOTE)||LA35_0==STRING||LA35_0==UNARY_NOT) ) {
                        alt35=1;
                    }
                    switch (alt35) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:506:4: e= expression ( COMMA e= expression )*
                            {
                            pushFollow(FOLLOW_expression_in_solo_method_call752);
                            e=expression();

                            state._fsp--;



                            			if(builder.getCurrentMethod() != null && startLocation != builder.getCurrentMethod().getSteps().size() -1){
                            				builder.getCurrentMethod().getSteps().get(startLocation).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
                            				(e!=null?e.step:null).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
                            			}
                            			values.add((e!=null?e.eval:null));
                            			steps.add((e!=null?e.step:null));
                            			registers.add((e!=null?e.eval:null).getRegister());
                            			types.add((e!=null?e.eval:null).getType().getStaticKey());
                                            	argumentTypes.add((e!=null?e.eval:null).getType());
                                            	inCallStep = false;
                            		

                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:518:4: ( COMMA e= expression )*
                            loop34:
                            do {
                                int alt34=2;
                                int LA34_0 = input.LA(1);

                                if ( (LA34_0==COMMA) ) {
                                    alt34=1;
                                }


                                switch (alt34) {
                            	case 1 :
                            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:518:5: COMMA e= expression
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_solo_method_call759); 

                            	    if(builder.getCurrentMethod() != null){startLocation = builder.getCurrentMethod().getSteps().size();}

                            	    pushFollow(FOLLOW_expression_in_solo_method_call769);
                            	    e=expression();

                            	    state._fsp--;



                            	    			if(builder.getCurrentMethod() != null && startLocation != builder.getCurrentMethod().getSteps().size() -1){
                            	    				builder.getCurrentMethod().getSteps().get(startLocation).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
                            	    				(e!=null?e.step:null).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
                            	    			}
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


                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_solo_method_call779); 

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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:569:1: alert_statement : ^( ALERT LEFT_PAREN ex= root_expression RIGHT_PAREN ) ;
    public final QuorumTreeWalker.alert_statement_return alert_statement() throws RecognitionException {
        alert_statement_stack.push(new alert_statement_scope());
        QuorumTreeWalker.alert_statement_return retval = new QuorumTreeWalker.alert_statement_return();
        retval.start = input.LT(1);


        CommonTree LEFT_PAREN13=null;
        QuorumTreeWalker.root_expression_return ex =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:575:2: ( ^( ALERT LEFT_PAREN ex= root_expression RIGHT_PAREN ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:575:4: ^( ALERT LEFT_PAREN ex= root_expression RIGHT_PAREN )
            {
            match(input,ALERT,FOLLOW_ALERT_in_alert_statement799); 


            		((alert_statement_scope)alert_statement_stack.peek()).errorValue = new ExpressionValue();
            		((alert_statement_scope)alert_statement_stack.peek()).errorStep = null;
            	

            match(input, Token.DOWN, null); 
            LEFT_PAREN13=(CommonTree)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_alert_statement807); 

            pushFollow(FOLLOW_root_expression_in_alert_statement811);
            ex=root_expression();

            state._fsp--;



            		ErrorTypeDescriptor t = new ErrorTypeDescriptor();

            		if(!(ex!=null?ex.eval:null).getType().getName().equals(TypeDescriptor.TEXT)){
            			
            			ClassDescriptor cd = symbol.findFullyQualifiedClass((ex!=null?ex.eval:null).getType().getName());
            			
            			if(cd == null)
            				cd = symbol.findClass((ex!=null?ex.eval:null).getType().getName(), "Libraries.Language.Errors");
            			
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
            	

            match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_alert_statement819); 


            		LineInformation location = new LineInformation();
                            
            		location.setEndColumn(((alert_statement_scope)alert_statement_stack.peek()).errorStep.getEndColumn());
                            location.setEndLine(((alert_statement_scope)alert_statement_stack.peek()).errorStep.getEndLine());
                            location.setStartColumn(((alert_statement_scope)alert_statement_stack.peek()).errorStep.getBeginColumn());
                            location.setStartLine(((alert_statement_scope)alert_statement_stack.peek()).errorStep.getBeginLine());
                            location.setFile(symbol.getCurrentClass().getFile().getStaticKey());
                            location.setClassName(symbol.getCurrentClass().getStaticKey());
                            location.setMethodName(symbol.getCurrentMethod().getStaticKey());
                            
                            
            		stepFactory.addAlertStep(location, ((alert_statement_scope)alert_statement_stack.peek()).errorType, ((alert_statement_scope)alert_statement_stack.peek()).errorValue, ((alert_statement_scope)alert_statement_stack.peek()).errorStep);
            		builder.addStepLabel(OpcodeType.ALERT, -1);
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:634:1: check_statement :check= CHECK block[true] ( (detect_start= DETECT det_param= detect_parameter block[true] )+ (always= ALWAYS block[true] )? |always= ALWAYS block[true] ) end= END ;
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
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:644:2: (check= CHECK block[true] ( (detect_start= DETECT det_param= detect_parameter block[true] )+ (always= ALWAYS block[true] )? |always= ALWAYS block[true] ) end= END )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:645:2: check= CHECK block[true] ( (detect_start= DETECT det_param= detect_parameter block[true] )+ (always= ALWAYS block[true] )? |always= ALWAYS block[true] ) end= END
            {
            	
            		((check_statement_scope)check_statement_stack.peek()).detectJump = new JumpStep();
            		((check_statement_scope)check_statement_stack.peek()).info = new ExceptionInfo();
            		((check_statement_scope)check_statement_stack.peek()).detect_counter = 0;
            		((check_statement_scope)check_statement_stack.peek()).has_always = false;
            		
            		sub_counter++;
            		if(sub_counter > 1){labelCounter++;}
            		((check_statement_scope)check_statement_stack.peek()).tempLabelCounter = labelCounter;
            		((check_statement_scope)check_statement_stack.peek()).info.alwaysStartLabel = builder.getCurrentClass().getStaticKey() + "_" + ((check_statement_scope)check_statement_stack.peek()).info.ALWAYS + ((check_statement_scope)check_statement_stack.peek()).tempLabelCounter + ((check_statement_scope)check_statement_stack.peek()).info.START;
            	

            check=(CommonTree)match(input,CHECK,FOLLOW_CHECK_in_check_statement851); 


            		((check_statement_scope)check_statement_stack.peek()).info.location = new LineInformation(
            			check.getLine(),
            			check.getLine(),
            			check.getCharPositionInLine(),
            			(check!=null?check.getText():null).length() + check.getCharPositionInLine());
            		((check_statement_scope)check_statement_stack.peek()).info.checkStartLabel = builder.getCurrentClass().getStaticKey() + "_" + (check!=null?check.getText():null) + ((check_statement_scope)check_statement_stack.peek()).tempLabelCounter + ((check_statement_scope)check_statement_stack.peek()).info.START;
            		stepFactory.startCheck(((check_statement_scope)check_statement_stack.peek()).info);
            		((check_statement_scope)check_statement_stack.peek()).startSymbol = "check";
            	

            pushFollow(FOLLOW_block_in_check_statement857);
            block(true);

            state._fsp--;



            		((check_statement_scope)check_statement_stack.peek()).info.checkJump.setBeginColumn(check.getCharPositionInLine());
            		((check_statement_scope)check_statement_stack.peek()).info.checkJump.setEndColumn(check.getCharPositionInLine() + ((check!=null?check.getText():null).length()));
                            ((check_statement_scope)check_statement_stack.peek()).info.checkJump.setEndLine(check.getLine());
            		stepFactory.addCheckEndJumpStep(((check_statement_scope)check_statement_stack.peek()).info);
            	

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:673:2: ( (detect_start= DETECT det_param= detect_parameter block[true] )+ (always= ALWAYS block[true] )? |always= ALWAYS block[true] )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:674:7: (detect_start= DETECT det_param= detect_parameter block[true] )+ (always= ALWAYS block[true] )?
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:674:7: (detect_start= DETECT det_param= detect_parameter block[true] )+
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
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:674:8: detect_start= DETECT det_param= detect_parameter block[true]
                    	    {
                    	    detect_start=(CommonTree)match(input,DETECT,FOLLOW_DETECT_in_check_statement879); 


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
                    	    	    		
                    	    	    	

                    	    pushFollow(FOLLOW_detect_parameter_in_check_statement906);
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
                    	    	    	

                    	    pushFollow(FOLLOW_block_in_check_statement930);
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


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:725:7: (always= ALWAYS block[true] )?
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0==ALWAYS) ) {
                        alt38=1;
                    }
                    switch (alt38) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:725:8: always= ALWAYS block[true]
                            {
                            always=(CommonTree)match(input,ALWAYS,FOLLOW_ALWAYS_in_check_statement963); 


                            	    		((check_statement_scope)check_statement_stack.peek()).detectJump.setBeginLine(always.getLine());
                            	    		stepFactory.endDetect(((check_statement_scope)check_statement_stack.peek()).info, ((check_statement_scope)check_statement_stack.peek()).detect_counter);
                            	    		((check_statement_scope)check_statement_stack.peek()).detect_counter = ((check_statement_scope)check_statement_stack.peek()).detect_counter + 1;
                            	    		
                            	    		((check_statement_scope)check_statement_stack.peek()).has_always = true;
                            	    		((check_statement_scope)check_statement_stack.peek()).info.hasAlways = true;
                            	    		stepFactory.startAlways(((check_statement_scope)check_statement_stack.peek()).info, true);
                            	    		((check_statement_scope)check_statement_stack.peek()).startSymbol = "always";
                            	    	

                            pushFollow(FOLLOW_block_in_check_statement979);
                            block(true);

                            state._fsp--;


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:740:7: always= ALWAYS block[true]
                    {
                    always=(CommonTree)match(input,ALWAYS,FOLLOW_ALWAYS_in_check_statement1011); 


                    		    	((check_statement_scope)check_statement_stack.peek()).info.checkJump.setBeginLine(always.getLine());
                    			stepFactory.endCheck(((check_statement_scope)check_statement_stack.peek()).info);
                    			
                    	    		((check_statement_scope)check_statement_stack.peek()).has_always = true;
                    	    		((check_statement_scope)check_statement_stack.peek()).info.hasAlways = true;
                    	    		stepFactory.startAlways(((check_statement_scope)check_statement_stack.peek()).info, true);
                    	    		((check_statement_scope)check_statement_stack.peek()).startSymbol = "always";
                    	    	

                    pushFollow(FOLLOW_block_in_check_statement1034);
                    block(true);

                    state._fsp--;


                    }
                    break;

            }


            end=(CommonTree)match(input,END,FOLLOW_END_in_check_statement1043); 


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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:775:1: detect_parameter returns [String name, ArrayList<ErrorTypeDescriptor> exceptionTypeList] : ^(id= ID ( OF_TYPE qn= qualified_name ( OR qn= qualified_name )* )? ) ;
    public final QuorumTreeWalker.detect_parameter_return detect_parameter() throws RecognitionException {
        detect_parameter_stack.push(new detect_parameter_scope());
        QuorumTreeWalker.detect_parameter_return retval = new QuorumTreeWalker.detect_parameter_return();
        retval.start = input.LT(1);


        CommonTree id=null;
        QuorumTreeWalker.qualified_name_return qn =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:779:2: ( ^(id= ID ( OF_TYPE qn= qualified_name ( OR qn= qualified_name )* )? ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:779:4: ^(id= ID ( OF_TYPE qn= qualified_name ( OR qn= qualified_name )* )? )
            {
            id=(CommonTree)match(input,ID,FOLLOW_ID_in_detect_parameter1072); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:780:2: ( OF_TYPE qn= qualified_name ( OR qn= qualified_name )* )?
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==OF_TYPE) ) {
                    alt41=1;
                }
                switch (alt41) {
                    case 1 :
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:780:3: OF_TYPE qn= qualified_name ( OR qn= qualified_name )*
                        {
                        match(input,OF_TYPE,FOLLOW_OF_TYPE_in_detect_parameter1077); 


                        		((detect_parameter_scope)detect_parameter_stack.peek()).exceptionList = new ArrayList<ErrorTypeDescriptor>();
                        	

                        pushFollow(FOLLOW_qualified_name_in_detect_parameter1086);
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
                        	

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:822:2: ( OR qn= qualified_name )*
                        loop40:
                        do {
                            int alt40=2;
                            int LA40_0 = input.LA(1);

                            if ( (LA40_0==OR) ) {
                                alt40=1;
                            }


                            switch (alt40) {
                        	case 1 :
                        	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:822:3: OR qn= qualified_name
                        	    {
                        	    match(input,OR,FOLLOW_OR_in_detect_parameter1093); 

                        	    pushFollow(FOLLOW_qualified_name_in_detect_parameter1097);
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:873:1: print_statement : PRINT root_expression ;
    public final QuorumTreeWalker.print_statement_return print_statement() throws RecognitionException {
        QuorumTreeWalker.print_statement_return retval = new QuorumTreeWalker.print_statement_return();
        retval.start = input.LT(1);


        QuorumTreeWalker.root_expression_return root_expression14 =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:874:2: ( PRINT root_expression )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:874:4: PRINT root_expression
            {
            match(input,PRINT,FOLLOW_PRINT_in_print_statement1124); 

            pushFollow(FOLLOW_root_expression_in_print_statement1126);
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:894:1: speak_statement : SAY root_expression ;
    public final QuorumTreeWalker.speak_statement_return speak_statement() throws RecognitionException {
        QuorumTreeWalker.speak_statement_return retval = new QuorumTreeWalker.speak_statement_return();
        retval.start = input.LT(1);


        QuorumTreeWalker.root_expression_return root_expression15 =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:895:2: ( SAY root_expression )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:895:4: SAY root_expression
            {
            match(input,SAY,FOLLOW_SAY_in_speak_statement1142); 

            pushFollow(FOLLOW_root_expression_in_speak_statement1144);
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:913:1: return_statement : RETURN ( root_expression | NOW ) ;
    public final QuorumTreeWalker.return_statement_return return_statement() throws RecognitionException {
        QuorumTreeWalker.return_statement_return retval = new QuorumTreeWalker.return_statement_return();
        retval.start = input.LT(1);


        CommonTree NOW17=null;
        CommonTree RETURN18=null;
        QuorumTreeWalker.root_expression_return root_expression16 =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:914:2: ( RETURN ( root_expression | NOW ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:914:4: RETURN ( root_expression | NOW )
            {
            RETURN18=(CommonTree)match(input,RETURN,FOLLOW_RETURN_in_return_statement1158); 

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:914:11: ( root_expression | NOW )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:914:12: root_expression
                    {
                    pushFollow(FOLLOW_root_expression_in_return_statement1161);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:931:4: NOW
                    {
                    NOW17=(CommonTree)match(input,NOW,FOLLOW_NOW_in_return_statement1169); 


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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:946:1: generic_declaration : ^( GENERIC LESS ids+= ID ( COMMA ids+= ID )* GREATER ) ;
    public final QuorumTreeWalker.generic_declaration_return generic_declaration() throws RecognitionException {
        QuorumTreeWalker.generic_declaration_return retval = new QuorumTreeWalker.generic_declaration_return();
        retval.start = input.LT(1);


        CommonTree ids=null;
        List list_ids=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:947:2: ( ^( GENERIC LESS ids+= ID ( COMMA ids+= ID )* GREATER ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:947:4: ^( GENERIC LESS ids+= ID ( COMMA ids+= ID )* GREATER )
            {
            match(input,GENERIC,FOLLOW_GENERIC_in_generic_declaration1187); 

            match(input, Token.DOWN, null); 
            match(input,LESS,FOLLOW_LESS_in_generic_declaration1189); 

            ids=(CommonTree)match(input,ID,FOLLOW_ID_in_generic_declaration1193); 
            if (list_ids==null) list_ids=new ArrayList();
            list_ids.add(ids);


            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:947:27: ( COMMA ids+= ID )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==COMMA) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:947:28: COMMA ids+= ID
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_generic_declaration1196); 

            	    ids=(CommonTree)match(input,ID,FOLLOW_ID_in_generic_declaration1200); 
            	    if (list_ids==null) list_ids=new ArrayList();
            	    list_ids.add(ids);


            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);


            match(input,GREATER,FOLLOW_GREATER_in_generic_declaration1204); 

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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:950:1: generic_statement returns [ArrayList<GenericDescriptor> templateTypes] : ^( GENERIC LESS ad= assignment_declaration ( COMMA a= assignment_declaration )* GREATER ) ;
    public final QuorumTreeWalker.generic_statement_return generic_statement() throws RecognitionException {
        QuorumTreeWalker.generic_statement_return retval = new QuorumTreeWalker.generic_statement_return();
        retval.start = input.LT(1);


        QuorumTreeWalker.assignment_declaration_return ad =null;

        QuorumTreeWalker.assignment_declaration_return a =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:951:2: ( ^( GENERIC LESS ad= assignment_declaration ( COMMA a= assignment_declaration )* GREATER ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:951:4: ^( GENERIC LESS ad= assignment_declaration ( COMMA a= assignment_declaration )* GREATER )
            {
            match(input,GENERIC,FOLLOW_GENERIC_in_generic_statement1222); 

            match(input, Token.DOWN, null); 
            match(input,LESS,FOLLOW_LESS_in_generic_statement1224); 


            		ArrayList<GenericDescriptor> types = new ArrayList<GenericDescriptor>();
            	

            pushFollow(FOLLOW_assignment_declaration_in_generic_statement1233);
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
            	

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:969:3: ( COMMA a= assignment_declaration )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==COMMA) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:969:4: COMMA a= assignment_declaration
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_generic_statement1241); 

            	    pushFollow(FOLLOW_assignment_declaration_in_generic_statement1245);
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
            	

            match(input,GREATER,FOLLOW_GREATER_in_generic_statement1261); 

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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:996:1: class_type returns [TypeDescriptor myType] : qn= qualified_name ;
    public final QuorumTreeWalker.class_type_return class_type() throws RecognitionException {
        QuorumTreeWalker.class_type_return retval = new QuorumTreeWalker.class_type_return();
        retval.start = input.LT(1);


        QuorumTreeWalker.qualified_name_return qn =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:997:2: (qn= qualified_name )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:997:4: qn= qualified_name
            {
            pushFollow(FOLLOW_qualified_name_in_class_type1286);
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1026:1: assignment_declaration returns [TypeDescriptor myType] : (qn= qualified_name (gs= generic_statement )? | INTEGER_KEYWORD | NUMBER_KEYWORD | TEXT | BOOLEAN_KEYWORD );
    public final QuorumTreeWalker.assignment_declaration_return assignment_declaration() throws RecognitionException {
        QuorumTreeWalker.assignment_declaration_return retval = new QuorumTreeWalker.assignment_declaration_return();
        retval.start = input.LT(1);


        QuorumTreeWalker.qualified_name_return qn =null;

        QuorumTreeWalker.generic_statement_return gs =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1027:2: (qn= qualified_name (gs= generic_statement )? | INTEGER_KEYWORD | NUMBER_KEYWORD | TEXT | BOOLEAN_KEYWORD )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1027:4: qn= qualified_name (gs= generic_statement )?
                    {
                    pushFollow(FOLLOW_qualified_name_in_assignment_declaration1308);
                    qn=qualified_name();

                    state._fsp--;


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1027:26: (gs= generic_statement )?
                    int alt45=2;
                    int LA45_0 = input.LA(1);

                    if ( (LA45_0==GENERIC) ) {
                        alt45=1;
                    }
                    switch (alt45) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1027:26: gs= generic_statement
                            {
                            pushFollow(FOLLOW_generic_statement_in_assignment_declaration1312);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1057:4: INTEGER_KEYWORD
                    {
                    match(input,INTEGER_KEYWORD,FOLLOW_INTEGER_KEYWORD_in_assignment_declaration1321); 


                    		TypeDescriptor type = new TypeDescriptor();
                    		type.setName(TypeDescriptor.INTEGER);
                    		retval.myType = type;
                    	

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1063:4: NUMBER_KEYWORD
                    {
                    match(input,NUMBER_KEYWORD,FOLLOW_NUMBER_KEYWORD_in_assignment_declaration1329); 


                    		TypeDescriptor type = new TypeDescriptor();
                    		type.setName(TypeDescriptor.NUMBER);
                    		retval.myType = type;
                    	

                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1069:4: TEXT
                    {
                    match(input,TEXT,FOLLOW_TEXT_in_assignment_declaration1337); 


                    		TypeDescriptor type = new TypeDescriptor();
                    		type.setName(TypeDescriptor.TEXT);
                    		retval.myType = type;
                    	

                    }
                    break;
                case 5 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1075:4: BOOLEAN_KEYWORD
                    {
                    match(input,BOOLEAN_KEYWORD,FOLLOW_BOOLEAN_KEYWORD_in_assignment_declaration1345); 


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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1081:1: assignment_statement : ( (sel= selector COLON )? ID rhs= assign_right_hand_side |obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side | (modifier= access_modifier )? ( CONSTANT )? type= assignment_declaration name= ID (rhs= assign_right_hand_side )? );
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
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1082:2: ( (sel= selector COLON )? ID rhs= assign_right_hand_side |obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side | (modifier= access_modifier )? ( CONSTANT )? type= assignment_declaration name= ID (rhs= assign_right_hand_side )? )
            int alt52=3;
            alt52 = dfa52.predict(input);
            switch (alt52) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1083:3: (sel= selector COLON )? ID rhs= assign_right_hand_side
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1083:3: (sel= selector COLON )?
                    int alt47=2;
                    int LA47_0 = input.LA(1);

                    if ( (LA47_0==ME||LA47_0==PARENT) ) {
                        alt47=1;
                    }
                    switch (alt47) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1083:4: sel= selector COLON
                            {
                            pushFollow(FOLLOW_selector_in_assignment_statement1361);
                            sel=selector();

                            state._fsp--;


                            match(input,COLON,FOLLOW_COLON_in_assignment_statement1363); 

                            }
                            break;

                    }


                    ID19=(CommonTree)match(input,ID,FOLLOW_ID_in_assignment_statement1367); 

                    pushFollow(FOLLOW_assign_right_hand_side_in_assignment_statement1371);
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
                    		
                    		boolean hasRHS = false;
                    		if((rhs!=null?rhs.eval:null) != null){
                    			hasRHS = true;
                    		}
                    		
                    		stepFactory.addAssignmentStep(location, (ID19!=null?ID19.getText():null), (rhs!=null?rhs.eval:null), (rhs!=null?rhs.step:null), false, "", cd, isMe, hasRHS);
                    		builder.addStepLabel(OpcodeType.ASSIGNMENT, -1);
                    	

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1119:4: obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side
                    {
                    pushFollow(FOLLOW_qualified_name_in_assignment_statement1381);
                    obj=qualified_name();

                    state._fsp--;


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1119:23: ( COLON PARENT COLON parent= qualified_name )?
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
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1119:24: COLON PARENT COLON parent= qualified_name
                            {
                            match(input,COLON,FOLLOW_COLON_in_assignment_statement1384); 

                            PARENT21=(CommonTree)match(input,PARENT,FOLLOW_PARENT_in_assignment_statement1386); 

                            match(input,COLON,FOLLOW_COLON_in_assignment_statement1388); 

                            pushFollow(FOLLOW_qualified_name_in_assignment_statement1392);
                            parent=qualified_name();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input,COLON,FOLLOW_COLON_in_assignment_statement1396); 

                    ID20=(CommonTree)match(input,ID,FOLLOW_ID_in_assignment_statement1398); 

                    pushFollow(FOLLOW_assign_right_hand_side_in_assignment_statement1402);
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
                    		
                    		boolean hasRHS = false;
                    		if((rhs!=null?rhs.eval:null) != null){
                    			hasRHS = true;
                    		}
                    		
                    		stepFactory.addAssignmentStep(location, (obj!=null?obj.type:null).getStaticKey(), (rhs!=null?rhs.eval:null), (rhs!=null?rhs.step:null), isLocal, (ID20!=null?ID20.getText():null), cd, false, hasRHS);
                    		builder.addStepLabel(OpcodeType.ASSIGNMENT, -1);
                    	

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1155:4: (modifier= access_modifier )? ( CONSTANT )? type= assignment_declaration name= ID (rhs= assign_right_hand_side )?
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1155:13: (modifier= access_modifier )?
                    int alt49=2;
                    int LA49_0 = input.LA(1);

                    if ( ((LA49_0 >= PRIVATE && LA49_0 <= PUBLIC)) ) {
                        alt49=1;
                    }
                    switch (alt49) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1155:13: modifier= access_modifier
                            {
                            pushFollow(FOLLOW_access_modifier_in_assignment_statement1414);
                            modifier=access_modifier();

                            state._fsp--;


                            }
                            break;

                    }


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1155:32: ( CONSTANT )?
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( (LA50_0==CONSTANT) ) {
                        alt50=1;
                    }
                    switch (alt50) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1155:32: CONSTANT
                            {
                            match(input,CONSTANT,FOLLOW_CONSTANT_in_assignment_statement1417); 

                            }
                            break;

                    }


                    pushFollow(FOLLOW_assignment_declaration_in_assignment_statement1424);
                    type=assignment_declaration();

                    state._fsp--;


                    name=(CommonTree)match(input,ID,FOLLOW_ID_in_assignment_statement1430); 

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1155:85: (rhs= assign_right_hand_side )?
                    int alt51=2;
                    int LA51_0 = input.LA(1);

                    if ( (LA51_0==EQUALITY) ) {
                        alt51=1;
                    }
                    switch (alt51) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1155:85: rhs= assign_right_hand_side
                            {
                            pushFollow(FOLLOW_assign_right_hand_side_in_assignment_statement1434);
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
                    			stepFactory.addAssignmentStep(location, (name!=null?name.getText():null), (rhs!=null?rhs.eval:null), (rhs!=null?rhs.step:null), isLocal, true);
                    		}
                                    else { // are we are trying to instantiate an object?
                                    	builder.addStepLabel(OpcodeType.ROOT_EXPRESSION, -1);
                                        	stepFactory.addAssignmentStep(location, (name!=null?name.getText():null), isLocal, false);
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1184:1: assign_right_hand_side returns [ExpressionValue eval, ExecutionStep step] : ( EQUALITY root_expression ) ;
    public final QuorumTreeWalker.assign_right_hand_side_return assign_right_hand_side() throws RecognitionException {
        QuorumTreeWalker.assign_right_hand_side_return retval = new QuorumTreeWalker.assign_right_hand_side_return();
        retval.start = input.LT(1);


        QuorumTreeWalker.root_expression_return root_expression22 =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1185:2: ( ( EQUALITY root_expression ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1186:3: ( EQUALITY root_expression )
            {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1186:3: ( EQUALITY root_expression )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1186:4: EQUALITY root_expression
            {
            match(input,EQUALITY,FOLLOW_EQUALITY_in_assign_right_hand_side1455); 

            pushFollow(FOLLOW_root_expression_in_assign_right_hand_side1457);
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1194:1: if_statement : begin_if= IF condition= root_expression b= block[true] (begin_else_if= ELSE_IF condition2= root_expression b= block[true] )* (begin_else= ELSE b= block[true] )? end= END ;
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
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1200:3: (begin_if= IF condition= root_expression b= block[true] (begin_else_if= ELSE_IF condition2= root_expression b= block[true] )* (begin_else= ELSE b= block[true] )? end= END )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1200:5: begin_if= IF condition= root_expression b= block[true] (begin_else_if= ELSE_IF condition2= root_expression b= block[true] )* (begin_else= ELSE b= block[true] )? end= END
            {
            begin_if=(CommonTree)match(input,IF,FOLLOW_IF_in_if_statement1483); 


            			((if_statement_scope)if_statement_stack.peek()).endMatch = "if";
            			((if_statement_scope)if_statement_stack.peek()).info = new IfStatementInfo();
            			((if_statement_scope)if_statement_stack.peek()).else_if_counter = 0;
            			((if_statement_scope)if_statement_stack.peek()).info.endLabel = (begin_if!=null?begin_if.getText():null) + labelCounter + ((if_statement_scope)if_statement_stack.peek()).info.END;
            			((if_statement_scope)if_statement_stack.peek()).info.ifFalseLabel =(begin_if!=null?begin_if.getText():null) + labelCounter + ((if_statement_scope)if_statement_stack.peek()).info.FALSE;
            			((if_statement_scope)if_statement_stack.peek()).info.ifStartLabel =(begin_if!=null?begin_if.getText():null) + labelCounter + ((if_statement_scope)if_statement_stack.peek()).info.START;
            			symbol.addStatementFlagToCurrentFile(begin_if.getLine());
            			labelCounter++;		
            		

            pushFollow(FOLLOW_root_expression_in_if_statement1496);
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
            			
            		

            pushFollow(FOLLOW_block_in_if_statement1508);
            b=block(true);

            state._fsp--;



                                    
                                    ((if_statement_scope)if_statement_stack.peek()).info.ifJumpStep.setBeginColumn(begin_if.getCharPositionInLine());
            			((if_statement_scope)if_statement_stack.peek()).info.ifJumpStep.setEndColumn(begin_if.getCharPositionInLine() + ((begin_if!=null?begin_if.getText():null).length()));
            	                ((if_statement_scope)if_statement_stack.peek()).info.ifJumpStep.setEndLine(begin_if.getLine());
            			stepFactory.addIfEndJumpStep(((if_statement_scope)if_statement_stack.peek()).info);
            			
            		

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1244:3: (begin_else_if= ELSE_IF condition2= root_expression b= block[true] )*
            loop53:
            do {
                int alt53=2;
                int LA53_0 = input.LA(1);

                if ( (LA53_0==ELSE_IF) ) {
                    alt53=1;
                }


                switch (alt53) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1244:4: begin_else_if= ELSE_IF condition2= root_expression b= block[true]
            	    {
            	    begin_else_if=(CommonTree)match(input,ELSE_IF,FOLLOW_ELSE_IF_in_if_statement1525); 


            	    			
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
            	    		

            	    pushFollow(FOLLOW_root_expression_in_if_statement1538);
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
            	    		

            	    pushFollow(FOLLOW_block_in_if_statement1548);
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
            	    break loop53;
                }
            } while (true);


            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1307:3: (begin_else= ELSE b= block[true] )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==ELSE) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1307:4: begin_else= ELSE b= block[true]
                    {
                    begin_else=(CommonTree)match(input,ELSE,FOLLOW_ELSE_in_if_statement1582); 


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
                    		

                    pushFollow(FOLLOW_block_in_if_statement1592);
                    b=block(true);

                    state._fsp--;


                    }
                    break;

            }


            end=(CommonTree)match(input,END,FOLLOW_END_in_if_statement1601); 


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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1351:1: loop_statement : REPEAT ( (expr= root_expression TIMES ) | ( ( WHILE | UNTIL ) expr= root_expression ) ) block[true] END ;
    public final QuorumTreeWalker.loop_statement_return loop_statement() throws RecognitionException {
        loop_statement_stack.push(new loop_statement_scope());
        QuorumTreeWalker.loop_statement_return retval = new QuorumTreeWalker.loop_statement_return();
        retval.start = input.LT(1);


        CommonTree REPEAT23=null;
        CommonTree TIMES24=null;
        QuorumTreeWalker.root_expression_return expr =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1370:2: ( REPEAT ( (expr= root_expression TIMES ) | ( ( WHILE | UNTIL ) expr= root_expression ) ) block[true] END )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1371:2: REPEAT ( (expr= root_expression TIMES ) | ( ( WHILE | UNTIL ) expr= root_expression ) ) block[true] END
            {
            REPEAT23=(CommonTree)match(input,REPEAT,FOLLOW_REPEAT_in_loop_statement1624); 



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
            		
            		CompilerError e = symbol.getControlFlow().addStatement(((loop_statement_scope)loop_statement_stack.peek()).location);
            	        if (e != null) {
            	                this.builder.getVirtualMachine().getCompilerErrors().addError(e);
            	        }
            	        
            		symbol.getControlFlow().repeatStart();
            	

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1404:2: ( (expr= root_expression TIMES ) | ( ( WHILE | UNTIL ) expr= root_expression ) )
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==ROOT_EXPRESSION) ) {
                alt56=1;
            }
            else if ( (LA56_0==UNTIL||LA56_0==WHILE) ) {
                alt56=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;

            }
            switch (alt56) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1434:3: (expr= root_expression TIMES )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1434:3: (expr= root_expression TIMES )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1434:4: expr= root_expression TIMES
                    {
                    pushFollow(FOLLOW_root_expression_in_loop_statement1697);
                    expr=root_expression();

                    state._fsp--;


                    TIMES24=(CommonTree)match(input,TIMES,FOLLOW_TIMES_in_loop_statement1699); 

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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1462:4: ( ( WHILE | UNTIL ) expr= root_expression )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1462:4: ( ( WHILE | UNTIL ) expr= root_expression )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1462:5: ( WHILE | UNTIL ) expr= root_expression
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1462:5: ( WHILE | UNTIL )
                    int alt55=2;
                    int LA55_0 = input.LA(1);

                    if ( (LA55_0==WHILE) ) {
                        alt55=1;
                    }
                    else if ( (LA55_0==UNTIL) ) {
                        alt55=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 55, 0, input);

                        throw nvae;

                    }
                    switch (alt55) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1462:6: WHILE
                            {
                            match(input,WHILE,FOLLOW_WHILE_in_loop_statement1710); 

                            ((loop_statement_scope)loop_statement_stack.peek()).isWhile = true;

                            }
                            break;
                        case 2 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1462:49: UNTIL
                            {
                            match(input,UNTIL,FOLLOW_UNTIL_in_loop_statement1716); 

                            ((loop_statement_scope)loop_statement_stack.peek()).isWhile = false;

                            }
                            break;

                    }



                                   		builder.addLabel(((loop_statement_scope)loop_statement_stack.peek()).jumpToTop);
                    	

                    pushFollow(FOLLOW_root_expression_in_loop_statement1731);
                    expr=root_expression();

                    state._fsp--;



                    			((loop_statement_scope)loop_statement_stack.peek()).first_value = (expr!=null?expr.eval:null);
                    			((loop_statement_scope)loop_statement_stack.peek()).first_step = (expr!=null?expr.step:null);
                    			((loop_statement_scope)loop_statement_stack.peek()).type = 2;
                    			
                    			TypeDescriptor type = (expr!=null?expr.eval:null).getType();
                    			if(type == null || !type.isBoolean()) {
                    				CompilerError error = new CompilerError(REPEAT23.getLine(), "repeat loops require a boolean expression.", ErrorType.REPEAT_NON_BOOLEAN);
                    				vm.getCompilerErrors().addError(error);	
                    			}
                    			
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


            pushFollow(FOLLOW_block_in_loop_statement1742);
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
            	

            match(input,END,FOLLOW_END_in_loop_statement1750); 


            		
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1548:1: selector returns [ScopeSelector scopeSel] : ( ^( PARENT qualified_name ) | ME );
    public final QuorumTreeWalker.selector_return selector() throws RecognitionException {
        QuorumTreeWalker.selector_return retval = new QuorumTreeWalker.selector_return();
        retval.start = input.LT(1);


        CommonTree PARENT26=null;
        QuorumTreeWalker.qualified_name_return qualified_name25 =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1549:2: ( ^( PARENT qualified_name ) | ME )
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==PARENT) ) {
                alt57=1;
            }
            else if ( (LA57_0==ME) ) {
                alt57=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 57, 0, input);

                throw nvae;

            }
            switch (alt57) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1549:4: ^( PARENT qualified_name )
                    {
                    PARENT26=(CommonTree)match(input,PARENT,FOLLOW_PARENT_in_selector1789); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_qualified_name_in_selector1791);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1562:4: ME
                    {
                    match(input,ME,FOLLOW_ME_in_selector1800); 


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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1570:1: root_expression returns [ExpressionValue eval, ExecutionStep step] : ^( ROOT_EXPRESSION expression ) ;
    public final QuorumTreeWalker.root_expression_return root_expression() throws RecognitionException {
        QuorumTreeWalker.root_expression_return retval = new QuorumTreeWalker.root_expression_return();
        retval.start = input.LT(1);


        QuorumTreeWalker.expression_return expression27 =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1571:2: ( ^( ROOT_EXPRESSION expression ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1572:2: ^( ROOT_EXPRESSION expression )
            {

            		builder.addStepLabel(OpcodeType.ROOT_EXPRESSION, -1);
            	

            match(input,ROOT_EXPRESSION,FOLLOW_ROOT_EXPRESSION_in_root_expression1826); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expression_in_root_expression1828);
            expression27=expression();

            state._fsp--;


            match(input, Token.UP, null); 



            		expressionDepth = 0;
            		retval.eval = (expression27!=null?expression27.eval:null);
            		retval.step = (expression27!=null?expression27.step:null);
            		retval.eval.getResult().getType().setExpressionLevel(expressionDepth );
            	

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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1584:1: expression returns [ExpressionValue eval, ExecutionStep step] : ( ^( UNARY_NOT NOT expr= expression ) | ^( EQUALITY left= expression right= expression ) | ^( NOTEQUALS left= expression right= expression ) | ^( GREATER left= expression right= expression ) | ^( GREATER_EQUAL left= expression right= expression ) | ^( INHERITS left= expression dec= class_type ) | ^( LESS left= expression right= expression ) | ^( LESS_EQUAL left= expression right= expression ) | ^( OR left= expression right= expression ) | ^( AND left= expression right= expression ) | ^( PLUS left= expression right= expression ) | ^( MINUS left= expression right= expression ) | ^( MULTIPLY left= expression right= expression ) | ^( DIVIDE left= expression right= expression ) | ^( MODULO left= expression right= expression ) | ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | ^( QUALIFIED_SOLO_EXPRESSION var= qualified_name ( COLON ID )? ) | ^( QUALIFIED_SOLO_PARENT_EXPRESSON var= qualified_name COLON PARENT COLON par= qualified_name COLON ID ) | ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name ) | ^( FUNCTION_CALL_PARENT PARENT COLON qn1= qualified_name COLON ID LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | BOOLEAN | ( MINUS )? DECIMAL | ( MINUS )? INT | STRING | QUOTE | NULL | ME | INPUT LEFT_PAREN input_expr= expression RIGHT_PAREN | CAST LEFT_PAREN castqn= assignment_declaration COMMA cast_expr= expression castrpn= RIGHT_PAREN );
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
        CommonTree QUOTE44=null;
        CommonTree NULL45=null;
        CommonTree ME46=null;
        CommonTree LEFT_PAREN47=null;
        CommonTree RIGHT_PAREN48=null;
        CommonTree CAST49=null;
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
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1585:2: ( ^( UNARY_NOT NOT expr= expression ) | ^( EQUALITY left= expression right= expression ) | ^( NOTEQUALS left= expression right= expression ) | ^( GREATER left= expression right= expression ) | ^( GREATER_EQUAL left= expression right= expression ) | ^( INHERITS left= expression dec= class_type ) | ^( LESS left= expression right= expression ) | ^( LESS_EQUAL left= expression right= expression ) | ^( OR left= expression right= expression ) | ^( AND left= expression right= expression ) | ^( PLUS left= expression right= expression ) | ^( MINUS left= expression right= expression ) | ^( MULTIPLY left= expression right= expression ) | ^( DIVIDE left= expression right= expression ) | ^( MODULO left= expression right= expression ) | ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | ^( QUALIFIED_SOLO_EXPRESSION var= qualified_name ( COLON ID )? ) | ^( QUALIFIED_SOLO_PARENT_EXPRESSON var= qualified_name COLON PARENT COLON par= qualified_name COLON ID ) | ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name ) | ^( FUNCTION_CALL_PARENT PARENT COLON qn1= qualified_name COLON ID LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN fel= function_expression_list RIGHT_PAREN ) | BOOLEAN | ( MINUS )? DECIMAL | ( MINUS )? INT | STRING | QUOTE | NULL | ME | INPUT LEFT_PAREN input_expr= expression RIGHT_PAREN | CAST LEFT_PAREN castqn= assignment_declaration COMMA cast_expr= expression castrpn= RIGHT_PAREN )
            int alt63=30;
            switch ( input.LA(1) ) {
            case UNARY_NOT:
                {
                alt63=1;
                }
                break;
            case EQUALITY:
                {
                alt63=2;
                }
                break;
            case NOTEQUALS:
                {
                alt63=3;
                }
                break;
            case GREATER:
                {
                alt63=4;
                }
                break;
            case GREATER_EQUAL:
                {
                alt63=5;
                }
                break;
            case INHERITS:
                {
                alt63=6;
                }
                break;
            case LESS:
                {
                alt63=7;
                }
                break;
            case LESS_EQUAL:
                {
                alt63=8;
                }
                break;
            case OR:
                {
                alt63=9;
                }
                break;
            case AND:
                {
                alt63=10;
                }
                break;
            case PLUS:
                {
                alt63=11;
                }
                break;
            case MINUS:
                {
                switch ( input.LA(2) ) {
                case DOWN:
                    {
                    alt63=12;
                    }
                    break;
                case DECIMAL:
                    {
                    alt63=23;
                    }
                    break;
                case INT:
                    {
                    alt63=24;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 63, 12, input);

                    throw nvae;

                }

                }
                break;
            case MULTIPLY:
                {
                alt63=13;
                }
                break;
            case DIVIDE:
                {
                alt63=14;
                }
                break;
            case MODULO:
                {
                alt63=15;
                }
                break;
            case FUNCTION_CALL:
                {
                alt63=16;
                }
                break;
            case QUALIFIED_SOLO_EXPRESSION:
                {
                alt63=17;
                }
                break;
            case QUALIFIED_SOLO_PARENT_EXPRESSON:
                {
                alt63=18;
                }
                break;
            case QUALIFIED_SOLO_EXPRESSION_SELECTOR:
                {
                alt63=19;
                }
                break;
            case FUNCTION_CALL_PARENT:
                {
                alt63=20;
                }
                break;
            case FUNCTION_CALL_THIS:
                {
                alt63=21;
                }
                break;
            case BOOLEAN:
                {
                alt63=22;
                }
                break;
            case DECIMAL:
                {
                alt63=23;
                }
                break;
            case INT:
                {
                alt63=24;
                }
                break;
            case STRING:
                {
                alt63=25;
                }
                break;
            case QUOTE:
                {
                alt63=26;
                }
                break;
            case NULL:
                {
                alt63=27;
                }
                break;
            case ME:
                {
                alt63=28;
                }
                break;
            case INPUT:
                {
                alt63=29;
                }
                break;
            case CAST:
                {
                alt63=30;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;

            }

            switch (alt63) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1586:3: ^( UNARY_NOT NOT expr= expression )
                    {
                    match(input,UNARY_NOT,FOLLOW_UNARY_NOT_in_expression1850); 

                    match(input, Token.DOWN, null); 
                    match(input,NOT,FOLLOW_NOT_in_expression1852); 

                    pushFollow(FOLLOW_expression_in_expression1858);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1593:4: ^( EQUALITY left= expression right= expression )
                    {
                    match(input,EQUALITY,FOLLOW_EQUALITY_in_expression1870); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1876);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression1882);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1600:4: ^( NOTEQUALS left= expression right= expression )
                    {
                    match(input,NOTEQUALS,FOLLOW_NOTEQUALS_in_expression1892); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1898);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression1904);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1607:4: ^( GREATER left= expression right= expression )
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_expression1914); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1920);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression1926);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1614:4: ^( GREATER_EQUAL left= expression right= expression )
                    {
                    match(input,GREATER_EQUAL,FOLLOW_GREATER_EQUAL_in_expression1936); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1942);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression1948);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1621:4: ^( INHERITS left= expression dec= class_type )
                    {
                    match(input,INHERITS,FOLLOW_INHERITS_in_expression1958); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1962);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_class_type_in_expression1966);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1628:4: ^( LESS left= expression right= expression )
                    {
                    match(input,LESS,FOLLOW_LESS_in_expression1976); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression1982);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression1988);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1635:4: ^( LESS_EQUAL left= expression right= expression )
                    {
                    match(input,LESS_EQUAL,FOLLOW_LESS_EQUAL_in_expression1998); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2004);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression2010);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1642:4: ^( OR left= expression right= expression )
                    {
                    match(input,OR,FOLLOW_OR_in_expression2020); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2026);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression2032);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1649:4: ^( AND left= expression right= expression )
                    {
                    match(input,AND,FOLLOW_AND_in_expression2042); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2048);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression2054);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1656:4: ^( PLUS left= expression right= expression )
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_expression2064); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2070);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression2076);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1663:4: ^( MINUS left= expression right= expression )
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_expression2086); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2092);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression2098);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1670:4: ^( MULTIPLY left= expression right= expression )
                    {
                    match(input,MULTIPLY,FOLLOW_MULTIPLY_in_expression2108); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2114);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression2120);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1677:4: ^( DIVIDE left= expression right= expression )
                    {
                    match(input,DIVIDE,FOLLOW_DIVIDE_in_expression2130); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2136);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression2142);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1684:4: ^( MODULO left= expression right= expression )
                    {
                    match(input,MODULO,FOLLOW_MODULO_in_expression2152); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_expression2158);
                    left=expression();

                    state._fsp--;


                    pushFollow(FOLLOW_expression_in_expression2164);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1691:4: ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN fel= function_expression_list RIGHT_PAREN )
                    {
                    match(input,FUNCTION_CALL,FOLLOW_FUNCTION_CALL_in_expression2174); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_qualified_name_in_expression2176);
                    qualified_name28=qualified_name();

                    state._fsp--;


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1691:34: ( COLON ID )?
                    int alt58=2;
                    int LA58_0 = input.LA(1);

                    if ( (LA58_0==COLON) ) {
                        alt58=1;
                    }
                    switch (alt58) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1691:35: COLON ID
                            {
                            match(input,COLON,FOLLOW_COLON_in_expression2178); 

                            ID29=(CommonTree)match(input,ID,FOLLOW_ID_in_expression2180); 

                            }
                            break;

                    }


                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_expression2184); 


                    		boolean unsetFlag = false;
                    		boolean nested = inCallStep;
                    		if (!inCallStep) {
                    			// We are now inside a call step--set the flag appropriately.
                    			inCallStep = true;
                    			unsetFlag = true;
                    		}
                    		 expressionDepth++;
                    	

                    pushFollow(FOLLOW_function_expression_list_in_expression2196);
                    fel=function_expression_list();

                    state._fsp--;


                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_expression2198); 

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
                    		
                    		expressionDepth--;
                    		retval.eval.getResult().getType().setExpressionLevel(expressionDepth);
                    		if(fel!=null){
                    			builder.addStepLabel(OpcodeType.METHOD_CALL, result.getStepCount());
                    		}
                    		
                    		if (unsetFlag)
                    			inCallStep = false;

                    	

                    }
                    break;
                case 17 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1779:4: ^( QUALIFIED_SOLO_EXPRESSION var= qualified_name ( COLON ID )? )
                    {
                    match(input,QUALIFIED_SOLO_EXPRESSION,FOLLOW_QUALIFIED_SOLO_EXPRESSION_in_expression2209); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_qualified_name_in_expression2213);
                    var=qualified_name();

                    state._fsp--;


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1779:51: ( COLON ID )?
                    int alt59=2;
                    int LA59_0 = input.LA(1);

                    if ( (LA59_0==COLON) ) {
                        alt59=1;
                    }
                    switch (alt59) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1779:52: COLON ID
                            {
                            match(input,COLON,FOLLOW_COLON_in_expression2216); 

                            ID30=(CommonTree)match(input,ID,FOLLOW_ID_in_expression2218); 

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
                    		retval.eval.getResult().getType().setExpressionLevel(expressionDepth);


                    	

                    }
                    break;
                case 18 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1810:4: ^( QUALIFIED_SOLO_PARENT_EXPRESSON var= qualified_name COLON PARENT COLON par= qualified_name COLON ID )
                    {
                    match(input,QUALIFIED_SOLO_PARENT_EXPRESSON,FOLLOW_QUALIFIED_SOLO_PARENT_EXPRESSON_in_expression2230); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_qualified_name_in_expression2234);
                    var=qualified_name();

                    state._fsp--;


                    match(input,COLON,FOLLOW_COLON_in_expression2236); 

                    PARENT31=(CommonTree)match(input,PARENT,FOLLOW_PARENT_in_expression2238); 

                    match(input,COLON,FOLLOW_COLON_in_expression2240); 

                    pushFollow(FOLLOW_qualified_name_in_expression2244);
                    par=qualified_name();

                    state._fsp--;


                    match(input,COLON,FOLLOW_COLON_in_expression2246); 

                    ID32=(CommonTree)match(input,ID,FOLLOW_ID_in_expression2248); 

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
                    		retval.eval.getResult().getType().setExpressionLevel(expressionDepth);
                    	

                    }
                    break;
                case 19 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1848:4: ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name )
                    {
                    match(input,QUALIFIED_SOLO_EXPRESSION_SELECTOR,FOLLOW_QUALIFIED_SOLO_EXPRESSION_SELECTOR_in_expression2258); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_selector_in_expression2260);
                    selector34=selector();

                    state._fsp--;


                    match(input,COLON,FOLLOW_COLON_in_expression2262); 

                    pushFollow(FOLLOW_qualified_name_in_expression2264);
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
                    		retval.eval.getResult().getType().setExpressionLevel(expressionDepth);
                    		
                    	

                    }
                    break;
                case 20 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1878:4: ^( FUNCTION_CALL_PARENT PARENT COLON qn1= qualified_name COLON ID LEFT_PAREN fel= function_expression_list RIGHT_PAREN )
                    {
                    match(input,FUNCTION_CALL_PARENT,FOLLOW_FUNCTION_CALL_PARENT_in_expression2276); 

                    match(input, Token.DOWN, null); 
                    match(input,PARENT,FOLLOW_PARENT_in_expression2278); 

                    match(input,COLON,FOLLOW_COLON_in_expression2280); 

                    pushFollow(FOLLOW_qualified_name_in_expression2284);
                    qn1=qualified_name();

                    state._fsp--;


                    match(input,COLON,FOLLOW_COLON_in_expression2286); 

                    ID35=(CommonTree)match(input,ID,FOLLOW_ID_in_expression2288); 

                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_expression2290); 


                    		boolean unsetFlag = false;
                    		boolean nested = inCallStep;
                    		if (!inCallStep) {
                    			// We are now inside a call step--set the flag appropriately.
                    			inCallStep = true;
                    			unsetFlag = true;
                    		}
                    		expressionDepth++;
                    	

                    pushFollow(FOLLOW_function_expression_list_in_expression2301);
                    fel=function_expression_list();

                    state._fsp--;


                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_expression2303); 

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
                    		expressionDepth--;
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    		
                    		retval.eval.getResult().getType().setExpressionLevel(expressionDepth);
                    		if(fel!=null){
                    			builder.addStepLabel(OpcodeType.METHOD_CALL, result.getStepCount());
                    		}
                    		
                    		if (unsetFlag)
                    			inCallStep = false;
                    		
                    		
                    	

                    }
                    break;
                case 21 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1960:4: ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN fel= function_expression_list RIGHT_PAREN )
                    {
                    match(input,FUNCTION_CALL_THIS,FOLLOW_FUNCTION_CALL_THIS_in_expression2313); 

                    match(input, Token.DOWN, null); 
                    match(input,ME,FOLLOW_ME_in_expression2315); 

                    match(input,COLON,FOLLOW_COLON_in_expression2317); 

                    pushFollow(FOLLOW_qualified_name_in_expression2319);
                    qualified_name36=qualified_name();

                    state._fsp--;


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1960:49: ( COLON ID )?
                    int alt60=2;
                    int LA60_0 = input.LA(1);

                    if ( (LA60_0==COLON) ) {
                        alt60=1;
                    }
                    switch (alt60) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:1960:50: COLON ID
                            {
                            match(input,COLON,FOLLOW_COLON_in_expression2322); 

                            ID37=(CommonTree)match(input,ID,FOLLOW_ID_in_expression2324); 

                            }
                            break;

                    }


                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_expression2328); 


                    		boolean unsetFlag = false;
                    		boolean nested = inCallStep;
                    		if (!inCallStep) {
                    			// We are now inside a call step--set the flag appropriately.
                    			inCallStep = true;
                    			unsetFlag = true;
                    		}
                    		expressionDepth++;
                    	

                    pushFollow(FOLLOW_function_expression_list_in_expression2339);
                    fel=function_expression_list();

                    state._fsp--;


                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_expression2341); 

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
                    		
                    		expressionDepth--;
                    		retval.eval.getResult().getType().setExpressionLevel(expressionDepth);
                    		if(fel!=null){
                    			builder.addStepLabel(OpcodeType.METHOD_CALL, result.getStepCount());
                    		}
                    		
                    		if (unsetFlag)
                    			inCallStep = false;
                    	

                    }
                    break;
                case 22 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2048:4: BOOLEAN
                    {
                    BOOLEAN38=(CommonTree)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_expression2350); 


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
                    		retval.eval.getResult().getType().setExpressionLevel(expressionDepth);
                    	

                    }
                    break;
                case 23 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2064:4: ( MINUS )? DECIMAL
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2064:4: ( MINUS )?
                    int alt61=2;
                    int LA61_0 = input.LA(1);

                    if ( (LA61_0==MINUS) ) {
                        alt61=1;
                    }
                    switch (alt61) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2064:5: MINUS
                            {
                            MINUS40=(CommonTree)match(input,MINUS,FOLLOW_MINUS_in_expression2359); 

                            }
                            break;

                    }


                    DECIMAL39=(CommonTree)match(input,DECIMAL,FOLLOW_DECIMAL_in_expression2363); 


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
                    		retval.eval.getResult().getType().setExpressionLevel(expressionDepth);
                    	

                    }
                    break;
                case 24 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2096:4: ( MINUS )? INT
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2096:4: ( MINUS )?
                    int alt62=2;
                    int LA62_0 = input.LA(1);

                    if ( (LA62_0==MINUS) ) {
                        alt62=1;
                    }
                    switch (alt62) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2096:5: MINUS
                            {
                            MINUS42=(CommonTree)match(input,MINUS,FOLLOW_MINUS_in_expression2372); 

                            }
                            break;

                    }


                    INT41=(CommonTree)match(input,INT,FOLLOW_INT_in_expression2376); 


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
                    		retval.eval.getResult().getType().setExpressionLevel(expressionDepth);
                    	

                    }
                    break;
                case 25 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2126:4: STRING
                    {
                    STRING43=(CommonTree)match(input,STRING,FOLLOW_STRING_in_expression2384); 


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
                    		retval.eval.getResult().getType().setExpressionLevel(expressionDepth);
                    	

                    }
                    break;
                case 26 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2141:4: QUOTE
                    {
                    QUOTE44=(CommonTree)match(input,QUOTE,FOLLOW_QUOTE_in_expression2392); 


                    		LineInformation location = new LineInformation (
                    			QUOTE44.getLine(),
                    			QUOTE44.getLine(),
                    			QUOTE44.getCharPositionInLine(),
                    			QUOTE44.getCharPositionInLine() + (QUOTE44!=null?QUOTE44.getText():null).length()
                    		);
                    		location.setFile(fileName);
                    		//we must pass three double quotes because normal strings strip out the beginning and the end.
                    		ResultTuple result = stepFactory.addMoveStep(temp, location, "\"\"\"");
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    		retval.eval.getResult().getType().setExpressionLevel(expressionDepth);
                    	

                    }
                    break;
                case 27 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2157:4: NULL
                    {
                    NULL45=(CommonTree)match(input,NULL,FOLLOW_NULL_in_expression2400); 


                    		LineInformation location = new LineInformation (
                    			NULL45.getLine(),
                    			NULL45.getLine(),
                    			NULL45.getCharPositionInLine(),
                    			NULL45.getCharPositionInLine() + (NULL45!=null?NULL45.getText():null).length()
                    		);
                    		location.setFile(fileName);
                    		ResultTuple result = stepFactory.addMoveNullStep(temp, location);
                    		temp = result.getNextRegister();
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    		retval.eval.getResult().getType().setExpressionLevel(expressionDepth);
                    	

                    }
                    break;
                case 28 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2172:4: ME
                    {
                    ME46=(CommonTree)match(input,ME,FOLLOW_ME_in_expression2408); 


                    		LineInformation location = new LineInformation (
                    			ME46.getLine(),
                    			ME46.getLine(),
                    			ME46.getCharPositionInLine(),
                    			ME46.getCharPositionInLine() + (ME46!=null?ME46.getText():null).length()
                    		);
                    		location.setFile(fileName);
                    		
                    		ResultTuple result = new ResultTuple();

                    		result = stepFactory.addMoveThisStep(location, temp);

                    		temp++;
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    		retval.eval.getResult().getType().setExpressionLevel(expressionDepth);
                    	

                    }
                    break;
                case 29 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2191:4: INPUT LEFT_PAREN input_expr= expression RIGHT_PAREN
                    {
                    match(input,INPUT,FOLLOW_INPUT_in_expression2416); 

                    inCallStep = true; int parameterPosition = builder.addParameterLabel();

                    LEFT_PAREN47=(CommonTree)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_expression2419); 

                    expressionDepth++;

                    pushFollow(FOLLOW_expression_in_expression2425);
                    input_expr=expression();

                    state._fsp--;


                    RIGHT_PAREN48=(CommonTree)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_expression2427); 


                    		LineInformation location = new LineInformation (
                    			LEFT_PAREN47.getLine(),
                    			RIGHT_PAREN48.getLine(),
                    			LEFT_PAREN47.getCharPositionInLine(),
                    			RIGHT_PAREN48.getCharPositionInLine()
                    		);
                    		location.setFile(fileName);
                    		
                    		ExecutionStep step = (input_expr!=null?input_expr.step:null);
                    		ExpressionValue value = (input_expr!=null?input_expr.eval:null);

                    		builder.addCallLabel(parameterPosition);
                    		ResultTuple result = stepFactory.addInputStep(location, value, step, temp);
                    		
                    		temp = result.getNextRegister();
                    		expressionDepth--;
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    		inCallStep = false;
                    		retval.eval.getResult().getType().setExpressionLevel(expressionDepth);
                    	

                    }
                    break;
                case 30 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2214:4: CAST LEFT_PAREN castqn= assignment_declaration COMMA cast_expr= expression castrpn= RIGHT_PAREN
                    {
                    CAST49=(CommonTree)match(input,CAST,FOLLOW_CAST_in_expression2435); 

                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_expression2437); 

                    pushFollow(FOLLOW_assignment_declaration_in_expression2441);
                    castqn=assignment_declaration();

                    state._fsp--;


                    match(input,COMMA,FOLLOW_COMMA_in_expression2443); 

                    expressionDepth++;

                    pushFollow(FOLLOW_expression_in_expression2448);
                    cast_expr=expression();

                    state._fsp--;


                    castrpn=(CommonTree)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_expression2452); 


                    		LineInformation location = new LineInformation (
                    			CAST49.getLine(),
                    			castrpn.getLine(),
                    			CAST49.getCharPositionInLine(),
                    			castrpn.getCharPositionInLine()
                    		);
                    		location.setFile(fileName);
                    		TypeDescriptor type = (castqn!=null?castqn.myType:null);
                    		ExecutionStep step = (cast_expr!=null?cast_expr.step:null);
                    		ExpressionValue value = (cast_expr!=null?cast_expr.eval:null);

                    		ResultTuple result = stepFactory.addCastStep(location, type, value, step, temp);
                    		
                    		temp = result.getNextRegister();
                    		expressionDepth--;
                    		retval.eval = result.getValue();
                    		retval.step = result.getStep();
                    		retval.eval.getResult().getType().setExpressionLevel(expressionDepth);
                    	

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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2237:1: function_expression_list returns [List list, int firstParam] : ^( FUNCTION_EXPRESSION_LIST (e= expression )* ) ;
    public final QuorumTreeWalker.function_expression_list_return function_expression_list() throws RecognitionException {
        QuorumTreeWalker.function_expression_list_return retval = new QuorumTreeWalker.function_expression_list_return();
        retval.start = input.LT(1);


        QuorumTreeWalker.expression_return e =null;


        retval.list = new ArrayList(); retval.firstParam = -1;int startLocation = 0;
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2239:2: ( ^( FUNCTION_EXPRESSION_LIST (e= expression )* ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2240:2: ^( FUNCTION_EXPRESSION_LIST (e= expression )* )
            {
            match(input,FUNCTION_EXPRESSION_LIST,FOLLOW_FUNCTION_EXPRESSION_LIST_in_function_expression_list2476); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2240:29: (e= expression )*
                loop64:
                do {
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( (LA64_0==AND||LA64_0==BOOLEAN||LA64_0==CAST||LA64_0==DECIMAL||LA64_0==DIVIDE||LA64_0==EQUALITY||(LA64_0 >= FUNCTION_CALL && LA64_0 <= FUNCTION_CALL_THIS)||(LA64_0 >= GREATER && LA64_0 <= GREATER_EQUAL)||(LA64_0 >= INHERITS && LA64_0 <= INT)||(LA64_0 >= LESS && LA64_0 <= MULTIPLY)||LA64_0==NOTEQUALS||LA64_0==NULL||LA64_0==OR||LA64_0==PLUS||(LA64_0 >= QUALIFIED_SOLO_EXPRESSION && LA64_0 <= QUOTE)||LA64_0==STRING||LA64_0==UNARY_NOT) ) {
                        alt64=1;
                    }


                    switch (alt64) {
                	case 1 :
                	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2241:2: e= expression
                	    {

                	    		if(retval.list.size() >= 1){
                	    			inCallStep = false;
                	    		}
                	    		if(retval.list.size() == 0){
                	    			retval.firstParam = builder.addParameterLabel();
                	    		}
                	    		if(builder.getCurrentMethod() != null){startLocation = builder.getCurrentMethod().getSteps().size();}
                	    	

                	    pushFollow(FOLLOW_expression_in_function_expression_list2488);
                	    e=expression();

                	    state._fsp--;



                	    		if(builder.getCurrentMethod() != null && builder.getCurrentMethod().getSteps().size() != 0 && startLocation != builder.getCurrentMethod().getSteps().size() -1){
                	    				builder.getCurrentMethod().getSteps().get(startLocation).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
                	    				(e!=null?e.step:null).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
                	    		}
                	    		retval.list.add(e);
                	    	

                	    }
                	    break;

                	default :
                	    break loop64;
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2261:1: formal_parameter returns [TypeDescriptor type, String name] : ^( FPARAM ad= assignment_declaration ID ) ;
    public final QuorumTreeWalker.formal_parameter_return formal_parameter() throws RecognitionException {
        QuorumTreeWalker.formal_parameter_return retval = new QuorumTreeWalker.formal_parameter_return();
        retval.start = input.LT(1);


        CommonTree ID50=null;
        QuorumTreeWalker.assignment_declaration_return ad =null;


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2262:2: ( ^( FPARAM ad= assignment_declaration ID ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/QuorumTreeWalker.g:2262:6: ^( FPARAM ad= assignment_declaration ID )
            {
            match(input,FPARAM,FOLLOW_FPARAM_in_formal_parameter2517); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_assignment_declaration_in_formal_parameter2521);
            ad=assignment_declaration();

            state._fsp--;


            ID50=(CommonTree)match(input,ID,FOLLOW_ID_in_formal_parameter2523); 

            match(input, Token.UP, null); 


            	
            		retval.type = (ad!=null?ad.myType:null);
            		retval.name = (ID50!=null?ID50.getText():null);
            	

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
    protected DFA52 dfa52 = new DFA52(this);
    static final String DFA4_eotS =
        "\24\uffff";
    static final String DFA4_eofS =
        "\24\uffff";
    static final String DFA4_minS =
        "\1\4\2\110\1\uffff\2\2\2\46\2\3\1\46\1\4\1\46\1\4\1\3\2\uffff\1"+
        "\3\2\uffff";
    static final String DFA4_maxS =
        "\1\135\2\110\1\uffff\2\2\2\46\2\103\1\46\1\135\1\46\1\135\1\103"+
        "\2\uffff\1\103\2\uffff";
    static final String DFA4_acceptS =
        "\3\uffff\1\5\13\uffff\1\1\1\3\1\uffff\1\2\1\4";
    static final String DFA4_specialS =
        "\24\uffff}>";
    static final String[] DFA4_transitionS = {
            "\2\3\2\uffff\1\3\1\uffff\1\3\1\uffff\2\3\3\uffff\1\3\24\uffff"+
            "\2\3\3\uffff\1\3\4\uffff\1\3\3\uffff\1\3\5\uffff\1\3\1\uffff"+
            "\1\3\3\uffff\1\1\1\3\3\uffff\4\3\4\uffff\2\3\4\uffff\4\3\2\uffff"+
            "\1\3\3\uffff\1\2",
            "\1\4",
            "\1\5",
            "",
            "\1\6",
            "\1\7",
            "\1\10",
            "\1\11",
            "\1\13\77\uffff\1\12",
            "\1\15\77\uffff\1\14",
            "\1\16",
            "\2\20\2\uffff\1\20\1\uffff\1\20\1\uffff\2\20\3\uffff\1\20\24"+
            "\uffff\2\20\3\uffff\1\20\4\uffff\1\20\3\uffff\1\20\5\uffff\1"+
            "\20\1\uffff\1\20\4\uffff\1\20\3\uffff\4\20\4\uffff\2\20\4\uffff"+
            "\4\20\2\uffff\1\20\3\uffff\1\17",
            "\1\21",
            "\2\23\2\uffff\1\23\1\uffff\1\23\1\uffff\2\23\3\uffff\1\23\24"+
            "\uffff\2\23\3\uffff\1\23\4\uffff\1\23\3\uffff\1\23\5\uffff\1"+
            "\23\1\uffff\1\23\3\uffff\1\22\1\23\3\uffff\4\23\4\uffff\2\23"+
            "\4\uffff\4\23\2\uffff\1\23\3\uffff\1\2",
            "\1\13\77\uffff\1\12",
            "",
            "",
            "\1\15\77\uffff\1\14",
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
            return "64:9: ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ |)";
        }
    }
    static final String DFA52_eotS =
        "\12\uffff";
    static final String DFA52_eofS =
        "\12\uffff";
    static final String DFA52_minS =
        "\1\12\1\uffff\1\2\1\uffff\1\46\1\3\1\46\1\16\1\3\1\uffff";
    static final String DFA52_maxS =
        "\1\131\1\uffff\1\2\1\uffff\1\46\1\103\2\46\1\103\1\uffff";
    static final String DFA52_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\5\uffff\1\2";
    static final String DFA52_specialS =
        "\12\uffff}>";
    static final String[] DFA52_transitionS = {
            "\1\3\6\uffff\1\3\24\uffff\1\1\4\uffff\1\3\4\uffff\1\1\11\uffff"+
            "\1\3\6\uffff\1\1\4\uffff\2\3\1\2\20\uffff\1\3",
            "",
            "\1\4",
            "",
            "\1\5",
            "\1\7\77\uffff\1\6",
            "\1\10",
            "\1\11\24\uffff\1\3\2\uffff\1\3",
            "\1\7\77\uffff\1\6",
            ""
    };

    static final short[] DFA52_eot = DFA.unpackEncodedString(DFA52_eotS);
    static final short[] DFA52_eof = DFA.unpackEncodedString(DFA52_eofS);
    static final char[] DFA52_min = DFA.unpackEncodedStringToUnsignedChars(DFA52_minS);
    static final char[] DFA52_max = DFA.unpackEncodedStringToUnsignedChars(DFA52_maxS);
    static final short[] DFA52_accept = DFA.unpackEncodedString(DFA52_acceptS);
    static final short[] DFA52_special = DFA.unpackEncodedString(DFA52_specialS);
    static final short[][] DFA52_transition;

    static {
        int numStates = DFA52_transitionS.length;
        DFA52_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA52_transition[i] = DFA.unpackEncodedString(DFA52_transitionS[i]);
        }
    }

    class DFA52 extends DFA {

        public DFA52(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 52;
            this.eot = DFA52_eot;
            this.eof = DFA52_eof;
            this.min = DFA52_min;
            this.max = DFA52_max;
            this.accept = DFA52_accept;
            this.special = DFA52_special;
            this.transition = DFA52_transition;
        }
        public String getDescription() {
            return "1081:1: assignment_statement : ( (sel= selector COLON )? ID rhs= assign_right_hand_side |obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side | (modifier= access_modifier )? ( CONSTANT )? type= assignment_declaration name= ID (rhs= assign_right_hand_side )? );";
        }
    }
 

    public static final BitSet FOLLOW_package_rule_in_start51 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_reference_in_start53 = new BitSet(new long[]{0x141108C000023530L,0x00000000227861E2L});
    public static final BitSet FOLLOW_reference_in_start59 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000001L});
    public static final BitSet FOLLOW_package_rule_in_start62 = new BitSet(new long[]{0x141108C000023530L,0x00000000027861E2L});
    public static final BitSet FOLLOW_package_rule_in_start67 = new BitSet(new long[]{0x141108C000023530L,0x00000000027861E2L});
    public static final BitSet FOLLOW_reference_in_start72 = new BitSet(new long[]{0x141108C000023530L,0x00000000227861E2L});
    public static final BitSet FOLLOW_class_declaration_in_start81 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_start84 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PACKAGE_NAME_in_package_rule96 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_qualified_name_in_package_rule100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_USE_in_reference113 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_qualified_name_in_reference115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_in_class_declaration131 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_class_declaration133 = new BitSet(new long[]{0x1411094804020510L,0x00000000020001C2L});
    public static final BitSet FOLLOW_generic_declaration_in_class_declaration139 = new BitSet(new long[]{0x1411094004020510L,0x00000000020001C2L});
    public static final BitSet FOLLOW_inherit_stmnts_in_class_declaration146 = new BitSet(new long[]{0x1411084004020510L,0x00000000020001C2L});
    public static final BitSet FOLLOW_class_stmnts_in_class_declaration153 = new BitSet(new long[]{0x1411084004020510L,0x00000000020001C2L});
    public static final BitSet FOLLOW_END_in_class_declaration156 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_no_class_stmnts_in_class_declaration169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_no_class_stmnts189 = new BitSet(new long[]{0x040108C000021422L,0x00000000027861E2L});
    public static final BitSet FOLLOW_access_modifier_in_no_class_stmnts203 = new BitSet(new long[]{0x1010000000000110L});
    public static final BitSet FOLLOW_method_declaration_in_no_class_stmnts211 = new BitSet(new long[]{0x1010000000000112L,0x00000000000000C0L});
    public static final BitSet FOLLOW_INHERITS_in_inherit_stmnts225 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_qualified_name_in_inherit_stmnts233 = new BitSet(new long[]{0x0000000800000008L,0x0000000000000100L});
    public static final BitSet FOLLOW_generic_statement_in_inherit_stmnts239 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000100L});
    public static final BitSet FOLLOW_PUBLIC_in_access_modifier257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIVATE_in_access_modifier265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_statement_in_class_stmnts279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_access_modifier_in_class_stmnts288 = new BitSet(new long[]{0x1010000000000110L});
    public static final BitSet FOLLOW_method_declaration_in_class_stmnts296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_method_declaration314 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_method_declaration320 = new BitSet(new long[]{0x0000000040000000L,0x0000000000808000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration325 = new BitSet(new long[]{0x0000000040000000L,0x0000000000808000L});
    public static final BitSet FOLLOW_RETURNS_in_method_declaration338 = new BitSet(new long[]{0x0400080000000400L,0x0000000002000100L});
    public static final BitSet FOLLOW_assignment_declaration_in_method_declaration342 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_block_in_method_declaration348 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_END_in_method_declaration351 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BLUEPRINT_in_method_declaration366 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ACTION_in_method_declaration368 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_method_declaration375 = new BitSet(new long[]{0x0000000040000008L,0x0000000000008000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration380 = new BitSet(new long[]{0x0000000040000008L,0x0000000000008000L});
    public static final BitSet FOLLOW_RETURNS_in_method_declaration389 = new BitSet(new long[]{0x0400080000000400L,0x0000000002000100L});
    public static final BitSet FOLLOW_assignment_declaration_in_method_declaration391 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NATIVE_in_method_declaration400 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ACTION_in_method_declaration402 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_method_declaration409 = new BitSet(new long[]{0x0000000040000008L,0x0000000000008000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration414 = new BitSet(new long[]{0x0000000040000008L,0x0000000000008000L});
    public static final BitSet FOLLOW_RETURNS_in_method_declaration423 = new BitSet(new long[]{0x0400080000000400L,0x0000000002000100L});
    public static final BitSet FOLLOW_assignment_declaration_in_method_declaration425 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ON_CREATE_in_method_declaration434 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_method_declaration441 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_END_in_method_declaration444 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_QUALIFIED_NAME_in_qualified_name467 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_qualified_name472 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000008L});
    public static final BitSet FOLLOW_PERIOD_in_qualified_name475 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_qualified_name479 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000008L});
    public static final BitSet FOLLOW_STATEMENT_LIST_in_block498 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_block505 = new BitSet(new long[]{0x040108C000021428L,0x00000000027861E2L});
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
    public static final BitSet FOLLOW_qualified_name_in_solo_method_call591 = new BitSet(new long[]{0x0000100000004000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call594 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_solo_method_call596 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call600 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009011E10L});
    public static final BitSet FOLLOW_expression_in_solo_method_call611 = new BitSet(new long[]{0x0000000000008000L,0x0000000000010000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call621 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009001E10L});
    public static final BitSet FOLLOW_expression_in_solo_method_call629 = new BitSet(new long[]{0x0000000000008000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call643 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SOLO_FUNCTION_CALL_PARENT_in_solo_method_call655 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_PARENT_in_solo_method_call662 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call664 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_qualified_name_in_solo_method_call666 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call668 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_solo_method_call670 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call672 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009011E10L});
    public static final BitSet FOLLOW_expression_in_solo_method_call683 = new BitSet(new long[]{0x0000000000008000L,0x0000000000010000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call690 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009001E10L});
    public static final BitSet FOLLOW_expression_in_solo_method_call700 = new BitSet(new long[]{0x0000000000008000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call710 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SOLO_FUNCTION_CALL_THIS_in_solo_method_call721 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ME_in_solo_method_call728 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call730 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_qualified_name_in_solo_method_call732 = new BitSet(new long[]{0x0000100000004000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call735 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_solo_method_call737 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call741 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009011E10L});
    public static final BitSet FOLLOW_expression_in_solo_method_call752 = new BitSet(new long[]{0x0000000000008000L,0x0000000000010000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call759 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009001E10L});
    public static final BitSet FOLLOW_expression_in_solo_method_call769 = new BitSet(new long[]{0x0000000000008000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call779 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ALERT_in_alert_statement799 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_alert_statement807 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_root_expression_in_alert_statement811 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_alert_statement819 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CHECK_in_check_statement851 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_block_in_check_statement857 = new BitSet(new long[]{0x0000000000100040L});
    public static final BitSet FOLLOW_DETECT_in_check_statement879 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_detect_parameter_in_check_statement906 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_block_in_check_statement930 = new BitSet(new long[]{0x0000000004100040L});
    public static final BitSet FOLLOW_ALWAYS_in_check_statement963 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_block_in_check_statement979 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ALWAYS_in_check_statement1011 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_block_in_check_statement1034 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_END_in_check_statement1043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_detect_parameter1072 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_OF_TYPE_in_detect_parameter1077 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_qualified_name_in_detect_parameter1086 = new BitSet(new long[]{0x4000000000000008L});
    public static final BitSet FOLLOW_OR_in_detect_parameter1093 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_qualified_name_in_detect_parameter1097 = new BitSet(new long[]{0x4000000000000008L});
    public static final BitSet FOLLOW_PRINT_in_print_statement1124 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_root_expression_in_print_statement1126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAY_in_speak_statement1142 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_root_expression_in_speak_statement1144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_return_statement1158 = new BitSet(new long[]{0x0100000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_root_expression_in_return_statement1161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOW_in_return_statement1169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GENERIC_in_generic_declaration1187 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_LESS_in_generic_declaration1189 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_generic_declaration1193 = new BitSet(new long[]{0x0000001000008000L});
    public static final BitSet FOLLOW_COMMA_in_generic_declaration1196 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_generic_declaration1200 = new BitSet(new long[]{0x0000001000008000L});
    public static final BitSet FOLLOW_GREATER_in_generic_declaration1204 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GENERIC_in_generic_statement1222 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_LESS_in_generic_statement1224 = new BitSet(new long[]{0x0400080000000400L,0x0000000002000100L});
    public static final BitSet FOLLOW_assignment_declaration_in_generic_statement1233 = new BitSet(new long[]{0x0000001000008000L});
    public static final BitSet FOLLOW_COMMA_in_generic_statement1241 = new BitSet(new long[]{0x0400080000000400L,0x0000000002000100L});
    public static final BitSet FOLLOW_assignment_declaration_in_generic_statement1245 = new BitSet(new long[]{0x0000001000008000L});
    public static final BitSet FOLLOW_GREATER_in_generic_statement1261 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_qualified_name_in_class_type1286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_assignment_declaration1308 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_generic_statement_in_assignment_declaration1312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_KEYWORD_in_assignment_declaration1321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_KEYWORD_in_assignment_declaration1329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TEXT_in_assignment_declaration1337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_KEYWORD_in_assignment_declaration1345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_assignment_statement1361 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1363 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_assignment_statement1367 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_assign_right_hand_side_in_assignment_statement1371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_assignment_statement1381 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1384 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_PARENT_in_assignment_statement1386 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1388 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_qualified_name_in_assignment_statement1392 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1396 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_assignment_statement1398 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_assign_right_hand_side_in_assignment_statement1402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_access_modifier_in_assignment_statement1414 = new BitSet(new long[]{0x0400080000020400L,0x0000000002000100L});
    public static final BitSet FOLLOW_CONSTANT_in_assignment_statement1417 = new BitSet(new long[]{0x0400080000000400L,0x0000000002000100L});
    public static final BitSet FOLLOW_assignment_declaration_in_assignment_statement1424 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_assignment_statement1430 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_assign_right_hand_side_in_assignment_statement1434 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALITY_in_assign_right_hand_side1455 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_root_expression_in_assign_right_hand_side1457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_if_statement1483 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_root_expression_in_if_statement1496 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_block_in_if_statement1508 = new BitSet(new long[]{0x0000000005800000L});
    public static final BitSet FOLLOW_ELSE_IF_in_if_statement1525 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_root_expression_in_if_statement1538 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_block_in_if_statement1548 = new BitSet(new long[]{0x0000000005800000L});
    public static final BitSet FOLLOW_ELSE_in_if_statement1582 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_block_in_if_statement1592 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_END_in_if_statement1601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REPEAT_in_loop_statement1624 = new BitSet(new long[]{0x0000000000000000L,0x0000000050040000L});
    public static final BitSet FOLLOW_root_expression_in_loop_statement1697 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_TIMES_in_loop_statement1699 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_WHILE_in_loop_statement1710 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_UNTIL_in_loop_statement1716 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_root_expression_in_loop_statement1731 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_block_in_loop_statement1742 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_END_in_loop_statement1750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENT_in_selector1789 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_qualified_name_in_selector1791 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ME_in_selector1800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ROOT_EXPRESSION_in_root_expression1826 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_root_expression1828 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNARY_NOT_in_expression1850 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NOT_in_expression1852 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009001E10L});
    public static final BitSet FOLLOW_expression_in_expression1858 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EQUALITY_in_expression1870 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1876 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009001E10L});
    public static final BitSet FOLLOW_expression_in_expression1882 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NOTEQUALS_in_expression1892 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1898 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009001E10L});
    public static final BitSet FOLLOW_expression_in_expression1904 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GREATER_in_expression1914 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1920 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009001E10L});
    public static final BitSet FOLLOW_expression_in_expression1926 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GREATER_EQUAL_in_expression1936 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1942 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009001E10L});
    public static final BitSet FOLLOW_expression_in_expression1948 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INHERITS_in_expression1958 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1962 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_class_type_in_expression1966 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LESS_in_expression1976 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression1982 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009001E10L});
    public static final BitSet FOLLOW_expression_in_expression1988 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LESS_EQUAL_in_expression1998 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2004 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009001E10L});
    public static final BitSet FOLLOW_expression_in_expression2010 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OR_in_expression2020 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2026 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009001E10L});
    public static final BitSet FOLLOW_expression_in_expression2032 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_AND_in_expression2042 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2048 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009001E10L});
    public static final BitSet FOLLOW_expression_in_expression2054 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PLUS_in_expression2064 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2070 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009001E10L});
    public static final BitSet FOLLOW_expression_in_expression2076 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MINUS_in_expression2086 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2092 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009001E10L});
    public static final BitSet FOLLOW_expression_in_expression2098 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MULTIPLY_in_expression2108 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2114 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009001E10L});
    public static final BitSet FOLLOW_expression_in_expression2120 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DIVIDE_in_expression2130 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2136 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009001E10L});
    public static final BitSet FOLLOW_expression_in_expression2142 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MODULO_in_expression2152 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression2158 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009001E10L});
    public static final BitSet FOLLOW_expression_in_expression2164 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNCTION_CALL_in_expression2174 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_qualified_name_in_expression2176 = new BitSet(new long[]{0x0000100000004000L});
    public static final BitSet FOLLOW_COLON_in_expression2178 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_expression2180 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_expression2184 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_function_expression_list_in_expression2196 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_expression2198 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_QUALIFIED_SOLO_EXPRESSION_in_expression2209 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_qualified_name_in_expression2213 = new BitSet(new long[]{0x0000000000004008L});
    public static final BitSet FOLLOW_COLON_in_expression2216 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_expression2218 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_QUALIFIED_SOLO_PARENT_EXPRESSON_in_expression2230 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_qualified_name_in_expression2234 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_expression2236 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_PARENT_in_expression2238 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_expression2240 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_qualified_name_in_expression2244 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_expression2246 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_expression2248 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_QUALIFIED_SOLO_EXPRESSION_SELECTOR_in_expression2258 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_selector_in_expression2260 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_expression2262 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_qualified_name_in_expression2264 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNCTION_CALL_PARENT_in_expression2276 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_PARENT_in_expression2278 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_expression2280 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_qualified_name_in_expression2284 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_expression2286 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_expression2288 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_expression2290 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_function_expression_list_in_expression2301 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_expression2303 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNCTION_CALL_THIS_in_expression2313 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ME_in_expression2315 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_expression2317 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_qualified_name_in_expression2319 = new BitSet(new long[]{0x0000100000004000L});
    public static final BitSet FOLLOW_COLON_in_expression2322 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_expression2324 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_expression2328 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_function_expression_list_in_expression2339 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_expression2341 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BOOLEAN_in_expression2350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_expression2359 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DECIMAL_in_expression2363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_expression2372 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_INT_in_expression2376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_expression2384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTE_in_expression2392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_expression2400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ME_in_expression2408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INPUT_in_expression2416 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_expression2419 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009001E10L});
    public static final BitSet FOLLOW_expression_in_expression2425 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_expression2427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CAST_in_expression2435 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_expression2437 = new BitSet(new long[]{0x0400080000000400L,0x0000000002000100L});
    public static final BitSet FOLLOW_assignment_declaration_in_expression2441 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_expression2443 = new BitSet(new long[]{0x428FC73388280A80L,0x0000000009001E10L});
    public static final BitSet FOLLOW_expression_in_expression2448 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_expression2452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_EXPRESSION_LIST_in_function_expression_list2476 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_function_expression_list2488 = new BitSet(new long[]{0x428FC73388280A88L,0x0000000009001E10L});
    public static final BitSet FOLLOW_FPARAM_in_formal_parameter2517 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_assignment_declaration_in_formal_parameter2521 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_formal_parameter2523 = new BitSet(new long[]{0x0000000000000008L});

}