package org.eleusoft.jaxpxpathtests.tests.suite;


import junit.framework.Test;
import junit.framework.TestSuite;

import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimpleCompiled_Default_Saxon;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimpleCompiled_JXPath_JXPath;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimpleCompiled_JXPath_Jaxen;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimpleCompiled_JXPath_Saxon;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimpleCompiled_JXPath_Xalan;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimpleCompiled_Jaxen_Jaxen;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimpleCompiled_Jaxen_Saxon;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimpleCompiled_Saxon_Saxon;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimpleCompiled_Xalan_Jaxen;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimpleCompiled_Xalan_Saxon;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimpleCompiled_Xalan_Xalan;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimple_Default_Saxon;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimple_JXPath_JXPath;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimple_JXPath_Jaxen;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimple_JXPath_Saxon;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimple_JXPath_Xalan;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimple_Jaxen_Jaxen;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimple_Jaxen_Saxon;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimple_Saxon_Saxon;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimple_Xalan_Jaxen;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimple_Xalan_Saxon;
import org.eleusoft.jaxpxpathtests.tests.providers.JAXPTestSimple_Xalan_Xalan;

/**
 * Test suite for JAXP integration of XPath.
 * 
 * <p>Cross tests different implementations checking also for a common expected result.
 * 
 * <p>201401: A single cross check test is now made of 256 single test methods, 
 * there are 22 = 11 x 2 (std,compiled) tests = 5632 total tests.
 * 
 * <p><B>Summary</B>: 
 * <ul>
 * <li>Saxon looks best in XPath but passes custom objects to function implementations,
 * is the only one that fixes precision of double when converted to string, 
 * eg: <code>1.1 * 1.1 = 1.21000000001 = "1.21"</code> while all the other
 * print "1.21000000001".
 * <li>Jaxen seems also quite correct but seems to not support functions with different
 * number of arguments or at least does not expose it in FunctionContext.
 * <li>Xalan has some XPath bugs others do not have and since 2.7.1
 * has not improved as per trunk of 20140130.
 * <li>JXPath not modified is not comparable to others for too many errors, 
 * Eleusoft-modified version is instead very similar to others,
 * in current trunk a fixed bug 
 * (<a href='https://issues.apache.org/jira/browse/JXPATH-113'>113</a>) 
 * reappeared so is ~same (2F+!).
 * </ul>
 * 
 * <p><b>Details</b>
 * <p>With standard JXPath 1.3:
 * <p>5632 tests, 50E 224F
 * <p>With JXPath 1.4-SNAPSHOT:
 * <p>5632 tests, 52E 224F !2E+!
 * <p>But then fixing issue <a href='https://issues.apache.org/jira/browse/JXPATH-113'>113</a> 
 * that I reopened goes to 20E 216F.
 * 
 * <p>With modified Eleusoft-JXPath (not published yet):
 * <p>5280 tests, 20E 120F - Errors (not failures) come from Xalan that does not parse "3.1-3.1=0"
 * <p>5324 tests, 20E 120F - Adding 2 tests for variables (were 14f for variable being Integer instead than Double, Xalan and Saxon fix it, Jaxen and JXPath no)
 * <p>5632 tests, 20E 120F - Other tests for variables as index, to check https://issues.apache.org/jira/browse/JXPATH-163 
 * <p>Minimum errors from test-yourself tests:
 * <ul>
 * <li>JXPath-JXPath 1 (double precision to string)
 * <li>Jaxen-Jaxen 2 (double precision to string and <code>count(//*[local-name()='a' and namespace-uri()=''])</code>)
 * <li>Saxon-Saxon 4 ('+' accepted and functions problem)
 * <li>Xalan-Xalan 6 (some suspect bugs, precision to string, parse number)
 * </ul>
 * <p>Cross check:
 * <ul>
 * <li>JXPath-Jaxen 3 problems: 
 * <li>JXPath-Saxon 7 problems: 
 * <li>Jaxen-Saxon 7 problems: 
 * <li>Xalan-JXPath 8 problems: 
 * <li>Xalan-Jaxen 8 problems: 
 * <li>Xalan-Saxon 12 problems
 * <li>Default(Xalan)-Saxon 12 problems
 * </ul>
 * 
 * <p> <b>OLD</b>
 * <p>20091210 - 464 tests, 18 failures.
 * <li>One failure for <code>+4</code> not valid.
 * <li>4 failures for number precision
 * <li>3 failures for invert-text function
 * <li><b>TODO:</b> One failure for  testSimpleTextRecursiveWithAncestorOrSelf_TODO_WRONGORDER.
 * <p>20130819 470T 18f
 * <p>Added code for patch 383726, to return null for negative indexes..
 * <p>20140128 added test for xalan problem (4-4) now Xalan 4e 16f - Saxon 18F 0E
 *  Saxon passes custom objects to functions so invert is not working
 *  Xalan has some bugs marked with XALANBUG 
 * @author Michele Vivoda
 */
public class TestSuiteJAXPXPath extends TestSuite
{

    public static TestSuite suite()
    {
        TestSuiteJAXPXPath suite = new TestSuiteJAXPXPath();
        suite.addTestSuite(JAXPTestSimple_Default_Saxon.class);
        suite.addTestSuite(JAXPTestSimple_Jaxen_Jaxen.class);
        suite.addTestSuite(JAXPTestSimple_Jaxen_Saxon.class);
        suite.addTestSuite(JAXPTestSimple_JXPath_Jaxen.class);
        suite.addTestSuite(JAXPTestSimple_JXPath_JXPath.class);
        suite.addTestSuite(JAXPTestSimple_JXPath_Saxon.class);
        suite.addTestSuite(JAXPTestSimple_JXPath_Xalan.class);
        suite.addTestSuite(JAXPTestSimple_Saxon_Saxon.class);
        suite.addTestSuite(JAXPTestSimple_Xalan_Jaxen.class);
        suite.addTestSuite(JAXPTestSimple_Xalan_Saxon.class);
        suite.addTestSuite(JAXPTestSimple_Xalan_Xalan.class);
        
        suite.addTestSuite(JAXPTestSimpleCompiled_Default_Saxon.class);
        suite.addTestSuite(JAXPTestSimpleCompiled_Jaxen_Jaxen.class);
        suite.addTestSuite(JAXPTestSimpleCompiled_Jaxen_Saxon.class);
        suite.addTestSuite(JAXPTestSimpleCompiled_JXPath_Jaxen.class);
        suite.addTestSuite(JAXPTestSimpleCompiled_JXPath_JXPath.class);
        suite.addTestSuite(JAXPTestSimpleCompiled_JXPath_Saxon.class);
        suite.addTestSuite(JAXPTestSimpleCompiled_JXPath_Xalan.class);
        suite.addTestSuite(JAXPTestSimpleCompiled_Saxon_Saxon.class);
        suite.addTestSuite(JAXPTestSimpleCompiled_Xalan_Jaxen.class);
        suite.addTestSuite(JAXPTestSimpleCompiled_Xalan_Saxon.class);
        suite.addTestSuite(JAXPTestSimpleCompiled_Xalan_Xalan.class);
        return suite;

    }
}
