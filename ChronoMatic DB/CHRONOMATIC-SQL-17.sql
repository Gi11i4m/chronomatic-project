SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `chrono_matic` ;
CREATE SCHEMA IF NOT EXISTS `chrono_matic` DEFAULT CHARACTER SET utf8 ;
USE `chrono_matic` ;

-- -----------------------------------------------------
-- Table `chrono_matic`.`gebruikers`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `chrono_matic`.`gebruikers` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `naam` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL ,
  `voornaam` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL ,
  `gebruikersnaam` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `passwoord` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL ,
  `email` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL ,
  `telefoonnummer` VARCHAR(20) NULL ,
  `hash` VARCHAR(45) NULL ,
  `validated` TINYINT(1) NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB
AUTO_INCREMENT = 100
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `chrono_matic`.`opdrachtgevers`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `chrono_matic`.`opdrachtgevers` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `bedrijfsnaam` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `naam` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `voornaam` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `email` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `telefoonnummer` VARCHAR(45) NOT NULL ,
  `gebruikers_ID` INT(11) NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_opdrachtgevers_gebruikers1_idx` (`gebruikers_ID` ASC) ,
  CONSTRAINT `fk_opdrachtgevers_gebruikers1`
    FOREIGN KEY (`gebruikers_ID` )
    REFERENCES `chrono_matic`.`gebruikers` (`ID` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 100
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `chrono_matic`.`projecten`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `chrono_matic`.`projecten` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `naam` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `start_datum` INT(11) NOT NULL ,
  `eind_datum` INT(11) NOT NULL ,
  `gebruikers_ID` INT(11) NOT NULL ,
  `opdrachtgevers_ID` INT(11) NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_projecten_gebruikers_idx` (`gebruikers_ID` ASC) ,
  INDEX `fk_projecten_opdrachtgevers1_idx` (`opdrachtgevers_ID` ASC) ,
  CONSTRAINT `fk_projecten_gebruikers`
    FOREIGN KEY (`gebruikers_ID` )
    REFERENCES `chrono_matic`.`gebruikers` (`ID` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_projecten_opdrachtgevers1`
    FOREIGN KEY (`opdrachtgevers_ID` )
    REFERENCES `chrono_matic`.`opdrachtgevers` (`ID` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 100
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `chrono_matic`.`taken`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `chrono_matic`.`taken` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `naam` VARCHAR(45) NOT NULL ,
  `begin_tijd` INT(11) NOT NULL ,
  `verwacht_eind` INT(11) NOT NULL ,
  `commentaar` VARCHAR(120) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `voltooid` TINYINT(1) NOT NULL ,
  `projecten_ID` INT(11) NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_taken_projecten1_idx` (`projecten_ID` ASC) ,
  CONSTRAINT `fk_taken_projecten1`
    FOREIGN KEY (`projecten_ID` )
    REFERENCES `chrono_matic`.`projecten` (`ID` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 100
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `chrono_matic`.`tijdspanne`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `chrono_matic`.`tijdspanne` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `begin_uur` INT(11) NOT NULL ,
  `eind_uur` INT(11) NOT NULL ,
  `pauze` TINYINT(1) NOT NULL ,
  `taken_ID` INT(11) NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_registreerde_uren_taken1_idx` (`taken_ID` ASC) ,
  CONSTRAINT `fk_registreerde_uren_taken1`
    FOREIGN KEY (`taken_ID` )
    REFERENCES `chrono_matic`.`taken` (`ID` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 100
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `chrono_matic`.`sessies`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `chrono_matic`.`sessies` (
  `session_key` VARCHAR(101) NOT NULL ,
  `time_out` INT(11) NOT NULL ,
  `last_activity` INT(11) NOT NULL ,
  `begin` INT(11) NOT NULL ,
  `gebruiker_ID` INT(11) NOT NULL ,
  PRIMARY KEY (`session_key`) ,
  INDEX `fk_sessies_gebruikers1_idx` (`gebruiker_ID` ASC) ,
  CONSTRAINT `fk_sessies_gebruikers1`
    FOREIGN KEY (`gebruiker_ID` )
    REFERENCES `chrono_matic`.`gebruikers` (`ID` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `chrono_matic`.`gebruikers`
-- -----------------------------------------------------
START TRANSACTION;
USE `chrono_matic`;
INSERT INTO `chrono_matic`.`gebruikers` (`ID`, `naam`, `voornaam`, `gebruikersnaam`, `passwoord`, `email`, `telefoonnummer`, `hash`, `validated`) VALUES (101, 'SAPSmurf', 'Koen', 'p', '4ba7e14b8176fb8163d903fbca711799', 'damenstijn@gmail.com', '0000000000', NULL, 1);
INSERT INTO `chrono_matic`.`gebruikers` (`ID`, `naam`, `voornaam`, `gebruikersnaam`, `passwoord`, `email`, `telefoonnummer`, `hash`, `validated`) VALUES (102, 'Naam1', 'Voornaam1', 'leeg', '4ba7e14b8176fb8163d903fbca711799', 'damenstijn@gmail.com', '0000000000', NULL, 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `chrono_matic`.`opdrachtgevers`
-- -----------------------------------------------------
START TRANSACTION;
USE `chrono_matic`;
INSERT INTO `chrono_matic`.`opdrachtgevers` (`ID`, `bedrijfsnaam`, `naam`, `voornaam`, `email`, `telefoonnummer`, `gebruikers_ID`) VALUES (100, 'Apple', 'Jobs', 'Steve', 'steve@apple.com', '555066554', 101);
INSERT INTO `chrono_matic`.`opdrachtgevers` (`ID`, `bedrijfsnaam`, `naam`, `voornaam`, `email`, `telefoonnummer`, `gebruikers_ID`) VALUES (102, 'Microsoft', 'Grenouille', 'Jean-Claude', 'jean@microsoft.com', '555648524', 101);
INSERT INTO `chrono_matic`.`opdrachtgevers` (`ID`, `bedrijfsnaam`, `naam`, `voornaam`, `email`, `telefoonnummer`, `gebruikers_ID`) VALUES (103, 'Google', 'Uitdebroek', 'Piet', 'piet@gmail.com', '000045454', 101);
INSERT INTO `chrono_matic`.`opdrachtgevers` (`ID`, `bedrijfsnaam`, `naam`, `voornaam`, `email`, `telefoonnummer`, `gebruikers_ID`) VALUES (104, 'Samsung', 'Hiruyashi', 'Ntongo', 'ntongo@samsung.com', '090369696', 101);
INSERT INTO `chrono_matic`.`opdrachtgevers` (`ID`, `bedrijfsnaam`, `naam`, `voornaam`, `email`, `telefoonnummer`, `gebruikers_ID`) VALUES (105, 'Philips', 'Philips', 'Gerard', 'gerard@philips.com', '111111111', 101);
INSERT INTO `chrono_matic`.`opdrachtgevers` (`ID`, `bedrijfsnaam`, `naam`, `voornaam`, `email`, `telefoonnummer`, `gebruikers_ID`) VALUES (106, 'Sony', 'Chubachi', 'Ryoji', 'baas@sony.com', '99988741112', 101);

COMMIT;

-- -----------------------------------------------------
-- Data for table `chrono_matic`.`projecten`
-- -----------------------------------------------------
START TRANSACTION;
USE `chrono_matic`;
INSERT INTO `chrono_matic`.`projecten` (`ID`, `naam`, `start_datum`, `eind_datum`, `gebruikers_ID`, `opdrachtgevers_ID`) VALUES (101, 'Java', 1349845200, 1355724000, 101, 103);
INSERT INTO `chrono_matic`.`projecten` (`ID`, `naam`, `start_datum`, `eind_datum`, `gebruikers_ID`, `opdrachtgevers_ID`) VALUES (102, 'Licht module', 1349499600, 1357020000, 101, 105);
INSERT INTO `chrono_matic`.`projecten` (`ID`, `naam`, `start_datum`, `eind_datum`, `gebruikers_ID`, `opdrachtgevers_ID`) VALUES (103, 'Android App', 1349672400, 1357020000, 101, 103);
INSERT INTO `chrono_matic`.`projecten` (`ID`, `naam`, `start_datum`, `eind_datum`, `gebruikers_ID`, `opdrachtgevers_ID`) VALUES (104, 'Galaxy S3', 1357020000, 1420092000, 101, 104);

COMMIT;

-- -----------------------------------------------------
-- Data for table `chrono_matic`.`taken`
-- -----------------------------------------------------
START TRANSACTION;
USE `chrono_matic`;
INSERT INTO `chrono_matic`.`taken` (`ID`, `naam`, `begin_tijd`, `verwacht_eind`, `commentaar`, `voltooid`, `projecten_ID`) VALUES (101, 'Oefen1', 1349845200, 1355292000, 'Oefening reeks 1', false, 101);
INSERT INTO `chrono_matic`.`taken` (`ID`, `naam`, `begin_tijd`, `verwacht_eind`, `commentaar`, `voltooid`, `projecten_ID`) VALUES (102, 'Oefen2', 1349845200, 1349845200, 'Oefening reeks 2', true, 101);
INSERT INTO `chrono_matic`.`taken` (`ID`, `naam`, `begin_tijd`, `verwacht_eind`, `commentaar`, `voltooid`, `projecten_ID`) VALUES (103, 'App1', 1349672400, 1349672400, 'Begin app', true, 103);
INSERT INTO `chrono_matic`.`taken` (`ID`, `naam`, `begin_tijd`, `verwacht_eind`, `commentaar`, `voltooid`, `projecten_ID`) VALUES (104, 'App2', 1355119200, 1356847200, 'App maken 2', false, 103);

COMMIT;

-- -----------------------------------------------------
-- Data for table `chrono_matic`.`tijdspanne`
-- -----------------------------------------------------
START TRANSACTION;
USE `chrono_matic`;
INSERT INTO `chrono_matic`.`tijdspanne` (`ID`, `begin_uur`, `eind_uur`, `pauze`, `taken_ID`) VALUES (101, 1349881200, 1349909100, false, 102);
INSERT INTO `chrono_matic`.`tijdspanne` (`ID`, `begin_uur`, `eind_uur`, `pauze`, `taken_ID`) VALUES (102, 1349888400, 1349892000, true, 102);
INSERT INTO `chrono_matic`.`tijdspanne` (`ID`, `begin_uur`, `eind_uur`, `pauze`, `taken_ID`) VALUES (103, 1349730000, 1349744400, false, 103);
INSERT INTO `chrono_matic`.`tijdspanne` (`ID`, `begin_uur`, `eind_uur`, `pauze`, `taken_ID`) VALUES (104, 1355173200, 1355187600, false, 104);
INSERT INTO `chrono_matic`.`tijdspanne` (`ID`, `begin_uur`, `eind_uur`, `pauze`, `taken_ID`) VALUES (105, 1355175060, 1355175960, true, 104);
INSERT INTO `chrono_matic`.`tijdspanne` (`ID`, `begin_uur`, `eind_uur`, `pauze`, `taken_ID`) VALUES (106, 1355184612, 1355185855, true, 104);

COMMIT;
