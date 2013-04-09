grammar Quorum;

options {
	output=AST;
	ASTLabelType = CommonTree;
}

tokens { //these are imaginary tokens --- they are inserted to help processing. More info in antlr book
	FUNCTION_CALL;
	FUNCTION_CALL_PARENT;
	FUNCTION_CALL_THIS;
	FUNCTION_EXPRESSION_LIST;
	SOLO_FUNCTION_CALL;
	SOLO_FUNCTION_CALL_PARENT;
	SOLO_FUNCTION_CALL_THIS;
	QUALIFIED_NAME;
	EXPRESSION_STATEMENT;
	STATEMENT_LIST;
	CONSTRUCTOR;
	FPARAM;
	UNARY_NOT;
	ELSE_IF_STATEMENT;
	FINAL_ELSE;
	PAREN_WRAPPED_EXPRESSION;
	ROOT_EXPRESSION;
	QUALIFIED_SOLO_EXPRESSION;
	QUALIFIED_SOLO_EXPRESSION_SELECTOR;
	QUALIFIED_SOLO_PARENT_EXPRESSON;
	GENERIC;
}

@header {


package org.quorum.parser;

import org.quorum.symbols.*;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import org.quorum.vm.interfaces.CompilerErrorManager;
import org.quorum.vm.interfaces.CompilerError;
import java.util.Iterator;
import java.util.Vector;
import org.antlr.runtime.tree.CommonTree;
import org.quorum.execution.ScopeSelector;
import org.quorum.vm.interfaces.ErrorType;
}
@lexer::header {package org.quorum.parser;
import org.quorum.vm.implementation.QuorumVirtualMachine;
import org.quorum.vm.interfaces.CompilerErrorManager;
import org.quorum.vm.interfaces.CompilerError;
import org.quorum.vm.interfaces.ErrorType;
}

@lexer::members {
	public static final int HIDDEN_DOCUMENTATION = 100;
	QuorumVirtualMachine vm;
	String fileName;
	public void setQuorumVirtualMachine(QuorumVirtualMachine m) {
		vm = m;
	}
	
	public String getGrammarFileNameNoExtension() {
		return fileName;
	}
	
	public void setGrammarFileNameNoExtension(String name) {
		fileName = name;
	}
	
	public void emitErrorMessage(String msg) {
		//super.emitErrorMessage(msg);
 	}
	public void recover(RecognitionException re) {
		if(vm != null) {
			String message = super.getErrorMessage(re, null);
			CompilerError error = new CompilerError();
			if(re instanceof NoViableAltException){
    				message = "Incomplete or invalid statement at " + super.getCharErrorDisplay(re.c);
    				error.setErrorType(ErrorType.OTHER);
    			}
			
			error.setLineNumber(re.line);
			error.setColumn(re.charPositionInLine);
			error.setError(message);
			error.setFile(fileName);
			CompilerErrorManager ces = vm.getCompilerErrors();
			ces.addError(error);
		}
		super.recover(re);
	}

}

