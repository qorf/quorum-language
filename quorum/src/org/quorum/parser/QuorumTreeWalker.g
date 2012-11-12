
tree grammar QuorumTreeWalker;

options {
	tokenVocab=Quorum;
	ASTLabelType=CommonTree;
	output = template;
}


@header {

package org.quorum.parser;
import org.quorum.vm.interfaces.*;
import org.quorum.symbols.*;
import org.quorum.vm.implementation.*;
import org.quorum.steps.*;
import org.quorum.execution.ExpressionValue;
import org.quorum.execution.ExecutionStep;
import org.quorum.execution.ScopeSelector;
import java.util.Iterator;
import java.util.Vector;
import java.util.Enumeration;
}

@members{
	QuorumVirtualMachine vm;
	AccessModifierEnum accessModifier;
	SymbolTable symbol;
	QualifiedNameDescriptor thisPackage = new QualifiedNameDescriptor();
	String fileName;
	boolean classWithNoName = false;
	ClassDescriptor currentClass;
	MethodDescriptor currentMethod;
	IntermediateExecutionBuilder builder;
	TypeChecker typeChecker;
	StepFactory stepFactory = new StepFactory();
	//used to create unique label hashes
	static int labelCounter = 0;
	static int sub_counter = 0;
	static int expressionDepth = 0;
	boolean inCallStep = false;
	StringBuffer  indexer = new StringBuffer();
	String className = "";
	
	//the register number, used to place values into fake registers in the computer
	static int temp = 0;
	public void setQuorumVirtualMachine(QuorumVirtualMachine m) {
		vm = m;
		symbol = vm.getSymbolTable();
		builder = vm.getBuilder();
		stepFactory.setMachine(vm);
		stepFactory.setTypeChecker(m.getTypeChecker());
		typeChecker = m.getTypeChecker();
	}

	public String getGrammarFileNameNoExtension() {
		return fileName;
	}

	public void setGrammarFileNameNoExtension(String name) {
		fileName = name;
	}
}

start	:	(package_rule reference+
	|	reference+ package_rule
	|	package_rule
	|	reference+
	|	)
	class_declaration  EOF //Declaring a class name
	;

package_rule 	:	PACKAGE_NAME qn=qualified_name
	{
		thisPackage = $qn.type;
	}
	;

reference	:	USE qualified_name
	{

	}
	;

class_declaration 	:
	^(CLASS ID
	{
		AccessModifierEnum e;
		String name;
		if($ID.text == null) {
			name = getGrammarFileNameNoExtension();
		}
		else {
			name = $ID.text;
		}
		//get the class from the symbol table
		String container = thisPackage.toString();
		ClassDescriptor cl = symbol.enterClass(name, container);
		className = cl.getStaticKey();
		builder.begin(cl);
		indexer.append("<debug><class><name>");
		indexer.append(className);
		indexer.append("</name>");
	}
	generic_declaration?
	{

	}
	inherit_stmnts?
	{
	}
	class_stmnts* END
	{
		builder.endClass();
		symbol.popScope();
		indexer.append("</class></debug>");
		symbol.addClassIndexer(className, indexer.toString());
	})
	|
	{
		String name = getGrammarFileNameNoExtension();
		String container = thisPackage.toString();
		ClassDescriptor cl = symbol.enterClass(name, container);
		builder.begin(cl);
		indexer.append("<debug><class>");
		className = cl.getStaticKey();
	}
	no_class_stmnts
	{
		builder.endClass();
		symbol.popScope();
		indexer.append("</class></debug>");
		symbol.addClassIndexer(className, indexer.toString());
	}
	;

no_class_stmnts
	:	
	{//enter the fake method main
		MethodDescriptor md = symbol.enterMethod("main");
		builder.begin(md);
	}
		statement+
	{//exit the fake method main
	
		CompilerError error = symbol.getControlFlow().endMethod();
		if(error != null) {
			vm.getCompilerErrors().addError(error);
		}
		if(symbol.getControlFlow().needsReturnStep() && error == null) { //do this only for void types
                	LineInformation location = new LineInformation();
	                location.setStartColumn(0);
	                location.setStartLine(0);
	                location.setFile(getGrammarFileNameNoExtension());
			stepFactory.addReturnStep(location, null, null);
		}
		builder.endMethod();
		symbol.popScope();
	}
	|	(modEnum = access_modifier?
	{
		accessModifier = $modEnum.amEnum;
		if(accessModifier == null){
			accessModifier = accessModifier.PUBLIC;
		}
	}
	 method_declaration)+ //no special processing needed for this case
	;
inherit_stmnts
	: ^(INHERITS ( qn = qualified_name gd = generic_statement?)+)
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
	:	assignment_statement
	|	modEnum = access_modifier? 
	{
		if(modEnum == null){
			accessModifier = AccessModifierEnum.PUBLIC;
		}
		else {
			accessModifier = $modEnum.amEnum;
		}
	}
	method_declaration
	;
method_declaration
scope {
	Vector<TypeDescriptor> types;
}
	:	^(

	ACTION
	{
		$method_declaration::types = new Vector<TypeDescriptor>();
	}
	ID (fp=formal_parameter { $method_declaration::types.add((fp).type); })*
	{
                String key = MethodDescriptor.autoGenerateKey($ID.text, 
                	$method_declaration::types);
                MethodDescriptor md = symbol.enterMethod(key);
                md.setAccessModifier(accessModifier);
		builder.begin(md);

	}

		(RETURNS ad=assignment_declaration {
			if($ad.myType != null) {
				MethodDescriptor mdResolve = symbol.getCurrentMethod();	
				mdResolve.setReturnType($ad.myType);
			}
		})? block[false] END
	{
		
		symbol.addStatementFlagToCurrentFile($END.line);
		
		CompilerError error = symbol.getControlFlow().endMethod();
		if(error != null) {
			vm.getCompilerErrors().addError(error);
		}
		
                if(symbol.getControlFlow().needsReturnStep() && error == null) { //do this only for void types
                	LineInformation location = new LineInformation();
	                location.setStartColumn($END.getCharPositionInLine());
	                location.setStartLine($END.line);
	                location.setFile(getGrammarFileNameNoExtension());
			stepFactory.addReturnStep(location, null, null);
		}
		
		builder.endMethod();
		//return scope to the class
		symbol.popScope();
		

	}
		)
	|   ^(BLUEPRINT ACTION
	{
		$method_declaration::types = new Vector<TypeDescriptor>();
	}
	 ID (fp=formal_parameter{ $method_declaration::types.add((fp).type); })*
	  (RETURNS assignment_declaration)?)
	| ^(NATIVE ACTION 
	{
		$method_declaration::types = new Vector<TypeDescriptor>();
	}
	ID (fp=formal_parameter{ $method_declaration::types.add((fp).type); })*
	  (RETURNS assignment_declaration)?)
	| ^(ON_CREATE
	{
		MethodDescriptor construct = symbol.enterConstructor();
                construct.setAccessModifier(AccessModifierEnum.PRIVATE);
                construct.flagMethodAsConstructor();
		builder.begin(construct);
	}
	 block[true] END
	{
		symbol.addStatementFlagToCurrentFile($END.line);
		
		CompilerError error = symbol.getControlFlow().endMethod();
		if(error != null) {
			vm.getCompilerErrors().addError(error);
		}
		
		if(symbol.getControlFlow().needsReturnStep() && error == null) { //do this only for void types
                	LineInformation location = new LineInformation();
	                location.setStartColumn($END.getCharPositionInLine());
	                location.setStartLine($END.line);
	                location.setFile(getGrammarFileNameNoExtension());
	                location.setClassName(symbol.getCurrentClass().getStaticKey());
	                location.setMethodName(construct.getStaticKey());
	                
			stepFactory.addReturnStep(location, null, null);
		}
		
		builder.endMethod();
		//return scope to the class
		symbol.popScope();
		

	}
	 )
	;

qualified_name returns [QualifiedNameDescriptor type]
	:	^(QUALIFIED_NAME ids+= ID (PERIOD ids+=ID)*)
	{
		QualifiedNameDescriptor t = new QualifiedNameDescriptor();
		t.setLineBegin(((CommonTree)$ids.get(0)).token.getLine());
		t.setColumnBegin(((CommonTree)$ids.get(0)).token.getCharPositionInLine());
		/*t.setNameFromList($ids);
		*/
		String name = "";
		Iterator it = $ids.iterator();

        	while(it.hasNext()) {
            		name += ((CommonTree) it.next()).getText();

            		if(it.hasNext()) {
                		name += $PERIOD.text;
           		}

            	}
            	t.setName(name);



		t.setLineEnd(((CommonTree)$ids.get($ids.size() - 1)).token.getLine());
		t.setColumnEnd(((CommonTree)$ids.get($ids.size() - 1)).token.getCharPositionInLine());
		$type = t;
	}
	;

block[boolean bool] 	:	^(STATEMENT_LIST
	{
		if(bool) {
			//add scope change step for runtime scoping
			LineInformation location = new LineInformation();
	                location.setStartColumn($STATEMENT_LIST.getCharPositionInLine());
	                location.setStartLine($STATEMENT_LIST.line);
	                location.setFile(getGrammarFileNameNoExtension());
		}

	}
		statement*
	{
		if(bool) {
			//add scope change step for runtime scoping
			LineInformation location2 = new LineInformation();
	                location2.setStartColumn($STATEMENT_LIST.getCharPositionInLine());
	                location2.setStartLine($STATEMENT_LIST.line);
	                location2.setFile(getGrammarFileNameNoExtension());
		}
	}
	);
