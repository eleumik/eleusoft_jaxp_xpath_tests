package org.eleusoft.jaxpxpathtests.tests.providers;

import org.eleusoft.jaxpxpathtests.XPathProvider;
import org.eleusoft.jaxpxpathtests.providers.JaxenXPathProvider;
import org.eleusoft.jaxpxpathtests.providers.XalanXPathProvider;
import org.eleusoft.jaxpxpathtests.tests.JAXPTestSimple;

public class JAXPTestSimple_Xalan_Jaxen extends JAXPTestSimple
{
    
    

    protected XPathProvider getProvider1()
    {
        return new XalanXPathProvider();
    }
    
    protected XPathProvider getProvider2()
    {
        return new JaxenXPathProvider();
    }

    
}
