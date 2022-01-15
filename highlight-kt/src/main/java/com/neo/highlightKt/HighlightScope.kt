package com.neo.highlightKt

import android.graphics.Typeface
import androidx.annotation.ColorInt
import com.neo.highlight.util.scheme.*
import com.neo.highlight.util.scheme.base.BaseScheme

class HighlightScope(private val parentList: MutableList<BaseScheme>) {
    fun colorScheme(
        @ColorInt color: Int,
        regex: Regex? = null,
        scope: HighlightScope.(BaseScheme) -> Unit = {}
    ) {
        val colorScheme = regex?.let {
            ColorScheme(it.toPattern(), color)
        } ?: run {
            ColorScheme(color)
        }

        process(scope, colorScheme)
    }

    fun styleScheme(
        style : StyleScheme.STYLE,
        regex: Regex? = null,
        scope: HighlightScope.(BaseScheme) -> Unit = {}
    ) {
        val colorScheme = regex?.let {
            StyleScheme(it.toPattern(), style)
        } ?: run {
            StyleScheme(style)
        }

        process(scope, colorScheme)
    }


    fun backgroundScheme(
        @ColorInt color: Int,
        regex: Regex? = null,
        scope: HighlightScope.(BaseScheme) -> Unit = {}
    ) {
        val colorScheme = regex?.let {
            BackgroundScheme(it.toPattern(), color)
        } ?: run {
            BackgroundScheme(color)
        }

        process(scope, colorScheme)
    }

    fun fontScheme(
        typeface: Typeface,
        regex: Regex? = null,
        scope: HighlightScope.(BaseScheme) -> Unit = {}
    ) {
        val colorScheme = regex?.let {
            FontScheme(it.toPattern(), typeface)
        } ?: run {
            FontScheme(typeface)
        }

        process(scope, colorScheme)
    }

    fun linkScheme(
        scope: HighlightScope.(BaseScheme) -> Unit = {}
    ) {
        process(scope, LinkScheme())
    }

    fun scope(
        regex: Regex,
        scope: HighlightScope.(BaseScheme) -> Unit = {}
    ) {
        process(scope, Scope(regex.toPattern()))
    }

    private fun process(
        scope: HighlightScope.(BaseScheme) -> Unit,
        colorScheme: BaseScheme
    ) {
        val childList = mutableListOf<BaseScheme>()

        scope.invoke(HighlightScope(childList), colorScheme)

        childList.forEach { colorScheme.addScopeScheme(it) }

        parentList.add(colorScheme)
    }
}