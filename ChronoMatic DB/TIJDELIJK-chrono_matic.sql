-- phpMyAdmin SQL Dump
-- version 3.5.7
-- http://www.phpmyadmin.net
--
-- Host: 127.7.94.129:3306
-- Generation Time: Mar 20, 2013 at 01:29 PM
-- Server version: 5.1.67
-- PHP Version: 5.3.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `chrono_matic`
--

-- --------------------------------------------------------

--
-- Table structure for table `gebruikers`
--

CREATE TABLE IF NOT EXISTS `gebruikers` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `voornaam` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gebruikersnaam` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `passwoord` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `telefoonnummer` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Dumping data for table `gebruikers`
--

INSERT INTO `gebruikers` (`ID`, `naam`, `voornaam`, `gebruikersnaam`, `passwoord`, `email`, `telefoonnummer`) VALUES
(1, 'Possemiers', 'Philippe', 'p', 'p', 'philippe.possemiers@artesis.be', '0903696969'),
(2, 'Naam1', 'Voornaam1', 'leeg', 'leeg', 'Email1@email.email', '0000000000'),
(7, 'Damen', 'Stijn', 'damenstijn@gmail.com', NULL, 'email@mail.com', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `opdrachtgevers`
--

CREATE TABLE IF NOT EXISTS `opdrachtgevers` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `bedrijfsnaam` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `naam` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `voornaam` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `telefoonnummer` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `gebruikers_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_opdrachtgevers_gebruikers1_idx` (`gebruikers_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=16 ;

--
-- Dumping data for table `opdrachtgevers`
--

INSERT INTO `opdrachtgevers` (`ID`, `bedrijfsnaam`, `naam`, `voornaam`, `email`, `telefoonnummer`, `gebruikers_ID`) VALUES
(1, 'Apple', 'Jobs', 'Steve', 'steve@apple.com', '555066554', 1),
(2, 'Microsoft', 'Grenouille', 'Jean-Claude', 'jean@microsoft.com', '555648524', 1),
(3, 'Google', 'Uitdebroek', 'Piet', 'piet@gmail.com', '000045454', 1),
(4, 'Samsung', 'Hiruyashi', 'Ntongo', 'ntongo@samsung.com', '090369696', 1),
(5, 'Philips', 'Philips', 'Gerard', 'gerard@philips.com', '111111111', 1),
(6, 'Sony', 'Chubachi', 'Ryoji', 'baas@sony.com', '99988741112', 1),
(7, 'BurningBuilding', 'Janssens', 'Henk', 'gay@lord.com', '1337', 1),
(8, 'Samsung', 'Hiruyashi', 'Ntongo', 'ntongo@samsung.co', '090369696', 1),
(9, 'Samsung', 'Hiruyashi', 'Ntongo', 'ntongo@samsung.co', '090369696', 1),
(10, 'Samsung', 'Hiruyashi', 'Ntongo', 'ntongo@samsung.co', '090369696', 1),
(11, 'Samsung', 'Hiruyashi', 'Ntongo', 'ntongo@samsung.co', '090369696', 1),
(12, 'Samsung', 'Hiruyashi', 'Ntongo', 'ntongo@samsung.co', '090369696', 1),
(14, 'joijoi', 'ijio', 'jijij', 'jiojoi@dd.dd', '084085', 7),
(15, 'Samsung', 'Hiruyashi', 'Ntongo', 'ntongo@samsung.com', '090369696', 1);

-- --------------------------------------------------------

--
-- Table structure for table `projecten`
--

