// $ANTLR 3.4 /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g 2012-09-11 15:39:01



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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ACTION", "ALERT", "ALWAYS", "AND", "BLUEPRINT", "BOOLEAN", "BOOLEAN_KEYWORD", "CAST", "CHECK", "CLASS", "COLON", "COMMA", "COMMENTS", "CONSTANT", "CONSTRUCTOR", "DECIMAL", "DETECT", "DIVIDE", "DOUBLE_QUOTE", "ELSE", "ELSE_IF", "ELSE_IF_STATEMENT", "END", "EQUALITY", "EXPRESSION_STATEMENT", "FINAL_ELSE", "FPARAM", "FUNCTION_CALL", "FUNCTION_CALL_PARENT", "FUNCTION_CALL_THIS", "FUNCTION_EXPRESSION_LIST", "GENERIC", "GREATER", "GREATER_EQUAL", "ID", "IF", "INHERITS", "INPUT", "INT", "INTEGER_KEYWORD", "LEFT_PAREN", "LEFT_SQR_BRACE", "LESS", "LESS_EQUAL", "ME", "MINUS", "MODULO", "MULTIPLY", "NATIVE", "NEWLINE", "NOT", "NOTEQUALS", "NOW", "NULL", "NUMBER_KEYWORD", "OF_TYPE", "ON_CREATE", "ON_DESTROY", "OR", "OVER", "PACKAGE_NAME", "PARENT", "PAREN_WRAPPED_EXPRESSION", "PERIOD", "PLUS", "PRINT", "PRIVATE", "PUBLIC", "QUALIFIED_NAME", "QUALIFIED_SOLO_EXPRESSION", "QUALIFIED_SOLO_EXPRESSION_SELECTOR", "QUALIFIED_SOLO_PARENT_EXPRESSON", "REPEAT", "RETURN", "RETURNS", "RIGHT_PAREN", "RIGHT_SQR_BRACE", "ROOT_EXPRESSION", "SAY", "SOLO_FUNCTION_CALL", "SOLO_FUNCTION_CALL_PARENT", "SOLO_FUNCTION_CALL_THIS", "STATEMENT_LIST", "STRING", "TEXT", "TIMES", "UNARY_NOT", "UNTIL", "USE", "WHILE", "WS"
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
    public static final int REPEAT=76;
    public static final int RETURN=77;
    public static final int RETURNS=78;
    public static final int RIGHT_PAREN=79;
    public static final int RIGHT_SQR_BRACE=80;
    public static final int ROOT_EXPRESSION=81;
    public static final int SAY=82;
    public static final int SOLO_FUNCTION_CALL=83;
    public static final int SOLO_FUNCTION_CALL_PARENT=84;
    public static final int SOLO_FUNCTION_CALL_THIS=85;
    public static final int STATEMENT_LIST=86;
    public static final int STRING=87;
    public static final int TEXT=88;
    public static final int TIMES=89;
    public static final int UNARY_NOT=90;
    public static final int UNTIL=91;
    public static final int USE=92;
    public static final int WHILE=93;
    public static final int WS=94;

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
            else if ( ((LA8_0 >= ACTION && LA8_0 <= ALERT)||LA8_0==BLUEPRINT||LA8_0==BOOLEAN_KEYWORD||LA8_0==CHECK||LA8_0==CONSTANT||(LA8_0 >= ID && LA8_0 <= IF)||LA8_0==INTEGER_KEYWORD||LA8_0==ME||LA8_0==NATIVE||LA8_0==NUMBER_KEYWORD||LA8_0==ON_CREATE||LA8_0==PARENT||(LA8_0 >= PRINT && LA8_0 <= PUBLIC)||(LA8_0 >= REPEAT && LA8_0 <= RETURN)||LA8_0==SAY||LA8_0==TEXT) ) {
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

                        if ( (LA7_0==ACTION||LA7_0==BLUEPRINT||LA7_0==BOOLEAN_KEYWORD||LA7_0==CONSTANT||LA7_0==ID||LA7_0==INTEGER_KEYWORD||LA7_0==ME||LA7_0==NATIVE||LA7_0==NUMBER_KEYWORD||LA7_0==ON_CREATE||LA7_0==PARENT||(LA7_0 >= PRIVATE && LA7_0 <= PUBLIC)||LA7_0==TEXT) ) {
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
                    // elements: ID, inherit_stmnts, generic_declaration, END, class_stmnts, CLASS
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
            case CONSTANT:
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

                if ( (LA12_2==BOOLEAN_KEYWORD||LA12_2==CONSTANT||LA12_2==ID||LA12_2==INTEGER_KEYWORD||LA12_2==NUMBER_KEYWORD||LA12_2==TEXT) ) {
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

                if ( (LA12_3==BOOLEAN_KEYWORD||LA12_3==CONSTANT||LA12_3==ID||LA12_3==INTEGER_KEYWORD||LA12_3==NUMBER_KEYWORD||LA12_3==TEXT) ) {
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

                        if ( (LA9_0==ALERT||LA9_0==BOOLEAN_KEYWORD||LA9_0==CHECK||LA9_0==CONSTANT||(LA9_0 >= ID && LA9_0 <= IF)||LA9_0==INTEGER_KEYWORD||LA9_0==ME||LA9_0==NUMBER_KEYWORD||LA9_0==PARENT||(LA9_0 >= PRINT && LA9_0 <= PUBLIC)||(LA9_0 >= REPEAT && LA9_0 <= RETURN)||LA9_0==SAY||LA9_0==TEXT) ) {
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
            // elements: generic_statement, qualified_name, INHERITS
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
            case CONSTANT:
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

                if ( (LA18_2==BOOLEAN_KEYWORD||LA18_2==CONSTANT||LA18_2==ID||LA18_2==INTEGER_KEYWORD||LA18_2==NUMBER_KEYWORD||LA18_2==TEXT) ) {
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

                if ( (LA18_3==BOOLEAN_KEYWORD||LA18_3==CONSTANT||LA18_3==ID||LA18_3==INTEGER_KEYWORD||LA18_3==NUMBER_KEYWORD||LA18_3==TEXT) ) {
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
                    // elements: ID, END, ACTION, assignment_declaration, formal_parameter, RETURNS, block
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
                        if ( stream_assignment_declaration.hasNext()||stream_RETURNS.hasNext() ) {
                            adaptor.addChild(root_1, 
                            stream_RETURNS.nextNode()
                            );

                            adaptor.addChild(root_1, stream_assignment_declaration.nextTree());

                        }
                        stream_assignment_declaration.reset();
                        stream_RETURNS.reset();

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
                    // elements: RETURNS, BLUEPRINT, formal_parameter, ACTION, assignment_declaration, ID
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
                        if ( stream_RETURNS.hasNext()||stream_assignment_declaration.hasNext() ) {
                            adaptor.addChild(root_1, 
                            stream_RETURNS.nextNode()
                            );

                            adaptor.addChild(root_1, stream_assignment_declaration.nextTree());

                        }
                        stream_RETURNS.reset();
                        stream_assignment_declaration.reset();

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
                    // elements: RETURNS, NATIVE, assignment_declaration, formal_parameter, ID, ACTION
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
                        if ( stream_RETURNS.hasNext()||stream_assignment_declaration.hasNext() ) {
                            adaptor.addChild(root_1, 
                            stream_RETURNS.nextNode()
                            );

                            adaptor.addChild(root_1, stream_assignment_declaration.nextTree());

                        }
                        stream_RETURNS.reset();
                        stream_assignment_declaration.reset();

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

                if ( (LA33_0==ALERT||LA33_0==BOOLEAN_KEYWORD||LA33_0==CHECK||LA33_0==CONSTANT||(LA33_0 >= ID && LA33_0 <= IF)||LA33_0==INTEGER_KEYWORD||LA33_0==ME||LA33_0==NUMBER_KEYWORD||LA33_0==PARENT||(LA33_0 >= PRINT && LA33_0 <= PUBLIC)||(LA33_0 >= REPEAT && LA33_0 <= RETURN)||LA33_0==SAY||LA33_0==TEXT) ) {
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
                    // elements: COLON, LEFT_PAREN, ID, RIGHT_PAREN, expression, COMMA, qualified_name, expression
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
                        if ( stream_expression.hasNext()||stream_COMMA.hasNext()||stream_expression.hasNext() ) {
                            adaptor.addChild(root_1, stream_expression.nextTree());

                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:716:75: ( COMMA expression )*
                            while ( stream_COMMA.hasNext()||stream_expression.hasNext() ) {
                                adaptor.addChild(root_1, 
                                stream_COMMA.nextNode()
                                );

                                adaptor.addChild(root_1, stream_expression.nextTree());

                            }
                            stream_COMMA.reset();
                            stream_expression.reset();

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
                    // elements: PARENT, qualified_name, RIGHT_PAREN, LEFT_PAREN, COMMA, expression, expression, COLON, COLON, ID
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
                        if ( stream_COMMA.hasNext()||stream_expression.hasNext()||stream_expression.hasNext() ) {
                            adaptor.addChild(root_1, stream_expression.nextTree());

                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:718:92: ( COMMA expression )*
                            while ( stream_COMMA.hasNext()||stream_expression.hasNext() ) {
                                adaptor.addChild(root_1, 
                                stream_COMMA.nextNode()
                                );

                                adaptor.addChild(root_1, stream_expression.nextTree());

                            }
                            stream_COMMA.reset();
                            stream_expression.reset();

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
                    // elements: ID, COLON, COMMA, RIGHT_PAREN, ME, LEFT_PAREN, expression, expression, qualified_name, COLON
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

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:720:77: ( expression ( COMMA expression )* )?
                        if ( stream_COMMA.hasNext()||stream_expression.hasNext()||stream_expression.hasNext() ) {
                            adaptor.addChild(root_1, stream_expression.nextTree());

                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:720:89: ( COMMA expression )*
                            while ( stream_COMMA.hasNext()||stream_expression.hasNext() ) {
                                adaptor.addChild(root_1, 
                                stream_COMMA.nextNode()
                                );

                                adaptor.addChild(root_1, stream_expression.nextTree());

                            }
                            stream_COMMA.reset();
                            stream_expression.reset();

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
            // elements: root_expression, ALERT, RIGHT_PAREN, LEFT_PAREN
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
            	new_desc.setInitialized(true);
                       		
            	CompilerError error = symbol.add(new_desc);
            	if(error != null) {
            		error.setLineNumber(ID107.getLine());
            		error.setColumn(ID107.getCharPositionInLine());
            		vm.getCompilerErrors().addError(error);
            	}	
            	

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:818:2: ( OF_TYPE qualified_name ( OR qualified_name )* )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==OF_TYPE) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:818:3: OF_TYPE qualified_name ( OR qualified_name )*
                    {
                    OF_TYPE108=(Token)match(input,OF_TYPE,FOLLOW_OF_TYPE_in_detect_parameter1309);  
                    stream_OF_TYPE.add(OF_TYPE108);


                    pushFollow(FOLLOW_qualified_name_in_detect_parameter1311);
                    qualified_name109=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name109.getTree());

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:818:25: ( OR qualified_name )*
                    loop47:
                    do {
                        int alt47=2;
                        int LA47_0 = input.LA(1);

                        if ( (LA47_0==OR) ) {
                            alt47=1;
                        }


                        switch (alt47) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:818:26: OR qualified_name
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
            // elements: qualified_name, ID, OR, OF_TYPE, qualified_name
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 819:2: -> ^( ID ( OF_TYPE qualified_name ( OR qualified_name )* )? )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:819:5: ^( ID ( OF_TYPE qualified_name ( OR qualified_name )* )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                stream_ID.nextNode()
                , root_1);

                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:819:10: ( OF_TYPE qualified_name ( OR qualified_name )* )?
                if ( stream_qualified_name.hasNext()||stream_OR.hasNext()||stream_OF_TYPE.hasNext()||stream_qualified_name.hasNext() ) {
                    adaptor.addChild(root_1, 
                    stream_OF_TYPE.nextNode()
                    );

                    adaptor.addChild(root_1, stream_qualified_name.nextTree());

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:819:33: ( OR qualified_name )*
                    while ( stream_OR.hasNext()||stream_qualified_name.hasNext() ) {
                        adaptor.addChild(root_1, 
                        stream_OR.nextNode()
                        );

                        adaptor.addChild(root_1, stream_qualified_name.nextTree());

                    }
                    stream_OR.reset();
                    stream_qualified_name.reset();

                }
                stream_qualified_name.reset();
                stream_OR.reset();
                stream_OF_TYPE.reset();
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:821:1: print_statement : PRINT root_expression ;
    public final QuorumParser.print_statement_return print_statement() throws RecognitionException {
        QuorumParser.print_statement_return retval = new QuorumParser.print_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token PRINT112=null;
        QuorumParser.root_expression_return root_expression113 =null;


        CommonTree PRINT112_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:822:2: ( PRINT root_expression )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:822:4: PRINT root_expression
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:825:1: speak_statement : SAY root_expression ;
    public final QuorumParser.speak_statement_return speak_statement() throws RecognitionException {
        QuorumParser.speak_statement_return retval = new QuorumParser.speak_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token SAY114=null;
        QuorumParser.root_expression_return root_expression115 =null;


        CommonTree SAY114_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:826:2: ( SAY root_expression )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:826:4: SAY root_expression
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:829:1: return_statement : RETURN ( root_expression | NOW ) ;
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
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:830:2: ( RETURN ( root_expression | NOW ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:830:4: RETURN ( root_expression | NOW )
            {
            root_0 = (CommonTree)adaptor.nil();


            RETURN116=(Token)match(input,RETURN,FOLLOW_RETURN_in_return_statement1377); 
            RETURN116_tree = 
            (CommonTree)adaptor.create(RETURN116)
            ;
            adaptor.addChild(root_0, RETURN116_tree);


            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:830:11: ( root_expression | NOW )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:830:13: root_expression
                    {
                    pushFollow(FOLLOW_root_expression_in_return_statement1381);
                    root_expression117=root_expression();

                    state._fsp--;

                    adaptor.addChild(root_0, root_expression117.getTree());

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:830:31: NOW
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:833:1: generic_declaration returns [ArrayList genericTypeList] : LESS ids+= ID ( COMMA ids+= ID )* GREATER -> ^( GENERIC LESS ID ( COMMA ID )* GREATER ) ;
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
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:834:2: ( LESS ids+= ID ( COMMA ids+= ID )* GREATER -> ^( GENERIC LESS ID ( COMMA ID )* GREATER ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:834:4: LESS ids+= ID ( COMMA ids+= ID )* GREATER
            {
            LESS119=(Token)match(input,LESS,FOLLOW_LESS_in_generic_declaration1402);  
            stream_LESS.add(LESS119);


            ids=(Token)match(input,ID,FOLLOW_ID_in_generic_declaration1406);  
            stream_ID.add(ids);

            if (list_ids==null) list_ids=new ArrayList();
            list_ids.add(ids);


            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:834:17: ( COMMA ids+= ID )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==COMMA) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:834:18: COMMA ids+= ID
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
            // elements: ID, GREATER, LESS, COMMA, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 857:2: -> ^( GENERIC LESS ID ( COMMA ID )* GREATER )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:857:5: ^( GENERIC LESS ID ( COMMA ID )* GREATER )
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

                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:857:23: ( COMMA ID )*
                while ( stream_ID.hasNext()||stream_COMMA.hasNext() ) {
                    adaptor.addChild(root_1, 
                    stream_COMMA.nextNode()
                    );

                    adaptor.addChild(root_1, 
                    stream_ID.nextNode()
                    );

                }
                stream_ID.reset();
                stream_COMMA.reset();

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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:859:1: generic_statement returns [ArrayList genericTypeList] : LESS type= assignment_declaration ( COMMA type= assignment_declaration )* GREATER -> ^( GENERIC LESS assignment_declaration ( COMMA assignment_declaration )* GREATER ) ;
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
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:863:2: ( LESS type= assignment_declaration ( COMMA type= assignment_declaration )* GREATER -> ^( GENERIC LESS assignment_declaration ( COMMA assignment_declaration )* GREATER ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:863:4: LESS type= assignment_declaration ( COMMA type= assignment_declaration )* GREATER
            {
            LESS122=(Token)match(input,LESS,FOLLOW_LESS_in_generic_statement1458);  
            stream_LESS.add(LESS122);



            		((generic_statement_scope)generic_statement_stack.peek()).typeList = new ArrayList<TypeDescriptor>();
            	

            pushFollow(FOLLOW_assignment_declaration_in_generic_statement1468);
            type=assignment_declaration();

            state._fsp--;

            stream_assignment_declaration.add(type.getTree());


            		((generic_statement_scope)generic_statement_stack.peek()).typeList.add((type!=null?type.type:null));
            	

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:871:2: ( COMMA type= assignment_declaration )*
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==COMMA) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:871:3: COMMA type= assignment_declaration
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
            // elements: LESS, COMMA, assignment_declaration, assignment_declaration, GREATER
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 901:2: -> ^( GENERIC LESS assignment_declaration ( COMMA assignment_declaration )* GREATER )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:901:5: ^( GENERIC LESS assignment_declaration ( COMMA assignment_declaration )* GREATER )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(GENERIC, "GENERIC")
                , root_1);

                adaptor.addChild(root_1, 
                stream_LESS.nextNode()
                );

                adaptor.addChild(root_1, stream_assignment_declaration.nextTree());

                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:901:42: ( COMMA assignment_declaration )*
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:903:1: class_type returns [TypeDescriptor type] : qn= qualified_name ;
    public final QuorumParser.class_type_return class_type() throws RecognitionException {
        QuorumParser.class_type_return retval = new QuorumParser.class_type_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        QuorumParser.qualified_name_return qn =null;



        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:904:2: (qn= qualified_name )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:904:4: qn= qualified_name
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:921:1: assignment_declaration returns [TypeDescriptor type] : (qn= qualified_name (gs= generic_statement )? | INTEGER_KEYWORD | NUMBER_KEYWORD | TEXT | BOOLEAN_KEYWORD );
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
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:922:2: (qn= qualified_name (gs= generic_statement )? | INTEGER_KEYWORD | NUMBER_KEYWORD | TEXT | BOOLEAN_KEYWORD )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:922:4: qn= qualified_name (gs= generic_statement )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_qualified_name_in_assignment_declaration1550);
                    qn=qualified_name();

                    state._fsp--;

                    adaptor.addChild(root_0, qn.getTree());

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:922:26: (gs= generic_statement )?
                    int alt52=2;
                    int LA52_0 = input.LA(1);

                    if ( (LA52_0==LESS) ) {
                        alt52=1;
                    }
                    switch (alt52) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:922:26: gs= generic_statement
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:945:4: INTEGER_KEYWORD
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:953:4: NUMBER_KEYWORD
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:961:4: TEXT
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:969:4: BOOLEAN_KEYWORD
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:977:1: assignment_statement : ( (sel= selector COLON )? ID rhs= assign_right_hand_side |obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side | (modifier= access_modifier )? ( CONSTANT )? type= assignment_declaration name= ID (rhs= assign_right_hand_side )? );
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
        Token CONSTANT136=null;
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
        CommonTree CONSTANT136_tree=null;


        	Documentation variableDocumentation = null;
        	if(isInClassAssignmentStatementScope) {
        		variableDocumentation = getDocumentationFromRecentToken();
        	}

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:984:2: ( (sel= selector COLON )? ID rhs= assign_right_hand_side |obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side | (modifier= access_modifier )? ( CONSTANT )? type= assignment_declaration name= ID (rhs= assign_right_hand_side )? )
            int alt59=3;
            alt59 = dfa59.predict(input);
            switch (alt59) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:985:3: (sel= selector COLON )? ID rhs= assign_right_hand_side
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:985:3: (sel= selector COLON )?
                    int alt54=2;
                    int LA54_0 = input.LA(1);

                    if ( (LA54_0==ME||LA54_0==PARENT) ) {
                        alt54=1;
                    }
                    switch (alt54) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:985:4: sel= selector COLON
                            {
                            pushFollow(FOLLOW_selector_in_assignment_statement1610);
                            sel=selector();

                            state._fsp--;

                            adaptor.addChild(root_0, sel.getTree());

                            COLON129=(Token)match(input,COLON,FOLLOW_COLON_in_assignment_statement1612); 
                            COLON129_tree = 
                            (CommonTree)adaptor.create(COLON129)
                            ;
                            adaptor.addChild(root_0, COLON129_tree);


                            }
                            break;

                    }


                    ID130=(Token)match(input,ID,FOLLOW_ID_in_assignment_statement1616); 
                    ID130_tree = 
                    (CommonTree)adaptor.create(ID130)
                    ;
                    adaptor.addChild(root_0, ID130_tree);


                    pushFollow(FOLLOW_assign_right_hand_side_in_assignment_statement1622);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1005:4: obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_qualified_name_in_assignment_statement1633);
                    obj=qualified_name();

                    state._fsp--;

                    adaptor.addChild(root_0, obj.getTree());

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1005:23: ( COLON PARENT COLON parent= qualified_name )?
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
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1005:24: COLON PARENT COLON parent= qualified_name
                            {
                            COLON131=(Token)match(input,COLON,FOLLOW_COLON_in_assignment_statement1636); 
                            COLON131_tree = 
                            (CommonTree)adaptor.create(COLON131)
                            ;
                            adaptor.addChild(root_0, COLON131_tree);


                            PARENT132=(Token)match(input,PARENT,FOLLOW_PARENT_in_assignment_statement1638); 
                            PARENT132_tree = 
                            (CommonTree)adaptor.create(PARENT132)
                            ;
                            adaptor.addChild(root_0, PARENT132_tree);


                            COLON133=(Token)match(input,COLON,FOLLOW_COLON_in_assignment_statement1640); 
                            COLON133_tree = 
                            (CommonTree)adaptor.create(COLON133)
                            ;
                            adaptor.addChild(root_0, COLON133_tree);


                            pushFollow(FOLLOW_qualified_name_in_assignment_statement1644);
                            parent=qualified_name();

                            state._fsp--;

                            adaptor.addChild(root_0, parent.getTree());

                            }
                            break;

                    }


                    COLON134=(Token)match(input,COLON,FOLLOW_COLON_in_assignment_statement1648); 
                    COLON134_tree = 
                    (CommonTree)adaptor.create(COLON134)
                    ;
                    adaptor.addChild(root_0, COLON134_tree);


                    ID135=(Token)match(input,ID,FOLLOW_ID_in_assignment_statement1650); 
                    ID135_tree = 
                    (CommonTree)adaptor.create(ID135)
                    ;
                    adaptor.addChild(root_0, ID135_tree);


                    pushFollow(FOLLOW_assign_right_hand_side_in_assignment_statement1654);
                    rhs=assign_right_hand_side();

                    state._fsp--;

                    adaptor.addChild(root_0, rhs.getTree());

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1006:4: (modifier= access_modifier )? ( CONSTANT )? type= assignment_declaration name= ID (rhs= assign_right_hand_side )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1006:13: (modifier= access_modifier )?
                    int alt56=2;
                    int LA56_0 = input.LA(1);

                    if ( ((LA56_0 >= PRIVATE && LA56_0 <= PUBLIC)) ) {
                        alt56=1;
                    }
                    switch (alt56) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1006:13: modifier= access_modifier
                            {
                            pushFollow(FOLLOW_access_modifier_in_assignment_statement1663);
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
                    		

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1014:2: ( CONSTANT )?
                    int alt57=2;
                    int LA57_0 = input.LA(1);

                    if ( (LA57_0==CONSTANT) ) {
                        alt57=1;
                    }
                    switch (alt57) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1014:2: CONSTANT
                            {
                            CONSTANT136=(Token)match(input,CONSTANT,FOLLOW_CONSTANT_in_assignment_statement1671); 
                            CONSTANT136_tree = 
                            (CommonTree)adaptor.create(CONSTANT136)
                            ;
                            adaptor.addChild(root_0, CONSTANT136_tree);


                            }
                            break;

                    }


                    pushFollow(FOLLOW_assignment_declaration_in_assignment_statement1678);
                    type=assignment_declaration();

                    state._fsp--;

                    adaptor.addChild(root_0, type.getTree());

                    name=(Token)match(input,ID,FOLLOW_ID_in_assignment_statement1684); 
                    name_tree = 
                    (CommonTree)adaptor.create(name)
                    ;
                    adaptor.addChild(root_0, name_tree);


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1014:56: (rhs= assign_right_hand_side )?
                    int alt58=2;
                    int LA58_0 = input.LA(1);

                    if ( (LA58_0==EQUALITY) ) {
                        alt58=1;
                    }
                    switch (alt58) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1014:56: rhs= assign_right_hand_side
                            {
                            pushFollow(FOLLOW_assign_right_hand_side_in_assignment_statement1690);
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
                    			if(CONSTANT136 != null){
                    				new_desc.setIsConstant(true);
                    			}else{
                    				new_desc.setIsConstant(false);
                    			}
                    			
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1052:1: assign_right_hand_side : ( EQUALITY root_expression ) ;
    public final QuorumParser.assign_right_hand_side_return assign_right_hand_side() throws RecognitionException {
        QuorumParser.assign_right_hand_side_return retval = new QuorumParser.assign_right_hand_side_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token EQUALITY137=null;
        QuorumParser.root_expression_return root_expression138 =null;


        CommonTree EQUALITY137_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1054:2: ( ( EQUALITY root_expression ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1055:3: ( EQUALITY root_expression )
            {
            root_0 = (CommonTree)adaptor.nil();


            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1055:3: ( EQUALITY root_expression )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1055:4: EQUALITY root_expression
            {
            EQUALITY137=(Token)match(input,EQUALITY,FOLLOW_EQUALITY_in_assign_right_hand_side1716); 
            EQUALITY137_tree = 
            (CommonTree)adaptor.create(EQUALITY137)
            ;
            adaptor.addChild(root_0, EQUALITY137_tree);


            pushFollow(FOLLOW_root_expression_in_assign_right_hand_side1718);
            root_expression138=root_expression();

            state._fsp--;

            adaptor.addChild(root_0, root_expression138.getTree());

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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1059:1: if_statement : firstif= IF root_expression block (firstelse= ELSE_IF root_expression block )* (secondelse= ELSE block )? end= END ;
    public final QuorumParser.if_statement_return if_statement() throws RecognitionException {
        QuorumParser.if_statement_return retval = new QuorumParser.if_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token firstif=null;
        Token firstelse=null;
        Token secondelse=null;
        Token end=null;
        QuorumParser.root_expression_return root_expression139 =null;

        QuorumParser.block_return block140 =null;

        QuorumParser.root_expression_return root_expression141 =null;

        QuorumParser.block_return block142 =null;

        QuorumParser.block_return block143 =null;


        CommonTree firstif_tree=null;
        CommonTree firstelse_tree=null;
        CommonTree secondelse_tree=null;
        CommonTree end_tree=null;


        	BlockDescriptor block;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1063:2: (firstif= IF root_expression block (firstelse= ELSE_IF root_expression block )* (secondelse= ELSE block )? end= END )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1064:2: firstif= IF root_expression block (firstelse= ELSE_IF root_expression block )* (secondelse= ELSE block )? end= END
            {
            root_0 = (CommonTree)adaptor.nil();


            firstif=(Token)match(input,IF,FOLLOW_IF_in_if_statement1743); 
            firstif_tree = 
            (CommonTree)adaptor.create(firstif)
            ;
            adaptor.addChild(root_0, firstif_tree);


            pushFollow(FOLLOW_root_expression_in_if_statement1745);
            root_expression139=root_expression();

            state._fsp--;

            adaptor.addChild(root_0, root_expression139.getTree());

             block = new BlockDescriptor(); symbol.add(block); 

            pushFollow(FOLLOW_block_in_if_statement1751);
            block140=block();

            state._fsp--;

            adaptor.addChild(root_0, block140.getTree());

             
            		symbol.popScope(); 
                   		//set the begin and end line column information in the block descriptors.
                   		block.setLineBegin(firstif.getLine());
                   		block.setColumnBegin(firstif.getCharPositionInLine());
            	

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1072:2: (firstelse= ELSE_IF root_expression block )*
            loop60:
            do {
                int alt60=2;
                int LA60_0 = input.LA(1);

                if ( (LA60_0==ELSE_IF) ) {
                    alt60=1;
                }


                switch (alt60) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1072:3: firstelse= ELSE_IF root_expression block
            	    {
            	    firstelse=(Token)match(input,ELSE_IF,FOLLOW_ELSE_IF_in_if_statement1763); 
            	    firstelse_tree = 
            	    (CommonTree)adaptor.create(firstelse)
            	    ;
            	    adaptor.addChild(root_0, firstelse_tree);



            	    		block.setLineEnd(firstelse.getLine());
            	    		block.setColumnEnd((firstelse!=null?firstelse.getText():null).length() + firstelse.getCharPositionInLine());
            	    	

            	    pushFollow(FOLLOW_root_expression_in_if_statement1771);
            	    root_expression141=root_expression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, root_expression141.getTree());

            	     block = new BlockDescriptor(); symbol.add(block); 

            	    pushFollow(FOLLOW_block_in_if_statement1777);
            	    block142=block();

            	    state._fsp--;

            	    adaptor.addChild(root_0, block142.getTree());

            	     
            	    		symbol.popScope(); 
            	           		//set the begin and end line column information in the block descriptors.
            	           		block.setLineBegin(firstelse.getLine());
            	           		block.setColumnBegin(firstelse.getCharPositionInLine());
            	    	

            	    }
            	    break;

            	default :
            	    break loop60;
                }
            } while (true);


            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1086:2: (secondelse= ELSE block )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==ELSE) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1086:3: secondelse= ELSE block
                    {
                    secondelse=(Token)match(input,ELSE,FOLLOW_ELSE_in_if_statement1795); 
                    secondelse_tree = 
                    (CommonTree)adaptor.create(secondelse)
                    ;
                    adaptor.addChild(root_0, secondelse_tree);


                     
                    		block.setLineEnd(secondelse.getLine());
                    		block.setColumnEnd((secondelse!=null?secondelse.getText():null).length() + secondelse.getCharPositionInLine());
                    		block = new BlockDescriptor(); 
                    		symbol.add(block); 
                    	

                    pushFollow(FOLLOW_block_in_if_statement1803);
                    block143=block();

                    state._fsp--;

                    adaptor.addChild(root_0, block143.getTree());

                     
                    		symbol.popScope(); 
                           		//set the begin and end line column information in the block descriptors.
                           		block.setLineBegin(secondelse.getLine());
                           		block.setColumnBegin(secondelse.getCharPositionInLine());
                    	

                    }
                    break;

            }


            end=(Token)match(input,END,FOLLOW_END_in_if_statement1819); 
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1108:1: loop_statement : REPEAT ( ( root_expression TIMES ) | ( ( WHILE | UNTIL ) root_expression ) ) block END ;
    public final QuorumParser.loop_statement_return loop_statement() throws RecognitionException {
        QuorumParser.loop_statement_return retval = new QuorumParser.loop_statement_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token REPEAT144=null;
        Token TIMES146=null;
        Token set147=null;
        Token END150=null;
        QuorumParser.root_expression_return root_expression145 =null;

        QuorumParser.root_expression_return root_expression148 =null;

        QuorumParser.block_return block149 =null;


        CommonTree REPEAT144_tree=null;
        CommonTree TIMES146_tree=null;
        CommonTree set147_tree=null;
        CommonTree END150_tree=null;


        	BlockDescriptor block;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1112:2: ( REPEAT ( ( root_expression TIMES ) | ( ( WHILE | UNTIL ) root_expression ) ) block END )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1113:2: REPEAT ( ( root_expression TIMES ) | ( ( WHILE | UNTIL ) root_expression ) ) block END
            {
            root_0 = (CommonTree)adaptor.nil();



            		block = new BlockDescriptor();
            		symbol.add(block);
            	

            REPEAT144=(Token)match(input,REPEAT,FOLLOW_REPEAT_in_loop_statement1844); 
            REPEAT144_tree = 
            (CommonTree)adaptor.create(REPEAT144)
            ;
            adaptor.addChild(root_0, REPEAT144_tree);


            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1117:10: ( ( root_expression TIMES ) | ( ( WHILE | UNTIL ) root_expression ) )
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==BOOLEAN||LA62_0==CAST||LA62_0==DECIMAL||LA62_0==ID||(LA62_0 >= INPUT && LA62_0 <= INT)||LA62_0==LEFT_PAREN||(LA62_0 >= ME && LA62_0 <= MINUS)||LA62_0==NOT||LA62_0==NULL||LA62_0==PARENT||LA62_0==STRING) ) {
                alt62=1;
            }
            else if ( (LA62_0==UNTIL||LA62_0==WHILE) ) {
                alt62=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;

            }
            switch (alt62) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1133:4: ( root_expression TIMES )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1133:4: ( root_expression TIMES )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1133:5: root_expression TIMES
                    {
                    pushFollow(FOLLOW_root_expression_in_loop_statement1912);
                    root_expression145=root_expression();

                    state._fsp--;

                    adaptor.addChild(root_0, root_expression145.getTree());

                    TIMES146=(Token)match(input,TIMES,FOLLOW_TIMES_in_loop_statement1914); 
                    TIMES146_tree = 
                    (CommonTree)adaptor.create(TIMES146)
                    ;
                    adaptor.addChild(root_0, TIMES146_tree);


                    }


                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1134:5: ( ( WHILE | UNTIL ) root_expression )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1134:5: ( ( WHILE | UNTIL ) root_expression )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1134:6: ( WHILE | UNTIL ) root_expression
                    {
                    set147=(Token)input.LT(1);

                    if ( input.LA(1)==UNTIL||input.LA(1)==WHILE ) {
                        input.consume();
                        adaptor.addChild(root_0, 
                        (CommonTree)adaptor.create(set147)
                        );
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_root_expression_in_loop_statement1930);
                    root_expression148=root_expression();

                    state._fsp--;

                    adaptor.addChild(root_0, root_expression148.getTree());

                    }


                    }
                    break;

            }


            pushFollow(FOLLOW_block_in_loop_statement1939);
            block149=block();

            state._fsp--;

            adaptor.addChild(root_0, block149.getTree());

            END150=(Token)match(input,END,FOLLOW_END_in_loop_statement1941); 
            END150_tree = 
            (CommonTree)adaptor.create(END150)
            ;
            adaptor.addChild(root_0, END150_tree);



                   			//set the begin and end line column information in the block descriptors.
                   			block.setLineBegin(REPEAT144.getLine());
                   			block.setLineEnd(END150.getLine());
                   			block.setColumnBegin(REPEAT144.getCharPositionInLine());
                   			block.setColumnEnd((END150!=null?END150.getText():null).length() + END150.getCharPositionInLine());
                   		


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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1150:1: selector returns [ScopeSelector scopeSel] : ( PARENT COLON qn= qualified_name -> ^( PARENT qualified_name ) | ME );
    public final QuorumParser.selector_return selector() throws RecognitionException {
        QuorumParser.selector_return retval = new QuorumParser.selector_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token PARENT151=null;
        Token COLON152=null;
        Token ME153=null;
        QuorumParser.qualified_name_return qn =null;


        CommonTree PARENT151_tree=null;
        CommonTree COLON152_tree=null;
        CommonTree ME153_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_PARENT=new RewriteRuleTokenStream(adaptor,"token PARENT");
        RewriteRuleSubtreeStream stream_qualified_name=new RewriteRuleSubtreeStream(adaptor,"rule qualified_name");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1151:2: ( PARENT COLON qn= qualified_name -> ^( PARENT qualified_name ) | ME )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==PARENT) ) {
                alt63=1;
            }
            else if ( (LA63_0==ME) ) {
                alt63=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;

            }
            switch (alt63) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1151:4: PARENT COLON qn= qualified_name
                    {
                    PARENT151=(Token)match(input,PARENT,FOLLOW_PARENT_in_selector1965);  
                    stream_PARENT.add(PARENT151);


                    COLON152=(Token)match(input,COLON,FOLLOW_COLON_in_selector1967);  
                    stream_COLON.add(COLON152);


                    pushFollow(FOLLOW_qualified_name_in_selector1971);
                    qn=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qn.getTree());


                    		ScopeSelector scopeItem = new ScopeSelector();
                    		scopeItem.setIsParent(true);
                    		retval.scopeSel = scopeItem;
                    	

                    // AST REWRITE
                    // elements: PARENT, qualified_name
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1156:4: -> ^( PARENT qualified_name )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1156:7: ^( PARENT qualified_name )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1158:4: ME
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    ME153=(Token)match(input,ME,FOLLOW_ME_in_selector1989); 
                    ME153_tree = 
                    (CommonTree)adaptor.create(ME153)
                    ;
                    adaptor.addChild(root_0, ME153_tree);



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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1166:1: root_expression : expression -> ^( ROOT_EXPRESSION expression ) ;
    public final QuorumParser.root_expression_return root_expression() throws RecognitionException {
        QuorumParser.root_expression_return retval = new QuorumParser.root_expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        QuorumParser.expression_return expression154 =null;


        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1167:2: ( expression -> ^( ROOT_EXPRESSION expression ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1167:4: expression
            {
            pushFollow(FOLLOW_expression_in_root_expression2003);
            expression154=expression();

            state._fsp--;

            stream_expression.add(expression154.getTree());

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
            // 1167:15: -> ^( ROOT_EXPRESSION expression )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1167:18: ^( ROOT_EXPRESSION expression )
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1170:1: expression : or ;
    public final QuorumParser.expression_return expression() throws RecognitionException {
        QuorumParser.expression_return retval = new QuorumParser.expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        QuorumParser.or_return or155 =null;



        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1171:2: ( or )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1171:4: or
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_or_in_expression2023);
            or155=or();

            state._fsp--;

            adaptor.addChild(root_0, or155.getTree());

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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1174:1: or : and ( OR ^ and )* ;
    public final QuorumParser.or_return or() throws RecognitionException {
        QuorumParser.or_return retval = new QuorumParser.or_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token OR157=null;
        QuorumParser.and_return and156 =null;

        QuorumParser.and_return and158 =null;


        CommonTree OR157_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1174:5: ( and ( OR ^ and )* )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1174:7: and ( OR ^ and )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_and_in_or2035);
            and156=and();

            state._fsp--;

            adaptor.addChild(root_0, and156.getTree());

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1174:11: ( OR ^ and )*
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( (LA64_0==OR) ) {
                    alt64=1;
                }


                switch (alt64) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1174:12: OR ^ and
            	    {
            	    OR157=(Token)match(input,OR,FOLLOW_OR_in_or2038); 
            	    OR157_tree = 
            	    (CommonTree)adaptor.create(OR157)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(OR157_tree, root_0);


            	    pushFollow(FOLLOW_and_in_or2042);
            	    and158=and();

            	    state._fsp--;

            	    adaptor.addChild(root_0, and158.getTree());

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
    // $ANTLR end "or"


    public static class and_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "and"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1177:1: and : equality ( AND ^ equality )* ;
    public final QuorumParser.and_return and() throws RecognitionException {
        QuorumParser.and_return retval = new QuorumParser.and_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token AND160=null;
        QuorumParser.equality_return equality159 =null;

        QuorumParser.equality_return equality161 =null;


        CommonTree AND160_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1177:6: ( equality ( AND ^ equality )* )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1177:8: equality ( AND ^ equality )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_equality_in_and2055);
            equality159=equality();

            state._fsp--;

            adaptor.addChild(root_0, equality159.getTree());

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1177:17: ( AND ^ equality )*
            loop65:
            do {
                int alt65=2;
                int LA65_0 = input.LA(1);

                if ( (LA65_0==AND) ) {
                    alt65=1;
                }


                switch (alt65) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1177:18: AND ^ equality
            	    {
            	    AND160=(Token)match(input,AND,FOLLOW_AND_in_and2058); 
            	    AND160_tree = 
            	    (CommonTree)adaptor.create(AND160)
            	    ;
            	    root_0 = (CommonTree)adaptor.becomeRoot(AND160_tree, root_0);


            	    pushFollow(FOLLOW_equality_in_and2062);
            	    equality161=equality();

            	    state._fsp--;

            	    adaptor.addChild(root_0, equality161.getTree());

            	    }
            	    break;

            	default :
            	    break loop65;
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1179:1: equality : isa_operation ( ( EQUALITY ^| NOTEQUALS ^) isa_operation )* ;
    public final QuorumParser.equality_return equality() throws RecognitionException {
        QuorumParser.equality_return retval = new QuorumParser.equality_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token EQUALITY163=null;
        Token NOTEQUALS164=null;
        QuorumParser.isa_operation_return isa_operation162 =null;

        QuorumParser.isa_operation_return isa_operation165 =null;


        CommonTree EQUALITY163_tree=null;
        CommonTree NOTEQUALS164_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1179:9: ( isa_operation ( ( EQUALITY ^| NOTEQUALS ^) isa_operation )* )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1179:11: isa_operation ( ( EQUALITY ^| NOTEQUALS ^) isa_operation )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_isa_operation_in_equality2073);
            isa_operation162=isa_operation();

            state._fsp--;

            adaptor.addChild(root_0, isa_operation162.getTree());

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1179:25: ( ( EQUALITY ^| NOTEQUALS ^) isa_operation )*
            loop67:
            do {
                int alt67=2;
                int LA67_0 = input.LA(1);

                if ( (LA67_0==EQUALITY||LA67_0==NOTEQUALS) ) {
                    alt67=1;
                }


                switch (alt67) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1179:26: ( EQUALITY ^| NOTEQUALS ^) isa_operation
            	    {
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1179:26: ( EQUALITY ^| NOTEQUALS ^)
            	    int alt66=2;
            	    int LA66_0 = input.LA(1);

            	    if ( (LA66_0==EQUALITY) ) {
            	        alt66=1;
            	    }
            	    else if ( (LA66_0==NOTEQUALS) ) {
            	        alt66=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 66, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt66) {
            	        case 1 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1179:27: EQUALITY ^
            	            {
            	            EQUALITY163=(Token)match(input,EQUALITY,FOLLOW_EQUALITY_in_equality2077); 
            	            EQUALITY163_tree = 
            	            (CommonTree)adaptor.create(EQUALITY163)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(EQUALITY163_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1179:40: NOTEQUALS ^
            	            {
            	            NOTEQUALS164=(Token)match(input,NOTEQUALS,FOLLOW_NOTEQUALS_in_equality2083); 
            	            NOTEQUALS164_tree = 
            	            (CommonTree)adaptor.create(NOTEQUALS164)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(NOTEQUALS164_tree, root_0);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_isa_operation_in_equality2088);
            	    isa_operation165=isa_operation();

            	    state._fsp--;

            	    adaptor.addChild(root_0, isa_operation165.getTree());

            	    }
            	    break;

            	default :
            	    break loop67;
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1181:1: isa_operation : comparison ( INHERITS ^ class_type )? ;
    public final QuorumParser.isa_operation_return isa_operation() throws RecognitionException {
        QuorumParser.isa_operation_return retval = new QuorumParser.isa_operation_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token INHERITS167=null;
        QuorumParser.comparison_return comparison166 =null;

        QuorumParser.class_type_return class_type168 =null;


        CommonTree INHERITS167_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1182:2: ( comparison ( INHERITS ^ class_type )? )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1182:4: comparison ( INHERITS ^ class_type )?
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_comparison_in_isa_operation2100);
            comparison166=comparison();

            state._fsp--;

            adaptor.addChild(root_0, comparison166.getTree());

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1182:15: ( INHERITS ^ class_type )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==INHERITS) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1182:16: INHERITS ^ class_type
                    {
                    INHERITS167=(Token)match(input,INHERITS,FOLLOW_INHERITS_in_isa_operation2103); 
                    INHERITS167_tree = 
                    (CommonTree)adaptor.create(INHERITS167)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(INHERITS167_tree, root_0);


                    pushFollow(FOLLOW_class_type_in_isa_operation2107);
                    class_type168=class_type();

                    state._fsp--;

                    adaptor.addChild(root_0, class_type168.getTree());

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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1184:1: comparison : add ( ( GREATER ^| GREATER_EQUAL ^| LESS ^| LESS_EQUAL ^) add )* ;
    public final QuorumParser.comparison_return comparison() throws RecognitionException {
        QuorumParser.comparison_return retval = new QuorumParser.comparison_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token GREATER170=null;
        Token GREATER_EQUAL171=null;
        Token LESS172=null;
        Token LESS_EQUAL173=null;
        QuorumParser.add_return add169 =null;

        QuorumParser.add_return add174 =null;


        CommonTree GREATER170_tree=null;
        CommonTree GREATER_EQUAL171_tree=null;
        CommonTree LESS172_tree=null;
        CommonTree LESS_EQUAL173_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1184:11: ( add ( ( GREATER ^| GREATER_EQUAL ^| LESS ^| LESS_EQUAL ^) add )* )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1184:13: add ( ( GREATER ^| GREATER_EQUAL ^| LESS ^| LESS_EQUAL ^) add )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_add_in_comparison2117);
            add169=add();

            state._fsp--;

            adaptor.addChild(root_0, add169.getTree());

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1184:17: ( ( GREATER ^| GREATER_EQUAL ^| LESS ^| LESS_EQUAL ^) add )*
            loop70:
            do {
                int alt70=2;
                int LA70_0 = input.LA(1);

                if ( ((LA70_0 >= GREATER && LA70_0 <= GREATER_EQUAL)||(LA70_0 >= LESS && LA70_0 <= LESS_EQUAL)) ) {
                    alt70=1;
                }


                switch (alt70) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1184:18: ( GREATER ^| GREATER_EQUAL ^| LESS ^| LESS_EQUAL ^) add
            	    {
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1184:18: ( GREATER ^| GREATER_EQUAL ^| LESS ^| LESS_EQUAL ^)
            	    int alt69=4;
            	    switch ( input.LA(1) ) {
            	    case GREATER:
            	        {
            	        alt69=1;
            	        }
            	        break;
            	    case GREATER_EQUAL:
            	        {
            	        alt69=2;
            	        }
            	        break;
            	    case LESS:
            	        {
            	        alt69=3;
            	        }
            	        break;
            	    case LESS_EQUAL:
            	        {
            	        alt69=4;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 69, 0, input);

            	        throw nvae;

            	    }

            	    switch (alt69) {
            	        case 1 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1184:19: GREATER ^
            	            {
            	            GREATER170=(Token)match(input,GREATER,FOLLOW_GREATER_in_comparison2121); 
            	            GREATER170_tree = 
            	            (CommonTree)adaptor.create(GREATER170)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(GREATER170_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1184:30: GREATER_EQUAL ^
            	            {
            	            GREATER_EQUAL171=(Token)match(input,GREATER_EQUAL,FOLLOW_GREATER_EQUAL_in_comparison2126); 
            	            GREATER_EQUAL171_tree = 
            	            (CommonTree)adaptor.create(GREATER_EQUAL171)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(GREATER_EQUAL171_tree, root_0);


            	            }
            	            break;
            	        case 3 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1184:47: LESS ^
            	            {
            	            LESS172=(Token)match(input,LESS,FOLLOW_LESS_in_comparison2131); 
            	            LESS172_tree = 
            	            (CommonTree)adaptor.create(LESS172)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(LESS172_tree, root_0);


            	            }
            	            break;
            	        case 4 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1184:55: LESS_EQUAL ^
            	            {
            	            LESS_EQUAL173=(Token)match(input,LESS_EQUAL,FOLLOW_LESS_EQUAL_in_comparison2136); 
            	            LESS_EQUAL173_tree = 
            	            (CommonTree)adaptor.create(LESS_EQUAL173)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(LESS_EQUAL173_tree, root_0);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_add_in_comparison2140);
            	    add174=add();

            	    state._fsp--;

            	    adaptor.addChild(root_0, add174.getTree());

            	    }
            	    break;

            	default :
            	    break loop70;
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1188:1: add : multiply ( ( PLUS ^| MINUS ^) multiply )* ;
    public final QuorumParser.add_return add() throws RecognitionException {
        QuorumParser.add_return retval = new QuorumParser.add_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token PLUS176=null;
        Token MINUS177=null;
        QuorumParser.multiply_return multiply175 =null;

        QuorumParser.multiply_return multiply178 =null;


        CommonTree PLUS176_tree=null;
        CommonTree MINUS177_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1188:5: ( multiply ( ( PLUS ^| MINUS ^) multiply )* )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1188:7: multiply ( ( PLUS ^| MINUS ^) multiply )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_multiply_in_add2154);
            multiply175=multiply();

            state._fsp--;

            adaptor.addChild(root_0, multiply175.getTree());

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1188:16: ( ( PLUS ^| MINUS ^) multiply )*
            loop72:
            do {
                int alt72=2;
                int LA72_0 = input.LA(1);

                if ( (LA72_0==MINUS||LA72_0==PLUS) ) {
                    alt72=1;
                }


                switch (alt72) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1188:17: ( PLUS ^| MINUS ^) multiply
            	    {
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1188:17: ( PLUS ^| MINUS ^)
            	    int alt71=2;
            	    int LA71_0 = input.LA(1);

            	    if ( (LA71_0==PLUS) ) {
            	        alt71=1;
            	    }
            	    else if ( (LA71_0==MINUS) ) {
            	        alt71=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 71, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt71) {
            	        case 1 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1188:18: PLUS ^
            	            {
            	            PLUS176=(Token)match(input,PLUS,FOLLOW_PLUS_in_add2158); 
            	            PLUS176_tree = 
            	            (CommonTree)adaptor.create(PLUS176)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(PLUS176_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1188:26: MINUS ^
            	            {
            	            MINUS177=(Token)match(input,MINUS,FOLLOW_MINUS_in_add2163); 
            	            MINUS177_tree = 
            	            (CommonTree)adaptor.create(MINUS177)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(MINUS177_tree, root_0);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_multiply_in_add2167);
            	    multiply178=multiply();

            	    state._fsp--;

            	    adaptor.addChild(root_0, multiply178.getTree());

            	    }
            	    break;

            	default :
            	    break loop72;
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1191:1: multiply : combo_expression ( ( MULTIPLY ^| DIVIDE ^| MODULO ^) combo_expression )* ;
    public final QuorumParser.multiply_return multiply() throws RecognitionException {
        QuorumParser.multiply_return retval = new QuorumParser.multiply_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token MULTIPLY180=null;
        Token DIVIDE181=null;
        Token MODULO182=null;
        QuorumParser.combo_expression_return combo_expression179 =null;

        QuorumParser.combo_expression_return combo_expression183 =null;


        CommonTree MULTIPLY180_tree=null;
        CommonTree DIVIDE181_tree=null;
        CommonTree MODULO182_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1191:9: ( combo_expression ( ( MULTIPLY ^| DIVIDE ^| MODULO ^) combo_expression )* )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1191:11: combo_expression ( ( MULTIPLY ^| DIVIDE ^| MODULO ^) combo_expression )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_combo_expression_in_multiply2180);
            combo_expression179=combo_expression();

            state._fsp--;

            adaptor.addChild(root_0, combo_expression179.getTree());

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1191:28: ( ( MULTIPLY ^| DIVIDE ^| MODULO ^) combo_expression )*
            loop74:
            do {
                int alt74=2;
                int LA74_0 = input.LA(1);

                if ( (LA74_0==DIVIDE||(LA74_0 >= MODULO && LA74_0 <= MULTIPLY)) ) {
                    alt74=1;
                }


                switch (alt74) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1191:29: ( MULTIPLY ^| DIVIDE ^| MODULO ^) combo_expression
            	    {
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1191:29: ( MULTIPLY ^| DIVIDE ^| MODULO ^)
            	    int alt73=3;
            	    switch ( input.LA(1) ) {
            	    case MULTIPLY:
            	        {
            	        alt73=1;
            	        }
            	        break;
            	    case DIVIDE:
            	        {
            	        alt73=2;
            	        }
            	        break;
            	    case MODULO:
            	        {
            	        alt73=3;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 73, 0, input);

            	        throw nvae;

            	    }

            	    switch (alt73) {
            	        case 1 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1191:30: MULTIPLY ^
            	            {
            	            MULTIPLY180=(Token)match(input,MULTIPLY,FOLLOW_MULTIPLY_in_multiply2184); 
            	            MULTIPLY180_tree = 
            	            (CommonTree)adaptor.create(MULTIPLY180)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(MULTIPLY180_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1191:42: DIVIDE ^
            	            {
            	            DIVIDE181=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_multiply2189); 
            	            DIVIDE181_tree = 
            	            (CommonTree)adaptor.create(DIVIDE181)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(DIVIDE181_tree, root_0);


            	            }
            	            break;
            	        case 3 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1191:51: MODULO ^
            	            {
            	            MODULO182=(Token)match(input,MODULO,FOLLOW_MODULO_in_multiply2193); 
            	            MODULO182_tree = 
            	            (CommonTree)adaptor.create(MODULO182)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(MODULO182_tree, root_0);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_combo_expression_in_multiply2197);
            	    combo_expression183=combo_expression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, combo_expression183.getTree());

            	    }
            	    break;

            	default :
            	    break loop74;
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1194:1: combo_expression : ( NOT atom -> ^( UNARY_NOT NOT atom ) | CAST LEFT_PAREN assignment_declaration COMMA expression RIGHT_PAREN | atom );
    public final QuorumParser.combo_expression_return combo_expression() throws RecognitionException {
        QuorumParser.combo_expression_return retval = new QuorumParser.combo_expression_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token NOT184=null;
        Token CAST186=null;
        Token LEFT_PAREN187=null;
        Token COMMA189=null;
        Token RIGHT_PAREN191=null;
        QuorumParser.atom_return atom185 =null;

        QuorumParser.assignment_declaration_return assignment_declaration188 =null;

        QuorumParser.expression_return expression190 =null;

        QuorumParser.atom_return atom192 =null;


        CommonTree NOT184_tree=null;
        CommonTree CAST186_tree=null;
        CommonTree LEFT_PAREN187_tree=null;
        CommonTree COMMA189_tree=null;
        CommonTree RIGHT_PAREN191_tree=null;
        RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
        RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1195:2: ( NOT atom -> ^( UNARY_NOT NOT atom ) | CAST LEFT_PAREN assignment_declaration COMMA expression RIGHT_PAREN | atom )
            int alt75=3;
            switch ( input.LA(1) ) {
            case NOT:
                {
                alt75=1;
                }
                break;
            case CAST:
                {
                alt75=2;
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
                alt75=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 75, 0, input);

                throw nvae;

            }

            switch (alt75) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1195:4: NOT atom
                    {
                    NOT184=(Token)match(input,NOT,FOLLOW_NOT_in_combo_expression2212);  
                    stream_NOT.add(NOT184);


                    pushFollow(FOLLOW_atom_in_combo_expression2214);
                    atom185=atom();

                    state._fsp--;

                    stream_atom.add(atom185.getTree());

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
                    // 1195:13: -> ^( UNARY_NOT NOT atom )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1195:16: ^( UNARY_NOT NOT atom )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1196:4: CAST LEFT_PAREN assignment_declaration COMMA expression RIGHT_PAREN
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    CAST186=(Token)match(input,CAST,FOLLOW_CAST_in_combo_expression2229); 
                    CAST186_tree = 
                    (CommonTree)adaptor.create(CAST186)
                    ;
                    adaptor.addChild(root_0, CAST186_tree);


                    LEFT_PAREN187=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_combo_expression2231); 
                    LEFT_PAREN187_tree = 
                    (CommonTree)adaptor.create(LEFT_PAREN187)
                    ;
                    adaptor.addChild(root_0, LEFT_PAREN187_tree);


                    pushFollow(FOLLOW_assignment_declaration_in_combo_expression2233);
                    assignment_declaration188=assignment_declaration();

                    state._fsp--;

                    adaptor.addChild(root_0, assignment_declaration188.getTree());

                    COMMA189=(Token)match(input,COMMA,FOLLOW_COMMA_in_combo_expression2235); 
                    COMMA189_tree = 
                    (CommonTree)adaptor.create(COMMA189)
                    ;
                    adaptor.addChild(root_0, COMMA189_tree);


                    pushFollow(FOLLOW_expression_in_combo_expression2237);
                    expression190=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression190.getTree());

                    RIGHT_PAREN191=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_combo_expression2239); 
                    RIGHT_PAREN191_tree = 
                    (CommonTree)adaptor.create(RIGHT_PAREN191)
                    ;
                    adaptor.addChild(root_0, RIGHT_PAREN191_tree);


                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1197:4: atom
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_atom_in_combo_expression2244);
                    atom192=atom();

                    state._fsp--;

                    adaptor.addChild(root_0, atom192.getTree());

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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1200:1: atom : ( qualified_name ( COLON ID )? -> ^( QUALIFIED_SOLO_EXPRESSION qualified_name ( COLON ID )? ) | qualified_name COLON PARENT COLON qualified_name COLON ID -> ^( QUALIFIED_SOLO_PARENT_EXPRESSON qualified_name COLON PARENT COLON qualified_name COLON ID ) | qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN ) | selector COLON qualified_name -> ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name ) | PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN ) | ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN ) | ( MINUS )? INT | BOOLEAN | ( MINUS )? DECIMAL | STRING | NULL | ME | INPUT LEFT_PAREN expression RIGHT_PAREN | LEFT_PAREN expression RIGHT_PAREN -> ^( expression ) );
    public final QuorumParser.atom_return atom() throws RecognitionException {
        QuorumParser.atom_return retval = new QuorumParser.atom_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token COLON194=null;
        Token ID195=null;
        Token COLON197=null;
        Token PARENT198=null;
        Token COLON199=null;
        Token COLON201=null;
        Token ID202=null;
        Token COLON204=null;
        Token ID205=null;
        Token LEFT_PAREN206=null;
        Token RIGHT_PAREN208=null;
        Token COLON210=null;
        Token PARENT212=null;
        Token COLON213=null;
        Token COLON215=null;
        Token ID216=null;
        Token LEFT_PAREN217=null;
        Token RIGHT_PAREN219=null;
        Token ME220=null;
        Token COLON221=null;
        Token COLON223=null;
        Token ID224=null;
        Token LEFT_PAREN225=null;
        Token RIGHT_PAREN227=null;
        Token MINUS228=null;
        Token INT229=null;
        Token BOOLEAN230=null;
        Token MINUS231=null;
        Token DECIMAL232=null;
        Token STRING233=null;
        Token NULL234=null;
        Token ME235=null;
        Token INPUT236=null;
        Token LEFT_PAREN237=null;
        Token RIGHT_PAREN239=null;
        Token LEFT_PAREN240=null;
        Token RIGHT_PAREN242=null;
        QuorumParser.qualified_name_return qualified_name193 =null;

        QuorumParser.qualified_name_return qualified_name196 =null;

        QuorumParser.qualified_name_return qualified_name200 =null;

        QuorumParser.qualified_name_return qualified_name203 =null;

        QuorumParser.function_expression_list_return function_expression_list207 =null;

        QuorumParser.selector_return selector209 =null;

        QuorumParser.qualified_name_return qualified_name211 =null;

        QuorumParser.qualified_name_return qualified_name214 =null;

        QuorumParser.function_expression_list_return function_expression_list218 =null;

        QuorumParser.qualified_name_return qualified_name222 =null;

        QuorumParser.function_expression_list_return function_expression_list226 =null;

        QuorumParser.expression_return expression238 =null;

        QuorumParser.expression_return expression241 =null;


        CommonTree COLON194_tree=null;
        CommonTree ID195_tree=null;
        CommonTree COLON197_tree=null;
        CommonTree PARENT198_tree=null;
        CommonTree COLON199_tree=null;
        CommonTree COLON201_tree=null;
        CommonTree ID202_tree=null;
        CommonTree COLON204_tree=null;
        CommonTree ID205_tree=null;
        CommonTree LEFT_PAREN206_tree=null;
        CommonTree RIGHT_PAREN208_tree=null;
        CommonTree COLON210_tree=null;
        CommonTree PARENT212_tree=null;
        CommonTree COLON213_tree=null;
        CommonTree COLON215_tree=null;
        CommonTree ID216_tree=null;
        CommonTree LEFT_PAREN217_tree=null;
        CommonTree RIGHT_PAREN219_tree=null;
        CommonTree ME220_tree=null;
        CommonTree COLON221_tree=null;
        CommonTree COLON223_tree=null;
        CommonTree ID224_tree=null;
        CommonTree LEFT_PAREN225_tree=null;
        CommonTree RIGHT_PAREN227_tree=null;
        CommonTree MINUS228_tree=null;
        CommonTree INT229_tree=null;
        CommonTree BOOLEAN230_tree=null;
        CommonTree MINUS231_tree=null;
        CommonTree DECIMAL232_tree=null;
        CommonTree STRING233_tree=null;
        CommonTree NULL234_tree=null;
        CommonTree ME235_tree=null;
        CommonTree INPUT236_tree=null;
        CommonTree LEFT_PAREN237_tree=null;
        CommonTree RIGHT_PAREN239_tree=null;
        CommonTree LEFT_PAREN240_tree=null;
        CommonTree RIGHT_PAREN242_tree=null;
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
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1200:7: ( qualified_name ( COLON ID )? -> ^( QUALIFIED_SOLO_EXPRESSION qualified_name ( COLON ID )? ) | qualified_name COLON PARENT COLON qualified_name COLON ID -> ^( QUALIFIED_SOLO_PARENT_EXPRESSON qualified_name COLON PARENT COLON qualified_name COLON ID ) | qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN ) | selector COLON qualified_name -> ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name ) | PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN ) | ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN ) | ( MINUS )? INT | BOOLEAN | ( MINUS )? DECIMAL | STRING | NULL | ME | INPUT LEFT_PAREN expression RIGHT_PAREN | LEFT_PAREN expression RIGHT_PAREN -> ^( expression ) )
            int alt81=14;
            alt81 = dfa81.predict(input);
            switch (alt81) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1201:2: qualified_name ( COLON ID )?
                    {
                    pushFollow(FOLLOW_qualified_name_in_atom2259);
                    qualified_name193=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name193.getTree());

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1201:17: ( COLON ID )?
                    int alt76=2;
                    int LA76_0 = input.LA(1);

                    if ( (LA76_0==COLON) ) {
                        alt76=1;
                    }
                    switch (alt76) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1201:18: COLON ID
                            {
                            COLON194=(Token)match(input,COLON,FOLLOW_COLON_in_atom2262);  
                            stream_COLON.add(COLON194);


                            ID195=(Token)match(input,ID,FOLLOW_ID_in_atom2264);  
                            stream_ID.add(ID195);


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
                    // 1201:29: -> ^( QUALIFIED_SOLO_EXPRESSION qualified_name ( COLON ID )? )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1201:32: ^( QUALIFIED_SOLO_EXPRESSION qualified_name ( COLON ID )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(QUALIFIED_SOLO_EXPRESSION, "QUALIFIED_SOLO_EXPRESSION")
                        , root_1);

                        adaptor.addChild(root_1, stream_qualified_name.nextTree());

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1201:75: ( COLON ID )?
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1202:4: qualified_name COLON PARENT COLON qualified_name COLON ID
                    {
                    pushFollow(FOLLOW_qualified_name_in_atom2286);
                    qualified_name196=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name196.getTree());

                    COLON197=(Token)match(input,COLON,FOLLOW_COLON_in_atom2288);  
                    stream_COLON.add(COLON197);


                    PARENT198=(Token)match(input,PARENT,FOLLOW_PARENT_in_atom2290);  
                    stream_PARENT.add(PARENT198);


                    COLON199=(Token)match(input,COLON,FOLLOW_COLON_in_atom2292);  
                    stream_COLON.add(COLON199);


                    pushFollow(FOLLOW_qualified_name_in_atom2294);
                    qualified_name200=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name200.getTree());

                    COLON201=(Token)match(input,COLON,FOLLOW_COLON_in_atom2296);  
                    stream_COLON.add(COLON201);


                    ID202=(Token)match(input,ID,FOLLOW_ID_in_atom2298);  
                    stream_ID.add(ID202);


                    // AST REWRITE
                    // elements: PARENT, COLON, COLON, COLON, ID, qualified_name, qualified_name
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1202:62: -> ^( QUALIFIED_SOLO_PARENT_EXPRESSON qualified_name COLON PARENT COLON qualified_name COLON ID )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1202:65: ^( QUALIFIED_SOLO_PARENT_EXPRESSON qualified_name COLON PARENT COLON qualified_name COLON ID )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1203:4: qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN
                    {
                    pushFollow(FOLLOW_qualified_name_in_atom2323);
                    qualified_name203=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name203.getTree());

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1203:19: ( COLON ID )?
                    int alt77=2;
                    int LA77_0 = input.LA(1);

                    if ( (LA77_0==COLON) ) {
                        alt77=1;
                    }
                    switch (alt77) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1203:20: COLON ID
                            {
                            COLON204=(Token)match(input,COLON,FOLLOW_COLON_in_atom2326);  
                            stream_COLON.add(COLON204);


                            ID205=(Token)match(input,ID,FOLLOW_ID_in_atom2328);  
                            stream_ID.add(ID205);


                            }
                            break;

                    }


                    LEFT_PAREN206=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_atom2332);  
                    stream_LEFT_PAREN.add(LEFT_PAREN206);


                    pushFollow(FOLLOW_function_expression_list_in_atom2334);
                    function_expression_list207=function_expression_list();

                    state._fsp--;

                    stream_function_expression_list.add(function_expression_list207.getTree());

                    RIGHT_PAREN208=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_atom2336);  
                    stream_RIGHT_PAREN.add(RIGHT_PAREN208);


                    // AST REWRITE
                    // elements: RIGHT_PAREN, function_expression_list, LEFT_PAREN, COLON, ID, qualified_name
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1203:79: -> ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1204:4: ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(FUNCTION_CALL, "FUNCTION_CALL")
                        , root_1);

                        adaptor.addChild(root_1, stream_qualified_name.nextTree());

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1204:35: ( COLON ID )?
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1205:4: selector COLON qualified_name
                    {
                    pushFollow(FOLLOW_selector_in_atom2365);
                    selector209=selector();

                    state._fsp--;

                    stream_selector.add(selector209.getTree());

                    COLON210=(Token)match(input,COLON,FOLLOW_COLON_in_atom2367);  
                    stream_COLON.add(COLON210);


                    pushFollow(FOLLOW_qualified_name_in_atom2369);
                    qualified_name211=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name211.getTree());

                    // AST REWRITE
                    // elements: qualified_name, COLON, selector
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1205:34: -> ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1206:4: ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1209:4: PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN
                    {
                    PARENT212=(Token)match(input,PARENT,FOLLOW_PARENT_in_atom2394);  
                    stream_PARENT.add(PARENT212);


                    COLON213=(Token)match(input,COLON,FOLLOW_COLON_in_atom2396);  
                    stream_COLON.add(COLON213);


                    pushFollow(FOLLOW_qualified_name_in_atom2398);
                    qualified_name214=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name214.getTree());

                    COLON215=(Token)match(input,COLON,FOLLOW_COLON_in_atom2400);  
                    stream_COLON.add(COLON215);


                    ID216=(Token)match(input,ID,FOLLOW_ID_in_atom2402);  
                    stream_ID.add(ID216);


                    LEFT_PAREN217=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_atom2404);  
                    stream_LEFT_PAREN.add(LEFT_PAREN217);


                    pushFollow(FOLLOW_function_expression_list_in_atom2406);
                    function_expression_list218=function_expression_list();

                    state._fsp--;

                    stream_function_expression_list.add(function_expression_list218.getTree());

                    RIGHT_PAREN219=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_atom2408);  
                    stream_RIGHT_PAREN.add(RIGHT_PAREN219);


                    // AST REWRITE
                    // elements: PARENT, LEFT_PAREN, function_expression_list, ID, qualified_name, COLON, RIGHT_PAREN, COLON
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1209:89: -> ^( FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1210:4: ^( FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1211:4: ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN
                    {
                    ME220=(Token)match(input,ME,FOLLOW_ME_in_atom2438);  
                    stream_ME.add(ME220);


                    COLON221=(Token)match(input,COLON,FOLLOW_COLON_in_atom2440);  
                    stream_COLON.add(COLON221);


                    pushFollow(FOLLOW_qualified_name_in_atom2442);
                    qualified_name222=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name222.getTree());

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1211:28: ( COLON ID )?
                    int alt78=2;
                    int LA78_0 = input.LA(1);

                    if ( (LA78_0==COLON) ) {
                        alt78=1;
                    }
                    switch (alt78) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1211:29: COLON ID
                            {
                            COLON223=(Token)match(input,COLON,FOLLOW_COLON_in_atom2445);  
                            stream_COLON.add(COLON223);


                            ID224=(Token)match(input,ID,FOLLOW_ID_in_atom2447);  
                            stream_ID.add(ID224);


                            }
                            break;

                    }


                    LEFT_PAREN225=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_atom2451);  
                    stream_LEFT_PAREN.add(LEFT_PAREN225);


                    pushFollow(FOLLOW_function_expression_list_in_atom2453);
                    function_expression_list226=function_expression_list();

                    state._fsp--;

                    stream_function_expression_list.add(function_expression_list226.getTree());

                    RIGHT_PAREN227=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_atom2455);  
                    stream_RIGHT_PAREN.add(RIGHT_PAREN227);


                    // AST REWRITE
                    // elements: COLON, RIGHT_PAREN, LEFT_PAREN, qualified_name, ID, function_expression_list, ME, COLON
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1211:88: -> ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1212:4: ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN )
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

                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1212:49: ( COLON ID )?
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1213:4: ( MINUS )? INT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1213:4: ( MINUS )?
                    int alt79=2;
                    int LA79_0 = input.LA(1);

                    if ( (LA79_0==MINUS) ) {
                        alt79=1;
                    }
                    switch (alt79) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1213:5: MINUS
                            {
                            MINUS228=(Token)match(input,MINUS,FOLLOW_MINUS_in_atom2489); 
                            MINUS228_tree = 
                            (CommonTree)adaptor.create(MINUS228)
                            ;
                            adaptor.addChild(root_0, MINUS228_tree);


                            }
                            break;

                    }


                    INT229=(Token)match(input,INT,FOLLOW_INT_in_atom2493); 
                    INT229_tree = 
                    (CommonTree)adaptor.create(INT229)
                    ;
                    adaptor.addChild(root_0, INT229_tree);


                    }
                    break;
                case 8 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1214:4: BOOLEAN
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    BOOLEAN230=(Token)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_atom2498); 
                    BOOLEAN230_tree = 
                    (CommonTree)adaptor.create(BOOLEAN230)
                    ;
                    adaptor.addChild(root_0, BOOLEAN230_tree);


                    }
                    break;
                case 9 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1215:4: ( MINUS )? DECIMAL
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1215:4: ( MINUS )?
                    int alt80=2;
                    int LA80_0 = input.LA(1);

                    if ( (LA80_0==MINUS) ) {
                        alt80=1;
                    }
                    switch (alt80) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1215:5: MINUS
                            {
                            MINUS231=(Token)match(input,MINUS,FOLLOW_MINUS_in_atom2504); 
                            MINUS231_tree = 
                            (CommonTree)adaptor.create(MINUS231)
                            ;
                            adaptor.addChild(root_0, MINUS231_tree);


                            }
                            break;

                    }


                    DECIMAL232=(Token)match(input,DECIMAL,FOLLOW_DECIMAL_in_atom2508); 
                    DECIMAL232_tree = 
                    (CommonTree)adaptor.create(DECIMAL232)
                    ;
                    adaptor.addChild(root_0, DECIMAL232_tree);


                    }
                    break;
                case 10 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1216:4: STRING
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    STRING233=(Token)match(input,STRING,FOLLOW_STRING_in_atom2514); 
                    STRING233_tree = 
                    (CommonTree)adaptor.create(STRING233)
                    ;
                    adaptor.addChild(root_0, STRING233_tree);


                    }
                    break;
                case 11 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1217:4: NULL
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    NULL234=(Token)match(input,NULL,FOLLOW_NULL_in_atom2519); 
                    NULL234_tree = 
                    (CommonTree)adaptor.create(NULL234)
                    ;
                    adaptor.addChild(root_0, NULL234_tree);


                    }
                    break;
                case 12 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1218:4: ME
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    ME235=(Token)match(input,ME,FOLLOW_ME_in_atom2524); 
                    ME235_tree = 
                    (CommonTree)adaptor.create(ME235)
                    ;
                    adaptor.addChild(root_0, ME235_tree);


                    }
                    break;
                case 13 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1219:5: INPUT LEFT_PAREN expression RIGHT_PAREN
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    INPUT236=(Token)match(input,INPUT,FOLLOW_INPUT_in_atom2530); 
                    INPUT236_tree = 
                    (CommonTree)adaptor.create(INPUT236)
                    ;
                    adaptor.addChild(root_0, INPUT236_tree);


                    LEFT_PAREN237=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_atom2532); 
                    LEFT_PAREN237_tree = 
                    (CommonTree)adaptor.create(LEFT_PAREN237)
                    ;
                    adaptor.addChild(root_0, LEFT_PAREN237_tree);


                    pushFollow(FOLLOW_expression_in_atom2534);
                    expression238=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression238.getTree());

                    RIGHT_PAREN239=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_atom2536); 
                    RIGHT_PAREN239_tree = 
                    (CommonTree)adaptor.create(RIGHT_PAREN239)
                    ;
                    adaptor.addChild(root_0, RIGHT_PAREN239_tree);


                    }
                    break;
                case 14 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1220:4: LEFT_PAREN expression RIGHT_PAREN
                    {
                    LEFT_PAREN240=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_atom2541);  
                    stream_LEFT_PAREN.add(LEFT_PAREN240);


                    pushFollow(FOLLOW_expression_in_atom2543);
                    expression241=expression();

                    state._fsp--;

                    stream_expression.add(expression241.getTree());

                    RIGHT_PAREN242=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_atom2545);  
                    stream_RIGHT_PAREN.add(RIGHT_PAREN242);


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
                    // 1220:38: -> ^( expression )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1220:41: ^( expression )
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1223:1: function_expression_list : ( expression ( COMMA expression )* )? -> ^( FUNCTION_EXPRESSION_LIST ( expression )* ) ;
    public final QuorumParser.function_expression_list_return function_expression_list() throws RecognitionException {
        QuorumParser.function_expression_list_return retval = new QuorumParser.function_expression_list_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token COMMA244=null;
        QuorumParser.expression_return expression243 =null;

        QuorumParser.expression_return expression245 =null;


        CommonTree COMMA244_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1224:2: ( ( expression ( COMMA expression )* )? -> ^( FUNCTION_EXPRESSION_LIST ( expression )* ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1225:2: ( expression ( COMMA expression )* )?
            {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1225:2: ( expression ( COMMA expression )* )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==BOOLEAN||LA83_0==CAST||LA83_0==DECIMAL||LA83_0==ID||(LA83_0 >= INPUT && LA83_0 <= INT)||LA83_0==LEFT_PAREN||(LA83_0 >= ME && LA83_0 <= MINUS)||LA83_0==NOT||LA83_0==NULL||LA83_0==PARENT||LA83_0==STRING) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1225:3: expression ( COMMA expression )*
                    {
                    pushFollow(FOLLOW_expression_in_function_expression_list2565);
                    expression243=expression();

                    state._fsp--;

                    stream_expression.add(expression243.getTree());

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1225:14: ( COMMA expression )*
                    loop82:
                    do {
                        int alt82=2;
                        int LA82_0 = input.LA(1);

                        if ( (LA82_0==COMMA) ) {
                            alt82=1;
                        }


                        switch (alt82) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1225:15: COMMA expression
                    	    {
                    	    COMMA244=(Token)match(input,COMMA,FOLLOW_COMMA_in_function_expression_list2568);  
                    	    stream_COMMA.add(COMMA244);


                    	    pushFollow(FOLLOW_expression_in_function_expression_list2570);
                    	    expression245=expression();

                    	    state._fsp--;

                    	    stream_expression.add(expression245.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop82;
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
            // 1226:2: -> ^( FUNCTION_EXPRESSION_LIST ( expression )* )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1226:5: ^( FUNCTION_EXPRESSION_LIST ( expression )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(FUNCTION_EXPRESSION_LIST, "FUNCTION_EXPRESSION_LIST")
                , root_1);

                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1226:32: ( expression )*
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
    protected DFA59 dfa59 = new DFA59(this);
    protected DFA81 dfa81 = new DFA81(this);
    static final String DFA4_eotS =
        "\16\uffff";
    static final String DFA4_eofS =
        "\16\uffff";
    static final String DFA4_minS =
        "\1\4\2\46\1\uffff\2\4\1\46\2\uffff\1\46\2\uffff\2\4";
    static final String DFA4_maxS =
        "\1\134\2\46\1\uffff\2\134\1\46\2\uffff\1\46\2\uffff\2\134";
    static final String DFA4_acceptS =
        "\3\uffff\1\5\3\uffff\1\1\1\3\1\uffff\1\2\1\4\2\uffff";
    static final String DFA4_specialS =
        "\16\uffff}>";
    static final String[] DFA4_transitionS = {
            "\2\3\2\uffff\1\3\1\uffff\1\3\1\uffff\2\3\3\uffff\1\3\24\uffff"+
            "\2\3\3\uffff\1\3\4\uffff\1\3\3\uffff\1\3\5\uffff\1\3\1\uffff"+
            "\1\3\3\uffff\1\1\1\3\3\uffff\3\3\4\uffff\2\3\4\uffff\1\3\5\uffff"+
            "\1\3\3\uffff\1\2",
            "\1\4",
            "\1\5",
            "",
            "\2\10\2\uffff\1\10\1\uffff\1\10\1\uffff\2\10\3\uffff\1\10\24"+
            "\uffff\2\10\3\uffff\1\10\4\uffff\1\10\3\uffff\1\10\5\uffff\1"+
            "\10\1\uffff\1\10\4\uffff\1\10\1\uffff\1\6\1\uffff\3\10\4\uffff"+
            "\2\10\4\uffff\1\10\5\uffff\1\10\3\uffff\1\7",
            "\2\13\2\uffff\1\13\1\uffff\1\13\1\uffff\2\13\3\uffff\1\13\24"+
            "\uffff\2\13\3\uffff\1\13\4\uffff\1\13\3\uffff\1\13\5\uffff\1"+
            "\13\1\uffff\1\13\3\uffff\1\12\1\13\1\uffff\1\11\1\uffff\3\13"+
            "\4\uffff\2\13\4\uffff\1\13\5\uffff\1\13\3\uffff\1\2",
            "\1\14",
            "",
            "",
            "\1\15",
            "",
            "",
            "\2\10\2\uffff\1\10\1\uffff\1\10\1\uffff\2\10\3\uffff\1\10\24"+
            "\uffff\2\10\3\uffff\1\10\4\uffff\1\10\3\uffff\1\10\5\uffff\1"+
            "\10\1\uffff\1\10\4\uffff\1\10\1\uffff\1\6\1\uffff\3\10\4\uffff"+
            "\2\10\4\uffff\1\10\5\uffff\1\10\3\uffff\1\7",
            "\2\13\2\uffff\1\13\1\uffff\1\13\1\uffff\2\13\3\uffff\1\13\24"+
            "\uffff\2\13\3\uffff\1\13\4\uffff\1\13\3\uffff\1\13\5\uffff\1"+
            "\13\1\uffff\1\13\3\uffff\1\12\1\13\1\uffff\1\11\1\uffff\3\13"+
            "\4\uffff\2\13\4\uffff\1\13\5\uffff\1\13\3\uffff\1\2"
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
        "\1\5\3\16\10\uffff\2\46\1\uffff\2\46\1\16\1\33\2\16\2\46\1\16\1"+
        "\33";
    static final String DFA34_maxS =
        "\1\130\1\103\2\16\10\uffff\1\46\1\101\1\uffff\2\46\1\103\1\54\2"+
        "\103\2\46\1\103\1\54";
    static final String DFA34_acceptS =
        "\4\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\2\uffff\1\1\12\uffff";
    static final String DFA34_specialS =
        "\31\uffff}>";
    static final String[] DFA34_transitionS = {
            "\1\13\4\uffff\1\5\1\uffff\1\12\4\uffff\1\5\24\uffff\1\1\1\4"+
            "\3\uffff\1\5\4\uffff\1\3\11\uffff\1\5\6\uffff\1\2\3\uffff\1"+
            "\10\2\5\4\uffff\1\6\1\7\4\uffff\1\11\5\uffff\1\5",
            "\1\15\14\uffff\1\5\12\uffff\1\5\5\uffff\1\16\1\uffff\1\5\24"+
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
            "\1\15\27\uffff\1\5\5\uffff\1\16\1\uffff\1\5\24\uffff\1\14",
            "\1\5\20\uffff\1\16",
            "\1\26\64\uffff\1\25",
            "\1\16\14\uffff\1\5\20\uffff\1\16\26\uffff\1\16",
            "\1\27",
            "\1\30",
            "\1\26\64\uffff\1\25",
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
    static final String DFA59_eotS =
        "\7\uffff";
    static final String DFA59_eofS =
        "\7\uffff";
    static final String DFA59_minS =
        "\1\12\1\uffff\1\16\1\uffff\1\46\1\uffff\1\16";
    static final String DFA59_maxS =
        "\1\130\1\uffff\1\103\1\uffff\1\46\1\uffff\1\103";
    static final String DFA59_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\1\uffff\1\2\1\uffff";
    static final String DFA59_specialS =
        "\7\uffff}>";
    static final String[] DFA59_transitionS = {
            "\1\3\6\uffff\1\3\24\uffff\1\2\4\uffff\1\3\4\uffff\1\1\11\uffff"+
            "\1\3\6\uffff\1\1\4\uffff\2\3\20\uffff\1\3",
            "",
            "\1\5\14\uffff\1\1\12\uffff\1\3\7\uffff\1\3\24\uffff\1\4",
            "",
            "\1\6",
            "",
            "\1\5\27\uffff\1\3\7\uffff\1\3\24\uffff\1\4"
    };

    static final short[] DFA59_eot = DFA.unpackEncodedString(DFA59_eotS);
    static final short[] DFA59_eof = DFA.unpackEncodedString(DFA59_eofS);
    static final char[] DFA59_min = DFA.unpackEncodedStringToUnsignedChars(DFA59_minS);
    static final char[] DFA59_max = DFA.unpackEncodedStringToUnsignedChars(DFA59_maxS);
    static final short[] DFA59_accept = DFA.unpackEncodedString(DFA59_acceptS);
    static final short[] DFA59_special = DFA.unpackEncodedString(DFA59_specialS);
    static final short[][] DFA59_transition;

    static {
        int numStates = DFA59_transitionS.length;
        DFA59_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA59_transition[i] = DFA.unpackEncodedString(DFA59_transitionS[i]);
        }
    }

    class DFA59 extends DFA {

        public DFA59(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 59;
            this.eot = DFA59_eot;
            this.eof = DFA59_eof;
            this.min = DFA59_min;
            this.max = DFA59_max;
            this.accept = DFA59_accept;
            this.special = DFA59_special;
            this.transition = DFA59_transition;
        }
        public String getDescription() {
            return "977:1: assignment_statement : ( (sel= selector COLON )? ID rhs= assign_right_hand_side |obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side | (modifier= access_modifier )? ( CONSTANT )? type= assignment_declaration name= ID (rhs= assign_right_hand_side )? );";
        }
    }
    static final String DFA81_eotS =
        "\41\uffff";
    static final String DFA81_eofS =
        "\1\uffff\1\16\1\uffff\1\22\17\uffff\2\16\2\uffff\1\34\6\uffff\2"+
        "\34\1\uffff";
    static final String DFA81_minS =
        "\1\11\1\4\1\16\1\4\1\23\7\uffff\2\46\2\uffff\2\46\1\uffff\2\4\1"+
        "\uffff\1\16\1\4\3\46\2\uffff\1\16\2\4\1\uffff";
    static final String DFA81_maxS =
        "\1\127\1\131\1\16\1\131\1\52\7\uffff\1\46\1\101\2\uffff\2\46\1\uffff"+
        "\2\131\1\uffff\1\103\1\131\3\46\2\uffff\1\103\2\131\1\uffff";
    static final String DFA81_acceptS =
        "\5\uffff\1\7\1\10\1\11\1\12\1\13\1\15\1\16\2\uffff\1\1\1\3\2\uffff"+
        "\1\14\2\uffff\1\2\5\uffff\1\6\1\4\3\uffff\1\5";
    static final String DFA81_specialS =
        "\41\uffff}>";
    static final String[] DFA81_transitionS = {
            "\1\6\11\uffff\1\7\22\uffff\1\1\2\uffff\1\12\1\5\1\uffff\1\13"+
            "\3\uffff\1\3\1\4\7\uffff\1\11\7\uffff\1\2\25\uffff\1\10",
            "\5\16\1\uffff\1\16\1\uffff\1\16\1\uffff\1\15\1\16\1\uffff\1"+
            "\16\2\uffff\2\16\1\uffff\2\16\1\uffff\2\16\10\uffff\5\16\2\uffff"+
            "\1\16\1\17\1\uffff\7\16\2\uffff\1\16\2\uffff\1\16\1\uffff\1"+
            "\16\1\uffff\1\16\2\uffff\1\16\1\uffff\1\14\4\16\4\uffff\2\16"+
            "\1\uffff\1\16\2\uffff\1\16\5\uffff\2\16",
            "\1\20",
            "\5\22\1\uffff\1\22\1\uffff\1\22\1\uffff\1\21\1\22\1\uffff\1"+
            "\22\2\uffff\2\22\1\uffff\2\22\1\uffff\2\22\10\uffff\5\22\2\uffff"+
            "\1\22\2\uffff\7\22\2\uffff\1\22\2\uffff\1\22\1\uffff\1\22\1"+
            "\uffff\1\22\2\uffff\1\22\2\uffff\4\22\4\uffff\2\22\1\uffff\1"+
            "\22\2\uffff\1\22\5\uffff\2\22",
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
            "\5\16\1\uffff\1\16\1\uffff\1\16\1\uffff\1\15\1\16\1\uffff\1"+
            "\16\2\uffff\2\16\1\uffff\2\16\1\uffff\2\16\10\uffff\5\16\2\uffff"+
            "\1\16\1\17\1\uffff\7\16\2\uffff\1\16\2\uffff\1\16\1\uffff\1"+
            "\16\1\uffff\1\16\2\uffff\1\16\1\uffff\1\14\4\16\4\uffff\2\16"+
            "\1\uffff\1\16\2\uffff\1\16\5\uffff\2\16",
            "\5\16\1\uffff\1\16\1\uffff\1\16\2\uffff\1\16\1\uffff\1\16\2"+
            "\uffff\2\16\1\uffff\2\16\1\uffff\2\16\10\uffff\5\16\2\uffff"+
            "\1\16\1\17\1\uffff\7\16\2\uffff\1\16\2\uffff\1\16\1\uffff\1"+
            "\16\1\uffff\1\16\2\uffff\1\16\2\uffff\4\16\4\uffff\2\16\1\uffff"+
            "\1\16\2\uffff\1\16\5\uffff\2\16",
            "",
            "\1\31\64\uffff\1\30",
            "\5\34\1\uffff\1\34\1\uffff\1\34\1\uffff\1\33\1\34\1\uffff\1"+
            "\34\2\uffff\2\34\1\uffff\2\34\1\uffff\2\34\10\uffff\5\34\2\uffff"+
            "\1\34\1\33\1\uffff\7\34\2\uffff\1\34\2\uffff\1\34\1\uffff\1"+
            "\34\1\uffff\1\34\2\uffff\1\34\1\uffff\1\32\4\34\4\uffff\2\34"+
            "\1\uffff\1\34\2\uffff\1\34\5\uffff\2\34",
            "\1\35",
            "\1\36",
            "\1\37",
            "",
            "",
            "\1\31\64\uffff\1\30",
            "\5\34\1\uffff\1\34\1\uffff\1\34\2\uffff\1\34\1\uffff\1\34\2"+
            "\uffff\2\34\1\uffff\2\34\1\uffff\2\34\10\uffff\5\34\2\uffff"+
            "\1\34\1\40\1\uffff\7\34\2\uffff\1\34\2\uffff\1\34\1\uffff\1"+
            "\34\1\uffff\1\34\2\uffff\1\34\1\uffff\5\34\4\uffff\2\34\1\uffff"+
            "\1\34\2\uffff\1\34\5\uffff\2\34",
            "\5\34\1\uffff\1\34\1\uffff\1\34\1\uffff\1\33\1\34\1\uffff\1"+
            "\34\2\uffff\2\34\1\uffff\2\34\1\uffff\2\34\10\uffff\5\34\2\uffff"+
            "\1\34\1\33\1\uffff\7\34\2\uffff\1\34\2\uffff\1\34\1\uffff\1"+
            "\34\1\uffff\1\34\2\uffff\1\34\1\uffff\1\32\4\34\4\uffff\2\34"+
            "\1\uffff\1\34\2\uffff\1\34\5\uffff\2\34",
            ""
    };

    static final short[] DFA81_eot = DFA.unpackEncodedString(DFA81_eotS);
    static final short[] DFA81_eof = DFA.unpackEncodedString(DFA81_eofS);
    static final char[] DFA81_min = DFA.unpackEncodedStringToUnsignedChars(DFA81_minS);
    static final char[] DFA81_max = DFA.unpackEncodedStringToUnsignedChars(DFA81_maxS);
    static final short[] DFA81_accept = DFA.unpackEncodedString(DFA81_acceptS);
    static final short[] DFA81_special = DFA.unpackEncodedString(DFA81_specialS);
    static final short[][] DFA81_transition;

    static {
        int numStates = DFA81_transitionS.length;
        DFA81_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA81_transition[i] = DFA.unpackEncodedString(DFA81_transitionS[i]);
        }
    }

    class DFA81 extends DFA {

        public DFA81(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 81;
            this.eot = DFA81_eot;
            this.eof = DFA81_eof;
            this.min = DFA81_min;
            this.max = DFA81_max;
            this.accept = DFA81_accept;
            this.special = DFA81_special;
            this.transition = DFA81_transition;
        }
        public String getDescription() {
            return "1200:1: atom : ( qualified_name ( COLON ID )? -> ^( QUALIFIED_SOLO_EXPRESSION qualified_name ( COLON ID )? ) | qualified_name COLON PARENT COLON qualified_name COLON ID -> ^( QUALIFIED_SOLO_PARENT_EXPRESSON qualified_name COLON PARENT COLON qualified_name COLON ID ) | qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN ) | selector COLON qualified_name -> ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name ) | PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN ) | ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN ) | ( MINUS )? INT | BOOLEAN | ( MINUS )? DECIMAL | STRING | NULL | ME | INPUT LEFT_PAREN expression RIGHT_PAREN | LEFT_PAREN expression RIGHT_PAREN -> ^( expression ) );";
        }
    }
 

    public static final BitSet FOLLOW_package_rule_in_start150 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_reference_in_start152 = new BitSet(new long[]{0x141108C000023530L,0x00000000110430E2L});
    public static final BitSet FOLLOW_reference_in_start159 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000001L});
    public static final BitSet FOLLOW_package_rule_in_start162 = new BitSet(new long[]{0x141108C000023530L,0x00000000010430E2L});
    public static final BitSet FOLLOW_package_rule_in_start167 = new BitSet(new long[]{0x141108C000023530L,0x00000000010430E2L});
    public static final BitSet FOLLOW_reference_in_start172 = new BitSet(new long[]{0x141108C000023530L,0x00000000110430E2L});
    public static final BitSet FOLLOW_class_declaration_in_start181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_start184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PACKAGE_NAME_in_package_rule195 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_qualified_name_in_package_rule199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_USE_in_reference217 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_qualified_name_in_reference223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_in_class_declaration255 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_class_declaration257 = new BitSet(new long[]{0x1411494004020510L,0x00000000010000C2L});
    public static final BitSet FOLLOW_generic_declaration_in_class_declaration268 = new BitSet(new long[]{0x1411094004020510L,0x00000000010000C2L});
    public static final BitSet FOLLOW_inherit_stmnts_in_class_declaration275 = new BitSet(new long[]{0x1411084004020510L,0x00000000010000C2L});
    public static final BitSet FOLLOW_class_stmnts_in_class_declaration286 = new BitSet(new long[]{0x1411084004020510L,0x00000000010000C2L});
    public static final BitSet FOLLOW_END_in_class_declaration290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_no_class_stmnts_in_class_declaration324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_no_class_stmnts343 = new BitSet(new long[]{0x040108C000021422L,0x00000000010430E2L});
    public static final BitSet FOLLOW_access_modifier_in_no_class_stmnts352 = new BitSet(new long[]{0x1010000000000110L});
    public static final BitSet FOLLOW_method_declaration_in_no_class_stmnts355 = new BitSet(new long[]{0x1010000000000112L,0x00000000000000C0L});
    public static final BitSet FOLLOW_INHERITS_in_inherit_stmnts368 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_qualified_name_in_inherit_stmnts372 = new BitSet(new long[]{0x0000400000008002L});
    public static final BitSet FOLLOW_generic_statement_in_inherit_stmnts376 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_COMMA_in_inherit_stmnts384 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_qualified_name_in_inherit_stmnts388 = new BitSet(new long[]{0x0000400000008002L});
    public static final BitSet FOLLOW_generic_statement_in_inherit_stmnts392 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_PUBLIC_in_access_modifier429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIVATE_in_access_modifier437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_statement_in_class_stmnts455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_access_modifier_in_class_stmnts466 = new BitSet(new long[]{0x1010000000000110L});
    public static final BitSet FOLLOW_method_declaration_in_class_stmnts474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_method_declaration492 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_method_declaration494 = new BitSet(new long[]{0x040118C004021420L,0x00000000010470E2L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_method_declaration504 = new BitSet(new long[]{0x0400084000000400L,0x0000000001008000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration507 = new BitSet(new long[]{0x0000000000008000L,0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_method_declaration511 = new BitSet(new long[]{0x0400084000000400L,0x0000000001000000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration513 = new BitSet(new long[]{0x0000000000008000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_method_declaration520 = new BitSet(new long[]{0x040108C004021420L,0x00000000010470E2L});
    public static final BitSet FOLLOW_RETURNS_in_method_declaration526 = new BitSet(new long[]{0x0400084000000400L,0x0000000001000000L});
    public static final BitSet FOLLOW_assignment_declaration_in_method_declaration532 = new BitSet(new long[]{0x040108C004021420L,0x00000000010430E2L});
    public static final BitSet FOLLOW_block_in_method_declaration542 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_END_in_method_declaration545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BLUEPRINT_in_method_declaration577 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ACTION_in_method_declaration579 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_method_declaration581 = new BitSet(new long[]{0x0000100000000002L,0x0000000000004000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_method_declaration591 = new BitSet(new long[]{0x0400084000000400L,0x0000000001008000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration594 = new BitSet(new long[]{0x0000000000008000L,0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_method_declaration598 = new BitSet(new long[]{0x0400084000000400L,0x0000000001000000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration600 = new BitSet(new long[]{0x0000000000008000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_method_declaration607 = new BitSet(new long[]{0x0000000000000002L,0x0000000000004000L});
    public static final BitSet FOLLOW_RETURNS_in_method_declaration613 = new BitSet(new long[]{0x0400084000000400L,0x0000000001000000L});
    public static final BitSet FOLLOW_assignment_declaration_in_method_declaration619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NATIVE_in_method_declaration651 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ACTION_in_method_declaration653 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_method_declaration655 = new BitSet(new long[]{0x0000100000000002L,0x0000000000004000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_method_declaration665 = new BitSet(new long[]{0x0400084000000400L,0x0000000001008000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration668 = new BitSet(new long[]{0x0000000000008000L,0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_method_declaration672 = new BitSet(new long[]{0x0400084000000400L,0x0000000001000000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration674 = new BitSet(new long[]{0x0000000000008000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_method_declaration681 = new BitSet(new long[]{0x0000000000000002L,0x0000000000004000L});
    public static final BitSet FOLLOW_RETURNS_in_method_declaration687 = new BitSet(new long[]{0x0400084000000400L,0x0000000001000000L});
    public static final BitSet FOLLOW_assignment_declaration_in_method_declaration693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ON_CREATE_in_method_declaration725 = new BitSet(new long[]{0x040108C004021420L,0x00000000010430E2L});
    public static final BitSet FOLLOW_block_in_method_declaration732 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_END_in_method_declaration734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_declaration_in_formal_parameter759 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_formal_parameter761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_qualified_name792 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_PERIOD_in_qualified_name795 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_qualified_name799 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_statement_in_block833 = new BitSet(new long[]{0x040108C000021422L,0x00000000010430E2L});
    public static final BitSet FOLLOW_solo_method_call_in_statement856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_statement_in_statement861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_statement_in_statement866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_loop_statement_in_statement871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_statement_in_statement876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_print_statement_in_statement881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_speak_statement_in_statement886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_check_statement_in_statement891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_alert_statement_in_statement896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_solo_method_call909 = new BitSet(new long[]{0x0000100000004000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call912 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_solo_method_call914 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call918 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000808002L});
    public static final BitSet FOLLOW_expression_in_solo_method_call921 = new BitSet(new long[]{0x0000000000008000L,0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call924 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_expression_in_solo_method_call926 = new BitSet(new long[]{0x0000000000008000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENT_in_solo_method_call971 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call973 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_qualified_name_in_solo_method_call975 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call977 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_solo_method_call979 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call981 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000808002L});
    public static final BitSet FOLLOW_expression_in_solo_method_call984 = new BitSet(new long[]{0x0000000000008000L,0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call987 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_expression_in_solo_method_call989 = new BitSet(new long[]{0x0000000000008000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ME_in_solo_method_call1035 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call1037 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_qualified_name_in_solo_method_call1039 = new BitSet(new long[]{0x0000100000004000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call1042 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_solo_method_call1044 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call1048 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000808002L});
    public static final BitSet FOLLOW_expression_in_solo_method_call1051 = new BitSet(new long[]{0x0000000000008000L,0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call1054 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_expression_in_solo_method_call1056 = new BitSet(new long[]{0x0000000000008000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call1062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALERT_in_alert_statement1113 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_alert_statement1115 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_root_expression_in_alert_statement1117 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_alert_statement1120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHECK_in_check_statement1154 = new BitSet(new long[]{0x040108C000121460L,0x00000000010430E2L});
    public static final BitSet FOLLOW_block_in_check_statement1157 = new BitSet(new long[]{0x0000000000100040L});
    public static final BitSet FOLLOW_DETECT_in_check_statement1172 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_detect_parameter_in_check_statement1184 = new BitSet(new long[]{0x040108C004121460L,0x00000000010430E2L});
    public static final BitSet FOLLOW_block_in_check_statement1186 = new BitSet(new long[]{0x0000000004100040L});
    public static final BitSet FOLLOW_ALWAYS_in_check_statement1210 = new BitSet(new long[]{0x040108C004021420L,0x00000000010430E2L});
    public static final BitSet FOLLOW_block_in_check_statement1221 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ALWAYS_in_check_statement1243 = new BitSet(new long[]{0x040108C004021420L,0x00000000010430E2L});
    public static final BitSet FOLLOW_block_in_check_statement1254 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_END_in_check_statement1269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_detect_parameter1297 = new BitSet(new long[]{0x0800000000000002L});
    public static final BitSet FOLLOW_OF_TYPE_in_detect_parameter1309 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_qualified_name_in_detect_parameter1311 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_OR_in_detect_parameter1313 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_qualified_name_in_detect_parameter1315 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_PRINT_in_print_statement1350 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_root_expression_in_print_statement1352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAY_in_speak_statement1364 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_root_expression_in_speak_statement1366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_return_statement1377 = new BitSet(new long[]{0x0343164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_root_expression_in_return_statement1381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOW_in_return_statement1385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_generic_declaration1402 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_generic_declaration1406 = new BitSet(new long[]{0x0000001000008000L});
    public static final BitSet FOLLOW_COMMA_in_generic_declaration1409 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_generic_declaration1413 = new BitSet(new long[]{0x0000001000008000L});
    public static final BitSet FOLLOW_GREATER_in_generic_declaration1417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_generic_statement1458 = new BitSet(new long[]{0x0400084000000400L,0x0000000001000000L});
    public static final BitSet FOLLOW_assignment_declaration_in_generic_statement1468 = new BitSet(new long[]{0x0000001000008000L});
    public static final BitSet FOLLOW_COMMA_in_generic_statement1475 = new BitSet(new long[]{0x0400084000000400L,0x0000000001000000L});
    public static final BitSet FOLLOW_assignment_declaration_in_generic_statement1479 = new BitSet(new long[]{0x0000001000008000L});
    public static final BitSet FOLLOW_GREATER_in_generic_statement1489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_class_type1529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_assignment_declaration1550 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_generic_statement_in_assignment_declaration1554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_KEYWORD_in_assignment_declaration1563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_KEYWORD_in_assignment_declaration1571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TEXT_in_assignment_declaration1579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_KEYWORD_in_assignment_declaration1587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_assignment_statement1610 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1612 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_assignment_statement1616 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_assign_right_hand_side_in_assignment_statement1622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_assignment_statement1633 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1636 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_PARENT_in_assignment_statement1638 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1640 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_qualified_name_in_assignment_statement1644 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1648 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_assignment_statement1650 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_assign_right_hand_side_in_assignment_statement1654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_access_modifier_in_assignment_statement1663 = new BitSet(new long[]{0x0400084000020400L,0x0000000001000000L});
    public static final BitSet FOLLOW_CONSTANT_in_assignment_statement1671 = new BitSet(new long[]{0x0400084000000400L,0x0000000001000000L});
    public static final BitSet FOLLOW_assignment_declaration_in_assignment_statement1678 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_assignment_statement1684 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_assign_right_hand_side_in_assignment_statement1690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALITY_in_assign_right_hand_side1716 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_root_expression_in_assign_right_hand_side1718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_if_statement1743 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_root_expression_in_if_statement1745 = new BitSet(new long[]{0x040108C005821420L,0x00000000010430E2L});
    public static final BitSet FOLLOW_block_in_if_statement1751 = new BitSet(new long[]{0x0000000005800000L});
    public static final BitSet FOLLOW_ELSE_IF_in_if_statement1763 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_root_expression_in_if_statement1771 = new BitSet(new long[]{0x040108C005821420L,0x00000000010430E2L});
    public static final BitSet FOLLOW_block_in_if_statement1777 = new BitSet(new long[]{0x0000000005800000L});
    public static final BitSet FOLLOW_ELSE_in_if_statement1795 = new BitSet(new long[]{0x040108C004021420L,0x00000000010430E2L});
    public static final BitSet FOLLOW_block_in_if_statement1803 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_END_in_if_statement1819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REPEAT_in_loop_statement1844 = new BitSet(new long[]{0x0243164000080A00L,0x0000000028800002L});
    public static final BitSet FOLLOW_root_expression_in_loop_statement1912 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_TIMES_in_loop_statement1914 = new BitSet(new long[]{0x040108C004021420L,0x00000000010430E2L});
    public static final BitSet FOLLOW_set_in_loop_statement1922 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_root_expression_in_loop_statement1930 = new BitSet(new long[]{0x040108C004021420L,0x00000000010430E2L});
    public static final BitSet FOLLOW_block_in_loop_statement1939 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_END_in_loop_statement1941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENT_in_selector1965 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_selector1967 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_qualified_name_in_selector1971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ME_in_selector1989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_root_expression2003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_expression2023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_in_or2035 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_OR_in_or2038 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_and_in_or2042 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_equality_in_and2055 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_AND_in_and2058 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_equality_in_and2062 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_isa_operation_in_equality2073 = new BitSet(new long[]{0x0080000008000002L});
    public static final BitSet FOLLOW_EQUALITY_in_equality2077 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_NOTEQUALS_in_equality2083 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_isa_operation_in_equality2088 = new BitSet(new long[]{0x0080000008000002L});
    public static final BitSet FOLLOW_comparison_in_isa_operation2100 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_INHERITS_in_isa_operation2103 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_class_type_in_isa_operation2107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_add_in_comparison2117 = new BitSet(new long[]{0x0000C03000000002L});
    public static final BitSet FOLLOW_GREATER_in_comparison2121 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_GREATER_EQUAL_in_comparison2126 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_LESS_in_comparison2131 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_LESS_EQUAL_in_comparison2136 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_add_in_comparison2140 = new BitSet(new long[]{0x0000C03000000002L});
    public static final BitSet FOLLOW_multiply_in_add2154 = new BitSet(new long[]{0x0002000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_PLUS_in_add2158 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_MINUS_in_add2163 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_multiply_in_add2167 = new BitSet(new long[]{0x0002000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_combo_expression_in_multiply2180 = new BitSet(new long[]{0x000C000000200002L});
    public static final BitSet FOLLOW_MULTIPLY_in_multiply2184 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_DIVIDE_in_multiply2189 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_MODULO_in_multiply2193 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_combo_expression_in_multiply2197 = new BitSet(new long[]{0x000C000000200002L});
    public static final BitSet FOLLOW_NOT_in_combo_expression2212 = new BitSet(new long[]{0x0203164000080200L,0x0000000000800002L});
    public static final BitSet FOLLOW_atom_in_combo_expression2214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CAST_in_combo_expression2229 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_combo_expression2231 = new BitSet(new long[]{0x0400084000000400L,0x0000000001000000L});
    public static final BitSet FOLLOW_assignment_declaration_in_combo_expression2233 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_combo_expression2235 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_expression_in_combo_expression2237 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_combo_expression2239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_combo_expression2244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_atom2259 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_COLON_in_atom2262 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_atom2264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_atom2286 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_atom2288 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_PARENT_in_atom2290 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_atom2292 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_qualified_name_in_atom2294 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_atom2296 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_atom2298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_atom2323 = new BitSet(new long[]{0x0000100000004000L});
    public static final BitSet FOLLOW_COLON_in_atom2326 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_atom2328 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_atom2332 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000808002L});
    public static final BitSet FOLLOW_function_expression_list_in_atom2334 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_atom2336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_atom2365 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_atom2367 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_qualified_name_in_atom2369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENT_in_atom2394 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_atom2396 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_qualified_name_in_atom2398 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_atom2400 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_atom2402 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_atom2404 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000808002L});
    public static final BitSet FOLLOW_function_expression_list_in_atom2406 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_atom2408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ME_in_atom2438 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_atom2440 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_qualified_name_in_atom2442 = new BitSet(new long[]{0x0000100000004000L});
    public static final BitSet FOLLOW_COLON_in_atom2445 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_ID_in_atom2447 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_atom2451 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000808002L});
    public static final BitSet FOLLOW_function_expression_list_in_atom2453 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_atom2455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_atom2489 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_INT_in_atom2493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_atom2498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_atom2504 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DECIMAL_in_atom2508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_atom2514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_atom2519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ME_in_atom2524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INPUT_in_atom2530 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_atom2532 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_expression_in_atom2534 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_atom2536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_atom2541 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_expression_in_atom2543 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_atom2545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_function_expression_list2565 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_COMMA_in_function_expression_list2568 = new BitSet(new long[]{0x0243164000080A00L,0x0000000000800002L});
    public static final BitSet FOLLOW_expression_in_function_expression_list2570 = new BitSet(new long[]{0x0000000000008002L});

}