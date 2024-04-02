package ru.test.foodies.screens.sample

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import ru.test.androiddevtask.R
import ru.test.foodies.dto.Product

class SampleProductProvider : PreviewParameterProvider<Product> {
    override val values = sequenceOf(
        Product(
            id = 1,
            category_id = 676168,
            name = "Авокадо Кранч Маки 8шт",
            description = "Ролл с нежным мясом камчатского краба, копченой курицей и авокадо.Украшается соусом\"Унаги\" и легким майонезом  Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
            image = R.drawable.card_photo_example.toString(),
            price_current = 47000,
            price_old = 50000,
            measure_unit = "г",
            energy_per_100_grams = 307.8,
            proteins_per_100_grams = 6.1,
            fats_per_100_grams = 11.4,
            carbohydrates_per_100_grams = 45.1,
            tag_ids = listOf(
                1,
                4
            )
        )
    )
}