statement:
	//	expression_statement
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
@init {
	Vector<ExpressionValue> values = new Vector<ExpressionValue>();
	Vector<ExecutionStep> steps = new Vector<ExecutionStep>();
	Vector<Integer> registers = new Vector<Integer>();
	Vector<String> types = new Vector<String>();
	Vector<TypeDescriptor> argumentTypes = new Vector<TypeDescriptor>();
	int startLocation = 0;
}
	:	
		^(SOLO_FUNCTION_CALL 
		{
			inCallStep = true;
			builder.addStepLabel(OpcodeType.ROOT_EXPRESSION, -1);
		}
		qualified_name (COLON ID)? LEFT_PAREN ({if(builder.getCurrentMethod() != null){startLocation = builder.getCurrentMethod().getSteps().size();}}
		e = expression 
		{
			if(builder.getCurrentMethod() != null && startLocation != builder.getCurrentMethod().getSteps().size() -1){
				builder.getCurrentMethod().getSteps().get(startLocation).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
				$e.step.setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
			}
			values.add($e.eval);
			steps.add($e.step);
			registers.add($e.eval.getRegister());
			if($e.eval.getType() != null)
				types.add($e.eval.getType().getStaticKey());
                	argumentTypes.add($e.eval.getType());
                	inCallStep = false;
		}
		(COMMA {if(builder.getCurrentMethod() != null){startLocation = builder.getCurrentMethod().getSteps().size();}} e = expression 
		{
			if(builder.getCurrentMethod() != null && startLocation != builder.getCurrentMethod().getSteps().size() -1){
				builder.getCurrentMethod().getSteps().get(startLocation).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
				$e.step.setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
			}
			values.add($e.eval);
			steps.add($e.step);
			registers.add($e.eval.getRegister());
			if($e.eval.getType() != null)
				types.add($e.eval.getType().getStaticKey());
                	argumentTypes.add($e.eval.getType());
		}
		)*)? RIGHT_PAREN) 
		{
		LineInformation location = new LineInformation();
                location.setEndColumn($qualified_name.type.getColumnEnd());
                location.setEndLine($qualified_name.type.getLineEnd());
                location.setStartColumn($qualified_name.type.getColumnBegin());
                location.setStartLine($qualified_name.type.getLineBegin());
                location.setFile(symbol.getCurrentClass().getFile().getStaticKey());
                location.setClassName(symbol.getCurrentClass().getStaticKey());
                location.setMethodName(symbol.getCurrentMethod().getStaticKey());
                
                
                symbol.addStatementFlagToCurrentFile($qualified_name.type.getLineBegin());
                
                String key = "";
                String myMethodName = "";
                if($ID == null) {
                	key = MethodDescriptor.generateKey($qualified_name.type.getStaticKey(), types);
                	myMethodName = $qualified_name.type.getStaticKey();
		}
		else {
			key = MethodDescriptor.generateKey($ID.text, types);
			myMethodName = $ID.text;
		}
		CallInfo info = new CallInfo();
		info.register = temp;
		info.location = location;
		info.argumentRegisters = registers;
		info.argumentSteps = steps;
		info.variable = $qualified_name.type;
		info.argumentTypes = argumentTypes;
		info.methodName = myMethodName;
		info.isObjectCall = ($ID != null);
		info.isSoloMethod = true;
		
		ResultTuple result =  stepFactory.addCallStep(info);
		builder.addStepLabel(OpcodeType.SOLO_METHOD_CALL, result.getStepCount());		
		
		temp = result.getNextRegister();
		}
	|	^(SOLO_FUNCTION_CALL_PARENT 
	{
		inCallStep = true;
		builder.addStepLabel(OpcodeType.ROOT_EXPRESSION, -1);
	}
	PARENT COLON qualified_name COLON ID LEFT_PAREN {if(builder.getCurrentMethod() != null){startLocation = builder.getCurrentMethod().getSteps().size();}}
		(e = expression
		{
			if(builder.getCurrentMethod() != null && startLocation != builder.getCurrentMethod().getSteps().size() -1){
				builder.getCurrentMethod().getSteps().get(startLocation).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
				$e.step.setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
			}
			values.add($e.eval);
			steps.add($e.step);
			registers.add($e.eval.getRegister());
			types.add($e.eval.getType().getStaticKey());
                	argumentTypes.add($e.eval.getType());
                	inCallStep = false;
		} (COMMA{if(builder.getCurrentMethod() != null){startLocation = builder.getCurrentMethod().getSteps().size();}}
		 e = expression
		{
			if(builder.getCurrentMethod() != null && startLocation != builder.getCurrentMethod().getSteps().size() -1){
				builder.getCurrentMethod().getSteps().get(startLocation).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
				$e.step.setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
			}
			values.add($e.eval);
			steps.add($e.step);
			registers.add($e.eval.getRegister());
			types.add($e.eval.getType().getStaticKey());
                	argumentTypes.add($e.eval.getType());
		})*)? RIGHT_PAREN)
		{
		LineInformation location = new LineInformation();
                location.setEndColumn($qualified_name.type.getColumnEnd());
                location.setEndLine($qualified_name.type.getLineEnd());
                location.setStartColumn($qualified_name.type.getColumnBegin());
                location.setStartLine($qualified_name.type.getLineBegin());
                location.setFile(symbol.getCurrentClass().getFile().getStaticKey());
                location.setClassName(symbol.getCurrentClass().getStaticKey());
                location.setMethodName(symbol.getCurrentMethod().getStaticKey());
                
                symbol.addStatementFlagToCurrentFile($qualified_name.type.getLineBegin());
                
                String key = "";
                String myMethodName = "";
                if($ID == null) {
                	key = MethodDescriptor.generateKey($qualified_name.type.getStaticKey(), types);
                	myMethodName = $qualified_name.type.getStaticKey();
		}
		else {
			key = MethodDescriptor.generateKey($ID.text, types);
			myMethodName = $ID.text;
		}
		CallInfo info = new CallInfo();
		info.register = temp;
		info.location = location;
		info.argumentRegisters = registers;
		info.argumentSteps = steps;
		info.variable = $qualified_name.type;
		info.locatedIn = $qualified_name.type.getStaticKey();
		info.argumentTypes = argumentTypes;
		info.methodName = myMethodName;
		info.isObjectCall = ($ID != null);
		info.isSoloMethod = true;
		
		ResultTuple result =  stepFactory.addParentCallStep(info);
		builder.addStepLabel(OpcodeType.SOLO_METHOD_CALL, result.getStepCount());
		
		temp = result.getNextRegister();
		inCallStep = false;
		}
	|	^(SOLO_FUNCTION_CALL_THIS 
	{
		inCallStep = true;
		builder.addStepLabel(OpcodeType.ROOT_EXPRESSION, -1);
	}
	ME COLON qualified_name (COLON ID)? LEFT_PAREN {if(builder.getCurrentMethod() != null){startLocation = builder.getCurrentMethod().getSteps().size();}}
		(e = expression 
		{
			if(builder.getCurrentMethod() != null && startLocation != builder.getCurrentMethod().getSteps().size() -1){
				builder.getCurrentMethod().getSteps().get(startLocation).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
				$e.step.setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
			}
			values.add($e.eval);
			steps.add($e.step);
			registers.add($e.eval.getRegister());
			types.add($e.eval.getType().getStaticKey());
                	argumentTypes.add($e.eval.getType());
                	inCallStep = false;
		}(COMMA{if(builder.getCurrentMethod() != null){startLocation = builder.getCurrentMethod().getSteps().size();}}
		 e = expression
		{
			if(builder.getCurrentMethod() != null && startLocation != builder.getCurrentMethod().getSteps().size() -1){
				builder.getCurrentMethod().getSteps().get(startLocation).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
				$e.step.setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
			}
			values.add($e.eval);
			steps.add($e.step);
			registers.add($e.eval.getRegister());
			types.add($e.eval.getType().getStaticKey());
                	argumentTypes.add($e.eval.getType());
		})*)? RIGHT_PAREN)
		{
		LineInformation location = new LineInformation();
                location.setEndColumn($qualified_name.type.getColumnEnd());
                location.setEndLine($qualified_name.type.getLineEnd());
                location.setStartColumn($qualified_name.type.getColumnBegin());
                location.setStartLine($qualified_name.type.getLineBegin());
                location.setFile(symbol.getCurrentClass().getFile().getStaticKey());
                location.setClassName(symbol.getCurrentClass().getStaticKey());
                location.setMethodName(symbol.getCurrentMethod().getStaticKey());
                
                symbol.addStatementFlagToCurrentFile($qualified_name.type.getLineBegin());
                
                String key = "";
                String myMethodName = "";
                if($ID == null) {
                	key = MethodDescriptor.generateKey($qualified_name.type.getStaticKey(), types);
                	myMethodName = $qualified_name.type.getStaticKey();
		}
		else {
			key = MethodDescriptor.generateKey($ID.text, types);
			myMethodName = $ID.text;
		}
		CallInfo info = new CallInfo();
		info.register = temp;
		info.location = location;
		info.argumentRegisters = registers;
		info.argumentSteps = steps;
		info.variable = $qualified_name.type;
		info.argumentTypes = argumentTypes;
		info.methodName = myMethodName;
		info.isObjectCall = ($ID != null);
		info.isSoloMethod = true;
		
		ResultTuple result =  stepFactory.addCallStep(info);
		builder.addStepLabel(OpcodeType.SOLO_METHOD_CALL, result.getStepCount());
		temp = result.getNextRegister();
		}
	;
alert_statement 
scope{
	ErrorTypeDescriptor errorType;
	ExpressionValue errorValue;
	ExecutionStep errorStep;
}
	:	^(ALERT
	{
		$alert_statement::errorValue = new ExpressionValue();
		$alert_statement::errorStep = null;
	}

	 LEFT_PAREN ex=root_expression
	{
		ErrorTypeDescriptor t = new ErrorTypeDescriptor();

		if(!$ex.eval.getType().getName().equals(TypeDescriptor.TEXT)){
			
			ClassDescriptor cd = symbol.findFullyQualifiedClass($ex.eval.getType().getName());
			
			if(cd == null)
				cd = symbol.findClass($ex.eval.getType().getName(), "Libraries.Language.Errors");
			
			if(cd.getStaticKey().equals(ErrorTypeDescriptor.ERROR) || cd.getParent(ErrorTypeDescriptor.ERROR) != null){
		        	t.setName(cd.getStaticKey());
		        	t.setType(cd.getType());
		        }else{
		                CompilerError error = new CompilerError();
		          	error.setLineNumber(cd.getLineBegin());
		          	error.setError("Class " + cd.getStaticKey() + " is not an error type." +
		          				cd.getStaticKey() + " must inherit from class Error to be an error type");
		          	error.setColumn($LEFT_PAREN.getCharPositionInLine());
		          	error.setErrorType(ErrorType.INVALID_ERROR);
		          	error.setFile(getGrammarFileNameNoExtension());
		          	vm.getCompilerErrors().addError(error);
		        }
	        }else{
	        	t = null;
	        }

		$alert_statement::errorValue = $ex.eval;
		$alert_statement::errorStep = $ex.step;
		$alert_statement::errorType = t;
	} 
	 RIGHT_PAREN
	{
		LineInformation location = new LineInformation();
                
		location.setEndColumn($alert_statement::errorStep.getEndColumn());
                location.setEndLine($alert_statement::errorStep.getEndLine());
                location.setStartColumn($alert_statement::errorStep.getBeginColumn());
                location.setStartLine($alert_statement::errorStep.getBeginLine());
                location.setFile(symbol.getCurrentClass().getFile().getStaticKey());
                location.setClassName(symbol.getCurrentClass().getStaticKey());
                location.setMethodName(symbol.getCurrentMethod().getStaticKey());
                
                
		stepFactory.addAlertStep(location, $alert_statement::errorType, $alert_statement::errorValue, $alert_statement::errorStep);
		builder.addStepLabel(OpcodeType.ALERT, -1);
		symbol.addStatementFlagToCurrentFile(location.getStartLine());
		
	}
	)

	;
