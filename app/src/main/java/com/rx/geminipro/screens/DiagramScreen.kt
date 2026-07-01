package com.rx.geminipro.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.rx.geminipro.components.HtmlViewer
import com.rx.geminipro.components.generateHtmlWithMermaid

@Composable
fun DiagramScreen(isDiagramVisible: Boolean, clipboardText: String, onBack: () -> Unit, onClose: () -> Unit) {
    val htmlContent = remember(clipboardText) { generateHtmlWithMermaid(clipboardText) }

    GenericPreviewScreen(
        isVisible = isDiagramVisible,
        title = "Close Diagram",
        onBack = onBack,
        onClose = onClose
    ) {
        HtmlViewer(htmlContent = htmlContent)
    }
}