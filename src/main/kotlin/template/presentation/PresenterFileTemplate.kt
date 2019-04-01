package template.presentation

import template.FileTemplate
import java.util.*

class PresenterFileTemplate(private val featureName: String) : FileTemplate() {
    override val resultFileName: String
        get() = "${featureName}Presenter"

    override fun getProperties(templateProperties: Properties): Properties? {
        templateProperties["FEATURE_NAME"] = featureName
        return templateProperties
    }

    override val templateFileName: String = "PresenterTemplate"
}