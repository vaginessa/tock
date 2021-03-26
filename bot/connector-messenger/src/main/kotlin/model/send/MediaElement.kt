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

package ai.tock.bot.connector.messenger.model.send

import ai.tock.bot.engine.message.Attachment
import ai.tock.bot.engine.message.GenericElement
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * See [https://developers.facebook.com/docs/messenger-platform/reference/template/media].
 */
data class MediaElement(
    @JsonProperty("media_type") val mediaType: MediaType,
    @JsonProperty("attachment_id") val attachmentId: String,
    val buttons: List<Button>? = null
) {

    fun toGenericElement(): GenericElement {
        return GenericElement(
            choices = buttons?.map { it.toChoice() } ?: emptyList(),
            attachments = listOf(Attachment(attachmentId, mediaType.toAttachmentType()))
        )
    }
}
