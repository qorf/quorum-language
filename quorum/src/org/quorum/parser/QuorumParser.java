// $ANTLR 3.3 Nov 30, 2010 12:45:30 /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g 2011-08-31 09:40:53



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

public class QuorumParser extends Parser {
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
                        message = " The end of the file was reached before all the code was evaluated. There may be an extraneous " + getTokenErrorDisplay(re.token);
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
                    if(tokenName.equals("THEN")){
                        message = "An 'else if' statement is missing the 'if' at line " + mte.line;
                        error.setErrorType(ErrorType.MISSING_IF);
                    }else if(tokenName.equals("END")){
                        message = "An 'end' is missing.";
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
                //NoViableAltException nvae = (NoViableAltException)e;
                // for development, can add "decision=<<"+nvae.grammarDecisionDescription+">>"
                // and "(decision="+nvae.decisionNumber+") and
                // "state "+nvae.stateNumber
                message = "no viable alternative at input " + getTokenErrorDisplay(re.token);
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
    		    else if(toke.getChannel() == this.DEFAULT_TOKEN_CHANNEL) {
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:262:1: start : ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ | ) class_declaration EOF ;
    public final QuorumParser.start_return start() throws RecognitionException {
        QuorumParser.start_return retval = new QuorumParser.start_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EOF8=null;
        QuorumParser.package_rule_return package_rule1 = null;

        QuorumParser.reference_return reference2 = null;

        QuorumParser.reference_return reference3 = null;

        QuorumParser.package_rule_return package_rule4 = null;

        QuorumParser.package_rule_return package_rule5 = null;

        QuorumParser.reference_return reference6 = null;

        QuorumParser.class_declaration_return class_declaration7 = null;


        CommonTree EOF8_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:262:7: ( ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ | ) class_declaration EOF )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:263:3: ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ | ) class_declaration EOF
            {
            root_0 = (CommonTree)adaptor.nil();

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:263:3: ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ | )
            int alt4=5;
            alt4 = dfa4.predict(input);
            switch (alt4) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:263:4: package_rule ( reference )+
                    {
                    pushFollow(FOLLOW_package_rule_in_start146);
                    package_rule1=package_rule();

                    state._fsp--;

                    adaptor.addChild(root_0, package_rule1.getTree());
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:263:17: ( reference )+
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
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:263:17: reference
                    	    {
                    	    pushFollow(FOLLOW_reference_in_start148);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:264:4: ( reference )+ package_rule
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:264:4: ( reference )+
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
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:264:4: reference
                    	    {
                    	    pushFollow(FOLLOW_reference_in_start155);
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

                    pushFollow(FOLLOW_package_rule_in_start158);
                    package_rule4=package_rule();

                    state._fsp--;

                    adaptor.addChild(root_0, package_rule4.getTree());

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:265:4: package_rule
                    {
                    pushFollow(FOLLOW_package_rule_in_start163);
                    package_rule5=package_rule();

                    state._fsp--;

                    adaptor.addChild(root_0, package_rule5.getTree());

                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:266:4: ( reference )+
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:266:4: ( reference )+
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
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:266:4: reference
                    	    {
                    	    pushFollow(FOLLOW_reference_in_start168);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:267:4: 
                    {
                    }
                    break;

            }

            pushFollow(FOLLOW_class_declaration_in_start177);
            class_declaration7=class_declaration();

            state._fsp--;

            adaptor.addChild(root_0, class_declaration7.getTree());
            EOF8=(Token)match(input,EOF,FOLLOW_EOF_in_start180); 
            EOF8_tree = (CommonTree)adaptor.create(EOF8);
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
        }
        return retval;
    }
    // $ANTLR end "start"

    public static class package_rule_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "package_rule"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:271:1: package_rule : PACKAGE_NAME qn= qualified_name ;
    public final QuorumParser.package_rule_return package_rule() throws RecognitionException {
        QuorumParser.package_rule_return retval = new QuorumParser.package_rule_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token PACKAGE_NAME9=null;
        QuorumParser.qualified_name_return qn = null;


        CommonTree PACKAGE_NAME9_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:271:14: ( PACKAGE_NAME qn= qualified_name )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:271:16: PACKAGE_NAME qn= qualified_name
            {
            root_0 = (CommonTree)adaptor.nil();

            PACKAGE_NAME9=(Token)match(input,PACKAGE_NAME,FOLLOW_PACKAGE_NAME_in_package_rule191); 
            PACKAGE_NAME9_tree = (CommonTree)adaptor.create(PACKAGE_NAME9);
            adaptor.addChild(root_0, PACKAGE_NAME9_tree);

            pushFollow(FOLLOW_qualified_name_in_package_rule195);
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
        }
        return retval;
    }
    // $ANTLR end "package_rule"

    public static class reference_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "reference"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:277:1: reference : USE qn= qualified_name ;
    public final QuorumParser.reference_return reference() throws RecognitionException {
        QuorumParser.reference_return retval = new QuorumParser.reference_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token USE10=null;
        QuorumParser.qualified_name_return qn = null;


        CommonTree USE10_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:278:2: ( USE qn= qualified_name )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:279:2: USE qn= qualified_name
            {
            root_0 = (CommonTree)adaptor.nil();

            USE10=(Token)match(input,USE,FOLLOW_USE_in_reference213); 
            USE10_tree = (CommonTree)adaptor.create(USE10);
            adaptor.addChild(root_0, USE10_tree);

            pushFollow(FOLLOW_qualified_name_in_reference219);
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:286:1: class_declaration : ( ( CLASS ID (genericList= generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END ) -> ^( CLASS ID ( generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END ) | no_class_stmnts );
    public final QuorumParser.class_declaration_return class_declaration() throws RecognitionException {
        class_declaration_stack.push(new class_declaration_scope());
        QuorumParser.class_declaration_return retval = new QuorumParser.class_declaration_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token CLASS11=null;
        Token ID12=null;
        Token END15=null;
        QuorumParser.generic_declaration_return genericList = null;

        QuorumParser.inherit_stmnts_return inherit_stmnts13 = null;

        QuorumParser.class_stmnts_return class_stmnts14 = null;

        QuorumParser.no_class_stmnts_return no_class_stmnts16 = null;


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
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:292:5: ( ( CLASS ID (genericList= generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END ) -> ^( CLASS ID ( generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END ) | no_class_stmnts )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==CLASS) ) {
                alt8=1;
            }
            else if ( (LA8_0==ID||(LA8_0>=PUBLIC && LA8_0<=ACTION)||(LA8_0>=BLUEPRINT && LA8_0<=ON_CREATE)||(LA8_0>=PARENT && LA8_0<=CHECK)||(LA8_0>=PRINT && LA8_0<=RETURN)||(LA8_0>=INTEGER_KEYWORD && LA8_0<=BOOLEAN_KEYWORD)||LA8_0==IF||LA8_0==REPEAT) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:293:2: ( CLASS ID (genericList= generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:293:2: ( CLASS ID (genericList= generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:294:2: CLASS ID (genericList= generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END
                    {
                    CLASS11=(Token)match(input,CLASS,FOLLOW_CLASS_in_class_declaration251);  
                    stream_CLASS.add(CLASS11);

                    ID12=(Token)match(input,ID,FOLLOW_ID_in_class_declaration253);  
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
                    	
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:312:13: (genericList= generic_declaration )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==LESS) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:312:13: genericList= generic_declaration
                            {
                            pushFollow(FOLLOW_generic_declaration_in_class_declaration264);
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
                    	
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:329:2: ( inherit_stmnts )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==INHERITS) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:329:2: inherit_stmnts
                            {
                            pushFollow(FOLLOW_inherit_stmnts_in_class_declaration271);
                            inherit_stmnts13=inherit_stmnts();

                            state._fsp--;

                            stream_inherit_stmnts.add(inherit_stmnts13.getTree());

                            }
                            break;

                    }

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:330:2: ( class_stmnts )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==ID||(LA7_0>=PUBLIC && LA7_0<=ACTION)||(LA7_0>=BLUEPRINT && LA7_0<=ON_CREATE)||(LA7_0>=PARENT && LA7_0<=ME)||(LA7_0>=INTEGER_KEYWORD && LA7_0<=BOOLEAN_KEYWORD)) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:330:2: class_stmnts
                    	    {
                    	    pushFollow(FOLLOW_class_stmnts_in_class_declaration282);
                    	    class_stmnts14=class_stmnts();

                    	    state._fsp--;

                    	    stream_class_stmnts.add(class_stmnts14.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    END15=(Token)match(input,END,FOLLOW_END_in_class_declaration286);  
                    stream_END.add(END15);


                    		ClassDescriptor currentClazz = symbol.getCurrentClass();
                    		currentClazz.checkClassVariableInitialization();
                    		((class_declaration_scope)class_declaration_stack.peek()).current_class.setLineEnd(END15.getLine());
                    		((class_declaration_scope)class_declaration_stack.peek()).current_class.setColumnEnd(END15.getCharPositionInLine());
                    	

                    }



                    // AST REWRITE
                    // elements: generic_declaration, CLASS, END, class_stmnts, ID, inherit_stmnts
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 338:4: -> ^( CLASS ID ( generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:338:7: ^( CLASS ID ( generic_declaration )? ( inherit_stmnts )? ( class_stmnts )* END )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_CLASS.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_ID.nextNode());
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:338:18: ( generic_declaration )?
                        if ( stream_generic_declaration.hasNext() ) {
                            adaptor.addChild(root_1, stream_generic_declaration.nextTree());

                        }
                        stream_generic_declaration.reset();
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:338:39: ( inherit_stmnts )?
                        if ( stream_inherit_stmnts.hasNext() ) {
                            adaptor.addChild(root_1, stream_inherit_stmnts.nextTree());

                        }
                        stream_inherit_stmnts.reset();
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:338:55: ( class_stmnts )*
                        while ( stream_class_stmnts.hasNext() ) {
                            adaptor.addChild(root_1, stream_class_stmnts.nextTree());

                        }
                        stream_class_stmnts.reset();
                        adaptor.addChild(root_1, stream_END.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:340:2: no_class_stmnts
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
                    	
                    pushFollow(FOLLOW_no_class_stmnts_in_class_declaration320);
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:352:1: no_class_stmnts : ( ( statement )+ | ( ( access_modifier )? method_declaration )+ );
    public final QuorumParser.no_class_stmnts_return no_class_stmnts() throws RecognitionException {
        no_class_stmnts_stack.push(new no_class_stmnts_scope());
        QuorumParser.no_class_stmnts_return retval = new QuorumParser.no_class_stmnts_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        QuorumParser.statement_return statement17 = null;

        QuorumParser.access_modifier_return access_modifier18 = null;

        QuorumParser.method_declaration_return method_declaration19 = null;



        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:356:2: ( ( statement )+ | ( ( access_modifier )? method_declaration )+ )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==ID||(LA12_0>=PARENT && LA12_0<=CHECK)||(LA12_0>=PRINT && LA12_0<=RETURN)||(LA12_0>=INTEGER_KEYWORD && LA12_0<=BOOLEAN_KEYWORD)||LA12_0==IF||LA12_0==REPEAT) ) {
                alt12=1;
            }
            else if ( ((LA12_0>=PUBLIC && LA12_0<=ACTION)||(LA12_0>=BLUEPRINT && LA12_0<=ON_CREATE)) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:357:2: ( statement )+
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
                    	
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:370:2: ( statement )+
                    int cnt9=0;
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==ID||(LA9_0>=PARENT && LA9_0<=CHECK)||(LA9_0>=PRINT && LA9_0<=RETURN)||(LA9_0>=INTEGER_KEYWORD && LA9_0<=BOOLEAN_KEYWORD)||LA9_0==IF||LA9_0==REPEAT) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:370:2: statement
                    	    {
                    	    pushFollow(FOLLOW_statement_in_no_class_stmnts339);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:374:3: ( ( access_modifier )? method_declaration )+
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:374:3: ( ( access_modifier )? method_declaration )+
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
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:374:4: ( access_modifier )? method_declaration
                    	    {
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:374:4: ( access_modifier )?
                    	    int alt10=2;
                    	    int LA10_0 = input.LA(1);

                    	    if ( ((LA10_0>=PUBLIC && LA10_0<=PRIVATE)) ) {
                    	        alt10=1;
                    	    }
                    	    switch (alt10) {
                    	        case 1 :
                    	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:374:4: access_modifier
                    	            {
                    	            pushFollow(FOLLOW_access_modifier_in_no_class_stmnts348);
                    	            access_modifier18=access_modifier();

                    	            state._fsp--;

                    	            adaptor.addChild(root_0, access_modifier18.getTree());

                    	            }
                    	            break;

                    	    }

                    	    pushFollow(FOLLOW_method_declaration_in_no_class_stmnts351);
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:376:1: inherit_stmnts : INHERITS qn= qualified_name (genericList= generic_statement )? ( COMMA qn= qualified_name (genericList= generic_statement )? )* -> ^( INHERITS ( qualified_name ( generic_statement )? )+ ) ;
    public final QuorumParser.inherit_stmnts_return inherit_stmnts() throws RecognitionException {
        QuorumParser.inherit_stmnts_return retval = new QuorumParser.inherit_stmnts_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token INHERITS20=null;
        Token COMMA21=null;
        QuorumParser.qualified_name_return qn = null;

        QuorumParser.generic_statement_return genericList = null;


        CommonTree INHERITS20_tree=null;
        CommonTree COMMA21_tree=null;
        RewriteRuleTokenStream stream_INHERITS=new RewriteRuleTokenStream(adaptor,"token INHERITS");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_qualified_name=new RewriteRuleSubtreeStream(adaptor,"rule qualified_name");
        RewriteRuleSubtreeStream stream_generic_statement=new RewriteRuleSubtreeStream(adaptor,"rule generic_statement");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:377:2: ( INHERITS qn= qualified_name (genericList= generic_statement )? ( COMMA qn= qualified_name (genericList= generic_statement )? )* -> ^( INHERITS ( qualified_name ( generic_statement )? )+ ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:377:4: INHERITS qn= qualified_name (genericList= generic_statement )? ( COMMA qn= qualified_name (genericList= generic_statement )? )*
            {
            INHERITS20=(Token)match(input,INHERITS,FOLLOW_INHERITS_in_inherit_stmnts364);  
            stream_INHERITS.add(INHERITS20);

            pushFollow(FOLLOW_qualified_name_in_inherit_stmnts368);
            qn=qualified_name();

            state._fsp--;

            stream_qualified_name.add(qn.getTree());
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:377:42: (genericList= generic_statement )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==LESS) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:377:42: genericList= generic_statement
                    {
                    pushFollow(FOLLOW_generic_statement_in_inherit_stmnts372);
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
            	
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:393:4: ( COMMA qn= qualified_name (genericList= generic_statement )? )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==COMMA) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:393:5: COMMA qn= qualified_name (genericList= generic_statement )?
            	    {
            	    COMMA21=(Token)match(input,COMMA,FOLLOW_COMMA_in_inherit_stmnts380);  
            	    stream_COMMA.add(COMMA21);

            	    pushFollow(FOLLOW_qualified_name_in_inherit_stmnts384);
            	    qn=qualified_name();

            	    state._fsp--;

            	    stream_qualified_name.add(qn.getTree());
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:393:40: (genericList= generic_statement )?
            	    int alt14=2;
            	    int LA14_0 = input.LA(1);

            	    if ( (LA14_0==LESS) ) {
            	        alt14=1;
            	    }
            	    switch (alt14) {
            	        case 1 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:393:40: genericList= generic_statement
            	            {
            	            pushFollow(FOLLOW_generic_statement_in_inherit_stmnts388);
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
            // elements: INHERITS, qualified_name, generic_statement
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 410:2: -> ^( INHERITS ( qualified_name ( generic_statement )? )+ )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:410:5: ^( INHERITS ( qualified_name ( generic_statement )? )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_INHERITS.nextNode(), root_1);

                if ( !(stream_qualified_name.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_qualified_name.hasNext() ) {
                    adaptor.addChild(root_1, stream_qualified_name.nextTree());
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:410:33: ( generic_statement )?
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:412:1: access_modifier returns [AccessModifierEnum amEnum] : ( PUBLIC | PRIVATE );
    public final QuorumParser.access_modifier_return access_modifier() throws RecognitionException {
        QuorumParser.access_modifier_return retval = new QuorumParser.access_modifier_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token PUBLIC22=null;
        Token PRIVATE23=null;

        CommonTree PUBLIC22_tree=null;
        CommonTree PRIVATE23_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:413:2: ( PUBLIC | PRIVATE )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:413:4: PUBLIC
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    PUBLIC22=(Token)match(input,PUBLIC,FOLLOW_PUBLIC_in_access_modifier425); 
                    PUBLIC22_tree = (CommonTree)adaptor.create(PUBLIC22);
                    adaptor.addChild(root_0, PUBLIC22_tree);


                    		retval.amEnum = retval.amEnum.PUBLIC;
                    	

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:417:4: PRIVATE
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    PRIVATE23=(Token)match(input,PRIVATE,FOLLOW_PRIVATE_in_access_modifier433); 
                    PRIVATE23_tree = (CommonTree)adaptor.create(PRIVATE23);
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
        }
        return retval;
    }
    // $ANTLR end "access_modifier"

    public static class class_stmnts_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "class_stmnts"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:422:1: class_stmnts : ( (modifier= access_modifier )? assignment_statement | (modifier= access_modifier )? method_declaration );
    public final QuorumParser.class_stmnts_return class_stmnts() throws RecognitionException {
        QuorumParser.class_stmnts_return retval = new QuorumParser.class_stmnts_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        QuorumParser.access_modifier_return modifier = null;

        QuorumParser.assignment_statement_return assignment_statement24 = null;

        QuorumParser.method_declaration_return method_declaration25 = null;



        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:423:2: ( (modifier= access_modifier )? assignment_statement | (modifier= access_modifier )? method_declaration )
            int alt19=2;
            switch ( input.LA(1) ) {
            case PUBLIC:
                {
                int LA19_1 = input.LA(2);

                if ( (LA19_1==ID||(LA19_1>=PARENT && LA19_1<=ME)||(LA19_1>=INTEGER_KEYWORD && LA19_1<=BOOLEAN_KEYWORD)) ) {
                    alt19=1;
                }
                else if ( (LA19_1==ACTION||(LA19_1>=BLUEPRINT && LA19_1<=ON_CREATE)) ) {
                    alt19=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 1, input);

                    throw nvae;
                }
                }
                break;
            case PRIVATE:
                {
                int LA19_2 = input.LA(2);

                if ( (LA19_2==ID||(LA19_2>=PARENT && LA19_2<=ME)||(LA19_2>=INTEGER_KEYWORD && LA19_2<=BOOLEAN_KEYWORD)) ) {
                    alt19=1;
                }
                else if ( (LA19_2==ACTION||(LA19_2>=BLUEPRINT && LA19_2<=ON_CREATE)) ) {
                    alt19=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 2, input);

                    throw nvae;
                }
                }
                break;
            case ID:
            case PARENT:
            case ME:
            case INTEGER_KEYWORD:
            case NUMBER_KEYWORD:
            case TEXT:
            case BOOLEAN_KEYWORD:
                {
                alt19=1;
                }
                break;
            case ACTION:
            case BLUEPRINT:
            case NATIVE:
            case ON_CREATE:
                {
                alt19=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:423:4: (modifier= access_modifier )? assignment_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:423:13: (modifier= access_modifier )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( ((LA17_0>=PUBLIC && LA17_0<=PRIVATE)) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:423:13: modifier= access_modifier
                            {
                            pushFollow(FOLLOW_access_modifier_in_class_stmnts451);
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
                    		isInClassAssignmentStatementScope = true;
                    	
                    pushFollow(FOLLOW_assignment_statement_in_class_stmnts458);
                    assignment_statement24=assignment_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, assignment_statement24.getTree());
                    isInClassAssignmentStatementScope = false;

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:433:4: (modifier= access_modifier )? method_declaration
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:433:13: (modifier= access_modifier )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( ((LA18_0>=PUBLIC && LA18_0<=PRIVATE)) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:433:13: modifier= access_modifier
                            {
                            pushFollow(FOLLOW_access_modifier_in_class_stmnts469);
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
                    	
                    pushFollow(FOLLOW_method_declaration_in_class_stmnts477);
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:443:1: method_declaration : ( ACTION ID ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )? ( RETURNS return_type= assignment_declaration )? block END -> ^( ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? block END ) | BLUEPRINT ACTION ID ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )? ( RETURNS return_type= assignment_declaration )? -> ^( BLUEPRINT ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? ) | NATIVE ACTION ID ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )? ( RETURNS return_type= assignment_declaration )? -> ^( NATIVE ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? ) | ON_CREATE block END -> ^( ON_CREATE block END ) );
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
        QuorumParser.assignment_declaration_return return_type = null;

        QuorumParser.formal_parameter_return formal_parameter29 = null;

        QuorumParser.formal_parameter_return formal_parameter31 = null;

        QuorumParser.block_return block34 = null;

        QuorumParser.formal_parameter_return formal_parameter40 = null;

        QuorumParser.formal_parameter_return formal_parameter42 = null;

        QuorumParser.formal_parameter_return formal_parameter49 = null;

        QuorumParser.formal_parameter_return formal_parameter51 = null;

        QuorumParser.block_return block55 = null;


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
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:454:2: ( ACTION ID ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )? ( RETURNS return_type= assignment_declaration )? block END -> ^( ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? block END ) | BLUEPRINT ACTION ID ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )? ( RETURNS return_type= assignment_declaration )? -> ^( BLUEPRINT ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? ) | NATIVE ACTION ID ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )? ( RETURNS return_type= assignment_declaration )? -> ^( NATIVE ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? ) | ON_CREATE block END -> ^( ON_CREATE block END ) )
            int alt32=4;
            switch ( input.LA(1) ) {
            case ACTION:
                {
                alt32=1;
                }
                break;
            case BLUEPRINT:
                {
                alt32=2;
                }
                break;
            case NATIVE:
                {
                alt32=3;
                }
                break;
            case ON_CREATE:
                {
                alt32=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }

            switch (alt32) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:454:4: ACTION ID ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )? ( RETURNS return_type= assignment_declaration )? block END
                    {
                    ACTION26=(Token)match(input,ACTION,FOLLOW_ACTION_in_method_declaration495);  
                    stream_ACTION.add(ACTION26);

                    ID27=(Token)match(input,ID,FOLLOW_ID_in_method_declaration497);  
                    stream_ID.add(ID27);


                    		((method_declaration_scope)method_declaration_stack.peek()).method = new MethodDescriptor();
                    		((method_declaration_scope)method_declaration_stack.peek()).method.setDocumentation(methodDocumentation);
                    		((method_declaration_scope)method_declaration_stack.peek()).method.setName((ID27!=null?ID27.getText():null));
                    		((method_declaration_scope)method_declaration_stack.peek()).method.setAccessModifier(accessModifier);
                    		((method_declaration_scope)method_declaration_stack.peek()).method.setLineBegin(ACTION26.getLine());
                    		((method_declaration_scope)method_declaration_stack.peek()).method.setColumnBegin(ACTION26.getCharPositionInLine());
                    		((method_declaration_scope)method_declaration_stack.peek()).params = new Vector<ParameterDescriptor>();		
                    	
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:465:2: ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==LEFT_PAREN) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:465:3: LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN
                            {
                            LEFT_PAREN28=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_method_declaration507);  
                            stream_LEFT_PAREN.add(LEFT_PAREN28);

                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:465:14: ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )?
                            int alt21=2;
                            int LA21_0 = input.LA(1);

                            if ( (LA21_0==ID||(LA21_0>=INTEGER_KEYWORD && LA21_0<=BOOLEAN_KEYWORD)) ) {
                                alt21=1;
                            }
                            switch (alt21) {
                                case 1 :
                                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:465:15: formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )*
                                    {
                                    pushFollow(FOLLOW_formal_parameter_in_method_declaration510);
                                    formal_parameter29=formal_parameter(((method_declaration_scope)method_declaration_stack.peek()).params);

                                    state._fsp--;

                                    stream_formal_parameter.add(formal_parameter29.getTree());
                                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:465:61: ( COMMA formal_parameter[$method_declaration::params] )*
                                    loop20:
                                    do {
                                        int alt20=2;
                                        int LA20_0 = input.LA(1);

                                        if ( (LA20_0==COMMA) ) {
                                            alt20=1;
                                        }


                                        switch (alt20) {
                                    	case 1 :
                                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:465:62: COMMA formal_parameter[$method_declaration::params]
                                    	    {
                                    	    COMMA30=(Token)match(input,COMMA,FOLLOW_COMMA_in_method_declaration514);  
                                    	    stream_COMMA.add(COMMA30);

                                    	    pushFollow(FOLLOW_formal_parameter_in_method_declaration516);
                                    	    formal_parameter31=formal_parameter(((method_declaration_scope)method_declaration_stack.peek()).params);

                                    	    state._fsp--;

                                    	    stream_formal_parameter.add(formal_parameter31.getTree());

                                    	    }
                                    	    break;

                                    	default :
                                    	    break loop20;
                                        }
                                    } while (true);


                                    }
                                    break;

                            }

                            RIGHT_PAREN32=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_method_declaration523);  
                            stream_RIGHT_PAREN.add(RIGHT_PAREN32);


                            }
                            break;

                    }

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:466:2: ( RETURNS return_type= assignment_declaration )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==RETURNS) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:466:3: RETURNS return_type= assignment_declaration
                            {
                            RETURNS33=(Token)match(input,RETURNS,FOLLOW_RETURNS_in_method_declaration529);  
                            stream_RETURNS.add(RETURNS33);

                            pushFollow(FOLLOW_assignment_declaration_in_method_declaration535);
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
                    		
                    	
                    pushFollow(FOLLOW_block_in_method_declaration545);
                    block34=block();

                    state._fsp--;

                    stream_block.add(block34.getTree());
                    END35=(Token)match(input,END,FOLLOW_END_in_method_declaration548);  
                    stream_END.add(END35);


                    		((method_declaration_scope)method_declaration_stack.peek()).method.setLineEnd(END35.getLine());
                    		((method_declaration_scope)method_declaration_stack.peek()).method.setColumnEnd(END35.getCharPositionInLine());
                    		if(classWithNoName) {
                    			//currentClass.setLineEnd(END35.getLine());
                    			//currentClass.setColumnEnd(END35.getCharPositionInLine() + (END35!=null?END35.getText():null).length());
                    		}
                    		symbol.popScope();
                    	


                    // AST REWRITE
                    // elements: block, formal_parameter, ACTION, ID, RETURNS, assignment_declaration, END
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 507:2: -> ^( ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? block END )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:507:5: ^( ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? block END )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_ACTION.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_ID.nextNode());
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:507:17: ( formal_parameter )*
                        while ( stream_formal_parameter.hasNext() ) {
                            adaptor.addChild(root_1, stream_formal_parameter.nextTree());

                        }
                        stream_formal_parameter.reset();
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:507:35: ( RETURNS assignment_declaration )?
                        if ( stream_RETURNS.hasNext()||stream_assignment_declaration.hasNext() ) {
                            adaptor.addChild(root_1, stream_RETURNS.nextNode());
                            adaptor.addChild(root_1, stream_assignment_declaration.nextTree());

                        }
                        stream_RETURNS.reset();
                        stream_assignment_declaration.reset();
                        adaptor.addChild(root_1, stream_block.nextTree());
                        adaptor.addChild(root_1, stream_END.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:508:4: BLUEPRINT ACTION ID ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )? ( RETURNS return_type= assignment_declaration )?
                    {
                    BLUEPRINT36=(Token)match(input,BLUEPRINT,FOLLOW_BLUEPRINT_in_method_declaration580);  
                    stream_BLUEPRINT.add(BLUEPRINT36);

                    ACTION37=(Token)match(input,ACTION,FOLLOW_ACTION_in_method_declaration582);  
                    stream_ACTION.add(ACTION37);

                    ID38=(Token)match(input,ID,FOLLOW_ID_in_method_declaration584);  
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
                    	
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:521:2: ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==LEFT_PAREN) ) {
                        alt26=1;
                    }
                    switch (alt26) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:521:3: LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN
                            {
                            LEFT_PAREN39=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_method_declaration594);  
                            stream_LEFT_PAREN.add(LEFT_PAREN39);

                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:521:14: ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )?
                            int alt25=2;
                            int LA25_0 = input.LA(1);

                            if ( (LA25_0==ID||(LA25_0>=INTEGER_KEYWORD && LA25_0<=BOOLEAN_KEYWORD)) ) {
                                alt25=1;
                            }
                            switch (alt25) {
                                case 1 :
                                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:521:15: formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )*
                                    {
                                    pushFollow(FOLLOW_formal_parameter_in_method_declaration597);
                                    formal_parameter40=formal_parameter(((method_declaration_scope)method_declaration_stack.peek()).params);

                                    state._fsp--;

                                    stream_formal_parameter.add(formal_parameter40.getTree());
                                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:521:61: ( COMMA formal_parameter[$method_declaration::params] )*
                                    loop24:
                                    do {
                                        int alt24=2;
                                        int LA24_0 = input.LA(1);

                                        if ( (LA24_0==COMMA) ) {
                                            alt24=1;
                                        }


                                        switch (alt24) {
                                    	case 1 :
                                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:521:62: COMMA formal_parameter[$method_declaration::params]
                                    	    {
                                    	    COMMA41=(Token)match(input,COMMA,FOLLOW_COMMA_in_method_declaration601);  
                                    	    stream_COMMA.add(COMMA41);

                                    	    pushFollow(FOLLOW_formal_parameter_in_method_declaration603);
                                    	    formal_parameter42=formal_parameter(((method_declaration_scope)method_declaration_stack.peek()).params);

                                    	    state._fsp--;

                                    	    stream_formal_parameter.add(formal_parameter42.getTree());

                                    	    }
                                    	    break;

                                    	default :
                                    	    break loop24;
                                        }
                                    } while (true);


                                    }
                                    break;

                            }

                            RIGHT_PAREN43=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_method_declaration610);  
                            stream_RIGHT_PAREN.add(RIGHT_PAREN43);


                            }
                            break;

                    }

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:522:2: ( RETURNS return_type= assignment_declaration )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==RETURNS) ) {
                        alt27=1;
                    }
                    switch (alt27) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:522:3: RETURNS return_type= assignment_declaration
                            {
                            RETURNS44=(Token)match(input,RETURNS,FOLLOW_RETURNS_in_method_declaration616);  
                            stream_RETURNS.add(RETURNS44);

                            pushFollow(FOLLOW_assignment_declaration_in_method_declaration622);
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
                    // elements: ACTION, assignment_declaration, RETURNS, BLUEPRINT, ID, formal_parameter
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 549:2: -> ^( BLUEPRINT ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:549:5: ^( BLUEPRINT ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_BLUEPRINT.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_ACTION.nextNode());
                        adaptor.addChild(root_1, stream_ID.nextNode());
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:549:27: ( formal_parameter )*
                        while ( stream_formal_parameter.hasNext() ) {
                            adaptor.addChild(root_1, stream_formal_parameter.nextTree());

                        }
                        stream_formal_parameter.reset();
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:549:45: ( RETURNS assignment_declaration )?
                        if ( stream_assignment_declaration.hasNext()||stream_RETURNS.hasNext() ) {
                            adaptor.addChild(root_1, stream_RETURNS.nextNode());
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:550:4: NATIVE ACTION ID ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )? ( RETURNS return_type= assignment_declaration )?
                    {
                    NATIVE45=(Token)match(input,NATIVE,FOLLOW_NATIVE_in_method_declaration654);  
                    stream_NATIVE.add(NATIVE45);

                    ACTION46=(Token)match(input,ACTION,FOLLOW_ACTION_in_method_declaration656);  
                    stream_ACTION.add(ACTION46);

                    ID47=(Token)match(input,ID,FOLLOW_ID_in_method_declaration658);  
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
                    	
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:563:2: ( LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN )?
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( (LA30_0==LEFT_PAREN) ) {
                        alt30=1;
                    }
                    switch (alt30) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:563:3: LEFT_PAREN ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )? RIGHT_PAREN
                            {
                            LEFT_PAREN48=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_method_declaration668);  
                            stream_LEFT_PAREN.add(LEFT_PAREN48);

                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:563:14: ( formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )* )?
                            int alt29=2;
                            int LA29_0 = input.LA(1);

                            if ( (LA29_0==ID||(LA29_0>=INTEGER_KEYWORD && LA29_0<=BOOLEAN_KEYWORD)) ) {
                                alt29=1;
                            }
                            switch (alt29) {
                                case 1 :
                                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:563:15: formal_parameter[$method_declaration::params] ( COMMA formal_parameter[$method_declaration::params] )*
                                    {
                                    pushFollow(FOLLOW_formal_parameter_in_method_declaration671);
                                    formal_parameter49=formal_parameter(((method_declaration_scope)method_declaration_stack.peek()).params);

                                    state._fsp--;

                                    stream_formal_parameter.add(formal_parameter49.getTree());
                                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:563:61: ( COMMA formal_parameter[$method_declaration::params] )*
                                    loop28:
                                    do {
                                        int alt28=2;
                                        int LA28_0 = input.LA(1);

                                        if ( (LA28_0==COMMA) ) {
                                            alt28=1;
                                        }


                                        switch (alt28) {
                                    	case 1 :
                                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:563:62: COMMA formal_parameter[$method_declaration::params]
                                    	    {
                                    	    COMMA50=(Token)match(input,COMMA,FOLLOW_COMMA_in_method_declaration675);  
                                    	    stream_COMMA.add(COMMA50);

                                    	    pushFollow(FOLLOW_formal_parameter_in_method_declaration677);
                                    	    formal_parameter51=formal_parameter(((method_declaration_scope)method_declaration_stack.peek()).params);

                                    	    state._fsp--;

                                    	    stream_formal_parameter.add(formal_parameter51.getTree());

                                    	    }
                                    	    break;

                                    	default :
                                    	    break loop28;
                                        }
                                    } while (true);


                                    }
                                    break;

                            }

                            RIGHT_PAREN52=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_method_declaration684);  
                            stream_RIGHT_PAREN.add(RIGHT_PAREN52);


                            }
                            break;

                    }

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:564:2: ( RETURNS return_type= assignment_declaration )?
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( (LA31_0==RETURNS) ) {
                        alt31=1;
                    }
                    switch (alt31) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:564:3: RETURNS return_type= assignment_declaration
                            {
                            RETURNS53=(Token)match(input,RETURNS,FOLLOW_RETURNS_in_method_declaration690);  
                            stream_RETURNS.add(RETURNS53);

                            pushFollow(FOLLOW_assignment_declaration_in_method_declaration696);
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
                    // elements: RETURNS, formal_parameter, ACTION, ID, assignment_declaration, NATIVE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 591:2: -> ^( NATIVE ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:591:5: ^( NATIVE ACTION ID ( formal_parameter )* ( RETURNS assignment_declaration )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_NATIVE.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_ACTION.nextNode());
                        adaptor.addChild(root_1, stream_ID.nextNode());
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:591:24: ( formal_parameter )*
                        while ( stream_formal_parameter.hasNext() ) {
                            adaptor.addChild(root_1, stream_formal_parameter.nextTree());

                        }
                        stream_formal_parameter.reset();
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:591:42: ( RETURNS assignment_declaration )?
                        if ( stream_RETURNS.hasNext()||stream_assignment_declaration.hasNext() ) {
                            adaptor.addChild(root_1, stream_RETURNS.nextNode());
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:592:4: ON_CREATE block END
                    {
                    ON_CREATE54=(Token)match(input,ON_CREATE,FOLLOW_ON_CREATE_in_method_declaration728);  
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
                    	
                    pushFollow(FOLLOW_block_in_method_declaration735);
                    block55=block();

                    state._fsp--;

                    stream_block.add(block55.getTree());
                    END56=(Token)match(input,END,FOLLOW_END_in_method_declaration737);  
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
                    // 623:2: -> ^( ON_CREATE block END )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:623:5: ^( ON_CREATE block END )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_ON_CREATE.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_block.nextTree());
                        adaptor.addChild(root_1, stream_END.nextNode());

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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:625:1: formal_parameter[Vector<ParameterDescriptor> params] : assignment_declaration ID -> ^( FPARAM assignment_declaration ID ) ;
    public final QuorumParser.formal_parameter_return formal_parameter(Vector<ParameterDescriptor> params) throws RecognitionException {
        QuorumParser.formal_parameter_return retval = new QuorumParser.formal_parameter_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ID58=null;
        QuorumParser.assignment_declaration_return assignment_declaration57 = null;


        CommonTree ID58_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_assignment_declaration=new RewriteRuleSubtreeStream(adaptor,"rule assignment_declaration");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:626:2: ( assignment_declaration ID -> ^( FPARAM assignment_declaration ID ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:626:4: assignment_declaration ID
            {
            pushFollow(FOLLOW_assignment_declaration_in_formal_parameter762);
            assignment_declaration57=assignment_declaration();

            state._fsp--;

            stream_assignment_declaration.add(assignment_declaration57.getTree());
            ID58=(Token)match(input,ID,FOLLOW_ID_in_formal_parameter764);  
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
            // 664:4: -> ^( FPARAM assignment_declaration ID )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:664:7: ^( FPARAM assignment_declaration ID )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(FPARAM, "FPARAM"), root_1);

                adaptor.addChild(root_1, stream_assignment_declaration.nextTree());
                adaptor.addChild(root_1, stream_ID.nextNode());

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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:667:1: qualified_name returns [QualifiedNameDescriptor type] : ids+= ID ( PERIOD ids+= ID )* -> ^( QUALIFIED_NAME ID ( PERIOD ID )* ) ;
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
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:668:2: (ids+= ID ( PERIOD ids+= ID )* -> ^( QUALIFIED_NAME ID ( PERIOD ID )* ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:668:4: ids+= ID ( PERIOD ids+= ID )*
            {
            ids=(Token)match(input,ID,FOLLOW_ID_in_qualified_name795);  
            stream_ID.add(ids);

            if (list_ids==null) list_ids=new ArrayList();
            list_ids.add(ids);

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:668:12: ( PERIOD ids+= ID )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==PERIOD) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:668:13: PERIOD ids+= ID
            	    {
            	    PERIOD59=(Token)match(input,PERIOD,FOLLOW_PERIOD_in_qualified_name798);  
            	    stream_PERIOD.add(PERIOD59);

            	    ids=(Token)match(input,ID,FOLLOW_ID_in_qualified_name802);  
            	    stream_ID.add(ids);

            	    if (list_ids==null) list_ids=new ArrayList();
            	    list_ids.add(ids);


            	    }
            	    break;

            	default :
            	    break loop33;
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
            // 691:3: -> ^( QUALIFIED_NAME ID ( PERIOD ID )* )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:691:6: ^( QUALIFIED_NAME ID ( PERIOD ID )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(QUALIFIED_NAME, "QUALIFIED_NAME"), root_1);

                adaptor.addChild(root_1, stream_ID.nextNode());
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:691:26: ( PERIOD ID )*
                while ( stream_ID.hasNext()||stream_PERIOD.hasNext() ) {
                    adaptor.addChild(root_1, stream_PERIOD.nextNode());
                    adaptor.addChild(root_1, stream_ID.nextNode());

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
        }
        return retval;
    }
    // $ANTLR end "qualified_name"

    public static class block_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "block"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:693:1: block : ( statement )* -> ^( STATEMENT_LIST ( statement )* ) ;
    public final QuorumParser.block_return block() throws RecognitionException {
        QuorumParser.block_return retval = new QuorumParser.block_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        QuorumParser.statement_return statement60 = null;


        RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:693:8: ( ( statement )* -> ^( STATEMENT_LIST ( statement )* ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:693:10: ( statement )*
            {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:693:10: ( statement )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==ID||(LA34_0>=PARENT && LA34_0<=CHECK)||(LA34_0>=PRINT && LA34_0<=RETURN)||(LA34_0>=INTEGER_KEYWORD && LA34_0<=BOOLEAN_KEYWORD)||LA34_0==IF||LA34_0==REPEAT) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:693:10: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block836);
            	    statement60=statement();

            	    state._fsp--;

            	    stream_statement.add(statement60.getTree());

            	    }
            	    break;

            	default :
            	    break loop34;
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
            // 694:3: -> ^( STATEMENT_LIST ( statement )* )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:694:6: ^( STATEMENT_LIST ( statement )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(STATEMENT_LIST, "STATEMENT_LIST"), root_1);

                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:694:23: ( statement )*
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
        }
        return retval;
    }
    // $ANTLR end "block"

    public static class statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:697:1: statement : ( solo_method_call | if_statement | assignment_statement | loop_statement | return_statement | print_statement | speak_statement | check_statement | alert_statement );
    public final QuorumParser.statement_return statement() throws RecognitionException {
        QuorumParser.statement_return retval = new QuorumParser.statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        QuorumParser.solo_method_call_return solo_method_call61 = null;

        QuorumParser.if_statement_return if_statement62 = null;

        QuorumParser.assignment_statement_return assignment_statement63 = null;

        QuorumParser.loop_statement_return loop_statement64 = null;

        QuorumParser.return_statement_return return_statement65 = null;

        QuorumParser.print_statement_return print_statement66 = null;

        QuorumParser.speak_statement_return speak_statement67 = null;

        QuorumParser.check_statement_return check_statement68 = null;

        QuorumParser.alert_statement_return alert_statement69 = null;



        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:697:10: ( solo_method_call | if_statement | assignment_statement | loop_statement | return_statement | print_statement | speak_statement | check_statement | alert_statement )
            int alt35=9;
            alt35 = dfa35.predict(input);
            switch (alt35) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:698:3: solo_method_call
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_solo_method_call_in_statement859);
                    solo_method_call61=solo_method_call();

                    state._fsp--;

                    adaptor.addChild(root_0, solo_method_call61.getTree());

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:699:4: if_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_if_statement_in_statement864);
                    if_statement62=if_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, if_statement62.getTree());

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:700:4: assignment_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_assignment_statement_in_statement869);
                    assignment_statement63=assignment_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, assignment_statement63.getTree());

                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:701:4: loop_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_loop_statement_in_statement874);
                    loop_statement64=loop_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, loop_statement64.getTree());

                    }
                    break;
                case 5 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:702:4: return_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_return_statement_in_statement879);
                    return_statement65=return_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, return_statement65.getTree());

                    }
                    break;
                case 6 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:703:4: print_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_print_statement_in_statement884);
                    print_statement66=print_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, print_statement66.getTree());

                    }
                    break;
                case 7 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:704:4: speak_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_speak_statement_in_statement889);
                    speak_statement67=speak_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, speak_statement67.getTree());

                    }
                    break;
                case 8 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:705:4: check_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_check_statement_in_statement894);
                    check_statement68=check_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, check_statement68.getTree());

                    }
                    break;
                case 9 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:706:4: alert_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_alert_statement_in_statement899);
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
        }
        return retval;
    }
    // $ANTLR end "statement"

    public static class solo_method_call_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "solo_method_call"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:709:1: solo_method_call : ( qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN -> ^( SOLO_FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN ) | PARENT COLON qualified_name COLON ID LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN -> ^( SOLO_FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN ) | ME COLON qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN -> ^( SOLO_FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN ) | LIBRARY_CALL LEFT_PAREN expression COMMA expression COMMA expression RIGHT_PAREN | CONNECT_TO LEFT_PAREN expression COMMA expression COMMA expression RIGHT_PAREN | SEND_TO LEFT_PAREN expression COMMA expression COMMA expression COMMA expression RIGHT_PAREN );
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
        Token LIBRARY_CALL98=null;
        Token LEFT_PAREN99=null;
        Token COMMA101=null;
        Token COMMA103=null;
        Token RIGHT_PAREN105=null;
        Token CONNECT_TO106=null;
        Token LEFT_PAREN107=null;
        Token COMMA109=null;
        Token COMMA111=null;
        Token RIGHT_PAREN113=null;
        Token SEND_TO114=null;
        Token LEFT_PAREN115=null;
        Token COMMA117=null;
        Token COMMA119=null;
        Token COMMA121=null;
        Token RIGHT_PAREN123=null;
        QuorumParser.qualified_name_return qualified_name70 = null;

        QuorumParser.expression_return expression74 = null;

        QuorumParser.expression_return expression76 = null;

        QuorumParser.qualified_name_return qualified_name80 = null;

        QuorumParser.expression_return expression84 = null;

        QuorumParser.expression_return expression86 = null;

        QuorumParser.qualified_name_return qualified_name90 = null;

        QuorumParser.expression_return expression94 = null;

        QuorumParser.expression_return expression96 = null;

        QuorumParser.expression_return expression100 = null;

        QuorumParser.expression_return expression102 = null;

        QuorumParser.expression_return expression104 = null;

        QuorumParser.expression_return expression108 = null;

        QuorumParser.expression_return expression110 = null;

        QuorumParser.expression_return expression112 = null;

        QuorumParser.expression_return expression116 = null;

        QuorumParser.expression_return expression118 = null;

        QuorumParser.expression_return expression120 = null;

        QuorumParser.expression_return expression122 = null;


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
        CommonTree LIBRARY_CALL98_tree=null;
        CommonTree LEFT_PAREN99_tree=null;
        CommonTree COMMA101_tree=null;
        CommonTree COMMA103_tree=null;
        CommonTree RIGHT_PAREN105_tree=null;
        CommonTree CONNECT_TO106_tree=null;
        CommonTree LEFT_PAREN107_tree=null;
        CommonTree COMMA109_tree=null;
        CommonTree COMMA111_tree=null;
        CommonTree RIGHT_PAREN113_tree=null;
        CommonTree SEND_TO114_tree=null;
        CommonTree LEFT_PAREN115_tree=null;
        CommonTree COMMA117_tree=null;
        CommonTree COMMA119_tree=null;
        CommonTree COMMA121_tree=null;
        CommonTree RIGHT_PAREN123_tree=null;
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
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:710:2: ( qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN -> ^( SOLO_FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN ) | PARENT COLON qualified_name COLON ID LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN -> ^( SOLO_FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN ) | ME COLON qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN -> ^( SOLO_FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN ) | LIBRARY_CALL LEFT_PAREN expression COMMA expression COMMA expression RIGHT_PAREN | CONNECT_TO LEFT_PAREN expression COMMA expression COMMA expression RIGHT_PAREN | SEND_TO LEFT_PAREN expression COMMA expression COMMA expression COMMA expression RIGHT_PAREN )
            int alt44=6;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt44=1;
                }
                break;
            case PARENT:
                {
                alt44=2;
                }
                break;
            case ME:
                {
                alt44=3;
                }
                break;
            case LIBRARY_CALL:
                {
                alt44=4;
                }
                break;
            case CONNECT_TO:
                {
                alt44=5;
                }
                break;
            case SEND_TO:
                {
                alt44=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;
            }

            switch (alt44) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:711:2: qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN
                    {
                    pushFollow(FOLLOW_qualified_name_in_solo_method_call912);
                    qualified_name70=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name70.getTree());
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:711:17: ( COLON ID )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==COLON) ) {
                        alt36=1;
                    }
                    switch (alt36) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:711:18: COLON ID
                            {
                            COLON71=(Token)match(input,COLON,FOLLOW_COLON_in_solo_method_call915);  
                            stream_COLON.add(COLON71);

                            ID72=(Token)match(input,ID,FOLLOW_ID_in_solo_method_call917);  
                            stream_ID.add(ID72);


                            }
                            break;

                    }

                    LEFT_PAREN73=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_solo_method_call921);  
                    stream_LEFT_PAREN.add(LEFT_PAREN73);

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:711:40: ( expression ( COMMA expression )* )?
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0==ID||LA38_0==LEFT_PAREN||(LA38_0>=PARENT && LA38_0<=ME)||LA38_0==MINUS||(LA38_0>=NOT && LA38_0<=INPUT)) ) {
                        alt38=1;
                    }
                    switch (alt38) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:711:41: expression ( COMMA expression )*
                            {
                            pushFollow(FOLLOW_expression_in_solo_method_call924);
                            expression74=expression();

                            state._fsp--;

                            stream_expression.add(expression74.getTree());
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:711:52: ( COMMA expression )*
                            loop37:
                            do {
                                int alt37=2;
                                int LA37_0 = input.LA(1);

                                if ( (LA37_0==COMMA) ) {
                                    alt37=1;
                                }


                                switch (alt37) {
                            	case 1 :
                            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:711:53: COMMA expression
                            	    {
                            	    COMMA75=(Token)match(input,COMMA,FOLLOW_COMMA_in_solo_method_call927);  
                            	    stream_COMMA.add(COMMA75);

                            	    pushFollow(FOLLOW_expression_in_solo_method_call929);
                            	    expression76=expression();

                            	    state._fsp--;

                            	    stream_expression.add(expression76.getTree());

                            	    }
                            	    break;

                            	default :
                            	    break loop37;
                                }
                            } while (true);


                            }
                            break;

                    }

                    RIGHT_PAREN77=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_solo_method_call935);  
                    stream_RIGHT_PAREN.add(RIGHT_PAREN77);



                    // AST REWRITE
                    // elements: COMMA, COLON, RIGHT_PAREN, LEFT_PAREN, ID, expression, expression, qualified_name
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 711:86: -> ^( SOLO_FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:712:4: ^( SOLO_FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SOLO_FUNCTION_CALL, "SOLO_FUNCTION_CALL"), root_1);

                        adaptor.addChild(root_1, stream_qualified_name.nextTree());
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:712:40: ( COLON ID )?
                        if ( stream_COLON.hasNext()||stream_ID.hasNext() ) {
                            adaptor.addChild(root_1, stream_COLON.nextNode());
                            adaptor.addChild(root_1, stream_ID.nextNode());

                        }
                        stream_COLON.reset();
                        stream_ID.reset();
                        adaptor.addChild(root_1, stream_LEFT_PAREN.nextNode());
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:712:63: ( expression ( COMMA expression )* )?
                        if ( stream_COMMA.hasNext()||stream_expression.hasNext()||stream_expression.hasNext() ) {
                            adaptor.addChild(root_1, stream_expression.nextTree());
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:712:75: ( COMMA expression )*
                            while ( stream_COMMA.hasNext()||stream_expression.hasNext() ) {
                                adaptor.addChild(root_1, stream_COMMA.nextNode());
                                adaptor.addChild(root_1, stream_expression.nextTree());

                            }
                            stream_COMMA.reset();
                            stream_expression.reset();

                        }
                        stream_COMMA.reset();
                        stream_expression.reset();
                        stream_expression.reset();
                        adaptor.addChild(root_1, stream_RIGHT_PAREN.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:713:4: PARENT COLON qualified_name COLON ID LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN
                    {
                    PARENT78=(Token)match(input,PARENT,FOLLOW_PARENT_in_solo_method_call974);  
                    stream_PARENT.add(PARENT78);

                    COLON79=(Token)match(input,COLON,FOLLOW_COLON_in_solo_method_call976);  
                    stream_COLON.add(COLON79);

                    pushFollow(FOLLOW_qualified_name_in_solo_method_call978);
                    qualified_name80=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name80.getTree());
                    COLON81=(Token)match(input,COLON,FOLLOW_COLON_in_solo_method_call980);  
                    stream_COLON.add(COLON81);

                    ID82=(Token)match(input,ID,FOLLOW_ID_in_solo_method_call982);  
                    stream_ID.add(ID82);

                    LEFT_PAREN83=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_solo_method_call984);  
                    stream_LEFT_PAREN.add(LEFT_PAREN83);

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:713:52: ( expression ( COMMA expression )* )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==ID||LA40_0==LEFT_PAREN||(LA40_0>=PARENT && LA40_0<=ME)||LA40_0==MINUS||(LA40_0>=NOT && LA40_0<=INPUT)) ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:713:53: expression ( COMMA expression )*
                            {
                            pushFollow(FOLLOW_expression_in_solo_method_call987);
                            expression84=expression();

                            state._fsp--;

                            stream_expression.add(expression84.getTree());
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:713:64: ( COMMA expression )*
                            loop39:
                            do {
                                int alt39=2;
                                int LA39_0 = input.LA(1);

                                if ( (LA39_0==COMMA) ) {
                                    alt39=1;
                                }


                                switch (alt39) {
                            	case 1 :
                            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:713:65: COMMA expression
                            	    {
                            	    COMMA85=(Token)match(input,COMMA,FOLLOW_COMMA_in_solo_method_call990);  
                            	    stream_COMMA.add(COMMA85);

                            	    pushFollow(FOLLOW_expression_in_solo_method_call992);
                            	    expression86=expression();

                            	    state._fsp--;

                            	    stream_expression.add(expression86.getTree());

                            	    }
                            	    break;

                            	default :
                            	    break loop39;
                                }
                            } while (true);


                            }
                            break;

                    }

                    RIGHT_PAREN87=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_solo_method_call998);  
                    stream_RIGHT_PAREN.add(RIGHT_PAREN87);



                    // AST REWRITE
                    // elements: ID, COMMA, RIGHT_PAREN, LEFT_PAREN, COLON, COLON, expression, expression, PARENT, qualified_name
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 713:98: -> ^( SOLO_FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:714:4: ^( SOLO_FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SOLO_FUNCTION_CALL_PARENT, "SOLO_FUNCTION_CALL_PARENT"), root_1);

                        adaptor.addChild(root_1, stream_PARENT.nextNode());
                        adaptor.addChild(root_1, stream_COLON.nextNode());
                        adaptor.addChild(root_1, stream_qualified_name.nextTree());
                        adaptor.addChild(root_1, stream_COLON.nextNode());
                        adaptor.addChild(root_1, stream_ID.nextNode());
                        adaptor.addChild(root_1, stream_LEFT_PAREN.nextNode());
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:714:80: ( expression ( COMMA expression )* )?
                        if ( stream_COMMA.hasNext()||stream_expression.hasNext()||stream_expression.hasNext() ) {
                            adaptor.addChild(root_1, stream_expression.nextTree());
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:714:92: ( COMMA expression )*
                            while ( stream_COMMA.hasNext()||stream_expression.hasNext() ) {
                                adaptor.addChild(root_1, stream_COMMA.nextNode());
                                adaptor.addChild(root_1, stream_expression.nextTree());

                            }
                            stream_COMMA.reset();
                            stream_expression.reset();

                        }
                        stream_COMMA.reset();
                        stream_expression.reset();
                        stream_expression.reset();
                        adaptor.addChild(root_1, stream_RIGHT_PAREN.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:715:4: ME COLON qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN
                    {
                    ME88=(Token)match(input,ME,FOLLOW_ME_in_solo_method_call1038);  
                    stream_ME.add(ME88);

                    COLON89=(Token)match(input,COLON,FOLLOW_COLON_in_solo_method_call1040);  
                    stream_COLON.add(COLON89);

                    pushFollow(FOLLOW_qualified_name_in_solo_method_call1042);
                    qualified_name90=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name90.getTree());
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:715:28: ( COLON ID )?
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==COLON) ) {
                        alt41=1;
                    }
                    switch (alt41) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:715:29: COLON ID
                            {
                            COLON91=(Token)match(input,COLON,FOLLOW_COLON_in_solo_method_call1045);  
                            stream_COLON.add(COLON91);

                            ID92=(Token)match(input,ID,FOLLOW_ID_in_solo_method_call1047);  
                            stream_ID.add(ID92);


                            }
                            break;

                    }

                    LEFT_PAREN93=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_solo_method_call1051);  
                    stream_LEFT_PAREN.add(LEFT_PAREN93);

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:715:51: ( expression ( COMMA expression )* )?
                    int alt43=2;
                    int LA43_0 = input.LA(1);

                    if ( (LA43_0==ID||LA43_0==LEFT_PAREN||(LA43_0>=PARENT && LA43_0<=ME)||LA43_0==MINUS||(LA43_0>=NOT && LA43_0<=INPUT)) ) {
                        alt43=1;
                    }
                    switch (alt43) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:715:52: expression ( COMMA expression )*
                            {
                            pushFollow(FOLLOW_expression_in_solo_method_call1054);
                            expression94=expression();

                            state._fsp--;

                            stream_expression.add(expression94.getTree());
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:715:63: ( COMMA expression )*
                            loop42:
                            do {
                                int alt42=2;
                                int LA42_0 = input.LA(1);

                                if ( (LA42_0==COMMA) ) {
                                    alt42=1;
                                }


                                switch (alt42) {
                            	case 1 :
                            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:715:64: COMMA expression
                            	    {
                            	    COMMA95=(Token)match(input,COMMA,FOLLOW_COMMA_in_solo_method_call1057);  
                            	    stream_COMMA.add(COMMA95);

                            	    pushFollow(FOLLOW_expression_in_solo_method_call1059);
                            	    expression96=expression();

                            	    state._fsp--;

                            	    stream_expression.add(expression96.getTree());

                            	    }
                            	    break;

                            	default :
                            	    break loop42;
                                }
                            } while (true);


                            }
                            break;

                    }

                    RIGHT_PAREN97=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_solo_method_call1065);  
                    stream_RIGHT_PAREN.add(RIGHT_PAREN97);



                    // AST REWRITE
                    // elements: COLON, expression, COMMA, expression, qualified_name, ID, RIGHT_PAREN, LEFT_PAREN, ME, COLON
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 715:97: -> ^( SOLO_FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:716:4: ^( SOLO_FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN ( expression ( COMMA expression )* )? RIGHT_PAREN )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SOLO_FUNCTION_CALL_THIS, "SOLO_FUNCTION_CALL_THIS"), root_1);

                        adaptor.addChild(root_1, stream_ME.nextNode());
                        adaptor.addChild(root_1, stream_COLON.nextNode());
                        adaptor.addChild(root_1, stream_qualified_name.nextTree());
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:716:54: ( COLON ID )?
                        if ( stream_ID.hasNext()||stream_COLON.hasNext() ) {
                            adaptor.addChild(root_1, stream_COLON.nextNode());
                            adaptor.addChild(root_1, stream_ID.nextNode());

                        }
                        stream_ID.reset();
                        stream_COLON.reset();
                        adaptor.addChild(root_1, stream_LEFT_PAREN.nextNode());
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:716:77: ( expression ( COMMA expression )* )?
                        if ( stream_expression.hasNext()||stream_COMMA.hasNext()||stream_expression.hasNext() ) {
                            adaptor.addChild(root_1, stream_expression.nextTree());
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:716:89: ( COMMA expression )*
                            while ( stream_COMMA.hasNext()||stream_expression.hasNext() ) {
                                adaptor.addChild(root_1, stream_COMMA.nextNode());
                                adaptor.addChild(root_1, stream_expression.nextTree());

                            }
                            stream_COMMA.reset();
                            stream_expression.reset();

                        }
                        stream_expression.reset();
                        stream_COMMA.reset();
                        stream_expression.reset();
                        adaptor.addChild(root_1, stream_RIGHT_PAREN.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:717:4: LIBRARY_CALL LEFT_PAREN expression COMMA expression COMMA expression RIGHT_PAREN
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    LIBRARY_CALL98=(Token)match(input,LIBRARY_CALL,FOLLOW_LIBRARY_CALL_in_solo_method_call1108); 
                    LIBRARY_CALL98_tree = (CommonTree)adaptor.create(LIBRARY_CALL98);
                    adaptor.addChild(root_0, LIBRARY_CALL98_tree);

                    LEFT_PAREN99=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_solo_method_call1110); 
                    LEFT_PAREN99_tree = (CommonTree)adaptor.create(LEFT_PAREN99);
                    adaptor.addChild(root_0, LEFT_PAREN99_tree);

                    pushFollow(FOLLOW_expression_in_solo_method_call1112);
                    expression100=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression100.getTree());
                    COMMA101=(Token)match(input,COMMA,FOLLOW_COMMA_in_solo_method_call1114); 
                    COMMA101_tree = (CommonTree)adaptor.create(COMMA101);
                    adaptor.addChild(root_0, COMMA101_tree);

                    pushFollow(FOLLOW_expression_in_solo_method_call1116);
                    expression102=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression102.getTree());
                    COMMA103=(Token)match(input,COMMA,FOLLOW_COMMA_in_solo_method_call1118); 
                    COMMA103_tree = (CommonTree)adaptor.create(COMMA103);
                    adaptor.addChild(root_0, COMMA103_tree);

                    pushFollow(FOLLOW_expression_in_solo_method_call1120);
                    expression104=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression104.getTree());
                    RIGHT_PAREN105=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_solo_method_call1122); 
                    RIGHT_PAREN105_tree = (CommonTree)adaptor.create(RIGHT_PAREN105);
                    adaptor.addChild(root_0, RIGHT_PAREN105_tree);


                    }
                    break;
                case 5 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:718:4: CONNECT_TO LEFT_PAREN expression COMMA expression COMMA expression RIGHT_PAREN
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    CONNECT_TO106=(Token)match(input,CONNECT_TO,FOLLOW_CONNECT_TO_in_solo_method_call1127); 
                    CONNECT_TO106_tree = (CommonTree)adaptor.create(CONNECT_TO106);
                    adaptor.addChild(root_0, CONNECT_TO106_tree);

                    LEFT_PAREN107=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_solo_method_call1129); 
                    LEFT_PAREN107_tree = (CommonTree)adaptor.create(LEFT_PAREN107);
                    adaptor.addChild(root_0, LEFT_PAREN107_tree);

                    pushFollow(FOLLOW_expression_in_solo_method_call1131);
                    expression108=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression108.getTree());
                    COMMA109=(Token)match(input,COMMA,FOLLOW_COMMA_in_solo_method_call1133); 
                    COMMA109_tree = (CommonTree)adaptor.create(COMMA109);
                    adaptor.addChild(root_0, COMMA109_tree);

                    pushFollow(FOLLOW_expression_in_solo_method_call1135);
                    expression110=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression110.getTree());
                    COMMA111=(Token)match(input,COMMA,FOLLOW_COMMA_in_solo_method_call1137); 
                    COMMA111_tree = (CommonTree)adaptor.create(COMMA111);
                    adaptor.addChild(root_0, COMMA111_tree);

                    pushFollow(FOLLOW_expression_in_solo_method_call1139);
                    expression112=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression112.getTree());
                    RIGHT_PAREN113=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_solo_method_call1141); 
                    RIGHT_PAREN113_tree = (CommonTree)adaptor.create(RIGHT_PAREN113);
                    adaptor.addChild(root_0, RIGHT_PAREN113_tree);


                    }
                    break;
                case 6 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:719:4: SEND_TO LEFT_PAREN expression COMMA expression COMMA expression COMMA expression RIGHT_PAREN
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    SEND_TO114=(Token)match(input,SEND_TO,FOLLOW_SEND_TO_in_solo_method_call1146); 
                    SEND_TO114_tree = (CommonTree)adaptor.create(SEND_TO114);
                    adaptor.addChild(root_0, SEND_TO114_tree);

                    LEFT_PAREN115=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_solo_method_call1148); 
                    LEFT_PAREN115_tree = (CommonTree)adaptor.create(LEFT_PAREN115);
                    adaptor.addChild(root_0, LEFT_PAREN115_tree);

                    pushFollow(FOLLOW_expression_in_solo_method_call1150);
                    expression116=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression116.getTree());
                    COMMA117=(Token)match(input,COMMA,FOLLOW_COMMA_in_solo_method_call1152); 
                    COMMA117_tree = (CommonTree)adaptor.create(COMMA117);
                    adaptor.addChild(root_0, COMMA117_tree);

                    pushFollow(FOLLOW_expression_in_solo_method_call1154);
                    expression118=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression118.getTree());
                    COMMA119=(Token)match(input,COMMA,FOLLOW_COMMA_in_solo_method_call1156); 
                    COMMA119_tree = (CommonTree)adaptor.create(COMMA119);
                    adaptor.addChild(root_0, COMMA119_tree);

                    pushFollow(FOLLOW_expression_in_solo_method_call1158);
                    expression120=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression120.getTree());
                    COMMA121=(Token)match(input,COMMA,FOLLOW_COMMA_in_solo_method_call1160); 
                    COMMA121_tree = (CommonTree)adaptor.create(COMMA121);
                    adaptor.addChild(root_0, COMMA121_tree);

                    pushFollow(FOLLOW_expression_in_solo_method_call1162);
                    expression122=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression122.getTree());
                    RIGHT_PAREN123=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_solo_method_call1164); 
                    RIGHT_PAREN123_tree = (CommonTree)adaptor.create(RIGHT_PAREN123);
                    adaptor.addChild(root_0, RIGHT_PAREN123_tree);


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
        }
        return retval;
    }
    // $ANTLR end "solo_method_call"

    public static class alert_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "alert_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:722:1: alert_statement : ALERT LEFT_PAREN expression RIGHT_PAREN -> ^( ALERT LEFT_PAREN expression RIGHT_PAREN ) ;
    public final QuorumParser.alert_statement_return alert_statement() throws RecognitionException {
        QuorumParser.alert_statement_return retval = new QuorumParser.alert_statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ALERT124=null;
        Token LEFT_PAREN125=null;
        Token RIGHT_PAREN127=null;
        QuorumParser.expression_return expression126 = null;


        CommonTree ALERT124_tree=null;
        CommonTree LEFT_PAREN125_tree=null;
        CommonTree RIGHT_PAREN127_tree=null;
        RewriteRuleTokenStream stream_LEFT_PAREN=new RewriteRuleTokenStream(adaptor,"token LEFT_PAREN");
        RewriteRuleTokenStream stream_RIGHT_PAREN=new RewriteRuleTokenStream(adaptor,"token RIGHT_PAREN");
        RewriteRuleTokenStream stream_ALERT=new RewriteRuleTokenStream(adaptor,"token ALERT");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:723:2: ( ALERT LEFT_PAREN expression RIGHT_PAREN -> ^( ALERT LEFT_PAREN expression RIGHT_PAREN ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:723:4: ALERT LEFT_PAREN expression RIGHT_PAREN
            {
            ALERT124=(Token)match(input,ALERT,FOLLOW_ALERT_in_alert_statement1177);  
            stream_ALERT.add(ALERT124);

            LEFT_PAREN125=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_alert_statement1179);  
            stream_LEFT_PAREN.add(LEFT_PAREN125);

            pushFollow(FOLLOW_expression_in_alert_statement1181);
            expression126=expression();

            state._fsp--;

            stream_expression.add(expression126.getTree());
            RIGHT_PAREN127=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_alert_statement1184);  
            stream_RIGHT_PAREN.add(RIGHT_PAREN127);



            // AST REWRITE
            // elements: RIGHT_PAREN, expression, LEFT_PAREN, ALERT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 724:2: -> ^( ALERT LEFT_PAREN expression RIGHT_PAREN )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:724:5: ^( ALERT LEFT_PAREN expression RIGHT_PAREN )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_ALERT.nextNode(), root_1);

                adaptor.addChild(root_1, stream_LEFT_PAREN.nextNode());
                adaptor.addChild(root_1, stream_expression.nextTree());
                adaptor.addChild(root_1, stream_RIGHT_PAREN.nextNode());

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
        }
        return retval;
    }
    // $ANTLR end "alert_statement"

    public static class check_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "check_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:727:1: check_statement : check_start= CHECK block check_end= END ( (detect_start= DETECT detect_parameter block detect_end= END )+ (always_start= ALWAYS block always_end= END )? | always_start_2= ALWAYS block always_end_2= END ) ;
    public final QuorumParser.check_statement_return check_statement() throws RecognitionException {
        QuorumParser.check_statement_return retval = new QuorumParser.check_statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token check_start=null;
        Token check_end=null;
        Token detect_start=null;
        Token detect_end=null;
        Token always_start=null;
        Token always_end=null;
        Token always_start_2=null;
        Token always_end_2=null;
        QuorumParser.block_return block128 = null;

        QuorumParser.detect_parameter_return detect_parameter129 = null;

        QuorumParser.block_return block130 = null;

        QuorumParser.block_return block131 = null;

        QuorumParser.block_return block132 = null;


        CommonTree check_start_tree=null;
        CommonTree check_end_tree=null;
        CommonTree detect_start_tree=null;
        CommonTree detect_end_tree=null;
        CommonTree always_start_tree=null;
        CommonTree always_end_tree=null;
        CommonTree always_start_2_tree=null;
        CommonTree always_end_2_tree=null;


        	BlockDescriptor block;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:731:2: (check_start= CHECK block check_end= END ( (detect_start= DETECT detect_parameter block detect_end= END )+ (always_start= ALWAYS block always_end= END )? | always_start_2= ALWAYS block always_end_2= END ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:731:6: check_start= CHECK block check_end= END ( (detect_start= DETECT detect_parameter block detect_end= END )+ (always_start= ALWAYS block always_end= END )? | always_start_2= ALWAYS block always_end_2= END )
            {
            root_0 = (CommonTree)adaptor.nil();

            check_start=(Token)match(input,CHECK,FOLLOW_CHECK_in_check_statement1218); 
            check_start_tree = (CommonTree)adaptor.create(check_start);
            adaptor.addChild(root_0, check_start_tree);

             block = new BlockDescriptor(); symbol.add(block); 
            pushFollow(FOLLOW_block_in_check_statement1221);
            block128=block();

            state._fsp--;

            adaptor.addChild(root_0, block128.getTree());
             symbol.popScope(); 
            check_end=(Token)match(input,END,FOLLOW_END_in_check_statement1226); 
            check_end_tree = (CommonTree)adaptor.create(check_end);
            adaptor.addChild(root_0, check_end_tree);


                   		//set the begin and end line column information in the block descriptors.
                   		block.setLineBegin(check_start.getLine());
                   		block.setLineEnd(check_end.getLine());
                   		block.setColumnBegin(check_start.getCharPositionInLine());
                   		block.setColumnEnd((check_end!=null?check_end.getText():null).length() + check_end.getCharPositionInLine());
            	
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:739:6: ( (detect_start= DETECT detect_parameter block detect_end= END )+ (always_start= ALWAYS block always_end= END )? | always_start_2= ALWAYS block always_end_2= END )
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==DETECT) ) {
                alt47=1;
            }
            else if ( (LA47_0==ALWAYS) ) {
                alt47=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;
            }
            switch (alt47) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:739:9: (detect_start= DETECT detect_parameter block detect_end= END )+ (always_start= ALWAYS block always_end= END )?
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:739:9: (detect_start= DETECT detect_parameter block detect_end= END )+
                    int cnt45=0;
                    loop45:
                    do {
                        int alt45=2;
                        int LA45_0 = input.LA(1);

                        if ( (LA45_0==DETECT) ) {
                            alt45=1;
                        }


                        switch (alt45) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:739:10: detect_start= DETECT detect_parameter block detect_end= END
                    	    {
                    	    detect_start=(Token)match(input,DETECT,FOLLOW_DETECT_in_check_statement1242); 
                    	    detect_start_tree = (CommonTree)adaptor.create(detect_start);
                    	    adaptor.addChild(root_0, detect_start_tree);

                    	    pushFollow(FOLLOW_detect_parameter_in_check_statement1244);
                    	    detect_parameter129=detect_parameter();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, detect_parameter129.getTree());
                    	     block = new BlockDescriptor(); symbol.add(block); 
                    	    pushFollow(FOLLOW_block_in_check_statement1247);
                    	    block130=block();

                    	    state._fsp--;

                    	    adaptor.addChild(root_0, block130.getTree());
                    	     symbol.popScope(); 
                    	    detect_end=(Token)match(input,END,FOLLOW_END_in_check_statement1252); 
                    	    detect_end_tree = (CommonTree)adaptor.create(detect_end);
                    	    adaptor.addChild(root_0, detect_end_tree);


                    	           		//set the begin and end line column information in the block descriptors.
                    	           		block.setLineBegin(detect_start.getLine());
                    	           		block.setLineEnd(detect_end.getLine());
                    	           		block.setColumnBegin(detect_start.getCharPositionInLine());
                    	           		block.setColumnEnd((detect_end!=null?detect_end.getText():null).length() + detect_end.getCharPositionInLine());
                    	    	

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt45 >= 1 ) break loop45;
                                EarlyExitException eee =
                                    new EarlyExitException(45, input);
                                throw eee;
                        }
                        cnt45++;
                    } while (true);

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:748:6: (always_start= ALWAYS block always_end= END )?
                    int alt46=2;
                    int LA46_0 = input.LA(1);

                    if ( (LA46_0==ALWAYS) ) {
                        alt46=1;
                    }
                    switch (alt46) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:748:7: always_start= ALWAYS block always_end= END
                            {
                            always_start=(Token)match(input,ALWAYS,FOLLOW_ALWAYS_in_check_statement1274); 
                            always_start_tree = (CommonTree)adaptor.create(always_start);
                            adaptor.addChild(root_0, always_start_tree);

                             block = new BlockDescriptor(); symbol.add(block); 
                            pushFollow(FOLLOW_block_in_check_statement1277);
                            block131=block();

                            state._fsp--;

                            adaptor.addChild(root_0, block131.getTree());
                             symbol.popScope(); 
                            always_end=(Token)match(input,END,FOLLOW_END_in_check_statement1282); 
                            always_end_tree = (CommonTree)adaptor.create(always_end);
                            adaptor.addChild(root_0, always_end_tree);


                                   		//set the begin and end line column information in the block descriptors.
                                   		block.setLineBegin(always_start.getLine());
                                   		block.setLineEnd(always_end.getLine());
                                   		block.setColumnBegin(always_start.getCharPositionInLine());
                                   		block.setColumnEnd((always_end!=null?always_end.getText():null).length() + always_end.getCharPositionInLine());
                            	

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:757:10: always_start_2= ALWAYS block always_end_2= END
                    {
                    always_start_2=(Token)match(input,ALWAYS,FOLLOW_ALWAYS_in_check_statement1307); 
                    always_start_2_tree = (CommonTree)adaptor.create(always_start_2);
                    adaptor.addChild(root_0, always_start_2_tree);

                     block = new BlockDescriptor(); symbol.add(block); 
                    pushFollow(FOLLOW_block_in_check_statement1310);
                    block132=block();

                    state._fsp--;

                    adaptor.addChild(root_0, block132.getTree());
                     symbol.popScope(); 
                    always_end_2=(Token)match(input,END,FOLLOW_END_in_check_statement1315); 
                    always_end_2_tree = (CommonTree)adaptor.create(always_end_2);
                    adaptor.addChild(root_0, always_end_2_tree);


                           		//set the begin and end line column information in the block descriptors.
                           		block.setLineBegin(always_start_2.getLine());
                           		block.setLineEnd(always_end_2.getLine());
                           		block.setColumnBegin(always_start_2.getCharPositionInLine());
                           		block.setColumnEnd((always_end_2!=null?always_end_2.getText():null).length() + always_end_2.getCharPositionInLine());
                    	

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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:768:1: detect_parameter returns [String name, ArrayList<ErrorTypeDescriptor> exceptionTypeList] : ID ( OF_TYPE qualified_name ( OR qualified_name )* )? -> ^( ID ( OF_TYPE qualified_name ( OR qualified_name )* )? ) ;
    public final QuorumParser.detect_parameter_return detect_parameter() throws RecognitionException {
        QuorumParser.detect_parameter_return retval = new QuorumParser.detect_parameter_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ID133=null;
        Token OF_TYPE134=null;
        Token OR136=null;
        QuorumParser.qualified_name_return qualified_name135 = null;

        QuorumParser.qualified_name_return qualified_name137 = null;


        CommonTree ID133_tree=null;
        CommonTree OF_TYPE134_tree=null;
        CommonTree OR136_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_OF_TYPE=new RewriteRuleTokenStream(adaptor,"token OF_TYPE");
        RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
        RewriteRuleSubtreeStream stream_qualified_name=new RewriteRuleSubtreeStream(adaptor,"rule qualified_name");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:769:2: ( ID ( OF_TYPE qualified_name ( OR qualified_name )* )? -> ^( ID ( OF_TYPE qualified_name ( OR qualified_name )* )? ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:769:5: ID ( OF_TYPE qualified_name ( OR qualified_name )* )?
            {
            ID133=(Token)match(input,ID,FOLLOW_ID_in_detect_parameter1351);  
            stream_ID.add(ID133);


            	VariableDescriptor new_desc = new VariableDescriptor();
            	TypeDescriptor type = new TypeDescriptor();
            	type.setName("Libraries.Language.Errors.Error");
            	
            	type.setLineBegin(ID133.getLine());
            	type.setColumnBegin(ID133.getCharPositionInLine());
            			
            	new_desc.setAccessModifier(AccessModifierEnum.PRIVATE);
            	           		
            	           		
            	new_desc.setType(type);
            	new_desc.setName((ID133!=null?ID133.getText():null));
                       		
            	CompilerError error = symbol.add(new_desc);
            	if(error != null) {
            		error.setLineNumber(ID133.getLine());
            		error.setColumn(ID133.getCharPositionInLine());
            		vm.getCompilerErrors().addError(error);
            	}	
            	
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:793:2: ( OF_TYPE qualified_name ( OR qualified_name )* )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==OF_TYPE) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:793:3: OF_TYPE qualified_name ( OR qualified_name )*
                    {
                    OF_TYPE134=(Token)match(input,OF_TYPE,FOLLOW_OF_TYPE_in_detect_parameter1363);  
                    stream_OF_TYPE.add(OF_TYPE134);

                    pushFollow(FOLLOW_qualified_name_in_detect_parameter1365);
                    qualified_name135=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name135.getTree());
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:793:25: ( OR qualified_name )*
                    loop48:
                    do {
                        int alt48=2;
                        int LA48_0 = input.LA(1);

                        if ( (LA48_0==OR) ) {
                            alt48=1;
                        }


                        switch (alt48) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:793:26: OR qualified_name
                    	    {
                    	    OR136=(Token)match(input,OR,FOLLOW_OR_in_detect_parameter1367);  
                    	    stream_OR.add(OR136);

                    	    pushFollow(FOLLOW_qualified_name_in_detect_parameter1369);
                    	    qualified_name137=qualified_name();

                    	    state._fsp--;

                    	    stream_qualified_name.add(qualified_name137.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop48;
                        }
                    } while (true);


                    }
                    break;

            }



            // AST REWRITE
            // elements: qualified_name, OR, ID, OF_TYPE, qualified_name
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 794:2: -> ^( ID ( OF_TYPE qualified_name ( OR qualified_name )* )? )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:794:5: ^( ID ( OF_TYPE qualified_name ( OR qualified_name )* )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_ID.nextNode(), root_1);

                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:794:10: ( OF_TYPE qualified_name ( OR qualified_name )* )?
                if ( stream_qualified_name.hasNext()||stream_OR.hasNext()||stream_OF_TYPE.hasNext()||stream_qualified_name.hasNext() ) {
                    adaptor.addChild(root_1, stream_OF_TYPE.nextNode());
                    adaptor.addChild(root_1, stream_qualified_name.nextTree());
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:794:33: ( OR qualified_name )*
                    while ( stream_OR.hasNext()||stream_qualified_name.hasNext() ) {
                        adaptor.addChild(root_1, stream_OR.nextNode());
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
        }
        return retval;
    }
    // $ANTLR end "detect_parameter"

    public static class print_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "print_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:796:1: print_statement : PRINT root_expression ;
    public final QuorumParser.print_statement_return print_statement() throws RecognitionException {
        QuorumParser.print_statement_return retval = new QuorumParser.print_statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token PRINT138=null;
        QuorumParser.root_expression_return root_expression139 = null;


        CommonTree PRINT138_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:797:2: ( PRINT root_expression )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:797:4: PRINT root_expression
            {
            root_0 = (CommonTree)adaptor.nil();

            PRINT138=(Token)match(input,PRINT,FOLLOW_PRINT_in_print_statement1404); 
            PRINT138_tree = (CommonTree)adaptor.create(PRINT138);
            adaptor.addChild(root_0, PRINT138_tree);

            pushFollow(FOLLOW_root_expression_in_print_statement1406);
            root_expression139=root_expression();

            state._fsp--;

            adaptor.addChild(root_0, root_expression139.getTree());

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
        }
        return retval;
    }
    // $ANTLR end "print_statement"

    public static class speak_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "speak_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:800:1: speak_statement : SAY root_expression ;
    public final QuorumParser.speak_statement_return speak_statement() throws RecognitionException {
        QuorumParser.speak_statement_return retval = new QuorumParser.speak_statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token SAY140=null;
        QuorumParser.root_expression_return root_expression141 = null;


        CommonTree SAY140_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:801:2: ( SAY root_expression )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:801:4: SAY root_expression
            {
            root_0 = (CommonTree)adaptor.nil();

            SAY140=(Token)match(input,SAY,FOLLOW_SAY_in_speak_statement1418); 
            SAY140_tree = (CommonTree)adaptor.create(SAY140);
            adaptor.addChild(root_0, SAY140_tree);

            pushFollow(FOLLOW_root_expression_in_speak_statement1420);
            root_expression141=root_expression();

            state._fsp--;

            adaptor.addChild(root_0, root_expression141.getTree());

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
        }
        return retval;
    }
    // $ANTLR end "speak_statement"

    public static class return_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "return_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:804:1: return_statement : RETURN ( root_expression | NOW ) ;
    public final QuorumParser.return_statement_return return_statement() throws RecognitionException {
        QuorumParser.return_statement_return retval = new QuorumParser.return_statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token RETURN142=null;
        Token NOW144=null;
        QuorumParser.root_expression_return root_expression143 = null;


        CommonTree RETURN142_tree=null;
        CommonTree NOW144_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:805:2: ( RETURN ( root_expression | NOW ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:805:4: RETURN ( root_expression | NOW )
            {
            root_0 = (CommonTree)adaptor.nil();

            RETURN142=(Token)match(input,RETURN,FOLLOW_RETURN_in_return_statement1431); 
            RETURN142_tree = (CommonTree)adaptor.create(RETURN142);
            adaptor.addChild(root_0, RETURN142_tree);

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:805:11: ( root_expression | NOW )
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==ID||LA50_0==LEFT_PAREN||(LA50_0>=PARENT && LA50_0<=ME)||LA50_0==MINUS||(LA50_0>=NOT && LA50_0<=INPUT)) ) {
                alt50=1;
            }
            else if ( (LA50_0==NOW) ) {
                alt50=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;
            }
            switch (alt50) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:805:13: root_expression
                    {
                    pushFollow(FOLLOW_root_expression_in_return_statement1435);
                    root_expression143=root_expression();

                    state._fsp--;

                    adaptor.addChild(root_0, root_expression143.getTree());

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:805:31: NOW
                    {
                    NOW144=(Token)match(input,NOW,FOLLOW_NOW_in_return_statement1439); 
                    NOW144_tree = (CommonTree)adaptor.create(NOW144);
                    adaptor.addChild(root_0, NOW144_tree);


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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:808:1: generic_declaration returns [ArrayList genericTypeList] : LESS ids+= ID ( COMMA ids+= ID )* GREATER -> ^( GENERIC LESS ID ( COMMA ID )* GREATER ) ;
    public final QuorumParser.generic_declaration_return generic_declaration() throws RecognitionException {
        QuorumParser.generic_declaration_return retval = new QuorumParser.generic_declaration_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token LESS145=null;
        Token COMMA146=null;
        Token GREATER147=null;
        Token ids=null;
        List list_ids=null;

        CommonTree LESS145_tree=null;
        CommonTree COMMA146_tree=null;
        CommonTree GREATER147_tree=null;
        CommonTree ids_tree=null;
        RewriteRuleTokenStream stream_GREATER=new RewriteRuleTokenStream(adaptor,"token GREATER");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_LESS=new RewriteRuleTokenStream(adaptor,"token LESS");

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:809:2: ( LESS ids+= ID ( COMMA ids+= ID )* GREATER -> ^( GENERIC LESS ID ( COMMA ID )* GREATER ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:809:4: LESS ids+= ID ( COMMA ids+= ID )* GREATER
            {
            LESS145=(Token)match(input,LESS,FOLLOW_LESS_in_generic_declaration1456);  
            stream_LESS.add(LESS145);

            ids=(Token)match(input,ID,FOLLOW_ID_in_generic_declaration1460);  
            stream_ID.add(ids);

            if (list_ids==null) list_ids=new ArrayList();
            list_ids.add(ids);

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:809:17: ( COMMA ids+= ID )*
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==COMMA) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:809:18: COMMA ids+= ID
            	    {
            	    COMMA146=(Token)match(input,COMMA,FOLLOW_COMMA_in_generic_declaration1463);  
            	    stream_COMMA.add(COMMA146);

            	    ids=(Token)match(input,ID,FOLLOW_ID_in_generic_declaration1467);  
            	    stream_ID.add(ids);

            	    if (list_ids==null) list_ids=new ArrayList();
            	    list_ids.add(ids);


            	    }
            	    break;

            	default :
            	    break loop51;
                }
            } while (true);

            GREATER147=(Token)match(input,GREATER,FOLLOW_GREATER_in_generic_declaration1471);  
            stream_GREATER.add(GREATER147);


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
            // elements: ID, GREATER, COMMA, ID, LESS
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 832:2: -> ^( GENERIC LESS ID ( COMMA ID )* GREATER )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:832:5: ^( GENERIC LESS ID ( COMMA ID )* GREATER )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(GENERIC, "GENERIC"), root_1);

                adaptor.addChild(root_1, stream_LESS.nextNode());
                adaptor.addChild(root_1, stream_ID.nextNode());
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:832:23: ( COMMA ID )*
                while ( stream_ID.hasNext()||stream_COMMA.hasNext() ) {
                    adaptor.addChild(root_1, stream_COMMA.nextNode());
                    adaptor.addChild(root_1, stream_ID.nextNode());

                }
                stream_ID.reset();
                stream_COMMA.reset();
                adaptor.addChild(root_1, stream_GREATER.nextNode());

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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:834:1: generic_statement returns [ArrayList genericTypeList] : LESS type= assignment_declaration ( COMMA type= assignment_declaration )* GREATER -> ^( GENERIC LESS assignment_declaration ( COMMA assignment_declaration )* GREATER ) ;
    public final QuorumParser.generic_statement_return generic_statement() throws RecognitionException {
        generic_statement_stack.push(new generic_statement_scope());
        QuorumParser.generic_statement_return retval = new QuorumParser.generic_statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token LESS148=null;
        Token COMMA149=null;
        Token GREATER150=null;
        QuorumParser.assignment_declaration_return type = null;


        CommonTree LESS148_tree=null;
        CommonTree COMMA149_tree=null;
        CommonTree GREATER150_tree=null;
        RewriteRuleTokenStream stream_GREATER=new RewriteRuleTokenStream(adaptor,"token GREATER");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_LESS=new RewriteRuleTokenStream(adaptor,"token LESS");
        RewriteRuleSubtreeStream stream_assignment_declaration=new RewriteRuleSubtreeStream(adaptor,"rule assignment_declaration");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:838:2: ( LESS type= assignment_declaration ( COMMA type= assignment_declaration )* GREATER -> ^( GENERIC LESS assignment_declaration ( COMMA assignment_declaration )* GREATER ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:838:4: LESS type= assignment_declaration ( COMMA type= assignment_declaration )* GREATER
            {
            LESS148=(Token)match(input,LESS,FOLLOW_LESS_in_generic_statement1512);  
            stream_LESS.add(LESS148);


            		((generic_statement_scope)generic_statement_stack.peek()).typeList = new ArrayList<TypeDescriptor>();
            	
            pushFollow(FOLLOW_assignment_declaration_in_generic_statement1522);
            type=assignment_declaration();

            state._fsp--;

            stream_assignment_declaration.add(type.getTree());

            		((generic_statement_scope)generic_statement_stack.peek()).typeList.add((type!=null?type.type:null));
            	
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:846:2: ( COMMA type= assignment_declaration )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==COMMA) ) {
                    alt52=1;
                }


                switch (alt52) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:846:3: COMMA type= assignment_declaration
            	    {
            	    COMMA149=(Token)match(input,COMMA,FOLLOW_COMMA_in_generic_statement1529);  
            	    stream_COMMA.add(COMMA149);

            	    pushFollow(FOLLOW_assignment_declaration_in_generic_statement1533);
            	    type=assignment_declaration();

            	    state._fsp--;

            	    stream_assignment_declaration.add(type.getTree());

            	    		((generic_statement_scope)generic_statement_stack.peek()).typeList.add((type!=null?type.type:null));
            	    	

            	    }
            	    break;

            	default :
            	    break loop52;
                }
            } while (true);

            GREATER150=(Token)match(input,GREATER,FOLLOW_GREATER_in_generic_statement1543);  
            stream_GREATER.add(GREATER150);


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
            // elements: assignment_declaration, GREATER, LESS, assignment_declaration, COMMA
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 876:2: -> ^( GENERIC LESS assignment_declaration ( COMMA assignment_declaration )* GREATER )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:876:5: ^( GENERIC LESS assignment_declaration ( COMMA assignment_declaration )* GREATER )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(GENERIC, "GENERIC"), root_1);

                adaptor.addChild(root_1, stream_LESS.nextNode());
                adaptor.addChild(root_1, stream_assignment_declaration.nextTree());
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:876:42: ( COMMA assignment_declaration )*
                while ( stream_assignment_declaration.hasNext()||stream_COMMA.hasNext() ) {
                    adaptor.addChild(root_1, stream_COMMA.nextNode());
                    adaptor.addChild(root_1, stream_assignment_declaration.nextTree());

                }
                stream_assignment_declaration.reset();
                stream_COMMA.reset();
                adaptor.addChild(root_1, stream_GREATER.nextNode());

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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:878:1: class_type returns [TypeDescriptor type] : qn= qualified_name ;
    public final QuorumParser.class_type_return class_type() throws RecognitionException {
        QuorumParser.class_type_return retval = new QuorumParser.class_type_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        QuorumParser.qualified_name_return qn = null;



        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:879:2: (qn= qualified_name )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:879:4: qn= qualified_name
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_qualified_name_in_class_type1583);
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
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:896:1: assignment_declaration returns [TypeDescriptor type] : (qn= qualified_name (gs= generic_statement )? | INTEGER_KEYWORD | NUMBER_KEYWORD | TEXT | BOOLEAN_KEYWORD );
    public final QuorumParser.assignment_declaration_return assignment_declaration() throws RecognitionException {
        QuorumParser.assignment_declaration_return retval = new QuorumParser.assignment_declaration_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token INTEGER_KEYWORD151=null;
        Token NUMBER_KEYWORD152=null;
        Token TEXT153=null;
        Token BOOLEAN_KEYWORD154=null;
        QuorumParser.qualified_name_return qn = null;

        QuorumParser.generic_statement_return gs = null;


        CommonTree INTEGER_KEYWORD151_tree=null;
        CommonTree NUMBER_KEYWORD152_tree=null;
        CommonTree TEXT153_tree=null;
        CommonTree BOOLEAN_KEYWORD154_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:897:2: (qn= qualified_name (gs= generic_statement )? | INTEGER_KEYWORD | NUMBER_KEYWORD | TEXT | BOOLEAN_KEYWORD )
            int alt54=5;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt54=1;
                }
                break;
            case INTEGER_KEYWORD:
                {
                alt54=2;
                }
                break;
            case NUMBER_KEYWORD:
                {
                alt54=3;
                }
                break;
            case TEXT:
                {
                alt54=4;
                }
                break;
            case BOOLEAN_KEYWORD:
                {
                alt54=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }

            switch (alt54) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:897:4: qn= qualified_name (gs= generic_statement )?
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_qualified_name_in_assignment_declaration1604);
                    qn=qualified_name();

                    state._fsp--;

                    adaptor.addChild(root_0, qn.getTree());
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:897:26: (gs= generic_statement )?
                    int alt53=2;
                    int LA53_0 = input.LA(1);

                    if ( (LA53_0==LESS) ) {
                        alt53=1;
                    }
                    switch (alt53) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:897:26: gs= generic_statement
                            {
                            pushFollow(FOLLOW_generic_statement_in_assignment_declaration1608);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:920:4: INTEGER_KEYWORD
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    INTEGER_KEYWORD151=(Token)match(input,INTEGER_KEYWORD,FOLLOW_INTEGER_KEYWORD_in_assignment_declaration1617); 
                    INTEGER_KEYWORD151_tree = (CommonTree)adaptor.create(INTEGER_KEYWORD151);
                    adaptor.addChild(root_0, INTEGER_KEYWORD151_tree);


                    		TypeDescriptor t = new TypeDescriptor();
                    		t.setName(TypeDescriptor.INTEGER);
                    		t.setLineBegin(INTEGER_KEYWORD151.getLine());
                    		t.setColumnBegin(INTEGER_KEYWORD151.getCharPositionInLine());
                    		retval.type = t;
                    	

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:928:4: NUMBER_KEYWORD
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    NUMBER_KEYWORD152=(Token)match(input,NUMBER_KEYWORD,FOLLOW_NUMBER_KEYWORD_in_assignment_declaration1625); 
                    NUMBER_KEYWORD152_tree = (CommonTree)adaptor.create(NUMBER_KEYWORD152);
                    adaptor.addChild(root_0, NUMBER_KEYWORD152_tree);


                    		TypeDescriptor t = new TypeDescriptor();
                    		t.setName(TypeDescriptor.NUMBER);
                    		t.setLineBegin(NUMBER_KEYWORD152.getLine());
                    		t.setColumnBegin(NUMBER_KEYWORD152.getCharPositionInLine());
                    		retval.type = t;
                    	

                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:936:4: TEXT
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    TEXT153=(Token)match(input,TEXT,FOLLOW_TEXT_in_assignment_declaration1633); 
                    TEXT153_tree = (CommonTree)adaptor.create(TEXT153);
                    adaptor.addChild(root_0, TEXT153_tree);


                    		TypeDescriptor t = new TypeDescriptor();
                    		t.setName(TypeDescriptor.TEXT);
                    		t.setLineBegin(TEXT153.getLine());
                    		t.setColumnBegin(TEXT153.getCharPositionInLine());
                    		retval.type = t;
                    	

                    }
                    break;
                case 5 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:944:4: BOOLEAN_KEYWORD
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    BOOLEAN_KEYWORD154=(Token)match(input,BOOLEAN_KEYWORD,FOLLOW_BOOLEAN_KEYWORD_in_assignment_declaration1641); 
                    BOOLEAN_KEYWORD154_tree = (CommonTree)adaptor.create(BOOLEAN_KEYWORD154);
                    adaptor.addChild(root_0, BOOLEAN_KEYWORD154_tree);


                    		TypeDescriptor t = new TypeDescriptor();
                    		t.setName(TypeDescriptor.BOOLEAN);
                    		t.setLineBegin(BOOLEAN_KEYWORD154.getLine());
                    		t.setColumnBegin(BOOLEAN_KEYWORD154.getCharPositionInLine());
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
        }
        return retval;
    }
    // $ANTLR end "assignment_declaration"

    public static class assignment_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "assignment_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:952:1: assignment_statement : ( (sel= selector COLON )? ID rhs= assign_right_hand_side | obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side | type= assignment_declaration name= ID (rhs= assign_right_hand_side )? );
    public final QuorumParser.assignment_statement_return assignment_statement() throws RecognitionException {
        QuorumParser.assignment_statement_return retval = new QuorumParser.assignment_statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token name=null;
        Token COLON155=null;
        Token ID156=null;
        Token COLON157=null;
        Token PARENT158=null;
        Token COLON159=null;
        Token COLON160=null;
        Token ID161=null;
        QuorumParser.selector_return sel = null;

        QuorumParser.assign_right_hand_side_return rhs = null;

        QuorumParser.qualified_name_return obj = null;

        QuorumParser.qualified_name_return parent = null;

        QuorumParser.assignment_declaration_return type = null;


        CommonTree name_tree=null;
        CommonTree COLON155_tree=null;
        CommonTree ID156_tree=null;
        CommonTree COLON157_tree=null;
        CommonTree PARENT158_tree=null;
        CommonTree COLON159_tree=null;
        CommonTree COLON160_tree=null;
        CommonTree ID161_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:953:2: ( (sel= selector COLON )? ID rhs= assign_right_hand_side | obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side | type= assignment_declaration name= ID (rhs= assign_right_hand_side )? )
            int alt58=3;
            alt58 = dfa58.predict(input);
            switch (alt58) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:954:3: (sel= selector COLON )? ID rhs= assign_right_hand_side
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:954:3: (sel= selector COLON )?
                    int alt55=2;
                    int LA55_0 = input.LA(1);

                    if ( ((LA55_0>=PARENT && LA55_0<=ME)) ) {
                        alt55=1;
                    }
                    switch (alt55) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:954:4: sel= selector COLON
                            {
                            pushFollow(FOLLOW_selector_in_assignment_statement1662);
                            sel=selector();

                            state._fsp--;

                            adaptor.addChild(root_0, sel.getTree());
                            COLON155=(Token)match(input,COLON,FOLLOW_COLON_in_assignment_statement1664); 
                            COLON155_tree = (CommonTree)adaptor.create(COLON155);
                            adaptor.addChild(root_0, COLON155_tree);


                            }
                            break;

                    }

                    ID156=(Token)match(input,ID,FOLLOW_ID_in_assignment_statement1668); 
                    ID156_tree = (CommonTree)adaptor.create(ID156);
                    adaptor.addChild(root_0, ID156_tree);

                    pushFollow(FOLLOW_assign_right_hand_side_in_assignment_statement1674);
                    rhs=assign_right_hand_side();

                    state._fsp--;

                    adaptor.addChild(root_0, rhs.getTree());

                    			String initMe = (ID156!=null?ID156.getText():null);
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:974:4: obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_qualified_name_in_assignment_statement1685);
                    obj=qualified_name();

                    state._fsp--;

                    adaptor.addChild(root_0, obj.getTree());
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:974:23: ( COLON PARENT COLON parent= qualified_name )?
                    int alt56=2;
                    int LA56_0 = input.LA(1);

                    if ( (LA56_0==COLON) ) {
                        int LA56_1 = input.LA(2);

                        if ( (LA56_1==PARENT) ) {
                            alt56=1;
                        }
                    }
                    switch (alt56) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:974:24: COLON PARENT COLON parent= qualified_name
                            {
                            COLON157=(Token)match(input,COLON,FOLLOW_COLON_in_assignment_statement1688); 
                            COLON157_tree = (CommonTree)adaptor.create(COLON157);
                            adaptor.addChild(root_0, COLON157_tree);

                            PARENT158=(Token)match(input,PARENT,FOLLOW_PARENT_in_assignment_statement1690); 
                            PARENT158_tree = (CommonTree)adaptor.create(PARENT158);
                            adaptor.addChild(root_0, PARENT158_tree);

                            COLON159=(Token)match(input,COLON,FOLLOW_COLON_in_assignment_statement1692); 
                            COLON159_tree = (CommonTree)adaptor.create(COLON159);
                            adaptor.addChild(root_0, COLON159_tree);

                            pushFollow(FOLLOW_qualified_name_in_assignment_statement1696);
                            parent=qualified_name();

                            state._fsp--;

                            adaptor.addChild(root_0, parent.getTree());

                            }
                            break;

                    }

                    COLON160=(Token)match(input,COLON,FOLLOW_COLON_in_assignment_statement1700); 
                    COLON160_tree = (CommonTree)adaptor.create(COLON160);
                    adaptor.addChild(root_0, COLON160_tree);

                    ID161=(Token)match(input,ID,FOLLOW_ID_in_assignment_statement1702); 
                    ID161_tree = (CommonTree)adaptor.create(ID161);
                    adaptor.addChild(root_0, ID161_tree);

                    pushFollow(FOLLOW_assign_right_hand_side_in_assignment_statement1706);
                    rhs=assign_right_hand_side();

                    state._fsp--;

                    adaptor.addChild(root_0, rhs.getTree());

                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:975:4: type= assignment_declaration name= ID (rhs= assign_right_hand_side )?
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_assignment_declaration_in_assignment_statement1715);
                    type=assignment_declaration();

                    state._fsp--;

                    adaptor.addChild(root_0, type.getTree());
                    name=(Token)match(input,ID,FOLLOW_ID_in_assignment_statement1721); 
                    name_tree = (CommonTree)adaptor.create(name);
                    adaptor.addChild(root_0, name_tree);

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:975:48: (rhs= assign_right_hand_side )?
                    int alt57=2;
                    int LA57_0 = input.LA(1);

                    if ( (LA57_0==EQUALITY) ) {
                        alt57=1;
                    }
                    switch (alt57) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:975:48: rhs= assign_right_hand_side
                            {
                            pushFollow(FOLLOW_assign_right_hand_side_in_assignment_statement1727);
                            rhs=assign_right_hand_side();

                            state._fsp--;

                            adaptor.addChild(root_0, rhs.getTree());

                            }
                            break;

                    }


                    			VariableDescriptor new_desc = new VariableDescriptor();
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
        }
        return retval;
    }
    // $ANTLR end "assignment_statement"

    public static class assign_right_hand_side_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "assign_right_hand_side"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1005:1: assign_right_hand_side : ( EQUALITY root_expression ) ;
    public final QuorumParser.assign_right_hand_side_return assign_right_hand_side() throws RecognitionException {
        QuorumParser.assign_right_hand_side_return retval = new QuorumParser.assign_right_hand_side_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EQUALITY162=null;
        QuorumParser.root_expression_return root_expression163 = null;


        CommonTree EQUALITY162_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1007:2: ( ( EQUALITY root_expression ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1008:3: ( EQUALITY root_expression )
            {
            root_0 = (CommonTree)adaptor.nil();

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1008:3: ( EQUALITY root_expression )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1008:4: EQUALITY root_expression
            {
            EQUALITY162=(Token)match(input,EQUALITY,FOLLOW_EQUALITY_in_assign_right_hand_side1753); 
            EQUALITY162_tree = (CommonTree)adaptor.create(EQUALITY162);
            adaptor.addChild(root_0, EQUALITY162_tree);

            pushFollow(FOLLOW_root_expression_in_assign_right_hand_side1755);
            root_expression163=root_expression();

            state._fsp--;

            adaptor.addChild(root_0, root_expression163.getTree());

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
        }
        return retval;
    }
    // $ANTLR end "assign_right_hand_side"

    public static class if_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "if_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1012:1: if_statement : firstif= IF root_expression THEN block firstend= END ( (firstelse= ELSE IF root_expression THEN block secondend= END ) )* ( (secondelse= ELSE THEN block thirdend= END ) )? ;
    public final QuorumParser.if_statement_return if_statement() throws RecognitionException {
        QuorumParser.if_statement_return retval = new QuorumParser.if_statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token firstif=null;
        Token firstend=null;
        Token firstelse=null;
        Token secondend=null;
        Token secondelse=null;
        Token thirdend=null;
        Token THEN165=null;
        Token IF167=null;
        Token THEN169=null;
        Token THEN171=null;
        QuorumParser.root_expression_return root_expression164 = null;

        QuorumParser.block_return block166 = null;

        QuorumParser.root_expression_return root_expression168 = null;

        QuorumParser.block_return block170 = null;

        QuorumParser.block_return block172 = null;


        CommonTree firstif_tree=null;
        CommonTree firstend_tree=null;
        CommonTree firstelse_tree=null;
        CommonTree secondend_tree=null;
        CommonTree secondelse_tree=null;
        CommonTree thirdend_tree=null;
        CommonTree THEN165_tree=null;
        CommonTree IF167_tree=null;
        CommonTree THEN169_tree=null;
        CommonTree THEN171_tree=null;


        	BlockDescriptor block;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1016:2: (firstif= IF root_expression THEN block firstend= END ( (firstelse= ELSE IF root_expression THEN block secondend= END ) )* ( (secondelse= ELSE THEN block thirdend= END ) )? )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1017:2: firstif= IF root_expression THEN block firstend= END ( (firstelse= ELSE IF root_expression THEN block secondend= END ) )* ( (secondelse= ELSE THEN block thirdend= END ) )?
            {
            root_0 = (CommonTree)adaptor.nil();

            firstif=(Token)match(input,IF,FOLLOW_IF_in_if_statement1780); 
            firstif_tree = (CommonTree)adaptor.create(firstif);
            adaptor.addChild(root_0, firstif_tree);

            pushFollow(FOLLOW_root_expression_in_if_statement1782);
            root_expression164=root_expression();

            state._fsp--;

            adaptor.addChild(root_0, root_expression164.getTree());
            THEN165=(Token)match(input,THEN,FOLLOW_THEN_in_if_statement1784); 
            THEN165_tree = (CommonTree)adaptor.create(THEN165);
            adaptor.addChild(root_0, THEN165_tree);

             block = new BlockDescriptor(); symbol.add(block); 
            pushFollow(FOLLOW_block_in_if_statement1788);
            block166=block();

            state._fsp--;

            adaptor.addChild(root_0, block166.getTree());
             symbol.popScope(); 
            firstend=(Token)match(input,END,FOLLOW_END_in_if_statement1796); 
            firstend_tree = (CommonTree)adaptor.create(firstend);
            adaptor.addChild(root_0, firstend_tree);


                   		//set the begin and end line column information in the block descriptors.
                   		block.setLineBegin(firstif.getLine());
                   		block.setLineEnd(firstend.getLine());
                   		block.setColumnBegin(firstif.getCharPositionInLine());
                   		block.setColumnEnd((firstend!=null?firstend.getText():null).length() + firstend.getCharPositionInLine());
            	
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1025:2: ( (firstelse= ELSE IF root_expression THEN block secondend= END ) )*
            loop59:
            do {
                int alt59=2;
                int LA59_0 = input.LA(1);

                if ( (LA59_0==ELSE) ) {
                    int LA59_1 = input.LA(2);

                    if ( (LA59_1==IF) ) {
                        alt59=1;
                    }


                }


                switch (alt59) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1025:3: (firstelse= ELSE IF root_expression THEN block secondend= END )
            	    {
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1025:3: (firstelse= ELSE IF root_expression THEN block secondend= END )
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1025:4: firstelse= ELSE IF root_expression THEN block secondend= END
            	    {
            	    firstelse=(Token)match(input,ELSE,FOLLOW_ELSE_in_if_statement1809); 
            	    firstelse_tree = (CommonTree)adaptor.create(firstelse);
            	    adaptor.addChild(root_0, firstelse_tree);

            	    IF167=(Token)match(input,IF,FOLLOW_IF_in_if_statement1811); 
            	    IF167_tree = (CommonTree)adaptor.create(IF167);
            	    adaptor.addChild(root_0, IF167_tree);

            	    pushFollow(FOLLOW_root_expression_in_if_statement1813);
            	    root_expression168=root_expression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, root_expression168.getTree());
            	    THEN169=(Token)match(input,THEN,FOLLOW_THEN_in_if_statement1815); 
            	    THEN169_tree = (CommonTree)adaptor.create(THEN169);
            	    adaptor.addChild(root_0, THEN169_tree);

            	     block = new BlockDescriptor(); symbol.add(block); 
            	    pushFollow(FOLLOW_block_in_if_statement1819);
            	    block170=block();

            	    state._fsp--;

            	    adaptor.addChild(root_0, block170.getTree());
            	     symbol.popScope(); 
            	    secondend=(Token)match(input,END,FOLLOW_END_in_if_statement1827); 
            	    secondend_tree = (CommonTree)adaptor.create(secondend);
            	    adaptor.addChild(root_0, secondend_tree);


            	           		//set the begin and end line column information in the block descriptors.
            	           		block.setLineBegin(firstelse.getLine());
            	           		block.setLineEnd(secondend.getLine());
            	           		block.setColumnBegin(firstelse.getCharPositionInLine());
            	           		block.setColumnEnd((secondend!=null?secondend.getText():null).length() + secondend.getCharPositionInLine());
            	    	

            	    }


            	    }
            	    break;

            	default :
            	    break loop59;
                }
            } while (true);

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1034:2: ( (secondelse= ELSE THEN block thirdend= END ) )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==ELSE) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1034:3: (secondelse= ELSE THEN block thirdend= END )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1034:3: (secondelse= ELSE THEN block thirdend= END )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1034:4: secondelse= ELSE THEN block thirdend= END
                    {
                    secondelse=(Token)match(input,ELSE,FOLLOW_ELSE_in_if_statement1847); 
                    secondelse_tree = (CommonTree)adaptor.create(secondelse);
                    adaptor.addChild(root_0, secondelse_tree);

                    THEN171=(Token)match(input,THEN,FOLLOW_THEN_in_if_statement1849); 
                    THEN171_tree = (CommonTree)adaptor.create(THEN171);
                    adaptor.addChild(root_0, THEN171_tree);

                     block = new BlockDescriptor(); symbol.add(block); 
                    pushFollow(FOLLOW_block_in_if_statement1853);
                    block172=block();

                    state._fsp--;

                    adaptor.addChild(root_0, block172.getTree());
                     symbol.popScope(); 
                    thirdend=(Token)match(input,END,FOLLOW_END_in_if_statement1861); 
                    thirdend_tree = (CommonTree)adaptor.create(thirdend);
                    adaptor.addChild(root_0, thirdend_tree);


                           		//set the begin and end line column information in the block descriptors.
                           		block.setLineBegin(secondelse.getLine());
                           		block.setLineEnd(thirdend.getLine());
                           		block.setColumnBegin(secondelse.getCharPositionInLine());
                           		block.setColumnEnd((thirdend!=null?thirdend.getText():null).length() + thirdend.getCharPositionInLine());
                    	

                    }


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
        }
        return retval;
    }
    // $ANTLR end "if_statement"

    public static class loop_statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "loop_statement"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1045:1: loop_statement : REPEAT ( ( OVER ID ) | ( ( FROM range ) ) | ( root_expression TIMES ) | ( ( WHILE | UNTIL ) root_expression ) ) block END ;
    public final QuorumParser.loop_statement_return loop_statement() throws RecognitionException {
        QuorumParser.loop_statement_return retval = new QuorumParser.loop_statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token REPEAT173=null;
        Token OVER174=null;
        Token ID175=null;
        Token FROM176=null;
        Token TIMES179=null;
        Token set180=null;
        Token END183=null;
        QuorumParser.range_return range177 = null;

        QuorumParser.root_expression_return root_expression178 = null;

        QuorumParser.root_expression_return root_expression181 = null;

        QuorumParser.block_return block182 = null;


        CommonTree REPEAT173_tree=null;
        CommonTree OVER174_tree=null;
        CommonTree ID175_tree=null;
        CommonTree FROM176_tree=null;
        CommonTree TIMES179_tree=null;
        CommonTree set180_tree=null;
        CommonTree END183_tree=null;


        	BlockDescriptor block;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1049:2: ( REPEAT ( ( OVER ID ) | ( ( FROM range ) ) | ( root_expression TIMES ) | ( ( WHILE | UNTIL ) root_expression ) ) block END )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1050:2: REPEAT ( ( OVER ID ) | ( ( FROM range ) ) | ( root_expression TIMES ) | ( ( WHILE | UNTIL ) root_expression ) ) block END
            {
            root_0 = (CommonTree)adaptor.nil();


            		block = new BlockDescriptor();
            		symbol.add(block);
            	
            REPEAT173=(Token)match(input,REPEAT,FOLLOW_REPEAT_in_loop_statement1893); 
            REPEAT173_tree = (CommonTree)adaptor.create(REPEAT173);
            adaptor.addChild(root_0, REPEAT173_tree);

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1054:10: ( ( OVER ID ) | ( ( FROM range ) ) | ( root_expression TIMES ) | ( ( WHILE | UNTIL ) root_expression ) )
            int alt61=4;
            switch ( input.LA(1) ) {
            case OVER:
                {
                alt61=1;
                }
                break;
            case FROM:
                {
                alt61=2;
                }
                break;
            case ID:
            case LEFT_PAREN:
            case PARENT:
            case ME:
            case MINUS:
            case NOT:
            case CAST:
            case INT:
            case BOOLEAN:
            case DECIMAL:
            case STRING:
            case NULL:
            case INPUT:
                {
                alt61=3;
                }
                break;
            case WHILE:
            case UNTIL:
                {
                alt61=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;
            }

            switch (alt61) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1054:12: ( OVER ID )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1054:12: ( OVER ID )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1054:13: OVER ID
                    {
                    OVER174=(Token)match(input,OVER,FOLLOW_OVER_in_loop_statement1898); 
                    OVER174_tree = (CommonTree)adaptor.create(OVER174);
                    adaptor.addChild(root_0, OVER174_tree);

                    ID175=(Token)match(input,ID,FOLLOW_ID_in_loop_statement1900); 
                    ID175_tree = (CommonTree)adaptor.create(ID175);
                    adaptor.addChild(root_0, ID175_tree);


                    }


                    				VariableParameterCommonDescriptor desc = symbol.getVariable((ID175!=null?ID175.getText():null));
                    				if(desc == null)
                    				{
                    					CompilerError error = new CompilerError();
                    					error.setError("Variable " + (ID175!=null?ID175.getText():null) + " not defined.");
                    					error.setErrorType(ErrorType.MISSING_VARIABLE);
                    					error.setLineNumber((ID175!=null?ID175.getLine():0));
                    					error.setColumn(ID175.getCharPositionInLine());
                    					error.setFile(getGrammarFileNameNoExtension());
                    					vm.getCompilerErrors().addError(error);
                    				} 
                    				
                    			

                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1069:5: ( ( FROM range ) )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1069:5: ( ( FROM range ) )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1069:6: ( FROM range )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1069:6: ( FROM range )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1069:7: FROM range
                    {
                    FROM176=(Token)match(input,FROM,FOLLOW_FROM_in_loop_statement1914); 
                    FROM176_tree = (CommonTree)adaptor.create(FROM176);
                    adaptor.addChild(root_0, FROM176_tree);

                    pushFollow(FOLLOW_range_in_loop_statement1916);
                    range177=range();

                    state._fsp--;

                    adaptor.addChild(root_0, range177.getTree());

                    }


                    }


                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1070:5: ( root_expression TIMES )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1070:5: ( root_expression TIMES )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1070:6: root_expression TIMES
                    {
                    pushFollow(FOLLOW_root_expression_in_loop_statement1925);
                    root_expression178=root_expression();

                    state._fsp--;

                    adaptor.addChild(root_0, root_expression178.getTree());
                    TIMES179=(Token)match(input,TIMES,FOLLOW_TIMES_in_loop_statement1927); 
                    TIMES179_tree = (CommonTree)adaptor.create(TIMES179);
                    adaptor.addChild(root_0, TIMES179_tree);


                    }


                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1071:5: ( ( WHILE | UNTIL ) root_expression )
                    {
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1071:5: ( ( WHILE | UNTIL ) root_expression )
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1071:6: ( WHILE | UNTIL ) root_expression
                    {
                    set180=(Token)input.LT(1);
                    if ( (input.LA(1)>=WHILE && input.LA(1)<=UNTIL) ) {
                        input.consume();
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(set180));
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    pushFollow(FOLLOW_root_expression_in_loop_statement1943);
                    root_expression181=root_expression();

                    state._fsp--;

                    adaptor.addChild(root_0, root_expression181.getTree());

                    }


                    }
                    break;

            }

            pushFollow(FOLLOW_block_in_loop_statement1948);
            block182=block();

            state._fsp--;

            adaptor.addChild(root_0, block182.getTree());
            END183=(Token)match(input,END,FOLLOW_END_in_loop_statement1950); 
            END183_tree = (CommonTree)adaptor.create(END183);
            adaptor.addChild(root_0, END183_tree);


                   			//set the begin and end line column information in the block descriptors.
                   			block.setLineBegin(REPEAT173.getLine());
                   			block.setLineEnd(END183.getLine());
                   			block.setColumnBegin(REPEAT173.getCharPositionInLine());
                   			block.setColumnEnd((END183!=null?END183.getText():null).length() + END183.getCharPositionInLine());
                   		

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
        }
        return retval;
    }
    // $ANTLR end "loop_statement"

    public static class range_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "range"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1084:1: range : ( root_expression ) TO ( root_expression ) -> ^( TO root_expression root_expression ) ;
    public final QuorumParser.range_return range() throws RecognitionException {
        QuorumParser.range_return retval = new QuorumParser.range_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token TO185=null;
        QuorumParser.root_expression_return root_expression184 = null;

        QuorumParser.root_expression_return root_expression186 = null;


        CommonTree TO185_tree=null;
        RewriteRuleTokenStream stream_TO=new RewriteRuleTokenStream(adaptor,"token TO");
        RewriteRuleSubtreeStream stream_root_expression=new RewriteRuleSubtreeStream(adaptor,"rule root_expression");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1084:7: ( ( root_expression ) TO ( root_expression ) -> ^( TO root_expression root_expression ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1084:9: ( root_expression ) TO ( root_expression )
            {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1084:9: ( root_expression )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1084:10: root_expression
            {
            pushFollow(FOLLOW_root_expression_in_range1969);
            root_expression184=root_expression();

            state._fsp--;

            stream_root_expression.add(root_expression184.getTree());

            }

            TO185=(Token)match(input,TO,FOLLOW_TO_in_range1972);  
            stream_TO.add(TO185);

            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1084:30: ( root_expression )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1084:31: root_expression
            {
            pushFollow(FOLLOW_root_expression_in_range1975);
            root_expression186=root_expression();

            state._fsp--;

            stream_root_expression.add(root_expression186.getTree());

            }



            // AST REWRITE
            // elements: root_expression, TO, root_expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 1084:48: -> ^( TO root_expression root_expression )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1084:51: ^( TO root_expression root_expression )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_TO.nextNode(), root_1);

                adaptor.addChild(root_1, stream_root_expression.nextTree());
                adaptor.addChild(root_1, stream_root_expression.nextTree());

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
        }
        return retval;
    }
    // $ANTLR end "range"

    public static class selector_return extends ParserRuleReturnScope {
        public ScopeSelector scopeSel;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "selector"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1086:1: selector returns [ScopeSelector scopeSel] : ( PARENT COLON qn= qualified_name -> ^( PARENT qualified_name ) | ME );
    public final QuorumParser.selector_return selector() throws RecognitionException {
        QuorumParser.selector_return retval = new QuorumParser.selector_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token PARENT187=null;
        Token COLON188=null;
        Token ME189=null;
        QuorumParser.qualified_name_return qn = null;


        CommonTree PARENT187_tree=null;
        CommonTree COLON188_tree=null;
        CommonTree ME189_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_PARENT=new RewriteRuleTokenStream(adaptor,"token PARENT");
        RewriteRuleSubtreeStream stream_qualified_name=new RewriteRuleSubtreeStream(adaptor,"rule qualified_name");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1087:2: ( PARENT COLON qn= qualified_name -> ^( PARENT qualified_name ) | ME )
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1087:4: PARENT COLON qn= qualified_name
                    {
                    PARENT187=(Token)match(input,PARENT,FOLLOW_PARENT_in_selector1998);  
                    stream_PARENT.add(PARENT187);

                    COLON188=(Token)match(input,COLON,FOLLOW_COLON_in_selector2000);  
                    stream_COLON.add(COLON188);

                    pushFollow(FOLLOW_qualified_name_in_selector2004);
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
                    // 1092:4: -> ^( PARENT qualified_name )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1092:7: ^( PARENT qualified_name )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_PARENT.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_qualified_name.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1094:4: ME
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    ME189=(Token)match(input,ME,FOLLOW_ME_in_selector2022); 
                    ME189_tree = (CommonTree)adaptor.create(ME189);
                    adaptor.addChild(root_0, ME189_tree);


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
        }
        return retval;
    }
    // $ANTLR end "selector"

    public static class root_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "root_expression"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1102:1: root_expression : expression -> ^( ROOT_EXPRESSION expression ) ;
    public final QuorumParser.root_expression_return root_expression() throws RecognitionException {
        QuorumParser.root_expression_return retval = new QuorumParser.root_expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        QuorumParser.expression_return expression190 = null;


        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1103:2: ( expression -> ^( ROOT_EXPRESSION expression ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1103:4: expression
            {
            pushFollow(FOLLOW_expression_in_root_expression2036);
            expression190=expression();

            state._fsp--;

            stream_expression.add(expression190.getTree());


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
            // 1103:15: -> ^( ROOT_EXPRESSION expression )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1103:18: ^( ROOT_EXPRESSION expression )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ROOT_EXPRESSION, "ROOT_EXPRESSION"), root_1);

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
        }
        return retval;
    }
    // $ANTLR end "root_expression"

    public static class expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expression"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1106:1: expression : or ;
    public final QuorumParser.expression_return expression() throws RecognitionException {
        QuorumParser.expression_return retval = new QuorumParser.expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        QuorumParser.or_return or191 = null;



        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1107:2: ( or )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1107:4: or
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_or_in_expression2056);
            or191=or();

            state._fsp--;

            adaptor.addChild(root_0, or191.getTree());

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
        }
        return retval;
    }
    // $ANTLR end "expression"

    public static class or_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "or"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1110:1: or : and ( OR and )* ;
    public final QuorumParser.or_return or() throws RecognitionException {
        QuorumParser.or_return retval = new QuorumParser.or_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token OR193=null;
        QuorumParser.and_return and192 = null;

        QuorumParser.and_return and194 = null;


        CommonTree OR193_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1110:5: ( and ( OR and )* )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1110:7: and ( OR and )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_and_in_or2068);
            and192=and();

            state._fsp--;

            adaptor.addChild(root_0, and192.getTree());
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1110:11: ( OR and )*
            loop63:
            do {
                int alt63=2;
                int LA63_0 = input.LA(1);

                if ( (LA63_0==OR) ) {
                    alt63=1;
                }


                switch (alt63) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1110:12: OR and
            	    {
            	    OR193=(Token)match(input,OR,FOLLOW_OR_in_or2071); 
            	    OR193_tree = (CommonTree)adaptor.create(OR193);
            	    root_0 = (CommonTree)adaptor.becomeRoot(OR193_tree, root_0);

            	    pushFollow(FOLLOW_and_in_or2075);
            	    and194=and();

            	    state._fsp--;

            	    adaptor.addChild(root_0, and194.getTree());

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
        }
        return retval;
    }
    // $ANTLR end "or"

    public static class and_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "and"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1113:1: and : equality ( AND equality )* ;
    public final QuorumParser.and_return and() throws RecognitionException {
        QuorumParser.and_return retval = new QuorumParser.and_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token AND196=null;
        QuorumParser.equality_return equality195 = null;

        QuorumParser.equality_return equality197 = null;


        CommonTree AND196_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1113:6: ( equality ( AND equality )* )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1113:8: equality ( AND equality )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_equality_in_and2088);
            equality195=equality();

            state._fsp--;

            adaptor.addChild(root_0, equality195.getTree());
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1113:17: ( AND equality )*
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( (LA64_0==AND) ) {
                    alt64=1;
                }


                switch (alt64) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1113:18: AND equality
            	    {
            	    AND196=(Token)match(input,AND,FOLLOW_AND_in_and2091); 
            	    AND196_tree = (CommonTree)adaptor.create(AND196);
            	    root_0 = (CommonTree)adaptor.becomeRoot(AND196_tree, root_0);

            	    pushFollow(FOLLOW_equality_in_and2095);
            	    equality197=equality();

            	    state._fsp--;

            	    adaptor.addChild(root_0, equality197.getTree());

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
        }
        return retval;
    }
    // $ANTLR end "and"

    public static class equality_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "equality"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1115:1: equality : isa_operation ( ( EQUALITY | NOTEQUALS ) isa_operation )* ;
    public final QuorumParser.equality_return equality() throws RecognitionException {
        QuorumParser.equality_return retval = new QuorumParser.equality_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EQUALITY199=null;
        Token NOTEQUALS200=null;
        QuorumParser.isa_operation_return isa_operation198 = null;

        QuorumParser.isa_operation_return isa_operation201 = null;


        CommonTree EQUALITY199_tree=null;
        CommonTree NOTEQUALS200_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1115:9: ( isa_operation ( ( EQUALITY | NOTEQUALS ) isa_operation )* )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1115:11: isa_operation ( ( EQUALITY | NOTEQUALS ) isa_operation )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_isa_operation_in_equality2106);
            isa_operation198=isa_operation();

            state._fsp--;

            adaptor.addChild(root_0, isa_operation198.getTree());
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1115:25: ( ( EQUALITY | NOTEQUALS ) isa_operation )*
            loop66:
            do {
                int alt66=2;
                int LA66_0 = input.LA(1);

                if ( (LA66_0==EQUALITY||LA66_0==NOTEQUALS) ) {
                    alt66=1;
                }


                switch (alt66) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1115:26: ( EQUALITY | NOTEQUALS ) isa_operation
            	    {
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1115:26: ( EQUALITY | NOTEQUALS )
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
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1115:27: EQUALITY
            	            {
            	            EQUALITY199=(Token)match(input,EQUALITY,FOLLOW_EQUALITY_in_equality2110); 
            	            EQUALITY199_tree = (CommonTree)adaptor.create(EQUALITY199);
            	            root_0 = (CommonTree)adaptor.becomeRoot(EQUALITY199_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1115:40: NOTEQUALS
            	            {
            	            NOTEQUALS200=(Token)match(input,NOTEQUALS,FOLLOW_NOTEQUALS_in_equality2116); 
            	            NOTEQUALS200_tree = (CommonTree)adaptor.create(NOTEQUALS200);
            	            root_0 = (CommonTree)adaptor.becomeRoot(NOTEQUALS200_tree, root_0);


            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_isa_operation_in_equality2121);
            	    isa_operation201=isa_operation();

            	    state._fsp--;

            	    adaptor.addChild(root_0, isa_operation201.getTree());

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
        }
        return retval;
    }
    // $ANTLR end "equality"

    public static class isa_operation_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "isa_operation"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1117:1: isa_operation : comparison ( INHERITS class_type )? ;
    public final QuorumParser.isa_operation_return isa_operation() throws RecognitionException {
        QuorumParser.isa_operation_return retval = new QuorumParser.isa_operation_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token INHERITS203=null;
        QuorumParser.comparison_return comparison202 = null;

        QuorumParser.class_type_return class_type204 = null;


        CommonTree INHERITS203_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1118:2: ( comparison ( INHERITS class_type )? )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1118:4: comparison ( INHERITS class_type )?
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_comparison_in_isa_operation2133);
            comparison202=comparison();

            state._fsp--;

            adaptor.addChild(root_0, comparison202.getTree());
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1118:15: ( INHERITS class_type )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==INHERITS) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1118:16: INHERITS class_type
                    {
                    INHERITS203=(Token)match(input,INHERITS,FOLLOW_INHERITS_in_isa_operation2136); 
                    INHERITS203_tree = (CommonTree)adaptor.create(INHERITS203);
                    root_0 = (CommonTree)adaptor.becomeRoot(INHERITS203_tree, root_0);

                    pushFollow(FOLLOW_class_type_in_isa_operation2140);
                    class_type204=class_type();

                    state._fsp--;

                    adaptor.addChild(root_0, class_type204.getTree());

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
        }
        return retval;
    }
    // $ANTLR end "isa_operation"

    public static class comparison_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "comparison"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1120:1: comparison : add ( ( GREATER | GREATER_EQUAL | LESS | LESS_EQUAL ) add )* ;
    public final QuorumParser.comparison_return comparison() throws RecognitionException {
        QuorumParser.comparison_return retval = new QuorumParser.comparison_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token GREATER206=null;
        Token GREATER_EQUAL207=null;
        Token LESS208=null;
        Token LESS_EQUAL209=null;
        QuorumParser.add_return add205 = null;

        QuorumParser.add_return add210 = null;


        CommonTree GREATER206_tree=null;
        CommonTree GREATER_EQUAL207_tree=null;
        CommonTree LESS208_tree=null;
        CommonTree LESS_EQUAL209_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1120:11: ( add ( ( GREATER | GREATER_EQUAL | LESS | LESS_EQUAL ) add )* )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1120:13: add ( ( GREATER | GREATER_EQUAL | LESS | LESS_EQUAL ) add )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_add_in_comparison2150);
            add205=add();

            state._fsp--;

            adaptor.addChild(root_0, add205.getTree());
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1120:17: ( ( GREATER | GREATER_EQUAL | LESS | LESS_EQUAL ) add )*
            loop69:
            do {
                int alt69=2;
                int LA69_0 = input.LA(1);

                if ( ((LA69_0>=LESS && LA69_0<=GREATER)||(LA69_0>=GREATER_EQUAL && LA69_0<=LESS_EQUAL)) ) {
                    alt69=1;
                }


                switch (alt69) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1120:18: ( GREATER | GREATER_EQUAL | LESS | LESS_EQUAL ) add
            	    {
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1120:18: ( GREATER | GREATER_EQUAL | LESS | LESS_EQUAL )
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
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1120:19: GREATER
            	            {
            	            GREATER206=(Token)match(input,GREATER,FOLLOW_GREATER_in_comparison2154); 
            	            GREATER206_tree = (CommonTree)adaptor.create(GREATER206);
            	            root_0 = (CommonTree)adaptor.becomeRoot(GREATER206_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1120:30: GREATER_EQUAL
            	            {
            	            GREATER_EQUAL207=(Token)match(input,GREATER_EQUAL,FOLLOW_GREATER_EQUAL_in_comparison2159); 
            	            GREATER_EQUAL207_tree = (CommonTree)adaptor.create(GREATER_EQUAL207);
            	            root_0 = (CommonTree)adaptor.becomeRoot(GREATER_EQUAL207_tree, root_0);


            	            }
            	            break;
            	        case 3 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1120:47: LESS
            	            {
            	            LESS208=(Token)match(input,LESS,FOLLOW_LESS_in_comparison2164); 
            	            LESS208_tree = (CommonTree)adaptor.create(LESS208);
            	            root_0 = (CommonTree)adaptor.becomeRoot(LESS208_tree, root_0);


            	            }
            	            break;
            	        case 4 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1120:55: LESS_EQUAL
            	            {
            	            LESS_EQUAL209=(Token)match(input,LESS_EQUAL,FOLLOW_LESS_EQUAL_in_comparison2169); 
            	            LESS_EQUAL209_tree = (CommonTree)adaptor.create(LESS_EQUAL209);
            	            root_0 = (CommonTree)adaptor.becomeRoot(LESS_EQUAL209_tree, root_0);


            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_add_in_comparison2173);
            	    add210=add();

            	    state._fsp--;

            	    adaptor.addChild(root_0, add210.getTree());

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
        }
        return retval;
    }
    // $ANTLR end "comparison"

    public static class add_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "add"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1124:1: add : multiply ( ( PLUS | MINUS ) multiply )* ;
    public final QuorumParser.add_return add() throws RecognitionException {
        QuorumParser.add_return retval = new QuorumParser.add_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token PLUS212=null;
        Token MINUS213=null;
        QuorumParser.multiply_return multiply211 = null;

        QuorumParser.multiply_return multiply214 = null;


        CommonTree PLUS212_tree=null;
        CommonTree MINUS213_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1124:5: ( multiply ( ( PLUS | MINUS ) multiply )* )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1124:7: multiply ( ( PLUS | MINUS ) multiply )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_multiply_in_add2187);
            multiply211=multiply();

            state._fsp--;

            adaptor.addChild(root_0, multiply211.getTree());
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1124:16: ( ( PLUS | MINUS ) multiply )*
            loop71:
            do {
                int alt71=2;
                int LA71_0 = input.LA(1);

                if ( ((LA71_0>=PLUS && LA71_0<=MINUS)) ) {
                    alt71=1;
                }


                switch (alt71) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1124:17: ( PLUS | MINUS ) multiply
            	    {
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1124:17: ( PLUS | MINUS )
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
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1124:18: PLUS
            	            {
            	            PLUS212=(Token)match(input,PLUS,FOLLOW_PLUS_in_add2191); 
            	            PLUS212_tree = (CommonTree)adaptor.create(PLUS212);
            	            root_0 = (CommonTree)adaptor.becomeRoot(PLUS212_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1124:26: MINUS
            	            {
            	            MINUS213=(Token)match(input,MINUS,FOLLOW_MINUS_in_add2196); 
            	            MINUS213_tree = (CommonTree)adaptor.create(MINUS213);
            	            root_0 = (CommonTree)adaptor.becomeRoot(MINUS213_tree, root_0);


            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_multiply_in_add2200);
            	    multiply214=multiply();

            	    state._fsp--;

            	    adaptor.addChild(root_0, multiply214.getTree());

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
        }
        return retval;
    }
    // $ANTLR end "add"

    public static class multiply_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "multiply"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1127:1: multiply : combo_expression ( ( MULTIPLY | DIVIDE | MODULO ) combo_expression )* ;
    public final QuorumParser.multiply_return multiply() throws RecognitionException {
        QuorumParser.multiply_return retval = new QuorumParser.multiply_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token MULTIPLY216=null;
        Token DIVIDE217=null;
        Token MODULO218=null;
        QuorumParser.combo_expression_return combo_expression215 = null;

        QuorumParser.combo_expression_return combo_expression219 = null;


        CommonTree MULTIPLY216_tree=null;
        CommonTree DIVIDE217_tree=null;
        CommonTree MODULO218_tree=null;

        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1127:9: ( combo_expression ( ( MULTIPLY | DIVIDE | MODULO ) combo_expression )* )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1127:11: combo_expression ( ( MULTIPLY | DIVIDE | MODULO ) combo_expression )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_combo_expression_in_multiply2213);
            combo_expression215=combo_expression();

            state._fsp--;

            adaptor.addChild(root_0, combo_expression215.getTree());
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1127:28: ( ( MULTIPLY | DIVIDE | MODULO ) combo_expression )*
            loop73:
            do {
                int alt73=2;
                int LA73_0 = input.LA(1);

                if ( ((LA73_0>=MULTIPLY && LA73_0<=MODULO)) ) {
                    alt73=1;
                }


                switch (alt73) {
            	case 1 :
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1127:29: ( MULTIPLY | DIVIDE | MODULO ) combo_expression
            	    {
            	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1127:29: ( MULTIPLY | DIVIDE | MODULO )
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
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1127:30: MULTIPLY
            	            {
            	            MULTIPLY216=(Token)match(input,MULTIPLY,FOLLOW_MULTIPLY_in_multiply2217); 
            	            MULTIPLY216_tree = (CommonTree)adaptor.create(MULTIPLY216);
            	            root_0 = (CommonTree)adaptor.becomeRoot(MULTIPLY216_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1127:42: DIVIDE
            	            {
            	            DIVIDE217=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_multiply2222); 
            	            DIVIDE217_tree = (CommonTree)adaptor.create(DIVIDE217);
            	            root_0 = (CommonTree)adaptor.becomeRoot(DIVIDE217_tree, root_0);


            	            }
            	            break;
            	        case 3 :
            	            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1127:51: MODULO
            	            {
            	            MODULO218=(Token)match(input,MODULO,FOLLOW_MODULO_in_multiply2226); 
            	            MODULO218_tree = (CommonTree)adaptor.create(MODULO218);
            	            root_0 = (CommonTree)adaptor.becomeRoot(MODULO218_tree, root_0);


            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_combo_expression_in_multiply2230);
            	    combo_expression219=combo_expression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, combo_expression219.getTree());

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
        }
        return retval;
    }
    // $ANTLR end "multiply"

    public static class combo_expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "combo_expression"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1130:1: combo_expression : ( NOT atom -> ^( UNARY_NOT NOT atom ) | CAST LEFT_PAREN assignment_declaration COMMA expression RIGHT_PAREN | atom );
    public final QuorumParser.combo_expression_return combo_expression() throws RecognitionException {
        QuorumParser.combo_expression_return retval = new QuorumParser.combo_expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token NOT220=null;
        Token CAST222=null;
        Token LEFT_PAREN223=null;
        Token COMMA225=null;
        Token RIGHT_PAREN227=null;
        QuorumParser.atom_return atom221 = null;

        QuorumParser.assignment_declaration_return assignment_declaration224 = null;

        QuorumParser.expression_return expression226 = null;

        QuorumParser.atom_return atom228 = null;


        CommonTree NOT220_tree=null;
        CommonTree CAST222_tree=null;
        CommonTree LEFT_PAREN223_tree=null;
        CommonTree COMMA225_tree=null;
        CommonTree RIGHT_PAREN227_tree=null;
        RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
        RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1131:2: ( NOT atom -> ^( UNARY_NOT NOT atom ) | CAST LEFT_PAREN assignment_declaration COMMA expression RIGHT_PAREN | atom )
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
            case ID:
            case LEFT_PAREN:
            case PARENT:
            case ME:
            case MINUS:
            case INT:
            case BOOLEAN:
            case DECIMAL:
            case STRING:
            case NULL:
            case INPUT:
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
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1131:4: NOT atom
                    {
                    NOT220=(Token)match(input,NOT,FOLLOW_NOT_in_combo_expression2245);  
                    stream_NOT.add(NOT220);

                    pushFollow(FOLLOW_atom_in_combo_expression2247);
                    atom221=atom();

                    state._fsp--;

                    stream_atom.add(atom221.getTree());


                    // AST REWRITE
                    // elements: atom, NOT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1131:13: -> ^( UNARY_NOT NOT atom )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1131:16: ^( UNARY_NOT NOT atom )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UNARY_NOT, "UNARY_NOT"), root_1);

                        adaptor.addChild(root_1, stream_NOT.nextNode());
                        adaptor.addChild(root_1, stream_atom.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1132:4: CAST LEFT_PAREN assignment_declaration COMMA expression RIGHT_PAREN
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    CAST222=(Token)match(input,CAST,FOLLOW_CAST_in_combo_expression2262); 
                    CAST222_tree = (CommonTree)adaptor.create(CAST222);
                    adaptor.addChild(root_0, CAST222_tree);

                    LEFT_PAREN223=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_combo_expression2264); 
                    LEFT_PAREN223_tree = (CommonTree)adaptor.create(LEFT_PAREN223);
                    adaptor.addChild(root_0, LEFT_PAREN223_tree);

                    pushFollow(FOLLOW_assignment_declaration_in_combo_expression2266);
                    assignment_declaration224=assignment_declaration();

                    state._fsp--;

                    adaptor.addChild(root_0, assignment_declaration224.getTree());
                    COMMA225=(Token)match(input,COMMA,FOLLOW_COMMA_in_combo_expression2268); 
                    COMMA225_tree = (CommonTree)adaptor.create(COMMA225);
                    adaptor.addChild(root_0, COMMA225_tree);

                    pushFollow(FOLLOW_expression_in_combo_expression2270);
                    expression226=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression226.getTree());
                    RIGHT_PAREN227=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_combo_expression2272); 
                    RIGHT_PAREN227_tree = (CommonTree)adaptor.create(RIGHT_PAREN227);
                    adaptor.addChild(root_0, RIGHT_PAREN227_tree);


                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1133:4: atom
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_atom_in_combo_expression2277);
                    atom228=atom();

                    state._fsp--;

                    adaptor.addChild(root_0, atom228.getTree());

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
        }
        return retval;
    }
    // $ANTLR end "combo_expression"

    public static class atom_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "atom"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1136:1: atom : ( qualified_name ( COLON ID )? -> ^( QUALIFIED_SOLO_EXPRESSION qualified_name ( COLON ID )? ) | qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN ) | selector COLON qualified_name -> ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name ) | PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN ) | ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN ) | ( MINUS )? INT | BOOLEAN | ( MINUS )? DECIMAL | STRING | NULL | ME | INPUT LEFT_PAREN expression RIGHT_PAREN | LEFT_PAREN expression RIGHT_PAREN -> ^( expression ) );
    public final QuorumParser.atom_return atom() throws RecognitionException {
        QuorumParser.atom_return retval = new QuorumParser.atom_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token COLON230=null;
        Token ID231=null;
        Token COLON233=null;
        Token ID234=null;
        Token LEFT_PAREN235=null;
        Token RIGHT_PAREN237=null;
        Token COLON239=null;
        Token PARENT241=null;
        Token COLON242=null;
        Token COLON244=null;
        Token ID245=null;
        Token LEFT_PAREN246=null;
        Token RIGHT_PAREN248=null;
        Token ME249=null;
        Token COLON250=null;
        Token COLON252=null;
        Token ID253=null;
        Token LEFT_PAREN254=null;
        Token RIGHT_PAREN256=null;
        Token MINUS257=null;
        Token INT258=null;
        Token BOOLEAN259=null;
        Token MINUS260=null;
        Token DECIMAL261=null;
        Token STRING262=null;
        Token NULL263=null;
        Token ME264=null;
        Token INPUT265=null;
        Token LEFT_PAREN266=null;
        Token RIGHT_PAREN268=null;
        Token LEFT_PAREN269=null;
        Token RIGHT_PAREN271=null;
        QuorumParser.qualified_name_return qualified_name229 = null;

        QuorumParser.qualified_name_return qualified_name232 = null;

        QuorumParser.function_expression_list_return function_expression_list236 = null;

        QuorumParser.selector_return selector238 = null;

        QuorumParser.qualified_name_return qualified_name240 = null;

        QuorumParser.qualified_name_return qualified_name243 = null;

        QuorumParser.function_expression_list_return function_expression_list247 = null;

        QuorumParser.qualified_name_return qualified_name251 = null;

        QuorumParser.function_expression_list_return function_expression_list255 = null;

        QuorumParser.expression_return expression267 = null;

        QuorumParser.expression_return expression270 = null;


        CommonTree COLON230_tree=null;
        CommonTree ID231_tree=null;
        CommonTree COLON233_tree=null;
        CommonTree ID234_tree=null;
        CommonTree LEFT_PAREN235_tree=null;
        CommonTree RIGHT_PAREN237_tree=null;
        CommonTree COLON239_tree=null;
        CommonTree PARENT241_tree=null;
        CommonTree COLON242_tree=null;
        CommonTree COLON244_tree=null;
        CommonTree ID245_tree=null;
        CommonTree LEFT_PAREN246_tree=null;
        CommonTree RIGHT_PAREN248_tree=null;
        CommonTree ME249_tree=null;
        CommonTree COLON250_tree=null;
        CommonTree COLON252_tree=null;
        CommonTree ID253_tree=null;
        CommonTree LEFT_PAREN254_tree=null;
        CommonTree RIGHT_PAREN256_tree=null;
        CommonTree MINUS257_tree=null;
        CommonTree INT258_tree=null;
        CommonTree BOOLEAN259_tree=null;
        CommonTree MINUS260_tree=null;
        CommonTree DECIMAL261_tree=null;
        CommonTree STRING262_tree=null;
        CommonTree NULL263_tree=null;
        CommonTree ME264_tree=null;
        CommonTree INPUT265_tree=null;
        CommonTree LEFT_PAREN266_tree=null;
        CommonTree RIGHT_PAREN268_tree=null;
        CommonTree LEFT_PAREN269_tree=null;
        CommonTree RIGHT_PAREN271_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_LEFT_PAREN=new RewriteRuleTokenStream(adaptor,"token LEFT_PAREN");
        RewriteRuleTokenStream stream_ME=new RewriteRuleTokenStream(adaptor,"token ME");
        RewriteRuleTokenStream stream_PARENT=new RewriteRuleTokenStream(adaptor,"token PARENT");
        RewriteRuleTokenStream stream_RIGHT_PAREN=new RewriteRuleTokenStream(adaptor,"token RIGHT_PAREN");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_selector=new RewriteRuleSubtreeStream(adaptor,"rule selector");
        RewriteRuleSubtreeStream stream_function_expression_list=new RewriteRuleSubtreeStream(adaptor,"rule function_expression_list");
        RewriteRuleSubtreeStream stream_qualified_name=new RewriteRuleSubtreeStream(adaptor,"rule qualified_name");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1136:7: ( qualified_name ( COLON ID )? -> ^( QUALIFIED_SOLO_EXPRESSION qualified_name ( COLON ID )? ) | qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN ) | selector COLON qualified_name -> ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name ) | PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN ) | ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN ) | ( MINUS )? INT | BOOLEAN | ( MINUS )? DECIMAL | STRING | NULL | ME | INPUT LEFT_PAREN expression RIGHT_PAREN | LEFT_PAREN expression RIGHT_PAREN -> ^( expression ) )
            int alt80=13;
            alt80 = dfa80.predict(input);
            switch (alt80) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1137:2: qualified_name ( COLON ID )?
                    {
                    pushFollow(FOLLOW_qualified_name_in_atom2292);
                    qualified_name229=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name229.getTree());
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1137:17: ( COLON ID )?
                    int alt75=2;
                    int LA75_0 = input.LA(1);

                    if ( (LA75_0==COLON) ) {
                        alt75=1;
                    }
                    switch (alt75) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1137:18: COLON ID
                            {
                            COLON230=(Token)match(input,COLON,FOLLOW_COLON_in_atom2295);  
                            stream_COLON.add(COLON230);

                            ID231=(Token)match(input,ID,FOLLOW_ID_in_atom2297);  
                            stream_ID.add(ID231);


                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: qualified_name, ID, COLON
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1137:29: -> ^( QUALIFIED_SOLO_EXPRESSION qualified_name ( COLON ID )? )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1137:32: ^( QUALIFIED_SOLO_EXPRESSION qualified_name ( COLON ID )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(QUALIFIED_SOLO_EXPRESSION, "QUALIFIED_SOLO_EXPRESSION"), root_1);

                        adaptor.addChild(root_1, stream_qualified_name.nextTree());
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1137:75: ( COLON ID )?
                        if ( stream_ID.hasNext()||stream_COLON.hasNext() ) {
                            adaptor.addChild(root_1, stream_COLON.nextNode());
                            adaptor.addChild(root_1, stream_ID.nextNode());

                        }
                        stream_ID.reset();
                        stream_COLON.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1138:4: qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN
                    {
                    pushFollow(FOLLOW_qualified_name_in_atom2319);
                    qualified_name232=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name232.getTree());
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1138:19: ( COLON ID )?
                    int alt76=2;
                    int LA76_0 = input.LA(1);

                    if ( (LA76_0==COLON) ) {
                        alt76=1;
                    }
                    switch (alt76) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1138:20: COLON ID
                            {
                            COLON233=(Token)match(input,COLON,FOLLOW_COLON_in_atom2322);  
                            stream_COLON.add(COLON233);

                            ID234=(Token)match(input,ID,FOLLOW_ID_in_atom2324);  
                            stream_ID.add(ID234);


                            }
                            break;

                    }

                    LEFT_PAREN235=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_atom2328);  
                    stream_LEFT_PAREN.add(LEFT_PAREN235);

                    pushFollow(FOLLOW_function_expression_list_in_atom2330);
                    function_expression_list236=function_expression_list();

                    state._fsp--;

                    stream_function_expression_list.add(function_expression_list236.getTree());
                    RIGHT_PAREN237=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_atom2332);  
                    stream_RIGHT_PAREN.add(RIGHT_PAREN237);



                    // AST REWRITE
                    // elements: LEFT_PAREN, function_expression_list, RIGHT_PAREN, ID, COLON, qualified_name
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1138:79: -> ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1139:4: ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(FUNCTION_CALL, "FUNCTION_CALL"), root_1);

                        adaptor.addChild(root_1, stream_qualified_name.nextTree());
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1139:35: ( COLON ID )?
                        if ( stream_ID.hasNext()||stream_COLON.hasNext() ) {
                            adaptor.addChild(root_1, stream_COLON.nextNode());
                            adaptor.addChild(root_1, stream_ID.nextNode());

                        }
                        stream_ID.reset();
                        stream_COLON.reset();
                        adaptor.addChild(root_1, stream_LEFT_PAREN.nextNode());
                        adaptor.addChild(root_1, stream_function_expression_list.nextTree());
                        adaptor.addChild(root_1, stream_RIGHT_PAREN.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1140:4: selector COLON qualified_name
                    {
                    pushFollow(FOLLOW_selector_in_atom2361);
                    selector238=selector();

                    state._fsp--;

                    stream_selector.add(selector238.getTree());
                    COLON239=(Token)match(input,COLON,FOLLOW_COLON_in_atom2363);  
                    stream_COLON.add(COLON239);

                    pushFollow(FOLLOW_qualified_name_in_atom2365);
                    qualified_name240=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name240.getTree());


                    // AST REWRITE
                    // elements: selector, qualified_name, COLON
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1140:34: -> ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1141:4: ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(QUALIFIED_SOLO_EXPRESSION_SELECTOR, "QUALIFIED_SOLO_EXPRESSION_SELECTOR"), root_1);

                        adaptor.addChild(root_1, stream_selector.nextTree());
                        adaptor.addChild(root_1, stream_COLON.nextNode());
                        adaptor.addChild(root_1, stream_qualified_name.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1142:4: PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN
                    {
                    PARENT241=(Token)match(input,PARENT,FOLLOW_PARENT_in_atom2386);  
                    stream_PARENT.add(PARENT241);

                    COLON242=(Token)match(input,COLON,FOLLOW_COLON_in_atom2388);  
                    stream_COLON.add(COLON242);

                    pushFollow(FOLLOW_qualified_name_in_atom2390);
                    qualified_name243=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name243.getTree());
                    COLON244=(Token)match(input,COLON,FOLLOW_COLON_in_atom2392);  
                    stream_COLON.add(COLON244);

                    ID245=(Token)match(input,ID,FOLLOW_ID_in_atom2394);  
                    stream_ID.add(ID245);

                    LEFT_PAREN246=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_atom2396);  
                    stream_LEFT_PAREN.add(LEFT_PAREN246);

                    pushFollow(FOLLOW_function_expression_list_in_atom2398);
                    function_expression_list247=function_expression_list();

                    state._fsp--;

                    stream_function_expression_list.add(function_expression_list247.getTree());
                    RIGHT_PAREN248=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_atom2400);  
                    stream_RIGHT_PAREN.add(RIGHT_PAREN248);



                    // AST REWRITE
                    // elements: COLON, qualified_name, function_expression_list, RIGHT_PAREN, ID, LEFT_PAREN, COLON, PARENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1142:89: -> ^( FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1143:4: ^( FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(FUNCTION_CALL_PARENT, "FUNCTION_CALL_PARENT"), root_1);

                        adaptor.addChild(root_1, stream_PARENT.nextNode());
                        adaptor.addChild(root_1, stream_COLON.nextNode());
                        adaptor.addChild(root_1, stream_qualified_name.nextTree());
                        adaptor.addChild(root_1, stream_COLON.nextNode());
                        adaptor.addChild(root_1, stream_ID.nextNode());
                        adaptor.addChild(root_1, stream_LEFT_PAREN.nextNode());
                        adaptor.addChild(root_1, stream_function_expression_list.nextTree());
                        adaptor.addChild(root_1, stream_RIGHT_PAREN.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1144:4: ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN
                    {
                    ME249=(Token)match(input,ME,FOLLOW_ME_in_atom2430);  
                    stream_ME.add(ME249);

                    COLON250=(Token)match(input,COLON,FOLLOW_COLON_in_atom2432);  
                    stream_COLON.add(COLON250);

                    pushFollow(FOLLOW_qualified_name_in_atom2434);
                    qualified_name251=qualified_name();

                    state._fsp--;

                    stream_qualified_name.add(qualified_name251.getTree());
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1144:28: ( COLON ID )?
                    int alt77=2;
                    int LA77_0 = input.LA(1);

                    if ( (LA77_0==COLON) ) {
                        alt77=1;
                    }
                    switch (alt77) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1144:29: COLON ID
                            {
                            COLON252=(Token)match(input,COLON,FOLLOW_COLON_in_atom2437);  
                            stream_COLON.add(COLON252);

                            ID253=(Token)match(input,ID,FOLLOW_ID_in_atom2439);  
                            stream_ID.add(ID253);


                            }
                            break;

                    }

                    LEFT_PAREN254=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_atom2443);  
                    stream_LEFT_PAREN.add(LEFT_PAREN254);

                    pushFollow(FOLLOW_function_expression_list_in_atom2445);
                    function_expression_list255=function_expression_list();

                    state._fsp--;

                    stream_function_expression_list.add(function_expression_list255.getTree());
                    RIGHT_PAREN256=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_atom2447);  
                    stream_RIGHT_PAREN.add(RIGHT_PAREN256);



                    // AST REWRITE
                    // elements: function_expression_list, COLON, ID, qualified_name, ME, RIGHT_PAREN, COLON, LEFT_PAREN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 1144:88: -> ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1145:4: ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(FUNCTION_CALL_THIS, "FUNCTION_CALL_THIS"), root_1);

                        adaptor.addChild(root_1, stream_ME.nextNode());
                        adaptor.addChild(root_1, stream_COLON.nextNode());
                        adaptor.addChild(root_1, stream_qualified_name.nextTree());
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1145:49: ( COLON ID )?
                        if ( stream_COLON.hasNext()||stream_ID.hasNext() ) {
                            adaptor.addChild(root_1, stream_COLON.nextNode());
                            adaptor.addChild(root_1, stream_ID.nextNode());

                        }
                        stream_COLON.reset();
                        stream_ID.reset();
                        adaptor.addChild(root_1, stream_LEFT_PAREN.nextNode());
                        adaptor.addChild(root_1, stream_function_expression_list.nextTree());
                        adaptor.addChild(root_1, stream_RIGHT_PAREN.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 6 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1146:4: ( MINUS )? INT
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1146:4: ( MINUS )?
                    int alt78=2;
                    int LA78_0 = input.LA(1);

                    if ( (LA78_0==MINUS) ) {
                        alt78=1;
                    }
                    switch (alt78) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1146:5: MINUS
                            {
                            MINUS257=(Token)match(input,MINUS,FOLLOW_MINUS_in_atom2481); 
                            MINUS257_tree = (CommonTree)adaptor.create(MINUS257);
                            adaptor.addChild(root_0, MINUS257_tree);


                            }
                            break;

                    }

                    INT258=(Token)match(input,INT,FOLLOW_INT_in_atom2485); 
                    INT258_tree = (CommonTree)adaptor.create(INT258);
                    adaptor.addChild(root_0, INT258_tree);


                    }
                    break;
                case 7 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1147:4: BOOLEAN
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    BOOLEAN259=(Token)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_atom2490); 
                    BOOLEAN259_tree = (CommonTree)adaptor.create(BOOLEAN259);
                    adaptor.addChild(root_0, BOOLEAN259_tree);


                    }
                    break;
                case 8 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1148:4: ( MINUS )? DECIMAL
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1148:4: ( MINUS )?
                    int alt79=2;
                    int LA79_0 = input.LA(1);

                    if ( (LA79_0==MINUS) ) {
                        alt79=1;
                    }
                    switch (alt79) {
                        case 1 :
                            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1148:5: MINUS
                            {
                            MINUS260=(Token)match(input,MINUS,FOLLOW_MINUS_in_atom2496); 
                            MINUS260_tree = (CommonTree)adaptor.create(MINUS260);
                            adaptor.addChild(root_0, MINUS260_tree);


                            }
                            break;

                    }

                    DECIMAL261=(Token)match(input,DECIMAL,FOLLOW_DECIMAL_in_atom2500); 
                    DECIMAL261_tree = (CommonTree)adaptor.create(DECIMAL261);
                    adaptor.addChild(root_0, DECIMAL261_tree);


                    }
                    break;
                case 9 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1149:4: STRING
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    STRING262=(Token)match(input,STRING,FOLLOW_STRING_in_atom2506); 
                    STRING262_tree = (CommonTree)adaptor.create(STRING262);
                    adaptor.addChild(root_0, STRING262_tree);


                    }
                    break;
                case 10 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1150:4: NULL
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    NULL263=(Token)match(input,NULL,FOLLOW_NULL_in_atom2511); 
                    NULL263_tree = (CommonTree)adaptor.create(NULL263);
                    adaptor.addChild(root_0, NULL263_tree);


                    }
                    break;
                case 11 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1151:4: ME
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    ME264=(Token)match(input,ME,FOLLOW_ME_in_atom2516); 
                    ME264_tree = (CommonTree)adaptor.create(ME264);
                    adaptor.addChild(root_0, ME264_tree);


                    }
                    break;
                case 12 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1152:5: INPUT LEFT_PAREN expression RIGHT_PAREN
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    INPUT265=(Token)match(input,INPUT,FOLLOW_INPUT_in_atom2522); 
                    INPUT265_tree = (CommonTree)adaptor.create(INPUT265);
                    adaptor.addChild(root_0, INPUT265_tree);

                    LEFT_PAREN266=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_atom2524); 
                    LEFT_PAREN266_tree = (CommonTree)adaptor.create(LEFT_PAREN266);
                    adaptor.addChild(root_0, LEFT_PAREN266_tree);

                    pushFollow(FOLLOW_expression_in_atom2526);
                    expression267=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression267.getTree());
                    RIGHT_PAREN268=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_atom2528); 
                    RIGHT_PAREN268_tree = (CommonTree)adaptor.create(RIGHT_PAREN268);
                    adaptor.addChild(root_0, RIGHT_PAREN268_tree);


                    }
                    break;
                case 13 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1153:4: LEFT_PAREN expression RIGHT_PAREN
                    {
                    LEFT_PAREN269=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_atom2533);  
                    stream_LEFT_PAREN.add(LEFT_PAREN269);

                    pushFollow(FOLLOW_expression_in_atom2535);
                    expression270=expression();

                    state._fsp--;

                    stream_expression.add(expression270.getTree());
                    RIGHT_PAREN271=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_atom2537);  
                    stream_RIGHT_PAREN.add(RIGHT_PAREN271);



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
                    // 1153:38: -> ^( expression )
                    {
                        // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1153:41: ^( expression )
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
        }
        return retval;
    }
    // $ANTLR end "atom"

    public static class function_expression_list_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "function_expression_list"
    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1156:1: function_expression_list : ( expression ( COMMA expression )* )? -> ^( FUNCTION_EXPRESSION_LIST ( expression )* ) ;
    public final QuorumParser.function_expression_list_return function_expression_list() throws RecognitionException {
        QuorumParser.function_expression_list_return retval = new QuorumParser.function_expression_list_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token COMMA273=null;
        QuorumParser.expression_return expression272 = null;

        QuorumParser.expression_return expression274 = null;


        CommonTree COMMA273_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1157:2: ( ( expression ( COMMA expression )* )? -> ^( FUNCTION_EXPRESSION_LIST ( expression )* ) )
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1158:2: ( expression ( COMMA expression )* )?
            {
            // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1158:2: ( expression ( COMMA expression )* )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==ID||LA82_0==LEFT_PAREN||(LA82_0>=PARENT && LA82_0<=ME)||LA82_0==MINUS||(LA82_0>=NOT && LA82_0<=INPUT)) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1158:3: expression ( COMMA expression )*
                    {
                    pushFollow(FOLLOW_expression_in_function_expression_list2557);
                    expression272=expression();

                    state._fsp--;

                    stream_expression.add(expression272.getTree());
                    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1158:14: ( COMMA expression )*
                    loop81:
                    do {
                        int alt81=2;
                        int LA81_0 = input.LA(1);

                        if ( (LA81_0==COMMA) ) {
                            alt81=1;
                        }


                        switch (alt81) {
                    	case 1 :
                    	    // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1158:15: COMMA expression
                    	    {
                    	    COMMA273=(Token)match(input,COMMA,FOLLOW_COMMA_in_function_expression_list2560);  
                    	    stream_COMMA.add(COMMA273);

                    	    pushFollow(FOLLOW_expression_in_function_expression_list2562);
                    	    expression274=expression();

                    	    state._fsp--;

                    	    stream_expression.add(expression274.getTree());

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
            // 1159:2: -> ^( FUNCTION_EXPRESSION_LIST ( expression )* )
            {
                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1159:5: ^( FUNCTION_EXPRESSION_LIST ( expression )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(FUNCTION_EXPRESSION_LIST, "FUNCTION_EXPRESSION_LIST"), root_1);

                // /Users/melissa/NetBeansProjects/quorum/trunk/quorum/src/org/quorum/parser/Quorum.g:1159:32: ( expression )*
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
        }
        return retval;
    }
    // $ANTLR end "function_expression_list"

    // Delegated rules


    protected DFA4 dfa4 = new DFA4(this);
    protected DFA35 dfa35 = new DFA35(this);
    protected DFA58 dfa58 = new DFA58(this);
    protected DFA80 dfa80 = new DFA80(this);
    static final String DFA4_eotS =
        "\16\uffff";
    static final String DFA4_eofS =
        "\16\uffff";
    static final String DFA4_minS =
        "\1\30\2\33\1\uffff\1\31\1\30\1\33\2\uffff\1\33\2\uffff\1\31\1\30";
    static final String DFA4_maxS =
        "\1\103\2\33\1\uffff\2\103\1\33\2\uffff\1\33\2\uffff\2\103";
    static final String DFA4_acceptS =
        "\3\uffff\1\5\3\uffff\1\1\1\3\1\uffff\1\2\1\4\2\uffff";
    static final String DFA4_specialS =
        "\16\uffff}>";
    static final String[] DFA4_transitionS = {
            "\1\1\1\2\2\3\3\uffff\3\3\3\uffff\3\3\2\uffff\7\3\4\uffff\3\3"+
            "\3\uffff\4\3\1\uffff\1\3\2\uffff\1\3",
            "\1\4",
            "\1\5",
            "",
            "\1\7\2\10\3\uffff\3\10\3\uffff\3\10\1\6\1\uffff\7\10\4\uffff"+
            "\3\10\3\uffff\4\10\1\uffff\1\10\2\uffff\1\10",
            "\1\12\1\2\2\13\3\uffff\3\13\3\uffff\3\13\1\11\1\uffff\7\13"+
            "\4\uffff\3\13\3\uffff\4\13\1\uffff\1\13\2\uffff\1\13",
            "\1\14",
            "",
            "",
            "\1\15",
            "",
            "",
            "\1\7\2\10\3\uffff\3\10\3\uffff\3\10\1\6\1\uffff\7\10\4\uffff"+
            "\3\10\3\uffff\4\10\1\uffff\1\10\2\uffff\1\10",
            "\1\12\1\2\2\13\3\uffff\3\13\3\uffff\3\13\1\11\1\uffff\7\13"+
            "\4\uffff\3\13\3\uffff\4\13\1\uffff\1\13\2\uffff\1\13"
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
            return "263:3: ( package_rule ( reference )+ | ( reference )+ package_rule | package_rule | ( reference )+ | )";
        }
    }
    static final String DFA35_eotS =
        "\31\uffff";
    static final String DFA35_eofS =
        "\31\uffff";
    static final String DFA35_minS =
        "\2\33\2\51\11\uffff\5\33\1\42\1\50\1\42\2\33\1\50\1\42";
    static final String DFA35_maxS =
        "\1\103\1\77\2\51\11\uffff\1\33\1\52\2\33\1\71\1\77\1\51\1\77\2\33"+
        "\1\51\1\77";
    static final String DFA35_acceptS =
        "\4\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\14\uffff";
    static final String DFA35_specialS =
        "\31\uffff}>";
    static final String[] DFA35_transitionS = {
            "\1\1\16\uffff\1\2\1\3\3\4\1\14\1\13\4\uffff\1\11\1\12\1\10\3"+
            "\uffff\4\6\1\uffff\1\5\2\uffff\1\7",
            "\1\6\6\uffff\1\4\5\uffff\1\15\1\16\17\uffff\1\6\5\uffff\1\6",
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
            "",
            "\1\21",
            "\1\22\16\uffff\1\6",
            "\1\23",
            "\1\24",
            "\1\6\6\uffff\1\4\5\uffff\1\15\1\16\17\uffff\1\6",
            "\1\4\34\uffff\1\6",
            "\1\25\1\26",
            "\1\4\5\uffff\2\4\25\uffff\1\6",
            "\1\27",
            "\1\30",
            "\1\25\1\26",
            "\1\4\34\uffff\1\6"
    };

    static final short[] DFA35_eot = DFA.unpackEncodedString(DFA35_eotS);
    static final short[] DFA35_eof = DFA.unpackEncodedString(DFA35_eofS);
    static final char[] DFA35_min = DFA.unpackEncodedStringToUnsignedChars(DFA35_minS);
    static final char[] DFA35_max = DFA.unpackEncodedStringToUnsignedChars(DFA35_maxS);
    static final short[] DFA35_accept = DFA.unpackEncodedString(DFA35_acceptS);
    static final short[] DFA35_special = DFA.unpackEncodedString(DFA35_specialS);
    static final short[][] DFA35_transition;

    static {
        int numStates = DFA35_transitionS.length;
        DFA35_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA35_transition[i] = DFA.unpackEncodedString(DFA35_transitionS[i]);
        }
    }

    class DFA35 extends DFA {

        public DFA35(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 35;
            this.eot = DFA35_eot;
            this.eof = DFA35_eof;
            this.min = DFA35_min;
            this.max = DFA35_max;
            this.accept = DFA35_accept;
            this.special = DFA35_special;
            this.transition = DFA35_transition;
        }
        public String getDescription() {
            return "697:1: statement : ( solo_method_call | if_statement | assignment_statement | loop_statement | return_statement | print_statement | speak_statement | check_statement | alert_statement );";
        }
    }
    static final String DFA58_eotS =
        "\7\uffff";
    static final String DFA58_eofS =
        "\7\uffff";
    static final String DFA58_minS =
        "\1\33\1\uffff\1\33\1\uffff\1\33\1\uffff\1\33";
    static final String DFA58_maxS =
        "\1\76\1\uffff\1\77\1\uffff\1\33\1\uffff\1\71";
    static final String DFA58_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\1\uffff\1\2\1\uffff";
    static final String DFA58_specialS =
        "\7\uffff}>";
    static final String[] DFA58_transitionS = {
            "\1\2\16\uffff\2\1\17\uffff\4\3",
            "",
            "\1\3\14\uffff\1\4\1\5\17\uffff\1\3\5\uffff\1\1",
            "",
            "\1\6",
            "",
            "\1\3\14\uffff\1\4\1\5\17\uffff\1\3"
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
            return "952:1: assignment_statement : ( (sel= selector COLON )? ID rhs= assign_right_hand_side | obj= qualified_name ( COLON PARENT COLON parent= qualified_name )? COLON ID rhs= assign_right_hand_side | type= assignment_declaration name= ID (rhs= assign_right_hand_side )? );";
        }
    }
    static final String DFA80_eotS =
        "\40\uffff";
    static final String DFA80_eofS =
        "\1\uffff\1\16\1\uffff\1\22\17\uffff\2\16\1\uffff\1\33\6\uffff\2"+
        "\33\1\uffff";
    static final String DFA80_minS =
        "\2\33\1\51\1\33\1\125\7\uffff\2\33\2\uffff\2\33\1\uffff\2\33\1\50"+
        "\4\33\2\uffff\1\50\2\33\1\uffff";
    static final String DFA80_maxS =
        "\1\132\1\122\1\51\1\122\1\127\7\uffff\2\33\2\uffff\2\33\1\uffff"+
        "\2\122\1\51\1\122\3\33\2\uffff\1\51\2\122\1\uffff";
    static final String DFA80_acceptS =
        "\5\uffff\1\6\1\7\1\10\1\11\1\12\1\14\1\15\2\uffff\1\1\1\2\2\uffff"+
        "\1\13\7\uffff\1\5\1\3\3\uffff\1\4";
    static final String DFA80_specialS =
        "\40\uffff}>";
    static final String[] DFA80_transitionS = {
            "\1\1\6\uffff\1\13\7\uffff\1\2\1\3\43\uffff\1\4\5\uffff\1\5\1"+
            "\6\1\7\1\10\1\11\1\12",
            "\7\16\1\17\1\16\1\uffff\3\16\1\14\1\15\7\16\3\uffff\4\16\1"+
            "\uffff\11\16\1\uffff\1\16\2\uffff\1\16\2\uffff\12\16",
            "\1\20",
            "\7\22\1\uffff\1\22\1\uffff\3\22\1\uffff\1\21\7\22\3\uffff\4"+
            "\22\1\uffff\11\22\1\uffff\1\22\2\uffff\1\22\2\uffff\12\22",
            "\1\5\1\uffff\1\7",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\23",
            "\1\24",
            "",
            "",
            "\1\25",
            "\1\26",
            "",
            "\7\16\1\17\1\16\1\uffff\3\16\1\14\1\15\7\16\3\uffff\4\16\1"+
            "\uffff\11\16\1\uffff\1\16\2\uffff\1\16\2\uffff\12\16",
            "\7\16\1\17\1\16\1\uffff\3\16\2\uffff\7\16\3\uffff\4\16\1\uffff"+
            "\11\16\1\uffff\1\16\2\uffff\1\16\2\uffff\12\16",
            "\1\27\1\30",
            "\7\33\1\32\1\33\1\uffff\3\33\1\31\1\32\7\33\3\uffff\4\33\1"+
            "\uffff\11\33\1\uffff\1\33\2\uffff\1\33\2\uffff\12\33",
            "\1\34",
            "\1\35",
            "\1\36",
            "",
            "",
            "\1\27\1\30",
            "\7\33\1\37\1\33\1\uffff\4\33\1\uffff\7\33\3\uffff\4\33\1\uffff"+
            "\11\33\1\uffff\1\33\2\uffff\1\33\2\uffff\12\33",
            "\7\33\1\32\1\33\1\uffff\3\33\1\31\1\32\7\33\3\uffff\4\33\1"+
            "\uffff\11\33\1\uffff\1\33\2\uffff\1\33\2\uffff\12\33",
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
            return "1136:1: atom : ( qualified_name ( COLON ID )? -> ^( QUALIFIED_SOLO_EXPRESSION qualified_name ( COLON ID )? ) | qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN ) | selector COLON qualified_name -> ^( QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name ) | PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN ) | ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN -> ^( FUNCTION_CALL_THIS ME COLON qualified_name ( COLON ID )? LEFT_PAREN function_expression_list RIGHT_PAREN ) | ( MINUS )? INT | BOOLEAN | ( MINUS )? DECIMAL | STRING | NULL | ME | INPUT LEFT_PAREN expression RIGHT_PAREN | LEFT_PAREN expression RIGHT_PAREN -> ^( expression ) );";
        }
    }
 

    public static final BitSet FOLLOW_package_rule_in_start146 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_reference_in_start148 = new BitSet(new long[]{0x78E1FCE38E000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_reference_in_start155 = new BitSet(new long[]{0x0000000003000000L});
    public static final BitSet FOLLOW_package_rule_in_start158 = new BitSet(new long[]{0x78E1FCE38C000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_package_rule_in_start163 = new BitSet(new long[]{0x78E1FCE38C000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_reference_in_start168 = new BitSet(new long[]{0x78E1FCE38E000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_class_declaration_in_start177 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_start180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PACKAGE_NAME_in_package_rule191 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_qualified_name_in_package_rule195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_USE_in_reference213 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_qualified_name_in_reference219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_in_class_declaration251 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_class_declaration253 = new BitSet(new long[]{0x7AE1FCE3BC000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_generic_declaration_in_class_declaration264 = new BitSet(new long[]{0x78E1FCE3BC000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_inherit_stmnts_in_class_declaration271 = new BitSet(new long[]{0x78E1FCE39C000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_class_stmnts_in_class_declaration282 = new BitSet(new long[]{0x78E1FCE39C000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_END_in_class_declaration286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_no_class_stmnts_in_class_declaration320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_no_class_stmnts339 = new BitSet(new long[]{0x78E1FC0008000002L,0x0000000000000009L});
    public static final BitSet FOLLOW_access_modifier_in_no_class_stmnts348 = new BitSet(new long[]{0x78E1FCE38C000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_method_declaration_in_no_class_stmnts351 = new BitSet(new long[]{0x78E1FCE38C000002L,0x0000000000000009L});
    public static final BitSet FOLLOW_INHERITS_in_inherit_stmnts364 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_qualified_name_in_inherit_stmnts368 = new BitSet(new long[]{0x0200000040000002L});
    public static final BitSet FOLLOW_generic_statement_in_inherit_stmnts372 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COMMA_in_inherit_stmnts380 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_qualified_name_in_inherit_stmnts384 = new BitSet(new long[]{0x0200000040000002L});
    public static final BitSet FOLLOW_generic_statement_in_inherit_stmnts388 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_PUBLIC_in_access_modifier425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRIVATE_in_access_modifier433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_access_modifier_in_class_stmnts451 = new BitSet(new long[]{0x78000C0008000000L});
    public static final BitSet FOLLOW_assignment_statement_in_class_stmnts458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_access_modifier_in_class_stmnts469 = new BitSet(new long[]{0x78E1FCE38C000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_method_declaration_in_class_stmnts477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_method_declaration495 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_method_declaration497 = new BitSet(new long[]{0x78E1FC1418000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_method_declaration507 = new BitSet(new long[]{0x78000C0808000000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration510 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_COMMA_in_method_declaration514 = new BitSet(new long[]{0x78000C0008000000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration516 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_method_declaration523 = new BitSet(new long[]{0x78E1FC1018000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_RETURNS_in_method_declaration529 = new BitSet(new long[]{0x78000C0008000000L});
    public static final BitSet FOLLOW_assignment_declaration_in_method_declaration535 = new BitSet(new long[]{0x78E1FC0018000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_block_in_method_declaration545 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_method_declaration548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BLUEPRINT_in_method_declaration580 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ACTION_in_method_declaration582 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_method_declaration584 = new BitSet(new long[]{0x0000001400000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_method_declaration594 = new BitSet(new long[]{0x78000C0808000000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration597 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_COMMA_in_method_declaration601 = new BitSet(new long[]{0x78000C0008000000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration603 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_method_declaration610 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_RETURNS_in_method_declaration616 = new BitSet(new long[]{0x78000C0008000000L});
    public static final BitSet FOLLOW_assignment_declaration_in_method_declaration622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NATIVE_in_method_declaration654 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ACTION_in_method_declaration656 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_method_declaration658 = new BitSet(new long[]{0x0000001400000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_method_declaration668 = new BitSet(new long[]{0x78000C0808000000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration671 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_COMMA_in_method_declaration675 = new BitSet(new long[]{0x78000C0008000000L});
    public static final BitSet FOLLOW_formal_parameter_in_method_declaration677 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_method_declaration684 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_RETURNS_in_method_declaration690 = new BitSet(new long[]{0x78000C0008000000L});
    public static final BitSet FOLLOW_assignment_declaration_in_method_declaration696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ON_CREATE_in_method_declaration728 = new BitSet(new long[]{0x78E1FC0018000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_block_in_method_declaration735 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_method_declaration737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_declaration_in_formal_parameter762 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_formal_parameter764 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_qualified_name795 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_PERIOD_in_qualified_name798 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_qualified_name802 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_statement_in_block836 = new BitSet(new long[]{0x78E1FC0008000002L,0x0000000000000009L});
    public static final BitSet FOLLOW_solo_method_call_in_statement859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_statement_in_statement864 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_statement_in_statement869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_loop_statement_in_statement874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_statement_in_statement879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_print_statement_in_statement884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_speak_statement_in_statement889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_check_statement_in_statement894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_alert_statement_in_statement899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_solo_method_call912 = new BitSet(new long[]{0x0000020400000000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call915 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_solo_method_call917 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call921 = new BitSet(new long[]{0x00000C0C08000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_solo_method_call924 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call927 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_solo_method_call929 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENT_in_solo_method_call974 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call976 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_qualified_name_in_solo_method_call978 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call980 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_solo_method_call982 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call984 = new BitSet(new long[]{0x00000C0C08000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_solo_method_call987 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call990 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_solo_method_call992 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ME_in_solo_method_call1038 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call1040 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_qualified_name_in_solo_method_call1042 = new BitSet(new long[]{0x0000020400000000L});
    public static final BitSet FOLLOW_COLON_in_solo_method_call1045 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_solo_method_call1047 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call1051 = new BitSet(new long[]{0x00000C0C08000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_solo_method_call1054 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call1057 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_solo_method_call1059 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call1065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LIBRARY_CALL_in_solo_method_call1108 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call1110 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_solo_method_call1112 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call1114 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_solo_method_call1116 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call1118 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_solo_method_call1120 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call1122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONNECT_TO_in_solo_method_call1127 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call1129 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_solo_method_call1131 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call1133 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_solo_method_call1135 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call1137 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_solo_method_call1139 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call1141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEND_TO_in_solo_method_call1146 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_solo_method_call1148 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_solo_method_call1150 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call1152 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_solo_method_call1154 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call1156 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_solo_method_call1158 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_COMMA_in_solo_method_call1160 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_solo_method_call1162 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_solo_method_call1164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALERT_in_alert_statement1177 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_alert_statement1179 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_alert_statement1181 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_alert_statement1184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHECK_in_check_statement1218 = new BitSet(new long[]{0x78E1FC0018000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_block_in_check_statement1221 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_check_statement1226 = new BitSet(new long[]{0x0006000000000000L});
    public static final BitSet FOLLOW_DETECT_in_check_statement1242 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_detect_parameter_in_check_statement1244 = new BitSet(new long[]{0x78E1FC0018000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_block_in_check_statement1247 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_check_statement1252 = new BitSet(new long[]{0x0006000000000002L});
    public static final BitSet FOLLOW_ALWAYS_in_check_statement1274 = new BitSet(new long[]{0x78E1FC0018000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_block_in_check_statement1277 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_check_statement1282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALWAYS_in_check_statement1307 = new BitSet(new long[]{0x78E1FC0018000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_block_in_check_statement1310 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_check_statement1315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_detect_parameter1351 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_OF_TYPE_in_detect_parameter1363 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_qualified_name_in_detect_parameter1365 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_OR_in_detect_parameter1367 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_qualified_name_in_detect_parameter1369 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_PRINT_in_print_statement1404 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_root_expression_in_print_statement1406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SAY_in_speak_statement1418 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_root_expression_in_speak_statement1420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_return_statement1431 = new BitSet(new long[]{0x01000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_root_expression_in_return_statement1435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOW_in_return_statement1439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_generic_declaration1456 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_generic_declaration1460 = new BitSet(new long[]{0x0400000040000000L});
    public static final BitSet FOLLOW_COMMA_in_generic_declaration1463 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_generic_declaration1467 = new BitSet(new long[]{0x0400000040000000L});
    public static final BitSet FOLLOW_GREATER_in_generic_declaration1471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_generic_statement1512 = new BitSet(new long[]{0x78000C0008000000L});
    public static final BitSet FOLLOW_assignment_declaration_in_generic_statement1522 = new BitSet(new long[]{0x0400000040000000L});
    public static final BitSet FOLLOW_COMMA_in_generic_statement1529 = new BitSet(new long[]{0x78000C0008000000L});
    public static final BitSet FOLLOW_assignment_declaration_in_generic_statement1533 = new BitSet(new long[]{0x0400000040000000L});
    public static final BitSet FOLLOW_GREATER_in_generic_statement1543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_class_type1583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_assignment_declaration1604 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_generic_statement_in_assignment_declaration1608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_KEYWORD_in_assignment_declaration1617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_KEYWORD_in_assignment_declaration1625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TEXT_in_assignment_declaration1633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_KEYWORD_in_assignment_declaration1641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_assignment_statement1662 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1664 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_assignment_statement1668 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_assign_right_hand_side_in_assignment_statement1674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_assignment_statement1685 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1688 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_PARENT_in_assignment_statement1690 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1692 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_qualified_name_in_assignment_statement1696 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_assignment_statement1700 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_assignment_statement1702 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_assign_right_hand_side_in_assignment_statement1706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_declaration_in_assignment_statement1715 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_assignment_statement1721 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_assign_right_hand_side_in_assignment_statement1727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALITY_in_assign_right_hand_side1753 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_root_expression_in_assign_right_hand_side1755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_if_statement1780 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_root_expression_in_if_statement1782 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_THEN_in_if_statement1784 = new BitSet(new long[]{0x78E1FC0018000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_block_in_if_statement1788 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_if_statement1796 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_ELSE_in_if_statement1809 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_IF_in_if_statement1811 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_root_expression_in_if_statement1813 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_THEN_in_if_statement1815 = new BitSet(new long[]{0x78E1FC0018000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_block_in_if_statement1819 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_if_statement1827 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_ELSE_in_if_statement1847 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_THEN_in_if_statement1849 = new BitSet(new long[]{0x78E1FC0018000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_block_in_if_statement1853 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_if_statement1861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REPEAT_in_loop_statement1893 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F881B0L});
    public static final BitSet FOLLOW_OVER_in_loop_statement1898 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_loop_statement1900 = new BitSet(new long[]{0x78E1FC0018000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_FROM_in_loop_statement1914 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_range_in_loop_statement1916 = new BitSet(new long[]{0x78E1FC0018000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_root_expression_in_loop_statement1925 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_TIMES_in_loop_statement1927 = new BitSet(new long[]{0x78E1FC0018000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_set_in_loop_statement1935 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_root_expression_in_loop_statement1943 = new BitSet(new long[]{0x78E1FC0018000000L,0x0000000000000009L});
    public static final BitSet FOLLOW_block_in_loop_statement1948 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_END_in_loop_statement1950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_root_expression_in_range1969 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_TO_in_range1972 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_root_expression_in_range1975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENT_in_selector1998 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_selector2000 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_qualified_name_in_selector2004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ME_in_selector2022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_root_expression2036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_in_expression2056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_in_or2068 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_OR_in_or2071 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_and_in_or2075 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_equality_in_and2088 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_AND_in_and2091 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_equality_in_and2095 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_isa_operation_in_equality2106 = new BitSet(new long[]{0x8000000000000002L,0x0000000000000800L});
    public static final BitSet FOLLOW_EQUALITY_in_equality2110 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_NOTEQUALS_in_equality2116 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_isa_operation_in_equality2121 = new BitSet(new long[]{0x8000000000000002L,0x0000000000000800L});
    public static final BitSet FOLLOW_comparison_in_isa_operation2133 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_INHERITS_in_isa_operation2136 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_class_type_in_isa_operation2140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_add_in_comparison2150 = new BitSet(new long[]{0x0600000000000002L,0x0000000000003000L});
    public static final BitSet FOLLOW_GREATER_in_comparison2154 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_GREATER_EQUAL_in_comparison2159 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_LESS_in_comparison2164 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_LESS_EQUAL_in_comparison2169 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_add_in_comparison2173 = new BitSet(new long[]{0x0600000000000002L,0x0000000000003000L});
    public static final BitSet FOLLOW_multiply_in_add2187 = new BitSet(new long[]{0x0000000000000002L,0x000000000000C000L});
    public static final BitSet FOLLOW_PLUS_in_add2191 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_MINUS_in_add2196 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_multiply_in_add2200 = new BitSet(new long[]{0x0000000000000002L,0x000000000000C000L});
    public static final BitSet FOLLOW_combo_expression_in_multiply2213 = new BitSet(new long[]{0x0000000000000002L,0x0000000000070000L});
    public static final BitSet FOLLOW_MULTIPLY_in_multiply2217 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_DIVIDE_in_multiply2222 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_MODULO_in_multiply2226 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_combo_expression_in_multiply2230 = new BitSet(new long[]{0x0000000000000002L,0x0000000000070000L});
    public static final BitSet FOLLOW_NOT_in_combo_expression2245 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_atom_in_combo_expression2247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CAST_in_combo_expression2262 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_combo_expression2264 = new BitSet(new long[]{0x78000C0008000000L});
    public static final BitSet FOLLOW_assignment_declaration_in_combo_expression2266 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_COMMA_in_combo_expression2268 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_combo_expression2270 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_combo_expression2272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_combo_expression2277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_atom2292 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_COLON_in_atom2295 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_atom2297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualified_name_in_atom2319 = new BitSet(new long[]{0x0000020400000000L});
    public static final BitSet FOLLOW_COLON_in_atom2322 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_atom2324 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_atom2328 = new BitSet(new long[]{0x00000C0C08000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_function_expression_list_in_atom2330 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_atom2332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_atom2361 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_atom2363 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_qualified_name_in_atom2365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARENT_in_atom2386 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_atom2388 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_qualified_name_in_atom2390 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_atom2392 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_atom2394 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_atom2396 = new BitSet(new long[]{0x00000C0C08000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_function_expression_list_in_atom2398 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_atom2400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ME_in_atom2430 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_COLON_in_atom2432 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_qualified_name_in_atom2434 = new BitSet(new long[]{0x0000020400000000L});
    public static final BitSet FOLLOW_COLON_in_atom2437 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ID_in_atom2439 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_atom2443 = new BitSet(new long[]{0x00000C0C08000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_function_expression_list_in_atom2445 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_atom2447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_atom2481 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_INT_in_atom2485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_atom2490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_atom2496 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_DECIMAL_in_atom2500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_atom2506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_atom2511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ME_in_atom2516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INPUT_in_atom2522 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_atom2524 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_atom2526 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_atom2528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_atom2533 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_atom2535 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_atom2537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_function_expression_list2557 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_COMMA_in_function_expression_list2560 = new BitSet(new long[]{0x00000C0408000000L,0x0000000007F88000L});
    public static final BitSet FOLLOW_expression_in_function_expression_list2562 = new BitSet(new long[]{0x0000000040000002L});

}