package com.matttax.composefortvsample.data

import com.matttax.composefortvsample.R
import com.matttax.composefortvsample.data.model.Dish
import com.matttax.composefortvsample.data.model.Restaurant

object LocalDataProvider {

    private val dishes = listOf(
        Dish(
            id = 1L,
            logoResId = R.drawable.dish_image_1,
            name = "Пельмешкин Ультрамарин",
            description = "Пельмени с ярко-синим чесночным соусом. Вам обязательно понадобится фото на память!",
        ),
        Dish(
            id = 2L,
            logoResId = R.drawable.dish_image_2,
            name = "Салат \"Оливье с Алисой\"",
            description = "Внезапно странное сочетание классического \"Оливье\" с кусочками маринованного имбиря и апельсина.",
        ),
        Dish(
            id = 3L,
            logoResId = R.drawable.dish_image_3,
            name = "Французские лягухи",
            description = "Лягушачьи лапки в легком кляре с клубничным соусом. Изящность французской кухни с особым шармом.",
        ),
        Dish(
            id = 4L,
            logoResId = R.drawable.dish_image_4,
            name = "Шалтай-Болтай Бургер",
            description = "Яйцо-пашот на вершине котлеты с грибным соусом. Аккуратно, не упустите Шалтая!",
        ),
        Dish(
            id = 5L,
            logoResId = R.drawable.dish_image_5,
            name = "Настоящая Бурда",
            description = "Запеканка из всех остатков сезона: картофель, брокколи, грибы и неожиданный кусочек ананаса.",
        ),
        Dish(
            id = 6L,
            logoResId = R.drawable.dish_image_6,
            name = "Вегетарианская загадка",
            description = "Разноцветный салат с морской капустой, кинвой и нарезанными в хаотическом порядке овощами. Никто не угадает все ингредиенты.",
        ),
        Dish(
            id = 7L,
            logoResId = R.drawable.dish_image_7,
            name = "Паста \"Чем дальше, тем глубже\"",
            description = "Спагетти с креветками и соусом из чернил каракатицы. Чем глубже, тем вкуснее!",
        ),
        Dish(
            id = 8L,
            logoResId = R.drawable.dish_image_8,
            name = "Чизкейк \"Тень в полнолуние\"\n",
            description = "Темный, как ночь, шоколадный чизкейк с натекающим клубничным соусом и капелькой мятного сиропа.",
        ),
        Dish(
            id = 9L,
            logoResId = R.drawable.dish_image_9,
            name = "Супчик \"Слеза блондинки\"",
            description = "Легкий крем-суп из пастернака и топинамбура с кусочками нежного сливочного сыра.",
        ),
        Dish(
            id = 10L,
            logoResId = R.drawable.dish_image_10,
            name = "Необычный шашлык \"Баланс вселенной\"",
            description = "Шашлык, сочетающий крылья куриного ангела и хвост дьявольского острого перца.",
        ),
        Dish(
            id = 11L,
            logoResId = R.drawable.dish_image_11,
            name = "Жареное яйцо \"Вуху!\"",
            description = "Яйцо, обжаренное с чесноком и имбирем, подается в фирменной желтой тарелке для поднятия настроения.",
        ),
        Dish(
            id = 12L,
            logoResId = R.drawable.dish_image_12,
            name = "Щепотка джазу",
            description = "Блюдо, где сочетаются несочетаемые: копченый сыр, сгущенка и вяленые томаты.",
        ),
        Dish(
            id = 13L,
            logoResId = R.drawable.dish_image_13,
            name = "Картошка \"Невозмутимый британец\"",
            description = "Подается в костюме из шелковистого бекона и слоями горячего чеддера.",
        ),
        Dish(
            id = 14L,
            logoResId = R.drawable.dish_image_14,
            name = "Сэндвич \"Главное не ошибиться\"",
            description = "Кусок мяса, два куска хлеба и горчица. Да, и точно — ничего больше!"
        ),
        Dish(
            id = 15L,
            logoResId = R.drawable.dish_image_15,
            name = "Попробуй до завтрака",
            description = "Блинчики с острым малиновым джемом и пряным сыром, на которые точно не захочется опоздать.",
        ),
        Dish(
            id = 16L,
            logoResId = R.drawable.dish_image_16,
            name = "Лосось \"Северное сияние\"",
            description = "Лосось под кремово-васаби соусом, сверкающим, как битники у северного полюса.",
        ),
        Dish(
            id = 17L,
            logoResId = R.drawable.dish_image_17,
            name = "Чайковский на углях",
            description = "Говяжий стейк под малиновым соусом, столь музыкально насыщенным, что его композиция напоминает великую симфонию.",
        ),
        Dish(
            id = 18L,
            logoResId = R.drawable.dish_image_18,
            name = "Десерт \"С видом на закат\"",
            description = "Золотистая карамельная запеканка с морковью и горькими орехами. Идеально на ужине при закате.",
        ),
        Dish(
            id = 19L,
            logoResId = R.drawable.dish_image_19,
            name = "Каша в кубе",
            description = "Овсянка в необычной кубической форме с йогуртом и ягодами, моментально заставит задуматься о геометрии вкуса.",
        ),
        Dish(
            id = 20L,
            logoResId = R.drawable.dish_image_20,
            name = "Смузи \"Три поросенка\"",
            description = "Напиток из трех розовых слоев: клубника, арбуз и розовый грейпфрут. Каждый слой ещё и с сюрпризом!",
        ),
    )


