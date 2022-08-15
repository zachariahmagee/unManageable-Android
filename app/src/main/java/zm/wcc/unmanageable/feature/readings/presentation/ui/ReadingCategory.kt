package zm.wcc.unmanageable.feature.readings.presentation.ui

import zm.wcc.unmanageable.feature.readings.presentation.ui.ReadingCategory.*


enum class ReadingCategory(val value: String) {
    SERVICE("Service"),
    HONESTY("Honesty"),
    HOPE("Hope"),
    FAITH("Faith"),
    WILLINGNESS("Willingness"),
    INTEGRITY("Integrity"),
    COURAGE("Courage"),
    HUMILITY("Humility"),
    SPIRITUALITY("Spirituality"),
    JUSTICE("Justice"),
    PERSEVERANCE("Perseverance"),
    LOVE("Love")
}

fun getAllReadingCategories() : List<ReadingCategory> {
    return listOf(SERVICE, HONESTY, HOPE,
        FAITH, WILLINGNESS, INTEGRITY,
        COURAGE, HUMILITY, SPIRITUALITY,
        JUSTICE, PERSEVERANCE, LOVE)
}

fun getReadingCategory(value: String) : ReadingCategory? {
    val map = ReadingCategory.values().associateBy(ReadingCategory::value)
    return map[value]
}