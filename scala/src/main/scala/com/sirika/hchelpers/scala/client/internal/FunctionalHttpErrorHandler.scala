package com.sirika.hchelpers.scala.client.internal

import org.apache.http.{HttpResponse, StatusLine}
import com.sirika.hchelpers.scala.client.HttpErrorHandler

/**
 * {@link HttpErrorHandler} that delegates to {@link HttpErrorMatcher} and {@link HttpResponseCallback}
 *
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 *
 */
protected[hchelpers] final class FunctionalHttpErrorHandler[T](val matches: (StatusLine) => Boolean, doOnError: (HttpResponse)=> T) extends HttpErrorHandler[T] {
  require(matches != null, "matches is required")
  require(doOnError != null, "callback is required")

  def appliesTo(response: HttpResponse): Boolean = {
    matches(response.getStatusLine)
  }

  def handle(response: HttpResponse): T = {
    doOnError(response)
  }
}