package org.eleusoft.jaxpxpathtests.helpers;

import java.io.StringReader;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFunction;
import javax.xml.xpath.XPathFunctionException;

import org.eleusoft.jaxp.common.NamespaceContextSupport;
import org.eleusoft.jaxp.common.XPathFunctionResolverSupport;
import org.eleusoft.jaxp.common.XPathValues;
import org.eleusoft.jaxp.common.XPathVariableResolverSupport;
import org.eleusoft.jaxpxpathtests.ExpectedResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Abstract implementation of XPathAssert
 *
 *
 * @author Michele Vivoda
 */
public abstract class AbstractXPathAssert
{

    public AbstractXPathAssert()
    {
        super();
    }

    protected abstract Object evaluateDocument(final String xpath,
                                                final Document doc,
                                                final XPath xpath1,
                                                final QName returnType) throws Exception;

    protected abstract Object evaluate(final String xpath,
                                        final String xml,
                                        final XPath xpath1,
                                        final QName returnType) throws XPathExpressionException;

    private final void assertValue(final ExpectedResult expected,
                               final String xpath,
                               final String xml,
                               final XPath xpath1,
                               final XPath xpath2,
                               final QName returnType) throws Exception
    {
        final Object res1 = evaluate(xpath, xml, xpath1, returnType);
        final Object res2 = evaluate(xpath, xml, xpath2, returnType);
        BaseTestXPath.assertNotNull("XPath Implementation nr.1 error returned null evaluating xpath", res1);
        BaseTestXPath.assertNotNull("XPath Implementation nr.2 error returned null evaluating xpath", res2);

        final StringBuffer error = new StringBuffer();
        if (expected!= null && expected.hasExpected(returnType))
        {
            Object exp = expected.getExpected(returnType);
            BaseTestXPath.assertNotNull("ASSERT-IMPLERROR", exp);
            if (!exp.equals(res1)) error.append ("\nvalue of {1}: [" + res1 + "] is not equal to EXPECTED: [" + exp + "]");
            if (!exp.equals(res2)) error.append ("\nvalue of {2}: [" + res2 + "] is not equal to EXPECTED: [" + exp + "]");

        }
        else if (!res1.equals(res2))
            error.append("\nvalue of {1}: [" + res1 + "] is not equal to value of {2}: [" + res2 + "]");

       if (error.length()!=0)
       {
           BaseTestXPath.fail("Error - \nXPath [" + xpath + "] - \nreturn type [" +
                getXPathReturnTypeLabel(returnType) +
                "] \nmessages:" + error.toString() +
                "\nImpl1: " + xpath1 + 
                "\nImpl2: " + xpath2 + 
                "\nxml: \n" + xml + "\n--");
       }
    }

    private final void assertNode(ExpectedResult expected, String xpath,
                                 String xml,
                                 XPath xpath1,
                                 XPath xpath2,
                                 QName returnType) throws Exception
    {
            final Document doc = getDocument(xml);
            final Object res1 = evaluateDocument(xpath, doc, xpath1, returnType);
            final Object res2 = evaluateDocument(xpath, doc, xpath2, returnType);
            BaseTestXPath.assertEquals("XPath [" + xpath + "] - [" +
               getXPathReturnTypeLabel(returnType) +
               "] result single nodes are not equal", res1, res2);
            if (expected!=null && expected.hasExpected(returnType))
            {
                Object expectedNode = expected.getExpected(returnType);
                BaseTestXPath.assertEquals("XPath [" + xpath + "] - [" +
                    getXPathReturnTypeLabel(returnType) +
                    "] result is not as expected", expectedNode, res2);

            }

       }

    public final void assertNodeListSize(int size, String xpath,
        XPath xpath1,
        XPath xpath2,
        String xml) throws Exception
    {
        final Document doc = getDocument(xml);
        final NodeList res1 = (NodeList)evaluateDocument(xpath, doc, xpath1, XPathConstants.NODESET);
        final NodeList res2 = (NodeList)evaluateDocument(xpath, doc, xpath2, XPathConstants.NODESET);
        BaseTestXPath.assertEquals("Node list1 size must be equal to " + size, size, res1.getLength());
        BaseTestXPath.assertEquals("Node list2 size must be equal to " + size, size, res2.getLength());
          
    }

    private final void assertNodeList(ExpectedResult expected, String xpath,
                                     String xml,
                                     XPath xpath1,
                                     XPath xpath2,
                                     QName returnType) throws Exception
    {
          final Document doc = getDocument(xml);
          final NodeList res1 = (NodeList)evaluateDocument(xpath, doc, xpath1, returnType);
          final NodeList res2 = (NodeList)evaluateDocument(xpath, doc, xpath2, returnType);
          BaseTestXPath.assertEquals("Node list size must be equal", res1.getLength(), res2.getLength());
          if (expected!=null && expected.hasExpected(returnType))
          {
              final NodeList expectedNodeList =  (NodeList)expected.getExpected(returnType);
              if (expectedNodeList.getLength()!=0) // TODO may support one day..
                  throw new Error("Not supported check other than empty node list.");
              else
              {
                  BaseTestXPath.assertEquals("Node list size must be zero", 0, res2.getLength());

              }
          }
          for (int i=0,len=res1.getLength();i<len;i++)
          {
              final Node node1 = res1.item(i);
              final Node node2 = res2.item(i);

              BaseTestXPath.assertEquals("XPath [" + xpath + "] - [" +
                  getXPathReturnTypeLabel(returnType) +
                  "] nodes at position [" + i +
                  "] of a list of ["+res1.getLength() + "] are not equal, " +
                  "\n1st list:\n"+printNodes(res1) + " from " + xpath1 +
                  "\n2nd list:\n"+printNodes(res2) + " from " + xpath2 +
                  "\nassert:",
                  node1,
                  node2);
          }

      }

