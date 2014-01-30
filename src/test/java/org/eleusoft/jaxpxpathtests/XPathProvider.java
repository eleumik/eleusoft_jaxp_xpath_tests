package org.eleusoft.jaxpxpathtests;

import javax.xml.XMLConstants;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFactoryConfigurationException;

/**
 * Abstraction of a provider of XPath instances,
 * we need to override the jaxp api to test
 * a specific processor. Additionally saxon needs
 * to be set in backward-compatibility mode.
 */
public abstract class XPathProvider
{
    protected XPathFactory factory;



    public XPath createNewSecureXPath() throws XPathFactoryConfigurationException
    {
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        try
        {
            return factory.newXPath();
        }
        finally
        {
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);

        }
    }
    public XPath createNewXPath()
        throws XPathFactoryConfigurationException
    {
        return factory.newXPath();
    }

}