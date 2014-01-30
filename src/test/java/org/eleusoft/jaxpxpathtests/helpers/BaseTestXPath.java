package org.eleusoft.jaxpxpathtests.helpers;


import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactoryConfigurationException;

import junit.framework.TestCase;

import org.eleusoft.jaxpxpathtests.ExpectedResult;
import org.eleusoft.jaxpxpathtests.XPathAssert;
import org.eleusoft.jaxpxpathtests.XPathProvider;

/**
 * Abstract base class for javax.xml.xpath
 * interfaces testing, based on a contained
 * {@link XPathAssert} interface that does
 * the actual work of checking.
 * <p>Since is very difficult to test
 * for xpath results when these are nodes or nodesets,
 * this test uses both Xalan and JXPath and
 * compares the results, so the results
 * are not 'absolute', but 'relative'
 * to the conformance of Xalan.
 * <p>Since earlier versions of Xalan have bugs
 * the newer version (now 2.7.1) should be used,
 * possibly using the <em>endorsed</em>
 * mechanism to replace the built in parsers (in java 1.4).
 *
 * @author Michele Vivoda
 */
public abstract class BaseTestXPath extends TestCase
{
    /**
     * Used to mark tests that verify that XPath
     * has same precision of Java Double so that 
     * <code>4-3.2 != 0.8</code>
     */
    protected static final boolean ISSUE_DOUBLE_PRECISION = true;
    
    /**
     * NOT USED, we directly instantiate the factory for JXPath,
     * is very complicate to have the XPathFactory load the
     * jxpath implementation inside the test environment and since the only thing that
     * the XPathFactory does is instantiating, it should be the same.
     * <p>The right way should be using:
     * <pre>XPathFactory.newInstance(JXPathXPathFactory.URI);</pre>
     */
    /**
    static
    {
        System.setProperty("javax.xml.xpath.XPathFactory:http://org.apache/commons/jxpath","org.apache.commons.jxpath.jaxp.JXPathXPathFactory");
    }
    **/
    /**
     * Xalan XPath implementation
     */
    private final XPath xpath1;
    /**
     * JXPath XPath implementation
     */
    private final XPath xpath2;
    /**
     * The contained XPathAssert instance
     */
    private XPathAssert xPathAssert;
    
    //XPathProvider provider1 = new SaxonXPathProvider();
    //XPathProvider provider1 = new DefaultXPathProvider();
    //XPathProvider provider1 = new XalanXPathProvider();
    //XPathProvider provider2 = new JXPathXPathProvider();
    //XPathProvider provider2 = new JaxenXPathProvider();

    protected abstract XPathProvider getProvider1() throws XPathFactoryConfigurationException;
    protected abstract XPathProvider getProvider2() throws XPathFactoryConfigurationException;
    
    protected BaseTestXPath()
    {
        this(false);
    }
    /**
     * Constructor
     */
    protected BaseTestXPath(boolean same)
    {
        try
        {

            xpath1 = getProvider1().createNewXPath();
            xpath2 = getProvider2().createNewXPath();
        }
        catch(Exception e)
        {
            throw new RuntimeException("BaseTestXPath initialization failure ", e);
        }
        setXPathAssert(new StdXPathAssert());
        if (false==same) assertFalse("Cannot be the same class, that is " + xpath1.getClass().getName(),
            xpath1.getClass().getName().equals(xpath2.getClass().getName()));
    }
    /**
     * Configures this instance with an alternate
     * XPathAssert, that by default is  {@link StdXPathAssert}.
     * @param xPathAssert a required {@link XPathAssert}
     */
    protected void setXPathAssert(XPathAssert xPathAssert)
    {
        this.xPathAssert = xPathAssert;
    }
    /**
     * Checks that the expression is invalid.
     * @param xpath
     * @param xml
     * @throws Exception
     */
    protected void assertXPathFails(String xpath, String xml) throws Exception
    {
        xPathAssert.assertXPathFails(xpath, xml, xpath1, xpath2);
    }
    /**
     * Checks that the expression is invalid under secure processing
     * @param xpath
     * @param xml
     * @throws Exception
     */
    protected void assertXPathFailsSecureProcessing(String xpath, String xml) throws Exception
    {
        xPathAssert.assertXPathFails(xpath, xml,
            getProvider1().createNewSecureXPath(),
            getProvider2().createNewSecureXPath());

    }


