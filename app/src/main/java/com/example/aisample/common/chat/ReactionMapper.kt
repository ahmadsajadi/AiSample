package com.example.aisample.common.chat
class ReactionMapper {

    companion object {
        private val reactionToEmojiMap = mapOf(
            ReactionType.HIFIVE to "🙏",
            ReactionType.LIKE to "👍",
            ReactionType.HAPPY to "😄",
            ReactionType.CRY to "😢",
            ReactionType.THUMBS_DOWN to "👎",
            ReactionType.RED_HEART to "❤️",
            ReactionType.ANGRY_FACE to "\uD83D\uDE21",
            ReactionType.VERIFICATION to "✅",
            ReactionType.HEARTEYES to "😍",
            ReactionType.CLAPPING_HANDS to "👏",
            ReactionType.FACES_CREAMING to "😱",
            ReactionType.FLUSHING_FACE to "😳",
            ReactionType.GRIMACING_FACE to "😬",
            ReactionType.NO_EXPRESSION_FACE to "😐",
            ReactionType.ROFL to "🤣",
            ReactionType.FACEPALMING_GIRL to "🤦‍♀️",
            ReactionType.FACEPALMING_BOY to "🤦‍♂️",
            ReactionType.SWEARING_FACE to "🤬",
            ReactionType.BLOWING_A_KISS_FACE to "😘",
            ReactionType.SEE_NO_EVIL_MONKEY to "🙈",
            ReactionType.TULIP to "💐",
            ReactionType.GREEN_HEART to "💚",
            ReactionType.PURPLE_HEART to "💜",
            ReactionType.BD_CAKE to "🎂",
            ReactionType.HUNDRED_POINTS to "💯",
            ReactionType.ALARM to "🚨",
            ReactionType.PARTY_POPPER to "🎉",
            ReactionType.PERSON_WALKING to "🚶",
            ReactionType.SMILING_POO to "💩",
            ReactionType.CRYING_LOUDLY_FACE to "😭"
        )

        private val emojiToReactionMap = reactionToEmojiMap.entries.associate { (k, v) -> v to k }

        private val idToReactionMap = ReactionType.values().associateBy { it.value }

        fun reactionToCode(type: ReactionType): String {
            return reactionToEmojiMap[type] ?: "👍"
        }

        fun codeToReaction(code: String): ReactionType {
            return emojiToReactionMap[code] ?: ReactionType.NOTING
        }

        fun idToType(reaction: Int): ReactionType {
            return idToReactionMap[reaction] ?: ReactionType.NOTING
        }

        fun idToCode(reaction: Int): String {
            val type = idToType(reaction)
            return reactionToCode(type)
        }
    }
}
