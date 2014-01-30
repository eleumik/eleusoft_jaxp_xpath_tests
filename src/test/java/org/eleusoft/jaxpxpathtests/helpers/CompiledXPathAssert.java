/**
 *
 */
package org.eleusoft.jaxpxpathtests.helpers;

import java.io.StringReader;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;

import org.eleusoft.jaxpxpathtests.XPathAssert;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * Implementation of XPathAssert
 * that uses compiled xpaths.
 *
 *
 * @author Michele Vivoda
 */
public class CompiledXPathAssert extends AbstractXPathAssert implements XPathAssert
{
    protected Object evaluate(final String xpath,
                              final String xml,
                           final XPath xpath1,
                           final QName returnType)
    throws XPathExpressionException
   {
       final InputSource is1 = new InputSource(new StringReader(xml));
       final Object res1 = xpath1.compile(xpath).evaluate(is1, returnType);
       return res1;
   }
    protected Object evaluateDocument(final String xpath,
                              final Document doc,
                           final XPath xpath1,
                           final QName returnType)
    throws Exception
   {
       final Object res1 = xpath1.compile(xpath).evaluate(doc, returnType);
       return res1;
   }

}