check_statement
scope {
	ExceptionInfo info;
	int detect_counter;
	int tempLabelCounter;
	boolean has_always;
	LineInformation location;
	String startSymbol;
	JumpStep detectJump;
}
	:   
	{	
		$check_statement::detectJump = new JumpStep();
		$check_statement::info = new ExceptionInfo();
		$check_statement::detect_counter = 0;
		$check_statement::has_always = false;
		
		sub_counter++;
		if(sub_counter > 1){labelCounter++;}
		$check_statement::tempLabelCounter = labelCounter;
		$check_statement::info.alwaysStartLabel = builder.getCurrentClass().getStaticKey() + "_" + $check_statement::info.ALWAYS + $check_statement::tempLabelCounter + $check_statement::info.START;
	}
	check = CHECK 
	{
		$check_statement::info.checkLocation = new LineInformation(
			$CHECK.getLine(),
			$CHECK.getLine(),
			$CHECK.getCharPositionInLine(),
			$CHECK.text.length() + $CHECK.getCharPositionInLine());
		$check_statement::info.checkStartLabel = builder.getCurrentClass().getStaticKey() + "_" + $check.text + $check_statement::tempLabelCounter + $check_statement::info.START;
		stepFactory.startCheck($check_statement::info);
		$check_statement::startSymbol = "check";
		
		indexer.append("<check line = \"");
		indexer.append($check.getLine());
		indexer.append("\" ");
	} block[true] 
	{
		$check_statement::info.checkJump.setBeginColumn($check.getCharPositionInLine());
		$check_statement::info.checkJump.setEndColumn($check.getCharPositionInLine() + ($check.text.length()));
                $check_statement::info.checkJump.setEndLine($check.getLine());
		stepFactory.addCheckEndJumpStep($check_statement::info);
	}
	(	//detect statement with optional always  
	    	(detect_start = DETECT
	    	{
	    		$check_statement::info.detectLocations.add(new LineInformation(
				$detect_start.getLine(),
				$detect_start.getLine(),
				$detect_start.getCharPositionInLine(),
				$detect_start.text.length() + $detect_start.getCharPositionInLine()));
	    		if($check_statement::startSymbol.equals("check")){
		    		$check_statement::info.checkJump.setBeginLine($detect_start.getLine());
				stepFactory.endCheck($check_statement::info);
				
				indexer.append("<check line = \"");
				indexer.append($check_statement::info.checkLocation.getStartLine());
				indexer.append("\" end = \"");
				indexer.append($detect_start.getLine());
				indexer.append("\"/>");
			}else{
				$check_statement::detectJump.setBeginLine($detect_start.getLine());
		    		stepFactory.endDetect($check_statement::info, $check_statement::detect_counter);
		    		$check_statement::detect_counter = $check_statement::detect_counter + 1;
		    		
		    		indexer.append("<detect line = \"");
				indexer.append($check_statement::info.detectLocations.get($check_statement::info.detectLocations.size() - 1).getStartLine());
				indexer.append("\" end = \"");
				indexer.append($detect_start.getLine());
				indexer.append("\" type = \"");
				indexer.append($check_statement::info.detectParameters.get($check_statement::info.detectParameters.size() - 1).getType().getName());
				indexer.append("\"/>");
			}
	    		
	    		$check_statement::info.addDetectLabel($detect_start.text + $check_statement::detect_counter
	    			 + "_" + $check_statement::tempLabelCounter + $check_statement::info.START);
	    			 symbol.addStatementFlagToCurrentFile($detect_start.getLine());
	    		symbol.getControlFlow().detectStart();
	    		symbol.enterNextBlock();
	    		$check_statement::startSymbol = "detect";
	    		
	    	}
	    	
	    	det_param = detect_parameter 
	    	{
	    		Iterator<ErrorTypeDescriptor> detectParamIt = det_param.exceptionTypeList.iterator();
	    		StringBuffer types = new StringBuffer();
	    		while(detectParamIt.hasNext()){
		    		VariableParameterCommonDescriptor d = new VariableParameterCommonDescriptor();
		    		DetectParameter er = new DetectParameter();
		    		TypeDescriptor t = new TypeDescriptor();
		    		
		    		d = symbol.getVariable($det_param.name);
		    		er.setVariableNumber(d.getVariableNumber());
		    		er.errorType = detectParamIt.next();
		    		t.setName(er.errorType.getName());
		    		er.setType(t);
		    		er.setName($det_param.name);
		    		$check_statement::info.addDetectParameter(er);
		    		types.append(t.getName());
	    		}
	    		stepFactory.startDetect($check_statement::info, $check_statement::detect_counter);
	    	}
	    	
	    	block[true] 
	    	{
	    		$check_statement::detectJump = new JumpStep();
	    		$check_statement::detectJump.setType(JumpType.CHECK);
		    	$check_statement::detectJump.setBeginColumn($detect_start.getCharPositionInLine());
			$check_statement::detectJump.setEndColumn($detect_start.getCharPositionInLine() + ($detect_start.text.length()));
	                $check_statement::detectJump.setEndLine($detect_start.getLine());
	                $check_statement::info.detectJumps.add($check_statement::detectJump);
			stepFactory.addDetectEndJumpStep($check_statement::info, $check_statement::detectJump);
	    	}
	    	)+ //ends 1 or more detect statements
	    	(always = ALWAYS
	    	{
		    	$check_statement::info.alwaysLocation = new LineInformation(
				$always.getLine(),
				$always.getLine(),
				$always.getCharPositionInLine(),
				$always.text.length() + $always.getCharPositionInLine());
	    		$check_statement::detectJump.setBeginLine($always.getLine());
	    		stepFactory.endDetect($check_statement::info, $check_statement::detect_counter);
	    		$check_statement::detect_counter = $check_statement::detect_counter + 1;
	    		
	    		$check_statement::has_always = true;
	    		$check_statement::info.hasAlways = true;
	    		stepFactory.startAlways($check_statement::info, true);
	    		$check_statement::startSymbol = "always";
	    		
	    		indexer.append("<detect line = \"");
			indexer.append($check_statement::info.detectLocations.get($check_statement::info.detectLocations.size() - 1).getStartLine());
			indexer.append("\" end = \"");
			indexer.append($detect_start.getLine());
			indexer.append("\" type = \"");
			indexer.append($check_statement::info.detectParameters.get($check_statement::info.detectParameters.size() - 1).getType().getName());
			indexer.append("\"/>");
	    	}
	    	block[true]
	    	)? 

	    |   //always statements with no detect statements.
	    	always=ALWAYS 
	    	{
	    		$check_statement::info.alwaysLocation = new LineInformation(
				$always.getLine(),
				$always.getLine(),
				$always.getCharPositionInLine(),
				$always.text.length() + $always.getCharPositionInLine());
		    	$check_statement::info.checkJump.setBeginLine($always.getLine());
			stepFactory.endCheck($check_statement::info);
			
	    		$check_statement::has_always = true;
	    		$check_statement::info.hasAlways = true;
	    		stepFactory.startAlways($check_statement::info, true);
	    		$check_statement::startSymbol = "always";
	    		
	    		indexer.append("<check line = \"");
			indexer.append($check_statement::info.checkLocation.getStartLine());
			indexer.append("\" end = \"");
			indexer.append($detect_start.getLine());
			indexer.append("\"/>");
	    	}
	    
	    	block[true]
	)
	end=END
    	{
    		if($check_statement::startSymbol.equals("always")){
	    		stepFactory.endAlways($check_statement::info);
	    		
	    		indexer.append("<always line = \"");
			indexer.append($check_statement::info.alwaysLocation.getStartLine());
			indexer.append("\" end = \"");
			indexer.append($detect_start.getLine());
			indexer.append("\"/>");
		}else{
			$check_statement::detectJump.setBeginLine($end.getLine());
	    		stepFactory.endDetect($check_statement::info, $check_statement::detect_counter);
	    		$check_statement::detect_counter = $check_statement::detect_counter + 1;
	    		
	    		if ($check_statement::has_always == false) {
	    			$check_statement::info.hasAlways = false;
	    			stepFactory.startAlways($check_statement::info, false);
	    			stepFactory.endAlways($check_statement::info);
    			}
    			
    			indexer.append("<detect line = \"");
			indexer.append($check_statement::info.detectLocations.get($check_statement::info.detectLocations.size() - 1).getStartLine());
			indexer.append("\" end = \"");
			indexer.append($detect_start.getLine());
			indexer.append("\" type = \"");
			indexer.append($check_statement::info.detectParameters.get($check_statement::info.detectParameters.size() - 1).getType().getName());
			indexer.append("\"/>");
		}

		sub_counter--;
		$check_statement::tempLabelCounter--;
		if(sub_counter == 0){labelCounter++;}
	} 
	 ;

detect_parameter returns[String name, ArrayList<ErrorTypeDescriptor> exceptionTypeList]
scope{
	ArrayList<ErrorTypeDescriptor> exceptionList;
}
	:	^(id=ID 
	(OF_TYPE 
	{
		$detect_parameter::exceptionList = new ArrayList<ErrorTypeDescriptor>();
	}
	qn=qualified_name
	{
		ErrorTypeDescriptor t = new ErrorTypeDescriptor();

		ClassDescriptor cd = symbol.findClassDescriptorFromCurrentClass($qn.type.getStaticKey());

		if(cd == null) { //this is a compiler error
			cd = symbol.getCurrentClass();
			GenericDescriptor gd = cd.getTemplateVariable($qn.type.getStaticKey());
			if(gd != null){
				t.setName(gd.getType().getStaticKey());
			}else{
				CompilerError error = new CompilerError();
				error.setLineNumber($qn.type.getLineBegin());
				error.setError("Class " + $qn.type.getStaticKey() + " could not be found." +
					" Did you forget a \"use\" statement?");
				error.setErrorType(ErrorType.MISSING_CLASS);
				error.setColumn($qn.type.getColumnBegin());
				error.setFile(getGrammarFileNameNoExtension());
				vm.getCompilerErrors().addError(error);
			}
		}
		else {
			if(cd.getStaticKey().equals(ErrorTypeDescriptor.ERROR) || cd.getParent(ErrorTypeDescriptor.ERROR) != null){
            			t.setName(cd.getStaticKey());
            		}else{
                                CompilerError error = new CompilerError();
            			error.setLineNumber(cd.getLineBegin());
            			error.setError("Class " + cd.getStaticKey() + " is not an error type." +
            					cd.getStaticKey() + " must inherit from class Error to be an error type");
            			error.setErrorType(ErrorType.INVALID_ERROR);
            			error.setColumn($qn.type.getColumnBegin());
            			error.setFile(getGrammarFileNameNoExtension());
            			vm.getCompilerErrors().addError(error);
                         }
		}
		$detect_parameter::exceptionList.add(t);
	}
	(OR qn=qualified_name
	{
		t = new ErrorTypeDescriptor();

		cd = symbol.findClassDescriptorFromCurrentClass($qn.type.getStaticKey());

		if(cd == null) { //this is a compiler error
			cd = symbol.getCurrentClass();
			GenericDescriptor gd = cd.getTemplateVariable($qn.type.getStaticKey());
			if(gd != null){
				t.setName(gd.getType().getStaticKey());
			}else{
				CompilerError error = new CompilerError();
				error.setLineNumber($qn.type.getLineBegin());
				error.setError("Class " + $qn.type.getStaticKey() + " could not be found." +
					" Did you forget a \"use\" statement?");
				error.setErrorType(ErrorType.MISSING_CLASS);
				error.setColumn($qn.type.getColumnBegin());
				error.setFile(getGrammarFileNameNoExtension());
				vm.getCompilerErrors().addError(error);
			}
		}
		else {
			if(cd.getStaticKey().equals(ErrorTypeDescriptor.ERROR) || cd.getParent(ErrorTypeDescriptor.ERROR) != null){
				t.setName(cd.getStaticKey());
			}else{
				CompilerError error = new CompilerError();
            			error.setLineNumber(cd.getLineBegin());
            			error.setError("Class " + cd.getStaticKey() + " is not an error type." +
            					cd.getStaticKey() + " must inherit from class Error to be an error type");
            			error.setErrorType(ErrorType.INVALID_ERROR);
            			error.setColumn($qn.type.getColumnBegin());
            			error.setFile(getGrammarFileNameNoExtension());
            			vm.getCompilerErrors().addError(error);
			}
		}
		$detect_parameter::exceptionList.add(t);
	}
	)*)?)
	{
		if($detect_parameter::exceptionList == null || $detect_parameter::exceptionList.isEmpty()){
			$detect_parameter::exceptionList = new ArrayList<ErrorTypeDescriptor>();
			ErrorTypeDescriptor t = new ErrorTypeDescriptor();
			t.setName(ErrorTypeDescriptor.ERROR);
			$detect_parameter::exceptionList.add(t);
		}
		$exceptionTypeList = $detect_parameter::exceptionList;
		$name = $ID.text;
	}
	;
		
