package template

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.fileTemplates.FileTemplateUtil
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiElement
import java.util.*

abstract class FileTemplate() {

    abstract val templateFileName: String
    abstract val resultFileName: String


    fun createTemplate(project: Project, destinationDirectory: PsiDirectory): PsiElement {
        val templateName = FileTemplateManager.getInstance(project).getInternalTemplate(templateFileName)
        val templateProperties = FileTemplateManager.getInstance(project).defaultProperties
        return FileTemplateUtil.createFromTemplate(templateName, resultFileName, getProperties(templateProperties), destinationDirectory)

    }

    abstract fun getProperties(templateProperties: Properties): Properties?
}