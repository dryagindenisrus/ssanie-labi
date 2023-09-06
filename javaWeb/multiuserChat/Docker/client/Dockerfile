# Используем базовый образ с JDK
FROM openjdk:8-jdk

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем исходный код клиента в контейнер
COPY src/Client.java /app

# Компилируем клиента
RUN javac Client.java
