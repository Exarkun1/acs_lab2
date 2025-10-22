# Практическая работа №2
*Выполнили студенты группы 6132-010402D Казаков П.А. и Маъруфжонов А.А.*
## Приложение на Spring
### Общее задание
Необходимо разработать приложение, используя типовую архитектуру и технологии Spring. Оно должно иметь три слоя (данные, логика, представление) и предоставлять средства для работы с базой данных.
#### Задание 1
Была выбрана та же предметная область, что и в первой лабораторной работе: **библиотека**.

Скрипты для создания таблиц **"Person"** и **"Book"** в **PostgreSQL**:
```
CREATE TABLE person (
    id bigint NOT NULL,
    full_name text NOT NULL,
    year_of_birth bigint NOT NULL,
    email text NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE book (
    id bigint NOT NULL,
    title text NOT NULL,
    author text NOT NULL,
    status text NOT NULL,
    person_id bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (person_id)
        REFERENCES "Person" (id)
);
```

#### Задание 2
В файле application.properties прописаны параметры конфигурации для подключения к базе данных **PostgreSQL**. Реализованы две сущности: **Person** и **Book**.
Практически всё в них идентично предыдущей лабораторной работе. Учтено использование значений из конструктора **BookStatus** в thymeleaf-шаблонах.

Доступ к данным осуществляется через классы репозиториев **PersonRepository** и **BookRepository** реализующие интерфейс `JpaRepository`.

#### Задание 3
Сервисный слой был реализован с использованием `@Service` аннотации для обеспечения бизнес-логики. Также созданы сервисы **PersonService** и **BookService**, которые используют репозитории для работы с базой данных.

#### Задание 4
Web-слой был реализован с использованием **Spring MVC**. Были созданы контроллеры для обработки запросов и отображения данных в представлениях **HelloController**, **PersonController** и **BookController**. Контроллеры используют аннотацию `@Controller` и методы с аннотациями `@GetMapping`, `@PostMapping`, `@PatchMapping` и `@DeteleMapping`.

Также использовался шаблонизатор **Thymeleaf** для генерации HTML-страниц.

#### Задание 5
После запуска сервера, приложение можно открыть в браузере. Страницы аналогичны предыдущей работе, за исключением новой, для присвоения книги пользователю:

![image](assets/assigned-book.png)
![image](assets/not-assigned-book.png)