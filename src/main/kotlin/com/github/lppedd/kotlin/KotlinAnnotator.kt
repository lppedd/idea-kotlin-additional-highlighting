package com.github.lppedd.kotlin

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.openapi.editor.colors.EditorColorsManager
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import org.jetbrains.kotlin.idea.highlighter.KotlinHighlightingColors
import org.jetbrains.kotlin.lexer.KtKeywordToken
import org.jetbrains.kotlin.lexer.KtSingleValueToken
import org.jetbrains.kotlin.lexer.KtTokens
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

internal val KOTLIN_MODIFIER_OVERRIDE: TextAttributesKey =
    TextAttributesKey.createTextAttributesKey(
        "CUSTOM_KOTLIN_MODIFIER_OVERRIDE",
        KotlinHighlightingColors.KEYWORD
    )

internal val KOTLIN_MODIFIER: TextAttributesKey =
    TextAttributesKey.createTextAttributesKey(
        "CUSTOM_KOTLIN_MODIFIER",
        KotlinHighlightingColors.KEYWORD
    )

internal val KOTLIN_SEALED_OPEN: TextAttributesKey =
    TextAttributesKey.createTextAttributesKey(
        "CUSTOM_KOTLIN_SEALED_OPEN",
        KotlinHighlightingColors.KEYWORD
    )

internal val KOTLIN_COMPANION: TextAttributesKey =
    TextAttributesKey.createTextAttributesKey(
        "CUSTOM_KOTLIN_COMPANION",
        KotlinHighlightingColors.KEYWORD
    )

internal val KOTLIN_DATA: TextAttributesKey =
    TextAttributesKey.createTextAttributesKey(
        "CUSTOM_KOTLIN_DATA",
        KotlinHighlightingColors.KEYWORD
    )

internal val KOTLIN_OPERATOR_INFIX: TextAttributesKey =
    TextAttributesKey.createTextAttributesKey(
        "CUSTOM_KOTLIN_OPERATOR_INFIX",
        KotlinHighlightingColors.KEYWORD
    )

internal val KOTLIN_THIS_SUPER: TextAttributesKey =
    TextAttributesKey.createTextAttributesKey(
        "CUSTOM_KOTLIN_THIS_SUPER",
        KotlinHighlightingColors.KEYWORD
    )

internal val KOTLIN_NULL: TextAttributesKey =
    TextAttributesKey.createTextAttributesKey(
        "CUSTOM_KOTLIN_NULL",
        KotlinHighlightingColors.KEYWORD
    )

internal val KOTLIN_TRUE_FALSE: TextAttributesKey =
    TextAttributesKey.createTextAttributesKey(
        "CUSTOM_KOTLIN_TRUE_FALSE",
        KotlinHighlightingColors.KEYWORD
    )

internal val KOTLIN_ABSTRACT: TextAttributesKey =
    TextAttributesKey.createTextAttributesKey(
        "CUSTOM_KOTLIN_ABSTRACT",
        KotlinHighlightingColors.KEYWORD
    )

internal val KOTLIN_SUSPEND: TextAttributesKey =
    TextAttributesKey.createTextAttributesKey(
        "CUSTOM_KOTLIN_SUSPEND",
        KotlinHighlightingColors.KEYWORD
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
        } else if (element is LeafPsiElement) {
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

    private fun getTextAttributes(element: LeafPsiElement): TextAttributes? {
        return when (element.elementType) {
            KtTokens.PUBLIC_KEYWORD,
            KtTokens.PRIVATE_KEYWORD,
            KtTokens.PROTECTED_KEYWORD,
            KtTokens.INNER_KEYWORD,
            KtTokens.INTERNAL_KEYWORD -> KOTLIN_MODIFIER.getAttributes()

            KtTokens.OVERRIDE_KEYWORD -> KOTLIN_MODIFIER_OVERRIDE.getAttributes()

            KtTokens.SEALED_KEYWORD,
            KtTokens.OPEN_KEYWORD -> KOTLIN_SEALED_OPEN.getAttributes()

            KtTokens.OBJECT_KEYWORD,
            KtTokens.COMPANION_KEYWORD -> KOTLIN_COMPANION.getAttributes()

            KtTokens.DATA_KEYWORD -> KOTLIN_DATA.getAttributes()

            KtTokens.OPERATOR_KEYWORD,
            KtTokens.INFIX_KEYWORD -> KOTLIN_OPERATOR_INFIX.getAttributes()

            KtTokens.THIS_KEYWORD,
            KtTokens.SUPER_KEYWORD -> KOTLIN_THIS_SUPER.getAttributes()

            KtTokens.NULL_KEYWORD -> KOTLIN_NULL.getAttributes()

            KtTokens.TRUE_KEYWORD,
            KtTokens.FALSE_KEYWORD -> KOTLIN_TRUE_FALSE.getAttributes()

            KtTokens.ABSTRACT_KEYWORD -> KOTLIN_ABSTRACT.getAttributes()

            KtTokens.SUSPEND_KEYWORD -> KOTLIN_SUSPEND.getAttributes()

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
