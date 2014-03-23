// Generated from /Users/stefika/Repositories/quorum-language/plugins/ParserPlugin/src/quorum/Libraries/Language/Parser/Quorum.g4 by ANTLR 4.1
package plugins.quorum.Libraries.Language.Parser;;
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
		RULE_root_expression = 29, RULE_expression = 30, RULE_or = 31, RULE_and = 32, 
		RULE_equality = 33, RULE_isa_operation = 34, RULE_comparison = 35, RULE_add = 36, 
		RULE_multiply = 37, RULE_combo_expression = 38, RULE_atom = 39, RULE_function_expression_list = 40;
	public static final String[] ruleNames = {
		"start", "package_rule", "reference", "class_declaration", "no_class_stmnts", 
		"inherit_stmnts", "access_modifier", "class_stmnts", "method_declaration", 
		"formal_parameter", "qualified_name", "block", "statement", "solo_method_call", 
		"alert_statement", "check_statement", "detect_parameter", "print_statement", 
		"speak_statement", "return_statement", "generic_declaration", "generic_statement", 
		"class_type", "assignment_declaration", "assignment_statement", "assign_right_hand_side", 
		"if_statement", "loop_statement", "selector", "root_expression", "expression", 
		"or", "and", "equality", "isa_operation", "comparison", "add", "multiply", 
		"combo_expression", "atom", "function_expression_list"
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
			setState(102);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(82); package_rule();
				setState(84); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(83); reference();
					}
					}
					setState(86); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==USE );
				}
				break;

			case 2:
				{
				setState(89); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(88); reference();
					}
					}
					setState(91); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==USE );
				setState(93); package_rule();
				}
				break;

			case 3:
				{
				setState(95); package_rule();
				}
				break;

			case 4:
				{
				setState(97); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(96); reference();
					}
					}
					setState(99); 
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
			setState(104); class_declaration();
			setState(105); match(EOF);
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
			setState(107); match(PACKAGE_NAME);
			setState(108); qualified_name();
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
			setState(110); match(USE);
			setState(111); qualified_name();
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
			setState(129);
			switch (_input.LA(1)) {
			case CLASS:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(113); match(CLASS);
				setState(114); match(ID);
				setState(116);
				_la = _input.LA(1);
				if (_la==LESS) {
					{
					setState(115); generic_declaration();
					}
				}

				setState(119);
				_la = _input.LA(1);
				if (_la==INHERITS) {
					{
					setState(118); inherit_stmnts();
					}
				}

				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 2)) & ~0x3f) == 0 && ((1L << (_la - 2)) & ((1L << (ON - 2)) | (1L << (CONSTANT - 2)) | (1L << (ME - 2)) | (1L << (PUBLIC - 2)) | (1L << (PRIVATE - 2)) | (1L << (PARENT - 2)) | (1L << (BLUEPRINT - 2)) | (1L << (NATIVE - 2)) | (1L << (ACTION - 2)) | (1L << (INTEGER_KEYWORD - 2)) | (1L << (NUMBER_KEYWORD - 2)) | (1L << (TEXT - 2)) | (1L << (BOOLEAN_KEYWORD - 2)) | (1L << (ID - 2)))) != 0)) {
					{
					{
					setState(121); class_stmnts();
					}
					}
					setState(126);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(127); match(END);
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
				setState(128); no_class_stmnts();
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
			setState(144);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(132); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(131); statement();
					}
					}
					setState(134); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((((_la - 1)) & ~0x3f) == 0 && ((1L << (_la - 1)) & ((1L << (OUTPUT - 1)) | (1L << (CONSTANT - 1)) | (1L << (ME - 1)) | (1L << (PUBLIC - 1)) | (1L << (PRIVATE - 1)) | (1L << (ALERT - 1)) | (1L << (CHECK - 1)) | (1L << (PARENT - 1)) | (1L << (SAY - 1)) | (1L << (REPEAT - 1)) | (1L << (RETURN - 1)) | (1L << (INTEGER_KEYWORD - 1)) | (1L << (NUMBER_KEYWORD - 1)) | (1L << (TEXT - 1)) | (1L << (BOOLEAN_KEYWORD - 1)) | (1L << (IF - 1)) | (1L << (ID - 1)))) != 0) );
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(140); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(137);
					_la = _input.LA(1);
					if (_la==PUBLIC || _la==PRIVATE) {
						{
						setState(136); access_modifier();
						}
					}

					setState(139); method_declaration();
					}
					}
					setState(142); 
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
			setState(146); match(INHERITS);
			setState(147); qualified_name();
			setState(149);
			_la = _input.LA(1);
			if (_la==LESS) {
				{
				setState(148); generic_statement();
				}
			}

			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(151); match(COMMA);
				setState(152); qualified_name();
				setState(154);
				_la = _input.LA(1);
				if (_la==LESS) {
					{
					setState(153); generic_statement();
					}
				}

				}
				}
				setState(160);
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
			setState(161);
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
			setState(168);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(163); assignment_statement();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(165);
				_la = _input.LA(1);
				if (_la==PUBLIC || _la==PRIVATE) {
					{
					setState(164); access_modifier();
					}
				}

				setState(167); method_declaration();
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
			setState(240);
			switch (_input.LA(1)) {
			case ACTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(170); match(ACTION);
				setState(171); match(ID);
				setState(184);
				_la = _input.LA(1);
				if (_la==LEFT_PAREN) {
					{
					setState(172); match(LEFT_PAREN);
					setState(181);
					_la = _input.LA(1);
					if (((((_la - 34)) & ~0x3f) == 0 && ((1L << (_la - 34)) & ((1L << (INTEGER_KEYWORD - 34)) | (1L << (NUMBER_KEYWORD - 34)) | (1L << (TEXT - 34)) | (1L << (BOOLEAN_KEYWORD - 34)) | (1L << (ID - 34)))) != 0)) {
						{
						setState(173); formal_parameter();
						setState(178);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(174); match(COMMA);
							setState(175); formal_parameter();
							}
							}
							setState(180);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(183); match(RIGHT_PAREN);
					}
				}

				setState(188);
				_la = _input.LA(1);
				if (_la==RETURNS) {
					{
					setState(186); match(RETURNS);
					setState(187); ((Method_declarationContext)_localctx).return_type = assignment_declaration();
					}
				}

				setState(190); block();
				setState(191); match(END);
				}
				break;
			case BLUEPRINT:
				enterOuterAlt(_localctx, 2);
				{
				setState(193); match(BLUEPRINT);
				setState(194); match(ACTION);
				setState(195); match(ID);
				setState(208);
				_la = _input.LA(1);
				if (_la==LEFT_PAREN) {
					{
					setState(196); match(LEFT_PAREN);
					setState(205);
					_la = _input.LA(1);
					if (((((_la - 34)) & ~0x3f) == 0 && ((1L << (_la - 34)) & ((1L << (INTEGER_KEYWORD - 34)) | (1L << (NUMBER_KEYWORD - 34)) | (1L << (TEXT - 34)) | (1L << (BOOLEAN_KEYWORD - 34)) | (1L << (ID - 34)))) != 0)) {
						{
						setState(197); formal_parameter();
						setState(202);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(198); match(COMMA);
							setState(199); formal_parameter();
							}
							}
							setState(204);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(207); match(RIGHT_PAREN);
					}
				}

				setState(212);
				_la = _input.LA(1);
				if (_la==RETURNS) {
					{
					setState(210); match(RETURNS);
					setState(211); ((Method_declarationContext)_localctx).return_type = assignment_declaration();
					}
				}

				}
				break;
			case NATIVE:
				enterOuterAlt(_localctx, 3);
				{
				setState(214); match(NATIVE);
				setState(215); match(ACTION);
				setState(216); match(ID);
				setState(229);
				_la = _input.LA(1);
				if (_la==LEFT_PAREN) {
					{
					setState(217); match(LEFT_PAREN);
					setState(226);
					_la = _input.LA(1);
					if (((((_la - 34)) & ~0x3f) == 0 && ((1L << (_la - 34)) & ((1L << (INTEGER_KEYWORD - 34)) | (1L << (NUMBER_KEYWORD - 34)) | (1L << (TEXT - 34)) | (1L << (BOOLEAN_KEYWORD - 34)) | (1L << (ID - 34)))) != 0)) {
						{
						setState(218); formal_parameter();
						setState(223);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(219); match(COMMA);
							setState(220); formal_parameter();
							}
							}
							setState(225);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(228); match(RIGHT_PAREN);
					}
				}

				setState(233);
				_la = _input.LA(1);
				if (_la==RETURNS) {
					{
					setState(231); match(RETURNS);
					setState(232); assignment_declaration();
					}
				}

				}
				break;
			case ON:
				enterOuterAlt(_localctx, 4);
				{
				setState(235); match(ON);
				setState(236); match(CREATE);
				setState(237); block();
				setState(238); match(END);
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
			setState(242); assignment_declaration();
			setState(243); match(ID);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245); ((Qualified_nameContext)_localctx).ID = match(ID);
			((Qualified_nameContext)_localctx).ids.add(((Qualified_nameContext)_localctx).ID);
			setState(250);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PERIOD) {
				{
				{
				setState(246); match(PERIOD);
				setState(247); ((Qualified_nameContext)_localctx).ID = match(ID);
				((Qualified_nameContext)_localctx).ids.add(((Qualified_nameContext)_localctx).ID);
				}
				}
				setState(252);
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
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 1)) & ~0x3f) == 0 && ((1L << (_la - 1)) & ((1L << (OUTPUT - 1)) | (1L << (CONSTANT - 1)) | (1L << (ME - 1)) | (1L << (PUBLIC - 1)) | (1L << (PRIVATE - 1)) | (1L << (ALERT - 1)) | (1L << (CHECK - 1)) | (1L << (PARENT - 1)) | (1L << (SAY - 1)) | (1L << (REPEAT - 1)) | (1L << (RETURN - 1)) | (1L << (INTEGER_KEYWORD - 1)) | (1L << (NUMBER_KEYWORD - 1)) | (1L << (TEXT - 1)) | (1L << (BOOLEAN_KEYWORD - 1)) | (1L << (IF - 1)) | (1L << (ID - 1)))) != 0)) {
				{
				{
				setState(253); statement();
				}
				}
				setState(258);
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
			setState(268);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(259); solo_method_call();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(260); if_statement();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(261); assignment_statement();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(262); loop_statement();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(263); return_statement();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(264); print_statement();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(265); speak_statement();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(266); check_statement();
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(267); alert_statement();
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
			setState(326);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(270); qualified_name();
				setState(273);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(271); match(COLON);
					setState(272); match(ID);
					}
				}

				setState(275); match(LEFT_PAREN);
				setState(284);
				_la = _input.LA(1);
				if (((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & ((1L << (ME - 6)) | (1L << (PARENT - 6)) | (1L << (CAST - 6)) | (1L << (INPUT - 6)) | (1L << (NULL - 6)) | (1L << (NOT - 6)) | (1L << (MINUS - 6)) | (1L << (LEFT_PAREN - 6)) | (1L << (BOOLEAN - 6)) | (1L << (INT - 6)) | (1L << (DECIMAL - 6)) | (1L << (ID - 6)) | (1L << (STRING - 6)))) != 0)) {
					{
					setState(276); expression();
					setState(281);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(277); match(COMMA);
						setState(278); expression();
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
			case PARENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(288); match(PARENT);
				setState(289); match(COLON);
				setState(290); qualified_name();
				setState(291); match(COLON);
				setState(292); match(ID);
				setState(293); match(LEFT_PAREN);
				setState(302);
				_la = _input.LA(1);
				if (((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & ((1L << (ME - 6)) | (1L << (PARENT - 6)) | (1L << (CAST - 6)) | (1L << (INPUT - 6)) | (1L << (NULL - 6)) | (1L << (NOT - 6)) | (1L << (MINUS - 6)) | (1L << (LEFT_PAREN - 6)) | (1L << (BOOLEAN - 6)) | (1L << (INT - 6)) | (1L << (DECIMAL - 6)) | (1L << (ID - 6)) | (1L << (STRING - 6)))) != 0)) {
					{
					setState(294); expression();
					setState(299);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(295); match(COMMA);
						setState(296); expression();
						}
						}
						setState(301);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(304); match(RIGHT_PAREN);
				}
				break;
			case ME:
				enterOuterAlt(_localctx, 3);
				{
				setState(306); match(ME);
				setState(307); match(COLON);
				setState(308); qualified_name();
				setState(311);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(309); match(COLON);
					setState(310); match(ID);
					}
				}

				setState(313); match(LEFT_PAREN);
				setState(322);
				_la = _input.LA(1);
				if (((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & ((1L << (ME - 6)) | (1L << (PARENT - 6)) | (1L << (CAST - 6)) | (1L << (INPUT - 6)) | (1L << (NULL - 6)) | (1L << (NOT - 6)) | (1L << (MINUS - 6)) | (1L << (LEFT_PAREN - 6)) | (1L << (BOOLEAN - 6)) | (1L << (INT - 6)) | (1L << (DECIMAL - 6)) | (1L << (ID - 6)) | (1L << (STRING - 6)))) != 0)) {
					{
					setState(314); expression();
					setState(319);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(315); match(COMMA);
						setState(316); expression();
						}
						}
						setState(321);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(324); match(RIGHT_PAREN);
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
			setState(328); match(ALERT);
			setState(329); match(LEFT_PAREN);
			setState(330); root_expression();
			setState(331); match(RIGHT_PAREN);
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
			setState(333); match(CHECK);
			setState(334); block();
			setState(349);
			switch (_input.LA(1)) {
			case DETECT:
				{
				setState(339); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(335); match(DETECT);
					setState(336); detect_parameter();
					setState(337); block();
					}
					}
					setState(341); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DETECT );
				setState(345);
				_la = _input.LA(1);
				if (_la==ALWAYS) {
					{
					setState(343); match(ALWAYS);
					setState(344); block();
					}
				}

				}
				break;
			case ALWAYS:
				{
				setState(347); match(ALWAYS);
				setState(348); block();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(351); match(END);
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
			setState(353); match(ID);
			setState(363);
			_la = _input.LA(1);
			if (_la==INHERITS) {
				{
				setState(354); match(INHERITS);
				setState(355); qualified_name();
				setState(360);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OR) {
					{
					{
					setState(356); match(OR);
					setState(357); qualified_name();
					}
					}
					setState(362);
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
			setState(365); match(OUTPUT);
			setState(366); root_expression();
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
			setState(368); match(SAY);
			setState(369); root_expression();
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
			setState(371); match(RETURN);
			setState(374);
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
				setState(372); root_expression();
				}
				break;
			case NOW:
				{
				setState(373); match(NOW);
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
			setState(376); match(LESS);
			setState(377); ((Generic_declarationContext)_localctx).ID = match(ID);
			((Generic_declarationContext)_localctx).ids.add(((Generic_declarationContext)_localctx).ID);
			setState(382);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(378); match(COMMA);
				setState(379); ((Generic_declarationContext)_localctx).ID = match(ID);
				((Generic_declarationContext)_localctx).ids.add(((Generic_declarationContext)_localctx).ID);
				}
				}
				setState(384);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(385); match(GREATER);
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
			setState(387); match(LESS);
			setState(388); assignment_declaration();
			setState(393);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(389); match(COMMA);
				setState(390); assignment_declaration();
				}
				}
				setState(395);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(396); match(GREATER);
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
			setState(398); qualified_name();
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
			setState(408);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(400); qualified_name();
				setState(402);
				_la = _input.LA(1);
				if (_la==LESS) {
					{
					setState(401); generic_statement();
					}
				}

				}
				break;
			case INTEGER_KEYWORD:
				enterOuterAlt(_localctx, 2);
				{
				setState(404); match(INTEGER_KEYWORD);
				}
				break;
			case NUMBER_KEYWORD:
				enterOuterAlt(_localctx, 3);
				{
				setState(405); match(NUMBER_KEYWORD);
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 4);
				{
				setState(406); match(TEXT);
				}
				break;
			case BOOLEAN_KEYWORD:
				enterOuterAlt(_localctx, 5);
				{
				setState(407); match(BOOLEAN_KEYWORD);
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
			setState(439);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(413);
				_la = _input.LA(1);
				if (_la==ME || _la==PARENT) {
					{
					setState(410); ((Assignment_statementContext)_localctx).sel = selector();
					setState(411); match(COLON);
					}
				}

				setState(415); match(ID);
				setState(416); ((Assignment_statementContext)_localctx).rhs = assign_right_hand_side();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(417); ((Assignment_statementContext)_localctx).obj = qualified_name();
				setState(422);
				switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
				case 1:
					{
					setState(418); match(COLON);
					setState(419); match(PARENT);
					setState(420); match(COLON);
					setState(421); ((Assignment_statementContext)_localctx).parent = qualified_name();
					}
					break;
				}
				setState(424); match(COLON);
				setState(425); match(ID);
				setState(426); ((Assignment_statementContext)_localctx).rhs = assign_right_hand_side();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(429);
				_la = _input.LA(1);
				if (_la==PUBLIC || _la==PRIVATE) {
					{
					setState(428); ((Assignment_statementContext)_localctx).modifier = access_modifier();
					}
				}

				setState(432);
				_la = _input.LA(1);
				if (_la==CONSTANT) {
					{
					setState(431); match(CONSTANT);
					}
				}

				setState(434); ((Assignment_statementContext)_localctx).type = assignment_declaration();
				setState(435); ((Assignment_statementContext)_localctx).name = match(ID);
				setState(437);
				_la = _input.LA(1);
				if (_la==EQUALITY) {
					{
					setState(436); ((Assignment_statementContext)_localctx).rhs = assign_right_hand_side();
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
			setState(441); match(EQUALITY);
			setState(442); root_expression();
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
			setState(444); match(IF);
			setState(445); root_expression();
			setState(446); block();
			setState(453);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELSE_IF) {
				{
				{
				setState(447); match(ELSE_IF);
				setState(448); root_expression();
				setState(449); block();
				}
				}
				setState(455);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(458);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(456); match(ELSE);
				setState(457); block();
				}
			}

			setState(460); match(END);
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
			setState(462); match(REPEAT);
			setState(468);
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
				setState(463); root_expression();
				setState(464); match(TIMES);
				}
				}
				break;
			case UNTIL:
			case WHILE:
				{
				{
				setState(466);
				_la = _input.LA(1);
				if ( !(_la==UNTIL || _la==WHILE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(467); root_expression();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(470); block();
			setState(471); match(END);
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
			setState(476);
			switch (_input.LA(1)) {
			case PARENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(473); match(PARENT);
				setState(474); match(COLON);
				}
				break;
			case ME:
				enterOuterAlt(_localctx, 2);
				{
				setState(475); match(ME);
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
			setState(478); expression();
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
		public OrContext or() {
			return getRuleContext(OrContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(480); or();
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

	public static class OrContext extends ParserRuleContext {
		public List<TerminalNode> OR() { return getTokens(QuorumParser.OR); }
		public AndContext and(int i) {
			return getRuleContext(AndContext.class,i);
		}
		public TerminalNode OR(int i) {
			return getToken(QuorumParser.OR, i);
		}
		public List<AndContext> and() {
			return getRuleContexts(AndContext.class);
		}
		public OrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_or; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitOr(this);
		}
	}

	public final OrContext or() throws RecognitionException {
		OrContext _localctx = new OrContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_or);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(482); and();
			setState(487);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(483); match(OR);
				setState(484); and();
				}
				}
				setState(489);
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

	public static class AndContext extends ParserRuleContext {
		public List<EqualityContext> equality() {
			return getRuleContexts(EqualityContext.class);
		}
		public EqualityContext equality(int i) {
			return getRuleContext(EqualityContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(QuorumParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(QuorumParser.AND, i);
		}
		public AndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitAnd(this);
		}
	}

	public final AndContext and() throws RecognitionException {
		AndContext _localctx = new AndContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_and);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(490); equality();
			setState(495);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(491); match(AND);
				setState(492); equality();
				}
				}
				setState(497);
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

	public static class EqualityContext extends ParserRuleContext {
		public TerminalNode EQUALITY(int i) {
			return getToken(QuorumParser.EQUALITY, i);
		}
		public List<TerminalNode> EQUALITY() { return getTokens(QuorumParser.EQUALITY); }
		public TerminalNode NOTEQUALS(int i) {
			return getToken(QuorumParser.NOTEQUALS, i);
		}
		public Isa_operationContext isa_operation(int i) {
			return getRuleContext(Isa_operationContext.class,i);
		}
		public List<TerminalNode> NOTEQUALS() { return getTokens(QuorumParser.NOTEQUALS); }
		public List<Isa_operationContext> isa_operation() {
			return getRuleContexts(Isa_operationContext.class);
		}
		public EqualityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equality; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterEquality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitEquality(this);
		}
	}

	public final EqualityContext equality() throws RecognitionException {
		EqualityContext _localctx = new EqualityContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_equality);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(498); isa_operation();
			setState(503);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NOTEQUALS || _la==EQUALITY) {
				{
				{
				setState(499);
				_la = _input.LA(1);
				if ( !(_la==NOTEQUALS || _la==EQUALITY) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(500); isa_operation();
				}
				}
				setState(505);
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

	public static class Isa_operationContext extends ParserRuleContext {
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public Class_typeContext class_type() {
			return getRuleContext(Class_typeContext.class,0);
		}
		public TerminalNode INHERITS() { return getToken(QuorumParser.INHERITS, 0); }
		public Isa_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_isa_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterIsa_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitIsa_operation(this);
		}
	}

	public final Isa_operationContext isa_operation() throws RecognitionException {
		Isa_operationContext _localctx = new Isa_operationContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_isa_operation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(506); comparison();
			setState(509);
			_la = _input.LA(1);
			if (_la==INHERITS) {
				{
				setState(507); match(INHERITS);
				setState(508); class_type();
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

	public static class ComparisonContext extends ParserRuleContext {
		public TerminalNode GREATER(int i) {
			return getToken(QuorumParser.GREATER, i);
		}
		public List<AddContext> add() {
			return getRuleContexts(AddContext.class);
		}
		public List<TerminalNode> GREATER_EQUAL() { return getTokens(QuorumParser.GREATER_EQUAL); }
		public TerminalNode GREATER_EQUAL(int i) {
			return getToken(QuorumParser.GREATER_EQUAL, i);
		}
		public List<TerminalNode> LESS_EQUAL() { return getTokens(QuorumParser.LESS_EQUAL); }
		public List<TerminalNode> LESS() { return getTokens(QuorumParser.LESS); }
		public AddContext add(int i) {
			return getRuleContext(AddContext.class,i);
		}
		public TerminalNode LESS(int i) {
			return getToken(QuorumParser.LESS, i);
		}
		public List<TerminalNode> GREATER() { return getTokens(QuorumParser.GREATER); }
		public TerminalNode LESS_EQUAL(int i) {
			return getToken(QuorumParser.LESS_EQUAL, i);
		}
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitComparison(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_comparison);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(511); add();
			setState(516);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER) | (1L << GREATER_EQUAL) | (1L << LESS) | (1L << LESS_EQUAL))) != 0)) {
				{
				{
				setState(512);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER) | (1L << GREATER_EQUAL) | (1L << LESS) | (1L << LESS_EQUAL))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(513); add();
				}
				}
				setState(518);
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

	public static class AddContext extends ParserRuleContext {
		public TerminalNode MINUS(int i) {
			return getToken(QuorumParser.MINUS, i);
		}
		public List<MultiplyContext> multiply() {
			return getRuleContexts(MultiplyContext.class);
		}
		public List<TerminalNode> MINUS() { return getTokens(QuorumParser.MINUS); }
		public MultiplyContext multiply(int i) {
			return getRuleContext(MultiplyContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(QuorumParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(QuorumParser.PLUS, i);
		}
		public AddContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_add; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterAdd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitAdd(this);
		}
	}

	public final AddContext add() throws RecognitionException {
		AddContext _localctx = new AddContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_add);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(519); multiply();
			setState(524);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				{
				setState(520);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(521); multiply();
				}
				}
				setState(526);
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

	public static class MultiplyContext extends ParserRuleContext {
		public TerminalNode MODULO(int i) {
			return getToken(QuorumParser.MODULO, i);
		}
		public List<TerminalNode> MODULO() { return getTokens(QuorumParser.MODULO); }
		public TerminalNode MULTIPLY(int i) {
			return getToken(QuorumParser.MULTIPLY, i);
		}
		public List<Combo_expressionContext> combo_expression() {
			return getRuleContexts(Combo_expressionContext.class);
		}
		public TerminalNode DIVIDE(int i) {
			return getToken(QuorumParser.DIVIDE, i);
		}
		public List<TerminalNode> MULTIPLY() { return getTokens(QuorumParser.MULTIPLY); }
		public Combo_expressionContext combo_expression(int i) {
			return getRuleContext(Combo_expressionContext.class,i);
		}
		public List<TerminalNode> DIVIDE() { return getTokens(QuorumParser.DIVIDE); }
		public MultiplyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiply; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterMultiply(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitMultiply(this);
		}
	}

	public final MultiplyContext multiply() throws RecognitionException {
		MultiplyContext _localctx = new MultiplyContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_multiply);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(527); combo_expression();
			setState(532);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLY) | (1L << DIVIDE) | (1L << MODULO))) != 0)) {
				{
				{
				setState(528);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLY) | (1L << DIVIDE) | (1L << MODULO))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(529); combo_expression();
				}
				}
				setState(534);
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

	public static class Combo_expressionContext extends ParserRuleContext {
		public Assignment_declarationContext assignment_declaration() {
			return getRuleContext(Assignment_declarationContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(QuorumParser.COMMA, 0); }
		public TerminalNode NOT() { return getToken(QuorumParser.NOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public TerminalNode CAST() { return getToken(QuorumParser.CAST, 0); }
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public Combo_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_combo_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterCombo_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitCombo_expression(this);
		}
	}

	public final Combo_expressionContext combo_expression() throws RecognitionException {
		Combo_expressionContext _localctx = new Combo_expressionContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_combo_expression);
		try {
			setState(545);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(535); match(NOT);
				setState(536); atom();
				}
				break;
			case CAST:
				enterOuterAlt(_localctx, 2);
				{
				setState(537); match(CAST);
				setState(538); match(LEFT_PAREN);
				setState(539); assignment_declaration();
				setState(540); match(COMMA);
				setState(541); expression();
				setState(542); match(RIGHT_PAREN);
				}
				break;
			case ME:
			case PARENT:
			case INPUT:
			case NULL:
			case MINUS:
			case LEFT_PAREN:
			case BOOLEAN:
			case INT:
			case DECIMAL:
			case ID:
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(544); atom();
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

	public static class AtomContext extends ParserRuleContext {
		public List<Qualified_nameContext> qualified_name() {
			return getRuleContexts(Qualified_nameContext.class);
		}
		public TerminalNode INPUT() { return getToken(QuorumParser.INPUT, 0); }
		public TerminalNode INT() { return getToken(QuorumParser.INT, 0); }
		public TerminalNode MINUS() { return getToken(QuorumParser.MINUS, 0); }
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PARENT() { return getToken(QuorumParser.PARENT, 0); }
		public SelectorContext selector() {
			return getRuleContext(SelectorContext.class,0);
		}
		public TerminalNode ME() { return getToken(QuorumParser.ME, 0); }
		public Qualified_nameContext qualified_name(int i) {
			return getRuleContext(Qualified_nameContext.class,i);
		}
		public TerminalNode BOOLEAN() { return getToken(QuorumParser.BOOLEAN, 0); }
		public TerminalNode COLON(int i) {
			return getToken(QuorumParser.COLON, i);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public Function_expression_listContext function_expression_list() {
			return getRuleContext(Function_expression_listContext.class,0);
		}
		public TerminalNode NULL() { return getToken(QuorumParser.NULL, 0); }
		public TerminalNode STRING() { return getToken(QuorumParser.STRING, 0); }
		public List<TerminalNode> COLON() { return getTokens(QuorumParser.COLON); }
		public TerminalNode DECIMAL() { return getToken(QuorumParser.DECIMAL, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_atom);
		int _la;
		try {
			setState(614);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(547); qualified_name();
				setState(550);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(548); match(COLON);
					setState(549); match(ID);
					}
				}

				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(552); qualified_name();
				setState(553); match(COLON);
				setState(554); match(PARENT);
				setState(555); match(COLON);
				setState(556); qualified_name();
				setState(557); match(COLON);
				setState(558); match(ID);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(560); qualified_name();
				setState(563);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(561); match(COLON);
					setState(562); match(ID);
					}
				}

				setState(565); match(LEFT_PAREN);
				setState(566); function_expression_list();
				setState(567); match(RIGHT_PAREN);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(569); selector();
				setState(570); match(COLON);
				setState(571); qualified_name();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(573); match(PARENT);
				setState(574); match(COLON);
				setState(575); qualified_name();
				setState(576); match(COLON);
				setState(577); match(ID);
				setState(578); match(LEFT_PAREN);
				setState(579); function_expression_list();
				setState(580); match(RIGHT_PAREN);
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(582); match(ME);
				setState(583); match(COLON);
				setState(584); qualified_name();
				setState(587);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(585); match(COLON);
					setState(586); match(ID);
					}
				}

				setState(589); match(LEFT_PAREN);
				setState(590); function_expression_list();
				setState(591); match(RIGHT_PAREN);
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(594);
				_la = _input.LA(1);
				if (_la==MINUS) {
					{
					setState(593); match(MINUS);
					}
				}

				setState(596); match(INT);
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(597); match(BOOLEAN);
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(599);
				_la = _input.LA(1);
				if (_la==MINUS) {
					{
					setState(598); match(MINUS);
					}
				}

				setState(601); match(DECIMAL);
				}
				break;

			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(602); match(STRING);
				}
				break;

			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(603); match(NULL);
				}
				break;

			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(604); match(ME);
				}
				break;

			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(605); match(INPUT);
				setState(606); match(LEFT_PAREN);
				setState(607); expression();
				setState(608); match(RIGHT_PAREN);
				}
				break;

			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(610); match(LEFT_PAREN);
				setState(611); expression();
				setState(612); match(RIGHT_PAREN);
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
		enterRule(_localctx, 80, RULE_function_expression_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(624);
			_la = _input.LA(1);
			if (((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & ((1L << (ME - 6)) | (1L << (PARENT - 6)) | (1L << (CAST - 6)) | (1L << (INPUT - 6)) | (1L << (NULL - 6)) | (1L << (NOT - 6)) | (1L << (MINUS - 6)) | (1L << (LEFT_PAREN - 6)) | (1L << (BOOLEAN - 6)) | (1L << (INT - 6)) | (1L << (DECIMAL - 6)) | (1L << (ID - 6)) | (1L << (STRING - 6)))) != 0)) {
				{
				setState(616); expression();
				setState(621);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(617); match(COMMA);
					setState(618); expression();
					}
					}
					setState(623);
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

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3F\u0275\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\6\2W\n\2\r\2\16\2X\3\2\6\2\\\n\2\r\2\16\2]\3\2\3\2\3\2\3\2\6\2d\n\2\r"+
		"\2\16\2e\3\2\5\2i\n\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\5\5w\n\5\3\5\5\5z\n\5\3\5\7\5}\n\5\f\5\16\5\u0080\13\5\3\5\3\5\5\5\u0084"+
		"\n\5\3\6\6\6\u0087\n\6\r\6\16\6\u0088\3\6\5\6\u008c\n\6\3\6\6\6\u008f"+
		"\n\6\r\6\16\6\u0090\5\6\u0093\n\6\3\7\3\7\3\7\5\7\u0098\n\7\3\7\3\7\3"+
		"\7\5\7\u009d\n\7\7\7\u009f\n\7\f\7\16\7\u00a2\13\7\3\b\3\b\3\t\3\t\5\t"+
		"\u00a8\n\t\3\t\5\t\u00ab\n\t\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00b3\n\n\f\n"+
		"\16\n\u00b6\13\n\5\n\u00b8\n\n\3\n\5\n\u00bb\n\n\3\n\3\n\5\n\u00bf\n\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00cb\n\n\f\n\16\n\u00ce"+
		"\13\n\5\n\u00d0\n\n\3\n\5\n\u00d3\n\n\3\n\3\n\5\n\u00d7\n\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\7\n\u00e0\n\n\f\n\16\n\u00e3\13\n\5\n\u00e5\n\n\3\n"+
		"\5\n\u00e8\n\n\3\n\3\n\5\n\u00ec\n\n\3\n\3\n\3\n\3\n\3\n\5\n\u00f3\n\n"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\7\f\u00fb\n\f\f\f\16\f\u00fe\13\f\3\r\7\r"+
		"\u0101\n\r\f\r\16\r\u0104\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\5\16\u010f\n\16\3\17\3\17\3\17\5\17\u0114\n\17\3\17\3\17\3\17\3"+
		"\17\7\17\u011a\n\17\f\17\16\17\u011d\13\17\5\17\u011f\n\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u012c\n\17\f\17\16\17"+
		"\u012f\13\17\5\17\u0131\n\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u013a"+
		"\n\17\3\17\3\17\3\17\3\17\7\17\u0140\n\17\f\17\16\17\u0143\13\17\5\17"+
		"\u0145\n\17\3\17\3\17\5\17\u0149\n\17\3\20\3\20\3\20\3\20\3\20\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\6\21\u0156\n\21\r\21\16\21\u0157\3\21\3\21\5\21"+
		"\u015c\n\21\3\21\3\21\5\21\u0160\n\21\3\21\3\21\3\22\3\22\3\22\3\22\3"+
		"\22\7\22\u0169\n\22\f\22\16\22\u016c\13\22\5\22\u016e\n\22\3\23\3\23\3"+
		"\23\3\24\3\24\3\24\3\25\3\25\3\25\5\25\u0179\n\25\3\26\3\26\3\26\3\26"+
		"\7\26\u017f\n\26\f\26\16\26\u0182\13\26\3\26\3\26\3\27\3\27\3\27\3\27"+
		"\7\27\u018a\n\27\f\27\16\27\u018d\13\27\3\27\3\27\3\30\3\30\3\31\3\31"+
		"\5\31\u0195\n\31\3\31\3\31\3\31\3\31\5\31\u019b\n\31\3\32\3\32\3\32\5"+
		"\32\u01a0\n\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u01a9\n\32\3\32"+
		"\3\32\3\32\3\32\3\32\5\32\u01b0\n\32\3\32\5\32\u01b3\n\32\3\32\3\32\3"+
		"\32\5\32\u01b8\n\32\5\32\u01ba\n\32\3\33\3\33\3\33\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\7\34\u01c6\n\34\f\34\16\34\u01c9\13\34\3\34\3\34\5\34"+
		"\u01cd\n\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u01d7\n\35\3"+
		"\35\3\35\3\35\3\36\3\36\3\36\5\36\u01df\n\36\3\37\3\37\3 \3 \3!\3!\3!"+
		"\7!\u01e8\n!\f!\16!\u01eb\13!\3\"\3\"\3\"\7\"\u01f0\n\"\f\"\16\"\u01f3"+
		"\13\"\3#\3#\3#\7#\u01f8\n#\f#\16#\u01fb\13#\3$\3$\3$\5$\u0200\n$\3%\3"+
		"%\3%\7%\u0205\n%\f%\16%\u0208\13%\3&\3&\3&\7&\u020d\n&\f&\16&\u0210\13"+
		"&\3\'\3\'\3\'\7\'\u0215\n\'\f\'\16\'\u0218\13\'\3(\3(\3(\3(\3(\3(\3(\3"+
		"(\3(\3(\5(\u0224\n(\3)\3)\3)\5)\u0229\n)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3"+
		")\3)\5)\u0236\n)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3"+
		")\3)\3)\3)\3)\5)\u024e\n)\3)\3)\3)\3)\3)\5)\u0255\n)\3)\3)\3)\5)\u025a"+
		"\n)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\5)\u0269\n)\3*\3*\3*\7*\u026e"+
		"\n*\f*\16*\u0271\13*\5*\u0273\n*\3*\2+\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPR\2\b\3\2\n\13\4\2\t\t\30\30\4"+
		"\2**--\3\2.\61\3\2\62\63\3\2\64\66\u02b7\2h\3\2\2\2\4m\3\2\2\2\6p\3\2"+
		"\2\2\b\u0083\3\2\2\2\n\u0092\3\2\2\2\f\u0094\3\2\2\2\16\u00a3\3\2\2\2"+
		"\20\u00aa\3\2\2\2\22\u00f2\3\2\2\2\24\u00f4\3\2\2\2\26\u00f7\3\2\2\2\30"+
		"\u0102\3\2\2\2\32\u010e\3\2\2\2\34\u0148\3\2\2\2\36\u014a\3\2\2\2 \u014f"+
		"\3\2\2\2\"\u0163\3\2\2\2$\u016f\3\2\2\2&\u0172\3\2\2\2(\u0175\3\2\2\2"+
		"*\u017a\3\2\2\2,\u0185\3\2\2\2.\u0190\3\2\2\2\60\u019a\3\2\2\2\62\u01b9"+
		"\3\2\2\2\64\u01bb\3\2\2\2\66\u01be\3\2\2\28\u01d0\3\2\2\2:\u01de\3\2\2"+
		"\2<\u01e0\3\2\2\2>\u01e2\3\2\2\2@\u01e4\3\2\2\2B\u01ec\3\2\2\2D\u01f4"+
		"\3\2\2\2F\u01fc\3\2\2\2H\u0201\3\2\2\2J\u0209\3\2\2\2L\u0211\3\2\2\2N"+
		"\u0223\3\2\2\2P\u0268\3\2\2\2R\u0272\3\2\2\2TV\5\4\3\2UW\5\6\4\2VU\3\2"+
		"\2\2WX\3\2\2\2XV\3\2\2\2XY\3\2\2\2Yi\3\2\2\2Z\\\5\6\4\2[Z\3\2\2\2\\]\3"+
		"\2\2\2][\3\2\2\2]^\3\2\2\2^_\3\2\2\2_`\5\4\3\2`i\3\2\2\2ai\5\4\3\2bd\5"+
		"\6\4\2cb\3\2\2\2de\3\2\2\2ec\3\2\2\2ef\3\2\2\2fi\3\2\2\2gi\3\2\2\2hT\3"+
		"\2\2\2h[\3\2\2\2ha\3\2\2\2hc\3\2\2\2hg\3\2\2\2ij\3\2\2\2jk\5\b\5\2kl\7"+
		"\2\2\3l\3\3\2\2\2mn\7\31\2\2no\5\26\f\2o\5\3\2\2\2pq\7(\2\2qr\5\26\f\2"+
		"r\7\3\2\2\2st\7>\2\2tv\7B\2\2uw\5*\26\2vu\3\2\2\2vw\3\2\2\2wy\3\2\2\2"+
		"xz\5\f\7\2yx\3\2\2\2yz\3\2\2\2z~\3\2\2\2{}\5\20\t\2|{\3\2\2\2}\u0080\3"+
		"\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0081\3\2\2\2\u0080~\3\2\2\2\u0081\u0084"+
		"\7=\2\2\u0082\u0084\5\n\6\2\u0083s\3\2\2\2\u0083\u0082\3\2\2\2\u0084\t"+
		"\3\2\2\2\u0085\u0087\5\32\16\2\u0086\u0085\3\2\2\2\u0087\u0088\3\2\2\2"+
		"\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u0093\3\2\2\2\u008a\u008c"+
		"\5\16\b\2\u008b\u008a\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\3\2\2\2"+
		"\u008d\u008f\5\22\n\2\u008e\u008b\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u008e"+
		"\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0093\3\2\2\2\u0092\u0086\3\2\2\2\u0092"+
		"\u008e\3\2\2\2\u0093\13\3\2\2\2\u0094\u0095\7\23\2\2\u0095\u0097\5\26"+
		"\f\2\u0096\u0098\5,\27\2\u0097\u0096\3\2\2\2\u0097\u0098\3\2\2\2\u0098"+
		"\u00a0\3\2\2\2\u0099\u009a\7,\2\2\u009a\u009c\5\26\f\2\u009b\u009d\5,"+
		"\27\2\u009c\u009b\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009f\3\2\2\2\u009e"+
		"\u0099\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2"+
		"\2\2\u00a1\r\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a4\t\2\2\2\u00a4\17"+
		"\3\2\2\2\u00a5\u00ab\5\62\32\2\u00a6\u00a8\5\16\b\2\u00a7\u00a6\3\2\2"+
		"\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00ab\5\22\n\2\u00aa"+
		"\u00a5\3\2\2\2\u00aa\u00a7\3\2\2\2\u00ab\21\3\2\2\2\u00ac\u00ad\7\"\2"+
		"\2\u00ad\u00ba\7B\2\2\u00ae\u00b7\79\2\2\u00af\u00b4\5\24\13\2\u00b0\u00b1"+
		"\7,\2\2\u00b1\u00b3\5\24\13\2\u00b2\u00b0\3\2\2\2\u00b3\u00b6\3\2\2\2"+
		"\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4"+
		"\3\2\2\2\u00b7\u00af\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9"+
		"\u00bb\7:\2\2\u00ba\u00ae\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00be\3\2"+
		"\2\2\u00bc\u00bd\7\35\2\2\u00bd\u00bf\5\60\31\2\u00be\u00bc\3\2\2\2\u00be"+
		"\u00bf\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\5\30\r\2\u00c1\u00c2\7"+
		"=\2\2\u00c2\u00f3\3\2\2\2\u00c3\u00c4\7\21\2\2\u00c4\u00c5\7\"\2\2\u00c5"+
		"\u00d2\7B\2\2\u00c6\u00cf\79\2\2\u00c7\u00cc\5\24\13\2\u00c8\u00c9\7,"+
		"\2\2\u00c9\u00cb\5\24\13\2\u00ca\u00c8\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc"+
		"\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cc\3\2"+
		"\2\2\u00cf\u00c7\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1"+
		"\u00d3\7:\2\2\u00d2\u00c6\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d6\3\2"+
		"\2\2\u00d4\u00d5\7\35\2\2\u00d5\u00d7\5\60\31\2\u00d6\u00d4\3\2\2\2\u00d6"+
		"\u00d7\3\2\2\2\u00d7\u00f3\3\2\2\2\u00d8\u00d9\7\22\2\2\u00d9\u00da\7"+
		"\"\2\2\u00da\u00e7\7B\2\2\u00db\u00e4\79\2\2\u00dc\u00e1\5\24\13\2\u00dd"+
		"\u00de\7,\2\2\u00de\u00e0\5\24\13\2\u00df\u00dd\3\2\2\2\u00e0\u00e3\3"+
		"\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3"+
		"\u00e1\3\2\2\2\u00e4\u00dc\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e6\3\2"+
		"\2\2\u00e6\u00e8\7:\2\2\u00e7\u00db\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8"+
		"\u00eb\3\2\2\2\u00e9\u00ea\7\35\2\2\u00ea\u00ec\5\60\31\2\u00eb\u00e9"+
		"\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00f3\3\2\2\2\u00ed\u00ee\7\4\2\2\u00ee"+
		"\u00ef\7\5\2\2\u00ef\u00f0\5\30\r\2\u00f0\u00f1\7=\2\2\u00f1\u00f3\3\2"+
		"\2\2\u00f2\u00ac\3\2\2\2\u00f2\u00c3\3\2\2\2\u00f2\u00d8\3\2\2\2\u00f2"+
		"\u00ed\3\2\2\2\u00f3\23\3\2\2\2\u00f4\u00f5\5\60\31\2\u00f5\u00f6\7B\2"+
		"\2\u00f6\25\3\2\2\2\u00f7\u00fc\7B\2\2\u00f8\u00f9\7+\2\2\u00f9\u00fb"+
		"\7B\2\2\u00fa\u00f8\3\2\2\2\u00fb\u00fe\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fc"+
		"\u00fd\3\2\2\2\u00fd\27\3\2\2\2\u00fe\u00fc\3\2\2\2\u00ff\u0101\5\32\16"+
		"\2\u0100\u00ff\3\2\2\2\u0101\u0104\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103"+
		"\3\2\2\2\u0103\31\3\2\2\2\u0104\u0102\3\2\2\2\u0105\u010f\5\34\17\2\u0106"+
		"\u010f\5\66\34\2\u0107\u010f\5\62\32\2\u0108\u010f\58\35\2\u0109\u010f"+
		"\5(\25\2\u010a\u010f\5$\23\2\u010b\u010f\5&\24\2\u010c\u010f\5 \21\2\u010d"+
		"\u010f\5\36\20\2\u010e\u0105\3\2\2\2\u010e\u0106\3\2\2\2\u010e\u0107\3"+
		"\2\2\2\u010e\u0108\3\2\2\2\u010e\u0109\3\2\2\2\u010e\u010a\3\2\2\2\u010e"+
		"\u010b\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010d\3\2\2\2\u010f\33\3\2\2"+
		"\2\u0110\u0113\5\26\f\2\u0111\u0112\7#\2\2\u0112\u0114\7B\2\2\u0113\u0111"+
		"\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u011e\79\2\2\u0116"+
		"\u011b\5> \2\u0117\u0118\7,\2\2\u0118\u011a\5> \2\u0119\u0117\3\2\2\2"+
		"\u011a\u011d\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011f"+
		"\3\2\2\2\u011d\u011b\3\2\2\2\u011e\u0116\3\2\2\2\u011e\u011f\3\2\2\2\u011f"+
		"\u0120\3\2\2\2\u0120\u0121\7:\2\2\u0121\u0149\3\2\2\2\u0122\u0123\7\20"+
		"\2\2\u0123\u0124\7#\2\2\u0124\u0125\5\26\f\2\u0125\u0126\7#\2\2\u0126"+
		"\u0127\7B\2\2\u0127\u0130\79\2\2\u0128\u012d\5> \2\u0129\u012a\7,\2\2"+
		"\u012a\u012c\5> \2\u012b\u0129\3\2\2\2\u012c\u012f\3\2\2\2\u012d\u012b"+
		"\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u0131\3\2\2\2\u012f\u012d\3\2\2\2\u0130"+
		"\u0128\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0133\7:"+
		"\2\2\u0133\u0149\3\2\2\2\u0134\u0135\7\b\2\2\u0135\u0136\7#\2\2\u0136"+
		"\u0139\5\26\f\2\u0137\u0138\7#\2\2\u0138\u013a\7B\2\2\u0139\u0137\3\2"+
		"\2\2\u0139\u013a\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u0144\79\2\2\u013c"+
		"\u0141\5> \2\u013d\u013e\7,\2\2\u013e\u0140\5> \2\u013f\u013d\3\2\2\2"+
		"\u0140\u0143\3\2\2\2\u0141\u013f\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0145"+
		"\3\2\2\2\u0143\u0141\3\2\2\2\u0144\u013c\3\2\2\2\u0144\u0145\3\2\2\2\u0145"+
		"\u0146\3\2\2\2\u0146\u0147\7:\2\2\u0147\u0149\3\2\2\2\u0148\u0110\3\2"+
		"\2\2\u0148\u0122\3\2\2\2\u0148\u0134\3\2\2\2\u0149\35\3\2\2\2\u014a\u014b"+
		"\7\f\2\2\u014b\u014c\79\2\2\u014c\u014d\5<\37\2\u014d\u014e\7:\2\2\u014e"+
		"\37\3\2\2\2\u014f\u0150\7\17\2\2\u0150\u015f\5\30\r\2\u0151\u0152\7\r"+
		"\2\2\u0152\u0153\5\"\22\2\u0153\u0154\5\30\r\2\u0154\u0156\3\2\2\2\u0155"+
		"\u0151\3\2\2\2\u0156\u0157\3\2\2\2\u0157\u0155\3\2\2\2\u0157\u0158\3\2"+
		"\2\2\u0158\u015b\3\2\2\2\u0159\u015a\7\16\2\2\u015a\u015c\5\30\r\2\u015b"+
		"\u0159\3\2\2\2\u015b\u015c\3\2\2\2\u015c\u0160\3\2\2\2\u015d\u015e\7\16"+
		"\2\2\u015e\u0160\5\30\r\2\u015f\u0155\3\2\2\2\u015f\u015d\3\2\2\2\u0160"+
		"\u0161\3\2\2\2\u0161\u0162\7=\2\2\u0162!\3\2\2\2\u0163\u016d\7B\2\2\u0164"+
		"\u0165\7\23\2\2\u0165\u016a\5\26\f\2\u0166\u0167\7 \2\2\u0167\u0169\5"+
		"\26\f\2\u0168\u0166\3\2\2\2\u0169\u016c\3\2\2\2\u016a\u0168\3\2\2\2\u016a"+
		"\u016b\3\2\2\2\u016b\u016e\3\2\2\2\u016c\u016a\3\2\2\2\u016d\u0164\3\2"+
		"\2\2\u016d\u016e\3\2\2\2\u016e#\3\2\2\2\u016f\u0170\7\3\2\2\u0170\u0171"+
		"\5<\37\2\u0171%\3\2\2\2\u0172\u0173\7\26\2\2\u0173\u0174\5<\37\2\u0174"+
		"\'\3\2\2\2\u0175\u0178\7\36\2\2\u0176\u0179\5<\37\2\u0177\u0179\7\27\2"+
		"\2\u0178\u0176\3\2\2\2\u0178\u0177\3\2\2\2\u0179)\3\2\2\2\u017a\u017b"+
		"\7\60\2\2\u017b\u0180\7B\2\2\u017c\u017d\7,\2\2\u017d\u017f\7B\2\2\u017e"+
		"\u017c\3\2\2\2\u017f\u0182\3\2\2\2\u0180\u017e\3\2\2\2\u0180\u0181\3\2"+
		"\2\2\u0181\u0183\3\2\2\2\u0182\u0180\3\2\2\2\u0183\u0184\7.\2\2\u0184"+
		"+\3\2\2\2\u0185\u0186\7\60\2\2\u0186\u018b\5\60\31\2\u0187\u0188\7,\2"+
		"\2\u0188\u018a\5\60\31\2\u0189\u0187\3\2\2\2\u018a\u018d\3\2\2\2\u018b"+
		"\u0189\3\2\2\2\u018b\u018c\3\2\2\2\u018c\u018e\3\2\2\2\u018d\u018b\3\2"+
		"\2\2\u018e\u018f\7.\2\2\u018f-\3\2\2\2\u0190\u0191\5\26\f\2\u0191/\3\2"+
		"\2\2\u0192\u0194\5\26\f\2\u0193\u0195\5,\27\2\u0194\u0193\3\2\2\2\u0194"+
		"\u0195\3\2\2\2\u0195\u019b\3\2\2\2\u0196\u019b\7$\2\2\u0197\u019b\7%\2"+
		"\2\u0198\u019b\7&\2\2\u0199\u019b\7\'\2\2\u019a\u0192\3\2\2\2\u019a\u0196"+
		"\3\2\2\2\u019a\u0197\3\2\2\2\u019a\u0198\3\2\2\2\u019a\u0199\3\2\2\2\u019b"+
		"\61\3\2\2\2\u019c\u019d\5:\36\2\u019d\u019e\7#\2\2\u019e\u01a0\3\2\2\2"+
		"\u019f\u019c\3\2\2\2\u019f\u01a0\3\2\2\2\u01a0\u01a1\3\2\2\2\u01a1\u01a2"+
		"\7B\2\2\u01a2\u01ba\5\64\33\2\u01a3\u01a8\5\26\f\2\u01a4\u01a5\7#\2\2"+
		"\u01a5\u01a6\7\20\2\2\u01a6\u01a7\7#\2\2\u01a7\u01a9\5\26\f\2\u01a8\u01a4"+
		"\3\2\2\2\u01a8\u01a9\3\2\2\2\u01a9\u01aa\3\2\2\2\u01aa\u01ab\7#\2\2\u01ab"+
		"\u01ac\7B\2\2\u01ac\u01ad\5\64\33\2\u01ad\u01ba\3\2\2\2\u01ae\u01b0\5"+
		"\16\b\2\u01af\u01ae\3\2\2\2\u01af\u01b0\3\2\2\2\u01b0\u01b2\3\2\2\2\u01b1"+
		"\u01b3\7\6\2\2\u01b2\u01b1\3\2\2\2\u01b2\u01b3\3\2\2\2\u01b3\u01b4\3\2"+
		"\2\2\u01b4\u01b5\5\60\31\2\u01b5\u01b7\7B\2\2\u01b6\u01b8\5\64\33\2\u01b7"+
		"\u01b6\3\2\2\2\u01b7\u01b8\3\2\2\2\u01b8\u01ba\3\2\2\2\u01b9\u019f\3\2"+
		"\2\2\u01b9\u01a3\3\2\2\2\u01b9\u01af\3\2\2\2\u01ba\63\3\2\2\2\u01bb\u01bc"+
		"\7-\2\2\u01bc\u01bd\5<\37\2\u01bd\65\3\2\2\2\u01be\u01bf\7<\2\2\u01bf"+
		"\u01c0\5<\37\2\u01c0\u01c7\5\30\r\2\u01c1\u01c2\7\7\2\2\u01c2\u01c3\5"+
		"<\37\2\u01c3\u01c4\5\30\r\2\u01c4\u01c6\3\2\2\2\u01c5\u01c1\3\2\2\2\u01c6"+
		"\u01c9\3\2\2\2\u01c7\u01c5\3\2\2\2\u01c7\u01c8\3\2\2\2\u01c8\u01cc\3\2"+
		"\2\2\u01c9\u01c7\3\2\2\2\u01ca\u01cb\7\34\2\2\u01cb\u01cd\5\30\r\2\u01cc"+
		"\u01ca\3\2\2\2\u01cc\u01cd\3\2\2\2\u01cd\u01ce\3\2\2\2\u01ce\u01cf\7="+
		"\2\2\u01cf\67\3\2\2\2\u01d0\u01d6\7\33\2\2\u01d1\u01d2\5<\37\2\u01d2\u01d3"+
		"\7\32\2\2\u01d3\u01d7\3\2\2\2\u01d4\u01d5\t\3\2\2\u01d5\u01d7\5<\37\2"+
		"\u01d6\u01d1\3\2\2\2\u01d6\u01d4\3\2\2\2\u01d7\u01d8\3\2\2\2\u01d8\u01d9"+
		"\5\30\r\2\u01d9\u01da\7=\2\2\u01da9\3\2\2\2\u01db\u01dc\7\20\2\2\u01dc"+
		"\u01df\7#\2\2\u01dd\u01df\7\b\2\2\u01de\u01db\3\2\2\2\u01de\u01dd\3\2"+
		"\2\2\u01df;\3\2\2\2\u01e0\u01e1\5> \2\u01e1=\3\2\2\2\u01e2\u01e3\5@!\2"+
		"\u01e3?\3\2\2\2\u01e4\u01e9\5B\"\2\u01e5\u01e6\7 \2\2\u01e6\u01e8\5B\""+
		"\2\u01e7\u01e5\3\2\2\2\u01e8\u01eb\3\2\2\2\u01e9\u01e7\3\2\2\2\u01e9\u01ea"+
		"\3\2\2\2\u01eaA\3\2\2\2\u01eb\u01e9\3\2\2\2\u01ec\u01f1\5D#\2\u01ed\u01ee"+
		"\7\37\2\2\u01ee\u01f0\5D#\2\u01ef\u01ed\3\2\2\2\u01f0\u01f3\3\2\2\2\u01f1"+
		"\u01ef\3\2\2\2\u01f1\u01f2\3\2\2\2\u01f2C\3\2\2\2\u01f3\u01f1\3\2\2\2"+
		"\u01f4\u01f9\5F$\2\u01f5\u01f6\t\4\2\2\u01f6\u01f8\5F$\2\u01f7\u01f5\3"+
		"\2\2\2\u01f8\u01fb\3\2\2\2\u01f9\u01f7\3\2\2\2\u01f9\u01fa\3\2\2\2\u01fa"+
		"E\3\2\2\2\u01fb\u01f9\3\2\2\2\u01fc\u01ff\5H%\2\u01fd\u01fe\7\23\2\2\u01fe"+
		"\u0200\5.\30\2\u01ff\u01fd\3\2\2\2\u01ff\u0200\3\2\2\2\u0200G\3\2\2\2"+
		"\u0201\u0206\5J&\2\u0202\u0203\t\5\2\2\u0203\u0205\5J&\2\u0204\u0202\3"+
		"\2\2\2\u0205\u0208\3\2\2\2\u0206\u0204\3\2\2\2\u0206\u0207\3\2\2\2\u0207"+
		"I\3\2\2\2\u0208\u0206\3\2\2\2\u0209\u020e\5L\'\2\u020a\u020b\t\6\2\2\u020b"+
		"\u020d\5L\'\2\u020c\u020a\3\2\2\2\u020d\u0210\3\2\2\2\u020e\u020c\3\2"+
		"\2\2\u020e\u020f\3\2\2\2\u020fK\3\2\2\2\u0210\u020e\3\2\2\2\u0211\u0216"+
		"\5N(\2\u0212\u0213\t\7\2\2\u0213\u0215\5N(\2\u0214\u0212\3\2\2\2\u0215"+
		"\u0218\3\2\2\2\u0216\u0214\3\2\2\2\u0216\u0217\3\2\2\2\u0217M\3\2\2\2"+
		"\u0218\u0216\3\2\2\2\u0219\u021a\7)\2\2\u021a\u0224\5P)\2\u021b\u021c"+
		"\7\24\2\2\u021c\u021d\79\2\2\u021d\u021e\5\60\31\2\u021e\u021f\7,\2\2"+
		"\u021f\u0220\5> \2\u0220\u0221\7:\2\2\u0221\u0224\3\2\2\2\u0222\u0224"+
		"\5P)\2\u0223\u0219\3\2\2\2\u0223\u021b\3\2\2\2\u0223\u0222\3\2\2\2\u0224"+
		"O\3\2\2\2\u0225\u0228\5\26\f\2\u0226\u0227\7#\2\2\u0227\u0229\7B\2\2\u0228"+
		"\u0226\3\2\2\2\u0228\u0229\3\2\2\2\u0229\u0269\3\2\2\2\u022a\u022b\5\26"+
		"\f\2\u022b\u022c\7#\2\2\u022c\u022d\7\20\2\2\u022d\u022e\7#\2\2\u022e"+
		"\u022f\5\26\f\2\u022f\u0230\7#\2\2\u0230\u0231\7B\2\2\u0231\u0269\3\2"+
		"\2\2\u0232\u0235\5\26\f\2\u0233\u0234\7#\2\2\u0234\u0236\7B\2\2\u0235"+
		"\u0233\3\2\2\2\u0235\u0236\3\2\2\2\u0236\u0237\3\2\2\2\u0237\u0238\79"+
		"\2\2\u0238\u0239\5R*\2\u0239\u023a\7:\2\2\u023a\u0269\3\2\2\2\u023b\u023c"+
		"\5:\36\2\u023c\u023d\7#\2\2\u023d\u023e\5\26\f\2\u023e\u0269\3\2\2\2\u023f"+
		"\u0240\7\20\2\2\u0240\u0241\7#\2\2\u0241\u0242\5\26\f\2\u0242\u0243\7"+
		"#\2\2\u0243\u0244\7B\2\2\u0244\u0245\79\2\2\u0245\u0246\5R*\2\u0246\u0247"+
		"\7:\2\2\u0247\u0269\3\2\2\2\u0248\u0249\7\b\2\2\u0249\u024a\7#\2\2\u024a"+
		"\u024d\5\26\f\2\u024b\u024c\7#\2\2\u024c\u024e\7B\2\2\u024d\u024b\3\2"+
		"\2\2\u024d\u024e\3\2\2\2\u024e\u024f\3\2\2\2\u024f\u0250\79\2\2\u0250"+
		"\u0251\5R*\2\u0251\u0252\7:\2\2\u0252\u0269\3\2\2\2\u0253\u0255\7\63\2"+
		"\2\u0254\u0253\3\2\2\2\u0254\u0255\3\2\2\2\u0255\u0256\3\2\2\2\u0256\u0269"+
		"\7@\2\2\u0257\u0269\7?\2\2\u0258\u025a\7\63\2\2\u0259\u0258\3\2\2\2\u0259"+
		"\u025a\3\2\2\2\u025a\u025b\3\2\2\2\u025b\u0269\7A\2\2\u025c\u0269\7C\2"+
		"\2\u025d\u0269\7!\2\2\u025e\u0269\7\b\2\2\u025f\u0260\7\25\2\2\u0260\u0261"+
		"\79\2\2\u0261\u0262\5> \2\u0262\u0263\7:\2\2\u0263\u0269\3\2\2\2\u0264"+
		"\u0265\79\2\2\u0265\u0266\5> \2\u0266\u0267\7:\2\2\u0267\u0269\3\2\2\2"+
		"\u0268\u0225\3\2\2\2\u0268\u022a\3\2\2\2\u0268\u0232\3\2\2\2\u0268\u023b"+
		"\3\2\2\2\u0268\u023f\3\2\2\2\u0268\u0248\3\2\2\2\u0268\u0254\3\2\2\2\u0268"+
		"\u0257\3\2\2\2\u0268\u0259\3\2\2\2\u0268\u025c\3\2\2\2\u0268\u025d\3\2"+
		"\2\2\u0268\u025e\3\2\2\2\u0268\u025f\3\2\2\2\u0268\u0264\3\2\2\2\u0269"+
		"Q\3\2\2\2\u026a\u026f\5> \2\u026b\u026c\7,\2\2\u026c\u026e\5> \2\u026d"+
		"\u026b\3\2\2\2\u026e\u0271\3\2\2\2\u026f\u026d\3\2\2\2\u026f\u0270\3\2"+
		"\2\2\u0270\u0273\3\2\2\2\u0271\u026f\3\2\2\2\u0272\u026a\3\2\2\2\u0272"+
		"\u0273\3\2\2\2\u0273S\3\2\2\2PX]ehvy~\u0083\u0088\u008b\u0090\u0092\u0097"+
		"\u009c\u00a0\u00a7\u00aa\u00b4\u00b7\u00ba\u00be\u00cc\u00cf\u00d2\u00d6"+
		"\u00e1\u00e4\u00e7\u00eb\u00f2\u00fc\u0102\u010e\u0113\u011b\u011e\u012d"+
		"\u0130\u0139\u0141\u0144\u0148\u0157\u015b\u015f\u016a\u016d\u0178\u0180"+
		"\u018b\u0194\u019a\u019f\u01a8\u01af\u01b2\u01b7\u01b9\u01c7\u01cc\u01d6"+
		"\u01de\u01e9\u01f1\u01f9\u01ff\u0206\u020e\u0216\u0223\u0228\u0235\u024d"+
		"\u0254\u0259\u0268\u026f\u0272";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}