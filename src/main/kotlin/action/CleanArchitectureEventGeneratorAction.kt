package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import template.FileCreator
import template.event.EventFileTemplate
import template.event.EventReceiverFileTemplate
import template.event.EventSenderFileTemplate
import util.DialogProvider


class CleanArchitectureEventGeneratorAction : AnAction() {
    private val dialogProvider = DialogProvider()
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project
        project?.let {
            WriteCommandAction.runWriteCommandAction(project) {
                createDomainFiles(project, event)
            }
        }

    }

    private fun createDomainFiles(
        project: Project,
        event: AnActionEvent
    ) {
        val eventName =
            dialogProvider.showInputDialog(project, "Create Event files", "Input name for Event")
        val fileCreator = FileCreator(project)
        if (eventName.isNotBlank()) {
            val destinationPath = event.getData(LangDataKeys.PSI_ELEMENT) as PsiDirectory

            val eventNameAfterTrim = eventName.trim()
            createEventFile(eventNameAfterTrim, fileCreator, destinationPath)
            createEventReceiverFile(eventNameAfterTrim, fileCreator, destinationPath)
            createEventSenderFile(eventNameAfterTrim, fileCreator, destinationPath)
            //TODO: open file after create
            //FileEditorManager.getInstance(project).openFile(createTemplateFile, true)
        }
    }

    private fun createEventFile(
        eventNameAfterTrim: String,
        fileCreator: FileCreator,
        destinationPath: PsiDirectory
    ) {
        val template = EventFileTemplate(eventNameAfterTrim)
        fileCreator.createTemplateFile(template, destinationPath)
    }

    private fun createEventReceiverFile(
        eventNameAfterTrim: String,
        fileCreator: FileCreator,
        destinationPath: PsiDirectory
    ) {
        val template = EventReceiverFileTemplate(eventNameAfterTrim)
        fileCreator.createTemplateFile(template, destinationPath)
    }

    private fun createEventSenderFile(
        eventNameAfterTrim: String,
        fileCreator: FileCreator,
        destinationPath: PsiDirectory
    ) {
        val template = EventSenderFileTemplate(eventNameAfterTrim)
        fileCreator.createTemplateFile(template, destinationPath)
    }
}