-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Янв 09 2018 г., 04:52
-- Версия сервера: 5.6.38
-- Версия PHP: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `notes`
--

-- --------------------------------------------------------

--
-- Дублирующая структура для представления `custom_view`
-- (См. Ниже фактическое представление)
--
CREATE TABLE `custom_view` (
`Title` varchar(255)
,`Text` longtext
,`Creation_Date` date
,`Author_id` int(10)
,`User_Name` varchar(50)
,`User_mail` varchar(25)
,`Privileges` int(5)
);

-- --------------------------------------------------------

--
-- Структура таблицы `notes`
--

CREATE TABLE `notes` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `creation_date` date NOT NULL,
  `text` longtext NOT NULL,
  `author_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `notes`
--

INSERT INTO `notes` (`id`, `title`, `creation_date`, `text`, `author_id`) VALUES
(1, 'Фанфик', '2011-04-20', 'Где-то далеко в дали, я вдруг увидел лес, который показался мне реальностью.\r\n        Однако, я твердо осознавал, что нахожусь среди прасторов Оренбургскиих степей.', 3),
(2, 'Я', '1997-09-25', 'aieriojeoirjgepoirjgsemngmmjsrelbm[seo,berohnmaoeimn', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `mail` varchar(25) DEFAULT NULL,
  `encrypted_password` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `name`, `mail`, `encrypted_password`) VALUES
(1, 'Ilya', 'ezatarri-8558@yopmail.com', 'fTMYmwwB'),
(2, 'Lena', 'Lena@gmail.com', 'QJysMYI4QYosHc2'),
(3, 'Pavel', 'anninnadde-5798@yopmail.c', 'eROBf3zWjxfE6va'),
(4, 'Vlad', 'wacedduva-5775@yopmail.co', '934vqVSLfGjaDHm');

-- --------------------------------------------------------

--
-- Структура таблицы `users_privileges`
--

CREATE TABLE `users_privileges` (
  `id` int(11) NOT NULL,
  `privilege` int(5) NOT NULL,
  `users_id` int(10) NOT NULL,
  `notes_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users_privileges`
--

INSERT INTO `users_privileges` (`id`, `privilege`, `users_id`, `notes_id`) VALUES
(1, 1, 1, 1),
(2, 2, 2, 1),
(3, 2, 3, 2),
(4, 1, 2, 2),
(5, 1, 4, 1);

-- --------------------------------------------------------

--
-- Структура для представления `custom_view`
--
DROP TABLE IF EXISTS `custom_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `custom_view`  AS  select `title` AS `Title`,`text` AS `Text`,`creation_date` AS `Creation_Date`,`author_id` AS `Author_id`,`users`.`name` AS `User_Name`,`users`.`mail` AS `User_mail`,`users_privileges`.`privilege` AS `Privileges` from ((`notes` join `users_privileges`) join `users`) where ((`users`.`id` = `users_privileges`.`users_id`) and (`users_privileges`.`notes_id` = `id`)) ;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `notes`
--
ALTER TABLE `notes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_notes_users_idx` (`author_id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users_privileges`
--
ALTER TABLE `users_privileges`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_users_privileges_users1_idx` (`users_id`),
  ADD KEY `fk_users_privileges_notes1_idx` (`notes_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `notes`
--
ALTER TABLE `notes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `users_privileges`
--
ALTER TABLE `users_privileges`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `notes`
--
ALTER TABLE `notes`
  ADD CONSTRAINT `fk_notes_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ограничения внешнего ключа таблицы `users_privileges`
--
ALTER TABLE `users_privileges`
  ADD CONSTRAINT `fk_users_privileges_notes1` FOREIGN KEY (`notes_id`) REFERENCES `notes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_users_privileges_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