print_statement 
	:	PRINT root_expression
	{
		ExecutionStep step = $root_expression.step;
		ExpressionValue value = $root_expression.eval;

		LineInformation location = new LineInformation();
                location.setEndColumn(step.getEndColumn());
                location.setEndLine(step.getEndLine());
                location.setStartColumn(step.getBeginColumn());
                location.setStartLine(step.getBeginLine());
                location.setFile(getGrammarFileNameNoExtension());

                symbol.addStatementFlagToCurrentFile(step.getBeginLine());
                
		stepFactory.addPrintStep(location, $root_expression.eval, $root_expression.step);
		builder.addStepLabel(OpcodeType.PRINT, -1);
	}
	;


speak_statement 
	:	SAY root_expression
	{
		ExecutionStep step = $root_expression.step;
		ExpressionValue value = $root_expression.eval;

		LineInformation location = new LineInformation();
                location.setEndColumn(step.getEndColumn());
                location.setEndLine(step.getEndLine());
                location.setStartColumn(step.getBeginColumn());
                location.setStartLine(step.getBeginLine());
                location.setFile(getGrammarFileNameNoExtension());

                symbol.addStatementFlagToCurrentFile(step.getBeginLine());
		stepFactory.addSpeakStep(location, $root_expression.eval, $root_expression.step);
		builder.addStepLabel(OpcodeType.SAY, -1);
	}
	;

return_statement
	:	RETURN (root_expression
	{

		ExecutionStep step = $root_expression.step;
		ExpressionValue value = $root_expression.eval;

		LineInformation location = new LineInformation();
                location.setEndColumn(step.getEndColumn());
                location.setEndLine(step.getEndLine());
                location.setStartColumn(step.getBeginColumn());
                location.setStartLine(step.getBeginLine());
                location.setFile(getGrammarFileNameNoExtension());

                symbol.addStatementFlagToCurrentFile(step.getBeginLine());
		stepFactory.addReturnStep(location, $root_expression.eval, $root_expression.step);
		builder.addStepLabel(OpcodeType.RETURN, -1);
	}
	| NOW 
	{
		LineInformation location = new LineInformation();
                location.setEndColumn($NOW.getCharPositionInLine());
                location.setEndLine($NOW.getLine());
                location.setStartColumn($RETURN.getCharPositionInLine());
                location.setStartLine($RETURN.getLine());
                location.setFile(getGrammarFileNameNoExtension());

                symbol.addStatementFlagToCurrentFile($RETURN.getLine());
		stepFactory.addReturnStep(location, null, null);
		builder.addStepLabel(OpcodeType.RETURN, -1);
	}
	)
	;
generic_declaration
	:	^(GENERIC LESS ids+=ID (COMMA ids+=ID)* GREATER
	)
	;
generic_statement returns [ArrayList<GenericDescriptor> templateTypes]
	:	^(GENERIC LESS 
	{
		ArrayList<GenericDescriptor> types = new ArrayList<GenericDescriptor>();
	}
	ad=assignment_declaration
	{
		TypeDescriptor type = $ad.myType;
				
		GenericDescriptor genericType = new GenericDescriptor();
		genericType.setLineBegin(type.getLineBegin());
		genericType.setColumnBegin(type.getColumnBegin());
		genericType.setLineEnd(type.getLineEnd());
		genericType.setColumnEnd(type.getColumnEnd());
		genericType.setName(type.getName());
		genericType.addBoundType(type);
			
		types.add(genericType);
	}
	 (COMMA a=assignment_declaration
	{
		type = $a.myType;
		
		genericType = new GenericDescriptor();
		genericType.setLineBegin(type.getLineBegin());
		genericType.setColumnBegin(type.getColumnBegin());
		genericType.setLineEnd(type.getLineEnd());
		genericType.setColumnEnd(type.getColumnEnd());
		genericType.setName(type.getName());
		genericType.addBoundType(type);
		
		types.add(genericType);
	}
	 )* 
	{
	
	 	$templateTypes = types;
	}
	 GREATER)
	;
//expression_statement
//	:	^(EXPRESSION_STATEMENT expr = root_expression)
//	{
//		symbol.addStatementFlagToCurrentFile($expr.step.getBeginLine());
//	}
//	;
class_type returns [TypeDescriptor myType]
	:	qn = qualified_name
	{
		TypeDescriptor t = new TypeDescriptor();

		ClassDescriptor cd = symbol.findClassDescriptorFromCurrentClass($qn.type.getStaticKey());

		if(cd == null) { //this is a compiler error
			cd = symbol.getCurrentClass();
			GenericDescriptor gd = cd.getTemplateVariable($qn.type.getStaticKey());
			if(gd != null){
				t = gd.getType();
			}else{
				CompilerError error = new CompilerError();
				error.setLineNumber($qn.type.getLineBegin());
				error.setError("Class " + $qn.type.getStaticKey() + " could not be found." +
					" Did you forget a \"use\" statement?");
				error.setErrorType(ErrorType.MISSING_CLASS);
				error.setColumn($qn.type.getColumnBegin());
				error.setFile(getGrammarFileNameNoExtension());
				vm.getCompilerErrors().addError(error);
			}
		}
		else {
			t.setName(cd.getStaticKey());
		}
		$myType=t;
	}
	;

assignment_declaration returns [TypeDescriptor myType]
	:	qn = qualified_name gs=generic_statement?
	{
		TypeDescriptor t = new TypeDescriptor();

		ClassDescriptor cd = symbol.findClassDescriptorFromCurrentClass($qn.type.getStaticKey());

		if(cd == null) { //this is a compiler error
			cd = symbol.getCurrentClass();
			GenericDescriptor gd = cd.getTemplateVariable($qn.type.getStaticKey());
			if(gd != null){
				t = gd.getType();
			}else{
				CompilerError error = new CompilerError();
				error.setLineNumber($qn.type.getLineBegin());
				error.setError("Class " + $qn.type.getStaticKey() + " could not be found." +
					" Did you forget a \"use\" statement?");
				error.setErrorType(ErrorType.MISSING_CLASS);
				error.setColumn($qn.type.getColumnBegin());
				error.setFile(getGrammarFileNameNoExtension());
				vm.getCompilerErrors().addError(error);
			}
		}
		else {
			if($gs.templateTypes != null){
				t.setSubTypes($gs.templateTypes);
			}
			t.setName(cd.getStaticKey());
		}
		$myType=t;
	}
	|	INTEGER_KEYWORD
	{
		TypeDescriptor type = new TypeDescriptor();
		type.setName(TypeDescriptor.INTEGER);
		$myType = type;
	}
	|	NUMBER_KEYWORD
	{
		TypeDescriptor type = new TypeDescriptor();
		type.setName(TypeDescriptor.NUMBER);
		$myType = type;
	}
	|	TEXT
	{
		TypeDescriptor type = new TypeDescriptor();
		type.setName(TypeDescriptor.TEXT);
		$myType = type;
	}
	|	BOOLEAN_KEYWORD
	{
		TypeDescriptor type = new TypeDescriptor();
		type.setName(TypeDescriptor.BOOLEAN);
		$myType = type;
	};
assignment_statement
	:
		(sel=selector COLON)? ID rhs=assign_right_hand_side
	{
		boolean isMe = false;
		LineInformation location = new LineInformation (
			 $ID.line,
			 0,
			 $ID.getCharPositionInLine(),
			 0
		);
		
		location.setFile(symbol.getCurrentClass().getFile().getStaticKey());
                location.setClassName(symbol.getCurrentClass().getStaticKey());
                if(symbol.getCurrentMethod() != null){
                	location.setMethodName(symbol.getCurrentMethod().getStaticKey());
                }
                
		ScopeSelector scope = $selector.scopeSel;
		ClassDescriptor cd = null;
		if(scope!= null){
			if(scope.isParent()){
				cd = scope.getCurrentClass();
			}else{
				cd = symbol.getCurrentClass();
				isMe = true;
			}
		}
		
		symbol.addStatementFlagToCurrentFile($ID.line);
		
		boolean hasRHS = false;
		if($rhs.eval != null){
			hasRHS = true;
		}
		
		stepFactory.addAssignmentStep(location, $ID.text, $rhs.eval, $rhs.step, false, "", cd, isMe, hasRHS);
		builder.addStepLabel(OpcodeType.ASSIGNMENT, -1);
		
		indexer.append("<assignment line = \"");
		indexer.append($ID.line);
		indexer.append("\" variable = \"");
		indexer.append($ID.text);
		indexer.append("\"/>");
	}
	|	obj=qualified_name (COLON PARENT COLON parent=qualified_name)? COLON ID rhs=assign_right_hand_side
	{
		LineInformation location = new LineInformation (
			 $ID.line,
			 0,
			 $ID.getCharPositionInLine(),
			 0
		);
		location.setFile(symbol.getCurrentClass().getFile().getStaticKey());
                location.setClassName(symbol.getCurrentClass().getStaticKey());
                if(symbol.getCurrentMethod() != null){
                	location.setMethodName(symbol.getCurrentMethod().getStaticKey());
                }
                
		boolean isLocal = $type.myType != null;
		
		symbol.addStatementFlagToCurrentFile($ID.line);
		ClassDescriptor cd = null;
		
		if($parent.type != null){
			cd = symbol.findClassDescriptorFromCurrentClass($parent.type.getStaticKey());
			
			if(cd == null){
				CompilerError error = new CompilerError($PARENT.getLine(), "The class "+ symbol.getCurrentClass().getStaticKey() +" does not have access to " + $parent.type.getStaticKey(), ErrorType.MISSING_PARENT);
				vm.getCompilerErrors().addError(error);
			}
		}
		
		boolean hasRHS = false;
		if($rhs.eval != null){
			hasRHS = true;
		}
		
		stepFactory.addAssignmentStep(location, $obj.type.getStaticKey(), $rhs.eval, $rhs.step, isLocal, $ID.text, cd, false, hasRHS);
		builder.addStepLabel(OpcodeType.ASSIGNMENT, -1);
		
		indexer.append("<assignment line = \"");
		indexer.append($ID.line);
		indexer.append("\" variable = \"");
		indexer.append($ID.text);
		indexer.append("\"/>");
	}
	|	modifier = access_modifier? CONSTANT? type = assignment_declaration name = ID rhs=assign_right_hand_side?
	{
	                	LineInformation location = new LineInformation (
	                    		$ID.line,
	                    		0,
	                    		$ID.getCharPositionInLine(),
	 		0
		);
                		location.setFile(symbol.getCurrentClass().getFile().getStaticKey());
                		location.setClassName(symbol.getCurrentClass().getStaticKey());
                		if(symbol.getCurrentMethod() != null){
                			location.setMethodName(symbol.getCurrentMethod().getStaticKey());
                		}
                
                		boolean isLocal = $type.myType != null;
                
                		symbol.addStatementFlagToCurrentFile($ID.line);
                
		if($rhs.eval != null)
		{
			stepFactory.addAssignmentStep(location, $ID.text, $rhs.eval, $rhs.step, isLocal, true);
		}
	                	else { // are we are trying to instantiate an object?
	                		builder.addStepLabel(OpcodeType.ROOT_EXPRESSION, -1);
	                    		stepFactory.addAssignmentStep(location, $ID.text, isLocal, false);
	                	}
	                	builder.addStepLabel(OpcodeType.ASSIGNMENT, -1);
		indexer.append("<assignment line = \"");
		indexer.append($ID.line);
		indexer.append("\" variable = \"");
		indexer.append($ID.text);
		indexer.append("\"/>");
	}
	;
