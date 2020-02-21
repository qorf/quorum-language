// Generated from /Users/stefika/Repositories/quorum-language/Plugins/ParserPlugin/src/plugins/quorum/Libraries/Language/Compile/QuorumParser.g4 by ANTLR 4.8
package plugins.quorum.Libraries.Language.Compile;
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
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OUTPUT=1, ON=2, CREATE=3, CONSTANT=4, ELSE_IF=5, ME=6, UNTIL=7, PUBLIC=8, 
		PRIVATE=9, ALERT=10, DETECT=11, ALWAYS=12, CHECK=13, PARENT=14, BLUEPRINT=15, 
		NATIVE=16, INHERITS=17, CAST=18, INPUT=19, SAY=20, NOW=21, WHILE=22, PACKAGE_NAME=23, 
		TIMES=24, REPEAT=25, ELSE=26, RETURNS=27, RETURN=28, AND=29, OR=30, NULL=31, 
		STATIC=32, ACTION=33, COLON=34, INTEGER_KEYWORD=35, NUMBER_KEYWORD=36, 
		TEXT=37, BOOLEAN_KEYWORD=38, USE=39, NOT=40, NOTEQUALS=41, PERIOD=42, 
		COMMA=43, EQUALITY=44, GREATER=45, GREATER_EQUAL=46, LESS=47, LESS_EQUAL=48, 
		PLUS=49, MINUS=50, MULTIPLY=51, DIVIDE=52, MODULO=53, LEFT_SQR_BRACE=54, 
		RIGHT_SQR_BRACE=55, LEFT_PAREN=56, RIGHT_PAREN=57, DOUBLE_QUOTE=58, IF=59, 
		END=60, CLASS=61, BOOLEAN=62, INT=63, DECIMAL=64, ID=65, STRING=66, NEWLINE=67, 
		WS=68, COMMENTS=69;
	public static final int
		RULE_start = 0, RULE_package_rule = 1, RULE_reference = 2, RULE_class_declaration = 3, 
		RULE_no_class_stmnts = 4, RULE_inherit_stmnts = 5, RULE_inherit_stmt = 6, 
		RULE_access_modifier = 7, RULE_class_stmnts = 8, RULE_method_declaration = 9, 
		RULE_method_shared = 10, RULE_formal_parameter = 11, RULE_qualified_name = 12, 
		RULE_block = 13, RULE_statement = 14, RULE_solo_method_call = 15, RULE_solo_method_required_method_part = 16, 
		RULE_alert_statement = 17, RULE_check_statement = 18, RULE_detect_statement = 19, 
		RULE_always_statement = 20, RULE_print_statement = 21, RULE_speak_statement = 22, 
		RULE_return_statement = 23, RULE_generic_declaration = 24, RULE_generic_statement = 25, 
		RULE_class_type = 26, RULE_assignment_declaration = 27, RULE_assignment_statement = 28, 
		RULE_if_statement = 29, RULE_elseif_statement = 30, RULE_else_statement = 31, 
		RULE_loop_statement = 32, RULE_initial_parent_action_call = 33, RULE_action_call = 34, 
		RULE_expression = 35, RULE_function_expression_list = 36;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "package_rule", "reference", "class_declaration", "no_class_stmnts", 
			"inherit_stmnts", "inherit_stmt", "access_modifier", "class_stmnts", 
			"method_declaration", "method_shared", "formal_parameter", "qualified_name", 
			"block", "statement", "solo_method_call", "solo_method_required_method_part", 
			"alert_statement", "check_statement", "detect_statement", "always_statement", 
			"print_statement", "speak_statement", "return_statement", "generic_declaration", 
			"generic_statement", "class_type", "assignment_declaration", "assignment_statement", 
			"if_statement", "elseif_statement", "else_statement", "loop_statement", 
			"initial_parent_action_call", "action_call", "expression", "function_expression_list"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'output'", "'on'", "'create'", "'constant'", "'elseif'", "'me'", 
			"'until'", "'public'", "'private'", "'alert'", "'detect'", "'always'", 
			"'check'", "'parent'", "'blueprint'", "'system'", "'is'", "'cast'", "'input'", 
			"'say'", "'now'", "'while'", "'package'", "'times'", "'repeat'", "'else'", 
			"'returns'", "'return'", "'and'", "'or'", "'undefined'", "'shared'", 
			"'action'", "':'", "'integer'", "'number'", "'text'", "'boolean'", "'use'", 
			null, null, "'.'", "','", "'='", "'>'", "'>='", "'<'", "'<='", "'+'", 
			"'-'", "'*'", "'/'", "'mod'", "'['", "']'", "'('", "')'", "'\"'", "'if'", 
			"'end'", "'class'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "OUTPUT", "ON", "CREATE", "CONSTANT", "ELSE_IF", "ME", "UNTIL", 
			"PUBLIC", "PRIVATE", "ALERT", "DETECT", "ALWAYS", "CHECK", "PARENT", 
			"BLUEPRINT", "NATIVE", "INHERITS", "CAST", "INPUT", "SAY", "NOW", "WHILE", 
			"PACKAGE_NAME", "TIMES", "REPEAT", "ELSE", "RETURNS", "RETURN", "AND", 
			"OR", "NULL", "STATIC", "ACTION", "COLON", "INTEGER_KEYWORD", "NUMBER_KEYWORD", 
			"TEXT", "BOOLEAN_KEYWORD", "USE", "NOT", "NOTEQUALS", "PERIOD", "COMMA", 
			"EQUALITY", "GREATER", "GREATER_EQUAL", "LESS", "LESS_EQUAL", "PLUS", 
			"MINUS", "MULTIPLY", "DIVIDE", "MODULO", "LEFT_SQR_BRACE", "RIGHT_SQR_BRACE", 
			"LEFT_PAREN", "RIGHT_PAREN", "DOUBLE_QUOTE", "IF", "END", "CLASS", "BOOLEAN", 
			"INT", "DECIMAL", "ID", "STRING", "NEWLINE", "WS", "COMMENTS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "QuorumParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QuorumParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public Class_declarationContext class_declaration() {
			return getRuleContext(Class_declarationContext.class,0);
		}
		public TerminalNode EOF() { return getToken(QuorumParser.EOF, 0); }
		public Package_ruleContext package_rule() {
			return getRuleContext(Package_ruleContext.class,0);
		}
		public List<ReferenceContext> reference() {
			return getRuleContexts(ReferenceContext.class);
		}
		public ReferenceContext reference(int i) {
			return getRuleContext(ReferenceContext.class,i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(74);
				package_rule();
				setState(76); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(75);
					reference();
					}
					}
					setState(78); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==USE );
				}
				break;
			case 2:
				{
				setState(81); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(80);
					reference();
					}
					}
					setState(83); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==USE );
				setState(85);
				package_rule();
				}
				break;
			case 3:
				{
				setState(87);
				package_rule();
				}
				break;
			case 4:
				{
				setState(89); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(88);
					reference();
					}
					}
					setState(91); 
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
			setState(96);
			class_declaration();
			setState(97);
			match(EOF);
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
		public Qualified_nameContext name;
		public TerminalNode PACKAGE_NAME() { return getToken(QuorumParser.PACKAGE_NAME, 0); }
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public Package_ruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_package_rule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterPackage_rule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitPackage_rule(this);
		}
	}

	public final Package_ruleContext package_rule() throws RecognitionException {
		Package_ruleContext _localctx = new Package_ruleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_package_rule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(PACKAGE_NAME);
			setState(100);
			((Package_ruleContext)_localctx).name = qualified_name();
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
		public Qualified_nameContext name;
		public TerminalNode USE() { return getToken(QuorumParser.USE, 0); }
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public ReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitReference(this);
		}
	}

	public final ReferenceContext reference() throws RecognitionException {
		ReferenceContext _localctx = new ReferenceContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_reference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(USE);
			setState(103);
			((ReferenceContext)_localctx).name = qualified_name();
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
		public Class_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_declaration; }
	 
		public Class_declarationContext() { }
		public void copyFrom(Class_declarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FullClassDeclarationContext extends Class_declarationContext {
		public TerminalNode CLASS() { return getToken(QuorumParser.CLASS, 0); }
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode END() { return getToken(QuorumParser.END, 0); }
		public TerminalNode STATIC() { return getToken(QuorumParser.STATIC, 0); }
		public Generic_declarationContext generic_declaration() {
			return getRuleContext(Generic_declarationContext.class,0);
		}
		public Inherit_stmntsContext inherit_stmnts() {
			return getRuleContext(Inherit_stmntsContext.class,0);
		}
		public List<Class_stmntsContext> class_stmnts() {
			return getRuleContexts(Class_stmntsContext.class);
		}
		public Class_stmntsContext class_stmnts(int i) {
			return getRuleContext(Class_stmntsContext.class,i);
		}
		public FullClassDeclarationContext(Class_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterFullClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitFullClassDeclaration(this);
		}
	}
	public static class NoClassDeclarationContext extends Class_declarationContext {
		public No_class_stmntsContext no_class_stmnts() {
			return getRuleContext(No_class_stmntsContext.class,0);
		}
		public NoClassDeclarationContext(Class_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterNoClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitNoClassDeclaration(this);
		}
	}

	public final Class_declarationContext class_declaration() throws RecognitionException {
		Class_declarationContext _localctx = new Class_declarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_class_declaration);
		int _la;
		try {
			setState(124);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STATIC:
			case CLASS:
				_localctx = new FullClassDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STATIC) {
					{
					setState(105);
					match(STATIC);
					}
				}

				setState(108);
				match(CLASS);
				setState(109);
				match(ID);
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LESS) {
					{
					setState(110);
					generic_declaration();
					}
				}

				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INHERITS) {
					{
					setState(113);
					inherit_stmnts();
					}
				}

				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 2)) & ~0x3f) == 0 && ((1L << (_la - 2)) & ((1L << (ON - 2)) | (1L << (CONSTANT - 2)) | (1L << (ME - 2)) | (1L << (PUBLIC - 2)) | (1L << (PRIVATE - 2)) | (1L << (PARENT - 2)) | (1L << (BLUEPRINT - 2)) | (1L << (NATIVE - 2)) | (1L << (ACTION - 2)) | (1L << (INTEGER_KEYWORD - 2)) | (1L << (NUMBER_KEYWORD - 2)) | (1L << (TEXT - 2)) | (1L << (BOOLEAN_KEYWORD - 2)) | (1L << (ID - 2)))) != 0)) {
					{
					{
					setState(116);
					class_stmnts();
					}
					}
					setState(121);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(122);
				match(END);
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
				_localctx = new NoClassDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				no_class_stmnts();
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
		public No_class_stmntsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_no_class_stmnts; }
	 
		public No_class_stmntsContext() { }
		public void copyFrom(No_class_stmntsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ActionsNoClassContext extends No_class_stmntsContext {
		public List<Method_declarationContext> method_declaration() {
			return getRuleContexts(Method_declarationContext.class);
		}
		public Method_declarationContext method_declaration(int i) {
			return getRuleContext(Method_declarationContext.class,i);
		}
		public List<Access_modifierContext> access_modifier() {
			return getRuleContexts(Access_modifierContext.class);
		}
		public Access_modifierContext access_modifier(int i) {
			return getRuleContext(Access_modifierContext.class,i);
		}
		public ActionsNoClassContext(No_class_stmntsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterActionsNoClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitActionsNoClass(this);
		}
	}
	public static class NoActionsNoClassContext extends No_class_stmntsContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public NoActionsNoClassContext(No_class_stmntsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterNoActionsNoClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitNoActionsNoClass(this);
		}
	}

	public final No_class_stmntsContext no_class_stmnts() throws RecognitionException {
		No_class_stmntsContext _localctx = new No_class_stmntsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_no_class_stmnts);
		int _la;
		try {
			setState(139);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new NoActionsNoClassContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(127); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(126);
					statement();
					}
					}
					setState(129); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OUTPUT) | (1L << CONSTANT) | (1L << ME) | (1L << PUBLIC) | (1L << PRIVATE) | (1L << ALERT) | (1L << CHECK) | (1L << PARENT) | (1L << SAY) | (1L << REPEAT) | (1L << RETURN) | (1L << INTEGER_KEYWORD) | (1L << NUMBER_KEYWORD) | (1L << TEXT) | (1L << BOOLEAN_KEYWORD) | (1L << IF))) != 0) || _la==ID );
				}
				break;
			case 2:
				_localctx = new ActionsNoClassContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(135); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(132);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						setState(131);
						access_modifier();
						}
						break;
					}
					setState(134);
					method_declaration();
					}
					}
					setState(137); 
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
		public TerminalNode INHERITS() { return getToken(QuorumParser.INHERITS, 0); }
		public List<Inherit_stmtContext> inherit_stmt() {
			return getRuleContexts(Inherit_stmtContext.class);
		}
		public Inherit_stmtContext inherit_stmt(int i) {
			return getRuleContext(Inherit_stmtContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(QuorumParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(QuorumParser.COMMA, i);
		}
		public Inherit_stmntsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inherit_stmnts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterInherit_stmnts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitInherit_stmnts(this);
		}
	}

	public final Inherit_stmntsContext inherit_stmnts() throws RecognitionException {
		Inherit_stmntsContext _localctx = new Inherit_stmntsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_inherit_stmnts);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(INHERITS);
			setState(142);
			inherit_stmt();
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(143);
				match(COMMA);
				setState(144);
				inherit_stmt();
				}
				}
				setState(149);
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

	public static class Inherit_stmtContext extends ParserRuleContext {
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public Generic_statementContext generic_statement() {
			return getRuleContext(Generic_statementContext.class,0);
		}
		public Inherit_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inherit_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterInherit_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitInherit_stmt(this);
		}
	}

	public final Inherit_stmtContext inherit_stmt() throws RecognitionException {
		Inherit_stmtContext _localctx = new Inherit_stmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_inherit_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			qualified_name();
			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LESS) {
				{
				setState(151);
				generic_statement();
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

	public static class Access_modifierContext extends ParserRuleContext {
		public TerminalNode PUBLIC() { return getToken(QuorumParser.PUBLIC, 0); }
		public TerminalNode PRIVATE() { return getToken(QuorumParser.PRIVATE, 0); }
		public Access_modifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_access_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterAccess_modifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitAccess_modifier(this);
		}
	}

	public final Access_modifierContext access_modifier() throws RecognitionException {
		Access_modifierContext _localctx = new Access_modifierContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_access_modifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			_la = _input.LA(1);
			if ( !(_la==PUBLIC || _la==PRIVATE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class Class_stmntsContext extends ParserRuleContext {
		public Assignment_statementContext assignment_statement() {
			return getRuleContext(Assignment_statementContext.class,0);
		}
		public Method_declarationContext method_declaration() {
			return getRuleContext(Method_declarationContext.class,0);
		}
		public Class_stmntsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_stmnts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterClass_stmnts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitClass_stmnts(this);
		}
	}

	public final Class_stmntsContext class_stmnts() throws RecognitionException {
		Class_stmntsContext _localctx = new Class_stmntsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_class_stmnts);
		try {
			setState(158);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(156);
				assignment_statement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(157);
				method_declaration();
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
		public Method_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_declaration; }
	 
		public Method_declarationContext() { }
		public void copyFrom(Method_declarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ActionContext extends Method_declarationContext {
		public Access_modifierContext modifier;
		public Method_sharedContext method_shared() {
			return getRuleContext(Method_sharedContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode END() { return getToken(QuorumParser.END, 0); }
		public Access_modifierContext access_modifier() {
			return getRuleContext(Access_modifierContext.class,0);
		}
		public ActionContext(Method_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitAction(this);
		}
	}
	public static class ConstructorContext extends Method_declarationContext {
		public TerminalNode ON() { return getToken(QuorumParser.ON, 0); }
		public TerminalNode CREATE() { return getToken(QuorumParser.CREATE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode END() { return getToken(QuorumParser.END, 0); }
		public ConstructorContext(Method_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterConstructor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitConstructor(this);
		}
	}
	public static class NativeActionContext extends Method_declarationContext {
		public Access_modifierContext modifier;
		public TerminalNode NATIVE() { return getToken(QuorumParser.NATIVE, 0); }
		public Method_sharedContext method_shared() {
			return getRuleContext(Method_sharedContext.class,0);
		}
		public Access_modifierContext access_modifier() {
			return getRuleContext(Access_modifierContext.class,0);
		}
		public NativeActionContext(Method_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterNativeAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitNativeAction(this);
		}
	}
	public static class BlueprintActionContext extends Method_declarationContext {
		public Access_modifierContext modifier;
		public TerminalNode BLUEPRINT() { return getToken(QuorumParser.BLUEPRINT, 0); }
		public Method_sharedContext method_shared() {
			return getRuleContext(Method_sharedContext.class,0);
		}
		public Access_modifierContext access_modifier() {
			return getRuleContext(Access_modifierContext.class,0);
		}
		public BlueprintActionContext(Method_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterBlueprintAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitBlueprintAction(this);
		}
	}

	public final Method_declarationContext method_declaration() throws RecognitionException {
		Method_declarationContext _localctx = new Method_declarationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_method_declaration);
		int _la;
		try {
			setState(182);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				_localctx = new ActionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PUBLIC || _la==PRIVATE) {
					{
					setState(160);
					((ActionContext)_localctx).modifier = access_modifier();
					}
				}

				setState(163);
				method_shared();
				setState(164);
				block();
				setState(165);
				match(END);
				}
				break;
			case 2:
				_localctx = new BlueprintActionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(168);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PUBLIC || _la==PRIVATE) {
					{
					setState(167);
					((BlueprintActionContext)_localctx).modifier = access_modifier();
					}
				}

				setState(170);
				match(BLUEPRINT);
				setState(171);
				method_shared();
				}
				break;
			case 3:
				_localctx = new NativeActionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(173);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PUBLIC || _la==PRIVATE) {
					{
					setState(172);
					((NativeActionContext)_localctx).modifier = access_modifier();
					}
				}

				setState(175);
				match(NATIVE);
				setState(176);
				method_shared();
				}
				break;
			case 4:
				_localctx = new ConstructorContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(177);
				match(ON);
				setState(178);
				match(CREATE);
				setState(179);
				block();
				setState(180);
				match(END);
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

	public static class Method_sharedContext extends ParserRuleContext {
		public quorum.Libraries.Language.Compile.Context.ActionContext actionContext;
		public Assignment_declarationContext return_type;
		public TerminalNode ACTION() { return getToken(QuorumParser.ACTION, 0); }
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public TerminalNode RETURNS() { return getToken(QuorumParser.RETURNS, 0); }
		public Assignment_declarationContext assignment_declaration() {
			return getRuleContext(Assignment_declarationContext.class,0);
		}
		public List<Formal_parameterContext> formal_parameter() {
			return getRuleContexts(Formal_parameterContext.class);
		}
		public Formal_parameterContext formal_parameter(int i) {
			return getRuleContext(Formal_parameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(QuorumParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(QuorumParser.COMMA, i);
		}
		public Method_sharedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_shared; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterMethod_shared(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitMethod_shared(this);
		}
	}

	public final Method_sharedContext method_shared() throws RecognitionException {
		Method_sharedContext _localctx = new Method_sharedContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_method_shared);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(ACTION);
			setState(185);
			match(ID);
			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LEFT_PAREN) {
				{
				setState(186);
				match(LEFT_PAREN);
				setState(195);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 35)) & ~0x3f) == 0 && ((1L << (_la - 35)) & ((1L << (INTEGER_KEYWORD - 35)) | (1L << (NUMBER_KEYWORD - 35)) | (1L << (TEXT - 35)) | (1L << (BOOLEAN_KEYWORD - 35)) | (1L << (ID - 35)))) != 0)) {
					{
					setState(187);
					formal_parameter();
					setState(192);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(188);
						match(COMMA);
						setState(189);
						formal_parameter();
						}
						}
						setState(194);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(197);
				match(RIGHT_PAREN);
				}
			}

			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURNS) {
				{
				setState(200);
				match(RETURNS);
				setState(201);
				((Method_sharedContext)_localctx).return_type = assignment_declaration();
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
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterFormal_parameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitFormal_parameter(this);
		}
	}

	public final Formal_parameterContext formal_parameter() throws RecognitionException {
		Formal_parameterContext _localctx = new Formal_parameterContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_formal_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			assignment_declaration();
			setState(205);
			match(ID);
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
		public quorum.Libraries.Language.Compile.QualifiedName qualifiedName;
		public Token ID;
		public List<Token> ids = new ArrayList<Token>();
		public List<TerminalNode> ID() { return getTokens(QuorumParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(QuorumParser.ID, i);
		}
		public List<TerminalNode> PERIOD() { return getTokens(QuorumParser.PERIOD); }
		public TerminalNode PERIOD(int i) {
			return getToken(QuorumParser.PERIOD, i);
		}
		public Qualified_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualified_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterQualified_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitQualified_name(this);
		}
	}

	public final Qualified_nameContext qualified_name() throws RecognitionException {
		Qualified_nameContext _localctx = new Qualified_nameContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_qualified_name);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			((Qualified_nameContext)_localctx).ID = match(ID);
			((Qualified_nameContext)_localctx).ids.add(((Qualified_nameContext)_localctx).ID);
			setState(212);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(208);
					match(PERIOD);
					setState(209);
					((Qualified_nameContext)_localctx).ID = match(ID);
					((Qualified_nameContext)_localctx).ids.add(((Qualified_nameContext)_localctx).ID);
					}
					} 
				}
				setState(214);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
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
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OUTPUT) | (1L << CONSTANT) | (1L << ME) | (1L << PUBLIC) | (1L << PRIVATE) | (1L << ALERT) | (1L << CHECK) | (1L << PARENT) | (1L << SAY) | (1L << REPEAT) | (1L << RETURN) | (1L << INTEGER_KEYWORD) | (1L << NUMBER_KEYWORD) | (1L << TEXT) | (1L << BOOLEAN_KEYWORD) | (1L << IF))) != 0) || _la==ID) {
				{
				{
				setState(215);
				statement();
				}
				}
				setState(220);
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
		public Solo_method_callContext solo_method_call() {
			return getRuleContext(Solo_method_callContext.class,0);
		}
		public If_statementContext if_statement() {
			return getRuleContext(If_statementContext.class,0);
		}
		public Assignment_statementContext assignment_statement() {
			return getRuleContext(Assignment_statementContext.class,0);
		}
		public Loop_statementContext loop_statement() {
			return getRuleContext(Loop_statementContext.class,0);
		}
		public Return_statementContext return_statement() {
			return getRuleContext(Return_statementContext.class,0);
		}
		public Print_statementContext print_statement() {
			return getRuleContext(Print_statementContext.class,0);
		}
		public Speak_statementContext speak_statement() {
			return getRuleContext(Speak_statementContext.class,0);
		}
		public Check_statementContext check_statement() {
			return getRuleContext(Check_statementContext.class,0);
		}
		public Alert_statementContext alert_statement() {
			return getRuleContext(Alert_statementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_statement);
		try {
			setState(230);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(221);
				solo_method_call();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(222);
				if_statement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(223);
				assignment_statement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(224);
				loop_statement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(225);
				return_statement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(226);
				print_statement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(227);
				speak_statement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(228);
				check_statement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(229);
				alert_statement();
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
		public Solo_method_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_solo_method_call; }
	 
		public Solo_method_callContext() { }
		public void copyFrom(Solo_method_callContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VariableSoloFunctionCallContext extends Solo_method_callContext {
		public Token object;
		public Solo_method_required_method_partContext solo_method_required_method_part() {
			return getRuleContext(Solo_method_required_method_partContext.class,0);
		}
		public TerminalNode ME() { return getToken(QuorumParser.ME, 0); }
		public List<TerminalNode> COLON() { return getTokens(QuorumParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(QuorumParser.COLON, i);
		}
		public List<Action_callContext> action_call() {
			return getRuleContexts(Action_callContext.class);
		}
		public Action_callContext action_call(int i) {
			return getRuleContext(Action_callContext.class,i);
		}
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public VariableSoloFunctionCallContext(Solo_method_callContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterVariableSoloFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitVariableSoloFunctionCall(this);
		}
	}
	public static class ParentVariableSoloFunctionCallContext extends Solo_method_callContext {
		public Token fieldName;
		public Qualified_nameContext parent;
		public TerminalNode PARENT() { return getToken(QuorumParser.PARENT, 0); }
		public List<TerminalNode> COLON() { return getTokens(QuorumParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(QuorumParser.COLON, i);
		}
		public Initial_parent_action_callContext initial_parent_action_call() {
			return getRuleContext(Initial_parent_action_callContext.class,0);
		}
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public List<Action_callContext> action_call() {
			return getRuleContexts(Action_callContext.class);
		}
		public Action_callContext action_call(int i) {
			return getRuleContext(Action_callContext.class,i);
		}
		public TerminalNode ME() { return getToken(QuorumParser.ME, 0); }
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public ParentVariableSoloFunctionCallContext(Solo_method_callContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterParentVariableSoloFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitParentVariableSoloFunctionCall(this);
		}
	}

	public final Solo_method_callContext solo_method_call() throws RecognitionException {
		Solo_method_callContext _localctx = new Solo_method_callContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_solo_method_call);
		int _la;
		try {
			int _alt;
			setState(269);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				_localctx = new VariableSoloFunctionCallContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(234);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ME) {
					{
					setState(232);
					match(ME);
					setState(233);
					match(COLON);
					}
				}

				setState(238);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(236);
					((VariableSoloFunctionCallContext)_localctx).object = match(ID);
					setState(237);
					match(COLON);
					}
					break;
				}
				setState(245);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(240);
						action_call();
						setState(241);
						match(COLON);
						}
						} 
					}
					setState(247);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				}
				setState(248);
				solo_method_required_method_part();
				}
				break;
			case 2:
				_localctx = new ParentVariableSoloFunctionCallContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(255);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ME || _la==ID) {
					{
					setState(251);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ME) {
						{
						setState(249);
						match(ME);
						setState(250);
						match(COLON);
						}
					}

					{
					setState(253);
					((ParentVariableSoloFunctionCallContext)_localctx).fieldName = match(ID);
					setState(254);
					match(COLON);
					}
					}
				}

				setState(257);
				match(PARENT);
				setState(258);
				match(COLON);
				setState(259);
				((ParentVariableSoloFunctionCallContext)_localctx).parent = qualified_name();
				setState(260);
				match(COLON);
				setState(261);
				initial_parent_action_call();
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COLON) {
					{
					{
					setState(262);
					match(COLON);
					{
					setState(263);
					action_call();
					}
					}
					}
					setState(268);
					_errHandler.sync(this);
					_la = _input.LA(1);
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

	public static class Solo_method_required_method_partContext extends ParserRuleContext {
		public Token var;
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public Function_expression_listContext function_expression_list() {
			return getRuleContext(Function_expression_listContext.class,0);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public Solo_method_required_method_partContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_solo_method_required_method_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterSolo_method_required_method_part(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitSolo_method_required_method_part(this);
		}
	}

	public final Solo_method_required_method_partContext solo_method_required_method_part() throws RecognitionException {
		Solo_method_required_method_partContext _localctx = new Solo_method_required_method_partContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_solo_method_required_method_part);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			((Solo_method_required_method_partContext)_localctx).var = match(ID);
			setState(272);
			match(LEFT_PAREN);
			setState(273);
			function_expression_list();
			setState(274);
			match(RIGHT_PAREN);
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
		public TerminalNode ALERT() { return getToken(QuorumParser.ALERT, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public Alert_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alert_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterAlert_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitAlert_statement(this);
		}
	}

	public final Alert_statementContext alert_statement() throws RecognitionException {
		Alert_statementContext _localctx = new Alert_statementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_alert_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			match(ALERT);
			setState(277);
			match(LEFT_PAREN);
			setState(278);
			expression(0);
			setState(279);
			match(RIGHT_PAREN);
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
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode END() { return getToken(QuorumParser.END, 0); }
		public Always_statementContext always_statement() {
			return getRuleContext(Always_statementContext.class,0);
		}
		public List<Detect_statementContext> detect_statement() {
			return getRuleContexts(Detect_statementContext.class);
		}
		public Detect_statementContext detect_statement(int i) {
			return getRuleContext(Detect_statementContext.class,i);
		}
		public Check_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_check_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterCheck_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitCheck_statement(this);
		}
	}

	public final Check_statementContext check_statement() throws RecognitionException {
		Check_statementContext _localctx = new Check_statementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_check_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			match(CHECK);
			setState(282);
			block();
			setState(292);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DETECT:
				{
				setState(284); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(283);
					detect_statement();
					}
					}
					setState(286); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DETECT );
				setState(289);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ALWAYS) {
					{
					setState(288);
					always_statement();
					}
				}

				}
				break;
			case ALWAYS:
				{
				setState(291);
				always_statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(294);
			match(END);
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

	public static class Detect_statementContext extends ParserRuleContext {
		public Token name;
		public TerminalNode DETECT() { return getToken(QuorumParser.DETECT, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode INHERITS() { return getToken(QuorumParser.INHERITS, 0); }
		public List<Qualified_nameContext> qualified_name() {
			return getRuleContexts(Qualified_nameContext.class);
		}
		public Qualified_nameContext qualified_name(int i) {
			return getRuleContext(Qualified_nameContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(QuorumParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(QuorumParser.OR, i);
		}
		public Detect_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_detect_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterDetect_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitDetect_statement(this);
		}
	}

	public final Detect_statementContext detect_statement() throws RecognitionException {
		Detect_statementContext _localctx = new Detect_statementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_detect_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			match(DETECT);
			setState(297);
			((Detect_statementContext)_localctx).name = match(ID);
			setState(307);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INHERITS) {
				{
				setState(298);
				match(INHERITS);
				setState(299);
				qualified_name();
				setState(304);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OR) {
					{
					{
					setState(300);
					match(OR);
					setState(301);
					qualified_name();
					}
					}
					setState(306);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(309);
			block();
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

	public static class Always_statementContext extends ParserRuleContext {
		public TerminalNode ALWAYS() { return getToken(QuorumParser.ALWAYS, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Always_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_always_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterAlways_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitAlways_statement(this);
		}
	}

	public final Always_statementContext always_statement() throws RecognitionException {
		Always_statementContext _localctx = new Always_statementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_always_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311);
			match(ALWAYS);
			setState(312);
			block();
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
		public TerminalNode OUTPUT() { return getToken(QuorumParser.OUTPUT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Print_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterPrint_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitPrint_statement(this);
		}
	}

	public final Print_statementContext print_statement() throws RecognitionException {
		Print_statementContext _localctx = new Print_statementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_print_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314);
			match(OUTPUT);
			setState(315);
			expression(0);
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Speak_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_speak_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterSpeak_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitSpeak_statement(this);
		}
	}

	public final Speak_statementContext speak_statement() throws RecognitionException {
		Speak_statementContext _localctx = new Speak_statementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_speak_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317);
			match(SAY);
			setState(318);
			expression(0);
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
		public TerminalNode RETURN() { return getToken(QuorumParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode NOW() { return getToken(QuorumParser.NOW, 0); }
		public Return_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterReturn_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitReturn_statement(this);
		}
	}

	public final Return_statementContext return_statement() throws RecognitionException {
		Return_statementContext _localctx = new Return_statementContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_return_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			match(RETURN);
			setState(323);
			_errHandler.sync(this);
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
				setState(321);
				expression(0);
				}
				break;
			case NOW:
				{
				setState(322);
				match(NOW);
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
		public TerminalNode GREATER() { return getToken(QuorumParser.GREATER, 0); }
		public List<TerminalNode> ID() { return getTokens(QuorumParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(QuorumParser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(QuorumParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(QuorumParser.COMMA, i);
		}
		public Generic_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generic_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterGeneric_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitGeneric_declaration(this);
		}
	}

	public final Generic_declarationContext generic_declaration() throws RecognitionException {
		Generic_declarationContext _localctx = new Generic_declarationContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_generic_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			match(LESS);
			setState(326);
			((Generic_declarationContext)_localctx).ID = match(ID);
			((Generic_declarationContext)_localctx).ids.add(((Generic_declarationContext)_localctx).ID);
			setState(331);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(327);
				match(COMMA);
				setState(328);
				((Generic_declarationContext)_localctx).ID = match(ID);
				((Generic_declarationContext)_localctx).ids.add(((Generic_declarationContext)_localctx).ID);
				}
				}
				setState(333);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(334);
			match(GREATER);
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
		public TerminalNode LESS() { return getToken(QuorumParser.LESS, 0); }
		public List<Assignment_declarationContext> assignment_declaration() {
			return getRuleContexts(Assignment_declarationContext.class);
		}
		public Assignment_declarationContext assignment_declaration(int i) {
			return getRuleContext(Assignment_declarationContext.class,i);
		}
		public TerminalNode GREATER() { return getToken(QuorumParser.GREATER, 0); }
		public List<TerminalNode> COMMA() { return getTokens(QuorumParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(QuorumParser.COMMA, i);
		}
		public Generic_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generic_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterGeneric_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitGeneric_statement(this);
		}
	}

	public final Generic_statementContext generic_statement() throws RecognitionException {
		Generic_statementContext _localctx = new Generic_statementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_generic_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
			match(LESS);
			setState(337);
			assignment_declaration();
			setState(342);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(338);
				match(COMMA);
				setState(339);
				assignment_declaration();
				}
				}
				setState(344);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(345);
			match(GREATER);
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
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterClass_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitClass_type(this);
		}
	}

	public final Class_typeContext class_type() throws RecognitionException {
		Class_typeContext _localctx = new Class_typeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_class_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(347);
			qualified_name();
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
		public quorum.Libraries.Language.Compile.Symbol.Type type;
		public Assignment_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment_declaration; }
	 
		public Assignment_declarationContext() { }
		public void copyFrom(Assignment_declarationContext ctx) {
			super.copyFrom(ctx);
			this.type = ctx.type;
		}
	}
	public static class IntegerAssignmentDeclarationContext extends Assignment_declarationContext {
		public TerminalNode INTEGER_KEYWORD() { return getToken(QuorumParser.INTEGER_KEYWORD, 0); }
		public IntegerAssignmentDeclarationContext(Assignment_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterIntegerAssignmentDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitIntegerAssignmentDeclaration(this);
		}
	}
	public static class NumberAssignmentDeclarationContext extends Assignment_declarationContext {
		public TerminalNode NUMBER_KEYWORD() { return getToken(QuorumParser.NUMBER_KEYWORD, 0); }
		public NumberAssignmentDeclarationContext(Assignment_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterNumberAssignmentDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitNumberAssignmentDeclaration(this);
		}
	}
	public static class TextAssignmentDeclarationContext extends Assignment_declarationContext {
		public TerminalNode TEXT() { return getToken(QuorumParser.TEXT, 0); }
		public TextAssignmentDeclarationContext(Assignment_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterTextAssignmentDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitTextAssignmentDeclaration(this);
		}
	}
	public static class BooleanAssignmentDeclarationContext extends Assignment_declarationContext {
		public TerminalNode BOOLEAN_KEYWORD() { return getToken(QuorumParser.BOOLEAN_KEYWORD, 0); }
		public BooleanAssignmentDeclarationContext(Assignment_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterBooleanAssignmentDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitBooleanAssignmentDeclaration(this);
		}
	}
	public static class GenericAssignmentDeclarationContext extends Assignment_declarationContext {
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public Generic_statementContext generic_statement() {
			return getRuleContext(Generic_statementContext.class,0);
		}
		public GenericAssignmentDeclarationContext(Assignment_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterGenericAssignmentDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitGenericAssignmentDeclaration(this);
		}
	}

	public final Assignment_declarationContext assignment_declaration() throws RecognitionException {
		Assignment_declarationContext _localctx = new Assignment_declarationContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_assignment_declaration);
		int _la;
		try {
			setState(357);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				_localctx = new GenericAssignmentDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(349);
				qualified_name();
				setState(351);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LESS) {
					{
					setState(350);
					generic_statement();
					}
				}

				}
				break;
			case INTEGER_KEYWORD:
				_localctx = new IntegerAssignmentDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(353);
				match(INTEGER_KEYWORD);
				}
				break;
			case NUMBER_KEYWORD:
				_localctx = new NumberAssignmentDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(354);
				match(NUMBER_KEYWORD);
				}
				break;
			case TEXT:
				_localctx = new TextAssignmentDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(355);
				match(TEXT);
				}
				break;
			case BOOLEAN_KEYWORD:
				_localctx = new BooleanAssignmentDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(356);
				match(BOOLEAN_KEYWORD);
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
		public Assignment_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment_statement; }
	 
		public Assignment_statementContext() { }
		public void copyFrom(Assignment_statementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NoTypeAssignmentContext extends Assignment_statementContext {
		public Token name;
		public ExpressionContext rhs;
		public TerminalNode EQUALITY() { return getToken(QuorumParser.EQUALITY, 0); }
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ME() { return getToken(QuorumParser.ME, 0); }
		public TerminalNode COLON() { return getToken(QuorumParser.COLON, 0); }
		public NoTypeAssignmentContext(Assignment_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterNoTypeAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitNoTypeAssignment(this);
		}
	}
	public static class ParentAssignmentContext extends Assignment_statementContext {
		public Qualified_nameContext parent;
		public Token name;
		public ExpressionContext rhs;
		public TerminalNode PARENT() { return getToken(QuorumParser.PARENT, 0); }
		public List<TerminalNode> COLON() { return getTokens(QuorumParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(QuorumParser.COLON, i);
		}
		public TerminalNode EQUALITY() { return getToken(QuorumParser.EQUALITY, 0); }
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParentAssignmentContext(Assignment_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterParentAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitParentAssignment(this);
		}
	}
	public static class ObjectAssignmentContext extends Assignment_statementContext {
		public Token object;
		public Qualified_nameContext parent;
		public Token name;
		public ExpressionContext rhs;
		public List<TerminalNode> COLON() { return getTokens(QuorumParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(QuorumParser.COLON, i);
		}
		public TerminalNode EQUALITY() { return getToken(QuorumParser.EQUALITY, 0); }
		public List<TerminalNode> ID() { return getTokens(QuorumParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(QuorumParser.ID, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PARENT() { return getToken(QuorumParser.PARENT, 0); }
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public ObjectAssignmentContext(Assignment_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterObjectAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitObjectAssignment(this);
		}
	}
	public static class NormalAssignmentContext extends Assignment_statementContext {
		public Access_modifierContext modifier;
		public Assignment_declarationContext type;
		public Token name;
		public ExpressionContext rhs;
		public Assignment_declarationContext assignment_declaration() {
			return getRuleContext(Assignment_declarationContext.class,0);
		}
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode CONSTANT() { return getToken(QuorumParser.CONSTANT, 0); }
		public TerminalNode EQUALITY() { return getToken(QuorumParser.EQUALITY, 0); }
		public Access_modifierContext access_modifier() {
			return getRuleContext(Access_modifierContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NormalAssignmentContext(Assignment_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterNormalAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitNormalAssignment(this);
		}
	}

	public final Assignment_statementContext assignment_statement() throws RecognitionException {
		Assignment_statementContext _localctx = new Assignment_statementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_assignment_statement);
		int _la;
		try {
			setState(397);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				_localctx = new NoTypeAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(361);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ME) {
					{
					setState(359);
					match(ME);
					setState(360);
					match(COLON);
					}
				}

				setState(363);
				((NoTypeAssignmentContext)_localctx).name = match(ID);
				setState(364);
				match(EQUALITY);
				setState(365);
				((NoTypeAssignmentContext)_localctx).rhs = expression(0);
				}
				break;
			case 2:
				_localctx = new ParentAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(366);
				match(PARENT);
				setState(367);
				match(COLON);
				setState(368);
				((ParentAssignmentContext)_localctx).parent = qualified_name();
				setState(369);
				match(COLON);
				setState(370);
				((ParentAssignmentContext)_localctx).name = match(ID);
				setState(371);
				match(EQUALITY);
				setState(372);
				((ParentAssignmentContext)_localctx).rhs = expression(0);
				}
				break;
			case 3:
				_localctx = new ObjectAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(374);
				((ObjectAssignmentContext)_localctx).object = match(ID);
				setState(379);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(375);
					match(COLON);
					setState(376);
					match(PARENT);
					setState(377);
					match(COLON);
					setState(378);
					((ObjectAssignmentContext)_localctx).parent = qualified_name();
					}
					break;
				}
				setState(381);
				match(COLON);
				setState(382);
				((ObjectAssignmentContext)_localctx).name = match(ID);
				setState(383);
				match(EQUALITY);
				setState(384);
				((ObjectAssignmentContext)_localctx).rhs = expression(0);
				}
				break;
			case 4:
				_localctx = new NormalAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(386);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PUBLIC || _la==PRIVATE) {
					{
					setState(385);
					((NormalAssignmentContext)_localctx).modifier = access_modifier();
					}
				}

				setState(389);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONSTANT) {
					{
					setState(388);
					match(CONSTANT);
					}
				}

				setState(391);
				((NormalAssignmentContext)_localctx).type = assignment_declaration();
				setState(392);
				((NormalAssignmentContext)_localctx).name = match(ID);
				setState(395);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EQUALITY) {
					{
					setState(393);
					match(EQUALITY);
					setState(394);
					((NormalAssignmentContext)_localctx).rhs = expression(0);
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

	public static class If_statementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(QuorumParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode END() { return getToken(QuorumParser.END, 0); }
		public List<Elseif_statementContext> elseif_statement() {
			return getRuleContexts(Elseif_statementContext.class);
		}
		public Elseif_statementContext elseif_statement(int i) {
			return getRuleContext(Elseif_statementContext.class,i);
		}
		public Else_statementContext else_statement() {
			return getRuleContext(Else_statementContext.class,0);
		}
		public If_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterIf_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitIf_statement(this);
		}
	}

	public final If_statementContext if_statement() throws RecognitionException {
		If_statementContext _localctx = new If_statementContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_if_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399);
			match(IF);
			setState(400);
			expression(0);
			setState(401);
			block();
			setState(405);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELSE_IF) {
				{
				{
				setState(402);
				elseif_statement();
				}
				}
				setState(407);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(409);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(408);
				else_statement();
				}
			}

			setState(411);
			match(END);
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

	public static class Elseif_statementContext extends ParserRuleContext {
		public TerminalNode ELSE_IF() { return getToken(QuorumParser.ELSE_IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Elseif_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseif_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterElseif_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitElseif_statement(this);
		}
	}

	public final Elseif_statementContext elseif_statement() throws RecognitionException {
		Elseif_statementContext _localctx = new Elseif_statementContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_elseif_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413);
			match(ELSE_IF);
			setState(414);
			expression(0);
			setState(415);
			block();
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

	public static class Else_statementContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(QuorumParser.ELSE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Else_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_else_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterElse_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitElse_statement(this);
		}
	}

	public final Else_statementContext else_statement() throws RecognitionException {
		Else_statementContext _localctx = new Else_statementContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_else_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(417);
			match(ELSE);
			setState(418);
			block();
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
		public TerminalNode REPEAT() { return getToken(QuorumParser.REPEAT, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode END() { return getToken(QuorumParser.END, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode TIMES() { return getToken(QuorumParser.TIMES, 0); }
		public TerminalNode WHILE() { return getToken(QuorumParser.WHILE, 0); }
		public TerminalNode UNTIL() { return getToken(QuorumParser.UNTIL, 0); }
		public Loop_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterLoop_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitLoop_statement(this);
		}
	}

	public final Loop_statementContext loop_statement() throws RecognitionException {
		Loop_statementContext _localctx = new Loop_statementContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_loop_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(420);
			match(REPEAT);
			setState(426);
			_errHandler.sync(this);
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
				setState(421);
				expression(0);
				setState(422);
				match(TIMES);
				}
				}
				break;
			case UNTIL:
			case WHILE:
				{
				{
				setState(424);
				_la = _input.LA(1);
				if ( !(_la==UNTIL || _la==WHILE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(425);
				expression(0);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(428);
			block();
			setState(429);
			match(END);
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

	public static class Initial_parent_action_callContext extends ParserRuleContext {
		public Token var;
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public Function_expression_listContext function_expression_list() {
			return getRuleContext(Function_expression_listContext.class,0);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public Initial_parent_action_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initial_parent_action_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterInitial_parent_action_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitInitial_parent_action_call(this);
		}
	}

	public final Initial_parent_action_callContext initial_parent_action_call() throws RecognitionException {
		Initial_parent_action_callContext _localctx = new Initial_parent_action_callContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_initial_parent_action_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(431);
			((Initial_parent_action_callContext)_localctx).var = match(ID);
			setState(436);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				setState(432);
				match(LEFT_PAREN);
				setState(433);
				function_expression_list();
				setState(434);
				match(RIGHT_PAREN);
				}
				break;
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

	public static class Action_callContext extends ParserRuleContext {
		public Token var;
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public Function_expression_listContext function_expression_list() {
			return getRuleContext(Function_expression_listContext.class,0);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public Action_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterAction_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitAction_call(this);
		}
	}

	public final Action_callContext action_call() throws RecognitionException {
		Action_callContext _localctx = new Action_callContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_action_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(438);
			((Action_callContext)_localctx).var = match(ID);
			setState(443);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				{
				setState(439);
				match(LEFT_PAREN);
				setState(440);
				function_expression_list();
				setState(441);
				match(RIGHT_PAREN);
				}
				break;
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

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CastContext extends ExpressionContext {
		public Assignment_declarationContext type;
		public TerminalNode CAST() { return getToken(QuorumParser.CAST, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public TerminalNode COMMA() { return getToken(QuorumParser.COMMA, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public Assignment_declarationContext assignment_declaration() {
			return getRuleContext(Assignment_declarationContext.class,0);
		}
		public CastContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterCast(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitCast(this);
		}
	}
	public static class NullContext extends ExpressionContext {
		public TerminalNode NULL() { return getToken(QuorumParser.NULL, 0); }
		public NullContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterNull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitNull(this);
		}
	}
	public static class MultiplicationContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MULTIPLY() { return getToken(QuorumParser.MULTIPLY, 0); }
		public TerminalNode DIVIDE() { return getToken(QuorumParser.DIVIDE, 0); }
		public TerminalNode MODULO() { return getToken(QuorumParser.MODULO, 0); }
		public MultiplicationContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterMultiplication(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitMultiplication(this);
		}
	}
	public static class AdditionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(QuorumParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(QuorumParser.MINUS, 0); }
		public AdditionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterAddition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitAddition(this);
		}
	}
	public static class OrContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OR() { return getToken(QuorumParser.OR, 0); }
		public OrContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitOr(this);
		}
	}
	public static class ParenthesisExpressionContext extends ExpressionContext {
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public ParenthesisExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterParenthesisExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitParenthesisExpression(this);
		}
	}
	public static class InheritsContext extends ExpressionContext {
		public Class_typeContext name;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode INHERITS() { return getToken(QuorumParser.INHERITS, 0); }
		public Class_typeContext class_type() {
			return getRuleContext(Class_typeContext.class,0);
		}
		public InheritsContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterInherits(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitInherits(this);
		}
	}
	public static class StringContext extends ExpressionContext {
		public TerminalNode STRING() { return getToken(QuorumParser.STRING, 0); }
		public StringContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitString(this);
		}
	}
	public static class IntegerContext extends ExpressionContext {
		public TerminalNode INT() { return getToken(QuorumParser.INT, 0); }
		public IntegerContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitInteger(this);
		}
	}
	public static class VariableFunctionCallContext extends ExpressionContext {
		public List<Action_callContext> action_call() {
			return getRuleContexts(Action_callContext.class);
		}
		public Action_callContext action_call(int i) {
			return getRuleContext(Action_callContext.class,i);
		}
		public TerminalNode ME() { return getToken(QuorumParser.ME, 0); }
		public List<TerminalNode> COLON() { return getTokens(QuorumParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(QuorumParser.COLON, i);
		}
		public VariableFunctionCallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterVariableFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitVariableFunctionCall(this);
		}
	}
	public static class InputContext extends ExpressionContext {
		public TerminalNode INPUT() { return getToken(QuorumParser.INPUT, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public InputContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterInput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitInput(this);
		}
	}
	public static class NotContext extends ExpressionContext {
		public TerminalNode NOT() { return getToken(QuorumParser.NOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NotContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitNot(this);
		}
	}
	public static class EqualsContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EQUALITY() { return getToken(QuorumParser.EQUALITY, 0); }
		public TerminalNode NOTEQUALS() { return getToken(QuorumParser.NOTEQUALS, 0); }
		public EqualsContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterEquals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitEquals(this);
		}
	}
	public static class DecimalContext extends ExpressionContext {
		public TerminalNode DECIMAL() { return getToken(QuorumParser.DECIMAL, 0); }
		public DecimalContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterDecimal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitDecimal(this);
		}
	}
	public static class AndContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(QuorumParser.AND, 0); }
		public AndContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitAnd(this);
		}
	}
	public static class MeContext extends ExpressionContext {
		public TerminalNode ME() { return getToken(QuorumParser.ME, 0); }
		public MeContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterMe(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitMe(this);
		}
	}
	public static class GreaterContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode GREATER() { return getToken(QuorumParser.GREATER, 0); }
		public TerminalNode GREATER_EQUAL() { return getToken(QuorumParser.GREATER_EQUAL, 0); }
		public TerminalNode LESS() { return getToken(QuorumParser.LESS, 0); }
		public TerminalNode LESS_EQUAL() { return getToken(QuorumParser.LESS_EQUAL, 0); }
		public GreaterContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterGreater(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitGreater(this);
		}
	}
	public static class BooleanContext extends ExpressionContext {
		public TerminalNode BOOLEAN() { return getToken(QuorumParser.BOOLEAN, 0); }
		public BooleanContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitBoolean(this);
		}
	}
	public static class InputNoParametersContext extends ExpressionContext {
		public TerminalNode INPUT() { return getToken(QuorumParser.INPUT, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public InputNoParametersContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterInputNoParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitInputNoParameters(this);
		}
	}
	public static class ParentVariableFunctionCallContext extends ExpressionContext {
		public Token fieldName;
		public Qualified_nameContext parent;
		public TerminalNode PARENT() { return getToken(QuorumParser.PARENT, 0); }
		public List<TerminalNode> COLON() { return getTokens(QuorumParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(QuorumParser.COLON, i);
		}
		public Initial_parent_action_callContext initial_parent_action_call() {
			return getRuleContext(Initial_parent_action_callContext.class,0);
		}
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public List<Action_callContext> action_call() {
			return getRuleContexts(Action_callContext.class);
		}
		public Action_callContext action_call(int i) {
			return getRuleContext(Action_callContext.class,i);
		}
		public TerminalNode ME() { return getToken(QuorumParser.ME, 0); }
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public ParentVariableFunctionCallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterParentVariableFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitParentVariableFunctionCall(this);
		}
	}
	public static class MinusContext extends ExpressionContext {
		public TerminalNode MINUS() { return getToken(QuorumParser.MINUS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public MinusContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitMinus(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 70;
		enterRecursionRule(_localctx, 70, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(507);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
			case 1:
				{
				_localctx = new ParenthesisExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(446);
				match(LEFT_PAREN);
				setState(447);
				expression(0);
				setState(448);
				match(RIGHT_PAREN);
				}
				break;
			case 2:
				{
				_localctx = new IntegerContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(450);
				match(INT);
				}
				break;
			case 3:
				{
				_localctx = new BooleanContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(451);
				match(BOOLEAN);
				}
				break;
			case 4:
				{
				_localctx = new DecimalContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(452);
				match(DECIMAL);
				}
				break;
			case 5:
				{
				_localctx = new StringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(453);
				match(STRING);
				}
				break;
			case 6:
				{
				_localctx = new NullContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(454);
				match(NULL);
				}
				break;
			case 7:
				{
				_localctx = new MeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(455);
				match(ME);
				}
				break;
			case 8:
				{
				_localctx = new InputContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(456);
				match(INPUT);
				setState(457);
				match(LEFT_PAREN);
				setState(458);
				expression(0);
				setState(459);
				match(RIGHT_PAREN);
				}
				break;
			case 9:
				{
				_localctx = new InputNoParametersContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(461);
				match(INPUT);
				setState(462);
				match(LEFT_PAREN);
				setState(463);
				match(RIGHT_PAREN);
				}
				break;
			case 10:
				{
				_localctx = new VariableFunctionCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(466);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ME) {
					{
					setState(464);
					match(ME);
					setState(465);
					match(COLON);
					}
				}

				setState(468);
				action_call();
				setState(473);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(469);
						match(COLON);
						{
						setState(470);
						action_call();
						}
						}
						} 
					}
					setState(475);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
				}
				}
				break;
			case 11:
				{
				_localctx = new ParentVariableFunctionCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(482);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ME || _la==ID) {
					{
					setState(478);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ME) {
						{
						setState(476);
						match(ME);
						setState(477);
						match(COLON);
						}
					}

					{
					setState(480);
					((ParentVariableFunctionCallContext)_localctx).fieldName = match(ID);
					setState(481);
					match(COLON);
					}
					}
				}

				setState(484);
				match(PARENT);
				setState(485);
				match(COLON);
				setState(486);
				((ParentVariableFunctionCallContext)_localctx).parent = qualified_name();
				setState(487);
				match(COLON);
				setState(488);
				initial_parent_action_call();
				setState(493);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(489);
						match(COLON);
						{
						setState(490);
						action_call();
						}
						}
						} 
					}
					setState(495);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
				}
				}
				break;
			case 12:
				{
				_localctx = new MinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(496);
				match(MINUS);
				setState(497);
				expression(10);
				}
				break;
			case 13:
				{
				_localctx = new NotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(498);
				match(NOT);
				setState(499);
				expression(9);
				}
				break;
			case 14:
				{
				_localctx = new CastContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(500);
				match(CAST);
				setState(501);
				match(LEFT_PAREN);
				setState(502);
				((CastContext)_localctx).type = assignment_declaration();
				setState(503);
				match(COMMA);
				setState(504);
				expression(0);
				setState(505);
				match(RIGHT_PAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(532);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(530);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplicationContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(509);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(510);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLY) | (1L << DIVIDE) | (1L << MODULO))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(511);
						expression(8);
						}
						break;
					case 2:
						{
						_localctx = new AdditionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(512);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(513);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(514);
						expression(7);
						}
						break;
					case 3:
						{
						_localctx = new GreaterContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(515);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(516);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER) | (1L << GREATER_EQUAL) | (1L << LESS) | (1L << LESS_EQUAL))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(517);
						expression(6);
						}
						break;
					case 4:
						{
						_localctx = new EqualsContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(518);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(519);
						_la = _input.LA(1);
						if ( !(_la==NOTEQUALS || _la==EQUALITY) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(520);
						expression(4);
						}
						break;
					case 5:
						{
						_localctx = new AndContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(521);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						{
						setState(522);
						match(AND);
						}
						setState(523);
						expression(3);
						}
						break;
					case 6:
						{
						_localctx = new OrContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(524);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						{
						setState(525);
						match(OR);
						}
						setState(526);
						expression(2);
						}
						break;
					case 7:
						{
						_localctx = new InheritsContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(527);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(528);
						match(INHERITS);
						setState(529);
						((InheritsContext)_localctx).name = class_type();
						}
						break;
					}
					} 
				}
				setState(534);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
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
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(QuorumParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(QuorumParser.COMMA, i);
		}
		public Function_expression_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_expression_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).enterFunction_expression_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumParserListener ) ((QuorumParserListener)listener).exitFunction_expression_list(this);
		}
	}

	public final Function_expression_listContext function_expression_list() throws RecognitionException {
		Function_expression_listContext _localctx = new Function_expression_listContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_function_expression_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(543);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & ((1L << (ME - 6)) | (1L << (PARENT - 6)) | (1L << (CAST - 6)) | (1L << (INPUT - 6)) | (1L << (NULL - 6)) | (1L << (NOT - 6)) | (1L << (MINUS - 6)) | (1L << (LEFT_PAREN - 6)) | (1L << (BOOLEAN - 6)) | (1L << (INT - 6)) | (1L << (DECIMAL - 6)) | (1L << (ID - 6)) | (1L << (STRING - 6)))) != 0)) {
				{
				setState(535);
				expression(0);
				setState(540);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(536);
					match(COMMA);
					setState(537);
					expression(0);
					}
					}
					setState(542);
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
		case 35:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 3);
		case 4:
			return precpred(_ctx, 2);
		case 5:
			return precpred(_ctx, 1);
		case 6:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3G\u0224\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\6\2O\n\2\r\2\16\2P\3\2\6\2"+
		"T\n\2\r\2\16\2U\3\2\3\2\3\2\3\2\6\2\\\n\2\r\2\16\2]\3\2\5\2a\n\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\5\5m\n\5\3\5\3\5\3\5\5\5r\n\5\3\5\5"+
		"\5u\n\5\3\5\7\5x\n\5\f\5\16\5{\13\5\3\5\3\5\5\5\177\n\5\3\6\6\6\u0082"+
		"\n\6\r\6\16\6\u0083\3\6\5\6\u0087\n\6\3\6\6\6\u008a\n\6\r\6\16\6\u008b"+
		"\5\6\u008e\n\6\3\7\3\7\3\7\3\7\7\7\u0094\n\7\f\7\16\7\u0097\13\7\3\b\3"+
		"\b\5\b\u009b\n\b\3\t\3\t\3\n\3\n\5\n\u00a1\n\n\3\13\5\13\u00a4\n\13\3"+
		"\13\3\13\3\13\3\13\3\13\5\13\u00ab\n\13\3\13\3\13\3\13\5\13\u00b0\n\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00b9\n\13\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\7\f\u00c1\n\f\f\f\16\f\u00c4\13\f\5\f\u00c6\n\f\3\f\5\f\u00c9\n"+
		"\f\3\f\3\f\5\f\u00cd\n\f\3\r\3\r\3\r\3\16\3\16\3\16\7\16\u00d5\n\16\f"+
		"\16\16\16\u00d8\13\16\3\17\7\17\u00db\n\17\f\17\16\17\u00de\13\17\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00e9\n\20\3\21\3\21\5\21"+
		"\u00ed\n\21\3\21\3\21\5\21\u00f1\n\21\3\21\3\21\3\21\7\21\u00f6\n\21\f"+
		"\21\16\21\u00f9\13\21\3\21\3\21\3\21\5\21\u00fe\n\21\3\21\3\21\5\21\u0102"+
		"\n\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u010b\n\21\f\21\16\21\u010e"+
		"\13\21\5\21\u0110\n\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3"+
		"\23\3\24\3\24\3\24\6\24\u011f\n\24\r\24\16\24\u0120\3\24\5\24\u0124\n"+
		"\24\3\24\5\24\u0127\n\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\7\25"+
		"\u0131\n\25\f\25\16\25\u0134\13\25\5\25\u0136\n\25\3\25\3\25\3\26\3\26"+
		"\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\5\31\u0146\n\31\3\32"+
		"\3\32\3\32\3\32\7\32\u014c\n\32\f\32\16\32\u014f\13\32\3\32\3\32\3\33"+
		"\3\33\3\33\3\33\7\33\u0157\n\33\f\33\16\33\u015a\13\33\3\33\3\33\3\34"+
		"\3\34\3\35\3\35\5\35\u0162\n\35\3\35\3\35\3\35\3\35\5\35\u0168\n\35\3"+
		"\36\3\36\5\36\u016c\n\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u017e\n\36\3\36\3\36\3\36\3\36"+
		"\3\36\5\36\u0185\n\36\3\36\5\36\u0188\n\36\3\36\3\36\3\36\3\36\5\36\u018e"+
		"\n\36\5\36\u0190\n\36\3\37\3\37\3\37\3\37\7\37\u0196\n\37\f\37\16\37\u0199"+
		"\13\37\3\37\5\37\u019c\n\37\3\37\3\37\3 \3 \3 \3 \3!\3!\3!\3\"\3\"\3\""+
		"\3\"\3\"\3\"\5\"\u01ad\n\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\5#\u01b7\n#\3$\3"+
		"$\3$\3$\3$\5$\u01be\n$\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3"+
		"%\3%\3%\3%\3%\3%\5%\u01d5\n%\3%\3%\3%\7%\u01da\n%\f%\16%\u01dd\13%\3%"+
		"\3%\5%\u01e1\n%\3%\3%\5%\u01e5\n%\3%\3%\3%\3%\3%\3%\3%\7%\u01ee\n%\f%"+
		"\16%\u01f1\13%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\5%\u01fe\n%\3%\3%\3%\3"+
		"%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\7%\u0215\n%\f%\16"+
		"%\u0218\13%\3&\3&\3&\7&\u021d\n&\f&\16&\u0220\13&\5&\u0222\n&\3&\2\3H"+
		"\'\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BD"+
		"FHJ\2\b\3\2\n\13\4\2\t\t\30\30\3\2\65\67\3\2\63\64\3\2/\62\4\2++..\2\u0261"+
		"\2`\3\2\2\2\4e\3\2\2\2\6h\3\2\2\2\b~\3\2\2\2\n\u008d\3\2\2\2\f\u008f\3"+
		"\2\2\2\16\u0098\3\2\2\2\20\u009c\3\2\2\2\22\u00a0\3\2\2\2\24\u00b8\3\2"+
		"\2\2\26\u00ba\3\2\2\2\30\u00ce\3\2\2\2\32\u00d1\3\2\2\2\34\u00dc\3\2\2"+
		"\2\36\u00e8\3\2\2\2 \u010f\3\2\2\2\"\u0111\3\2\2\2$\u0116\3\2\2\2&\u011b"+
		"\3\2\2\2(\u012a\3\2\2\2*\u0139\3\2\2\2,\u013c\3\2\2\2.\u013f\3\2\2\2\60"+
		"\u0142\3\2\2\2\62\u0147\3\2\2\2\64\u0152\3\2\2\2\66\u015d\3\2\2\28\u0167"+
		"\3\2\2\2:\u018f\3\2\2\2<\u0191\3\2\2\2>\u019f\3\2\2\2@\u01a3\3\2\2\2B"+
		"\u01a6\3\2\2\2D\u01b1\3\2\2\2F\u01b8\3\2\2\2H\u01fd\3\2\2\2J\u0221\3\2"+
		"\2\2LN\5\4\3\2MO\5\6\4\2NM\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2\2\2Qa\3\2"+
		"\2\2RT\5\6\4\2SR\3\2\2\2TU\3\2\2\2US\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\5\4"+
		"\3\2Xa\3\2\2\2Ya\5\4\3\2Z\\\5\6\4\2[Z\3\2\2\2\\]\3\2\2\2][\3\2\2\2]^\3"+
		"\2\2\2^a\3\2\2\2_a\3\2\2\2`L\3\2\2\2`S\3\2\2\2`Y\3\2\2\2`[\3\2\2\2`_\3"+
		"\2\2\2ab\3\2\2\2bc\5\b\5\2cd\7\2\2\3d\3\3\2\2\2ef\7\31\2\2fg\5\32\16\2"+
		"g\5\3\2\2\2hi\7)\2\2ij\5\32\16\2j\7\3\2\2\2km\7\"\2\2lk\3\2\2\2lm\3\2"+
		"\2\2mn\3\2\2\2no\7?\2\2oq\7C\2\2pr\5\62\32\2qp\3\2\2\2qr\3\2\2\2rt\3\2"+
		"\2\2su\5\f\7\2ts\3\2\2\2tu\3\2\2\2uy\3\2\2\2vx\5\22\n\2wv\3\2\2\2x{\3"+
		"\2\2\2yw\3\2\2\2yz\3\2\2\2z|\3\2\2\2{y\3\2\2\2|\177\7>\2\2}\177\5\n\6"+
		"\2~l\3\2\2\2~}\3\2\2\2\177\t\3\2\2\2\u0080\u0082\5\36\20\2\u0081\u0080"+
		"\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084"+
		"\u008e\3\2\2\2\u0085\u0087\5\20\t\2\u0086\u0085\3\2\2\2\u0086\u0087\3"+
		"\2\2\2\u0087\u0088\3\2\2\2\u0088\u008a\5\24\13\2\u0089\u0086\3\2\2\2\u008a"+
		"\u008b\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008e\3\2"+
		"\2\2\u008d\u0081\3\2\2\2\u008d\u0089\3\2\2\2\u008e\13\3\2\2\2\u008f\u0090"+
		"\7\23\2\2\u0090\u0095\5\16\b\2\u0091\u0092\7-\2\2\u0092\u0094\5\16\b\2"+
		"\u0093\u0091\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096"+
		"\3\2\2\2\u0096\r\3\2\2\2\u0097\u0095\3\2\2\2\u0098\u009a\5\32\16\2\u0099"+
		"\u009b\5\64\33\2\u009a\u0099\3\2\2\2\u009a\u009b\3\2\2\2\u009b\17\3\2"+
		"\2\2\u009c\u009d\t\2\2\2\u009d\21\3\2\2\2\u009e\u00a1\5:\36\2\u009f\u00a1"+
		"\5\24\13\2\u00a0\u009e\3\2\2\2\u00a0\u009f\3\2\2\2\u00a1\23\3\2\2\2\u00a2"+
		"\u00a4\5\20\t\2\u00a3\u00a2\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\3"+
		"\2\2\2\u00a5\u00a6\5\26\f\2\u00a6\u00a7\5\34\17\2\u00a7\u00a8\7>\2\2\u00a8"+
		"\u00b9\3\2\2\2\u00a9\u00ab\5\20\t\2\u00aa\u00a9\3\2\2\2\u00aa\u00ab\3"+
		"\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\7\21\2\2\u00ad\u00b9\5\26\f\2\u00ae"+
		"\u00b0\5\20\t\2\u00af\u00ae\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\3"+
		"\2\2\2\u00b1\u00b2\7\22\2\2\u00b2\u00b9\5\26\f\2\u00b3\u00b4\7\4\2\2\u00b4"+
		"\u00b5\7\5\2\2\u00b5\u00b6\5\34\17\2\u00b6\u00b7\7>\2\2\u00b7\u00b9\3"+
		"\2\2\2\u00b8\u00a3\3\2\2\2\u00b8\u00aa\3\2\2\2\u00b8\u00af\3\2\2\2\u00b8"+
		"\u00b3\3\2\2\2\u00b9\25\3\2\2\2\u00ba\u00bb\7#\2\2\u00bb\u00c8\7C\2\2"+
		"\u00bc\u00c5\7:\2\2\u00bd\u00c2\5\30\r\2\u00be\u00bf\7-\2\2\u00bf\u00c1"+
		"\5\30\r\2\u00c0\u00be\3\2\2\2\u00c1\u00c4\3\2\2\2\u00c2\u00c0\3\2\2\2"+
		"\u00c2\u00c3\3\2\2\2\u00c3\u00c6\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00bd"+
		"\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c9\7;\2\2\u00c8"+
		"\u00bc\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00cc\3\2\2\2\u00ca\u00cb\7\35"+
		"\2\2\u00cb\u00cd\58\35\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd"+
		"\27\3\2\2\2\u00ce\u00cf\58\35\2\u00cf\u00d0\7C\2\2\u00d0\31\3\2\2\2\u00d1"+
		"\u00d6\7C\2\2\u00d2\u00d3\7,\2\2\u00d3\u00d5\7C\2\2\u00d4\u00d2\3\2\2"+
		"\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\33"+
		"\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00db\5\36\20\2\u00da\u00d9\3\2\2\2"+
		"\u00db\u00de\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\35"+
		"\3\2\2\2\u00de\u00dc\3\2\2\2\u00df\u00e9\5 \21\2\u00e0\u00e9\5<\37\2\u00e1"+
		"\u00e9\5:\36\2\u00e2\u00e9\5B\"\2\u00e3\u00e9\5\60\31\2\u00e4\u00e9\5"+
		",\27\2\u00e5\u00e9\5.\30\2\u00e6\u00e9\5&\24\2\u00e7\u00e9\5$\23\2\u00e8"+
		"\u00df\3\2\2\2\u00e8\u00e0\3\2\2\2\u00e8\u00e1\3\2\2\2\u00e8\u00e2\3\2"+
		"\2\2\u00e8\u00e3\3\2\2\2\u00e8\u00e4\3\2\2\2\u00e8\u00e5\3\2\2\2\u00e8"+
		"\u00e6\3\2\2\2\u00e8\u00e7\3\2\2\2\u00e9\37\3\2\2\2\u00ea\u00eb\7\b\2"+
		"\2\u00eb\u00ed\7$\2\2\u00ec\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00f0"+
		"\3\2\2\2\u00ee\u00ef\7C\2\2\u00ef\u00f1\7$\2\2\u00f0\u00ee\3\2\2\2\u00f0"+
		"\u00f1\3\2\2\2\u00f1\u00f7\3\2\2\2\u00f2\u00f3\5F$\2\u00f3\u00f4\7$\2"+
		"\2\u00f4\u00f6\3\2\2\2\u00f5\u00f2\3\2\2\2\u00f6\u00f9\3\2\2\2\u00f7\u00f5"+
		"\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00fa\3\2\2\2\u00f9\u00f7\3\2\2\2\u00fa"+
		"\u0110\5\"\22\2\u00fb\u00fc\7\b\2\2\u00fc\u00fe\7$\2\2\u00fd\u00fb\3\2"+
		"\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0100\7C\2\2\u0100"+
		"\u0102\7$\2\2\u0101\u00fd\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0103\3\2"+
		"\2\2\u0103\u0104\7\20\2\2\u0104\u0105\7$\2\2\u0105\u0106\5\32\16\2\u0106"+
		"\u0107\7$\2\2\u0107\u010c\5D#\2\u0108\u0109\7$\2\2\u0109\u010b\5F$\2\u010a"+
		"\u0108\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2"+
		"\2\2\u010d\u0110\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u00ec\3\2\2\2\u010f"+
		"\u0101\3\2\2\2\u0110!\3\2\2\2\u0111\u0112\7C\2\2\u0112\u0113\7:\2\2\u0113"+
		"\u0114\5J&\2\u0114\u0115\7;\2\2\u0115#\3\2\2\2\u0116\u0117\7\f\2\2\u0117"+
		"\u0118\7:\2\2\u0118\u0119\5H%\2\u0119\u011a\7;\2\2\u011a%\3\2\2\2\u011b"+
		"\u011c\7\17\2\2\u011c\u0126\5\34\17\2\u011d\u011f\5(\25\2\u011e\u011d"+
		"\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u0121\3\2\2\2\u0121"+
		"\u0123\3\2\2\2\u0122\u0124\5*\26\2\u0123\u0122\3\2\2\2\u0123\u0124\3\2"+
		"\2\2\u0124\u0127\3\2\2\2\u0125\u0127\5*\26\2\u0126\u011e\3\2\2\2\u0126"+
		"\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u0129\7>\2\2\u0129\'\3\2\2\2"+
		"\u012a\u012b\7\r\2\2\u012b\u0135\7C\2\2\u012c\u012d\7\23\2\2\u012d\u0132"+
		"\5\32\16\2\u012e\u012f\7 \2\2\u012f\u0131\5\32\16\2\u0130\u012e\3\2\2"+
		"\2\u0131\u0134\3\2\2\2\u0132\u0130\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0136"+
		"\3\2\2\2\u0134\u0132\3\2\2\2\u0135\u012c\3\2\2\2\u0135\u0136\3\2\2\2\u0136"+
		"\u0137\3\2\2\2\u0137\u0138\5\34\17\2\u0138)\3\2\2\2\u0139\u013a\7\16\2"+
		"\2\u013a\u013b\5\34\17\2\u013b+\3\2\2\2\u013c\u013d\7\3\2\2\u013d\u013e"+
		"\5H%\2\u013e-\3\2\2\2\u013f\u0140\7\26\2\2\u0140\u0141\5H%\2\u0141/\3"+
		"\2\2\2\u0142\u0145\7\36\2\2\u0143\u0146\5H%\2\u0144\u0146\7\27\2\2\u0145"+
		"\u0143\3\2\2\2\u0145\u0144\3\2\2\2\u0146\61\3\2\2\2\u0147\u0148\7\61\2"+
		"\2\u0148\u014d\7C\2\2\u0149\u014a\7-\2\2\u014a\u014c\7C\2\2\u014b\u0149"+
		"\3\2\2\2\u014c\u014f\3\2\2\2\u014d\u014b\3\2\2\2\u014d\u014e\3\2\2\2\u014e"+
		"\u0150\3\2\2\2\u014f\u014d\3\2\2\2\u0150\u0151\7/\2\2\u0151\63\3\2\2\2"+
		"\u0152\u0153\7\61\2\2\u0153\u0158\58\35\2\u0154\u0155\7-\2\2\u0155\u0157"+
		"\58\35\2\u0156\u0154\3\2\2\2\u0157\u015a\3\2\2\2\u0158\u0156\3\2\2\2\u0158"+
		"\u0159\3\2\2\2\u0159\u015b\3\2\2\2\u015a\u0158\3\2\2\2\u015b\u015c\7/"+
		"\2\2\u015c\65\3\2\2\2\u015d\u015e\5\32\16\2\u015e\67\3\2\2\2\u015f\u0161"+
		"\5\32\16\2\u0160\u0162\5\64\33\2\u0161\u0160\3\2\2\2\u0161\u0162\3\2\2"+
		"\2\u0162\u0168\3\2\2\2\u0163\u0168\7%\2\2\u0164\u0168\7&\2\2\u0165\u0168"+
		"\7\'\2\2\u0166\u0168\7(\2\2\u0167\u015f\3\2\2\2\u0167\u0163\3\2\2\2\u0167"+
		"\u0164\3\2\2\2\u0167\u0165\3\2\2\2\u0167\u0166\3\2\2\2\u01689\3\2\2\2"+
		"\u0169\u016a\7\b\2\2\u016a\u016c\7$\2\2\u016b\u0169\3\2\2\2\u016b\u016c"+
		"\3\2\2\2\u016c\u016d\3\2\2\2\u016d\u016e\7C\2\2\u016e\u016f\7.\2\2\u016f"+
		"\u0190\5H%\2\u0170\u0171\7\20\2\2\u0171\u0172\7$\2\2\u0172\u0173\5\32"+
		"\16\2\u0173\u0174\7$\2\2\u0174\u0175\7C\2\2\u0175\u0176\7.\2\2\u0176\u0177"+
		"\5H%\2\u0177\u0190\3\2\2\2\u0178\u017d\7C\2\2\u0179\u017a\7$\2\2\u017a"+
		"\u017b\7\20\2\2\u017b\u017c\7$\2\2\u017c\u017e\5\32\16\2\u017d\u0179\3"+
		"\2\2\2\u017d\u017e\3\2\2\2\u017e\u017f\3\2\2\2\u017f\u0180\7$\2\2\u0180"+
		"\u0181\7C\2\2\u0181\u0182\7.\2\2\u0182\u0190\5H%\2\u0183\u0185\5\20\t"+
		"\2\u0184\u0183\3\2\2\2\u0184\u0185\3\2\2\2\u0185\u0187\3\2\2\2\u0186\u0188"+
		"\7\6\2\2\u0187\u0186\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u0189\3\2\2\2\u0189"+
		"\u018a\58\35\2\u018a\u018d\7C\2\2\u018b\u018c\7.\2\2\u018c\u018e\5H%\2"+
		"\u018d\u018b\3\2\2\2\u018d\u018e\3\2\2\2\u018e\u0190\3\2\2\2\u018f\u016b"+
		"\3\2\2\2\u018f\u0170\3\2\2\2\u018f\u0178\3\2\2\2\u018f\u0184\3\2\2\2\u0190"+
		";\3\2\2\2\u0191\u0192\7=\2\2\u0192\u0193\5H%\2\u0193\u0197\5\34\17\2\u0194"+
		"\u0196\5> \2\u0195\u0194\3\2\2\2\u0196\u0199\3\2\2\2\u0197\u0195\3\2\2"+
		"\2\u0197\u0198\3\2\2\2\u0198\u019b\3\2\2\2\u0199\u0197\3\2\2\2\u019a\u019c"+
		"\5@!\2\u019b\u019a\3\2\2\2\u019b\u019c\3\2\2\2\u019c\u019d\3\2\2\2\u019d"+
		"\u019e\7>\2\2\u019e=\3\2\2\2\u019f\u01a0\7\7\2\2\u01a0\u01a1\5H%\2\u01a1"+
		"\u01a2\5\34\17\2\u01a2?\3\2\2\2\u01a3\u01a4\7\34\2\2\u01a4\u01a5\5\34"+
		"\17\2\u01a5A\3\2\2\2\u01a6\u01ac\7\33\2\2\u01a7\u01a8\5H%\2\u01a8\u01a9"+
		"\7\32\2\2\u01a9\u01ad\3\2\2\2\u01aa\u01ab\t\3\2\2\u01ab\u01ad\5H%\2\u01ac"+
		"\u01a7\3\2\2\2\u01ac\u01aa\3\2\2\2\u01ad\u01ae\3\2\2\2\u01ae\u01af\5\34"+
		"\17\2\u01af\u01b0\7>\2\2\u01b0C\3\2\2\2\u01b1\u01b6\7C\2\2\u01b2\u01b3"+
		"\7:\2\2\u01b3\u01b4\5J&\2\u01b4\u01b5\7;\2\2\u01b5\u01b7\3\2\2\2\u01b6"+
		"\u01b2\3\2\2\2\u01b6\u01b7\3\2\2\2\u01b7E\3\2\2\2\u01b8\u01bd\7C\2\2\u01b9"+
		"\u01ba\7:\2\2\u01ba\u01bb\5J&\2\u01bb\u01bc\7;\2\2\u01bc\u01be\3\2\2\2"+
		"\u01bd\u01b9\3\2\2\2\u01bd\u01be\3\2\2\2\u01beG\3\2\2\2\u01bf\u01c0\b"+
		"%\1\2\u01c0\u01c1\7:\2\2\u01c1\u01c2\5H%\2\u01c2\u01c3\7;\2\2\u01c3\u01fe"+
		"\3\2\2\2\u01c4\u01fe\7A\2\2\u01c5\u01fe\7@\2\2\u01c6\u01fe\7B\2\2\u01c7"+
		"\u01fe\7D\2\2\u01c8\u01fe\7!\2\2\u01c9\u01fe\7\b\2\2\u01ca\u01cb\7\25"+
		"\2\2\u01cb\u01cc\7:\2\2\u01cc\u01cd\5H%\2\u01cd\u01ce\7;\2\2\u01ce\u01fe"+
		"\3\2\2\2\u01cf\u01d0\7\25\2\2\u01d0\u01d1\7:\2\2\u01d1\u01fe\7;\2\2\u01d2"+
		"\u01d3\7\b\2\2\u01d3\u01d5\7$\2\2\u01d4\u01d2\3\2\2\2\u01d4\u01d5\3\2"+
		"\2\2\u01d5\u01d6\3\2\2\2\u01d6\u01db\5F$\2\u01d7\u01d8\7$\2\2\u01d8\u01da"+
		"\5F$\2\u01d9\u01d7\3\2\2\2\u01da\u01dd\3\2\2\2\u01db\u01d9\3\2\2\2\u01db"+
		"\u01dc\3\2\2\2\u01dc\u01fe\3\2\2\2\u01dd\u01db\3\2\2\2\u01de\u01df\7\b"+
		"\2\2\u01df\u01e1\7$\2\2\u01e0\u01de\3\2\2\2\u01e0\u01e1\3\2\2\2\u01e1"+
		"\u01e2\3\2\2\2\u01e2\u01e3\7C\2\2\u01e3\u01e5\7$\2\2\u01e4\u01e0\3\2\2"+
		"\2\u01e4\u01e5\3\2\2\2\u01e5\u01e6\3\2\2\2\u01e6\u01e7\7\20\2\2\u01e7"+
		"\u01e8\7$\2\2\u01e8\u01e9\5\32\16\2\u01e9\u01ea\7$\2\2\u01ea\u01ef\5D"+
		"#\2\u01eb\u01ec\7$\2\2\u01ec\u01ee\5F$\2\u01ed\u01eb\3\2\2\2\u01ee\u01f1"+
		"\3\2\2\2\u01ef\u01ed\3\2\2\2\u01ef\u01f0\3\2\2\2\u01f0\u01fe\3\2\2\2\u01f1"+
		"\u01ef\3\2\2\2\u01f2\u01f3\7\64\2\2\u01f3\u01fe\5H%\f\u01f4\u01f5\7*\2"+
		"\2\u01f5\u01fe\5H%\13\u01f6\u01f7\7\24\2\2\u01f7\u01f8\7:\2\2\u01f8\u01f9"+
		"\58\35\2\u01f9\u01fa\7-\2\2\u01fa\u01fb\5H%\2\u01fb\u01fc\7;\2\2\u01fc"+
		"\u01fe\3\2\2\2\u01fd\u01bf\3\2\2\2\u01fd\u01c4\3\2\2\2\u01fd\u01c5\3\2"+
		"\2\2\u01fd\u01c6\3\2\2\2\u01fd\u01c7\3\2\2\2\u01fd\u01c8\3\2\2\2\u01fd"+
		"\u01c9\3\2\2\2\u01fd\u01ca\3\2\2\2\u01fd\u01cf\3\2\2\2\u01fd\u01d4\3\2"+
		"\2\2\u01fd\u01e4\3\2\2\2\u01fd\u01f2\3\2\2\2\u01fd\u01f4\3\2\2\2\u01fd"+
		"\u01f6\3\2\2\2\u01fe\u0216\3\2\2\2\u01ff\u0200\f\t\2\2\u0200\u0201\t\4"+
		"\2\2\u0201\u0215\5H%\n\u0202\u0203\f\b\2\2\u0203\u0204\t\5\2\2\u0204\u0215"+
		"\5H%\t\u0205\u0206\f\7\2\2\u0206\u0207\t\6\2\2\u0207\u0215\5H%\b\u0208"+
		"\u0209\f\5\2\2\u0209\u020a\t\7\2\2\u020a\u0215\5H%\6\u020b\u020c\f\4\2"+
		"\2\u020c\u020d\7\37\2\2\u020d\u0215\5H%\5\u020e\u020f\f\3\2\2\u020f\u0210"+
		"\7 \2\2\u0210\u0215\5H%\4\u0211\u0212\f\6\2\2\u0212\u0213\7\23\2\2\u0213"+
		"\u0215\5\66\34\2\u0214\u01ff\3\2\2\2\u0214\u0202\3\2\2\2\u0214\u0205\3"+
		"\2\2\2\u0214\u0208\3\2\2\2\u0214\u020b\3\2\2\2\u0214\u020e\3\2\2\2\u0214"+
		"\u0211\3\2\2\2\u0215\u0218\3\2\2\2\u0216\u0214\3\2\2\2\u0216\u0217\3\2"+
		"\2\2\u0217I\3\2\2\2\u0218\u0216\3\2\2\2\u0219\u021e\5H%\2\u021a\u021b"+
		"\7-\2\2\u021b\u021d\5H%\2\u021c\u021a\3\2\2\2\u021d\u0220\3\2\2\2\u021e"+
		"\u021c\3\2\2\2\u021e\u021f\3\2\2\2\u021f\u0222\3\2\2\2\u0220\u021e\3\2"+
		"\2\2\u0221\u0219\3\2\2\2\u0221\u0222\3\2\2\2\u0222K\3\2\2\2CPU]`lqty~"+
		"\u0083\u0086\u008b\u008d\u0095\u009a\u00a0\u00a3\u00aa\u00af\u00b8\u00c2"+
		"\u00c5\u00c8\u00cc\u00d6\u00dc\u00e8\u00ec\u00f0\u00f7\u00fd\u0101\u010c"+
		"\u010f\u0120\u0123\u0126\u0132\u0135\u0145\u014d\u0158\u0161\u0167\u016b"+
		"\u017d\u0184\u0187\u018d\u018f\u0197\u019b\u01ac\u01b6\u01bd\u01d4\u01db"+
		"\u01e0\u01e4\u01ef\u01fd\u0214\u0216\u021e\u0221";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}