# Используем базовый образ с JDK
FROM openjdk:8-jdk

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем исходный код сервера в контейнер
COPY src/Server.java /app

# Компилируем сервер
RUN javac Server.java

# Открываем порт 4000 для сервера
EXPOSE 4000

