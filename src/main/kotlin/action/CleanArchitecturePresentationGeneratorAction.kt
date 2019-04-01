package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import template.FileCreator
import template.presentation.PresentationContractFileTemplate
import template.presentation.PresentationModelFileTemplate
import template.presentation.PresenterFileTemplate
import util.DialogProvider


class CleanArchitecturePresentationGeneratorAction : AnAction() {
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
        val featureName =
            dialogProvider.showInputDialog(project, "Create Presentation files", "Input feature's name")
        val fileCreator = FileCreator(project)
        if (featureName.isNotBlank()) {
            val destinationPath = event.getData(LangDataKeys.PSI_ELEMENT) as PsiDirectory

            val featureNameAfterTrim = featureName.trim()
            createContractFile(featureNameAfterTrim, fileCreator, destinationPath)
            createPresenterFile(featureNameAfterTrim, fileCreator, destinationPath)
            createPresentationModelFile(featureNameAfterTrim, fileCreator, destinationPath)
            //TODO: open file after create
            //FileEditorManager.getInstance(project).openFile(createTemplateFile, true)
        }
    }

    private fun createContractFile(
        featureNameAfterTrim: String,
        fileCreator: FileCreator,
        destinationPath: PsiDirectory
    ) {
        val template = PresentationContractFileTemplate(featureNameAfterTrim)
        fileCreator.createTemplateFile(template, destinationPath)
    }

    private fun createPresenterFile(
        featureNameAfterTrim: String,
        fileCreator: FileCreator,
        destinationPath: PsiDirectory
    ) {
        val template = PresenterFileTemplate(featureNameAfterTrim)
        fileCreator.createTemplateFile(template, destinationPath)
    }

    private fun createPresentationModelFile(
        featureNameAfterTrim: String,
        fileCreator: FileCreator,
        destinationPath: PsiDirectory
    ) {
        val template = PresentationModelFileTemplate(featureNameAfterTrim)
        fileCreator.createTemplateFile(template, destinationPath)
    }
}