package com.neo.highlightKt.extensions

import android.widget.EditText
import android.widget.TextView
import com.neo.highlight.core.Highlight
import com.neo.highlight.core.Scheme
import com.neo.highlight.util.listener.HighlightTextWatcher
import com.neo.highlight.util.scheme.base.BaseScheme
import com.neo.highlightKt.HighlightScope

fun TextView.highlight(scope: HighlightScope.() -> Unit) {

    val listSchemes = mutableListOf<BaseScheme>()

    scope.invoke(HighlightScope(listSchemes))

    Highlight(listSchemes as List<Scheme>).setSpan(this)
}

fun EditText.highlight(continuous: Boolean = true, scope: HighlightScope.() -> Unit) {

    val listSchemes = mutableListOf<BaseScheme>()
    scope.invoke(HighlightScope(listSchemes))

    val highlight = Highlight(listSchemes as List<Scheme>)

    if (continuous) {
        addTextChangedListener(HighlightTextWatcher(highlight))
    } else {
        highlight.setSpan(this)
    }
}