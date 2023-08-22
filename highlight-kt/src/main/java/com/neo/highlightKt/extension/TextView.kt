package com.neo.highlightKt.extension

import android.widget.TextView
import com.neo.highlight.core.Highlight
import com.neo.highlight.core.Scheme
import com.neo.highlight.util.scheme.base.BaseScheme
import com.neo.highlightKt.HighlightScope

fun TextView.highlight(scope: HighlightScope.() -> Unit) {

    val listSchemes = mutableListOf<BaseScheme>()

    scope.invoke(HighlightScope(listSchemes))

    Highlight(listSchemes as List<Scheme>).setSpan(this)
}