@members{
	public static final int HIDDEN_DOCUMENTATION = 100;
	QuorumVirtualMachine vm;
	AccessModifierEnum accessModifier;
	SymbolTable symbol;
	QualifiedNameDescriptor thisPackage;
	Vector<QualifiedNameDescriptor> uses = new Vector<QualifiedNameDescriptor>();
	
	String fileName;
	boolean classWithNoName = false;
	boolean isInClassAssignmentStatementScope = false;
	boolean isInConstructorScope = false;
	
	public void setQuorumVirtualMachine(QuorumVirtualMachine m) {
		vm = m;
		symbol = vm.getSymbolTable();
	}
	
	public String getGrammarFileNameNoExtension() {
		return fileName;
	}
	
	public void setGrammarFileNameNoExtension(String name) {
		fileName = name;
	}
	
	public void emitErrorMessage(String msg) {
		//super.emitErrorMessage(msg);
 	}

    @Override
    public String getErrorMessage(RecognitionException re, String[] tokenNames) {
        String message = re.getMessage();
        CompilerError error = new CompilerError();
        
        if (re instanceof UnwantedTokenException) {
            UnwantedTokenException ute = (UnwantedTokenException) re;
            String tokenName = "<unknown>";
            if (ute.expecting == Token.EOF) {
                tokenName = "EOF";
                message = "extraneous input " + getTokenErrorDisplay(ute.getUnexpectedToken());
                error.setErrorType(ErrorType.EOF);
            } else {
                tokenName = tokenNames[ute.expecting];
                message = "extraneous input " + getTokenErrorDisplay(ute.getUnexpectedToken());
                if(tokenName.equals(")")){
                    error.setErrorType(ErrorType.EXPECTED_CLOSURE);
                    message = "For every '(' there must be a matching ')'. Extraneous input " + getTokenErrorDisplay(ute.getUnexpectedToken());
                }else if(ute.getUnexpectedToken().getText().equals("end")){
                    error.setErrorType(ErrorType.EOF);
                }else{
                    error.setErrorType(ErrorType.OTHER);
                }
            }      
        } else if (re instanceof MissingTokenException) {
            MissingTokenException mte = (MissingTokenException) re;
            String tokenName = "<unknown>";
            if (mte.expecting == Token.EOF) {
                tokenName = "EOF";
                if(getTokenErrorDisplay(re.token).equals("'end'")){
                    message = " The end of the file was reached before all the code was evaluated. There may be an extra " + getTokenErrorDisplay(re.token);
            	    error.setErrorType(ErrorType.EOF);
            	}else{
            	    message = "Missing or invalid statement at " + getTokenErrorDisplay(re.token);
            	    error.setErrorType(ErrorType.OTHER);
            	}
            }else {
                tokenName = tokenNames[mte.expecting];
                message = "missing " + tokenName + " at " + getTokenErrorDisplay(re.token);
                if(tokenName.equals("THEN")){
                    message = "An 'if' or 'else' statement is missing a 'then' at line " + (mte.line - 1);
                    error.setErrorType(ErrorType.MISSING_THEN);
                }else if(tokenName.equals("ID")){
                    message = "An <identifier> is missing. Please give the item you are declaring a name.";
                    error.setErrorType(ErrorType.IDENTIFIER_EXPECTED);
                }else if(tokenName.equals("RIGHT_PAREN") || tokenName.equals("LEFT_PAREN")){
                    message = "For every '(' there must be a matching ')'.";
                    error.setErrorType(ErrorType.EXPECTED_CLOSURE);
                }else{
                    error.setErrorType(ErrorType.OTHER);
                }
            }
                
        } else if (re instanceof MismatchedTokenException) {
            MismatchedTokenException mte = (MismatchedTokenException) re;
            String tokenName = "<unknown>";
            if (mte.expecting == Token.EOF) {
                tokenName = "EOF";
                message = "mismatched input " + getTokenErrorDisplay(re.token)
                            + " expecting " + tokenName;
            } else {
                tokenName = tokenNames[mte.expecting];
                message = "mismatched input " + getTokenErrorDisplay(re.token)
                           + " expecting " + tokenName;
                if(tokenName.equals("IF")){
                    message = "An 'elseif' statement is missing the 'if' at line " + mte.line;
                    error.setErrorType(ErrorType.MISSING_IF);
                }else if(tokenName.equals("END")){
                    message = "A loop, conditional, class, or action statement is missing an 'end'.";
                    error.setErrorType(ErrorType.EXPECTED_CLOSURE);
                }else{
                    error.setErrorType(ErrorType.OTHER);
                }
            }
        } else if (re instanceof MismatchedTreeNodeException) {
            MismatchedTreeNodeException mtne = (MismatchedTreeNodeException) re;
            String tokenName = "<unknown>";
            if (mtne.expecting == Token.EOF) {
                tokenName = "EOF";
            } else {
                tokenName = tokenNames[mtne.expecting];
            }
            message = "mismatched tree node: " + mtne.node
                    + " expecting " + tokenName;
            error.setErrorType(ErrorType.OTHER);
        } else if (re instanceof NoViableAltException) {
            //NoViableAltException nvae = (NoViableAltException)re;
            // for development, can add "decision=<<"+nvae.grammarDecisionDescription+">>"
            // and "(decision="+nvae.decisionNumber+") and
            // "state "+nvae.stateNumber
            message = "Incomplete or invalid statement at " + getTokenErrorDisplay(re.token);
            error.setErrorType(ErrorType.OTHER);
        } else if (re instanceof EarlyExitException) {
            //EarlyExitException eee = (EarlyExitException)e;
            // for development, can add "(decision="+eee.decisionNumber+")"
            message = "required (...)+ loop did not match anything at input "
                    + getTokenErrorDisplay(re.token);
            error.setErrorType(ErrorType.OTHER);
        } else if (re instanceof MismatchedSetException) {
            MismatchedSetException mse = (MismatchedSetException) re;
            message = "mismatched input " + getTokenErrorDisplay(re.token)
                    + " expecting set " + mse.expecting;
            error.setErrorType(ErrorType.OTHER);
        } else if (re instanceof MismatchedNotSetException) {
            MismatchedNotSetException mse = (MismatchedNotSetException) re;
            message = "mismatched input " + getTokenErrorDisplay(re.token)
                    + " expecting set " + mse.expecting;
            error.setErrorType(ErrorType.OTHER);
        } else if (re instanceof FailedPredicateException) {
            FailedPredicateException fpe = (FailedPredicateException) re;
            message = "rule " + fpe.ruleName + " failed predicate: {"
                    + fpe.predicateText + "}?";
            error.setErrorType(ErrorType.OTHER);
        }

        if (vm != null) {
            error.setLineNumber(re.line);
            error.setColumn(re.charPositionInLine);
            error.setError(message);
            error.setFile(fileName);
            CompilerErrorManager ces = vm.getCompilerErrors();
            ces.addError(error);
        }
        return message;
    }
        
        public Documentation getDocumentationFromRecentToken() {
        	String documentationString = "";
		Documentation doc = null;
		TokenStream stream = this.getTokenStream();
		int actualIndex = input.index();
		int current = actualIndex - 1;
		while(current >= 0) {
		    Token toke = stream.get(current);
		    if(toke.getChannel() == this.HIDDEN_DOCUMENTATION) {
		        documentationString = toke.getText();
		        doc = new Documentation();
		        doc.parseDocumentationString(documentationString);
		        current = -1; //we've found the documentation, so bail
		    }
		    else if(toke.getChannel() == this.DEFAULT_TOKEN_CHANNEL
		    	    && toke.getType() != PRIVATE 
                            && toke.getType() != PUBLIC 
                            && toke.getType() != BLUEPRINT 
                            && toke.getType() != NATIVE) {
		        current = -1; //bail out, there's no documentation
		    }
		    current = current - 1;
		}
		return doc;
        }
}

start	:
		(package_rule reference+ 
	|	reference+ package_rule
	|	package_rule
	|	reference+
	|	)
	class_declaration  EOF//Declaring a class name
	;
	
package_rule :	PACKAGE_NAME qn=qualified_name
	{
		thisPackage = $qn.type;
	}
	;
	
reference 
	:	
	USE qn = qualified_name 
	{
		uses.add($qn.type);
	}
	;


