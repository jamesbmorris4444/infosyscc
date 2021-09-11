package com.sample.infosyscc.repository.storage

data class NewsResponsePage(
    val page: NewsResponseCards
)

data class NewsResponseCards(
    val cards: List<CardPair>
)

data class CardPair(
    val card_type: String?,
    val card: Card?
)

data class Card(
    val value: String?,
    val attributes: Attribute?,
    val title: CardTitle?,
    val description: CardDescription?,
    val image: CardImage?
)

data class Attribute(
    val text_color: String?,
    val font: CardFont?
)

data class CardFont(
    val size: Int
)

data class CardTitle(
    val value: String?,
    val attributes: Attribute?
)

data class CardDescription(
    val value: String?,
    val attributes: Attribute?
)

data class CardImage(
    val url: String?,
    val size: WidthHeight?
)

data class WidthHeight(
    val width: Int,
    val height: Int
)