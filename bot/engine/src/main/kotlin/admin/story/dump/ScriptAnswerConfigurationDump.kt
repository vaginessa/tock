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

package ai.tock.bot.admin.story.dump

import ai.tock.bot.admin.answer.AnswerConfiguration
import ai.tock.bot.admin.answer.AnswerConfigurationType
import ai.tock.bot.admin.answer.AnswerConfigurationType.script
import ai.tock.bot.admin.answer.ScriptAnswerConfiguration

/**
 * An [AnswerConfigurationDump] defined by a Kotlin script.
 */
data class ScriptAnswerConfigurationDump(
    val scriptVersions: List<ScriptAnswerVersionedConfigurationDump>,
    val current: ScriptAnswerVersionedConfigurationDump = scriptVersions.maxByOrNull { it.date } ?: error("no script version found")
) : AnswerConfigurationDump(AnswerConfigurationType.script) {

    constructor(conf: ScriptAnswerConfiguration) : this(
        conf.scriptVersions.map { ScriptAnswerVersionedConfigurationDump(it) }
    )

    override fun toAnswer(currentType: AnswerConfigurationType, controller: StoryDefinitionConfigurationDumpController): AnswerConfiguration {
        val newCurrent = current.toAnswer(controller, currentType == script)
        return ScriptAnswerConfiguration(
            scriptVersions.map { if (it == current) newCurrent else it.toAnswer(controller) }
        )
    }
}
