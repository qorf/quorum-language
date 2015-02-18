import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.StringTokenizer;

public class TypeCheckTests {
    static String path = "C:/Repositories/quorum-language/Quorum3/";
    static String testsPath = path + "Library/Tests/TypeCheckerGenerated/";
    static String testerPath = path + "SourceCode/";
    String passTestStr = new String();
    String failTestStr = new String();
    String testActionsStr = new String();

    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("ImpTests.txt");
        BufferedReader testsFile = new BufferedReader(file);
        TypeCheckTests t = new TypeCheckTests();
        String nextLine = new String();
        nextLine = testsFile.readLine();
        while (nextLine != null) {
            t.generateImpTest(nextLine);
            nextLine = testsFile.readLine();
        }
        testsFile.close();
        file.close();
        file = new FileReader("ReturnTests.txt");
        testsFile = new BufferedReader(file);
        nextLine = testsFile.readLine();
        while (nextLine != null) {
            t.generateRetTest(nextLine);
            nextLine = testsFile.readLine();
        }
        
        t.writeTesterClass();
    }
    
    public void generateImpTest(String nextLine) throws IOException {
            Boolean isToObject = false;
            Boolean isFromObject = false;
            StringTokenizer strTok = new StringTokenizer(nextLine);
            String toType = strTok.nextToken(",");
            String fromType = strTok.nextToken(",");
            String testType = strTok.nextToken();
            String filePath = (testsPath + testType + "/");
            Boolean localVariableDeclaration = true;
            String testFileName = "ImpAssign";
            if (toType.contains("Obj")) {
                isToObject = true;
                testFileName += toType;
                if (toType.equals("BoolObj")) toType = "Boolean";
                if (toType.equals("IntObj")) toType = "Integer";
                if (toType.equals("NumObj")) toType = "Number";
                if (toType.equals("TextObj")) toType = "Text";
            } else {
                if (toType.equals("boolean")) testFileName += "Boolean";
                if (toType.equals("integer")) testFileName += "Integer";
                if (toType.equals("number")) testFileName += "Number";
                if (toType.equals("text")) testFileName += "Text";
            }
            String testValue = new String();
            if (fromType.contains("Obj")) {
                isFromObject = true;
                testFileName += fromType;
                if (fromType.equals("BoolObj")) {
                    fromType = "Boolean";
                    testValue = "true";
                }
                if (fromType.equals("IntObj")) {
                    fromType = "Integer";
                    testValue = "5";
                }
                if (fromType.equals("NumObj")) {
                    fromType = "Number";
                    testValue = "5.0";
                }
                if (fromType.equals("TextObj")) {
                    fromType = "Text";
                    testValue = "\"text\"";
                }
                if (fromType.equals("Object")) {
                    if (toType.equals("boolean")) testValue = "true";
                    if (toType.equals("Boolean")) testValue = "true";
                    if (toType.equals("integer")) testValue = "5";
                    if (toType.equals("Integer")) testValue = "5";
                    if (toType.equals("number")) testValue = "5.0";
                    if (toType.equals("Number")) testValue = "5.0";
                    if (toType.equals("text")) testValue = "\"text\"";
                    if (toType.equals("Text")) testValue = "\"text\"";
                }
            } else {
                if (fromType.equals("boolean")) {
                    testFileName += "Boolean";
                    testValue = "true";
                }
                if (fromType.equals("integer")) {
                    testFileName += "Integer";
                    testValue = "5";
                }
                if (fromType.equals("number")) {
                    testFileName += "Number";
                    testValue = "5.0";
                }
                if (fromType.equals("text")) {
                    testFileName += "Text";
                    testValue = "\"text\"";
                }
                if (fromType.equals("undefined")) {
                    testFileName += "Undefined";
                    testValue = "undefined";
                }
            }
            for (int i = 0; i < 2; i++) {
                if (localVariableDeclaration) {
                    filePath += testFileName + ".quorum";
                } else {
                    filePath = filePath.replace(".quorum", "Field.quorum");
                    if (fromType.equals("Integer") || fromType.equals("integer")) {
                        testValue = "5";
                    }
                }
                System.out.println(filePath);
                if (testType.equals("Pass")) {
                    passTestStr += ("        " + testFileName + "()\r\n");
                } else {
                    failTestStr += ("        " + testFileName + "()\r\n");
                }

                FileWriter testFile = new FileWriter(filePath, false);
                PrintWriter testWriter = new PrintWriter(testFile);

                testWriter.printf("%s%n", "class Main");
                if (!localVariableDeclaration ) {
                    if (isFromObject) {
                        testWriter.printf("%s%n", "    " + fromType + " v");
                        if (fromType.equals("Object")) {
                            if (!toType.equals("Object")) {
                                if (isToObject) {
                                    testWriter.printf("%s%n", "    " + toType + " t");
                                } else {
                                    testWriter.printf("%s%n", "    " + toType + " t = " + testValue);
                                }
                            }
                        }
                    } else {
                        if (fromType.equals("undefined")) {
                            if (isToObject) {
                                testWriter.printf("%s%n", "    " + toType + " v = " + testValue);
                            } else {
                                testWriter.printf("%s%n", "    Object v = " + testValue);
                            }
                        } else {
                            testWriter.printf("%s%n", "    " + fromType + " v = " + testValue);
                        }
                    }
                }
                testWriter.printf("%s%n", "    action Main");
                if (localVariableDeclaration) {
                    if (isFromObject) {
                        testWriter.printf("%s%n", "        " + fromType + " v");
//                        if (fromType.equals("Object") && testType.equals("Pass")) {
//                            if (isToObject) {
//                                testWriter.printf("%s%n", "        " + toType + " t");
//                            } else {
//                                testWriter.printf("%s%n", "        " + toType + " t = " + testValue);
//                            }
//                        }
                    } else {
                        if (fromType.equals("undefined")) {
                            if (isToObject) {
                                testWriter.printf("%s%n", "        " + toType + " v = " + testValue);
                            } else {
                                testWriter.printf("%s%n", "        Object v = " + testValue);
                            }
                        } else {
                            testWriter.printf("%s%n", "        " + fromType + " v = " + testValue);
                        }
                    }
                } 
                if (!(fromType.equals("Object") && toType.equals("Object"))) {
                    if (isFromObject) {
                        if (!(fromType.equals("Object"))) {
                            testWriter.printf("%s%n", "        v:SetValue(" + testValue + ")");
                        }
                    }
                } else {
//                        testWriter.printf("%s%n", "        t:SetValue(" + testValue + ")");
//                        testWriter.printf("%s%n", "        v = cast(Object, t)");
                }
                testWriter.printf("%s%n", "        Test(v)");
                testWriter.printf("%s%n", "    end");
                testWriter.printf("%s%n", "");
                testWriter.printf("%s%n", "    action Test(" + toType + " var)");
                if (testType.contains("Pass")) {
                    if (toType.equals("Number") || toType.equals("number")) {
                        if (testValue.equals("5")) {
                            testValue = "5.0";
                        }
                    }
                    if (toType.equals("Integer") || toType.equals("integer")) {
                        if (testValue.equals("5.0")) {
                            testValue = "5";
                        }
                    }
                    if (fromType.equals("Object") && toType.equals("Object")) {
                        testWriter.printf("%s%n", "        output \"passed\"");    
                    } else {
                        if (isToObject) {
                            if (fromType.equals("undefined")) {
                                testWriter.printf("%s%n", "        if var = " + testValue);
                            } else if (toType.equals("Object")) {
                                    testWriter.printf("%s%n", "        " + fromType + " v = cast(" + fromType + ", var)");
                                if (isFromObject) {
                                    testWriter.printf("%s%n", "        if v:GetValue() = " + testValue);
                                } else {
                                    testWriter.printf("%s%n", "        if v = " + testValue);
                                }
                            } else {
                                testWriter.printf("%s%n", "        if var:GetValue() = " + testValue);
                            }
                        } else {
                                testWriter.printf("%s%n", "        if var = " + testValue);
                        } 
                        testWriter.printf("%s%n", "            output \"passed\"");
                        testWriter.printf("%s%n", "        end");
                    }
                }
                testWriter.printf("%s%n", "    end");
                testWriter.printf("%s%n", "end");
                testWriter.close();

                testActionsStr += ("\r\n");
                testActionsStr += ("    action " + testFileName + "\r\n");
                testActionsStr += ("        Array<File> files\r\n");
                if (testType.equals("Pass")) {
                    testActionsStr += ("        Array<text> results\r\n");
                }
                testActionsStr += ("        File main\r\n");
                testActionsStr += ("        test = path + \"" + testType + "/" + testFileName + ".quorum\"\r\n");
                testActionsStr += ("        main:SetPath(test)\r\n");
                testActionsStr += ("        files:Add(main)\r\n");
                if (testType.equals("Pass")) {
                    testActionsStr += ("        results:Add(0, \"passed\")\r\n");
                    testActionsStr += ("        suite:Test(files, results)\r\n");
                    testActionsStr += ("        suite:TestJavaScript(files, results)\r\n");
                } else {
                    testActionsStr += ("        suite:TestFail(files)\r\n");
                    testActionsStr += ("        suite:TestJavaScriptFail(files)\r\n");
                }
                testActionsStr += ("    end\r\n");
                testFileName += "Field";
                localVariableDeclaration = false;
            }
    }
    
    public void generateRetTest(String nextLine) throws IOException {
            Boolean isToObject = false;
            Boolean isFromObject = false;
            StringTokenizer strTok = new StringTokenizer(nextLine);
            String toType = strTok.nextToken(",");
            String fromType = strTok.nextToken(",");
            String testType = strTok.nextToken();
            String filePath = (testsPath + testType + "/");
            Boolean localVariableDeclaration = true;
            String testFileName = "RetAssign";
            if (toType.contains("Obj")) {
                isToObject = true;
                testFileName += toType;
                if (toType.equals("BoolObj")) toType = "Boolean";
                if (toType.equals("IntObj")) toType = "Integer";
                if (toType.equals("NumObj")) toType = "Number";
                if (toType.equals("TextObj")) toType = "Text";
            } else {
                if (toType.equals("boolean")) testFileName += "Boolean";
                if (toType.equals("integer")) testFileName += "Integer";
                if (toType.equals("number")) testFileName += "Number";
                if (toType.equals("text")) testFileName += "Text";
            }
            String testValue = new String();
            if (fromType.contains("Obj")) {
                isFromObject = true;
                testFileName += fromType;
                if (fromType.equals("BoolObj")) {
                    fromType = "Boolean";
                    testValue = "true";
                }
                if (fromType.equals("IntObj")) {
                    fromType = "Integer";
                    testValue = "5";
                }
                if (fromType.equals("NumObj")) {
                    fromType = "Number";
                    testValue = "5.0";
                }
                if (fromType.equals("TextObj")) {
                    fromType = "Text";
                    testValue = "\"text\"";
                }
            } else {
                if (fromType.equals("boolean")) {
                    testFileName += "Boolean";
                    testValue = "true";
                }
                if (fromType.equals("integer")) {
                    testFileName += "Integer";
                    testValue = "5";
                }
                if (fromType.equals("number")) {
                    testFileName += "Number";
                    testValue = "5.0";
                }
                if (fromType.equals("text")) {
                    testFileName += "Text";
                    testValue = "\"text\"";
                }
                if (fromType.equals("undefined")) {
                    testFileName += "Undefined";
                    testValue = "undefined";
                }
            }
            for (int i = 0; i < 2; i++) {
                if (localVariableDeclaration) {
                    filePath += testFileName + ".quorum";
                } else {
                    filePath = filePath.replace(".quorum", "Field.quorum");
                    if (fromType.equals("Integer") || fromType.equals("integer")) {
                        testValue = "5";
                    }
                }
                System.out.println(filePath);
                if (testType.equals("Pass")) {
                    passTestStr += ("        " + testFileName + "()\r\n");
                } else {
                    failTestStr += ("        " + testFileName + "()\r\n");
                }

                FileWriter testFile = new FileWriter(filePath, false);
                PrintWriter testWriter = new PrintWriter(testFile);

                testWriter.printf("%s%n", "class Main");
                if (!localVariableDeclaration) {
                    if (isToObject) {
                        if (fromType.equals("undefined") || fromType.equals("Object") || toType.equals("Object")) { 
                            testWriter.printf("%s%n", "    " + toType + " v = Test()");
                        } else {
                            testWriter.printf("%s%n", "    " + toType + " v");
                        }
                    } else {
                        testWriter.printf("%s%n", "    " + toType + " v = Test()");        
                    }
                }
                testWriter.printf("%s%n", "    action Main");
                if (localVariableDeclaration) {
                    if (isToObject) {
                        if (fromType.equals("undefined") || fromType.equals("Object") || toType.equals("Object")) { 
                            testWriter.printf("%s%n", "        " + toType + " v = Test()");
                        } else {
                            testWriter.printf("%s%n", "        " + toType + " v");
                        }
                    } else {
                        testWriter.printf("%s%n", "        " + toType + " v = Test()");        
                    }
                } 
                if (isToObject && !fromType.equals("undefined") && !fromType.equals("Object") && !toType.equals("Object")) {
                    testWriter.printf("%s%n", "        v:SetValue(Test())");
                }
                if (testType.contains("Pass")) {
                    if (toType.equals("Number") || toType.equals("number")) {
                        if (testValue.equals("5")) {
                            testValue = "5.0";
                        }
                    }
                    if (fromType.equals("Object") && toType.equals("Object")) {
                        testWriter.printf("%s%n", "        output \"passed\"");    
                    } else {
                        if (isToObject) {
                            if (fromType.equals("undefined")) {
                                testWriter.printf("%s%n", "        if v = undefined");
                            } else if (toType.equals("Object") && isFromObject) {
                                testWriter.printf("%s%n", "        " + fromType + " var = cast(" + fromType + ", v)");
                                testWriter.printf("%s%n", "        if var:GetValue() = " + testValue);
                            } else {
                                testWriter.printf("%s%n", "        if v:GetValue() = " + testValue);
                            }
                        } else {
                                testWriter.printf("%s%n", "        if v = " + testValue);
                        } 
                        testWriter.printf("%s%n", "            output \"passed\"");
                        testWriter.printf("%s%n", "        end");
                    }
                }
                testWriter.printf("%s%n", "    end");
                testWriter.printf("%s%n", "");
                testWriter.printf("%s%n", "    action Test() returns " + toType);
                if (fromType.equals("undefined")) {
                    testWriter.printf("%s%n", "        return undefined");
                } else {
                    if (fromType.equals("Integer") || fromType.equals("integer")) {
                        if (testValue.equals("5.0")) {
                            testValue = "5";
                        }
                    }
                    if (isFromObject) {
                        testWriter.printf("%s%n", "        " + fromType + " var");
                        if (!fromType.equals("Object")) {
                            testWriter.printf("%s%n", "        var:SetValue(" + testValue + ")");
                        }
                    } else {
                        testWriter.printf("%s%n", "        " + fromType + " var = " + testValue);
                    }
                    testWriter.printf("%s%n", "        return var");
                }
                testWriter.printf("%s%n", "    end");
                testWriter.printf("%s", "end");
                testWriter.close();

                testActionsStr += ("\r\n");
                testActionsStr += ("    action " + testFileName + "\r\n");
                testActionsStr += ("        Array<File> files\r\n");
                if (testType.equals("Pass")) {
                    testActionsStr += ("        Array<text> results\r\n");
                }
                testActionsStr += ("        File main\r\n");
                testActionsStr += ("        test = path + \"" + testType + "/" + testFileName + ".quorum\"\r\n");
                testActionsStr += ("        main:SetPath(test)\r\n");
                testActionsStr += ("        files:Add(main)\r\n");
                if (testType.equals("Pass")) {
                    testActionsStr += ("        results:Add(0, \"passed\")\r\n");
                    testActionsStr += ("        suite:Test(files, results)\r\n");
                    testActionsStr += ("        suite:TestJavaScript(files, results)\r\n");
                } else {
                    testActionsStr += ("        suite:TestFail(files)\r\n");
                    testActionsStr += ("        suite:TestJavaScriptFail(files)\r\n");
                }
                testActionsStr += ("    end\r\n");
                testFileName += "Field";
                localVariableDeclaration = false;
            }
    }
    
    public void writeTesterClass() throws IOException {
        FileWriter finalTestFW = new FileWriter(testerPath + "TypeCheckTesterGenerated.quorum", false);
        PrintWriter finalWriter = new PrintWriter(finalTestFW);
        finalWriter.println("package Libraries.Language.Compile.Test");
        finalWriter.println("");
        finalWriter.println("use Libraries.Language.Compile.all");
        finalWriter.println("use Libraries.Containers.HashTable");
        finalWriter.println("use Libraries.Containers.Array");
        finalWriter.println("use Libraries.Language.Compile.Parser");
        finalWriter.println("use Libraries.System.File");
        finalWriter.println("use Libraries.Containers.Blueprints.Iterator");
        finalWriter.println("use Libraries.Language.Compile.Translate.JarGenerator");
        finalWriter.println("use Libraries.System.StackTraceItem");
        finalWriter.println("");
        finalWriter.println("class TypeCheckTesterGenerated");
        finalWriter.println("    CompilerTestSuite suite = undefined");
        finalWriter.println("    text path = \"Library/Tests/TypeCheckerGenerated/\"");
        finalWriter.println("");
        finalWriter.println("    action Test");
        finalWriter.println("        //PASS TESTS");
        finalWriter.println(passTestStr);
        finalWriter.println("        //FAIL TESTS");
        finalWriter.println(failTestStr);
        finalWriter.println("    end");
        finalWriter.println("");
        finalWriter.println("    action GetName returns text");
        finalWriter.println("        return \"Type Check Generated Tests\"");
        finalWriter.println("    end");
        finalWriter.println("    ");
        finalWriter.println("    action GetCompilerTestSuite returns CompilerTestSuite");
        finalWriter.println("        return suite");
        finalWriter.println("    end");
        finalWriter.println("    ");
        finalWriter.println("    action SetCompilerTestSuite(CompilerTestSuite suite)");
        finalWriter.println("        me:suite = suite");
        finalWriter.println("    end");
        finalWriter.println("");
        finalWriter.println(testActionsStr);
        finalWriter.println("end");
        finalTestFW.close();        
    }
}
