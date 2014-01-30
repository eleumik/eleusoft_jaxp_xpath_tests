package org.eleusoft.jaxpxpathtests.tests;


import junit.framework.AssertionFailedError;

import org.eleusoft.jaxp.common.NodeListImpl;
import org.eleusoft.jaxpxpathtests.ExpectedResult;
import org.eleusoft.jaxpxpathtests.helpers.BaseTestXPath;

/**
 * Simple tests using the XPath interface. 
 * This test does not compile the expression.
 * 
 * <p>This file is the place where to add new tests.
 * 
 * @author Michele Vivoda
 */
public abstract class JAXPTestSimple extends BaseTestXPath
{
    protected JAXPTestSimple()
    {
        this(false);
    }
    protected JAXPTestSimple(boolean same)
    {
        super(same);
    }
    
    
    public void testSimpleRoot() throws Exception
    {
        assertXPath(new Double(123.3), "/", "<root>123.3</root>");
    }

    // new20091210
    public void testSimpleParentOfRoot() throws Exception
    {
        assertXPathNoNodes("..", "<root>123.3</root>");
    }

    public void testSimpleRootChild() throws Exception
    {
        assertXPath(new Double(123.3), "/child::node()", "<root>123.3</root>");
    }

    public void testSimpleRootChild2() throws Exception
    {
        assertXPath(new Double(123.3), "/child::*", "<root>123.3</root>");
    }

    public void testSimpleContext() throws Exception
    {
        assertXPath(new Double(123.3), ".", "<root>123.3</root>");
    }

    public void testSimpleRootContext() throws Exception
    {
        assertXPath(new Double(123.3), "/.", "<root>123.3</root>");
    }

    public void testSimpleText() throws Exception
    {
        assertXPath(new Double(123.3), "/root", "<root>123.3</root>");
    }

    public void testSimpleTextAttribute() throws Exception
    {
        assertXPath(new Double(123.3), "/root/@value", "<root value='123.3'/>");
    }

    public void testSimpleTextPreserveWS() throws Exception
    {
        assertXPath(new Double(123.3), "/root", "<root> 123.3 </root>");
    }

    public void testSimpleTextAttributePreserveWS() throws Exception
    {
        assertXPath(new Double(123.3),
            "/root/@value",
            "<root value=' 123.3 '/>");
    }

    public void testSimpleTextContained() throws Exception
    {
        assertXPath(new Double(123.3), "/root", "<root><a>123.3</a></root>");
    }

    public void testSimpleTextAttributeContained() throws Exception
    {
        assertXPath(new Double(123.3),
            "/root/a/@value",
            "<root><a value='123.3'/></root>");
    }

    public void testSimpleTextSelected() throws Exception
    {
        assertXPath(new Double(123.3), "/root/a", "<root><a>123.3</a></root>");
    }

    public void testSimpleText2Items() throws Exception
    {
        // Here the result is a nodelist so the text of chldren is taken
        assertXPath(new Double(Double.NaN),
            "/root",
            "<root><a>123.3</a><a>1.2</a></root>");
    }

    public void testSimpleText2Items_Trick() throws Exception
    {
        // Here the result is a nodelist so the text of chldren is taken
        // Here the result is '123'+'124' = 123124
        assertXPath(new Double(123124),
            "/root",
            "<root><a>123</a><a>124</a></root>");
    }

    public void testSimpleTextAttribute2Items() throws Exception
    {
        assertXPath(new Double(123.3),
            "/root/a/@value",
            "<root><a value='123.3'/><a value='3.3'/></root>");
    }

    public void testSimpleText2ItemsPreserveWS_NodeText() throws Exception
    {
        // Here the result is ' 123.3 1.2 ' so is NaN
        assertXPath(new Double(Double.NaN),
            "/root",
            "<root><a> 123.3 </a><a> 1.2 </a></root>");
    }

    public void testSimpleText2ItemsPreserveWS_Select() throws Exception
    {
        assertXPath(new Double(123.3),
            "/root/a",
            "<root><a> 123.3 </a><a> 1.2 </a></root>");
    }

    public void testSimpleText2ItemsSelectFirst() throws Exception
    {
        assertXPath(new Double(123.3),
            "/root/a[1]",
            "<root><a>123.3</a><a>dfds</a></root>");
    }

    public void testSimpleTextAttribute2ItemsSelectFirst() throws Exception
    {
        assertXPath(new Double(123.3),
            "/root/a/@value[1]",
            "<root><a value='123.3'/><a value='3.3'/></root>");
    }

    public void testSimpleText2ItemsSelectFirstWithPrecedingSibling() throws Exception
    {
        assertXPath(new Double(123.3),
            "/root/a[2]/preceding-sibling::*",
            "<root><a>123.3</a><a>3.3</a></root>");
    }

    public void testSimpleText2ItemsSelectSecond() throws Exception
    {
        assertXPath(new Double(3.4),
            "/root/a[2]",
            "<root><a>123.3</a><a>3.4</a></root>");
    }

    public void testSimpleText2ItemsSelectSecondWithFollowingSibiling() throws Exception
    {
        assertXPath(new Double(3.4),
            "/root/a[1]/following-sibling::*",
            "<root><a>123.3</a><a>3.4</a></root>");
    }

    public void testSimpleTextAttribute2ItemsSelectSecond() throws Exception
    {
        assertXPath(new Double(3.3),
            "/root/a[2]/@value",
            "<root><a value='123.3'/><a value='3.3'/></root>");
    }

    public void testSimpleTextAttribute2ItemsDifferentNameSelectSecondByName() throws Exception
    {
        assertXPath(new Double(3.3),
            "/root/a/@value2",
            "<root><a value='123.3'/><a value2='3.3'/></root>");
    }

    public void testSimpleTextAttribute2ItemsDifferentNameSelectSecondByName_Recursive() throws Exception
    {
        assertXPath(new Double(3.3),
            "//@value2",
            "<root><a value='123.3'/><a value2='3.3'/></root>");
    }

    public void testSimpleTextAttribute2ItemsDifferentNameSelectSecondByName2_Recursive() throws Exception
    {
        assertXPath(new Double(3.3),
            "//@*[name()='value2']",
            "<root><a value='123.3'/><a value2='3.3'/></root>");
    }

    public void testSimpleTextAttribute2ItemsDifferentNameSelectSecondByLocalName_Recursive() throws Exception
    {
        assertXPath(new Double(3.3),
            "//@*[local-name()='value2']",
            "<root><a value='123.3'/><a value2='3.3'/></root>");
    }

    public void testSimpleTextAttribute2ItemsSelectFirstRecursive() throws Exception
    {
        assertXPath(new Double("123.3"),
            "//@*[position()=1]",
            "<root><a value='123.3'/><a value='3.3'/></root>");
    }

    /**
     * All processors give as result "" for position()=2 but 123.3 for
     * position()=1
     * <p>
     * The reason is that //@*[2] means
     * "all the second attribute of any element of the document" and not
     * "the second of all atttributes of the document" and since all elements
     * have only one attribute the result is "". See also note in
     * {@link #testSimpleTextAttribute2ItemsSelectSecondRecursive_ProofTest2()}.
     */
    public void testSimpleTextAttribute2ItemsSelectSecondRecursive() throws Exception
    {
        assertXPath(new String(""),
            "//@*[position()=2]",
            "<root><a value='123.3'/><a value='3.3'/></root>");
    }

