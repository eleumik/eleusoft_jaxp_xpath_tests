package org.eleusoft.jaxpxpathtests.tests.providers;

import org.eleusoft.jaxpxpathtests.XPathProvider;
import org.eleusoft.jaxpxpathtests.providers.SaxonXPathProvider;
import org.eleusoft.jaxpxpathtests.tests.JAXPTestSimpleCompiled;

public class JAXPTestSimpleCompiled_Saxon_Saxon extends JAXPTestSimpleCompiled
{
    public JAXPTestSimpleCompiled_Saxon_Saxon()
    {
        super(true);
    } 
    
    protected XPathProvider getProvider1()
    {
        return new SaxonXPathProvider();
    }
    
    protected XPathProvider getProvider2()
    {
        return new SaxonXPathProvider();
    }

    
}
