# DEMOQA — UI-автоматизация тестирования формы регистрации

Проект автоматизированного тестирования формы [Student Registration Form](https://demoqa.com/automation-practice-form) на сайте demoqa.com. Демонстрирует навыки написания поддерживаемых UI-тестов на Java с использованием паттерна Page Object Model, генерации случайных тестовых данных и формирования отчётов Allure.

## Стек

| Инструмент | Назначение |
|------------|------------|
| Java 17+ | Язык |
| JUnit Jupiter 5.10 | Тестовый фреймворк |
| Selenide 7.2 | Обёртка над Selenium (авто-ожидания, скриншоты, лаконичный API) |
| Allure 2.27 | Система отчётов с шагами, скриншотами и видео |
| Allure Selenide | Интеграция Selenide ↔ Allure (авто-логирование действий) |
| JavaFaker 1.0 | Генерация случайных тестовых данных |
| Gradle (wrapper) | Сборка |
| Selenoid | Удалённый запуск браузеров в Docker-контейнерах с VNC и записью видео |

## Возможности проекта

- **Три тестовых сценария**: полное заполнение всех полей, только обязательные поля, негативный кейс с невалидным телефоном
- **Page Object Model** с декомпозицией на компоненты (`CalendarComponent`, `ResultTableComponent`)
- **Генерация случайных данных** через JavaFaker + кастомные методы (город зависит от выбранного штата)
- **Allure-отчёты** с пошаговым описанием на русском языке, скриншотами, логами браузера и видео прогона
- **Запуск в Selenoid** — удалённые браузеры с VNC-трансляцией и записью видео
- **Тегирование** (`@Tag("positive")`, `@Tag("negative")`, `@Tag("regression")`) для фильтрации запусков
- **Рефакторинг**: в проекте сохранён исходный тест без Page Object (`PracticeFormTestWithoutPO`, `@Disabled`) — демонстрирует эволюцию кода от «простыни» к поддерживаемой архитектуре

## Быстрый старт

```bash
# 1. Клонировать репозиторий
git clone https://github.com/darfl/demoqa.git && cd demoqa

# 2. Запустить все тесты локально (Chrome, оконный режим)
./gradlew test

# 3. Запустить тесты в Selenoid (удалённо)
remoteUrl="https://selenoid.autotests.cloud/wd/hub" ./gradlew test

# 4. Фильтрация по тегам
./gradlew test -Dtype=positive    # только позитивные
./gradlew test -Dtype=negative    # только негативные
./gradlew test -Dtype=regression  # все regression-тесты

# 5. Сгенерировать и открыть Allure-отчёт
./gradlew allureServe
```

## Структура проекта

```
src/test/java/
├── enums/ResultTableEnums.java                  # Enum с названиями полей таблицы результатов
├── helpers/AllureAttachments.java               # Скриншоты, page source, логи, видео
├── pages/
│   ├── RegistrationPage.java                    # Page Object формы регистрации
│   └── components/
│       ├── CalendarComponent.java               # Компонент календаря
│       └── ResultTableComponent.java            # Компонент таблицы с результатами
├── tests/
│   ├── TestBase.java                            # Конфигурация Selenide + Selenoid + Allure
│   ├── PracticeFormTest.java                    # 3 теста (positive / negative / required fields)
│   └── PracticeFormTestWithoutPO.java           # Исходный тест без Page Object (@Disabled)
└── utils/TestData.java                          # Генераторы тестовых данных (Faker + кастомные)
```

## Ключевые компетенции, демонстрируемые проектом

### 1. Паттерн Page Object Model с компонентным подходом

Логика страницы инкапсулирована в класс `RegistrationPage`. Повторяющиеся элементы (календарь, таблица результатов) вынесены в отдельные классы-компоненты — их можно переиспользовать на других страницах.

```java
public class RegistrationPage {
    CalendarComponent calendarComponent = new CalendarComponent();
    ResultTableComponent resultTableComponent = new ResultTableComponent();

    @Step("Ввести имя")
    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;  // fluent interface — цепочки вызовов
    }
}
```

### 2. Человекочитаемые Allure-отчёты

Каждый метод страницы аннотирован `@Step` с описанием на русском языке. В отчёте Allure виден не стек вызовов, а пошаговый сценарий:

```
✔ Открыть страницу /automation-practice-form и скрыть footer
✔ Ввести имя: John
✔ Ввести фамилию: Doe
✔ Указать пол: Male
✔ Нажать кнопку submit
✔ Проверить содержание таблицы с введенными данными
```

### 3. Интеграция с Selenoid

Тесты запускаются удалённо в Docker-контейнерах через Selenoid. `TestBase` конфигурирует capabilities для VNC и видео, а `AllureAttachments.addVideo()` встраивает видеозапись прямо в Allure-отчёт.

### 4. Генерация тестовых данных с зависимостями

`TestData` не просто генерирует случайные значения — город выбирается **в зависимости от штата**:

```java
public String getRandomCity() {
    if (state.equals("NCR")) city = faker.options().option("Delhi", "Gurgaon", "Noida");
    if (state.equals("Uttar Pradesh")) city = faker.options().option("Agra", "Lucknow", "Merrut");
    // ...
    return city;
}
```

Это гарантирует, что данные всегда будут валидными с точки зрения логики формы.

### 5. Гибкая конфигурация через системные свойства

Браузер, его версия, разрешение окна и адрес Selenoid задаются через параметры командной строки — не нужно редактировать код для смены окружения:

```bash
./gradlew test -Dbrowser=firefox -Dversion=125 -DwindowSize=1366x768
```

### 6. Тегирование и фильтрация тестов

Тесты размечены тегами `@Tag("positive")`, `@Tag("negative")`, `@Tag("regression")`. В `build.gradle` настроен запуск по тегу через системное свойство `type` — удобно для CI-пайплайнов.

### 7. Рефакторинг и эволюция кода

Класс `PracticeFormTestWithoutPO` (с аннотацией `@Disabled`) сохранён в проекте — это исходный тест до рефакторинга. Можно наглядно сравнить «простыню» из `$("#firstName").setValue(userName)` с лаконичным `registrationPage.setFirstName(testData.firstName)`.

### 8. Аттачменты после каждого теста

После каждого теста в Allure-отчёт автоматически прикрепляются:
- **Скриншот** последнего состояния страницы
- **Page Source** (HTML-код страницы на момент падения)
- **Логи браузера** (ошибки JavaScript)
- **Видео** прогона из Selenoid

### 9. Selenide: авто-ожидания и лаконичный API

Selenide автоматически ждёт появления элемента перед взаимодействием — не нужны явные `WebDriverWait`. Селекторы используют CSS и текст:

```java
$("#firstName").setValue(value);
genderWrapper.$(byText(value)).click();
resultTitle.shouldHave(exactText("Thanks for submitting the form"));