class_declaration 	
scope {
	ClassDescriptor current_class;
}
@init{
	Documentation classDocumentation = getDocumentationFromRecentToken();
}			:	
	(
	CLASS ID  
	{
		ClassDescriptor cd = new ClassDescriptor();
		cd.setDocumentation(classDocumentation);
		cd.addUsesDescriptors(uses);
		cd.setContainerErrorCheck(thisPackage);
		classWithNoName = false;		
		if($ID.text == null) {
			cd.setName(getGrammarFileNameNoExtension());
		}
		else {
			cd.setName($ID.text);
		}
		cd.setLineBegin($CLASS.getLine());
		cd.setColumnBegin($CLASS.getCharPositionInLine());
		symbol.add(cd);
		$class_declaration::current_class = cd;
	} 
	genericList=generic_declaration?
	{
		ArrayList<GenericDescriptor> gdList = $genericList.genericTypeList;
		ClassDescriptor clazz = symbol.getCurrentClass();
		
		if(gdList != null){
			for(int i = 0; i < gdList.size(); i++){
				TypeDescriptor td = new TypeDescriptor();
				
				td.setName(TypeDescriptor.OBJECT);
				GenericDescriptor genericType = gdList.get(i);
				td.setTemplateName(genericType.getName());
				genericType.addBoundType(td);
				clazz.addTemplateVariables(genericType);
			}
		}
	}
	inherit_stmnts?       
	class_stmnts*
	END
	{
		ClassDescriptor currentClazz = symbol.getCurrentClass();
		currentClazz.checkClassVariableInitialization();
		$class_declaration::current_class.setLineEnd($END.getLine());
		$class_declaration::current_class.setColumnEnd($END.getCharPositionInLine());
	}
	) -> ^(CLASS ID generic_declaration? inherit_stmnts? class_stmnts* END)
	|
	{
		ClassDescriptor cd = new ClassDescriptor();
		cd.addUsesDescriptors(uses);
		cd.setContainerErrorCheck(thisPackage);
		classWithNoName = true;
		cd.setName(getGrammarFileNameNoExtension());
		cd.setLineBegin(1);	//should it be the beginning of the file?
		cd.setColumnBegin(1);
		symbol.add(cd);
	}
	no_class_stmnts
	;
no_class_stmnts
scope {
	MethodDescriptor fakeMain;
}
	:	
	{//create a fake method main
		$no_class_stmnts::fakeMain = new MethodDescriptor();
		$no_class_stmnts::fakeMain.setLineBegin(1);
		$no_class_stmnts::fakeMain.setLineBegin(1);
		$no_class_stmnts::fakeMain.setName("main");
		$no_class_stmnts::fakeMain.setAccessModifier(AccessModifierEnum.PUBLIC);
		
		TypeDescriptor t = new TypeDescriptor();
		t.setName(TypeDescriptor.VOID);
		$no_class_stmnts::fakeMain.setReturnType(t);
		
		symbol.add($no_class_stmnts::fakeMain);
	}
	statement+
	{//end the fake method main
		symbol.popScope();
	}
	|(access_modifier? method_declaration)+ //no special processing needed for this case
	;
inherit_stmnts
	:	INHERITS qn=qualified_name genericList=generic_statement? 
	{
		ClassDescriptor clazz = symbol.getCurrentClass();
		ArrayList<GenericDescriptor> gdList = $genericList.genericTypeList;
		ArrayList<GenericDescriptor> resultList = new ArrayList<GenericDescriptor>();
		if(gdList != null){
			for(int i = 0; i < gdList.size(); i++){
				TypeDescriptor td = new TypeDescriptor();
				GenericDescriptor genericType = gdList.get(i);
				td.setName(genericType.getStaticKey());
				resultList.add(genericType);
			}
			clazz.addUnresolvedParentClassNames($qn.type, resultList);
		}else{
			clazz.addUnresolvedParentClassNames($qn.type, null);
		}	
	} (COMMA qn=qualified_name genericList=generic_statement?
	{
		clazz = symbol.getCurrentClass();
		gdList = $genericList.genericTypeList;
		resultList = new ArrayList<GenericDescriptor>();
		if(gdList != null){
			for(int i = 0; i < gdList.size(); i++){
				TypeDescriptor td = new TypeDescriptor();
				GenericDescriptor genericType = gdList.get(i);
				td.setName(genericType.getStaticKey());
				resultList.add(genericType);
			}
			clazz.addUnresolvedParentClassNames($qn.type, resultList);
		}else{
			clazz.addUnresolvedParentClassNames($qn.type, null);
		}
	} )*
	-> ^(INHERITS ( qualified_name generic_statement?)+)
	;
access_modifier returns [AccessModifierEnum amEnum]
	:	PUBLIC
	{
		$amEnum = $amEnum.PUBLIC;
	}
	|	PRIVATE
	{
		$amEnum = $amEnum.PRIVATE;
	}
	;	
class_stmnts
	:
	{
		isInClassAssignmentStatementScope = true;
	}
	assignment_statement {isInClassAssignmentStatementScope = false;}
	|	modifier = access_modifier?
	{
		accessModifier = $modifier.amEnum;
		if(accessModifier == null){
			
			accessModifier = accessModifier.PUBLIC;
		}
	}
	 method_declaration
	;