    /**
     * This is a test for what said in comment to
     * {@link #testSimpleTextAttribute2ItemsSelectSecondRecursive()}.
     */
    public void testSimpleTextAttribute2ItemsSelectSecondRecursive_ProofTest() throws Exception
    {
        assertXPath(new Double(44.3),
            "//@*[position()=2]",
            "<root><a a1='123.3'/><a a1='3.3' a2='44.3'/></root>");
    }

    /**
     * This is a test for what said in comment to
     * {@link #testSimpleTextAttribute2ItemsSelectSecondRecursive()}.
     * <p>
     * Note: When there are more than one attribute the return value is the
     * string value of the first, for this reason the string value is "zz" and
     * not "zz44.3".
     * <p>
     * Note2: The selected values are 2, as can be seen by test
     * {@link #testSimpleTextAttribute2ItemsSelectSecondRecursive_ProofTest3Count()}.
     */
    public void testSimpleTextAttribute2ItemsSelectSecondRecursive_ProofTest2() throws Exception
    {
        assertXPath(new String("zz"),
            "//@*[position()=2]",
            "<root><a a1='123.3' a2='zz'/><a a1='3.3' a2='44.3'/></root>");
    }

    /**
     * There are 2 nodes, also if string value is only first attribute
     */
    public void testSimpleTextAttribute2ItemsSelectSecondRecursive_ProofTest3Count() throws Exception
    {
        assertXPathNoNodes(new Double(2),
            "count(//@*[position()=2])",
            "<root><a a1='123.3' a2='zz'/><a a1='3.3' a2='44.3'/></root>");
    }

    public void testSimpleText2ItemsSelectLast() throws Exception
    {
        assertXPath(new Double(11),
            "/root/a[position()=last()]",
            "<root><a>fds</a><a>11</a></root>");
    }

    public void testSimpleTextUnexisting() throws Exception
    {
        assertXPath(new Double(Double.NaN),
            "/unexisting",
            "<root><a>123.3</a><a>dfds</a></root>");
    }

    public void testSimpleTextUnexisting_String() throws Exception
    {
        assertXPath(new String(""),
            "/unexisting",
            "<root><a>123.3</a><a>dfds</a></root>");
    }

    public void testSimpleTextUnexisting_Full() throws Exception
    {
        ExpectedResult expected = new ExpectedResult("");
        expected.setExpectedBoolean(Boolean.FALSE);
        expected.setExpectedNodeList(NodeListImpl.EMPTY);
        expected.setExpectedNode(ExpectedResult.NULLNODE);

        assertXPath(expected,
            "/unexisting",
            "<root><a>123.3</a><a>dfds</a></root>");
    }

    public void testSimpleTextUnexisting2Levels() throws Exception
    {
        assertXPath(new Double(Double.NaN),
            "/unexisting/unexisting",
            "<root><a>123.3</a><a>dfds</a></root>");
    }

    public void testSimpleTextAttributeUnexisting() throws Exception
    {
        assertXPath(new Double(Double.NaN), "/root/@value", "<root/>");
    }

    public void testSimpleTextAttributeUnexisting2Levels() throws Exception
    {
        assertXPath(new Double(Double.NaN), "/root/a/@value", "<root/>");
    }

    // /////// mixed

    public void testSimpleMixedContent() throws Exception
    {
        assertXPath("/root", "<root>This is <a>mixed</a> content</root>");
    }

    public void testSimpleMixedContentNoSpaces() throws Exception
    {
        assertXPath("/root", "<root>This is<a>mixed</a>content</root>");
    }

    public void testSimpleMixedContentPreserveWS() throws Exception
    {
        assertXPath("/root", "<root> This is <a>mixed</a> content </root>");
    }

    // //// recursive

    public void testSimpleTextRecursiveUnexisting() throws Exception
    {
        assertXPath("", "//unexisting", "<root><a>123.3</a><a>1.3</a></root>");
        assertXPath(new Double(Double.NaN),
            "//unexisting",
            "<root><a>123.3</a><a>1.3</a></root>");

    }

    public void testSimpleTextAttributeRecursiveUnexisting() throws Exception
    {
        assertXPath("", "//@unexisting", "<root><a>123.3</a><a>1.3</a></root>");
        assertXPath(new Double(Double.NaN),
            "//@unexisting",
            "<root><a>123.3</a><a>1.3</a></root>");
    }

