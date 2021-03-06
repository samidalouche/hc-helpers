/**
 * Copyright 2011 Sami Dalouche
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.iglootools.hchelpers

import org.apache.http.client.HttpClient

package object scala {
  type HttpClientTemplate[E <: Throwable] = scala.client.HttpClientTemplate[E]
  val SimpleHttpClient = scala.client.SimpleHttpClient

  type HttpErrorHandler[+E] = scala.client.HttpErrorHandler[E]
  val HttpErrorHandler = scala.client.HttpErrorHandler


  implicit def asHttpClientTemplate(httpClient: HttpClient): HttpClientTemplate[Exception] = new HttpClientTemplate(httpClient=httpClient)
}