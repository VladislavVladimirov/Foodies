package ru.test.foodies.dto

data class Product(
    val carbohydrates_per_100_grams: Double? = null,
    val category_id: Int? = null,
    val description: String? = null,
    val energy_per_100_grams: Double? = null,
    val fats_per_100_grams: Double? = null,
    val id: Int,
    val image: String? = null,
    val measure: Int? = null,
    val measure_unit: String? = null,
    val name: String = "",
    val price_current: Int? = null,
    val price_old: Int? = null,
    val proteins_per_100_grams: Double? = null,
    val tag_ids: List<Int> = emptyList(),
    val count: Int = 0
)