package org.eleusoft.jaxpxpathtests.tests;

import org.eleusoft.jaxpxpathtests.helpers.CompiledXPathAssert;

/**
 * Simple tests using the XPath interface;
 * this test <em>does compile</em> the expression.
 *
 * @author Michele Vivoda
 */
public abstract  class JAXPTestSimpleCompiled extends JAXPTestSimple
{
    protected JAXPTestSimpleCompiled()
    {
        this(false);
    }
    protected JAXPTestSimpleCompiled(boolean same)
    {
        super(same);
        this.setXPathAssert(new CompiledXPathAssert());
    }
    
}
