package org.eleusoft.jaxpxpathtests.tests.providers;

import org.eleusoft.jaxpxpathtests.XPathProvider;
import org.eleusoft.jaxpxpathtests.providers.SaxonXPathProvider;
import org.eleusoft.jaxpxpathtests.tests.JAXPTestSimple;

public class JAXPTestSimple_Saxon_Saxon extends JAXPTestSimple
{
    public JAXPTestSimple_Saxon_Saxon()
    {
        super(true); // specify use same class
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