assign_right_hand_side returns[ExpressionValue eval, ExecutionStep step]
	:	//^(CONSTRUCTOR expression*)
		(EQUALITY root_expression)
	{
		$eval = $root_expression.eval;
		$step = $root_expression.step;
	}
	;


if_statement	
scope {
	IfStatementInfo info;
	int else_if_counter;
	String endMatch;
}
		:	begin_if = IF
		{
			$if_statement::endMatch = "if";
			$if_statement::info = new IfStatementInfo();
			$if_statement::else_if_counter = 0;
			$if_statement::info.endLabel = $begin_if.text + labelCounter + $if_statement::info.END;
			$if_statement::info.ifFalseLabel =$begin_if.text + labelCounter + $if_statement::info.FALSE;
			$if_statement::info.ifStartLabel =$begin_if.text + labelCounter + $if_statement::info.START;
			symbol.addStatementFlagToCurrentFile($begin_if.getLine());
			labelCounter++;
					
		}
			condition = root_expression 
		{
			stepFactory.assertBooleanExpression($condition.eval.getType(),
			    $condition.step,
			    getGrammarFileNameNoExtension());
			    
			$if_statement::info.ifLocation = new LineInformation(
				$begin_if.getLine(),
				$begin_if.getLine(),
				$begin_if.getCharPositionInLine(),
				$begin_if.text.length() + $begin_if.getCharPositionInLine());
				
			ConditionalJumpIfStep ifConditionalStep = new ConditionalJumpIfStep();
			ifConditionalStep.setEndColumn($begin_if.text.length() + $begin_if.getCharPositionInLine());
			ifConditionalStep.setEndLine($begin_if.getLine());
			ifConditionalStep.setBeginColumn($begin_if.getCharPositionInLine());
			ifConditionalStep.setBeginLine($begin_if.getLine());

			ifConditionalStep.setLeftRegister($condition.eval.getRegister());
			$if_statement::info.ifConditionalStep = ifConditionalStep;

			stepFactory.startIf($if_statement::info);
			
		}
			b=block[true] //<< true code 
		{
                        
                        $if_statement::info.ifJumpStep.setBeginColumn($begin_if.getCharPositionInLine());
			$if_statement::info.ifJumpStep.setEndColumn($begin_if.getCharPositionInLine() + ($begin_if.text.length()));
	                $if_statement::info.ifJumpStep.setEndLine($begin_if.getLine());
			stepFactory.addIfEndJumpStep($if_statement::info);
			
		}		
		(begin_else_if = ELSE_IF 
		{
			
			if($if_statement::endMatch.equals("if"))
			{
				$if_statement::info.ifJumpStep.setBeginLine($begin_else_if.getLine());
				stepFactory.endIf($if_statement::info);
				
				indexer.append("<if line = \"");
				indexer.append($if_statement::info.ifLocation.getStartLine());
				indexer.append("\" end = \"");
				indexer.append($begin_else_if.getLine());
				indexer.append("\"/>");
			}
			else
			{
				$if_statement::info.elseIfJumpSteps.get($if_statement::else_if_counter).setBeginLine($begin_else_if.getLine());
				stepFactory.endElseIf($if_statement::info, $if_statement::else_if_counter);
				$if_statement::else_if_counter = $if_statement::else_if_counter + 1;
				
				indexer.append("<elseif line = \"");
				indexer.append($if_statement::info.elseIfLocations.get($if_statement::info.elseIfLocations.size() - 1).getStartLine());
				indexer.append("\" end = \"");
				indexer.append($begin_else_if.getLine());
				indexer.append("\"/>");
			}
			$if_statement::endMatch = "elseif";
			
			$if_statement::info.elseIfEndLabels.add($begin_else_if.text  + labelCounter + $if_statement::info.END + $if_statement::else_if_counter);
			$if_statement::info.elseIfFalseLabels.add($begin_else_if.text + labelCounter + $if_statement::info.FALSE + $if_statement::else_if_counter);
			$if_statement::info.elseIfStartLabels.add($begin_else_if.text + + labelCounter + $if_statement::info.START + $if_statement::else_if_counter);	
			
			symbol.addStatementFlagToCurrentFile($begin_else_if.getLine());		
			labelCounter++;
					
		}
		condition2 = root_expression
		{
			stepFactory.assertBooleanExpression($condition2.eval.getType(),
			    $condition2.step,
			    getGrammarFileNameNoExtension());
			    
			$if_statement::info.elseIfLocations.add(new LineInformation(
				$begin_else_if.getLine(),
				$begin_else_if.getLine(),
				$begin_else_if.getCharPositionInLine(),
				$begin_else_if.text.length() + $begin_else_if.getCharPositionInLine()));
			
			ConditionalJumpIfStep conditionalStep = new ConditionalJumpIfStep();
			conditionalStep.setIsElseIf(true);
			conditionalStep.setEndColumn($begin_else_if.text.length() + $begin_else_if.getCharPositionInLine());
			conditionalStep.setEndLine($begin_else_if.getLine());
			conditionalStep.setBeginColumn($begin_else_if.getCharPositionInLine());
			conditionalStep.setBeginLine($begin_else_if.getLine());

			conditionalStep.setLeftRegister($condition2.eval.getRegister());
			
			$if_statement::info.elseIfConditionalSteps.add(conditionalStep);	
			
			stepFactory.startElseIf($if_statement::info,$if_statement::else_if_counter);
		}
		b=block[true] 
		{
			JumpStep jump = new JumpStep();
			jump.setType(JumpType.IF);
			jump.setBeginColumn($begin_else_if.getCharPositionInLine());
			jump.setEndColumn($begin_else_if.getCharPositionInLine() + ($begin_else_if.text.length()));
			jump.setEndLine($begin_else_if.getLine());
			$if_statement::info.elseIfJumpSteps.add(jump);
			
			stepFactory.addElseIfEndJumpStep($if_statement::info, $if_statement::else_if_counter);	
		}
		{
								
		}					
		)*					
		(begin_else = ELSE
		{
			$if_statement::info.elseLocation = new LineInformation(
				$begin_else.getLine(),
				$begin_else.getLine(),
				$begin_else.getCharPositionInLine(),
				$begin_else.text.length() + $begin_if.getCharPositionInLine());
			if($if_statement::endMatch.equals("if"))
			{
				$if_statement::info.ifJumpStep.setBeginLine($begin_else.getLine());
				stepFactory.endIf($if_statement::info);
				
				indexer.append("<if line = \"");
				indexer.append($if_statement::info.ifLocation.getStartLine());
				indexer.append("\" end = \"");
				indexer.append($begin_else.getLine());
				indexer.append("\"/>");
			}
			else
			{
				$if_statement::info.elseIfJumpSteps.get($if_statement::else_if_counter).setBeginLine($begin_else.getLine());
				stepFactory.endElseIf($if_statement::info, $if_statement::else_if_counter);
				$if_statement::else_if_counter = $if_statement::else_if_counter + 1;
				
				indexer.append("<elseif line = \"");
				indexer.append($if_statement::info.elseIfLocations.get($if_statement::info.elseIfLocations.size() - 1).getStartLine());
				indexer.append("\" end = \"");
				indexer.append($begin_else.getLine());
				indexer.append("\"/>");
			}
			$if_statement::endMatch = "else";
			
			$if_statement::info.elseStartLabel = $begin_else.text + labelCounter + $if_statement::info.START;	
			$if_statement::info.hasElse = true;
			labelCounter++;	
			stepFactory.startElse($if_statement::info);	
			
		}
		b=block[true])?
		end=END
		{
			if($if_statement::endMatch.equals("if"))
			{
				$if_statement::info.ifJumpStep.setBeginLine($end.getLine());
				stepFactory.endIf($if_statement::info);
				
				indexer.append("<if line = \"");
				indexer.append($if_statement::info.ifLocation.getStartLine());
				indexer.append("\" end = \"");
				indexer.append($end.getLine());
				indexer.append("\"/>");
			}
			else if($if_statement::endMatch.equals("elseif"))
			{
				$if_statement::info.elseIfJumpSteps.get($if_statement::else_if_counter).setBeginLine($end.getLine());
				stepFactory.endElseIf($if_statement::info, $if_statement::else_if_counter);
				// $if_statement::else_if_counter = $if_statement::else_if_counter + 1;
				
				indexer.append("<elseif line = \"");
				indexer.append($if_statement::info.elseIfLocations.get($if_statement::info.elseIfLocations.size() - 1).getStartLine());
				indexer.append("\" end = \"");
				indexer.append($end.getLine());
				indexer.append("\"/>");
			}
			else
			{
				stepFactory.endElse($if_statement::info);
				
				indexer.append("<else line = \"");
				indexer.append($if_statement::info.elseLocation.getStartLine());
				indexer.append("\" end = \"");
				indexer.append($end.getLine());
				indexer.append("\"/>");
			}
			
			stepFactory.endIfBlock($if_statement::info);
  																		
		}		
		;

