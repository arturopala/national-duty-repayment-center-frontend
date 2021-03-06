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

package controllers

import base.SpecBase
import models.{ClaimRepaymentType, NormalMode, UserAnswers}
import pages.ClaimRepaymentTypePage
import play.api.test.FakeRequest
import play.api.test.Helpers._
import play.twirl.api.Html
import viewmodels.{AnswerRow, AnswerSection}
import views.html.RepaymentAmountSummaryView

class RepaymentAmountSummaryControllerSpec extends SpecBase {

  def answersViewModel = Seq(
    AnswerSection(Some("Customs Duty"), Seq(
      AnswerRow(Html("Customs Duty paid"), Html("£0.0"), Some("/national-duty-repayment-center/changecustomsDutyPaid")),
      AnswerRow(Html("Customs Duty that was due"), Html("£0.0"), Some("/national-duty-repayment-center/changeCustomsDutyDueToHMRC")),
      AnswerRow(Html("Total Customs Duty repayment amount"), Html("<span class=\"bold\">£0.0</span>"))
    )),
    AnswerSection(Some("VAT"), Seq(
      AnswerRow(Html("VAT paid"), Html("£0.0"), Some("/national-duty-repayment-center/changeVATPaid")),
      AnswerRow(Html("VAT that was due"), Html("£0.0"), Some("/national-duty-repayment-center/changeVATDueToHMRC")),
      AnswerRow(Html("Total VAT repayment amount"), Html("<span class=\"bold\">£0.0</span>"))
    )),
    AnswerSection(Some("Other duties"), Seq(
      AnswerRow(Html("Other duties paid"), Html("£0.0"), Some("/national-duty-repayment-center/changeOtherDutiesPaid")),
      AnswerRow(Html("Other duties that were due"), Html("£0.0"), Some("/national-duty-repayment-center/changeOtherDutiesDueToHMRC")),
      AnswerRow(Html("Total other duties repayment amount"), Html("<span class=\"bold\">£0.0</span>"))
    )),
    AnswerSection(Some("Total"), Seq(
      AnswerRow(Html("Total repayment amount"), Html("<span class=\"bold\">£0.0</span>"))
    ))
  )

  "RepaymentAmountSummary Controller" must {

    "return OK and the correct view for a GET" in {

      val userAnswers = UserAnswers(userAnswersId).set(ClaimRepaymentTypePage, ClaimRepaymentType.values.toSet).success.value

      val application = applicationBuilder(userAnswers = Some(userAnswers)).build()

      val request = FakeRequest(GET, routes.RepaymentAmountSummaryController.onPageLoad().url)

      val result = route(application, request).value

      val view = application.injector.instanceOf[RepaymentAmountSummaryView]

      status(result) mustEqual OK

      contentAsString(result) mustEqual
        view(answersViewModel)(fakeRequest, messages).toString

      application.stop()
    }
  }
}