method_declaration
scope {
	Vector<ParameterDescriptor> params;
	MethodDescriptor method;
	BlueprintDescriptor blueprint;
	SystemActionDescriptor systemAction;
}
@init{
Documentation methodDocumentation = getDocumentationFromRecentToken();

}
	:	ACTION ID 
	{
		$method_declaration::method = new MethodDescriptor();
		$method_declaration::method.setDocumentation(methodDocumentation);
		$method_declaration::method.setName($ID.text);
		$method_declaration::method.setAccessModifier(accessModifier);
		$method_declaration::method.setLineBegin($ACTION.getLine());
		$method_declaration::method.setColumnBegin($ACTION.getCharPositionInLine());
		$method_declaration::params = new Vector<ParameterDescriptor>();		
	}
	//now parameters, which are optional
	(LEFT_PAREN (formal_parameter[$method_declaration::params] (COMMA formal_parameter[$method_declaration::params])*)? RIGHT_PAREN)?
	(RETURNS return_type = assignment_declaration )?
	{
		if($RETURNS.text != null) {
			TypeDescriptor type = $return_type.type;
			$method_declaration::method.setReturnType(type);
			
		}
		else {			
			TypeDescriptor t = new TypeDescriptor();
			t.setName(TypeDescriptor.VOID);
			$method_declaration::method.setReturnType(t);
		}
		//now add the method, to check for overloading
		for(ParameterDescriptor param : $method_declaration::params) {
			CompilerError error = $method_declaration::method.add(param);
			if(error != null) {
                        	vm.getCompilerErrors().addError(error);
                        }
		}
		
		
		CompilerError error = symbol.add($method_declaration::method);
                if(error != null) {
                	error.setColumn($ACTION.getCharPositionInLine());
                	error.setLineNumber($ACTION.getLine());
                	error.setFile(getGrammarFileNameNoExtension());
                        vm.getCompilerErrors().addError(error);    
                }
		
	}
		block
	END 
	{
		$method_declaration::method.setLineEnd($END.getLine());
		$method_declaration::method.setColumnEnd($END.getCharPositionInLine());
		if(classWithNoName) {
			//currentClass.setLineEnd($END.getLine());
			//currentClass.setColumnEnd($END.getCharPositionInLine() + $END.text.length());
		}
		symbol.popScope();
	}
	-> ^(ACTION ID formal_parameter* (RETURNS assignment_declaration)? block END)
	|	BLUEPRINT ACTION ID 
	{
		$method_declaration::blueprint = new BlueprintDescriptor();
		$method_declaration::blueprint.setDocumentation(methodDocumentation);		
		$method_declaration::blueprint.setName($ID.text);
		$method_declaration::blueprint.setAccessModifier(accessModifier);
		$method_declaration::blueprint.setLineBegin($ACTION.getLine());
		$method_declaration::blueprint.setColumnBegin($ACTION.getCharPositionInLine());
		$method_declaration::blueprint.setLineEnd($ACTION.getLine());
		$method_declaration::blueprint.setColumnEnd($ACTION.getCharPositionInLine());
		$method_declaration::params = new Vector<ParameterDescriptor>();		
	}
	//now parameters, which are optional
	(LEFT_PAREN (formal_parameter[$method_declaration::params] (COMMA formal_parameter[$method_declaration::params])*)? RIGHT_PAREN)?
	(RETURNS return_type = assignment_declaration)?
	{
		if($RETURNS.text != null) {
			$method_declaration::blueprint.setReturnType($return_type.type);
		}
		else {			
			TypeDescriptor t = new TypeDescriptor();
			t.setName(TypeDescriptor.VOID);
			$method_declaration::blueprint.setReturnType(t);
		}
		//now add the method, to check for overloading
		for(ParameterDescriptor param : $method_declaration::params) {
			CompilerError error = $method_declaration::blueprint.add(param);
			if(error != null) {
                        	vm.getCompilerErrors().addError(error);
                        }
		}
		
		
		CompilerError error = symbol.add($method_declaration::blueprint);
                if(error != null) {
                	error.setColumn($ACTION.getCharPositionInLine());
                	error.setLineNumber($ACTION.getLine());
                	error.setFile(getGrammarFileNameNoExtension());
                        vm.getCompilerErrors().addError(error);    
                }
	}	
	-> ^(BLUEPRINT ACTION ID formal_parameter* (RETURNS assignment_declaration)?)
	|	NATIVE ACTION ID 
	{
		$method_declaration::systemAction = new SystemActionDescriptor();
		$method_declaration::systemAction.setDocumentation(methodDocumentation);			
		$method_declaration::systemAction.setName($ID.text);
		$method_declaration::systemAction.setAccessModifier(accessModifier);
		$method_declaration::systemAction.setLineBegin($ACTION.getLine());
		$method_declaration::systemAction.setColumnBegin($ACTION.getCharPositionInLine());
		$method_declaration::systemAction.setLineEnd($ACTION.getLine());
		$method_declaration::systemAction.setColumnEnd($ACTION.getCharPositionInLine());
		$method_declaration::params = new Vector<ParameterDescriptor>();		
	}
	//now parameters, which are optional
	(LEFT_PAREN (formal_parameter[$method_declaration::params] (COMMA formal_parameter[$method_declaration::params])*)? RIGHT_PAREN)?
	(RETURNS return_type = assignment_declaration)?
	{
		if($RETURNS.text != null) {
			$method_declaration::systemAction.setReturnType($return_type.type);
		}
		else {			
			TypeDescriptor t = new TypeDescriptor();
			t.setName(TypeDescriptor.VOID);
			$method_declaration::systemAction.setReturnType(t);
		}
		//now add the method, to check for overloading
		for(ParameterDescriptor param : $method_declaration::params) {
			CompilerError error = $method_declaration::systemAction.add(param);
			if(error != null) {
                        	vm.getCompilerErrors().addError(error);
                        }
		}
		
		
		CompilerError error = symbol.add($method_declaration::systemAction);
                if(error != null) {
                	error.setColumn($ACTION.getCharPositionInLine());
                	error.setLineNumber($ACTION.getLine());
                	error.setFile(getGrammarFileNameNoExtension());
                        vm.getCompilerErrors().addError(error);    
                }
	}	
	-> ^(NATIVE ACTION ID formal_parameter* (RETURNS assignment_declaration)?)
	| ON CREATE
	{
		$method_declaration::method = new MethodDescriptor();
		$method_declaration::method.setName($ON.text + ' ' + $CREATE.text);
		$method_declaration::method.flagMethodAsConstructor();
		$method_declaration::method.setAccessModifier(AccessModifierEnum.PRIVATE);
		$method_declaration::method.setLineBegin($CREATE.getLine());
		$method_declaration::method.setColumnBegin($CREATE.getCharPositionInLine());
		
		TypeDescriptor t = new TypeDescriptor();
		t.setName(TypeDescriptor.VOID);
		$method_declaration::method.setReturnType(t);
			
		CompilerError error = symbol.add($method_declaration::method);
                if(error != null) {
                	error.setColumn($CREATE.getCharPositionInLine());
                	error.setLineNumber($CREATE.getLine());
                	error.setFile(getGrammarFileNameNoExtension());
                        vm.getCompilerErrors().addError(error);    
                }
		isInConstructorScope = true;
	}
	 block END
	{
		$method_declaration::method.setLineEnd($END.getLine());
		$method_declaration::method.setColumnEnd($END.getCharPositionInLine());
		$method_declaration::method.setColumnEnd($END.getCharPositionInLine());
		
		symbol.popScope();
		isInConstructorScope = false;
	}
	-> ^(ON CREATE block END)
	;
