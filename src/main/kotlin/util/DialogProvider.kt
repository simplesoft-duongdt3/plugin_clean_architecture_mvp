package util

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages

class DialogProvider {
    fun showInputDialog(project: Project, msg: String, title: String): String {
        return try {
            Messages.showInputDialog(project, msg, title, null) ?: ""
        } catch (e: Throwable) {
            Messages.showErrorDialog(e.message, "ERROR")
            ""
        }
    }
}