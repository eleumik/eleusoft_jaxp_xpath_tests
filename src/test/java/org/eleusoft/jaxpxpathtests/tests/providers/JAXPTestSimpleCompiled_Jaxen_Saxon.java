package org.eleusoft.jaxpxpathtests.tests.providers;

import org.eleusoft.jaxpxpathtests.XPathProvider;
import org.eleusoft.jaxpxpathtests.providers.JaxenXPathProvider;
import org.eleusoft.jaxpxpathtests.providers.SaxonXPathProvider;
import org.eleusoft.jaxpxpathtests.tests.JAXPTestSimpleCompiled;

public class JAXPTestSimpleCompiled_Jaxen_Saxon extends JAXPTestSimpleCompiled
{
    
    protected XPathProvider getProvider1()
    {
        return new JaxenXPathProvider();
    }
    
    protected XPathProvider getProvider2()
    {
        return new SaxonXPathProvider();
    }

    
}
