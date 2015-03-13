// Generated from /Users/stefika/Repositories/quorum-language/plugins/ParserPlugin/src/plugins/quorum/Libraries/Language/Compile/Quorum.g4 by ANTLR 4.2.2
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
	public static final String[] ruleNames = {
		"start", "package_rule", "reference", "class_declaration", "no_class_stmnts", 
		"inherit_stmnts", "inherit_stmt", "access_modifier", "class_stmnts", "method_declaration", 
		"method_shared", "formal_parameter", "qualified_name", "block", "statement", 
		"solo_method_call", "solo_method_required_method_part", "alert_statement", 
		"check_statement", "detect_statement", "always_statement", "print_statement", 
		"speak_statement", "return_statement", "generic_declaration", "generic_statement", 
		"class_type", "assignment_declaration", "assignment_statement", "if_statement", 
		"elseif_statement", "else_statement", "loop_statement", "initial_parent_action_call", 
		"action_call", "expression", "function_expression_list"
	};

	@Override
	public String getGrammarFileName() { return "Quorum.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

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
		public TerminalNode EOF() { return getToken(QuorumParser.EOF, 0); }
		public Class_declarationContext class_declaration() {
			return getRuleContext(Class_declarationContext.class,0);
		}
		public Package_ruleContext package_rule() {
			return getRuleContext(Package_ruleContext.class,0);
		}
		public ReferenceContext reference(int i) {
			return getRuleContext(ReferenceContext.class,i);
		}
		public List<ReferenceContext> reference() {
			return getRuleContexts(ReferenceContext.class);
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
			setState(94);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(74); package_rule();
				setState(76); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(75); reference();
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
					setState(80); reference();
					}
					}
					setState(83); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==USE );
				setState(85); package_rule();
				}
				break;

			case 3:
				{
				setState(87); package_rule();
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
					setState(88); reference();
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
			setState(96); class_declaration();
			setState(97); match(EOF);
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
			setState(99); match(PACKAGE_NAME);
			setState(100); ((Package_ruleContext)_localctx).name = qualified_name();
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
			setState(102); match(USE);
			setState(103); ((ReferenceContext)_localctx).name = qualified_name();
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
			setState(121);
			switch (_input.LA(1)) {
			case CLASS:
				_localctx = new FullClassDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(105); match(CLASS);
				setState(106); match(ID);
				setState(108);
				_la = _input.LA(1);
				if (_la==LESS) {
					{
					setState(107); generic_declaration();
					}
				}

				setState(111);
				_la = _input.LA(1);
				if (_la==INHERITS) {
					{
					setState(110); inherit_stmnts();
					}
				}

				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 2)) & ~0x3f) == 0 && ((1L << (_la - 2)) & ((1L << (ON - 2)) | (1L << (CONSTANT - 2)) | (1L << (ME - 2)) | (1L << (PUBLIC - 2)) | (1L << (PRIVATE - 2)) | (1L << (PARENT - 2)) | (1L << (BLUEPRINT - 2)) | (1L << (NATIVE - 2)) | (1L << (ACTION - 2)) | (1L << (INTEGER_KEYWORD - 2)) | (1L << (NUMBER_KEYWORD - 2)) | (1L << (TEXT - 2)) | (1L << (BOOLEAN_KEYWORD - 2)) | (1L << (ID - 2)))) != 0)) {
					{
					{
					setState(113); class_stmnts();
					}
					}
					setState(118);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(119); match(END);
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
				setState(120); no_class_stmnts();
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
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterNoActionsNoClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitNoActionsNoClass(this);
		}
	}
	public static class ActionsNoClassContext extends No_class_stmntsContext {
		public Access_modifierContext access_modifier(int i) {
			return getRuleContext(Access_modifierContext.class,i);
		}
		public Method_declarationContext method_declaration(int i) {
			return getRuleContext(Method_declarationContext.class,i);
		}
		public List<Method_declarationContext> method_declaration() {
			return getRuleContexts(Method_declarationContext.class);
		}
		public List<Access_modifierContext> access_modifier() {
			return getRuleContexts(Access_modifierContext.class);
		}
		public ActionsNoClassContext(No_class_stmntsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterActionsNoClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitActionsNoClass(this);
		}
	}

	public final No_class_stmntsContext no_class_stmnts() throws RecognitionException {
		No_class_stmntsContext _localctx = new No_class_stmntsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_no_class_stmnts);
		int _la;
		try {
			setState(136);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				_localctx = new NoActionsNoClassContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(124); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(123); statement();
					}
					}
					setState(126); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((((_la - 1)) & ~0x3f) == 0 && ((1L << (_la - 1)) & ((1L << (OUTPUT - 1)) | (1L << (CONSTANT - 1)) | (1L << (ME - 1)) | (1L << (PUBLIC - 1)) | (1L << (PRIVATE - 1)) | (1L << (ALERT - 1)) | (1L << (CHECK - 1)) | (1L << (PARENT - 1)) | (1L << (SAY - 1)) | (1L << (REPEAT - 1)) | (1L << (RETURN - 1)) | (1L << (INTEGER_KEYWORD - 1)) | (1L << (NUMBER_KEYWORD - 1)) | (1L << (TEXT - 1)) | (1L << (BOOLEAN_KEYWORD - 1)) | (1L << (IF - 1)) | (1L << (ID - 1)))) != 0) );
				}
				break;

			case 2:
				_localctx = new ActionsNoClassContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(132); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(129);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						setState(128); access_modifier();
						}
						break;
					}
					setState(131); method_declaration();
					}
					}
					setState(134); 
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
		public List<TerminalNode> COMMA() { return getTokens(QuorumParser.COMMA); }
		public List<Inherit_stmtContext> inherit_stmt() {
			return getRuleContexts(Inherit_stmtContext.class);
		}
		public TerminalNode INHERITS() { return getToken(QuorumParser.INHERITS, 0); }
		public Inherit_stmtContext inherit_stmt(int i) {
			return getRuleContext(Inherit_stmtContext.class,i);
		}
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
			setState(138); match(INHERITS);
			setState(139); inherit_stmt();
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(140); match(COMMA);
				setState(141); inherit_stmt();
				}
				}
				setState(146);
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
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterInherit_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitInherit_stmt(this);
		}
	}

	public final Inherit_stmtContext inherit_stmt() throws RecognitionException {
		Inherit_stmtContext _localctx = new Inherit_stmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_inherit_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147); qualified_name();
			setState(149);
			_la = _input.LA(1);
			if (_la==LESS) {
				{
				setState(148); generic_statement();
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
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterAccess_modifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitAccess_modifier(this);
		}
	}

	public final Access_modifierContext access_modifier() throws RecognitionException {
		Access_modifierContext _localctx = new Access_modifierContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_access_modifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
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
		enterRule(_localctx, 16, RULE_class_stmnts);
		try {
			setState(155);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(153); assignment_statement();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(154); method_declaration();
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
		public Access_modifierContext modifier;
		public TerminalNode NATIVE() { return getToken(QuorumParser.NATIVE, 0); }
		public Access_modifierContext access_modifier() {
			return getRuleContext(Access_modifierContext.class,0);
		}
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
		public Access_modifierContext modifier;
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Access_modifierContext access_modifier() {
			return getRuleContext(Access_modifierContext.class,0);
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
		public Access_modifierContext modifier;
		public TerminalNode BLUEPRINT() { return getToken(QuorumParser.BLUEPRINT, 0); }
		public Access_modifierContext access_modifier() {
			return getRuleContext(Access_modifierContext.class,0);
		}
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
		enterRule(_localctx, 18, RULE_method_declaration);
		int _la;
		try {
			setState(179);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				_localctx = new ActionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				_la = _input.LA(1);
				if (_la==PUBLIC || _la==PRIVATE) {
					{
					setState(157); ((ActionContext)_localctx).modifier = access_modifier();
					}
				}

				setState(160); method_shared();
				setState(161); block();
				setState(162); match(END);
				}
				break;

			case 2:
				_localctx = new BlueprintActionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(165);
				_la = _input.LA(1);
				if (_la==PUBLIC || _la==PRIVATE) {
					{
					setState(164); ((BlueprintActionContext)_localctx).modifier = access_modifier();
					}
				}

				setState(167); match(BLUEPRINT);
				setState(168); method_shared();
				}
				break;

			case 3:
				_localctx = new NativeActionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(170);
				_la = _input.LA(1);
				if (_la==PUBLIC || _la==PRIVATE) {
					{
					setState(169); ((NativeActionContext)_localctx).modifier = access_modifier();
					}
				}

				setState(172); match(NATIVE);
				setState(173); method_shared();
				}
				break;

			case 4:
				_localctx = new ConstructorContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(174); match(ON);
				setState(175); match(CREATE);
				setState(176); block();
				setState(177); match(END);
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
		enterRule(_localctx, 20, RULE_method_shared);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181); match(ACTION);
			setState(182); match(ID);
			setState(195);
			_la = _input.LA(1);
			if (_la==LEFT_PAREN) {
				{
				setState(183); match(LEFT_PAREN);
				setState(192);
				_la = _input.LA(1);
				if (((((_la - 34)) & ~0x3f) == 0 && ((1L << (_la - 34)) & ((1L << (INTEGER_KEYWORD - 34)) | (1L << (NUMBER_KEYWORD - 34)) | (1L << (TEXT - 34)) | (1L << (BOOLEAN_KEYWORD - 34)) | (1L << (ID - 34)))) != 0)) {
					{
					setState(184); formal_parameter();
					setState(189);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(185); match(COMMA);
						setState(186); formal_parameter();
						}
						}
						setState(191);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(194); match(RIGHT_PAREN);
				}
			}

			setState(199);
			_la = _input.LA(1);
			if (_la==RETURNS) {
				{
				setState(197); match(RETURNS);
				setState(198); ((Method_sharedContext)_localctx).return_type = assignment_declaration();
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
		enterRule(_localctx, 22, RULE_formal_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201); assignment_declaration();
			setState(202); match(ID);
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
		enterRule(_localctx, 24, RULE_qualified_name);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(204); ((Qualified_nameContext)_localctx).ID = match(ID);
			((Qualified_nameContext)_localctx).ids.add(((Qualified_nameContext)_localctx).ID);
			setState(209);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(205); match(PERIOD);
					setState(206); ((Qualified_nameContext)_localctx).ID = match(ID);
					((Qualified_nameContext)_localctx).ids.add(((Qualified_nameContext)_localctx).ID);
					}
					} 
				}
				setState(211);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
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
		enterRule(_localctx, 26, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 1)) & ~0x3f) == 0 && ((1L << (_la - 1)) & ((1L << (OUTPUT - 1)) | (1L << (CONSTANT - 1)) | (1L << (ME - 1)) | (1L << (PUBLIC - 1)) | (1L << (PRIVATE - 1)) | (1L << (ALERT - 1)) | (1L << (CHECK - 1)) | (1L << (PARENT - 1)) | (1L << (SAY - 1)) | (1L << (REPEAT - 1)) | (1L << (RETURN - 1)) | (1L << (INTEGER_KEYWORD - 1)) | (1L << (NUMBER_KEYWORD - 1)) | (1L << (TEXT - 1)) | (1L << (BOOLEAN_KEYWORD - 1)) | (1L << (IF - 1)) | (1L << (ID - 1)))) != 0)) {
				{
				{
				setState(212); statement();
				}
				}
				setState(217);
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
		enterRule(_localctx, 28, RULE_statement);
		try {
			setState(227);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(218); solo_method_call();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(219); if_statement();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(220); assignment_statement();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(221); loop_statement();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(222); return_statement();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(223); print_statement();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(224); speak_statement();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(225); check_statement();
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(226); alert_statement();
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
	public static class ParentVariableSoloFunctionCallContext extends Solo_method_callContext {
		public Token fieldName;
		public Qualified_nameContext parent;
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public List<Action_callContext> action_call() {
			return getRuleContexts(Action_callContext.class);
		}
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode COLON(int i) {
			return getToken(QuorumParser.COLON, i);
		}
		public TerminalNode ME() { return getToken(QuorumParser.ME, 0); }
		public Initial_parent_action_callContext initial_parent_action_call() {
			return getRuleContext(Initial_parent_action_callContext.class,0);
		}
		public TerminalNode PARENT() { return getToken(QuorumParser.PARENT, 0); }
		public List<TerminalNode> COLON() { return getTokens(QuorumParser.COLON); }
		public Action_callContext action_call(int i) {
			return getRuleContext(Action_callContext.class,i);
		}
		public ParentVariableSoloFunctionCallContext(Solo_method_callContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterParentVariableSoloFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitParentVariableSoloFunctionCall(this);
		}
	}
	public static class VariableSoloFunctionCallContext extends Solo_method_callContext {
		public Token object;
		public List<Action_callContext> action_call() {
			return getRuleContexts(Action_callContext.class);
		}
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode COLON(int i) {
			return getToken(QuorumParser.COLON, i);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public TerminalNode ME() { return getToken(QuorumParser.ME, 0); }
		public List<TerminalNode> COLON() { return getTokens(QuorumParser.COLON); }
		public Action_callContext action_call(int i) {
			return getRuleContext(Action_callContext.class,i);
		}
		public Solo_method_required_method_partContext solo_method_required_method_part() {
			return getRuleContext(Solo_method_required_method_partContext.class,0);
		}
		public VariableSoloFunctionCallContext(Solo_method_callContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterVariableSoloFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitVariableSoloFunctionCall(this);
		}
	}

	public final Solo_method_callContext solo_method_call() throws RecognitionException {
		Solo_method_callContext _localctx = new Solo_method_callContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_solo_method_call);
		int _la;
		try {
			setState(266);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				_localctx = new VariableSoloFunctionCallContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(231);
				_la = _input.LA(1);
				if (_la==ME) {
					{
					setState(229); match(ME);
					setState(230); match(COLON);
					}
				}

				setState(235);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(233); ((VariableSoloFunctionCallContext)_localctx).object = match(ID);
					setState(234); match(COLON);
					}
					break;
				}
				setState(237); solo_method_required_method_part();
				setState(238); match(RIGHT_PAREN);
				setState(243);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COLON) {
					{
					{
					setState(239); match(COLON);
					setState(240); action_call();
					}
					}
					setState(245);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;

			case 2:
				_localctx = new ParentVariableSoloFunctionCallContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(252);
				_la = _input.LA(1);
				if (_la==ME || _la==ID) {
					{
					setState(248);
					_la = _input.LA(1);
					if (_la==ME) {
						{
						setState(246); match(ME);
						setState(247); match(COLON);
						}
					}

					{
					setState(250); ((ParentVariableSoloFunctionCallContext)_localctx).fieldName = match(ID);
					setState(251); match(COLON);
					}
					}
				}

				setState(254); match(PARENT);
				setState(255); match(COLON);
				setState(256); ((ParentVariableSoloFunctionCallContext)_localctx).parent = qualified_name();
				setState(257); match(COLON);
				setState(258); initial_parent_action_call();
				setState(263);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COLON) {
					{
					{
					setState(259); match(COLON);
					{
					setState(260); action_call();
					}
					}
					}
					setState(265);
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
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public Function_expression_listContext function_expression_list() {
			return getRuleContext(Function_expression_listContext.class,0);
		}
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public Solo_method_required_method_partContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_solo_method_required_method_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterSolo_method_required_method_part(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitSolo_method_required_method_part(this);
		}
	}

	public final Solo_method_required_method_partContext solo_method_required_method_part() throws RecognitionException {
		Solo_method_required_method_partContext _localctx = new Solo_method_required_method_partContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_solo_method_required_method_part);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268); ((Solo_method_required_method_partContext)_localctx).var = match(ID);
			setState(269); match(LEFT_PAREN);
			setState(270); function_expression_list();
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
		enterRule(_localctx, 34, RULE_alert_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272); match(ALERT);
			setState(273); match(LEFT_PAREN);
			setState(274); expression(0);
			setState(275); match(RIGHT_PAREN);
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
		public List<Detect_statementContext> detect_statement() {
			return getRuleContexts(Detect_statementContext.class);
		}
		public Always_statementContext always_statement() {
			return getRuleContext(Always_statementContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Detect_statementContext detect_statement(int i) {
			return getRuleContext(Detect_statementContext.class,i);
		}
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
		enterRule(_localctx, 36, RULE_check_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277); match(CHECK);
			setState(278); block();
			setState(288);
			switch (_input.LA(1)) {
			case DETECT:
				{
				setState(280); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(279); detect_statement();
					}
					}
					setState(282); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DETECT );
				setState(285);
				_la = _input.LA(1);
				if (_la==ALWAYS) {
					{
					setState(284); always_statement();
					}
				}

				}
				break;
			case ALWAYS:
				{
				setState(287); always_statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(290); match(END);
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
		public List<Qualified_nameContext> qualified_name() {
			return getRuleContexts(Qualified_nameContext.class);
		}
		public Qualified_nameContext qualified_name(int i) {
			return getRuleContext(Qualified_nameContext.class,i);
		}
		public TerminalNode DETECT() { return getToken(QuorumParser.DETECT, 0); }
		public List<TerminalNode> OR() { return getTokens(QuorumParser.OR); }
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode INHERITS() { return getToken(QuorumParser.INHERITS, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode OR(int i) {
			return getToken(QuorumParser.OR, i);
		}
		public Detect_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_detect_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterDetect_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitDetect_statement(this);
		}
	}

	public final Detect_statementContext detect_statement() throws RecognitionException {
		Detect_statementContext _localctx = new Detect_statementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_detect_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292); match(DETECT);
			setState(293); ((Detect_statementContext)_localctx).name = match(ID);
			setState(303);
			_la = _input.LA(1);
			if (_la==INHERITS) {
				{
				setState(294); match(INHERITS);
				setState(295); qualified_name();
				setState(300);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OR) {
					{
					{
					setState(296); match(OR);
					setState(297); qualified_name();
					}
					}
					setState(302);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(305); block();
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
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode ALWAYS() { return getToken(QuorumParser.ALWAYS, 0); }
		public Always_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_always_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterAlways_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitAlways_statement(this);
		}
	}

	public final Always_statementContext always_statement() throws RecognitionException {
		Always_statementContext _localctx = new Always_statementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_always_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307); match(ALWAYS);
			setState(308); block();
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
		enterRule(_localctx, 42, RULE_print_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310); match(OUTPUT);
			setState(311); expression(0);
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
		enterRule(_localctx, 44, RULE_speak_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313); match(SAY);
			setState(314); expression(0);
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
		enterRule(_localctx, 46, RULE_return_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316); match(RETURN);
			setState(319);
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
				setState(317); expression(0);
				}
				break;
			case NOW:
				{
				setState(318); match(NOW);
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
		enterRule(_localctx, 48, RULE_generic_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321); match(LESS);
			setState(322); ((Generic_declarationContext)_localctx).ID = match(ID);
			((Generic_declarationContext)_localctx).ids.add(((Generic_declarationContext)_localctx).ID);
			setState(327);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(323); match(COMMA);
				setState(324); ((Generic_declarationContext)_localctx).ID = match(ID);
				((Generic_declarationContext)_localctx).ids.add(((Generic_declarationContext)_localctx).ID);
				}
				}
				setState(329);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(330); match(GREATER);
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
		enterRule(_localctx, 50, RULE_generic_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(332); match(LESS);
			setState(333); assignment_declaration();
			setState(338);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(334); match(COMMA);
				setState(335); assignment_declaration();
				}
				}
				setState(340);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(341); match(GREATER);
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
		enterRule(_localctx, 52, RULE_class_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(343); qualified_name();
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

	public final Assignment_declarationContext assignment_declaration() throws RecognitionException {
		Assignment_declarationContext _localctx = new Assignment_declarationContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_assignment_declaration);
		int _la;
		try {
			setState(353);
			switch (_input.LA(1)) {
			case ID:
				_localctx = new GenericAssignmentDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(345); qualified_name();
				setState(347);
				_la = _input.LA(1);
				if (_la==LESS) {
					{
					setState(346); generic_statement();
					}
				}

				}
				break;
			case INTEGER_KEYWORD:
				_localctx = new IntegerAssignmentDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(349); match(INTEGER_KEYWORD);
				}
				break;
			case NUMBER_KEYWORD:
				_localctx = new NumberAssignmentDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(350); match(NUMBER_KEYWORD);
				}
				break;
			case TEXT:
				_localctx = new TextAssignmentDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(351); match(TEXT);
				}
				break;
			case BOOLEAN_KEYWORD:
				_localctx = new BooleanAssignmentDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(352); match(BOOLEAN_KEYWORD);
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
		enterRule(_localctx, 56, RULE_assignment_statement);
		int _la;
		try {
			setState(393);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				_localctx = new NoTypeAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(357);
				_la = _input.LA(1);
				if (_la==ME) {
					{
					setState(355); match(ME);
					setState(356); match(COLON);
					}
				}

				setState(359); ((NoTypeAssignmentContext)_localctx).name = match(ID);
				setState(360); match(EQUALITY);
				setState(361); ((NoTypeAssignmentContext)_localctx).rhs = expression(0);
				}
				break;

			case 2:
				_localctx = new ParentAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(362); match(PARENT);
				setState(363); match(COLON);
				setState(364); ((ParentAssignmentContext)_localctx).parent = qualified_name();
				setState(365); match(COLON);
				setState(366); ((ParentAssignmentContext)_localctx).name = match(ID);
				setState(367); match(EQUALITY);
				setState(368); ((ParentAssignmentContext)_localctx).rhs = expression(0);
				}
				break;

			case 3:
				_localctx = new ObjectAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(370); ((ObjectAssignmentContext)_localctx).object = match(ID);
				setState(375);
				switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
				case 1:
					{
					setState(371); match(COLON);
					setState(372); match(PARENT);
					setState(373); match(COLON);
					setState(374); ((ObjectAssignmentContext)_localctx).parent = qualified_name();
					}
					break;
				}
				setState(377); match(COLON);
				setState(378); ((ObjectAssignmentContext)_localctx).name = match(ID);
				setState(379); match(EQUALITY);
				setState(380); ((ObjectAssignmentContext)_localctx).rhs = expression(0);
				}
				break;

			case 4:
				_localctx = new NormalAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(382);
				_la = _input.LA(1);
				if (_la==PUBLIC || _la==PRIVATE) {
					{
					setState(381); ((NormalAssignmentContext)_localctx).modifier = access_modifier();
					}
				}

				setState(385);
				_la = _input.LA(1);
				if (_la==CONSTANT) {
					{
					setState(384); match(CONSTANT);
					}
				}

				setState(387); ((NormalAssignmentContext)_localctx).type = assignment_declaration();
				setState(388); ((NormalAssignmentContext)_localctx).name = match(ID);
				setState(391);
				_la = _input.LA(1);
				if (_la==EQUALITY) {
					{
					setState(389); match(EQUALITY);
					setState(390); ((NormalAssignmentContext)_localctx).rhs = expression(0);
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
		public List<Elseif_statementContext> elseif_statement() {
			return getRuleContexts(Elseif_statementContext.class);
		}
		public Else_statementContext else_statement() {
			return getRuleContext(Else_statementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Elseif_statementContext elseif_statement(int i) {
			return getRuleContext(Elseif_statementContext.class,i);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
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
		enterRule(_localctx, 58, RULE_if_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(395); match(IF);
			setState(396); expression(0);
			setState(397); block();
			setState(401);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELSE_IF) {
				{
				{
				setState(398); elseif_statement();
				}
				}
				setState(403);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(405);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(404); else_statement();
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

	public static class Elseif_statementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode ELSE_IF() { return getToken(QuorumParser.ELSE_IF, 0); }
		public Elseif_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseif_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterElseif_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitElseif_statement(this);
		}
	}

	public final Elseif_statementContext elseif_statement() throws RecognitionException {
		Elseif_statementContext _localctx = new Elseif_statementContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_elseif_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(409); match(ELSE_IF);
			setState(410); expression(0);
			setState(411); block();
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
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterElse_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitElse_statement(this);
		}
	}

	public final Else_statementContext else_statement() throws RecognitionException {
		Else_statementContext _localctx = new Else_statementContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_else_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413); match(ELSE);
			setState(414); block();
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
		enterRule(_localctx, 64, RULE_loop_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(416); match(REPEAT);
			setState(422);
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
				setState(417); expression(0);
				setState(418); match(TIMES);
				}
				}
				break;
			case UNTIL:
			case WHILE:
				{
				{
				setState(420);
				_la = _input.LA(1);
				if ( !(_la==UNTIL || _la==WHILE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(421); expression(0);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(424); block();
			setState(425); match(END);
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
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public Function_expression_listContext function_expression_list() {
			return getRuleContext(Function_expression_listContext.class,0);
		}
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public Initial_parent_action_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initial_parent_action_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterInitial_parent_action_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitInitial_parent_action_call(this);
		}
	}

	public final Initial_parent_action_callContext initial_parent_action_call() throws RecognitionException {
		Initial_parent_action_callContext _localctx = new Initial_parent_action_callContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_initial_parent_action_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(427); ((Initial_parent_action_callContext)_localctx).var = match(ID);
			setState(432);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				{
				setState(428); match(LEFT_PAREN);
				setState(429); function_expression_list();
				setState(430); match(RIGHT_PAREN);
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
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public Function_expression_listContext function_expression_list() {
			return getRuleContext(Function_expression_listContext.class,0);
		}
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public Action_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterAction_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitAction_call(this);
		}
	}

	public final Action_callContext action_call() throws RecognitionException {
		Action_callContext _localctx = new Action_callContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_action_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(434); ((Action_callContext)_localctx).var = match(ID);
			setState(439);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				setState(435); match(LEFT_PAREN);
				setState(436); function_expression_list();
				setState(437); match(RIGHT_PAREN);
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
	public static class VariableFunctionCallContext extends ExpressionContext {
		public List<Action_callContext> action_call() {
			return getRuleContexts(Action_callContext.class);
		}
		public TerminalNode COLON(int i) {
			return getToken(QuorumParser.COLON, i);
		}
		public TerminalNode ME() { return getToken(QuorumParser.ME, 0); }
		public List<TerminalNode> COLON() { return getTokens(QuorumParser.COLON); }
		public Action_callContext action_call(int i) {
			return getRuleContext(Action_callContext.class,i);
		}
		public VariableFunctionCallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterVariableFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitVariableFunctionCall(this);
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
	public static class ParentVariableFunctionCallContext extends ExpressionContext {
		public Token fieldName;
		public Qualified_nameContext parent;
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public List<Action_callContext> action_call() {
			return getRuleContexts(Action_callContext.class);
		}
		public TerminalNode ID() { return getToken(QuorumParser.ID, 0); }
		public TerminalNode COLON(int i) {
			return getToken(QuorumParser.COLON, i);
		}
		public TerminalNode ME() { return getToken(QuorumParser.ME, 0); }
		public Initial_parent_action_callContext initial_parent_action_call() {
			return getRuleContext(Initial_parent_action_callContext.class,0);
		}
		public TerminalNode PARENT() { return getToken(QuorumParser.PARENT, 0); }
		public List<TerminalNode> COLON() { return getTokens(QuorumParser.COLON); }
		public Action_callContext action_call(int i) {
			return getRuleContext(Action_callContext.class,i);
		}
		public ParentVariableFunctionCallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterParentVariableFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitParentVariableFunctionCall(this);
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
	public static class InputNoParametersContext extends ExpressionContext {
		public TerminalNode INPUT() { return getToken(QuorumParser.INPUT, 0); }
		public TerminalNode RIGHT_PAREN() { return getToken(QuorumParser.RIGHT_PAREN, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(QuorumParser.LEFT_PAREN, 0); }
		public InputNoParametersContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterInputNoParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitInputNoParameters(this);
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
	public static class CastContext extends ExpressionContext {
		public Assignment_declarationContext type;
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
	public static class InheritsContext extends ExpressionContext {
		public Class_typeContext name;
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
			setState(503);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				{
				_localctx = new MinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(442); match(MINUS);
				setState(443); expression(10);
				}
				break;

			case 2:
				{
				_localctx = new NotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(444); match(NOT);
				setState(445); expression(9);
				}
				break;

			case 3:
				{
				_localctx = new ParenthesisExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(446); match(LEFT_PAREN);
				setState(447); expression(0);
				setState(448); match(RIGHT_PAREN);
				}
				break;

			case 4:
				{
				_localctx = new IntegerContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(450); match(INT);
				}
				break;

			case 5:
				{
				_localctx = new BooleanContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(451); match(BOOLEAN);
				}
				break;

			case 6:
				{
				_localctx = new DecimalContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(452); match(DECIMAL);
				}
				break;

			case 7:
				{
				_localctx = new StringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(453); match(STRING);
				}
				break;

			case 8:
				{
				_localctx = new NullContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(454); match(NULL);
				}
				break;

			case 9:
				{
				_localctx = new MeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(455); match(ME);
				}
				break;

			case 10:
				{
				_localctx = new InputContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(456); match(INPUT);
				setState(457); match(LEFT_PAREN);
				setState(458); expression(0);
				setState(459); match(RIGHT_PAREN);
				}
				break;

			case 11:
				{
				_localctx = new InputNoParametersContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(461); match(INPUT);
				setState(462); match(LEFT_PAREN);
				setState(463); match(RIGHT_PAREN);
				}
				break;

			case 12:
				{
				_localctx = new VariableFunctionCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(466);
				_la = _input.LA(1);
				if (_la==ME) {
					{
					setState(464); match(ME);
					setState(465); match(COLON);
					}
				}

				setState(468); action_call();
				setState(473);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
				while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(469); match(COLON);
						{
						setState(470); action_call();
						}
						}
						} 
					}
					setState(475);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
				}
				}
				break;

			case 13:
				{
				_localctx = new ParentVariableFunctionCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(482);
				_la = _input.LA(1);
				if (_la==ME || _la==ID) {
					{
					setState(478);
					_la = _input.LA(1);
					if (_la==ME) {
						{
						setState(476); match(ME);
						setState(477); match(COLON);
						}
					}

					{
					setState(480); ((ParentVariableFunctionCallContext)_localctx).fieldName = match(ID);
					setState(481); match(COLON);
					}
					}
				}

				setState(484); match(PARENT);
				setState(485); match(COLON);
				setState(486); ((ParentVariableFunctionCallContext)_localctx).parent = qualified_name();
				setState(487); match(COLON);
				setState(488); initial_parent_action_call();
				setState(493);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
				while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(489); match(COLON);
						{
						setState(490); action_call();
						}
						}
						} 
					}
					setState(495);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
				}
				}
				break;

			case 14:
				{
				_localctx = new CastContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(496); match(CAST);
				setState(497); match(LEFT_PAREN);
				setState(498); ((CastContext)_localctx).type = assignment_declaration();
				setState(499); match(COMMA);
				setState(500); expression(0);
				setState(501); match(RIGHT_PAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(528);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(526);
					switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplicationContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(505);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(506);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLY) | (1L << DIVIDE) | (1L << MODULO))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(507); expression(8);
						}
						break;

					case 2:
						{
						_localctx = new AdditionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(508);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(509);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(510); expression(7);
						}
						break;

					case 3:
						{
						_localctx = new GreaterContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(511);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(512);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER) | (1L << GREATER_EQUAL) | (1L << LESS) | (1L << LESS_EQUAL))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(513); expression(6);
						}
						break;

					case 4:
						{
						_localctx = new EqualsContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(514);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(515);
						_la = _input.LA(1);
						if ( !(_la==NOTEQUALS || _la==EQUALITY) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(516); expression(4);
						}
						break;

					case 5:
						{
						_localctx = new AndContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(517);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						{
						setState(518); match(AND);
						}
						setState(519); expression(3);
						}
						break;

					case 6:
						{
						_localctx = new OrContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(520);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						{
						setState(521); match(OR);
						}
						setState(522); expression(2);
						}
						break;

					case 7:
						{
						_localctx = new InheritsContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(523);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(524); match(INHERITS);
						setState(525); ((InheritsContext)_localctx).name = class_type();
						}
						break;
					}
					} 
				}
				setState(530);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
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
		enterRule(_localctx, 72, RULE_function_expression_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(539);
			_la = _input.LA(1);
			if (((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & ((1L << (ME - 6)) | (1L << (PARENT - 6)) | (1L << (CAST - 6)) | (1L << (INPUT - 6)) | (1L << (NULL - 6)) | (1L << (NOT - 6)) | (1L << (MINUS - 6)) | (1L << (LEFT_PAREN - 6)) | (1L << (BOOLEAN - 6)) | (1L << (INT - 6)) | (1L << (DECIMAL - 6)) | (1L << (ID - 6)) | (1L << (STRING - 6)))) != 0)) {
				{
				setState(531); expression(0);
				setState(536);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(532); match(COMMA);
					setState(533); expression(0);
					}
					}
					setState(538);
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
		case 35: return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 7);

		case 1: return precpred(_ctx, 6);

		case 2: return precpred(_ctx, 5);

		case 3: return precpred(_ctx, 3);

		case 4: return precpred(_ctx, 2);

		case 5: return precpred(_ctx, 1);

		case 6: return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3F\u0220\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\6\2O\n\2\r\2\16\2P\3\2\6\2"+
		"T\n\2\r\2\16\2U\3\2\3\2\3\2\3\2\6\2\\\n\2\r\2\16\2]\3\2\5\2a\n\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\5\5o\n\5\3\5\5\5r\n\5\3\5\7"+
		"\5u\n\5\f\5\16\5x\13\5\3\5\3\5\5\5|\n\5\3\6\6\6\177\n\6\r\6\16\6\u0080"+
		"\3\6\5\6\u0084\n\6\3\6\6\6\u0087\n\6\r\6\16\6\u0088\5\6\u008b\n\6\3\7"+
		"\3\7\3\7\3\7\7\7\u0091\n\7\f\7\16\7\u0094\13\7\3\b\3\b\5\b\u0098\n\b\3"+
		"\t\3\t\3\n\3\n\5\n\u009e\n\n\3\13\5\13\u00a1\n\13\3\13\3\13\3\13\3\13"+
		"\3\13\5\13\u00a8\n\13\3\13\3\13\3\13\5\13\u00ad\n\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\5\13\u00b6\n\13\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00be\n"+
		"\f\f\f\16\f\u00c1\13\f\5\f\u00c3\n\f\3\f\5\f\u00c6\n\f\3\f\3\f\5\f\u00ca"+
		"\n\f\3\r\3\r\3\r\3\16\3\16\3\16\7\16\u00d2\n\16\f\16\16\16\u00d5\13\16"+
		"\3\17\7\17\u00d8\n\17\f\17\16\17\u00db\13\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\5\20\u00e6\n\20\3\21\3\21\5\21\u00ea\n\21\3\21\3"+
		"\21\5\21\u00ee\n\21\3\21\3\21\3\21\3\21\7\21\u00f4\n\21\f\21\16\21\u00f7"+
		"\13\21\3\21\3\21\5\21\u00fb\n\21\3\21\3\21\5\21\u00ff\n\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\7\21\u0108\n\21\f\21\16\21\u010b\13\21\5\21\u010d"+
		"\n\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\6\24"+
		"\u011b\n\24\r\24\16\24\u011c\3\24\5\24\u0120\n\24\3\24\5\24\u0123\n\24"+
		"\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u012d\n\25\f\25\16\25\u0130"+
		"\13\25\5\25\u0132\n\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3"+
		"\30\3\30\3\31\3\31\3\31\5\31\u0142\n\31\3\32\3\32\3\32\3\32\7\32\u0148"+
		"\n\32\f\32\16\32\u014b\13\32\3\32\3\32\3\33\3\33\3\33\3\33\7\33\u0153"+
		"\n\33\f\33\16\33\u0156\13\33\3\33\3\33\3\34\3\34\3\35\3\35\5\35\u015e"+
		"\n\35\3\35\3\35\3\35\3\35\5\35\u0164\n\35\3\36\3\36\5\36\u0168\n\36\3"+
		"\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3"+
		"\36\3\36\5\36\u017a\n\36\3\36\3\36\3\36\3\36\3\36\5\36\u0181\n\36\3\36"+
		"\5\36\u0184\n\36\3\36\3\36\3\36\3\36\5\36\u018a\n\36\5\36\u018c\n\36\3"+
		"\37\3\37\3\37\3\37\7\37\u0192\n\37\f\37\16\37\u0195\13\37\3\37\5\37\u0198"+
		"\n\37\3\37\3\37\3 \3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\5\"\u01a9"+
		"\n\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\5#\u01b3\n#\3$\3$\3$\3$\3$\5$\u01ba\n"+
		"$\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3"+
		"%\3%\3%\5%\u01d5\n%\3%\3%\3%\7%\u01da\n%\f%\16%\u01dd\13%\3%\3%\5%\u01e1"+
		"\n%\3%\3%\5%\u01e5\n%\3%\3%\3%\3%\3%\3%\3%\7%\u01ee\n%\f%\16%\u01f1\13"+
		"%\3%\3%\3%\3%\3%\3%\3%\5%\u01fa\n%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3"+
		"%\3%\3%\3%\3%\3%\3%\3%\3%\3%\7%\u0211\n%\f%\16%\u0214\13%\3&\3&\3&\7&"+
		"\u0219\n&\f&\16&\u021c\13&\5&\u021e\n&\3&\2\3H\'\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJ\2\b\3\2\n\13\4\2\t\t"+
		"\30\30\3\2\64\66\3\2\62\63\3\2.\61\4\2**--\u025c\2`\3\2\2\2\4e\3\2\2\2"+
		"\6h\3\2\2\2\b{\3\2\2\2\n\u008a\3\2\2\2\f\u008c\3\2\2\2\16\u0095\3\2\2"+
		"\2\20\u0099\3\2\2\2\22\u009d\3\2\2\2\24\u00b5\3\2\2\2\26\u00b7\3\2\2\2"+
		"\30\u00cb\3\2\2\2\32\u00ce\3\2\2\2\34\u00d9\3\2\2\2\36\u00e5\3\2\2\2 "+
		"\u010c\3\2\2\2\"\u010e\3\2\2\2$\u0112\3\2\2\2&\u0117\3\2\2\2(\u0126\3"+
		"\2\2\2*\u0135\3\2\2\2,\u0138\3\2\2\2.\u013b\3\2\2\2\60\u013e\3\2\2\2\62"+
		"\u0143\3\2\2\2\64\u014e\3\2\2\2\66\u0159\3\2\2\28\u0163\3\2\2\2:\u018b"+
		"\3\2\2\2<\u018d\3\2\2\2>\u019b\3\2\2\2@\u019f\3\2\2\2B\u01a2\3\2\2\2D"+
		"\u01ad\3\2\2\2F\u01b4\3\2\2\2H\u01f9\3\2\2\2J\u021d\3\2\2\2LN\5\4\3\2"+
		"MO\5\6\4\2NM\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2\2\2Qa\3\2\2\2RT\5\6\4\2"+
		"SR\3\2\2\2TU\3\2\2\2US\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\5\4\3\2Xa\3\2\2\2"+
		"Ya\5\4\3\2Z\\\5\6\4\2[Z\3\2\2\2\\]\3\2\2\2][\3\2\2\2]^\3\2\2\2^a\3\2\2"+
		"\2_a\3\2\2\2`L\3\2\2\2`S\3\2\2\2`Y\3\2\2\2`[\3\2\2\2`_\3\2\2\2ab\3\2\2"+
		"\2bc\5\b\5\2cd\7\2\2\3d\3\3\2\2\2ef\7\31\2\2fg\5\32\16\2g\5\3\2\2\2hi"+
		"\7(\2\2ij\5\32\16\2j\7\3\2\2\2kl\7>\2\2ln\7B\2\2mo\5\62\32\2nm\3\2\2\2"+
		"no\3\2\2\2oq\3\2\2\2pr\5\f\7\2qp\3\2\2\2qr\3\2\2\2rv\3\2\2\2su\5\22\n"+
		"\2ts\3\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2wy\3\2\2\2xv\3\2\2\2y|\7=\2"+
		"\2z|\5\n\6\2{k\3\2\2\2{z\3\2\2\2|\t\3\2\2\2}\177\5\36\20\2~}\3\2\2\2\177"+
		"\u0080\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u008b\3\2\2\2"+
		"\u0082\u0084\5\20\t\2\u0083\u0082\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0085"+
		"\3\2\2\2\u0085\u0087\5\24\13\2\u0086\u0083\3\2\2\2\u0087\u0088\3\2\2\2"+
		"\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008b\3\2\2\2\u008a~\3"+
		"\2\2\2\u008a\u0086\3\2\2\2\u008b\13\3\2\2\2\u008c\u008d\7\23\2\2\u008d"+
		"\u0092\5\16\b\2\u008e\u008f\7,\2\2\u008f\u0091\5\16\b\2\u0090\u008e\3"+
		"\2\2\2\u0091\u0094\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093"+
		"\r\3\2\2\2\u0094\u0092\3\2\2\2\u0095\u0097\5\32\16\2\u0096\u0098\5\64"+
		"\33\2\u0097\u0096\3\2\2\2\u0097\u0098\3\2\2\2\u0098\17\3\2\2\2\u0099\u009a"+
		"\t\2\2\2\u009a\21\3\2\2\2\u009b\u009e\5:\36\2\u009c\u009e\5\24\13\2\u009d"+
		"\u009b\3\2\2\2\u009d\u009c\3\2\2\2\u009e\23\3\2\2\2\u009f\u00a1\5\20\t"+
		"\2\u00a0\u009f\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3"+
		"\5\26\f\2\u00a3\u00a4\5\34\17\2\u00a4\u00a5\7=\2\2\u00a5\u00b6\3\2\2\2"+
		"\u00a6\u00a8\5\20\t\2\u00a7\u00a6\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9"+
		"\3\2\2\2\u00a9\u00aa\7\21\2\2\u00aa\u00b6\5\26\f\2\u00ab\u00ad\5\20\t"+
		"\2\u00ac\u00ab\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af"+
		"\7\22\2\2\u00af\u00b6\5\26\f\2\u00b0\u00b1\7\4\2\2\u00b1\u00b2\7\5\2\2"+
		"\u00b2\u00b3\5\34\17\2\u00b3\u00b4\7=\2\2\u00b4\u00b6\3\2\2\2\u00b5\u00a0"+
		"\3\2\2\2\u00b5\u00a7\3\2\2\2\u00b5\u00ac\3\2\2\2\u00b5\u00b0\3\2\2\2\u00b6"+
		"\25\3\2\2\2\u00b7\u00b8\7\"\2\2\u00b8\u00c5\7B\2\2\u00b9\u00c2\79\2\2"+
		"\u00ba\u00bf\5\30\r\2\u00bb\u00bc\7,\2\2\u00bc\u00be\5\30\r\2\u00bd\u00bb"+
		"\3\2\2\2\u00be\u00c1\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0"+
		"\u00c3\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c2\u00ba\3\2\2\2\u00c2\u00c3\3\2"+
		"\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c6\7:\2\2\u00c5\u00b9\3\2\2\2\u00c5"+
		"\u00c6\3\2\2\2\u00c6\u00c9\3\2\2\2\u00c7\u00c8\7\35\2\2\u00c8\u00ca\5"+
		"8\35\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\27\3\2\2\2\u00cb"+
		"\u00cc\58\35\2\u00cc\u00cd\7B\2\2\u00cd\31\3\2\2\2\u00ce\u00d3\7B\2\2"+
		"\u00cf\u00d0\7+\2\2\u00d0\u00d2\7B\2\2\u00d1\u00cf\3\2\2\2\u00d2\u00d5"+
		"\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\33\3\2\2\2\u00d5"+
		"\u00d3\3\2\2\2\u00d6\u00d8\5\36\20\2\u00d7\u00d6\3\2\2\2\u00d8\u00db\3"+
		"\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\35\3\2\2\2\u00db"+
		"\u00d9\3\2\2\2\u00dc\u00e6\5 \21\2\u00dd\u00e6\5<\37\2\u00de\u00e6\5:"+
		"\36\2\u00df\u00e6\5B\"\2\u00e0\u00e6\5\60\31\2\u00e1\u00e6\5,\27\2\u00e2"+
		"\u00e6\5.\30\2\u00e3\u00e6\5&\24\2\u00e4\u00e6\5$\23\2\u00e5\u00dc\3\2"+
		"\2\2\u00e5\u00dd\3\2\2\2\u00e5\u00de\3\2\2\2\u00e5\u00df\3\2\2\2\u00e5"+
		"\u00e0\3\2\2\2\u00e5\u00e1\3\2\2\2\u00e5\u00e2\3\2\2\2\u00e5\u00e3\3\2"+
		"\2\2\u00e5\u00e4\3\2\2\2\u00e6\37\3\2\2\2\u00e7\u00e8\7\b\2\2\u00e8\u00ea"+
		"\7#\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb"+
		"\u00ec\7B\2\2\u00ec\u00ee\7#\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2"+
		"\2\u00ee\u00ef\3\2\2\2\u00ef\u00f0\5\"\22\2\u00f0\u00f5\7:\2\2\u00f1\u00f2"+
		"\7#\2\2\u00f2\u00f4\5F$\2\u00f3\u00f1\3\2\2\2\u00f4\u00f7\3\2\2\2\u00f5"+
		"\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u010d\3\2\2\2\u00f7\u00f5\3\2"+
		"\2\2\u00f8\u00f9\7\b\2\2\u00f9\u00fb\7#\2\2\u00fa\u00f8\3\2\2\2\u00fa"+
		"\u00fb\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fd\7B\2\2\u00fd\u00ff\7#\2"+
		"\2\u00fe\u00fa\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u0101"+
		"\7\20\2\2\u0101\u0102\7#\2\2\u0102\u0103\5\32\16\2\u0103\u0104\7#\2\2"+
		"\u0104\u0109\5D#\2\u0105\u0106\7#\2\2\u0106\u0108\5F$\2\u0107\u0105\3"+
		"\2\2\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a"+
		"\u010d\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u00e9\3\2\2\2\u010c\u00fe\3\2"+
		"\2\2\u010d!\3\2\2\2\u010e\u010f\7B\2\2\u010f\u0110\79\2\2\u0110\u0111"+
		"\5J&\2\u0111#\3\2\2\2\u0112\u0113\7\f\2\2\u0113\u0114\79\2\2\u0114\u0115"+
		"\5H%\2\u0115\u0116\7:\2\2\u0116%\3\2\2\2\u0117\u0118\7\17\2\2\u0118\u0122"+
		"\5\34\17\2\u0119\u011b\5(\25\2\u011a\u0119\3\2\2\2\u011b\u011c\3\2\2\2"+
		"\u011c\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011f\3\2\2\2\u011e\u0120"+
		"\5*\26\2\u011f\u011e\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0123\3\2\2\2\u0121"+
		"\u0123\5*\26\2\u0122\u011a\3\2\2\2\u0122\u0121\3\2\2\2\u0123\u0124\3\2"+
		"\2\2\u0124\u0125\7=\2\2\u0125\'\3\2\2\2\u0126\u0127\7\r\2\2\u0127\u0131"+
		"\7B\2\2\u0128\u0129\7\23\2\2\u0129\u012e\5\32\16\2\u012a\u012b\7 \2\2"+
		"\u012b\u012d\5\32\16\2\u012c\u012a\3\2\2\2\u012d\u0130\3\2\2\2\u012e\u012c"+
		"\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0132\3\2\2\2\u0130\u012e\3\2\2\2\u0131"+
		"\u0128\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0134\5\34"+
		"\17\2\u0134)\3\2\2\2\u0135\u0136\7\16\2\2\u0136\u0137\5\34\17\2\u0137"+
		"+\3\2\2\2\u0138\u0139\7\3\2\2\u0139\u013a\5H%\2\u013a-\3\2\2\2\u013b\u013c"+
		"\7\26\2\2\u013c\u013d\5H%\2\u013d/\3\2\2\2\u013e\u0141\7\36\2\2\u013f"+
		"\u0142\5H%\2\u0140\u0142\7\27\2\2\u0141\u013f\3\2\2\2\u0141\u0140\3\2"+
		"\2\2\u0142\61\3\2\2\2\u0143\u0144\7\60\2\2\u0144\u0149\7B\2\2\u0145\u0146"+
		"\7,\2\2\u0146\u0148\7B\2\2\u0147\u0145\3\2\2\2\u0148\u014b\3\2\2\2\u0149"+
		"\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u014c\3\2\2\2\u014b\u0149\3\2"+
		"\2\2\u014c\u014d\7.\2\2\u014d\63\3\2\2\2\u014e\u014f\7\60\2\2\u014f\u0154"+
		"\58\35\2\u0150\u0151\7,\2\2\u0151\u0153\58\35\2\u0152\u0150\3\2\2\2\u0153"+
		"\u0156\3\2\2\2\u0154\u0152\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0157\3\2"+
		"\2\2\u0156\u0154\3\2\2\2\u0157\u0158\7.\2\2\u0158\65\3\2\2\2\u0159\u015a"+
		"\5\32\16\2\u015a\67\3\2\2\2\u015b\u015d\5\32\16\2\u015c\u015e\5\64\33"+
		"\2\u015d\u015c\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u0164\3\2\2\2\u015f\u0164"+
		"\7$\2\2\u0160\u0164\7%\2\2\u0161\u0164\7&\2\2\u0162\u0164\7\'\2\2\u0163"+
		"\u015b\3\2\2\2\u0163\u015f\3\2\2\2\u0163\u0160\3\2\2\2\u0163\u0161\3\2"+
		"\2\2\u0163\u0162\3\2\2\2\u01649\3\2\2\2\u0165\u0166\7\b\2\2\u0166\u0168"+
		"\7#\2\2\u0167\u0165\3\2\2\2\u0167\u0168\3\2\2\2\u0168\u0169\3\2\2\2\u0169"+
		"\u016a\7B\2\2\u016a\u016b\7-\2\2\u016b\u018c\5H%\2\u016c\u016d\7\20\2"+
		"\2\u016d\u016e\7#\2\2\u016e\u016f\5\32\16\2\u016f\u0170\7#\2\2\u0170\u0171"+
		"\7B\2\2\u0171\u0172\7-\2\2\u0172\u0173\5H%\2\u0173\u018c\3\2\2\2\u0174"+
		"\u0179\7B\2\2\u0175\u0176\7#\2\2\u0176\u0177\7\20\2\2\u0177\u0178\7#\2"+
		"\2\u0178\u017a\5\32\16\2\u0179\u0175\3\2\2\2\u0179\u017a\3\2\2\2\u017a"+
		"\u017b\3\2\2\2\u017b\u017c\7#\2\2\u017c\u017d\7B\2\2\u017d\u017e\7-\2"+
		"\2\u017e\u018c\5H%\2\u017f\u0181\5\20\t\2\u0180\u017f\3\2\2\2\u0180\u0181"+
		"\3\2\2\2\u0181\u0183\3\2\2\2\u0182\u0184\7\6\2\2\u0183\u0182\3\2\2\2\u0183"+
		"\u0184\3\2\2\2\u0184\u0185\3\2\2\2\u0185\u0186\58\35\2\u0186\u0189\7B"+
		"\2\2\u0187\u0188\7-\2\2\u0188\u018a\5H%\2\u0189\u0187\3\2\2\2\u0189\u018a"+
		"\3\2\2\2\u018a\u018c\3\2\2\2\u018b\u0167\3\2\2\2\u018b\u016c\3\2\2\2\u018b"+
		"\u0174\3\2\2\2\u018b\u0180\3\2\2\2\u018c;\3\2\2\2\u018d\u018e\7<\2\2\u018e"+
		"\u018f\5H%\2\u018f\u0193\5\34\17\2\u0190\u0192\5> \2\u0191\u0190\3\2\2"+
		"\2\u0192\u0195\3\2\2\2\u0193\u0191\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u0197"+
		"\3\2\2\2\u0195\u0193\3\2\2\2\u0196\u0198\5@!\2\u0197\u0196\3\2\2\2\u0197"+
		"\u0198\3\2\2\2\u0198\u0199\3\2\2\2\u0199\u019a\7=\2\2\u019a=\3\2\2\2\u019b"+
		"\u019c\7\7\2\2\u019c\u019d\5H%\2\u019d\u019e\5\34\17\2\u019e?\3\2\2\2"+
		"\u019f\u01a0\7\34\2\2\u01a0\u01a1\5\34\17\2\u01a1A\3\2\2\2\u01a2\u01a8"+
		"\7\33\2\2\u01a3\u01a4\5H%\2\u01a4\u01a5\7\32\2\2\u01a5\u01a9\3\2\2\2\u01a6"+
		"\u01a7\t\3\2\2\u01a7\u01a9\5H%\2\u01a8\u01a3\3\2\2\2\u01a8\u01a6\3\2\2"+
		"\2\u01a9\u01aa\3\2\2\2\u01aa\u01ab\5\34\17\2\u01ab\u01ac\7=\2\2\u01ac"+
		"C\3\2\2\2\u01ad\u01b2\7B\2\2\u01ae\u01af\79\2\2\u01af\u01b0\5J&\2\u01b0"+
		"\u01b1\7:\2\2\u01b1\u01b3\3\2\2\2\u01b2\u01ae\3\2\2\2\u01b2\u01b3\3\2"+
		"\2\2\u01b3E\3\2\2\2\u01b4\u01b9\7B\2\2\u01b5\u01b6\79\2\2\u01b6\u01b7"+
		"\5J&\2\u01b7\u01b8\7:\2\2\u01b8\u01ba\3\2\2\2\u01b9\u01b5\3\2\2\2\u01b9"+
		"\u01ba\3\2\2\2\u01baG\3\2\2\2\u01bb\u01bc\b%\1\2\u01bc\u01bd\7\63\2\2"+
		"\u01bd\u01fa\5H%\f\u01be\u01bf\7)\2\2\u01bf\u01fa\5H%\13\u01c0\u01c1\7"+
		"9\2\2\u01c1\u01c2\5H%\2\u01c2\u01c3\7:\2\2\u01c3\u01fa\3\2\2\2\u01c4\u01fa"+
		"\7@\2\2\u01c5\u01fa\7?\2\2\u01c6\u01fa\7A\2\2\u01c7\u01fa\7C\2\2\u01c8"+
		"\u01fa\7!\2\2\u01c9\u01fa\7\b\2\2\u01ca\u01cb\7\25\2\2\u01cb\u01cc\79"+
		"\2\2\u01cc\u01cd\5H%\2\u01cd\u01ce\7:\2\2\u01ce\u01fa\3\2\2\2\u01cf\u01d0"+
		"\7\25\2\2\u01d0\u01d1\79\2\2\u01d1\u01fa\7:\2\2\u01d2\u01d3\7\b\2\2\u01d3"+
		"\u01d5\7#\2\2\u01d4\u01d2\3\2\2\2\u01d4\u01d5\3\2\2\2\u01d5\u01d6\3\2"+
		"\2\2\u01d6\u01db\5F$\2\u01d7\u01d8\7#\2\2\u01d8\u01da\5F$\2\u01d9\u01d7"+
		"\3\2\2\2\u01da\u01dd\3\2\2\2\u01db\u01d9\3\2\2\2\u01db\u01dc\3\2\2\2\u01dc"+
		"\u01fa\3\2\2\2\u01dd\u01db\3\2\2\2\u01de\u01df\7\b\2\2\u01df\u01e1\7#"+
		"\2\2\u01e0\u01de\3\2\2\2\u01e0\u01e1\3\2\2\2\u01e1\u01e2\3\2\2\2\u01e2"+
		"\u01e3\7B\2\2\u01e3\u01e5\7#\2\2\u01e4\u01e0\3\2\2\2\u01e4\u01e5\3\2\2"+
		"\2\u01e5\u01e6\3\2\2\2\u01e6\u01e7\7\20\2\2\u01e7\u01e8\7#\2\2\u01e8\u01e9"+
		"\5\32\16\2\u01e9\u01ea\7#\2\2\u01ea\u01ef\5D#\2\u01eb\u01ec\7#\2\2\u01ec"+
		"\u01ee\5F$\2\u01ed\u01eb\3\2\2\2\u01ee\u01f1\3\2\2\2\u01ef\u01ed\3\2\2"+
		"\2\u01ef\u01f0\3\2\2\2\u01f0\u01fa\3\2\2\2\u01f1\u01ef\3\2\2\2\u01f2\u01f3"+
		"\7\24\2\2\u01f3\u01f4\79\2\2\u01f4\u01f5\58\35\2\u01f5\u01f6\7,\2\2\u01f6"+
		"\u01f7\5H%\2\u01f7\u01f8\7:\2\2\u01f8\u01fa\3\2\2\2\u01f9\u01bb\3\2\2"+
		"\2\u01f9\u01be\3\2\2\2\u01f9\u01c0\3\2\2\2\u01f9\u01c4\3\2\2\2\u01f9\u01c5"+
		"\3\2\2\2\u01f9\u01c6\3\2\2\2\u01f9\u01c7\3\2\2\2\u01f9\u01c8\3\2\2\2\u01f9"+
		"\u01c9\3\2\2\2\u01f9\u01ca\3\2\2\2\u01f9\u01cf\3\2\2\2\u01f9\u01d4\3\2"+
		"\2\2\u01f9\u01e4\3\2\2\2\u01f9\u01f2\3\2\2\2\u01fa\u0212\3\2\2\2\u01fb"+
		"\u01fc\f\t\2\2\u01fc\u01fd\t\4\2\2\u01fd\u0211\5H%\n\u01fe\u01ff\f\b\2"+
		"\2\u01ff\u0200\t\5\2\2\u0200\u0211\5H%\t\u0201\u0202\f\7\2\2\u0202\u0203"+
		"\t\6\2\2\u0203\u0211\5H%\b\u0204\u0205\f\5\2\2\u0205\u0206\t\7\2\2\u0206"+
		"\u0211\5H%\6\u0207\u0208\f\4\2\2\u0208\u0209\7\37\2\2\u0209\u0211\5H%"+
		"\5\u020a\u020b\f\3\2\2\u020b\u020c\7 \2\2\u020c\u0211\5H%\4\u020d\u020e"+
		"\f\6\2\2\u020e\u020f\7\23\2\2\u020f\u0211\5\66\34\2\u0210\u01fb\3\2\2"+
		"\2\u0210\u01fe\3\2\2\2\u0210\u0201\3\2\2\2\u0210\u0204\3\2\2\2\u0210\u0207"+
		"\3\2\2\2\u0210\u020a\3\2\2\2\u0210\u020d\3\2\2\2\u0211\u0214\3\2\2\2\u0212"+
		"\u0210\3\2\2\2\u0212\u0213\3\2\2\2\u0213I\3\2\2\2\u0214\u0212\3\2\2\2"+
		"\u0215\u021a\5H%\2\u0216\u0217\7,\2\2\u0217\u0219\5H%\2\u0218\u0216\3"+
		"\2\2\2\u0219\u021c\3\2\2\2\u021a\u0218\3\2\2\2\u021a\u021b\3\2\2\2\u021b"+
		"\u021e\3\2\2\2\u021c\u021a\3\2\2\2\u021d\u0215\3\2\2\2\u021d\u021e\3\2"+
		"\2\2\u021eK\3\2\2\2BPU]`nqv{\u0080\u0083\u0088\u008a\u0092\u0097\u009d"+
		"\u00a0\u00a7\u00ac\u00b5\u00bf\u00c2\u00c5\u00c9\u00d3\u00d9\u00e5\u00e9"+
		"\u00ed\u00f5\u00fa\u00fe\u0109\u010c\u011c\u011f\u0122\u012e\u0131\u0141"+
		"\u0149\u0154\u015d\u0163\u0167\u0179\u0180\u0183\u0189\u018b\u0193\u0197"+
		"\u01a8\u01b2\u01b9\u01d4\u01db\u01e0\u01e4\u01ef\u01f9\u0210\u0212\u021a"+
		"\u021d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}