/*
 *  Copyright (c) 2020 Squrlabs @ http://squrlabs.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *              http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.deepanshut041.peertube.common.remote.dto.others


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationSettingRequest(
    @SerialName("abuseAsModerator")
    val abuseAsModerator: Int?,
    @SerialName("autoInstanceFollowing")
    val autoInstanceFollowing: Int?,
    @SerialName("blacklistOnMyVideo")
    val blacklistOnMyVideo: Int?,
    @SerialName("commentMention")
    val commentMention: Int?,
    @SerialName("myVideoImportFinished")
    val myVideoImportFinished: Int?,
    @SerialName("myVideoPublished")
    val myVideoPublished: Int?,
    @SerialName("newCommentOnMyVideo")
    val newCommentOnMyVideo: Int?,
    @SerialName("newFollow")
    val newFollow: Int?,
    @SerialName("newInstanceFollower")
    val newInstanceFollower: Int?,
    @SerialName("newUserRegistration")
    val newUserRegistration: Int?,
    @SerialName("newVideoFromSubscription")
    val newVideoFromSubscription: Int?,
    @SerialName("videoAutoBlacklistAsModerator")
    val videoAutoBlacklistAsModerator: Int?
)