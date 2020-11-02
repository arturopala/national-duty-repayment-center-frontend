/*
 * Copyright 2020 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package forms

import forms.mappings.Mappings
import javax.inject.Inject
import models.Address
import play.api.data.Forms._
import play.api.data.{Form, Forms}

class AddressFormProvider @Inject() extends Mappings {
  private val maxLineLength = 35
  private val maxCountyLength = 100

  def apply(): Form[Address] = Form(
    mapping(
      "AddressLine1" ->
        text("address.error.AddressLine1.required")
          .verifying(firstError(
            maxLength(maxLineLength, "address.error.AddressLine1.length"),
       //     regexp(Validation.safeInputPattern,"address.error.line1.invalid")
          )),
      "AddressLine2" ->
        optional(Forms.text
          .verifying(firstError(
            maxLength(maxLineLength, "address.error.AddressLine2.length"),
      //      regexp(Validation.safeInputPattern,"address.error.line2.invalid")
          ))),
      "City" ->
        text("address.error.City.required")
          .verifying(firstError(
            maxLength(maxLineLength, "address.error.City.length"),
        //    regexp(Validation.safeInputPattern,"address.error.town.invalid")
          )),
      "Region" ->
        text("address.error.Region.required")
          .verifying(firstError(
            maxLength(maxLineLength, "address.error.Region.length"),
            //    regexp(Validation.safeInputPattern,"address.error.town.invalid")
          )),
      "CountryCode" ->
        text("address.error.CountryCode.required")
          .verifying(firstError(
            maxLength(maxLineLength, "address.error.CountryCode.length"),
            //    regexp(Validation.safeInputPattern,"address.error.town.invalid")
          )),
      "PostalCode" ->
        optional(Forms.text
          .verifying(firstError(
            maxLength(maxCountyLength, "address.error.PostalCode.length"),
          //  regexp(Validation.safeInputPattern,"address.error.county.invalid")
          ))),
      "TelephoneNumber" ->
        optional(Forms.text
          .verifying(firstError(
            maxLength(maxCountyLength, "address.error.TelephoneNumber.length"),
            //  regexp(Validation.safeInputPattern,"address.error.county.invalid")
          ))),
      "EmailAddress" ->
        optional(Forms.text
          .verifying(firstError(
            maxLength(maxCountyLength, "address.error.EmailAddress.length"),
            //  regexp(Validation.safeInputPattern,"address.error.county.invalid")
          )))
    )(Address.apply)(address => Some((address.AddressLine1,address.AddressLine2, address.City, address.Region,
      address.CountryCode, address.PostalCode, address.TelephoneNumber,address.EmailAddress)))
  )

}
