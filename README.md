<h1 align="center">Приветствую, меня зовут<a href="https://daniilshat.ru/" target="_blank"> Владимиров Владислав</a> 
<img src="https://github.com/blackcater/blackcater/raw/main/images/Hi.gif" height="32"/></h1>
<h3 align="center">Я начинающий Android разработчик</h3>

Данный проект представляет собой небольшое приложение доставки блюд. Проект выполнен в рамках прохождения тестового задания от компании  Request Design

[Тестовое задание](https://docs.google.com/document/d/1_GwEDzsLOt1jEllWbIvKGRz88OHj5SnTmXF-CCwwS2w/edit)

[Дизайн-макет в Figma](https://www.figma.com/file/AgOZovMHsetEejeuF33vkw/Foodies?type=design&mode=design&t=9mYRe9OgoAEEEit2-0) 


Используется следующий стек технологий:
- Архитектура MVVM
- Architecture Components (ViewModel, LiveData)
- Jetpack Compose
- Clean Arrchitectire
- Coroutines
- Navigation with Jetpack Compose
- Okhttp
- Hilt (Dagger)

Приложение загружает с сервера список блюд, категории блюд и теги (острый, вегетарианский и т.д).
Блюда разделены на категории, можно фильтровать список блюд по тегам.
Блюда отображаются в каталоге в виде списка из двух столбцов, блюда можно добавлять в корзину, менять их количество. 

С экрана каталога можно перейти на карточку конкретного блюда, где можно ознакомиться более подробно с описанием и добавить в корзину. 

На экране поиска можно искать блюда по названию.

Приложение включает в себя следующиие экраны:
- Каталог блюд
- Поиск по блюдам
- Карточка блюда
- Корзина

В будущих обновлениях планируется реализовать:
- Экран корзины

