CREATE  DATABASE `exposition_calendar_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE TABLE `accounts` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL DEFAULT 'user',
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `cards` (
  `card_id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(16) NOT NULL,
  `cvv` int(11) NOT NULL,
  `balance` double NOT NULL DEFAULT '2000',
  `holder` varchar(45) NOT NULL,
  `month` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`card_id`),
  KEY `account_id_idx` (`card_id`),
  CONSTRAINT `card_id` FOREIGN KEY (`card_id`) REFERENCES `accounts` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `expohalls` (
  `expohall_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` mediumtext NOT NULL,
  PRIMARY KEY (`expohall_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `expositions` (
  `exposition_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `date_from` date NOT NULL,
  `date_to` date NOT NULL,
  `theme` varchar(45) NOT NULL,
  `ticket_price` decimal(10,0) NOT NULL,
  `expohall_id` int(11) DEFAULT NULL,
  `picture` mediumtext NOT NULL,
  `description` mediumtext NOT NULL,
  `begin_time` time NOT NULL,
  PRIMARY KEY (`exposition_id`),
  UNIQUE KEY `exposition_id_UNIQUE` (`exposition_id`),
  KEY `expohall_id_idx` (`expohall_id`),
  CONSTRAINT `expohall_id` FOREIGN KEY (`expohall_id`) REFERENCES `expohalls` (`expohall_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `orders` (
  `order_key` varchar(45) NOT NULL,
  `account_id` int(11) NOT NULL,
  `exposition_id` int(11) NOT NULL,
  `tickets_number` int(11) NOT NULL,
  `date_valid` date NOT NULL,
  PRIMARY KEY (`order_key`),
  KEY `account_id_idx` (`account_id`),
  KEY `exposition_id_idx` (`exposition_id`),
  CONSTRAINT `account_id` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `exposition_id` FOREIGN KEY (`exposition_id`) REFERENCES `expositions` (`exposition_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `exposition_calendar_db`.`accounts` (`first_name`, `last_name`, `login`, `password`, `role`, `email`) VALUES ('admin', 'admin', 'root', 'root', 'admin', 'expocalendar2017@gmail.com');
 