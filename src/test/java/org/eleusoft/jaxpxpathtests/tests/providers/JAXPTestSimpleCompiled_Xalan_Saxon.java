package org.eleusoft.jaxpxpathtests.tests.providers;

import org.eleusoft.jaxpxpathtests.XPathProvider;
import org.eleusoft.jaxpxpathtests.providers.SaxonXPathProvider;
import org.eleusoft.jaxpxpathtests.providers.XalanXPathProvider;
import org.eleusoft.jaxpxpathtests.tests.JAXPTestSimpleCompiled;

public class JAXPTestSimpleCompiled_Xalan_Saxon extends JAXPTestSimpleCompiled
{
    
    protected XPathProvider getProvider1()
    {
        return new XalanXPathProvider();
    }
    
    protected XPathProvider getProvider2()
    {
        return new SaxonXPathProvider();
    }

    
}
