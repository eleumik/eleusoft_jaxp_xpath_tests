package org.eleusoft.jaxpxpathtests.tests.providers;

import javax.xml.xpath.XPathFactoryConfigurationException;

import org.eleusoft.jaxpxpathtests.XPathProvider;
import org.eleusoft.jaxpxpathtests.providers.DefaultXPathProvider;
import org.eleusoft.jaxpxpathtests.providers.SaxonXPathProvider;
import org.eleusoft.jaxpxpathtests.tests.JAXPTestSimpleCompiled;

public class JAXPTestSimpleCompiled_Default_Saxon extends JAXPTestSimpleCompiled
{
    
    protected XPathProvider getProvider1() throws XPathFactoryConfigurationException
    {
        return new DefaultXPathProvider();
    }
    
    protected XPathProvider getProvider2()
    {
        return new SaxonXPathProvider();
    }

    
}
