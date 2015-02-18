import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.StringTokenizer;

public class ChainingTests {
    static String path = "C:/Repositories/quorum-language/Quorum3/";
    static String testsPath = path + "Library/Tests/Chaining/";
    static String testerPath = path + "SourceCode/";
    String passTestStr = new String();
    String failTestStr = new String();
    String testActionsStr = new String();

    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("ChainingTests.txt");
        BufferedReader testsFile = new BufferedReader(file);
        ChainingTests t = new ChainingTests();
        String nextLine = new String();
        nextLine = testsFile.readLine();
        while (nextLine != null) {
            t.generateTests(nextLine);
            nextLine = testsFile.readLine();
        }
        testsFile.close();
        file.close();
        t.writeTesterClass();
        t.writeYodaClass();
    }
    
    public void generateTests(String nextLine) throws IOException {
            Boolean isToObject = false;
            Boolean isFromObject = false;
            StringTokenizer strTok = new StringTokenizer(nextLine);
            String testName = strTok.nextToken(",");
            Boolean meTest = testName.contains("Me");
            String toType = new String();
            if (testName.contains("Var")) {
                isToObject = false;
                toType = "Var";
            } else if (testName.contains("ObjObj")) {
                isToObject = true;
                toType = "ObjObj";
            } else {
                isToObject = true;
                toType = "Obj";
            }
            String fromType = strTok.nextToken(",");
            String testType = strTok.nextToken();
            String filePath = (testsPath + testType + "/");
            Boolean soloTest = true;
            Boolean localVariableDeclaration = true;
            String testFileName = "Return";
            if (meTest) testFileName += "Me";
            testFileName += toType;
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
                    testValue = "66";
                }
                if (fromType.equals("NumObj")) {
                    fromType = "Number";
                    testValue = "900.0";
                }
                if (fromType.equals("TextObj")) {
                    fromType = "Text";
                    testValue = "\"green\"";
                }
            } else {
                if (fromType.equals("boolean")) {
                    testFileName += "Boolean";
                    testValue = "true";
                }
                if (fromType.equals("integer")) {
                    testFileName += "Integer";
                    testValue = "66";
                }
                if (fromType.equals("number")) {
                    testFileName += "Number";
                    testValue = "900.0";
                }
                if (fromType.equals("text")) {
                    testFileName += "Text";
                    testValue = "\"green\"";
                }
                if (fromType.equals("undefined")) {
                    testFileName += "Undefined";
                    testValue = "undefined";
                }
            }
            
            String chainString = new String();
            if (meTest) chainString = "me:";
            switch (toType) {
                case "Var":
                    chainString += "m:Var()";
                    break;
                case "Obj":
                    chainString += "m:Obj():Get" + fromType + "()";
                    break;
                case "ObjObj":
                    chainString += "m:Obj():GetYoda():Get" + fromType + "()";
                    break;
            }
            
            for (int i = 0; i < 3; i++) {
                if (soloTest) {
                    testFileName += "Solo";
                    filePath += testFileName + ".quorum";
                } else {
                    if (localVariableDeclaration) {
                        testFileName = testFileName.replace("Solo", "Assign");
                        filePath = filePath.replace("Solo", "Assign");
                    } else {
                        testFileName += "Field";
                        filePath = filePath.replace(".", "Field.");
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
                testWriter.printf("%s%n", "    Main m = undefined");
                if (!localVariableDeclaration && !soloTest) {
                    if (isFromObject) {
                        testWriter.printf("%s%n", "    " + fromType + " v ");
                    } else {
                        if (fromType.equals("undefined")) {
                            testWriter.printf("%s%n", "    Object v");
                        } else {
                            testWriter.printf("%s%n", "    " + fromType + " v = " + testValue);
                        }
                    }
                }
                testWriter.printf("%s%n", "    action Main");
                testWriter.printf("%s%n", "        Main main");
                testWriter.printf("%s%n", "        m = main");
                if (localVariableDeclaration && !soloTest) {
                    if (isFromObject) {
                        testWriter.printf("%s%n", "        " + fromType + " v = " + chainString);
                    } else {
                        if (fromType.equals("undefined")) {
                            testWriter.printf("%s%n", "        Object v = " + chainString);
                        } else {
                            testWriter.printf("%s%n", "        " + fromType + " v = " + chainString);
                        }
                    }
                } else {
                    if (!soloTest && !fromType.equals("Object")) {
                        testWriter.printf("%s%n", "        v = " + chainString);
                    }
                }
                if (testType.contains("Pass")) {
                    if (fromType.equals("Object")) {
                        if (soloTest) {
                            testWriter.printf("%s%n", "        " + chainString);
                        } else {
                            testWriter.printf("%s%n", "        v = " + chainString);
                        }
                        testWriter.printf("%s%n", "        output \"passed\"");    
                    } else {
                        if (soloTest) {
                            if (isFromObject) {
                                if (fromType.equals("undefined")) {
                                    testWriter.printf("%s%n", "        if " + chainString + " = undefined");
                                } else if (toType.equals("Object") && isFromObject) {
                                    testWriter.printf("%s%n", "        cast(" + fromType + ", var) = " + testValue);
                                } else {
                                    testWriter.printf("%s%n", "        if " + chainString + ":GetValue() = " + testValue);
                                }
                            } else {
                                    testWriter.printf("%s%n", "        if " + chainString + " = " + testValue);
                            }
                        } else {
                            if (isFromObject) {
                                if (fromType.equals("undefined")) {
                                    testWriter.printf("%s%n", "        if v = " + testValue);
                                } else if (toType.equals("Object") && isFromObject) {
                                    testWriter.printf("%s%n", "        " + fromType + " v = cast(" + fromType + ", var)");
                                    testWriter.printf("%s%n", "        if v:GetValue() = " + testValue);
                                } else {
                                    testWriter.printf("%s%n", "        if v:GetValue() = " + testValue);
                                }
                            } else {
                                    testWriter.printf("%s%n", "        if v = " + testValue);
                            }
                        }
                        testWriter.printf("%s%n", "            output \"passed\"");
                        testWriter.printf("%s%n", "        end");
                    }
                }
                testWriter.printf("%s%n%n", "    end");
                if (toType.contains("Var")) {
                    if (fromType.contains("undefined")) {
                        testWriter.printf("%s%n", "    action Var() returns Object");
                        testWriter.printf("%s%n", "        Object val = undefined");
                    } else {
                        testWriter.printf("%s%n", "    action Var() returns " + fromType);
                        if (isFromObject) {
                            testWriter.printf("%s%n", "        " + fromType + " val");
                            if (!fromType.contains("Object")) {
                                testWriter.printf("%s%n", "        val:SetValue(" + testValue + ")");
                            }
                        } else {
                            testWriter.printf("%s%n", "        " + fromType + " val =" + testValue);
                        }
                    }
                    testWriter.printf("%s%n", "        return val");
                    testWriter.printf("%s%n", "    end");
                } else {
                    testWriter.printf("%s%n", "    action Obj() returns Yoda");
                    testWriter.printf("%s%n", "        Yoda yoda");
                    testWriter.printf("%s%n", "        return yoda");
                    testWriter.printf("%s%n", "    end");
                }
                testWriter.printf("%s%n", "end");
                testWriter.close();

                testActionsStr += ("\r\n");
                testActionsStr += ("    action " + testFileName + "\r\n");
                testActionsStr += ("        Array<File> files\r\n");
                if (testType.equals("Pass")) {
                    testActionsStr += ("        Array<text> results\r\n");
                }
                testActionsStr += ("        File main\r\n");
                testActionsStr += ("        File yoda\r\n");
                testActionsStr += ("        test = path + \"" + testType + "/" + testFileName + ".quorum\"\r\n");
                testActionsStr += ("        yodaPath = path + \"Yoda.quorum\"\r\n");
                testActionsStr += ("        main:SetPath(test)\r\n");
                testActionsStr += ("        yoda:SetPath(yodaPath)\r\n");
                testActionsStr += ("        files:Add(main)\r\n");
                testActionsStr += ("        files:Add(yoda)\r\n");
                if (testType.equals("Pass")) {
                    testActionsStr += ("        results:Add(0, \"passed\")\r\n");
                    testActionsStr += ("        suite:Test(files, results)\r\n");
                    testActionsStr += ("        suite:TestJavaScript(files, results)\r\n");
                } else {
                    testActionsStr += ("        suite:TestFail(files)\r\n");
                    testActionsStr += ("        suite:TestJavaScriptFail(files)\r\n");
                }
                testActionsStr += ("    end\r\n");
                if (soloTest) {
                    localVariableDeclaration = true;
                    soloTest = false;
                } else {
                    localVariableDeclaration = false;
                }
            }
    }
    
    public void writeTesterClass() throws IOException {
        FileWriter finalTestFW = new FileWriter(testerPath + "ChainingTester.quorum", false);
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
        finalWriter.println("class ChainingTester");
        finalWriter.println("    CompilerTestSuite suite = undefined");
        finalWriter.println("    text path = \"Library/Tests/Chaining/\"");
        finalWriter.println("");
        finalWriter.println("    action Test");
        finalWriter.println("        //PASS TESTS");
        finalWriter.println(passTestStr);
        finalWriter.println("        //FAIL TESTS");
        finalWriter.println(failTestStr);
        finalWriter.println("    end");
        finalWriter.println("");
        finalWriter.println("    action GetName returns text");
        finalWriter.println("        return \"Chaining Tests\"");
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
    public void writeYodaClass() throws IOException {
        FileWriter yodaFW = new FileWriter(testsPath + "Yoda.quorum", false);
        PrintWriter yodaWriter = new PrintWriter(yodaFW);
        yodaWriter.println("class Yoda");
        yodaWriter.println("    boolean isGreen = true");
        yodaWriter.println("    integer height = 66");
        yodaWriter.println("    number age = 900.0");
        yodaWriter.println("    text color = \"green\"");
        yodaWriter.println("");
        yodaWriter.println("    action Getboolean returns boolean");
        yodaWriter.println("        return isGreen");
        yodaWriter.println("    end");
        yodaWriter.println("");
        yodaWriter.println("    action GetBoolean returns Boolean");
        yodaWriter.println("        Boolean obj");
        yodaWriter.println("        obj:SetValue(isGreen)");
        yodaWriter.println("        return obj");
        yodaWriter.println("    end");
        yodaWriter.println("");
        yodaWriter.println("    action Getinteger returns integer");
        yodaWriter.println("        return height");
        yodaWriter.println("    end");
        yodaWriter.println("");
        yodaWriter.println("    action GetInteger returns Integer");
        yodaWriter.println("        Integer obj");
        yodaWriter.println("        obj:SetValue(height)");
        yodaWriter.println("        return obj");
        yodaWriter.println("    end");
        yodaWriter.println("");
        yodaWriter.println("    action Getnumber returns number");
        yodaWriter.println("        return age");
        yodaWriter.println("    end");
        yodaWriter.println("");
        yodaWriter.println("    action GetNumber returns Number");
        yodaWriter.println("        Number obj");
        yodaWriter.println("        obj:SetValue(age)");
        yodaWriter.println("        return obj");
        yodaWriter.println("    end");
        yodaWriter.println("");
        yodaWriter.println("    action Gettext returns text");
        yodaWriter.println("        return color");
        yodaWriter.println("    end");
        yodaWriter.println("");
        yodaWriter.println("    action GetText returns Text");
        yodaWriter.println("        Text obj");
        yodaWriter.println("        obj:SetValue(color)");
        yodaWriter.println("        return obj");
        yodaWriter.println("    end");
        yodaWriter.println("");
        yodaWriter.println("    action GetObject returns Object");
        yodaWriter.println("        return cast(Object, me)");
        yodaWriter.println("    end");
        yodaWriter.println("");
        yodaWriter.println("    action Getundefined returns Yoda");
        yodaWriter.println("        return undefined");
        yodaWriter.println("    end");
        yodaWriter.println("");
        yodaWriter.println("    action GetYoda returns Yoda");
        yodaWriter.println("        return me");
        yodaWriter.println("    end");
        yodaWriter.println("end");
        yodaFW.close();        
    }
}