    private String printNodes(NodeList res1)
    {
        StringBuffer sb = new StringBuffer();
        for (int i=0,len=res1.getLength();i<len;i++)
        {
            final Node node1 = res1.item(i);
            sb.append(i + ": " + node1);
            String nv = node1.getTextContent();
            if (nv!=null) sb.append(" - text:[" + nv + "]");
            sb.append('\n');
        }
        return sb.toString();
    }

    private final Document getDocument(String xml) throws Exception
    {
        return JAXP.getDocument(new InputSource(new StringReader(xml)));
    }

    private final String getXPathReturnTypeLabel(QName q)
    {
        if (q.equals(XPathConstants.STRING))
            return "STRING";
        else if (q.equals(XPathConstants.NUMBER))
            return "NUMBER";
        else if (q.equals(XPathConstants.NODE))
            return "NODE";
        else if (q.equals(XPathConstants.NODESET))
            return "NODESET";
        else if (q.equals(XPathConstants.BOOLEAN))
            return "BOOLEAN";
        else throw new IllegalArgumentException("Unknown return type:" + q);

    }

    public final void assertXPath(ExpectedResult expected, String xpath,
                               String xml,
                               XPath xpath1,
                               XPath xpath2,
                               boolean doNodes) throws Exception
    {

        NamespaceContextSupport ns = new NamespaceContextSupport();
        ns.put("x", "http://example.org");
        ns.put("b", "bla");
        xpath1.setNamespaceContext(ns);
        xpath2.setNamespaceContext(ns);
        XPathFunctionResolverSupport func = new XPathFunctionResolverSupport();
        func.registerFunction(new QName("http://example.org","square"), 1, new XPathFunction()
        {

            public Object evaluate(List list) throws XPathFunctionException
            {
                if (list.size()!=1)
                    throw new XPathFunctionException("Only one argument for square(), passed " + list.size());
                Object o = list.get(0);
                if (o instanceof Number)
                {
                    double d = ((Number)o).doubleValue();
                    return new Double(d*d);
                }
                else return new Double(Double.NaN);
            }

        });
        func.registerFunction(new QName("http://example.org","concat"),
            XPathFunctionResolverSupport.ARITY_ANY,
            new XPathFunction()
        {

            public Object evaluate(List list) throws XPathFunctionException
            {
                final StringBuffer sb = new StringBuffer();
                for(int i=0,len=list.size();i<len;i++)
                {
                    Object o = list.get(i);
                    sb.append(o.toString());
                }
                return sb.toString();
            }

        });
        func.registerFunction(new QName("http://example.org","invert"), 1, new XPathFunctionInvert());
        // this won't be queried
        func.registerFunction(new QName(XMLConstants.NULL_NS_URI,"invert"), 1, new XPathFunctionInvert());
        xpath1.setXPathFunctionResolver(func);
        xpath2.setXPathFunctionResolver(func);
        XPathVariableResolverSupport variables = new XPathVariableResolverSupport();
        variables.declareVariable(new QName("", "unoString"), "1");
        variables.declareVariable(new QName("", "dueString"), "2");
        // Here jxpath and jaxen were transforming to NaN if variable is Integer !
        variables.declareVariable(new QName("", "unoDouble"), new Double("1"));
        variables.declareVariable(new QName("", "dueDouble"), new Double("2"));
        xpath1.setXPathVariableResolver(variables);
        xpath2.setXPathVariableResolver(variables);
        

        assertValue(expected, xpath, xml, xpath1, xpath2, XPathConstants.STRING);
        assertValue(expected, xpath, xml, xpath1, xpath2, XPathConstants.NUMBER);
        assertValue(expected, xpath, xml, xpath1, xpath2, XPathConstants.BOOLEAN);
        if (doNodes)
        {
            assertNode(expected, xpath, xml, xpath1, xpath2, XPathConstants.NODE);
            assertNodeList(expected, xpath, xml, xpath1, xpath2, XPathConstants.NODESET);
        }

    }

    static class XPathFunctionInvert implements XPathFunction
    {

        public Object evaluate(List list) throws XPathFunctionException
        {
            if (list.size()!=1)
                throw new XPathFunctionException("Only one argument for invert(), passed " + list.size());
            Object o = list.get(0);
            //System.out.println("Valus is " + o.getClass());
            String s =  XPathValues.convertToString(o);
            StringBuffer sb = new StringBuffer();
            for(int i=s.length();--i>=0;)
            {
                sb.append(s.charAt(i));
            }
            return sb.toString();
        }

    }


    public void assertXPathFails(String xpath,
                                    String xml,
                                    XPath xpath1,
                                    XPath xpath2) throws Exception
    {
        String msg = "";
        try
        {
            evaluate(xpath, xml, xpath1, XPathConstants.STRING);
            msg = "\n{1} Has not failed as expected.";
        }
        catch(XPathExpressionException x)
        {

        }
        try
        {
            evaluate(xpath, xml, xpath2, XPathConstants.STRING);
            msg = msg + "\n{2} Has not failed as expected.";
        }
        catch(XPathExpressionException x)
        {

        }
        if (msg.length()!=0)
        {
            BaseTestXPath.fail("Missing Failure Error - \nXPath [" + xpath + "] - \nreturn type [" +
                getXPathReturnTypeLabel(XPathConstants.STRING) +
                "] \nMessage:" + msg + "\n--");

        }
    }

}