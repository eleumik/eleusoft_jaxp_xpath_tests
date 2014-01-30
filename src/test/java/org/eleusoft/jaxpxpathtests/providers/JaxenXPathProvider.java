/**
 *
 */
package org.eleusoft.jaxpxpathtests.providers;

import javax.xml.XMLConstants;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactoryConfigurationException;

import org.eleusoft.jaxp.jaxen.JaxenXPathFactory;
import org.eleusoft.jaxpxpathtests.XPathProvider;

public class JaxenXPathProvider extends XPathProvider
{
    public JaxenXPathProvider()
    {
        // TODO Auto-generated constructor stub
    }
    public XPath createNewXPath()
    //throws XPathFactoryConfigurationException
    {
        JaxenXPathFactory factory1 = new JaxenXPathFactory();
        return factory1.newXPath();
    }
    public XPath createNewSecureXPath() throws XPathFactoryConfigurationException
    {
        JaxenXPathFactory factory1 = new JaxenXPathFactory();
        factory1.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        return factory1.newXPath();

    }
}