loop_statement
scope {
	String marker_top;
	String marker_loop;
	String marker_bottom;
	LineInformation location;
	ResultTuple loop_counter;
	JumpStep jumpToTop;

	ExpressionValue first_value;
	ExecutionStep first_step;
	ExpressionValue last_value;
	ExecutionStep last_step;
	int type;
	ConditionalJumpLoopStep cJumpStep;
	ConditionalJumpUntilStep uJumpStep;
	boolean hasCounter;
	boolean isWhile;
}
	:
	REPEAT
	{

		$loop_statement::marker_top = $REPEAT.text + labelCounter + "_top";
		$loop_statement::marker_loop = $REPEAT.text + labelCounter + "_loop";
		$loop_statement::marker_bottom = $REPEAT.text + labelCounter + "_bottom";
		labelCounter++;
		$loop_statement::location = new LineInformation(
			$REPEAT.getLine(),
			$REPEAT.getLine(),
			$REPEAT.getCharPositionInLine(),
			$REPEAT.text.length() + $REPEAT.getCharPositionInLine());
		$loop_statement::loop_counter = null;
		$loop_statement::jumpToTop = new JumpStep();
		$loop_statement::jumpToTop.setType(JumpType.LOOP);
		$loop_statement::jumpToTop.setLineInformation($loop_statement::location);
		symbol.addStatementFlagToCurrentFile($REPEAT.getLine());

		$loop_statement::first_value = null;
		$loop_statement::first_step = null;
		$loop_statement::last_value = null;
		$loop_statement::last_step = null;
		$loop_statement::type = -1;
		$loop_statement::cJumpStep = null;
		
		CompilerError e = symbol.getControlFlow().addStatement($loop_statement::location);
	        	if (e != null) {
	                		this.builder.getVirtualMachine().getCompilerErrors().addError(e);
	        	}
	        
		symbol.getControlFlow().repeatStart();
		
	}

	(

	//( OVER ID )
	//{
	//	$loop_statement::type = 0;
	//}
	//|	((FROM ran = range)
	//{
	//		$loop_statement::first_value = $ran.first_value;
	//	$loop_statement::first_step = $ran.first_step;
	//	$loop_statement::last_value = $ran.last_value;
	//	$loop_statement::last_step = $ran.last_step;
	//	
	//	$loop_statement::loop_counter = stepFactory.addLoopCounter(temp,$loop_statement::first_value,$loop_statement::location);
	//	builder.addLabel($loop_statement::jumpToTop);
	//	temp = $loop_statement::loop_counter.getNextRegister();
	//	ResultTuple jump_compare = stepFactory.addBinaryLessEqualsStep(temp, $loop_statement::loop_counter.getValue(), $loop_statement::loop_counter.getStep(),  $loop_statement::last_value, $loop_statement::last_step);
	//	temp = jump_compare.getNextRegister();
	//	$loop_statement::cJumpStep = new ConditionalJumpLoopStep();
	//	$loop_statement::cJumpStep.setLeftRegister(jump_compare.getValue().getRegister());
	//	$loop_statement::cJumpStep.setLineInformation($loop_statement::location);
	//	$loop_statement::cJumpStep.setLoopType(LoopType.FROM);
	//	builder.add($loop_statement::cJumpStep);
	//	builder.addStepLabel(OpcodeType.FROM, -1);
	//	builder.addMarker($loop_statement::marker_bottom);
	//	
	//	symbol.enterNextBlock();
	//	$loop_statement::type = 1;
	//	stepFactory.addBeginScopeStep($loop_statement::marker_loop, "loop");
	//})
		(expr = root_expression TIMES)
	{
		$loop_statement::first_value = $expr.eval;
		TypeDescriptor type = $expr.eval.getType();
		if(type == null || !type.isInteger()) {
			CompilerError error = new CompilerError($TIMES.getLine(), "repeat times requires an integer.", ErrorType.REPEAT_TIMES_NON_INTEGER);
			vm.getCompilerErrors().addError(error);	
		}
		$loop_statement::first_step = $expr.step;
		$loop_statement::loop_counter = stepFactory.addLoopCounter(temp,$loop_statement::location);
		builder.addLabel($loop_statement::jumpToTop);
		temp = $loop_statement::loop_counter.getNextRegister();
		ResultTuple jump_compare = stepFactory.addBinaryLessThanStep(temp, $loop_statement::loop_counter.getValue(), $loop_statement::loop_counter.getStep(),  $loop_statement::first_value, $loop_statement::first_step);
		temp = jump_compare.getNextRegister();
		$loop_statement::cJumpStep = new ConditionalJumpLoopStep();
		$loop_statement::cJumpStep.setIsEndValueKnown(true);
		$loop_statement::cJumpStep.setHowManyTimesRegister($expr.eval.getRegister());
		$loop_statement::cJumpStep.setLeftRegister(jump_compare.getValue().getRegister());
		$loop_statement::cJumpStep.setLineInformation($loop_statement::location);
		$loop_statement::cJumpStep.setLoopType(LoopType.TIMES);
		builder.add($loop_statement::cJumpStep);
		builder.addStepLabel(OpcodeType.TIMES, -1);
		builder.addMarker($loop_statement::marker_bottom);
		stepFactory.addBeginScopeStep($loop_statement::marker_loop, "loop");
		symbol.enterNextBlock();
		$loop_statement::type = 1;
		
	}
	|	((WHILE {$loop_statement::isWhile = true;} | UNTIL {$loop_statement::isWhile = false;}) 
	{
               		builder.addLabel($loop_statement::jumpToTop);
	} 
	expr = root_expression
		{
			$loop_statement::first_value = $expr.eval;
			$loop_statement::first_step = $expr.step;
			$loop_statement::type = 2;
			
			TypeDescriptor type = $expr.eval.getType();
			if(type == null || !type.isBoolean()) {
				CompilerError error = new CompilerError($REPEAT.getLine(), "repeat loops require a boolean expression.", ErrorType.REPEAT_NON_BOOLEAN);
				vm.getCompilerErrors().addError(error);	
			}
			
			if($loop_statement::isWhile){
				$loop_statement::cJumpStep = new ConditionalJumpLoopStep();
				$loop_statement::cJumpStep.setLoopType(LoopType.WHILE);
	
				$loop_statement::cJumpStep.setLeftRegister($loop_statement::first_value.getRegister());
				$loop_statement::cJumpStep.setLineInformation($loop_statement::location);
				builder.add($loop_statement::cJumpStep);
			}else{
				$loop_statement::uJumpStep = new ConditionalJumpUntilStep();
				$loop_statement::uJumpStep.setLoopType(LoopType.UNTIL);
	
				$loop_statement::uJumpStep.setLeftRegister($loop_statement::first_value.getRegister());
				$loop_statement::uJumpStep.setLineInformation($loop_statement::location);
				builder.add($loop_statement::uJumpStep);
			}
			builder.addStepLabel(OpcodeType.LOOP, -1);
			builder.addMarker($loop_statement::marker_bottom);
			stepFactory.addBeginScopeStep($loop_statement::marker_loop, "loop");
			symbol.enterNextBlock();

		}))
	
	block[true]
	{
		stepFactory.addEndScopeStep("loop", $loop_statement::location);
		symbol.popScope();
		if($loop_statement::type == 1)
		{
			// move the ammount to increment to a register
			ResultTuple inc_ammount = stepFactory.addMoveStep(temp, $loop_statement::location, 1);
			temp = inc_ammount.getNextRegister();
			//add the increment ammount to the loop counter
			ResultTuple inc_result = stepFactory.addBinaryAddStep(temp, inc_ammount.getValue(), inc_ammount.getStep(), $loop_statement::loop_counter.getValue(), $loop_statement::loop_counter.getStep());
			int loopResult = temp;
			temp = inc_result.getNextRegister();
			//move the result to loop counters register
			stepFactory.addMoveRegistersStep($loop_statement::loop_counter.getValue().getRegister(), loopResult, $loop_statement::location);
		}
		
		builder.convertBackJump($loop_statement::jumpToTop);
		builder.add($loop_statement::jumpToTop);
		builder.addLabel($loop_statement::marker_bottom);
	}	
	END
	{
		
		symbol.getControlFlow().repeatEnd();
		
		indexer.append("<loop line = \"");
		indexer.append($loop_statement::location.getStartLine());
		indexer.append("\" end = \"");
		indexer.append($END.getLine());
		indexer.append("\"/>");
	}
		;

//range		returns[ExpressionValue first_value, ExecutionStep first_step, ExpressionValue last_value, ExecutionStep last_step]
//	:	^(
//		TO first = root_expression{} last = root_expression
//	{
//		TypeCheckerResult result = typeChecker.check($first.eval.getType(), $last.eval.getType(), OperationEnum.RANGE, false);
//
//		if(result.getResult() == null)
//		{
//			CompilerError error = new CompilerError($TO.getLine(), result.getErrorMessage(), result.getErrorType());
//			vm.getCompilerErrors().addError(error);
//		}
//
//		
//		$first_value = $first.eval;
//		$first_step = $first.step;
//		$last_value = $last.eval;
//		$last_step = $last.step;
//	}
//		)
//	;
selector returns[ScopeSelector scopeSel]
	:	^(PARENT qualified_name)
	{
		ScopeSelector scopeItem = new ScopeSelector();
		ClassDescriptor cd = symbol.findClassDescriptorFromCurrentClass($qualified_name.type.getStaticKey());
		
		if(cd == null){
			CompilerError error = new CompilerError($PARENT.getLine(), "The class "+ symbol.getCurrentClass().getStaticKey() +" does not have access to " + $qualified_name.type.getStaticKey(), ErrorType.MISSING_PARENT);
			vm.getCompilerErrors().addError(error);
		}
		scopeItem.setIsParent(true);
		scopeItem.setCurrentClass(cd);
		$scopeSel = scopeItem;
	}
	|	ME 
	{
		ScopeSelector scopeItem = new ScopeSelector();
		scopeItem.setIsParent(false);
		$scopeSel = scopeItem;
	}
	;
		
root_expression returns[ExpressionValue eval, ExecutionStep step]
	:	
	{
		builder.addStepLabel(OpcodeType.ROOT_EXPRESSION, -1);
	}
	^(ROOT_EXPRESSION expression) 
	{
		expressionDepth = 0;
		$eval = $expression.eval;
		$step = $expression.step;
		$eval.getResult().getType().setExpressionLevel(expressionDepth );
	}
	;

