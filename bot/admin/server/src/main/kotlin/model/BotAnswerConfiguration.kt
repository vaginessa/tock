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

package ai.tock.bot.admin.model

import ai.tock.bot.admin.answer.AnswerConfigurationType
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

/**
 *
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "answerType"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = BotSimpleAnswerConfiguration::class, name = "0"),
    JsonSubTypes.Type(value = BotSimpleAnswerConfiguration::class, name = "simple"),
    JsonSubTypes.Type(value = BotScriptAnswerConfiguration::class, name = "2"),
    JsonSubTypes.Type(value = BotScriptAnswerConfiguration::class, name = "script"),
    JsonSubTypes.Type(value = BotBuiltinAnswerConfiguration::class, name = "3"),
    JsonSubTypes.Type(value = BotBuiltinAnswerConfiguration::class, name = "builtin")
)
abstract class BotAnswerConfiguration(val answerType: AnswerConfigurationType)
