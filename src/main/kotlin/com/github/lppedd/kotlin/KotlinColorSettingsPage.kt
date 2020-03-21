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
      AttributesDescriptor("Infix function call", KOTLIN_INFIX_FUN),
      AttributesDescriptor("Keyword operator function call", KOTLIN_KEYWORD_OPERATOR_FUN),
      AttributesDescriptor("Math operator function call", KOTLIN_MATH_OPERATOR_FUN),
    )

  override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey> =
    mapOf(
      "k-infix" to KOTLIN_INFIX_FUN,
      "k-kw-operator" to KOTLIN_KEYWORD_OPERATOR_FUN,
      "k-math-operator" to KOTLIN_MATH_OPERATOR_FUN,
    )

  override fun getDemoText(): String = """
        |fun myFunction(myValue: String, myValues: List<String>) {
        |    if (myValue <k-infix>owns</k-infix> 'c') { }
        |    if (myValue <k-kw-operator>!in</k-kw-operator> myValues) { }
        |    if (myValue <k-math-operator>></k-math-operator> myValues.first()) { }
        |}
        |
        |infix fun String.owns(char: Char): Boolean = contains(char)
    """.trimMargin()

  override fun getColorDescriptors(): Array<ColorDescriptor> =
    ColorDescriptor.EMPTY_ARRAY
}