formal_parameter[Vector<ParameterDescriptor> params]
	:	assignment_declaration ID
	{
		TypeDescriptor type = $assignment_declaration.type;		
		ParameterDescriptor d = new ParameterDescriptor();
		Iterator<GenericDescriptor> gdList = null;
		if(type != null){
			gdList = type.getSubTypes();
		}
		
		d.setName($ID.text);
		d.setLineBegin($ID.getLine());
		d.setColumnBegin($ID.getCharPositionInLine());
		d.setLineEnd($ID.getLine());
		d.setColumnEnd($ID.getCharPositionInLine());
		
		if(gdList != null){
			while(gdList.hasNext()){
				TypeDescriptor td = new TypeDescriptor();
				GenericDescriptor genericType = gdList.next();
				td.setName(genericType.getStaticKey());
				genericType.addBoundType(td);
				d.addTemplateType(genericType);
			}
		}
		
		GenericDescriptor gd = null;
		if(type!=null){
		 	gd = symbol.getCurrentClass().getTemplateVariable($assignment_declaration.type.getName());
		}
		
		if(gd != null){
			d.setType(gd.getType());
		}else{
			d.setType(type);
		}
		
		
		$params.add(d);
	} -> ^(FPARAM assignment_declaration ID)
	;
	
qualified_name returns [QualifiedNameDescriptor type]
	:	ids+=ID (PERIOD ids+=ID)* 
		{
			QualifiedNameDescriptor type = new QualifiedNameDescriptor();
			type.setLineBegin(((Token)$ids.get(0)).getLine());
			type.setColumnBegin(((Token)$ids.get(0)).getCharPositionInLine());
			
			String name = "";
			Iterator it = $ids.iterator();       		
        	
        		while(it.hasNext()) {           
            			name += ((CommonToken) it.next()).getText();
           		
            			if(it.hasNext()) {
                			name += ".";
           			}
            		
            		}
            		type.setName(name);
			
			type.setLineEnd(((Token)$ids.get($ids.size() - 1)).getLine());
			type.setColumnEnd(((Token)$ids.get($ids.size() - 1)).getCharPositionInLine());
			$type = type;			
		}
		-> ^(QUALIFIED_NAME ID (PERIOD ID)*)
	;
block 	:	statement*
		-> ^(STATEMENT_LIST statement*)
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
	qualified_name (COLON ID)? LEFT_PAREN (expression (COMMA expression)*)? RIGHT_PAREN ->
			^(SOLO_FUNCTION_CALL qualified_name (COLON ID)? LEFT_PAREN (expression (COMMA expression)*)? RIGHT_PAREN)
	|	PARENT COLON qualified_name COLON ID LEFT_PAREN (expression (COMMA expression)*)? RIGHT_PAREN ->
			^(SOLO_FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN (expression (COMMA expression)*)? RIGHT_PAREN)
	|	ME COLON qualified_name (COLON ID)? LEFT_PAREN (expression (COMMA expression)*)? RIGHT_PAREN ->
			^(SOLO_FUNCTION_CALL_THIS ME COLON qualified_name (COLON ID)? LEFT_PAREN (expression (COMMA expression)*)? RIGHT_PAREN)
	;
	
alert_statement 
	:	ALERT LEFT_PAREN root_expression  RIGHT_PAREN
	-> ^(ALERT LEFT_PAREN root_expression RIGHT_PAREN)
	;
	
check_statement 
@init{
	BlockDescriptor block;
}
	:   check_start=CHECK { block = new BlockDescriptor(); symbol.add(block); }block 
	{ 
		symbol.popScope(); 
       		//set the begin and end line column information in the block descriptors.
       		block.setLineBegin($check_start.getLine());
       		block.setColumnBegin($check_start.getCharPositionInLine());
	}
	    ((detect_start=DETECT 
	{ 
		block.setLineEnd($detect_start.getLine());
		block.setColumnEnd($detect_start.text.length() + $detect_start.getCharPositionInLine());
		
		block = new BlockDescriptor(); 
		symbol.add(block); 
	} 
	    detect_parameter block { symbol.popScope(); }
	{
       		//set the begin and end line column information in the block descriptors.
       		block.setLineBegin($detect_start.getLine());
       		block.setColumnBegin($detect_start.getCharPositionInLine());
	}
	    )+ 
	    (always_start=ALWAYS 
	{ 
		block.setLineEnd($always_start.getLine()); 
		block.setColumnEnd($always_start.text.length() + $always_start.getCharPositionInLine()); 
		
		block = new BlockDescriptor(); 
		symbol.add(block); 
	}
	    block 
	{ 
		symbol.popScope(); 
       		//set the begin and end line column information in the block descriptors.
       		block.setLineBegin($always_start.getLine());
       		block.setColumnBegin($always_start.getCharPositionInLine());
	}
	    )? 
	|   always_start_2=ALWAYS 
	{ 
		block.setLineEnd($always_start_2.getLine()); 
		block.setColumnEnd($always_start_2.text.length() + $always_start_2.getCharPositionInLine());
		
		block = new BlockDescriptor(); 
		symbol.add(block); 
	}
	    block 
	{ 
		symbol.popScope();
       		//set the begin and end line column information in the block descriptors.
       		block.setLineBegin($always_start_2.getLine());
       		block.setColumnBegin($always_start_2.getCharPositionInLine());
	}
	    ) end=END   
	{
		block.setLineEnd($end.getLine());
		block.setColumnEnd($end.text.length() + $end.getCharPositionInLine());
	}
	 ;	
    
