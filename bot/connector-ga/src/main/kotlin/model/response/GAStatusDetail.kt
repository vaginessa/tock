/*
 * Copyright (C) 2017/2020 e-voyageurs technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ai.tock.bot.connector.ga.model.response

import ai.tock.bot.connector.ga.model.request.GARequest
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Standard GA connector error.
 */
data class GAStatusDetail(
    val stackTrace: String,
    val requestBody: String? = null,
    val requestParsed: GARequest? = null,
    @get:JsonProperty("@type") val type: String = "type.googleapis.com"
)