CREATE TABLE IF NOT EXISTS `projecten` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `start_datum` int(11) NOT NULL,
  `eind_datum` int(11) NOT NULL,
  `gebruikers_ID` int(11) NOT NULL,
  `opdrachtgevers_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_projecten_gebruikers_idx` (`gebruikers_ID`),
  KEY `fk_projecten_opdrachtgevers1_idx` (`opdrachtgevers_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=12 ;

--
-- Dumping data for table `projecten`
--

INSERT INTO `projecten` (`ID`, `naam`, `start_datum`, `eind_datum`, `gebruikers_ID`, `opdrachtgevers_ID`) VALUES
(1, 'Java', 1349820000, 1418770800, 1, 3),
(2, 'Licht module', 1349499600, 1357020000, 1, 5),
(3, 'Android App', 1349672400, 1357020000, 1, 3),
(4, 'Galaxy S3', 1420066800, 1420066800, 1, 4),
(5, 'Testproject', 1363561200, 1426633200, 1, 5),
(6, 'lololopp', 1363561200, 1426633200, 1, 6),
(9, 'piet', 1363561200, 1426633200, 7, 14),
(11, 'neger', 1363647600, 1426719600, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `sessies`
--

CREATE TABLE IF NOT EXISTS `sessies` (
  `session_key` varchar(101) COLLATE utf8_unicode_ci NOT NULL,
  `time_out` int(11) NOT NULL,
  `last_activity` int(11) NOT NULL,
  `begin` int(11) NOT NULL,
  `gebruiker_ID` int(11) NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `fk_sessies_gebruikers1_idx` (`gebruiker_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `sessies`
--

INSERT INTO `sessies` (`session_key`, `time_out`, `last_activity`, `begin`, `gebruiker_ID`) VALUES
('10v86adv2tp0aprthbjj6e5dd1', 3600, 1363695738, 1363695738, 1),
('1d6vitaeh5a83ju8eenkrl0aq3', 3600, 1363695766, 1363695766, 1),
('1sn7mbododrou37kcs577189p7', 3600, 1363619368, 1363619368, 7),
('1srj082vj573qbngv3ce96fv5e', 3600, 1363620852, 1363620852, 7),
('2sq7bjpgb702hd71n06bdg68m7', 3600, 1363621237, 1363621237, 1),
('3im7prmu4qrba7vbee2s2qosrn', 3600, 1363686834, 1363686834, 7),
('4ndue8382pdstbvnn64rpuk8ds', 3600, 1363683119, 1363683119, 1),
('5067srio8hdrpj7rn13j0lhoon', 3600, 1363621682, 1363621682, 7),
('5jbp5097ubna2qsuabdfgis731', 3600, 1363696288, 1363696288, 1),
('6764ac133tn4e4afqk634veqs', 3600, 1363633119, 1363633119, 1),
('67f0p1u1mrnoapadfbt1hrtno4', 3600, 1363694898, 1363694898, 1),
('6a5fbd2lakd8aimevg02o2g8vu', 3600, 1363693669, 1363693669, 7),
('6nuufujlhvhdpigdqfte4o1ttv', 3600, 1363695231, 1363695231, 1),
('6o6j5gcjmuqhgd70gsrtbqb72i', 3600, 1363621504, 1363621504, 1),
('6tg64o6rtali38qmigu9ju8rd', 3600, 1363695614, 1363695614, 1),
('80gfcuccj660la20bof04tofjh', 3600, 1363619100, 1363619100, 7),
('8a93vinj30f1jirtel5l9k1qpm', 3600, 1363694932, 1363694932, 1),
('8oaci7f4svei3ad49t2f3vfqtf', 3600, 1363685598, 1363685598, 1),
('90r0a4jdjudrlht7dbltqcu3mk', 3600, 1363696503, 1363696503, 1),
('9g4s47icaiq5dbcdgq5hudg786', 3600, 1363646154, 1363646154, 1),
('9l9r0m2i297tppukrofl9m8s1m', 3600, 1363621113, 1363621113, 1),
('9opf7qun6ta5iv9l0hllu25hpn', 3600, 1363621290, 1363621290, 1),
('b6aka9fei5as8odjdbqbj2qsk2', 3600, 1363687628, 1363687628, 1),
('c3nrqm9b2bo2m0bgrjpf9ag1ug', 3600, 1363621528, 1363621528, 1),
('c6oja3crs3gboi2e08fmv6drle', 3600, 1363685606, 1363685606, 1),
('ce8j8qfkag6aoo92o7ll2hjb08', 3600, 1363769055, 1363769055, 1),
('cl3366dfu4tq0g8kgmdhpt2k1o', 3600, 1363688483, 1363688483, 1),
('dnnv21k5dib0hfohr2tc2mk104', 3600, 1363696290, 1363696290, 1),
('emu5ic013d9iuqjtodvfc3bndp', 3600, 1363685681, 1363685681, 1),
('g910ea3k5u7vdflsn9t1lvh0m3', 3600, 1363695690, 1363695690, 1),
('h8jcfcaoqjf8oh1nquv36d6bgt', 3600, 1363645866, 1363645866, 1),
('hb47jdt9ps4bksel8k34b91a4a', 3600, 1363621510, 1363621510, 1),
('ioebtkol8cuulmiserr9bvjiqc', 3600, 1363692584, 1363692584, 7),
('ivsar595i70g83qrlvb7jto73r', 3600, 1363618049, 1363618049, 1),
('jfuvivnhmf1l4r2cq122v36fqq', 3600, 1363702477, 1363702477, 1),
('jvhi830cf9ieu98relripdlust', 3600, 1363686976, 1363686976, 7),
('k1t2rrhee65b17ddhdcoa9momf', 3600, 1363695978, 1363695978, 1),
('k2fh7vhvflgbuso9lkadb4c20f', 3600, 1363619245, 1363619245, 7),
('k3ijidr8898dspnadg08eercj', 3600, 1363638368, 1363638368, 7),
('k6glvuofeblmuhp1jgsgiajk08', 3600, 1363702508, 1363702508, 1),
('kea0ptg6cr7bf2qjekgobtm8a0', 3600, 1363619196, 1363619196, 7),
('kgfvpvuqg0hprmkqlk99fav7c7', 3600, 1363692968, 1363692968, 7),
('kmvk6kf8ecrii585l53dlvhst9', 3600, 1363620687, 1363620687, 1),
('ls2o7g9jg3o3ee3eti0fqu8a0s', 3600, 1363620998, 1363620998, 1),
('m32j15pscie701qajtmr6f9jv6', 3600, 1363695898, 1363695898, 1),
('mhcaf2qk65ejpp6qff10h3dvog', 3600, 1363638778, 1363638778, 1),
('n0n5r1oj23mcuhonbrrf3ph8oe', 3600, 1363696541, 1363696541, 1),
('n3trvlalp6a292ru9p811ffkk4', 3600, 1363702550, 1363702550, 1),
('nfgemrt253fo63usct6bv8sfm5', 3600, 1363632225, 1363632225, 7),
('nh5hur5d8v7t487tir7shfudte', 3600, 1363620906, 1363620906, 7),
('nslr4jgsv8ivjporv02eu1esld', 3600, 1363698851, 1363698851, 1),
('ockkk8urhqcp9k3oi0bo16e2f7', 3600, 1363621063, 1363621063, 1),
('oha7o9lffc4hvr31srr1d8t9oh', 3600, 1363620930, 1363620930, 1),
('pqvi0degl8adaetilvmqjkm90n', 3600, 1363456287, 1363456287, 1),
('q2e21rsdkibf1m7jp2hrqp8hfe', 3600, 1363693641, 1363693641, 7),
('qavvfqlj7chrmdms6krgkaa2e7', 3600, 1363603781, 1363603781, 1),
('r58moas780ev6bcjc3vvf97lcf', 3600, 1363568262, 1363568262, 1),
('r8pagmvo8mtjt1rs0bv0k7epk', 3600, 1363615825, 1363615825, 1),
('siss4tatn0r42uaes58i7pup7e', 3600, 1363576576, 1363576576, 1),
('t49lkaeedm14lsv797htqg2oci', 3600, 1363696469, 1363696469, 1),
('tbdrqvsiv2orngvglonukbl6md', 3600, 1363620981, 1363620981, 7),
('u7p83d6drsj9n9rp5ciroredqs', 3600, 1363695995, 1363695995, 1),
('u872595ha30eetshi55lq32agm', 3600, 1363371101, 1363371101, 1),
('urgn2kfbqjs4ib6f10bnuc0k3o', 3600, 1363620945, 1363620945, 7),
('v105tp1kge317eb2d4i6rlqde6', 3600, 1363701909, 1363701909, 1),
('v9g2saecr744up3iagdtj6gfrr', 3600, 1363621023, 1363621023, 1),
('vf6n2ch4fu1gh9ubdj0afnt9r3', 3600, 1363615734, 1363615734, 1);

-- --------------------------------------------------------

--
-- Table structure for table `taken`
--

CREATE TABLE IF NOT EXISTS `taken` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `begin_tijd` int(11) NOT NULL,
  `verwacht_eind` int(11) NOT NULL,
  `commentaar` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `voltooid` tinyint(1) NOT NULL,
  `projecten_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_taken_projecten1_idx` (`projecten_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=12 ;

--
-- Dumping data for table `taken`
--

INSERT INTO `taken` (`ID`, `naam`, `begin_tijd`, `verwacht_eind`, `commentaar`, `voltooid`, `projecten_ID`) VALUES
(1, 'Oefen1', 1349845200, 1355292000, 'Oefening reeks 1', 0, 1),
(2, 'Oefen2', 1349845200, 1349845200, 'Oefening reeks 2', 1, 1),
(3, 'App1', 1349672400, 1349672400, 'Begin app', 1, 3),
(4, 'App2', 1355119200, 1356847200, 'App maken 2', 0, 3),
(5, 'piemel', 1363561200, 1363647600, 'ekekek', 1, 5),
(6, 'pop', 1363561200, 1426633200, 'olo', 1, 6),
(7, 'Testtask1', 1363561200, 1426633200, ' ', 1, 5),
(8, 'lol', 1363561200, 1426633200, 'lol', 1, 5),
(9, 'azezaeza', 1363561200, 1426633200, 'lol', 1, 5),
(10, 'Testtaak2', 1363561200, 1363647600, 'lol', 1, 5),
(11, 'Testtaak3', 1363561200, 1395097200, 'll', 1, 5);

-- --------------------------------------------------------

--
-- Table structure for table `tijdspanne`
--

CREATE TABLE IF NOT EXISTS `tijdspanne` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `begin_uur` int(11) NOT NULL,
  `eind_uur` int(11) NOT NULL,
  `pauze` tinyint(1) NOT NULL,
  `taken_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_registreerde_uren_taken1_idx` (`taken_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Dumping data for table `tijdspanne`
--

INSERT INTO `tijdspanne` (`ID`, `begin_uur`, `eind_uur`, `pauze`, `taken_ID`) VALUES
(1, 1349881200, 1349909100, 0, 2),
(2, 1349888400, 1349892000, 1, 2),
(3, 1349730000, 1349744400, 0, 3),
(4, 1355173200, 1355187600, 0, 4),
(5, 1355175060, 1355175960, 1, 4),
(6, 1355184612, 1355185855, 1, 4),
(7, 1363702006, 1363702025, 0, 7);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `opdrachtgevers`
--
ALTER TABLE `opdrachtgevers`
  ADD CONSTRAINT `fk_opdrachtgevers_gebruikers1` FOREIGN KEY (`gebruikers_ID`) REFERENCES `gebruikers` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `projecten`
--
ALTER TABLE `projecten`
  ADD CONSTRAINT `fk_projecten_gebruikers` FOREIGN KEY (`gebruikers_ID`) REFERENCES `gebruikers` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_projecten_opdrachtgevers1` FOREIGN KEY (`opdrachtgevers_ID`) REFERENCES `opdrachtgevers` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sessies`
--
ALTER TABLE `sessies`
  ADD CONSTRAINT `fk_sessies_gebruikers1` FOREIGN KEY (`gebruiker_ID`) REFERENCES `gebruikers` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `taken`
--
ALTER TABLE `taken`
  ADD CONSTRAINT `fk_taken_projecten1` FOREIGN KEY (`projecten_ID`) REFERENCES `projecten` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tijdspanne`
--
ALTER TABLE `tijdspanne`
  ADD CONSTRAINT `fk_registreerde_uren_taken1` FOREIGN KEY (`taken_ID`) REFERENCES `taken` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
