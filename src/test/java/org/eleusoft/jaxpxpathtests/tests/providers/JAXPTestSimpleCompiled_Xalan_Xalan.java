package org.eleusoft.jaxpxpathtests.tests.providers;

import org.eleusoft.jaxpxpathtests.XPathProvider;
import org.eleusoft.jaxpxpathtests.providers.XalanXPathProvider;
import org.eleusoft.jaxpxpathtests.tests.JAXPTestSimpleCompiled;

public class JAXPTestSimpleCompiled_Xalan_Xalan extends JAXPTestSimpleCompiled
{
    public JAXPTestSimpleCompiled_Xalan_Xalan()
    {
        super(true);
    }

    protected XPathProvider getProvider1()
    {
        return new XalanXPathProvider();
    }

    protected XPathProvider getProvider2()
    {
        return new XalanXPathProvider();
    }

}
