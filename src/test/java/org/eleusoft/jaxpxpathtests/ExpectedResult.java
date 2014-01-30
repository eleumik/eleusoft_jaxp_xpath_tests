package org.eleusoft.jaxpxpathtests;

import java.util.HashMap;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathConstants;


/**
 * Helper for identifying correct results.
 *
 *
 */
public class ExpectedResult
{
    public static final Object NULLNODE = null;

    private final HashMap expectedValues = new HashMap();
    public ExpectedResult(Double d)
    {
        setExpectedDouble(d);
    }
    public ExpectedResult(String d)
    {
        setExpectedString(d);
    }
    public ExpectedResult(Boolean d)
    {
        setExpectedBoolean(d);
    }
    public Object getExpected(QName type)
    {
        return expectedValues.get(type);
    }
    public boolean hasExpected(QName type)
    {
        return expectedValues.containsKey(type);
    }

    public void setExpectedDouble(Double d)
    {
        put(XPathConstants.NUMBER, d);
    }
    public void setExpectedString(String s)
    {
        put(XPathConstants.STRING, s);
    }
    public void setExpectedBoolean(Boolean s)
    {
        put(XPathConstants.BOOLEAN, s);
    }



    private void put(QName type, Object v)
    {
        expectedValues.put(type, v);
    }
    public void setExpectedNodeList(Object n)
    {
        put(XPathConstants.NODESET, n);

    }
    public void setExpectedNode(Object n)
    {
        put(XPathConstants.NODE, n);

    }

}
