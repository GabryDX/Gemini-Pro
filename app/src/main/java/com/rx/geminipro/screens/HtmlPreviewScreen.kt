package com.rx.geminipro.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.rx.geminipro.components.HtmlViewer

@Composable
fun HtmlPreviewScreen(isHtmlPreviewVisible: Boolean, clipboardText: String, onBack: () -> Unit, onClose: () -> Unit) {
    val htmlContent = remember(clipboardText) { clipboardText }

    GenericPreviewScreen(
        isVisible = isHtmlPreviewVisible,
        title = "Close Preview",
        onBack = onBack,
        onClose = onClose
    ) {
        HtmlViewer(htmlContent = htmlContent)
    }
}