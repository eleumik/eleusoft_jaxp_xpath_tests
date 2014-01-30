/**
 *
 */
package org.eleusoft.jaxpxpathtests.providers;

import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFactoryConfigurationException;

import org.eleusoft.jaxpxpathtests.XPathProvider;


public class DefaultXPathProvider extends XPathProvider
{
    public DefaultXPathProvider() throws XPathFactoryConfigurationException
    {
        factory = XPathFactory.newInstance(XPathFactory.DEFAULT_OBJECT_MODEL_URI);
    }

}