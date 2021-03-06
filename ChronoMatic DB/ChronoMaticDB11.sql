SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `chrono_matic` DEFAULT CHARACTER SET utf8 ;
USE `chrono_matic` ;

-- -----------------------------------------------------
-- Table `chrono_matic`.`gebruikers`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `chrono_matic`.`gebruikers` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `naam` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `voornaam` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `gebruikersnaam` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `passwoord` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `email` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `telefoonnummer` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
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
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
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
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_projecten_opdrachtgevers1`
    FOREIGN KEY (`opdrachtgevers_ID` )
    REFERENCES `chrono_matic`.`opdrachtgevers` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `chrono_matic`.`taken`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `chrono_matic`.`taken` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
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
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
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
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
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
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
INSERT INTO `chrono_matic`.`gebruikers` (`ID`, `naam`, `voornaam`, `gebruikersnaam`, `passwoord`, `email`, `telefoonnummer`) VALUES (1, 'Possemiers', 'Philippe', 'possemiersp', 'pass', 'philippe.possemiers@artesis.be', '0903696969');
INSERT INTO `chrono_matic`.`gebruikers` (`ID`, `naam`, `voornaam`, `gebruikersnaam`, `passwoord`, `email`, `telefoonnummer`) VALUES (2, 'Coutrin', 'Olga', 'c', 'c', 'olga.coutrin@artesis.be', '0498113720');
INSERT INTO `chrono_matic`.`gebruikers` (`ID`, `naam`, `voornaam`, `gebruikersnaam`, `passwoord`, `email`, `telefoonnummer`) VALUES (3, 'Naam1', 'Voornaam1', 'Username1', 'Password1', 'Email1', '0000000000');

COMMIT;

-- -----------------------------------------------------
-- Data for table `chrono_matic`.`opdrachtgevers`
-- -----------------------------------------------------
START TRANSACTION;
USE `chrono_matic`;
INSERT INTO `chrono_matic`.`opdrachtgevers` (`ID`, `bedrijfsnaam`, `naam`, `voornaam`, `email`, `telefoonnummer`, `gebruikers_ID`) VALUES (1, 'Apple', 'Jobs', 'Steve', 'steve@apple.com', '555066554', 1);
INSERT INTO `chrono_matic`.`opdrachtgevers` (`ID`, `bedrijfsnaam`, `naam`, `voornaam`, `email`, `telefoonnummer`, `gebruikers_ID`) VALUES (2, 'Microsoft', 'Grenouille', 'Jean-Claude', 'jean@microsoft.com', '555648524', 2);
INSERT INTO `chrono_matic`.`opdrachtgevers` (`ID`, `bedrijfsnaam`, `naam`, `voornaam`, `email`, `telefoonnummer`, `gebruikers_ID`) VALUES (3, 'Google', 'Uitdebroek', 'Piet', 'piet@gmail.com', '000045454', 1);
INSERT INTO `chrono_matic`.`opdrachtgevers` (`ID`, `bedrijfsnaam`, `naam`, `voornaam`, `email`, `telefoonnummer`, `gebruikers_ID`) VALUES (4, 'Samsung', 'Hiruyashi', 'Ntongo', 'ntongo@samsung.com', '090369696', 1);
INSERT INTO `chrono_matic`.`opdrachtgevers` (`ID`, `bedrijfsnaam`, `naam`, `voornaam`, `email`, `telefoonnummer`, `gebruikers_ID`) VALUES (5, 'Philips', 'Philips', 'Gerard', 'gerard@philips.com', '111111111', 2);
INSERT INTO `chrono_matic`.`opdrachtgevers` (`ID`, `bedrijfsnaam`, `naam`, `voornaam`, `email`, `telefoonnummer`, `gebruikers_ID`) VALUES (6, 'Sony', 'Chubachi', 'Ryoji', 'baas@sony.com', '99988741112', 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `chrono_matic`.`projecten`
-- -----------------------------------------------------
START TRANSACTION;
USE `chrono_matic`;
INSERT INTO `chrono_matic`.`projecten` (`ID`, `naam`, `start_datum`, `eind_datum`, `gebruikers_ID`, `opdrachtgevers_ID`) VALUES (1, 'Java', 1349499600, 1355464800, 1, 1);
INSERT INTO `chrono_matic`.`projecten` (`ID`, `naam`, `start_datum`, `eind_datum`, `gebruikers_ID`, `opdrachtgevers_ID`) VALUES (2, 'C#', 1352268000, 1360821600, 2, 2);
INSERT INTO `chrono_matic`.`projecten` (`ID`, `naam`, `start_datum`, `eind_datum`, `gebruikers_ID`, `opdrachtgevers_ID`) VALUES (3, 'Android', 1353952800, 1367427600, 1, 1);
INSERT INTO `chrono_matic`.`projecten` (`ID`, `naam`, `start_datum`, `eind_datum`, `gebruikers_ID`, `opdrachtgevers_ID`) VALUES (4, 'Apple', 1355232600, 1359580500, 1, 3);
INSERT INTO `chrono_matic`.`projecten` (`ID`, `naam`, `start_datum`, `eind_datum`, `gebruikers_ID`, `opdrachtgevers_ID`) VALUES (5, 'Windows', 1359842400, 1393704000, 1, 1);
INSERT INTO `chrono_matic`.`projecten` (`ID`, `naam`, `start_datum`, `eind_datum`, `gebruikers_ID`, `opdrachtgevers_ID`) VALUES (6, 'L&M', 1353823200, 1359698400, 2, 2);
INSERT INTO `chrono_matic`.`projecten` (`ID`, `naam`, `start_datum`, `eind_datum`, `gebruikers_ID`, `opdrachtgevers_ID`) VALUES (7, 'VAIO gate build', 1353909600, 1354255200, 1, 6);
INSERT INTO `chrono_matic`.`projecten` (`ID`, `naam`, `start_datum`, `eind_datum`, `gebruikers_ID`, `opdrachtgevers_ID`) VALUES (8, 'Lightning Module', 1353391200, 1354255200, 1, 5);

COMMIT;

-- -----------------------------------------------------
-- Data for table `chrono_matic`.`taken`
-- -----------------------------------------------------
START TRANSACTION;
USE `chrono_matic`;
INSERT INTO `chrono_matic`.`taken` (`ID`, `begin_tijd`, `verwacht_eind`, `commentaar`, `voltooid`, `projecten_ID`) VALUES (1, 1350277200, 1350709200, 'JSP', false, 1);
INSERT INTO `chrono_matic`.`taken` (`ID`, `begin_tijd`, `verwacht_eind`, `commentaar`, `voltooid`, `projecten_ID`) VALUES (2, 1352527200, 1353391200, 'array', false, 2);
INSERT INTO `chrono_matic`.`taken` (`ID`, `begin_tijd`, `verwacht_eind`, `commentaar`, `voltooid`, `projecten_ID`) VALUES (3, 1353535200, 1353551400, 'Module1', true, 8);

COMMIT;

-- -----------------------------------------------------
-- Data for table `chrono_matic`.`tijdspanne`
-- -----------------------------------------------------
START TRANSACTION;
USE `chrono_matic`;
INSERT INTO `chrono_matic`.`tijdspanne` (`ID`, `begin_uur`, `eind_uur`, `pauze`, `taken_ID`) VALUES (1, 1350322200, 1350343800, false, 1);
INSERT INTO `chrono_matic`.`tijdspanne` (`ID`, `begin_uur`, `eind_uur`, `pauze`, `taken_ID`) VALUES (2, 1353361500, 1353380725, false, 2);

COMMIT;
