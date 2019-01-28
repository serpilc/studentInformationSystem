-- phpMyAdmin SQL Dump
-- version 4.4.15.5
-- http://www.phpmyadmin.net
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 26 Ara 2016, 17:16:29
-- Sunucu sürümü: 5.6.30
-- PHP Sürümü: 5.5.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `student_information`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `absenteeism`
--

CREATE TABLE IF NOT EXISTS `absenteeism` (
  `ID` int(11) NOT NULL,
  `studentID` int(11) NOT NULL,
  `lessonID` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `absenteeism`
--

INSERT INTO `absenteeism` (`ID`, `studentID`, `lessonID`) VALUES
(2, 1, 1),
(3, 1, 1),
(4, 1, 2),
(5, 1, 4),
(6, 1, 3),
(7, 1, 1),
(8, 1, 1),
(9, 5, 10),
(10, 1, 1),
(11, 1, 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `accounts`
--

CREATE TABLE IF NOT EXISTS `accounts` (
  `ID` int(11) NOT NULL,
  `accountType` varchar(15) NOT NULL DEFAULT 'student',
  `password` varchar(50) NOT NULL,
  `name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `accounts`
--

INSERT INTO `accounts` (`ID`, `accountType`, `password`, `name`, `surname`) VALUES
(1, 'student', '12345', 'kutay', 'yavuz'),
(3, 'lecturer', '12345', 'abdül', 'rezzak'),
(4, 'admin', '12345', 'zama', 'zumba'),
(5, 'student', '12345', 'Elif', 'Soykam'),
(6, 'student', '1234', '1234', '4321');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `documents`
--

CREATE TABLE IF NOT EXISTS `documents` (
  `ID` int(11) NOT NULL,
  `studentID` int(11) NOT NULL,
  `doc` varchar(30) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `documents`
--

INSERT INTO `documents` (`ID`, `studentID`, `doc`) VALUES
(1, 1, 'studentDocument'),
(2, 1, 'internDocument');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `grades`
--

CREATE TABLE IF NOT EXISTS `grades` (
  `ID` int(11) NOT NULL,
  `studentID` int(11) NOT NULL,
  `lessonID` int(11) NOT NULL,
  `grade` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `grades`
--

INSERT INTO `grades` (`ID`, `studentID`, `lessonID`, `grade`) VALUES
(1, 1, 1, 100),
(2, 5, 10, 100),
(3, 1, 1, 100),
(4, 1, 1, 40),
(5, 1, 1, 5);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `lessons`
--

CREATE TABLE IF NOT EXISTS `lessons` (
  `ID` int(11) NOT NULL,
  `code` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `lessons`
--

INSERT INTO `lessons` (`ID`, `code`, `name`) VALUES
(1, 'se115', 'Introduction to programming'),
(2, 'se480', 'javascript'),
(3, 'ce308', 'computing theory'),
(4, 'him340', 'ethics decision'),
(5, 'math240', 'probability'),
(9, 'math250', 'linear algebra'),
(10, 'm603', 'Dahiliye');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `messages`
--

CREATE TABLE IF NOT EXISTS `messages` (
  `ID` int(11) NOT NULL,
  `fromID` int(11) NOT NULL,
  `toID` int(11) NOT NULL,
  `message` text NOT NULL,
  `isRead` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `messages`
--

INSERT INTO `messages` (`ID`, `fromID`, `toID`, `message`, `isRead`) VALUES
(1, 1, 1, 'asdadadad', 1),
(2, 1, 1, '1', 1),
(3, 1, 1, 'asdasdad', 1),
(4, 1, 3, 'asdsada', 1),
(5, 1, 3, 'asdsadsadad', 1),
(6, 1, 3, 'adsadad adsadasdsad adsadasdsadqweqwe', 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `students_lessons`
--

CREATE TABLE IF NOT EXISTS `students_lessons` (
  `ID` int(11) NOT NULL,
  `studentID` int(11) NOT NULL,
  `lessonID` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `students_lessons`
--

INSERT INTO `students_lessons` (`ID`, `studentID`, `lessonID`) VALUES
(1, 1, 1),
(2, 3, 1);

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `absenteeism`
--
ALTER TABLE `absenteeism`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `studentID` (`studentID`),
  ADD KEY `lessonID` (`lessonID`);

--
-- Tablo için indeksler `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`ID`);

--
-- Tablo için indeksler `documents`
--
ALTER TABLE `documents`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `studentID` (`studentID`);

--
-- Tablo için indeksler `grades`
--
ALTER TABLE `grades`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `studentID` (`studentID`),
  ADD KEY `lessonID` (`lessonID`);

--
-- Tablo için indeksler `lessons`
--
ALTER TABLE `lessons`
  ADD PRIMARY KEY (`ID`);

--
-- Tablo için indeksler `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `fromID` (`fromID`),
  ADD KEY `toID` (`toID`);

--
-- Tablo için indeksler `students_lessons`
--
ALTER TABLE `students_lessons`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `studentID` (`studentID`),
  ADD KEY `lessonID` (`lessonID`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `absenteeism`
--
ALTER TABLE `absenteeism`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- Tablo için AUTO_INCREMENT değeri `accounts`
--
ALTER TABLE `accounts`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- Tablo için AUTO_INCREMENT değeri `documents`
--
ALTER TABLE `documents`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Tablo için AUTO_INCREMENT değeri `grades`
--
ALTER TABLE `grades`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- Tablo için AUTO_INCREMENT değeri `lessons`
--
ALTER TABLE `lessons`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- Tablo için AUTO_INCREMENT değeri `messages`
--
ALTER TABLE `messages`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- Tablo için AUTO_INCREMENT değeri `students_lessons`
--
ALTER TABLE `students_lessons`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `absenteeism`
--
ALTER TABLE `absenteeism`
  ADD CONSTRAINT `absenteeism_ibfk_1` FOREIGN KEY (`studentID`) REFERENCES `accounts` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `absenteeism_ibfk_2` FOREIGN KEY (`lessonID`) REFERENCES `lessons` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Tablo kısıtlamaları `documents`
--
ALTER TABLE `documents`
  ADD CONSTRAINT `documents_ibfk_1` FOREIGN KEY (`studentID`) REFERENCES `accounts` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Tablo kısıtlamaları `grades`
--
ALTER TABLE `grades`
  ADD CONSTRAINT `grades_ibfk_1` FOREIGN KEY (`studentID`) REFERENCES `accounts` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `grades_ibfk_2` FOREIGN KEY (`lessonID`) REFERENCES `lessons` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Tablo kısıtlamaları `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`fromID`) REFERENCES `accounts` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`toID`) REFERENCES `accounts` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Tablo kısıtlamaları `students_lessons`
--
ALTER TABLE `students_lessons`
  ADD CONSTRAINT `students_lessons_ibfk_1` FOREIGN KEY (`studentID`) REFERENCES `accounts` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `students_lessons_ibfk_2` FOREIGN KEY (`lessonID`) REFERENCES `lessons` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