detect_parameter returns [String name, ArrayList<ErrorTypeDescriptor> exceptionTypeList]
	: 	ID 
	
	{
	VariableDescriptor new_desc = new VariableDescriptor();
	TypeDescriptor type = new TypeDescriptor();
	type.setName("Libraries.Language.Errors.Error");
	
	type.setLineBegin($ID.getLine());
	type.setColumnBegin($ID.getCharPositionInLine());
			
	new_desc.setAccessModifier(AccessModifierEnum.PRIVATE);
	           		
	           		
	new_desc.setType(type);
	new_desc.setName($ID.text);
	new_desc.setInitialized(true);
           		
	CompilerError error = symbol.add(new_desc);
	if(error != null) {
		error.setLineNumber($ID.getLine());
		error.setColumn($ID.getCharPositionInLine());
		vm.getCompilerErrors().addError(error);
	}	
	}
	
	(INHERITS  qualified_name(OR qualified_name)*)?
	-> ^(ID (INHERITS  qualified_name(OR qualified_name)*)?)
	;
print_statement 
	:	OUTPUT root_expression
	;

speak_statement 
	:	SAY root_expression
	;

return_statement
	:	RETURN ( root_expression | NOW)
	;
	
generic_declaration returns [ArrayList genericTypeList]
	:	LESS ids+=ID (COMMA ids+=ID)* GREATER
	{
		ArrayList<GenericDescriptor> gd = new ArrayList<GenericDescriptor>();
		Iterator it = $ids.iterator();
		
		while(it.hasNext()){
		
			CommonToken t = ((CommonToken) it.next());
			
			GenericDescriptor genericType = new GenericDescriptor();
			genericType.setLineBegin(((Token)$ids.get(0)).getLine());
			genericType.setColumnBegin(((Token)$ids.get(0)).getCharPositionInLine());
			genericType.setLineEnd(((Token)$ids.get($ids.size() - 1)).getLine());
			genericType.setColumnEnd(((Token)$ids.get($ids.size() - 1)).getCharPositionInLine());
			
			genericType.setName(t.getText());

			gd.add(genericType);
			
		}
		
		$genericTypeList = gd;
	}
	-> ^(GENERIC LESS ID (COMMA ID)* GREATER)
	;
generic_statement returns [ArrayList genericTypeList]
scope {
	ArrayList<TypeDescriptor> typeList;
}
	:	LESS 
	{
		$generic_statement::typeList = new ArrayList<TypeDescriptor>();
	}
	type =assignment_declaration
	{
		$generic_statement::typeList.add($type.type);
	}
	(COMMA type=assignment_declaration
	{
		$generic_statement::typeList.add($type.type);
	} 
	)* GREATER
	{
		ArrayList<GenericDescriptor> gd = new ArrayList<GenericDescriptor>();
		Iterator<TypeDescriptor> it = $generic_statement::typeList.iterator();
		
		while(it.hasNext()){
			//CommonTree tree = it.next();
			TypeDescriptor t = it.next();
			//t.convertToClass();
			
			GenericDescriptor genericType = new GenericDescriptor();
			genericType.setLineBegin(t.getLineBegin());
			genericType.setColumnBegin(t.getColumnBegin());
			genericType.setLineEnd(t.getLineEnd());
			genericType.setColumnEnd(t.getColumnEnd());
			
			genericType.setName(t.getName());
			//t.setSubTypes(gen);
			genericType.addBoundType(t);
			
		
			gd.add(genericType);
		}
		
		$genericTypeList = gd;
	}
	-> ^(GENERIC LESS assignment_declaration(COMMA assignment_declaration)* GREATER)
	;
class_type returns [TypeDescriptor type]
	:	qn = qualified_name
	{
		TypeDescriptor t = new TypeDescriptor();
		GenericDescriptor gd = symbol.getCurrentClass().getTemplateVariable($qn.type.getStaticKey());
		if(gd != null){
			t.setName(gd.getType().getStaticKey());
			t.setTemplateName($qn.type.getStaticKey());
		}else{
			t.setName($qn.type.getStaticKey());
		}

		t.setLineBegin($qn.type.getLineBegin());
		t.setColumnBegin($qn.type.getColumnBegin());
			
		$type=t;
	}
	;
assignment_declaration returns [TypeDescriptor type]
	:	qn = qualified_name gs=generic_statement?
	{
		TypeDescriptor t = new TypeDescriptor();
		GenericDescriptor gd = symbol.getCurrentClass().getTemplateVariable($qn.type.getStaticKey());
		if(gd != null){
			t.setName(gd.getType().getStaticKey());
			t.setTemplateName($qn.type.getStaticKey());
			
			if(gs != null){
				t.setSubTypes($gs.genericTypeList);
			}
		}else{
			t.setName($qn.type.getStaticKey());
			if(gs != null){
				t.setSubTypes($gs.genericTypeList);
			}
		}

		t.setLineBegin($qn.type.getLineBegin());
		t.setColumnBegin($qn.type.getColumnBegin());
			
		$type=t;	
	}
	|	INTEGER_KEYWORD
	{
		TypeDescriptor t = new TypeDescriptor();
		t.setName(TypeDescriptor.INTEGER);
		t.setLineBegin($INTEGER_KEYWORD.getLine());
		t.setColumnBegin($INTEGER_KEYWORD.getCharPositionInLine());
		$type = t;
	}
	|	NUMBER_KEYWORD
	{
		TypeDescriptor t = new TypeDescriptor();
		t.setName(TypeDescriptor.NUMBER);
		t.setLineBegin($NUMBER_KEYWORD.getLine());
		t.setColumnBegin($NUMBER_KEYWORD.getCharPositionInLine());
		$type = t;
	}
	|	TEXT
	{
		TypeDescriptor t = new TypeDescriptor();
		t.setName(TypeDescriptor.TEXT);
		t.setLineBegin($TEXT.getLine());
		t.setColumnBegin($TEXT.getCharPositionInLine());
		$type = t;
	}
	|	BOOLEAN_KEYWORD
	{
		TypeDescriptor t = new TypeDescriptor();
		t.setName(TypeDescriptor.BOOLEAN);
		t.setLineBegin($BOOLEAN_KEYWORD.getLine());
		t.setColumnBegin($BOOLEAN_KEYWORD.getCharPositionInLine());
		$type = t;
	};
