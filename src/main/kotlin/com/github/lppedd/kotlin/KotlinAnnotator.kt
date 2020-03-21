package com.github.lppedd.kotlin

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.openapi.editor.colors.EditorColorsManager
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.idea.highlighter.KotlinHighlightingColors
import org.jetbrains.kotlin.psi.KtOperationReferenceExpression

internal val KOTLIN_INFIX_FUN: TextAttributesKey =
  TextAttributesKey.createTextAttributesKey(
    "CUSTOM_KOTLIN_INFIX_FUN",
    KotlinHighlightingColors.FUNCTION_CALL
  )

internal val KOTLIN_OPERATOR_FUN: TextAttributesKey =
  TextAttributesKey.createTextAttributesKey(
    "CUSTOM_KOTLIN_OPERATOR_FUN",
    KotlinHighlightingColors.KEYWORD
  )

/**
 * @author Edoardo Luppi
 */
private class KotlinAnnotator : Annotator {
  private val globalScheme = EditorColorsManager.getInstance().globalScheme
  private val infixFunAttributes = globalScheme.getAttributes(KOTLIN_INFIX_FUN)
  private val operatorFunAttributes = globalScheme.getAttributes(KOTLIN_OPERATOR_FUN)

  override fun annotate(element: PsiElement, annotationHolder: AnnotationHolder) {
    if (element !is KtOperationReferenceExpression) return

    val textAttributes =
      if (element.isConventionOperator()) operatorFunAttributes
      else infixFunAttributes

    annotationHolder.setTextAttributes(element, textAttributes)
  }

  private fun AnnotationHolder.setTextAttributes(
      element: PsiElement,
      textAttributes: TextAttributes) {
    createInfoAnnotation(element, null).enforcedTextAttributes = textAttributes
  }
}
