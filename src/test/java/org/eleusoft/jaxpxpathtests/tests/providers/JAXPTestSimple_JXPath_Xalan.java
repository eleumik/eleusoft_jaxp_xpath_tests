package org.eleusoft.jaxpxpathtests.tests.providers;

import org.eleusoft.jaxpxpathtests.XPathProvider;
import org.eleusoft.jaxpxpathtests.providers.JXPathXPathProvider;
import org.eleusoft.jaxpxpathtests.providers.XalanXPathProvider;
import org.eleusoft.jaxpxpathtests.tests.JAXPTestSimple;

public class JAXPTestSimple_JXPath_Xalan extends JAXPTestSimple
{
    
    protected XPathProvider getProvider1()
    {
        return new JXPathXPathProvider();
    }
    
    protected XPathProvider getProvider2()
    {
        return new XalanXPathProvider();
    }

    
}