assignment_statement
@init{
	Documentation variableDocumentation = null;
	if(isInClassAssignmentStatementScope) {
		variableDocumentation = getDocumentationFromRecentToken();
	}
}
	:	
		(sel = selector COLON)? ID rhs = assign_right_hand_side
		{
			String initMe = $ID.text;
			ClassDescriptor clazz = symbol.getCurrentClass();
			if(clazz != null) {
				boolean validConstructorInit = false;
				if(sel != null) {
					ScopeSelector select = $sel.scopeSel;
					if(!select.isParent()) {
						validConstructorInit = true;	
					}else if(isInClassAssignmentStatementScope){
					                	CompilerError error = new CompilerError();
						error.setLineNumber($ID.getLine());
						error.setError("Cannot assign a value to the parents' variable, " + $ID.text + ", in the initialization scope of this class. Try assigning a value to the variable in an 'action' or an 'on create' statement.");
						error.setErrorType(ErrorType.INITIALIZED_OUT_OF_BOUNDS);
						error.setColumn($ID.getCharPositionInLine());
						error.setFile(getGrammarFileNameNoExtension());
						vm.getCompilerErrors().addError(error);
					}					
				}
				else if(rhs != null){
					VariableParameterCommonDescriptor variable = symbol.getCurrentScope().getVariable(initMe);
			                                            if(variable == null){
			                                            	VariableDescriptor new_desc = new VariableDescriptor();
			                                            	if(isInClassAssignmentStatementScope) {
							new_desc.setDocumentation(variableDocumentation);
						}
						//Iterator<GenericDescriptor> gdList = $type.type.getSubTypes();
						new_desc.setAccessModifier(accessModifier.PRIVATE);
						new_desc.setLineBegin($ID.getLine());
						new_desc.setName(initMe);
						new_desc.setIsConstant(false);
				                                            if( isInClassAssignmentStatementScope) {
							new_desc.setIsInitializedClassVariable(true);
						}
						
						CompilerError error = symbol.add(new_desc);
						if(error != null) {
							error.setLineNumber($ID.getLine());
							error.setColumn($ID.getCharPositionInLine());
							vm.getCompilerErrors().addError(error);
						}
			                                            }
					validConstructorInit = true;
				}else{
					validConstructorInit = true;
				}
				if(validConstructorInit) {
					clazz.addToConstructorInitializationList(initMe);
				}
			}
		}
	|	obj=qualified_name (COLON PARENT COLON parent=qualified_name)? COLON ID rhs=assign_right_hand_side
	|	modifier = access_modifier?
		{
			accessModifier = $modifier.amEnum;
			if(accessModifier == null){
				
				accessModifier = accessModifier.PRIVATE;
			}
		}
	CONSTANT? type = assignment_declaration name = ID rhs = assign_right_hand_side?	
		{
			VariableDescriptor new_desc = new VariableDescriptor();
			if(isInClassAssignmentStatementScope) {
				new_desc.setDocumentation(variableDocumentation);
			}
			Iterator<GenericDescriptor> gdList = $type.type.getSubTypes();
			new_desc.setAccessModifier(accessModifier);
			new_desc.setType($type.type);
			new_desc.setLineBegin($type.type.getLineBegin());
			new_desc.setName($name.text);
			if($CONSTANT != null){
				new_desc.setIsConstant(true);
			}else{
				new_desc.setIsConstant(false);
			}
			
			if((rhs != null && isInClassAssignmentStatementScope) || (rhs == null && !new_desc.getType().isPrimitiveType())) {
				new_desc.setIsInitializedClassVariable(true);
			}
			if(gdList != null){
				while(gdList.hasNext()){
					
					TypeDescriptor td = new TypeDescriptor();
					GenericDescriptor genericType = gdList.next();
					td.setName(genericType.getStaticKey());
					genericType.addBoundType(td);
					new_desc.addTemplateType(genericType);
				}
			}
			
			CompilerError error = symbol.add(new_desc);
			if(error != null) {
				error.setLineNumber($ID.getLine());
				error.setColumn($ID.getCharPositionInLine());
				vm.getCompilerErrors().addError(error);
			}
		}			
	;
assign_right_hand_side
		//interferes with function calls
	:	//(LEFT_PAREN expression (COMMA expression)* RIGHT_PAREN) -> ^(CONSTRUCTOR expression*)
		(EQUALITY root_expression)
	;

	
if_statement	
@init {
	BlockDescriptor block;
}
	:
	firstif = IF root_expression { block = new BlockDescriptor(); symbol.add(block); } 
	block 
	{ 
		symbol.popScope(); 
       		//set the begin and end line column information in the block descriptors.
       		block.setLineBegin($firstif.getLine());
       		block.setColumnBegin($firstif.getCharPositionInLine());
	}
	(firstelse = ELSE_IF 
	{
		block.setLineEnd($firstelse.getLine());
		block.setColumnEnd($firstelse.text.length() + $firstelse.getCharPositionInLine());
	} 
	root_expression { block = new BlockDescriptor(); symbol.add(block); } 
	block 
	{ 
		symbol.popScope(); 
       		//set the begin and end line column information in the block descriptors.
       		block.setLineBegin($firstelse.getLine());
       		block.setColumnBegin($firstelse.getCharPositionInLine());
	}
	)*  //else if blocks
	(secondelse = ELSE 
	{ 
		block.setLineEnd($secondelse.getLine());
		block.setColumnEnd($secondelse.text.length() + $secondelse.getCharPositionInLine());
		block = new BlockDescriptor(); 
		symbol.add(block); 
	} 
	block 
	{ 
		symbol.popScope(); 
       		//set the begin and end line column information in the block descriptors.
       		block.setLineBegin($secondelse.getLine());
       		block.setColumnBegin($secondelse.getCharPositionInLine());
	}
	)? 
	end = END
	{
		block.setLineEnd($end.getLine());
		block.setColumnEnd($end.text.length() + $end.getCharPositionInLine());
	}
	;