    public void testSimpleTextRecursive() throws Exception
    {
        assertXPath(new String("123.3"),
            "//a",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    public void testSimpleTextRecursiveWithChild() throws Exception
    {
        // this is the same as //a and of /descendant-or-self::node()/child::a
        assertXPath(new String("123.3"),
            "//child::a",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    // saxon and xalan ok
    public void testSimpleTextRecursiveWithSelf_TODO() throws Exception
    {
        assertXPath(new String("123.3"),
            "//self::a",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    // here order is wrong
    public void testSimpleTextRecursiveWithAncestorOrSelf_TODO_WRONGORDER() throws Exception
    {
        assertXPath(new String("123.3"),
            "//ancestor-or-self::a",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    public void testSimpleTextRecursiveWithAncestorOrSelf_PredicateSelectsOne() throws Exception
    {
        assertXPath(new String("123.3"),
            "//ancestor-or-self::a[text()='123.3']",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    // ok
    public void testSimpleTextRecursiveWithAncestorOrSelfNode() throws Exception
    {
        assertXPath(new String("123.3"),
            "//ancestor-or-self::a/node()",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    // here xalan and saxon return "123.3"...jxpath "ccc"
    public void testSimpleTextWithChildSelf_TODO() throws Exception
    {
        assertXPath(new String("123.3"),
            "/root/child::*/self::a",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    public void testSimpleTextWithChildSelf2_TODO() throws Exception
    {
        assertXPath(new String("123.3"),
            "/root/*/self::a",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    // This does not work ..returns "ccc"
    public void testSimpleTextWithChildSelfContext_TODO() throws Exception
    {
        assertXPath(new String("123.3"),
            "/root/child::*/self::a",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    // This works..so if there is a child context
    // that queries self is ok, is not ok when self is leaf..
    // but the [.] child context does not work, se up.
    public void testSimpleTextWithChildSelfText() throws Exception
    {
        assertXPath(new String("123.3"),
            "/root/child::*/self::a/text()",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    // This works..so if there is a child context
    // that queries self is ok, is not ok when self is leaf..
    // but the [.] child context does not work, se up.
    public void testSimpleTextWithChildSelfNode() throws Exception
    {
        assertXPath(new String("123.3"),
            "/root/child::*/self::a/node()",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    // also here jxpath returns "ccc"
    public void testSimpleTextWithChildSelfPredicate_TODO() throws Exception
    {
        assertXPath(new String("123.3"),
            "/root/child::*[self::a]",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    public void testSimpleTextWithParent() throws Exception
    {
        assertXPath(new String("ccc123.31.3"),
            "/root/a/parent::root",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    public void testSimpleTextRecursiveWithParent() throws Exception
    {
        assertXPath(new String("ccc123.31.3"),
            "//a/parent::root",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    public void testSimpleTextWithSelfNoMatch_TODO() throws Exception
    {
        assertXPath(new String(""),
            "/root/self::undefined",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    public void testSimpleTextWithChild() throws Exception
    {
        assertXPath(new String("123.3"),
            "/root/child::a",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    // here jxpath gives "ccc", xalan ok
    public void testSimpleTextWithSelf2_TODO() throws Exception
    {
        assertXPath(new String("123.3"),
            "/root/*[self::a]",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    public void testSimpleTextRecursiveWithSelf_Count() throws Exception
    {
        assertXPathNoNodes(new Double(2),
            "count(//self::a)",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    public void testSimpleTextRecursiveWithNameTest_Count() throws Exception
    {
        assertXPathNoNodes(new Double(2),
            "count(//*[name()='a'])",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    public void testSimpleTextRecursiveWithLocalNameTest_Count() throws Exception
    {
        assertXPathNoNodes(new Double(2),
            "count(//*[local-name()='a'])",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    // Here jaxen and jxpath give zero, while xalan and saxon 2
    public void testSimpleTextRecursiveWithLocalNameNamespaceTest_Count() throws Exception
    {
        assertXPathNoNodes(new Double(2),
            "count(//*[local-name()='a' and namespace-uri()=''])",
            "<root><b>s</b><a>123.3</a><a>1.3</a></root>");
    }

    // NOTE: // is short for /descendant-or-self::node()/

    public void testSimpleTextRecursiveCount_LongSyntax() throws Exception
    {
        assertXPathNoNodes(new Double(2),
            "count(/descendant-or-self::a)",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    public void testSimpleTextRecursiveCount2_LongSyntax() throws Exception
    {
        assertXPathNoNodes(new Double(2),
            "count(/descendant-or-self::node()/a)",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    public void testSimpleTextRecursiveWithSelfCount_LongSyntax() throws Exception
    {
        assertXPathNoNodes(new Double(2),
            "count(/descendant-or-self::node()/self::a)",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    public void testSimpleTextRecursiveWithChildCount_LongSyntax() throws Exception
    {
        assertXPathNoNodes(new Double(2),
            "count(/descendant-or-self::node()/child::a)",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    public void testSimpleTextRecursive_LongSyntax() throws Exception
    {
        assertXPath("123.3",
            "/descendant-or-self::a",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    // here xalan returns 123.3 while JXPath 123.31.3
    // saxon and xalan ok
    public void testSimpleTextRecursive_LongSyntax2_TODO() throws Exception
    {
        assertXPath("123.3",
            "/descendant-or-self::*[self::a]",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    // here xalan returns 123.3 while JXPath 123.31.3
    // saxon and xalan ok
    public void testSimpleTextRecursiveWithSelf_LongSyntax_TODO() throws Exception
    {
        assertXPath("123.3",
            "/descendant-or-self::node()/self::a",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    public void testSimpleTextRecursiveWithSelf_LongSyntax2_TODO() throws Exception
    {
        assertXPath("123.3",
            "//*/self::a",
            "<root><b>ccc</b><a>123.3</a><a>1.3</a></root>");
    }

    public void testSimpleTextAttributeRecursive() throws Exception
    {
        assertXPath("//@value",
            "<root><b value='3'>ccc</b><a value='123.3'/><a value='3.3'/></root>");
    }

    public void testSimpleTextRecursive2Levels() throws Exception
    {
        assertXPath("//a",
            "<root><b value='3'>ccc</b><a>123.3</a><b><a>34</a></b><a>1.3</a></root>");
    }

    public void testSimpleTextAttributeRecursive2Levels() throws Exception
    {
     // 201401 removed decimals to not introduce  precision error, tested elsewhere
        assertXPath("//@value",
            "<root><a value='123'/><c><a value='3'/></c><a value='3'/></root>");
    }

    public void testSimpleTextRecursive2LevelsSum() throws Exception
    {
        // 201401 removed decimals to not introduce  precision error, tested elsewhere
        assertXPathNoNodes("sum(//a)",
            "<root><b>ccc</b><a>123</a><b><a>34</a></b><a>1</a></root>");
    }

    // ////// namespaces

    public void testSimpleTextNS() throws Exception
    {
        assertXPath("/x:root",
            "<x:root xmlns:x='http://example.org'><a>123.3</a></x:root>");
    }

    public void testSimpleTextNSAnyCtx() throws Exception
    {
        assertXPath("/.",
            "<x:root xmlns:x='http://example.org'><a>123.3</a></x:root>");
    }

    public void testSimpleTextNSAny() throws Exception
    {
        assertXPath("/*",
            "<x:root xmlns:x='http://example.org'><a>123.3</a></x:root>");
    }

    public void testSimpleTextNSAnyX() throws Exception
    {
        assertXPath("/x:*",
            "<x:root xmlns:x='http://example.org'><a>123.3</a></x:root>");
    }

    public void testSimpleTextAttributeNS() throws Exception
    {
        assertXPath("/root/@x:value",
            "<root x:value='3' xmlns:x='http://example.org'/>");
    }

    public void testSimpleTextAttributeNS_WildcardPrefixed() throws Exception
    {
        assertXPath("/root/@x:*",
            "<root x:value='3' xmlns:x='http://example.org'/>");
    }

    public void testSimpleTextAttributeNS_WildcardPrefixed2() throws Exception
    {
        assertXPath("/root/attribute::x:*",
            "<root x:value='3' xmlns:x='http://example.org'/>");
    }

    public void testSimpleTextAttributeNS_WildcardUnprefixed() throws Exception
    {
        assertXPath("/root/@*",
            "<root x:value='3' xmlns:x='http://example.org'/>");
    }

    public void testSimpleTextAttributeNS_WildcardUnprefixed2() throws Exception
    {
        assertXPath("/root/attribute::*",
            "<root x:value='3' xmlns:x='http://example.org'/>");
    }

    public void testSimpleTextAttributeNSOnNSElement() throws Exception
    {
        assertXPath("/x:root/@x:value",
            "<x:root x:value='454' xmlns:x='http://example.org'><a>123.3</a></x:root>");
    }

    public void testSimpleTextSelectedNS() throws Exception
    {
        assertXPath("/x:root/a",
            "<x:root xmlns:x='http://example.org'><a>123.3</a></x:root>");
    }

    public void testSimpleText2ItemsNS() throws Exception
    {
        assertXPath("/x:root/a",
            "<x:root xmlns:x='http://example.org'><a>123.3</a><a>34.3</a></x:root>");
    }

    public void testSimpleText2ItemsNSSelectFirst() throws Exception
    {
        assertXPath("/x:root/a[1]",
            "<x:root xmlns:x='http://example.org'><a>123.3</a><a>34.3</a></x:root>");
    }

    public void testSimpleTextRecursiveNS() throws Exception
    {
        assertXPath("//a",
            "<x:root xmlns:x='http://example.org'><a>123.3</a><a>34.3</a></x:root>");
    }

    public void testSimpleTextRecursiveNSSelectFirst() throws Exception
    {
        assertXPath("//a[1]",
            "<x:root xmlns:x='http://example.org'><a>123.3</a><a>34.3</a></x:root>");
    }

    // ///// functions

    static final String XMLFORCOUNT_ELATTRTEXT_COMMENTS = "<x:root xmlns:x='http://example.org'><a x:ciao='34'>123.3</a><!-- com --><a attr='34.3'>34.3<!-- om --></a></x:root>";
    static final String XMLFORCOUNT_ELATTRTEXT = "<x:root xmlns:x='http://example.org'><a x:ciao='34'>123.3</a><a attr='34.3'>34.3</a></x:root>";
    static final String XMLFORCOUNT_ELATTR = "<x:root xmlns:x='http://example.org'><a x:ciao='34'></a><a attr='34.3'></a></x:root>";
    static final String XMLFORCOUNT_ELATTRCDATA = "<x:root xmlns:x='http://example.org'><a x:ciao='34'></a><a attr='34.3'><![CDATA[<unescaped>]]></a></x:root>";
    static final String XMLFORCOUNT_ELATTRTEXTADJACENTCDATA = "<x:root xmlns:x='http://example.org'><a x:ciao='34'></a><a attr='34.3'>Text<![CDATA[<adjacent cdata>]]>othertext</a></x:root>";

    public void testElAttrTextComments_SimpleRootChild() throws Exception
    {
        assertXPath("/child::node()", XMLFORCOUNT_ELATTRTEXT_COMMENTS);
    }

    public void testElAttrTextComments_SimpleRootChild2() throws Exception
    {
        assertXPath("/child::*", XMLFORCOUNT_ELATTRTEXT_COMMENTS);
    }

    public void testElAttrText_SimpleRootChild() throws Exception
    {
        assertXPath("/child::node()", XMLFORCOUNT_ELATTRTEXT);
    }

    public void testElAttrText_SimpleRootChild2() throws Exception
    {
        assertXPath("/child::*", XMLFORCOUNT_ELATTRTEXT);
    }

    public void testElAttrCDATA_SimpleRootChild() throws Exception
    {
        assertXPath("/child::node()", XMLFORCOUNT_ELATTRCDATA);
    }

    public void testElAttrCDATA_SimpleRootChild2() throws Exception
    {
        assertXPath("/child::*", XMLFORCOUNT_ELATTRCDATA);
    }

    // Here is bug of xalan..returns zero..if doing with a
    // coalescing=true doc it works ok, but that means that
    // parser is converting cdata to text so is not really surprising.
    // note: cdata() is not a valid node type, it does not exist, they are:
    // 'comment' | 'text' | 'processing-instruction' | 'node'.
    // 200807 https://issues.apache.org/jira/browse/XALANJ-2452
    public void testElAttrCDATA_CountText_Expected1_XALANBUG2452() throws Exception
    {
        assertXPathNoNodes(new Double(1),
            "count(//text())",
            XMLFORCOUNT_ELATTRCDATA);
    }

    public void testElAttrCDATA_CountText_Expected1_XALANBUG2452_fix1() throws Exception
    {
        assertXPathNoNodes(new Double(1),
            "count(//self::text())",
            XMLFORCOUNT_ELATTRCDATA);
    }

    public void testElAttrCDATA_CountText_Expected1_XALANBUG2452_fix2() throws Exception
    {
        assertXPathNoNodes(new Double(1),
            "count(//descendant-or-self::text())",
            XMLFORCOUNT_ELATTRCDATA);
    }

    public void testElAttrCDATA_CountText_Expected1_XALANBUG2452_fix3() throws Exception
    {
        assertXPathNoNodes(new Double(1),
            "count(//descendant-or-self::node()/self::text())",
            XMLFORCOUNT_ELATTRCDATA);
    }

    public void testElAttrCDATA_Text_XALANBUG2452() throws Exception
    {
        assertXPathNoNodes(new String("<unescaped>"),
            "//text()",
            XMLFORCOUNT_ELATTRCDATA);
    }

    public void testElAttrCDATA_CountElement_Expected3() throws Exception
    {
        assertXPathNoNodes(new Double(3), "count(//*)", XMLFORCOUNT_ELATTRCDATA);
    }

    public void testElAttr_CountText_Expected0() throws Exception
    {
        assertXPathNoNodes(new Double(0), "count(//text())", XMLFORCOUNT_ELATTR);
    }

    public void testElAttrText_CountText_Expected2() throws Exception
    {
        assertXPathNoNodes(new Double(2),
            "count(//text())",
            XMLFORCOUNT_ELATTRTEXT);
    }

    // Bug of JXPath, considers the cdata and text two different nodes ?
    // Now is pseudo-fixed with coaelescing=true
    // Perhaps is not a bug ?
    // https://issues.apache.org/jira/browse/XALANJ-829
    // http://lists.w3.org/Archives/Public/w3c-ietf-xmldsig/2002JanMar/0129.html
    // http://www.w3.org/TR/2002/WD-DOM-Level-3-XPath-20020208/xpath.html#TextNodes

    public void testElAttrTextAdjacentCData_CountText_TODETERMINE() throws Exception
    {
        assertXPathNoNodes(new Double(1),
            "count(//text())",
            XMLFORCOUNT_ELATTRTEXTADJACENTCDATA);
    }

    public void testCountNodes1ElAttrTextComments() throws Exception
    {
        assertXPathNoNodes("count(//.)", XMLFORCOUNT_ELATTRTEXT_COMMENTS);
    }

    public void testCountNodes1ElAttrTextComments_Syntax2() throws Exception
    {
        // "." is short for "self::node()"
        assertXPathNoNodes("count(//self::node())",
            XMLFORCOUNT_ELATTRTEXT_COMMENTS);
    }

    public void testCountNodes1ElAttrTextComments_Consistency() throws Exception
    {
        // "." is short for "self::node()"
        assertXPathNoNodes("count(//self::node())=count(//.)",
            XMLFORCOUNT_ELATTRTEXT_COMMENTS);
    }

    public void testCountNodes2ElAttrTextComments() throws Exception
    {
        assertXPathNoNodes("count(//*)", XMLFORCOUNT_ELATTRTEXT_COMMENTS);
    }

    public void testCountNodes2ElAttrTextCommentsB() throws Exception
    {
        // This is the same as testCountNodes2ElAttrTextComments
        assertXPathNoNodes("count(//./*)", XMLFORCOUNT_ELATTRTEXT_COMMENTS);
    }

    public void testCountNodes2ElAttrTextComments_Child() throws Exception
    {
        assertXPathNoNodes("count(//*/*)", XMLFORCOUNT_ELATTRTEXT_COMMENTS);
    }

    public void testCountNodes2ElAttrTextComments_ChildNode() throws Exception
    {
        assertXPathNoNodes("count(//child::node())",
            XMLFORCOUNT_ELATTRTEXT_COMMENTS);
    }

    public void testCountNodes2ElAttrTextComments_Consistency1() throws Exception
    {
        assertXPathNoNodes("count(//node())=count(//*)",
            XMLFORCOUNT_ELATTRTEXT_COMMENTS);
    }

    public void testCountNodes2ElAttrTextComments_Consistency2() throws Exception
    {
        assertXPathNoNodes("count(//child::node())=count(//*/*)",
            XMLFORCOUNT_ELATTRTEXT_COMMENTS);
    }

    public void testCountNodes2ElAttrTextComments_Consistency3() throws Exception
    {
        assertXPathNoNodes("count(//child::node())!=count(//*)",
            XMLFORCOUNT_ELATTRTEXT_COMMENTS);
    }

    public void testCountNodes3ElAttrTextComments() throws Exception
    {
        assertXPathNoNodes("count(//node())", XMLFORCOUNT_ELATTRTEXT_COMMENTS);
    }

    public void testCountNodes4ElAttrTextComments() throws Exception
    {
        assertXPathNoNodes("count(//text())", XMLFORCOUNT_ELATTRTEXT_COMMENTS);
    }

    public void testCountNodes1ElAttrText() throws Exception
    {
        assertXPathNoNodes("count(//.)", XMLFORCOUNT_ELATTRTEXT);
    }

    public void testCountNodes1ElAttrText_Syntax2() throws Exception
    {
        assertXPathNoNodes("count(//self::node())", XMLFORCOUNT_ELATTRTEXT);
    }

    public void testCountNodes2ElAttrText() throws Exception
    {
        assertXPathNoNodes("count(//*)", XMLFORCOUNT_ELATTRTEXT);
    }

    public void testCountNodes3ElAttrText() throws Exception
    {
        assertXPathNoNodes("count(//node())", XMLFORCOUNT_ELATTRTEXT);
    }

    public void testCountNodes4ElAttrText() throws Exception
    {
        assertXPathNoNodes("count(//text())", XMLFORCOUNT_ELATTRTEXT);
    }

    // /////////

    public void testCountNodes1OnlyElAttr() throws Exception
    {
        assertXPathNoNodes("count(//.)", XMLFORCOUNT_ELATTR);
    }

    public void testCountNodes1OnlyElAttr_Syntax2() throws Exception
    {
        assertXPathNoNodes("count(//self::node())", XMLFORCOUNT_ELATTR);
    }

    public void testCountNodes2OnlyElAttr() throws Exception
    {
        assertXPathNoNodes("count(//*)", XMLFORCOUNT_ELATTR);
    }

    public void testCountNodes2OnlyElAttr_Prefixed() throws Exception
    {
        assertXPathNoNodes("count(//x:*)", XMLFORCOUNT_ELATTR);
    }

    public void testCountNodes3OnlyElAttr() throws Exception
    {
        assertXPathNoNodes("count(//node())", XMLFORCOUNT_ELATTR);
    }

    public void testCountNodes4OnlyElAttr() throws Exception
    {
        assertXPathNoNodes("count(//text())", XMLFORCOUNT_ELATTR);
    }

    // ///////////////

    public void testCountAttributes1NS1AbsentNS() throws Exception
    {
        assertXPathNoNodes("count(//@*)",
            "<x:root xmlns:x='http://example.org'><a x:ciao='34'>123.3</a><a attr='34.3'>34.3</a></x:root>");
    }

    public void testCountAttributes2NS() throws Exception
    {
        assertXPathNoNodes("count(//@*)",
            "<x:root xmlns:x='http://example.org'><a x:ciao='34'>123.3</a><a x:attr='34.3'>34.3</a></x:root>");
    }

    public void testCountAttributes2NS_UsePrefix() throws Exception
    {
        assertXPathNoNodes("count(//@x:*)",
            "<x:root xmlns:x='http://example.org'><a x:ciao='34'>123.3</a><a x:attr='34.3'>34.3</a></x:root>");
    }

    public void testCountAttributes2AbsentNS() throws Exception
    {
        assertXPathNoNodes("count(//@*)",
            "<x:root xmlns:x='http://example.org'><a ciao='34'>123.3</a><a attr='34.3'>34.3</a></x:root>");
    }

    public void testCountAttributes2AbsentNSNested() throws Exception
    {
        assertXPathNoNodes("count(//@*)",
            "<x:root xmlns:x='http://example.org'><a ciao='34'>123.3</a><c><a c='c'/></c><a attr='34.3'>34.3</a></x:root>");
    }

    public void testCountAttributes2AbsentNSNestedNS() throws Exception
    {
        assertXPathNoNodes("count(//@*)",
            "<x:root xmlns:x='http://example.org'><a ciao='34'>123.3</a><x:c><a c='c'/></x:c><a attr='34.3'>34.3</a></x:root>");
    }

    public void testCountAttributesXmlLang() throws Exception
    {
        assertXPathNoNodes("count(//@*)", "<root xml:lang='it'/>");
    }

    public void testCountAttributesXmlLang_UsePrefix() throws Exception
    {
        assertXPathNoNodes("count(//@xml:*)", "<root xml:lang='it'/>");
    }

    public void testGetAttributeXmlLang() throws Exception
    {
        assertXPathNoNodes("/root/@xml:lang", "<root xml:lang='it'/>");
    }

    public void testGetUnexistingAttributeXmlLang() throws Exception
    {
        assertXPathNoNodes("/root/@xml:lang", "<root/>");
    }

    public void testGetAttributeXmlSpace() throws Exception
    {
        assertXPathNoNodes("/root/@xml:space", "<root xml:space='default'/>");
    }

    public void testGetUnexistingAttributeXmlSpace() throws Exception
    {
        assertXPathNoNodes("/root/@xml:space", "<root/>");
    }

    public void testGetUnexistingAttributeXmlUnexisting() throws Exception
    {
        assertXPathNoNodes("/root/@xml:unexisting", "<root/>");
    }

    public void testPrecedingAxis() throws Exception
    {
        assertXPathNoNodes(new Double(3),
            "count(//preceding::node())",
            "<root><a1><b1/><b2/></a1><a2/></root>");
    }

    public void testCountText() throws Exception
    {
        assertXPathNoNodes("count(//text())",
            "<x:root xmlns:x='http://example.org'><a x:ciao='34'>123.3</a><a attr='34.3'>34.3</a></x:root>");
    }

    public void testCountCtxText() throws Exception
    {
        assertXPathNoNodes("count(.//text())",
            "<x:root xmlns:x='http://example.org'><a x:ciao='34'>123.3</a><a attr='34.3'>34.3</a></x:root>");
    }

    public void testCountComments() throws Exception
    {
        assertXPathNoNodes("count(//comment())",
            "<x:root xmlns:x='http://example.org'><a x:ciao='34'>123.3</a><!-- com --><a attr='34.3'>34.3<!-- om --></a></x:root>");
    }

    public void testNumberLiteral() throws Exception
    {
        assertXPathNoNodes(new Double(2.7), "2.7", "<root/>");
    }

    public void testNumberLiteralNoDecimal() throws Exception
    {
        assertXPathNoNodes(new Double(2), "2", "<root/>");
    }

    public void testNumberLiteralMinus() throws Exception
    {
        assertXPathNoNodes(new Double(-2.3), "-2.3", "<root/>");
    }

    public void testNumberLiteralPlus_Invalid() throws Exception
    {
        assertXPathFails("+2.3", "<root/>"); // invalid
    }
    public void testNumberLiteralMess_Invalid() throws Exception
    {
        assertXPathFails("+*.3", "<root/>"); // invalid
    }

    public void testNumberOfRoot() throws Exception
    {
        assertXPathNoNodes(new Double(3), "number(/root)", "<root>3</root>"); // invalid
    }

    public void testNumberOfNumberWithTrailingSpaces() throws Exception
    {
        assertXPathNoNodes(new Double(3.2), "number(' 3.2 ')", "<root/>");
    }

    public void testNegativeIndex2013() throws Exception
    {
        // FIXED with patch 383726
        assertXPath("", "/root/el[-2]", "<root><el>ciao</el></root>");
    }

    public void testNegativeIndex2013_TwoNodes() throws Exception
    {
        // FIXED with patch 383726
        assertXPath("", "/root/el[-3]", "<root><el>ciao</el><el>2</el></root>");
    }

    public void testNegativeIndex2013_TwoNodes_IndexHighIsAlreadyOk() throws Exception
    {
        // already FIXED, not changing with patch 383726
        assertXPath("", "/root/el[-40]", "<root><el>ciao</el><el>2</el></root>");
    }

    public void testSimpleSum() throws Exception
    {
        assertXPathNoNodes("1+2", "<root/>");
    }

    public void testSimpleSumOneDecimal() throws Exception
    {
        assertXPathNoNodes("1+2.3", "<root/>");
    }

    public void testSimpleProduct() throws Exception
    {
        assertXPathNoNodes("4*2", "<root/>");
    }

    public void testSimpleProductOneDecimal() throws Exception
    {
        assertXPathNoNodes("4*2.3", "<root/>");
    }

    public void testSimpleProductAsNumber() throws Exception
    {
        assertXPathNoNodes("number(4*2)", "<root/>");
    }

    public void testSimpleProductAsNumberOneDecimal() throws Exception
    {
        assertXPathNoNodes("number(4*2.3)", "<root/>");
    }

    public void testFunctionString() throws Exception
    {
        assertXPathNoNodes("string('4')", "<root/>");
    }

    public void testFunctionStringOfTrue() throws Exception
    {
        assertXPathNoNodes("string(true())", "<root/>");
    }

    public void testFunctionStringOfFalse() throws Exception
    {
        assertXPathNoNodes("string(false())", "<root/>");
    }

    public void testFunctionStringOfRootEmpty() throws Exception
    {
        assertXPathNoNodes("string(/root)", "<root/>");
    }

    public void testFunctionStringOfRoot1ChildWithText() throws Exception
    {
        assertXPathNoNodes("string(/root)", "<root><a>dfg</a></root>");
    }

    public void testFunctionStringOfRoot2ChildWithText() throws Exception
    {
        assertXPathNoNodes("string(/root)", "<root><a>dfs</a><a>dfg</a></root>");
    }

    // Issue Compatibility
    public void testFunctionStringOfSimpleTextRecursiveNS() throws Exception
    {
        // Saxon if not in compatibility mode throws exceptionn
        // net.sf.saxon.trans.XPathException: A sequence of more than one
        // item is not allowed as the first argument of
        // string() (<a/>, <a/>)
        assertXPathNoNodes("string(//a)",
            "<x:root xmlns:x='http://example.org'><a>123.3</a><a>34.3</a></x:root>");
    }

    // Issue Compatibility
    public void testFunctionStringOfSimpleTextRecursiveNS_Compatible() throws Exception
    {
        // I suppose in xpath2 one should write with [1] index.
        assertXPathNoNodes("string(//a[1])",
            "<x:root xmlns:x='http://example.org'><a>123.3</a><a>34.3</a></x:root>");
    }

    public void testFunctionNumber() throws Exception
    {
        assertXPathNoNodes("number('4')", "<root/>");
    }

    public void testFunctionNumberInvalid() throws Exception
    {
        assertXPathNoNodes("number('ddsfgsdg')", "<root/>");
    }

    public void testFunctionNumberWith1Decimal() throws Exception
    {
        assertXPathNoNodes("number('4.3')", "<root/>");
    }

    public void testFunctionNumberWith2Decimals() throws Exception
    {
        assertXPathNoNodes("number('4.34')", "<root/>");
    }

    public void testFunctionNumberOfSimpleTextRecursiveNS() throws Exception
    {
        // Saxon if not in compatibility mode throws exceptionn
        // net.sf.saxon.trans.XPathException: A sequence of more than one
        // item is not allowed as the first argument of
        // string() (<a/>, <a/>)
        assertXPathNoNodes("number(//a)",
            "<x:root xmlns:x='http://example.org'><a>123.3</a><a>34.3</a></x:root>");
    }

    public void testNumberPrecision_CheckJava() throws Exception
    {
        // 3.2 - 4 == -0.7999999999999999
        // not -0.8
        assertFalse(new Double(-0.8).equals(new Double(3.2d - 4d)));
    }

    // here xalan fails for xpath "3.2-4"
    public void testNumberPrecisionInJunit_XalanFailsPerhapsBug() throws Exception
    {
        try
        {
            assertXPathNoNodes(new Double(-0.8), "3.2-4", "<root/>");
            if (ISSUE_DOUBLE_PRECISION) fail("Precision OK but no setting.");
        }
        catch (AssertionFailedError e)
        {
            if (ISSUE_DOUBLE_PRECISION == false)
                fail("Should have not failed");
        }
        assertXPathNoNodes(new Double(3.2 - 4), "3.2-4", "<root/>");

    }

    public void testNumberPrecisionInJunit2() throws Exception
    {
        try
        {
            assertXPathNoNodes(new Double(-0.8), "number(3.2)-4", "<root/>");
            if (ISSUE_DOUBLE_PRECISION) fail("Precision OK but no setting.");
        }
        catch (AssertionFailedError e)
        {
            if (ISSUE_DOUBLE_PRECISION == false)
                fail("Should have not failed");
        }
        assertXPathNoNodes(new Double(3.2 - 4), "number(3.2)-4", "<root/>");

    }

    // this does not work in Xalan, following yes
    public void testXPathStdSubtraction_XalanParseNumberProblem() throws Exception
    {
        assertXPathNoNodes(Boolean.TRUE, "3.1-3.1=0", "<root/>");
    }

    public void testXPathStdSubtraction_XalanParseNumberOk() throws Exception
    {
        assertXPathNoNodes(Boolean.TRUE, "number(3.1)-3.1=0", "<root/>");
    }

    public void testNumberPrecisionInXPath() throws Exception
    {
        try
        {
            assertXPathNoNodes(Boolean.TRUE,
                "number(3.2)-number(4)=number(-0.8)",
                "<root/>");
        }
        catch (AssertionFailedError e)
        {
            if (ISSUE_DOUBLE_PRECISION == false)
                fail("Should have not failed");
        }
        assertXPathNoNodes(Boolean.TRUE,
            "number(3.2)-number(4)=number(3.2)-number(4)",
            "<root/>");

    }

    public void testFunctionBooleanEmptyString() throws Exception
    {
        assertXPathNoNodes("boolean('')", "<root/>");
    }

    public void testFunctionBooleanNotEmptyString() throws Exception
    {
        assertXPathNoNodes("boolean('dfsf')", "<root/>");
    }

    public void testFunctionBooleanOfEmptyRoot() throws Exception
    {
        assertXPathNoNodes("boolean(/root)", "<root/>");
    }

    public void testFunctionBooleanOfFilledRoot() throws Exception
    {
        assertXPathNoNodes("boolean(/root)", "<root>ffd</root>");
    }

    public void testFunctionTrue() throws Exception
    {
        assertXPathNoNodes("true()", "<root/>");
    }

    public void testFunctionFalse() throws Exception
    {
        assertXPathNoNodes("false()", "<root/>");
    }

    public void testFunctionNot() throws Exception
    {
        assertXPathNoNodes("not(false())", "<root/>");
    }

    public void testFunctionFalseMultTrue_Compatibility() throws Exception
    {
        // Saxon if not in compatibility mode throws exceptionn
        // because not defined arithmetic operators for booleans
        assertXPathNoNodes("false() * true()", "<root/>");
    }

    // /////// failures

    public void testInvalid_Unparsable() throws Exception
    {
        assertXPathFails("/root", "<root>"); // unparsable xml
    }

    public void testInvalid_Unbalanced() throws Exception
    {
        assertXPathFails("//root[1/a", "<root/>"); // invalid
    }

    public void testInvalid_DotBegin() throws Exception
    {
        assertXPathFails("//.root/a", "<root/>"); // invalid
    }

    public void testInvalid_Dollar() throws Exception
    {
        assertXPathFails("//a$root/a", "<root/>"); // invalid
    }

    public void testInvalid_Empty() throws Exception
    {
        assertXPathFails("", "<root/>"); // invalid
    }

    public void testInvalid_DoubleColon() throws Exception
    {
        assertXPathFails("a::b", "<root/>"); // invalid
    }

    // Xalan does not fail, I think it should..
    public void testInvalid_DoubleColon2_XALANBUG() throws Exception
    {
        assertXPathFails("a:b:c", "<root/>"); // invalid
    }

    public void testInvalid_UnexistingFunction() throws Exception
    {
        assertXPathFails("unexisting()", "<root/>"); // invalid
    }

    public void testFunctionSquare() throws Exception
    {
        assertXPathNoNodes(new Double(64), "x:square(8)", "<root/>");
    }

    public void testFunctionSquareDecimals() throws Exception
    {
        // This fails in most since checks also STRING result
        // not only DOUBLE, next test testFunctionSquareDecimals2
        // shows it more specifically
        assertXPathNoNodes(new Double(1.1d * 1.1d), "x:square(1.1)", "<root/>");
    }
    public void testFunctionSquareDecimals2() throws Exception
    {
        // This asks string
        // This only SAXON does not fail
        assertXPathNoNodes("1.21", "x:square(1.1)", "<root/>");
    }
    public void testFunctionSquareDecimals3_Java() throws Exception
    {
        // Just to check
        assertFalse(new Double(1.1d * 1.1d).equals(new Double(1.21)));
    }
    
    

    public void testFunctionSquareStringInvalid() throws Exception
    {
        assertXPathFails("x:square('ghfh')", "<root/>");
    }

    public void testFunctionInvert() throws Exception
    {
        assertXPathNoNodes("oaic", "x:invert('ciao')", "<root/>");
    }

    public void testFunctionInvert_SecureProcessing() throws Exception
    {
        assertXPathFailsSecureProcessing("x:invert('ciao')", "<root/>");
    }

    public void testFunctionInvertOnNode() throws Exception
    {
        assertXPathNoNodes("oaic", "x:invert(/root)", "<root>ciao</root>");
    }

    public void testFunctionInvertOnNodeText() throws Exception
    {
        assertXPathNoNodes("oaic",
            "x:invert(/root/text())",
            "<root>ciao</root>");
    }

    public void testFunctionInvertOnAttribute() throws Exception
    {
        assertXPathNoNodes("oaic", "x:invert(/root/@foo)", "<root foo='ciao'/>");
    }

    public void testFunctionInvertWithNumber() throws Exception
    {
        assertXPathNoNodes("5.43", "x:invert(34.5)", "<root/>");
    }

    public void testFunctionInvertNoNS_Invalid() throws Exception
    {
        assertXPathFails("invert('cba')", "<root/>");
    }

    public void testFunctionInvertTwoArgs_Invalid() throws Exception
    {
        assertXPathFails("invert('cba', 'cba')", "<root/>");
    }

    public void testFunctionConcatTwoArgs() throws Exception
    {
        assertXPathNoNodes("cbacba", "x:concat('cba', 'cba')", "<root/>");
    }

    public void testFunctionConcatFourArgs() throws Exception
    {
        assertXPathNoNodes("cbacbazz",
            "x:concat('cb', 'ac', 'ba', 'zz')",
            "<root/>");
    }

    public void testInvalid_UnexistingVariable() throws Exception
    {
        assertXPathFails("$unexisting", "<root/>"); // invalid
    }
    public void testVariableUnoString() throws Exception
    {
        assertXPathNoNodes("1",
            "$unoString",
            "<root/>");
    }
    public void testVariableUnoDouble() throws Exception
    {
        // This fails in Jaxen and JXPath with a NaN ?!?
        // AH was because was not double but Integer 
        // TODO ? However Xalan and Saxon are fixing it 
        assertXPathNoNodes(new Double("1"),
            "$unoDouble",
            "<root/>");
    }
    public void testVariableAsIndex_InitialChecks() throws Exception
    {
        // Setup check
        assertXPath("ciao", "/root/el[1]", "<root><a/><el>ciao</el><el>2</el></root>");
        assertXPath("2", "/root/el[2]", "<root><a/><el>ciao</el><el>2</el></root>");
        assertXPath("2", "/root/el[position()=2]", "<root><a/><el>ciao</el><el>2</el></root>");
        assertXPath("ciao", "/root/el['dfsddfsdf']", "<root><a/><el>ciao</el><el>2</el></root>");
        assertXPath("", "/root/el['']", "<root><a/><el>ciao</el><el>2</el></root>");
          }
    public void testVariableAsIndex_InitialChecks_PositionEqualString() throws Exception
    {
        // Hmm.. so for all position()='2' is the second one
        // I think position()='2' works because
        // here is position to be converted to string for comparison
        // See later on testVariableStringAsIndexTwoUsePosition
        assertXPath("2", "/root/el[position()='2']", "<root><a/><el>ciao</el><el>2</el></root>");
    }
    public void testVariableAsIndex() throws Exception
    {
        // Works in all !!
        assertXPath("ciao", "/root/el[$unoDouble]", "<root><a/><el>ciao</el><el>2</el></root>");
    }
    public void testVariableAsIndexSum() throws Exception
    {
        // Works in all !!
        assertXPath("2", "/root/el[$unoDouble+1]", "<root><a/><el>ciao</el><el>2</el></root>");
    }
    public void testVariableAsIndexTwo() throws Exception
    {
     // Works in all !!
        assertXPath("2", "/root/el[$dueDouble]", "<root><a/><el>ciao</el><el>2</el></root>");
    }
    
    public void testVariableStringAsIndex() throws Exception
    {
        // Works in all !!
        assertXPath("ciao", "/root/el[$unoString]", "<root><a/><el>ciao</el><el>2</el></root>");
    }
    public void testVariableStringAsIndexTwo() throws Exception
    {
        // Works in all with non-empty-string considered always a match  
        // (should be correct one) so first node included,
        // if many nodes selected = all nodes
        assertXPath("ciao", "/root/el[$dueString]", "<root><a/><el>ciao</el><el>2</el></root>");
    }
    public void testVariableStringAsIndexTwoUsePosition() throws Exception
    {
        // All understand this,
        // like they understand position()='2' that is the 
        // same but without variable.
        assertXPath("2", "/root/el[position()=$dueString]", "<root><a/><el>ciao</el><el>2</el></root>");
    }
    
    public void testNotEmptyStringAsIndexSelectsAllNodes() throws Exception
    {
     // Works in all !!
        // a string index not empty means all nodes
        assertXPath( "/root/el['dd']", "<root><a/><el>ciao</el><el>2</el></root>");
        
    }
    public void testNotEmptyStringAsIndexSelectsAllNodes2() throws Exception
    {
     // Works in all !!
     // a string index not empty means all nodes
        assertXPathNodelistSize(2, "/root/el['ss']", "<root><a/><el>ciao</el><el>2</el></root>");
        
    }
    public void testNotEmptyStringAsIndexSelectsNoNodes() throws Exception
    {
     // Works in all !!
     // a string index  empty means no nodes
        assertXPathNodelistSize(0, "/root/el['']", "<root><a/><el>ciao</el><el>2</el></root>");
        
    }
    
    
    public void testVariableStringAsIndexTwoAsNumber() throws Exception
    {
        // Works in all !!
        assertXPath("2", "/root/el[number($dueString)]", "<root><a/><el>ciao</el><el>2</el></root>");
    }
    public void testVariableStringAsIndexSum() throws Exception
    {
        // Works in all !!
        assertXPath("2", "/root/el[$unoString+1]", "<root><a/><el>ciao</el><el>2</el></root>");
    }
    
    ////////////
    
    /**
     * See https://github.com/seeburger-ag/commons-jxpath/commit/9695d635437f60a68f8e289db652e8643eb44976
     * @throws Exception 
     */
    public void testEmptyInnerNs() throws Exception
    {
        // works also without any mod in JXPath ?
        // ah must call asPath()..
        String xml = "<b:foo xmlns:b=\"bla\" xmlns=\"test111\"><b:bar>a</b:bar><test xmlns=\"\">x</test></b:foo>";
        assertXPath("x", "b:foo/test", xml);
        assertXPath("x", "/b:foo[1]/test[1]",xml);
    }


    // /////// TESTS FROM ISSUE XALANJ 172
    // /////// https://issues.apache.org/jira/browse/XALANJ-172

    private static String XML_XALANJ_172 = "<?xml version='1.0'?>" + "\n<?spi some_pi ?> <!-- Non-functional pi -->"
        + "\n<doc-element xmlns:xxx='http://www.bogus.com' "
        + "\nxmlns:xsl='http://www.w3.org/1999/XSL/Transform'>"
        + "\n<xxx:output encoding='utf-8' indent='yes'/>"
        + "\n<outer>"
        + "\n<foo name='bar' select='what'>"
        + "\n<xxx:param/>"
        + "\n<xxx:with-param name='Column' select='0'/>"
        + "\n<?api another_pi ?> <!-- Another non-functional pi -->"
        + "\n</foo>"
        + "\n</outer>"
        + "\n</doc-element>";

    private void assert172(String xpath) throws Exception
    {
        assertXPathNoNodes(xpath, XML_XALANJ_172);
    }

    public void testIssue172_CountNode200911() throws Exception
    {
        assert172("count(ancestor-or-self::node())");
        assert172("count(descendant-or-self::*)");
        // [//] is short for [descendant-or-self::node()]
        // [//para] is short for [descendant-or-self::node()/para]
        assert172("count(descendant-or-self::node()/*)");
        assert172("count(//*)");
        assert172("count(descendant::*)");

        // [.] is short for [self::node()]
        assert172("count(//.)");
        assert172("count(descendant-or-self::node()/self::node())");

        assert172("count(//node())");

    }

    // <xsl:value-of select="count(//node())"/> "//node()" :

    public void testIssue172_CountNode() throws Exception
    {
        assert172("count(//node())");
    }

    // <xsl:value-of select="count(//self::node())"/> "//self::node()" :
    public void testIssue172_CountSelfNode() throws Exception
    {
        assert172("count(//self::node())");
    }

    // <xsl:value-of select="count(//child::node())"/> "//child::node()" :
    public void testIssue172_CountChildNode() throws Exception
    {
        assert172("count(//child::node())");
    }

    // <xsl:value-of select="count(//child::node()/self::node())"/>
    // "//child::node()/self::node()" :
    public void testIssue172_CountChildNodeSelfNode() throws Exception
    {
        assert172("count(//child::node()/self::node())");
    }

    // <xsl:value-of select="count(//ancestor::node())"/> "//ancestor::node()" :
    public void testIssue172_CountAncestorNode() throws Exception
    {
        assert172("count(//ancestor::node())");
    }

    // <xsl:value-of select="count(//ancestor-or-self::node())"/>
    // "//ancestor-or-self::node()" :
    public void testIssue172_CountAncestorOrSelfNode() throws Exception
    {
        assert172("count(//ancestor-or-self::node())");
    }

    // <xsl:value-of select="count(//descendant::node())"/>
    // "//descendant::node()" :
    public void testIssue172_CountDescendantNode() throws Exception
    {
        assert172("count(//descendant::node())");
    }

    // <xsl:value-of select="count(//descendant-or-self::node())"/>
    // "//dos::node()" :
    public void testIssue172_CountDescendantOrSelfNode() throws Exception
    {
        assert172("count(//descendant-or-self::node())");
    }

    // <xsl:value-of select="count(//following::node())"/> "//follow::node()" :
    public void testIssue172_CountFollowingNode() throws Exception
    {
        assert172("count(//following::node())");
    }

    // <xsl:value-of select="count(//following-sibling::node())"/>
    // "//follow-sib::node()" :
    public void testIssue172_CountFollowingSiblingNode() throws Exception
    {
        assert172("count(//following-sibling::node())");
    }

    // here xalan 16, jxpath and saxon 18.
    // <xsl:value-of select="count(//preceding::node())"/> "//preceding::node()"
    // :
    public void testIssue172_CountPrecedingNode_XALANBUG() throws Exception
    {
        assert172("count(//preceding::node())");
    }

    // <xsl:value-of select="count(//preceding-sibling::node())"/>
    // "//preceding-sib::node()" :
    public void testIssue172_CountPrecedingSiblingNode() throws Exception
    {
        assert172("count(//preceding-sibling::node())");
    }

    // <xsl:value-of select="count(//attribute::node())"/> "//attribute::node()"
    // :
    public void testIssue172_CountAttributeNode() throws Exception
    {
        assert172("count(//attribute::node())");
    }

    // <xsl:value-of select="count(//parent::node())"/> "//parent::node()" :
    public void testIssue172_CountParentNode() throws Exception
    {
        assert172("count(//parent::node())");
    }

    // <xsl:value-of select="count(//.)"/> "//." :
    public void testIssue172_CountNode_WrittenAsPoint() throws Exception
    {
        assert172("count(//.)");
    }

    // <xsl:value-of select="count(//text())"/> text nodes,
    public void testIssue172_CountText() throws Exception
    {
        assert172("count(//text())");
    }

    // <xsl:value-of select="count(//comment())"/> comment nodes,
    public void testIssue172_CountComment() throws Exception
    {
        assert172("count(//comment())");
    }

    // <xsl:value-of select="count(//processing-instruction())"/> pi nodes,
    public void testIssue172_CountPI() throws Exception
    {
        assert172("count(//processing-instruction())");
    }

    // <xsl:value-of select="count(//*/@*)"/> attributes.
    public void testIssue172_CountAttributes() throws Exception
    {
        assert172("count(//*/@*)");
    }

    public void testIssue172_CountAttributes2() throws Exception
    {
        assert172("count(//@*)");
    }

    // //////////////////////////////////

    // https://issues.apache.org/jira/browse/JXPATH-114
    public void testForIssue114() throws Exception
    {
        assertXPathNoNodes("count(//node())",
            "<root><a foo='34'>123.3</a><!-- com --><a attr='34.3'>34.3<!-- om --></a></root>");
    }

    public void testForIssue113() throws Exception
    {
        assertXPathNoNodes("count(//following-sibling::node())",
            "<root><a foo='34'>123.3</a><!-- com --><a attr='34.3'>34.3<!-- om --></a></root>");
    }

    public void testForIssue113Simpler() throws Exception
    {
        assertXPath("//following-sibling::node()", "<root/>");
    }

    public void testForIssue115() throws Exception
    {
        assertXPath("//attribute::node()", "<xml foo='bar'/>");
    }

}
