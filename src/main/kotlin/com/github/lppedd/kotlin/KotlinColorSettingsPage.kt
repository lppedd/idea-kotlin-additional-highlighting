package com.github.lppedd.kotlin

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import org.jetbrains.kotlin.idea.KotlinIcons
import org.jetbrains.kotlin.idea.highlighter.KotlinHighlighter
import javax.swing.Icon

/**
 * @author Edoardo Luppi
 */
private class KotlinColorSettingsPage : ColorSettingsPage {
  override fun getHighlighter(): SyntaxHighlighter =
    KotlinHighlighter()

  override fun getDisplayName(): String =
    "Kotlin (additional)"

  override fun getIcon(): Icon =
    KotlinIcons.SMALL_LOGO

  override fun getAttributeDescriptors(): Array<AttributesDescriptor> =
    arrayOf(
      AttributesDescriptor("Operation//Infix function call", KOTLIN_INFIX_FUN),
      AttributesDescriptor("Operation//Keyword operator function call", KOTLIN_KEYWORD_OPERATOR_FUN),
      AttributesDescriptor("Operation//Math operator function call", KOTLIN_MATH_OPERATOR_FUN),

      AttributesDescriptor("Keywords//override", KOTLIN_MODIFIER_OVERRIDE),
      AttributesDescriptor("Keywords//public private protected internal inner", KOTLIN_MODIFIER),
      AttributesDescriptor("Keywords//sealed open", KOTLIN_SEALED_OPEN),
      AttributesDescriptor("Keywords//companion", KOTLIN_COMPANION),
      AttributesDescriptor("Keywords//data", KOTLIN_DATA),
      AttributesDescriptor("Keywords//operator infix", KOTLIN_OPERATOR_INFIX),
      AttributesDescriptor("Keywords//this super", KOTLIN_THIS_SUPER),
      AttributesDescriptor("Keywords//null", KOTLIN_NULL),
      AttributesDescriptor("Keywords//true false", KOTLIN_TRUE_FALSE),
      AttributesDescriptor("Keywords//abstract", KOTLIN_ABSTRACT),
      AttributesDescriptor("Keywords//suspend", KOTLIN_SUSPEND),
    )

  override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey> =
    mapOf(
      "kt-infix" to KOTLIN_INFIX_FUN,
      "kt-operator" to KOTLIN_KEYWORD_OPERATOR_FUN,
      "kt-math-operator" to KOTLIN_MATH_OPERATOR_FUN,

      "kt-kw-override" to KOTLIN_MODIFIER_OVERRIDE,
      "kt-kw-modifier" to KOTLIN_MODIFIER,
      "kt-kw-sealed" to KOTLIN_SEALED_OPEN,
      "kt-kw-open" to KOTLIN_SEALED_OPEN,
      "kt-kw-companion" to KOTLIN_COMPANION,
      "kt-kw-data" to KOTLIN_DATA,
      "kt-kw-operator" to KOTLIN_OPERATOR_INFIX,
      "kt-kw-infix" to KOTLIN_OPERATOR_INFIX,
      "kt-kw-this" to KOTLIN_THIS_SUPER,
      "kt-kw-super" to KOTLIN_THIS_SUPER,
      "kt-kw-null" to KOTLIN_NULL,
      "kt-kw-true" to KOTLIN_TRUE_FALSE,
      "kt-kw-false" to KOTLIN_TRUE_FALSE,
      "kt-kw-abstract" to KOTLIN_ABSTRACT,
      "kt-kw-suspend" to KOTLIN_SUSPEND,
    )

  override fun getDemoText(): String = """
        |import java.util.*
        |
        |package myPackage.hello
        |
        |<kt-kw-open>open</kt-kw-open> class MyClass {
        |    <kt-kw-modifier>private</kt-kw-modifier> val fooBar = "";
        |
        |    <kt-kw-modifier>protected</kt-kw-modifier> <kt-kw-open>open</kt-kw-open> fun foo(): Unit? {
        |        val values = listOf(<kt-kw-true>true</kt-kw-true>, <kt-kw-null>null</kt-kw-null>, <kt-kw-false>false</kt-kw-false>)
        |        println(values)
        |        return <kt-kw-null>null</kt-kw-null>
        |    }
        |
        |    <kt-kw-modifier>internal</kt-kw-modifier> fun bar() {
        |        return Unit
        |    }
        |}
        |
        |<kt-kw-sealed>sealed</kt-kw-sealed> class MySealedClass : MyClass() {
        |    <kt-kw-override>override</kt-kw-override> fun foo(): Unit? {
        |        println("override")
        |        <kt-kw-super>super</kt-kw-super>.foo()
        |        return null
        |    }
        |
        |    <kt-kw-companion>companion</kt-kw-companion> object {
        |        fun create(): MyClass {
        |            return MyClass()
        |        }
        |    }
        |}
        |
        |<kt-kw-data>data</kt-kw-data> class MyDataClass(val num: Int) {
        |    <kt-kw-modifier>public</kt-kw-modifier> <kt-kw-operator>operator</kt-kw-operator> <kt-kw-infix>infix</kt-kw-infix> fun plus(to: Int) {
        |        return <kt-kw-this>this</kt-kw-this> + to
        |    }
        |}
        |
        |<kt-kw-override>override</kt-kw-override> fun myFunction(myValue: String, myValues: List<String>) {
        |    if (myValue <kt-infix>owns</kt-infix> 'c') { }
        |    if (myValue <kt-operator>!in</kt-operator> myValues) { }
        |    if (myValue <kt-math-operator>></kt-math-operator> myValues.first()) { }
        |}
        |
        |<kt-kw-abstract>abstract</kt-kw-abstract> class AbstractClass {
        |   abstract val text: Int
        |}
        |
        |<kt-kw-modifier>inner</kt-kw-modifier> class InnerClass {}
        |
        |<kt-kw-suspend>suspend</kt-kw-suspend> fun suspendCall() = suspendFun()
        |
        |<kt-kw-suspend>suspend</kt-kw-suspend> fun suspendFun() {}
        |
        |<kt-kw-infix>infix</kt-kw-infix> fun String.owns(char: Char): Boolean = contains(char)
    """.trimMargin()

  override fun getColorDescriptors(): Array<ColorDescriptor> =
    ColorDescriptor.EMPTY_ARRAY
}