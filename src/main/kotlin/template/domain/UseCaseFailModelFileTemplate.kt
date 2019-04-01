package template.domain

import template.FileTemplate
import java.util.*

class UseCaseFailModelFileTemplate(private val useCaseName: String) : FileTemplate() {
    override val resultFileName: String
        get() = "${useCaseName}UseCaseFailModel"

    override fun getProperties(templateProperties: Properties): Properties? {
        templateProperties["USE_CASE_NAME"] = useCaseName
        return templateProperties
    }

    override val templateFileName: String = "UseCaseFailModelTemplate"
}