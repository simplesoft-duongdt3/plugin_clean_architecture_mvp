package template.domain

import template.FileTemplate
import java.util.*

class UseCaseInputModelFileTemplate(private val useCaseName: String) : FileTemplate() {
    override val resultFileName: String
        get() = "${useCaseName}UseCaseInputModel"

    override fun getProperties(templateProperties: Properties): Properties? {
        templateProperties["USE_CASE_NAME"] = useCaseName
        return templateProperties
    }

    override val templateFileName: String = "UseCaseInputModelTemplate"
}