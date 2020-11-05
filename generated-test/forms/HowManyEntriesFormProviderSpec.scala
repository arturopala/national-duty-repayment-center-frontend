package forms

import forms.behaviours.StringFieldBehaviours
import play.api.data.FormError

class HowManyEntriesFormProviderSpec extends StringFieldBehaviours {

  val requiredKey = "howManyEntries.error.required"
  val lengthKey = "howManyEntries.error.length"
  val maxLength = 2

  val form = new HowManyEntriesFormProvider()()

  ".value" must {

    val fieldName = "value"

    behave like fieldThatBindsValidData(
      form,
      fieldName,
      stringsWithMaxLength(maxLength)
    )

    behave like fieldWithMaxLength(
      form,
      fieldName,
      maxLength = maxLength,
      lengthError = FormError(fieldName, lengthKey, Seq(maxLength))
    )

    behave like mandatoryField(
      form,
      fieldName,
      requiredError = FormError(fieldName, requiredKey)
    )
  }
}