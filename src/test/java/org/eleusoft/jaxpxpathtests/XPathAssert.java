/**
 *
 */
package org.eleusoft.jaxpxpathtests;

import javax.xml.xpath.XPath;



/**
 * Abstraction of a tester of xpath results.
 * @author Michele Vivoda
 */
public interface XPathAssert
{
    /**
     * Compares the results of the passed expression on the passed
     * xml, using the two passed XPath implementations and
     * performing check for NODE and NODESET result types when <code class='code'>doNodes</code>
     * is <code class='code'>true</code>.
     * @param xpath
     * @param xml
     * @param xpath1
     * @param xpath2
     * @param doNodes
     * @throws Exception
     */
    void assertXPath(ExpectedResult result, String xpath, String xml, XPath xpath1, XPath xpath2, boolean doNodes) throws Exception;
    /**
     * Checks that the passed expressions generate an XPathException
     * @param xpath
     * @param xml
     * @param xpath1
     * @param xpath2
     * @throws Exception
     */
    void assertXPathFails(String xpath, String xml, XPath xpath1, XPath xpath2) throws Exception;
    
    public void assertNodeListSize(int size, String xpath,
        XPath xpath1,
        XPath xpath2,
        String xml) throws Exception;
    
}