    /**
     * Checks the results avoiding check for NODE and NODESET results
     * @param xpath
     * @param xml
     * @throws Exception
     */
    protected void assertXPathNoNodes(String xpath, String xml) throws Exception
    {
        xPathAssert.assertXPath(null, xpath, xml, xpath1, xpath2, false);
    }
    /**
     * Checks the results for NODESET only checking only size
     * @param xpath
     * @param xml
     * @throws Exception
     */
    protected void assertXPathNodelistSize(int size, String xpath, String xml) throws Exception
    {
        xPathAssert.assertNodeListSize(size, xpath, xpath1, xpath2, xml);
    }


    /**
     * Checks the results avoiding check for NODE and NODESET results
     * and with an expected Double result
     * @param xpath
     * @param xml
     * @throws Exception
     */
    protected void assertXPathNoNodes(Double expected, String xpath, String xml) throws Exception
    {
        xPathAssert.assertXPath(new ExpectedResult(expected),  xpath, xml, xpath1, xpath2, false);
    }
    /**
     * Checks the results avoiding check for NODE and NODESET results
     * and with an expected Boolean result
     * @param xpath
     * @param xml
     * @throws Exception
     */
    protected void assertXPathNoNodes(Boolean expected, String xpath, String xml) throws Exception
    {
        xPathAssert.assertXPath(new ExpectedResult(expected),  xpath, xml, xpath1, xpath2, false);
    }

    /**
     * Checks the results avoiding check for NODE and NODESET results
     * and with an expected String result
     * @param xpath
     * @param xml
     * @throws Exception
     */
    protected void assertXPathNoNodes(String expected, String xpath, String xml) throws Exception
    {
        xPathAssert.assertXPath(new ExpectedResult(expected),  xpath, xml, xpath1, xpath2, false);
    }




    /**
     * Checks the results with also check for NODE and NODESET results
     * <p>This method cross checks WITHOUT checking an expected result,
     * is used for those checks in which is not possible/convenient to specify
     * the expected result.
     * @param xpath
     * @param xml
     * @throws Exception
     */
    protected void assertXPath(String xpath, String xml) throws Exception
    {
        xPathAssert.assertXPath(null, xpath, xml, xpath1, xpath2, true);
    }
    /**
     * Checks the results with also check for NODE and NODESET results
     * and expecting the passed Double for NUMBER result.
     * <p>This method cross checks but also has an expected result parameter
     * @param xpath
     * @param xml
     * @throws Exception
     */
    protected void assertXPath(Double d, String xpath, String xml) throws Exception
    {
        xPathAssert.assertXPath(new ExpectedResult(d), xpath, xml, xpath1, xpath2, true);
    }
    /**
     * Checks the results with also check for NODE and NODESET results
     * and expecting the passed String for STRING result.
     * <p>This method cross checks but also has an expected result parameter
     * @param xpath
     * @param xml
     * @throws Exception
     */
    protected void assertXPath(String expected, String xpath, String xml) throws Exception
    {
        xPathAssert.assertXPath(new ExpectedResult(expected), xpath, xml, xpath1, xpath2, true);
    }


    /**
     * Checks the results with also check for NODE and NODESET results
     * using also the passed expected result.
     * <p>This method cross checks but also has an expected result parameter
     * @param xpath
     * @param xml
     * @throws Exception
     */
    protected void assertXPath(ExpectedResult expected, String xpath, String xml) throws Exception
    {
        xPathAssert.assertXPath(expected, xpath, xml, xpath1, xpath2, true);
    }

}
