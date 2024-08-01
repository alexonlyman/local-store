Описание проекта: 
Local Store - это проект, состоящий из двух модулей: manager-app и catalogue-service. Этот проект использует Spring Boot и предназначен для управления локальным магазином.
Структура проекта
Проект состоит из двух основных модулей:

manager-app: Приложение для управления магазином
catalogue-service: Сервис каталога товаров

Требования

Java 19
Maven 3.x
PostgreSQL

Зависимости
Проект использует следующие основные зависимости:

Spring Boot 3.2.6
Spring Security
Spring Data JPA
Thymeleaf
PostgreSQL
Flyway
Lombok
WireMock (для тестирования)

Настройка и запуск

Клонируйте репозиторий
Убедитесь, что у вас установлены Java 19 и Maven
Настройте подключение к базе данных PostgreSQL в файле application.properties каждого модуля
Выполните команду для сборки проекта:
Copymvn clean install

Запустите каждый модуль отдельно:
Copyjava -jar manager-app/target/manager-app-1.0-SNAPSHOT.jar
java -jar catalogue-service/target/catalogue-service-1.0-SNAPSHOT.jar


Разработка
Проект использует Spring Boot DevTools для упрощения разработки. Для запуска в режиме разработки используйте:
Copymvn spring-boot:run
Тестирование
Для запуска тестов выполните:
Copymvn test
Дополнительная информация

Проект использует Flyway для миграции базы данных
OAuth2 используется для аутентификации и авторизации
Lombok применяется для уменьшения шаблонного кода
