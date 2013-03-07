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
  `telefoonnummer` INT(11) NOT NULL ,
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
  `telefoonnummer` INT(11) NOT NULL ,
  PRIMARY KEY (`ID`) )
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
  `estimated_end` INT(11) NOT NULL ,
  `commentaar` VARCHAR(120) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `projecten_ID` INT(11) NOT NULL ,
  `voltooid` TINYINT(1) NOT NULL ,
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
-- Table `chrono_matic`.`registreerde_uren`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `chrono_matic`.`registreerde_uren` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `datum` INT(11) NOT NULL ,
  `begin_uur` INT(11) NOT NULL ,
  `eind_uur` INT(11) NOT NULL ,
  `taken_ID` INT(11) NOT NULL ,
  `pauze` TINYINT(1) NOT NULL ,
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
  `sessie_key` VARCHAR(101) NOT NULL ,
  `time_out` INT(11) NOT NULL ,
  `last_activity` INT(11) NOT NULL ,
  `begin` INT(11) NOT NULL ,
  `gebruiker_ID` INT(11) NOT NULL ,
  PRIMARY KEY (`sessie_key`) ,
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
INSERT INTO `chrono_matic`.`gebruikers` (`ID`, `naam`, `voornaam`, `gebruikersnaam`, `passwoord`, `email`, `telefoonnummer`) VALUES (1, 'Possemiers', 'Philippe', 'possemiersp', 'pass', 'philippe.possemiers@artesis.be', 0903696969);
INSERT INTO `chrono_matic`.`gebruikers` (`ID`, `naam`, `voornaam`, `gebruikersnaam`, `passwoord`, `email`, `telefoonnummer`) VALUES (2, 'Coutrin', 'Olga', 'coutrino', 'pass', 'olga.coutrin@artesis.be', 0498113720);

COMMIT;

-- -----------------------------------------------------
-- Data for table `chrono_matic`.`opdrachtgevers`
-- -----------------------------------------------------
START TRANSACTION;
USE `chrono_matic`;
INSERT INTO `chrono_matic`.`opdrachtgevers` (`ID`, `bedrijfsnaam`, `naam`, `voornaam`, `email`, `telefoonnummer`) VALUES (1, 'Apple', 'Jobs', 'Steve', 'steve@apple.com', 555066554);
INSERT INTO `chrono_matic`.`opdrachtgevers` (`ID`, `bedrijfsnaam`, `naam`, `voornaam`, `email`, `telefoonnummer`) VALUES (2, 'Microsoft', 'Grenouille', 'Jean-Claude', 'jean@microsoft.com', 555648524);

COMMIT;

-- -----------------------------------------------------
-- Data for table `chrono_matic`.`projecten`
-- -----------------------------------------------------
START TRANSACTION;
USE `chrono_matic`;
INSERT INTO `chrono_matic`.`projecten` (`ID`, `naam`, `start_datum`, `eind_datum`, `gebruikers_ID`, `opdrachtgevers_ID`) VALUES (1, 'Java', 1349499600, 1355464800, 1, 1);
INSERT INTO `chrono_matic`.`projecten` (`ID`, `naam`, `start_datum`, `eind_datum`, `gebruikers_ID`, `opdrachtgevers_ID`) VALUES (2, 'C#', 1352268000, 1360821600, 2, 2);

COMMIT;

-- -----------------------------------------------------
-- Data for table `chrono_matic`.`taken`
-- -----------------------------------------------------
START TRANSACTION;
USE `chrono_matic`;
INSERT INTO `chrono_matic`.`taken` (`ID`, `begin_tijd`, `estimated_end`, `commentaar`, `projecten_ID`, `voltooid`) VALUES (1, 1350277200, 1350709200, 'JSP', 1, NULL);
INSERT INTO `chrono_matic`.`taken` (`ID`, `begin_tijd`, `estimated_end`, `commentaar`, `projecten_ID`, `voltooid`) VALUES (2, 1352527200, 1353391200, 'array', 2, NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `chrono_matic`.`registreerde_uren`
-- -----------------------------------------------------
START TRANSACTION;
USE `chrono_matic`;
INSERT INTO `chrono_matic`.`registreerde_uren` (`ID`, `datum`, `begin_uur`, `eind_uur`, `taken_ID`, `pauze`) VALUES (1, 1350277200, 1350322200, 1350343800, 1, NULL);
INSERT INTO `chrono_matic`.`registreerde_uren` (`ID`, `datum`, `begin_uur`, `eind_uur`, `taken_ID`, `pauze`) VALUES (2, 1353304800, 1353361500, 1353380725, 2, NULL);

COMMIT;