expression	returns[ExpressionValue eval, ExecutionStep step]
	:
		^(UNARY_NOT NOT expr = expression)
	{
		ResultTuple result = stepFactory.addUnaryNotStep(temp, $expr.eval, $expr.step);
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
	}	/*-> notexpr(exp={$expr.text})*/
	|	^(EQUALITY left = expression right = expression)
	{
		ResultTuple result = stepFactory.addBinaryEqualsStep(temp, $left.eval, $left.step, $right.eval, $right.step);
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
	}
	|	^(NOTEQUALS left = expression right = expression)
	{
		ResultTuple result = stepFactory.addBinaryNotEqualsStep(temp, $left.eval, $left.step, $right.eval, $right.step);
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
	}
	|	^(GREATER left = expression right = expression)
	{
		ResultTuple result = stepFactory.addBinaryGreaterThanStep(temp, $left.eval, $left.step, $right.eval, $right.step);
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
	}
	|	^(GREATER_EQUAL left = expression right = expression)
	{
		ResultTuple result = stepFactory.addBinaryGreaterEqualsStep(temp, $left.eval, $left.step, $right.eval, $right.step);
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
	}
	|	^(INHERITS left=expression dec=class_type)
	{
		ResultTuple result = stepFactory.addBinaryIsAStep(temp, $left.eval, $left.step, $dec.myType);
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
	}
	|	^(LESS left = expression right = expression)
	{
		ResultTuple result = stepFactory.addBinaryLessThanStep(temp, $left.eval, $left.step, $right.eval, $right.step);
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
	}
	|	^(LESS_EQUAL left = expression right = expression)
	{
		ResultTuple result = stepFactory.addBinaryLessEqualsStep(temp, $left.eval, $left.step, $right.eval, $right.step);
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
	}
	|	^(OR left = expression right = expression)
	{
		ResultTuple result = stepFactory.addBinaryOrStep(temp, $left.eval, $left.step, $right.eval, $right.step);
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
	}
	|	^(AND left = expression right = expression)
	{
		ResultTuple result = stepFactory.addBinaryAndStep(temp, $left.eval, $left.step, $right.eval, $right.step);
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
	}
	|	^(PLUS left = expression right = expression)
	{
		ResultTuple result = stepFactory.addBinaryAddStep(temp, $left.eval, $left.step, $right.eval, $right.step);
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
	}
	|	^(MINUS left = expression right = expression)
	{
		ResultTuple result = stepFactory.addBinarySubtractStep(temp, $left.eval, $left.step, $right.eval, $right.step);
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
	}
	|	^(MULTIPLY left = expression right = expression)
	{
		ResultTuple result = stepFactory.addBinaryMultiplyStep(temp, $left.eval, $left.step, $right.eval, $right.step);
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
	}
	|	^(DIVIDE left = expression right = expression)
	{
		ResultTuple result = stepFactory.addBinaryDivideStep(temp, $left.eval, $left.step, $right.eval, $right.step);
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
	}
	|	^(MODULO left = expression right = expression)
	{
		ResultTuple result = stepFactory.addBinaryModStep(temp, $left.eval, $left.step, $right.eval, $right.step);
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
	}
	|	^(FUNCTION_CALL qualified_name(COLON ID)? LEFT_PAREN
	{
		boolean unsetFlag = false;
		boolean nested = inCallStep;
		if (!inCallStep) {
			// We are now inside a call step--set the flag appropriately.
			inCallStep = true;
			unsetFlag = true;
		}
		 expressionDepth++;
	}
		 fel = function_expression_list RIGHT_PAREN) //the id is non-null example: Animal.Dog:walk(50), null walk(50)
	{	
								//Dog.walk(50) should be dissallowed during semantic analysis
		LineInformation location = new LineInformation();
                location.setEndColumn($qualified_name.type.getColumnEnd());
                location.setEndLine($qualified_name.type.getLineEnd());
                location.setStartColumn($qualified_name.type.getColumnBegin());
                location.setStartLine($qualified_name.type.getLineBegin());
                location.setFile(fileName);
                location.setClassName(symbol.getCurrentClass().getStaticKey());
                MethodDescriptor curMethod = symbol.getCurrentMethod();
                if(curMethod != null){
                	location.setMethodName(curMethod.getStaticKey());
                }
                
		Vector<ExecutionStep> steps = new Vector<ExecutionStep>();
		Vector<ExpressionValue> values = new Vector<ExpressionValue>();
		Vector<Integer> registers = new Vector<Integer>();

		Vector<TypeDescriptor> types = new Vector<TypeDescriptor>();
		Vector<TypeDescriptor> argumentTypes = new Vector<TypeDescriptor>();
		
		int parameterPosition = -1;
		if(fel != null) {
			parameterPosition = $fel.firstParam;
			for(Object o : $fel.list) {
				expression_return ex = (expression_return)o;
                		types.add(ex.eval.getType());
                		argumentTypes.add(ex.eval.getType());
                		steps.add(ex.step);
                		values.add(ex.eval);
                		registers.add(ex.eval.getRegister());
			}
		}
		
                String key = "";
                String myMethodName = "";
                if($ID == null) {
                	key = MethodDescriptor.autoGenerateKey($qualified_name.type.getStaticKey(), types);
                	myMethodName = $qualified_name.type.getStaticKey();
		}
		else {
			key = MethodDescriptor.autoGenerateKey($ID.text, types);
			myMethodName = $ID.text;
		}
		CallInfo info = new CallInfo();
		info.register = temp;
		info.location = location;
		info.argumentRegisters = registers;
		info.argumentSteps = steps;
		info.variable = $qualified_name.type;
		info.argumentTypes = argumentTypes;
		info.methodName = myMethodName;
		info.isObjectCall = ($ID != null);
		info.isNested = nested;
		
		if(fel!=null && !$fel.list.isEmpty()){
			builder.addCallLabel(parameterPosition);
			//j builder.addStepLabel(OpcodeType.METHOD_CALL);
		}
		
		ResultTuple result =  stepFactory.addCallStep(info);
		
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
		
		expressionDepth--;
		$eval.getResult().getType().setExpressionLevel(expressionDepth);
		if(fel!=null){
			builder.addStepLabel(OpcodeType.METHOD_CALL, result.getStepCount());
		}
		
		if (unsetFlag)
			inCallStep = false;

	}
	|	^(QUALIFIED_SOLO_EXPRESSION var=qualified_name (COLON ID)?)
	{
		LineInformation location = new LineInformation (
			$var.type.getLineBegin(),
			$var.type.getLineEnd(),
			$var.type.getColumnBegin(),
			$var.type.getColumnEnd()
		);
		location.setFile(fileName);
                location.setClassName(symbol.getCurrentClass().getStaticKey());
                MethodDescriptor curMethod = symbol.getCurrentMethod();
                if(curMethod != null){
                	location.setMethodName(curMethod.getStaticKey());
                }

		ResultTuple result = new ResultTuple();
		ClassDescriptor cd = null;
		
		if($ID != null){
			result = stepFactory.addVariableInObjectMoveStep(location, null, null, temp, $var.type, $ID.text, null);
		}else{
			result = stepFactory.addVariableMoveStep(location, null, null, temp, $var.type);
		}

		temp++;
		$eval = result.getValue();
		$step = result.getStep();
		$eval.getResult().getType().setExpressionLevel(expressionDepth);


	}
	|	^(QUALIFIED_SOLO_PARENT_EXPRESSON var=qualified_name COLON PARENT COLON par=qualified_name COLON ID)
	{
		LineInformation location = new LineInformation (
			$var.type.getLineBegin(),
			$var.type.getLineEnd(),
			$var.type.getColumnBegin(),
			$var.type.getColumnEnd()
		);
		location.setFile(fileName);
                location.setClassName(symbol.getCurrentClass().getStaticKey());
                MethodDescriptor curMethod = symbol.getCurrentMethod();
                if(curMethod != null){
                	location.setMethodName(curMethod.getStaticKey());
                }

		ResultTuple result = new ResultTuple();
		ClassDescriptor cd = null;
		
		if($par.type != null){
			cd = symbol.findClassDescriptorFromCurrentClass($par.type.getStaticKey());
			
			if(cd == null){
				CompilerError error = new CompilerError($PARENT.getLine(), "The class "+ symbol.getCurrentClass().getStaticKey() +" does not have access to " + $par.type.getStaticKey(), ErrorType.MISSING_PARENT);
				vm.getCompilerErrors().addError(error);
			}
		}
		
		if($ID != null){
			result = stepFactory.addVariableInObjectMoveStep(location, null, null, temp, $var.type, $ID.text, cd);
		}else{
			result = stepFactory.addVariableMoveStep(location, null, null, temp, $var.type);
		}

		temp++;
		$eval = result.getValue();
		$step = result.getStep();
		$eval.getResult().getType().setExpressionLevel(expressionDepth);
	}
	|	^(QUALIFIED_SOLO_EXPRESSION_SELECTOR selector COLON qualified_name)
	{
		LineInformation location = new LineInformation (
			$qualified_name.type.getLineBegin(),
			$qualified_name.type.getLineEnd(),
			$qualified_name.type.getColumnBegin(),
			$qualified_name.type.getColumnEnd()
		);
		location.setFile(fileName);
                location.setClassName(symbol.getCurrentClass().getStaticKey());
                MethodDescriptor curMethod = symbol.getCurrentMethod();
                if(curMethod != null){
                	location.setMethodName(curMethod.getStaticKey());
                }
		
		ResultTuple result = new ResultTuple();
		ScopeSelector scope = $selector.scopeSel;
		
		if(!scope.isParent()){
			result = stepFactory.addMeVariableMoveStep(location, null, null, temp, $qualified_name.type);
		} else{
			result = stepFactory.addParentVariableMoveStep(location, null, null, temp, $qualified_name.type, scope.getCurrentClass());
		}
		temp++;
		$eval = result.getValue();
		$step = result.getStep();
		$eval.getResult().getType().setExpressionLevel(expressionDepth);
		
	}
	// s k |	^(QUALIFIED_SOLO_PARENT_EXPRESSON qn1=qualified_name COLON PARENT COLON qn2=qualified_name)
	|	^(FUNCTION_CALL_PARENT PARENT COLON qn1=qualified_name COLON ID LEFT_PAREN 
	{
		boolean unsetFlag = false;
		boolean nested = inCallStep;
		if (!inCallStep) {
			// We are now inside a call step--set the flag appropriately.
			inCallStep = true;
			unsetFlag = true;
		}
		expressionDepth++;
	}
	fel = function_expression_list RIGHT_PAREN)
	{
		LineInformation location = new LineInformation();
                location.setEndColumn($qn1.type.getColumnEnd());
                location.setEndLine($qn1.type.getLineEnd());
                location.setStartColumn($qn1.type.getColumnBegin());
                location.setStartLine($qn1.type.getLineBegin());
                location.setFile(fileName);
                location.setClassName(symbol.getCurrentClass().getStaticKey());
                MethodDescriptor curMethod = symbol.getCurrentMethod();
                if(curMethod != null){
                	location.setMethodName(curMethod.getStaticKey());
                }

		Vector<ExecutionStep> steps = new Vector<ExecutionStep>();
		Vector<ExpressionValue> values = new Vector<ExpressionValue>();
		Vector<Integer> registers = new Vector<Integer>();

		Vector<String> types = new Vector<String>();
		Vector<TypeDescriptor> argumentTypes = new Vector<TypeDescriptor>();
		
		int parameterPosition = -1;
		if(fel != null) {
			parameterPosition = $fel.firstParam;
			for(Object o : $fel.list) {
				expression_return ex = (expression_return)o;
                		types.add(ex.eval.getType().getName());
                		argumentTypes.add(ex.eval.getType());
                		steps.add(ex.step);
                		values.add(ex.eval);
                		registers.add(ex.eval.getRegister());
			
			}
		}

		String key = MethodDescriptor.generateKey($ID.text, types);
		String myMethodName = $ID.text;
		
		CallInfo info = new CallInfo();
		info.register = temp;
		info.location = location;
		info.locatedIn = $qn1.type.getStaticKey();
		info.argumentRegisters = registers;
		info.argumentSteps = steps;
		info.argumentTypes = argumentTypes;
		info.methodName = myMethodName;
		info.isObjectCall = false;
		info.isNested = nested;
		
		if(fel!=null && !$fel.list.isEmpty()){
			builder.addCallLabel(parameterPosition);
			//j builder.addStepLabel(OpcodeType.METHOD_CALL);
		}
		
		ResultTuple result =  stepFactory.addParentCallStep(info);
		
		temp = result.getNextRegister();
		expressionDepth--;
		$eval = result.getValue();
		$step = result.getStep();
		
		$eval.getResult().getType().setExpressionLevel(expressionDepth);
		if(fel!=null){
			builder.addStepLabel(OpcodeType.METHOD_CALL, result.getStepCount());
		}
		
		if (unsetFlag)
			inCallStep = false;
		
		
	}
	|	^(FUNCTION_CALL_THIS ME COLON qualified_name (COLON ID)? LEFT_PAREN 
	{
		boolean unsetFlag = false;
		boolean nested = inCallStep;
		if (!inCallStep) {
			// We are now inside a call step--set the flag appropriately.
			inCallStep = true;
			unsetFlag = true;
		}
		expressionDepth++;
	}
	fel = function_expression_list RIGHT_PAREN)
	{
		LineInformation location = new LineInformation();
                location.setEndColumn($qualified_name.type.getColumnEnd());
                location.setEndLine($qualified_name.type.getLineEnd());
                location.setStartColumn($qualified_name.type.getColumnBegin());
                location.setStartLine($qualified_name.type.getLineBegin());
                location.setFile(fileName);
                location.setClassName(symbol.getCurrentClass().getStaticKey());
                MethodDescriptor curMethod = symbol.getCurrentMethod();
                if(curMethod != null){
                	location.setMethodName(curMethod.getStaticKey());
                }

		Vector<ExecutionStep> steps = new Vector<ExecutionStep>();
		Vector<ExpressionValue> values = new Vector<ExpressionValue>();
		Vector<Integer> registers = new Vector<Integer>();

		Vector<String> types = new Vector<String>();
		Vector<TypeDescriptor> argumentTypes = new Vector<TypeDescriptor>();
		
		int parameterPosition = -1;
		if(fel != null) {
			parameterPosition = $fel.firstParam;
			for(Object o : $fel.list) {
				expression_return ex = (expression_return)o;
                		types.add(ex.eval.getType().getName());
                		argumentTypes.add(ex.eval.getType());
                		steps.add(ex.step);
                		values.add(ex.eval);
                		registers.add(ex.eval.getRegister());
			
			}
		}

		String key = "";
                String myMethodName = "";
                if($ID == null) {
                	key = MethodDescriptor.generateKey($qualified_name.type.getStaticKey(), types);
                	myMethodName = $qualified_name.type.getStaticKey();
		}
		else {
			key = MethodDescriptor.generateKey($ID.text, types);
			myMethodName = $ID.text;
		}

		CallInfo info = new CallInfo();
		info.register = temp;
		info.location = location;
		info.argumentRegisters = registers;
		info.argumentSteps = steps;
		info.variable = $qualified_name.type;
		info.argumentTypes = argumentTypes;
		info.methodName = myMethodName;
		info.isObjectCall = ($ID!=null);
		info.isThisCall = true;
		info.isNested = nested;
		
		if(fel!=null && !$fel.list.isEmpty()){
			builder.addCallLabel(parameterPosition);
		}
		
		ResultTuple result =  stepFactory.addCallStep(info);
		
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
		
		expressionDepth--;
		$eval.getResult().getType().setExpressionLevel(expressionDepth);
		if(fel!=null){
			builder.addStepLabel(OpcodeType.METHOD_CALL, result.getStepCount());
		}
		
		if (unsetFlag)
			inCallStep = false;
	}
	|	BOOLEAN
	{
		LineInformation location = new LineInformation (
			$BOOLEAN.getLine(),
			$BOOLEAN.getLine(),
			$BOOLEAN.getCharPositionInLine(),
			$BOOLEAN.getCharPositionInLine() + $BOOLEAN.text.length()
		);
		location.setFile(fileName);
		
		ResultTuple result = stepFactory.addMoveStep(temp, location, Boolean.parseBoolean($BOOLEAN.text));
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
		$eval.getResult().getType().setExpressionLevel(expressionDepth);
	}
	|	(MINUS)? DECIMAL
	{
		LineInformation location = new LineInformation (
			$DECIMAL.getLine(),
			$DECIMAL.getLine(),
			$DECIMAL.getCharPositionInLine(),
			$DECIMAL.getCharPositionInLine() + $DECIMAL.text.length()
		);
		location.setFile(fileName);
		
		double val = -1;
		try{
			val = Double.parseDouble($DECIMAL.text);
		}catch(NumberFormatException e){
			CompilerError error = new CompilerError();
			error.setLineNumber($DECIMAL.getLine());
			error.setError($DECIMAL.text + " is an invalid number.");
			error.setErrorType(ErrorType.INCOMPATIBLE_TYPES);
			error.setColumn($DECIMAL.getCharPositionInLine());
			error.setFile(getGrammarFileNameNoExtension());
			vm.getCompilerErrors().addError(error);
		}
		
		if($MINUS != null) {
			val *= -1;
		}
		ResultTuple result = stepFactory.addMoveStep(temp, location, val);
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
		$eval.getResult().getType().setExpressionLevel(expressionDepth);
	}
	|	(MINUS)? INT
	{
		LineInformation location = new LineInformation (
			$INT.getLine(),
			$INT.getLine(),
			$INT.getCharPositionInLine(),
			$INT.getCharPositionInLine() + $INT.text.length()
		);
		location.setFile(fileName);
		int val = -1;
		try{
			val = Integer.parseInt($INT.text);
		}catch(NumberFormatException e){
			CompilerError error = new CompilerError();
			error.setLineNumber($INT.getLine());
			error.setError("The integer " + $INT.text + " is not valid. All integers must be between the range of -2,147,483,648 and 2,147,483,647");
			error.setErrorType(ErrorType.INCOMPATIBLE_TYPES);
			error.setColumn($INT.getCharPositionInLine());
			error.setFile(getGrammarFileNameNoExtension());
			vm.getCompilerErrors().addError(error);
		}
		if($MINUS != null) {
			val *= -1;
		}
		ResultTuple result = stepFactory.addMoveStep(temp, location, val);
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
		$eval.getResult().getType().setExpressionLevel(expressionDepth);
	}
	|	STRING
	{
		LineInformation location = new LineInformation (
			$STRING.getLine(),
			$STRING.getLine(),
			$STRING.getCharPositionInLine(),
			$STRING.getCharPositionInLine() + $STRING.text.length()
		);
		location.setFile(fileName);
		ResultTuple result = stepFactory.addMoveStep(temp, location, $STRING.text);
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
		$eval.getResult().getType().setExpressionLevel(expressionDepth);
	}
	|	QUOTE
	{
		LineInformation location = new LineInformation (
			$QUOTE.getLine(),
			$QUOTE.getLine(),
			$QUOTE.getCharPositionInLine(),
			$QUOTE.getCharPositionInLine() + $QUOTE.text.length()
		);
		location.setFile(fileName);
		//we must pass three double quotes because normal strings strip out the beginning and the end.
		ResultTuple result = stepFactory.addMoveStep(temp, location, "\"\"\"");
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
		$eval.getResult().getType().setExpressionLevel(expressionDepth);
	}
	|	NULL
	{
		LineInformation location = new LineInformation (
			$NULL.getLine(),
			$NULL.getLine(),
			$NULL.getCharPositionInLine(),
			$NULL.getCharPositionInLine() + $NULL.text.length()
		);
		location.setFile(fileName);
		ResultTuple result = stepFactory.addMoveNullStep(temp, location);
		temp = result.getNextRegister();
		$eval = result.getValue();
		$step = result.getStep();
		$eval.getResult().getType().setExpressionLevel(expressionDepth);
	}
	|	ME
	{
		LineInformation location = new LineInformation (
			$ME.getLine(),
			$ME.getLine(),
			$ME.getCharPositionInLine(),
			$ME.getCharPositionInLine() + $ME.text.length()
		);
		location.setFile(fileName);
		
		ResultTuple result = new ResultTuple();

		result = stepFactory.addMoveThisStep(location, temp);

		temp++;
		$eval = result.getValue();
		$step = result.getStep();
		$eval.getResult().getType().setExpressionLevel(expressionDepth);
	}
	|	INPUT{inCallStep = true; int parameterPosition = builder.addParameterLabel();} LEFT_PAREN {expressionDepth++;} input_expr=expression RIGHT_PAREN
	{
		LineInformation location = new LineInformation (
			$LEFT_PAREN.getLine(),
			$RIGHT_PAREN.getLine(),
			$LEFT_PAREN.getCharPositionInLine(),
			$RIGHT_PAREN.getCharPositionInLine()
		);
		location.setFile(fileName);
		
				ExecutionStep step = $input_expr.step;
		ExpressionValue value = $input_expr.eval;

		builder.addCallLabel(parameterPosition);
		ResultTuple result = stepFactory.addInputStep(location, value, step, temp);
		
		temp = result.getNextRegister();
		expressionDepth--;
		$eval = result.getValue();
		$step = result.getStep();
		inCallStep = false;
		$eval.getResult().getType().setExpressionLevel(expressionDepth);
	}
	|	CAST LEFT_PAREN castqn=assignment_declaration COMMA {expressionDepth++;}cast_expr=expression castrpn=RIGHT_PAREN
	{
		LineInformation location = new LineInformation (
			$CAST.getLine(),
			$castrpn.getLine(),
			$CAST.getCharPositionInLine(),
			$castrpn.getCharPositionInLine()
		);
		location.setFile(fileName);
		TypeDescriptor type = $castqn.myType;
		ExecutionStep step = $cast_expr.step;
		ExpressionValue value = $cast_expr.eval;
		
		ResultTuple result = stepFactory.addCastStep(location, type, value, step, temp);
		
		temp = result.getNextRegister();
		expressionDepth--;
		$eval = result.getValue();
		$step = result.getStep();
		$eval.getResult().getType().setExpressionLevel(expressionDepth);
	}
	;

function_expression_list returns [List list, int firstParam]
@init{$list = new ArrayList(); $firstParam = -1;int startLocation = 0;}
	:
	^(FUNCTION_EXPRESSION_LIST (
	{
		if($list.size() >= 1){
			inCallStep = false;
		}
		if($list.size() == 0){
			$firstParam = builder.addParameterLabel();
		}
		if(builder.getCurrentMethod() != null){startLocation = builder.getCurrentMethod().getSteps().size();}
	}
	e = expression 
	{
		if(builder.getCurrentMethod() != null && builder.getCurrentMethod().getSteps().size() != 0 && startLocation != builder.getCurrentMethod().getSteps().size() -1){
				builder.getCurrentMethod().getSteps().get(startLocation).setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
				$e.step.setExpressionEndPosition(builder.getCurrentMethod().getSteps().size() - 1);
		}
		$list.add(e);
	} )*)
	;
	
	
formal_parameter	returns [TypeDescriptor type, String name]
	:	  ^(FPARAM ad=assignment_declaration ID)
	{	
		$type = $assignment_declaration.myType;
		$name = $ID.text;
	}
	;

