package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import template.FileCreator
import template.domain.UseCaseFailModelFileTemplate
import template.domain.UseCaseFileTemplate
import template.domain.UseCaseInputModelFileTemplate
import template.domain.UseCaseSuccessModelFileTemplate
import util.DialogProvider


class CleanArchitectureDomainGeneratorAction : AnAction() {
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
        val useCaseName =
            dialogProvider.showInputDialog(project, "Create UseCase files", "Input name for UseCase")
        val fileCreator = FileCreator(project)
        if (useCaseName.isNotBlank()) {
            val destinationPath = event.getData(LangDataKeys.PSI_ELEMENT) as PsiDirectory

            val useCaseNameAfterTrim = useCaseName.trim()
            createUseCaseFile(useCaseNameAfterTrim, fileCreator, destinationPath)
            createUseCaseInputModelFile(useCaseNameAfterTrim, fileCreator, destinationPath)
            createUseCaseSuccessModelFile(useCaseNameAfterTrim, fileCreator, destinationPath)
            createUseCaseFailModelFile(useCaseNameAfterTrim, fileCreator, destinationPath)
            //TODO: open file after create
            //FileEditorManager.getInstance(project).openFile(createTemplateFile, true)
        }
    }

    private fun createUseCaseFile(
        useCaseNameAfterTrim: String,
        fileCreator: FileCreator,
        destinationPath: PsiDirectory
    ) {
        val template = UseCaseFileTemplate(useCaseNameAfterTrim)
        fileCreator.createTemplateFile(template, destinationPath)
    }

    private fun createUseCaseInputModelFile(
        useCaseNameAfterTrim: String,
        fileCreator: FileCreator,
        destinationPath: PsiDirectory
    ) {
        val template = UseCaseInputModelFileTemplate(useCaseNameAfterTrim)
        fileCreator.createTemplateFile(template, destinationPath)
    }

    private fun createUseCaseSuccessModelFile(
        useCaseNameAfterTrim: String,
        fileCreator: FileCreator,
        destinationPath: PsiDirectory
    ) {
        val template = UseCaseSuccessModelFileTemplate(useCaseNameAfterTrim)
        fileCreator.createTemplateFile(template, destinationPath)
    }

    private fun createUseCaseFailModelFile(
        useCaseNameAfterTrim: String,
        fileCreator: FileCreator,
        destinationPath: PsiDirectory
    ) {
        val template = UseCaseFailModelFileTemplate(useCaseNameAfterTrim)
        fileCreator.createTemplateFile(template, destinationPath)
    }
}