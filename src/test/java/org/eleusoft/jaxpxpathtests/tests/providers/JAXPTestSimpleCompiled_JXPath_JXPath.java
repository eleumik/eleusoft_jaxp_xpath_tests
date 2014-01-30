package org.eleusoft.jaxpxpathtests.tests.providers;

import org.eleusoft.jaxpxpathtests.XPathProvider;
import org.eleusoft.jaxpxpathtests.providers.JXPathXPathProvider;
import org.eleusoft.jaxpxpathtests.providers.JaxenXPathProvider;
import org.eleusoft.jaxpxpathtests.tests.JAXPTestSimpleCompiled;

public class JAXPTestSimpleCompiled_JXPath_JXPath extends JAXPTestSimpleCompiled
{
    public JAXPTestSimpleCompiled_JXPath_JXPath()
    {
        super(true);
    }
    
    protected XPathProvider getProvider1()
    {
        return new JXPathXPathProvider();
    }
    
    protected XPathProvider getProvider2()
    {
        return new JXPathXPathProvider();
    }

    
}
