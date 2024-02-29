# GetUsersApp #
## Описание ##
Клиент-серверное приложение, получает список сотрудников через Api (https://kode-education.stoplight.io/docs/trainee-test/b3A6MjUxNDM5Mjg-get-users), 
отображает список с учетом сортировок и поискового запроса.

Основные функции:
+ просмотр страницы деталей сотрудника
+ сортировка по департаментам в TabLayout
+ поиск по имени
+ сортировка  по алфавиту и дню рождения в диалоговом окне

Приложение написано на языке котлин, с применением паттерна MVVM и принципов чистой архитектуры и разделено на 3 модуля - app, domain, data.
Взаимодействие с сервером осуществляеся с помощью библиотеки Retrofit, okHttp.

## Стек ##
Kotlin | Retrofit | OkHttp | GSON | MVVM | Dagger-Hilt | XML | Jetpack navigation | Kotlin Coroutine | Clean Architecture 
## Скриншоты ##

[![app01.jpg](https://i.postimg.cc/HkhQW9Bd/app01.jpg)](https://postimg.cc/qN2tjnP5) [![app02.jpg](https://i.postimg.cc/FzpLMyYt/app02.jpg)](https://postimg.cc/kRVG8tNw) [![app03.jpg](https://i.postimg.cc/050DKsct/app03.jpg)](https://postimg.cc/c66rPVqY)

[![app04.jpg](https://i.postimg.cc/264vwRmS/app04.jpg)](https://postimg.cc/njhCFWCy) [![app05.jpg](https://i.postimg.cc/qRfyyBPF/app05.jpg)](https://postimg.cc/4K5YTGNv) [![app06.jpg](https://i.postimg.cc/Zqv6WvM3/app06.jpg)](https://postimg.cc/67w8Sqw5)

[![app07.jpg](https://i.postimg.cc/Bb4F7YQc/app07.jpg)](https://postimg.cc/Kkp4R5TR) [![app08.jpg](https://i.postimg.cc/Gh3Yk5fb/app08.jpg)](https://postimg.cc/qgYzrj0F) [![app09.jpg](https://i.postimg.cc/tCr6Dz0W/app09.jpg)](https://postimg.cc/SJMjxCxx)
