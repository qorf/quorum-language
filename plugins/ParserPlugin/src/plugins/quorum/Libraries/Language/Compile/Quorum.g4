grammar Quorum;

start	:
		(package_rule reference+ 
	|	reference+ package_rule
	|	package_rule
	|	reference+
	|	)
	class_declaration  EOF
	;
	
package_rule :	
    PACKAGE_NAME name=qualified_name
    ;
	
reference 
	:	
	USE qualified_name
	;


class_declaration 	
        :	
	(
	CLASS ID
            generic_declaration?
            inherit_stmnts?       
            class_stmnts*
	END
	)
	|
	no_class_stmnts
	;
no_class_stmnts
        :
	statement+
	|(access_modifier? method_declaration)+
	;
inherit_stmnts
	:	INHERITS qualified_name generic_statement? 
        (COMMA qualified_name generic_statement? )*
	;
access_modifier
	:	PUBLIC
	|	PRIVATE
	;	
class_stmnts
	:
	assignment_statement
	|	access_modifier?
	 method_declaration
	;
method_declaration
	:	ACTION ID (LEFT_PAREN (formal_parameter (COMMA formal_parameter)*)? RIGHT_PAREN)?
	(RETURNS return_type = assignment_declaration )? block END 
	|	BLUEPRINT ACTION ID 
	(LEFT_PAREN (formal_parameter (COMMA formal_parameter)*)? RIGHT_PAREN)?
	(RETURNS return_type = assignment_declaration)?
	|	NATIVE ACTION ID 
	(LEFT_PAREN (formal_parameter (COMMA formal_parameter)*)? RIGHT_PAREN)?
	(RETURNS assignment_declaration)?
	| ON CREATE block END
	;
formal_parameter
	:	assignment_declaration ID
	;
	
qualified_name
	:	ids+=ID (PERIOD ids+=ID)* 
	;
block 	:	statement*
	;

statement:
		solo_method_call
	|	if_statement
	|	assignment_statement
	|	loop_statement
	|	return_statement
	|	print_statement
	|	speak_statement
	|	check_statement
	|	alert_statement
	;

solo_method_call 
	:
	qualified_name (COLON ID)? LEFT_PAREN (expression (COMMA expression)*)? RIGHT_PAREN
	|	PARENT COLON qualified_name COLON ID LEFT_PAREN (expression (COMMA expression)*)? RIGHT_PAREN
	|	ME COLON qualified_name (COLON ID)? LEFT_PAREN (expression (COMMA expression)*)? RIGHT_PAREN
	;
	
alert_statement 
	:	ALERT LEFT_PAREN expression RIGHT_PAREN
	;
	
check_statement
	:   CHECK block 
	    ((DETECT 
	    detect_parameter block
	    )+ 
	    (ALWAYS
	    block
	    )? 
	|   ALWAYS 
	    block
	    ) END
	 ;	
    
detect_parameter
	: 	ID (INHERITS  qualified_name(OR qualified_name)*)?
	;
print_statement 
	:	OUTPUT expression
	;

speak_statement 
	:	SAY expression
	;

return_statement
	:	RETURN ( expression | NOW)
	;
	
generic_declaration
	:	LESS ids+=ID (COMMA ids+=ID)* GREATER
	;
generic_statement
	:	
        LESS
	assignment_declaration
	(COMMA assignment_declaration
	)* GREATER
	;
class_type
	:	qualified_name
	;
assignment_declaration
	:	qualified_name generic_statement?
	|	INTEGER_KEYWORD
	|	NUMBER_KEYWORD
	|	TEXT
	|	BOOLEAN_KEYWORD
        ;
assignment_statement
	:	
		(sel = selector COLON)? ID rhs = assign_right_hand_side
	|	obj=qualified_name (COLON PARENT COLON parent=qualified_name)? COLON ID rhs=assign_right_hand_side
	|	modifier = access_modifier?
	CONSTANT? type = assignment_declaration name = ID rhs = assign_right_hand_side?				
	;
assign_right_hand_side
	:	
		(EQUALITY expression)
	;

	
if_statement
	:
	IF expression 
	block
	(ELSE_IF
	expression
	block
	)*  //else if blocks
	(ELSE
	block
	)? 
	END
	;

loop_statement
    :
    REPEAT (
            (expression TIMES)
    |	((WHILE | UNTIL) expression)
            )  block END
    ;
selector
	:	PARENT COLON
	|	ME
	;

expression : 
        qualified_name (COLON ID)?                                                              #VariableOrFieldAccess
    |	qualified_name COLON PARENT COLON qualified_name COLON ID                               #ParentFieldAccess
    |	qualified_name (COLON ID)? LEFT_PAREN function_expression_list RIGHT_PAREN              #ObjectFunctionCall
    |	selector COLON qualified_name                                                           #FieldAccess
    |	PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN    #ParentFunctionCall
    |	ME COLON qualified_name (COLON ID)? LEFT_PAREN function_expression_list RIGHT_PAREN     #MeFunctionCall
    |   MINUS expression                                                                        #Minus
    |   NOT expression                                                                          #Not
    |   CAST LEFT_PAREN assignment_declaration COMMA expression RIGHT_PAREN                     #Cast
    |   expression (MULTIPLY | DIVIDE |MODULO) expression                                       #Multiplication
    |   expression (PLUS | MINUS) expression                                                    #Addition
    |   expression (GREATER | GREATER_EQUAL | LESS | LESS_EQUAL) expression                     #Greater
    |   expression INHERITS class_type                                                          #Inherits
    |   expression (EQUALITY | NOTEQUALS) expression                                            #Equals
    |   expression (AND) expression                                                             #And
    |   expression (OR) expression                                                              #Or
    |   INT                                                                                     #Integer
    |   BOOLEAN                                                                                 #Boolean
    |   DECIMAL                                                                                 #Decimal
    |   STRING                                                                                  #String
    |   NULL                                                                                    #Null
    |   ME                                                                                      #Me
    |   INPUT LEFT_PAREN expression RIGHT_PAREN                                                 #Input
    |   LEFT_PAREN expression RIGHT_PAREN                                                       #ParenthesisExpression
    ;


function_expression_list 
	:
	(expression (COMMA expression)*)?
	;
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


NEWLINE	:	 '\r'?'\n' -> channel(HIDDEN);
WS	:	(' '|'\t'|'\n'|'\r')+ -> channel(HIDDEN);

COMMENTS
    :   ('//' ~('\n'|'\r')* (('\r'? '\n') | EOF)
    |   '/*' .*? '*/') -> channel(HIDDEN)
    ;