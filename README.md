# Отчёт по лабораторной работе

## Описание
Проект реализует вычисление системы функций и её модулей с разбиением на тригонометрическую и логарифмическую ветви.

Система:

```text
f(x) = {
  ((((sec(x) - sec(x)) + sec(x) * sin(x)) * cos(x)) - (sin(x) * csc(x))) / tan(x),  x <= 0
  ((((log2(x) - log2(x)) + log2(x) * log10(x)) * log3(x)) - (log3(x) * ln(x))) / log3(x), x > 0
}
```

## Требования задания
1. Все составные функции должны быть выражены через базовые.
2. Базовые функции `sin(x)` и `ln(x)` должны быть реализованы численно с параметром точности `eps`.
3. Экспорт значений модулей в CSV должен поддерживать произвольный шаг по `x`.
4. Необходимо модульное и интеграционное тестирование с поэтапной интеграцией.

## Реализация модулей
Базовые:
1. `sin(x)` — ряд Тейлора.
2. `ln(x)` — степенной ряд через преобразование `z = (x - 1) / (x + 1)`.

Составные:
1. `cos(x) = sin(x + π/2)`
2. `tan(x) = sin(x) / cos(x)`
3. `sec(x) = 1 / cos(x)`
4. `csc(x) = 1 / sin(x)`
5. `log2(x) = ln(x) / ln(2)`
6. `log3(x) = ln(x) / ln(3)`
7. `log10(x) = ln(x) / ln(10)`

## Области определения
1. `sin(x)`, `cos(x)` — все `x`.
2. `tan(x)`, `sec(x)` — `x != π/2 + πk`.
3. `csc(x)` — `x != πk`.
4. `ln(x)`, `log2(x)`, `log3(x)`, `log10(x)` — `x > 0`.
5. Для логарифмической ветви `f(x)` дополнительно исключается `x = 1`, так как `log3(1) = 0` (деление на ноль в формуле системы).

## Структура проекта
```text
src/main/java/org/example
  AbstractFunction.java
  Function.java
  CsvExporter.java
  Main.java
  math/
    Sin.java Cos.java Tan.java Sec.java Csc.java Ln.java Log2.java Log3.java Log10.java

src/test/java/org/example
  FunctionTest.java
  IntegrationTest.java
  testutil/CsvTestData.java
  math/*Test.java

src/test/resources/testdata
  function/*.csv
  integration/*.csv
  math/*.csv
  main/output-files.csv
```

## Тестирование
### Подход
Используется стратегия поэтапной интеграции `bottom-up`:
1. Проверка базовых модулей (`sin`, `ln`).
2. Подключение зависимых тригонометрических модулей.
3. Подключение логарифмических модулей.
4. Финальная проверка полной системы `Function`.

### Изоляция зависимостей
В модульных тестах и части системных сценариев используется Mockito (`mock`, `when(...).thenReturn(...)`) вместо табличных stub-классов.

### Наборы данных
Тестовые значения хранятся в CSV в `src/test/resources/testdata`.

## Экспорт CSV
`Main` формирует CSV-файлы в директорию `csv-output`:
1. `sin.csv`, `cos.csv`, `tan.csv`, `sec.csv`, `csc.csv`
2. `ln.csv`, `log2.csv`, `log3.csv`, `log10.csv`
3. `system.csv`

## Сборка и запуск
Требования:
1. JDK 17+
2. Maven 3.8+

Команды:

```bash
mvn clean test
mvn -DskipTests package
mvn -q exec:java -Dexec.mainClass="org.example.Main"
```

Если `exec-maven-plugin` не подключён, запустить можно так:

```bash
mvn -DskipTests package
java -cp target/function-system-1.0-SNAPSHOT.jar org.example.Main
```

## Покрытие
Отчёт JaCoCo формируется после `mvn test`:

```text
target/site/jacoco/index.html
target/site/jacoco/jacoco.csv
```

## Вывод
Проект реализует систему функций с численными базовыми модулями, поддерживает CSV-экспорт, покрыт модульными и интеграционными тестами и соответствует выбранной стратегии поэтапной интеграции.
