// Generated from Quorum.g4 by ANTLR 4.2.2
package plugins.quorum.Libraries.Language.Compile;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QuorumLexer extends Lexer {
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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'output'", "'on'", "'create'", "'constant'", "'elseif'", "'me'", "'until'", 
		"'public'", "'private'", "'alert'", "'detect'", "'always'", "'check'", 
		"'parent'", "'blueprint'", "'system'", "'is'", "'cast'", "'input'", "'say'", 
		"'now'", "'while'", "'package'", "'times'", "'repeat'", "'else'", "'returns'", 
		"'return'", "'and'", "'or'", "'undefined'", "'shared'", "'action'", "':'", 
		"'integer'", "'number'", "'text'", "'boolean'", "'use'", "NOT", "NOTEQUALS", 
		"'.'", "','", "'='", "'>'", "'>='", "'<'", "'<='", "'+'", "'-'", "'*'", 
		"'/'", "'mod'", "'['", "']'", "'('", "')'", "'\"'", "'if'", "'end'", "'class'", 
		"BOOLEAN", "INT", "DECIMAL", "ID", "STRING", "NEWLINE", "WS", "COMMENTS"
	};
	public static final String[] ruleNames = {
		"OUTPUT", "ON", "CREATE", "CONSTANT", "ELSE_IF", "ME", "UNTIL", "PUBLIC", 
		"PRIVATE", "ALERT", "DETECT", "ALWAYS", "CHECK", "PARENT", "BLUEPRINT", 
		"NATIVE", "INHERITS", "CAST", "INPUT", "SAY", "NOW", "WHILE", "PACKAGE_NAME", 
		"TIMES", "REPEAT", "ELSE", "RETURNS", "RETURN", "AND", "OR", "NULL", "STATIC", 
		"ACTION", "COLON", "INTEGER_KEYWORD", "NUMBER_KEYWORD", "TEXT", "BOOLEAN_KEYWORD", 
		"USE", "NOT", "NOTEQUALS", "PERIOD", "COMMA", "EQUALITY", "GREATER", "GREATER_EQUAL", 
		"LESS", "LESS_EQUAL", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", "MODULO", 
		"LEFT_SQR_BRACE", "RIGHT_SQR_BRACE", "LEFT_PAREN", "RIGHT_PAREN", "DOUBLE_QUOTE", 
		"IF", "END", "CLASS", "BOOLEAN", "INT", "DECIMAL", "ID", "STRING", "NEWLINE", 
		"WS", "COMMENTS"
	};


	    public static final int WHITESPACE_CHANNEL = 1000;
	    public static final int COMMENT_CHANNEL = 1001;


	public QuorumLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Quorum.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 66: NEWLINE_action((RuleContext)_localctx, actionIndex); break;

		case 67: WS_action((RuleContext)_localctx, actionIndex); break;

		case 68: COMMENTS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: _channel = WHITESPACE_CHANNEL; break;
		}
	}
	private void COMMENTS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: _channel = COMMENT_CHANNEL; break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: _channel = WHITESPACE_CHANNEL; break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2G\u0219\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3"+
		"\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3"+
		"\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3"+
		" \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3"+
		"#\3#\3$\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3"+
		"\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\5)\u0185\n)\3"+
		"*\3*\3*\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3/\3\60\3\60\3\61\3\61\3\61"+
		"\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\66\3\66\3\67\3\67"+
		"\38\38\39\39\3:\3:\3;\3;\3<\3<\3<\3=\3=\3=\3=\3>\3>\3>\3>\3>\3>\3?\3?"+
		"\3?\3?\3?\3?\3?\3?\3?\5?\u01c8\n?\3@\6@\u01cb\n@\r@\16@\u01cc\3A\6A\u01d0"+
		"\nA\rA\16A\u01d1\3A\3A\7A\u01d6\nA\fA\16A\u01d9\13A\5A\u01db\nA\3B\3B"+
		"\7B\u01df\nB\fB\16B\u01e2\13B\3C\3C\7C\u01e6\nC\fC\16C\u01e9\13C\3C\3"+
		"C\3D\5D\u01ee\nD\3D\3D\3D\3D\3E\6E\u01f5\nE\rE\16E\u01f6\3E\3E\3F\3F\3"+
		"F\3F\7F\u01ff\nF\fF\16F\u0202\13F\3F\5F\u0205\nF\3F\3F\5F\u0209\nF\3F"+
		"\3F\3F\3F\7F\u020f\nF\fF\16F\u0212\13F\3F\3F\5F\u0216\nF\3F\3F\4\u01e7"+
		"\u0210\2G\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
		"\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67"+
		"\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65"+
		"i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081B\u0083C\u0085D\u0087E\u0089F\u008b"+
		"G\3\2\7\4\2PPpp\4\2C\\c|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\4\2\f\f\17"+
		"\17\u0227\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S"+
		"\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2"+
		"\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2"+
		"\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y"+
		"\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3"+
		"\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2"+
		"\3\u008d\3\2\2\2\5\u0094\3\2\2\2\7\u0097\3\2\2\2\t\u009e\3\2\2\2\13\u00a7"+
		"\3\2\2\2\r\u00ae\3\2\2\2\17\u00b1\3\2\2\2\21\u00b7\3\2\2\2\23\u00be\3"+
		"\2\2\2\25\u00c6\3\2\2\2\27\u00cc\3\2\2\2\31\u00d3\3\2\2\2\33\u00da\3\2"+
		"\2\2\35\u00e0\3\2\2\2\37\u00e7\3\2\2\2!\u00f1\3\2\2\2#\u00f8\3\2\2\2%"+
		"\u00fb\3\2\2\2\'\u0100\3\2\2\2)\u0106\3\2\2\2+\u010a\3\2\2\2-\u010e\3"+
		"\2\2\2/\u0114\3\2\2\2\61\u011c\3\2\2\2\63\u0122\3\2\2\2\65\u0129\3\2\2"+
		"\2\67\u012e\3\2\2\29\u0136\3\2\2\2;\u013d\3\2\2\2=\u0141\3\2\2\2?\u0144"+
		"\3\2\2\2A\u014e\3\2\2\2C\u0155\3\2\2\2E\u015c\3\2\2\2G\u015e\3\2\2\2I"+
		"\u0166\3\2\2\2K\u016d\3\2\2\2M\u0172\3\2\2\2O\u017a\3\2\2\2Q\u0184\3\2"+
		"\2\2S\u0186\3\2\2\2U\u018b\3\2\2\2W\u018d\3\2\2\2Y\u018f\3\2\2\2[\u0191"+
		"\3\2\2\2]\u0193\3\2\2\2_\u0196\3\2\2\2a\u0198\3\2\2\2c\u019b\3\2\2\2e"+
		"\u019d\3\2\2\2g\u019f\3\2\2\2i\u01a1\3\2\2\2k\u01a3\3\2\2\2m\u01a7\3\2"+
		"\2\2o\u01a9\3\2\2\2q\u01ab\3\2\2\2s\u01ad\3\2\2\2u\u01af\3\2\2\2w\u01b1"+
		"\3\2\2\2y\u01b4\3\2\2\2{\u01b8\3\2\2\2}\u01c7\3\2\2\2\177\u01ca\3\2\2"+
		"\2\u0081\u01cf\3\2\2\2\u0083\u01dc\3\2\2\2\u0085\u01e3\3\2\2\2\u0087\u01ed"+
		"\3\2\2\2\u0089\u01f4\3\2\2\2\u008b\u0215\3\2\2\2\u008d\u008e\7q\2\2\u008e"+
		"\u008f\7w\2\2\u008f\u0090\7v\2\2\u0090\u0091\7r\2\2\u0091\u0092\7w\2\2"+
		"\u0092\u0093\7v\2\2\u0093\4\3\2\2\2\u0094\u0095\7q\2\2\u0095\u0096\7p"+
		"\2\2\u0096\6\3\2\2\2\u0097\u0098\7e\2\2\u0098\u0099\7t\2\2\u0099\u009a"+
		"\7g\2\2\u009a\u009b\7c\2\2\u009b\u009c\7v\2\2\u009c\u009d\7g\2\2\u009d"+
		"\b\3\2\2\2\u009e\u009f\7e\2\2\u009f\u00a0\7q\2\2\u00a0\u00a1\7p\2\2\u00a1"+
		"\u00a2\7u\2\2\u00a2\u00a3\7v\2\2\u00a3\u00a4\7c\2\2\u00a4\u00a5\7p\2\2"+
		"\u00a5\u00a6\7v\2\2\u00a6\n\3\2\2\2\u00a7\u00a8\7g\2\2\u00a8\u00a9\7n"+
		"\2\2\u00a9\u00aa\7u\2\2\u00aa\u00ab\7g\2\2\u00ab\u00ac\7k\2\2\u00ac\u00ad"+
		"\7h\2\2\u00ad\f\3\2\2\2\u00ae\u00af\7o\2\2\u00af\u00b0\7g\2\2\u00b0\16"+
		"\3\2\2\2\u00b1\u00b2\7w\2\2\u00b2\u00b3\7p\2\2\u00b3\u00b4\7v\2\2\u00b4"+
		"\u00b5\7k\2\2\u00b5\u00b6\7n\2\2\u00b6\20\3\2\2\2\u00b7\u00b8\7r\2\2\u00b8"+
		"\u00b9\7w\2\2\u00b9\u00ba\7d\2\2\u00ba\u00bb\7n\2\2\u00bb\u00bc\7k\2\2"+
		"\u00bc\u00bd\7e\2\2\u00bd\22\3\2\2\2\u00be\u00bf\7r\2\2\u00bf\u00c0\7"+
		"t\2\2\u00c0\u00c1\7k\2\2\u00c1\u00c2\7x\2\2\u00c2\u00c3\7c\2\2\u00c3\u00c4"+
		"\7v\2\2\u00c4\u00c5\7g\2\2\u00c5\24\3\2\2\2\u00c6\u00c7\7c\2\2\u00c7\u00c8"+
		"\7n\2\2\u00c8\u00c9\7g\2\2\u00c9\u00ca\7t\2\2\u00ca\u00cb\7v\2\2\u00cb"+
		"\26\3\2\2\2\u00cc\u00cd\7f\2\2\u00cd\u00ce\7g\2\2\u00ce\u00cf\7v\2\2\u00cf"+
		"\u00d0\7g\2\2\u00d0\u00d1\7e\2\2\u00d1\u00d2\7v\2\2\u00d2\30\3\2\2\2\u00d3"+
		"\u00d4\7c\2\2\u00d4\u00d5\7n\2\2\u00d5\u00d6\7y\2\2\u00d6\u00d7\7c\2\2"+
		"\u00d7\u00d8\7{\2\2\u00d8\u00d9\7u\2\2\u00d9\32\3\2\2\2\u00da\u00db\7"+
		"e\2\2\u00db\u00dc\7j\2\2\u00dc\u00dd\7g\2\2\u00dd\u00de\7e\2\2\u00de\u00df"+
		"\7m\2\2\u00df\34\3\2\2\2\u00e0\u00e1\7r\2\2\u00e1\u00e2\7c\2\2\u00e2\u00e3"+
		"\7t\2\2\u00e3\u00e4\7g\2\2\u00e4\u00e5\7p\2\2\u00e5\u00e6\7v\2\2\u00e6"+
		"\36\3\2\2\2\u00e7\u00e8\7d\2\2\u00e8\u00e9\7n\2\2\u00e9\u00ea\7w\2\2\u00ea"+
		"\u00eb\7g\2\2\u00eb\u00ec\7r\2\2\u00ec\u00ed\7t\2\2\u00ed\u00ee\7k\2\2"+
		"\u00ee\u00ef\7p\2\2\u00ef\u00f0\7v\2\2\u00f0 \3\2\2\2\u00f1\u00f2\7u\2"+
		"\2\u00f2\u00f3\7{\2\2\u00f3\u00f4\7u\2\2\u00f4\u00f5\7v\2\2\u00f5\u00f6"+
		"\7g\2\2\u00f6\u00f7\7o\2\2\u00f7\"\3\2\2\2\u00f8\u00f9\7k\2\2\u00f9\u00fa"+
		"\7u\2\2\u00fa$\3\2\2\2\u00fb\u00fc\7e\2\2\u00fc\u00fd\7c\2\2\u00fd\u00fe"+
		"\7u\2\2\u00fe\u00ff\7v\2\2\u00ff&\3\2\2\2\u0100\u0101\7k\2\2\u0101\u0102"+
		"\7p\2\2\u0102\u0103\7r\2\2\u0103\u0104\7w\2\2\u0104\u0105\7v\2\2\u0105"+
		"(\3\2\2\2\u0106\u0107\7u\2\2\u0107\u0108\7c\2\2\u0108\u0109\7{\2\2\u0109"+
		"*\3\2\2\2\u010a\u010b\7p\2\2\u010b\u010c\7q\2\2\u010c\u010d\7y\2\2\u010d"+
		",\3\2\2\2\u010e\u010f\7y\2\2\u010f\u0110\7j\2\2\u0110\u0111\7k\2\2\u0111"+
		"\u0112\7n\2\2\u0112\u0113\7g\2\2\u0113.\3\2\2\2\u0114\u0115\7r\2\2\u0115"+
		"\u0116\7c\2\2\u0116\u0117\7e\2\2\u0117\u0118\7m\2\2\u0118\u0119\7c\2\2"+
		"\u0119\u011a\7i\2\2\u011a\u011b\7g\2\2\u011b\60\3\2\2\2\u011c\u011d\7"+
		"v\2\2\u011d\u011e\7k\2\2\u011e\u011f\7o\2\2\u011f\u0120\7g\2\2\u0120\u0121"+
		"\7u\2\2\u0121\62\3\2\2\2\u0122\u0123\7t\2\2\u0123\u0124\7g\2\2\u0124\u0125"+
		"\7r\2\2\u0125\u0126\7g\2\2\u0126\u0127\7c\2\2\u0127\u0128\7v\2\2\u0128"+
		"\64\3\2\2\2\u0129\u012a\7g\2\2\u012a\u012b\7n\2\2\u012b\u012c\7u\2\2\u012c"+
		"\u012d\7g\2\2\u012d\66\3\2\2\2\u012e\u012f\7t\2\2\u012f\u0130\7g\2\2\u0130"+
		"\u0131\7v\2\2\u0131\u0132\7w\2\2\u0132\u0133\7t\2\2\u0133\u0134\7p\2\2"+
		"\u0134\u0135\7u\2\2\u01358\3\2\2\2\u0136\u0137\7t\2\2\u0137\u0138\7g\2"+
		"\2\u0138\u0139\7v\2\2\u0139\u013a\7w\2\2\u013a\u013b\7t\2\2\u013b\u013c"+
		"\7p\2\2\u013c:\3\2\2\2\u013d\u013e\7c\2\2\u013e\u013f\7p\2\2\u013f\u0140"+
		"\7f\2\2\u0140<\3\2\2\2\u0141\u0142\7q\2\2\u0142\u0143\7t\2\2\u0143>\3"+
		"\2\2\2\u0144\u0145\7w\2\2\u0145\u0146\7p\2\2\u0146\u0147\7f\2\2\u0147"+
		"\u0148\7g\2\2\u0148\u0149\7h\2\2\u0149\u014a\7k\2\2\u014a\u014b\7p\2\2"+
		"\u014b\u014c\7g\2\2\u014c\u014d\7f\2\2\u014d@\3\2\2\2\u014e\u014f\7u\2"+
		"\2\u014f\u0150\7j\2\2\u0150\u0151\7c\2\2\u0151\u0152\7t\2\2\u0152\u0153"+
		"\7g\2\2\u0153\u0154\7f\2\2\u0154B\3\2\2\2\u0155\u0156\7c\2\2\u0156\u0157"+
		"\7e\2\2\u0157\u0158\7v\2\2\u0158\u0159\7k\2\2\u0159\u015a\7q\2\2\u015a"+
		"\u015b\7p\2\2\u015bD\3\2\2\2\u015c\u015d\7<\2\2\u015dF\3\2\2\2\u015e\u015f"+
		"\7k\2\2\u015f\u0160\7p\2\2\u0160\u0161\7v\2\2\u0161\u0162\7g\2\2\u0162"+
		"\u0163\7i\2\2\u0163\u0164\7g\2\2\u0164\u0165\7t\2\2\u0165H\3\2\2\2\u0166"+
		"\u0167\7p\2\2\u0167\u0168\7w\2\2\u0168\u0169\7o\2\2\u0169\u016a\7d\2\2"+
		"\u016a\u016b\7g\2\2\u016b\u016c\7t\2\2\u016cJ\3\2\2\2\u016d\u016e\7v\2"+
		"\2\u016e\u016f\7g\2\2\u016f\u0170\7z\2\2\u0170\u0171\7v\2\2\u0171L\3\2"+
		"\2\2\u0172\u0173\7d\2\2\u0173\u0174\7q\2\2\u0174\u0175\7q\2\2\u0175\u0176"+
		"\7n\2\2\u0176\u0177\7g\2\2\u0177\u0178\7c\2\2\u0178\u0179\7p\2\2\u0179"+
		"N\3\2\2\2\u017a\u017b\7w\2\2\u017b\u017c\7u\2\2\u017c\u017d\7g\2\2\u017d"+
		"P\3\2\2\2\u017e\u017f\7p\2\2\u017f\u0180\7q\2\2\u0180\u0185\7v\2\2\u0181"+
		"\u0182\7P\2\2\u0182\u0183\7q\2\2\u0183\u0185\7v\2\2\u0184\u017e\3\2\2"+
		"\2\u0184\u0181\3\2\2\2\u0185R\3\2\2\2\u0186\u0187\t\2\2\2\u0187\u0188"+
		"\7q\2\2\u0188\u0189\7v\2\2\u0189\u018a\7?\2\2\u018aT\3\2\2\2\u018b\u018c"+
		"\7\60\2\2\u018cV\3\2\2\2\u018d\u018e\7.\2\2\u018eX\3\2\2\2\u018f\u0190"+
		"\7?\2\2\u0190Z\3\2\2\2\u0191\u0192\7@\2\2\u0192\\\3\2\2\2\u0193\u0194"+
		"\7@\2\2\u0194\u0195\7?\2\2\u0195^\3\2\2\2\u0196\u0197\7>\2\2\u0197`\3"+
		"\2\2\2\u0198\u0199\7>\2\2\u0199\u019a\7?\2\2\u019ab\3\2\2\2\u019b\u019c"+
		"\7-\2\2\u019cd\3\2\2\2\u019d\u019e\7/\2\2\u019ef\3\2\2\2\u019f\u01a0\7"+
		",\2\2\u01a0h\3\2\2\2\u01a1\u01a2\7\61\2\2\u01a2j\3\2\2\2\u01a3\u01a4\7"+
		"o\2\2\u01a4\u01a5\7q\2\2\u01a5\u01a6\7f\2\2\u01a6l\3\2\2\2\u01a7\u01a8"+
		"\7]\2\2\u01a8n\3\2\2\2\u01a9\u01aa\7_\2\2\u01aap\3\2\2\2\u01ab\u01ac\7"+
		"*\2\2\u01acr\3\2\2\2\u01ad\u01ae\7+\2\2\u01aet\3\2\2\2\u01af\u01b0\7$"+
		"\2\2\u01b0v\3\2\2\2\u01b1\u01b2\7k\2\2\u01b2\u01b3\7h\2\2\u01b3x\3\2\2"+
		"\2\u01b4\u01b5\7g\2\2\u01b5\u01b6\7p\2\2\u01b6\u01b7\7f\2\2\u01b7z\3\2"+
		"\2\2\u01b8\u01b9\7e\2\2\u01b9\u01ba\7n\2\2\u01ba\u01bb\7c\2\2\u01bb\u01bc"+
		"\7u\2\2\u01bc\u01bd\7u\2\2\u01bd|\3\2\2\2\u01be\u01bf\7v\2\2\u01bf\u01c0"+
		"\7t\2\2\u01c0\u01c1\7w\2\2\u01c1\u01c8\7g\2\2\u01c2\u01c3\7h\2\2\u01c3"+
		"\u01c4\7c\2\2\u01c4\u01c5\7n\2\2\u01c5\u01c6\7u\2\2\u01c6\u01c8\7g\2\2"+
		"\u01c7\u01be\3\2\2\2\u01c7\u01c2\3\2\2\2\u01c8~\3\2\2\2\u01c9\u01cb\4"+
		"\62;\2\u01ca\u01c9\3\2\2\2\u01cb\u01cc\3\2\2\2\u01cc\u01ca\3\2\2\2\u01cc"+
		"\u01cd\3\2\2\2\u01cd\u0080\3\2\2\2\u01ce\u01d0\4\62;\2\u01cf\u01ce\3\2"+
		"\2\2\u01d0\u01d1\3\2\2\2\u01d1\u01cf\3\2\2\2\u01d1\u01d2\3\2\2\2\u01d2"+
		"\u01da\3\2\2\2\u01d3\u01d7\5U+\2\u01d4\u01d6\4\62;\2\u01d5\u01d4\3\2\2"+
		"\2\u01d6\u01d9\3\2\2\2\u01d7\u01d5\3\2\2\2\u01d7\u01d8\3\2\2\2\u01d8\u01db"+
		"\3\2\2\2\u01d9\u01d7\3\2\2\2\u01da\u01d3\3\2\2\2\u01da\u01db\3\2\2\2\u01db"+
		"\u0082\3\2\2\2\u01dc\u01e0\t\3\2\2\u01dd\u01df\t\4\2\2\u01de\u01dd\3\2"+
		"\2\2\u01df\u01e2\3\2\2\2\u01e0\u01de\3\2\2\2\u01e0\u01e1\3\2\2\2\u01e1"+
		"\u0084\3\2\2\2\u01e2\u01e0\3\2\2\2\u01e3\u01e7\5u;\2\u01e4\u01e6\13\2"+
		"\2\2\u01e5\u01e4\3\2\2\2\u01e6\u01e9\3\2\2\2\u01e7\u01e8\3\2\2\2\u01e7"+
		"\u01e5\3\2\2\2\u01e8\u01ea\3\2\2\2\u01e9\u01e7\3\2\2\2\u01ea\u01eb\5u"+
		";\2\u01eb\u0086\3\2\2\2\u01ec\u01ee\7\17\2\2\u01ed\u01ec\3\2\2\2\u01ed"+
		"\u01ee\3\2\2\2\u01ee\u01ef\3\2\2\2\u01ef\u01f0\7\f\2\2\u01f0\u01f1\3\2"+
		"\2\2\u01f1\u01f2\bD\2\2\u01f2\u0088\3\2\2\2\u01f3\u01f5\t\5\2\2\u01f4"+
		"\u01f3\3\2\2\2\u01f5\u01f6\3\2\2\2\u01f6\u01f4\3\2\2\2\u01f6\u01f7\3\2"+
		"\2\2\u01f7\u01f8\3\2\2\2\u01f8\u01f9\bE\3\2\u01f9\u008a\3\2\2\2\u01fa"+
		"\u01fb\7\61\2\2\u01fb\u01fc\7\61\2\2\u01fc\u0200\3\2\2\2\u01fd\u01ff\n"+
		"\6\2\2\u01fe\u01fd\3\2\2\2\u01ff\u0202\3\2\2\2\u0200\u01fe\3\2\2\2\u0200"+
		"\u0201\3\2\2\2\u0201\u0208\3\2\2\2\u0202\u0200\3\2\2\2\u0203\u0205\7\17"+
		"\2\2\u0204\u0203\3\2\2\2\u0204\u0205\3\2\2\2\u0205\u0206\3\2\2\2\u0206"+
		"\u0209\7\f\2\2\u0207\u0209\7\2\2\3\u0208\u0204\3\2\2\2\u0208\u0207\3\2"+
		"\2\2\u0209\u0216\3\2\2\2\u020a\u020b\7\61\2\2\u020b\u020c\7,\2\2\u020c"+
		"\u0210\3\2\2\2\u020d\u020f\13\2\2\2\u020e\u020d\3\2\2\2\u020f\u0212\3"+
		"\2\2\2\u0210\u0211\3\2\2\2\u0210\u020e\3\2\2\2\u0211\u0213\3\2\2\2\u0212"+
		"\u0210\3\2\2\2\u0213\u0214\7,\2\2\u0214\u0216\7\61\2\2\u0215\u01fa\3\2"+
		"\2\2\u0215\u020a\3\2\2\2\u0216\u0217\3\2\2\2\u0217\u0218\bF\4\2\u0218"+
		"\u008c\3\2\2\2\22\2\u0184\u01c7\u01cc\u01d1\u01d7\u01da\u01e0\u01e7\u01ed"+
		"\u01f6\u0200\u0204\u0208\u0210\u0215\5\3D\2\3E\3\3F\4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}