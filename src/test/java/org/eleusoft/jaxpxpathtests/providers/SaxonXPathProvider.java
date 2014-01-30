/**
 *
 */
package org.eleusoft.jaxpxpathtests.providers;

import javax.xml.XMLConstants;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactoryConfigurationException;

import org.eleusoft.jaxpxpathtests.XPathProvider;

public class SaxonXPathProvider extends XPathProvider
{
    public SaxonXPathProvider()
    {
        // TODO Auto-generated constructor stub
    }
    public XPath createNewXPath()
    //throws XPathFactoryConfigurationException
    {
        net.sf.saxon.xpath.XPathFactoryImpl factory1 = new net.sf.saxon.xpath.XPathFactoryImpl();
        net.sf.saxon.xpath.XPathEvaluator ev = (net.sf.saxon.xpath.XPathEvaluator)factory1.newXPath();
        ev.setBackwardsCompatible(true);
        return ev;
    }
    public XPath createNewSecureXPath() throws XPathFactoryConfigurationException
    {
        net.sf.saxon.xpath.XPathFactoryImpl factory1 = new net.sf.saxon.xpath.XPathFactoryImpl();
        factory1.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        net.sf.saxon.xpath.XPathEvaluator ev = (net.sf.saxon.xpath.XPathEvaluator)factory1.newXPath();
        ev.setBackwardsCompatible(true);
        return ev;

    }
}