    val restaurants = listOf(
        Restaurant(
            id = 0L,
            logoResId = R.drawable.restaurant_logo_1,
            name = "СуперСуперСуп",
            description = """
                Этот ресторан предлагает супы всего мира, приготовленные супергероями шеф-поварами, которые уверены, что даже Бэтмен предпочел бы их супы летать в небе.
            """.trimIndent(),
            isStarred = true,
            dishes = getRandomDishes(),
        ),
        Restaurant(
            id = 1L,
            logoResId = R.drawable.restaurant_logo_2,
            name = "Макарони на уши",
            description = """
                Вы найдете здесь блюда из пасты, которые не только удовлетворят аппетит, но и заставят вас задуматься, зачем вы раньше ели простую лапшу.
            """.trimIndent(),
            dishes = getRandomDishes(),
        ),
        Restaurant(
            id = 2L,
            logoResId = R.drawable.restaurant_logo_3,
            name = "Борщ и не только",
            description = """
                Начав с классического борща и переходя к кулинарным экспериментам от борщ-бургеров до борщ-мороженого, этот ресторан устроит ваш желудок в захватывающее приключение.
            """.trimIndent(),
            dishes = getRandomDishes(),
        ),
        Restaurant(
            3L,
            logoResId = R.drawable.restaurant_logo_4,
            name = "Рыба моей мечты",
            description = """
                Каждый гость получает свою уникальную порцию рыбы мечты — освежающая загадка для ваших вкусовых рецепторов и разговорная тема вечера.
            """.trimIndent(),
            dishes = getRandomDishes(),
        ),
        Restaurant(
            id = 4L,
            logoResId = R.drawable.restaurant_logo_5,
            name = "Салат и балет",
            description = """
                Мы превращаем прием пищи в выставку искусства, где каждое блюдо сопровождается мини-постановкой от нашего кулинарного балетного ансамбля.
            """.trimIndent(),
            isStarred = true,
            dishes = getRandomDishes(),
        ),
        Restaurant(
            id = 5L,
            logoResId = R.drawable.restaurant_logo_6,
            name = "Пирожковая лавка: Да хрюкни ты!",
            description = """
                Этот ресторан комбинирует традиционные мясные пироги и актуальные темы разговора, чтобы вы могли хрюкать от удовольствия за каждым столом.
            """.trimIndent(),
            dishes = getRandomDishes(),
        ),
    )

    private fun getRandomDishes() = dishes.shuffled().take((3..7).random())
}
