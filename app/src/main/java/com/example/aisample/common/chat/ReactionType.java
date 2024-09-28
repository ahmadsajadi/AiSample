package com.example.aisample.common.chat;

public enum ReactionType {
    NOTING(0),
    HIFIVE(1),
    LIKE(2),
    HAPPY(3),
    CRY(4),
    THUMBS_DOWN(5),
    RED_HEART(6),
    ANGRY_FACE(7),
    VERIFICATION(8),
    HEARTEYES(9),
    CLAPPING_HANDS(10),
    FACES_CREAMING(11),
    FLUSHING_FACE(12),
    GRIMACING_FACE(13),
    NO_EXPRESSION_FACE(14),
    ROFL(15),
    FACEPALMING_GIRL(16),
    FACEPALMING_BOY(17),
    SWEARING_FACE(18),
    BLOWING_A_KISS_FACE(19),
    SEE_NO_EVIL_MONKEY(20),
    TULIP(21),
    GREEN_HEART(22),
    PURPLE_HEART(23),
    BD_CAKE(24),
    HUNDRED_POINTS(25),
    ALARM(26),
    PARTY_POPPER(27),
    PERSON_WALKING(28),
    SMILING_POO(29),
    CRYING_LOUDLY_FACE(30);


    private final int value;

    ReactionType(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}
