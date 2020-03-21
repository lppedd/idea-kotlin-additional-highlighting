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
    "Kotlin - additional"

  override fun getIcon(): Icon =
    KotlinIcons.SMALL_LOGO

  override fun getAttributeDescriptors(): Array<AttributesDescriptor> =
    arrayOf(
      AttributesDescriptor("Infix function call", KOTLIN_INFIX_FUN),
      AttributesDescriptor("Operator function call", KOTLIN_OPERATOR_FUN)
    )

  override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey> =
    mapOf(
      "infix" to KOTLIN_INFIX_FUN,
      "operator" to KOTLIN_OPERATOR_FUN,
    )

  override fun getDemoText(): String = """
        |fun myFunction(myValue: String, myValues: List<String>) {
        |    if (myValue <infix>owns</infix> 'c') { }
        |    if (myValue <operator>!in</operator> myValues) { }
        |}
        |
        |infix fun String.owns(char: Char): Boolean = contains(char)
    """.trimMargin()

  override fun getColorDescriptors(): Array<ColorDescriptor> =
    ColorDescriptor.EMPTY_ARRAY
}
