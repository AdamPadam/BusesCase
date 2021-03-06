# Задание:
* Есть автобусы, которые проезжают туннель и после едут к остановке для посадки различных пассажиров.
* Они проезжают узкий туннель за случайное время от ```A``` до ```B``` ms, где может одновременно может поместиться ```N``` автобусов. Автобусов будет ```M```. Пусть они появляются из "Генератора автобусов".
* Автобус может перевозить только один вид пассажиров, назовем это "Тип автобуса". Существует ```i``` видов пассажиров и ```j``` размеров автобуса. Итого ```i*j``` разных автобусов.
* Далее есть ```i``` видов остановок для каждого вида пассажира. На каждой остановке в секунду может сесть ```X``` пассажиров. В автобус садиться максимальное количество пассажиров.

# Требование к коду:
* Правильно разбить задачу на параллельность.
* Синхронизировать потоки, сохранить целостность данных.
* Работа генератора кораблей не должна зависеть от работы причалов и наоборот.
* Общий ресурс должен быть Thread Safe (Если таковой есть в реализации).
* Потоки не должны быть активными если нет задач.
* Потоки не должны держать mutex если нет задач.

# Для примера я взял такие значения:
`A = 250 (минимальное время появления)`

`B = 600 (максимальное время появления)`

`N = 5 (Место в туннеле)`

`M = 10 (Количество автобусов)`

`X = 10 (пассажиров в секунду)`

и поместил их в файл CONF.java.

Отдельно, в `Package: types` я создал два `enum` класса `Type.java` и `Size.java`. Там я реализовал размеры автобусов и типы перевозимых пассажиров.
