package org.eleusoft.jaxpxpathtests.helpers;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Helper for retrieving a DOM document.
 *
 *
  * @author Michele Vivoda
*/
public class JAXP
{
    public static Document getDocument(InputSource is)
    throws Exception
    {

        final DocumentBuilder builder = getDocumentBuilder();
        builder.setErrorHandler(new ErrorHandler()
        {
            
            public void warning(SAXParseException exception) throws SAXException
            {
                exception.printStackTrace();
                throw exception;
            }
            
            public void fatalError(SAXParseException exception) throws SAXException
            {
                exception.printStackTrace();
                throw exception;
            }
            
            public void error(SAXParseException exception) throws SAXException
            {
                exception.printStackTrace();
                throw exception;
                
            }
        });
        return builder.parse(is);
    }

    private static DocumentBuilder getDocumentBuilder()
    {
        try
        {
            DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(true);
            factory.setExpandEntityReferences(false);
            return factory.newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            throw new Error("JAXP config error:" + e.getMessage(), e);
        }

    }

}
