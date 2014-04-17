// Generated from /Users/stefika/Repositories/quorum-language/plugins/ParserPlugin/src/plugins/quorum/Libraries/Language/Compile/Quorum.g4 by ANTLR 4.1
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
		RULE_class_stmnts = 7, RULE_method_declaration = 8, RULE_method_shared = 9, 
		RULE_formal_parameter = 10, RULE_qualified_name = 11, RULE_block = 12, 
		RULE_statement = 13, RULE_solo_method_call = 14, RULE_alert_statement = 15, 
		RULE_check_statement = 16, RULE_detect_parameter = 17, RULE_print_statement = 18, 
		RULE_speak_statement = 19, RULE_return_statement = 20, RULE_generic_declaration = 21, 
		RULE_generic_statement = 22, RULE_class_type = 23, RULE_assignment_declaration = 24, 
		RULE_assignment_statement = 25, RULE_if_statement = 26, RULE_loop_statement = 27, 
		RULE_expression = 28, RULE_function_expression_list = 29;
	public static final String[] ruleNames = {
		"start", "package_rule", "reference", "class_declaration", "no_class_stmnts", 
		"inherit_stmnts", "access_modifier", "class_stmnts", "method_declaration", 
		"method_shared", "formal_parameter", "qualified_name", "block", "statement", 
		"solo_method_call", "alert_statement", "check_statement", "detect_parameter", 
		"print_statement", "speak_statement", "return_statement", "generic_declaration", 
		"generic_statement", "class_type", "assignment_declaration", "assignment_statement", 
		"if_statement", "loop_statement", "expression", "function_expression_list"
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
			setState(80);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(60); package_rule();
				setState(62); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(61); reference();
					}
					}
					setState(64); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==USE );
				}
				break;

			case 2:
				{
				setState(67); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(66); reference();
					}
					}
					setState(69); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==USE );
				setState(71); package_rule();
				}
				break;

			case 3:
				{
				setState(73); package_rule();
				}
				break;

			case 4:
				{
				setState(75); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(74); reference();
					}
					}
					setState(77); 
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
			setState(82); class_declaration();
			setState(83); match(EOF);
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
			setState(85); match(PACKAGE_NAME);
			setState(86); ((Package_ruleContext)_localctx).name = qualified_name();
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
			setState(88); match(USE);
			setState(89); qualified_name();
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
	public static class NoClassDeclarationContext extends Class_declarationContext {
		public No_class_stmntsContext no_class_stmnts() {
			return getRuleContext(No_class_stmntsContext.class,0);
		}
		public NoClassDeclarationContext(Class_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterNoClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitNoClassDeclaration(this);
		}
	}
	public static class FullClassDeclarationContext extends Class_declarationContext {
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
		public FullClassDeclarationContext(Class_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterFullClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitFullClassDeclaration(this);
		}
	}

	public final Class_declarationContext class_declaration() throws RecognitionException {
		Class_declarationContext _localctx = new Class_declarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_class_declaration);
		int _la;
		try {
			setState(107);
			switch (_input.LA(1)) {
			case CLASS:
				_localctx = new FullClassDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(91); match(CLASS);
				setState(92); match(ID);
				setState(94);
				_la = _input.LA(1);
				if (_la==LESS) {
					{
					setState(93); generic_declaration();
					}
				}

				setState(97);
				_la = _input.LA(1);
				if (_la==INHERITS) {
					{
					setState(96); inherit_stmnts();
					}
				}

				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 2)) & ~0x3f) == 0 && ((1L << (_la - 2)) & ((1L << (ON - 2)) | (1L << (CONSTANT - 2)) | (1L << (ME - 2)) | (1L << (PUBLIC - 2)) | (1L << (PRIVATE - 2)) | (1L << (PARENT - 2)) | (1L << (BLUEPRINT - 2)) | (1L << (NATIVE - 2)) | (1L << (ACTION - 2)) | (1L << (INTEGER_KEYWORD - 2)) | (1L << (NUMBER_KEYWORD - 2)) | (1L << (TEXT - 2)) | (1L << (BOOLEAN_KEYWORD - 2)) | (1L << (ID - 2)))) != 0)) {
					{
					{
					setState(99); class_stmnts();
					}
					}
					setState(104);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(105); match(END);
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
				setState(106); no_class_stmnts();
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
			setState(122);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(110); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(109); statement();
					}
					}
					setState(112); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((((_la - 1)) & ~0x3f) == 0 && ((1L << (_la - 1)) & ((1L << (OUTPUT - 1)) | (1L << (CONSTANT - 1)) | (1L << (ME - 1)) | (1L << (PUBLIC - 1)) | (1L << (PRIVATE - 1)) | (1L << (ALERT - 1)) | (1L << (CHECK - 1)) | (1L << (PARENT - 1)) | (1L << (SAY - 1)) | (1L << (REPEAT - 1)) | (1L << (RETURN - 1)) | (1L << (INTEGER_KEYWORD - 1)) | (1L << (NUMBER_KEYWORD - 1)) | (1L << (TEXT - 1)) | (1L << (BOOLEAN_KEYWORD - 1)) | (1L << (IF - 1)) | (1L << (ID - 1)))) != 0) );
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(118); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(115);
					_la = _input.LA(1);
					if (_la==PUBLIC || _la==PRIVATE) {
						{
						setState(114); access_modifier();
						}
					}

					setState(117); method_declaration();
					}
					}
					setState(120); 
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
			setState(124); match(INHERITS);
			setState(125); qualified_name();
			setState(127);
			_la = _input.LA(1);
			if (_la==LESS) {
				{
				setState(126); generic_statement();
				}
			}

			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(129); match(COMMA);
				setState(130); qualified_name();
				setState(132);
				_la = _input.LA(1);
				if (_la==LESS) {
					{
					setState(131); generic_statement();
					}
				}

				}
				}
				setState(138);
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
			setState(139);
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
			setState(146);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(141); assignment_statement();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(143);
				_la = _input.LA(1);
				if (_la==PUBLIC || _la==PRIVATE) {
					{
					setState(142); access_modifier();
					}
				}

				setState(145); method_declaration();
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
	public static class NativeActionContext extends Method_declarationContext {
		public TerminalNode NATIVE() { return getToken(QuorumParser.NATIVE, 0); }
		public Method_sharedContext method_shared() {
			return getRuleContext(Method_sharedContext.class,0);
		}
		public NativeActionContext(Method_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterNativeAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitNativeAction(this);
		}
	}
	public static class ActionContext extends Method_declarationContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Method_sharedContext method_shared() {
			return getRuleContext(Method_sharedContext.class,0);
		}
		public TerminalNode END() { return getToken(QuorumParser.END, 0); }
		public ActionContext(Method_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitAction(this);
		}
	}
	public static class ConstructorContext extends Method_declarationContext {
		public TerminalNode CREATE() { return getToken(QuorumParser.CREATE, 0); }
		public TerminalNode ON() { return getToken(QuorumParser.ON, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode END() { return getToken(QuorumParser.END, 0); }
		public ConstructorContext(Method_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterConstructor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitConstructor(this);
		}
	}
	public static class BlueprintActionContext extends Method_declarationContext {
		public TerminalNode BLUEPRINT() { return getToken(QuorumParser.BLUEPRINT, 0); }
		public Method_sharedContext method_shared() {
			return getRuleContext(Method_sharedContext.class,0);
		}
		public BlueprintActionContext(Method_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterBlueprintAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitBlueprintAction(this);
		}
	}

	public final Method_declarationContext method_declaration() throws RecognitionException {
		Method_declarationContext _localctx = new Method_declarationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_method_declaration);
		try {
			setState(161);
			switch (_input.LA(1)) {
			case ACTION:
				_localctx = new ActionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(148); method_shared();
				setState(149); block();
				setState(150); match(END);
				}
				break;
			case BLUEPRINT:
				_localctx = new BlueprintActionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(152); match(BLUEPRINT);
				setState(153); method_shared();
				}
				break;
			case NATIVE:
				_localctx = new NativeActionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(154); match(NATIVE);
				setState(155); method_shared();
				}
				break;
			case ON:
				_localctx = new ConstructorContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(156); match(ON);
				setState(157); match(CREATE);
				setState(158); block();
				setState(159); match(END);
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

	public static class Method_sharedContext extends ParserRuleContext {
		public quorum.Libraries.Language.Compile.Context.ActionContext actionContext;
		public Assignment_declarationContext return_type;
		public Assignment_declarationContext assignment_declaration() {
			return getRuleContext(Assignment_declarationContext.class,0);
		}
		public TerminalNode ACTION() { return getToken(QuorumParser.ACTION, 0); }
		public Formal_parameterContext formal_parameter(int i) {
			return getRuleContext(Formal_parameterContext.class,i);
		}
		public List<Formal_parameterContext> formal_parameter() {
			return getRuleContexts(Formal_parameterContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(QuorumParser.COMMA); }
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode RETURNS() { return getToken(QuorumParser.RETURNS, 0); }
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(QuorumParser.COMMA, i);
		}
		public Method_sharedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_shared; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterMethod_shared(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitMethod_shared(this);
		}
	}

	public final Method_sharedContext method_shared() throws RecognitionException {
		Method_sharedContext _localctx = new Method_sharedContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_method_shared);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163); match(ACTION);
			setState(164); match(ID);
			setState(177);
			_la = _input.LA(1);
			if (_la==LEFT_PAREN) {
				{
				setState(165); match(LEFT_PAREN);
				setState(174);
				_la = _input.LA(1);
				if (((((_la - 34)) & ~0x3f) == 0 && ((1L << (_la - 34)) & ((1L << (INTEGER_KEYWORD - 34)) | (1L << (NUMBER_KEYWORD - 34)) | (1L << (TEXT - 34)) | (1L << (BOOLEAN_KEYWORD - 34)) | (1L << (ID - 34)))) != 0)) {
					{
					setState(166); formal_parameter();
					setState(171);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(167); match(COMMA);
						setState(168); formal_parameter();
						}
						}
						setState(173);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(176); match(RIGHT_PAREN);
				}
			}

			setState(181);
			_la = _input.LA(1);
			if (_la==RETURNS) {
				{
				setState(179); match(RETURNS);
				setState(180); ((Method_sharedContext)_localctx).return_type = assignment_declaration();
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
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterFormal_parameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitFormal_parameter(this);
		}
	}

	public final Formal_parameterContext formal_parameter() throws RecognitionException {
		Formal_parameterContext _localctx = new Formal_parameterContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_formal_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183); assignment_declaration();
			setState(184); match(ID);
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
		enterRule(_localctx, 22, RULE_qualified_name);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(186); ((Qualified_nameContext)_localctx).ID = match(ID);
			((Qualified_nameContext)_localctx).ids.add(((Qualified_nameContext)_localctx).ID);
			setState(191);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(187); match(PERIOD);
					setState(188); ((Qualified_nameContext)_localctx).ID = match(ID);
					((Qualified_nameContext)_localctx).ids.add(((Qualified_nameContext)_localctx).ID);
					}
					} 
				}
				setState(193);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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
		enterRule(_localctx, 24, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 1)) & ~0x3f) == 0 && ((1L << (_la - 1)) & ((1L << (OUTPUT - 1)) | (1L << (CONSTANT - 1)) | (1L << (ME - 1)) | (1L << (PUBLIC - 1)) | (1L << (PRIVATE - 1)) | (1L << (ALERT - 1)) | (1L << (CHECK - 1)) | (1L << (PARENT - 1)) | (1L << (SAY - 1)) | (1L << (REPEAT - 1)) | (1L << (RETURN - 1)) | (1L << (INTEGER_KEYWORD - 1)) | (1L << (NUMBER_KEYWORD - 1)) | (1L << (TEXT - 1)) | (1L << (BOOLEAN_KEYWORD - 1)) | (1L << (IF - 1)) | (1L << (ID - 1)))) != 0)) {
				{
				{
				setState(194); statement();
				}
				}
				setState(199);
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
		enterRule(_localctx, 26, RULE_statement);
		try {
			setState(209);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(200); solo_method_call();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(201); if_statement();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(202); assignment_statement();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(203); loop_statement();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(204); return_statement();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(205); print_statement();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(206); speak_statement();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(207); check_statement();
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(208); alert_statement();
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
		enterRule(_localctx, 28, RULE_solo_method_call);
		int _la;
		try {
			setState(267);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(211); qualified_name();
				setState(214);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(212); match(COLON);
					setState(213); match(ID);
					}
				}

				setState(216); match(LEFT_PAREN);
				setState(225);
				_la = _input.LA(1);
				if (((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & ((1L << (ME - 6)) | (1L << (PARENT - 6)) | (1L << (CAST - 6)) | (1L << (INPUT - 6)) | (1L << (NULL - 6)) | (1L << (NOT - 6)) | (1L << (MINUS - 6)) | (1L << (LEFT_PAREN - 6)) | (1L << (BOOLEAN - 6)) | (1L << (INT - 6)) | (1L << (DECIMAL - 6)) | (1L << (ID - 6)) | (1L << (STRING - 6)))) != 0)) {
					{
					setState(217); expression(0);
					setState(222);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(218); match(COMMA);
						setState(219); expression(0);
						}
						}
						setState(224);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(227); match(RIGHT_PAREN);
				}
				break;
			case PARENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(229); match(PARENT);
				setState(230); match(COLON);
				setState(231); qualified_name();
				setState(232); match(COLON);
				setState(233); match(ID);
				setState(234); match(LEFT_PAREN);
				setState(243);
				_la = _input.LA(1);
				if (((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & ((1L << (ME - 6)) | (1L << (PARENT - 6)) | (1L << (CAST - 6)) | (1L << (INPUT - 6)) | (1L << (NULL - 6)) | (1L << (NOT - 6)) | (1L << (MINUS - 6)) | (1L << (LEFT_PAREN - 6)) | (1L << (BOOLEAN - 6)) | (1L << (INT - 6)) | (1L << (DECIMAL - 6)) | (1L << (ID - 6)) | (1L << (STRING - 6)))) != 0)) {
					{
					setState(235); expression(0);
					setState(240);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(236); match(COMMA);
						setState(237); expression(0);
						}
						}
						setState(242);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(245); match(RIGHT_PAREN);
				}
				break;
			case ME:
				enterOuterAlt(_localctx, 3);
				{
				setState(247); match(ME);
				setState(248); match(COLON);
				setState(249); qualified_name();
				setState(252);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(250); match(COLON);
					setState(251); match(ID);
					}
				}

				setState(254); match(LEFT_PAREN);
				setState(263);
				_la = _input.LA(1);
				if (((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & ((1L << (ME - 6)) | (1L << (PARENT - 6)) | (1L << (CAST - 6)) | (1L << (INPUT - 6)) | (1L << (NULL - 6)) | (1L << (NOT - 6)) | (1L << (MINUS - 6)) | (1L << (LEFT_PAREN - 6)) | (1L << (BOOLEAN - 6)) | (1L << (INT - 6)) | (1L << (DECIMAL - 6)) | (1L << (ID - 6)) | (1L << (STRING - 6)))) != 0)) {
					{
					setState(255); expression(0);
					setState(260);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(256); match(COMMA);
						setState(257); expression(0);
						}
						}
						setState(262);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(265); match(RIGHT_PAREN);
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
		enterRule(_localctx, 30, RULE_alert_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269); match(ALERT);
			setState(270); match(LEFT_PAREN);
			setState(271); expression(0);
			setState(272); match(RIGHT_PAREN);
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
		enterRule(_localctx, 32, RULE_check_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274); match(CHECK);
			setState(275); block();
			setState(290);
			switch (_input.LA(1)) {
			case DETECT:
				{
				setState(280); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(276); match(DETECT);
					setState(277); detect_parameter();
					setState(278); block();
					}
					}
					setState(282); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DETECT );
				setState(286);
				_la = _input.LA(1);
				if (_la==ALWAYS) {
					{
					setState(284); match(ALWAYS);
					setState(285); block();
					}
				}

				}
				break;
			case ALWAYS:
				{
				setState(288); match(ALWAYS);
				setState(289); block();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(292); match(END);
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
		enterRule(_localctx, 34, RULE_detect_parameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294); match(ID);
			setState(304);
			_la = _input.LA(1);
			if (_la==INHERITS) {
				{
				setState(295); match(INHERITS);
				setState(296); qualified_name();
				setState(301);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OR) {
					{
					{
					setState(297); match(OR);
					setState(298); qualified_name();
					}
					}
					setState(303);
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
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterPrint_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitPrint_statement(this);
		}
	}

	public final Print_statementContext print_statement() throws RecognitionException {
		Print_statementContext _localctx = new Print_statementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_print_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306); match(OUTPUT);
			setState(307); expression(0);
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
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterSpeak_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitSpeak_statement(this);
		}
	}

	public final Speak_statementContext speak_statement() throws RecognitionException {
		Speak_statementContext _localctx = new Speak_statementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_speak_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309); match(SAY);
			setState(310); expression(0);
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
		public TerminalNode RETURN() { return getToken(QuorumParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
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
		enterRule(_localctx, 40, RULE_return_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312); match(RETURN);
			setState(315);
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
				setState(313); expression(0);
				}
				break;
			case NOW:
				{
				setState(314); match(NOW);
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
		enterRule(_localctx, 42, RULE_generic_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317); match(LESS);
			setState(318); ((Generic_declarationContext)_localctx).ID = match(ID);
			((Generic_declarationContext)_localctx).ids.add(((Generic_declarationContext)_localctx).ID);
			setState(323);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(319); match(COMMA);
				setState(320); ((Generic_declarationContext)_localctx).ID = match(ID);
				((Generic_declarationContext)_localctx).ids.add(((Generic_declarationContext)_localctx).ID);
				}
				}
				setState(325);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(326); match(GREATER);
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
		enterRule(_localctx, 44, RULE_generic_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328); match(LESS);
			setState(329); assignment_declaration();
			setState(334);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(330); match(COMMA);
				setState(331); assignment_declaration();
				}
				}
				setState(336);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(337); match(GREATER);
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
		enterRule(_localctx, 46, RULE_class_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339); qualified_name();
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
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterGenericAssignmentDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitGenericAssignmentDeclaration(this);
		}
	}
	public static class NumberAssignmentDeclarationContext extends Assignment_declarationContext {
		public TerminalNode NUMBER_KEYWORD() { return getToken(QuorumParser.NUMBER_KEYWORD, 0); }
		public NumberAssignmentDeclarationContext(Assignment_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterNumberAssignmentDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitNumberAssignmentDeclaration(this);
		}
	}
	public static class TextAssignmentDeclarationContext extends Assignment_declarationContext {
		public TerminalNode TEXT() { return getToken(QuorumParser.TEXT, 0); }
		public TextAssignmentDeclarationContext(Assignment_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterTextAssignmentDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitTextAssignmentDeclaration(this);
		}
	}
	public static class IntegerAssignmentDeclarationContext extends Assignment_declarationContext {
		public TerminalNode INTEGER_KEYWORD() { return getToken(QuorumParser.INTEGER_KEYWORD, 0); }
		public IntegerAssignmentDeclarationContext(Assignment_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterIntegerAssignmentDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitIntegerAssignmentDeclaration(this);
		}
	}
	public static class BooleanAssignmentDeclarationContext extends Assignment_declarationContext {
		public TerminalNode BOOLEAN_KEYWORD() { return getToken(QuorumParser.BOOLEAN_KEYWORD, 0); }
		public BooleanAssignmentDeclarationContext(Assignment_declarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterBooleanAssignmentDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitBooleanAssignmentDeclaration(this);
		}
	}

	public final Assignment_declarationContext assignment_declaration() throws RecognitionException {
		Assignment_declarationContext _localctx = new Assignment_declarationContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_assignment_declaration);
		int _la;
		try {
			setState(349);
			switch (_input.LA(1)) {
			case ID:
				_localctx = new GenericAssignmentDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(341); qualified_name();
				setState(343);
				_la = _input.LA(1);
				if (_la==LESS) {
					{
					setState(342); generic_statement();
					}
				}

				}
				break;
			case INTEGER_KEYWORD:
				_localctx = new IntegerAssignmentDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(345); match(INTEGER_KEYWORD);
				}
				break;
			case NUMBER_KEYWORD:
				_localctx = new NumberAssignmentDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(346); match(NUMBER_KEYWORD);
				}
				break;
			case TEXT:
				_localctx = new TextAssignmentDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(347); match(TEXT);
				}
				break;
			case BOOLEAN_KEYWORD:
				_localctx = new BooleanAssignmentDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(348); match(BOOLEAN_KEYWORD);
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
	public static class ObjectAssignmentContext extends Assignment_statementContext {
		public Token object;
		public Qualified_nameContext parent;
		public Token name;
		public ExpressionContext rhs;
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public TerminalNode EQUALITY() { return getToken(QuorumParser.EQUALITY, 0); }
		public List<TerminalNode> ID() { return getTokens(QuorumParser.ID); }
		public TerminalNode COLON(int i) {
			return getToken(QuorumParser.COLON, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PARENT() { return getToken(QuorumParser.PARENT, 0); }
		public List<TerminalNode> COLON() { return getTokens(QuorumParser.COLON); }
		public TerminalNode ID(int i) {
			return getToken(QuorumParser.ID, i);
		}
		public ObjectAssignmentContext(Assignment_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterObjectAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitObjectAssignment(this);
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
		public TerminalNode EQUALITY() { return getToken(QuorumParser.EQUALITY, 0); }
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Access_modifierContext access_modifier() {
			return getRuleContext(Access_modifierContext.class,0);
		}
		public TerminalNode CONSTANT() { return getToken(QuorumParser.CONSTANT, 0); }
		public NormalAssignmentContext(Assignment_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterNormalAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitNormalAssignment(this);
		}
	}
	public static class ParentAssignmentContext extends Assignment_statementContext {
		public Qualified_nameContext parent;
		public Token name;
		public ExpressionContext rhs;
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public TerminalNode EQUALITY() { return getToken(QuorumParser.EQUALITY, 0); }
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode COLON(int i) {
			return getToken(QuorumParser.COLON, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PARENT() { return getToken(QuorumParser.PARENT, 0); }
		public List<TerminalNode> COLON() { return getTokens(QuorumParser.COLON); }
		public ParentAssignmentContext(Assignment_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterParentAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitParentAssignment(this);
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
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterNoTypeAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitNoTypeAssignment(this);
		}
	}

	public final Assignment_statementContext assignment_statement() throws RecognitionException {
		Assignment_statementContext _localctx = new Assignment_statementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_assignment_statement);
		int _la;
		try {
			setState(389);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				_localctx = new NoTypeAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(353);
				_la = _input.LA(1);
				if (_la==ME) {
					{
					setState(351); match(ME);
					setState(352); match(COLON);
					}
				}

				setState(355); ((NoTypeAssignmentContext)_localctx).name = match(ID);
				setState(356); match(EQUALITY);
				setState(357); ((NoTypeAssignmentContext)_localctx).rhs = expression(0);
				}
				break;

			case 2:
				_localctx = new ParentAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(358); match(PARENT);
				setState(359); match(COLON);
				setState(360); ((ParentAssignmentContext)_localctx).parent = qualified_name();
				setState(361); match(COLON);
				setState(362); ((ParentAssignmentContext)_localctx).name = match(ID);
				setState(363); match(EQUALITY);
				setState(364); ((ParentAssignmentContext)_localctx).rhs = expression(0);
				}
				break;

			case 3:
				_localctx = new ObjectAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(366); ((ObjectAssignmentContext)_localctx).object = match(ID);
				setState(371);
				switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(367); match(COLON);
					setState(368); match(PARENT);
					setState(369); match(COLON);
					setState(370); ((ObjectAssignmentContext)_localctx).parent = qualified_name();
					}
					break;
				}
				setState(373); match(COLON);
				setState(374); ((ObjectAssignmentContext)_localctx).name = match(ID);
				setState(375); match(EQUALITY);
				setState(376); ((ObjectAssignmentContext)_localctx).rhs = expression(0);
				}
				break;

			case 4:
				_localctx = new NormalAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(378);
				_la = _input.LA(1);
				if (_la==PUBLIC || _la==PRIVATE) {
					{
					setState(377); ((NormalAssignmentContext)_localctx).modifier = access_modifier();
					}
				}

				setState(381);
				_la = _input.LA(1);
				if (_la==CONSTANT) {
					{
					setState(380); match(CONSTANT);
					}
				}

				setState(383); ((NormalAssignmentContext)_localctx).type = assignment_declaration();
				setState(384); ((NormalAssignmentContext)_localctx).name = match(ID);
				setState(387);
				_la = _input.LA(1);
				if (_la==EQUALITY) {
					{
					setState(385); match(EQUALITY);
					setState(386); ((NormalAssignmentContext)_localctx).rhs = expression(0);
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
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode IF() { return getToken(QuorumParser.IF, 0); }
		public TerminalNode ELSE() { return getToken(QuorumParser.ELSE, 0); }
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode ELSE_IF(int i) {
			return getToken(QuorumParser.ELSE_IF, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public List<TerminalNode> ELSE_IF() { return getTokens(QuorumParser.ELSE_IF); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
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
			setState(391); match(IF);
			setState(392); expression(0);
			setState(393); block();
			setState(400);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELSE_IF) {
				{
				{
				setState(394); match(ELSE_IF);
				setState(395); expression(0);
				setState(396); block();
				}
				}
				setState(402);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(405);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(403); match(ELSE);
				setState(404); block();
				}
			}

			setState(407); match(END);
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
		public TerminalNode UNTIL() { return getToken(QuorumParser.UNTIL, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
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
			setState(409); match(REPEAT);
			setState(415);
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
				setState(410); expression(0);
				setState(411); match(TIMES);
				}
				}
				break;
			case UNTIL:
			case WHILE:
				{
				{
				setState(413);
				_la = _input.LA(1);
				if ( !(_la==UNTIL || _la==WHILE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(414); expression(0);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(417); block();
			setState(418); match(END);
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
		public quorum.Libraries.Language.Compile.Symbol.Type type;
		public ExpressionContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ExpressionContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
			this._p = ctx._p;
			this.type = ctx.type;
		}
	}
	public static class ParentFieldAccessContext extends ExpressionContext {
		public List<Qualified_nameContext> qualified_name() {
			return getRuleContexts(Qualified_nameContext.class);
		}
		public Qualified_nameContext qualified_name(int i) {
			return getRuleContext(Qualified_nameContext.class,i);
		}
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode COLON(int i) {
			return getToken(QuorumParser.COLON, i);
		}
		public TerminalNode PARENT() { return getToken(QuorumParser.PARENT, 0); }
		public List<TerminalNode> COLON() { return getTokens(QuorumParser.COLON); }
		public ParentFieldAccessContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterParentFieldAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitParentFieldAccess(this);
		}
	}
	public static class MultiplicationContext extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MODULO() { return getToken(QuorumParser.MODULO, 0); }
		public TerminalNode MULTIPLY() { return getToken(QuorumParser.MULTIPLY, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode DIVIDE() { return getToken(QuorumParser.DIVIDE, 0); }
		public MultiplicationContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterMultiplication(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitMultiplication(this);
		}
	}
	public static class InputContext extends ExpressionContext {
		public TerminalNode INPUT() { return getToken(QuorumParser.INPUT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public InputContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterInput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitInput(this);
		}
	}
	public static class MeFunctionCallContext extends ExpressionContext {
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode COLON(int i) {
			return getToken(QuorumParser.COLON, i);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public Function_expression_listContext function_expression_list() {
			return getRuleContext(Function_expression_listContext.class,0);
		}
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public TerminalNode ME() { return getToken(QuorumParser.ME, 0); }
		public List<TerminalNode> COLON() { return getTokens(QuorumParser.COLON); }
		public MeFunctionCallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterMeFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitMeFunctionCall(this);
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
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitMinus(this);
		}
	}
	public static class ObjectFunctionCallContext extends ExpressionContext {
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public Function_expression_listContext function_expression_list() {
			return getRuleContext(Function_expression_listContext.class,0);
		}
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public TerminalNode COLON() { return getToken(QuorumParser.COLON, 0); }
		public ObjectFunctionCallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterObjectFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitObjectFunctionCall(this);
		}
	}
	public static class BooleanContext extends ExpressionContext {
		public TerminalNode BOOLEAN() { return getToken(QuorumParser.BOOLEAN, 0); }
		public BooleanContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitBoolean(this);
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
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitNot(this);
		}
	}
	public static class MeContext extends ExpressionContext {
		public TerminalNode ME() { return getToken(QuorumParser.ME, 0); }
		public MeContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterMe(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitMe(this);
		}
	}
	public static class ParenthesisExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public ParenthesisExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterParenthesisExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitParenthesisExpression(this);
		}
	}
	public static class DecimalContext extends ExpressionContext {
		public TerminalNode DECIMAL() { return getToken(QuorumParser.DECIMAL, 0); }
		public DecimalContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterDecimal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitDecimal(this);
		}
	}
	public static class StringContext extends ExpressionContext {
		public TerminalNode STRING() { return getToken(QuorumParser.STRING, 0); }
		public StringContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitString(this);
		}
	}
	public static class CastContext extends ExpressionContext {
		public Assignment_declarationContext assignment_declaration() {
			return getRuleContext(Assignment_declarationContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(QuorumParser.COMMA, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public TerminalNode CAST() { return getToken(QuorumParser.CAST, 0); }
		public CastContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterCast(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitCast(this);
		}
	}
	public static class OrContext extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OR() { return getToken(QuorumParser.OR, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public OrContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitOr(this);
		}
	}
	public static class MeVariableAccessContext extends ExpressionContext {
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public TerminalNode ME() { return getToken(QuorumParser.ME, 0); }
		public TerminalNode COLON() { return getToken(QuorumParser.COLON, 0); }
		public MeVariableAccessContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterMeVariableAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitMeVariableAccess(this);
		}
	}
	public static class ParentFunctionCallContext extends ExpressionContext {
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode COLON(int i) {
			return getToken(QuorumParser.COLON, i);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public Function_expression_listContext function_expression_list() {
			return getRuleContext(Function_expression_listContext.class,0);
		}
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public TerminalNode PARENT() { return getToken(QuorumParser.PARENT, 0); }
		public List<TerminalNode> COLON() { return getTokens(QuorumParser.COLON); }
		public ParentFunctionCallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterParentFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitParentFunctionCall(this);
		}
	}
	public static class EqualsContext extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EQUALITY() { return getToken(QuorumParser.EQUALITY, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode NOTEQUALS() { return getToken(QuorumParser.NOTEQUALS, 0); }
		public EqualsContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterEquals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitEquals(this);
		}
	}
	public static class IntegerContext extends ExpressionContext {
		public TerminalNode INT() { return getToken(QuorumParser.INT, 0); }
		public IntegerContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitInteger(this);
		}
	}
	public static class GreaterContext extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode GREATER_EQUAL() { return getToken(QuorumParser.GREATER_EQUAL, 0); }
		public TerminalNode LESS_EQUAL() { return getToken(QuorumParser.LESS_EQUAL, 0); }
		public TerminalNode LESS() { return getToken(QuorumParser.LESS, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode GREATER() { return getToken(QuorumParser.GREATER, 0); }
		public GreaterContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterGreater(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitGreater(this);
		}
	}
	public static class AdditionContext extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MINUS() { return getToken(QuorumParser.MINUS, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode PLUS() { return getToken(QuorumParser.PLUS, 0); }
		public AdditionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterAddition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitAddition(this);
		}
	}
	public static class NullContext extends ExpressionContext {
		public TerminalNode NULL() { return getToken(QuorumParser.NULL, 0); }
		public NullContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterNull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitNull(this);
		}
	}
	public static class AndContext extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(QuorumParser.AND, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public AndContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitAnd(this);
		}
	}
	public static class VariableOrFieldAccessContext extends ExpressionContext {
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode COLON() { return getToken(QuorumParser.COLON, 0); }
		public VariableOrFieldAccessContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterVariableOrFieldAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitVariableOrFieldAccess(this);
		}
	}
	public static class InheritsContext extends ExpressionContext {
		public Class_typeContext class_type() {
			return getRuleContext(Class_typeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode INHERITS() { return getToken(QuorumParser.INHERITS, 0); }
		public InheritsContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterInherits(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitInherits(this);
		}
	}

	public final ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState, _p);
		ExpressionContext _prevctx = _localctx;
		int _startState = 56;
		enterRecursionRule(_localctx, RULE_expression);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(492);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				{
				_localctx = new MinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(421); match(MINUS);
				setState(422); expression(18);
				}
				break;

			case 2:
				{
				_localctx = new NotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(423); match(NOT);
				setState(424); expression(17);
				}
				break;

			case 3:
				{
				_localctx = new VariableOrFieldAccessContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(425); qualified_name();
				setState(428);
				switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
				case 1:
					{
					setState(426); match(COLON);
					setState(427); match(ID);
					}
					break;
				}
				}
				break;

			case 4:
				{
				_localctx = new ParentFieldAccessContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(430); qualified_name();
				setState(431); match(COLON);
				setState(432); match(PARENT);
				setState(433); match(COLON);
				setState(434); qualified_name();
				setState(435); match(COLON);
				setState(436); match(ID);
				}
				break;

			case 5:
				{
				_localctx = new ObjectFunctionCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(438); qualified_name();
				setState(441);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(439); match(COLON);
					setState(440); match(ID);
					}
				}

				setState(443); match(LEFT_PAREN);
				setState(444); function_expression_list();
				setState(445); match(RIGHT_PAREN);
				}
				break;

			case 6:
				{
				_localctx = new ParentFunctionCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(447); match(PARENT);
				setState(448); match(COLON);
				setState(449); qualified_name();
				setState(450); match(COLON);
				setState(451); match(ID);
				setState(452); match(LEFT_PAREN);
				setState(453); function_expression_list();
				setState(454); match(RIGHT_PAREN);
				}
				break;

			case 7:
				{
				_localctx = new MeVariableAccessContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(456); match(ME);
				setState(457); match(COLON);
				setState(458); qualified_name();
				}
				break;

			case 8:
				{
				_localctx = new MeFunctionCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(459); match(ME);
				setState(460); match(COLON);
				setState(461); qualified_name();
				setState(464);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(462); match(COLON);
					setState(463); match(ID);
					}
				}

				setState(466); match(LEFT_PAREN);
				setState(467); function_expression_list();
				setState(468); match(RIGHT_PAREN);
				}
				break;

			case 9:
				{
				_localctx = new CastContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(470); match(CAST);
				setState(471); match(LEFT_PAREN);
				setState(472); assignment_declaration();
				setState(473); match(COMMA);
				setState(474); expression(0);
				setState(475); match(RIGHT_PAREN);
				}
				break;

			case 10:
				{
				_localctx = new IntegerContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(477); match(INT);
				}
				break;

			case 11:
				{
				_localctx = new BooleanContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(478); match(BOOLEAN);
				}
				break;

			case 12:
				{
				_localctx = new DecimalContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(479); match(DECIMAL);
				}
				break;

			case 13:
				{
				_localctx = new StringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(480); match(STRING);
				}
				break;

			case 14:
				{
				_localctx = new NullContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(481); match(NULL);
				}
				break;

			case 15:
				{
				_localctx = new MeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(482); match(ME);
				}
				break;

			case 16:
				{
				_localctx = new InputContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(483); match(INPUT);
				setState(484); match(LEFT_PAREN);
				setState(485); expression(0);
				setState(486); match(RIGHT_PAREN);
				}
				break;

			case 17:
				{
				_localctx = new ParenthesisExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(488); match(LEFT_PAREN);
				setState(489); expression(0);
				setState(490); match(RIGHT_PAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(517);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(515);
					switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplicationContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(494);
						if (!(15 >= _localctx._p)) throw new FailedPredicateException(this, "15 >= $_p");
						setState(495);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLY) | (1L << DIVIDE) | (1L << MODULO))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(496); expression(16);
						}
						break;

					case 2:
						{
						_localctx = new AdditionContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(497);
						if (!(14 >= _localctx._p)) throw new FailedPredicateException(this, "14 >= $_p");
						setState(498);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(499); expression(15);
						}
						break;

					case 3:
						{
						_localctx = new GreaterContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(500);
						if (!(13 >= _localctx._p)) throw new FailedPredicateException(this, "13 >= $_p");
						setState(501);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER) | (1L << GREATER_EQUAL) | (1L << LESS) | (1L << LESS_EQUAL))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(502); expression(14);
						}
						break;

					case 4:
						{
						_localctx = new EqualsContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(503);
						if (!(11 >= _localctx._p)) throw new FailedPredicateException(this, "11 >= $_p");
						setState(504);
						_la = _input.LA(1);
						if ( !(_la==NOTEQUALS || _la==EQUALITY) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(505); expression(12);
						}
						break;

					case 5:
						{
						_localctx = new AndContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(506);
						if (!(10 >= _localctx._p)) throw new FailedPredicateException(this, "10 >= $_p");
						{
						setState(507); match(AND);
						}
						setState(508); expression(11);
						}
						break;

					case 6:
						{
						_localctx = new OrContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(509);
						if (!(9 >= _localctx._p)) throw new FailedPredicateException(this, "9 >= $_p");
						{
						setState(510); match(OR);
						}
						setState(511); expression(10);
						}
						break;

					case 7:
						{
						_localctx = new InheritsContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(512);
						if (!(12 >= _localctx._p)) throw new FailedPredicateException(this, "12 >= $_p");
						setState(513); match(INHERITS);
						setState(514); class_type();
						}
						break;
					}
					} 
				}
				setState(519);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
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
		enterRule(_localctx, 58, RULE_function_expression_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(528);
			_la = _input.LA(1);
			if (((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & ((1L << (ME - 6)) | (1L << (PARENT - 6)) | (1L << (CAST - 6)) | (1L << (INPUT - 6)) | (1L << (NULL - 6)) | (1L << (NOT - 6)) | (1L << (MINUS - 6)) | (1L << (LEFT_PAREN - 6)) | (1L << (BOOLEAN - 6)) | (1L << (INT - 6)) | (1L << (DECIMAL - 6)) | (1L << (ID - 6)) | (1L << (STRING - 6)))) != 0)) {
				{
				setState(520); expression(0);
				setState(525);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(521); match(COMMA);
					setState(522); expression(0);
					}
					}
					setState(527);
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
		case 28: return expression_sempred((ExpressionContext)_localctx, predIndex);
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3F\u0215\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\6"+
		"\2A\n\2\r\2\16\2B\3\2\6\2F\n\2\r\2\16\2G\3\2\3\2\3\2\3\2\6\2N\n\2\r\2"+
		"\16\2O\3\2\5\2S\n\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\5"+
		"\5a\n\5\3\5\5\5d\n\5\3\5\7\5g\n\5\f\5\16\5j\13\5\3\5\3\5\5\5n\n\5\3\6"+
		"\6\6q\n\6\r\6\16\6r\3\6\5\6v\n\6\3\6\6\6y\n\6\r\6\16\6z\5\6}\n\6\3\7\3"+
		"\7\3\7\5\7\u0082\n\7\3\7\3\7\3\7\5\7\u0087\n\7\7\7\u0089\n\7\f\7\16\7"+
		"\u008c\13\7\3\b\3\b\3\t\3\t\5\t\u0092\n\t\3\t\5\t\u0095\n\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00a4\n\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\7\13\u00ac\n\13\f\13\16\13\u00af\13\13\5\13\u00b1\n\13"+
		"\3\13\5\13\u00b4\n\13\3\13\3\13\5\13\u00b8\n\13\3\f\3\f\3\f\3\r\3\r\3"+
		"\r\7\r\u00c0\n\r\f\r\16\r\u00c3\13\r\3\16\7\16\u00c6\n\16\f\16\16\16\u00c9"+
		"\13\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00d4\n\17\3"+
		"\20\3\20\3\20\5\20\u00d9\n\20\3\20\3\20\3\20\3\20\7\20\u00df\n\20\f\20"+
		"\16\20\u00e2\13\20\5\20\u00e4\n\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\7\20\u00f1\n\20\f\20\16\20\u00f4\13\20\5\20\u00f6"+
		"\n\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00ff\n\20\3\20\3\20\3\20"+
		"\3\20\7\20\u0105\n\20\f\20\16\20\u0108\13\20\5\20\u010a\n\20\3\20\3\20"+
		"\5\20\u010e\n\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\6\22\u011b\n\22\r\22\16\22\u011c\3\22\3\22\5\22\u0121\n\22\3\22\3\22"+
		"\5\22\u0125\n\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\7\23\u012e\n\23\f"+
		"\23\16\23\u0131\13\23\5\23\u0133\n\23\3\24\3\24\3\24\3\25\3\25\3\25\3"+
		"\26\3\26\3\26\5\26\u013e\n\26\3\27\3\27\3\27\3\27\7\27\u0144\n\27\f\27"+
		"\16\27\u0147\13\27\3\27\3\27\3\30\3\30\3\30\3\30\7\30\u014f\n\30\f\30"+
		"\16\30\u0152\13\30\3\30\3\30\3\31\3\31\3\32\3\32\5\32\u015a\n\32\3\32"+
		"\3\32\3\32\3\32\5\32\u0160\n\32\3\33\3\33\5\33\u0164\n\33\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5"+
		"\33\u0176\n\33\3\33\3\33\3\33\3\33\3\33\5\33\u017d\n\33\3\33\5\33\u0180"+
		"\n\33\3\33\3\33\3\33\3\33\5\33\u0186\n\33\5\33\u0188\n\33\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\7\34\u0191\n\34\f\34\16\34\u0194\13\34\3\34\3"+
		"\34\5\34\u0198\n\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u01a2"+
		"\n\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u01af"+
		"\n\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u01bc"+
		"\n\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u01d3\n\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u01ef\n\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\7\36\u0206\n\36\f\36\16\36\u0209\13\36\3\37"+
		"\3\37\3\37\7\37\u020e\n\37\f\37\16\37\u0211\13\37\5\37\u0213\n\37\3\37"+
		"\2 \2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<\2\b"+
		"\3\2\n\13\4\2\t\t\30\30\3\2\64\66\3\2\62\63\3\2.\61\4\2**--\u0259\2R\3"+
		"\2\2\2\4W\3\2\2\2\6Z\3\2\2\2\bm\3\2\2\2\n|\3\2\2\2\f~\3\2\2\2\16\u008d"+
		"\3\2\2\2\20\u0094\3\2\2\2\22\u00a3\3\2\2\2\24\u00a5\3\2\2\2\26\u00b9\3"+
		"\2\2\2\30\u00bc\3\2\2\2\32\u00c7\3\2\2\2\34\u00d3\3\2\2\2\36\u010d\3\2"+
		"\2\2 \u010f\3\2\2\2\"\u0114\3\2\2\2$\u0128\3\2\2\2&\u0134\3\2\2\2(\u0137"+
		"\3\2\2\2*\u013a\3\2\2\2,\u013f\3\2\2\2.\u014a\3\2\2\2\60\u0155\3\2\2\2"+
		"\62\u015f\3\2\2\2\64\u0187\3\2\2\2\66\u0189\3\2\2\28\u019b\3\2\2\2:\u01ee"+
		"\3\2\2\2<\u0212\3\2\2\2>@\5\4\3\2?A\5\6\4\2@?\3\2\2\2AB\3\2\2\2B@\3\2"+
		"\2\2BC\3\2\2\2CS\3\2\2\2DF\5\6\4\2ED\3\2\2\2FG\3\2\2\2GE\3\2\2\2GH\3\2"+
		"\2\2HI\3\2\2\2IJ\5\4\3\2JS\3\2\2\2KS\5\4\3\2LN\5\6\4\2ML\3\2\2\2NO\3\2"+
		"\2\2OM\3\2\2\2OP\3\2\2\2PS\3\2\2\2QS\3\2\2\2R>\3\2\2\2RE\3\2\2\2RK\3\2"+
		"\2\2RM\3\2\2\2RQ\3\2\2\2ST\3\2\2\2TU\5\b\5\2UV\7\2\2\3V\3\3\2\2\2WX\7"+
		"\31\2\2XY\5\30\r\2Y\5\3\2\2\2Z[\7(\2\2[\\\5\30\r\2\\\7\3\2\2\2]^\7>\2"+
		"\2^`\7B\2\2_a\5,\27\2`_\3\2\2\2`a\3\2\2\2ac\3\2\2\2bd\5\f\7\2cb\3\2\2"+
		"\2cd\3\2\2\2dh\3\2\2\2eg\5\20\t\2fe\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2"+
		"\2\2ik\3\2\2\2jh\3\2\2\2kn\7=\2\2ln\5\n\6\2m]\3\2\2\2ml\3\2\2\2n\t\3\2"+
		"\2\2oq\5\34\17\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2\2s}\3\2\2\2tv\5"+
		"\16\b\2ut\3\2\2\2uv\3\2\2\2vw\3\2\2\2wy\5\22\n\2xu\3\2\2\2yz\3\2\2\2z"+
		"x\3\2\2\2z{\3\2\2\2{}\3\2\2\2|p\3\2\2\2|x\3\2\2\2}\13\3\2\2\2~\177\7\23"+
		"\2\2\177\u0081\5\30\r\2\u0080\u0082\5.\30\2\u0081\u0080\3\2\2\2\u0081"+
		"\u0082\3\2\2\2\u0082\u008a\3\2\2\2\u0083\u0084\7,\2\2\u0084\u0086\5\30"+
		"\r\2\u0085\u0087\5.\30\2\u0086\u0085\3\2\2\2\u0086\u0087\3\2\2\2\u0087"+
		"\u0089\3\2\2\2\u0088\u0083\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088\3\2"+
		"\2\2\u008a\u008b\3\2\2\2\u008b\r\3\2\2\2\u008c\u008a\3\2\2\2\u008d\u008e"+
		"\t\2\2\2\u008e\17\3\2\2\2\u008f\u0095\5\64\33\2\u0090\u0092\5\16\b\2\u0091"+
		"\u0090\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095\5\22"+
		"\n\2\u0094\u008f\3\2\2\2\u0094\u0091\3\2\2\2\u0095\21\3\2\2\2\u0096\u0097"+
		"\5\24\13\2\u0097\u0098\5\32\16\2\u0098\u0099\7=\2\2\u0099\u00a4\3\2\2"+
		"\2\u009a\u009b\7\21\2\2\u009b\u00a4\5\24\13\2\u009c\u009d\7\22\2\2\u009d"+
		"\u00a4\5\24\13\2\u009e\u009f\7\4\2\2\u009f\u00a0\7\5\2\2\u00a0\u00a1\5"+
		"\32\16\2\u00a1\u00a2\7=\2\2\u00a2\u00a4\3\2\2\2\u00a3\u0096\3\2\2\2\u00a3"+
		"\u009a\3\2\2\2\u00a3\u009c\3\2\2\2\u00a3\u009e\3\2\2\2\u00a4\23\3\2\2"+
		"\2\u00a5\u00a6\7\"\2\2\u00a6\u00b3\7B\2\2\u00a7\u00b0\79\2\2\u00a8\u00ad"+
		"\5\26\f\2\u00a9\u00aa\7,\2\2\u00aa\u00ac\5\26\f\2\u00ab\u00a9\3\2\2\2"+
		"\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b1"+
		"\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00a8\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1"+
		"\u00b2\3\2\2\2\u00b2\u00b4\7:\2\2\u00b3\u00a7\3\2\2\2\u00b3\u00b4\3\2"+
		"\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b6\7\35\2\2\u00b6\u00b8\5\62\32\2\u00b7"+
		"\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\25\3\2\2\2\u00b9\u00ba\5\62\32"+
		"\2\u00ba\u00bb\7B\2\2\u00bb\27\3\2\2\2\u00bc\u00c1\7B\2\2\u00bd\u00be"+
		"\7+\2\2\u00be\u00c0\7B\2\2\u00bf\u00bd\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1"+
		"\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\31\3\2\2\2\u00c3\u00c1\3\2\2"+
		"\2\u00c4\u00c6\5\34\17\2\u00c5\u00c4\3\2\2\2\u00c6\u00c9\3\2\2\2\u00c7"+
		"\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\33\3\2\2\2\u00c9\u00c7\3\2\2"+
		"\2\u00ca\u00d4\5\36\20\2\u00cb\u00d4\5\66\34\2\u00cc\u00d4\5\64\33\2\u00cd"+
		"\u00d4\58\35\2\u00ce\u00d4\5*\26\2\u00cf\u00d4\5&\24\2\u00d0\u00d4\5("+
		"\25\2\u00d1\u00d4\5\"\22\2\u00d2\u00d4\5 \21\2\u00d3\u00ca\3\2\2\2\u00d3"+
		"\u00cb\3\2\2\2\u00d3\u00cc\3\2\2\2\u00d3\u00cd\3\2\2\2\u00d3\u00ce\3\2"+
		"\2\2\u00d3\u00cf\3\2\2\2\u00d3\u00d0\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3"+
		"\u00d2\3\2\2\2\u00d4\35\3\2\2\2\u00d5\u00d8\5\30\r\2\u00d6\u00d7\7#\2"+
		"\2\u00d7\u00d9\7B\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00da"+
		"\3\2\2\2\u00da\u00e3\79\2\2\u00db\u00e0\5:\36\2\u00dc\u00dd\7,\2\2\u00dd"+
		"\u00df\5:\36\2\u00de\u00dc\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2"+
		"\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e4\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3"+
		"\u00db\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e6\7:"+
		"\2\2\u00e6\u010e\3\2\2\2\u00e7\u00e8\7\20\2\2\u00e8\u00e9\7#\2\2\u00e9"+
		"\u00ea\5\30\r\2\u00ea\u00eb\7#\2\2\u00eb\u00ec\7B\2\2\u00ec\u00f5\79\2"+
		"\2\u00ed\u00f2\5:\36\2\u00ee\u00ef\7,\2\2\u00ef\u00f1\5:\36\2\u00f0\u00ee"+
		"\3\2\2\2\u00f1\u00f4\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3"+
		"\u00f6\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f5\u00ed\3\2\2\2\u00f5\u00f6\3\2"+
		"\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f8\7:\2\2\u00f8\u010e\3\2\2\2\u00f9"+
		"\u00fa\7\b\2\2\u00fa\u00fb\7#\2\2\u00fb\u00fe\5\30\r\2\u00fc\u00fd\7#"+
		"\2\2\u00fd\u00ff\7B\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff"+
		"\u0100\3\2\2\2\u0100\u0109\79\2\2\u0101\u0106\5:\36\2\u0102\u0103\7,\2"+
		"\2\u0103\u0105\5:\36\2\u0104\u0102\3\2\2\2\u0105\u0108\3\2\2\2\u0106\u0104"+
		"\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u010a\3\2\2\2\u0108\u0106\3\2\2\2\u0109"+
		"\u0101\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010c\7:"+
		"\2\2\u010c\u010e\3\2\2\2\u010d\u00d5\3\2\2\2\u010d\u00e7\3\2\2\2\u010d"+
		"\u00f9\3\2\2\2\u010e\37\3\2\2\2\u010f\u0110\7\f\2\2\u0110\u0111\79\2\2"+
		"\u0111\u0112\5:\36\2\u0112\u0113\7:\2\2\u0113!\3\2\2\2\u0114\u0115\7\17"+
		"\2\2\u0115\u0124\5\32\16\2\u0116\u0117\7\r\2\2\u0117\u0118\5$\23\2\u0118"+
		"\u0119\5\32\16\2\u0119\u011b\3\2\2\2\u011a\u0116\3\2\2\2\u011b\u011c\3"+
		"\2\2\2\u011c\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u0120\3\2\2\2\u011e"+
		"\u011f\7\16\2\2\u011f\u0121\5\32\16\2\u0120\u011e\3\2\2\2\u0120\u0121"+
		"\3\2\2\2\u0121\u0125\3\2\2\2\u0122\u0123\7\16\2\2\u0123\u0125\5\32\16"+
		"\2\u0124\u011a\3\2\2\2\u0124\u0122\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0127"+
		"\7=\2\2\u0127#\3\2\2\2\u0128\u0132\7B\2\2\u0129\u012a\7\23\2\2\u012a\u012f"+
		"\5\30\r\2\u012b\u012c\7 \2\2\u012c\u012e\5\30\r\2\u012d\u012b\3\2\2\2"+
		"\u012e\u0131\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0133"+
		"\3\2\2\2\u0131\u012f\3\2\2\2\u0132\u0129\3\2\2\2\u0132\u0133\3\2\2\2\u0133"+
		"%\3\2\2\2\u0134\u0135\7\3\2\2\u0135\u0136\5:\36\2\u0136\'\3\2\2\2\u0137"+
		"\u0138\7\26\2\2\u0138\u0139\5:\36\2\u0139)\3\2\2\2\u013a\u013d\7\36\2"+
		"\2\u013b\u013e\5:\36\2\u013c\u013e\7\27\2\2\u013d\u013b\3\2\2\2\u013d"+
		"\u013c\3\2\2\2\u013e+\3\2\2\2\u013f\u0140\7\60\2\2\u0140\u0145\7B\2\2"+
		"\u0141\u0142\7,\2\2\u0142\u0144\7B\2\2\u0143\u0141\3\2\2\2\u0144\u0147"+
		"\3\2\2\2\u0145\u0143\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0148\3\2\2\2\u0147"+
		"\u0145\3\2\2\2\u0148\u0149\7.\2\2\u0149-\3\2\2\2\u014a\u014b\7\60\2\2"+
		"\u014b\u0150\5\62\32\2\u014c\u014d\7,\2\2\u014d\u014f\5\62\32\2\u014e"+
		"\u014c\3\2\2\2\u014f\u0152\3\2\2\2\u0150\u014e\3\2\2\2\u0150\u0151\3\2"+
		"\2\2\u0151\u0153\3\2\2\2\u0152\u0150\3\2\2\2\u0153\u0154\7.\2\2\u0154"+
		"/\3\2\2\2\u0155\u0156\5\30\r\2\u0156\61\3\2\2\2\u0157\u0159\5\30\r\2\u0158"+
		"\u015a\5.\30\2\u0159\u0158\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u0160\3\2"+
		"\2\2\u015b\u0160\7$\2\2\u015c\u0160\7%\2\2\u015d\u0160\7&\2\2\u015e\u0160"+
		"\7\'\2\2\u015f\u0157\3\2\2\2\u015f\u015b\3\2\2\2\u015f\u015c\3\2\2\2\u015f"+
		"\u015d\3\2\2\2\u015f\u015e\3\2\2\2\u0160\63\3\2\2\2\u0161\u0162\7\b\2"+
		"\2\u0162\u0164\7#\2\2\u0163\u0161\3\2\2\2\u0163\u0164\3\2\2\2\u0164\u0165"+
		"\3\2\2\2\u0165\u0166\7B\2\2\u0166\u0167\7-\2\2\u0167\u0188\5:\36\2\u0168"+
		"\u0169\7\20\2\2\u0169\u016a\7#\2\2\u016a\u016b\5\30\r\2\u016b\u016c\7"+
		"#\2\2\u016c\u016d\7B\2\2\u016d\u016e\7-\2\2\u016e\u016f\5:\36\2\u016f"+
		"\u0188\3\2\2\2\u0170\u0175\7B\2\2\u0171\u0172\7#\2\2\u0172\u0173\7\20"+
		"\2\2\u0173\u0174\7#\2\2\u0174\u0176\5\30\r\2\u0175\u0171\3\2\2\2\u0175"+
		"\u0176\3\2\2\2\u0176\u0177\3\2\2\2\u0177\u0178\7#\2\2\u0178\u0179\7B\2"+
		"\2\u0179\u017a\7-\2\2\u017a\u0188\5:\36\2\u017b\u017d\5\16\b\2\u017c\u017b"+
		"\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017f\3\2\2\2\u017e\u0180\7\6\2\2\u017f"+
		"\u017e\3\2\2\2\u017f\u0180\3\2\2\2\u0180\u0181\3\2\2\2\u0181\u0182\5\62"+
		"\32\2\u0182\u0185\7B\2\2\u0183\u0184\7-\2\2\u0184\u0186\5:\36\2\u0185"+
		"\u0183\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u0188\3\2\2\2\u0187\u0163\3\2"+
		"\2\2\u0187\u0168\3\2\2\2\u0187\u0170\3\2\2\2\u0187\u017c\3\2\2\2\u0188"+
		"\65\3\2\2\2\u0189\u018a\7<\2\2\u018a\u018b\5:\36\2\u018b\u0192\5\32\16"+
		"\2\u018c\u018d\7\7\2\2\u018d\u018e\5:\36\2\u018e\u018f\5\32\16\2\u018f"+
		"\u0191\3\2\2\2\u0190\u018c\3\2\2\2\u0191\u0194\3\2\2\2\u0192\u0190\3\2"+
		"\2\2\u0192\u0193\3\2\2\2\u0193\u0197\3\2\2\2\u0194\u0192\3\2\2\2\u0195"+
		"\u0196\7\34\2\2\u0196\u0198\5\32\16\2\u0197\u0195\3\2\2\2\u0197\u0198"+
		"\3\2\2\2\u0198\u0199\3\2\2\2\u0199\u019a\7=\2\2\u019a\67\3\2\2\2\u019b"+
		"\u01a1\7\33\2\2\u019c\u019d\5:\36\2\u019d\u019e\7\32\2\2\u019e\u01a2\3"+
		"\2\2\2\u019f\u01a0\t\3\2\2\u01a0\u01a2\5:\36\2\u01a1\u019c\3\2\2\2\u01a1"+
		"\u019f\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3\u01a4\5\32\16\2\u01a4\u01a5\7"+
		"=\2\2\u01a59\3\2\2\2\u01a6\u01a7\b\36\1\2\u01a7\u01a8\7\63\2\2\u01a8\u01ef"+
		"\5:\36\2\u01a9\u01aa\7)\2\2\u01aa\u01ef\5:\36\2\u01ab\u01ae\5\30\r\2\u01ac"+
		"\u01ad\7#\2\2\u01ad\u01af\7B\2\2\u01ae\u01ac\3\2\2\2\u01ae\u01af\3\2\2"+
		"\2\u01af\u01ef\3\2\2\2\u01b0\u01b1\5\30\r\2\u01b1\u01b2\7#\2\2\u01b2\u01b3"+
		"\7\20\2\2\u01b3\u01b4\7#\2\2\u01b4\u01b5\5\30\r\2\u01b5\u01b6\7#\2\2\u01b6"+
		"\u01b7\7B\2\2\u01b7\u01ef\3\2\2\2\u01b8\u01bb\5\30\r\2\u01b9\u01ba\7#"+
		"\2\2\u01ba\u01bc\7B\2\2\u01bb\u01b9\3\2\2\2\u01bb\u01bc\3\2\2\2\u01bc"+
		"\u01bd\3\2\2\2\u01bd\u01be\79\2\2\u01be\u01bf\5<\37\2\u01bf\u01c0\7:\2"+
		"\2\u01c0\u01ef\3\2\2\2\u01c1\u01c2\7\20\2\2\u01c2\u01c3\7#\2\2\u01c3\u01c4"+
		"\5\30\r\2\u01c4\u01c5\7#\2\2\u01c5\u01c6\7B\2\2\u01c6\u01c7\79\2\2\u01c7"+
		"\u01c8\5<\37\2\u01c8\u01c9\7:\2\2\u01c9\u01ef\3\2\2\2\u01ca\u01cb\7\b"+
		"\2\2\u01cb\u01cc\7#\2\2\u01cc\u01ef\5\30\r\2\u01cd\u01ce\7\b\2\2\u01ce"+
		"\u01cf\7#\2\2\u01cf\u01d2\5\30\r\2\u01d0\u01d1\7#\2\2\u01d1\u01d3\7B\2"+
		"\2\u01d2\u01d0\3\2\2\2\u01d2\u01d3\3\2\2\2\u01d3\u01d4\3\2\2\2\u01d4\u01d5"+
		"\79\2\2\u01d5\u01d6\5<\37\2\u01d6\u01d7\7:\2\2\u01d7\u01ef\3\2\2\2\u01d8"+
		"\u01d9\7\24\2\2\u01d9\u01da\79\2\2\u01da\u01db\5\62\32\2\u01db\u01dc\7"+
		",\2\2\u01dc\u01dd\5:\36\2\u01dd\u01de\7:\2\2\u01de\u01ef\3\2\2\2\u01df"+
		"\u01ef\7@\2\2\u01e0\u01ef\7?\2\2\u01e1\u01ef\7A\2\2\u01e2\u01ef\7C\2\2"+
		"\u01e3\u01ef\7!\2\2\u01e4\u01ef\7\b\2\2\u01e5\u01e6\7\25\2\2\u01e6\u01e7"+
		"\79\2\2\u01e7\u01e8\5:\36\2\u01e8\u01e9\7:\2\2\u01e9\u01ef\3\2\2\2\u01ea"+
		"\u01eb\79\2\2\u01eb\u01ec\5:\36\2\u01ec\u01ed\7:\2\2\u01ed\u01ef\3\2\2"+
		"\2\u01ee\u01a6\3\2\2\2\u01ee\u01a9\3\2\2\2\u01ee\u01ab\3\2\2\2\u01ee\u01b0"+
		"\3\2\2\2\u01ee\u01b8\3\2\2\2\u01ee\u01c1\3\2\2\2\u01ee\u01ca\3\2\2\2\u01ee"+
		"\u01cd\3\2\2\2\u01ee\u01d8\3\2\2\2\u01ee\u01df\3\2\2\2\u01ee\u01e0\3\2"+
		"\2\2\u01ee\u01e1\3\2\2\2\u01ee\u01e2\3\2\2\2\u01ee\u01e3\3\2\2\2\u01ee"+
		"\u01e4\3\2\2\2\u01ee\u01e5\3\2\2\2\u01ee\u01ea\3\2\2\2\u01ef\u0207\3\2"+
		"\2\2\u01f0\u01f1\6\36\2\3\u01f1\u01f2\t\4\2\2\u01f2\u0206\5:\36\2\u01f3"+
		"\u01f4\6\36\3\3\u01f4\u01f5\t\5\2\2\u01f5\u0206\5:\36\2\u01f6\u01f7\6"+
		"\36\4\3\u01f7\u01f8\t\6\2\2\u01f8\u0206\5:\36\2\u01f9\u01fa\6\36\5\3\u01fa"+
		"\u01fb\t\7\2\2\u01fb\u0206\5:\36\2\u01fc\u01fd\6\36\6\3\u01fd\u01fe\7"+
		"\37\2\2\u01fe\u0206\5:\36\2\u01ff\u0200\6\36\7\3\u0200\u0201\7 \2\2\u0201"+
		"\u0206\5:\36\2\u0202\u0203\6\36\b\3\u0203\u0204\7\23\2\2\u0204\u0206\5"+
		"\60\31\2\u0205\u01f0\3\2\2\2\u0205\u01f3\3\2\2\2\u0205\u01f6\3\2\2\2\u0205"+
		"\u01f9\3\2\2\2\u0205\u01fc\3\2\2\2\u0205\u01ff\3\2\2\2\u0205\u0202\3\2"+
		"\2\2\u0206\u0209\3\2\2\2\u0207\u0205\3\2\2\2\u0207\u0208\3\2\2\2\u0208"+
		";\3\2\2\2\u0209\u0207\3\2\2\2\u020a\u020f\5:\36\2\u020b\u020c\7,\2\2\u020c"+
		"\u020e\5:\36\2\u020d\u020b\3\2\2\2\u020e\u0211\3\2\2\2\u020f\u020d\3\2"+
		"\2\2\u020f\u0210\3\2\2\2\u0210\u0213\3\2\2\2\u0211\u020f\3\2\2\2\u0212"+
		"\u020a\3\2\2\2\u0212\u0213\3\2\2\2\u0213=\3\2\2\2?BGOR`chmruz|\u0081\u0086"+
		"\u008a\u0091\u0094\u00a3\u00ad\u00b0\u00b3\u00b7\u00c1\u00c7\u00d3\u00d8"+
		"\u00e0\u00e3\u00f2\u00f5\u00fe\u0106\u0109\u010d\u011c\u0120\u0124\u012f"+
		"\u0132\u013d\u0145\u0150\u0159\u015f\u0163\u0175\u017c\u017f\u0185\u0187"+
		"\u0192\u0197\u01a1\u01ae\u01bb\u01d2\u01ee\u0205\u0207\u020f\u0212";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}