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
		RULE_class_stmnts = 7, RULE_method_declaration = 8, RULE_formal_parameter = 9, 
		RULE_qualified_name = 10, RULE_block = 11, RULE_statement = 12, RULE_solo_method_call = 13, 
		RULE_alert_statement = 14, RULE_check_statement = 15, RULE_detect_parameter = 16, 
		RULE_print_statement = 17, RULE_speak_statement = 18, RULE_return_statement = 19, 
		RULE_generic_declaration = 20, RULE_generic_statement = 21, RULE_class_type = 22, 
		RULE_assignment_declaration = 23, RULE_assignment_statement = 24, RULE_assign_right_hand_side = 25, 
		RULE_if_statement = 26, RULE_loop_statement = 27, RULE_selector = 28, 
		RULE_expression = 29, RULE_function_expression_list = 30;
	public static final String[] ruleNames = {
		"start", "package_rule", "reference", "class_declaration", "no_class_stmnts", 
		"inherit_stmnts", "access_modifier", "class_stmnts", "method_declaration", 
		"formal_parameter", "qualified_name", "block", "statement", "solo_method_call", 
		"alert_statement", "check_statement", "detect_parameter", "print_statement", 
		"speak_statement", "return_statement", "generic_declaration", "generic_statement", 
		"class_type", "assignment_declaration", "assignment_statement", "assign_right_hand_side", 
		"if_statement", "loop_statement", "selector", "expression", "function_expression_list"
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
			setState(82);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(62); package_rule();
				setState(64); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(63); reference();
					}
					}
					setState(66); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==USE );
				}
				break;

			case 2:
				{
				setState(69); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(68); reference();
					}
					}
					setState(71); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==USE );
				setState(73); package_rule();
				}
				break;

			case 3:
				{
				setState(75); package_rule();
				}
				break;

			case 4:
				{
				setState(77); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(76); reference();
					}
					}
					setState(79); 
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
			setState(84); class_declaration();
			setState(85); match(EOF);
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
			setState(87); match(PACKAGE_NAME);
			setState(88); ((Package_ruleContext)_localctx).name = qualified_name();
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
			setState(90); match(USE);
			setState(91); qualified_name();
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
			setState(109);
			switch (_input.LA(1)) {
			case CLASS:
				_localctx = new FullClassDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(93); match(CLASS);
				setState(94); match(ID);
				setState(96);
				_la = _input.LA(1);
				if (_la==LESS) {
					{
					setState(95); generic_declaration();
					}
				}

				setState(99);
				_la = _input.LA(1);
				if (_la==INHERITS) {
					{
					setState(98); inherit_stmnts();
					}
				}

				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 2)) & ~0x3f) == 0 && ((1L << (_la - 2)) & ((1L << (ON - 2)) | (1L << (CONSTANT - 2)) | (1L << (ME - 2)) | (1L << (PUBLIC - 2)) | (1L << (PRIVATE - 2)) | (1L << (PARENT - 2)) | (1L << (BLUEPRINT - 2)) | (1L << (NATIVE - 2)) | (1L << (ACTION - 2)) | (1L << (INTEGER_KEYWORD - 2)) | (1L << (NUMBER_KEYWORD - 2)) | (1L << (TEXT - 2)) | (1L << (BOOLEAN_KEYWORD - 2)) | (1L << (ID - 2)))) != 0)) {
					{
					{
					setState(101); class_stmnts();
					}
					}
					setState(106);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(107); match(END);
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
				setState(108); no_class_stmnts();
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
			setState(124);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(112); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(111); statement();
					}
					}
					setState(114); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((((_la - 1)) & ~0x3f) == 0 && ((1L << (_la - 1)) & ((1L << (OUTPUT - 1)) | (1L << (CONSTANT - 1)) | (1L << (ME - 1)) | (1L << (PUBLIC - 1)) | (1L << (PRIVATE - 1)) | (1L << (ALERT - 1)) | (1L << (CHECK - 1)) | (1L << (PARENT - 1)) | (1L << (SAY - 1)) | (1L << (REPEAT - 1)) | (1L << (RETURN - 1)) | (1L << (INTEGER_KEYWORD - 1)) | (1L << (NUMBER_KEYWORD - 1)) | (1L << (TEXT - 1)) | (1L << (BOOLEAN_KEYWORD - 1)) | (1L << (IF - 1)) | (1L << (ID - 1)))) != 0) );
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(120); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(117);
					_la = _input.LA(1);
					if (_la==PUBLIC || _la==PRIVATE) {
						{
						setState(116); access_modifier();
						}
					}

					setState(119); method_declaration();
					}
					}
					setState(122); 
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
			setState(126); match(INHERITS);
			setState(127); qualified_name();
			setState(129);
			_la = _input.LA(1);
			if (_la==LESS) {
				{
				setState(128); generic_statement();
				}
			}

			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(131); match(COMMA);
				setState(132); qualified_name();
				setState(134);
				_la = _input.LA(1);
				if (_la==LESS) {
					{
					setState(133); generic_statement();
					}
				}

				}
				}
				setState(140);
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
			setState(141);
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
			setState(148);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(143); assignment_statement();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				_la = _input.LA(1);
				if (_la==PUBLIC || _la==PRIVATE) {
					{
					setState(144); access_modifier();
					}
				}

				setState(147); method_declaration();
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
			setState(220);
			switch (_input.LA(1)) {
			case ACTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(150); match(ACTION);
				setState(151); match(ID);
				setState(164);
				_la = _input.LA(1);
				if (_la==LEFT_PAREN) {
					{
					setState(152); match(LEFT_PAREN);
					setState(161);
					_la = _input.LA(1);
					if (((((_la - 34)) & ~0x3f) == 0 && ((1L << (_la - 34)) & ((1L << (INTEGER_KEYWORD - 34)) | (1L << (NUMBER_KEYWORD - 34)) | (1L << (TEXT - 34)) | (1L << (BOOLEAN_KEYWORD - 34)) | (1L << (ID - 34)))) != 0)) {
						{
						setState(153); formal_parameter();
						setState(158);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(154); match(COMMA);
							setState(155); formal_parameter();
							}
							}
							setState(160);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(163); match(RIGHT_PAREN);
					}
				}

				setState(168);
				_la = _input.LA(1);
				if (_la==RETURNS) {
					{
					setState(166); match(RETURNS);
					setState(167); ((Method_declarationContext)_localctx).return_type = assignment_declaration();
					}
				}

				setState(170); block();
				setState(171); match(END);
				}
				break;
			case BLUEPRINT:
				enterOuterAlt(_localctx, 2);
				{
				setState(173); match(BLUEPRINT);
				setState(174); match(ACTION);
				setState(175); match(ID);
				setState(188);
				_la = _input.LA(1);
				if (_la==LEFT_PAREN) {
					{
					setState(176); match(LEFT_PAREN);
					setState(185);
					_la = _input.LA(1);
					if (((((_la - 34)) & ~0x3f) == 0 && ((1L << (_la - 34)) & ((1L << (INTEGER_KEYWORD - 34)) | (1L << (NUMBER_KEYWORD - 34)) | (1L << (TEXT - 34)) | (1L << (BOOLEAN_KEYWORD - 34)) | (1L << (ID - 34)))) != 0)) {
						{
						setState(177); formal_parameter();
						setState(182);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(178); match(COMMA);
							setState(179); formal_parameter();
							}
							}
							setState(184);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(187); match(RIGHT_PAREN);
					}
				}

				setState(192);
				_la = _input.LA(1);
				if (_la==RETURNS) {
					{
					setState(190); match(RETURNS);
					setState(191); ((Method_declarationContext)_localctx).return_type = assignment_declaration();
					}
				}

				}
				break;
			case NATIVE:
				enterOuterAlt(_localctx, 3);
				{
				setState(194); match(NATIVE);
				setState(195); match(ACTION);
				setState(196); match(ID);
				setState(209);
				_la = _input.LA(1);
				if (_la==LEFT_PAREN) {
					{
					setState(197); match(LEFT_PAREN);
					setState(206);
					_la = _input.LA(1);
					if (((((_la - 34)) & ~0x3f) == 0 && ((1L << (_la - 34)) & ((1L << (INTEGER_KEYWORD - 34)) | (1L << (NUMBER_KEYWORD - 34)) | (1L << (TEXT - 34)) | (1L << (BOOLEAN_KEYWORD - 34)) | (1L << (ID - 34)))) != 0)) {
						{
						setState(198); formal_parameter();
						setState(203);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(199); match(COMMA);
							setState(200); formal_parameter();
							}
							}
							setState(205);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(208); match(RIGHT_PAREN);
					}
				}

				setState(213);
				_la = _input.LA(1);
				if (_la==RETURNS) {
					{
					setState(211); match(RETURNS);
					setState(212); assignment_declaration();
					}
				}

				}
				break;
			case ON:
				enterOuterAlt(_localctx, 4);
				{
				setState(215); match(ON);
				setState(216); match(CREATE);
				setState(217); block();
				setState(218); match(END);
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
			setState(222); assignment_declaration();
			setState(223); match(ID);
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
			setState(225); ((Qualified_nameContext)_localctx).ID = match(ID);
			((Qualified_nameContext)_localctx).ids.add(((Qualified_nameContext)_localctx).ID);
			setState(230);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(226); match(PERIOD);
					setState(227); ((Qualified_nameContext)_localctx).ID = match(ID);
					((Qualified_nameContext)_localctx).ids.add(((Qualified_nameContext)_localctx).ID);
					}
					} 
				}
				setState(232);
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
			setState(236);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 1)) & ~0x3f) == 0 && ((1L << (_la - 1)) & ((1L << (OUTPUT - 1)) | (1L << (CONSTANT - 1)) | (1L << (ME - 1)) | (1L << (PUBLIC - 1)) | (1L << (PRIVATE - 1)) | (1L << (ALERT - 1)) | (1L << (CHECK - 1)) | (1L << (PARENT - 1)) | (1L << (SAY - 1)) | (1L << (REPEAT - 1)) | (1L << (RETURN - 1)) | (1L << (INTEGER_KEYWORD - 1)) | (1L << (NUMBER_KEYWORD - 1)) | (1L << (TEXT - 1)) | (1L << (BOOLEAN_KEYWORD - 1)) | (1L << (IF - 1)) | (1L << (ID - 1)))) != 0)) {
				{
				{
				setState(233); statement();
				}
				}
				setState(238);
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
			setState(248);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(239); solo_method_call();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(240); if_statement();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(241); assignment_statement();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(242); loop_statement();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(243); return_statement();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(244); print_statement();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(245); speak_statement();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(246); check_statement();
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(247); alert_statement();
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
			setState(306);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(250); qualified_name();
				setState(253);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(251); match(COLON);
					setState(252); match(ID);
					}
				}

				setState(255); match(LEFT_PAREN);
				setState(264);
				_la = _input.LA(1);
				if (((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & ((1L << (ME - 6)) | (1L << (PARENT - 6)) | (1L << (CAST - 6)) | (1L << (INPUT - 6)) | (1L << (NULL - 6)) | (1L << (NOT - 6)) | (1L << (MINUS - 6)) | (1L << (LEFT_PAREN - 6)) | (1L << (BOOLEAN - 6)) | (1L << (INT - 6)) | (1L << (DECIMAL - 6)) | (1L << (ID - 6)) | (1L << (STRING - 6)))) != 0)) {
					{
					setState(256); expression(0);
					setState(261);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(257); match(COMMA);
						setState(258); expression(0);
						}
						}
						setState(263);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(266); match(RIGHT_PAREN);
				}
				break;
			case PARENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(268); match(PARENT);
				setState(269); match(COLON);
				setState(270); qualified_name();
				setState(271); match(COLON);
				setState(272); match(ID);
				setState(273); match(LEFT_PAREN);
				setState(282);
				_la = _input.LA(1);
				if (((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & ((1L << (ME - 6)) | (1L << (PARENT - 6)) | (1L << (CAST - 6)) | (1L << (INPUT - 6)) | (1L << (NULL - 6)) | (1L << (NOT - 6)) | (1L << (MINUS - 6)) | (1L << (LEFT_PAREN - 6)) | (1L << (BOOLEAN - 6)) | (1L << (INT - 6)) | (1L << (DECIMAL - 6)) | (1L << (ID - 6)) | (1L << (STRING - 6)))) != 0)) {
					{
					setState(274); expression(0);
					setState(279);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(275); match(COMMA);
						setState(276); expression(0);
						}
						}
						setState(281);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(284); match(RIGHT_PAREN);
				}
				break;
			case ME:
				enterOuterAlt(_localctx, 3);
				{
				setState(286); match(ME);
				setState(287); match(COLON);
				setState(288); qualified_name();
				setState(291);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(289); match(COLON);
					setState(290); match(ID);
					}
				}

				setState(293); match(LEFT_PAREN);
				setState(302);
				_la = _input.LA(1);
				if (((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & ((1L << (ME - 6)) | (1L << (PARENT - 6)) | (1L << (CAST - 6)) | (1L << (INPUT - 6)) | (1L << (NULL - 6)) | (1L << (NOT - 6)) | (1L << (MINUS - 6)) | (1L << (LEFT_PAREN - 6)) | (1L << (BOOLEAN - 6)) | (1L << (INT - 6)) | (1L << (DECIMAL - 6)) | (1L << (ID - 6)) | (1L << (STRING - 6)))) != 0)) {
					{
					setState(294); expression(0);
					setState(299);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(295); match(COMMA);
						setState(296); expression(0);
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
		enterRule(_localctx, 28, RULE_alert_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308); match(ALERT);
			setState(309); match(LEFT_PAREN);
			setState(310); expression(0);
			setState(311); match(RIGHT_PAREN);
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
			setState(313); match(CHECK);
			setState(314); block();
			setState(329);
			switch (_input.LA(1)) {
			case DETECT:
				{
				setState(319); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(315); match(DETECT);
					setState(316); detect_parameter();
					setState(317); block();
					}
					}
					setState(321); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DETECT );
				setState(325);
				_la = _input.LA(1);
				if (_la==ALWAYS) {
					{
					setState(323); match(ALWAYS);
					setState(324); block();
					}
				}

				}
				break;
			case ALWAYS:
				{
				setState(327); match(ALWAYS);
				setState(328); block();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(331); match(END);
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
			setState(333); match(ID);
			setState(343);
			_la = _input.LA(1);
			if (_la==INHERITS) {
				{
				setState(334); match(INHERITS);
				setState(335); qualified_name();
				setState(340);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OR) {
					{
					{
					setState(336); match(OR);
					setState(337); qualified_name();
					}
					}
					setState(342);
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
		enterRule(_localctx, 34, RULE_print_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345); match(OUTPUT);
			setState(346); expression(0);
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
		enterRule(_localctx, 36, RULE_speak_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(348); match(SAY);
			setState(349); expression(0);
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
		enterRule(_localctx, 38, RULE_return_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(351); match(RETURN);
			setState(354);
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
				setState(352); expression(0);
				}
				break;
			case NOW:
				{
				setState(353); match(NOW);
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
			setState(356); match(LESS);
			setState(357); ((Generic_declarationContext)_localctx).ID = match(ID);
			((Generic_declarationContext)_localctx).ids.add(((Generic_declarationContext)_localctx).ID);
			setState(362);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(358); match(COMMA);
				setState(359); ((Generic_declarationContext)_localctx).ID = match(ID);
				((Generic_declarationContext)_localctx).ids.add(((Generic_declarationContext)_localctx).ID);
				}
				}
				setState(364);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(365); match(GREATER);
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
			setState(367); match(LESS);
			setState(368); assignment_declaration();
			setState(373);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(369); match(COMMA);
				setState(370); assignment_declaration();
				}
				}
				setState(375);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(376); match(GREATER);
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
			setState(378); qualified_name();
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
			setState(388);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(380); qualified_name();
				setState(382);
				_la = _input.LA(1);
				if (_la==LESS) {
					{
					setState(381); generic_statement();
					}
				}

				}
				break;
			case INTEGER_KEYWORD:
				enterOuterAlt(_localctx, 2);
				{
				setState(384); match(INTEGER_KEYWORD);
				}
				break;
			case NUMBER_KEYWORD:
				enterOuterAlt(_localctx, 3);
				{
				setState(385); match(NUMBER_KEYWORD);
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 4);
				{
				setState(386); match(TEXT);
				}
				break;
			case BOOLEAN_KEYWORD:
				enterOuterAlt(_localctx, 5);
				{
				setState(387); match(BOOLEAN_KEYWORD);
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
			setState(419);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(393);
				_la = _input.LA(1);
				if (_la==ME || _la==PARENT) {
					{
					setState(390); ((Assignment_statementContext)_localctx).sel = selector();
					setState(391); match(COLON);
					}
				}

				setState(395); match(ID);
				setState(396); ((Assignment_statementContext)_localctx).rhs = assign_right_hand_side();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(397); ((Assignment_statementContext)_localctx).obj = qualified_name();
				setState(402);
				switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
				case 1:
					{
					setState(398); match(COLON);
					setState(399); match(PARENT);
					setState(400); match(COLON);
					setState(401); ((Assignment_statementContext)_localctx).parent = qualified_name();
					}
					break;
				}
				setState(404); match(COLON);
				setState(405); match(ID);
				setState(406); ((Assignment_statementContext)_localctx).rhs = assign_right_hand_side();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(409);
				_la = _input.LA(1);
				if (_la==PUBLIC || _la==PRIVATE) {
					{
					setState(408); ((Assignment_statementContext)_localctx).modifier = access_modifier();
					}
				}

				setState(412);
				_la = _input.LA(1);
				if (_la==CONSTANT) {
					{
					setState(411); match(CONSTANT);
					}
				}

				setState(414); ((Assignment_statementContext)_localctx).type = assignment_declaration();
				setState(415); ((Assignment_statementContext)_localctx).name = match(ID);
				setState(417);
				_la = _input.LA(1);
				if (_la==EQUALITY) {
					{
					setState(416); ((Assignment_statementContext)_localctx).rhs = assign_right_hand_side();
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
			setState(421); match(EQUALITY);
			setState(422); expression(0);
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
			setState(424); match(IF);
			setState(425); expression(0);
			setState(426); block();
			setState(433);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELSE_IF) {
				{
				{
				setState(427); match(ELSE_IF);
				setState(428); expression(0);
				setState(429); block();
				}
				}
				setState(435);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(438);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(436); match(ELSE);
				setState(437); block();
				}
			}

			setState(440); match(END);
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
			setState(442); match(REPEAT);
			setState(448);
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
				setState(443); expression(0);
				setState(444); match(TIMES);
				}
				}
				break;
			case UNTIL:
			case WHILE:
				{
				{
				setState(446);
				_la = _input.LA(1);
				if ( !(_la==UNTIL || _la==WHILE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(447); expression(0);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(450); block();
			setState(451); match(END);
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
			setState(456);
			switch (_input.LA(1)) {
			case PARENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(453); match(PARENT);
				setState(454); match(COLON);
				}
				break;
			case ME:
				enterOuterAlt(_localctx, 2);
				{
				setState(455); match(ME);
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

	public static class ExpressionContext extends ParserRuleContext {
		public int _p;
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
	public static class FieldAccessContext extends ExpressionContext {
		public Qualified_nameContext qualified_name() {
			return getRuleContext(Qualified_nameContext.class,0);
		}
		public SelectorContext selector() {
			return getRuleContext(SelectorContext.class,0);
		}
		public TerminalNode COLON() { return getToken(QuorumParser.COLON, 0); }
		public FieldAccessContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).enterFieldAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuorumListener ) ((QuorumListener)listener).exitFieldAccess(this);
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
		int _startState = 58;
		enterRecursionRule(_localctx, RULE_expression);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(531);
			switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
			case 1:
				{
				_localctx = new MinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(459); match(MINUS);
				setState(460); expression(18);
				}
				break;

			case 2:
				{
				_localctx = new NotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(461); match(NOT);
				setState(462); expression(17);
				}
				break;

			case 3:
				{
				_localctx = new VariableOrFieldAccessContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(463); qualified_name();
				setState(466);
				switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
				case 1:
					{
					setState(464); match(COLON);
					setState(465); match(ID);
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
				setState(468); qualified_name();
				setState(469); match(COLON);
				setState(470); match(PARENT);
				setState(471); match(COLON);
				setState(472); qualified_name();
				setState(473); match(COLON);
				setState(474); match(ID);
				}
				break;

			case 5:
				{
				_localctx = new ObjectFunctionCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(476); qualified_name();
				setState(479);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(477); match(COLON);
					setState(478); match(ID);
					}
				}

				setState(481); match(LEFT_PAREN);
				setState(482); function_expression_list();
				setState(483); match(RIGHT_PAREN);
				}
				break;

			case 6:
				{
				_localctx = new FieldAccessContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(485); selector();
				setState(486); match(COLON);
				setState(487); qualified_name();
				}
				break;

			case 7:
				{
				_localctx = new ParentFunctionCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(489); match(PARENT);
				setState(490); match(COLON);
				setState(491); qualified_name();
				setState(492); match(COLON);
				setState(493); match(ID);
				setState(494); match(LEFT_PAREN);
				setState(495); function_expression_list();
				setState(496); match(RIGHT_PAREN);
				}
				break;

			case 8:
				{
				_localctx = new MeFunctionCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(498); match(ME);
				setState(499); match(COLON);
				setState(500); qualified_name();
				setState(503);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(501); match(COLON);
					setState(502); match(ID);
					}
				}

				setState(505); match(LEFT_PAREN);
				setState(506); function_expression_list();
				setState(507); match(RIGHT_PAREN);
				}
				break;

			case 9:
				{
				_localctx = new CastContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(509); match(CAST);
				setState(510); match(LEFT_PAREN);
				setState(511); assignment_declaration();
				setState(512); match(COMMA);
				setState(513); expression(0);
				setState(514); match(RIGHT_PAREN);
				}
				break;

			case 10:
				{
				_localctx = new IntegerContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(516); match(INT);
				}
				break;

			case 11:
				{
				_localctx = new BooleanContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(517); match(BOOLEAN);
				}
				break;

			case 12:
				{
				_localctx = new DecimalContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(518); match(DECIMAL);
				}
				break;

			case 13:
				{
				_localctx = new StringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(519); match(STRING);
				}
				break;

			case 14:
				{
				_localctx = new NullContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(520); match(NULL);
				}
				break;

			case 15:
				{
				_localctx = new MeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(521); match(ME);
				}
				break;

			case 16:
				{
				_localctx = new InputContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(522); match(INPUT);
				setState(523); match(LEFT_PAREN);
				setState(524); expression(0);
				setState(525); match(RIGHT_PAREN);
				}
				break;

			case 17:
				{
				_localctx = new ParenthesisExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(527); match(LEFT_PAREN);
				setState(528); expression(0);
				setState(529); match(RIGHT_PAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(556);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,67,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(554);
					switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplicationContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(533);
						if (!(15 >= _localctx._p)) throw new FailedPredicateException(this, "15 >= $_p");
						setState(534);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLY) | (1L << DIVIDE) | (1L << MODULO))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(535); expression(16);
						}
						break;

					case 2:
						{
						_localctx = new AdditionContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(536);
						if (!(14 >= _localctx._p)) throw new FailedPredicateException(this, "14 >= $_p");
						setState(537);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(538); expression(15);
						}
						break;

					case 3:
						{
						_localctx = new GreaterContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(539);
						if (!(13 >= _localctx._p)) throw new FailedPredicateException(this, "13 >= $_p");
						setState(540);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER) | (1L << GREATER_EQUAL) | (1L << LESS) | (1L << LESS_EQUAL))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(541); expression(14);
						}
						break;

					case 4:
						{
						_localctx = new EqualsContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(542);
						if (!(11 >= _localctx._p)) throw new FailedPredicateException(this, "11 >= $_p");
						setState(543);
						_la = _input.LA(1);
						if ( !(_la==NOTEQUALS || _la==EQUALITY) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(544); expression(12);
						}
						break;

					case 5:
						{
						_localctx = new AndContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(545);
						if (!(10 >= _localctx._p)) throw new FailedPredicateException(this, "10 >= $_p");
						{
						setState(546); match(AND);
						}
						setState(547); expression(11);
						}
						break;

					case 6:
						{
						_localctx = new OrContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(548);
						if (!(9 >= _localctx._p)) throw new FailedPredicateException(this, "9 >= $_p");
						{
						setState(549); match(OR);
						}
						setState(550); expression(10);
						}
						break;

					case 7:
						{
						_localctx = new InheritsContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(551);
						if (!(12 >= _localctx._p)) throw new FailedPredicateException(this, "12 >= $_p");
						setState(552); match(INHERITS);
						setState(553); class_type();
						}
						break;
					}
					} 
				}
				setState(558);
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
		enterRule(_localctx, 60, RULE_function_expression_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(567);
			_la = _input.LA(1);
			if (((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & ((1L << (ME - 6)) | (1L << (PARENT - 6)) | (1L << (CAST - 6)) | (1L << (INPUT - 6)) | (1L << (NULL - 6)) | (1L << (NOT - 6)) | (1L << (MINUS - 6)) | (1L << (LEFT_PAREN - 6)) | (1L << (BOOLEAN - 6)) | (1L << (INT - 6)) | (1L << (DECIMAL - 6)) | (1L << (ID - 6)) | (1L << (STRING - 6)))) != 0)) {
				{
				setState(559); expression(0);
				setState(564);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(560); match(COMMA);
					setState(561); expression(0);
					}
					}
					setState(566);
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
		case 29: return expression_sempred((ExpressionContext)_localctx, predIndex);
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3F\u023c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\6\2C\n\2\r\2\16\2D\3\2\6\2H\n\2\r\2\16\2I\3\2\3\2\3\2\3\2\6\2P\n"+
		"\2\r\2\16\2Q\3\2\5\2U\n\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5"+
		"\3\5\5\5c\n\5\3\5\5\5f\n\5\3\5\7\5i\n\5\f\5\16\5l\13\5\3\5\3\5\5\5p\n"+
		"\5\3\6\6\6s\n\6\r\6\16\6t\3\6\5\6x\n\6\3\6\6\6{\n\6\r\6\16\6|\5\6\177"+
		"\n\6\3\7\3\7\3\7\5\7\u0084\n\7\3\7\3\7\3\7\5\7\u0089\n\7\7\7\u008b\n\7"+
		"\f\7\16\7\u008e\13\7\3\b\3\b\3\t\3\t\5\t\u0094\n\t\3\t\5\t\u0097\n\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\7\n\u009f\n\n\f\n\16\n\u00a2\13\n\5\n\u00a4\n\n"+
		"\3\n\5\n\u00a7\n\n\3\n\3\n\5\n\u00ab\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\7\n\u00b7\n\n\f\n\16\n\u00ba\13\n\5\n\u00bc\n\n\3\n\5\n\u00bf"+
		"\n\n\3\n\3\n\5\n\u00c3\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00cc\n\n\f"+
		"\n\16\n\u00cf\13\n\5\n\u00d1\n\n\3\n\5\n\u00d4\n\n\3\n\3\n\5\n\u00d8\n"+
		"\n\3\n\3\n\3\n\3\n\3\n\5\n\u00df\n\n\3\13\3\13\3\13\3\f\3\f\3\f\7\f\u00e7"+
		"\n\f\f\f\16\f\u00ea\13\f\3\r\7\r\u00ed\n\r\f\r\16\r\u00f0\13\r\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00fb\n\16\3\17\3\17\3\17"+
		"\5\17\u0100\n\17\3\17\3\17\3\17\3\17\7\17\u0106\n\17\f\17\16\17\u0109"+
		"\13\17\5\17\u010b\n\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\7\17\u0118\n\17\f\17\16\17\u011b\13\17\5\17\u011d\n\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\5\17\u0126\n\17\3\17\3\17\3\17\3\17\7\17"+
		"\u012c\n\17\f\17\16\17\u012f\13\17\5\17\u0131\n\17\3\17\3\17\5\17\u0135"+
		"\n\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\6\21\u0142"+
		"\n\21\r\21\16\21\u0143\3\21\3\21\5\21\u0148\n\21\3\21\3\21\5\21\u014c"+
		"\n\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\7\22\u0155\n\22\f\22\16\22\u0158"+
		"\13\22\5\22\u015a\n\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\5"+
		"\25\u0165\n\25\3\26\3\26\3\26\3\26\7\26\u016b\n\26\f\26\16\26\u016e\13"+
		"\26\3\26\3\26\3\27\3\27\3\27\3\27\7\27\u0176\n\27\f\27\16\27\u0179\13"+
		"\27\3\27\3\27\3\30\3\30\3\31\3\31\5\31\u0181\n\31\3\31\3\31\3\31\3\31"+
		"\5\31\u0187\n\31\3\32\3\32\3\32\5\32\u018c\n\32\3\32\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\5\32\u0195\n\32\3\32\3\32\3\32\3\32\3\32\5\32\u019c\n\32"+
		"\3\32\5\32\u019f\n\32\3\32\3\32\3\32\5\32\u01a4\n\32\5\32\u01a6\n\32\3"+
		"\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\7\34\u01b2\n\34\f\34"+
		"\16\34\u01b5\13\34\3\34\3\34\5\34\u01b9\n\34\3\34\3\34\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\5\35\u01c3\n\35\3\35\3\35\3\35\3\36\3\36\3\36\5\36\u01cb"+
		"\n\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u01d5\n\37\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u01e2\n\37\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u01fa\n\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u0216\n\37\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\3\37\7\37\u022d\n\37\f\37\16\37\u0230\13\37\3 \3 \3 \7 \u0235"+
		"\n \f \16 \u0238\13 \5 \u023a\n \3 \2!\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \"$&(*,.\60\62\64\668:<>\2\b\3\2\n\13\4\2\t\t\30\30\3\2\64\66\3"+
		"\2\62\63\3\2.\61\4\2**--\u0287\2T\3\2\2\2\4Y\3\2\2\2\6\\\3\2\2\2\bo\3"+
		"\2\2\2\n~\3\2\2\2\f\u0080\3\2\2\2\16\u008f\3\2\2\2\20\u0096\3\2\2\2\22"+
		"\u00de\3\2\2\2\24\u00e0\3\2\2\2\26\u00e3\3\2\2\2\30\u00ee\3\2\2\2\32\u00fa"+
		"\3\2\2\2\34\u0134\3\2\2\2\36\u0136\3\2\2\2 \u013b\3\2\2\2\"\u014f\3\2"+
		"\2\2$\u015b\3\2\2\2&\u015e\3\2\2\2(\u0161\3\2\2\2*\u0166\3\2\2\2,\u0171"+
		"\3\2\2\2.\u017c\3\2\2\2\60\u0186\3\2\2\2\62\u01a5\3\2\2\2\64\u01a7\3\2"+
		"\2\2\66\u01aa\3\2\2\28\u01bc\3\2\2\2:\u01ca\3\2\2\2<\u0215\3\2\2\2>\u0239"+
		"\3\2\2\2@B\5\4\3\2AC\5\6\4\2BA\3\2\2\2CD\3\2\2\2DB\3\2\2\2DE\3\2\2\2E"+
		"U\3\2\2\2FH\5\6\4\2GF\3\2\2\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JK\3\2\2\2"+
		"KL\5\4\3\2LU\3\2\2\2MU\5\4\3\2NP\5\6\4\2ON\3\2\2\2PQ\3\2\2\2QO\3\2\2\2"+
		"QR\3\2\2\2RU\3\2\2\2SU\3\2\2\2T@\3\2\2\2TG\3\2\2\2TM\3\2\2\2TO\3\2\2\2"+
		"TS\3\2\2\2UV\3\2\2\2VW\5\b\5\2WX\7\2\2\3X\3\3\2\2\2YZ\7\31\2\2Z[\5\26"+
		"\f\2[\5\3\2\2\2\\]\7(\2\2]^\5\26\f\2^\7\3\2\2\2_`\7>\2\2`b\7B\2\2ac\5"+
		"*\26\2ba\3\2\2\2bc\3\2\2\2ce\3\2\2\2df\5\f\7\2ed\3\2\2\2ef\3\2\2\2fj\3"+
		"\2\2\2gi\5\20\t\2hg\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2km\3\2\2\2lj"+
		"\3\2\2\2mp\7=\2\2np\5\n\6\2o_\3\2\2\2on\3\2\2\2p\t\3\2\2\2qs\5\32\16\2"+
		"rq\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2\2\2u\177\3\2\2\2vx\5\16\b\2wv\3\2"+
		"\2\2wx\3\2\2\2xy\3\2\2\2y{\5\22\n\2zw\3\2\2\2{|\3\2\2\2|z\3\2\2\2|}\3"+
		"\2\2\2}\177\3\2\2\2~r\3\2\2\2~z\3\2\2\2\177\13\3\2\2\2\u0080\u0081\7\23"+
		"\2\2\u0081\u0083\5\26\f\2\u0082\u0084\5,\27\2\u0083\u0082\3\2\2\2\u0083"+
		"\u0084\3\2\2\2\u0084\u008c\3\2\2\2\u0085\u0086\7,\2\2\u0086\u0088\5\26"+
		"\f\2\u0087\u0089\5,\27\2\u0088\u0087\3\2\2\2\u0088\u0089\3\2\2\2\u0089"+
		"\u008b\3\2\2\2\u008a\u0085\3\2\2\2\u008b\u008e\3\2\2\2\u008c\u008a\3\2"+
		"\2\2\u008c\u008d\3\2\2\2\u008d\r\3\2\2\2\u008e\u008c\3\2\2\2\u008f\u0090"+
		"\t\2\2\2\u0090\17\3\2\2\2\u0091\u0097\5\62\32\2\u0092\u0094\5\16\b\2\u0093"+
		"\u0092\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0097\5\22"+
		"\n\2\u0096\u0091\3\2\2\2\u0096\u0093\3\2\2\2\u0097\21\3\2\2\2\u0098\u0099"+
		"\7\"\2\2\u0099\u00a6\7B\2\2\u009a\u00a3\79\2\2\u009b\u00a0\5\24\13\2\u009c"+
		"\u009d\7,\2\2\u009d\u009f\5\24\13\2\u009e\u009c\3\2\2\2\u009f\u00a2\3"+
		"\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2"+
		"\u00a0\3\2\2\2\u00a3\u009b\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\3\2"+
		"\2\2\u00a5\u00a7\7:\2\2\u00a6\u009a\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\u00aa\3\2\2\2\u00a8\u00a9\7\35\2\2\u00a9\u00ab\5\60\31\2\u00aa\u00a8"+
		"\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\5\30\r\2"+
		"\u00ad\u00ae\7=\2\2\u00ae\u00df\3\2\2\2\u00af\u00b0\7\21\2\2\u00b0\u00b1"+
		"\7\"\2\2\u00b1\u00be\7B\2\2\u00b2\u00bb\79\2\2\u00b3\u00b8\5\24\13\2\u00b4"+
		"\u00b5\7,\2\2\u00b5\u00b7\5\24\13\2\u00b6\u00b4\3\2\2\2\u00b7\u00ba\3"+
		"\2\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba"+
		"\u00b8\3\2\2\2\u00bb\u00b3\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\3\2"+
		"\2\2\u00bd\u00bf\7:\2\2\u00be\u00b2\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf"+
		"\u00c2\3\2\2\2\u00c0\u00c1\7\35\2\2\u00c1\u00c3\5\60\31\2\u00c2\u00c0"+
		"\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00df\3\2\2\2\u00c4\u00c5\7\22\2\2"+
		"\u00c5\u00c6\7\"\2\2\u00c6\u00d3\7B\2\2\u00c7\u00d0\79\2\2\u00c8\u00cd"+
		"\5\24\13\2\u00c9\u00ca\7,\2\2\u00ca\u00cc\5\24\13\2\u00cb\u00c9\3\2\2"+
		"\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d1"+
		"\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00c8\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1"+
		"\u00d2\3\2\2\2\u00d2\u00d4\7:\2\2\u00d3\u00c7\3\2\2\2\u00d3\u00d4\3\2"+
		"\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d6\7\35\2\2\u00d6\u00d8\5\60\31\2\u00d7"+
		"\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00df\3\2\2\2\u00d9\u00da\7\4"+
		"\2\2\u00da\u00db\7\5\2\2\u00db\u00dc\5\30\r\2\u00dc\u00dd\7=\2\2\u00dd"+
		"\u00df\3\2\2\2\u00de\u0098\3\2\2\2\u00de\u00af\3\2\2\2\u00de\u00c4\3\2"+
		"\2\2\u00de\u00d9\3\2\2\2\u00df\23\3\2\2\2\u00e0\u00e1\5\60\31\2\u00e1"+
		"\u00e2\7B\2\2\u00e2\25\3\2\2\2\u00e3\u00e8\7B\2\2\u00e4\u00e5\7+\2\2\u00e5"+
		"\u00e7\7B\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00ea\3\2\2\2\u00e8\u00e6\3\2"+
		"\2\2\u00e8\u00e9\3\2\2\2\u00e9\27\3\2\2\2\u00ea\u00e8\3\2\2\2\u00eb\u00ed"+
		"\5\32\16\2\u00ec\u00eb\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee\u00ec\3\2\2\2"+
		"\u00ee\u00ef\3\2\2\2\u00ef\31\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1\u00fb"+
		"\5\34\17\2\u00f2\u00fb\5\66\34\2\u00f3\u00fb\5\62\32\2\u00f4\u00fb\58"+
		"\35\2\u00f5\u00fb\5(\25\2\u00f6\u00fb\5$\23\2\u00f7\u00fb\5&\24\2\u00f8"+
		"\u00fb\5 \21\2\u00f9\u00fb\5\36\20\2\u00fa\u00f1\3\2\2\2\u00fa\u00f2\3"+
		"\2\2\2\u00fa\u00f3\3\2\2\2\u00fa\u00f4\3\2\2\2\u00fa\u00f5\3\2\2\2\u00fa"+
		"\u00f6\3\2\2\2\u00fa\u00f7\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00f9\3\2"+
		"\2\2\u00fb\33\3\2\2\2\u00fc\u00ff\5\26\f\2\u00fd\u00fe\7#\2\2\u00fe\u0100"+
		"\7B\2\2\u00ff\u00fd\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u0101\3\2\2\2\u0101"+
		"\u010a\79\2\2\u0102\u0107\5<\37\2\u0103\u0104\7,\2\2\u0104\u0106\5<\37"+
		"\2\u0105\u0103\3\2\2\2\u0106\u0109\3\2\2\2\u0107\u0105\3\2\2\2\u0107\u0108"+
		"\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u010a\u0102\3\2\2\2\u010a"+
		"\u010b\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010d\7:\2\2\u010d\u0135\3\2"+
		"\2\2\u010e\u010f\7\20\2\2\u010f\u0110\7#\2\2\u0110\u0111\5\26\f\2\u0111"+
		"\u0112\7#\2\2\u0112\u0113\7B\2\2\u0113\u011c\79\2\2\u0114\u0119\5<\37"+
		"\2\u0115\u0116\7,\2\2\u0116\u0118\5<\37\2\u0117\u0115\3\2\2\2\u0118\u011b"+
		"\3\2\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011d\3\2\2\2\u011b"+
		"\u0119\3\2\2\2\u011c\u0114\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011e\3\2"+
		"\2\2\u011e\u011f\7:\2\2\u011f\u0135\3\2\2\2\u0120\u0121\7\b\2\2\u0121"+
		"\u0122\7#\2\2\u0122\u0125\5\26\f\2\u0123\u0124\7#\2\2\u0124\u0126\7B\2"+
		"\2\u0125\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u0130"+
		"\79\2\2\u0128\u012d\5<\37\2\u0129\u012a\7,\2\2\u012a\u012c\5<\37\2\u012b"+
		"\u0129\3\2\2\2\u012c\u012f\3\2\2\2\u012d\u012b\3\2\2\2\u012d\u012e\3\2"+
		"\2\2\u012e\u0131\3\2\2\2\u012f\u012d\3\2\2\2\u0130\u0128\3\2\2\2\u0130"+
		"\u0131\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0133\7:\2\2\u0133\u0135\3\2"+
		"\2\2\u0134\u00fc\3\2\2\2\u0134\u010e\3\2\2\2\u0134\u0120\3\2\2\2\u0135"+
		"\35\3\2\2\2\u0136\u0137\7\f\2\2\u0137\u0138\79\2\2\u0138\u0139\5<\37\2"+
		"\u0139\u013a\7:\2\2\u013a\37\3\2\2\2\u013b\u013c\7\17\2\2\u013c\u014b"+
		"\5\30\r\2\u013d\u013e\7\r\2\2\u013e\u013f\5\"\22\2\u013f\u0140\5\30\r"+
		"\2\u0140\u0142\3\2\2\2\u0141\u013d\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0141"+
		"\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0147\3\2\2\2\u0145\u0146\7\16\2\2"+
		"\u0146\u0148\5\30\r\2\u0147\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148\u014c"+
		"\3\2\2\2\u0149\u014a\7\16\2\2\u014a\u014c\5\30\r\2\u014b\u0141\3\2\2\2"+
		"\u014b\u0149\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u014e\7=\2\2\u014e!\3\2"+
		"\2\2\u014f\u0159\7B\2\2\u0150\u0151\7\23\2\2\u0151\u0156\5\26\f\2\u0152"+
		"\u0153\7 \2\2\u0153\u0155\5\26\f\2\u0154\u0152\3\2\2\2\u0155\u0158\3\2"+
		"\2\2\u0156\u0154\3\2\2\2\u0156\u0157\3\2\2\2\u0157\u015a\3\2\2\2\u0158"+
		"\u0156\3\2\2\2\u0159\u0150\3\2\2\2\u0159\u015a\3\2\2\2\u015a#\3\2\2\2"+
		"\u015b\u015c\7\3\2\2\u015c\u015d\5<\37\2\u015d%\3\2\2\2\u015e\u015f\7"+
		"\26\2\2\u015f\u0160\5<\37\2\u0160\'\3\2\2\2\u0161\u0164\7\36\2\2\u0162"+
		"\u0165\5<\37\2\u0163\u0165\7\27\2\2\u0164\u0162\3\2\2\2\u0164\u0163\3"+
		"\2\2\2\u0165)\3\2\2\2\u0166\u0167\7\60\2\2\u0167\u016c\7B\2\2\u0168\u0169"+
		"\7,\2\2\u0169\u016b\7B\2\2\u016a\u0168\3\2\2\2\u016b\u016e\3\2\2\2\u016c"+
		"\u016a\3\2\2\2\u016c\u016d\3\2\2\2\u016d\u016f\3\2\2\2\u016e\u016c\3\2"+
		"\2\2\u016f\u0170\7.\2\2\u0170+\3\2\2\2\u0171\u0172\7\60\2\2\u0172\u0177"+
		"\5\60\31\2\u0173\u0174\7,\2\2\u0174\u0176\5\60\31\2\u0175\u0173\3\2\2"+
		"\2\u0176\u0179\3\2\2\2\u0177\u0175\3\2\2\2\u0177\u0178\3\2\2\2\u0178\u017a"+
		"\3\2\2\2\u0179\u0177\3\2\2\2\u017a\u017b\7.\2\2\u017b-\3\2\2\2\u017c\u017d"+
		"\5\26\f\2\u017d/\3\2\2\2\u017e\u0180\5\26\f\2\u017f\u0181\5,\27\2\u0180"+
		"\u017f\3\2\2\2\u0180\u0181\3\2\2\2\u0181\u0187\3\2\2\2\u0182\u0187\7$"+
		"\2\2\u0183\u0187\7%\2\2\u0184\u0187\7&\2\2\u0185\u0187\7\'\2\2\u0186\u017e"+
		"\3\2\2\2\u0186\u0182\3\2\2\2\u0186\u0183\3\2\2\2\u0186\u0184\3\2\2\2\u0186"+
		"\u0185\3\2\2\2\u0187\61\3\2\2\2\u0188\u0189\5:\36\2\u0189\u018a\7#\2\2"+
		"\u018a\u018c\3\2\2\2\u018b\u0188\3\2\2\2\u018b\u018c\3\2\2\2\u018c\u018d"+
		"\3\2\2\2\u018d\u018e\7B\2\2\u018e\u01a6\5\64\33\2\u018f\u0194\5\26\f\2"+
		"\u0190\u0191\7#\2\2\u0191\u0192\7\20\2\2\u0192\u0193\7#\2\2\u0193\u0195"+
		"\5\26\f\2\u0194\u0190\3\2\2\2\u0194\u0195\3\2\2\2\u0195\u0196\3\2\2\2"+
		"\u0196\u0197\7#\2\2\u0197\u0198\7B\2\2\u0198\u0199\5\64\33\2\u0199\u01a6"+
		"\3\2\2\2\u019a\u019c\5\16\b\2\u019b\u019a\3\2\2\2\u019b\u019c\3\2\2\2"+
		"\u019c\u019e\3\2\2\2\u019d\u019f\7\6\2\2\u019e\u019d\3\2\2\2\u019e\u019f"+
		"\3\2\2\2\u019f\u01a0\3\2\2\2\u01a0\u01a1\5\60\31\2\u01a1\u01a3\7B\2\2"+
		"\u01a2\u01a4\5\64\33\2\u01a3\u01a2\3\2\2\2\u01a3\u01a4\3\2\2\2\u01a4\u01a6"+
		"\3\2\2\2\u01a5\u018b\3\2\2\2\u01a5\u018f\3\2\2\2\u01a5\u019b\3\2\2\2\u01a6"+
		"\63\3\2\2\2\u01a7\u01a8\7-\2\2\u01a8\u01a9\5<\37\2\u01a9\65\3\2\2\2\u01aa"+
		"\u01ab\7<\2\2\u01ab\u01ac\5<\37\2\u01ac\u01b3\5\30\r\2\u01ad\u01ae\7\7"+
		"\2\2\u01ae\u01af\5<\37\2\u01af\u01b0\5\30\r\2\u01b0\u01b2\3\2\2\2\u01b1"+
		"\u01ad\3\2\2\2\u01b2\u01b5\3\2\2\2\u01b3\u01b1\3\2\2\2\u01b3\u01b4\3\2"+
		"\2\2\u01b4\u01b8\3\2\2\2\u01b5\u01b3\3\2\2\2\u01b6\u01b7\7\34\2\2\u01b7"+
		"\u01b9\5\30\r\2\u01b8\u01b6\3\2\2\2\u01b8\u01b9\3\2\2\2\u01b9\u01ba\3"+
		"\2\2\2\u01ba\u01bb\7=\2\2\u01bb\67\3\2\2\2\u01bc\u01c2\7\33\2\2\u01bd"+
		"\u01be\5<\37\2\u01be\u01bf\7\32\2\2\u01bf\u01c3\3\2\2\2\u01c0\u01c1\t"+
		"\3\2\2\u01c1\u01c3\5<\37\2\u01c2\u01bd\3\2\2\2\u01c2\u01c0\3\2\2\2\u01c3"+
		"\u01c4\3\2\2\2\u01c4\u01c5\5\30\r\2\u01c5\u01c6\7=\2\2\u01c69\3\2\2\2"+
		"\u01c7\u01c8\7\20\2\2\u01c8\u01cb\7#\2\2\u01c9\u01cb\7\b\2\2\u01ca\u01c7"+
		"\3\2\2\2\u01ca\u01c9\3\2\2\2\u01cb;\3\2\2\2\u01cc\u01cd\b\37\1\2\u01cd"+
		"\u01ce\7\63\2\2\u01ce\u0216\5<\37\2\u01cf\u01d0\7)\2\2\u01d0\u0216\5<"+
		"\37\2\u01d1\u01d4\5\26\f\2\u01d2\u01d3\7#\2\2\u01d3\u01d5\7B\2\2\u01d4"+
		"\u01d2\3\2\2\2\u01d4\u01d5\3\2\2\2\u01d5\u0216\3\2\2\2\u01d6\u01d7\5\26"+
		"\f\2\u01d7\u01d8\7#\2\2\u01d8\u01d9\7\20\2\2\u01d9\u01da\7#\2\2\u01da"+
		"\u01db\5\26\f\2\u01db\u01dc\7#\2\2\u01dc\u01dd\7B\2\2\u01dd\u0216\3\2"+
		"\2\2\u01de\u01e1\5\26\f\2\u01df\u01e0\7#\2\2\u01e0\u01e2\7B\2\2\u01e1"+
		"\u01df\3\2\2\2\u01e1\u01e2\3\2\2\2\u01e2\u01e3\3\2\2\2\u01e3\u01e4\79"+
		"\2\2\u01e4\u01e5\5> \2\u01e5\u01e6\7:\2\2\u01e6\u0216\3\2\2\2\u01e7\u01e8"+
		"\5:\36\2\u01e8\u01e9\7#\2\2\u01e9\u01ea\5\26\f\2\u01ea\u0216\3\2\2\2\u01eb"+
		"\u01ec\7\20\2\2\u01ec\u01ed\7#\2\2\u01ed\u01ee\5\26\f\2\u01ee\u01ef\7"+
		"#\2\2\u01ef\u01f0\7B\2\2\u01f0\u01f1\79\2\2\u01f1\u01f2\5> \2\u01f2\u01f3"+
		"\7:\2\2\u01f3\u0216\3\2\2\2\u01f4\u01f5\7\b\2\2\u01f5\u01f6\7#\2\2\u01f6"+
		"\u01f9\5\26\f\2\u01f7\u01f8\7#\2\2\u01f8\u01fa\7B\2\2\u01f9\u01f7\3\2"+
		"\2\2\u01f9\u01fa\3\2\2\2\u01fa\u01fb\3\2\2\2\u01fb\u01fc\79\2\2\u01fc"+
		"\u01fd\5> \2\u01fd\u01fe\7:\2\2\u01fe\u0216\3\2\2\2\u01ff\u0200\7\24\2"+
		"\2\u0200\u0201\79\2\2\u0201\u0202\5\60\31\2\u0202\u0203\7,\2\2\u0203\u0204"+
		"\5<\37\2\u0204\u0205\7:\2\2\u0205\u0216\3\2\2\2\u0206\u0216\7@\2\2\u0207"+
		"\u0216\7?\2\2\u0208\u0216\7A\2\2\u0209\u0216\7C\2\2\u020a\u0216\7!\2\2"+
		"\u020b\u0216\7\b\2\2\u020c\u020d\7\25\2\2\u020d\u020e\79\2\2\u020e\u020f"+
		"\5<\37\2\u020f\u0210\7:\2\2\u0210\u0216\3\2\2\2\u0211\u0212\79\2\2\u0212"+
		"\u0213\5<\37\2\u0213\u0214\7:\2\2\u0214\u0216\3\2\2\2\u0215\u01cc\3\2"+
		"\2\2\u0215\u01cf\3\2\2\2\u0215\u01d1\3\2\2\2\u0215\u01d6\3\2\2\2\u0215"+
		"\u01de\3\2\2\2\u0215\u01e7\3\2\2\2\u0215\u01eb\3\2\2\2\u0215\u01f4\3\2"+
		"\2\2\u0215\u01ff\3\2\2\2\u0215\u0206\3\2\2\2\u0215\u0207\3\2\2\2\u0215"+
		"\u0208\3\2\2\2\u0215\u0209\3\2\2\2\u0215\u020a\3\2\2\2\u0215\u020b\3\2"+
		"\2\2\u0215\u020c\3\2\2\2\u0215\u0211\3\2\2\2\u0216\u022e\3\2\2\2\u0217"+
		"\u0218\6\37\2\3\u0218\u0219\t\4\2\2\u0219\u022d\5<\37\2\u021a\u021b\6"+
		"\37\3\3\u021b\u021c\t\5\2\2\u021c\u022d\5<\37\2\u021d\u021e\6\37\4\3\u021e"+
		"\u021f\t\6\2\2\u021f\u022d\5<\37\2\u0220\u0221\6\37\5\3\u0221\u0222\t"+
		"\7\2\2\u0222\u022d\5<\37\2\u0223\u0224\6\37\6\3\u0224\u0225\7\37\2\2\u0225"+
		"\u022d\5<\37\2\u0226\u0227\6\37\7\3\u0227\u0228\7 \2\2\u0228\u022d\5<"+
		"\37\2\u0229\u022a\6\37\b\3\u022a\u022b\7\23\2\2\u022b\u022d\5.\30\2\u022c"+
		"\u0217\3\2\2\2\u022c\u021a\3\2\2\2\u022c\u021d\3\2\2\2\u022c\u0220\3\2"+
		"\2\2\u022c\u0223\3\2\2\2\u022c\u0226\3\2\2\2\u022c\u0229\3\2\2\2\u022d"+
		"\u0230\3\2\2\2\u022e\u022c\3\2\2\2\u022e\u022f\3\2\2\2\u022f=\3\2\2\2"+
		"\u0230\u022e\3\2\2\2\u0231\u0236\5<\37\2\u0232\u0233\7,\2\2\u0233\u0235"+
		"\5<\37\2\u0234\u0232\3\2\2\2\u0235\u0238\3\2\2\2\u0236\u0234\3\2\2\2\u0236"+
		"\u0237\3\2\2\2\u0237\u023a\3\2\2\2\u0238\u0236\3\2\2\2\u0239\u0231\3\2"+
		"\2\2\u0239\u023a\3\2\2\2\u023a?\3\2\2\2HDIQTbejotw|~\u0083\u0088\u008c"+
		"\u0093\u0096\u00a0\u00a3\u00a6\u00aa\u00b8\u00bb\u00be\u00c2\u00cd\u00d0"+
		"\u00d3\u00d7\u00de\u00e8\u00ee\u00fa\u00ff\u0107\u010a\u0119\u011c\u0125"+
		"\u012d\u0130\u0134\u0143\u0147\u014b\u0156\u0159\u0164\u016c\u0177\u0180"+
		"\u0186\u018b\u0194\u019b\u019e\u01a3\u01a5\u01b3\u01b8\u01c2\u01ca\u01d4"+
		"\u01e1\u01f9\u0215\u022c\u022e\u0236\u0239";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}