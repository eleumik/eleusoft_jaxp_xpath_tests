package org.eleusoft.jaxpxpathtests.tests.providers;

import org.eleusoft.jaxpxpathtests.XPathProvider;
import org.eleusoft.jaxpxpathtests.providers.JaxenXPathProvider;
import org.eleusoft.jaxpxpathtests.tests.JAXPTestSimpleCompiled;

public class JAXPTestSimpleCompiled_Jaxen_Jaxen extends JAXPTestSimpleCompiled
{
    public JAXPTestSimpleCompiled_Jaxen_Jaxen()
    {
        super(true);
    }
    
    protected XPathProvider getProvider1()
    {
        return new JaxenXPathProvider();
    }
    
    protected XPathProvider getProvider2()
    {
        return new JaxenXPathProvider();
    }

    
}
