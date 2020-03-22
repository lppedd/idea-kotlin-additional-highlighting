package com.github.lppedd.kotlin

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.openapi.editor.colors.EditorColorsManager
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.idea.highlighter.KotlinHighlightingColors
import org.jetbrains.kotlin.lexer.KtKeywordToken
import org.jetbrains.kotlin.lexer.KtSingleValueToken
import org.jetbrains.kotlin.psi.KtOperationReferenceExpression

internal val KOTLIN_INFIX_FUN: TextAttributesKey =
  TextAttributesKey.createTextAttributesKey(
    "CUSTOM_KOTLIN_INFIX_FUN",
    KotlinHighlightingColors.FUNCTION_CALL
  )

internal val KOTLIN_KEYWORD_OPERATOR_FUN: TextAttributesKey =
  TextAttributesKey.createTextAttributesKey(
    "CUSTOM_KOTLIN_KEYWORD_OPERATOR_FUN",
    KotlinHighlightingColors.KEYWORD
  )

internal val KOTLIN_MATH_OPERATOR_FUN: TextAttributesKey =
  TextAttributesKey.createTextAttributesKey(
    "CUSTOM_KOTLIN_MATH_OPERATOR_FUN",
    KotlinHighlightingColors.OPERATOR_SIGN
  )

/**
 * @author Edoardo Luppi
 */
private class KotlinAnnotator : Annotator {
  private val globalScheme = EditorColorsManager.getInstance().globalScheme

  override fun annotate(element: PsiElement, annotationHolder: AnnotationHolder) {
    if (element is KtOperationReferenceExpression) {
      getTextAttributes(element)?.let {
        annotationHolder.setTextAttributes(element, it)
      }
    }
  }

  private fun getTextAttributes(expr: KtOperationReferenceExpression): TextAttributes? {
    val operationSignTokenType = expr.operationSignTokenType
    return when {
      operationSignTokenType is KtKeywordToken -> KOTLIN_KEYWORD_OPERATOR_FUN.getAttributes()
      operationSignTokenType is KtSingleValueToken -> KOTLIN_MATH_OPERATOR_FUN.getAttributes()
      !expr.isConventionOperator() -> KOTLIN_INFIX_FUN.getAttributes()
      else -> null
    }
  }

  private fun AnnotationHolder.setTextAttributes(
      element: PsiElement,
      textAttributes: TextAttributes) {
    createInfoAnnotation(element, null).enforcedTextAttributes = textAttributes
  }

  private fun TextAttributesKey.getAttributes(): TextAttributes =
    globalScheme.getAttributes(this)
}
