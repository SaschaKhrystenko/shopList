# shopList
Есть список магазинов. У каждого магазина есть список категорий, которые уникальны для каждого магазина (2-3 на магазина достаточно). У каждой категории есть товары (поля: Title, Price, Status). Товар может иметь статусы: Available, Absent, Expected. 

SQL
Написать SQL-скрипт для создания базы данных и хранения данных в предоставленной задаче. В этом же скрипте написать заполнение магазинов, категорий, статусов. Структура базы на Ваше усмотрение. 
СерверБД: MySQL server 5.7 (user:test, password: test).
Java
Написать фабричный метод, который будет возвращать классы магазинов. Классы магазинов должны быть Singleton. Класс магазинов должен иметь методы получения, добавления продуктов, изменения их статусов и цены в базе данных. В  Main класе создать магазины, запустить их с интервалом в 10 секунд в разных потоках.

В каждом потоке:
• Записать по 3-4 продукта в категории;
• В какой-то из категорий изменить статусы всех товаров на «Absent» ,  половине товаров, из оставшихся категорий, изменить статус на «Expected».
• По товарам, что доступны увеличить цену на 20%;
• Дождаться завершения всех потоков– вывести сообщение о завершении работы;
Сборка проекта Maven(dependency mysql-connector-java), Libraries: JDK 1.8, JDBC API, IDE предпочтительно Intellij IDEA
