/**
 *
 */
package org.eleusoft.jaxpxpathtests.providers;

import org.eleusoft.jaxpxpathtests.XPathProvider;

public class XalanXPathProvider extends XPathProvider
{
    static
    {
        System.out.println("xalan Version should be at least 2.7.1");
        org.apache.xalan.Version.main(new String[]{});
    }
    public XalanXPathProvider()
    {
        factory = new org.apache.xpath.jaxp.XPathFactoryImpl();
    }
}