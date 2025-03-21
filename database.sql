-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3307
-- Generation Time: Mar 21, 2025 at 11:08 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `demo`
--

-- --------------------------------------------------------

--
-- Table structure for table `marketplace`
--

CREATE TABLE `marketplace` (
  `id` int(11) NOT NULL,
  `seller` varchar(50) DEFAULT NULL,
  `player_ID` int(11) DEFAULT NULL,
  `bidder` varchar(50) DEFAULT NULL,
  `biddingPrice` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `marketplace`
--

INSERT INTO `marketplace` (`id`, `seller`, `player_ID`, `bidder`, `biddingPrice`) VALUES
(23, 'def', 51, NULL, 0),
(24, 'def', 51, 'abc', 190000),
(25, 'abc', 52, NULL, 0),
(26, 'abc', 53, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `match_history`
--

CREATE TABLE `match_history` (
  `ID` int(11) NOT NULL,
  `me` varchar(50) NOT NULL,
  `my_score` int(11) NOT NULL,
  `opponent` varchar(50) NOT NULL,
  `opponent_score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `match_history`
--

INSERT INTO `match_history` (`ID`, `me`, `my_score`, `opponent`, `opponent_score`) VALUES
(1, 'abc', 3, 'ahmed', 1),
(2, 'abc', 0, 'def', 3);

-- --------------------------------------------------------

--
-- Table structure for table `messenger`
--

CREATE TABLE `messenger` (
  `ID` int(11) NOT NULL,
  `sender` varchar(50) NOT NULL,
  `receiver` varchar(50) NOT NULL,
  `message_content` varchar(1000) NOT NULL,
  `time_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `messenger`
--

INSERT INTO `messenger` (`ID`, `sender`, `receiver`, `message_content`, `time_date`) VALUES
(1, 'abc', 'ahmed', 'efewf ef ef e ', '2025-01-26 17:48:11'),
(2, 'ahmed', 'abc', 'fesrv fe A ', '2025-01-26 17:59:24'),
(10, 'abc', 'ahmed', 'aw', '2025-01-26 19:48:47');

-- --------------------------------------------------------

--
-- Table structure for table `multiplayer`
--

CREATE TABLE `multiplayer` (
  `username` varchar(50) NOT NULL,
  `rank` int(11) NOT NULL,
  `score_value` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `multiplayer`
--

INSERT INTO `multiplayer` (`username`, `rank`, `score_value`) VALUES
('abc', 4, '0-10-30-20-50-70-40');

-- --------------------------------------------------------

--
-- Table structure for table `player_data`
--

CREATE TABLE `player_data` (
  `player_ID` int(11) NOT NULL,
  `player_base_id` int(11) NOT NULL,
  `img` varchar(500) NOT NULL,
  `name` varchar(50) NOT NULL,
  `position` varchar(5) NOT NULL,
  `ovr` int(11) NOT NULL,
  `country` varchar(100) NOT NULL,
  `price` int(11) NOT NULL,
  `base` varchar(5) NOT NULL,
  `sellable` varchar(5) NOT NULL DEFAULT 'YES',
  `in_market` varchar(5) NOT NULL DEFAULT 'YES',
  `level` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `player_data`
--

INSERT INTO `player_data` (`player_ID`, `player_base_id`, `img`, `name`, `position`, `ovr`, `country`, `price`, `base`, `sellable`, `in_market`, `level`) VALUES
(1, 1, 'https://www.zerozero.pt/img/jogadores/new/45/08/394508_kylian_mbappe_20240809095413.png', 'K. Mbappe', 'ST', 91, 'France', 250000, 'YES', 'YES', 'YES', 0),
(2, 2, 'https://www.zerozero.pt/img/jogadores/new/27/41/512741_erling_haaland_20240816210106.png', 'E. Haaland', 'ST', 91, 'Norway', 250000, 'YES', 'YES', 'YES', 0),
(3, 3, 'https://www.zerozero.pt/img/jogadores/new/05/69/180569_harry_kane_20241031111721.png', 'H. Kane', 'ST', 88, 'England', 220000, 'YES', 'YES', 'YES', 0),
(4, 4, 'https://www.zerozero.pt/img/jogadores/new/77/37/547737_vinicius_junior_20240809101116.png', 'Vinicius Jr', 'ST', 88, 'Brazil', 220000, 'YES', 'YES', 'YES', 0),
(5, 5, 'https://www.zerozero.pt/img/jogadores/new/10/23/71023_robert_lewandowski_20240914122345.png', 'R. Lewandawnski', 'ST', 87, 'Poland', 210000, 'YES', 'YES', 'YES', 0),
(6, 6, 'https://www.zerozero.pt/img/jogadores/new/75/88/207588_mohamed_salah_20240817000647.png', 'M. Salah', 'ST', 88, 'Egypt', 220000, 'YES', 'YES', 'YES', 0),
(7, 7, 'https://www.zerozero.pt/img/jogadores/new/41/40/254140_jack_grealish_20240816205850.png', 'J. Grealish', 'ST', 79, 'England', 120000, 'YES', 'YES', 'YES', 0),
(8, 8, 'https://www.zerozero.pt/img/jogadores/new/63/64/556364_rodrygo_20240809101231.png', 'Rodrygo', 'ST', 78, 'Brazil', 100000, 'YES', 'YES', 'YES', 0),
(9, 9, 'https://www.zerozero.pt/img/jogadores/new/61/57/606157_darwin_nunez_20240817000812.png', 'D. Nunez', 'ST', 77, 'Uruguay', 90000, 'YES', 'YES', 'YES', 0),
(10, 10, 'https://www.zerozero.pt/img/jogadores/new/29/13/12913_karim_benzema_20241125221422.png', 'K. Benzema', 'ST', 76, 'France', 80000, 'YES', 'YES', 'YES', 0),
(11, 11, 'https://www.zerozero.pt/img/jogadores/new/75/32/737532_jude_bellingham_20240809101052.png', 'J. Bellingham', 'CM', 89, 'England', 230000, 'YES', 'YES', 'YES', 0),
(12, 12, 'https://www.zerozero.pt/img/jogadores/new/68/72/26872_luka_modric_20240809101202.png', 'L. Modric', 'CM', 87, 'Croatia', 210000, 'YES', 'YES', 'YES', 0),
(13, 13, 'https://www.zerozero.pt/img/jogadores/new/34/42/383442_fede_valverde_20240809101139.png', 'F. Velverde', 'CM', 87, 'Uruguay', 210000, 'YES', 'YES', 'YES', 0),
(14, 14, 'https://www.zerozero.pt/img/jogadores/new/79/08/97908_kevin_de_bruyne_20240816210307.png', 'K. De Bruyne', 'CM', 91, 'Belgium', 250000, 'YES', 'YES', 'YES', 0),
(15, 15, 'https://www.zerozero.pt/img/jogadores/new/49/53/74953_bernardo_silva_20240816210349.png', 'B. Silva', 'CM', 88, 'Portugal', 220000, 'YES', 'YES', 'YES', 0),
(16, 16, 'https://www.zerozero.pt/img/jogadores/new/02/09/480209_phil_foden_20240816210038.png', 'P. Foden', 'CM', 86, 'England', 180000, 'YES', 'YES', 'YES', 0),
(17, 17, 'https://www.zerozero.pt/img/jogadores/new/77/39/107739_ilkay_gundogan_20240903102833.png', 'I. Gundogan', 'CM', 84, 'Germany', 150000, 'YES', 'YES', 'YES', 0),
(18, 18, 'https://www.zerozero.pt/img/jogadores/new/04/88/220488_bruno_fernandes_20240814235656.png', 'Bruno F.', 'CM', 76, 'Portugal', 80000, 'YES', 'YES', 'YES', 0),
(19, 19, 'https://www.zerozero.pt/img/jogadores/new/76/23/697623_enzo_fernandez_20240817021231.png', 'Enzo Fernandez', 'CM', 78, 'Argentina', 100000, 'YES', 'YES', 'YES', 0),
(20, 20, 'https://www.zerozero.pt/img/jogadores/new/96/13/739613_pedri_gonzalez_20240914123237.png', 'Pedri', 'CM', 79, 'Spain', 120000, 'YES', 'YES', 'YES', 0),
(21, 21, 'https://www.playmakerstats.com/img/jogadores/new/15/60/321560_rodrigo_de_paul_20240914120210.png', 'De Paul', 'CM', 78, 'Argentina', 100000, 'YES', 'YES', 'YES', 0),
(22, 22, 'https://www.zerozero.pt/img/jogadores/new/68/01/206801_antonio_rudiger_20240809100306.png', 'A. Rudigar', 'CB', 87, 'Germany', 210000, 'YES', 'YES', 'YES', 0),
(23, 23, 'https://www.zerozero.pt/img/jogadores/new/52/70/155270_ruben_dias_20240816210746.png', 'R. Dias', 'CB', 87, 'Portugal', 210000, 'YES', 'YES', 'YES', 0),
(24, 24, 'https://www.zerozero.pt/img/jogadores/new/56/15/195615_john_stones_20240816210826.png', 'J. Stones', 'CB', 85, 'England', 160000, 'YES', 'YES', 'YES', 0),
(25, 25, 'https://www.zerozero.pt/img/jogadores/new/27/74/92774_kyle_walker_20240816210809.png', 'K. Walker', 'CB', 84, 'England', 150000, 'YES', 'YES', 'NO', 0),
(26, 26, 'https://www.zerozero.pt/img/jogadores/new/44/14/394414_manuel_akanji_20240816210621.png', 'M. Akanji', 'CB', 79, 'Switzerland', 120000, 'YES', 'YES', 'YES', 0),
(27, 27, 'https://www.zerozero.pt/img/jogadores/new/90/14/149014_nathan_ake_20240816210717.png', 'N. Ake', 'CB', 77, 'Netharland', 90000, 'YES', 'YES', 'YES', 0),
(28, 28, 'https://www.zerozero.pt/img/jogadores/new/83/04/188304_harry_maguire_20240815220136.png', 'H. Maguire', 'CB', 75, 'England', 60000, 'YES', 'YES', 'YES', 0),
(29, 29, 'https://www.zerozero.pt/img/jogadores/new/62/32/436232_marc_cucurella_20240817014444.png', 'M. Cucurella', 'CB', 75, 'Spain', 60000, 'YES', 'YES', 'YES', 0),
(30, 30, 'https://www.zerozero.pt/img/jogadores/new/68/85/136885_ederson_moraes_20240816211041.png', 'Ederson', 'GK', 86, 'Brazil', 180000, 'YES', 'YES', 'YES', 0),
(31, 31, 'https://www.zerozero.pt/img/jogadores/new/56/14/95614_alisson_becker_20240816234831.png', 'Alisson', 'GK', 88, 'Brazil', 220000, 'YES', 'YES', 'YES', 0),
(32, 32, 'https://www.zerozero.pt/img/jogadores/new/56/28/95628_thibaut_courtois_20240809094141.png', 'Courtois', 'GK', 89, 'Belgium', 230000, 'YES', 'YES', 'YES', 0),
(33, 33, 'https://www.zerozero.pt/img/jogadores/new/98/74/379874_dominic_solanke_20240817013051.png', 'D. Solanke', 'ST', 70, 'England', 0, 'YES', 'NO', 'YES', 0),
(34, 34, 'https://www.zerozero.pt/img/jogadores/new/93/48/509348_alexander_isak_20240816001128.png', 'A. Isak', 'ST', 70, 'Sweden', 0, 'YES', 'NO', 'YES', 0),
(35, 35, 'https://www.zerozero.pt/img/jogadores/new/37/21/593721_noni_madueke_20240817020616.png', 'N. Nadueke', 'ST', 70, 'England', 0, 'YES', 'NO', 'YES', 0),
(36, 36, 'https://www.zerozero.pt/img/jogadores/new/20/84/452084_rodrigo_bentancur_20240817013033.png', 'R. Bentancur', 'CM', 70, 'Uruguay', 0, 'YES', 'NO', 'YES', 0),
(37, 37, 'https://www.zerozero.pt/img/jogadores/new/58/50/525850_weston_mckennie_20250118162302.png', 'W. McKennie', 'CM', 70, 'USA', 0, 'YES', 'NO', 'YES', 0),
(38, 38, 'https://www.zerozero.pt/img/jogadores/new/18/85/251885_julian_brandt_20241125134834.jpg', 'J. Brandt', 'CM', 70, 'Germany', 0, 'YES', 'NO', 'YES', 0),
(39, 39, 'https://www.zerozero.pt/img/jogadores/new/12/84/421284_vitinha_20241012232905.png', 'Vitinha', 'CB', 70, 'Portugal', 0, 'YES', 'NO', 'YES', 0),
(40, 40, 'https://www.zerozero.pt/img/jogadores/new/12/86/331286_lucas_hernandez_20241012234652.png', 'Lucas Hernández', 'CB', 70, 'France', 0, 'YES', 'NO', 'YES', 0),
(41, 41, 'https://www.zerozero.pt/img/jogadores/new/02/88/260288_clement_lenglet_20240913171950.png', 'C. Lenglet', 'CB', 70, 'France', 0, 'YES', 'NO', 'YES', 0),
(42, 42, 'https://www.zerozero.pt/img/jogadores/new/18/34/511834_ibrahima_konate_20240816235054.png', 'Konate', 'CB', 70, 'France', 0, 'YES', 'NO', 'YES', 0),
(43, 43, 'https://www.zerozero.pt/img/jogadores/new/79/61/127961_bernd_leno_20240816194324.png', 'B. Leno', 'GK', 70, 'Germany', 0, 'YES', 'NO', 'YES', 0),
(48, 2, 'https://www.zerozero.pt/img/jogadores/new/27/41/512741_erling_haaland_20240816210106.png', 'E. Haaland', 'ST', 94, 'Norway', 470000, 'NO', 'YES', 'NO', 3),
(49, 14, 'https://www.zerozero.pt/img/jogadores/new/79/08/97908_kevin_de_bruyne_20240816210307.png', 'K. De Bruyne', 'CM', 93, 'Belgium', 350000, 'NO', 'YES', 'NO', 2),
(50, 2, 'https://www.zerozero.pt/img/jogadores/new/27/41/512741_erling_haaland_20240816210106.png', 'E. Haaland', 'ST', 92, 'Norway', 450000, 'NO', 'YES', 'YES', 1),
(51, 15, 'https://www.zerozero.pt/img/jogadores/new/49/53/74953_bernardo_silva_20240816210349.png', 'B. Silva', 'CM', 90, 'Portugal', 200000, 'NO', 'YES', 'YES', 2),
(52, 2, 'https://www.zerozero.pt/img/jogadores/new/27/41/512741_erling_haaland_20240816210106.png', 'E. Haaland', 'ST', 92, 'Norway', 320000, 'NO', 'YES', 'YES', 1),
(53, 31, 'https://www.zerozero.pt/img/jogadores/new/56/14/95614_alisson_becker_20240816234831.png', 'Alisson', 'GK', 89, 'Brazil', 300000, 'NO', 'YES', 'YES', 1);

-- --------------------------------------------------------

--
-- Table structure for table `team_player`
--

CREATE TABLE `team_player` (
  `id` int(11) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `player_ID` int(11) DEFAULT NULL,
  `player_base_id` int(11) NOT NULL,
  `active` varchar(10) NOT NULL DEFAULT 'NO',
  `pos` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `team_player`
--

INSERT INTO `team_player` (`id`, `username`, `player_ID`, `player_base_id`, `active`, `pos`) VALUES
(52, 'abc', 33, 33, 'NO', 2),
(53, 'abc', 34, 34, 'NO', 1),
(54, 'abc', 35, 35, 'NO', 2),
(55, 'abc', 36, 36, 'YES', 1),
(56, 'abc', 37, 37, 'NO', 2),
(57, 'abc', 38, 38, 'NO', 3),
(58, 'abc', 39, 39, 'YES', 2),
(59, 'abc', 40, 40, 'NO', 3),
(60, 'abc', 41, 41, 'YES', 4),
(61, 'abc', 42, 42, 'YES', 1),
(62, 'abc', 43, 43, 'NO', 0),
(65, 'ahmed', 33, 33, 'YES', 1),
(66, 'ahmed', 34, 34, 'YES', 3),
(67, 'ahmed', 35, 35, 'NO', 0),
(68, 'ahmed', 36, 36, 'YES', 3),
(69, 'ahmed', 37, 37, 'YES', 2),
(70, 'ahmed', 38, 38, 'YES', 1),
(71, 'ahmed', 39, 39, 'YES', 4),
(72, 'ahmed', 40, 40, 'YES', 1),
(73, 'ahmed', 41, 41, 'YES', 3),
(74, 'ahmed', 42, 42, 'YES', 2),
(75, 'ahmed', 43, 43, 'YES', 0),
(76, 'def', 48, 2, 'NO', 0),
(77, 'ahmed', 49, 14, 'NO', 0),
(79, 'abc', 16, 16, 'YES', 3),
(80, 'def', 33, 33, 'YES', 0),
(81, 'def', 34, 34, 'YES', 0),
(82, 'def', 35, 35, 'YES', 0),
(83, 'def', 36, 36, 'YES', 0),
(84, 'def', 37, 37, 'YES', 0),
(85, 'def', 38, 38, 'YES', 0),
(86, 'def', 39, 39, 'YES', 0),
(87, 'def', 40, 40, 'YES', 0),
(88, 'def', 41, 41, 'YES', 0),
(89, 'def', 42, 42, 'YES', 0),
(90, 'def', 43, 43, 'YES', 0),
(91, 'abc', 4, 4, 'YES', 1),
(92, 'ahmed', 50, 2, 'NO', 0),
(94, 'def', 51, 15, 'NO', 0),
(95, 'abc', 52, 2, 'YES', 2),
(96, 'abc', 6, 6, 'YES', 3),
(97, 'abc', 53, 31, 'YES', 0),
(98, 'abc', 14, 14, 'YES', 2),
(99, 'abc', 22, 22, 'YES', 3);

-- --------------------------------------------------------

--
-- Table structure for table `user_info`
--

CREATE TABLE `user_info` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `account_level` int(11) NOT NULL DEFAULT 0,
  `coins` int(11) NOT NULL DEFAULT 1000,
  `power_pass` int(50) NOT NULL DEFAULT 0,
  `Date` date NOT NULL DEFAULT current_timestamp(),
  `xp` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_info`
--

INSERT INTO `user_info` (`username`, `password`, `account_level`, `coins`, `power_pass`, `Date`, `xp`) VALUES
('abc', '123', 15, 7249300, 5, '2025-01-27', 66),
('ahmed', '123', 0, 9750000, 0, '2025-01-24', 0),
('def', '123', 0, 8890000, 0, '2025-01-24', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `marketplace`
--
ALTER TABLE `marketplace`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_marketplace_user_info_seller` (`seller`),
  ADD KEY `fk_marketplace_user_info_bidder` (`bidder`),
  ADD KEY `fk_marketplace_player_data` (`player_ID`);

--
-- Indexes for table `match_history`
--
ALTER TABLE `match_history`
  ADD PRIMARY KEY (`ID`,`me`,`opponent`),
  ADD KEY `fk_mh_me` (`me`),
  ADD KEY `fk_mh_opponent` (`opponent`);

--
-- Indexes for table `messenger`
--
ALTER TABLE `messenger`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `sender` (`sender`),
  ADD KEY `receiver` (`receiver`);

--
-- Indexes for table `multiplayer`
--
ALTER TABLE `multiplayer`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `player_data`
--
ALTER TABLE `player_data`
  ADD PRIMARY KEY (`player_ID`,`player_base_id`);

--
-- Indexes for table `team_player`
--
ALTER TABLE `team_player`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_teamPlayer_userInfo` (`username`),
  ADD KEY `fk_teamplayer_playerdata` (`player_ID`,`player_base_id`);

--
-- Indexes for table `user_info`
--
ALTER TABLE `user_info`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `marketplace`
--
ALTER TABLE `marketplace`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `match_history`
--
ALTER TABLE `match_history`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `messenger`
--
ALTER TABLE `messenger`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `player_data`
--
ALTER TABLE `player_data`
  MODIFY `player_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT for table `team_player`
--
ALTER TABLE `team_player`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `marketplace`
--
ALTER TABLE `marketplace`
  ADD CONSTRAINT `fk_marketplace_player_data` FOREIGN KEY (`player_ID`) REFERENCES `player_data` (`player_ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_marketplace_user_info_bidder` FOREIGN KEY (`bidder`) REFERENCES `user_info` (`username`),
  ADD CONSTRAINT `fk_marketplace_user_info_seller` FOREIGN KEY (`seller`) REFERENCES `user_info` (`username`);

--
-- Constraints for table `match_history`
--
ALTER TABLE `match_history`
  ADD CONSTRAINT `fk_mh_me` FOREIGN KEY (`me`) REFERENCES `user_info` (`username`),
  ADD CONSTRAINT `fk_mh_opponent` FOREIGN KEY (`opponent`) REFERENCES `user_info` (`username`);

--
-- Constraints for table `messenger`
--
ALTER TABLE `messenger`
  ADD CONSTRAINT `messenger_ibfk_1` FOREIGN KEY (`sender`) REFERENCES `user_info` (`username`),
  ADD CONSTRAINT `messenger_ibfk_2` FOREIGN KEY (`receiver`) REFERENCES `user_info` (`username`);

--
-- Constraints for table `multiplayer`
--
ALTER TABLE `multiplayer`
  ADD CONSTRAINT `fk_multiplayer_userinfo` FOREIGN KEY (`username`) REFERENCES `user_info` (`username`) ON DELETE CASCADE;

--
-- Constraints for table `team_player`
--
ALTER TABLE `team_player`
  ADD CONSTRAINT `fk_teamPlayer_userInfo` FOREIGN KEY (`username`) REFERENCES `user_info` (`username`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_teamPlayer_user_info` FOREIGN KEY (`username`) REFERENCES `user_info` (`username`),
  ADD CONSTRAINT `fk_team_player_player_data` FOREIGN KEY (`player_ID`) REFERENCES `player_data` (`player_ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_teamplayer_playerdata` FOREIGN KEY (`player_ID`,`player_base_id`) REFERENCES `player_data` (`player_ID`, `player_base_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
