parser grammar QuorumParser;

options { tokenVocab = QuorumLexer; }

start	:
		(package_rule reference+ 
	|	reference+ package_rule
	|	package_rule
	|	reference+
	|	)
	class_declaration  EOF
	;
	
package_rule : PACKAGE_NAME name=qualified_name;
	
reference : USE name=qualified_name ;


class_declaration 	
        :	
	(
	STATIC? CLASS ID
            generic_declaration?
            inherit_stmnts?       
            class_stmnts*
	END                         
	)                           #FullClassDeclaration
	|   no_class_stmnts         #NoClassDeclaration
	;
no_class_stmnts
        :
	statement+                              #NoActionsNoClass
	|(access_modifier? method_declaration)+ #ActionsNoClass
	;
inherit_stmnts
	:	INHERITS inherit_stmt 
        (COMMA inherit_stmt )*
	;
inherit_stmt
        :
            qualified_name generic_statement? 
        ;
access_modifier
	:	PUBLIC
	|	PRIVATE
	;	
class_stmnts
	:
            assignment_statement
        |   method_declaration
	;

method_declaration
	:	modifier = access_modifier? method_shared block END     #Action
	|	modifier = access_modifier? BLUEPRINT method_shared     #BlueprintAction
	|	modifier = access_modifier? NATIVE method_shared        #NativeAction
	| ON CREATE block END                                           #Constructor
	;

method_shared returns [quorum.Libraries.Language.Compile.Context.ActionContext actionContext]
        :
        ACTION ID (LEFT_PAREN (formal_parameter (COMMA formal_parameter)*)? RIGHT_PAREN)? (RETURNS return_type = assignment_declaration )?
        ;

formal_parameter
	:	assignment_declaration ID
	;
	
qualified_name returns [quorum.Libraries.Language.Compile.QualifiedName qualifiedName]
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
            (ME COLON)? (object=ID COLON)? (action_call COLON)* solo_method_required_method_part #VariableSoloFunctionCall   
        |   ((ME COLON)? (fieldName=ID COLON))? PARENT COLON parent=qualified_name 
                COLON initial_parent_action_call (COLON (action_call))* #ParentVariableSoloFunctionCall
	;

solo_method_required_method_part
    :
        var=ID LEFT_PAREN function_expression_list RIGHT_PAREN
    ;

alert_statement 
	:	ALERT LEFT_PAREN expression RIGHT_PAREN
	;
	
check_statement
	:   CHECK block 
	    (
                    (detect_statement)+ (always_statement)? 
                |   always_statement
            ) 
            END
	 ;	
    
detect_statement
	: 	DETECT name=ID (INHERITS  qualified_name (OR qualified_name)*)? block
	;

always_statement
        :   ALWAYS block
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

assignment_declaration returns [quorum.Libraries.Language.Compile.Symbol.Type type]
	:	qualified_name generic_statement?   #GenericAssignmentDeclaration
	|	INTEGER_KEYWORD                     #IntegerAssignmentDeclaration
	|	NUMBER_KEYWORD                      #NumberAssignmentDeclaration
	|	TEXT                                #TextAssignmentDeclaration
	|	BOOLEAN_KEYWORD                     #BooleanAssignmentDeclaration
        ;

assignment_statement
	:	
            (ME COLON)? name = ID EQUALITY rhs = expression  #NoTypeAssignment
        |   PARENT COLON parent=qualified_name COLON name = ID EQUALITY rhs = expression #ParentAssignment
	|   object=ID (COLON PARENT COLON parent=qualified_name)? COLON name = ID EQUALITY rhs = expression #ObjectAssignment
	|   modifier = access_modifier? CONSTANT? type = assignment_declaration name = ID (EQUALITY rhs = expression)? #NormalAssignment	
	;
	
if_statement
	:
	IF expression 
	block
	(elseif_statement)*
	(else_statement
	)? 
	END
	;

elseif_statement
    :
    ELSE_IF
    expression
    block
    ;

else_statement
    :
    ELSE block
    ;

loop_statement
    :
    REPEAT (
            (expression TIMES)
    |	((WHILE | UNTIL) expression)
            )  block END
    ;

initial_parent_action_call
    :   var=ID (LEFT_PAREN function_expression_list RIGHT_PAREN)?
    ;

action_call
    :   var=ID (LEFT_PAREN function_expression_list RIGHT_PAREN)?
    ;

expression
    :
        LEFT_PAREN expression RIGHT_PAREN                                                       #ParenthesisExpression
    |   INT                                                                                     #Integer
    |   BOOLEAN                                                                                 #Boolean
    |   DECIMAL                                                                                 #Decimal
    |   STRING                                                                                  #String
    |   NULL                                                                                    #Null
    |   ME                                                                                      #Me
    |   INPUT LEFT_PAREN expression RIGHT_PAREN                                                 #Input
    |   INPUT LEFT_PAREN RIGHT_PAREN                                                            #InputNoParameters
    |   (ME COLON)? action_call (COLON (action_call))*                                          #VariableFunctionCall
    |   ((ME COLON)? (fieldName=ID COLON))? PARENT COLON parent=qualified_name COLON initial_parent_action_call (COLON (action_call))* #ParentVariableFunctionCall
    |   MINUS expression                                                                        #Minus
    |   NOT expression                                                                          #Not
    |   CAST LEFT_PAREN type=assignment_declaration COMMA expression RIGHT_PAREN                #Cast
    |   expression (MULTIPLY | DIVIDE |MODULO) expression                                       #Multiplication
    |   expression (PLUS | MINUS) expression                                                    #Addition
    |   expression (GREATER | GREATER_EQUAL | LESS | LESS_EQUAL) expression                     #Greater
    |   expression INHERITS name=class_type                                                     #Inherits
    |   expression (EQUALITY | NOTEQUALS) expression                                            #Equals
    |   expression (AND) expression                                                             #And
    |   expression (OR) expression                                                              #Or
    ;


function_expression_list 
	:
	(expression (COMMA expression)*)?
	;