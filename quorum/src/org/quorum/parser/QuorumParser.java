// $ANTLR 3.4 /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g 2012-08-09 11:23:42



package org.quorum.parser;

import org.quorum.symbols.*;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import org.quorum.vm.interfaces.CompilerErrorManager;
import org.quorum.vm.interfaces.CompilerError;
import java.util.Iterator;
import java.util.Vector;
import org.antlr.runtime.tree.CommonTree;
import org.quorum.execution.ScopeSelector;
import org.quorum.vm.interfaces.ErrorType;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class QuorumParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ACTION", "ALERT", "ALWAYS", "AND", "BLUEPRINT", "BOOLEAN", "BOOLEAN_KEYWORD", "CAST", "CHECK", "CLASS", "COLON", "COMMA", "COMMENTS", "CONSTRUCTOR", "DECIMAL", "DETECT", "DIVIDE", "DOUBLE_QUOTE", "ELSE", "ELSE_IF", "ELSE_IF_STATEMENT", "END", "EQUALITY", "EXPRESSION_STATEMENT", "FINAL_ELSE", "FPARAM", "FUNCTION_CALL", "FUNCTION_CALL_PARENT", "FUNCTION_CALL_THIS", "FUNCTION_EXPRESSION_LIST", "GENERIC", "GREATER", "GREATER_EQUAL", "ID", "IF", "INHERITS", "INPUT", "INT", "INTEGER_KEYWORD", "LEFT_PAREN", "LEFT_SQR_BRACE", "LESS", "LESS_EQUAL", "ME", "MINUS", "MODULO", "MULTIPLY", "NATIVE", "NEWLINE", "NOT", "NOTEQUALS", "NOW", "NULL", "NUMBER_KEYWORD", "OF_TYPE", "ON_CREATE", "ON_DESTROY", "OR", "OVER", "PACKAGE_NAME", "PARENT", "PAREN_WRAPPED_EXPRESSION", "PERIOD", "PLUS", "PRINT", "PRIVATE", "PUBLIC", "QUALIFIED_NAME", "QUALIFIED_SOLO_EXPRESSION", "QUALIFIED_SOLO_EXPRESSION_SELECTOR", "QUALIFIED_SOLO_PARENT_EXPRESSON", "REPEAT", "RETURN", "RETURNS", "RIGHT_PAREN", "RIGHT_SQR_BRACE", "ROOT_EXPRESSION", "SAY", "SOLO_FUNCTION_CALL", "SOLO_FUNCTION_CALL_PARENT", "SOLO_FUNCTION_CALL_THIS", "STATEMENT_LIST", "STRING", "TEXT", "TIMES", "UNARY_NOT", "UNTIL", "USE", "WHILE", "WS"
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

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public QuorumParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public QuorumParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return QuorumParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g"; }


    	public static final int HIDDEN_DOCUMENTATION = 100;
    	QuorumVirtualMachine vm;
    	AccessModifierEnum accessModifier;
    	SymbolTable symbol;
    	QualifiedNameDescriptor thisPackage;
    	Vector<QualifiedNameDescriptor> uses = new Vector<QualifiedNameDescriptor>();
    	
    	String fileName;
    	boolean classWithNoName = false;
    	boolean isInClassAssignmentStatementScope = false;
    	boolean isInConstructorScope = false;
    	
    	public void setQuorumVirtualMachine(QuorumVirtualMachine m) {
    		vm = m;
    		symbol = vm.getSymbolTable();
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

        @Override
        public String getErrorMessage(RecognitionException re, String[] tokenNames) {
            String message = re.getMessage();
            CompilerError error = new CompilerError();
            
            if (re instanceof UnwantedTokenException) {
                UnwantedTokenException ute = (UnwantedTokenException) re;
                String tokenName = "<unknown>";
                if (ute.expecting == Token.EOF) {
                    tokenName = "EOF";
                    message = "extraneous input " + getTokenErrorDisplay(ute.getUnexpectedToken());
                    error.setErrorType(ErrorType.EOF);
                } else {
                    tokenName = tokenNames[ute.expecting];
                    message = "extraneous input " + getTokenErrorDisplay(ute.getUnexpectedToken());
                    if(tokenName.equals(")")){
                        error.setErrorType(ErrorType.EXPECTED_CLOSURE);
                        message = "For every '(' there must be a matching ')'. Extraneous input " + getTokenErrorDisplay(ute.getUnexpectedToken());
                    }else if(ute.getUnexpectedToken().getText().equals("end")){
                        error.setErrorType(ErrorType.EOF);
                    }else{
                        error.setErrorType(ErrorType.OTHER);
                    }
                }      
            } else if (re instanceof MissingTokenException) {
                MissingTokenException mte = (MissingTokenException) re;
                String tokenName = "<unknown>";
                if (mte.expecting == Token.EOF) {
                    tokenName = "EOF";
                    if(getTokenErrorDisplay(re.token).equals("'end'")){
                        message = " The end of the file was reached before all the code was evaluated. There may be an extra " + getTokenErrorDisplay(re.token);
                	    error.setErrorType(ErrorType.EOF);
                	}else{
                	    message = "Missing or invalid statement at " + getTokenErrorDisplay(re.token);
                	    error.setErrorType(ErrorType.OTHER);
                	}
                }else {
                    tokenName = tokenNames[mte.expecting];
                    message = "missing " + tokenName + " at " + getTokenErrorDisplay(re.token);
                    if(tokenName.equals("THEN")){
                        message = "An 'if' or 'else' statement is missing a 'then' at line " + (mte.line - 1);
                        error.setErrorType(ErrorType.MISSING_THEN);
                    }else if(tokenName.equals("ID")){
                        message = "An <identifier> is missing. Please give the item you are declaring a name.";
                        error.setErrorType(ErrorType.IDENTIFIER_EXPECTED);
                    }else if(tokenName.equals("RIGHT_PAREN") || tokenName.equals("LEFT_PAREN")){
                        message = "For every '(' there must be a matching ')'.";
                        error.setErrorType(ErrorType.EXPECTED_CLOSURE);
                    }else{
                        error.setErrorType(ErrorType.OTHER);
                    }
                }
                    
            } else if (re instanceof MismatchedTokenException) {
                MismatchedTokenException mte = (MismatchedTokenException) re;
                String tokenName = "<unknown>";
                if (mte.expecting == Token.EOF) {
                    tokenName = "EOF";
                    message = "mismatched input " + getTokenErrorDisplay(re.token)
                                + " expecting " + tokenName;
                } else {
                    tokenName = tokenNames[mte.expecting];
                    message = "mismatched input " + getTokenErrorDisplay(re.token)
                               + " expecting " + tokenName;
                    if(tokenName.equals("IF")){
                        message = "An 'elseif' statement is missing the 'if' at line " + mte.line;
                        error.setErrorType(ErrorType.MISSING_IF);
                    }else if(tokenName.equals("END")){
                        message = "A loop, conditional, class, or action statement is missing an 'end'.";
                        error.setErrorType(ErrorType.EXPECTED_CLOSURE);
                    }else{
                        error.setErrorType(ErrorType.OTHER);
                    }
                }
            } else if (re instanceof MismatchedTreeNodeException) {
                MismatchedTreeNodeException mtne = (MismatchedTreeNodeException) re;
                String tokenName = "<unknown>";
                if (mtne.expecting == Token.EOF) {
                    tokenName = "EOF";
                } else {
                    tokenName = tokenNames[mtne.expecting];
                }
                message = "mismatched tree node: " + mtne.node
                        + " expecting " + tokenName;
                error.setErrorType(ErrorType.OTHER);
            } else if (re instanceof NoViableAltException) {
                //NoViableAltException nvae = (NoViableAltException)re;
                // for development, can add "decision=<<"+nvae.grammarDecisionDescription+">>"
                // and "(decision="+nvae.decisionNumber+") and
                // "state "+nvae.stateNumber
                message = "Incomplete or invalid statement at " + getTokenErrorDisplay(re.token);
                error.setErrorType(ErrorType.OTHER);
            } else if (re instanceof EarlyExitException) {
                //EarlyExitException eee = (EarlyExitException)e;
                // for development, can add "(decision="+eee.decisionNumber+")"
                message = "required (...)+ loop did not match anything at input "
                        + getTokenErrorDisplay(re.token);
                error.setErrorType(ErrorType.OTHER);
            } else if (re instanceof MismatchedSetException) {
                MismatchedSetException mse = (MismatchedSetException) re;
                message = "mismatched input " + getTokenErrorDisplay(re.token)
                        + " expecting set " + mse.expecting;
                error.setErrorType(ErrorType.OTHER);
            } else if (re instanceof MismatchedNotSetException) {
                MismatchedNotSetException mse = (MismatchedNotSetException) re;
                message = "mismatched input " + getTokenErrorDisplay(re.token)
                        + " expecting set " + mse.expecting;
                error.setErrorType(ErrorType.OTHER);
            } else if (re instanceof FailedPredicateException) {
                FailedPredicateException fpe = (FailedPredicateException) re;
                message = "rule " + fpe.ruleName + " failed predicate: {"
                        + fpe.predicateText + "}?";
                error.setErrorType(ErrorType.OTHER);
            }

            if (vm != null) {
                error.setLineNumber(re.line);
                error.setColumn(re.charPositionInLine);
                error.setError(message);
                error.setFile(fileName);
                CompilerErrorManager ces = vm.getCompilerErrors();
                ces.addError(error);
            }
            return message;
        }
            
            public Documentation getDocumentationFromRecentToken() {
            	String documentationString = "";
    		Documentation doc = null;
    		TokenStream stream = this.getTokenStream();
    		int actualIndex = input.index();
    		int current = actualIndex - 1;
    		while(current >= 0) {
    		    Token toke = stream.get(current);
    		    if(toke.getChannel() == this.HIDDEN_DOCUMENTATION) {
    		        documentationString = toke.getText();
    		        doc = new Documentation();
    		        doc.parseDocumentationString(documentationString);
    		        current = -1; //we've found the documentation, so bail
    		    }
    		    else if(toke.getChannel() == this.DEFAULT_TOKEN_CHANNEL
    		    	    && toke.getType() != PRIVATE 
                                && toke.getType() != PUBLIC 
                                && toke.getType() != BLUEPRINT 
                                && toke.getType() != NATIVE) {
    		        current = -1; //bail out, there's no documentation
    		    }
    		    current = current - 1;
    		}
    		return doc;
            }


    public static class start_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "start"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:271:1: start : ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ |) class_declaration EOF ;
    public final QuorumParser.start_return start() throws RecognitionException {
        QuorumParser.start_return retval = new QuorumParser.start_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token EOF8=null;
        QuorumParser.package_rule_return package_rule1 =null;

        QuorumParser.reference_return reference2 =null;

        QuorumParser.reference_return reference3 =null;

        QuorumParser.package_rule_return package_rule4 =null;

        QuorumParser.package_rule_return package_rule5 =null;

        QuorumParser.reference_return reference6 =null;

        QuorumParser.class_declaration_return class_declaration7 =null;


        CommonTree EOF8_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:271:7: ( ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ |) class_declaration EOF )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:272:3: ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ |) class_declaration EOF
            {
            root_0 = (CommonTree)adaptor.nil();


            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:272:3: ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ |)
            int alt4=5;
            alt4 = dfa4.predict(input);
            switch (alt4) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:272:4: package_rule ( reference )+
                    {
                    pushFollow(FOLLOW_package_rule_in_start150);
                    package_rule1=package_rule();

                    state._fsp--;

                    adaptor.addChild(root_0, package_rule1.getTree());

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:272:17: ( reference )+
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
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:272:17: reference
                    	    {
                    	    pushFollow(FOLLOW_reference_in_start152);
                    	    reference2=reference();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, reference2.getTree());

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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:273:4: ( reference )+ package_rule
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:273:4: ( reference )+
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
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:273:4: reference
                    	    {
                    	    pushFollow(FOLLOW_reference_in_start159);
                    	    reference3=reference();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, reference3.getTree());

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


                    pushFollow(FOLLOW_package_rule_in_start162);
                    package_rule4=package_rule();

                    state._fsp--;

                    adaptor.addChild(root_0, package_rule4.getTree());

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:274:4: package_rule
                    {
                    pushFollow(FOLLOW_package_rule_in_start167);
                    package_rule5=package_rule();

                    state._fsp--;

                    adaptor.addChild(root_0, package_rule5.getTree());

                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:275:4: ( reference )+
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:275:4: ( reference )+
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
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:275:4: reference
                    	    {
                    	    pushFollow(FOLLOW_reference_in_start172);
                    	    reference6=reference();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, reference6.getTree());

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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:276:4: 
                    {
                    }
                    break;

            }


            pushFollow(FOLLOW_class_declaration_in_start181);
            class_declaration7=class_declaration();

            state._fsp--;

            adaptor.addChild(root_0, class_declaration7.getTree());

            EOF8=(Token)match(input,EOF,FOLLOW_EOF_in_start184); 
            EOF8_tree = 
            (CommonTree)adaptor.create(EOF8)
            ;
            adaptor.addChild(root_0, EOF8_tree);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "start"


    public static class package_rule_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "package_rule"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:280:1: package_rule : PACKAGE_NAME qn= qualified_name ;
    public final QuorumParser.package_rule_return package_rule() throws RecognitionException {
        QuorumParser.package_rule_return retval = new QuorumParser.package_rule_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token PACKAGE_NAME9=null;
        QuorumParser.qualified_name_return qn =null;


        CommonTree PACKAGE_NAME9_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:280:14: ( PACKAGE_NAME qn= qualified_name )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:280:16: PACKAGE_NAME qn= qualified_name
            {
            root_0 = (CommonTree)adaptor.nil();


            PACKAGE_NAME9=(Token)match(input,PACKAGE_NAME,FOLLOW_PACKAGE_NAME_in_package_rule195); 
            PACKAGE_NAME9_tree = 
            (CommonTree)adaptor.create(PACKAGE_NAME9)
            ;
            adaptor.addChild(root_0, PACKAGE_NAME9_tree);


            pushFollow(FOLLOW_qualified_name_in_package_rule199);
            qn=qualified_name();

            state._fsp--;

            adaptor.addChild(root_0, qn.getTree());


            		thisPackage = (qn!=null?qn.type:null);
            	

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "package_rule"


    public static class reference_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "reference"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:286:1: reference : USE qn= qualified_name ;
    public final QuorumParser.reference_return reference() throws RecognitionException {
        QuorumParser.reference_return retval = new QuorumParser.reference_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token USE10=null;
        QuorumParser.qualified_name_return qn =null;


        CommonTree USE10_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:287:2: ( USE qn= qualified_name )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:288:2: USE qn= qualified_name
            {
            root_0 = (CommonTree)adaptor.nil();


            USE10=(Token)match(input,USE,FOLLOW_USE_in_reference217); 
            USE10_tree = 
            (CommonTree)adaptor.create(USE10)
            ;
            adaptor.addChild(root_0, USE10_tree);


            pushFollow(FOLLOW_qualified_name_in_reference223);
            qn=qualified_name();

            state._fsp--;

            adaptor.addChild(root_0, qn.getTree());


            		uses.add((qn!=null?qn.type:null));
            	

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "reference"


    protected static class class_declaration_scope {
        ClassDescriptor current_class;
    }
    protected Stack class_declaration_stack = new Stack();


    public static class class_declaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "class_declaration"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:295:1: class_declaration : ( ( CLASS ID (genericList= generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END ) -> ^( CLASS ID ( generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END ) | no_class_stmnts );
    public final QuorumParser.class_declaration_return class_declaration() throws RecognitionException {
        class_declaration_stack.push(new class_declaration_scope());
        QuorumParser.class_declaration_return retval = new QuorumParser.class_declaration_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token CLASS11=null;
        Token ID12=null;
        Token END15=null;
        QuorumParser.generic_declaration_return genericList =null;

        QuorumParser.inherit_stmnts_return inherit_stmnts13 =null;

        QuorumParser.class_stmnts_return class_stmnts14 =null;

        QuorumParser.no_class_stmnts_return no_class_stmnts16 =null;


        CommonTree CLASS11_tree=null;
        CommonTree ID12_tree=null;
        CommonTree END15_tree=null;
        RewriteRuleTokenStream stream_CLASS=new RewriteRuleTokenStream(adaptor,"token CLASS");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_END=new RewriteRuleTokenStream(adaptor,"token END");
        RewriteRuleSubtreeStream stream_generic_declaration=new RewriteRuleSubtreeStream(adaptor,"rule generic_declaration");
        RewriteRuleSubtreeStream stream_inherit_stmnts=new RewriteRuleSubtreeStream(adaptor,"rule inherit_stmnts");
        RewriteRuleSubtreeStream stream_class_stmnts=new RewriteRuleSubtreeStream(adaptor,"rule class_stmnts");

        	Documentation classDocumentation = getDocumentationFromRecentToken();

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:301:5: ( ( CLASS ID (genericList= generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END ) -> ^( CLASS ID ( generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END ) | no_class_stmnts )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==CLASS) ) {
                alt8=1;
            }
            else if ( ((LA8_0 >= ACTION && LA8_0 <= ALERT)||LA8_0==BLUEPRINT||LA8_0==BOOLEAN_KEYWORD||LA8_0==CHECK||(LA8_0 >= ID && LA8_0 <= IF)||LA8_0==INTEGER_KEYWORD||LA8_0==ME||LA8_0==NATIVE||LA8_0==NUMBER_KEYWORD||LA8_0==ON_CREATE||LA8_0==PARENT||(LA8_0 >= PRINT && LA8_0 <= PUBLIC)||(LA8_0 >= REPEAT && LA8_0 <= RETURN)||LA8_0==SAY||LA8_0==TEXT) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }
            switch (alt8) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:302:2: ( CLASS ID (genericList= generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:302:2: ( CLASS ID (genericList= generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:303:2: CLASS ID (genericList= generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END
                    {
                    CLASS11=(Token)match(input,CLASS,FOLLOW_CLASS_in_class_declaration255);  
                    stream_CLASS.add(CLASS11);


                    ID12=(Token)match(input,ID,FOLLOW_ID_in_class_declaration257);  
                    stream_ID.add(ID12);



                    		ClassDescriptor cd = new ClassDescriptor();
                    		cd.setDocumentation(classDocumentation);
                    		cd.addUsesDescriptors(uses);
                    		cd.setContainerErrorCheck(thisPackage);
                    		classWithNoName = false;		
                    		if((ID12!=null?ID12.getText():null) == null) {
                    			cd.setName(getGrammarFileNameNoExtension());
                    		}
                    		else {
                    			cd.setName((ID12!=null?ID12.getText():null));
                    		}
                    		cd.setLineBegin(CLASS11.getLine());
                    		cd.setColumnBegin(CLASS11.getCharPositionInLine());
                    		symbol.add(cd);
                    		((class_declaration_scope)class_declaration_stack.peek()).current_class = cd;
                    	

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:321:13: (genericList= generic_declaration )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==LESS) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:321:13: genericList= generic_declaration
                            {
                            pushFollow(FOLLOW_generic_declaration_in_class_declaration268);
                            genericList=generic_declaration();

                            state._fsp--;

                            stream_generic_declaration.add(genericList.getTree());

                            }
                            break;

                    }



                    		ArrayList<GenericDescriptor> gdList = (genericList!=null?genericList.genericTypeList:null);
                    		ClassDescriptor clazz = symbol.getCurrentClass();
                    		
                    		if(gdList != null){
                    			for(int i = 0; i < gdList.size(); i++){
                    				TypeDescriptor td = new TypeDescriptor();
                    				
                    				td.setName(TypeDescriptor.OBJECT);
                    				GenericDescriptor genericType = gdList.get(i);
                    				td.setTemplateName(genericType.getName());
                    				genericType.addBoundType(td);
                    				clazz.addTemplateVariables(genericType);
                    			}
                    		}
                    	

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:338:2: ( inherit_stmnts )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==INHERITS) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:338:2: inherit_stmnts
                            {
                            pushFollow(FOLLOW_inherit_stmnts_in_class_declaration275);
                            inherit_stmnts13=inherit_stmnts();

                            state._fsp--;

                            stream_inherit_stmnts.add(inherit_stmnts13.getTree());

                            }
                            break;

                    }


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:339:2: ( class_stmnts )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==ACTION||LA7_0==BLUEPRINT||LA7_0==BOOLEAN_KEYWORD||LA7_0==ID||LA7_0==INTEGER_KEYWORD||LA7_0==ME||LA7_0==NATIVE||LA7_0==NUMBER_KEYWORD||LA7_0==ON_CREATE||LA7_0==PARENT||(LA7_0 >= PRIVATE && LA7_0 <= PUBLIC)||LA7_0==TEXT) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:339:2: class_stmnts
                    	    {
                    	    pushFollow(FOLLOW_class_stmnts_in_class_declaration286);
                    	    class_stmnts14=class_stmnts();

                    	    state._fsp--;

                    	    stream_class_stmnts.add(class_stmnts14.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);


                    END15=(Token)match(input,END,FOLLOW_END_in_class_declaration290);  
                    stream_END.add(END15);



                    		ClassDescriptor currentClazz = symbol.getCurrentClass();
                    		currentClazz.checkClassVariableInitialization();
                    		((class_declaration_scope)class_declaration_stack.peek()).current_class.setLineEnd(END15.getLine());
                    		((class_declaration_scope)class_declaration_stack.peek()).current_class.setColumnEnd(END15.getCharPositionInLine());
                    	

                    }


                    // AST REWRITE
                    // elements: class_stmnts, generic_declaration, CLASS, inherit_stmnts, END, ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 347:4: -> ^( CLASS ID ( generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:347:7: ^( CLASS ID ( generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_CLASS.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:347:18: ( generic_declaration )?
                        if ( stream_generic_declaration.hasNext() ) {
                            adaptor.addChild(root_1, stream_generic_declaration.nextTree());

                        }
                        stream_generic_declaration.reset();

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:347:39: ( inherit_stmnts )?
                        if ( stream_inherit_stmnts.hasNext() ) {
                            adaptor.addChild(root_1, stream_inherit_stmnts.nextTree());

                        }
                        stream_inherit_stmnts.reset();

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:347:55: ( class_stmnts )*
                        while ( stream_class_stmnts.hasNext() ) {
                            adaptor.addChild(root_1, stream_class_stmnts.nextTree());

                        }
                        stream_class_stmnts.reset();

                        adaptor.addChild(root_1, 
                        stream_END.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:349:2: no_class_stmnts
                    {
                    root_0 = (CommonTree)adaptor.nil();



                    		ClassDescriptor cd = new ClassDescriptor();
                    		cd.addUsesDescriptors(uses);
                    		cd.setContainerErrorCheck(thisPackage);
                    		classWithNoName = true;
                    		cd.setName(getGrammarFileNameNoExtension());
                    		cd.setLineBegin(1);	//should it be the beginning of the file?
                    		cd.setColumnBegin(1);
                    		symbol.add(cd);
                    	

                    pushFollow(FOLLOW_no_class_stmnts_in_class_declaration324);
                    no_class_stmnts16=no_class_stmnts();

                    state._fsp--;

                    adaptor.addChild(root_0, no_class_stmnts16.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            class_declaration_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "class_declaration"


    protected static class no_class_stmnts_scope {
        MethodDescriptor fakeMain;
    }
    protected Stack no_class_stmnts_stack = new Stack();


    public static class no_class_stmnts_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "no_class_stmnts"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:361:1: no_class_stmnts : ( ( statement )+ | ( ( access_modifier )? method_declaration )+ );
    public final QuorumParser.no_class_stmnts_return no_class_stmnts() throws RecognitionException {
        no_class_stmnts_stack.push(new no_class_stmnts_scope());
        QuorumParser.no_class_stmnts_return retval = new QuorumParser.no_class_stmnts_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        QuorumParser.statement_return statement17 =null;

        QuorumParser.access_modifier_return access_modifier18 =null;

        QuorumParser.method_declaration_return method_declaration19 =null;



        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:365:2: ( ( statement )+ | ( ( access_modifier )? method_declaration )+ )
            int alt12=2;
            switch ( input.LA(1) ) {
            case ALERT:
            case BOOLEAN_KEYWORD:
            case CHECK:
            case ID:
            case IF:
            case INTEGER_KEYWORD:
            case ME:
            case NUMBER_KEYWORD:
            case PARENT:
            case PRINT:
            case REPEAT:
            case RETURN:
            case SAY:
            case TEXT:
                {
                alt12=1;
                }
                break;
            case PUBLIC:
                {
                int LA12_2 = input.LA(2);

                if ( (LA12_2==BOOLEAN_KEYWORD||LA12_2==ID||LA12_2==INTEGER_KEYWORD||LA12_2==NUMBER_KEYWORD||LA12_2==TEXT) ) {
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

                if ( (LA12_3==BOOLEAN_KEYWORD||LA12_3==ID||LA12_3==INTEGER_KEYWORD||LA12_3==NUMBER_KEYWORD||LA12_3==TEXT) ) {
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:366:2: ( statement )+
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    //create a fake method main
                    		((no_class_stmnts_scope)no_class_stmnts_stack.peek()).fakeMain = new MethodDescriptor();
                    		((no_class_stmnts_scope)no_class_stmnts_stack.peek()).fakeMain.setLineBegin(1);
                    		((no_class_stmnts_scope)no_class_stmnts_stack.peek()).fakeMain.setLineBegin(1);
                    		((no_class_stmnts_scope)no_class_stmnts_stack.peek()).fakeMain.setName("main");
                    		((no_class_stmnts_scope)no_class_stmnts_stack.peek()).fakeMain.setAccessModifier(AccessModifierEnum.PUBLIC);
                    		
                    		TypeDescriptor t = new TypeDescriptor();
                    		t.setName(TypeDescriptor.VOID);
                    		((no_class_stmnts_scope)no_class_stmnts_stack.peek()).fakeMain.setReturnType(t);
                    		
                    		symbol.add(((no_class_stmnts_scope)no_class_stmnts_stack.peek()).fakeMain);
                    	

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:379:2: ( statement )+
                    int cnt9=0;
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==ALERT||LA9_0==BOOLEAN_KEYWORD||LA9_0==CHECK||(LA9_0 >= ID && LA9_0 <= IF)||LA9_0==INTEGER_KEYWORD||LA9_0==ME||LA9_0==NUMBER_KEYWORD||LA9_0==PARENT||(LA9_0 >= PRINT && LA9_0 <= PUBLIC)||(LA9_0 >= REPEAT && LA9_0 <= RETURN)||LA9_0==SAY||LA9_0==TEXT) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:379:2: statement
                    	    {
                    	    pushFollow(FOLLOW_statement_in_no_class_stmnts343);
                    	    statement17=statement();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, statement17.getTree());

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


                    //end the fake method main
                    		symbol.popScope();
                    	

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:383:3: ( ( access_modifier )? method_declaration )+
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:383:3: ( ( access_modifier )? method_declaration )+
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
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:383:4: ( access_modifier )? method_declaration
                    	    {
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:383:4: ( access_modifier )?
                    	    int alt10=2;
                    	    int LA10_0 = input.LA(1);

                    	    if ( ((LA10_0 >= PRIVATE && LA10_0 <= PUBLIC)) ) {
                    	        alt10=1;
                    	    }
                    	    switch (alt10) {
                    	        case 1 :
                    	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:383:4: access_modifier
                    	            {
                    	            pushFollow(FOLLOW_access_modifier_in_no_class_stmnts352);
                    	            access_modifier18=access_modifier();

                    	            state._fsp--;

                    	            adaptor.addChild(root_0, access_modifier18.getTree());

                    	            }
                    	            break;

                    	    }


                    	    pushFollow(FOLLOW_method_declaration_in_no_class_stmnts355);
                    	    method_declaration19=method_declaration();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, method_declaration19.getTree());

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
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            no_class_stmnts_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "no_class_stmnts"


    public static class inherit_stmnts_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "inherit_stmnts"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:385:1: inherit_stmnts : INHERITS qn= qualified_name (genericList= generic_statement )? ( COMMA qn= qualified_name (genericList= generic_statement )? )* -> ^( INHERITS ( qualified_name ( generic_statement )? )+ ) ;
    public final QuorumParser.inherit_stmnts_return inherit_stmnts() throws RecognitionException {
        QuorumParser.inherit_stmnts_return retval = new QuorumParser.inherit_stmnts_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token INHERITS20=null;
        Token COMMA21=null;
        QuorumParser.qualified_name_return qn =null;

        QuorumParser.generic_statement_return genericList =null;


        CommonTree INHERITS20_tree=null;
        CommonTree COMMA21_tree=null;
        RewriteRuleTokenStream stream_INHERITS=new RewriteRuleTokenStream(adaptor,"token INHERITS");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_qualified_name=new RewriteRuleSubtreeStream(adaptor,"rule qualified_name");
        RewriteRuleSubtreeStream stream_generic_statement=new RewriteRuleSubtreeStream(adaptor,"rule generic_statement");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:386:2: ( INHERITS qn= qualified_name (genericList= generic_statement )? ( COMMA qn= qualified_name (genericList= generic_statement )? )* -> ^( INHERITS ( qualified_name ( generic_statement )? )+ ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:386:4: INHERITS qn= qualified_name (genericList= generic_statement )? ( COMMA qn= qualified_name (genericList= generic_statement )? )*
            {
            INHERITS20=(Token)match(input,INHERITS,FOLLOW_INHERITS_in_inherit_stmnts368);  
            stream_INHERITS.add(INHERITS20);


            pushFollow(FOLLOW_qualified_name_in_inherit_stmnts372);
            qn=qualified_name();

            state._fsp--;

            stream_qualified_name.add(qn.getTree());

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:386:42: (genericList= generic_statement )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==LESS) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:386:42: genericList= generic_statement
                    {
                    pushFollow(FOLLOW_generic_statement_in_inherit_stmnts376);
                    genericList=generic_statement();

                    state._fsp--;

                    stream_generic_statement.add(genericList.getTree());

                    }
                    break;

            }



            		ClassDescriptor clazz = symbol.getCurrentClass();
            		ArrayList<GenericDescriptor> gdList = (genericList!=null?genericList.genericTypeList:null);
            		ArrayList<GenericDescriptor> resultList = new ArrayList<GenericDescriptor>();
            		if(gdList != null){
            			for(int i = 0; i < gdList.size(); i++){
            				TypeDescriptor td = new TypeDescriptor();
            				GenericDescriptor genericType = gdList.get(i);
            				td.setName(genericType.getStaticKey());
            				resultList.add(genericType);
            			}
            			clazz.addUnresolvedParentClassNames((qn!=null?qn.type:null), resultList);
            		}else{
            			clazz.addUnresolvedParentClassNames((qn!=null?qn.type:null), null);
            		}	
            	

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:402:4: ( COMMA qn= qualified_name (genericList= generic_statement )? )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==COMMA) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:402:5: COMMA qn= qualified_name (genericList= generic_statement )?
            	    {
            	    COMMA21=(Token)match(input,COMMA,FOLLOW_COMMA_in_inherit_stmnts384);  
            	    stream_COMMA.add(COMMA21);


            	    pushFollow(FOLLOW_qualified_name_in_inherit_stmnts388);
            	    qn=qualified_name();

            	    state._fsp--;

            	    stream_qualified_name.add(qn.getTree());

            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:402:40: (genericList= generic_statement )?
            	    int alt14=2;
            	    int LA14_0 = input.LA(1);

            	    if ( (LA14_0==LESS) ) {
            	        alt14=1;
            	    }
            	    switch (alt14) {
            	        case 1 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:402:40: genericList= generic_statement
            	            {
            	            pushFollow(FOLLOW_generic_statement_in_inherit_stmnts392);
            	            genericList=generic_statement();

            	            state._fsp--;

            	            stream_generic_statement.add(genericList.getTree());

            	            }
            	            break;

            	    }



            	    		clazz = symbol.getCurrentClass();
            	    		gdList = (genericList!=null?genericList.genericTypeList:null);
            	    		resultList = new ArrayList<GenericDescriptor>();
            	    		if(gdList != null){
            	    			for(int i = 0; i < gdList.size(); i++){
            	    				TypeDescriptor td = new TypeDescriptor();
            	    				GenericDescriptor genericType = gdList.get(i);
            	    				td.setName(genericType.getStaticKey());
            	    				resultList.add(genericType);
            	    			}
            	    			clazz.addUnresolvedParentClassNames((qn!=null?qn.type:null), resultList);
            	    		}else{
            	    			clazz.addUnresolvedParentClassNames((qn!=null?qn.type:null), null);
            	    		}
            	    	

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            // AST REWRITE
            // elements: INHERITS, generic_statement, qualified_name
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 419:2: -> ^( INHERITS ( qualified_name ( generic_statement )? )+ )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:419:5: ^( INHERITS ( qualified_name ( generic_statement )? )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                stream_INHERITS.nextNode()
                , root_1);

                if ( !(stream_qualified_name.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_qualified_name.hasNext() ) {
                    adaptor.addChild(root_1, stream_qualified_name.nextTree());

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:419:33: ( generic_statement )?
                    if ( stream_generic_statement.hasNext() ) {
                        adaptor.addChild(root_1, stream_generic_statement.nextTree());

                    }
                    stream_generic_statement.reset();

                }
                stream_qualified_name.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "inherit_stmnts"


    public static class access_modifier_return extends ParserRuleReturnScope {
        public AccessModifierEnum amEnum;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "access_modifier"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:421:1: access_modifier returns [AccessModifierEnum amEnum] : ( PUBLIC | PRIVATE );
    public final QuorumParser.access_modifier_return access_modifier() throws RecognitionException {
        QuorumParser.access_modifier_return retval = new QuorumParser.access_modifier_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token PUBLIC22=null;
        Token PRIVATE23=null;

        CommonTree PUBLIC22_tree=null;
        CommonTree PRIVATE23_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:422:2: ( PUBLIC | PRIVATE )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==PUBLIC) ) {
                alt16=1;
            }
            else if ( (LA16_0==PRIVATE) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }
            switch (alt16) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:422:4: PUBLIC
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    PUBLIC22=(Token)match(input,PUBLIC,FOLLOW_PUBLIC_in_access_modifier429); 
                    PUBLIC22_tree = 
                    (CommonTree)adaptor.create(PUBLIC22)
                    ;
                    adaptor.addChild(root_0, PUBLIC22_tree);



                    		retval.amEnum = retval.amEnum.PUBLIC;
                    	

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:426:4: PRIVATE
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    PRIVATE23=(Token)match(input,PRIVATE,FOLLOW_PRIVATE_in_access_modifier437); 
                    PRIVATE23_tree = 
                    (CommonTree)adaptor.create(PRIVATE23)
                    ;
                    adaptor.addChild(root_0, PRIVATE23_tree);



                    		retval.amEnum = retval.amEnum.PRIVATE;
                    	

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "access_modifier"


    public static class class_stmnts_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "class_stmnts"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:431:1: class_stmnts : ( assignment_statement | (modifier= access_modifier )? method_declaration );
    public final QuorumParser.class_stmnts_return class_stmnts() throws RecognitionException {
        QuorumParser.class_stmnts_return retval = new QuorumParser.class_stmnts_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        QuorumParser.access_modifier_return modifier =null;

        QuorumParser.assignment_statement_return assignment_statement24 =null;

        QuorumParser.method_declaration_return method_declaration25 =null;



        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:432:2: ( assignment_statement | (modifier= access_modifier )? method_declaration )
            int alt18=2;
            switch ( input.LA(1) ) {
            case BOOLEAN_KEYWORD:
            case ID:
            case INTEGER_KEYWORD:
            case ME:
            case NUMBER_KEYWORD:
            case PARENT:
            case TEXT:
                {
                alt18=1;
                }
                break;
            case PUBLIC:
                {
                int LA18_2 = input.LA(2);

                if ( (LA18_2==BOOLEAN_KEYWORD||LA18_2==ID||LA18_2==INTEGER_KEYWORD||LA18_2==NUMBER_KEYWORD||LA18_2==TEXT) ) {
                    alt18=1;
                }
                else if ( (LA18_2==ACTION||LA18_2==BLUEPRINT||LA18_2==NATIVE||LA18_2==ON_CREATE) ) {
                    alt18=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 2, input);

                    throw nvae;

                }
                }
                break;
            case PRIVATE:
                {
                int LA18_3 = input.LA(2);

                if ( (LA18_3==BOOLEAN_KEYWORD||LA18_3==ID||LA18_3==INTEGER_KEYWORD||LA18_3==NUMBER_KEYWORD||LA18_3==TEXT) ) {
                    alt18=1;
                }
                else if ( (LA18_3==ACTION||LA18_3==BLUEPRINT||LA18_3==NATIVE||LA18_3==ON_CREATE) ) {
                    alt18=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 3, input);

                    throw nvae;

                }
                }
                break;
            case ACTION:
            case BLUEPRINT:
            case NATIVE:
            case ON_CREATE:
                {
                alt18=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;

            }

            switch (alt18) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:433:2: assignment_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();



                    		isInClassAssignmentStatementScope = true;
                    	

                    pushFollow(FOLLOW_assignment_statement_in_class_stmnts455);
                    assignment_statement24=assignment_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, assignment_statement24.getTree());

                    isInClassAssignmentStatementScope = false;

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:437:4: (modifier= access_modifier )? method_declaration
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:437:13: (modifier= access_modifier )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( ((LA17_0 >= PRIVATE && LA17_0 <= PUBLIC)) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:437:13: modifier= access_modifier
                            {
                            pushFollow(FOLLOW_access_modifier_in_class_stmnts466);
                            modifier=access_modifier();

                            state._fsp--;

                            adaptor.addChild(root_0, modifier.getTree());

                            }
                            break;

                    }



                    		accessModifier = (modifier!=null?modifier.amEnum:null);
                    		if(accessModifier == null){
                    			
                    			accessModifier = accessModifier.PUBLIC;
                    		}
                    	

                    pushFollow(FOLLOW_method_declaration_in_class_stmnts474);
                    method_declaration25=method_declaration();

                    state._fsp--;

                    adaptor.addChild(root_0, method_declaration25.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "class_stmnts"


    protected static class method_declaration_scope {
        Vector<ParameterDescriptor> params;
        MethodDescriptor method;
        BlueprintDescriptor blueprint;
        SystemActionDescriptor systemAction;
    }
    protected Stack method_declaration_stack = new Stack();


    public static class method_declaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "method_declaration"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:447:1: method_declaration : ( ACTION ID ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )? ( RETURNS return_type= assignment_declaration )? block END -> ^( ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? block END ) | BLUEPRINT ACTION ID ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )? ( RETURNS return_type= assignment_declaration )? -> ^( BLUEPRINT ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? ) | NATIVE ACTION ID ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )? ( RETURNS return_type= assignment_declaration )? -> ^( NATIVE ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? ) | ON_CREATE block END -> ^( ON_CREATE block END ) );
    public final QuorumParser.method_declaration_return method_declaration() throws RecognitionException {
        method_declaration_stack.push(new method_declaration_scope());
        QuorumParser.method_declaration_return retval = new QuorumParser.method_declaration_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ACTION26=null;
        Token ID27=null;
        Token LEFT_PAREN28=null;
        Token COMMA30=null;
        Token RIGHT_PAREN32=null;
        Token RETURNS33=null;
        Token END35=null;
        Token BLUEPRINT36=null;
        Token ACTION37=null;
        Token ID38=null;
        Token LEFT_PAREN39=null;
        Token COMMA41=null;
        Token RIGHT_PAREN43=null;
        Token RETURNS44=null;
        Token NATIVE45=null;
        Token ACTION46=null;
        Token ID47=null;
        Token LEFT_PAREN48=null;
        Token COMMA50=null;
        Token RIGHT_PAREN52=null;
        Token RETURNS53=null;
        Token ON_CREATE54=null;
        Token END56=null;
        QuorumParser.assignment_declaration_return return_type =null;

        QuorumParser.formal_parameter_return formal_parameter29 =null;

        QuorumParser.formal_parameter_return formal_parameter31 =null;

        QuorumParser.block_return block34 =null;

        QuorumParser.formal_parameter_return formal_parameter40 =null;

        QuorumParser.formal_parameter_return formal_parameter42 =null;

        QuorumParser.formal_parameter_return formal_parameter49 =null;

        QuorumParser.formal_parameter_return formal_parameter51 =null;

        QuorumParser.block_return block55 =null;


        CommonTree ACTION26_tree=null;
        CommonTree ID27_tree=null;
        CommonTree LEFT_PAREN28_tree=null;
        CommonTree COMMA30_tree=null;
        CommonTree RIGHT_PAREN32_tree=null;
        CommonTree RETURNS33_tree=null;
        CommonTree END35_tree=null;
        CommonTree BLUEPRINT36_tree=null;
        CommonTree ACTION37_tree=null;
        CommonTree ID38_tree=null;
        CommonTree LEFT_PAREN39_tree=null;
        CommonTree COMMA41_tree=null;
        CommonTree RIGHT_PAREN43_tree=null;
        CommonTree RETURNS44_tree=null;
        CommonTree NATIVE45_tree=null;
        CommonTree ACTION46_tree=null;
        CommonTree ID47_tree=null;
        CommonTree LEFT_PAREN48_tree=null;
        CommonTree COMMA50_tree=null;
        CommonTree RIGHT_PAREN52_tree=null;
        CommonTree RETURNS53_tree=null;
        CommonTree ON_CREATE54_tree=null;
        CommonTree END56_tree=null;
        RewriteRuleTokenStream stream_NATIVE=new RewriteRuleTokenStream(adaptor,"token NATIVE");
        RewriteRuleTokenStream stream_LEFT_PAREN=new RewriteRuleTokenStream(adaptor,"token LEFT_PAREN");
        RewriteRuleTokenStream stream_RIGHT_PAREN=new RewriteRuleTokenStream(adaptor,"token RIGHT_PAREN");
        RewriteRuleTokenStream stream_ON_CREATE=new RewriteRuleTokenStream(adaptor,"token ON_CREATE");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_END=new RewriteRuleTokenStream(adaptor,"token END");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_BLUEPRINT=new RewriteRuleTokenStream(adaptor,"token BLUEPRINT");
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleTokenStream stream_RETURNS=new RewriteRuleTokenStream(adaptor,"token RETURNS");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        RewriteRuleSubtreeStream stream_assignment_declaration=new RewriteRuleSubtreeStream(adaptor,"rule assignment_declaration");
        RewriteRuleSubtreeStream stream_formal_parameter=new RewriteRuleSubtreeStream(adaptor,"rule formal_parameter");

        Documentation methodDocumentation = getDocumentationFromRecentToken();


        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:458:2: ( ACTION ID ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )? ( RETURNS return_type= assignment_declaration )? block END -> ^( ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? block END ) | BLUEPRINT ACTION ID ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )? ( RETURNS return_type= assignment_declaration )? -> ^( BLUEPRINT ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? ) | NATIVE ACTION ID ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )? ( RETURNS return_type= assignment_declaration )? -> ^( NATIVE ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? ) | ON_CREATE block END -> ^( ON_CREATE block END ) )
            int alt31=4;
            switch ( input.LA(1) ) {
            case ACTION:
                {
                alt31=1;
                }
                break;
            case BLUEPRINT:
                {
                alt31=2;
                }
                break;
            case NATIVE:
                {
                alt31=3;
                }
                break;
            case ON_CREATE:
                {
                alt31=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;

            }

            switch (alt31) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:458:4: ACTION ID ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )? ( RETURNS return_type= assignment_declaration )? block END
                    {
                    ACTION26=(Token)match(input,ACTION,FOLLOW_ACTION_in_method_declaration492);  
                    stream_ACTION.add(ACTION26);


                    ID27=(Token)match(input,ID,FOLLOW_ID_in_method_declaration494);  
                    stream_ID.add(ID27);



                    		((method_declaration_scope)method_declaration_stack.peek()).method = new MethodDescriptor();
                    		((method_declaration_scope)method_declaration_stack.peek()).method.setDocumentation(methodDocumentation);
                    		((method_declaration_scope)method_declaration_stack.peek()).method.setName((ID27!=null?ID27.getText():null));
                    		((method_declaration_scope)method_declaration_stack.peek()).method.setAccessModifier(accessModifier);
                    		((method_declaration_scope)method_declaration_stack.peek()).method.setLineBegin(ACTION26.getLine());
                    		((method_declaration_scope)method_declaration_stack.peek()).method.setColumnBegin(ACTION26.getCharPositionInLine());
                    		((method_declaration_scope)method_declaration_stack.peek()).params = new Vector<ParameterDescriptor>();		
                    	

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:469:2: ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==LEFT_PAREN) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:469:3: LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN
                            {
                            LEFT_PAREN28=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_method_declaration504);  
                            stream_LEFT_PAREN.add(LEFT_PAREN28);


                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:469:14: ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )?
                            int alt20=2;
                            int LA20_0 = input.LA(1);

                            if ( (LA20_0==BOOLEAN_KEYWORD||LA20_0==ID||LA20_0==INTEGER_KEYWORD||LA20_0==NUMBER_KEYWORD||LA20_0==TEXT) ) {
                                alt20=1;
                            }
                            switch (alt20) {
                                case 1 :
                                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:469:15: formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )*
                                    {
                                    pushFollow(FOLLOW_formal_parameter_in_method_declaration507);
                                    formal_parameter29=formal_parameter(((method_declaration_scope)method_declaration_stack.peek()).params);

                                    state._fsp--;

                                    stream_formal_parameter.add(formal_parameter29.getTree());

                                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:469:61: ( COMMA formal_parameter[$method_declaration::params] )*
                                    loop19:
                                    do {
                                        int alt19=2;
                                        int LA19_0 = input.LA(1);

                                        if ( (LA19_0==COMMA) ) {
                                            alt19=1;
                                        }


                                        switch (alt19) {
                                    	case 1 :
                                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:469:62: COMMA formal_parameter[$method_declaration::params]
                                    	    {
                                    	    COMMA30=(Token)match(input,COMMA,FOLLOW_COMMA_in_method_declaration511);  
                                    	    stream_COMMA.add(COMMA30);


                                    	    pushFollow(FOLLOW_formal_parameter_in_method_declaration513);
                                    	    formal_parameter31=formal_parameter(((method_declaration_scope)method_declaration_stack.peek()).params);

                                    	    state._fsp--;

                                    	    stream_formal_parameter.add(formal_parameter31.getTree());

                                    	    }
                                    	    break;

                                    	default :
                                    	    break loop19;
                                        }
                                    } while (true);


                                    }
                                    break;

                            }


                            RIGHT_PAREN32=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_method_declaration520);  
                            stream_RIGHT_PAREN.add(RIGHT_PAREN32);


                            }
                            break;

                    }


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:470:2: ( RETURNS return_type= assignment_declaration )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==RETURNS) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:470:3: RETURNS return_type= assignment_declaration
                            {
                            RETURNS33=(Token)match(input,RETURNS,FOLLOW_RETURNS_in_method_declaration526);  
                            stream_RETURNS.add(RETURNS33);


                            pushFollow(FOLLOW_assignment_declaration_in_method_declaration532);
                            return_type=assignment_declaration();

                            state._fsp--;

                            stream_assignment_declaration.add(return_type.getTree());

                            }
                            break;

                    }



                    		if((RETURNS33!=null?RETURNS33.getText():null) != null) {
                    			TypeDescriptor type = (return_type!=null?return_type.type:null);
                    			((method_declaration_scope)method_declaration_stack.peek()).method.setReturnType(type);
                    			
                    		}
                    		else {			
                    			TypeDescriptor t = new TypeDescriptor();
                    			t.setName(TypeDescriptor.VOID);
                    			((method_declaration_scope)method_declaration_stack.peek()).method.setReturnType(t);
                    		}
                    		//now add the method, to check for overloading
                    		for(ParameterDescriptor param : ((method_declaration_scope)method_declaration_stack.peek()).params) {
                    			CompilerError error = ((method_declaration_scope)method_declaration_stack.peek()).method.add(param);
                    			if(error != null) {
                                            	vm.getCompilerErrors().addError(error);
                                            }
                    		}
                    		
                    		
                    		CompilerError error = symbol.add(((method_declaration_scope)method_declaration_stack.peek()).method);
                                    if(error != null) {
                                    	error.setColumn(ACTION26.getCharPositionInLine());
                                    	error.setLineNumber(ACTION26.getLine());
                                    	error.setFile(getGrammarFileNameNoExtension());
                                            vm.getCompilerErrors().addError(error);    
                                    }
                    		
                    	

                    pushFollow(FOLLOW_block_in_method_declaration542);
                    block34=block();

                    state._fsp--;

                    stream_block.add(block34.getTree());

                    END35=(Token)match(input,END,FOLLOW_END_in_method_declaration545);  
                    stream_END.add(END35);



                    		((method_declaration_scope)method_declaration_stack.peek()).method.setLineEnd(END35.getLine());
                    		((method_declaration_scope)method_declaration_stack.peek()).method.setColumnEnd(END35.getCharPositionInLine());
                    		if(classWithNoName) {
                    			//currentClass.setLineEnd(END35.getLine());
                    			//currentClass.setColumnEnd(END35.getCharPositionInLine() + (END35!=null?END35.getText():null).length());
                    		}
                    		symbol.popScope();
                    	

                    // AST REWRITE
                    // elements: RETURNS, block, assignment_declaration, END, formal_parameter, ID, ACTION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 511:2: -> ^( ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? block END )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:511:5: ^( ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? block END )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_ACTION.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:511:17: ( formal_parameter )*
                        while ( stream_formal_parameter.hasNext() ) {
                            adaptor.addChild(root_1, stream_formal_parameter.nextTree());

                        }
                        stream_formal_parameter.reset();

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:511:35: ( RETURNS assignment_declaration )?
                        if ( stream_RETURNS.hasNext()||stream_assignment_declaration.hasNext() ) {
                            adaptor.addChild(root_1, 
                            stream_RETURNS.nextNode()
                            );

                            adaptor.addChild(root_1, stream_assignment_declaration.nextTree());

                        }
                        stream_RETURNS.reset();
                        stream_assignment_declaration.reset();

                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_1, 
                        stream_END.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:512:4: BLUEPRINT ACTION ID ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )? ( RETURNS return_type= assignment_declaration )?
                    {
                    BLUEPRINT36=(Token)match(input,BLUEPRINT,FOLLOW_BLUEPRINT_in_method_declaration577);  
                    stream_BLUEPRINT.add(BLUEPRINT36);


                    ACTION37=(Token)match(input,ACTION,FOLLOW_ACTION_in_method_declaration579);  
                    stream_ACTION.add(ACTION37);


                    ID38=(Token)match(input,ID,FOLLOW_ID_in_method_declaration581);  
                    stream_ID.add(ID38);



                    		((method_declaration_scope)method_declaration_stack.peek()).blueprint = new BlueprintDescriptor();
                    		((method_declaration_scope)method_declaration_stack.peek()).blueprint.setDocumentation(methodDocumentation);		
                    		((method_declaration_scope)method_declaration_stack.peek()).blueprint.setName((ID38!=null?ID38.getText():null));
                    		((method_declaration_scope)method_declaration_stack.peek()).blueprint.setAccessModifier(accessModifier);
                    		((method_declaration_scope)method_declaration_stack.peek()).blueprint.setLineBegin(ACTION37.getLine());
                    		((method_declaration_scope)method_declaration_stack.peek()).blueprint.setColumnBegin(ACTION37.getCharPositionInLine());
                    		((method_declaration_scope)method_declaration_stack.peek()).blueprint.setLineEnd(ACTION37.getLine());
                    		((method_declaration_scope)method_declaration_stack.peek()).blueprint.setColumnEnd(ACTION37.getCharPositionInLine());
                    		((method_declaration_scope)method_declaration_stack.peek()).params = new Vector<ParameterDescriptor>();		
                    	

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:525:2: ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )?
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( (LA25_0==LEFT_PAREN) ) {
                        alt25=1;
                    }
                    switch (alt25) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:525:3: LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN
                            {
                            LEFT_PAREN39=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_method_declaration591);  
                            stream_LEFT_PAREN.add(LEFT_PAREN39);


                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:525:14: ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )?
                            int alt24=2;
                            int LA24_0 = input.LA(1);

                            if ( (LA24_0==BOOLEAN_KEYWORD||LA24_0==ID||LA24_0==INTEGER_KEYWORD||LA24_0==NUMBER_KEYWORD||LA24_0==TEXT) ) {
                                alt24=1;
                            }
                            switch (alt24) {
                                case 1 :
                                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:525:15: formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )*
                                    {
                                    pushFollow(FOLLOW_formal_parameter_in_method_declaration594);
                                    formal_parameter40=formal_parameter(((method_declaration_scope)method_declaration_stack.peek()).params);

                                    state._fsp--;

                                    stream_formal_parameter.add(formal_parameter40.getTree());

                                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:525:61: ( COMMA formal_parameter[$method_declaration::params] )*
                                    loop23:
                                    do {
                                        int alt23=2;
                                        int LA23_0 = input.LA(1);

                                        if ( (LA23_0==COMMA) ) {
                                            alt23=1;
                                        }


                                        switch (alt23) {
                                    	case 1 :
                                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:525:62: COMMA formal_parameter[$method_declaration::params]
                                    	    {
                                    	    COMMA41=(Token)match(input,COMMA,FOLLOW_COMMA_in_method_declaration598);  
                                    	    stream_COMMA.add(COMMA41);


                                    	    pushFollow(FOLLOW_formal_parameter_in_method_declaration600);
                                    	    formal_parameter42=formal_parameter(((method_declaration_scope)method_declaration_stack.peek()).params);

                                    	    state._fsp--;

                                    	    stream_formal_parameter.add(formal_parameter42.getTree());

                                    	    }
                                    	    break;

                                    	default :
                                    	    break loop23;
                                        }
                                    } while (true);


                                    }
                                    break;

                            }


                            RIGHT_PAREN43=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_method_declaration607);  
                            stream_RIGHT_PAREN.add(RIGHT_PAREN43);


                            }
                            break;

                    }


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:526:2: ( RETURNS return_type= assignment_declaration )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==RETURNS) ) {
                        alt26=1;
                    }
                    switch (alt26) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:526:3: RETURNS return_type= assignment_declaration
                            {
                            RETURNS44=(Token)match(input,RETURNS,FOLLOW_RETURNS_in_method_declaration613);  
                            stream_RETURNS.add(RETURNS44);


                            pushFollow(FOLLOW_assignment_declaration_in_method_declaration619);
                            return_type=assignment_declaration();

                            state._fsp--;

                            stream_assignment_declaration.add(return_type.getTree());

                            }
                            break;

                    }



                    		if((RETURNS44!=null?RETURNS44.getText():null) != null) {
                    			((method_declaration_scope)method_declaration_stack.peek()).blueprint.setReturnType((return_type!=null?return_type.type:null));
                    		}
                    		else {			
                    			TypeDescriptor t = new TypeDescriptor();
                    			t.setName(TypeDescriptor.VOID);
                    			((method_declaration_scope)method_declaration_stack.peek()).blueprint.setReturnType(t);
                    		}
                    		//now add the method, to check for overloading
                    		for(ParameterDescriptor param : ((method_declaration_scope)method_declaration_stack.peek()).params) {
                    			CompilerError error = ((method_declaration_scope)method_declaration_stack.peek()).blueprint.add(param);
                    			if(error != null) {
                                            	vm.getCompilerErrors().addError(error);
                                            }
                    		}
                    		
                    		
                    		CompilerError error = symbol.add(((method_declaration_scope)method_declaration_stack.peek()).blueprint);
                                    if(error != null) {
                                    	error.setColumn(ACTION37.getCharPositionInLine());
                                    	error.setLineNumber(ACTION37.getLine());
                                    	error.setFile(getGrammarFileNameNoExtension());
                                            vm.getCompilerErrors().addError(error);    
                                    }
                    	

                    // AST REWRITE
                    // elements: BLUEPRINT, ID, formal_parameter, assignment_declaration, ACTION, RETURNS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 553:2: -> ^( BLUEPRINT ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:553:5: ^( BLUEPRINT ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_BLUEPRINT.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ACTION.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:553:27: ( formal_parameter )*
                        while ( stream_formal_parameter.hasNext() ) {
                            adaptor.addChild(root_1, stream_formal_parameter.nextTree());

                        }
                        stream_formal_parameter.reset();

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:553:45: ( RETURNS assignment_declaration )?
                        if ( stream_assignment_declaration.hasNext()||stream_RETURNS.hasNext() ) {
                            adaptor.addChild(root_1, 
                            stream_RETURNS.nextNode()
                            );

                            adaptor.addChild(root_1, stream_assignment_declaration.nextTree());

                        }
                        stream_assignment_declaration.reset();
                        stream_RETURNS.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:554:4: NATIVE ACTION ID ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )? ( RETURNS return_type= assignment_declaration )?
                    {
                    NATIVE45=(Token)match(input,NATIVE,FOLLOW_NATIVE_in_method_declaration651);  
                    stream_NATIVE.add(NATIVE45);


                    ACTION46=(Token)match(input,ACTION,FOLLOW_ACTION_in_method_declaration653);  
                    stream_ACTION.add(ACTION46);


                    ID47=(Token)match(input,ID,FOLLOW_ID_in_method_declaration655);  
                    stream_ID.add(ID47);



                    		((method_declaration_scope)method_declaration_stack.peek()).systemAction = new SystemActionDescriptor();
                    		((method_declaration_scope)method_declaration_stack.peek()).systemAction.setDocumentation(methodDocumentation);			
                    		((method_declaration_scope)method_declaration_stack.peek()).systemAction.setName((ID47!=null?ID47.getText():null));
                    		((method_declaration_scope)method_declaration_stack.peek()).systemAction.setAccessModifier(accessModifier);
                    		((method_declaration_scope)method_declaration_stack.peek()).systemAction.setLineBegin(ACTION46.getLine());
                    		((method_declaration_scope)method_declaration_stack.peek()).systemAction.setColumnBegin(ACTION46.getCharPositionInLine());
                    		((method_declaration_scope)method_declaration_stack.peek()).systemAction.setLineEnd(ACTION46.getLine());
                    		((method_declaration_scope)method_declaration_stack.peek()).systemAction.setColumnEnd(ACTION46.getCharPositionInLine());
                    		((method_declaration_scope)method_declaration_stack.peek()).params = new Vector<ParameterDescriptor>();		
                    	

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:567:2: ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )?
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( (LA29_0==LEFT_PAREN) ) {
                        alt29=1;
                    }
                    switch (alt29) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:567:3: LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN
                            {
                            LEFT_PAREN48=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_method_declaration665);  
                            stream_LEFT_PAREN.add(LEFT_PAREN48);


                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:567:14: ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )?
                            int alt28=2;
                            int LA28_0 = input.LA(1);

                            if ( (LA28_0==BOOLEAN_KEYWORD||LA28_0==ID||LA28_0==INTEGER_KEYWORD||LA28_0==NUMBER_KEYWORD||LA28_0==TEXT) ) {
                                alt28=1;
                            }
                            switch (alt28) {
                                case 1 :
                                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:567:15: formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )*
                                    {
                                    pushFollow(FOLLOW_formal_parameter_in_method_declaration668);
                                    formal_parameter49=formal_parameter(((method_declaration_scope)method_declaration_stack.peek()).params);

                                    state._fsp--;

                                    stream_formal_parameter.add(formal_parameter49.getTree());

                                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:567:61: ( COMMA formal_parameter[$method_declaration::params] )*
                                    loop27:
                                    do {
                                        int alt27=2;
                                        int LA27_0 = input.LA(1);

                                        if ( (LA27_0==COMMA) ) {
                                            alt27=1;
                                        }


                                        switch (alt27) {
                                    	case 1 :
                                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:567:62: COMMA formal_parameter[$method_declaration::params]
                                    	    {
                                    	    COMMA50=(Token)match(input,COMMA,FOLLOW_COMMA_in_method_declaration672);  
                                    	    stream_COMMA.add(COMMA50);


                                    	    pushFollow(FOLLOW_formal_parameter_in_method_declaration674);
                                    	    formal_parameter51=formal_parameter(((method_declaration_scope)method_declaration_stack.peek()).params);

                                    	    state._fsp--;

                                    	    stream_formal_parameter.add(formal_parameter51.getTree());

                                    	    }
                                    	    break;

                                    	default :
                                    	    break loop27;
                                        }
                                    } while (true);


                                    }
                                    break;

                            }


                            RIGHT_PAREN52=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_method_declaration681);  
                            stream_RIGHT_PAREN.add(RIGHT_PAREN52);


                            }
                            break;

                    }


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:568:2: ( RETURNS return_type= assignment_declaration )?
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( (LA30_0==RETURNS) ) {
                        alt30=1;
                    }
                    switch (alt30) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:568:3: RETURNS return_type= assignment_declaration
                            {
                            RETURNS53=(Token)match(input,RETURNS,FOLLOW_RETURNS_in_method_declaration687);  
                            stream_RETURNS.add(RETURNS53);


                            pushFollow(FOLLOW_assignment_declaration_in_method_declaration693);
                            return_type=assignment_declaration();

                            state._fsp--;

                            stream_assignment_declaration.add(return_type.getTree());

                            }
                            break;

                    }



                    		if((RETURNS53!=null?RETURNS53.getText():null) != null) {
                    			((method_declaration_scope)method_declaration_stack.peek()).systemAction.setReturnType((return_type!=null?return_type.type:null));
                    		}
                    		else {			
                    			TypeDescriptor t = new TypeDescriptor();
                    			t.setName(TypeDescriptor.VOID);
                    			((method_declaration_scope)method_declaration_stack.peek()).systemAction.setReturnType(t);
                    		}
                    		//now add the method, to check for overloading
                    		for(ParameterDescriptor param : ((method_declaration_scope)method_declaration_stack.peek()).params) {
                    			CompilerError error = ((method_declaration_scope)method_declaration_stack.peek()).systemAction.add(param);
                    			if(error != null) {
                                            	vm.getCompilerErrors().addError(error);
                                            }
                    		}
                    		
                    		
                    		CompilerError error = symbol.add(((method_declaration_scope)method_declaration_stack.peek()).systemAction);
                                    if(error != null) {
                                    	error.setColumn(ACTION46.getCharPositionInLine());
                                    	error.setLineNumber(ACTION46.getLine());
                                    	error.setFile(getGrammarFileNameNoExtension());
                                            vm.getCompilerErrors().addError(error);    
                                    }
                    	

                    // AST REWRITE
                    // elements: assignment_declaration, ID, ACTION, NATIVE, formal_parameter, RETURNS
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 595:2: -> ^( NATIVE ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:595:5: ^( NATIVE ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_NATIVE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ACTION.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:595:24: ( formal_parameter )*
                        while ( stream_formal_parameter.hasNext() ) {
                            adaptor.addChild(root_1, stream_formal_parameter.nextTree());

                        }
                        stream_formal_parameter.reset();

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:595:42: ( RETURNS assignment_declaration )?
                        if ( stream_assignment_declaration.hasNext()||stream_RETURNS.hasNext() ) {
                            adaptor.addChild(root_1, 
                            stream_RETURNS.nextNode()
                            );

                            adaptor.addChild(root_1, stream_assignment_declaration.nextTree());

                        }
                        stream_assignment_declaration.reset();
                        stream_RETURNS.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:596:4: ON_CREATE block END
                    {
                    ON_CREATE54=(Token)match(input,ON_CREATE,FOLLOW_ON_CREATE_in_method_declaration725);  
                    stream_ON_CREATE.add(ON_CREATE54);



                    		((method_declaration_scope)method_declaration_stack.peek()).method = new MethodDescriptor();
                    		((method_declaration_scope)method_declaration_stack.peek()).method.setName((ON_CREATE54!=null?ON_CREATE54.getText():null));
                    		((method_declaration_scope)method_declaration_stack.peek()).method.flagMethodAsConstructor();
                    		((method_declaration_scope)method_declaration_stack.peek()).method.setAccessModifier(AccessModifierEnum.PRIVATE);
                    		((method_declaration_scope)method_declaration_stack.peek()).method.setLineBegin(ON_CREATE54.getLine());
                    		((method_declaration_scope)method_declaration_stack.peek()).method.setColumnBegin(ON_CREATE54.getCharPositionInLine());
                    		
                    		TypeDescriptor t = new TypeDescriptor();
                    		t.setName(TypeDescriptor.VOID);
                    		((method_declaration_scope)method_declaration_stack.peek()).method.setReturnType(t);
                    			
                    		CompilerError error = symbol.add(((method_declaration_scope)method_declaration_stack.peek()).method);
                                    if(error != null) {
                                    	error.setColumn(ON_CREATE54.getCharPositionInLine());
                                    	error.setLineNumber(ON_CREATE54.getLine());
                                    	error.setFile(getGrammarFileNameNoExtension());
                                            vm.getCompilerErrors().addError(error);    
                                    }
                    		isInConstructorScope = true;
                    	

                    pushFollow(FOLLOW_block_in_method_declaration732);
                    block55=block();

                    state._fsp--;

                    stream_block.add(block55.getTree());

                    END56=(Token)match(input,END,FOLLOW_END_in_method_declaration734);  
                    stream_END.add(END56);



                    		((method_declaration_scope)method_declaration_stack.peek()).method.setLineEnd(END56.getLine());
                    		((method_declaration_scope)method_declaration_stack.peek()).method.setColumnEnd(END56.getCharPositionInLine());
                    		((method_declaration_scope)method_declaration_stack.peek()).method.setColumnEnd(END56.getCharPositionInLine());
                    		
                    		symbol.popScope();
                    		isInConstructorScope = false;
                    	

                    // AST REWRITE
                    // elements: block, END, ON_CREATE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 627:2: -> ^( ON_CREATE block END )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:627:5: ^( ON_CREATE block END )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_ON_CREATE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_1, 
                        stream_END.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            method_declaration_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "method_declaration"


    public static class formal_parameter_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "formal_parameter"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:629:1: formal_parameter[Vector<ParameterDescriptor> params] : assignment_declaration ID -> ^( FPARAM assignment_declaration ID ) ;
    public final QuorumParser.formal_parameter_return formal_parameter(Vector<ParameterDescriptor> params) throws RecognitionException {
        QuorumParser.formal_parameter_return retval = new QuorumParser.formal_parameter_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ID58=null;
        QuorumParser.assignment_declaration_return assignment_declaration57 =null;


        CommonTree ID58_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_assignment_declaration=new RewriteRuleSubtreeStream(adaptor,"rule assignment_declaration");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:630:2: ( assignment_declaration ID -> ^( FPARAM assignment_declaration ID ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:630:4: assignment_declaration ID
            {
            pushFollow(FOLLOW_assignment_declaration_in_formal_parameter759);
            assignment_declaration57=assignment_declaration();

            state._fsp--;

            stream_assignment_declaration.add(assignment_declaration57.getTree());

            ID58=(Token)match(input,ID,FOLLOW_ID_in_formal_parameter761);  
            stream_ID.add(ID58);



            		TypeDescriptor type = (assignment_declaration57!=null?assignment_declaration57.type:null);		
            		ParameterDescriptor d = new ParameterDescriptor();
            		Iterator<GenericDescriptor> gdList = null;
            		if(type != null){
            			gdList = type.getSubTypes();
            		}
            		
            		d.setName((ID58!=null?ID58.getText():null));
            		d.setLineBegin(ID58.getLine());
            		d.setColumnBegin(ID58.getCharPositionInLine());
            		d.setLineEnd(ID58.getLine());
            		d.setColumnEnd(ID58.getCharPositionInLine());
            		
            		if(gdList != null){
            			while(gdList.hasNext()){
            				TypeDescriptor td = new TypeDescriptor();
            				GenericDescriptor genericType = gdList.next();
            				td.setName(genericType.getStaticKey());
            				genericType.addBoundType(td);
            				d.addTemplateType(genericType);
            			}
            		}
            		
            		GenericDescriptor gd = null;
            		if(type!=null){
            		 	gd = symbol.getCurrentClass().getTemplateVariable((assignment_declaration57!=null?assignment_declaration57.type:null).getName());
            		}
            		
            		if(gd != null){
            			d.setType(gd.getType());
            		}else{
            			d.setType(type);
            		}
            		
            		
            		params.add(d);
            	

            // AST REWRITE
            // elements: assignment_declaration, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 668:4: -> ^( FPARAM assignment_declaration ID )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:668:7: ^( FPARAM assignment_declaration ID )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(FPARAM, "FPARAM")
                , root_1);

                adaptor.addChild(root_1, stream_assignment_declaration.nextTree());

                adaptor.addChild(root_1, 
                stream_ID.nextNode()
                );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "formal_parameter"


    public static class qualified_name_return extends ParserRuleReturnScope {
        public QualifiedNameDescriptor type;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "qualified_name"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:671:1: qualified_name returns [QualifiedNameDescriptor type] :ids+= ID ( PERIOD ids+= ID )* -> ^( QUALIFIED_NAME ID ( PERIOD ID )* ) ;
    public final QuorumParser.qualified_name_return qualified_name() throws RecognitionException {
        QuorumParser.qualified_name_return retval = new QuorumParser.qualified_name_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token PERIOD59=null;
        Token ids=null;
        List list_ids=null;

        CommonTree PERIOD59_tree=null;
        CommonTree ids_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_PERIOD=new RewriteRuleTokenStream(adaptor,"token PERIOD");

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:672:2: (ids+= ID ( PERIOD ids+= ID )* -> ^( QUALIFIED_NAME ID ( PERIOD ID )* ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:672:4: ids+= ID ( PERIOD ids+= ID )*
            {
            ids=(Token)match(input,ID,FOLLOW_ID_in_qualified_name792);  
            stream_ID.add(ids);

            if (list_ids==null) list_ids=new ArrayList();
            list_ids.add(ids);


            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:672:12: ( PERIOD ids+= ID )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==PERIOD) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:672:13: PERIOD ids+= ID
            	    {
            	    PERIOD59=(Token)match(input,PERIOD,FOLLOW_PERIOD_in_qualified_name795);  
            	    stream_PERIOD.add(PERIOD59);


            	    ids=(Token)match(input,ID,FOLLOW_ID_in_qualified_name799);  
            	    stream_ID.add(ids);

            	    if (list_ids==null) list_ids=new ArrayList();
            	    list_ids.add(ids);


            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);



            			QualifiedNameDescriptor type = new QualifiedNameDescriptor();
            			type.setLineBegin(((Token)list_ids.get(0)).getLine());
            			type.setColumnBegin(((Token)list_ids.get(0)).getCharPositionInLine());
            			
            			String name = "";
            			Iterator it = list_ids.iterator();       		
                    	
                    		while(it.hasNext()) {           
                        			name += ((CommonToken) it.next()).getText();
                       		
                        			if(it.hasNext()) {
                            			name += ".";
                       			}
                        		
                        		}
                        		type.setName(name);
            			
            			type.setLineEnd(((Token)list_ids.get(list_ids.size() - 1)).getLine());
            			type.setColumnEnd(((Token)list_ids.get(list_ids.size() - 1)).getCharPositionInLine());
            			retval.type = type;			
            		

            // AST REWRITE
            // elements: ID, ID, PERIOD
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 695:3: -> ^( QUALIFIED_NAME ID ( PERIOD ID )* )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:695:6: ^( QUALIFIED_NAME ID ( PERIOD ID )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(QUALIFIED_NAME, "QUALIFIED_NAME")
                , root_1);

                adaptor.addChild(root_1, 
                stream_ID.nextNode()
                );

                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:695:26: ( PERIOD ID )*
                while ( stream_ID.hasNext()||stream_PERIOD.hasNext() ) {
                    adaptor.addChild(root_1, 
                    stream_PERIOD.nextNode()
                    );

                    adaptor.addChild(root_1, 
                    stream_ID.nextNode()
                    );

                }
                stream_ID.reset();
                stream_PERIOD.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "qualified_name"


    public static class block_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "block"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:697:1: block : ( statement )* -> ^( STATEMENT_LIST ( statement )* ) ;
    public final QuorumParser.block_return block() throws RecognitionException {
        QuorumParser.block_return retval = new QuorumParser.block_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        QuorumParser.statement_return statement60 =null;


        RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:697:8: ( ( statement )* -> ^( STATEMENT_LIST ( statement )* ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:697:10: ( statement )*
            {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:697:10: ( statement )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==ALERT||LA33_0==BOOLEAN_KEYWORD||LA33_0==CHECK||(LA33_0 >= ID && LA33_0 <= IF)||LA33_0==INTEGER_KEYWORD||LA33_0==ME||LA33_0==NUMBER_KEYWORD||LA33_0==PARENT||(LA33_0 >= PRINT && LA33_0 <= PUBLIC)||(LA33_0 >= REPEAT && LA33_0 <= RETURN)||LA33_0==SAY||LA33_0==TEXT) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:697:10: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block833);
            	    statement60=statement();

            	    state._fsp--;

            	    stream_statement.add(statement60.getTree());

            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);


            // AST REWRITE
            // elements: statement
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 698:3: -> ^( STATEMENT_LIST ( statement )* )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:698:6: ^( STATEMENT_LIST ( statement )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(STATEMENT_LIST, "STATEMENT_LIST")
                , root_1);

                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:698:23: ( statement )*
                while ( stream_statement.hasNext() ) {
                    adaptor.addChild(root_1, stream_statement.nextTree());

                }
                stream_statement.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "block"


    public static class statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:701:1: statement : ( solo_method_call | if_statement | assignment_statement | loop_statement | return_statement | print_statement | speak_statement | check_statement | alert_statement );
    public final QuorumParser.statement_return statement() throws RecognitionException {
        QuorumParser.statement_return retval = new QuorumParser.statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        QuorumParser.solo_method_call_return solo_method_call61 =null;

        QuorumParser.if_statement_return if_statement62 =null;

        QuorumParser.assignment_statement_return assignment_statement63 =null;

        QuorumParser.loop_statement_return loop_statement64 =null;

        QuorumParser.return_statement_return return_statement65 =null;

        QuorumParser.print_statement_return print_statement66 =null;

        QuorumParser.speak_statement_return speak_statement67 =null;

        QuorumParser.check_statement_return check_statement68 =null;

        QuorumParser.alert_statement_return alert_statement69 =null;



        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:701:10: ( solo_method_call | if_statement | assignment_statement | loop_statement | return_statement | print_statement | speak_statement | check_statement | alert_statement )
            int alt34=9;
            alt34 = dfa34.predict(input);
            switch (alt34) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:702:3: solo_method_call
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_solo_method_call_in_statement856);
                    solo_method_call61=solo_method_call();

                    state._fsp--;

                    adaptor.addChild(root_0, solo_method_call61.getTree());

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:703:4: if_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_if_statement_in_statement861);
                    if_statement62=if_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, if_statement62.getTree());

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:704:4: assignment_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_assignment_statement_in_statement866);
                    assignment_statement63=assignment_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, assignment_statement63.getTree());

                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:705:4: loop_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_loop_statement_in_statement871);
                    loop_statement64=loop_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, loop_statement64.getTree());

                    }
                    break;
                case 5 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:706:4: return_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_return_statement_in_statement876);
                    return_statement65=return_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, return_statement65.getTree());

                    }
                    break;
                case 6 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:707:4: print_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_print_statement_in_statement881);
                    print_statement66=print_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, print_statement66.getTree());

                    }
                    break;
                case 7 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:708:4: speak_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_speak_statement_in_statement886);
                    speak_statement67=speak_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, speak_statement67.getTree());

                    }
                    break;
                case 8 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:709:4: check_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_check_statement_in_statement891);
                    check_statement68=check_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, check_statement68.getTree());

                    }
                    break;
                case 9 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:710:4: alert_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_alert_statement_in_statement896);
                    alert_statement69=alert_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, alert_statement69.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "statement"


    public static class solo_method_call_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "solo_method_call"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:713:1: solo_method_call : ( qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN -> ^( SOLO_FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN ) | PARENT COLON qualified_name COLON ID LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN -> ^( SOLO_FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN ) | ME COLON qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN -> ^( SOLO_FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN ) );
    public final QuorumParser.solo_method_call_return solo_method_call() throws RecognitionException {
        QuorumParser.solo_method_call_return retval = new QuorumParser.solo_method_call_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token COLON71=null;
        Token ID72=null;
        Token LEFT_PAREN73=null;
        Token COMMA75=null;
        Token RIGHT_PAREN77=null;
        Token PARENT78=null;
        Token COLON79=null;
        Token COLON81=null;
        Token ID82=null;
        Token LEFT_PAREN83=null;
        Token COMMA85=null;
        Token RIGHT_PAREN87=null;
        Token ME88=null;
        Token COLON89=null;
        Token COLON91=null;
        Token ID92=null;
        Token LEFT_PAREN93=null;
        Token COMMA95=null;
        Token RIGHT_PAREN97=null;
        QuorumParser.qualified_name_return qualified_name70 =null;

        QuorumParser.expression_return expression74 =null;

        QuorumParser.expression_return expression76 =null;

        QuorumParser.qualified_name_return qualified_name80 =null;

        QuorumParser.expression_return expression84 =null;

        QuorumParser.expression_return expression86 =null;

        QuorumParser.qualified_name_return qualified_name90 =null;

        QuorumParser.expression_return expression94 =null;

        QuorumParser.expression_return expression96 =null;


        CommonTree COLON71_tree=null;
        CommonTree ID72_tree=null;
        CommonTree LEFT_PAREN73_tree=null;
        CommonTree COMMA75_tree=null;
        CommonTree RIGHT_PAREN77_tree=null;
        CommonTree PARENT78_tree=null;
        CommonTree COLON79_tree=null;
        CommonTree COLON81_tree=null;
        CommonTree ID82_tree=null;
        CommonTree LEFT_PAREN83_tree=null;
        CommonTree COMMA85_tree=null;
        CommonTree RIGHT_PAREN87_tree=null;
        CommonTree ME88_tree=null;
        CommonTree COLON89_tree=null;
        CommonTree COLON91_tree=null;
        CommonTree ID92_tree=null;
        CommonTree LEFT_PAREN93_tree=null;
        CommonTree COMMA95_tree=null;
        CommonTree RIGHT_PAREN97_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_LEFT_PAREN=new RewriteRuleTokenStream(adaptor,"token LEFT_PAREN");
        RewriteRuleTokenStream stream_ME=new RewriteRuleTokenStream(adaptor,"token ME");
        RewriteRuleTokenStream stream_PARENT=new RewriteRuleTokenStream(adaptor,"token PARENT");
        RewriteRuleTokenStream stream_RIGHT_PAREN=new RewriteRuleTokenStream(adaptor,"token RIGHT_PAREN");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_qualified_name=new RewriteRuleSubtreeStream(adaptor,"rule qualified_name");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:714:2: ( qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN -> ^( SOLO_FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN ) | PARENT COLON qualified_name COLON ID LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN -> ^( SOLO_FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN ) | ME COLON qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN -> ^( SOLO_FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN ) )
            int alt43=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt43=1;
                }
                break;
            case PARENT:
                {
                alt43=2;
                }
                break;
            case ME:
                {
                alt43=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;

            }

            switch (alt43) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:715:2: qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN
                    {
                    pushFollow(FOLLOW_qualified_name_in_solo_method_call909);
                    qualified_name70=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name70.getTree());

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:715:17: ( COLON ID )?
                    int alt35=2;
                    int LA35_0 = input.LA(1);

                    if ( (LA35_0==COLON) ) {
                        alt35=1;
                    }
                    switch (alt35) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:715:18: COLON ID
                            {
                            COLON71=(Token)match(input,COLON,FOLLOW_COLON_in_solo_method_call912);  
                            stream_COLON.add(COLON71);


                            ID72=(Token)match(input,ID,FOLLOW_ID_in_solo_method_call914);  
                            stream_ID.add(ID72);


                            }
                            break;

                    }


                    LEFT_PAREN73=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_solo_method_call918);  
                    stream_LEFT_PAREN.add(LEFT_PAREN73);


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:715:40: ( expression ( COMMA expression )* )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==BOOLEAN||LA37_0==CAST||LA37_0==DECIMAL||LA37_0==ID||(LA37_0 >= INPUT && LA37_0 <= INT)||LA37_0==LEFT_PAREN||(LA37_0 >= ME && LA37_0 <= MINUS)||LA37_0==NOT||LA37_0==NULL||LA37_0==PARENT||LA37_0==STRING) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:715:41: expression ( COMMA expression )*
                            {
                            pushFollow(FOLLOW_expression_in_solo_method_call921);
                            expression74=expression();

                            state._fsp--;

                            stream_expression.add(expression74.getTree());

                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:715:52: ( COMMA expression )*
                            loop36:
                            do {
                                int alt36=2;
                                int LA36_0 = input.LA(1);

                                if ( (LA36_0==COMMA) ) {
                                    alt36=1;
                                }


                                switch (alt36) {
                            	case 1 :
                            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:715:53: COMMA expression
                            	    {
                            	    COMMA75=(Token)match(input,COMMA,FOLLOW_COMMA_in_solo_method_call924);  
                            	    stream_COMMA.add(COMMA75);


                            	    pushFollow(FOLLOW_expression_in_solo_method_call926);
                            	    expression76=expression();

                            	    state._fsp--;

                            	    stream_expression.add(expression76.getTree());

                            	    }
                            	    break;

                            	default :
                            	    break loop36;
                                }
                            } while (true);


                            }
                            break;

                    }


                    RIGHT_PAREN77=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_solo_method_call932);  
                    stream_RIGHT_PAREN.add(RIGHT_PAREN77);


                    // AST REWRITE
                    // elements: RIGHT_PAREN, expression, COMMA, expression, LEFT_PAREN, COLON, ID, qualified_name
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 715:86: -> ^( SOLO_FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:716:4: ^( SOLO_FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(SOLO_FUNCTION_CALL, "SOLO_FUNCTION_CALL")
                        , root_1);

                        adaptor.addChild(root_1, stream_qualified_name.nextTree());

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:716:40: ( COLON ID )?
                        if ( stream_COLON.hasNext()||stream_ID.hasNext() ) {
                            adaptor.addChild(root_1, 
                            stream_COLON.nextNode()
                            );

                            adaptor.addChild(root_1, 
                            stream_ID.nextNode()
                            );

                        }
                        stream_COLON.reset();
                        stream_ID.reset();

                        adaptor.addChild(root_1, 
                        stream_LEFT_PAREN.nextNode()
                        );

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:716:63: ( expression ( COMMA expression )* )?
                        if ( stream_COMMA.hasNext()||stream_expression.hasNext()||stream_expression.hasNext() ) {
                            adaptor.addChild(root_1, stream_expression.nextTree());

                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:716:75: ( COMMA expression )*
                            while ( stream_expression.hasNext()||stream_COMMA.hasNext() ) {
                                adaptor.addChild(root_1, 
                                stream_COMMA.nextNode()
                                );

                                adaptor.addChild(root_1, stream_expression.nextTree());

                            }
                            stream_expression.reset();
                            stream_COMMA.reset();

                        }
                        stream_COMMA.reset();
                        stream_expression.reset();
                        stream_expression.reset();

                        adaptor.addChild(root_1, 
                        stream_RIGHT_PAREN.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:717:4: PARENT COLON qualified_name COLON ID LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN
                    {
                    PARENT78=(Token)match(input,PARENT,FOLLOW_PARENT_in_solo_method_call971);  
                    stream_PARENT.add(PARENT78);


                    COLON79=(Token)match(input,COLON,FOLLOW_COLON_in_solo_method_call973);  
                    stream_COLON.add(COLON79);


                    pushFollow(FOLLOW_qualified_name_in_solo_method_call975);
                    qualified_name80=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name80.getTree());

                    COLON81=(Token)match(input,COLON,FOLLOW_COLON_in_solo_method_call977);  
                    stream_COLON.add(COLON81);


                    ID82=(Token)match(input,ID,FOLLOW_ID_in_solo_method_call979);  
                    stream_ID.add(ID82);


                    LEFT_PAREN83=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_solo_method_call981);  
                    stream_LEFT_PAREN.add(LEFT_PAREN83);


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:717:52: ( expression ( COMMA expression )* )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==BOOLEAN||LA39_0==CAST||LA39_0==DECIMAL||LA39_0==ID||(LA39_0 >= INPUT && LA39_0 <= INT)||LA39_0==LEFT_PAREN||(LA39_0 >= ME && LA39_0 <= MINUS)||LA39_0==NOT||LA39_0==NULL||LA39_0==PARENT||LA39_0==STRING) ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:717:53: expression ( COMMA expression )*
                            {
                            pushFollow(FOLLOW_expression_in_solo_method_call984);
                            expression84=expression();

                            state._fsp--;

                            stream_expression.add(expression84.getTree());

                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:717:64: ( COMMA expression )*
                            loop38:
                            do {
                                int alt38=2;
                                int LA38_0 = input.LA(1);

                                if ( (LA38_0==COMMA) ) {
                                    alt38=1;
                                }


                                switch (alt38) {
                            	case 1 :
                            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:717:65: COMMA expression
                            	    {
                            	    COMMA85=(Token)match(input,COMMA,FOLLOW_COMMA_in_solo_method_call987);  
                            	    stream_COMMA.add(COMMA85);


                            	    pushFollow(FOLLOW_expression_in_solo_method_call989);
                            	    expression86=expression();

                            	    state._fsp--;

                            	    stream_expression.add(expression86.getTree());

                            	    }
                            	    break;

                            	default :
                            	    break loop38;
                                }
                            } while (true);


                            }
                            break;

                    }


                    RIGHT_PAREN87=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_solo_method_call995);  
                    stream_RIGHT_PAREN.add(RIGHT_PAREN87);


                    // AST REWRITE
                    // elements: qualified_name, expression, PARENT, LEFT_PAREN, ID, COLON, expression, RIGHT_PAREN, COLON, COMMA
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 717:98: -> ^( SOLO_FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:718:4: ^( SOLO_FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(SOLO_FUNCTION_CALL_PARENT, "SOLO_FUNCTION_CALL_PARENT")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_PARENT.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        stream_COLON.nextNode()
                        );

                        adaptor.addChild(root_1, stream_qualified_name.nextTree());

                        adaptor.addChild(root_1, 
                        stream_COLON.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        stream_LEFT_PAREN.nextNode()
                        );

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:718:80: ( expression ( COMMA expression )* )?
                        if ( stream_expression.hasNext()||stream_expression.hasNext()||stream_COMMA.hasNext() ) {
                            adaptor.addChild(root_1, stream_expression.nextTree());

                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:718:92: ( COMMA expression )*
                            while ( stream_expression.hasNext()||stream_COMMA.hasNext() ) {
                                adaptor.addChild(root_1, 
                                stream_COMMA.nextNode()
                                );

                                adaptor.addChild(root_1, stream_expression.nextTree());

                            }
                            stream_expression.reset();
                            stream_COMMA.reset();

                        }
                        stream_expression.reset();
                        stream_expression.reset();
                        stream_COMMA.reset();

                        adaptor.addChild(root_1, 
                        stream_RIGHT_PAREN.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:719:4: ME COLON qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN
                    {
                    ME88=(Token)match(input,ME,FOLLOW_ME_in_solo_method_call1035);  
                    stream_ME.add(ME88);


                    COLON89=(Token)match(input,COLON,FOLLOW_COLON_in_solo_method_call1037);  
                    stream_COLON.add(COLON89);


                    pushFollow(FOLLOW_qualified_name_in_solo_method_call1039);
                    qualified_name90=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name90.getTree());

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:719:28: ( COLON ID )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==COLON) ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:719:29: COLON ID
                            {
                            COLON91=(Token)match(input,COLON,FOLLOW_COLON_in_solo_method_call1042);  
                            stream_COLON.add(COLON91);


                            ID92=(Token)match(input,ID,FOLLOW_ID_in_solo_method_call1044);  
                            stream_ID.add(ID92);


                            }
                            break;

                    }


                    LEFT_PAREN93=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_solo_method_call1048);  
                    stream_LEFT_PAREN.add(LEFT_PAREN93);


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:719:51: ( expression ( COMMA expression )* )?
                    int alt42=2;
                    int LA42_0 = input.LA(1);

                    if ( (LA42_0==BOOLEAN||LA42_0==CAST||LA42_0==DECIMAL||LA42_0==ID||(LA42_0 >= INPUT && LA42_0 <= INT)||LA42_0==LEFT_PAREN||(LA42_0 >= ME && LA42_0 <= MINUS)||LA42_0==NOT||LA42_0==NULL||LA42_0==PARENT||LA42_0==STRING) ) {
                        alt42=1;
                    }
                    switch (alt42) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:719:52: expression ( COMMA expression )*
                            {
                            pushFollow(FOLLOW_expression_in_solo_method_call1051);
                            expression94=expression();

                            state._fsp--;

                            stream_expression.add(expression94.getTree());

                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:719:63: ( COMMA expression )*
                            loop41:
                            do {
                                int alt41=2;
                                int LA41_0 = input.LA(1);

                                if ( (LA41_0==COMMA) ) {
                                    alt41=1;
                                }


                                switch (alt41) {
                            	case 1 :
                            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:719:64: COMMA expression
                            	    {
                            	    COMMA95=(Token)match(input,COMMA,FOLLOW_COMMA_in_solo_method_call1054);  
                            	    stream_COMMA.add(COMMA95);


                            	    pushFollow(FOLLOW_expression_in_solo_method_call1056);
                            	    expression96=expression();

                            	    state._fsp--;

                            	    stream_expression.add(expression96.getTree());

                            	    }
                            	    break;

                            	default :
                            	    break loop41;
                                }
                            } while (true);


                            }
                            break;

                    }


                    RIGHT_PAREN97=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_solo_method_call1062);  
                    stream_RIGHT_PAREN.add(RIGHT_PAREN97);


                    // AST REWRITE
                    // elements: ME, COLON, expression, COMMA, COLON, expression, ID, RIGHT_PAREN, LEFT_PAREN, qualified_name
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 719:97: -> ^( SOLO_FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:720:4: ^( SOLO_FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(SOLO_FUNCTION_CALL_THIS, "SOLO_FUNCTION_CALL_THIS")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ME.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        stream_COLON.nextNode()
                        );

                        adaptor.addChild(root_1, stream_qualified_name.nextTree());

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:720:54: ( COLON ID )?
                        if ( stream_COLON.hasNext()||stream_ID.hasNext() ) {
                            adaptor.addChild(root_1, 
                            stream_COLON.nextNode()
                            );

                            adaptor.addChild(root_1, 
                            stream_ID.nextNode()
                            );

                        }
                        stream_COLON.reset();
                        stream_ID.reset();

                        adaptor.addChild(root_1, 
                        stream_LEFT_PAREN.nextNode()
                        );

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:720:77: ( expression ( COMMA expression )* )?
                        if ( stream_expression.hasNext()||stream_COMMA.hasNext()||stream_expression.hasNext() ) {
                            adaptor.addChild(root_1, stream_expression.nextTree());

                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:720:89: ( COMMA expression )*
                            while ( stream_expression.hasNext()||stream_COMMA.hasNext() ) {
                                adaptor.addChild(root_1, 
                                stream_COMMA.nextNode()
                                );

                                adaptor.addChild(root_1, stream_expression.nextTree());

                            }
                            stream_expression.reset();
                            stream_COMMA.reset();

                        }
                        stream_expression.reset();
                        stream_COMMA.reset();
                        stream_expression.reset();

                        adaptor.addChild(root_1, 
                        stream_RIGHT_PAREN.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "solo_method_call"


    public static class alert_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "alert_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:723:1: alert_statement : ALERT LEFT_PAREN root_expression RIGHT_PAREN -> ^( ALERT LEFT_PAREN root_expression RIGHT_PAREN ) ;
    public final QuorumParser.alert_statement_return alert_statement() throws RecognitionException {
        QuorumParser.alert_statement_return retval = new QuorumParser.alert_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ALERT98=null;
        Token LEFT_PAREN99=null;
        Token RIGHT_PAREN101=null;
        QuorumParser.root_expression_return root_expression100 =null;


        CommonTree ALERT98_tree=null;
        CommonTree LEFT_PAREN99_tree=null;
        CommonTree RIGHT_PAREN101_tree=null;
        RewriteRuleTokenStream stream_LEFT_PAREN=new RewriteRuleTokenStream(adaptor,"token LEFT_PAREN");
        RewriteRuleTokenStream stream_RIGHT_PAREN=new RewriteRuleTokenStream(adaptor,"token RIGHT_PAREN");
        RewriteRuleTokenStream stream_ALERT=new RewriteRuleTokenStream(adaptor,"token ALERT");
        RewriteRuleSubtreeStream stream_root_expression=new RewriteRuleSubtreeStream(adaptor,"rule root_expression");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:724:2: ( ALERT LEFT_PAREN root_expression RIGHT_PAREN -> ^( ALERT LEFT_PAREN root_expression RIGHT_PAREN ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:724:4: ALERT LEFT_PAREN root_expression RIGHT_PAREN
            {
            ALERT98=(Token)match(input,ALERT,FOLLOW_ALERT_in_alert_statement1113);  
            stream_ALERT.add(ALERT98);


            LEFT_PAREN99=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_alert_statement1115);  
            stream_LEFT_PAREN.add(LEFT_PAREN99);


            pushFollow(FOLLOW_root_expression_in_alert_statement1117);
            root_expression100=root_expression();

            state._fsp--;

            stream_root_expression.add(root_expression100.getTree());

            RIGHT_PAREN101=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_alert_statement1120);  
            stream_RIGHT_PAREN.add(RIGHT_PAREN101);


            // AST REWRITE
            // elements: RIGHT_PAREN, root_expression, ALERT, LEFT_PAREN
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 725:2: -> ^( ALERT LEFT_PAREN root_expression RIGHT_PAREN )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:725:5: ^( ALERT LEFT_PAREN root_expression RIGHT_PAREN )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                stream_ALERT.nextNode()
                , root_1);

                adaptor.addChild(root_1, 
                stream_LEFT_PAREN.nextNode()
                );

                adaptor.addChild(root_1, stream_root_expression.nextTree());

                adaptor.addChild(root_1, 
                stream_RIGHT_PAREN.nextNode()
                );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "alert_statement"


    public static class check_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "check_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:728:1: check_statement : check_start= CHECK block ( (detect_start= DETECT detect_parameter block )+ (always_start= ALWAYS block )? |always_start_2= ALWAYS block ) end= END ;
    public final QuorumParser.check_statement_return check_statement() throws RecognitionException {
        QuorumParser.check_statement_return retval = new QuorumParser.check_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token check_start=null;
        Token detect_start=null;
        Token always_start=null;
        Token always_start_2=null;
        Token end=null;
        QuorumParser.block_return block102 =null;

        QuorumParser.detect_parameter_return detect_parameter103 =null;

        QuorumParser.block_return block104 =null;

        QuorumParser.block_return block105 =null;

        QuorumParser.block_return block106 =null;


        CommonTree check_start_tree=null;
        CommonTree detect_start_tree=null;
        CommonTree always_start_tree=null;
        CommonTree always_start_2_tree=null;
        CommonTree end_tree=null;


        	BlockDescriptor block;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:732:2: (check_start= CHECK block ( (detect_start= DETECT detect_parameter block )+ (always_start= ALWAYS block )? |always_start_2= ALWAYS block ) end= END )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:732:6: check_start= CHECK block ( (detect_start= DETECT detect_parameter block )+ (always_start= ALWAYS block )? |always_start_2= ALWAYS block ) end= END
            {
            root_0 = (CommonTree)adaptor.nil();


            check_start=(Token)match(input,CHECK,FOLLOW_CHECK_in_check_statement1154); 
            check_start_tree = 
            (CommonTree)adaptor.create(check_start)
            ;
            adaptor.addChild(root_0, check_start_tree);


             block = new BlockDescriptor(); symbol.add(block); 

            pushFollow(FOLLOW_block_in_check_statement1157);
            block102=block();

            state._fsp--;

            adaptor.addChild(root_0, block102.getTree());

             
            		symbol.popScope(); 
                   		//set the begin and end line column information in the block descriptors.
                   		block.setLineBegin(check_start.getLine());
                   		block.setColumnBegin(check_start.getCharPositionInLine());
            	

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:739:6: ( (detect_start= DETECT detect_parameter block )+ (always_start= ALWAYS block )? |always_start_2= ALWAYS block )
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==DETECT) ) {
                alt46=1;
            }
            else if ( (LA46_0==ALWAYS) ) {
                alt46=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;

            }
            switch (alt46) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:739:7: (detect_start= DETECT detect_parameter block )+ (always_start= ALWAYS block )?
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:739:7: (detect_start= DETECT detect_parameter block )+
                    int cnt44=0;
                    loop44:
                    do {
                        int alt44=2;
                        int LA44_0 = input.LA(1);

                        if ( (LA44_0==DETECT) ) {
                            alt44=1;
                        }


                        switch (alt44) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:739:8: detect_start= DETECT detect_parameter block
                    	    {
                    	    detect_start=(Token)match(input,DETECT,FOLLOW_DETECT_in_check_statement1172); 
                    	    detect_start_tree = 
                    	    (CommonTree)adaptor.create(detect_start)
                    	    ;
                    	    adaptor.addChild(root_0, detect_start_tree);


                    	     
                    	    		block.setLineEnd(detect_start.getLine());
                    	    		block.setColumnEnd((detect_start!=null?detect_start.getText():null).length() + detect_start.getCharPositionInLine());
                    	    		
                    	    		block = new BlockDescriptor(); 
                    	    		symbol.add(block); 
                    	    	

                    	    pushFollow(FOLLOW_detect_parameter_in_check_statement1184);
                    	    detect_parameter103=detect_parameter();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, detect_parameter103.getTree());

                    	    pushFollow(FOLLOW_block_in_check_statement1186);
                    	    block104=block();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, block104.getTree());

                    	     symbol.popScope(); 


                    	           		//set the begin and end line column information in the block descriptors.
                    	           		block.setLineBegin(detect_start.getLine());
                    	           		block.setColumnBegin(detect_start.getCharPositionInLine());
                    	    	

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt44 >= 1 ) break loop44;
                                EarlyExitException eee =
                                    new EarlyExitException(44, input);
                                throw eee;
                        }
                        cnt44++;
                    } while (true);


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:754:6: (always_start= ALWAYS block )?
                    int alt45=2;
                    int LA45_0 = input.LA(1);

                    if ( (LA45_0==ALWAYS) ) {
                        alt45=1;
                    }
                    switch (alt45) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:754:7: always_start= ALWAYS block
                            {
                            always_start=(Token)match(input,ALWAYS,FOLLOW_ALWAYS_in_check_statement1210); 
                            always_start_tree = 
                            (CommonTree)adaptor.create(always_start)
                            ;
                            adaptor.addChild(root_0, always_start_tree);


                             
                            		block.setLineEnd(always_start.getLine()); 
                            		block.setColumnEnd((always_start!=null?always_start.getText():null).length() + always_start.getCharPositionInLine()); 
                            		
                            		block = new BlockDescriptor(); 
                            		symbol.add(block); 
                            	

                            pushFollow(FOLLOW_block_in_check_statement1221);
                            block105=block();

                            state._fsp--;

                            adaptor.addChild(root_0, block105.getTree());

                             
                            		symbol.popScope(); 
                                   		//set the begin and end line column information in the block descriptors.
                                   		block.setLineBegin(always_start.getLine());
                                   		block.setColumnBegin(always_start.getCharPositionInLine());
                            	

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:770:6: always_start_2= ALWAYS block
                    {
                    always_start_2=(Token)match(input,ALWAYS,FOLLOW_ALWAYS_in_check_statement1243); 
                    always_start_2_tree = 
                    (CommonTree)adaptor.create(always_start_2)
                    ;
                    adaptor.addChild(root_0, always_start_2_tree);


                     
                    		block.setLineEnd(always_start_2.getLine()); 
                    		block.setColumnEnd((always_start_2!=null?always_start_2.getText():null).length() + always_start_2.getCharPositionInLine());
                    		
                    		block = new BlockDescriptor(); 
                    		symbol.add(block); 
                    	

                    pushFollow(FOLLOW_block_in_check_statement1254);
                    block106=block();

                    state._fsp--;

                    adaptor.addChild(root_0, block106.getTree());

                     
                    		symbol.popScope();
                           		//set the begin and end line column information in the block descriptors.
                           		block.setLineBegin(always_start_2.getLine());
                           		block.setColumnBegin(always_start_2.getCharPositionInLine());
                    	

                    }
                    break;

            }


            end=(Token)match(input,END,FOLLOW_END_in_check_statement1269); 
            end_tree = 
            (CommonTree)adaptor.create(end)
            ;
            adaptor.addChild(root_0, end_tree);



            		block.setLineEnd(end.getLine());
            		block.setColumnEnd((end!=null?end.getText():null).length() + end.getCharPositionInLine());
            	

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "check_statement"


    public static class detect_parameter_return extends ParserRuleReturnScope {
        public String name;
        public ArrayList<ErrorTypeDescriptor> exceptionTypeList;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "detect_parameter"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:792:1: detect_parameter returns [String name, ArrayList<ErrorTypeDescriptor> exceptionTypeList] : ID ( OF_TYPE qualified_name ( OR qualified_name )* )? -> ^( ID ( OF_TYPE qualified_name ( OR qualified_name )* )? ) ;
    public final QuorumParser.detect_parameter_return detect_parameter() throws RecognitionException {
        QuorumParser.detect_parameter_return retval = new QuorumParser.detect_parameter_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ID107=null;
        Token OF_TYPE108=null;
        Token OR110=null;
        QuorumParser.qualified_name_return qualified_name109 =null;

        QuorumParser.qualified_name_return qualified_name111 =null;


        CommonTree ID107_tree=null;
        CommonTree OF_TYPE108_tree=null;
        CommonTree OR110_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_OF_TYPE=new RewriteRuleTokenStream(adaptor,"token OF_TYPE");
        RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
        RewriteRuleSubtreeStream stream_qualified_name=new RewriteRuleSubtreeStream(adaptor,"rule qualified_name");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:793:2: ( ID ( OF_TYPE qualified_name ( OR qualified_name )* )? -> ^( ID ( OF_TYPE qualified_name ( OR qualified_name )* )? ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:793:5: ID ( OF_TYPE qualified_name ( OR qualified_name )* )?
            {
            ID107=(Token)match(input,ID,FOLLOW_ID_in_detect_parameter1297);  
            stream_ID.add(ID107);



            	VariableDescriptor new_desc = new VariableDescriptor();
            	TypeDescriptor type = new TypeDescriptor();
            	type.setName("Libraries.Language.Errors.Error");
            	
            	type.setLineBegin(ID107.getLine());
            	type.setColumnBegin(ID107.getCharPositionInLine());
            			
            	new_desc.setAccessModifier(AccessModifierEnum.PRIVATE);
            	           		
            	           		
            	new_desc.setType(type);
            	new_desc.setName((ID107!=null?ID107.getText():null));
                       		
            	CompilerError error = symbol.add(new_desc);
            	if(error != null) {
            		error.setLineNumber(ID107.getLine());
            		error.setColumn(ID107.getCharPositionInLine());
            		vm.getCompilerErrors().addError(error);
            	}	
            	

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:817:2: ( OF_TYPE qualified_name ( OR qualified_name )* )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==OF_TYPE) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:817:3: OF_TYPE qualified_name ( OR qualified_name )*
                    {
                    OF_TYPE108=(Token)match(input,OF_TYPE,FOLLOW_OF_TYPE_in_detect_parameter1309);  
                    stream_OF_TYPE.add(OF_TYPE108);


                    pushFollow(FOLLOW_qualified_name_in_detect_parameter1311);
                    qualified_name109=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name109.getTree());

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:817:25: ( OR qualified_name )*
                    loop47:
                    do {
                        int alt47=2;
                        int LA47_0 = input.LA(1);

                        if ( (LA47_0==OR) ) {
                            alt47=1;
                        }


                        switch (alt47) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:817:26: OR qualified_name
                    	    {
                    	    OR110=(Token)match(input,OR,FOLLOW_OR_in_detect_parameter1313);  
                    	    stream_OR.add(OR110);


                    	    pushFollow(FOLLOW_qualified_name_in_detect_parameter1315);
                    	    qualified_name111=qualified_name();

                    	    state._fsp--;

                    	    stream_qualified_name.add(qualified_name111.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop47;
                        }
                    } while (true);


                    }
                    break;

            }


            // AST REWRITE
            // elements: OR, OF_TYPE, qualified_name, qualified_name, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 818:2: -> ^( ID ( OF_TYPE qualified_name ( OR qualified_name )* )? )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:818:5: ^( ID ( OF_TYPE qualified_name ( OR qualified_name )* )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                stream_ID.nextNode()
                , root_1);

                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:818:10: ( OF_TYPE qualified_name ( OR qualified_name )* )?
                if ( stream_OR.hasNext()||stream_OF_TYPE.hasNext()||stream_qualified_name.hasNext()||stream_qualified_name.hasNext() ) {
                    adaptor.addChild(root_1, 
                    stream_OF_TYPE.nextNode()
                    );

                    adaptor.addChild(root_1, stream_qualified_name.nextTree());

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:818:33: ( OR qualified_name )*
                    while ( stream_OR.hasNext()||stream_qualified_name.hasNext() ) {
                        adaptor.addChild(root_1, 
                        stream_OR.nextNode()
                        );

                        adaptor.addChild(root_1, stream_qualified_name.nextTree());

                    }
                    stream_OR.reset();
                    stream_qualified_name.reset();

                }
                stream_OR.reset();
                stream_OF_TYPE.reset();
                stream_qualified_name.reset();
                stream_qualified_name.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "detect_parameter"


    public static class print_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "print_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:820:1: print_statement : PRINT root_expression ;
    public final QuorumParser.print_statement_return print_statement() throws RecognitionException {
        QuorumParser.print_statement_return retval = new QuorumParser.print_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token PRINT112=null;
        QuorumParser.root_expression_return root_expression113 =null;


        CommonTree PRINT112_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:821:2: ( PRINT root_expression )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:821:4: PRINT root_expression
            {
            root_0 = (CommonTree)adaptor.nil();


            PRINT112=(Token)match(input,PRINT,FOLLOW_PRINT_in_print_statement1350); 
            PRINT112_tree = 
            (CommonTree)adaptor.create(PRINT112)
            ;
            adaptor.addChild(root_0, PRINT112_tree);


            pushFollow(FOLLOW_root_expression_in_print_statement1352);
            root_expression113=root_expression();

            state._fsp--;

            adaptor.addChild(root_0, root_expression113.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "print_statement"


    public static class speak_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "speak_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:824:1: speak_statement : SAY root_expression ;
    public final QuorumParser.speak_statement_return speak_statement() throws RecognitionException {
        QuorumParser.speak_statement_return retval = new QuorumParser.speak_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token SAY114=null;
        QuorumParser.root_expression_return root_expression115 =null;


        CommonTree SAY114_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:825:2: ( SAY root_expression )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:825:4: SAY root_expression
            {
            root_0 = (CommonTree)adaptor.nil();


            SAY114=(Token)match(input,SAY,FOLLOW_SAY_in_speak_statement1364); 
            SAY114_tree = 
            (CommonTree)adaptor.create(SAY114)
            ;
            adaptor.addChild(root_0, SAY114_tree);


            pushFollow(FOLLOW_root_expression_in_speak_statement1366);
            root_expression115=root_expression();

            state._fsp--;

            adaptor.addChild(root_0, root_expression115.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "speak_statement"


    public static class return_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "return_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:828:1: return_statement : RETURN ( root_expression | NOW ) ;
    public final QuorumParser.return_statement_return return_statement() throws RecognitionException {
        QuorumParser.return_statement_return retval = new QuorumParser.return_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token RETURN116=null;
        Token NOW118=null;
        QuorumParser.root_expression_return root_expression117 =null;


        CommonTree RETURN116_tree=null;
        CommonTree NOW118_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:829:2: ( RETURN ( root_expression | NOW ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:829:4: RETURN ( root_expression | NOW )
            {
            root_0 = (CommonTree)adaptor.nil();


            RETURN116=(Token)match(input,RETURN,FOLLOW_RETURN_in_return_statement1377); 
            RETURN116_tree = 
            (CommonTree)adaptor.create(RETURN116)
            ;
            adaptor.addChild(root_0, RETURN116_tree);


            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:829:11: ( root_expression | NOW )
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==BOOLEAN||LA49_0==CAST||LA49_0==DECIMAL||LA49_0==ID||(LA49_0 >= INPUT && LA49_0 <= INT)||LA49_0==LEFT_PAREN||(LA49_0 >= ME && LA49_0 <= MINUS)||LA49_0==NOT||LA49_0==NULL||LA49_0==PARENT||LA49_0==STRING) ) {
                alt49=1;
            }
            else if ( (LA49_0==NOW) ) {
                alt49=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;

            }
            switch (alt49) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:829:13: root_expression
                    {
                    pushFollow(FOLLOW_root_expression_in_return_statement1381);
                    root_expression117=root_expression();

                    state._fsp--;

                    adaptor.addChild(root_0, root_expression117.getTree());

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:829:31: NOW
                    {
                    NOW118=(Token)match(input,NOW,FOLLOW_NOW_in_return_statement1385); 
                    NOW118_tree = 
                    (CommonTree)adaptor.create(NOW118)
                    ;
                    adaptor.addChild(root_0, NOW118_tree);


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "return_statement"


    public static class generic_declaration_return extends ParserRuleReturnScope {
        public ArrayList genericTypeList;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "generic_declaration"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:832:1: generic_declaration returns [ArrayList genericTypeList] : LESS ids+= ID ( COMMA ids+= ID )* GREATER -> ^( GENERIC LESS ID ( COMMA ID )* GREATER ) ;
    public final QuorumParser.generic_declaration_return generic_declaration() throws RecognitionException {
        QuorumParser.generic_declaration_return retval = new QuorumParser.generic_declaration_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token LESS119=null;
        Token COMMA120=null;
        Token GREATER121=null;
        Token ids=null;
        List list_ids=null;

        CommonTree LESS119_tree=null;
        CommonTree COMMA120_tree=null;
        CommonTree GREATER121_tree=null;
        CommonTree ids_tree=null;
        RewriteRuleTokenStream stream_GREATER=new RewriteRuleTokenStream(adaptor,"token GREATER");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_LESS=new RewriteRuleTokenStream(adaptor,"token LESS");

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:833:2: ( LESS ids+= ID ( COMMA ids+= ID )* GREATER -> ^( GENERIC LESS ID ( COMMA ID )* GREATER ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:833:4: LESS ids+= ID ( COMMA ids+= ID )* GREATER
            {
            LESS119=(Token)match(input,LESS,FOLLOW_LESS_in_generic_declaration1402);  
            stream_LESS.add(LESS119);


            ids=(Token)match(input,ID,FOLLOW_ID_in_generic_declaration1406);  
            stream_ID.add(ids);

            if (list_ids==null) list_ids=new ArrayList();
            list_ids.add(ids);


            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:833:17: ( COMMA ids+= ID )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==COMMA) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:833:18: COMMA ids+= ID
            	    {
            	    COMMA120=(Token)match(input,COMMA,FOLLOW_COMMA_in_generic_declaration1409);  
            	    stream_COMMA.add(COMMA120);


            	    ids=(Token)match(input,ID,FOLLOW_ID_in_generic_declaration1413);  
            	    stream_ID.add(ids);

            	    if (list_ids==null) list_ids=new ArrayList();
            	    list_ids.add(ids);


            	    }
            	    break;

            	default :
            	    break loop50;
                }
            } while (true);


            GREATER121=(Token)match(input,GREATER,FOLLOW_GREATER_in_generic_declaration1417);  
            stream_GREATER.add(GREATER121);



            		ArrayList<GenericDescriptor> gd = new ArrayList<GenericDescriptor>();
            		Iterator it = list_ids.iterator();
            		
            		while(it.hasNext()){
            		
            			CommonToken t = ((CommonToken) it.next());
            			
            			GenericDescriptor genericType = new GenericDescriptor();
            			genericType.setLineBegin(((Token)list_ids.get(0)).getLine());
            			genericType.setColumnBegin(((Token)list_ids.get(0)).getCharPositionInLine());
            			genericType.setLineEnd(((Token)list_ids.get(list_ids.size() - 1)).getLine());
            			genericType.setColumnEnd(((Token)list_ids.get(list_ids.size() - 1)).getCharPositionInLine());
            			
            			genericType.setName(t.getText());

            			gd.add(genericType);
            			
            		}
            		
            		retval.genericTypeList = gd;
            	

            // AST REWRITE
            // elements: LESS, COMMA, GREATER, ID, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 856:2: -> ^( GENERIC LESS ID ( COMMA ID )* GREATER )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:856:5: ^( GENERIC LESS ID ( COMMA ID )* GREATER )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(GENERIC, "GENERIC")
                , root_1);

                adaptor.addChild(root_1, 
                stream_LESS.nextNode()
                );

                adaptor.addChild(root_1, 
                stream_ID.nextNode()
                );

                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:856:23: ( COMMA ID )*
                while ( stream_COMMA.hasNext()||stream_ID.hasNext() ) {
                    adaptor.addChild(root_1, 
                    stream_COMMA.nextNode()
                    );

                    adaptor.addChild(root_1, 
                    stream_ID.nextNode()
                    );

                }
                stream_COMMA.reset();
                stream_ID.reset();

                adaptor.addChild(root_1, 
                stream_GREATER.nextNode()
                );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "generic_declaration"


    protected static class generic_statement_scope {
        ArrayList<TypeDescriptor> typeList;
    }
    protected Stack generic_statement_stack = new Stack();


    public static class generic_statement_return extends ParserRuleReturnScope {
        public ArrayList genericTypeList;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "generic_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:858:1: generic_statement returns [ArrayList genericTypeList] : LESS type= assignment_declaration ( COMMA type= assignment_declaration )* GREATER -> ^( GENERIC LESS assignment_declaration ( COMMA assignment_declaration )* GREATER ) ;
    public final QuorumParser.generic_statement_return generic_statement() throws RecognitionException {
        generic_statement_stack.push(new generic_statement_scope());
        QuorumParser.generic_statement_return retval = new QuorumParser.generic_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token LESS122=null;
        Token COMMA123=null;
        Token GREATER124=null;
        QuorumParser.assignment_declaration_return type =null;


        CommonTree LESS122_tree=null;
        CommonTree COMMA123_tree=null;
        CommonTree GREATER124_tree=null;
        RewriteRuleTokenStream stream_GREATER=new RewriteRuleTokenStream(adaptor,"token GREATER");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_LESS=new RewriteRuleTokenStream(adaptor,"token LESS");
        RewriteRuleSubtreeStream stream_assignment_declaration=new RewriteRuleSubtreeStream(adaptor,"rule assignment_declaration");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:862:2: ( LESS type= assignment_declaration ( COMMA type= assignment_declaration )* GREATER -> ^( GENERIC LESS assignment_declaration ( COMMA assignment_declaration )* GREATER ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:862:4: LESS type= assignment_declaration ( COMMA type= assignment_declaration )* GREATER
            {
            LESS122=(Token)match(input,LESS,FOLLOW_LESS_in_generic_statement1458);  
            stream_LESS.add(LESS122);



            		((generic_statement_scope)generic_statement_stack.peek()).typeList = new ArrayList<TypeDescriptor>();
            	

            pushFollow(FOLLOW_assignment_declaration_in_generic_statement1468);
            type=assignment_declaration();

            state._fsp--;

            stream_assignment_declaration.add(type.getTree());


            		((generic_statement_scope)generic_statement_stack.peek()).typeList.add((type!=null?type.type:null));
            	

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:870:2: ( COMMA type= assignment_declaration )*
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==COMMA) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:870:3: COMMA type= assignment_declaration
            	    {
            	    COMMA123=(Token)match(input,COMMA,FOLLOW_COMMA_in_generic_statement1475);  
            	    stream_COMMA.add(COMMA123);


            	    pushFollow(FOLLOW_assignment_declaration_in_generic_statement1479);
            	    type=assignment_declaration();

            	    state._fsp--;

            	    stream_assignment_declaration.add(type.getTree());


            	    		((generic_statement_scope)generic_statement_stack.peek()).typeList.add((type!=null?type.type:null));
            	    	

            	    }
            	    break;

            	default :
            	    break loop51;
                }
            } while (true);


            GREATER124=(Token)match(input,GREATER,FOLLOW_GREATER_in_generic_statement1489);  
            stream_GREATER.add(GREATER124);



            		ArrayList<GenericDescriptor> gd = new ArrayList<GenericDescriptor>();
            		Iterator<TypeDescriptor> it = ((generic_statement_scope)generic_statement_stack.peek()).typeList.iterator();
            		
            		while(it.hasNext()){
            			//CommonTree tree = it.next();
            			TypeDescriptor t = it.next();
            			//t.convertToClass();
            			
            			GenericDescriptor genericType = new GenericDescriptor();
            			genericType.setLineBegin(t.getLineBegin());
            			genericType.setColumnBegin(t.getColumnBegin());
            			genericType.setLineEnd(t.getLineEnd());
            			genericType.setColumnEnd(t.getColumnEnd());
            			
            			genericType.setName(t.getName());
            			//t.setSubTypes(gen);
            			genericType.addBoundType(t);
            			
            		
            			gd.add(genericType);
            		}
            		
            		retval.genericTypeList = gd;
            	

            // AST REWRITE
            // elements: COMMA, LESS, assignment_declaration, assignment_declaration, GREATER
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 900:2: -> ^( GENERIC LESS assignment_declaration ( COMMA assignment_declaration )* GREATER )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:900:5: ^( GENERIC LESS assignment_declaration ( COMMA assignment_declaration )* GREATER )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(GENERIC, "GENERIC")
                , root_1);

                adaptor.addChild(root_1, 
                stream_LESS.nextNode()
                );

                adaptor.addChild(root_1, stream_assignment_declaration.nextTree());

                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:900:42: ( COMMA assignment_declaration )*
                while ( stream_COMMA.hasNext()||stream_assignment_declaration.hasNext() ) {
                    adaptor.addChild(root_1, 
                    stream_COMMA.nextNode()
                    );

                    adaptor.addChild(root_1, stream_assignment_declaration.nextTree());

                }
                stream_COMMA.reset();
                stream_assignment_declaration.reset();

                adaptor.addChild(root_1, 
                stream_GREATER.nextNode()
                );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            generic_statement_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "generic_statement"


    public static class class_type_return extends ParserRuleReturnScope {
        public TypeDescriptor type;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "class_type"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:902:1: class_type returns [TypeDescriptor type] : qn= qualified_name ;
    public final QuorumParser.class_type_return class_type() throws RecognitionException {
        QuorumParser.class_type_return retval = new QuorumParser.class_type_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        QuorumParser.qualified_name_return qn =null;



        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:903:2: (qn= qualified_name )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:903:4: qn= qualified_name
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_qualified_name_in_class_type1529);
            qn=qualified_name();

            state._fsp--;

            adaptor.addChild(root_0, qn.getTree());


            		TypeDescriptor t = new TypeDescriptor();
            		GenericDescriptor gd = symbol.getCurrentClass().getTemplateVariable((qn!=null?qn.type:null).getStaticKey());
            		if(gd != null){
            			t.setName(gd.getType().getStaticKey());
            			t.setTemplateName((qn!=null?qn.type:null).getStaticKey());
            		}else{
            			t.setName((qn!=null?qn.type:null).getStaticKey());
            		}

            		t.setLineBegin((qn!=null?qn.type:null).getLineBegin());
            		t.setColumnBegin((qn!=null?qn.type:null).getColumnBegin());
            			
            		retval.type =t;
            	

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "class_type"


    public static class assignment_declaration_return extends ParserRuleReturnScope {
        public TypeDescriptor type;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assignment_declaration"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:920:1: assignment_declaration returns [TypeDescriptor type] : (qn= qualified_name (gs= generic_statement )? | INTEGER_KEYWORD | NUMBER_KEYWORD | TEXT | BOOLEAN_KEYWORD );
    public final QuorumParser.assignment_declaration_return assignment_declaration() throws RecognitionException {
        QuorumParser.assignment_declaration_return retval = new QuorumParser.assignment_declaration_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token INTEGER_KEYWORD125=null;
        Token NUMBER_KEYWORD126=null;
        Token TEXT127=null;
        Token BOOLEAN_KEYWORD128=null;
        QuorumParser.qualified_name_return qn =null;

        QuorumParser.generic_statement_return gs =null;


        CommonTree INTEGER_KEYWORD125_tree=null;
        CommonTree NUMBER_KEYWORD126_tree=null;
        CommonTree TEXT127_tree=null;
        CommonTree BOOLEAN_KEYWORD128_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:921:2: (qn= qualified_name (gs= generic_statement )? | INTEGER_KEYWORD | NUMBER_KEYWORD | TEXT | BOOLEAN_KEYWORD )
            int alt53=5;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt53=1;
                }
                break;
            case INTEGER_KEYWORD:
                {
                alt53=2;
                }
                break;
            case NUMBER_KEYWORD:
                {
                alt53=3;
                }
                break;
            case TEXT:
                {
                alt53=4;
                }
                break;
            case BOOLEAN_KEYWORD:
                {
                alt53=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;

            }

            switch (alt53) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:921:4: qn= qualified_name (gs= generic_statement )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_qualified_name_in_assignment_declaration1550);
                    qn=qualified_name();

                    state._fsp--;

                    adaptor.addChild(root_0, qn.getTree());

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:921:26: (gs= generic_statement )?
                    int alt52=2;
                    int LA52_0 = input.LA(1);

                    if ( (LA52_0==LESS) ) {
                        alt52=1;
                    }
                    switch (alt52) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:921:26: gs= generic_statement
                            {
                            pushFollow(FOLLOW_generic_statement_in_assignment_declaration1554);
                            gs=generic_statement();

                            state._fsp--;

                            adaptor.addChild(root_0, gs.getTree());

                            }
                            break;

                    }



                    		TypeDescriptor t = new TypeDescriptor();
                    		GenericDescriptor gd = symbol.getCurrentClass().getTemplateVariable((qn!=null?qn.type:null).getStaticKey());
                    		if(gd != null){
                    			t.setName(gd.getType().getStaticKey());
                    			t.setTemplateName((qn!=null?qn.type:null).getStaticKey());
                    			
                    			if(gs != null){
                    				t.setSubTypes((gs!=null?gs.genericTypeList:null));
                    			}
                    		}else{
                    			t.setName((qn!=null?qn.type:null).getStaticKey());
                    			if(gs != null){
                    				t.setSubTypes((gs!=null?gs.genericTypeList:null));
                    			}
                    		}

                    		t.setLineBegin((qn!=null?qn.type:null).getLineBegin());
                    		t.setColumnBegin((qn!=null?qn.type:null).getColumnBegin());
                    			
                    		retval.type =t;	
                    	

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:944:4: INTEGER_KEYWORD
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    INTEGER_KEYWORD125=(Token)match(input,INTEGER_KEYWORD,FOLLOW_INTEGER_KEYWORD_in_assignment_declaration1563); 
                    INTEGER_KEYWORD125_tree = 
                    (CommonTree)adaptor.create(INTEGER_KEYWORD125)
                    ;
                    adaptor.addChild(root_0, INTEGER_KEYWORD125_tree);



                    		TypeDescriptor t = new TypeDescriptor();
                    		t.setName(TypeDescriptor.INTEGER);
                    		t.setLineBegin(INTEGER_KEYWORD125.getLine());
                    		t.setColumnBegin(INTEGER_KEYWORD125.getCharPositionInLine());
                    		retval.type = t;
                    	

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:952:4: NUMBER_KEYWORD
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    NUMBER_KEYWORD126=(Token)match(input,NUMBER_KEYWORD,FOLLOW_NUMBER_KEYWORD_in_assignment_declaration1571); 
                    NUMBER_KEYWORD126_tree = 
                    (CommonTree)adaptor.create(NUMBER_KEYWORD126)
                    ;
                    adaptor.addChild(root_0, NUMBER_KEYWORD126_tree);



                    		TypeDescriptor t = new TypeDescriptor();
                    		t.setName(TypeDescriptor.NUMBER);
                    		t.setLineBegin(NUMBER_KEYWORD126.getLine());
                    		t.setColumnBegin(NUMBER_KEYWORD126.getCharPositionInLine());
                    		retval.type = t;
                    	

                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:960:4: TEXT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    TEXT127=(Token)match(input,TEXT,FOLLOW_TEXT_in_assignment_declaration1579); 
                    TEXT127_tree = 
                    (CommonTree)adaptor.create(TEXT127)
                    ;
                    adaptor.addChild(root_0, TEXT127_tree);



                    		TypeDescriptor t = new TypeDescriptor();
                    		t.setName(TypeDescriptor.TEXT);
                    		t.setLineBegin(TEXT127.getLine());
                    		t.setColumnBegin(TEXT127.getCharPositionInLine());
                    		retval.type = t;
                    	

                    }
                    break;
                case 5 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:968:4: BOOLEAN_KEYWORD
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    BOOLEAN_KEYWORD128=(Token)match(input,BOOLEAN_KEYWORD,FOLLOW_BOOLEAN_KEYWORD_in_assignment_declaration1587); 
                    BOOLEAN_KEYWORD128_tree = 
                    (CommonTree)adaptor.create(BOOLEAN_KEYWORD128)
                    ;
                    adaptor.addChild(root_0, BOOLEAN_KEYWORD128_tree);



                    		TypeDescriptor t = new TypeDescriptor();
                    		t.setName(TypeDescriptor.BOOLEAN);
                    		t.setLineBegin(BOOLEAN_KEYWORD128.getLine());
                    		t.setColumnBegin(BOOLEAN_KEYWORD128.getCharPositionInLine());
                    		retval.type = t;
                    	

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "assignment_declaration"


    public static class assignment_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assignment_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:976:1: assignment_statement : ( (sel= selector COLON )? ID rhs= assign_right_hand_side |obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side | (modifier= access_modifier )? type= assignment_declaration name= ID (rhs= assign_right_hand_side )? );
    public final QuorumParser.assignment_statement_return assignment_statement() throws RecognitionException {
        QuorumParser.assignment_statement_return retval = new QuorumParser.assignment_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token name=null;
        Token COLON129=null;
        Token ID130=null;
        Token COLON131=null;
        Token PARENT132=null;
        Token COLON133=null;
        Token COLON134=null;
        Token ID135=null;
        QuorumParser.selector_return sel =null;

        QuorumParser.assign_right_hand_side_return rhs =null;

        QuorumParser.qualified_name_return obj =null;

        QuorumParser.qualified_name_return parent =null;

        QuorumParser.access_modifier_return modifier =null;

        QuorumParser.assignment_declaration_return type =null;


        CommonTree name_tree=null;
        CommonTree COLON129_tree=null;
        CommonTree ID130_tree=null;
        CommonTree COLON131_tree=null;
        CommonTree PARENT132_tree=null;
        CommonTree COLON133_tree=null;
        CommonTree COLON134_tree=null;
        CommonTree ID135_tree=null;


        	Documentation variableDocumentation = null;
        	if(isInClassAssignmentStatementScope) {
        		variableDocumentation = getDocumentationFromRecentToken();
        	}

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:983:2: ( (sel= selector COLON )? ID rhs= assign_right_hand_side |obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side | (modifier= access_modifier )? type= assignment_declaration name= ID (rhs= assign_right_hand_side )? )
            int alt58=3;
            alt58 = dfa58.predict(input);
            switch (alt58) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:984:3: (sel= selector COLON )? ID rhs= assign_right_hand_side
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:984:3: (sel= selector COLON )?
                    int alt54=2;
                    int LA54_0 = input.LA(1);

                    if ( (LA54_0==ME||LA54_0==PARENT) ) {
                        alt54=1;
                    }
                    switch (alt54) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:984:4: sel= selector COLON
                            {
                            pushFollow(FOLLOW_selector_in_assignment_statement1612);
                            sel=selector();

                            state._fsp--;

                            adaptor.addChild(root_0, sel.getTree());

                            COLON129=(Token)match(input,COLON,FOLLOW_COLON_in_assignment_statement1614); 
                            COLON129_tree = 
                            (CommonTree)adaptor.create(COLON129)
                            ;
                            adaptor.addChild(root_0, COLON129_tree);


                            }
                            break;

                    }


                    ID130=(Token)match(input,ID,FOLLOW_ID_in_assignment_statement1618); 
                    ID130_tree = 
                    (CommonTree)adaptor.create(ID130)
                    ;
                    adaptor.addChild(root_0, ID130_tree);


                    pushFollow(FOLLOW_assign_right_hand_side_in_assignment_statement1624);
                    rhs=assign_right_hand_side();

                    state._fsp--;

                    adaptor.addChild(root_0, rhs.getTree());


                    			String initMe = (ID130!=null?ID130.getText():null);
                    			ClassDescriptor clazz = symbol.getCurrentClass();
                    			if(clazz != null) {
                    				boolean validConstructorInit = false;
                    				if(sel != null) {
                    					ScopeSelector select = (sel!=null?sel.scopeSel:null);
                    					if(!select.isParent()) {
                    						validConstructorInit = true;	
                    					}					
                    				}
                    				else {
                    					validConstructorInit = true;
                    				}
                    				if(validConstructorInit) {
                    					clazz.addToConstructorInitializationList(initMe);
                    				}
                    			}
                    		

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1004:4: obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_qualified_name_in_assignment_statement1635);
                    obj=qualified_name();

                    state._fsp--;

                    adaptor.addChild(root_0, obj.getTree());

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1004:23: ( COLON PARENT COLON parent= qualified_name )?
                    int alt55=2;
                    int LA55_0 = input.LA(1);

                    if ( (LA55_0==COLON) ) {
                        int LA55_1 = input.LA(2);

                        if ( (LA55_1==PARENT) ) {
                            alt55=1;
                        }
                    }
                    switch (alt55) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1004:24: COLON PARENT COLON parent= qualified_name
                            {
                            COLON131=(Token)match(input,COLON,FOLLOW_COLON_in_assignment_statement1638); 
                            COLON131_tree = 
                            (CommonTree)adaptor.create(COLON131)
                            ;
                            adaptor.addChild(root_0, COLON131_tree);


                            PARENT132=(Token)match(input,PARENT,FOLLOW_PARENT_in_assignment_statement1640); 
                            PARENT132_tree = 
                            (CommonTree)adaptor.create(PARENT132)
                            ;
                            adaptor.addChild(root_0, PARENT132_tree);


                            COLON133=(Token)match(input,COLON,FOLLOW_COLON_in_assignment_statement1642); 
                            COLON133_tree = 
                            (CommonTree)adaptor.create(COLON133)
                            ;
                            adaptor.addChild(root_0, COLON133_tree);


                            pushFollow(FOLLOW_qualified_name_in_assignment_statement1646);
                            parent=qualified_name();

                            state._fsp--;

                            adaptor.addChild(root_0, parent.getTree());

                            }
                            break;

                    }


                    COLON134=(Token)match(input,COLON,FOLLOW_COLON_in_assignment_statement1650); 
                    COLON134_tree = 
                    (CommonTree)adaptor.create(COLON134)
                    ;
                    adaptor.addChild(root_0, COLON134_tree);


                    ID135=(Token)match(input,ID,FOLLOW_ID_in_assignment_statement1652); 
                    ID135_tree = 
                    (CommonTree)adaptor.create(ID135)
                    ;
                    adaptor.addChild(root_0, ID135_tree);


                    pushFollow(FOLLOW_assign_right_hand_side_in_assignment_statement1656);
                    rhs=assign_right_hand_side();

                    state._fsp--;

                    adaptor.addChild(root_0, rhs.getTree());

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1005:4: (modifier= access_modifier )? type= assignment_declaration name= ID (rhs= assign_right_hand_side )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1005:13: (modifier= access_modifier )?
                    int alt56=2;
                    int LA56_0 = input.LA(1);

                    if ( ((LA56_0 >= PRIVATE && LA56_0 <= PUBLIC)) ) {
                        alt56=1;
                    }
                    switch (alt56) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1005:13: modifier= access_modifier
                            {
                            pushFollow(FOLLOW_access_modifier_in_assignment_statement1665);
                            modifier=access_modifier();

                            state._fsp--;

                            adaptor.addChild(root_0, modifier.getTree());

                            }
                            break;

                    }



                    			accessModifier = (modifier!=null?modifier.amEnum:null);
                    			if(accessModifier == null){
                    				
                    				accessModifier = accessModifier.PRIVATE;
                    			}
                    		

                    pushFollow(FOLLOW_assignment_declaration_in_assignment_statement1677);
                    type=assignment_declaration();

                    state._fsp--;

                    adaptor.addChild(root_0, type.getTree());

                    name=(Token)match(input,ID,FOLLOW_ID_in_assignment_statement1683); 
                    name_tree = 
                    (CommonTree)adaptor.create(name)
                    ;
                    adaptor.addChild(root_0, name_tree);


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1013:46: (rhs= assign_right_hand_side )?
                    int alt57=2;
                    int LA57_0 = input.LA(1);

                    if ( (LA57_0==EQUALITY) ) {
                        alt57=1;
                    }
                    switch (alt57) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1013:46: rhs= assign_right_hand_side
                            {
                            pushFollow(FOLLOW_assign_right_hand_side_in_assignment_statement1689);
                            rhs=assign_right_hand_side();

                            state._fsp--;

                            adaptor.addChild(root_0, rhs.getTree());

                            }
                            break;

                    }



                    			VariableDescriptor new_desc = new VariableDescriptor();
                    			if(isInClassAssignmentStatementScope) {
                    				new_desc.setDocumentation(variableDocumentation);
                    			}
                    			Iterator<GenericDescriptor> gdList = (type!=null?type.type:null).getSubTypes();
                    			new_desc.setAccessModifier(accessModifier);
                    			new_desc.setType((type!=null?type.type:null));
                    			new_desc.setName((name!=null?name.getText():null));
                    			
                    			if(rhs != null && isInClassAssignmentStatementScope) {
                    				new_desc.setIsInitializedClassVariable(true);
                    			}
                    			if(gdList != null){
                    				while(gdList.hasNext()){
                    					
                    					TypeDescriptor td = new TypeDescriptor();
                    					GenericDescriptor genericType = gdList.next();
                    					td.setName(genericType.getStaticKey());
                    					genericType.addBoundType(td);
                    					new_desc.addTemplateType(genericType);
                    				}
                    			}
                    			
                    			CompilerError error = symbol.add(new_desc);
                    			if(error != null) {
                    				error.setLineNumber(name.getLine());
                    				error.setColumn(name.getCharPositionInLine());
                    				vm.getCompilerErrors().addError(error);
                    			}
                    		

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "assignment_statement"


    public static class assign_right_hand_side_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assign_right_hand_side"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1046:1: assign_right_hand_side : ( EQUALITY root_expression ) ;
    public final QuorumParser.assign_right_hand_side_return assign_right_hand_side() throws RecognitionException {
        QuorumParser.assign_right_hand_side_return retval = new QuorumParser.assign_right_hand_side_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token EQUALITY136=null;
        QuorumParser.root_expression_return root_expression137 =null;


        CommonTree EQUALITY136_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1048:2: ( ( EQUALITY root_expression ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1049:3: ( EQUALITY root_expression )
            {
            root_0 = (CommonTree)adaptor.nil();


            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1049:3: ( EQUALITY root_expression )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1049:4: EQUALITY root_expression
            {
            EQUALITY136=(Token)match(input,EQUALITY,FOLLOW_EQUALITY_in_assign_right_hand_side1715); 
            EQUALITY136_tree = 
            (CommonTree)adaptor.create(EQUALITY136)
            ;
            adaptor.addChild(root_0, EQUALITY136_tree);


            pushFollow(FOLLOW_root_expression_in_assign_right_hand_side1717);
            root_expression137=root_expression();

            state._fsp--;

            adaptor.addChild(root_0, root_expression137.getTree());

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "assign_right_hand_side"


    public static class if_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "if_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1053:1: if_statement : firstif= IF root_expression block (firstelse= ELSE_IF root_expression block )* (secondelse= ELSE block )? end= END ;
    public final QuorumParser.if_statement_return if_statement() throws RecognitionException {
        QuorumParser.if_statement_return retval = new QuorumParser.if_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token firstif=null;
        Token firstelse=null;
        Token secondelse=null;
        Token end=null;
        QuorumParser.root_expression_return root_expression138 =null;

        QuorumParser.block_return block139 =null;

        QuorumParser.root_expression_return root_expression140 =null;

        QuorumParser.block_return block141 =null;

        QuorumParser.block_return block142 =null;


        CommonTree firstif_tree=null;
        CommonTree firstelse_tree=null;
        CommonTree secondelse_tree=null;
        CommonTree end_tree=null;


        	BlockDescriptor block;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1057:2: (firstif= IF root_expression block (firstelse= ELSE_IF root_expression block )* (secondelse= ELSE block )? end= END )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1058:2: firstif= IF root_expression block (firstelse= ELSE_IF root_expression block )* (secondelse= ELSE block )? end= END
            {
            root_0 = (CommonTree)adaptor.nil();


            firstif=(Token)match(input,IF,FOLLOW_IF_in_if_statement1742); 
            firstif_tree = 
            (CommonTree)adaptor.create(firstif)
            ;
            adaptor.addChild(root_0, firstif_tree);


            pushFollow(FOLLOW_root_expression_in_if_statement1744);
            root_expression138=root_expression();

            state._fsp--;

            adaptor.addChild(root_0, root_expression138.getTree());

             block = new BlockDescriptor(); symbol.add(block); 

            pushFollow(FOLLOW_block_in_if_statement1750);
            block139=block();

            state._fsp--;

            adaptor.addChild(root_0, block139.getTree());

             
            		symbol.popScope(); 
                   		//set the begin and end line column information in the block descriptors.
                   		block.setLineBegin(firstif.getLine());
                   		block.setColumnBegin(firstif.getCharPositionInLine());
            	

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1066:2: (firstelse= ELSE_IF root_expression block )*
            loop59:
            do {
                int alt59=2;
                int LA59_0 = input.LA(1);

                if ( (LA59_0==ELSE_IF) ) {
                    alt59=1;
                }


                switch (alt59) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1066:3: firstelse= ELSE_IF root_expression block
            	    {
            	    firstelse=(Token)match(input,ELSE_IF,FOLLOW_ELSE_IF_in_if_statement1762); 
            	    firstelse_tree = 
            	    (CommonTree)adaptor.create(firstelse)
            	    ;
            	    adaptor.addChild(root_0, firstelse_tree);



            	    		block.setLineEnd(firstelse.getLine());
            	    		block.setColumnEnd((firstelse!=null?firstelse.getText():null).length() + firstelse.getCharPositionInLine());
            	    	

            	    pushFollow(FOLLOW_root_expression_in_if_statement1770);
            	    root_expression140=root_expression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, root_expression140.getTree());

            	     block = new BlockDescriptor(); symbol.add(block); 

            	    pushFollow(FOLLOW_block_in_if_statement1776);
            	    block141=block();

            	    state._fsp--;

            	    adaptor.addChild(root_0, block141.getTree());

            	     
            	    		symbol.popScope(); 
            	           		//set the begin and end line column information in the block descriptors.
            	           		block.setLineBegin(firstelse.getLine());
            	           		block.setColumnBegin(firstelse.getCharPositionInLine());
            	    	

            	    }
            	    break;

            	default :
            	    break loop59;
                }
            } while (true);


            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1080:2: (secondelse= ELSE block )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==ELSE) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1080:3: secondelse= ELSE block
                    {
                    secondelse=(Token)match(input,ELSE,FOLLOW_ELSE_in_if_statement1794); 
                    secondelse_tree = 
                    (CommonTree)adaptor.create(secondelse)
                    ;
                    adaptor.addChild(root_0, secondelse_tree);


                     
                    		block.setLineEnd(secondelse.getLine());
                    		block.setColumnEnd((secondelse!=null?secondelse.getText():null).length() + secondelse.getCharPositionInLine());
                    		block = new BlockDescriptor(); 
                    		symbol.add(block); 
                    	

                    pushFollow(FOLLOW_block_in_if_statement1802);
                    block142=block();

                    state._fsp--;

                    adaptor.addChild(root_0, block142.getTree());

                     
                    		symbol.popScope(); 
                           		//set the begin and end line column information in the block descriptors.
                           		block.setLineBegin(secondelse.getLine());
                           		block.setColumnBegin(secondelse.getCharPositionInLine());
                    	

                    }
                    break;

            }


            end=(Token)match(input,END,FOLLOW_END_in_if_statement1818); 
            end_tree = 
            (CommonTree)adaptor.create(end)
            ;
            adaptor.addChild(root_0, end_tree);



            		block.setLineEnd(end.getLine());
            		block.setColumnEnd((end!=null?end.getText():null).length() + end.getCharPositionInLine());
            	

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "if_statement"


    public static class loop_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "loop_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1102:1: loop_statement : REPEAT ( ( root_expression TIMES ) | ( ( WHILE | UNTIL ) root_expression ) ) block END ;
    public final QuorumParser.loop_statement_return loop_statement() throws RecognitionException {
        QuorumParser.loop_statement_return retval = new QuorumParser.loop_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token REPEAT143=null;
        Token TIMES145=null;
        Token set146=null;
        Token END149=null;
        QuorumParser.root_expression_return root_expression144 =null;

        QuorumParser.root_expression_return root_expression147 =null;

        QuorumParser.block_return block148 =null;


        CommonTree REPEAT143_tree=null;
        CommonTree TIMES145_tree=null;
        CommonTree set146_tree=null;
        CommonTree END149_tree=null;


        	BlockDescriptor block;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1106:2: ( REPEAT ( ( root_expression TIMES ) | ( ( WHILE | UNTIL ) root_expression ) ) block END )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1107:2: REPEAT ( ( root_expression TIMES ) | ( ( WHILE | UNTIL ) root_expression ) ) block END
            {
            root_0 = (CommonTree)adaptor.nil();



            		block = new BlockDescriptor();
            		symbol.add(block);
            	

            REPEAT143=(Token)match(input,REPEAT,FOLLOW_REPEAT_in_loop_statement1843); 
            REPEAT143_tree = 
            (CommonTree)adaptor.create(REPEAT143)
            ;
            adaptor.addChild(root_0, REPEAT143_tree);


            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1111:10: ( ( root_expression TIMES ) | ( ( WHILE | UNTIL ) root_expression ) )
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==BOOLEAN||LA61_0==CAST||LA61_0==DECIMAL||LA61_0==ID||(LA61_0 >= INPUT && LA61_0 <= INT)||LA61_0==LEFT_PAREN||(LA61_0 >= ME && LA61_0 <= MINUS)||LA61_0==NOT||LA61_0==NULL||LA61_0==PARENT||LA61_0==STRING) ) {
                alt61=1;
            }
            else if ( (LA61_0==UNTIL||LA61_0==WHILE) ) {
                alt61=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;

            }
            switch (alt61) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1127:4: ( root_expression TIMES )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1127:4: ( root_expression TIMES )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1127:5: root_expression TIMES
                    {
                    pushFollow(FOLLOW_root_expression_in_loop_statement1911);
                    root_expression144=root_expression();

                    state._fsp--;

                    adaptor.addChild(root_0, root_expression144.getTree());

                    TIMES145=(Token)match(input,TIMES,FOLLOW_TIMES_in_loop_statement1913); 
                    TIMES145_tree = 
                    (CommonTree)adaptor.create(TIMES145)
                    ;
                    adaptor.addChild(root_0, TIMES145_tree);


                    }


                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1128:5: ( ( WHILE | UNTIL ) root_expression )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1128:5: ( ( WHILE | UNTIL ) root_expression )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1128:6: ( WHILE | UNTIL ) root_expression
                    {
                    set146=(Token)input.LT(1);

                    if ( input.LA(1)==UNTIL||input.LA(1)==WHILE ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (CommonTree)adaptor.create(set146)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_root_expression_in_loop_statement1929);
                    root_expression147=root_expression();

                    state._fsp--;

                    adaptor.addChild(root_0, root_expression147.getTree());

                    }


                    }
                    break;

            }


            pushFollow(FOLLOW_block_in_loop_statement1938);
            block148=block();

            state._fsp--;

            adaptor.addChild(root_0, block148.getTree());

            END149=(Token)match(input,END,FOLLOW_END_in_loop_statement1940); 
            END149_tree = 
            (CommonTree)adaptor.create(END149)
            ;
            adaptor.addChild(root_0, END149_tree);



                   			//set the begin and end line column information in the block descriptors.
                   			block.setLineBegin(REPEAT143.getLine());
                   			block.setLineEnd(END149.getLine());
                   			block.setColumnBegin(REPEAT143.getCharPositionInLine());
                   			block.setColumnEnd((END149!=null?END149.getText():null).length() + END149.getCharPositionInLine());
                   		


            		symbol.popScope();
            	

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "loop_statement"


    public static class selector_return extends ParserRuleReturnScope {
        public ScopeSelector scopeSel;
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "selector"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1144:1: selector returns [ScopeSelector scopeSel] : ( PARENT COLON qn= qualified_name -> ^( PARENT qualified_name ) | ME );
    public final QuorumParser.selector_return selector() throws RecognitionException {
        QuorumParser.selector_return retval = new QuorumParser.selector_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token PARENT150=null;
        Token COLON151=null;
        Token ME152=null;
        QuorumParser.qualified_name_return qn =null;


        CommonTree PARENT150_tree=null;
        CommonTree COLON151_tree=null;
        CommonTree ME152_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_PARENT=new RewriteRuleTokenStream(adaptor,"token PARENT");
        RewriteRuleSubtreeStream stream_qualified_name=new RewriteRuleSubtreeStream(adaptor,"rule qualified_name");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1145:2: ( PARENT COLON qn= qualified_name -> ^( PARENT qualified_name ) | ME )
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==PARENT) ) {
                alt62=1;
            }
            else if ( (LA62_0==ME) ) {
                alt62=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;

            }
            switch (alt62) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1145:4: PARENT COLON qn= qualified_name
                    {
                    PARENT150=(Token)match(input,PARENT,FOLLOW_PARENT_in_selector1964);  
                    stream_PARENT.add(PARENT150);


                    COLON151=(Token)match(input,COLON,FOLLOW_COLON_in_selector1966);  
                    stream_COLON.add(COLON151);


                    pushFollow(FOLLOW_qualified_name_in_selector1970);
                    qn=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qn.getTree());


                    		ScopeSelector scopeItem = new ScopeSelector();
                    		scopeItem.setIsParent(true);
                    		retval.scopeSel = scopeItem;
                    	

                    // AST REWRITE
                    // elements: qualified_name, PARENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1150:4: -> ^( PARENT qualified_name )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1150:7: ^( PARENT qualified_name )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_PARENT.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_qualified_name.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1152:4: ME
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    ME152=(Token)match(input,ME,FOLLOW_ME_in_selector1988); 
                    ME152_tree = 
                    (CommonTree)adaptor.create(ME152)
                    ;
                    adaptor.addChild(root_0, ME152_tree);



                    		ScopeSelector scopeItem = new ScopeSelector();
                    		scopeItem.setIsParent(false);
                    		retval.scopeSel = scopeItem;
                    	

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "selector"


    public static class root_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "root_expression"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1160:1: root_expression : expression -> ^( ROOT_EXPRESSION expression ) ;
    public final QuorumParser.root_expression_return root_expression() throws RecognitionException {
        QuorumParser.root_expression_return retval = new QuorumParser.root_expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        QuorumParser.expression_return expression153 =null;


        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1161:2: ( expression -> ^( ROOT_EXPRESSION expression ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1161:4: expression
            {
            pushFollow(FOLLOW_expression_in_root_expression2002);
            expression153=expression();

            state._fsp--;

            stream_expression.add(expression153.getTree());

            // AST REWRITE
            // elements: expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 1161:15: -> ^( ROOT_EXPRESSION expression )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1161:18: ^( ROOT_EXPRESSION expression )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(ROOT_EXPRESSION, "ROOT_EXPRESSION")
                , root_1);

                adaptor.addChild(root_1, stream_expression.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "root_expression"


    public static class expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expression"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1164:1: expression : or ;
    public final QuorumParser.expression_return expression() throws RecognitionException {
        QuorumParser.expression_return retval = new QuorumParser.expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        QuorumParser.or_return or154 =null;



        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1165:2: ( or )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1165:4: or
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_or_in_expression2022);
            or154=or();

            state._fsp--;

            adaptor.addChild(root_0, or154.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expression"


    public static class or_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "or"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1168:1: or : and ( OR ^ and )* ;
    public final QuorumParser.or_return or() throws RecognitionException {
        QuorumParser.or_return retval = new QuorumParser.or_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token OR156=null;
        QuorumParser.and_return and155 =null;

        QuorumParser.and_return and157 =null;


        CommonTree OR156_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1168:5: ( and ( OR ^ and )* )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1168:7: and ( OR ^ and )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_and_in_or2034);
            and155=and();

            state._fsp--;

            adaptor.addChild(root_0, and155.getTree());

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1168:11: ( OR ^ and )*
            loop63:
            do {
                int alt63=2;
                int LA63_0 = input.LA(1);

                if ( (LA63_0==OR) ) {
                    alt63=1;
                }


                switch (alt63) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1168:12: OR ^ and
            	    {
            	    OR156=(Token)match(input,OR,FOLLOW_OR_in_or2037); 
            	    OR156_tree = 
            	    (CommonTree)adaptor.create(OR156)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(OR156_tree, root_0);


            	    pushFollow(FOLLOW_and_in_or2041);
            	    and157=and();

            	    state._fsp--;

            	    adaptor.addChild(root_0, and157.getTree());

            	    }
            	    break;

            	default :
            	    break loop63;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "or"


    public static class and_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "and"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1171:1: and : equality ( AND ^ equality )* ;
    public final QuorumParser.and_return and() throws RecognitionException {
        QuorumParser.and_return retval = new QuorumParser.and_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token AND159=null;
        QuorumParser.equality_return equality158 =null;

        QuorumParser.equality_return equality160 =null;


        CommonTree AND159_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1171:6: ( equality ( AND ^ equality )* )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1171:8: equality ( AND ^ equality )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_equality_in_and2054);
            equality158=equality();

            state._fsp--;

            adaptor.addChild(root_0, equality158.getTree());

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1171:17: ( AND ^ equality )*
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( (LA64_0==AND) ) {
                    alt64=1;
                }


                switch (alt64) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1171:18: AND ^ equality
            	    {
            	    AND159=(Token)match(input,AND,FOLLOW_AND_in_and2057); 
            	    AND159_tree = 
            	    (CommonTree)adaptor.create(AND159)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(AND159_tree, root_0);


            	    pushFollow(FOLLOW_equality_in_and2061);
            	    equality160=equality();

            	    state._fsp--;

            	    adaptor.addChild(root_0, equality160.getTree());

            	    }
            	    break;

            	default :
            	    break loop64;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "and"


    public static class equality_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "equality"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1173:1: equality : isa_operation ( ( EQUALITY ^| NOTEQUALS ^) isa_operation )* ;
    public final QuorumParser.equality_return equality() throws RecognitionException {
        QuorumParser.equality_return retval = new QuorumParser.equality_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token EQUALITY162=null;
        Token NOTEQUALS163=null;
        QuorumParser.isa_operation_return isa_operation161 =null;

        QuorumParser.isa_operation_return isa_operation164 =null;


        CommonTree EQUALITY162_tree=null;
        CommonTree NOTEQUALS163_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1173:9: ( isa_operation ( ( EQUALITY ^| NOTEQUALS ^) isa_operation )* )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1173:11: isa_operation ( ( EQUALITY ^| NOTEQUALS ^) isa_operation )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_isa_operation_in_equality2072);
            isa_operation161=isa_operation();

            state._fsp--;

            adaptor.addChild(root_0, isa_operation161.getTree());

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1173:25: ( ( EQUALITY ^| NOTEQUALS ^) isa_operation )*
            loop66:
            do {
                int alt66=2;
                int LA66_0 = input.LA(1);

                if ( (LA66_0==EQUALITY||LA66_0==NOTEQUALS) ) {
                    alt66=1;
                }


                switch (alt66) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1173:26: ( EQUALITY ^| NOTEQUALS ^) isa_operation
            	    {
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1173:26: ( EQUALITY ^| NOTEQUALS ^)
            	    int alt65=2;
            	    int LA65_0 = input.LA(1);

            	    if ( (LA65_0==EQUALITY) ) {
            	        alt65=1;
            	    }
            	    else if ( (LA65_0==NOTEQUALS) ) {
            	        alt65=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 65, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt65) {
            	        case 1 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1173:27: EQUALITY ^
            	            {
            	            EQUALITY162=(Token)match(input,EQUALITY,FOLLOW_EQUALITY_in_equality2076); 
            	            EQUALITY162_tree = 
            	            (CommonTree)adaptor.create(EQUALITY162)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(EQUALITY162_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1173:40: NOTEQUALS ^
            	            {
            	            NOTEQUALS163=(Token)match(input,NOTEQUALS,FOLLOW_NOTEQUALS_in_equality2082); 
            	            NOTEQUALS163_tree = 
            	            (CommonTree)adaptor.create(NOTEQUALS163)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(NOTEQUALS163_tree, root_0);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_isa_operation_in_equality2087);
            	    isa_operation164=isa_operation();

            	    state._fsp--;

            	    adaptor.addChild(root_0, isa_operation164.getTree());

            	    }
            	    break;

            	default :
            	    break loop66;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "equality"


    public static class isa_operation_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "isa_operation"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1175:1: isa_operation : comparison ( INHERITS ^ class_type )? ;
    public final QuorumParser.isa_operation_return isa_operation() throws RecognitionException {
        QuorumParser.isa_operation_return retval = new QuorumParser.isa_operation_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token INHERITS166=null;
        QuorumParser.comparison_return comparison165 =null;

        QuorumParser.class_type_return class_type167 =null;


        CommonTree INHERITS166_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1176:2: ( comparison ( INHERITS ^ class_type )? )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1176:4: comparison ( INHERITS ^ class_type )?
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_comparison_in_isa_operation2099);
            comparison165=comparison();

            state._fsp--;

            adaptor.addChild(root_0, comparison165.getTree());

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1176:15: ( INHERITS ^ class_type )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==INHERITS) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1176:16: INHERITS ^ class_type
                    {
                    INHERITS166=(Token)match(input,INHERITS,FOLLOW_INHERITS_in_isa_operation2102); 
                    INHERITS166_tree = 
                    (CommonTree)adaptor.create(INHERITS166)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(INHERITS166_tree, root_0);


                    pushFollow(FOLLOW_class_type_in_isa_operation2106);
                    class_type167=class_type();

                    state._fsp--;

                    adaptor.addChild(root_0, class_type167.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "isa_operation"


    public static class comparison_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "comparison"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1178:1: comparison : add ( ( GREATER ^| GREATER_EQUAL ^| LESS ^| LESS_EQUAL ^) add )* ;
    public final QuorumParser.comparison_return comparison() throws RecognitionException {
        QuorumParser.comparison_return retval = new QuorumParser.comparison_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token GREATER169=null;
        Token GREATER_EQUAL170=null;
        Token LESS171=null;
        Token LESS_EQUAL172=null;
        QuorumParser.add_return add168 =null;

        QuorumParser.add_return add173 =null;


        CommonTree GREATER169_tree=null;
        CommonTree GREATER_EQUAL170_tree=null;
        CommonTree LESS171_tree=null;
        CommonTree LESS_EQUAL172_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1178:11: ( add ( ( GREATER ^| GREATER_EQUAL ^| LESS ^| LESS_EQUAL ^) add )* )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1178:13: add ( ( GREATER ^| GREATER_EQUAL ^| LESS ^| LESS_EQUAL ^) add )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_add_in_comparison2116);
            add168=add();

            state._fsp--;

            adaptor.addChild(root_0, add168.getTree());

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1178:17: ( ( GREATER ^| GREATER_EQUAL ^| LESS ^| LESS_EQUAL ^) add )*
            loop69:
            do {
                int alt69=2;
                int LA69_0 = input.LA(1);

                if ( ((LA69_0 >= GREATER && LA69_0 <= GREATER_EQUAL)||(LA69_0 >= LESS && LA69_0 <= LESS_EQUAL)) ) {
                    alt69=1;
                }


                switch (alt69) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1178:18: ( GREATER ^| GREATER_EQUAL ^| LESS ^| LESS_EQUAL ^) add
            	    {
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1178:18: ( GREATER ^| GREATER_EQUAL ^| LESS ^| LESS_EQUAL ^)
            	    int alt68=4;
            	    switch ( input.LA(1) ) {
            	    case GREATER:
            	        {
            	        alt68=1;
            	        }
            	        break;
            	    case GREATER_EQUAL:
            	        {
            	        alt68=2;
            	        }
            	        break;
            	    case LESS:
            	        {
            	        alt68=3;
            	        }
            	        break;
            	    case LESS_EQUAL:
            	        {
            	        alt68=4;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 68, 0, input);

            	        throw nvae;

            	    }

            	    switch (alt68) {
            	        case 1 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1178:19: GREATER ^
            	            {
            	            GREATER169=(Token)match(input,GREATER,FOLLOW_GREATER_in_comparison2120); 
            	            GREATER169_tree = 
            	            (CommonTree)adaptor.create(GREATER169)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(GREATER169_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1178:30: GREATER_EQUAL ^
            	            {
            	            GREATER_EQUAL170=(Token)match(input,GREATER_EQUAL,FOLLOW_GREATER_EQUAL_in_comparison2125); 
            	            GREATER_EQUAL170_tree = 
            	            (CommonTree)adaptor.create(GREATER_EQUAL170)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(GREATER_EQUAL170_tree, root_0);


            	            }
            	            break;
            	        case 3 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1178:47: LESS ^
            	            {
            	            LESS171=(Token)match(input,LESS,FOLLOW_LESS_in_comparison2130); 
            	            LESS171_tree = 
            	            (CommonTree)adaptor.create(LESS171)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(LESS171_tree, root_0);


            	            }
            	            break;
            	        case 4 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1178:55: LESS_EQUAL ^
            	            {
            	            LESS_EQUAL172=(Token)match(input,LESS_EQUAL,FOLLOW_LESS_EQUAL_in_comparison2135); 
            	            LESS_EQUAL172_tree = 
            	            (CommonTree)adaptor.create(LESS_EQUAL172)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(LESS_EQUAL172_tree, root_0);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_add_in_comparison2139);
            	    add173=add();

            	    state._fsp--;

            	    adaptor.addChild(root_0, add173.getTree());

            	    }
            	    break;

            	default :
            	    break loop69;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "comparison"


    public static class add_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "add"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1182:1: add : multiply ( ( PLUS ^| MINUS ^) multiply )* ;
    public final QuorumParser.add_return add() throws RecognitionException {
        QuorumParser.add_return retval = new QuorumParser.add_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token PLUS175=null;
        Token MINUS176=null;
        QuorumParser.multiply_return multiply174 =null;

        QuorumParser.multiply_return multiply177 =null;


        CommonTree PLUS175_tree=null;
        CommonTree MINUS176_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1182:5: ( multiply ( ( PLUS ^| MINUS ^) multiply )* )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1182:7: multiply ( ( PLUS ^| MINUS ^) multiply )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_multiply_in_add2153);
            multiply174=multiply();

            state._fsp--;

            adaptor.addChild(root_0, multiply174.getTree());

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1182:16: ( ( PLUS ^| MINUS ^) multiply )*
            loop71:
            do {
                int alt71=2;
                int LA71_0 = input.LA(1);

                if ( (LA71_0==MINUS||LA71_0==PLUS) ) {
                    alt71=1;
                }


                switch (alt71) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1182:17: ( PLUS ^| MINUS ^) multiply
            	    {
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1182:17: ( PLUS ^| MINUS ^)
            	    int alt70=2;
            	    int LA70_0 = input.LA(1);

            	    if ( (LA70_0==PLUS) ) {
            	        alt70=1;
            	    }
            	    else if ( (LA70_0==MINUS) ) {
            	        alt70=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 70, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt70) {
            	        case 1 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1182:18: PLUS ^
            	            {
            	            PLUS175=(Token)match(input,PLUS,FOLLOW_PLUS_in_add2157); 
            	            PLUS175_tree = 
            	            (CommonTree)adaptor.create(PLUS175)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(PLUS175_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1182:26: MINUS ^
            	            {
            	            MINUS176=(Token)match(input,MINUS,FOLLOW_MINUS_in_add2162); 
            	            MINUS176_tree = 
            	            (CommonTree)adaptor.create(MINUS176)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(MINUS176_tree, root_0);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_multiply_in_add2166);
            	    multiply177=multiply();

            	    state._fsp--;

            	    adaptor.addChild(root_0, multiply177.getTree());

            	    }
            	    break;

            	default :
            	    break loop71;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "add"


    public static class multiply_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "multiply"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1185:1: multiply : combo_expression ( ( MULTIPLY ^| DIVIDE ^| MODULO ^) combo_expression )* ;
    public final QuorumParser.multiply_return multiply() throws RecognitionException {
        QuorumParser.multiply_return retval = new QuorumParser.multiply_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token MULTIPLY179=null;
        Token DIVIDE180=null;
        Token MODULO181=null;
        QuorumParser.combo_expression_return combo_expression178 =null;

        QuorumParser.combo_expression_return combo_expression182 =null;


        CommonTree MULTIPLY179_tree=null;
        CommonTree DIVIDE180_tree=null;
        CommonTree MODULO181_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1185:9: ( combo_expression ( ( MULTIPLY ^| DIVIDE ^| MODULO ^) combo_expression )* )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1185:11: combo_expression ( ( MULTIPLY ^| DIVIDE ^| MODULO ^) combo_expression )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_combo_expression_in_multiply2179);
            combo_expression178=combo_expression();

            state._fsp--;

            adaptor.addChild(root_0, combo_expression178.getTree());

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1185:28: ( ( MULTIPLY ^| DIVIDE ^| MODULO ^) combo_expression )*
            loop73:
            do {
                int alt73=2;
                int LA73_0 = input.LA(1);

                if ( (LA73_0==DIVIDE||(LA73_0 >= MODULO && LA73_0 <= MULTIPLY)) ) {
                    alt73=1;
                }


                switch (alt73) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1185:29: ( MULTIPLY ^| DIVIDE ^| MODULO ^) combo_expression
            	    {
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1185:29: ( MULTIPLY ^| DIVIDE ^| MODULO ^)
            	    int alt72=3;
            	    switch ( input.LA(1) ) {
            	    case MULTIPLY:
            	        {
            	        alt72=1;
            	        }
            	        break;
            	    case DIVIDE:
            	        {
            	        alt72=2;
            	        }
            	        break;
            	    case MODULO:
            	        {
            	        alt72=3;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 72, 0, input);

            	        throw nvae;

            	    }

            	    switch (alt72) {
            	        case 1 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1185:30: MULTIPLY ^
            	            {
            	            MULTIPLY179=(Token)match(input,MULTIPLY,FOLLOW_MULTIPLY_in_multiply2183); 
            	            MULTIPLY179_tree = 
            	            (CommonTree)adaptor.create(MULTIPLY179)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(MULTIPLY179_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1185:42: DIVIDE ^
            	            {
            	            DIVIDE180=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_multiply2188); 
            	            DIVIDE180_tree = 
            	            (CommonTree)adaptor.create(DIVIDE180)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(DIVIDE180_tree, root_0);


            	            }
            	            break;
            	        case 3 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1185:51: MODULO ^
            	            {
            	            MODULO181=(Token)match(input,MODULO,FOLLOW_MODULO_in_multiply2192); 
            	            MODULO181_tree = 
            	            (CommonTree)adaptor.create(MODULO181)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(MODULO181_tree, root_0);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_combo_expression_in_multiply2196);
            	    combo_expression182=combo_expression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, combo_expression182.getTree());

            	    }
            	    break;

            	default :
            	    break loop73;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "multiply"


    public static class combo_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "combo_expression"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1188:1: combo_expression : ( NOT atom -> ^( UNARY_NOT NOT atom ) | CAST LEFT_PAREN assignment_declaration COMMA expression RIGHT_PAREN | atom );
    public final QuorumParser.combo_expression_return combo_expression() throws RecognitionException {
        QuorumParser.combo_expression_return retval = new QuorumParser.combo_expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token NOT183=null;
        Token CAST185=null;
        Token LEFT_PAREN186=null;
        Token COMMA188=null;
        Token RIGHT_PAREN190=null;
        QuorumParser.atom_return atom184 =null;

        QuorumParser.assignment_declaration_return assignment_declaration187 =null;

        QuorumParser.expression_return expression189 =null;

        QuorumParser.atom_return atom191 =null;


        CommonTree NOT183_tree=null;
        CommonTree CAST185_tree=null;
        CommonTree LEFT_PAREN186_tree=null;
        CommonTree COMMA188_tree=null;
        CommonTree RIGHT_PAREN190_tree=null;
        RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
        RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1189:2: ( NOT atom -> ^( UNARY_NOT NOT atom ) | CAST LEFT_PAREN assignment_declaration COMMA expression RIGHT_PAREN | atom )
            int alt74=3;
            switch ( input.LA(1) ) {
            case NOT:
                {
                alt74=1;
                }
                break;
            case CAST:
                {
                alt74=2;
                }
                break;
            case BOOLEAN:
            case DECIMAL:
            case ID:
            case INPUT:
            case INT:
            case LEFT_PAREN:
            case ME:
            case MINUS:
            case NULL:
            case PARENT:
            case STRING:
                {
                alt74=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 74, 0, input);

                throw nvae;

            }

            switch (alt74) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1189:4: NOT atom
                    {
                    NOT183=(Token)match(input,NOT,FOLLOW_NOT_in_combo_expression2211);  
                    stream_NOT.add(NOT183);


                    pushFollow(FOLLOW_atom_in_combo_expression2213);
                    atom184=atom();

                    state._fsp--;

                    stream_atom.add(atom184.getTree());

                    // AST REWRITE
                    // elements: NOT, atom
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1189:13: -> ^( UNARY_NOT NOT atom )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1189:16: ^( UNARY_NOT NOT atom )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(UNARY_NOT, "UNARY_NOT")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_NOT.nextNode()
                        );

                        adaptor.addChild(root_1, stream_atom.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1190:4: CAST LEFT_PAREN assignment_declaration COMMA expression RIGHT_PAREN
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    CAST185=(Token)match(input,CAST,FOLLOW_CAST_in_combo_expression2228); 
                    CAST185_tree = 
                    (CommonTree)adaptor.create(CAST185)
                    ;
                    adaptor.addChild(root_0, CAST185_tree);


                    LEFT_PAREN186=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_combo_expression2230); 
                    LEFT_PAREN186_tree = 
                    (CommonTree)adaptor.create(LEFT_PAREN186)
                    ;
                    adaptor.addChild(root_0, LEFT_PAREN186_tree);


                    pushFollow(FOLLOW_assignment_declaration_in_combo_expression2232);
                    assignment_declaration187=assignment_declaration();

                    state._fsp--;

                    adaptor.addChild(root_0, assignment_declaration187.getTree());

                    COMMA188=(Token)match(input,COMMA,FOLLOW_COMMA_in_combo_expression2234); 
                    COMMA188_tree = 
                    (CommonTree)adaptor.create(COMMA188)
                    ;
                    adaptor.addChild(root_0, COMMA188_tree);


                    pushFollow(FOLLOW_expression_in_combo_expression2236);
                    expression189=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression189.getTree());

                    RIGHT_PAREN190=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_combo_expression2238); 
                    RIGHT_PAREN190_tree = 
                    (CommonTree)adaptor.create(RIGHT_PAREN190)
                    ;
                    adaptor.addChild(root_0, RIGHT_PAREN190_tree);


                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1191:4: atom
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_atom_in_combo_expression2243);
                    atom191=atom();

                    state._fsp--;

                    adaptor.addChild(root_0, atom191.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "combo_expression"


    public static class atom_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "atom"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1194:1: atom : ( qualified_name ( COLON ID )? -> ^( QUALIFIED_SOLO_EXPRESSION qualified_name ( COLON ID )? ) | qualified_name COLON PARENT COLON qualified_name COLON ID -> ^( QUALIFIED_SOLO_PARENT_EXPRESSON qualified_name COLON PARENT COLON qualified_name COLON ID ) | qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN ) | selector COLON qualified_name -> ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name ) | PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN ) | ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN ) | ( MINUS )? INT | BOOLEAN | ( MINUS )? DECIMAL | STRING | NULL | ME | INPUT LEFT_PAREN expression RIGHT_PAREN | LEFT_PAREN expression RIGHT_PAREN -> ^( expression ) );
    public final QuorumParser.atom_return atom() throws RecognitionException {
        QuorumParser.atom_return retval = new QuorumParser.atom_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token COLON193=null;
        Token ID194=null;
        Token COLON196=null;
        Token PARENT197=null;
        Token COLON198=null;
        Token COLON200=null;
        Token ID201=null;
        Token COLON203=null;
        Token ID204=null;
        Token LEFT_PAREN205=null;
        Token RIGHT_PAREN207=null;
        Token COLON209=null;
        Token PARENT211=null;
        Token COLON212=null;
        Token COLON214=null;
        Token ID215=null;
        Token LEFT_PAREN216=null;
        Token RIGHT_PAREN218=null;
        Token ME219=null;
        Token COLON220=null;
        Token COLON222=null;
        Token ID223=null;
        Token LEFT_PAREN224=null;
        Token RIGHT_PAREN226=null;
        Token MINUS227=null;
        Token INT228=null;
        Token BOOLEAN229=null;
        Token MINUS230=null;
        Token DECIMAL231=null;
        Token STRING232=null;
        Token NULL233=null;
        Token ME234=null;
        Token INPUT235=null;
        Token LEFT_PAREN236=null;
        Token RIGHT_PAREN238=null;
        Token LEFT_PAREN239=null;
        Token RIGHT_PAREN241=null;
        QuorumParser.qualified_name_return qualified_name192 =null;

        QuorumParser.qualified_name_return qualified_name195 =null;

        QuorumParser.qualified_name_return qualified_name199 =null;

        QuorumParser.qualified_name_return qualified_name202 =null;

        QuorumParser.function_expression_list_return function_expression_list206 =null;

        QuorumParser.selector_return selector208 =null;

        QuorumParser.qualified_name_return qualified_name210 =null;

        QuorumParser.qualified_name_return qualified_name213 =null;

        QuorumParser.function_expression_list_return function_expression_list217 =null;

        QuorumParser.qualified_name_return qualified_name221 =null;

        QuorumParser.function_expression_list_return function_expression_list225 =null;

        QuorumParser.expression_return expression237 =null;

        QuorumParser.expression_return expression240 =null;


        CommonTree COLON193_tree=null;
        CommonTree ID194_tree=null;
        CommonTree COLON196_tree=null;
        CommonTree PARENT197_tree=null;
        CommonTree COLON198_tree=null;
        CommonTree COLON200_tree=null;
        CommonTree ID201_tree=null;
        CommonTree COLON203_tree=null;
        CommonTree ID204_tree=null;
        CommonTree LEFT_PAREN205_tree=null;
        CommonTree RIGHT_PAREN207_tree=null;
        CommonTree COLON209_tree=null;
        CommonTree PARENT211_tree=null;
        CommonTree COLON212_tree=null;
        CommonTree COLON214_tree=null;
        CommonTree ID215_tree=null;
        CommonTree LEFT_PAREN216_tree=null;
        CommonTree RIGHT_PAREN218_tree=null;
        CommonTree ME219_tree=null;
        CommonTree COLON220_tree=null;
        CommonTree COLON222_tree=null;
        CommonTree ID223_tree=null;
        CommonTree LEFT_PAREN224_tree=null;
        CommonTree RIGHT_PAREN226_tree=null;
        CommonTree MINUS227_tree=null;
        CommonTree INT228_tree=null;
        CommonTree BOOLEAN229_tree=null;
        CommonTree MINUS230_tree=null;
        CommonTree DECIMAL231_tree=null;
        CommonTree STRING232_tree=null;
        CommonTree NULL233_tree=null;
        CommonTree ME234_tree=null;
        CommonTree INPUT235_tree=null;
        CommonTree LEFT_PAREN236_tree=null;
        CommonTree RIGHT_PAREN238_tree=null;
        CommonTree LEFT_PAREN239_tree=null;
        CommonTree RIGHT_PAREN241_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_LEFT_PAREN=new RewriteRuleTokenStream(adaptor,"token LEFT_PAREN");
        RewriteRuleTokenStream stream_ME=new RewriteRuleTokenStream(adaptor,"token ME");
        RewriteRuleTokenStream stream_RIGHT_PAREN=new RewriteRuleTokenStream(adaptor,"token RIGHT_PAREN");
        RewriteRuleTokenStream stream_PARENT=new RewriteRuleTokenStream(adaptor,"token PARENT");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_selector=new RewriteRuleSubtreeStream(adaptor,"rule selector");
        RewriteRuleSubtreeStream stream_function_expression_list=new RewriteRuleSubtreeStream(adaptor,"rule function_expression_list");
        RewriteRuleSubtreeStream stream_qualified_name=new RewriteRuleSubtreeStream(adaptor,"rule qualified_name");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1194:7: ( qualified_name ( COLON ID )? -> ^( QUALIFIED_SOLO_EXPRESSION qualified_name ( COLON ID )? ) | qualified_name COLON PARENT COLON qualified_name COLON ID -> ^( QUALIFIED_SOLO_PARENT_EXPRESSON qualified_name COLON PARENT COLON qualified_name COLON ID ) | qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN ) | selector COLON qualified_name -> ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name ) | PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN ) | ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN ) | ( MINUS )? INT | BOOLEAN | ( MINUS )? DECIMAL | STRING | NULL | ME | INPUT LEFT_PAREN expression RIGHT_PAREN | LEFT_PAREN expression RIGHT_PAREN -> ^( expression ) )
            int alt80=14;
            alt80 = dfa80.predict(input);
            switch (alt80) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1195:2: qualified_name ( COLON ID )?
                    {
                    pushFollow(FOLLOW_qualified_name_in_atom2258);
                    qualified_name192=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name192.getTree());

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1195:17: ( COLON ID )?
                    int alt75=2;
                    int LA75_0 = input.LA(1);

                    if ( (LA75_0==COLON) ) {
                        alt75=1;
                    }
                    switch (alt75) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1195:18: COLON ID
                            {
                            COLON193=(Token)match(input,COLON,FOLLOW_COLON_in_atom2261);  
                            stream_COLON.add(COLON193);


                            ID194=(Token)match(input,ID,FOLLOW_ID_in_atom2263);  
                            stream_ID.add(ID194);


                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: qualified_name, COLON, ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1195:29: -> ^( QUALIFIED_SOLO_EXPRESSION qualified_name ( COLON ID )? )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1195:32: ^( QUALIFIED_SOLO_EXPRESSION qualified_name ( COLON ID )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(QUALIFIED_SOLO_EXPRESSION, "QUALIFIED_SOLO_EXPRESSION")
                        , root_1);

                        adaptor.addChild(root_1, stream_qualified_name.nextTree());

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1195:75: ( COLON ID )?
                        if ( stream_COLON.hasNext()||stream_ID.hasNext() ) {
                            adaptor.addChild(root_1, 
                            stream_COLON.nextNode()
                            );

                            adaptor.addChild(root_1, 
                            stream_ID.nextNode()
                            );

                        }
                        stream_COLON.reset();
                        stream_ID.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1196:4: qualified_name COLON PARENT COLON qualified_name COLON ID
                    {
                    pushFollow(FOLLOW_qualified_name_in_atom2285);
                    qualified_name195=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name195.getTree());

                    COLON196=(Token)match(input,COLON,FOLLOW_COLON_in_atom2287);  
                    stream_COLON.add(COLON196);


                    PARENT197=(Token)match(input,PARENT,FOLLOW_PARENT_in_atom2289);  
                    stream_PARENT.add(PARENT197);


                    COLON198=(Token)match(input,COLON,FOLLOW_COLON_in_atom2291);  
                    stream_COLON.add(COLON198);


                    pushFollow(FOLLOW_qualified_name_in_atom2293);
                    qualified_name199=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name199.getTree());

                    COLON200=(Token)match(input,COLON,FOLLOW_COLON_in_atom2295);  
                    stream_COLON.add(COLON200);


                    ID201=(Token)match(input,ID,FOLLOW_ID_in_atom2297);  
                    stream_ID.add(ID201);


                    // AST REWRITE
                    // elements: COLON, COLON, COLON, ID, qualified_name, PARENT, qualified_name
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1196:62: -> ^( QUALIFIED_SOLO_PARENT_EXPRESSON qualified_name COLON PARENT COLON qualified_name COLON ID )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1196:65: ^( QUALIFIED_SOLO_PARENT_EXPRESSON qualified_name COLON PARENT COLON qualified_name COLON ID )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(QUALIFIED_SOLO_PARENT_EXPRESSON, "QUALIFIED_SOLO_PARENT_EXPRESSON")
                        , root_1);

                        adaptor.addChild(root_1, stream_qualified_name.nextTree());

                        adaptor.addChild(root_1, 
                        stream_COLON.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        stream_PARENT.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        stream_COLON.nextNode()
                        );

                        adaptor.addChild(root_1, stream_qualified_name.nextTree());

                        adaptor.addChild(root_1, 
                        stream_COLON.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1197:4: qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN
                    {
                    pushFollow(FOLLOW_qualified_name_in_atom2322);
                    qualified_name202=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name202.getTree());

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1197:19: ( COLON ID )?
                    int alt76=2;
                    int LA76_0 = input.LA(1);

                    if ( (LA76_0==COLON) ) {
                        alt76=1;
                    }
                    switch (alt76) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1197:20: COLON ID
                            {
                            COLON203=(Token)match(input,COLON,FOLLOW_COLON_in_atom2325);  
                            stream_COLON.add(COLON203);


                            ID204=(Token)match(input,ID,FOLLOW_ID_in_atom2327);  
                            stream_ID.add(ID204);


                            }
                            break;

                    }


                    LEFT_PAREN205=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_atom2331);  
                    stream_LEFT_PAREN.add(LEFT_PAREN205);


                    pushFollow(FOLLOW_function_expression_list_in_atom2333);
                    function_expression_list206=function_expression_list();

                    state._fsp--;

                    stream_function_expression_list.add(function_expression_list206.getTree());

                    RIGHT_PAREN207=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_atom2335);  
                    stream_RIGHT_PAREN.add(RIGHT_PAREN207);


                    // AST REWRITE
                    // elements: function_expression_list, RIGHT_PAREN, ID, qualified_name, COLON, LEFT_PAREN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1197:79: -> ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1198:4: ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(FUNCTION_CALL, "FUNCTION_CALL")
                        , root_1);

                        adaptor.addChild(root_1, stream_qualified_name.nextTree());

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1198:35: ( COLON ID )?
                        if ( stream_ID.hasNext()||stream_COLON.hasNext() ) {
                            adaptor.addChild(root_1, 
                            stream_COLON.nextNode()
                            );

                            adaptor.addChild(root_1, 
                            stream_ID.nextNode()
                            );

                        }
                        stream_ID.reset();
                        stream_COLON.reset();

                        adaptor.addChild(root_1, 
                        stream_LEFT_PAREN.nextNode()
                        );

                        adaptor.addChild(root_1, stream_function_expression_list.nextTree());

                        adaptor.addChild(root_1, 
                        stream_RIGHT_PAREN.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1199:4: selector COLON qualified_name
                    {
                    pushFollow(FOLLOW_selector_in_atom2364);
                    selector208=selector();

                    state._fsp--;

                    stream_selector.add(selector208.getTree());

                    COLON209=(Token)match(input,COLON,FOLLOW_COLON_in_atom2366);  
                    stream_COLON.add(COLON209);


                    pushFollow(FOLLOW_qualified_name_in_atom2368);
                    qualified_name210=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name210.getTree());

                    // AST REWRITE
                    // elements: selector, COLON, qualified_name
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1199:34: -> ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1200:4: ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(QUALIFIED_SOLO_EXPRESSION_SELECTOR, "QUALIFIED_SOLO_EXPRESSION_SELECTOR")
                        , root_1);

                        adaptor.addChild(root_1, stream_selector.nextTree());

                        adaptor.addChild(root_1, 
                        stream_COLON.nextNode()
                        );

                        adaptor.addChild(root_1, stream_qualified_name.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 5 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1203:4: PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN
                    {
                    PARENT211=(Token)match(input,PARENT,FOLLOW_PARENT_in_atom2393);  
                    stream_PARENT.add(PARENT211);


                    COLON212=(Token)match(input,COLON,FOLLOW_COLON_in_atom2395);  
                    stream_COLON.add(COLON212);


                    pushFollow(FOLLOW_qualified_name_in_atom2397);
                    qualified_name213=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name213.getTree());

                    COLON214=(Token)match(input,COLON,FOLLOW_COLON_in_atom2399);  
                    stream_COLON.add(COLON214);


                    ID215=(Token)match(input,ID,FOLLOW_ID_in_atom2401);  
                    stream_ID.add(ID215);


                    LEFT_PAREN216=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_atom2403);  
                    stream_LEFT_PAREN.add(LEFT_PAREN216);


                    pushFollow(FOLLOW_function_expression_list_in_atom2405);
                    function_expression_list217=function_expression_list();

                    state._fsp--;

                    stream_function_expression_list.add(function_expression_list217.getTree());

                    RIGHT_PAREN218=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_atom2407);  
                    stream_RIGHT_PAREN.add(RIGHT_PAREN218);


                    // AST REWRITE
                    // elements: RIGHT_PAREN, qualified_name, ID, function_expression_list, LEFT_PAREN, PARENT, COLON, COLON
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1203:89: -> ^( FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1204:4: ^( FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(FUNCTION_CALL_PARENT, "FUNCTION_CALL_PARENT")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_PARENT.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        stream_COLON.nextNode()
                        );

                        adaptor.addChild(root_1, stream_qualified_name.nextTree());

                        adaptor.addChild(root_1, 
                        stream_COLON.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        stream_LEFT_PAREN.nextNode()
                        );

                        adaptor.addChild(root_1, stream_function_expression_list.nextTree());

                        adaptor.addChild(root_1, 
                        stream_RIGHT_PAREN.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 6 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1205:4: ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN
                    {
                    ME219=(Token)match(input,ME,FOLLOW_ME_in_atom2437);  
                    stream_ME.add(ME219);


                    COLON220=(Token)match(input,COLON,FOLLOW_COLON_in_atom2439);  
                    stream_COLON.add(COLON220);


                    pushFollow(FOLLOW_qualified_name_in_atom2441);
                    qualified_name221=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name221.getTree());

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1205:28: ( COLON ID )?
                    int alt77=2;
                    int LA77_0 = input.LA(1);

                    if ( (LA77_0==COLON) ) {
                        alt77=1;
                    }
                    switch (alt77) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1205:29: COLON ID
                            {
                            COLON222=(Token)match(input,COLON,FOLLOW_COLON_in_atom2444);  
                            stream_COLON.add(COLON222);


                            ID223=(Token)match(input,ID,FOLLOW_ID_in_atom2446);  
                            stream_ID.add(ID223);


                            }
                            break;

                    }


                    LEFT_PAREN224=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_atom2450);  
                    stream_LEFT_PAREN.add(LEFT_PAREN224);


                    pushFollow(FOLLOW_function_expression_list_in_atom2452);
                    function_expression_list225=function_expression_list();

                    state._fsp--;

                    stream_function_expression_list.add(function_expression_list225.getTree());

                    RIGHT_PAREN226=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_atom2454);  
                    stream_RIGHT_PAREN.add(RIGHT_PAREN226);


                    // AST REWRITE
                    // elements: RIGHT_PAREN, ID, function_expression_list, LEFT_PAREN, qualified_name, ME, COLON, COLON
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1205:88: -> ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1206:4: ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(FUNCTION_CALL_THIS, "FUNCTION_CALL_THIS")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ME.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        stream_COLON.nextNode()
                        );

                        adaptor.addChild(root_1, stream_qualified_name.nextTree());

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1206:49: ( COLON ID )?
                        if ( stream_ID.hasNext()||stream_COLON.hasNext() ) {
                            adaptor.addChild(root_1, 
                            stream_COLON.nextNode()
                            );

                            adaptor.addChild(root_1, 
                            stream_ID.nextNode()
                            );

                        }
                        stream_ID.reset();
                        stream_COLON.reset();

                        adaptor.addChild(root_1, 
                        stream_LEFT_PAREN.nextNode()
                        );

                        adaptor.addChild(root_1, stream_function_expression_list.nextTree());

                        adaptor.addChild(root_1, 
                        stream_RIGHT_PAREN.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 7 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1207:4: ( MINUS )? INT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1207:4: ( MINUS )?
                    int alt78=2;
                    int LA78_0 = input.LA(1);

                    if ( (LA78_0==MINUS) ) {
                        alt78=1;
                    }
                    switch (alt78) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1207:5: MINUS
                            {
                            MINUS227=(Token)match(input,MINUS,FOLLOW_MINUS_in_atom2488); 
                            MINUS227_tree = 
                            (CommonTree)adaptor.create(MINUS227)
                            ;
                            adaptor.addChild(root_0, MINUS227_tree);


                            }
                            break;

                    }


                    INT228=(Token)match(input,INT,FOLLOW_INT_in_atom2492); 
                    INT228_tree = 
                    (CommonTree)adaptor.create(INT228)
                    ;
                    adaptor.addChild(root_0, INT228_tree);


                    }
                    break;
                case 8 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1208:4: BOOLEAN
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    BOOLEAN229=(Token)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_atom2497); 
                    BOOLEAN229_tree = 
                    (CommonTree)adaptor.create(BOOLEAN229)
                    ;
                    adaptor.addChild(root_0, BOOLEAN229_tree);


                    }
                    break;
                case 9 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1209:4: ( MINUS )? DECIMAL
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1209:4: ( MINUS )?
                    int alt79=2;
                    int LA79_0 = input.LA(1);

                    if ( (LA79_0==MINUS) ) {
                        alt79=1;
                    }
                    switch (alt79) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1209:5: MINUS
                            {
                            MINUS230=(Token)match(input,MINUS,FOLLOW_MINUS_in_atom2503); 
                            MINUS230_tree = 
                            (CommonTree)adaptor.create(MINUS230)
                            ;
                            adaptor.addChild(root_0, MINUS230_tree);


                            }
                            break;

                    }


                    DECIMAL231=(Token)match(input,DECIMAL,FOLLOW_DECIMAL_in_atom2507); 
                    DECIMAL231_tree = 
                    (CommonTree)adaptor.create(DECIMAL231)
                    ;
                    adaptor.addChild(root_0, DECIMAL231_tree);


                    }
                    break;
                case 10 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1210:4: STRING
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    STRING232=(Token)match(input,STRING,FOLLOW_STRING_in_atom2513); 
                    STRING232_tree = 
                    (CommonTree)adaptor.create(STRING232)
                    ;
                    adaptor.addChild(root_0, STRING232_tree);


                    }
                    break;
                case 11 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1211:4: NULL
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    NULL233=(Token)match(input,NULL,FOLLOW_NULL_in_atom2518); 
                    NULL233_tree = 
                    (CommonTree)adaptor.create(NULL233)
                    ;
                    adaptor.addChild(root_0, NULL233_tree);


                    }
                    break;
                case 12 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1212:4: ME
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    ME234=(Token)match(input,ME,FOLLOW_ME_in_atom2523); 
                    ME234_tree = 
                    (CommonTree)adaptor.create(ME234)
                    ;
                    adaptor.addChild(root_0, ME234_tree);


                    }
                    break;
                case 13 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1213:5: INPUT LEFT_PAREN expression RIGHT_PAREN
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    INPUT235=(Token)match(input,INPUT,FOLLOW_INPUT_in_atom2529); 
                    INPUT235_tree = 
                    (CommonTree)adaptor.create(INPUT235)
                    ;
                    adaptor.addChild(root_0, INPUT235_tree);


                    LEFT_PAREN236=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_atom2531); 
                    LEFT_PAREN236_tree = 
                    (CommonTree)adaptor.create(LEFT_PAREN236)
                    ;
                    adaptor.addChild(root_0, LEFT_PAREN236_tree);


                    pushFollow(FOLLOW_expression_in_atom2533);
                    expression237=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression237.getTree());

                    RIGHT_PAREN238=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_atom2535); 
                    RIGHT_PAREN238_tree = 
                    (CommonTree)adaptor.create(RIGHT_PAREN238)
                    ;
                    adaptor.addChild(root_0, RIGHT_PAREN238_tree);


                    }
                    break;
                case 14 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1214:4: LEFT_PAREN expression RIGHT_PAREN
                    {
                    LEFT_PAREN239=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_atom2540);  
                    stream_LEFT_PAREN.add(LEFT_PAREN239);


                    pushFollow(FOLLOW_expression_in_atom2542);
                    expression240=expression();

                    state._fsp--;

                    stream_expression.add(expression240.getTree());

                    RIGHT_PAREN241=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_atom2544);  
                    stream_RIGHT_PAREN.add(RIGHT_PAREN241);


                    // AST REWRITE
                    // elements: expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1214:38: -> ^( expression )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1214:41: ^( expression )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_expression.nextNode(), root_1);

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "atom"


    public static class function_expression_list_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "function_expression_list"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1217:1: function_expression_list : ( expression ( COMMA expression )* )? -> ^( FUNCTION_EXPRESSION_LIST ( expression )* ) ;
    public final QuorumParser.function_expression_list_return function_expression_list() throws RecognitionException {
        QuorumParser.function_expression_list_return retval = new QuorumParser.function_expression_list_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token COMMA243=null;
        QuorumParser.expression_return expression242 =null;

        QuorumParser.expression_return expression244 =null;


        CommonTree COMMA243_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1218:2: ( ( expression ( COMMA expression )* )? -> ^( FUNCTION_EXPRESSION_LIST ( expression )* ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1219:2: ( expression ( COMMA expression )* )?
            {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1219:2: ( expression ( COMMA expression )* )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==BOOLEAN||LA82_0==CAST||LA82_0==DECIMAL||LA82_0==ID||(LA82_0 >= INPUT && LA82_0 <= INT)||LA82_0==LEFT_PAREN||(LA82_0 >= ME && LA82_0 <= MINUS)||LA82_0==NOT||LA82_0==NULL||LA82_0==PARENT||LA82_0==STRING) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1219:3: expression ( COMMA expression )*
                    {
                    pushFollow(FOLLOW_expression_in_function_expression_list2564);
                    expression242=expression();

                    state._fsp--;

                    stream_expression.add(expression242.getTree());

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1219:14: ( COMMA expression )*
                    loop81:
                    do {
                        int alt81=2;
                        int LA81_0 = input.LA(1);

                        if ( (LA81_0==COMMA) ) {
                            alt81=1;
                        }


                        switch (alt81) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1219:15: COMMA expression
                    	    {
                    	    COMMA243=(Token)match(input,COMMA,FOLLOW_COMMA_in_function_expression_list2567);  
                    	    stream_COMMA.add(COMMA243);


                    	    pushFollow(FOLLOW_expression_in_function_expression_list2569);
                    	    expression244=expression();

                    	    state._fsp--;

                    	    stream_expression.add(expression244.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop81;
                        }
                    } while (true);


                    }
                    break;

            }


            // AST REWRITE
            // elements: expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 1220:2: -> ^( FUNCTION_EXPRESSION_LIST ( expression )* )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1220:5: ^( FUNCTION_EXPRESSION_LIST ( expression )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(FUNCTION_EXPRESSION_LIST, "FUNCTION_EXPRESSION_LIST")
                , root_1);

                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1220:32: ( expression )*
                while ( stream_expression.hasNext() ) {
                    adaptor.addChild(root_1, stream_expression.nextTree());

                }
                stream_expression.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "function_expression_list"

    // Delegated rules


    protected DFA4 dfa4 = new DFA4(this);
    protected DFA34 dfa34 = new DFA34(this);
    protected DFA58 dfa58 = new DFA58(this);
    protected DFA80 dfa80 = new DFA80(this);
    static final String DFA4_eotS =
        "\16\uffff";
    static final String DFA4_eofS =
        "\16\uffff";
    static final String DFA4_minS =
        "\1\4\2\45\1\uffff\2\4\1\45\2\uffff\1\45\2\uffff\2\4";
    static final String DFA4_maxS =
        "\1\133\2\45\1\uffff\2\133\1\45\2\uffff\1\45\2\uffff\2\133";
    static final String DFA4_acceptS =
        "\3\uffff\1\5\3\uffff\1\1\1\3\1\uffff\1\2\1\4\2\uffff";
    static final String DFA4_specialS =
        "\16\uffff}>";
    static final String[] DFA4_transitionS = {
            "\2\3\2\uffff\1\3\1\uffff\1\3\1\uffff\2\3\27\uffff\2\3\3\uffff"+
            "\1\3\4\uffff\1\3\3\uffff\1\3\5\uffff\1\3\1\uffff\1\3\3\uffff"+
            "\1\1\1\3\3\uffff\3\3\4\uffff\2\3\4\uffff\1\3\5\uffff\1\3\3\uffff"+
            "\1\2",
            "\1\4",
            "\1\5",
            "",
            "\2\10\2\uffff\1\10\1\uffff\1\10\1\uffff\2\10\27\uffff\2\10"+
            "\3\uffff\1\10\4\uffff\1\10\3\uffff\1\10\5\uffff\1\10\1\uffff"+
            "\1\10\4\uffff\1\10\1\uffff\1\6\1\uffff\3\10\4\uffff\2\10\4\uffff"+
            "\1\10\5\uffff\1\10\3\uffff\1\7",
            "\2\13\2\uffff\1\13\1\uffff\1\13\1\uffff\2\13\27\uffff\2\13"+
            "\3\uffff\1\13\4\uffff\1\13\3\uffff\1\13\5\uffff\1\13\1\uffff"+
            "\1\13\3\uffff\1\12\1\13\1\uffff\1\11\1\uffff\3\13\4\uffff\2"+
            "\13\4\uffff\1\13\5\uffff\1\13\3\uffff\1\2",
            "\1\14",
            "",
            "",
            "\1\15",
            "",
            "",
            "\2\10\2\uffff\1\10\1\uffff\1\10\1\uffff\2\10\27\uffff\2\10"+
            "\3\uffff\1\10\4\uffff\1\10\3\uffff\1\10\5\uffff\1\10\1\uffff"+
            "\1\10\4\uffff\1\10\1\uffff\1\6\1\uffff\3\10\4\uffff\2\10\4\uffff"+
            "\1\10\5\uffff\1\10\3\uffff\1\7",
            "\2\13\2\uffff\1\13\1\uffff\1\13\1\uffff\2\13\27\uffff\2\13"+
            "\3\uffff\1\13\4\uffff\1\13\3\uffff\1\13\5\uffff\1\13\1\uffff"+
            "\1\13\3\uffff\1\12\1\13\1\uffff\1\11\1\uffff\3\13\4\uffff\2"+
            "\13\4\uffff\1\13\5\uffff\1\13\3\uffff\1\2"
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
            return "272:3: ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ |)";
        }
    }
    static final String DFA34_eotS =
        "\31\uffff";
    static final String DFA34_eofS =
        "\31\uffff";
    static final String DFA34_minS =
        "\1\5\3\16\10\uffff\2\45\1\uffff\2\45\1\16\1\32\2\16\2\45\1\16\1"+
        "\32";
    static final String DFA34_maxS =
        "\1\127\1\102\2\16\10\uffff\1\45\1\100\1\uffff\2\45\1\102\1\53\2"+
        "\102\2\45\1\102\1\53";
    static final String DFA34_acceptS =
        "\4\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\2\uffff\1\1\12\uffff";
    static final String DFA34_specialS =
        "\31\uffff}>";
    static final String[] DFA34_transitionS = {
            "\1\13\4\uffff\1\5\1\uffff\1\12\30\uffff\1\1\1\4\3\uffff\1\5"+
            "\4\uffff\1\3\11\uffff\1\5\6\uffff\1\2\3\uffff\1\10\2\5\4\uffff"+
            "\1\6\1\7\4\uffff\1\11\5\uffff\1\5",
            "\1\15\13\uffff\1\5\12\uffff\1\5\5\uffff\1\16\1\uffff\1\5\24"+
            "\uffff\1\14",
            "\1\17",
            "\1\20",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\21",
            "\1\22\32\uffff\1\5",
            "",
            "\1\23",
            "\1\24",
            "\1\15\26\uffff\1\5\5\uffff\1\16\1\uffff\1\5\24\uffff\1\14",
            "\1\5\20\uffff\1\16",
            "\1\26\63\uffff\1\25",
            "\1\16\13\uffff\1\5\20\uffff\1\16\26\uffff\1\16",
            "\1\27",
            "\1\30",
            "\1\26\63\uffff\1\25",
            "\1\5\20\uffff\1\16"
    };

    static final short[] DFA34_eot = DFA.unpackEncodedString(DFA34_eotS);
    static final short[] DFA34_eof = DFA.unpackEncodedString(DFA34_eofS);
    static final char[] DFA34_min = DFA.unpackEncodedStringToUnsignedChars(DFA34_minS);
    static final char[] DFA34_max = DFA.unpackEncodedStringToUnsignedChars(DFA34_maxS);
    static final short[] DFA34_accept = DFA.unpackEncodedString(DFA34_acceptS);
    static final short[] DFA34_special = DFA.unpackEncodedString(DFA34_specialS);
    static final short[][] DFA34_transition;

    static {
        int numStates = DFA34_transitionS.length;
        DFA34_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA34_transition[i] = DFA.unpackEncodedString(DFA34_transitionS[i]);
        }
    }

    class DFA34 extends DFA {

        public DFA34(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 34;
            this.eot = DFA34_eot;
            this.eof = DFA34_eof;
            this.min = DFA34_min;
            this.max = DFA34_max;
            this.accept = DFA34_accept;
            this.special = DFA34_special;
            this.transition = DFA34_transition;
        }
        public String getDescription() {
            return "701:1: statement : ( solo_method_call | if_statement | assignment_statement | loop_statement | return_statement | print_statement | speak_statement | check_statement | alert_statement );";
        }
    }
    static final String DFA58_eotS =
        "\7\uffff";
    static final String DFA58_eofS =
        "\7\uffff";
    static final String DFA58_minS =
        "\1\12\1\uffff\1\16\1\uffff\1\45\1\uffff\1\16";
    static final String DFA58_maxS =
        "\1\127\1\uffff\1\102\1\uffff\1\45\1\uffff\1\102";
    static final String DFA58_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\1\uffff\1\2\1\uffff";
    static final String DFA58_specialS =
        "\7\uffff}>";
    static final String[] DFA58_transitionS = {
            "\1\3\32\uffff\1\2\4\uffff\1\3\4\uffff\1\1\11\uffff\1\3\6\uffff"+
            "\1\1\4\uffff\2\3\20\uffff\1\3",
            "",
            "\1\5\13\uffff\1\1\12\uffff\1\3\7\uffff\1\3\24\uffff\1\4",
            "",
            "\1\6",
            "",
            "\1\5\26\uffff\1\3\7\uffff\1\3\24\uffff\1\4"
    };

    static final short[] DFA58_eot = DFA.unpackEncodedString(DFA58_eotS);
    static final short[] DFA58_eof = DFA.unpackEncodedString(DFA58_eofS);
    static final char[] DFA58_min = DFA.unpackEncodedStringToUnsignedChars(DFA58_minS);
    static final char[] DFA58_max = DFA.unpackEncodedStringToUnsignedChars(DFA58_maxS);
    static final short[] DFA58_accept = DFA.unpackEncodedString(DFA58_acceptS);
    static final short[] DFA58_special = DFA.unpackEncodedString(DFA58_specialS);
    static final short[][] DFA58_transition;

    static {
        int numStates = DFA58_transitionS.length;
        DFA58_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA58_transition[i] = DFA.unpackEncodedString(DFA58_transitionS[i]);
        }
    }

    class DFA58 extends DFA {

        public DFA58(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 58;
            this.eot = DFA58_eot;
            this.eof = DFA58_eof;
            this.min = DFA58_min;
            this.max = DFA58_max;
            this.accept = DFA58_accept;
            this.special = DFA58_special;
            this.transition = DFA58_transition;
        }
        public String getDescription() {
            return "976:1: assignment_statement : ( (sel= selector COLON )? ID rhs= assign_right_hand_side |obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side | (modifier= access_modifier )? type= assignment_declaration name= ID (rhs= assign_right_hand_side )? );";
        }
    }
    static final String DFA80_eotS =
        "\41\uffff";
    static final String DFA80_eofS =
        "\1\uffff\1\16\1\uffff\1\22\17\uffff\2\16\2\uffff\1\34\6\uffff\2"+
        "\34\1\uffff";
    static final String DFA80_minS =
        "\1\11\1\4\1\16\1\4\1\22\7\uffff\2\45\2\uffff\2\45\1\uffff\2\4\1"+
        "\uffff\1\16\1\4\3\45\2\uffff\1\16\2\4\1\uffff";
    static final String DFA80_maxS =
        "\1\126\1\130\1\16\1\130\1\51\7\uffff\1\45\1\100\2\uffff\2\45\1\uffff"+
        "\2\130\1\uffff\1\102\1\130\3\45\2\uffff\1\102\2\130\1\uffff";
    static final String DFA80_acceptS =
        "\5\uffff\1\7\1\10\1\11\1\12\1\13\1\15\1\16\2\uffff\1\1\1\3\2\uffff"+
        "\1\14\2\uffff\1\2\5\uffff\1\6\1\4\3\uffff\1\5";
    static final String DFA80_specialS =
        "\41\uffff}>";
    static final String[] DFA80_transitionS = {
            "\1\6\10\uffff\1\7\22\uffff\1\1\2\uffff\1\12\1\5\1\uffff\1\13"+
            "\3\uffff\1\3\1\4\7\uffff\1\11\7\uffff\1\2\25\uffff\1\10",
            "\5\16\1\uffff\1\16\1\uffff\1\16\1\uffff\1\15\1\16\3\uffff\2"+
            "\16\1\uffff\2\16\1\uffff\2\16\10\uffff\5\16\2\uffff\1\16\1\17"+
            "\1\uffff\7\16\2\uffff\1\16\2\uffff\1\16\1\uffff\1\16\1\uffff"+
            "\1\16\2\uffff\1\16\1\uffff\1\14\4\16\4\uffff\2\16\1\uffff\1"+
            "\16\2\uffff\1\16\5\uffff\2\16",
            "\1\20",
            "\5\22\1\uffff\1\22\1\uffff\1\22\1\uffff\1\21\1\22\3\uffff\2"+
            "\22\1\uffff\2\22\1\uffff\2\22\10\uffff\5\22\2\uffff\1\22\2\uffff"+
            "\7\22\2\uffff\1\22\2\uffff\1\22\1\uffff\1\22\1\uffff\1\22\2"+
            "\uffff\1\22\2\uffff\4\22\4\uffff\2\22\1\uffff\1\22\2\uffff\1"+
            "\22\5\uffff\2\22",
            "\1\7\26\uffff\1\5",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\23",
            "\1\24\32\uffff\1\25",
            "",
            "",
            "\1\26",
            "\1\27",
            "",
            "\5\16\1\uffff\1\16\1\uffff\1\16\1\uffff\1\15\1\16\3\uffff\2"+
            "\16\1\uffff\2\16\1\uffff\2\16\10\uffff\5\16\2\uffff\1\16\1\17"+
            "\1\uffff\7\16\2\uffff\1\16\2\uffff\1\16\1\uffff\1\16\1\uffff"+
            "\1\16\2\uffff\1\16\1\uffff\1\14\4\16\4\uffff\2\16\1\uffff\1"+
            "\16\2\uffff\1\16\5\uffff\2\16",
            "\5\16\1\uffff\1\16\1\uffff\1\16\2\uffff\1\16\3\uffff\2\16\1"+
            "\uffff\2\16\1\uffff\2\16\10\uffff\5\16\2\uffff\1\16\1\17\1\uffff"+
            "\7\16\2\uffff\1\16\2\uffff\1\16\1\uffff\1\16\1\uffff\1\16\2"+
            "\uffff\1\16\2\uffff\4\16\4\uffff\2\16\1\uffff\1\16\2\uffff\1"+
            "\16\5\uffff\2\16",
            "",
            "\1\31\63\uffff\1\30",
            "\5\34\1\uffff\1\34\1\uffff\1\34\1\uffff\1\33\1\34\3\uffff\2"+
            "\34\1\uffff\2\34\1\uffff\2\34\10\uffff\5\34\2\uffff\1\34\1\33"+
            "\1\uffff\7\34\2\uffff\1\34\2\uffff\1\34\1\uffff\1\34\1\uffff"+
            "\1\34\2\uffff\1\34\1\uffff\1\32\4\34\4\uffff\2\34\1\uffff\1"+
            "\34\2\uffff\1\34\5\uffff\2\34",
            "\1\35",
            "\1\36",
            "\1\37",
            "",
            "",
            "\1\31\63\uffff\1\30",
            "\5\34\1\uffff\1\34\1\uffff\1\34\2\uffff\1\34\3\uffff\2\34\1"+
            "\uffff\2\34\1\uffff\2\34\10\uffff\5\34\2\uffff\1\34\1\40\1\uffff"+
            "\7\34\2\uffff\1\34\2\uffff\1\34\1\uffff\1\34\1\uffff\1\34\2"+
            "\uffff\1\34\1\uffff\5\34\4\uffff\2\34\1\uffff\1\34\2\uffff\1"+
            "\34\5\uffff\2\34",
            "\5\34\1\uffff\1\34\1\uffff\1\34\1\uffff\1\33\1\34\3\uffff\2"+
            "\34\1\uffff\2\34\1\uffff\2\34\10\uffff\5\34\2\uffff\1\34\1\33"+
            "\1\uffff\7\34\2\uffff\1\34\2\uffff\1\34\1\uffff\1\34\1\uffff"+
            "\1\34\2\uffff\1\34\1\uffff\1\32\4\34\4\uffff\2\34\1\uffff\1"+
            "\34\2\uffff\1\34\5\uffff\2\34",
            ""
    };

    static final short[] DFA80_eot = DFA.unpackEncodedString(DFA80_eotS);
    static final short[] DFA80_eof = DFA.unpackEncodedString(DFA80_eofS);
    static final char[] DFA80_min = DFA.unpackEncodedStringToUnsignedChars(DFA80_minS);
    static final char[] DFA80_max = DFA.unpackEncodedStringToUnsignedChars(DFA80_maxS);
    static final short[] DFA80_accept = DFA.unpackEncodedString(DFA80_acceptS);
    static final short[] DFA80_special = DFA.unpackEncodedString(DFA80_specialS);
    static final short[][] DFA80_transition;

    static {
        int numStates = DFA80_transitionS.length;
        DFA80_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA80_transition[i] = DFA.unpackEncodedString(DFA80_transitionS[i]);
        }
    }

    class DFA80 extends DFA {

        public DFA80(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 80;
            this.eot = DFA80_eot;
            this.eof = DFA80_eof;
            this.min = DFA80_min;
            this.max = DFA80_max;
            this.accept = DFA80_accept;
            this.special = DFA80_special;
            this.transition = DFA80_transition;
        }
        public String getDescription() {
            return "1194:1: atom : ( qualified_name ( COLON ID )? -> ^( QUALIFIED_SOLO_EXPRESSION qualified_name ( COLON ID )? ) | qualified_name COLON PARENT COLON qualified_name COLON ID -> ^( QUALIFIED_SOLO_PARENT_EXPRESSON qualified_name COLON PARENT COLON qualified_name COLON ID ) | qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN ) | selector COLON qualified_name -> ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name ) | PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN ) | ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN ) | ( MINUS )? INT | BOOLEAN | ( MINUS )? DECIMAL | STRING | NULL | ME | INPUT LEFT_PAREN expression RIGHT_PAREN | LEFT_PAREN expression RIGHT_PAREN -> ^( expression ) );";
        }
    }
 

    public static final BitSet FOLLOW_package_rule_in_start150 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_reference_in_start152 = new BitSet(new long[]{0x0A08846000003530L,0x0000000008821871L});
    public static final BitSet FOLLOW_reference_in_start159 = new BitSet(new long[]{0x8000000000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_package_rule_in_start162 = new BitSet(new long[]{0x0A08846000003530L,0x0000000000821871L});
    public static final BitSet FOLLOW_package_rule_in_start167 = new BitSet(new long[]{0x0A08846000003530L,0x0000000000821871L});
    public static final BitSet FOLLOW_reference_in_start172 = new BitSet(new long[]{0x0A08846000003530L,0x0000000008821871L});
    public static final BitSet FOLLOW_class_declaration_in_start181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_start184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PACKAGE_NAME_in_package_rule195 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_qualified_name_in_package_rule199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_USE_in_reference217 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_qualified_name_in_reference223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_in_class_declaration255 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ID_in_class_declaration257 = new BitSet(new long[]{0x0A08A4A002000510L,0x0000000000800061L});
    public static final BitSet FOLLOW_generic_declaration_in_class_declaration268 = new BitSet(new long[]{0x0A0884A002000510L,0x0000000000800061L});
    public static final BitSet FOLLOW_inherit_stmnts_in_class_declaration275 = new BitSet(new long[]{0x0A08842002000510L,0x0000000000800061L});
    public static final BitSet FOLLOW_class_stmnts_in_class_declaration286 = new BitSet(new long[]{0x0A08842002000510L,0x0000000000800061L});
    public static final BitSet FOLLOW_END_in_class_declaration290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_no_class_stmnts_in_class_declaration324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_no_class_stmnts343 = new BitSet(new long[]{0x0200846000001422L,0x0000000000821871L});
    public static final BitSet FOLLOW_access_modifier_in_no_class_stmnts352 = new BitSet(new long[]{0x0808000000000110L});
    public static final BitSet FOLLOW_method_declaration_in_no_class_stmnts355 = new BitSet(new long[]{0x0808000000000112L,0x0000000000000060L});
    public static final BitSet FOLLOW_INHERITS_in_inherit_stmnts368 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_qualified_name_in_inherit_stmnts372 = new BitSet(new long[]{0x0000200000008002L});
    public static final BitSet FOLLOW_generic_statement_in_inherit_stmnts376 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_COMMA_in_inherit_stmnts384 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_qualified_name_in_inherit_stmnts388 = new BitSet(new long[]{0x0000200000008002L});
    public static final BitSet FOLLOW_generic_statement_in_inherit_stmnts392 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_PUBLIC_in_access_modifier429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIVATE_in_access_modifier437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_statement_in_class_stmnts455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_access_modifier_in_class_stmnts466 = new BitSet(new long[]{0x0808000000000110L});
    public static final BitSet FOLLOW_method_declaration_in_class_stmnts474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_method_declaration492 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ID_in_method_declaration494 = new BitSet(new long[]{0x02008C6002001420L,0x0000000000823871L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_method_declaration504 = new BitSet(new long[]{0x0200042000000400L,0x0000000000804000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration507 = new BitSet(new long[]{0x0000000000008000L,0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_method_declaration511 = new BitSet(new long[]{0x0200042000000400L,0x0000000000800000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration513 = new BitSet(new long[]{0x0000000000008000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_method_declaration520 = new BitSet(new long[]{0x0200846002001420L,0x0000000000823871L});
    public static final BitSet FOLLOW_RETURNS_in_method_declaration526 = new BitSet(new long[]{0x0200042000000400L,0x0000000000800000L});
    public static final BitSet FOLLOW_assignment_declaration_in_method_declaration532 = new BitSet(new long[]{0x0200846002001420L,0x0000000000821871L});
    public static final BitSet FOLLOW_block_in_method_declaration542 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_END_in_method_declaration545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BLUEPRINT_in_method_declaration577 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ACTION_in_method_declaration579 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ID_in_method_declaration581 = new BitSet(new long[]{0x0000080000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_method_declaration591 = new BitSet(new long[]{0x0200042000000400L,0x0000000000804000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration594 = new BitSet(new long[]{0x0000000000008000L,0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_method_declaration598 = new BitSet(new long[]{0x0200042000000400L,0x0000000000800000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration600 = new BitSet(new long[]{0x0000000000008000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_method_declaration607 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_RETURNS_in_method_declaration613 = new BitSet(new long[]{0x0200042000000400L,0x0000000000800000L});
    public static final BitSet FOLLOW_assignment_declaration_in_method_declaration619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NATIVE_in_method_declaration651 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ACTION_in_method_declaration653 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ID_in_method_declaration655 = new BitSet(new long[]{0x0000080000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_method_declaration665 = new BitSet(new long[]{0x0200042000000400L,0x0000000000804000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration668 = new BitSet(new long[]{0x0000000000008000L,0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_method_declaration672 = new BitSet(new long[]{0x0200042000000400L,0x0000000000800000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration674 = new BitSet(new long[]{0x0000000000008000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_method_declaration681 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_RETURNS_in_method_declaration687 = new BitSet(new long[]{0x0200042000000400L,0x0000000000800000L});
    public static final BitSet FOLLOW_assignment_declaration_in_method_declaration693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ON_CREATE_in_method_declaration725 = new BitSet(new long[]{0x0200846002001420L,0x0000000000821871L});
    public static final BitSet FOLLOW_block_in_method_declaration732 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_END_in_method_declaration734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_declaration_in_formal_parameter759 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ID_in_formal_parameter761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_qualified_name792 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_PERIOD_in_qualified_name795 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ID_in_qualified_name799 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_block833 = new BitSet(new long[]{0x0200846000001422L,0x0000000000821871L});
    public static final BitSet FOLLOW_solo_method_call_in_statement856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_statement_in_statement861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_statement_in_statement866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_loop_statement_in_statement871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_statement_in_statement876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_print_statement_in_statement881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_speak_statement_in_statement886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_check_statement_in_statement891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_alert_statement_in_statement896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_solo_method_call909 = new BitSet(new long[]{0x0000080000004000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call912 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ID_in_solo_method_call914 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call918 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000404001L});
    public static final BitSet FOLLOW_expression_in_solo_method_call921 = new BitSet(new long[]{0x0000000000008000L,0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call924 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_expression_in_solo_method_call926 = new BitSet(new long[]{0x0000000000008000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENT_in_solo_method_call971 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call973 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_qualified_name_in_solo_method_call975 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call977 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ID_in_solo_method_call979 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call981 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000404001L});
    public static final BitSet FOLLOW_expression_in_solo_method_call984 = new BitSet(new long[]{0x0000000000008000L,0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call987 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_expression_in_solo_method_call989 = new BitSet(new long[]{0x0000000000008000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ME_in_solo_method_call1035 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call1037 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_qualified_name_in_solo_method_call1039 = new BitSet(new long[]{0x0000080000004000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call1042 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ID_in_solo_method_call1044 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call1048 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000404001L});
    public static final BitSet FOLLOW_expression_in_solo_method_call1051 = new BitSet(new long[]{0x0000000000008000L,0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call1054 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_expression_in_solo_method_call1056 = new BitSet(new long[]{0x0000000000008000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call1062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALERT_in_alert_statement1113 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_alert_statement1115 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_root_expression_in_alert_statement1117 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_alert_statement1120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHECK_in_check_statement1154 = new BitSet(new long[]{0x0200846000081460L,0x0000000000821871L});
    public static final BitSet FOLLOW_block_in_check_statement1157 = new BitSet(new long[]{0x0000000000080040L});
    public static final BitSet FOLLOW_DETECT_in_check_statement1172 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_detect_parameter_in_check_statement1184 = new BitSet(new long[]{0x0200846002081460L,0x0000000000821871L});
    public static final BitSet FOLLOW_block_in_check_statement1186 = new BitSet(new long[]{0x0000000002080040L});
    public static final BitSet FOLLOW_ALWAYS_in_check_statement1210 = new BitSet(new long[]{0x0200846002001420L,0x0000000000821871L});
    public static final BitSet FOLLOW_block_in_check_statement1221 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ALWAYS_in_check_statement1243 = new BitSet(new long[]{0x0200846002001420L,0x0000000000821871L});
    public static final BitSet FOLLOW_block_in_check_statement1254 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_END_in_check_statement1269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_detect_parameter1297 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_OF_TYPE_in_detect_parameter1309 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_qualified_name_in_detect_parameter1311 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_OR_in_detect_parameter1313 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_qualified_name_in_detect_parameter1315 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_PRINT_in_print_statement1350 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_root_expression_in_print_statement1352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAY_in_speak_statement1364 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_root_expression_in_speak_statement1366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_return_statement1377 = new BitSet(new long[]{0x01A18B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_root_expression_in_return_statement1381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOW_in_return_statement1385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_generic_declaration1402 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ID_in_generic_declaration1406 = new BitSet(new long[]{0x0000000800008000L});
    public static final BitSet FOLLOW_COMMA_in_generic_declaration1409 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ID_in_generic_declaration1413 = new BitSet(new long[]{0x0000000800008000L});
    public static final BitSet FOLLOW_GREATER_in_generic_declaration1417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_generic_statement1458 = new BitSet(new long[]{0x0200042000000400L,0x0000000000800000L});
    public static final BitSet FOLLOW_assignment_declaration_in_generic_statement1468 = new BitSet(new long[]{0x0000000800008000L});
    public static final BitSet FOLLOW_COMMA_in_generic_statement1475 = new BitSet(new long[]{0x0200042000000400L,0x0000000000800000L});
    public static final BitSet FOLLOW_assignment_declaration_in_generic_statement1479 = new BitSet(new long[]{0x0000000800008000L});
    public static final BitSet FOLLOW_GREATER_in_generic_statement1489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_class_type1529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_assignment_declaration1550 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_generic_statement_in_assignment_declaration1554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_KEYWORD_in_assignment_declaration1563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_KEYWORD_in_assignment_declaration1571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TEXT_in_assignment_declaration1579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_KEYWORD_in_assignment_declaration1587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_assignment_statement1612 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1614 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ID_in_assignment_statement1618 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_assign_right_hand_side_in_assignment_statement1624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_assignment_statement1635 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1638 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_PARENT_in_assignment_statement1640 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1642 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_qualified_name_in_assignment_statement1646 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1650 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ID_in_assignment_statement1652 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_assign_right_hand_side_in_assignment_statement1656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_access_modifier_in_assignment_statement1665 = new BitSet(new long[]{0x0200042000000400L,0x0000000000800000L});
    public static final BitSet FOLLOW_assignment_declaration_in_assignment_statement1677 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ID_in_assignment_statement1683 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_assign_right_hand_side_in_assignment_statement1689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALITY_in_assign_right_hand_side1715 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_root_expression_in_assign_right_hand_side1717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_if_statement1742 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_root_expression_in_if_statement1744 = new BitSet(new long[]{0x0200846002C01420L,0x0000000000821871L});
    public static final BitSet FOLLOW_block_in_if_statement1750 = new BitSet(new long[]{0x0000000002C00000L});
    public static final BitSet FOLLOW_ELSE_IF_in_if_statement1762 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_root_expression_in_if_statement1770 = new BitSet(new long[]{0x0200846002C01420L,0x0000000000821871L});
    public static final BitSet FOLLOW_block_in_if_statement1776 = new BitSet(new long[]{0x0000000002C00000L});
    public static final BitSet FOLLOW_ELSE_in_if_statement1794 = new BitSet(new long[]{0x0200846002001420L,0x0000000000821871L});
    public static final BitSet FOLLOW_block_in_if_statement1802 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_END_in_if_statement1818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REPEAT_in_loop_statement1843 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000014400001L});
    public static final BitSet FOLLOW_root_expression_in_loop_statement1911 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_TIMES_in_loop_statement1913 = new BitSet(new long[]{0x0200846002001420L,0x0000000000821871L});
    public static final BitSet FOLLOW_set_in_loop_statement1921 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_root_expression_in_loop_statement1929 = new BitSet(new long[]{0x0200846002001420L,0x0000000000821871L});
    public static final BitSet FOLLOW_block_in_loop_statement1938 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_END_in_loop_statement1940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENT_in_selector1964 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_selector1966 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_qualified_name_in_selector1970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ME_in_selector1988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_root_expression2002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_expression2022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_in_or2034 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_OR_in_or2037 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_and_in_or2041 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_equality_in_and2054 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_AND_in_and2057 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_equality_in_and2061 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_isa_operation_in_equality2072 = new BitSet(new long[]{0x0040000004000002L});
    public static final BitSet FOLLOW_EQUALITY_in_equality2076 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_NOTEQUALS_in_equality2082 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_isa_operation_in_equality2087 = new BitSet(new long[]{0x0040000004000002L});
    public static final BitSet FOLLOW_comparison_in_isa_operation2099 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_INHERITS_in_isa_operation2102 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_class_type_in_isa_operation2106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_add_in_comparison2116 = new BitSet(new long[]{0x0000601800000002L});
    public static final BitSet FOLLOW_GREATER_in_comparison2120 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_GREATER_EQUAL_in_comparison2125 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_LESS_in_comparison2130 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_LESS_EQUAL_in_comparison2135 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_add_in_comparison2139 = new BitSet(new long[]{0x0000601800000002L});
    public static final BitSet FOLLOW_multiply_in_add2153 = new BitSet(new long[]{0x0001000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_PLUS_in_add2157 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_MINUS_in_add2162 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_multiply_in_add2166 = new BitSet(new long[]{0x0001000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_combo_expression_in_multiply2179 = new BitSet(new long[]{0x0006000000100002L});
    public static final BitSet FOLLOW_MULTIPLY_in_multiply2183 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_DIVIDE_in_multiply2188 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_MODULO_in_multiply2192 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_combo_expression_in_multiply2196 = new BitSet(new long[]{0x0006000000100002L});
    public static final BitSet FOLLOW_NOT_in_combo_expression2211 = new BitSet(new long[]{0x01018B2000040200L,0x0000000000400001L});
    public static final BitSet FOLLOW_atom_in_combo_expression2213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CAST_in_combo_expression2228 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_combo_expression2230 = new BitSet(new long[]{0x0200042000000400L,0x0000000000800000L});
    public static final BitSet FOLLOW_assignment_declaration_in_combo_expression2232 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_combo_expression2234 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_expression_in_combo_expression2236 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_combo_expression2238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_combo_expression2243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_atom2258 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_COLON_in_atom2261 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ID_in_atom2263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_atom2285 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_atom2287 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_PARENT_in_atom2289 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_atom2291 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_qualified_name_in_atom2293 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_atom2295 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ID_in_atom2297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_atom2322 = new BitSet(new long[]{0x0000080000004000L});
    public static final BitSet FOLLOW_COLON_in_atom2325 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ID_in_atom2327 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_atom2331 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000404001L});
    public static final BitSet FOLLOW_function_expression_list_in_atom2333 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_atom2335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_atom2364 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_atom2366 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_qualified_name_in_atom2368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENT_in_atom2393 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_atom2395 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_qualified_name_in_atom2397 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_atom2399 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ID_in_atom2401 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_atom2403 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000404001L});
    public static final BitSet FOLLOW_function_expression_list_in_atom2405 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_atom2407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ME_in_atom2437 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_atom2439 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_qualified_name_in_atom2441 = new BitSet(new long[]{0x0000080000004000L});
    public static final BitSet FOLLOW_COLON_in_atom2444 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_ID_in_atom2446 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_atom2450 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000404001L});
    public static final BitSet FOLLOW_function_expression_list_in_atom2452 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_atom2454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_atom2488 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_INT_in_atom2492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_atom2497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_atom2503 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DECIMAL_in_atom2507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_atom2513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_atom2518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ME_in_atom2523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INPUT_in_atom2529 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_atom2531 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_expression_in_atom2533 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_atom2535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_atom2540 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_expression_in_atom2542 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_atom2544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_function_expression_list2564 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_COMMA_in_function_expression_list2567 = new BitSet(new long[]{0x01218B2000040A00L,0x0000000000400001L});
    public static final BitSet FOLLOW_expression_in_function_expression_list2569 = new BitSet(new long[]{0x0000000000008002L});

}