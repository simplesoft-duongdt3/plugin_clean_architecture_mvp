package template.domain

import template.FileTemplate
import java.util.*

class UseCaseFileTemplate(private val useCaseName: String) : FileTemplate() {
    override val resultFileName: String
        get() = "${useCaseName}UseCase"

    override fun getProperties(templateProperties: Properties): Properties? {
        templateProperties["USE_CASE_NAME"] = useCaseName
        return templateProperties
    }

    override val templateFileName: String = "UseCaseTemplate"
}