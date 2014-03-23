// Generated from /Users/stefika/Repositories/quorum-language/plugins/ParserPlugin/src/plugins/quorum/Libraries/Language/Parser/Quorum.g4 by ANTLR 4.1
package plugins.quorum.Libraries.Language.Parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QuorumParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OUTPUT=1, ON=2, CREATE=3, CONSTANT=4, ELSE_IF=5, ME=6, UNTIL=7, PUBLIC=8, 
		PRIVATE=9, ALERT=10, DETECT=11, ALWAYS=12, CHECK=13, PARENT=14, BLUEPRINT=15, 
		NATIVE=16, INHERITS=17, CAST=18, INPUT=19, SAY=20, NOW=21, WHILE=22, PACKAGE_NAME=23, 
		TIMES=24, REPEAT=25, ELSE=26, RETURNS=27, RETURN=28, AND=29, OR=30, NULL=31, 
		ACTION=32, COLON=33, INTEGER_KEYWORD=34, NUMBER_KEYWORD=35, TEXT=36, BOOLEAN_KEYWORD=37, 
		USE=38, NOT=39, NOTEQUALS=40, PERIOD=41, COMMA=42, EQUALITY=43, GREATER=44, 
		GREATER_EQUAL=45, LESS=46, LESS_EQUAL=47, PLUS=48, MINUS=49, MULTIPLY=50, 
		DIVIDE=51, MODULO=52, LEFT_SQR_BRACE=53, RIGHT_SQR_BRACE=54, LEFT_PAREN=55, 
		RIGHT_PAREN=56, DOUBLE_QUOTE=57, IF=58, END=59, CLASS=60, BOOLEAN=61, 
		INT=62, DECIMAL=63, ID=64, STRING=65, NEWLINE=66, WS=67, COMMENTS=68;
	public static final String[] tokenNames = {
		"<INVALID>", "'output'", "'on'", "'create'", "'constant'", "'elseif'", 
		"'me'", "'until'", "'public'", "'private'", "'alert'", "'detect'", "'always'", 
		"'check'", "'parent'", "'blueprint'", "'system'", "'is'", "'cast'", "'input'", 
		"'say'", "'now'", "'while'", "'package'", "'times'", "'repeat'", "'else'", 
		"'returns'", "'return'", "'and'", "'or'", "'undefined'", "'action'", "':'", 
		"'integer'", "'number'", "'text'", "'boolean'", "'use'", "NOT", "NOTEQUALS", 
		"'.'", "','", "'='", "'>'", "'>='", "'<'", "'<='", "'+'", "'-'", "'*'", 
		"'/'", "'mod'", "'['", "']'", "'('", "')'", "'\"'", "'if'", "'end'", "'class'", 
		"BOOLEAN", "INT", "DECIMAL", "ID", "STRING", "NEWLINE", "WS", "COMMENTS"
	};
	public static final int
		RULE_start = 0, RULE_package_rule = 1, RULE_reference = 2, RULE_class_declaration = 3, 
		RULE_no_class_stmnts = 4, RULE_inherit_stmnts = 5, RULE_access_modifier = 6, 
		RULE_class_stmnts = 7, RULE_method_declaration = 8, RULE_formal_parameter = 9, 
		RULE_qualified_name = 10, RULE_block = 11, RULE_statement = 12, RULE_solo_method_call = 13, 
		RULE_alert_statement = 14, RULE_check_statement = 15, RULE_detect_parameter = 16, 
		RULE_print_statement = 17, RULE_speak_statement = 18, RULE_return_statement = 19, 
		RULE_generic_declaration = 20, RULE_generic_statement = 21, RULE_class_type = 22, 
		RULE_assignment_declaration = 23, RULE_assignment_statement = 24, RULE_assign_right_hand_side = 25, 
		RULE_if_statement = 26, RULE_loop_statement = 27, RULE_selector = 28, 
		RULE_root_expression = 29, RULE_expression = 30, RULE_function_expression_list = 31;
	public static final String[] ruleNames = {
		"start", "package_rule", "reference", "class_declaration", "no_class_stmnts", 
		"inherit_stmnts", "access_modifier", "class_stmnts", "method_declaration", 
		"formal_parameter", "qualified_name", "block", "statement", "solo_method_call", 
		"alert_statement", "check_statement", "detect_parameter", "print_statement", 
		"speak_statement", "return_statement", "generic_declaration", "generic_statement", 
		"class_type", "assignment_declaration", "assignment_statement", "assign_right_hand_side", 
		"if_statement", "loop_statement", "selector", "root_expression", "expression", 
		"function_expression_list"
	};

	@Override
	public String getGrammarFileName() { return "Quorum.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public QuorumParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(QuorumParser.EOF, 0); }
		public Class_declarationContext class_declaration() {
			return getRuleContext(Class_declarationContext.class,0);
		}
		public Package_ruleContext package_rule() {
			return getRuleContext(Package_ruleContext.class,0);
		}
		public ReferenceContext reference() {
			return getRuleContext(ReferenceContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(64); package_rule();
				setState(66); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(65); reference();
					}
					}
					setState(68); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==USE );
				}
				break;

			case 2:
				{
				setState(71); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(70); reference();
					}
					}
					setState(73); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==USE );
				setState(75); package_rule();
				}
				break;

			case 3:
				{
				setState(77); package_rule();
				}
				break;

			case 4:
				{
				setState(79); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(78); reference();
					}
					}
					setState(81); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==USE );
				}
				break;

			case 5:
				{
				}
				break;
			}
			setState(86); class_declaration();
			setState(87); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Package_ruleContext extends ParserRuleContext {
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public TerminalNode PACKAGE_NAME() { return getToken(QuorumParser.PACKAGE_NAME, 0); }
		public Package_ruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_package_rule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterPackage_rule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitPackage_rule(this);
		}
	}

	public final Package_ruleContext package_rule() throws RecognitionException {
		Package_ruleContext _localctx = new Package_ruleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_package_rule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89); match(PACKAGE_NAME);
			setState(90); qualified_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReferenceContext extends ParserRuleContext {
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public TerminalNode USE() { return getToken(QuorumParser.USE, 0); }
		public ReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitReference(this);
		}
	}

	public final ReferenceContext reference() throws RecognitionException {
		ReferenceContext _localctx = new ReferenceContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_reference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92); match(USE);
			setState(93); qualified_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_declarationContext extends ParserRuleContext {
		public No_class_stmntsContext no_class_stmnts() {
			return getRuleContext(No_class_stmntsContext.class,0);
		}
		public Class_stmntsContext class_stmnts(int i) {
			return getRuleContext(Class_stmntsContext.class,i);
		}
		public Generic_declarationContext generic_declaration() {
			return getRuleContext(Generic_declarationContext.class,0);
		}
		public List<Class_stmntsContext> class_stmnts() {
			return getRuleContexts(Class_stmntsContext.class);
		}
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public Inherit_stmntsContext inherit_stmnts() {
			return getRuleContext(Inherit_stmntsContext.class,0);
		}
		public TerminalNode END() { return getToken(QuorumParser.END, 0); }
		public TerminalNode CLASS() { return getToken(QuorumParser.CLASS, 0); }
		public Class_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterClass_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitClass_declaration(this);
		}
	}

	public final Class_declarationContext class_declaration() throws RecognitionException {
		Class_declarationContext _localctx = new Class_declarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_class_declaration);
		int _la;
		try {
			setState(111);
			switch (_input.LA(1)) {
			case CLASS:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(95); match(CLASS);
				setState(96); match(ID);
				setState(98);
				_la = _input.LA(1);
				if (_la==LESS) {
					{
					setState(97); generic_declaration();
					}
				}

				setState(101);
				_la = _input.LA(1);
				if (_la==INHERITS) {
					{
					setState(100); inherit_stmnts();
					}
				}

				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 2)) & ~0x3f) == 0 && ((1L << (_la - 2)) & ((1L << (ON - 2)) | (1L << (CONSTANT - 2)) | (1L << (ME - 2)) | (1L << (PUBLIC - 2)) | (1L << (PRIVATE - 2)) | (1L << (PARENT - 2)) | (1L << (BLUEPRINT - 2)) | (1L << (NATIVE - 2)) | (1L << (ACTION - 2)) | (1L << (INTEGER_KEYWORD - 2)) | (1L << (NUMBER_KEYWORD - 2)) | (1L << (TEXT - 2)) | (1L << (BOOLEAN_KEYWORD - 2)) | (1L << (ID - 2)))) != 0)) {
					{
					{
					setState(103); class_stmnts();
					}
					}
					setState(108);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(109); match(END);
				}
				}
				break;
			case OUTPUT:
			case ON:
			case CONSTANT:
			case ME:
			case PUBLIC:
			case PRIVATE:
			case ALERT:
			case CHECK:
			case PARENT:
			case BLUEPRINT:
			case NATIVE:
			case SAY:
			case REPEAT:
			case RETURN:
			case ACTION:
			case INTEGER_KEYWORD:
			case NUMBER_KEYWORD:
			case TEXT:
			case BOOLEAN_KEYWORD:
			case IF:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(110); no_class_stmnts();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class No_class_stmntsContext extends ParserRuleContext {
		public Access_modifierContext access_modifier(int i) {
			return getRuleContext(Access_modifierContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public Method_declarationContext method_declaration(int i) {
			return getRuleContext(Method_declarationContext.class,i);
		}
		public List<Method_declarationContext> method_declaration() {
			return getRuleContexts(Method_declarationContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<Access_modifierContext> access_modifier() {
			return getRuleContexts(Access_modifierContext.class);
		}
		public No_class_stmntsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_no_class_stmnts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterNo_class_stmnts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitNo_class_stmnts(this);
		}
	}

	public final No_class_stmntsContext no_class_stmnts() throws RecognitionException {
		No_class_stmntsContext _localctx = new No_class_stmntsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_no_class_stmnts);
		int _la;
		try {
			setState(126);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(114); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(113); statement();
					}
					}
					setState(116); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((((_la - 1)) & ~0x3f) == 0 && ((1L << (_la - 1)) & ((1L << (OUTPUT - 1)) | (1L << (CONSTANT - 1)) | (1L << (ME - 1)) | (1L << (PUBLIC - 1)) | (1L << (PRIVATE - 1)) | (1L << (ALERT - 1)) | (1L << (CHECK - 1)) | (1L << (PARENT - 1)) | (1L << (SAY - 1)) | (1L << (REPEAT - 1)) | (1L << (RETURN - 1)) | (1L << (INTEGER_KEYWORD - 1)) | (1L << (NUMBER_KEYWORD - 1)) | (1L << (TEXT - 1)) | (1L << (BOOLEAN_KEYWORD - 1)) | (1L << (IF - 1)) | (1L << (ID - 1)))) != 0) );
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(122); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(119);
					_la = _input.LA(1);
					if (_la==PUBLIC || _la==PRIVATE) {
						{
						setState(118); access_modifier();
						}
					}

					setState(121); method_declaration();
					}
					}
					setState(124); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ON) | (1L << PUBLIC) | (1L << PRIVATE) | (1L << BLUEPRINT) | (1L << NATIVE) | (1L << ACTION))) != 0) );
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Inherit_stmntsContext extends ParserRuleContext {
		public List<Qualified_nameContext> qualified_name() {
			return getRuleContexts(Qualified_nameContext.class);
		}
		public List<Generic_statementContext> generic_statement() {
			return getRuleContexts(Generic_statementContext.class);
		}
		public Qualified_nameContext qualified_name(int i) {
			return getRuleContext(Qualified_nameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(QuorumParser.COMMA); }
		public Generic_statementContext generic_statement(int i) {
			return getRuleContext(Generic_statementContext.class,i);
		}
		public TerminalNode INHERITS() { return getToken(QuorumParser.INHERITS, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(QuorumParser.COMMA, i);
		}
		public Inherit_stmntsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inherit_stmnts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterInherit_stmnts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitInherit_stmnts(this);
		}
	}

	public final Inherit_stmntsContext inherit_stmnts() throws RecognitionException {
		Inherit_stmntsContext _localctx = new Inherit_stmntsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_inherit_stmnts);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128); match(INHERITS);
			setState(129); qualified_name();
			setState(131);
			_la = _input.LA(1);
			if (_la==LESS) {
				{
				setState(130); generic_statement();
				}
			}

			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(133); match(COMMA);
				setState(134); qualified_name();
				setState(136);
				_la = _input.LA(1);
				if (_la==LESS) {
					{
					setState(135); generic_statement();
					}
				}

				}
				}
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Access_modifierContext extends ParserRuleContext {
		public TerminalNode PUBLIC() { return getToken(QuorumParser.PUBLIC, 0); }
		public TerminalNode PRIVATE() { return getToken(QuorumParser.PRIVATE, 0); }
		public Access_modifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_access_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterAccess_modifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitAccess_modifier(this);
		}
	}

	public final Access_modifierContext access_modifier() throws RecognitionException {
		Access_modifierContext _localctx = new Access_modifierContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_access_modifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			_la = _input.LA(1);
			if ( !(_la==PUBLIC || _la==PRIVATE) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_stmntsContext extends ParserRuleContext {
		public Assignment_statementContext assignment_statement() {
			return getRuleContext(Assignment_statementContext.class,0);
		}
		public Method_declarationContext method_declaration() {
			return getRuleContext(Method_declarationContext.class,0);
		}
		public Access_modifierContext access_modifier() {
			return getRuleContext(Access_modifierContext.class,0);
		}
		public Class_stmntsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_stmnts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterClass_stmnts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitClass_stmnts(this);
		}
	}

	public final Class_stmntsContext class_stmnts() throws RecognitionException {
		Class_stmntsContext _localctx = new Class_stmntsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_class_stmnts);
		int _la;
		try {
			setState(150);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(145); assignment_statement();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(147);
				_la = _input.LA(1);
				if (_la==PUBLIC || _la==PRIVATE) {
					{
					setState(146); access_modifier();
					}
				}

				setState(149); method_declaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Method_declarationContext extends ParserRuleContext {
		public Assignment_declarationContext return_type;
		public Assignment_declarationContext assignment_declaration() {
			return getRuleContext(Assignment_declarationContext.class,0);
		}
		public TerminalNode CREATE() { return getToken(QuorumParser.CREATE, 0); }
		public TerminalNode NATIVE() { return getToken(QuorumParser.NATIVE, 0); }
		public List<Formal_parameterContext> formal_parameter() {
			return getRuleContexts(Formal_parameterContext.class);
		}
		public Formal_parameterContext formal_parameter(int i) {
			return getRuleContext(Formal_parameterContext.class,i);
		}
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(QuorumParser.COMMA, i);
		}
		public TerminalNode ON() { return getToken(QuorumParser.ON, 0); }
		public TerminalNode ACTION() { return getToken(QuorumParser.ACTION, 0); }
		public List<TerminalNode> COMMA() { return getTokens(QuorumParser.COMMA); }
		public TerminalNode BLUEPRINT() { return getToken(QuorumParser.BLUEPRINT, 0); }
		public TerminalNode RETURNS() { return getToken(QuorumParser.RETURNS, 0); }
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public TerminalNode END() { return getToken(QuorumParser.END, 0); }
		public Method_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterMethod_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitMethod_declaration(this);
		}
	}

	public final Method_declarationContext method_declaration() throws RecognitionException {
		Method_declarationContext _localctx = new Method_declarationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_method_declaration);
		int _la;
		try {
			setState(222);
			switch (_input.LA(1)) {
			case ACTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(152); match(ACTION);
				setState(153); match(ID);
				setState(166);
				_la = _input.LA(1);
				if (_la==LEFT_PAREN) {
					{
					setState(154); match(LEFT_PAREN);
					setState(163);
					_la = _input.LA(1);
					if (((((_la - 34)) & ~0x3f) == 0 && ((1L << (_la - 34)) & ((1L << (INTEGER_KEYWORD - 34)) | (1L << (NUMBER_KEYWORD - 34)) | (1L << (TEXT - 34)) | (1L << (BOOLEAN_KEYWORD - 34)) | (1L << (ID - 34)))) != 0)) {
						{
						setState(155); formal_parameter();
						setState(160);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(156); match(COMMA);
							setState(157); formal_parameter();
							}
							}
							setState(162);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(165); match(RIGHT_PAREN);
					}
				}

				setState(170);
				_la = _input.LA(1);
				if (_la==RETURNS) {
					{
					setState(168); match(RETURNS);
					setState(169); ((Method_declarationContext)_localctx).return_type = assignment_declaration();
					}
				}

				setState(172); block();
				setState(173); match(END);
				}
				break;
			case BLUEPRINT:
				enterOuterAlt(_localctx, 2);
				{
				setState(175); match(BLUEPRINT);
				setState(176); match(ACTION);
				setState(177); match(ID);
				setState(190);
				_la = _input.LA(1);
				if (_la==LEFT_PAREN) {
					{
					setState(178); match(LEFT_PAREN);
					setState(187);
					_la = _input.LA(1);
					if (((((_la - 34)) & ~0x3f) == 0 && ((1L << (_la - 34)) & ((1L << (INTEGER_KEYWORD - 34)) | (1L << (NUMBER_KEYWORD - 34)) | (1L << (TEXT - 34)) | (1L << (BOOLEAN_KEYWORD - 34)) | (1L << (ID - 34)))) != 0)) {
						{
						setState(179); formal_parameter();
						setState(184);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(180); match(COMMA);
							setState(181); formal_parameter();
							}
							}
							setState(186);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(189); match(RIGHT_PAREN);
					}
				}

				setState(194);
				_la = _input.LA(1);
				if (_la==RETURNS) {
					{
					setState(192); match(RETURNS);
					setState(193); ((Method_declarationContext)_localctx).return_type = assignment_declaration();
					}
				}

				}
				break;
			case NATIVE:
				enterOuterAlt(_localctx, 3);
				{
				setState(196); match(NATIVE);
				setState(197); match(ACTION);
				setState(198); match(ID);
				setState(211);
				_la = _input.LA(1);
				if (_la==LEFT_PAREN) {
					{
					setState(199); match(LEFT_PAREN);
					setState(208);
					_la = _input.LA(1);
					if (((((_la - 34)) & ~0x3f) == 0 && ((1L << (_la - 34)) & ((1L << (INTEGER_KEYWORD - 34)) | (1L << (NUMBER_KEYWORD - 34)) | (1L << (TEXT - 34)) | (1L << (BOOLEAN_KEYWORD - 34)) | (1L << (ID - 34)))) != 0)) {
						{
						setState(200); formal_parameter();
						setState(205);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(201); match(COMMA);
							setState(202); formal_parameter();
							}
							}
							setState(207);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(210); match(RIGHT_PAREN);
					}
				}

				setState(215);
				_la = _input.LA(1);
				if (_la==RETURNS) {
					{
					setState(213); match(RETURNS);
					setState(214); assignment_declaration();
					}
				}

				}
				break;
			case ON:
				enterOuterAlt(_localctx, 4);
				{
				setState(217); match(ON);
				setState(218); match(CREATE);
				setState(219); block();
				setState(220); match(END);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Formal_parameterContext extends ParserRuleContext {
		public Assignment_declarationContext assignment_declaration() {
			return getRuleContext(Assignment_declarationContext.class,0);
		}
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public Formal_parameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterFormal_parameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitFormal_parameter(this);
		}
	}

	public final Formal_parameterContext formal_parameter() throws RecognitionException {
		Formal_parameterContext _localctx = new Formal_parameterContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_formal_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224); assignment_declaration();
			setState(225); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Qualified_nameContext extends ParserRuleContext {
		public Token ID;
		public List<Token> ids = new ArrayList<Token>();
		public List<TerminalNode> ID() { return getTokens(QuorumParser.ID); }
		public TerminalNode PERIOD(int i) {
			return getToken(QuorumParser.PERIOD, i);
		}
		public List<TerminalNode> PERIOD() { return getTokens(QuorumParser.PERIOD); }
		public TerminalNode ID(int i) {
			return getToken(QuorumParser.ID, i);
		}
		public Qualified_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualified_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterQualified_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitQualified_name(this);
		}
	}

	public final Qualified_nameContext qualified_name() throws RecognitionException {
		Qualified_nameContext _localctx = new Qualified_nameContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_qualified_name);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(227); ((Qualified_nameContext)_localctx).ID = match(ID);
			((Qualified_nameContext)_localctx).ids.add(((Qualified_nameContext)_localctx).ID);
			setState(232);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(228); match(PERIOD);
					setState(229); ((Qualified_nameContext)_localctx).ID = match(ID);
					((Qualified_nameContext)_localctx).ids.add(((Qualified_nameContext)_localctx).ID);
					}
					} 
				}
				setState(234);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 1)) & ~0x3f) == 0 && ((1L << (_la - 1)) & ((1L << (OUTPUT - 1)) | (1L << (CONSTANT - 1)) | (1L << (ME - 1)) | (1L << (PUBLIC - 1)) | (1L << (PRIVATE - 1)) | (1L << (ALERT - 1)) | (1L << (CHECK - 1)) | (1L << (PARENT - 1)) | (1L << (SAY - 1)) | (1L << (REPEAT - 1)) | (1L << (RETURN - 1)) | (1L << (INTEGER_KEYWORD - 1)) | (1L << (NUMBER_KEYWORD - 1)) | (1L << (TEXT - 1)) | (1L << (BOOLEAN_KEYWORD - 1)) | (1L << (IF - 1)) | (1L << (ID - 1)))) != 0)) {
				{
				{
				setState(235); statement();
				}
				}
				setState(240);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public Loop_statementContext loop_statement() {
			return getRuleContext(Loop_statementContext.class,0);
		}
		public Print_statementContext print_statement() {
			return getRuleContext(Print_statementContext.class,0);
		}
		public Alert_statementContext alert_statement() {
			return getRuleContext(Alert_statementContext.class,0);
		}
		public Speak_statementContext speak_statement() {
			return getRuleContext(Speak_statementContext.class,0);
		}
		public Assignment_statementContext assignment_statement() {
			return getRuleContext(Assignment_statementContext.class,0);
		}
		public Solo_method_callContext solo_method_call() {
			return getRuleContext(Solo_method_callContext.class,0);
		}
		public Check_statementContext check_statement() {
			return getRuleContext(Check_statementContext.class,0);
		}
		public Return_statementContext return_statement() {
			return getRuleContext(Return_statementContext.class,0);
		}
		public If_statementContext if_statement() {
			return getRuleContext(If_statementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_statement);
		try {
			setState(250);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(241); solo_method_call();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(242); if_statement();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(243); assignment_statement();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(244); loop_statement();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(245); return_statement();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(246); print_statement();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(247); speak_statement();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(248); check_statement();
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(249); alert_statement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Solo_method_callContext extends ParserRuleContext {
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(QuorumParser.COMMA); }
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode COLON(int i) {
			return getToken(QuorumParser.COLON, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public TerminalNode ME() { return getToken(QuorumParser.ME, 0); }
		public TerminalNode PARENT() { return getToken(QuorumParser.PARENT, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public List<TerminalNode> COLON() { return getTokens(QuorumParser.COLON); }
		public TerminalNode COMMA(int i) {
			return getToken(QuorumParser.COMMA, i);
		}
		public Solo_method_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_solo_method_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterSolo_method_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitSolo_method_call(this);
		}
	}

	public final Solo_method_callContext solo_method_call() throws RecognitionException {
		Solo_method_callContext _localctx = new Solo_method_callContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_solo_method_call);
		int _la;
		try {
			setState(308);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(252); qualified_name();
				setState(255);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(253); match(COLON);
					setState(254); match(ID);
					}
				}

				setState(257); match(LEFT_PAREN);
				setState(266);
				_la = _input.LA(1);
				if (((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & ((1L << (ME - 6)) | (1L << (PARENT - 6)) | (1L << (CAST - 6)) | (1L << (INPUT - 6)) | (1L << (NULL - 6)) | (1L << (NOT - 6)) | (1L << (MINUS - 6)) | (1L << (LEFT_PAREN - 6)) | (1L << (BOOLEAN - 6)) | (1L << (INT - 6)) | (1L << (DECIMAL - 6)) | (1L << (ID - 6)) | (1L << (STRING - 6)))) != 0)) {
					{
					setState(258); expression(0);
					setState(263);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(259); match(COMMA);
						setState(260); expression(0);
						}
						}
						setState(265);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(268); match(RIGHT_PAREN);
				}
				break;
			case PARENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(270); match(PARENT);
				setState(271); match(COLON);
				setState(272); qualified_name();
				setState(273); match(COLON);
				setState(274); match(ID);
				setState(275); match(LEFT_PAREN);
				setState(284);
				_la = _input.LA(1);
				if (((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & ((1L << (ME - 6)) | (1L << (PARENT - 6)) | (1L << (CAST - 6)) | (1L << (INPUT - 6)) | (1L << (NULL - 6)) | (1L << (NOT - 6)) | (1L << (MINUS - 6)) | (1L << (LEFT_PAREN - 6)) | (1L << (BOOLEAN - 6)) | (1L << (INT - 6)) | (1L << (DECIMAL - 6)) | (1L << (ID - 6)) | (1L << (STRING - 6)))) != 0)) {
					{
					setState(276); expression(0);
					setState(281);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(277); match(COMMA);
						setState(278); expression(0);
						}
						}
						setState(283);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(286); match(RIGHT_PAREN);
				}
				break;
			case ME:
				enterOuterAlt(_localctx, 3);
				{
				setState(288); match(ME);
				setState(289); match(COLON);
				setState(290); qualified_name();
				setState(293);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(291); match(COLON);
					setState(292); match(ID);
					}
				}

				setState(295); match(LEFT_PAREN);
				setState(304);
				_la = _input.LA(1);
				if (((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & ((1L << (ME - 6)) | (1L << (PARENT - 6)) | (1L << (CAST - 6)) | (1L << (INPUT - 6)) | (1L << (NULL - 6)) | (1L << (NOT - 6)) | (1L << (MINUS - 6)) | (1L << (LEFT_PAREN - 6)) | (1L << (BOOLEAN - 6)) | (1L << (INT - 6)) | (1L << (DECIMAL - 6)) | (1L << (ID - 6)) | (1L << (STRING - 6)))) != 0)) {
					{
					setState(296); expression(0);
					setState(301);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(297); match(COMMA);
						setState(298); expression(0);
						}
						}
						setState(303);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(306); match(RIGHT_PAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Alert_statementContext extends ParserRuleContext {
		public Root_expressionContext root_expression() {
			return getRuleContext(Root_expressionContext.class,0);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public TerminalNode ALERT() { return getToken(QuorumParser.ALERT, 0); }
		public Alert_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alert_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterAlert_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitAlert_statement(this);
		}
	}

	public final Alert_statementContext alert_statement() throws RecognitionException {
		Alert_statementContext _localctx = new Alert_statementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_alert_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310); match(ALERT);
			setState(311); match(LEFT_PAREN);
			setState(312); root_expression();
			setState(313); match(RIGHT_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Check_statementContext extends ParserRuleContext {
		public TerminalNode CHECK() { return getToken(QuorumParser.CHECK, 0); }
		public TerminalNode DETECT() { return getToken(QuorumParser.DETECT, 0); }
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public Detect_parameterContext detect_parameter() {
			return getRuleContext(Detect_parameterContext.class,0);
		}
		public TerminalNode ALWAYS() { return getToken(QuorumParser.ALWAYS, 0); }
		public TerminalNode END() { return getToken(QuorumParser.END, 0); }
		public Check_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_check_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterCheck_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitCheck_statement(this);
		}
	}

	public final Check_statementContext check_statement() throws RecognitionException {
		Check_statementContext _localctx = new Check_statementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_check_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315); match(CHECK);
			setState(316); block();
			setState(331);
			switch (_input.LA(1)) {
			case DETECT:
				{
				setState(321); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(317); match(DETECT);
					setState(318); detect_parameter();
					setState(319); block();
					}
					}
					setState(323); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DETECT );
				setState(327);
				_la = _input.LA(1);
				if (_la==ALWAYS) {
					{
					setState(325); match(ALWAYS);
					setState(326); block();
					}
				}

				}
				break;
			case ALWAYS:
				{
				setState(329); match(ALWAYS);
				setState(330); block();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(333); match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Detect_parameterContext extends ParserRuleContext {
		public List<Qualified_nameContext> qualified_name() {
			return getRuleContexts(Qualified_nameContext.class);
		}
		public Qualified_nameContext qualified_name(int i) {
			return getRuleContext(Qualified_nameContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(QuorumParser.OR); }
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode INHERITS() { return getToken(QuorumParser.INHERITS, 0); }
		public TerminalNode OR(int i) {
			return getToken(QuorumParser.OR, i);
		}
		public Detect_parameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_detect_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterDetect_parameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitDetect_parameter(this);
		}
	}

	public final Detect_parameterContext detect_parameter() throws RecognitionException {
		Detect_parameterContext _localctx = new Detect_parameterContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_detect_parameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(335); match(ID);
			setState(345);
			_la = _input.LA(1);
			if (_la==INHERITS) {
				{
				setState(336); match(INHERITS);
				setState(337); qualified_name();
				setState(342);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OR) {
					{
					{
					setState(338); match(OR);
					setState(339); qualified_name();
					}
					}
					setState(344);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Print_statementContext extends ParserRuleContext {
		public Root_expressionContext root_expression() {
			return getRuleContext(Root_expressionContext.class,0);
		}
		public TerminalNode OUTPUT() { return getToken(QuorumParser.OUTPUT, 0); }
		public Print_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterPrint_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitPrint_statement(this);
		}
	}

	public final Print_statementContext print_statement() throws RecognitionException {
		Print_statementContext _localctx = new Print_statementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_print_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(347); match(OUTPUT);
			setState(348); root_expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Speak_statementContext extends ParserRuleContext {
		public TerminalNode SAY() { return getToken(QuorumParser.SAY, 0); }
		public Root_expressionContext root_expression() {
			return getRuleContext(Root_expressionContext.class,0);
		}
		public Speak_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_speak_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterSpeak_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitSpeak_statement(this);
		}
	}

	public final Speak_statementContext speak_statement() throws RecognitionException {
		Speak_statementContext _localctx = new Speak_statementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_speak_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350); match(SAY);
			setState(351); root_expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Return_statementContext extends ParserRuleContext {
		public TerminalNode NOW() { return getToken(QuorumParser.NOW, 0); }
		public Root_expressionContext root_expression() {
			return getRuleContext(Root_expressionContext.class,0);
		}
		public TerminalNode RETURN() { return getToken(QuorumParser.RETURN, 0); }
		public Return_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterReturn_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitReturn_statement(this);
		}
	}

	public final Return_statementContext return_statement() throws RecognitionException {
		Return_statementContext _localctx = new Return_statementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_return_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(353); match(RETURN);
			setState(356);
			switch (_input.LA(1)) {
			case ME:
			case PARENT:
			case CAST:
			case INPUT:
			case NULL:
			case NOT:
			case MINUS:
			case LEFT_PAREN:
			case BOOLEAN:
			case INT:
			case DECIMAL:
			case ID:
			case STRING:
				{
				setState(354); root_expression();
				}
				break;
			case NOW:
				{
				setState(355); match(NOW);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Generic_declarationContext extends ParserRuleContext {
		public Token ID;
		public List<Token> ids = new ArrayList<Token>();
		public TerminalNode LESS() { return getToken(QuorumParser.LESS, 0); }
		public List<TerminalNode> COMMA() { return getTokens(QuorumParser.COMMA); }
		public List<TerminalNode> ID() { return getTokens(QuorumParser.ID); }
		public TerminalNode GREATER() { return getToken(QuorumParser.GREATER, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(QuorumParser.COMMA, i);
		}
		public TerminalNode ID(int i) {
			return getToken(QuorumParser.ID, i);
		}
		public Generic_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generic_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterGeneric_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitGeneric_declaration(this);
		}
	}

	public final Generic_declarationContext generic_declaration() throws RecognitionException {
		Generic_declarationContext _localctx = new Generic_declarationContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_generic_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(358); match(LESS);
			setState(359); ((Generic_declarationContext)_localctx).ID = match(ID);
			((Generic_declarationContext)_localctx).ids.add(((Generic_declarationContext)_localctx).ID);
			setState(364);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(360); match(COMMA);
				setState(361); ((Generic_declarationContext)_localctx).ID = match(ID);
				((Generic_declarationContext)_localctx).ids.add(((Generic_declarationContext)_localctx).ID);
				}
				}
				setState(366);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(367); match(GREATER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Generic_statementContext extends ParserRuleContext {
		public List<Assignment_declarationContext> assignment_declaration() {
			return getRuleContexts(Assignment_declarationContext.class);
		}
		public Assignment_declarationContext assignment_declaration(int i) {
			return getRuleContext(Assignment_declarationContext.class,i);
		}
		public TerminalNode LESS() { return getToken(QuorumParser.LESS, 0); }
		public List<TerminalNode> COMMA() { return getTokens(QuorumParser.COMMA); }
		public TerminalNode GREATER() { return getToken(QuorumParser.GREATER, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(QuorumParser.COMMA, i);
		}
		public Generic_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generic_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterGeneric_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitGeneric_statement(this);
		}
	}

	public final Generic_statementContext generic_statement() throws RecognitionException {
		Generic_statementContext _localctx = new Generic_statementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_generic_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(369); match(LESS);
			setState(370); assignment_declaration();
			setState(375);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(371); match(COMMA);
				setState(372); assignment_declaration();
				}
				}
				setState(377);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(378); match(GREATER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_typeContext extends ParserRuleContext {
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public Class_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterClass_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitClass_type(this);
		}
	}

	public final Class_typeContext class_type() throws RecognitionException {
		Class_typeContext _localctx = new Class_typeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_class_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(380); qualified_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Assignment_declarationContext extends ParserRuleContext {
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public Generic_statementContext generic_statement() {
			return getRuleContext(Generic_statementContext.class,0);
		}
		public TerminalNode TEXT() { return getToken(QuorumParser.TEXT, 0); }
		public TerminalNode NUMBER_KEYWORD() { return getToken(QuorumParser.NUMBER_KEYWORD, 0); }
		public TerminalNode BOOLEAN_KEYWORD() { return getToken(QuorumParser.BOOLEAN_KEYWORD, 0); }
		public TerminalNode INTEGER_KEYWORD() { return getToken(QuorumParser.INTEGER_KEYWORD, 0); }
		public Assignment_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterAssignment_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitAssignment_declaration(this);
		}
	}

	public final Assignment_declarationContext assignment_declaration() throws RecognitionException {
		Assignment_declarationContext _localctx = new Assignment_declarationContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_assignment_declaration);
		int _la;
		try {
			setState(390);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(382); qualified_name();
				setState(384);
				_la = _input.LA(1);
				if (_la==LESS) {
					{
					setState(383); generic_statement();
					}
				}

				}
				break;
			case INTEGER_KEYWORD:
				enterOuterAlt(_localctx, 2);
				{
				setState(386); match(INTEGER_KEYWORD);
				}
				break;
			case NUMBER_KEYWORD:
				enterOuterAlt(_localctx, 3);
				{
				setState(387); match(NUMBER_KEYWORD);
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 4);
				{
				setState(388); match(TEXT);
				}
				break;
			case BOOLEAN_KEYWORD:
				enterOuterAlt(_localctx, 5);
				{
				setState(389); match(BOOLEAN_KEYWORD);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Assignment_statementContext extends ParserRuleContext {
		public SelectorContext sel;
		public Assign_right_hand_sideContext rhs;
		public Qualified_nameContext obj;
		public Qualified_nameContext parent;
		public Access_modifierContext modifier;
		public Assignment_declarationContext type;
		public Token name;
		public List<Qualified_nameContext> qualified_name() {
			return getRuleContexts(Qualified_nameContext.class);
		}
		public Assignment_declarationContext assignment_declaration() {
			return getRuleContext(Assignment_declarationContext.class,0);
		}
		public Qualified_nameContext qualified_name(int i) {
			return getRuleContext(Qualified_nameContext.class,i);
		}
		public Assign_right_hand_sideContext assign_right_hand_side() {
			return getRuleContext(Assign_right_hand_sideContext.class,0);
		}
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode COLON(int i) {
			return getToken(QuorumParser.COLON, i);
		}
		public TerminalNode PARENT() { return getToken(QuorumParser.PARENT, 0); }
		public SelectorContext selector() {
			return getRuleContext(SelectorContext.class,0);
		}
		public Access_modifierContext access_modifier() {
			return getRuleContext(Access_modifierContext.class,0);
		}
		public List<TerminalNode> COLON() { return getTokens(QuorumParser.COLON); }
		public TerminalNode CONSTANT() { return getToken(QuorumParser.CONSTANT, 0); }
		public Assignment_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterAssignment_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitAssignment_statement(this);
		}
	}

	public final Assignment_statementContext assignment_statement() throws RecognitionException {
		Assignment_statementContext _localctx = new Assignment_statementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_assignment_statement);
		int _la;
		try {
			setState(421);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(395);
				_la = _input.LA(1);
				if (_la==ME || _la==PARENT) {
					{
					setState(392); ((Assignment_statementContext)_localctx).sel = selector();
					setState(393); match(COLON);
					}
				}

				setState(397); match(ID);
				setState(398); ((Assignment_statementContext)_localctx).rhs = assign_right_hand_side();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(399); ((Assignment_statementContext)_localctx).obj = qualified_name();
				setState(404);
				switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
				case 1:
					{
					setState(400); match(COLON);
					setState(401); match(PARENT);
					setState(402); match(COLON);
					setState(403); ((Assignment_statementContext)_localctx).parent = qualified_name();
					}
					break;
				}
				setState(406); match(COLON);
				setState(407); match(ID);
				setState(408); ((Assignment_statementContext)_localctx).rhs = assign_right_hand_side();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(411);
				_la = _input.LA(1);
				if (_la==PUBLIC || _la==PRIVATE) {
					{
					setState(410); ((Assignment_statementContext)_localctx).modifier = access_modifier();
					}
				}

				setState(414);
				_la = _input.LA(1);
				if (_la==CONSTANT) {
					{
					setState(413); match(CONSTANT);
					}
				}

				setState(416); ((Assignment_statementContext)_localctx).type = assignment_declaration();
				setState(417); ((Assignment_statementContext)_localctx).name = match(ID);
				setState(419);
				_la = _input.LA(1);
				if (_la==EQUALITY) {
					{
					setState(418); ((Assignment_statementContext)_localctx).rhs = assign_right_hand_side();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Assign_right_hand_sideContext extends ParserRuleContext {
		public TerminalNode EQUALITY() { return getToken(QuorumParser.EQUALITY, 0); }
		public Root_expressionContext root_expression() {
			return getRuleContext(Root_expressionContext.class,0);
		}
		public Assign_right_hand_sideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_right_hand_side; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterAssign_right_hand_side(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitAssign_right_hand_side(this);
		}
	}

	public final Assign_right_hand_sideContext assign_right_hand_side() throws RecognitionException {
		Assign_right_hand_sideContext _localctx = new Assign_right_hand_sideContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_assign_right_hand_side);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(423); match(EQUALITY);
			setState(424); root_expression();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_statementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(QuorumParser.IF, 0); }
		public TerminalNode ELSE() { return getToken(QuorumParser.ELSE, 0); }
		public List<Root_expressionContext> root_expression() {
			return getRuleContexts(Root_expressionContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode ELSE_IF(int i) {
			return getToken(QuorumParser.ELSE_IF, i);
		}
		public List<TerminalNode> ELSE_IF() { return getTokens(QuorumParser.ELSE_IF); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public Root_expressionContext root_expression(int i) {
			return getRuleContext(Root_expressionContext.class,i);
		}
		public TerminalNode END() { return getToken(QuorumParser.END, 0); }
		public If_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterIf_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitIf_statement(this);
		}
	}

	public final If_statementContext if_statement() throws RecognitionException {
		If_statementContext _localctx = new If_statementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_if_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(426); match(IF);
			setState(427); root_expression();
			setState(428); block();
			setState(435);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELSE_IF) {
				{
				{
				setState(429); match(ELSE_IF);
				setState(430); root_expression();
				setState(431); block();
				}
				}
				setState(437);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(440);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(438); match(ELSE);
				setState(439); block();
				}
			}

			setState(442); match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Loop_statementContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(QuorumParser.WHILE, 0); }
		public TerminalNode TIMES() { return getToken(QuorumParser.TIMES, 0); }
		public TerminalNode REPEAT() { return getToken(QuorumParser.REPEAT, 0); }
		public Root_expressionContext root_expression() {
			return getRuleContext(Root_expressionContext.class,0);
		}
		public TerminalNode UNTIL() { return getToken(QuorumParser.UNTIL, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode END() { return getToken(QuorumParser.END, 0); }
		public Loop_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterLoop_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitLoop_statement(this);
		}
	}

	public final Loop_statementContext loop_statement() throws RecognitionException {
		Loop_statementContext _localctx = new Loop_statementContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_loop_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(444); match(REPEAT);
			setState(450);
			switch (_input.LA(1)) {
			case ME:
			case PARENT:
			case CAST:
			case INPUT:
			case NULL:
			case NOT:
			case MINUS:
			case LEFT_PAREN:
			case BOOLEAN:
			case INT:
			case DECIMAL:
			case ID:
			case STRING:
				{
				{
				setState(445); root_expression();
				setState(446); match(TIMES);
				}
				}
				break;
			case UNTIL:
			case WHILE:
				{
				{
				setState(448);
				_la = _input.LA(1);
				if ( !(_la==UNTIL || _la==WHILE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(449); root_expression();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(452); block();
			setState(453); match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectorContext extends ParserRuleContext {
		public TerminalNode ME() { return getToken(QuorumParser.ME, 0); }
		public TerminalNode PARENT() { return getToken(QuorumParser.PARENT, 0); }
		public TerminalNode COLON() { return getToken(QuorumParser.COLON, 0); }
		public SelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitSelector(this);
		}
	}

	public final SelectorContext selector() throws RecognitionException {
		SelectorContext _localctx = new SelectorContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_selector);
		try {
			setState(458);
			switch (_input.LA(1)) {
			case PARENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(455); match(PARENT);
				setState(456); match(COLON);
				}
				break;
			case ME:
				enterOuterAlt(_localctx, 2);
				{
				setState(457); match(ME);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Root_expressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Root_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterRoot_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitRoot_expression(this);
		}
	}

	public final Root_expressionContext root_expression() throws RecognitionException {
		Root_expressionContext _localctx = new Root_expressionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_root_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(460); expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public int _p;
		public TerminalNode INPUT() { return getToken(QuorumParser.INPUT, 0); }
		public TerminalNode INT() { return getToken(QuorumParser.INT, 0); }
		public TerminalNode AND() { return getToken(QuorumParser.AND, 0); }
		public Class_typeContext class_type() {
			return getRuleContext(Class_typeContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode INHERITS() { return getToken(QuorumParser.INHERITS, 0); }
		public TerminalNode ME() { return getToken(QuorumParser.ME, 0); }
		public TerminalNode DIVIDE() { return getToken(QuorumParser.DIVIDE, 0); }
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public Qualified_nameContext qualified_name(int i) {
			return getRuleContext(Qualified_nameContext.class,i);
		}
		public TerminalNode BOOLEAN() { return getToken(QuorumParser.BOOLEAN, 0); }
		public TerminalNode LESS_EQUAL() { return getToken(QuorumParser.LESS_EQUAL, 0); }
		public TerminalNode LESS() { return getToken(QuorumParser.LESS, 0); }
		public TerminalNode COLON(int i) {
			return getToken(QuorumParser.COLON, i);
		}
		public TerminalNode PLUS() { return getToken(QuorumParser.PLUS, 0); }
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public TerminalNode GREATER() { return getToken(QuorumParser.GREATER, 0); }
		public Function_expression_listContext function_expression_list() {
			return getRuleContext(Function_expression_listContext.class,0);
		}
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public TerminalNode CAST() { return getToken(QuorumParser.CAST, 0); }
		public List<Qualified_nameContext> qualified_name() {
			return getRuleContexts(Qualified_nameContext.class);
		}
		public Assignment_declarationContext assignment_declaration() {
			return getRuleContext(Assignment_declarationContext.class,0);
		}
		public TerminalNode EQUALITY() { return getToken(QuorumParser.EQUALITY, 0); }
		public TerminalNode GREATER_EQUAL() { return getToken(QuorumParser.GREATER_EQUAL, 0); }
		public TerminalNode MODULO() { return getToken(QuorumParser.MODULO, 0); }
		public TerminalNode OR() { return getToken(QuorumParser.OR, 0); }
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode MINUS() { return getToken(QuorumParser.MINUS, 0); }
		public TerminalNode MULTIPLY() { return getToken(QuorumParser.MULTIPLY, 0); }
		public TerminalNode PARENT() { return getToken(QuorumParser.PARENT, 0); }
		public SelectorContext selector() {
			return getRuleContext(SelectorContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(QuorumParser.COMMA, 0); }
		public TerminalNode NOT() { return getToken(QuorumParser.NOT, 0); }
		public TerminalNode NOTEQUALS() { return getToken(QuorumParser.NOTEQUALS, 0); }
		public List<TerminalNode> COLON() { return getTokens(QuorumParser.COLON); }
		public TerminalNode STRING() { return getToken(QuorumParser.STRING, 0); }
		public TerminalNode NULL() { return getToken(QuorumParser.NULL, 0); }
		public TerminalNode DECIMAL() { return getToken(QuorumParser.DECIMAL, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ExpressionContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState, _p);
		ExpressionContext _prevctx = _localctx;
		int _startState = 60;
		enterRecursionRule(_localctx, RULE_expression);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(535);
			switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
			case 1:
				{
				setState(463); match(MINUS);
				setState(464); expression(18);
				}
				break;

			case 2:
				{
				setState(465); match(NOT);
				setState(466); expression(17);
				}
				break;

			case 3:
				{
				setState(467); qualified_name();
				setState(470);
				switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
				case 1:
					{
					setState(468); match(COLON);
					setState(469); match(ID);
					}
					break;
				}
				}
				break;

			case 4:
				{
				setState(472); qualified_name();
				setState(473); match(COLON);
				setState(474); match(PARENT);
				setState(475); match(COLON);
				setState(476); qualified_name();
				setState(477); match(COLON);
				setState(478); match(ID);
				}
				break;

			case 5:
				{
				setState(480); qualified_name();
				setState(483);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(481); match(COLON);
					setState(482); match(ID);
					}
				}

				setState(485); match(LEFT_PAREN);
				setState(486); function_expression_list();
				setState(487); match(RIGHT_PAREN);
				}
				break;

			case 6:
				{
				setState(489); selector();
				setState(490); match(COLON);
				setState(491); qualified_name();
				}
				break;

			case 7:
				{
				setState(493); match(PARENT);
				setState(494); match(COLON);
				setState(495); qualified_name();
				setState(496); match(COLON);
				setState(497); match(ID);
				setState(498); match(LEFT_PAREN);
				setState(499); function_expression_list();
				setState(500); match(RIGHT_PAREN);
				}
				break;

			case 8:
				{
				setState(502); match(ME);
				setState(503); match(COLON);
				setState(504); qualified_name();
				setState(507);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(505); match(COLON);
					setState(506); match(ID);
					}
				}

				setState(509); match(LEFT_PAREN);
				setState(510); function_expression_list();
				setState(511); match(RIGHT_PAREN);
				}
				break;

			case 9:
				{
				setState(513); match(CAST);
				setState(514); match(LEFT_PAREN);
				setState(515); assignment_declaration();
				setState(516); match(COMMA);
				setState(517); expression(0);
				setState(518); match(RIGHT_PAREN);
				}
				break;

			case 10:
				{
				setState(520); match(INT);
				}
				break;

			case 11:
				{
				setState(521); match(BOOLEAN);
				}
				break;

			case 12:
				{
				setState(522); match(DECIMAL);
				}
				break;

			case 13:
				{
				setState(523); match(STRING);
				}
				break;

			case 14:
				{
				setState(524); match(NULL);
				}
				break;

			case 15:
				{
				setState(525); match(ME);
				}
				break;

			case 16:
				{
				setState(526); match(INPUT);
				setState(527); match(LEFT_PAREN);
				setState(528); expression(0);
				setState(529); match(RIGHT_PAREN);
				}
				break;

			case 17:
				{
				setState(531); match(LEFT_PAREN);
				setState(532); expression(0);
				setState(533); match(RIGHT_PAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(560);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,67,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(558);
					switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(537);
						if (!(15 >= _localctx._p)) throw new FailedPredicateException(this, "15 >= $_p");
						setState(538);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLY) | (1L << DIVIDE) | (1L << MODULO))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(539); expression(16);
						}
						break;

					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(540);
						if (!(14 >= _localctx._p)) throw new FailedPredicateException(this, "14 >= $_p");
						setState(541);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(542); expression(15);
						}
						break;

					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(543);
						if (!(13 >= _localctx._p)) throw new FailedPredicateException(this, "13 >= $_p");
						setState(544);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER) | (1L << GREATER_EQUAL) | (1L << LESS) | (1L << LESS_EQUAL))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(545); expression(14);
						}
						break;

					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(546);
						if (!(11 >= _localctx._p)) throw new FailedPredicateException(this, "11 >= $_p");
						setState(547);
						_la = _input.LA(1);
						if ( !(_la==NOTEQUALS || _la==EQUALITY) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(548); expression(12);
						}
						break;

					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(549);
						if (!(10 >= _localctx._p)) throw new FailedPredicateException(this, "10 >= $_p");
						{
						setState(550); match(AND);
						}
						setState(551); expression(11);
						}
						break;

					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(552);
						if (!(9 >= _localctx._p)) throw new FailedPredicateException(this, "9 >= $_p");
						{
						setState(553); match(OR);
						}
						setState(554); expression(10);
						}
						break;

					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState, _p);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(555);
						if (!(12 >= _localctx._p)) throw new FailedPredicateException(this, "12 >= $_p");
						setState(556); match(INHERITS);
						setState(557); class_type();
						}
						break;
					}
					} 
				}
				setState(562);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,67,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Function_expression_listContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(QuorumParser.COMMA); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode COMMA(int i) {
			return getToken(QuorumParser.COMMA, i);
		}
		public Function_expression_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_expression_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterFunction_expression_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitFunction_expression_list(this);
		}
	}

	public final Function_expression_listContext function_expression_list() throws RecognitionException {
		Function_expression_listContext _localctx = new Function_expression_listContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_function_expression_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(571);
			_la = _input.LA(1);
			if (((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & ((1L << (ME - 6)) | (1L << (PARENT - 6)) | (1L << (CAST - 6)) | (1L << (INPUT - 6)) | (1L << (NULL - 6)) | (1L << (NOT - 6)) | (1L << (MINUS - 6)) | (1L << (LEFT_PAREN - 6)) | (1L << (BOOLEAN - 6)) | (1L << (INT - 6)) | (1L << (DECIMAL - 6)) | (1L << (ID - 6)) | (1L << (STRING - 6)))) != 0)) {
				{
				setState(563); expression(0);
				setState(568);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(564); match(COMMA);
					setState(565); expression(0);
					}
					}
					setState(570);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 30: return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return 15 >= _localctx._p;

		case 1: return 14 >= _localctx._p;

		case 2: return 13 >= _localctx._p;

		case 3: return 11 >= _localctx._p;

		case 4: return 10 >= _localctx._p;

		case 5: return 9 >= _localctx._p;

		case 6: return 12 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3F\u0240\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\6\2E\n\2\r\2\16\2F\3\2\6\2J\n\2\r\2\16\2K\3\2\3\2\3\2\3\2"+
		"\6\2R\n\2\r\2\16\2S\3\2\5\2W\n\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\5\5e\n\5\3\5\5\5h\n\5\3\5\7\5k\n\5\f\5\16\5n\13\5\3\5\3\5\5"+
		"\5r\n\5\3\6\6\6u\n\6\r\6\16\6v\3\6\5\6z\n\6\3\6\6\6}\n\6\r\6\16\6~\5\6"+
		"\u0081\n\6\3\7\3\7\3\7\5\7\u0086\n\7\3\7\3\7\3\7\5\7\u008b\n\7\7\7\u008d"+
		"\n\7\f\7\16\7\u0090\13\7\3\b\3\b\3\t\3\t\5\t\u0096\n\t\3\t\5\t\u0099\n"+
		"\t\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00a1\n\n\f\n\16\n\u00a4\13\n\5\n\u00a6"+
		"\n\n\3\n\5\n\u00a9\n\n\3\n\3\n\5\n\u00ad\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\7\n\u00b9\n\n\f\n\16\n\u00bc\13\n\5\n\u00be\n\n\3\n\5\n"+
		"\u00c1\n\n\3\n\3\n\5\n\u00c5\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00ce"+
		"\n\n\f\n\16\n\u00d1\13\n\5\n\u00d3\n\n\3\n\5\n\u00d6\n\n\3\n\3\n\5\n\u00da"+
		"\n\n\3\n\3\n\3\n\3\n\3\n\5\n\u00e1\n\n\3\13\3\13\3\13\3\f\3\f\3\f\7\f"+
		"\u00e9\n\f\f\f\16\f\u00ec\13\f\3\r\7\r\u00ef\n\r\f\r\16\r\u00f2\13\r\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00fd\n\16\3\17\3\17"+
		"\3\17\5\17\u0102\n\17\3\17\3\17\3\17\3\17\7\17\u0108\n\17\f\17\16\17\u010b"+
		"\13\17\5\17\u010d\n\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\7\17\u011a\n\17\f\17\16\17\u011d\13\17\5\17\u011f\n\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\5\17\u0128\n\17\3\17\3\17\3\17\3\17\7\17"+
		"\u012e\n\17\f\17\16\17\u0131\13\17\5\17\u0133\n\17\3\17\3\17\5\17\u0137"+
		"\n\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\6\21\u0144"+
		"\n\21\r\21\16\21\u0145\3\21\3\21\5\21\u014a\n\21\3\21\3\21\5\21\u014e"+
		"\n\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\7\22\u0157\n\22\f\22\16\22\u015a"+
		"\13\22\5\22\u015c\n\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\5"+
		"\25\u0167\n\25\3\26\3\26\3\26\3\26\7\26\u016d\n\26\f\26\16\26\u0170\13"+
		"\26\3\26\3\26\3\27\3\27\3\27\3\27\7\27\u0178\n\27\f\27\16\27\u017b\13"+
		"\27\3\27\3\27\3\30\3\30\3\31\3\31\5\31\u0183\n\31\3\31\3\31\3\31\3\31"+
		"\5\31\u0189\n\31\3\32\3\32\3\32\5\32\u018e\n\32\3\32\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\5\32\u0197\n\32\3\32\3\32\3\32\3\32\3\32\5\32\u019e\n\32"+
		"\3\32\5\32\u01a1\n\32\3\32\3\32\3\32\5\32\u01a6\n\32\5\32\u01a8\n\32\3"+
		"\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\7\34\u01b4\n\34\f\34"+
		"\16\34\u01b7\13\34\3\34\3\34\5\34\u01bb\n\34\3\34\3\34\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\5\35\u01c5\n\35\3\35\3\35\3\35\3\36\3\36\3\36\5\36\u01cd"+
		"\n\36\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \5 \u01d9\n \3 \3 \3 \3 \3 \3 "+
		"\3 \3 \3 \3 \3 \5 \u01e6\n \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 "+
		"\3 \3 \3 \3 \3 \3 \3 \3 \5 \u01fe\n \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 "+
		"\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \5 \u021a\n \3 \3 \3 \3 "+
		"\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \7 \u0231\n \f \16"+
		" \u0234\13 \3!\3!\3!\7!\u0239\n!\f!\16!\u023c\13!\5!\u023e\n!\3!\2\"\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@\2\b\3"+
		"\2\n\13\4\2\t\t\30\30\3\2\64\66\3\2\62\63\3\2.\61\4\2**--\u028a\2V\3\2"+
		"\2\2\4[\3\2\2\2\6^\3\2\2\2\bq\3\2\2\2\n\u0080\3\2\2\2\f\u0082\3\2\2\2"+
		"\16\u0091\3\2\2\2\20\u0098\3\2\2\2\22\u00e0\3\2\2\2\24\u00e2\3\2\2\2\26"+
		"\u00e5\3\2\2\2\30\u00f0\3\2\2\2\32\u00fc\3\2\2\2\34\u0136\3\2\2\2\36\u0138"+
		"\3\2\2\2 \u013d\3\2\2\2\"\u0151\3\2\2\2$\u015d\3\2\2\2&\u0160\3\2\2\2"+
		"(\u0163\3\2\2\2*\u0168\3\2\2\2,\u0173\3\2\2\2.\u017e\3\2\2\2\60\u0188"+
		"\3\2\2\2\62\u01a7\3\2\2\2\64\u01a9\3\2\2\2\66\u01ac\3\2\2\28\u01be\3\2"+
		"\2\2:\u01cc\3\2\2\2<\u01ce\3\2\2\2>\u0219\3\2\2\2@\u023d\3\2\2\2BD\5\4"+
		"\3\2CE\5\6\4\2DC\3\2\2\2EF\3\2\2\2FD\3\2\2\2FG\3\2\2\2GW\3\2\2\2HJ\5\6"+
		"\4\2IH\3\2\2\2JK\3\2\2\2KI\3\2\2\2KL\3\2\2\2LM\3\2\2\2MN\5\4\3\2NW\3\2"+
		"\2\2OW\5\4\3\2PR\5\6\4\2QP\3\2\2\2RS\3\2\2\2SQ\3\2\2\2ST\3\2\2\2TW\3\2"+
		"\2\2UW\3\2\2\2VB\3\2\2\2VI\3\2\2\2VO\3\2\2\2VQ\3\2\2\2VU\3\2\2\2WX\3\2"+
		"\2\2XY\5\b\5\2YZ\7\2\2\3Z\3\3\2\2\2[\\\7\31\2\2\\]\5\26\f\2]\5\3\2\2\2"+
		"^_\7(\2\2_`\5\26\f\2`\7\3\2\2\2ab\7>\2\2bd\7B\2\2ce\5*\26\2dc\3\2\2\2"+
		"de\3\2\2\2eg\3\2\2\2fh\5\f\7\2gf\3\2\2\2gh\3\2\2\2hl\3\2\2\2ik\5\20\t"+
		"\2ji\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mo\3\2\2\2nl\3\2\2\2or\7=\2"+
		"\2pr\5\n\6\2qa\3\2\2\2qp\3\2\2\2r\t\3\2\2\2su\5\32\16\2ts\3\2\2\2uv\3"+
		"\2\2\2vt\3\2\2\2vw\3\2\2\2w\u0081\3\2\2\2xz\5\16\b\2yx\3\2\2\2yz\3\2\2"+
		"\2z{\3\2\2\2{}\5\22\n\2|y\3\2\2\2}~\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177"+
		"\u0081\3\2\2\2\u0080t\3\2\2\2\u0080|\3\2\2\2\u0081\13\3\2\2\2\u0082\u0083"+
		"\7\23\2\2\u0083\u0085\5\26\f\2\u0084\u0086\5,\27\2\u0085\u0084\3\2\2\2"+
		"\u0085\u0086\3\2\2\2\u0086\u008e\3\2\2\2\u0087\u0088\7,\2\2\u0088\u008a"+
		"\5\26\f\2\u0089\u008b\5,\27\2\u008a\u0089\3\2\2\2\u008a\u008b\3\2\2\2"+
		"\u008b\u008d\3\2\2\2\u008c\u0087\3\2\2\2\u008d\u0090\3\2\2\2\u008e\u008c"+
		"\3\2\2\2\u008e\u008f\3\2\2\2\u008f\r\3\2\2\2\u0090\u008e\3\2\2\2\u0091"+
		"\u0092\t\2\2\2\u0092\17\3\2\2\2\u0093\u0099\5\62\32\2\u0094\u0096\5\16"+
		"\b\2\u0095\u0094\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\3\2\2\2\u0097"+
		"\u0099\5\22\n\2\u0098\u0093\3\2\2\2\u0098\u0095\3\2\2\2\u0099\21\3\2\2"+
		"\2\u009a\u009b\7\"\2\2\u009b\u00a8\7B\2\2\u009c\u00a5\79\2\2\u009d\u00a2"+
		"\5\24\13\2\u009e\u009f\7,\2\2\u009f\u00a1\5\24\13\2\u00a0\u009e\3\2\2"+
		"\2\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a6"+
		"\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u009d\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6"+
		"\u00a7\3\2\2\2\u00a7\u00a9\7:\2\2\u00a8\u009c\3\2\2\2\u00a8\u00a9\3\2"+
		"\2\2\u00a9\u00ac\3\2\2\2\u00aa\u00ab\7\35\2\2\u00ab\u00ad\5\60\31\2\u00ac"+
		"\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\5\30"+
		"\r\2\u00af\u00b0\7=\2\2\u00b0\u00e1\3\2\2\2\u00b1\u00b2\7\21\2\2\u00b2"+
		"\u00b3\7\"\2\2\u00b3\u00c0\7B\2\2\u00b4\u00bd\79\2\2\u00b5\u00ba\5\24"+
		"\13\2\u00b6\u00b7\7,\2\2\u00b7\u00b9\5\24\13\2\u00b8\u00b6\3\2\2\2\u00b9"+
		"\u00bc\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00be\3\2"+
		"\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00b5\3\2\2\2\u00bd\u00be\3\2\2\2\u00be"+
		"\u00bf\3\2\2\2\u00bf\u00c1\7:\2\2\u00c0\u00b4\3\2\2\2\u00c0\u00c1\3\2"+
		"\2\2\u00c1\u00c4\3\2\2\2\u00c2\u00c3\7\35\2\2\u00c3\u00c5\5\60\31\2\u00c4"+
		"\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00e1\3\2\2\2\u00c6\u00c7\7\22"+
		"\2\2\u00c7\u00c8\7\"\2\2\u00c8\u00d5\7B\2\2\u00c9\u00d2\79\2\2\u00ca\u00cf"+
		"\5\24\13\2\u00cb\u00cc\7,\2\2\u00cc\u00ce\5\24\13\2\u00cd\u00cb\3\2\2"+
		"\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d3"+
		"\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2\u00ca\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3"+
		"\u00d4\3\2\2\2\u00d4\u00d6\7:\2\2\u00d5\u00c9\3\2\2\2\u00d5\u00d6\3\2"+
		"\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d8\7\35\2\2\u00d8\u00da\5\60\31\2\u00d9"+
		"\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00e1\3\2\2\2\u00db\u00dc\7\4"+
		"\2\2\u00dc\u00dd\7\5\2\2\u00dd\u00de\5\30\r\2\u00de\u00df\7=\2\2\u00df"+
		"\u00e1\3\2\2\2\u00e0\u009a\3\2\2\2\u00e0\u00b1\3\2\2\2\u00e0\u00c6\3\2"+
		"\2\2\u00e0\u00db\3\2\2\2\u00e1\23\3\2\2\2\u00e2\u00e3\5\60\31\2\u00e3"+
		"\u00e4\7B\2\2\u00e4\25\3\2\2\2\u00e5\u00ea\7B\2\2\u00e6\u00e7\7+\2\2\u00e7"+
		"\u00e9\7B\2\2\u00e8\u00e6\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00e8\3\2"+
		"\2\2\u00ea\u00eb\3\2\2\2\u00eb\27\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00ef"+
		"\5\32\16\2\u00ee\u00ed\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0\u00ee\3\2\2\2"+
		"\u00f0\u00f1\3\2\2\2\u00f1\31\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3\u00fd"+
		"\5\34\17\2\u00f4\u00fd\5\66\34\2\u00f5\u00fd\5\62\32\2\u00f6\u00fd\58"+
		"\35\2\u00f7\u00fd\5(\25\2\u00f8\u00fd\5$\23\2\u00f9\u00fd\5&\24\2\u00fa"+
		"\u00fd\5 \21\2\u00fb\u00fd\5\36\20\2\u00fc\u00f3\3\2\2\2\u00fc\u00f4\3"+
		"\2\2\2\u00fc\u00f5\3\2\2\2\u00fc\u00f6\3\2\2\2\u00fc\u00f7\3\2\2\2\u00fc"+
		"\u00f8\3\2\2\2\u00fc\u00f9\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fc\u00fb\3\2"+
		"\2\2\u00fd\33\3\2\2\2\u00fe\u0101\5\26\f\2\u00ff\u0100\7#\2\2\u0100\u0102"+
		"\7B\2\2\u0101\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0103\3\2\2\2\u0103"+
		"\u010c\79\2\2\u0104\u0109\5> \2\u0105\u0106\7,\2\2\u0106\u0108\5> \2\u0107"+
		"\u0105\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2"+
		"\2\2\u010a\u010d\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u0104\3\2\2\2\u010c"+
		"\u010d\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u010f\7:\2\2\u010f\u0137\3\2"+
		"\2\2\u0110\u0111\7\20\2\2\u0111\u0112\7#\2\2\u0112\u0113\5\26\f\2\u0113"+
		"\u0114\7#\2\2\u0114\u0115\7B\2\2\u0115\u011e\79\2\2\u0116\u011b\5> \2"+
		"\u0117\u0118\7,\2\2\u0118\u011a\5> \2\u0119\u0117\3\2\2\2\u011a\u011d"+
		"\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011f\3\2\2\2\u011d"+
		"\u011b\3\2\2\2\u011e\u0116\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u0120\3\2"+
		"\2\2\u0120\u0121\7:\2\2\u0121\u0137\3\2\2\2\u0122\u0123\7\b\2\2\u0123"+
		"\u0124\7#\2\2\u0124\u0127\5\26\f\2\u0125\u0126\7#\2\2\u0126\u0128\7B\2"+
		"\2\u0127\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u0132"+
		"\79\2\2\u012a\u012f\5> \2\u012b\u012c\7,\2\2\u012c\u012e\5> \2\u012d\u012b"+
		"\3\2\2\2\u012e\u0131\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130"+
		"\u0133\3\2\2\2\u0131\u012f\3\2\2\2\u0132\u012a\3\2\2\2\u0132\u0133\3\2"+
		"\2\2\u0133\u0134\3\2\2\2\u0134\u0135\7:\2\2\u0135\u0137\3\2\2\2\u0136"+
		"\u00fe\3\2\2\2\u0136\u0110\3\2\2\2\u0136\u0122\3\2\2\2\u0137\35\3\2\2"+
		"\2\u0138\u0139\7\f\2\2\u0139\u013a\79\2\2\u013a\u013b\5<\37\2\u013b\u013c"+
		"\7:\2\2\u013c\37\3\2\2\2\u013d\u013e\7\17\2\2\u013e\u014d\5\30\r\2\u013f"+
		"\u0140\7\r\2\2\u0140\u0141\5\"\22\2\u0141\u0142\5\30\r\2\u0142\u0144\3"+
		"\2\2\2\u0143\u013f\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0143\3\2\2\2\u0145"+
		"\u0146\3\2\2\2\u0146\u0149\3\2\2\2\u0147\u0148\7\16\2\2\u0148\u014a\5"+
		"\30\r\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u014e\3\2\2\2\u014b"+
		"\u014c\7\16\2\2\u014c\u014e\5\30\r\2\u014d\u0143\3\2\2\2\u014d\u014b\3"+
		"\2\2\2\u014e\u014f\3\2\2\2\u014f\u0150\7=\2\2\u0150!\3\2\2\2\u0151\u015b"+
		"\7B\2\2\u0152\u0153\7\23\2\2\u0153\u0158\5\26\f\2\u0154\u0155\7 \2\2\u0155"+
		"\u0157\5\26\f\2\u0156\u0154\3\2\2\2\u0157\u015a\3\2\2\2\u0158\u0156\3"+
		"\2\2\2\u0158\u0159\3\2\2\2\u0159\u015c\3\2\2\2\u015a\u0158\3\2\2\2\u015b"+
		"\u0152\3\2\2\2\u015b\u015c\3\2\2\2\u015c#\3\2\2\2\u015d\u015e\7\3\2\2"+
		"\u015e\u015f\5<\37\2\u015f%\3\2\2\2\u0160\u0161\7\26\2\2\u0161\u0162\5"+
		"<\37\2\u0162\'\3\2\2\2\u0163\u0166\7\36\2\2\u0164\u0167\5<\37\2\u0165"+
		"\u0167\7\27\2\2\u0166\u0164\3\2\2\2\u0166\u0165\3\2\2\2\u0167)\3\2\2\2"+
		"\u0168\u0169\7\60\2\2\u0169\u016e\7B\2\2\u016a\u016b\7,\2\2\u016b\u016d"+
		"\7B\2\2\u016c\u016a\3\2\2\2\u016d\u0170\3\2\2\2\u016e\u016c\3\2\2\2\u016e"+
		"\u016f\3\2\2\2\u016f\u0171\3\2\2\2\u0170\u016e\3\2\2\2\u0171\u0172\7."+
		"\2\2\u0172+\3\2\2\2\u0173\u0174\7\60\2\2\u0174\u0179\5\60\31\2\u0175\u0176"+
		"\7,\2\2\u0176\u0178\5\60\31\2\u0177\u0175\3\2\2\2\u0178\u017b\3\2\2\2"+
		"\u0179\u0177\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u017c\3\2\2\2\u017b\u0179"+
		"\3\2\2\2\u017c\u017d\7.\2\2\u017d-\3\2\2\2\u017e\u017f\5\26\f\2\u017f"+
		"/\3\2\2\2\u0180\u0182\5\26\f\2\u0181\u0183\5,\27\2\u0182\u0181\3\2\2\2"+
		"\u0182\u0183\3\2\2\2\u0183\u0189\3\2\2\2\u0184\u0189\7$\2\2\u0185\u0189"+
		"\7%\2\2\u0186\u0189\7&\2\2\u0187\u0189\7\'\2\2\u0188\u0180\3\2\2\2\u0188"+
		"\u0184\3\2\2\2\u0188\u0185\3\2\2\2\u0188\u0186\3\2\2\2\u0188\u0187\3\2"+
		"\2\2\u0189\61\3\2\2\2\u018a\u018b\5:\36\2\u018b\u018c\7#\2\2\u018c\u018e"+
		"\3\2\2\2\u018d\u018a\3\2\2\2\u018d\u018e\3\2\2\2\u018e\u018f\3\2\2\2\u018f"+
		"\u0190\7B\2\2\u0190\u01a8\5\64\33\2\u0191\u0196\5\26\f\2\u0192\u0193\7"+
		"#\2\2\u0193\u0194\7\20\2\2\u0194\u0195\7#\2\2\u0195\u0197\5\26\f\2\u0196"+
		"\u0192\3\2\2\2\u0196\u0197\3\2\2\2\u0197\u0198\3\2\2\2\u0198\u0199\7#"+
		"\2\2\u0199\u019a\7B\2\2\u019a\u019b\5\64\33\2\u019b\u01a8\3\2\2\2\u019c"+
		"\u019e\5\16\b\2\u019d\u019c\3\2\2\2\u019d\u019e\3\2\2\2\u019e\u01a0\3"+
		"\2\2\2\u019f\u01a1\7\6\2\2\u01a0\u019f\3\2\2\2\u01a0\u01a1\3\2\2\2\u01a1"+
		"\u01a2\3\2\2\2\u01a2\u01a3\5\60\31\2\u01a3\u01a5\7B\2\2\u01a4\u01a6\5"+
		"\64\33\2\u01a5\u01a4\3\2\2\2\u01a5\u01a6\3\2\2\2\u01a6\u01a8\3\2\2\2\u01a7"+
		"\u018d\3\2\2\2\u01a7\u0191\3\2\2\2\u01a7\u019d\3\2\2\2\u01a8\63\3\2\2"+
		"\2\u01a9\u01aa\7-\2\2\u01aa\u01ab\5<\37\2\u01ab\65\3\2\2\2\u01ac\u01ad"+
		"\7<\2\2\u01ad\u01ae\5<\37\2\u01ae\u01b5\5\30\r\2\u01af\u01b0\7\7\2\2\u01b0"+
		"\u01b1\5<\37\2\u01b1\u01b2\5\30\r\2\u01b2\u01b4\3\2\2\2\u01b3\u01af\3"+
		"\2\2\2\u01b4\u01b7\3\2\2\2\u01b5\u01b3\3\2\2\2\u01b5\u01b6\3\2\2\2\u01b6"+
		"\u01ba\3\2\2\2\u01b7\u01b5\3\2\2\2\u01b8\u01b9\7\34\2\2\u01b9\u01bb\5"+
		"\30\r\2\u01ba\u01b8\3\2\2\2\u01ba\u01bb\3\2\2\2\u01bb\u01bc\3\2\2\2\u01bc"+
		"\u01bd\7=\2\2\u01bd\67\3\2\2\2\u01be\u01c4\7\33\2\2\u01bf\u01c0\5<\37"+
		"\2\u01c0\u01c1\7\32\2\2\u01c1\u01c5\3\2\2\2\u01c2\u01c3\t\3\2\2\u01c3"+
		"\u01c5\5<\37\2\u01c4\u01bf\3\2\2\2\u01c4\u01c2\3\2\2\2\u01c5\u01c6\3\2"+
		"\2\2\u01c6\u01c7\5\30\r\2\u01c7\u01c8\7=\2\2\u01c89\3\2\2\2\u01c9\u01ca"+
		"\7\20\2\2\u01ca\u01cd\7#\2\2\u01cb\u01cd\7\b\2\2\u01cc\u01c9\3\2\2\2\u01cc"+
		"\u01cb\3\2\2\2\u01cd;\3\2\2\2\u01ce\u01cf\5> \2\u01cf=\3\2\2\2\u01d0\u01d1"+
		"\b \1\2\u01d1\u01d2\7\63\2\2\u01d2\u021a\5> \2\u01d3\u01d4\7)\2\2\u01d4"+
		"\u021a\5> \2\u01d5\u01d8\5\26\f\2\u01d6\u01d7\7#\2\2\u01d7\u01d9\7B\2"+
		"\2\u01d8\u01d6\3\2\2\2\u01d8\u01d9\3\2\2\2\u01d9\u021a\3\2\2\2\u01da\u01db"+
		"\5\26\f\2\u01db\u01dc\7#\2\2\u01dc\u01dd\7\20\2\2\u01dd\u01de\7#\2\2\u01de"+
		"\u01df\5\26\f\2\u01df\u01e0\7#\2\2\u01e0\u01e1\7B\2\2\u01e1\u021a\3\2"+
		"\2\2\u01e2\u01e5\5\26\f\2\u01e3\u01e4\7#\2\2\u01e4\u01e6\7B\2\2\u01e5"+
		"\u01e3\3\2\2\2\u01e5\u01e6\3\2\2\2\u01e6\u01e7\3\2\2\2\u01e7\u01e8\79"+
		"\2\2\u01e8\u01e9\5@!\2\u01e9\u01ea\7:\2\2\u01ea\u021a\3\2\2\2\u01eb\u01ec"+
		"\5:\36\2\u01ec\u01ed\7#\2\2\u01ed\u01ee\5\26\f\2\u01ee\u021a\3\2\2\2\u01ef"+
		"\u01f0\7\20\2\2\u01f0\u01f1\7#\2\2\u01f1\u01f2\5\26\f\2\u01f2\u01f3\7"+
		"#\2\2\u01f3\u01f4\7B\2\2\u01f4\u01f5\79\2\2\u01f5\u01f6\5@!\2\u01f6\u01f7"+
		"\7:\2\2\u01f7\u021a\3\2\2\2\u01f8\u01f9\7\b\2\2\u01f9\u01fa\7#\2\2\u01fa"+
		"\u01fd\5\26\f\2\u01fb\u01fc\7#\2\2\u01fc\u01fe\7B\2\2\u01fd\u01fb\3\2"+
		"\2\2\u01fd\u01fe\3\2\2\2\u01fe\u01ff\3\2\2\2\u01ff\u0200\79\2\2\u0200"+
		"\u0201\5@!\2\u0201\u0202\7:\2\2\u0202\u021a\3\2\2\2\u0203\u0204\7\24\2"+
		"\2\u0204\u0205\79\2\2\u0205\u0206\5\60\31\2\u0206\u0207\7,\2\2\u0207\u0208"+
		"\5> \2\u0208\u0209\7:\2\2\u0209\u021a\3\2\2\2\u020a\u021a\7@\2\2\u020b"+
		"\u021a\7?\2\2\u020c\u021a\7A\2\2\u020d\u021a\7C\2\2\u020e\u021a\7!\2\2"+
		"\u020f\u021a\7\b\2\2\u0210\u0211\7\25\2\2\u0211\u0212\79\2\2\u0212\u0213"+
		"\5> \2\u0213\u0214\7:\2\2\u0214\u021a\3\2\2\2\u0215\u0216\79\2\2\u0216"+
		"\u0217\5> \2\u0217\u0218\7:\2\2\u0218\u021a\3\2\2\2\u0219\u01d0\3\2\2"+
		"\2\u0219\u01d3\3\2\2\2\u0219\u01d5\3\2\2\2\u0219\u01da\3\2\2\2\u0219\u01e2"+
		"\3\2\2\2\u0219\u01eb\3\2\2\2\u0219\u01ef\3\2\2\2\u0219\u01f8\3\2\2\2\u0219"+
		"\u0203\3\2\2\2\u0219\u020a\3\2\2\2\u0219\u020b\3\2\2\2\u0219\u020c\3\2"+
		"\2\2\u0219\u020d\3\2\2\2\u0219\u020e\3\2\2\2\u0219\u020f\3\2\2\2\u0219"+
		"\u0210\3\2\2\2\u0219\u0215\3\2\2\2\u021a\u0232\3\2\2\2\u021b\u021c\6 "+
		"\2\3\u021c\u021d\t\4\2\2\u021d\u0231\5> \2\u021e\u021f\6 \3\3\u021f\u0220"+
		"\t\5\2\2\u0220\u0231\5> \2\u0221\u0222\6 \4\3\u0222\u0223\t\6\2\2\u0223"+
		"\u0231\5> \2\u0224\u0225\6 \5\3\u0225\u0226\t\7\2\2\u0226\u0231\5> \2"+
		"\u0227\u0228\6 \6\3\u0228\u0229\7\37\2\2\u0229\u0231\5> \2\u022a\u022b"+
		"\6 \7\3\u022b\u022c\7 \2\2\u022c\u0231\5> \2\u022d\u022e\6 \b\3\u022e"+
		"\u022f\7\23\2\2\u022f\u0231\5.\30\2\u0230\u021b\3\2\2\2\u0230\u021e\3"+
		"\2\2\2\u0230\u0221\3\2\2\2\u0230\u0224\3\2\2\2\u0230\u0227\3\2\2\2\u0230"+
		"\u022a\3\2\2\2\u0230\u022d\3\2\2\2\u0231\u0234\3\2\2\2\u0232\u0230\3\2"+
		"\2\2\u0232\u0233\3\2\2\2\u0233?\3\2\2\2\u0234\u0232\3\2\2\2\u0235\u023a"+
		"\5> \2\u0236\u0237\7,\2\2\u0237\u0239\5> \2\u0238\u0236\3\2\2\2\u0239"+
		"\u023c\3\2\2\2\u023a\u0238\3\2\2\2\u023a\u023b\3\2\2\2\u023b\u023e\3\2"+
		"\2\2\u023c\u023a\3\2\2\2\u023d\u0235\3\2\2\2\u023d\u023e\3\2\2\2\u023e"+
		"A\3\2\2\2HFKSVdglqvy~\u0080\u0085\u008a\u008e\u0095\u0098\u00a2\u00a5"+
		"\u00a8\u00ac\u00ba\u00bd\u00c0\u00c4\u00cf\u00d2\u00d5\u00d9\u00e0\u00ea"+
		"\u00f0\u00fc\u0101\u0109\u010c\u011b\u011e\u0127\u012f\u0132\u0136\u0145"+
		"\u0149\u014d\u0158\u015b\u0166\u016e\u0179\u0182\u0188\u018d\u0196\u019d"+
		"\u01a0\u01a5\u01a7\u01b5\u01ba\u01c4\u01cc\u01d8\u01e5\u01fd\u0219\u0230"+
		"\u0232\u023a\u023d";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}