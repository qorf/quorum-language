
lexer grammar QuorumLexer;

channels { WHITESPACE_CHANNEL, COMMENT_CHANNEL }

OUTPUT	:	'output';
ON	:	'on';
CREATE	:	'create';
CONSTANT:	'constant';
ELSE_IF :	'elseif';
ME	:	'me';
UNTIL	:	'until';
PUBLIC	:	'public';
PRIVATE	:	'private';	
ALERT	:	'alert';
DETECT	:	'detect';
ALWAYS	:	'always';
CHECK	:	'check';
PARENT	:	'parent';
BLUEPRINT :	'blueprint';
NATIVE :	'system';
INHERITS :	'is';
CAST	:	'cast';
INPUT	:	'input';
SAY	:	'say';
NOW	:	'now';
WHILE	:	'while';
PACKAGE_NAME :	'package';
TIMES	:	'times';
REPEAT	:	'repeat';
ELSE 	:	'else';
RETURNS :	'returns';
RETURN 	:	'return';
AND	:	'and';
OR 	:	'or';
NULL	:	'undefined';
STATIC  :       'shared';
ACTION 
	:	'action'
	;
COLON	:	':'
	;
INTEGER_KEYWORD	
	:	'integer';
NUMBER_KEYWORD	
	:	'number';
TEXT	
	:	'text';
BOOLEAN_KEYWORD	
	:	'boolean';	
USE 	:	'use'
	;
NOT	:	'not' | 'Not';
NOTEQUALS
	:	('n' | 'N' ) 'ot=';
PERIOD	:	'.';
COMMA	:	',';
EQUALITY:	'=';
GREATER	:	'>';
GREATER_EQUAL
	:	'>=';
LESS	:	'<';
LESS_EQUAL 
	:	'<=';
PLUS	:	'+';
MINUS	:	'-';
MULTIPLY:	'*';
DIVIDE	:	'/';
MODULO	:	'mod';
LEFT_SQR_BRACE
	:	'[';
RIGHT_SQR_BRACE
	:	']';	
LEFT_PAREN
	:	'(';
RIGHT_PAREN
	:	')';
DOUBLE_QUOTE
	:	'"';
IF	:	'if';
END	:	'end';
CLASS	:	'class';
BOOLEAN	:	'true' | 'false';
INT 	:	'0'..'9'+;
DECIMAL	:	'0'..'9'+ (PERIOD ('0'..'9')*)?;	
ID 	: 	('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9' | '_')*;
STRING	:	DOUBLE_QUOTE .*? DOUBLE_QUOTE;


NEWLINE	:	 '\r'?'\n' -> channel(WHITESPACE_CHANNEL);
WS	:	(' '|'\t'|'\n'|'\r')+ -> channel(WHITESPACE_CHANNEL);

COMMENTS
    :   ('//' ~('\n'|'\r')* (('\r'? '\n') | EOF)
    |   '/*' .*? '*/') -> channel(COMMENT_CHANNEL)
    ;