loop_statement
@init {
	BlockDescriptor block;
}
	:	
	{
		block = new BlockDescriptor();
		symbol.add(block);
	}
		REPEAT ( //(OVER ID)
			//{
			//	VariableParameterCommonDescriptor desc = symbol.getVariable($ID.text);
			//	if(desc == null)
			//	{
			//		CompilerError error = new CompilerError();
			//		error.setError("Variable " + $ID.text + " not defined.");
			//		error.setErrorType(ErrorType.MISSING_VARIABLE);
			//		error.setLineNumber($ID.line);
			//		error.setColumn($ID.getCharPositionInLine());
			//		error.setFile(getGrammarFileNameNoExtension());
			//		vm.getCompilerErrors().addError(error);
			//	} 
			//	
			//}
		//|	((FROM range))
			(root_expression TIMES)
		|	((WHILE | UNTIL) root_expression)
			)  block END
		{
       			//set the begin and end line column information in the block descriptors.
       			block.setLineBegin($REPEAT.getLine());
       			block.setLineEnd($END.getLine());
       			block.setColumnBegin($REPEAT.getCharPositionInLine());
       			block.setColumnEnd($END.text.length() + $END.getCharPositionInLine());
       		}
	{
		symbol.popScope();
	}
		;

//range	:	(root_expression) TO (root_expression) -> ^(TO root_expression root_expression);

selector	returns[ScopeSelector scopeSel]
	:	PARENT COLON qn=qualified_name
	{
		ScopeSelector scopeItem = new ScopeSelector();
		scopeItem.setIsParent(true);
		$scopeSel = scopeItem;
	} -> ^(PARENT qualified_name)
	
	|	ME
	{
		ScopeSelector scopeItem = new ScopeSelector();
		scopeItem.setIsParent(false);
		$scopeSel = scopeItem;
	}
	;

root_expression
	:	expression -> ^(ROOT_EXPRESSION expression)
	;
	
expression
	:	or
	;
	
or 	:	and (OR ^ and)*
	;

and 	:	equality (AND ^ equality)*
	;	
equality:	isa_operation ((EQUALITY ^ | NOTEQUALS ^) isa_operation)*
	;
isa_operation
	:	comparison (INHERITS ^ class_type)?
	;
comparison:	add ((GREATER ^| GREATER_EQUAL ^| LESS ^| LESS_EQUAL^) add)*
	;
	

add	:	multiply ((PLUS ^| MINUS^) multiply)*
	;
		
multiply:	combo_expression ((MULTIPLY ^| DIVIDE ^|MODULO^) combo_expression)*
	;
	
combo_expression 
	:	NOT atom -> ^(UNARY_NOT NOT atom)
	|	CAST LEFT_PAREN assignment_declaration COMMA expression RIGHT_PAREN
	|	atom
	;
		
atom 	: 
	qualified_name (COLON ID)? -> ^(QUALIFIED_SOLO_EXPRESSION qualified_name (COLON ID)?)
	|	qualified_name COLON PARENT COLON qualified_name COLON ID -> ^(QUALIFIED_SOLO_PARENT_EXPRESSON qualified_name COLON PARENT COLON qualified_name COLON ID)
	|	qualified_name (COLON ID)? LEFT_PAREN function_expression_list RIGHT_PAREN ->
			^(FUNCTION_CALL qualified_name (COLON ID)? LEFT_PAREN function_expression_list RIGHT_PAREN)
	|	selector COLON qualified_name -> 
			^(QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name)
	// sdf s |	qualified_name COLON PARENT COLON qualified_name ->
	//sdfsd		^(QUALIFIED_SOLO_PARENT_EXPRESSON qualified_name COLON PARENT COLON qualified_name)
	|	PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN ->
			^(FUNCTION_CALL_PARENT PARENT COLON qualified_name COLON ID LEFT_PAREN function_expression_list RIGHT_PAREN)
	|	ME COLON qualified_name (COLON ID)? LEFT_PAREN function_expression_list RIGHT_PAREN ->
			^(FUNCTION_CALL_THIS ME COLON qualified_name (COLON ID)? LEFT_PAREN function_expression_list RIGHT_PAREN)
	| (MINUS)? INT
	| BOOLEAN
	| (MINUS)? DECIMAL 
	| STRING
	| QUOTE
	| NULL
	| ME
		| INPUT LEFT_PAREN expression RIGHT_PAREN
	| LEFT_PAREN expression RIGHT_PAREN -> ^(expression)
	;

function_expression_list 
	:
	(expression (COMMA expression)*)?	
	-> ^(FUNCTION_EXPRESSION_LIST expression*)
	;
OUTPUT	:	'output';
ON	:	'on';
DESTROY	:	'destroy';
CREATE	:	'create';
QUOTE	:	'quote';
CONSTANT	:	'constant';
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
// FROM	:	'from';
TIMES	:	'times';
REPEAT	:	'repeat';
OVER	:	'over';
ELSE 	:	'else';
RETURNS :	'returns';
RETURN 	:	'return';
AND	:	'and';
OR 	:	'or';
// TO	:	'to';
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
STRING	:	DOUBLE_QUOTE ~(DOUBLE_QUOTE)* DOUBLE_QUOTE;


NEWLINE	:	 '\r'?'\n' {$channel = HIDDEN;};
WS	:	(' '|'\t'|'\n'|'\r')+ {$channel = HIDDEN;};

COMMENTS
    :   '//' ~('\n'|'\r')* (('\r'? '\n') | EOF) {$channel=HIDDEN;}
    |   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN_DOCUMENTATION;}
    ;

